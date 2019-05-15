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

import java.util.List;


public class CategoryPagerAdapter extends PagerAdapter {
    private List<String> data;
    private Context context;

    private int mType ;

    private LayoutInflater mLayoutInflater ;

    public CategoryPagerAdapter(Context context, List<String> data, int type) {
        this.data = data;
        this.mType = type ;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context) ;
    }

    @Override
    public int getCount() {
    	if(mType == 0){
    		return data == null ? 3 : data.size() ;
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

        View rootView = mLayoutInflater.inflate(R.layout.layout_category_detail,null) ;
        ImageView view = rootView.findViewById(R.id.iv_pic) ;

        String img = data.get(position % data.size());
        Glide.with(context)
                .load(img)
                .placeholder(R.mipmap.default_pic)
                .error(R.mipmap.default_pic)
                .crossFade(300)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);

        /*ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        String img = data.get(position % data.size());
        Glide.with(context)
                .load(img)
                .placeholder(R.mipmap.default_pic)
                .error(R.mipmap.default_pic)
                .crossFade(300)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);*/

        container.addView(rootView);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
