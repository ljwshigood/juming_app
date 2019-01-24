package com.zzteck.jumin.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.BaseViewHolder;
import com.zzteck.jumin.bean.HomeBean;
import com.zzteck.jumin.ui.business.CategoryListActivity;


/**
 *
 */
public class PictureViewHolder extends BaseViewHolder<HomeBean> {

    private Context mContext ;

    private ImageView mIvErshoufang ;

    public PictureViewHolder(Context context , ViewGroup parent) {
        super(parent, R.layout.item_home_picture);
        this.mContext = context ;
        mIvErshoufang = itemView.findViewById(R.id.iv_ershoufang) ;

        mIvErshoufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CategoryListActivity.class) ;
                mContext.startActivity(intent);

            }
        });
    }


    @Override
    public void refreshData(final HomeBean data, final int position,long paramDuration) {
        super.refreshData(data, position,paramDuration);




    }

}
