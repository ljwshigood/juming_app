package com.zzteck.jumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.*;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.zzview.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class RecommandAdapter extends RecyclerArrayAdapter<HomeInfo.DataBean> {

    private Context mContext ;

    private List<HomeInfo.DataBean> mHomeList ;


    public RecommandAdapter(Context context, List<HomeInfo.DataBean> objects) {
        super(context, objects);
        this.mContext = context ;
        this.mHomeList = objects ;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommandViewHolder(parent);
    }


    public class RecommandViewHolder extends BaseViewHolder<HomeInfo.DataBean> {

        private RoundImageView mIvLogo ;

        private TextView mTvTitle ;

        private TextView mTvDetail ;

        private TextView mTvValue ;

        public RecommandViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_recommand);

            mIvLogo = $(R.id.iv_pic) ;
            mTvTitle = $(R.id.tv_title) ;
            mTvDetail = $(R.id.tv_detail) ;
            mTvValue = $(R.id.tv_value) ;
        }

        @Override
        public void setData(HomeInfo.DataBean data) {
            super.setData(data);

            Glide.with(mContext)
                    .load(data.getImg_path())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .crossFade(300)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mIvLogo);

            mTvTitle.setText(data.getTitle());
            mTvDetail.setText(data.getContent());
        }
    }


    public class ViewHolder extends BaseViewHolder{

        private RoundImageView mIvLogo ;

        private TextView mTvTitle ;

        private TextView mTvDetail ;

        private TextView mTvValue ;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvLogo = itemView.findViewById(R.id.iv_pic) ;
            mTvTitle = itemView.findViewById(R.id.tv_title) ;
            mTvDetail = itemView.findViewById(R.id.tv_detail) ;
            mTvValue = itemView.findViewById(R.id.tv_value) ;
        }
    }

}
