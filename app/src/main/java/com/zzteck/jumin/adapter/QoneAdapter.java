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
import com.zzteck.jumin.bean.QoneInfo;
import com.zzteck.jumin.bean.VideoBean;
import com.zzteck.jumin.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class QoneAdapter extends RecyclerView.Adapter<QoneAdapter.ViewHolder> {

    private Context mContext;

    private List<QoneInfo.DataBean> mVideoList;

    public QoneAdapter(Context context, List<QoneInfo.DataBean> list) {
        this.mContext = context;
        this.mVideoList = list;
    }

    public void notifyVideoListChange(List<QoneInfo.DataBean> list){
        this.mVideoList = list ;
        notifyDataSetChanged();
    }

    @Override
    public QoneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_qone, parent, false);
        return new QoneAdapter.ViewHolder(view);
    }

    public IOnCityItemLister getmIOnItemClick() {
        return mIOnItemClick;
    }

    public void setmIOnItemClick(IOnCityItemLister mIOnItemClick) {
        this.mIOnItemClick = mIOnItemClick;
    }

    private IOnCityItemLister mIOnItemClick ;

    public interface IOnCityItemLister{

        public void onItemCityClick(QoneInfo.DataBean bean) ;
    }


    @Override
    public void onBindViewHolder(QoneAdapter.ViewHolder holder, final int position) {

        holder.mTvQone.setText(mVideoList.get(position).getAreaname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIOnItemClick != null){
                    mIOnItemClick.onItemCityClick(mVideoList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoList == null ? 0 : mVideoList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvQone;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvQone = itemView.findViewById(R.id.tv_qone);
        }
    }


}
