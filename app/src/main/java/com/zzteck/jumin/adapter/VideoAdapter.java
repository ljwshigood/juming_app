package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.VideoBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private Context mContext;

    private List<VideoBean> mVideoList;

    public VideoAdapter(Context context, List<VideoBean> list) {
        this.mContext = context;
        this.mVideoList = list;
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mVideoList == null ? 10 : mVideoList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvVideo = itemView.findViewById(R.id.iv_video);
        }
    }


}
