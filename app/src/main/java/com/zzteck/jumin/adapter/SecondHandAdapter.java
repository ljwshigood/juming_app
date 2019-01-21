package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.SecondHandBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class SecondHandAdapter extends RecyclerView.Adapter<SecondHandAdapter.ViewHolder> {

    private Context mContext ;

    private List<SecondHandBean> mSecondList;

    public SecondHandAdapter(Context context, List<SecondHandBean> list) {
        this.mContext = context ;
        this.mSecondList = list ;
    }


    @NonNull
    @Override
    public SecondHandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_second_hand,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondHandAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mSecondList == null ? 10 : mSecondList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_title);

        }
    }

}
