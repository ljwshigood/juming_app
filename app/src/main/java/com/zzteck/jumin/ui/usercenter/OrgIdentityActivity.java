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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
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

	private MediaInfo mMediaFront ;

	private MediaInfo mMediaOrg ;

	private ImageView mIvFront ;

	private ImageView mIvOrg ;

	private ImageView mIvAdd ;



	private LinearLayout mLLComplete ;

	private EditText mEtName ;

	private EditText mEtNumber ;

	private RelativeLayout mLLPic ;

	private void initView() {

		mLLPic = findViewById(R.id.ll_pic) ;
		mIvAdd = findViewById(R.id.iv_add) ;
		mEtName = findViewById(R.id.et_name) ;
		mEtNumber = findViewById(R.id.et_number) ;

		mLLComplete = findViewById(R.id.ll_complete) ;
		mIvAdd.setOnClickListener(this);
		mIvFront = findViewById(R.id.iv_front) ;
		mIvOrg = findViewById(R.id.iv_org) ;

		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("企业认证");
		mRlBack.setVisibility(View.VISIBLE);

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

	private List<com.luck.picture.lib.entity.LocalMedia> selectList = new java.util.ArrayList<>() ;

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.ll_back :
				finish();
				break ;
			case R.id.iv_add:
				com.luck.picture.lib.PictureSelector.create(OrgIdentityActivity.this)
						.openGallery(com.luck.picture.lib.config.PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
						.theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
						.maxSelectNum(4)// 最大图片选择数量
						.minSelectNum(1)// 最小选择数量
						.imageSpanCount(4)// 每行显示个数
						.selectionMode(PictureConfig.SINGLE)// 多选 or 单选
						.previewImage(true)// 是否可预览图片
						.previewVideo(true)// 是否可预览视频
						.enablePreviewAudio(true) // 是否可播放音频
						.isCamera(true)// 是否显示拍照按钮
						.isZoomAnim(true)// 图片列表点击 缩放效果 默认true
						//.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
						//.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
						.enableCrop(true)// 是否裁剪
						.compress(true)// 是否压缩
						.synOrAsy(true)//同步true或异步false 压缩 默认同步
						//.compressSavePath(getPath())//压缩图片保存地址
						//.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
						.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
						.withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
						.hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
						.isGif(false)// 是否显示gif图片
						.freeStyleCropEnabled(true)// 裁剪框是否可拖拽
						.circleDimmedLayer(false)// 是否圆形裁剪
						.showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
						.showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
						.openClickSound(false)// 是否开启点击声音
						.selectionMedia(selectList)// 是否传入已选图片
						//.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
						//.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
						//.cropCompressQuality(90)// 裁剪压缩质量 默认100
						.minimumCompressSize(100)// 小于100kb的图片不压缩
						//.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
						//.rotateEnabled(true) // 裁剪是否可旋转图片
						//.scaleEnabled(true)// 裁剪是否可放大缩小图片
						//.videoQuality()// 视频录制质量 0 or 1
						//.videoSecond()//显示多少秒以内的视频or音频也可适用
						//.recordVideoSecond()//录制视频秒数 默认60s
						.forResult(com.luck.picture.lib.config.PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

				break ;
			/*case R.id.ll_org :
				com.luck.picture.lib.PictureSelector.create(OrgIdentityActivity.this)
						.openGallery(com.luck.picture.lib.config.PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
						.theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
						.maxSelectNum(4)// 最大图片选择数量
						.minSelectNum(1)// 最小选择数量
						.imageSpanCount(4)// 每行显示个数
						.selectionMode(com.luck.picture.lib.config.PictureConfig.SINGLE)// 多选 or 单选
						.previewImage(true)// 是否可预览图片
						.previewVideo(true)// 是否可预览视频
						.enablePreviewAudio(true) // 是否可播放音频
						.isCamera(true)// 是否显示拍照按钮
						.isZoomAnim(true)// 图片列表点击 缩放效果 默认true
						//.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
						//.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
						.enableCrop(true)// 是否裁剪
						.compress(true)// 是否压缩
						.synOrAsy(true)//同步true或异步false 压缩 默认同步
						//.compressSavePath(getPath())//压缩图片保存地址
						//.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
						.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
						.withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
						.hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
						.isGif(false)// 是否显示gif图片
						.freeStyleCropEnabled(true)// 裁剪框是否可拖拽
						.circleDimmedLayer(false)// 是否圆形裁剪
						.showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
						.showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
						.openClickSound(false)// 是否开启点击声音
						.selectionMedia(selectList)// 是否传入已选图片
						//.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
						//.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
						//.cropCompressQuality(90)// 裁剪压缩质量 默认100
						.minimumCompressSize(100)// 小于100kb的图片不压缩
						//.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
						//.rotateEnabled(true) // 裁剪是否可旋转图片
						//.scaleEnabled(true)// 裁剪是否可放大缩小图片
						//.videoQuality()// 视频录制质量 0 or 1
						//.videoSecond()//显示多少秒以内的视频or音频也可适用
						//.recordVideoSecond()//录制视频秒数 默认60s
						.forResult(com.luck.picture.lib.config.PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

				break ;*/
			case R.id.ll_complete :

				String org = (String) mLLPic.getTag();

				if(TextUtils.isEmpty(mEtName.getText().toString().trim())){
					WindowsToast.makeText(mContext,"公司名称不能为空").show();
					return ;
				}else if(TextUtils.isEmpty(mEtNumber.getText().toString().trim())){
					WindowsToast.makeText(mContext,"营业执照不能为空").show();
					return ;
				}else if(TextUtils.isEmpty(org)){
					WindowsToast.makeText(mContext,"请上传营业执照").show();
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

				String org = (String) mLLPic.getTag();

				MultipartBody body = RequestBuilder.uploadRequestBody5(OrgIdentityActivity.this,mEtName.getText().toString().trim(),mEtNumber.getText().toString().trim(),
						new File(org));


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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(data == null){
			return ;
		}

		if(requestCode == PictureConfig.CHOOSE_REQUEST && data != null){
			// 图片选择结果回调
			selectList = PictureSelector.obtainMultipleResult(data);

			RequestOptions options = new RequestOptions()
					.centerCrop()
					.placeholder(R.mipmap.default_pic)
					.diskCacheStrategy(DiskCacheStrategy.ALL);

			Glide.with(mContext)
					.load(selectList.get(0).getCompressPath())
					.apply(options)
					.into(mIvOrg);

			mLLPic.setTag(selectList.get(0).getCompressPath());

		}

	}


}
