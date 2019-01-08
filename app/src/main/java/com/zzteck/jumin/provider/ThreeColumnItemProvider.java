package com.zzteck.jumin.provider;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.HomeAdapter;
import com.zzteck.jumin.bean.TestBean;

/**
 * Created by Administrator on 2018/7/4.
 * Describe:
 */

public class ThreeColumnItemProvider extends BaseItemProvider<TestBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return HomeAdapter.TYPE_THREE_COLUMN;
    }

    @Override
    public int layout() {
        return R.layout.app_item_home_three_column;
    }

    @Override
    public void convert(BaseViewHolder helper, TestBean data, int position) {
        helper.setText(R.id.item_tv, data.getGoodsName() + "");
        Glide.with(mContext)
                .load(data.getGoodsUrl())
                .dontAnimate()
                .into((AppCompatImageView) helper.getView(R.id.item_img));


    }
}
