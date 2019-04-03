package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.PictureUtil;
import com.zzteck.jumin.utils.UtilsTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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


	private void identity(String cname,String idc){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Member.Personcer") ;
		map.put("cname",cname) ;
		map.put("idc",idc) ;

		map.put("sign", UtilsTools.getSign(mContext,"App.Member.Personcer")) ;

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().get().url(Constants.HOST+"?"+ UtilsTools.getMapToString(map)).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Log.e("liujw","##########################IOException : "+e.toString());
			}

			@Override
			public void onResponse(Call call, final Response response) throws IOException {
				final String responseStr = response.body().string();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						String message = new String(responseStr.getBytes()) ;
						Gson gson = new Gson() ;

						/*LoginBean bean = gson.fromJson(message,LoginBean.class) ;
						if(bean.getData().isIs_login()){

							UserDAO.getInstance(mContext).editorUserTable(bean.getData());

							Intent intent = new Intent(mContext,MainActivity.class) ;
							startActivity(intent);
							finish();
						}*/

					}
				});
			}
		});
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
					return ;
				}else if(TextUtils.isEmpty(mEtNumber.getText().toString().trim())){
					return ;
				}else {
					identity(mEtName.getText().toString().trim(),mEtNumber.getText().toString().trim());
				}
				break ;
		}

	}
}
