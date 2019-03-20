package com.ccj.poptabview.listener;

import com.ccj.poptabview.base.SuperListener;

import java.util.List;

/**
 * 复杂筛选的回调
 * Created by chenchangjun on 17/7/7.
 */

public interface OnSortItemClickListener extends SuperListener {
    /**
     * 筛选分类、商城点击
     */
    void onSortItemClick(int position, List<Integer> filterTabBeen);
}
