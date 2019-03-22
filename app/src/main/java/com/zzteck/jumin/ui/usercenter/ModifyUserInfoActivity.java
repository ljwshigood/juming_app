package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baijiahulian.common.crop.BJCommonImageCropHelper;
import com.baijiahulian.common.crop.PhotoSelectActivity;
import com.baijiahulian.common.crop.ThemeConfig;
import com.baijiahulian.common.crop.model.PhotoInfo;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.zzview.WindowsToast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ModifyUserInfoActivity extends BaseActivity implements OnClickListener{

	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private RelativeLayout mRlUploadPhoto ;

	private void initView() {
		mRlUploadPhoto = findViewById(R.id.rl_upload_photo) ;
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("修改头像");
		mRlBack.setVisibility(View.VISIBLE);
		mRlUploadPhoto.setOnClickListener(this);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_modify_user_info);

		mContext = ModifyUserInfoActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.ll_back :
				finish();
				break ;
			case R.id.rl_upload_photo:

				BJCommonImageCropHelper.openImageSingleAblum(ModifyUserInfoActivity.this, BJCommonImageCropHelper.PhotoCropType.None,
						new ThemeConfig.Builder().setMainElementsColor(Color.parseColor("#00ccff")).setTitlebarRightButtonText(R.string.complete).build(), new BJCommonImageCropHelper.OnHandlerResultCallback(){

							@Override
							public void onHandlerSuccess(List<PhotoInfo> resultList) {

								/*MediaInfo media = new MediaInfo();
								media.setType(0);
								media.setFilePath(resultList.get(0).getPhotoPath());
								mPictureList.add(media);

								if(mPictureList.size() > 0){
									mIvAdd.setVisibility(View.GONE);
								}

								initMediaData();
								compressFileList();*/

							}

							@Override
							public void onHandlerFailure(String errorMsg) {

							}

						});
				break ;
		}
	}
}
