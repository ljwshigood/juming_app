package com.zzteck.jumin.ViewHolder;

import android.content.Context;
import android.view.ViewGroup;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.BaseViewHolder;
import com.zzteck.jumin.bean.HomeBean;



/**
 *
 */
public class VideoViewHolder extends BaseViewHolder<HomeBean> {

    private Context mContext ;

    public VideoViewHolder(Context context , ViewGroup parent) {
        super(parent, R.layout.item_home_video);
        this.mContext = context ;

    }


    @Override
    public void refreshData(final HomeBean data, final int position,long paramDuration) {
        super.refreshData(data, position,paramDuration);
    }

}
