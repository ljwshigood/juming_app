package com.zzteck.jumin.provider;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.HomeAdapter;
import com.zzteck.jumin.adapter.HomeItemAdapter;
import com.zzteck.jumin.bean.TestBean;

/**
 * Created by Administrator on 2018/7/2.
 * Describe:
 */

public class ScrollItemProvider extends BaseItemProvider<TestBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return HomeAdapter.TYPE_HORIZONTAL_SCROLL;
    }

    @Override
    public int layout() {
        return R.layout.app_item_home_scroll;
    }

    @Override
    public void convert(BaseViewHolder helper, TestBean data, int position) {
        RecyclerView mRecyclerView = helper.getView(R.id.item_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        HomeItemAdapter adapter = new HomeItemAdapter();
        adapter.setNewData(data.getGoodsInfo());
        mRecyclerView.setAdapter(adapter);
    }
}
