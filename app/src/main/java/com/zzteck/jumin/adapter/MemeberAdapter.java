package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.UpgradeMemeberBean;

import java.util.List;

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

    @Override
    public MemeberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_memeber, parent, false);
        return new MemeberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemeberAdapter.ViewHolder holder, int position) {

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
