package com.baijiahulian.common.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baijiahulian.common.crop.model.PhotoFolderInfo;
import com.baijiahulian.common.crop.model.PhotoInfo;
import com.baijiahulian.common.crop.uikit.GFImageView;
import com.baijiahulian.common.crop.utils.DeviceUtils;
import com.baijiahulian.common.utils.DataHolder;

/**
 * Created by yanglei on 16/1/23.
 */
public class PhotoSelectViewModel implements View.OnClickListener {

    private ImageButton mButtonBack;
    private TextView mTitleView;
    private Button mRightButton;
    private GridView mPhotosView;
    private ListView mFoldersView;

    private TextView mToggleFolderTextView;
    private TextView mPreviewTextView;

    private PhotoFolderInfo mCurrentFolderInfo;
    private ArrayList<PhotoInfo> mCurPhotos;

    private PhotoSelectActivity mActivity;

    private PhotosAdapter mPhotosAdapter;

    private View mFolderViewPannel;

    private DisplayMetrics mDisplayMetrics;
    
    private List<PhotoInfo> mPhotoSelectList ;
    
    private LinearLayout mLLProgressLoading;

    public void initPhotoSelectList(List<PhotoInfo> list){
    	if(list == null) return ;
    	this.mPhotoSelectList  = list ;
    	for(int i = 0 ;i < mPhotoSelectList.size() ;i++){
    		PhotoInfo photoInfo = mPhotoSelectList.get(i);
    		ImageCropProxy.getFunctionConfig().getSelectedList().put(photoInfo.getPhotoPath(), photoInfo);
    	}
    }

	public void setUI(PhotoSelectActivity activity, PhotoSelectActivity.FoldersAdapter foldersAdapter) {
        mActivity = activity;
        mCurPhotos = new ArrayList<>();
        mDisplayMetrics = DeviceUtils.getScreenPix(activity);

        activity.setContentView(R.layout.activity_photo_selected);

        mButtonBack = (ImageButton) activity.findViewById(R.id.title_bar_back_button_ib);
        mTitleView = (TextView) activity.findViewById(R.id.title_bar_title_tv);
        mRightButton = (Button) activity.findViewById(R.id.title_bar_right_button_cb);
        mLLProgressLoading = (LinearLayout)activity.findViewById(R.id.ll_photoselect_loading);

        mPhotosView = (GridView) activity.findViewById(R.id.activity_photo_selected_photos_list);
        mFoldersView = (ListView) activity.findViewById(R.id.activity_photo_selected_folders_list);

       /* mPhotosView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));  
        mFoldersView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));  */
        
        mFoldersView.setAdapter(foldersAdapter);
        mPhotosAdapter = new PhotosAdapter();
        mPhotosView.setAdapter(mPhotosAdapter);

        mToggleFolderTextView = (TextView) activity.findViewById(R.id.activity_photo_selected_toggle_folders_tv);
        mPreviewTextView = (TextView) activity.findViewById(R.id.activity_photo_selected_preview_tv);

        mFolderViewPannel = activity.findViewById(R.id.activit_photo_selected_folders_container_fl);

        mButtonBack.setOnClickListener(this);
        mRightButton.setOnClickListener(this);
        activity.findViewById(R.id.activity_photo_selected_toggler_folder_container_ll).setOnClickListener(this);
        mPreviewTextView.setOnClickListener(this);
    }
	

    public void refreshUI() {
        String folderName = mCurrentFolderInfo == null ? mActivity.getString(R.string.common_crop_all_photo) :
                mCurrentFolderInfo.getFolderName() + "";
        mTitleView.setText(folderName);
        mToggleFolderTextView.setText(folderName);

        String rightText = mActivity.getString(ImageCropProxy.getThemeConfig().getTitleBarRightButtonText());
        FunctionConfig config = ImageCropProxy.getFunctionConfig();
        if (config.isMultiModel() && config.getSelectedList().size() > 0) {
        	if(config.getMaxSize() == 100){
	    		 String text = "(" + config.getSelectedList().size() +  ")";
	    		 rightText += text;
        	}else {
        		String text = "(" + config.getSelectedList().size() + "/" + config.getMaxSize() + ")";
        		rightText += text;
        	}
            
        }

        if (config.getSelectedList().size() >= 0) {
            mRightButton.setEnabled(true);
        } else {
            mRightButton.setEnabled(false);
        }

        
        mRightButton.setText(rightText);
        mRightButton.setBackgroundColor(ImageCropProxy.getThemeConfig().getMainElementsColor());

        if (! ImageCropProxy.getFunctionConfig().isMultiModel()) {
           mRightButton.setVisibility(View.GONE);
        }


        mPreviewTextView.setText(mActivity.getString(R.string.common_crop_preview, config.getSelectedList().size()));
        if (config.isMultiModel()) {
            mPreviewTextView.setEnabled(config.getSelectedList().size() > 0);
        } else {
            mPreviewTextView.setVisibility(View.GONE);
        }

    }

    public void refreshPhotos() {
        mPhotosAdapter.notifyDataSetChanged();
    }

