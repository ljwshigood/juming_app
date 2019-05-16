package com.zzteck.jumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.zzteck.jumin.bean.MoreCategory;
import com.zzteck.jumin.ui.business.CategoryListActivity;
import com.zzteck.jumin.ui.business.MoreCategoryListActivity;
import com.zzteck.jumin.utils.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class MoreCategoryAdapter extends RecyclerView.Adapter<MoreCategoryAdapter.ViewHolder> {

    private Context mContext ;

    private List<MoreCategory.DataBean> mMoreCategoryList;

    public MoreCategoryAdapter(Context context, List<MoreCategory.DataBean> list) {
        this.mContext = context ;
        this.mMoreCategoryList = list ;
    }

    public void notifyMoreCategoryAdapter(List<MoreCategory.DataBean> list){
        this.mMoreCategoryList = list ;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MoreCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_more_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoreCategoryAdapter.ViewHolder holder, final int position) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_pic)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(mMoreCategoryList.get(position).getIcon())
                .apply(options)
                .into(holder.mIv);


       /* Glide.with(mContext)
                .load(mMoreCategoryList.get(position).getIcon())
                .placeholder(R.mipmap.default_pic)
                .error(R.mipmap.default_pic)
                .crossFade(300)
                .transform(new GlideCircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mIv);*/

        holder.name.setText(mMoreCategoryList.get(position).getCatname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CategoryListActivity.class) ;
                intent.putExtra("title",mMoreCategoryList.get(position).getCatname()) ;
                intent.putExtra("id",mMoreCategoryList.get(position).getCatid()+"") ;
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMoreCategoryList == null ? 0 : mMoreCategoryList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        private ImageView mIv ;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_cate);
            mIv = itemView.findViewById(R.id.iv_cate) ;

        }
    }

}
