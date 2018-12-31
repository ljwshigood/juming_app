package com.zzteck.jumin.provider;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.TestHomeAdapter;
import com.zzteck.jumin.bean.TestBean;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:图片Item
 */

public class ImgItemProvider extends BaseItemProvider<TestBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return TestHomeAdapter.TYPE_IMG;
    }

    @Override
    public int layout() {
        return R.layout.app_item_home_img;
    }

    @Override
    public void convert(BaseViewHolder helper, TestBean data, int position) {
        Glide.with(mContext)
                .load(data.getGoodsUrl())
                .dontAnimate()
                .into((AppCompatImageView) helper.getView(R.id.item_img));
    }
}
