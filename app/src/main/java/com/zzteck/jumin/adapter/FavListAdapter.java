package com.zzteck.jumin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.AttentionList;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.zzview.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class FavListAdapter extends RecyclerArrayAdapter<AttentionList.DataBean> {

    private Context mContext;

    public List<AttentionList.DataBean> getmAttentList() {
        return mHomeList;
    }

    public void setmAttentionList(List<AttentionList.DataBean> mHomeList) {
        this.mHomeList = mHomeList;
    }

    private List<AttentionList.DataBean> mHomeList;

    public FavListAdapter(Context context, List<AttentionList.DataBean> objects) {
        super(context, objects);
        this.mContext = context;
        this.mHomeList = objects;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavViewHolder(parent);
    }

    public class FavViewHolder extends BaseViewHolder<AttentionList.DataBean> {

        private TextView mTvTitle;

        private TextView mTvCate;

        private TextView mTvDate;

        public FavViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_fav);

            mTvTitle = $(R.id.tv_fav_title);
            mTvCate = $(R.id.tv_cate);
            mTvDate = $(R.id.tv_date);
        }

        @Override
        public void setData(AttentionList.DataBean data) {
            super.setData(data);
            mTvTitle.setText(data.getTitle());
            mTvCate.setText(data.getCatname());
            mTvDate.setText(data.getIntime());
        }
    }


    /*public class ViewHolder extends BaseViewHolder {

        private TextView mTvTitle;

        private TextView mTvCate;

        private TextView mTvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_fav_title);
            mTvCate = itemView.findViewById(R.id.tv_cate);
            mTvDate = itemView.findViewById(R.id.tv_date);
        }
    }*/

}
