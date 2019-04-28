package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.BusinessBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class MyReleaseAdapter extends RecyclerView.Adapter<MyReleaseAdapter.ViewHolder> {

    private Context mContext ;

    private List<BusinessBean> mBusinessList;

    public MyReleaseAdapter(Context context, List<BusinessBean> list) {
        this.mContext = context ;
        this.mBusinessList = list ;
    }


    @NonNull
    @Override
    public MyReleaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_release,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReleaseAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBusinessList == null ? 10 : mBusinessList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_title);

        }
    }

}
