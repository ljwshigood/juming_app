package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import com.zzteck.jumin.bean.Favorite;
import com.zzteck.jumin.bean.MainCategoryBean;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    private Context mContext;

    private List<MainCategoryBean.DataBean> mFavoriteList;

    public void setOnItemClickListener(MessageAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private MessageAdapter.OnItemClickListener onItemClickListener ;

    public static interface OnItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
        void onTouchListener(int x ,int y) ;

    }

    public HomeCategoryAdapter(Context context, List<MainCategoryBean.DataBean> list) {
        this.mContext = context;
        this.mFavoriteList = list;

        if(mFavoriteList != null && mFavoriteList.size() > 0){
            mFavoriteList.get(0).setSelect(true);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public HomeCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);
        ViewHolder holder = new HomeCategoryAdapter.ViewHolder(view) ;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view);
                }
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(HomeCategoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(mFavoriteList.get(position).getCatname());

        if(mFavoriteList.get(position).isSelect()){
            holder.mLLCate.setBackgroundResource(R.mipmap.bg_cate);
            holder.name.setTextColor(mContext.getResources().getColor(R.color.mainRed));
        }else{
            holder.mLLCate.setBackgroundResource(R.mipmap.bg_cate_nomal);
            holder.name.setTextColor(mContext.getResources().getColor(R.color.dark_gray));
        }

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.default_pic)
                .transform(new GlideCircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(Constants.PIC_HOST+mFavoriteList.get(position).getIcon())
                .apply(options)
                .into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return mFavoriteList == null ? 0 : mFavoriteList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView mIv ;
        private LinearLayout mLLCate ;
        public ViewHolder(View itemView) {
            super(itemView);
            mLLCate = itemView.findViewById(R.id.ll_cate) ;
            mIv = itemView.findViewById(R.id.iv) ;
            name = itemView.findViewById(R.id.tv_title);
        }
    }


}
