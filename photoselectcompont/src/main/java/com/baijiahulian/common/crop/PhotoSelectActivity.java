package com.baijiahulian.common.crop;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baijiahulian.common.crop.model.PhotoFolderInfo;
import com.baijiahulian.common.crop.model.PhotoInfo;
import com.baijiahulian.common.crop.permissions.AfterPermissionGranted;
import com.baijiahulian.common.crop.permissions.EasyPermissions;
import com.baijiahulian.common.crop.uikit.CircleColorView;
import com.baijiahulian.common.crop.uikit.GFImageView;
import com.baijiahulian.common.crop.utils.CCDispatchAsync;
import com.baijiahulian.common.crop.utils.PhotoTools;
import com.baijiahulian.common.utils.StringUtils;

/**
 * Created by yanglei on 16/1/23.
 */
public class PhotoSelectActivity extends PhotoBaseActivity {

    protected static void launch(Context context) {
        Intent intent = new Intent(context, PhotoSelectActivity.class);
        context.startActivity(intent);
    }

    protected static void launch(Context context,List<PhotoInfo> photoList) {
        Intent intent = new Intent(context, PhotoSelectActivity.class);
    	intent.putExtra("imgList", (Serializable)photoList);
        context.startActivity(intent);
    }

    private PhotoSelectViewModel mViewModel;
    private FoldersAdapter mFoldersAdapter;
    private PhotoFolderInfo mSelectFolder;
    private ArrayList<PhotoFolderInfo> mAllPhotoFolders;
    private boolean mHasRefreshGallery = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ImageCropProxy.getFunctionConfig() == null ||
                ImageCropProxy.getThemeConfig() == null) {
            resultFailure(getString(R.string.common_crop_please_reopen_gf), true);
            return;
        }
        
        List<PhotoInfo> photoList = (List<PhotoInfo>) getIntent().getSerializableExtra("imgList");
        mAllPhotoFolders = new ArrayList<>();

        mFoldersAdapter = new FoldersAdapter();
        mViewModel = new PhotoSelectViewModel();
        mViewModel.initPhotoSelectList(photoList) ;
        mViewModel.setUI(this, mFoldersAdapter);

        requestGalleryPermission();

        if (ImageCropProxy.getFunctionConfig().isSingleCamera()) {
            takePhotoAction();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mViewModel != null) {
            mViewModel.refreshUI();
            mViewModel.refreshPhotos();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (! mViewModel.onKeyDown(keyCode, event)) {
            return super.onKeyDown(keyCode, event);
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mHasRefreshGallery) {
            mHasRefreshGallery = false;
            requestGalleryPermission();
        }
    }

    @Override
    protected void takeResult(PhotoInfo info) {

        if (ImageCropProxy.getFunctionConfig().isMultiModel()) {
            if (ImageCropProxy.getFunctionConfig().getSelectedList().size() <
                    ImageCropProxy.getFunctionConfig().getMaxSize()) {
                ImageCropProxy.getFunctionConfig().getSelectedList()
                        .put(info.getPhotoPath(), info);
            }
        } else {
            ImageCropProxy.getFunctionConfig().getSelectedList().clear();
            ImageCropProxy.getFunctionConfig().getSelectedList()
                    .put(info.getPhotoPath(), info);

            if (ImageCropProxy.getFunctionConfig().isEnableCrop()) {
                // edit photo
                PhotoEditActivity.launch(this);
            } else {
                resultData();
                return;
            }
        }

        takeRefreshGallery(info);
    }

    private void takeRefreshGallery(PhotoInfo photoInfo) {
        List<PhotoInfo> photoInfoList = mAllPhotoFolders.get(0).getPhotoList();
        if (photoInfoList == null) {
            photoInfoList = new ArrayList<>();
        }

        photoInfoList.add(0, photoInfo);
        mAllPhotoFolders.get(0).setPhotoList(photoInfoList);

        String folderA = new File(photoInfo.getPhotoPath()).getParent();
        boolean find = false;
        for (int i = 1; i < mAllPhotoFolders.size(); i++) {
            PhotoFolderInfo folderInfo = mAllPhotoFolders.get(i);
            String folderB = null;
            if (!StringUtils.isEmpty(folderInfo.getFolderName())) {
                folderB = new File(folderInfo.getCoverPhoto().getPhotoPath()).getParent();
            }
            if (TextUtils.equals(folderA, folderB)) {
                List<PhotoInfo> list = folderInfo.getPhotoList();
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(0, photoInfo);
                folderInfo.setPhotoList(list);
                if ( list.size() == 1 ) {
                    folderInfo.setCoverPhoto(photoInfo);
                }

                find = true;
                break;
            }
        }

        if (! find) {
            PhotoFolderInfo folder = new PhotoFolderInfo();
            folder.setFolderName(new File(photoInfo.getPhotoPath()).getParentFile().getName());
            ArrayList<PhotoInfo> list = new ArrayList<>();
            list.add(photoInfo);
            folder.setCoverPhoto(photoInfo);
            folder.setPhotoList(list);

            if (mAllPhotoFolders.size() > 1) {
                mAllPhotoFolders.add(1, folder);
            } else {
                mAllPhotoFolders.add(folder);
            }
            if (mSelectFolder != mAllPhotoFolders.get(0)) {
                mSelectFolder = folder;
            }
        }

        mViewModel.setCurrentFolder(mSelectFolder);
        mViewModel.refreshUI();
        mFoldersAdapter.notifyDataSetChanged();
    }

    private void getPhotos() {
        CCDispatchAsync.dispatchAsync(new CCDispatchAsync.CCDisPatchRunnable() {
            @Override
            public void runInBackground() {
                mAllPhotoFolders.clear();
                List<PhotoFolderInfo> allFolderList = PhotoTools.getAllPhotoFolder(PhotoSelectActivity.this);

                mAllPhotoFolders.addAll(allFolderList);
                if (mSelectFolder == null) {
                    mSelectFolder = mAllPhotoFolders.get(0);
                }
            }

            @Override
            public void runInMain() {
                mViewModel.setCurrentFolder(mSelectFolder);
            }
        });
    }

    @Override
    public void onPermissionsGranted(List<String> list) {
        getPhotos();
    }

    /**
     * 
     */
    
    @AfterPermissionGranted(ImageCropProxy.PERMISSIONS_CODE_GALLERY)
    private void requestGalleryPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            getPhotos();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.common_crop_permissions_tips_gallery),
                    ImageCropProxy.PERMISSIONS_CODE_GALLERY, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    protected class FoldersAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mAllPhotoFolders == null ? 0 : mAllPhotoFolders.size();
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
				convertView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_folder_list, parent, false);
				viewHolder = new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
            
		    PhotoFolderInfo folderInfo = mAllPhotoFolders.get(position);
		    
		    if(ImageCropProxy.getFunctionConfig().getImageLoader() != null){
		        ImageCropProxy.getFunctionConfig().getImageLoader().displayImage(PhotoSelectActivity.this, "file://"+folderInfo.getCoverPhoto().getPhotoPath(),
	            		viewHolder.folderAvatar, getResources().getDimensionPixelOffset(R.dimen.common_crop_width_folder_img),
	                            getResources().getDimensionPixelOffset(R.dimen.common_crop_width_folder_img),null);
		    }

            viewHolder.folderName.setText(folderInfo.getFolderName());
            viewHolder.picNumber.setText(getString(R.string.common_crop_pic_number, folderInfo.getPhotoList().size()));

            if (folderInfo == mSelectFolder) {
            	viewHolder.checkedView.setVisibility(View.VISIBLE);
            } else {
            	viewHolder.checkedView.setVisibility(View.INVISIBLE);
            }

            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mSelectFolder = mAllPhotoFolders.get(position);
                    mViewModel.setCurrentFolder(mSelectFolder);
                    mFoldersAdapter.notifyDataSetChanged();
                    mViewModel.toggleFolderPannel();
                }
            });
			
			return convertView;
		}
     
    }

    private static class ViewHolder {

        private GFImageView folderAvatar;
        private TextView folderName;
        private TextView picNumber;
        private CircleColorView checkedView;
        public ViewHolder(View itemView) {
        	
            folderAvatar = (GFImageView) itemView.findViewById(R.id.item_photo_folder_img_ci);
            folderName = (TextView) itemView.findViewById(R.id.item_photo_folder_name_tv);
            picNumber = (TextView) itemView.findViewById(R.id.item_photo_folder_num_tv);
            checkedView = (CircleColorView) itemView.findViewById(R.id.item_photo_folder_checked_cc);

            checkedView.setSelected(true);
            checkedView.setColor(ImageCropProxy.getThemeConfig().getMainElementsColor());
        }
    }
}
