package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.AreaInfo;
import com.zzteck.jumin.bean.QoneInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.ViewHolder> {

    private Context mContext;

    private List<AreaInfo.DataBean> mVideoList;

    public StreamAdapter(Context context, List<AreaInfo.DataBean> list) {
        this.mContext = context;
        this.mVideoList = list;
    }

    public void notifyVideoListChange(List<AreaInfo.DataBean> list){
        this.mVideoList = list ;
        notifyDataSetChanged();
    }

    @Override
    public StreamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_qone, parent, false);
        return new StreamAdapter.ViewHolder(view);
    }

    public IOnCityItemLister getmIOnItemClick() {
        return mIOnItemClick;
    }

    public void setmIOnItemClick(IOnCityItemLister mIOnItemClick) {
        this.mIOnItemClick = mIOnItemClick;
    }

    private IOnCityItemLister mIOnItemClick ;

    public interface IOnCityItemLister{

        public void onItemCityClick(AreaInfo.DataBean bean) ;
    }


    @Override
    public void onBindViewHolder(StreamAdapter.ViewHolder holder, final int position) {

        holder.mTvQone.setText(mVideoList.get(position).getStreetname());
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
