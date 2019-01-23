package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.MessageBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context mContext ;

    private List<MessageBean> mMessageList;

    public MessageAdapter(Context context, List<MessageBean> list) {
        this.mContext = context ;
        this.mMessageList = list ;
    }


    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_message,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mMessageList == null ? 10 : mMessageList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_title);

        }
    }

}
