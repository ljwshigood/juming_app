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

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private Context mContext;

    private List<Favorite> mFavoriteList;

    public CategoryListAdapter(Context context, List<Favorite> list) {
        this.mContext = context;
        this.mFavoriteList = list;
    }

    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cate_list, parent, false);
        return new CategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mFavoriteList == null ? 10 : mFavoriteList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_title);
        }
    }


}
