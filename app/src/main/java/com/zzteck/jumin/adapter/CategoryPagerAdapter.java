package com.zzteck.jumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luck.picture.lib.PicturePreview2Activity;
import com.luck.picture.lib.PicturePreviewActivity;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.BannerBean;
import com.zzteck.jumin.bean.CategoryDetailHeader;
import com.zzteck.jumin.utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;
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
            Glide.with(context).load(Constants.PIC_HOST+data.get(position).getFilePath())
                    .into(imageView);
            container.addView(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<LocalMedia> selectedImages = new ArrayList<>() ;
                    for(int i = 0 ;i < data.size() ;i++){
                        LocalMedia localMedia = new LocalMedia() ;
                        localMedia.setPath(Constants.PIC_HOST+data.get(i).getFilePath());
                        if(data.get(i).getType() == 0){
                            localMedia.setPictureType(PictureConfig.IMAGE);
                        }else{
                            localMedia.setPictureType(PictureConfig.VIDEO);
                        }
                        selectedImages.add(localMedia);
                    }

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(PictureConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) selectedImages);
                    bundle.putSerializable(PictureConfig.EXTRA_SELECT_LIST, (Serializable) selectedImages);
                    bundle.putBoolean(PictureConfig.EXTRA_BOTTOM_PREVIEW, true);
                    bundle.putInt(PictureConfig.EXTRA_POSITION, position);

                    Intent intent = new Intent();
                    intent.setClass(context, PicturePreview2Activity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                   // context.startActivity(new Intent(context,PicturePreview2Activity.class));
                }
            });

            return imageView;
        } else {

            JCVideoPlayerStandard jcVideoPlayer = new JCVideoPlayerStandard(context);
            jcVideoPlayer.setUp(Constants.PIC_HOST+data.get(position).getFilePath() , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");

            Glide.with(context)
                    .load(Constants.PIC_HOST+data.get(position).getThumbPath())
                    .into(jcVideoPlayer.thumbImageView);


            container.addView(jcVideoPlayer);

            jcVideoPlayer.thumbImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List<LocalMedia> selectedImages = new ArrayList<>() ;
                    for(int i = 0 ;i < data.size() ;i++){
                        LocalMedia localMedia = new LocalMedia() ;
                        localMedia.setPath(Constants.PIC_HOST+data.get(i).getFilePath());
                        if(data.get(i).getType() == 0){
                            localMedia.setPictureType(PictureConfig.IMAGE);
                        }else{
                            localMedia.setPictureType(PictureConfig.VIDEO);
                        }
                        selectedImages.add(localMedia);
                    }

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(PictureConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) selectedImages);
                    bundle.putSerializable(PictureConfig.EXTRA_SELECT_LIST, (Serializable) selectedImages);
                    bundle.putBoolean(PictureConfig.EXTRA_BOTTOM_PREVIEW, true);
                    bundle.putInt(PictureConfig.EXTRA_POSITION, position);

                    Intent intent = new Intent();
                    intent.setClass(context, PicturePreview2Activity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                  //  context.startActivity(new Intent(context,PicturePreview2Activity.class));
                }
            });

            return jcVideoPlayer;
        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
