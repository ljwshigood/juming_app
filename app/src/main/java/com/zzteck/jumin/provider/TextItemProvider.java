package com.zzteck.jumin.provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.HomeAdapter;
import com.zzteck.jumin.bean.TestBean;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class TextItemProvider extends BaseItemProvider<TestBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return HomeAdapter.TYPE_TEXT;
    }

    @Override
    public int layout() {
        return R.layout.app_item_home_text;
    }

    @Override
    public void convert(BaseViewHolder helper, TestBean data, int position) {
        helper.setText(R.id.item_tv_main_title, data.getGoodsName()+"")
                .setText(R.id.item_tv_subtitle, data.getGoodsDesc()+"");
    }
}
