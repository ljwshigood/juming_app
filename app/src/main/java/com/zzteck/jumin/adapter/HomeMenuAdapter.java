package com.zzteck.jumin.adapter;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.homeMenuBean;

/**
 * @Author cuiChenBo
 * Created by zz on 2018/3/6 14:51.
 * 　　class explain:
 * 　　　　update:       upAuthor:      explain:
 */

public class HomeMenuAdapter extends BaseQuickAdapter<homeMenuBean.Data,BaseViewHolder> {

    public HomeMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, homeMenuBean.Data item) {
         helper.setText(R.id.tvTitle,item.title);
        ((ImageView) helper.getView(R.id.ivIcon)).setImageResource(item.icon);
//         GlideImageUtils.display(mContext,item.icon,(ImageView) helper.getView(R.id.ivIcon));

    }
}
