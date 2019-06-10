package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.MessageBean;
import com.zzteck.jumin.bean.PushBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context mContext ;

    private List<PushBean.DataBean> mMessageList;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener ;

    public static interface OnItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
        void onTouchListener(int x ,int y) ;

    }

    public void notifyMessageList(List<PushBean.DataBean> list){
        this.mMessageList = list ;
        notifyDataSetChanged();
    }


    public MessageAdapter(Context context, List<PushBean.DataBean> list) {
        this.mContext = context ;
        this.mMessageList = list ;
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_message,parent,false);
        ViewHolder viewHolder = new ViewHolder(view) ;

        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getRawX();
                int y = (int) motionEvent.getRawY();

                if(onItemClickListener != null){
                    onItemClickListener.onTouchListener(x,y);
                }

                return false;
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemLongClick(view);
                }
                return false;
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {
        holder.name.setText(mMessageList.get(position).getTitle()+"");
        holder.mTvContent.setText(mMessageList.get(position).getContent()+"");
        holder.mTvDate.setText(mMessageList.get(position).getBegintime());
    }

    @Override
    public int getItemCount() {
        return mMessageList == null ? 0 : mMessageList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        private TextView mTvContent ;

        private TextView mTvDate ;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvDate = itemView.findViewById(R.id.tv_date) ;
            name = itemView.findViewById(R.id.tv_name);
            mTvContent = itemView.findViewById(R.id.tv_content) ;
        }
    }

}
