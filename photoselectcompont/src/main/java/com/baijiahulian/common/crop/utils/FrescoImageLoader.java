/*
 * Copyright (C) 2014 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.baijiahulian.common.crop.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.baijiahulian.common.crop.R;
import com.baijiahulian.common.crop.uikit.GFImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Desction:fresco image loader
 * Author:pengjianbo
 * Date:15/12/24 下午9:34
 */
public class FrescoImageLoader {

    private Context context;

    private DisplayImageOptions mOptionsStyle ; 
    
    private ProgressBar mProgressBar ;
    
    public ProgressBar getmProgressBar() {
		return mProgressBar;
	}

	public void setmProgressBar(ProgressBar mProgressBar) {
		this.mProgressBar = mProgressBar;
	}

	public FrescoImageLoader(Context context) {
        this(context, Bitmap.Config.RGB_565);
    }

    public FrescoImageLoader(Context context, Bitmap.Config config) {
        this.context = context;
       /* ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(context)
                .setBitmapsConfig(config)
                .build();
        Fresco.initialize(context, imagePipelineConfig);*/
        
        mOptionsStyle = new DisplayImageOptions.Builder()
		.showImageOnLoading(null)
		.showImageForEmptyUri(null)
		.showImageOnFail(null)
		.cacheOnDisk(true)
		.bitmapConfig(Bitmap.Config.RGB_565).build();
        
    }
  public void displayImage(final Context context, String path, GFImageView imageView, int width, int height,Drawable defaultDrawable,final ProgressBar bar) {
	  
        imageView.setImageDrawable(defaultDrawable);
        ImageSize imageSize = new ImageSize(width, height);
        ImageLoader.getInstance().displayImage(path, new ImageViewAware(imageView), mOptionsStyle,imageSize,new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				if(bar != null){
					bar.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onLoadingFailed(String imageUri, View view,FailReason failReason) {
				if(bar != null){
					bar.setVisibility(View.GONE);
					Toast.makeText(context, context.getString(R.string.picture_failed), Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				if(bar != null){
					bar.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				
			}
		},null);
      
    }
    

    public void displayImage(Context context, String path, GFImageView imageView, int width, int height,Drawable defaultDrawable) {
    	
        Resources resources = context.getResources();
        //Drawable defaultDrawable = ContextCompat.getDrawable(context, R.drawable.common_crop_image_placeholder);
        imageView.setImageDrawable(defaultDrawable);
        ImageSize imageSize = new ImageSize(width, height);
        
        ImageLoader.getInstance().displayImage(path, new ImageViewAware(imageView), mOptionsStyle,imageSize,null,null);
        
       // imageView.setImageDrawable(null);
       /* GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(resources)
                .setFadeDuration(300)
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                .setPlaceholderImage(defaultDrawable)
                .setFailureImage(defaultDrawable)
                .setProgressBarImage(new ProgressBarDrawable())
                .build();
        final DraweeHolder<GenericDraweeHierarchy> draweeHolder = DraweeHolder.create(hierarchy, context);
        imageView.setOnImageViewListener(new GFImageView.OnImageViewListener() {
            @Override
            public void onDetach() {
                draweeHolder.onDetach();
            }

            @Override
            public void onAttach() {
                draweeHolder.onAttach();
            }

            @Override
            public boolean verifyDrawable(Drawable dr) {
                if (dr == draweeHolder.getHierarchy().getTopLevelDrawable()) {
                    return true;
                }
                return false;
            }
        });
        
        Uri uri = Uri.parse(path); 
        		
        displayImage(uri, new ResizeOptions(width, height), imageView, draweeHolder);*/
    }

    /**
     * 
     * 加载远程图片
     * @param url
     * @param imageSize
     * 
     */
   /* private void displayImage(Uri url, ResizeOptions imageSize, final ImageView imageView, final DraweeHolder<GenericDraweeHierarchy> draweeHolder) {
    	
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(url)
                .setResizeOptions(imageSize) //图片目标大小
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeHolder.getController())
                .setImageRequest(imageRequest)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String s, ImageInfo imageInfo, Animatable animatable) {
                        CloseableReference<CloseableImage> imageReference = null;
                        try {
                            imageReference = dataSource.getResult();
                            if (imageReference != null) {
                                CloseableImage image = imageReference.get();
                                if (image != null && image instanceof CloseableStaticBitmap) {
                                    CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) image;
                                    Bitmap bitmap = closeableStaticBitmap.getUnderlyingBitmap();
                                    if (bitmap != null && !bitmap.isRecycled() && imageView != null) {
                                        imageView.setImageBitmap(bitmap);
                                    }
                                }
                            }
                        } finally {
                            dataSource.close();
                            CloseableReference.closeSafely(imageReference);
                        }
                    }
                })
                .setTapToRetryEnabled(true)
                .build();
        draweeHolder.setController(controller);
    }
*/
    public void clearMemoryCache() {

    }
}
