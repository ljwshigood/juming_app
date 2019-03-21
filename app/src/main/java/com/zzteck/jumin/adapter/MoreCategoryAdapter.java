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
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.MoreCategory;
import com.zzteck.jumin.ui.business.CategoryListActivity;
import com.zzteck.jumin.ui.business.MoreCategoryListActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class MoreCategoryAdapter extends RecyclerView.Adapter<MoreCategoryAdapter.ViewHolder> {

    private Context mContext ;

    private List<MoreCategory> mMoreCategoryList;

    public MoreCategoryAdapter(Context context, List<MoreCategory> list) {
        this.mContext = context ;
        this.mMoreCategoryList = list ;
    }


    @NonNull
    @Override
    public MoreCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_more_category,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MoreCategoryListActivity.class) ;
                mContext.startActivity(intent);
            }
        });


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoreCategoryAdapter.ViewHolder holder, int position) {

        Glide.with(mContext)
                .load(mMoreCategoryList.get(position).getRes())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade(300)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mIv);

        holder.name.setText(mMoreCategoryList.get(position).getInfo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CategoryListActivity.class) ;
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
