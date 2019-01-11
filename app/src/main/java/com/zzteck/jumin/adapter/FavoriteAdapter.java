package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zzteck.jumin.bean.Favorite;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class FavoriteAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext ;

    private List<Favorite> mFavoriteList;

    public FavoriteAdapter(Context context, List<Favorite> list) {
        this.mContext = context ;
        this.mFavoriteList = list ;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        BaseViewHolder viewHolder = null;

        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mFavoriteList == null ? 0 : mFavoriteList.size() ;
    }




}
