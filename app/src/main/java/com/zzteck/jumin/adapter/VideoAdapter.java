package com.zzteck.jumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.VideoBean;
import com.zzteck.jumin.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private Context mContext;

    private List<VideoBean.DataBean> mVideoList;

    public VideoAdapter(Context context, List<VideoBean.DataBean> list) {
        this.mContext = context;
        this.mVideoList = list;
    }

    public void notifyVideoListChange(List<VideoBean.DataBean> list){
        this.mVideoList = list ;
        notifyDataSetChanged();
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, final int position) {

        Glide.with(mContext)
                .load(mVideoList.get(position).getImg_path())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade(300)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mIvVideo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(Constants.HOST+mVideoList.get(position).getVideo());
                Intent intent= new Intent(Intent.ACTION_VIEW) ;
                intent.setDataAndType(uri,"video/*");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoList == null ? 0 : mVideoList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvVideo = itemView.findViewById(R.id.iv_video);
        }
    }


}
