package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import com.baijiahulian.common.crop.BJCommonImageCropHelper;
import com.baijiahulian.common.crop.ThemeConfig;
import com.baijiahulian.common.crop.model.PhotoInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zx.uploadlibrary.listener.ProgressListener;
import com.zx.uploadlibrary.listener.impl.UIProgressListener;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.MediaInfo;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.OKHttpUtils;
import com.zzteck.jumin.utils.PictureUtil;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.zzview.WindowsToast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class PersionIdentityActivity extends BaseActivity implements OnClickListener{


	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private LinearLayout mLLFront ;

	private LinearLayout mLLOrg ;

	private EditText mEtName ;

	private EditText mEtNumber ;

	private LinearLayout mLlComplete ;

	private void initView() {

		mLlComplete = findViewById(R.id.ll_complete) ;
		mEtName = findViewById(R.id.et_name) ;
		mEtNumber = findViewById(R.id.et_number) ;
		mIvFront = findViewById(R.id.iv_front) ;
		mIvOrg = findViewById(R.id.iv_org) ;
		mLLFront = findViewById(R.id.ll_front) ;
		mLLOrg = findViewById(R.id.ll_org) ;
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("个人认证");
		mRlBack.setVisibility(View.VISIBLE);
		mLLFront.setOnClickListener(this);
		mLLOrg.setOnClickListener(this);

		mLlComplete.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_persion_identity);

		mContext = PersionIdentityActivity.this ;
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

	private MediaInfo mMediaFront ;

	private MediaInfo mMediaOrg ;

	private ImageView mIvFront ;

	private ImageView mIvOrg ;

	private void initMediaDataOrg(MediaInfo info) {

		Glide.with(mContext)
				.load(info.getFilePath())
				.placeholder(R.mipmap.ic_launcher)
				.error(R.mipmap.ic_launcher)
				.crossFade(300)
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(mIvOrg);


	}

	private void initMediaDataFront(MediaInfo info) {

		Glide.with(mContext)
				.load(info.getFilePath())
				.placeholder(R.mipmap.ic_launcher)
				.error(R.mipmap.ic_launcher)
				.crossFade(300)
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(mIvFront);

	}

	private void compressFileListOrg(){
		new Thread(){

			public void run() {

				if(mMediaOrg.getIsCompressFile() == 0){
					String compressString = PictureUtil.compressPicture(mContext,mMediaOrg.getFilePath());
					if(compressString != null){
						mMediaOrg.setCompressFile(compressString);
						mMediaOrg.setIsCompressFile(1);
					}else{
						mMediaOrg.setLoadFlag(2);
					}
				}
			}

		}.start();
	}

	private void compressFileListFront(){
		new Thread(){

			public void run() {

				if(mMediaFront.getIsCompressFile() == 0){
					String compressString = PictureUtil.compressPicture(mContext,mMediaFront.getFilePath());
					if(compressString != null){
						mMediaFront.setCompressFile(compressString);
						mMediaFront.setIsCompressFile(1);
					}else{
						mMediaFront.setLoadFlag(2);
					}
				}
			}

		}.start();
	}

	//初始化上传文件的数据
	private List<String> initUploadFile(){
		List<String> fileNames = new ArrayList<>();
		fileNames.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
				+ File.separator + "test.txt"); //txt文件
		fileNames.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
				+ File.separator + "bell.png"); //图片
		fileNames.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
				+ File.separator + "kobe.mp4"); //视频
		fileNames.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
				+ File.separator + "xinnian.mp3"); //音乐
		return fileNames;
	}

	//多文件上传（带进度）
	private void identity() {
		//这个是非ui线程回调，不可直接操作UI
		final ProgressListener progressListener = new ProgressListener() {
			@Override
			public void onProgress(long bytesWrite, long contentLength, boolean done) {
				Log.i("TAG", "bytesWrite:" + bytesWrite);
				Log.i("TAG", "contentLength" + contentLength);
				Log.i("TAG", (100 * bytesWrite) / contentLength + " % done ");
				Log.i("TAG", "done:" + done);
				Log.i("TAG", "================================");
			}
		};


		//这个是ui线程回调，可直接操作UI
		UIProgressListener uiProgressRequestListener = new UIProgressListener() {
			@Override
			public void onUIProgress(long bytesWrite, long contentLength, boolean done) {
				Log.i("TAG", "bytesWrite:" + bytesWrite);
				Log.i("TAG", "contentLength" + contentLength);
				Log.i("TAG", (100 * bytesWrite) / contentLength + " % done ");
				Log.i("TAG", "done:" + done);
				Log.i("TAG", "================================");
				//ui层回调,设置当前上传的进度值
				int progress = (int) ((100 * bytesWrite) / contentLength);
			//	uploadProgress.setProgress(progress);
				//uploadTV.setText("上传进度值：" + progress + "%");
			}

			//上传开始
			@Override
			public void onUIStart(long bytesWrite, long contentLength, boolean done) {
				super.onUIStart(bytesWrite, contentLength, done);
				Toast.makeText(getApplicationContext(),"开始上传",Toast.LENGTH_SHORT).show();
			}

			//上传结束
			@Override
			public void onUIFinish(long bytesWrite, long contentLength, boolean done) {
				super.onUIFinish(bytesWrite, contentLength, done);
				//uploadProgress.setVisibility(View.GONE); //设置进度条不可见
				Toast.makeText(getApplicationContext(),"上传成功",Toast.LENGTH_SHORT).show();

			}
		};


		//开始Post请求,上传文件
		OKHttpUtils.doPostRequest(Constants.HOST, initUploadFile(), uiProgressRequestListener, new Callback() {
			@Override
			public void onFailure(Call call, final IOException e) {
				Log.i("TAG", "error------> "+e.getMessage());
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(PersionIdentityActivity.this, "上传失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.i("TAG", "success---->"+response.body().string());
			}
		},mEtName.getText().toString().trim(),mEtNumber.getText().toString().trim(), UtilsTools.getSign(mContext,"App.Member.Personcer"));

	}



	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.ll_back :
				finish();
				break ;
			case R.id.ll_front:
				BJCommonImageCropHelper.openImageSingleAblum(PersionIdentityActivity.this, BJCommonImageCropHelper.PhotoCropType.None,
						new ThemeConfig.Builder().setMainElementsColor(Color.parseColor("#00ccff")).setTitlebarRightButtonText(R.string.complete).build(), new BJCommonImageCropHelper.OnHandlerResultCallback(){

							@Override
							public void onHandlerSuccess(List<PhotoInfo> resultList) {

								mMediaFront = new MediaInfo();
								mMediaFront.setType(0);
								mMediaFront.setFilePath(resultList.get(0).getPhotoPath());
								initMediaDataFront(mMediaFront);
								compressFileListFront();

							}

							@Override
							public void onHandlerFailure(String errorMsg) {

							}

						});
				break ;
			case R.id.ll_org :
				BJCommonImageCropHelper.openImageSingleAblum(PersionIdentityActivity.this, BJCommonImageCropHelper.PhotoCropType.None,
						new ThemeConfig.Builder().setMainElementsColor(Color.parseColor("#00ccff")).setTitlebarRightButtonText(R.string.complete).build(), new BJCommonImageCropHelper.OnHandlerResultCallback(){

							@Override
							public void onHandlerSuccess(List<PhotoInfo> resultList) {

								mMediaOrg = new MediaInfo();
								mMediaOrg.setType(0);
								mMediaOrg.setFilePath(resultList.get(0).getPhotoPath());
								initMediaDataOrg(mMediaOrg) ;
								compressFileListOrg();

							}

							@Override
							public void onHandlerFailure(String errorMsg) {

							}

						});
				break ;
			case R.id.ll_complete :
				if(TextUtils.isEmpty(mEtName.getText().toString().trim())){
					WindowsToast.makeText(mContext,"名称不能为空").show();
					return ;
				}else if(TextUtils.isEmpty(mEtNumber.getText().toString().trim())){
					WindowsToast.makeText(mContext,"身份证不能为空").show();
					return ;
				}else {
					identity();
				}
				break ;
		}

	}
}
