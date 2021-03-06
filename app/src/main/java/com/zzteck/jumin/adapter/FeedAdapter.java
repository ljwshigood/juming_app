package com.zzteck.jumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.FeedBack;
import com.zzteck.jumin.bean.VideoBean;
import com.zzteck.jumin.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context mContext;

    private List<FeedBack> mFeedBackList;

    public FeedAdapter(Context context, List<FeedBack> list) {
        this.mContext = context;
        this.mFeedBackList = list;
    }

    public void notifyVideoListChange(List<FeedBack> list){
        this.mFeedBackList = list ;
        notifyDataSetChanged();
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_feed_back, parent, false);
        return new FeedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FeedAdapter.ViewHolder holder, final int position) {

        holder.mTvVideo.setText(mFeedBackList.get(position).getFeedBack());

        boolean isSelect = mFeedBackList.get(position).isSelect();
        if(isSelect){
            holder.mLLFeedBack.setBackgroundResource(R.drawable.bg_red_rec_fd_press);
            holder.mTvVideo.setTextColor(Color.WHITE);
        }else{
            holder.mLLFeedBack.setBackgroundResource(R.drawable.bg_btn_rec_press);
            holder.mTvVideo.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelect = mFeedBackList.get(position).isSelect();

                for(int i = 0 ;i< mFeedBackList.size() ;i++){
                    if(i == position){
                        mFeedBackList.get(i).setSelect(true);
                    }else{
                        mFeedBackList.get(i).setSelect(false);
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFeedBackList == null ? 0 : mFeedBackList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvVideo;

        private LinearLayout mLLFeedBack ;

        public ViewHolder(View itemView) {
            super(itemView);
            mLLFeedBack = itemView.findViewById(R.id.ll_feedback) ;
            mTvVideo = itemView.findViewById(R.id.tv_content);
        }
    }


}
