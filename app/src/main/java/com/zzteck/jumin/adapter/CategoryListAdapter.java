package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.ChildCategoryBean;
import com.zzteck.jumin.bean.Favorite;
import com.zzteck.jumin.bean.MainCategoryBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private Context mContext;

    private List<ChildCategoryBean.DataBean> mFavoriteList;

    public CategoryListAdapter(Context context, List<ChildCategoryBean.DataBean> list) {
        this.mContext = context;
        this.mFavoriteList = list;
    }

    public void notifyCategoryList(List<ChildCategoryBean.DataBean> mFavoriteList){
        this.mFavoriteList = mFavoriteList ;
        notifyDataSetChanged();
    }

    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cate_list, parent, false);
        return new CategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.ViewHolder holder, int position) {
        holder.name.setText(mFavoriteList.get(position).getChildren().get(0).getCatname());
    }

    @Override
    public int getItemCount() {
        return mFavoriteList == null ? 0 : mFavoriteList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_title);
        }
    }


}
