package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.BaseInfo;
import com.zzteck.jumin.bean.Category2Bean;
import com.zzteck.jumin.bean.MainCategoryBean;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.GlideCircleTransform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 *
 */

public class MainCategoryAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    public static final int VIEW_TYPE_CHAPTER = 1;
    public static final int VIEW_TYPE_SECTION = 2;

    private Category2Bean mainCategoryBean;

    private List<BaseInfo> dataInfos = new ArrayList<>();

    private int curExpandChapterIndex = -1;

    private Context mContext ;

    public MainCategoryAdapter(Context context, Category2Bean category2Bean) {
        this.mainCategoryBean = category2Bean;
        this.mContext = context ;
        if(mainCategoryBean != null){
            gernationDataInfos() ;
        }
    }

    public void notifyCategoryAdapter(Category2Bean category2Bean){
        this.mainCategoryBean = category2Bean;
        if(mainCategoryBean != null){
            gernationDataInfos() ;
        }
        notifyDataSetChanged();
    }

    public List<List<Category2Bean.DataBean.ListBean>> splitList(List<Category2Bean.DataBean.ListBean> list, int len) {

        if (list == null || list.isEmpty() || len < 1) {
            return Collections.emptyList();
        }

        List<List<Category2Bean.DataBean.ListBean>> result = new ArrayList<>();

        int size = list.size();
        int count = (size + len - 1) / len;

        for (int i = 0; i < count; i++) {
            List<Category2Bean.DataBean.ListBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }

        return result;
    }


    private void gernationDataInfos(){

        for(int i = 0 ; i < mainCategoryBean.getData().getExtra().size();i++){
            Category2Bean.DataBean.ExtraBean bean = mainCategoryBean.getData().getExtra().get(i) ;
            List<List<Category2Bean.DataBean.ListBean>> lists = splitList(bean.getList(),3) ;

            for(int j = 0 ;j < lists.size() ;j++){
                if(j == 0){
                    bean.setMainList(lists.get(j));
                }else {
                    Category2Bean.DataBean.ColumData data1 = new Category2Bean.DataBean.ColumData(lists.get(j));
                    bean.getChildList().add(data1) ;

                }
            }

            bean.setIndex(i);
            dataInfos.add(bean);

           // bean.setChildList(splitList(bean.getList().subList(4,bean.getList().size()),3));
           // bean.setChildList(bean.getList().subList(4,bean.getList().size()));

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == VIEW_TYPE_CHAPTER){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_chapter, parent, false);
            return new ItemHolder(itemView);
        }else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_bak, parent, false);
            return new ItemSectionHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == VIEW_TYPE_CHAPTER){
            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.itemView.setTag(position);
            itemHolder.ivArrow.setTag(position);

            itemHolder.mTvFilterOne.setTag(position);
            itemHolder.mTvFilterTwo.setTag(position);
            itemHolder.mTvFilterThree.setTag(position);
            itemHolder.mLLMainItem.setTag(position);

            Category2Bean.DataBean.ExtraBean chapterInfo = (Category2Bean.DataBean.ExtraBean) dataInfos.get(position);

            itemHolder.mTvTitle.setText(chapterInfo.getCatname());

            RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.default_pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .load(Constants.PIC_HOST+chapterInfo.getIcon())
                    .apply(options)
                    .into(itemHolder.mIvTitleIcon);

            for(int i = 0 ;i < chapterInfo.getMainList().size() ;i++){
                itemHolder.mTextViews.get(i).setText(chapterInfo.getMainList().get(i).getCatname());
            }

            if(chapterInfo.getChildList().size() > 0){
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
            itemSectionHolder.mLLSection.setTag(position);

            Category2Bean.DataBean.ColumData columData = (Category2Bean.DataBean.ColumData) dataInfos.get(position);

            if(columData.getMainList() != null && columData.getMainList().size() == 1){
                itemSectionHolder.tvOne.setText(columData.getMainList().get(0).getCatname());
            }else if(columData.getMainList() != null && columData.getMainList().size() == 2){
                itemSectionHolder.tvOne.setText(columData.getMainList().get(0).getCatname());
                itemSectionHolder.tvTwo.setText(columData.getMainList().get(1).getCatname());
            }else if(columData.getMainList() != null && columData.getMainList().size() == 3){
                itemSectionHolder.tvOne.setText(columData.getMainList().get(0).getCatname());
                itemSectionHolder.tvTwo.setText(columData.getMainList().get(1).getCatname());
                itemSectionHolder.tvThree.setText(columData.getMainList().get(2).getCatname());
            }

            /*Category2Bean.DataBean.ExtraBean.ListBean sectionInfo = (Category2Bean.DataBean.ExtraBean.ListBean) dataInfos.get(position);
            if(!TextUtils.isEmpty(sectionInfo.getCatname())){
                itemSectionHolder.tvName.setText(sectionInfo.getCatname());
            }*/
        }
    }

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
                    itemSectionHolder.mLLSection.setTag(position);
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

        if(dataInfos.get(position) instanceof Category2Bean.DataBean.ExtraBean){
            return VIEW_TYPE_CHAPTER;
        }else if(dataInfos.get(position) instanceof Category2Bean.DataBean.ColumData){
            return VIEW_TYPE_SECTION;
        }
        return super.getItemViewType(position);

    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        public ImageView ivArrow;

        public TextView mTvTitle ;

        public ImageView mIvTitleIcon ;

        public TextView mTvFilterOne ;

        private TextView mTvFilterTwo ;

        private TextView mTvFilterThree ;

        private List<TextView> mTextViews  = new ArrayList<>();

        private LinearLayout mLLMainItem ;

        public ItemHolder(View itemView) {

            super(itemView);
          // itemView.setOnClickListener(MainCategoryAdapter.this);

            mLLMainItem = itemView.findViewById(R.id.ll_main_item) ;

            mTvFilterOne = itemView.findViewById(R.id.tv_filter_one) ;
            mTvFilterTwo = itemView.findViewById(R.id.tv_filter_two) ;
            mTvFilterThree = itemView.findViewById(R.id.tv_filter_three) ;

            mTextViews.add(mTvFilterOne) ;
            mTextViews.add(mTvFilterTwo) ;
            mTextViews.add(mTvFilterThree) ;

            mTvTitle = itemView.findViewById(R.id.tv_title_info) ;
            mIvTitleIcon = itemView.findViewById(R.id.iv_title_info) ;
            ivArrow = itemView.findViewById(R.id.iv_arrow);
            ivArrow.setOnClickListener(MainCategoryAdapter.this);


            mTvFilterOne.setOnClickListener(MainCategoryAdapter.this);
            mTvFilterTwo.setOnClickListener(MainCategoryAdapter.this);
            mTvFilterThree.setOnClickListener(MainCategoryAdapter.this);
            mLLMainItem.setOnClickListener(MainCategoryAdapter.this);

        }
    }

    public class ItemSectionHolder extends RecyclerView.ViewHolder {
        public TextView tvOne;
        public TextView tvTwo;
        public TextView tvThree;

        public LinearLayout mLLSection ;

        public ItemSectionHolder(View itemView) {
            super(itemView);
            mLLSection = itemView.findViewById(R.id.ll_section) ;
            tvOne = itemView.findViewById(R.id.tv_sub_one);
            tvTwo = itemView.findViewById(R.id.tv_sub_two);
            tvThree = itemView.findViewById(R.id.tv_sub_three);
            tvOne.setOnClickListener(MainCategoryAdapter.this);
            tvTwo.setOnClickListener(MainCategoryAdapter.this);
            tvThree.setOnClickListener(MainCategoryAdapter.this);
        }
    }


    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public enum ViewName {
        CHAPTER_ITEM,
        CHAPTER_ITEM_PRACTISE,
        SECTION_ITEM
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int chapterIndex, int sectionIndex, String catId, String subCatId, String name);
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
                Category2Bean.DataBean.ExtraBean mainInfo = (Category2Bean.DataBean.ExtraBean) dataInfos.get(position);

                switch (v.getId()){
                    case R.id.tv_filter_one :
                        viewName = ViewName.CHAPTER_ITEM ;
                        subCatId = mainInfo.getMainList().get(0).getCatid() ;
                        mCategoryName = mainInfo.getMainList().get(0).getCatname() ;
                        break ;
                    case R.id.tv_filter_two :
                        viewName = ViewName.CHAPTER_ITEM ;
                        subCatId = mainInfo.getMainList().get(1).getCatid() ;
                        mCategoryName = mainInfo.getMainList().get(1).getCatname() ;
                        break ;
                    case R.id.tv_filter_three :
                        viewName = ViewName.CHAPTER_ITEM ;
                        subCatId = mainInfo.getMainList().get(2).getCatid() ;
                        mCategoryName = mainInfo.getMainList().get(2).getCatname() ;
                        break ;
                    case R.id.ll_main_item :
                        //catId = mainInfo.getCatid() ;
                        chapterIndex = mainInfo.getIndex() ;
                        sectionIndex = -1;
                        if(v.getId() == R.id.iv_arrow){
                            viewName = ViewName.CHAPTER_ITEM_PRACTISE;
                        }else{
                            viewName = ViewName.CHAPTER_ITEM;
                            if(mainInfo.getChildList().size() > 0){
                                if(chapterIndex == curExpandChapterIndex){
                                    narrow(curExpandChapterIndex);
                                }else{
                                    narrow(curExpandChapterIndex);
                                    expand(chapterIndex);
                                }
                            }
                        }
                        break ;
                }

            }else if(getItemViewType(position) == VIEW_TYPE_SECTION){

                Category2Bean.DataBean.ColumData columData = (Category2Bean.DataBean.ColumData) dataInfos.get(position);

                switch (v.getId()){
                    case R.id.tv_sub_one :
                        viewName = ViewName.SECTION_ITEM ;
                        subCatId = columData.getMainList().get(0).getCatid() ;
                        mCategoryName = columData.getMainList().get(0).getCatname() ;
                        break ;
                    case R.id.tv_sub_two :
                        viewName = ViewName.SECTION_ITEM ;
                        subCatId = columData.getMainList().get(0).getCatid() ;
                        mCategoryName = columData.getMainList().get(0).getCatname() ;
                        break ;
                    case R.id.tv_sub_three :
                        viewName = ViewName.SECTION_ITEM ;
                        subCatId = columData.getMainList().get(0).getCatid() ;
                        mCategoryName = columData.getMainList().get(0).getCatname() ;
                        break ;
                }
            }

            mOnItemClickListener.onClick(v, viewName, chapterIndex, sectionIndex,catId,subCatId,mCategoryName);
        }

    }

    /**
     * 展开某个item
     * @param chapterIndex
     */
    private void expand(int chapterIndex){
        dataInfos.addAll(chapterIndex+1, mainCategoryBean.getData().getExtra().get(chapterIndex).getChildList());
        curExpandChapterIndex = chapterIndex;
        notifyItemRangeInserted(chapterIndex+1, mainCategoryBean.getData().getExtra().get(chapterIndex).getChildList().size());
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
            dataInfos.removeAll(mainCategoryBean.getData().getExtra().get(chapterIndex).getChildList());
            curExpandChapterIndex = -1;
            notifyItemRangeRemoved(removeStart, removeCount);
            notifyItemRangeChanged(0, getItemCount(), "change_position");
        }
    }
}