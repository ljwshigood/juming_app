package com.zzteck.jumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.RecommandBean;
import com.zzteck.jumin.ui.mainui.WebViewActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class RecommandAdapter extends RecyclerView.Adapter<RecommandAdapter.ViewHolder> {

    private Context mContext ;

    private List<RecommandBean> mHomeList ;

    public RecommandAdapter(Context context, List<RecommandBean> list) {
        this.mContext = context ;
        this.mHomeList = list ;
    }


    @NonNull
    @Override
    public RecommandAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommand,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class) ;
                mContext.startActivity(intent);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommandAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mHomeList == null ? 10 : mHomeList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_title);

        }
    }

}
