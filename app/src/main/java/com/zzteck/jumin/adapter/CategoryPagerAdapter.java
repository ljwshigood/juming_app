package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.BannerBean;
import com.zzteck.jumin.bean.CategoryDetailHeader;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class CategoryPagerAdapter extends PagerAdapter {


    private List<CategoryDetailHeader> data;
    private Context context;

    private int mType ;

    private LayoutInflater mLayoutInflater ;

    public CategoryPagerAdapter(Context context, List<CategoryDetailHeader> data, int type) {
        this.data = data;
        this.mType = type ;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context) ;
    }

    @Override
    public int getCount() {
    	if(mType == 0){
    		return data == null ? 0 : data.size() ;
    	}else{
    		return Integer.MAX_VALUE;	
    	}
        
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {


        if (data.get(position).getType()== 0) {//图片
            final ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(data.get(position).getFilePath())
//                    .skipMemoryCache(true)
                    .into(imageView);
            container.addView(imageView);

            return imageView;
        } else {

            JCVideoPlayerStandard jcVideoPlayer = new JCVideoPlayerStandard(context);
            jcVideoPlayer.setUp(data.get(position).getFilePath() , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");

            Glide.with(context)
                    .load(data.get(position).getThumbPath())
                    .into(jcVideoPlayer.thumbImageView);

            jcVideoPlayer.prepareMediaPlayer();
            container.addView(jcVideoPlayer);

            return jcVideoPlayer;
        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
