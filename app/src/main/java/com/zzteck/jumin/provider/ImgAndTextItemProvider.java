package com.zzteck.jumin.provider;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.TestHomeAdapter;
import com.zzteck.jumin.bean.TestBean;

/**
 * Created by Administrator on 2018/7/4.
 */

public class ImgAndTextItemProvider extends BaseItemProvider<TestBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return TestHomeAdapter.TYPE_TEXT_AND_IMG;
    }

    @Override
    public int layout() {
        return R.layout.app_item_home_img_and_text;
    }

    @Override
    public void convert(BaseViewHolder helper, TestBean data, int position) {
        helper.setText(R.id.item_tv_main_title, data.getGoodsName() + "")
                .setText(R.id.item_tv_subtitle, data.getGoodsDesc() + "");

        Glide.with(mContext)
                .load(data.getGoodsUrl())
                .dontAnimate()
                .into((AppCompatImageView) helper.getView(R.id.item_double_img));
    }
}
