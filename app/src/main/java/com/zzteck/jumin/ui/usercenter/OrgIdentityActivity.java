package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.baijiahulian.common.crop.ThemeConfig;
import com.baijiahulian.common.crop.model.PhotoInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.MediaInfo;
import com.zzteck.jumin.bean.UploadInfo;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.PictureUtil;
import com.zzteck.jumin.webmanager.CountingRequestBody;
import com.zzteck.jumin.webmanager.RequestBuilder;
import com.zzteck.zzview.WindowsToast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OrgIdentityActivity extends BaseActivity implements OnClickListener{


	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private LinearLayout mLLFront ;

	private LinearLayout mLLOrg ;

	private MediaInfo mMediaFront ;

	private MediaInfo mMediaOrg ;

	private ImageView mIvFront ;

	private ImageView mIvOrg ;

	private void initMediaDataOrg(MediaInfo info) {

		Glide.with(mContext)
				.load(info.getFilePath())
				.placeholder(R.mipmap.default_pic)
				.error(R.mipmap.default_pic)
				.crossFade(300)
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(mIvOrg);


	}

	private void initMediaDataFront(MediaInfo info) {

		Glide.with(mContext)
				.load(info.getFilePath())
				.placeholder(R.mipmap.default_pic)
				.error(R.mipmap.default_pic)
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


	private LinearLayout mLLComplete ;

	private EditText mEtName ;

	private EditText mEtNumber ;

	private void initView() {

		mEtName = findViewById(R.id.et_name) ;
		mEtNumber = findViewById(R.id.et_number) ;

		mLLComplete = findViewById(R.id.ll_complete) ;
		mIvFront = findViewById(R.id.iv_front) ;
		mIvOrg = findViewById(R.id.iv_org) ;

		mLLFront = findViewById(R.id.ll_front) ;
		mLLOrg = findViewById(R.id.ll_org) ;

		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("企业认证");
		mRlBack.setVisibility(View.VISIBLE);

		mLLFront.setOnClickListener(this);
		mLLOrg.setOnClickListener(this);
		mLLComplete.setOnClickListener(this);
	}

	private OkHttpClient client ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_org_identity);

		mContext = OrgIdentityActivity.this ;
		App.getInstance().addActivity(this);

		client = new OkHttpClient() ;

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
			case R.id.ll_front:
				BJCommonImageCropHelper.openImageSingleAblum(OrgIdentityActivity.this, BJCommonImageCropHelper.PhotoCropType.None,
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
				BJCommonImageCropHelper.openImageSingleAblum(OrgIdentityActivity.this, BJCommonImageCropHelper.PhotoCropType.None,
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
					WindowsToast.makeText(mContext,"公司名称不能为空").show();
					return ;
				}else if(TextUtils.isEmpty(mEtNumber.getText().toString().trim())){
					WindowsToast.makeText(mContext,"营业执照不能为空").show();
					return ;
				}else if(mMediaFront == null || TextUtils.isEmpty(mMediaFront.getCompressFile())){
					WindowsToast.makeText(mContext,"请上传营业执照正面照片").show();
					return ;
				}else if(mMediaOrg == null || TextUtils.isEmpty(mMediaOrg.getCompressFile())){
					WindowsToast.makeText(mContext,"请上传营业执照反面照片").show();
					return ;
				}else{
					try {
						upload(Constants.HOST);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				break ;
		}

	}

	private void upload(final String url) throws Exception {

		new AsyncTask<Integer, Integer, String>() {

			@Override
			protected String doInBackground(Integer... params) {
				MultipartBody body = RequestBuilder.uploadRequestBody3(OrgIdentityActivity.this,mEtName.getText().toString().trim(),mEtNumber.getText().toString().trim(),
						new File(mMediaFront.getCompressFile()),new File(mMediaOrg.getCompressFile()));


				CountingRequestBody monitoredRequest = new CountingRequestBody(body, new CountingRequestBody.Listener() {
					@Override
					public void onRequestProgress(long bytesWritten, long contentLength) {
						float percentage = 100f * bytesWritten / contentLength;
						if (percentage >= 0) {
							publishProgress(Math.round(percentage));
							Log.e("progress ", percentage + "");
						} else {
							Log.e("No progress ", 0 + "");
						}
					}
				});

				Request request = new Request.Builder()
						.url(url)
						.post(monitoredRequest)
						.build();
				Call response = client.newCall(request) ;

				response.enqueue(new Callback() {
					@Override
					public void onFailure(Call call, IOException e) {
						Log.e("liujw","####################onFailure");
					}

					@Override
					public void onResponse(Call call, Response response) throws IOException {
						final String responseStr = response.body().string();
						Gson gson = new Gson() ;
						final UploadInfo bean = gson.fromJson(responseStr, UploadInfo.class) ;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								WindowsToast.makeText(mContext,bean.getData().getInfo()).show();
								if(bean.getData().isIs_login()){
									finish();
								}
								Log.e("liujw","####################responseStr "+responseStr);
							}
						});

					}
				});

				return "";
			}

			@Override
			protected void onPostExecute(String result) {
			}

			@Override
			protected void onPreExecute() {
			}

			@Override
			protected void onProgressUpdate( Integer... values) {

			}


		}.execute();
	}


}
