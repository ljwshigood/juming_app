package com.baijiahulian.common.crop.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.baijiahulian.common.crop.ImageCropProxy;
import com.baijiahulian.common.crop.PhotoPreviewActivity;
import com.baijiahulian.common.crop.R;
import com.baijiahulian.common.crop.model.PhotoInfo;
import com.baijiahulian.common.crop.uikit.zoonview.PhotoView;
import com.baijiahulian.common.crop.uikit.zoonview.PhotoViewAttacher;
import com.baijiahulian.common.crop.utils.DeviceUtils;

import java.util.List;

/**
 * Desction:
 * Author:pengjianbo
 * Date:2015/12/29 0029 15:53
 */
public class PhotoPreviewAdapter extends PagerAdapter {

    private Activity mActivity;
    private DisplayMetrics mDisplayMetrics;

    private PhotoView mCurrentPagePhotoView;
    
    private int mUrls ; // 0: file : 1 : not progress 2: http

    private  List<PhotoInfo> mPhotolist ;
    
    public PhotoPreviewAdapter(Activity activity, List<PhotoInfo> list,int urlType,int backgroundType) {
        this.mActivity = activity;
        this.mPhotolist = list ;
        this.mDisplayMetrics = DeviceUtils.getScreenPix(mActivity);
        this.mUrls = urlType ;
    }
    
    public PhotoView getCurrentPagePhotoView() {
        return mCurrentPagePhotoView;
    }

    
    class PreviewViewHolder{
		 PhotoView mImageView ;
	     public PreviewViewHolder(View view) {
	         mImageView = (PhotoView) view;
	     }
    }


	@Override
	public int getCount() {
		return mPhotolist == null ? 0 : mPhotolist.size() ;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}
	
	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		View imageLayout = LayoutInflater.from(mActivity).inflate(R.layout.item_preview_photo, view, false);;
		assert imageLayout != null;
		
		PhotoInfo photoInfo = mPhotolist.get(position);
        String path = "";
        if (photoInfo != null) {
            path = photoInfo.getPhotoPath();
        }
        String url ;
		if(mUrls == 0){
			if(path.startsWith("drawable")){
				url = path;
			}else{
				url = "file://"+path;
			}
		}else if(mUrls == 1){
			url = path ;
		}else{
			
			//test web service :
			//url = "http://linge360.oss-cn-shenzhen.aliyuncs.com/"+path ;
			// public web service 
			
			url = path ;
			
		}
		
		PhotoView pv = (PhotoView) imageLayout.findViewById(R.id.photo_view);
		
		pv.setBackgroundColor(Color.TRANSPARENT) ;
		
		if(ImageCropProxy.getFunctionConfig().getImageLoader() != null){
			 ImageCropProxy.getFunctionConfig().getImageLoader() 
			 .displayImage(mActivity,url,
					 (PhotoView) imageLayout.findViewById(R.id.photo_view), 
					 mDisplayMetrics.widthPixels / 2, mDisplayMetrics.heightPixels / 2,null,
					 (ProgressBar)imageLayout.findViewById(R.id.loading));
		}
     
        mCurrentPagePhotoView =  (PhotoView) imageLayout.findViewById(R.id.photo_view);
        mCurrentPagePhotoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                ((PhotoPreviewActivity)mActivity).toggleTitleBar();
            }
        });

		view.addView(imageLayout, 0);
		return imageLayout;
	}
	
}
