package com.zzteck.jumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.UpgradeMemeberBean;
import com.zzteck.jumin.ui.usercenter.MemberUpgradeDetailActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class MemeberAdapter extends RecyclerView.Adapter<MemeberAdapter.ViewHolder> {

    private Context mContext;

    private List<UpgradeMemeberBean> mMemberList;

    public MemeberAdapter(Context context, List<UpgradeMemeberBean> list) {
        this.mContext = context;
        this.mMemberList = list;
    }

    public void notifyMemberAdapter(List<UpgradeMemeberBean> list){
        this.mMemberList = list ;
        notifyDataSetChanged();
    }

    @Override
    public MemeberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_memeber, parent, false);
        return new MemeberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemeberAdapter.ViewHolder holder, final int position) {

        Glide.with(mContext)
                .load(mMemberList.get(position).getRes())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade(300)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mIvMemeber);

        holder.name.setText(mMemberList.get(position).getInfo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(mContext, MemberUpgradeDetailActivity.class) ;
                        intent.putExtra("position",position) ;
                        mContext.startActivity(intent);
                        break  ;
                    case 1:
                        Intent intent1 = new Intent(mContext, MemberUpgradeDetailActivity.class) ;
                        intent1.putExtra("position",position) ;
                        mContext.startActivity(intent1);
                        break  ;
                    case 2:
                        Intent intent2 = new Intent(mContext, MemberUpgradeDetailActivity.class) ;
                        intent2.putExtra("position",position) ;
                        mContext.startActivity(intent2);
                        break  ;
                    case 3:
                        Intent intent3 = new Intent(mContext, MemberUpgradeDetailActivity.class) ;
                        intent3.putExtra("position",position) ;
                        mContext.startActivity(intent3);
                        break  ;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMemberList == null ? 6 : mMemberList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        private ImageView mIvMemeber ;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_title);
            mIvMemeber = itemView.findViewById(R.id.iv_member) ;
        }
    }


}
