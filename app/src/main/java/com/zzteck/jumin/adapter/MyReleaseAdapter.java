package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.MyReleaseBean;
import com.zzteck.jumin.utils.DateUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class MyReleaseAdapter extends RecyclerView.Adapter<MyReleaseAdapter.ViewHolder> {

    private Context mContext ;

    private List<MyReleaseBean.DataBean> mMyReleaseList;


    public void notifyMyReleaseAdapter(List<MyReleaseBean.DataBean> list){
        this.mMyReleaseList = list ;
        notifyDataSetChanged();
    }

    public MyReleaseAdapter(Context context, List<MyReleaseBean.DataBean> list) {
        this.mContext = context ;
        this.mMyReleaseList = list ;
    }

    @Override
    public MyReleaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_release,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyReleaseAdapter.ViewHolder holder, int position) {

        holder.mTvDetail.setText(mMyReleaseList.get(position).getContent());
        holder.mTvDate.setText(DateUtils.getFormatDate(Long.valueOf(mMyReleaseList.get(position).getBegintime())*1000));
    }

    @Override
    public int getItemCount() {
        return mMyReleaseList == null ? 0 : mMyReleaseList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvDate ;

        private TextView mTvDetail ;

        private TextView mTvValue ;

        private LinearLayout mLLTop ;

        private LinearLayout mLLBold ;

        private LinearLayout mLLRefresh ;

        private LinearLayout mLLDisplay ;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvDate = itemView.findViewById(R.id.tv_date);
            mTvDetail = itemView.findViewById(R.id.tv_detail) ;
            mTvValue = itemView.findViewById(R.id.tv_value) ;

            mLLTop = itemView.findViewById(R.id.ll_top) ;
            mLLBold = itemView.findViewById(R.id.ll_bold) ;
            mLLRefresh = itemView.findViewById(R.id.ll_refresh) ;
            mLLDisplay = itemView.findViewById(R.id.ll_display) ;

        }
    }

}
