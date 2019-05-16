package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.MediaInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;

    private List<MediaInfo> mMediaInfoList;

    public ImageAdapterLister getmImageAdapterListener() {
        return mImageAdapterListener;
    }

    public void setmImageAdapterListener(ImageAdapterLister mImageAdapterListener) {
        this.mImageAdapterListener = mImageAdapterListener;
    }

    private ImageAdapterLister mImageAdapterListener ;

    public interface  ImageAdapterLister{

        public void imageAddListener();

    }

    public ImageAdapter(Context context, List<MediaInfo> list) {
        this.mContext = context;
        this.mMediaInfoList = list;
    }

    public void notifyImageListChange(List<MediaInfo> list){
        this.mMediaInfoList = list ;
        notifyDataSetChanged();
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        return new ImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageAdapter.ViewHolder holder, final int position) {

        if(position == mMediaInfoList.size()){
            holder.mIvPicture.setImageResource(R.mipmap.btn_tianjiatupian);
        }else {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.default_pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .load(mMediaInfoList.get(position).getFilePath())
                    .apply(options)
                    .into(holder.mIvPicture);


           /* Glide.with(mContext)
                    .load(mMediaInfoList.get(position).getFilePath())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .crossFade(300)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.mIvPicture);*/
        }

        holder.mIvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == mMediaInfoList.size()){
                    if(mImageAdapterListener != null){
                        mImageAdapterListener.imageAddListener();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMediaInfoList == null ? 0 : mMediaInfoList.size() + 1;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvPicture = itemView.findViewById(R.id.iv_picture) ;
        }
    }


}