    public void setCurrentFolder(PhotoFolderInfo folderInfo) {
        mCurrentFolderInfo = folderInfo;
        mCurPhotos.clear();
        mCurPhotos.addAll(folderInfo.getPhotoList());
        mPhotosAdapter.notifyDataSetChanged();
        refreshUI();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mFolderViewPannel.getVisibility() == View.VISIBLE) {
                toggleFolderPannel();
                return true;
            }
        }
        return false;
    }

    public void toggleFolderPannel() {
        if (mFolderViewPannel.getVisibility() == View.VISIBLE) {
            mFolderViewPannel.setVisibility(View.GONE);
            mFolderViewPannel.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.common_crop_out_up_to_bottom));
        } else {
            mFolderViewPannel.setVisibility(View.VISIBLE);
            mFolderViewPannel.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.common_crop_in_bottom_to_up));
        }
    }
    
    

    @Override
    public void onClick(View v) {
        if (v == mButtonBack) {
            if (mFolderViewPannel.getVisibility() == View.VISIBLE) {
                toggleFolderPannel();
            } else {
                mActivity.finish();
            }
        } else if (v == mRightButton) {
        	 mLLProgressLoading.setVisibility(View.VISIBLE);
        	 TimerTask task = new TimerTask(){    
        	     public void run(){    
        	    	 mActivity.resultData();
        	     }    	
        	 };    
    		 Timer timer = new Timer();  
    		 timer.schedule(task, 200); 
           
        } else if (v.getId() == R.id.activity_photo_selected_toggler_folder_container_ll) {
            toggleFolderPannel();
        } else if (v == mPreviewTextView) {
            if (mFolderViewPannel.getVisibility() == View.VISIBLE) {
                toggleFolderPannel();
            }
            if (ImageCropProxy.getFunctionConfig().getSelectedList().size() > 0) {
                ArrayList<PhotoInfo> list = new ArrayList<>();
                list.addAll(ImageCropProxy.getFunctionConfig().getSelectedList().values());
                PhotoPreviewActivity.launch(mActivity, list,0,1,0);
            }
        }
    }
    

    private class PhotosAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			  return  mCurPhotos == null ? 0 : mCurPhotos.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			
			ViewHolder viewHolder = null ;
			if (convertView == null) {
				convertView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_list_photo, parent, false);
				viewHolder = new ViewHolder();
				viewHolder.imageView = (GFImageView) convertView.findViewById(R.id.item_photo_list_photo_ci);
				viewHolder.checkBox = (ImageButton)convertView.findViewById(R.id.item_photo_list_photo_checkbox);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
            final PhotoInfo photoInfo = mCurPhotos.get(position);
            
            final ImageButton mCheckBox = viewHolder.checkBox ;
            
            if(ImageCropProxy.getFunctionConfig() != null && ImageCropProxy.getFunctionConfig().getImageLoader() != null){
            	 ImageCropProxy.getFunctionConfig().getImageLoader()
                 .displayImage(mActivity, "file://"+photoInfo.getPhotoPath(),
                		 viewHolder.imageView,100,100,null);
            }

             viewHolder.imageView.setOnClickListener(new View.OnClickListener() {

                 @Override
                 public void onClick(View v) {
                     if (ImageCropProxy.getFunctionConfig() != null && ImageCropProxy.getFunctionConfig().isMultiModel()) {
                    	 DataHolder.getInstance().setData(mCurPhotos) ;
                         PhotoPreviewActivity.launch(mActivity, mCurPhotos,position,1,0);
                     } else {
                         ImageCropProxy.getFunctionConfig().getSelectedList().clear();
                         ImageCropProxy.getFunctionConfig().getSelectedList().put(photoInfo.getPhotoPath(), photoInfo);
                         if (! ImageCropProxy.getFunctionConfig().isEnableCrop()) {
                        	 //	PhotoPreviewActivity.launch(mActivity, mCurPhotos,position,0,0);
                        	    mActivity.resultData();
                         } else {
                             PhotoEditActivity.launch(mActivity);
                         }
                     }
                 }
             });

             if (ImageCropProxy.getFunctionConfig() != null && ImageCropProxy.getFunctionConfig().isMultiModel()) {
            	 viewHolder.checkBox.setVisibility(View.VISIBLE);
                 boolean checked = ImageCropProxy.getFunctionConfig().getSelectedList().containsKey(photoInfo.getPhotoPath());
                 viewHolder.checkBox.setImageResource(checked?R.drawable.common_crop_ic_checkbox_checked:R.drawable.common_crop_ic_checkbox_unchecked);

                 viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

                     @Override
                     public void onClick(View v) {
                         boolean checked = ImageCropProxy.getFunctionConfig().getSelectedList().containsKey(photoInfo.getPhotoPath());

                         if (! checked &&  ImageCropProxy.getFunctionConfig().getSelectedList().size() <
                                     ImageCropProxy.getFunctionConfig().getMaxSize()) {
                                 ImageCropProxy.getFunctionConfig().getSelectedList().put(photoInfo.getPhotoPath(), photoInfo);
                                 mCheckBox.setImageResource(R.drawable.common_crop_ic_checkbox_checked);
                         } else if (! checked) {
                             mActivity.toast(mActivity.getString(R.string.common_crop_tips_max_count,
                                     ImageCropProxy.getFunctionConfig().getMaxSize()));
                         } else {
                             ImageCropProxy.getFunctionConfig().getSelectedList().remove(photoInfo.getPhotoPath());
                             mCheckBox.setImageResource(R.drawable.common_crop_ic_checkbox_unchecked);
                         }

                         refreshUI();
                     }
                 });
             } else {
            	 viewHolder.checkBox.setVisibility(View.GONE);
                 viewHolder.checkBox.setOnClickListener(null);
             }
			return convertView;
		}
    }
    
    class ViewHolder {
    	GFImageView imageView ;
    	ImageButton checkBox ;
    }
    
    
   /* private static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }*/
}
