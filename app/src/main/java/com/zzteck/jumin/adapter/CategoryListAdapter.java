package com.zzteck.jumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.MainCategoryBean;
import com.zzteck.jumin.ui.business.CategoryListActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private Context mContext;

    private List<MainCategoryBean.DataBean.ChildrenBean> mCategoryList;

    public CategoryListAdapter(Context context, List<MainCategoryBean.DataBean.ChildrenBean> list) {
        this.mContext = context;
        this.mCategoryList = list;
    }

    public void notifyCategoryList(List<MainCategoryBean.DataBean.ChildrenBean> mFavoriteList){
        this.mCategoryList = mFavoriteList ;
        notifyDataSetChanged();
    }

    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cate_list, parent, false);
        return new CategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.ViewHolder holder, final int position) {
        holder.name.setText(mCategoryList.get(position).getCatname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CategoryListActivity.class) ;
                intent.putExtra("title",mCategoryList.get(position).getCatname()) ;
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryList == null ? 0 : mCategoryList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_title);
        }
    }


}
