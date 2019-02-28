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
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.zzview.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class RecommandAdapter extends RecyclerView.Adapter<RecommandAdapter.ViewHolder> {

    private Context mContext ;

    private List<HomeInfo.DataBean> mHomeList ;

    public RecommandAdapter(Context context, List<HomeInfo.DataBean> list) {
        this.mContext = context ;
        this.mHomeList = list ;
    }


    @NonNull
    @Override
    public RecommandAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommand,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(mContext, WebViewActivity.class) ;
                mContext.startActivity(intent);*/
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommandAdapter.ViewHolder holder, int position) {

        Glide.with(mContext)
                .load(mHomeList.get(position).getImg_path())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade(300)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mIvLogo);

        holder.mTvTitle.setText(mHomeList.get(position).getTitle());
        holder.mTvDetail.setText(mHomeList.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return mHomeList == null ? 0 : mHomeList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RoundImageView mIvLogo ;
        //private ImageView mIvLogo ;

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
