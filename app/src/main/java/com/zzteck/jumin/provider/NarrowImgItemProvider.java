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

public class NarrowImgItemProvider extends BaseItemProvider<TestBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return HomeAdapter.TYPE_NARROW_IMG;
    }

    @Override
    public int layout() {
        return R.layout.app_item_home_narrow_img;
    }

    @Override
    public void convert(BaseViewHolder helper, TestBean data, int position) {
        Glide.with(mContext)
                .load(data.getGoodsUrl())
                .into((AppCompatImageView) helper.getView(R.id.item_narrow_img));
    }
}
