package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.Favorite;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    private Context mContext;

    private String[] mFavoriteList;

    public void setOnItemClickListener(MessageAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private MessageAdapter.OnItemClickListener onItemClickListener ;

    public static interface OnItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
        void onTouchListener(int x ,int y) ;

    }

    public HomeCategoryAdapter(Context context, String[] list) {
        this.mContext = context;
        this.mFavoriteList = list;
    }

    @Override
    public HomeCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);
        ViewHolder holder = new HomeCategoryAdapter.ViewHolder(view) ;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeCategoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(mFavoriteList[position]);
    }

    @Override
    public int getItemCount() {
        return mFavoriteList == null ? 0 : mFavoriteList.length;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_title);
        }
    }


}
