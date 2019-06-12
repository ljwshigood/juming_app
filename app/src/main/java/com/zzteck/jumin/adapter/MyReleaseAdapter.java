package com.zzteck.jumin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    public IActionReleaseListener getiActionReleaseListener() {
        return iActionReleaseListener;
    }

    public void setiActionReleaseListener(IActionReleaseListener iActionReleaseListener) {
        this.iActionReleaseListener = iActionReleaseListener;
    }

    private IActionReleaseListener iActionReleaseListener ;

    public interface  IActionReleaseListener{

        public void releaseListener(String id,int action) ;

    }


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
    public void onBindViewHolder(MyReleaseAdapter.ViewHolder holder, final int position) {

        holder.mTvDetail.setText(mMyReleaseList.get(position).getTitle());
        holder.mTvDate.setText(DateUtils.getFormatDate(Long.valueOf(mMyReleaseList.get(position).getBegintime())*1000));


        if(mMyReleaseList.get(position).getIfred().equals("1")){
            holder.mTvRed.setTextColor(Color.RED);
        }else {
            holder.mTvRed.setTextColor(Color.DKGRAY);
        }


        if(mMyReleaseList.get(position).getIfbold().equals("1")){
            TextPaint tp = holder.mTvBold.getPaint();
            tp.setFakeBoldText(true);
        }else {
            holder.mTvRed.setTextColor(Color.DKGRAY);
            TextPaint tp = holder.mTvBold.getPaint();
            tp.setFakeBoldText(false);
        }


        if(mMyReleaseList.get(position).getIfred().equals("1")){
            holder.mTvRed.setTextColor(Color.RED);
        }else {
            holder.mTvRed.setTextColor(Color.DKGRAY);
        }


        holder.mLLBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iActionReleaseListener != null){
                    iActionReleaseListener.releaseListener(mMyReleaseList.get(position).getId(),0);
                }
            }
        });

        holder.mLLRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iActionReleaseListener != null){
                    iActionReleaseListener.releaseListener(mMyReleaseList.get(position).getId(),1);
                }

            }
        });

        holder.mLLRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iActionReleaseListener != null){
                    iActionReleaseListener.releaseListener(mMyReleaseList.get(position).getId(),2);
                }
            }
        });

        holder.mLLTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iActionReleaseListener != null){
                    iActionReleaseListener.releaseListener(mMyReleaseList.get(position).getId(),3);
                }
            }
        });


        holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iActionReleaseListener != null){
                    iActionReleaseListener.releaseListener(mMyReleaseList.get(position).getId(),4);
                }
            }
        });
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

        private LinearLayout mLLRed;

        private ImageView mIvDelete ;

        private TextView mTvShow ;
        private TextView mTvBold ;
        private TextView mTvRed ;
        private TextView mTvTop ;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvDelete = itemView.findViewById(R.id.iv_delete) ;
            mTvShow = itemView.findViewById(R.id.tv_isshow) ;
            mTvBold = itemView.findViewById(R.id.tv_isbold) ;
            mTvRed = itemView.findViewById(R.id.tv_isred) ;
            mTvTop = itemView.findViewById(R.id.tv_istop) ;

            mTvDate = itemView.findViewById(R.id.tv_date);
            mTvDetail = itemView.findViewById(R.id.tv_detail) ;
            mTvValue = itemView.findViewById(R.id.tv_value) ;

            mLLTop = itemView.findViewById(R.id.ll_top) ;
            mLLBold = itemView.findViewById(R.id.ll_bold) ;
            mLLRefresh = itemView.findViewById(R.id.ll_refresh) ;
            mLLRed = itemView.findViewById(R.id.ll_red) ;

        }
    }

}
