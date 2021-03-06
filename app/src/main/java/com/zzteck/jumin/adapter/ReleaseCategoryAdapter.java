package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.BaseInfo;
import com.zzteck.jumin.bean.MainCategoryBean;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *
 */

public class ReleaseCategoryAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    public static final int VIEW_TYPE_CHAPTER = 1;
    public static final int VIEW_TYPE_SECTION = 2;

    private MainCategoryBean mainCategoryBean;

    private List<BaseInfo> dataInfos = new ArrayList<>();

    private int curExpandChapterIndex = -1;

    private Context mContext ;

    public ReleaseCategoryAdapter(Context context, MainCategoryBean mainCategoryBean) {
        this.mainCategoryBean = mainCategoryBean;
        this.mContext = context ;
        for(int i = 0 ; i < mainCategoryBean.getData().size() ;i++){
            MainCategoryBean.DataBean bean = mainCategoryBean.getData().get(i) ;
            bean.setIndex(i);
            dataInfos.add(bean);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == VIEW_TYPE_CHAPTER){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
            return new ItemHolder(itemView);
        }else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);
            return new ItemSectionHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == VIEW_TYPE_CHAPTER){
            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.itemView.setTag(position);
            itemHolder.ivArrow.setTag(position);

            MainCategoryBean.DataBean chapterInfo = (MainCategoryBean.DataBean) dataInfos.get(position);

            itemHolder.mTvTitle.setText(chapterInfo.getCatname());

            RequestOptions options = new RequestOptions()
                    .transform(new GlideCircleTransform(mContext))
                    .placeholder(R.mipmap.default_pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .load(Constants.PIC_HOST+chapterInfo.getIcon())
                    .apply(options)
                    .into(itemHolder.mIvTitleIcon);

            if(chapterInfo.getChildren().size() > 0){
                itemHolder.ivArrow.setVisibility(View.VISIBLE);
                if(curExpandChapterIndex == position){
                    itemHolder.ivArrow.setImageResource(R.mipmap.icon_xiala_nor);
                }else{
                    itemHolder.ivArrow.setImageResource(R.mipmap.icon_xiala_pre);
                }
            }else{
                itemHolder.ivArrow.setVisibility(View.INVISIBLE);
            }

        }else{
            ItemSectionHolder itemSectionHolder = (ItemSectionHolder) holder;
            itemSectionHolder.tvName.setTag(position);

            MainCategoryBean.DataBean.ChildrenBean sectionInfo = (MainCategoryBean.DataBean.ChildrenBean) dataInfos.get(position);
            itemSectionHolder.tvName.setText(sectionInfo.getCatname());
        }
    }

    //该方法只更改itemView的部分信息，不全部刷新
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if(payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads);
        }else{
            String str = (String) payloads.get(0);
            //更改view的tag
            if(str.equals("change_position")){
                if(getItemViewType(position) == VIEW_TYPE_CHAPTER){
                    ItemHolder itemHolder = (ItemHolder) holder;
                    itemHolder.itemView.setTag(position);
                    itemHolder.ivArrow.setTag(position);
                    //改变箭头方向
                    if(curExpandChapterIndex == position){
                        itemHolder.ivArrow.setImageResource(R.mipmap.icon_xiala_nor);
                    }else{
                        itemHolder.ivArrow.setImageResource(R.mipmap.icon_xiala_pre);
                    }
                }else{
                    ItemSectionHolder itemSectionHolder = (ItemSectionHolder) holder;
                    itemSectionHolder.tvName.setTag(position);
                }
            }
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        if(mainCategoryBean == null  || mainCategoryBean.getData() == null){
            return 0;
        }else{
            return dataInfos.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(dataInfos.get(position) instanceof MainCategoryBean.DataBean){
            return VIEW_TYPE_CHAPTER;
        }else if(dataInfos.get(position) instanceof MainCategoryBean.DataBean.ChildrenBean){
            return VIEW_TYPE_SECTION;
        }
        return super.getItemViewType(position);
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView ivArrow;
        public TextView mTvTitle ;

        public ImageView mIvTitleIcon ;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(ReleaseCategoryAdapter.this);
            mTvTitle = itemView.findViewById(R.id.tv_title_info) ;
            mIvTitleIcon = itemView.findViewById(R.id.iv_title_info) ;
            ivArrow = itemView.findViewById(R.id.iv_arrow);
            ivArrow.setOnClickListener(ReleaseCategoryAdapter.this);
        }
    }

    public class ItemSectionHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ItemSectionHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_section_name);
            //将创建的View注册点击事件
            tvName.setOnClickListener(ReleaseCategoryAdapter.this);
        }
    }


    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /** item里面有多个控件可以点击 */
    public enum ViewName {
        CHAPTER_ITEM,
        CHAPTER_ITEM_PRACTISE,
        SECTION_ITEM
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int chapterIndex, int sectionIndex,String catId,String subCatId,String name);
    }

    private String catId = "" ;

    private String subCatId  = "" ;

    private String mCategoryName ;

    @Override
    public void onClick(View v) {

        if (mOnItemClickListener != null) {

            int position = (int) v.getTag();
            ViewName viewName = ViewName.CHAPTER_ITEM;
            int chapterIndex = -1;
            int sectionIndex = -1;
            if(getItemViewType(position) == VIEW_TYPE_CHAPTER){
                MainCategoryBean.DataBean mainInfo = (MainCategoryBean.DataBean) dataInfos.get(position);

                catId = mainInfo.getCatid() ;
                chapterIndex = mainInfo.getIndex() ;
                sectionIndex = -1;
                if(v.getId() == R.id.iv_arrow){
                    viewName = ViewName.CHAPTER_ITEM_PRACTISE;
                }else{
                    viewName = ViewName.CHAPTER_ITEM;
                    if(mainInfo.getChildren().size() > 0){
                        if(chapterIndex == curExpandChapterIndex){
                            narrow(curExpandChapterIndex);
                        }else{
                            narrow(curExpandChapterIndex);
                            expand(chapterIndex);
                        }
                    }
                }
            }else if(getItemViewType(position) == VIEW_TYPE_SECTION){
                MainCategoryBean.DataBean.ChildrenBean sectionInfo = (MainCategoryBean.DataBean.ChildrenBean) dataInfos.get(position) ;
                viewName = ViewName.SECTION_ITEM ;
                subCatId = sectionInfo.getCatid() ;
                mCategoryName = sectionInfo.getCatname() ;
            }

            mOnItemClickListener.onClick(v, viewName, chapterIndex, sectionIndex,catId,subCatId,mCategoryName);
        }

    }

    /**
     * 展开某个item
     * @param chapterIndex
     */
    private void expand(int chapterIndex){
        dataInfos.addAll(chapterIndex+1, mainCategoryBean.getData().get(chapterIndex).getChildren());
        curExpandChapterIndex = chapterIndex;
       // notifyDataSetChanged();
        notifyItemRangeInserted(chapterIndex+1, mainCategoryBean.getData().get(chapterIndex).getChildren().size());
        notifyItemRangeChanged(0, getItemCount(), "change_position");
    }

    /**
     * 收起某个item
     * @param chapterIndex
     */
    private void narrow(int chapterIndex){
        if(chapterIndex != -1){
            int removeStart = chapterIndex + 1;
            int removeCount = 0;
            for(int i=removeStart; i< dataInfos.size() && getItemViewType(i) == VIEW_TYPE_SECTION; i++){
                removeCount++;
            }
            dataInfos.removeAll(mainCategoryBean.getData().get(chapterIndex).getChildren());
            curExpandChapterIndex = -1;
            notifyItemRangeRemoved(removeStart, removeCount);
            notifyItemRangeChanged(0, getItemCount(), "change_position");
        }
    }
}