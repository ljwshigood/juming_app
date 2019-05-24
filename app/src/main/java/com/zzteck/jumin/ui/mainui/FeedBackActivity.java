package com.zzteck.jumin.ui.mainui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.FeedAdapter;
import com.zzteck.jumin.adapter.ImageAdapter;
import com.zzteck.jumin.bean.FeedBack;
import com.zzteck.jumin.bean.MediaInfo;

import java.util.ArrayList;
import java.util.List;


public class FeedBackActivity extends BaseActivity implements OnClickListener{


	private TextView mTvMainInfo ;

	private RelativeLayout mLLBack ;

	private String [] mString = new String[]{"帮助很大","界面很好看","虚假信息多","界面卡顿","引导不清晰","服务质量不高"} ;

	private LinearLayout mLLCommit ;

	private FeedAdapter mFeedBackApdater ;

	private RecyclerView mRVFeedBack ;

	private ImageView mIvAddPicture ;

	private RecyclerView mRvAddPicture ;

	private ImageAdapter mImageAdapter ;

	//private List<PhotoInfo> mPhotoList = new ArrayList<>();

	private void initData(){
		mImageAdapter = new ImageAdapter(mContext,null) ;
		List<FeedBack> list = new ArrayList<>() ;
		for(int i = 0 ;i < mString.length ;i++){
			FeedBack bean = new FeedBack();
			bean.setFeedBack(mString[i]);
			list.add(bean) ;
		}

		mFeedBackApdater = new FeedAdapter(mContext,list) ;

		GridLayoutManager linearLayoutManager = new GridLayoutManager(this,3);
		mRVFeedBack.setLayoutManager(linearLayoutManager);

		mRVFeedBack.setAdapter(mFeedBackApdater);

		mTvMainInfo.setText("用户反馈");

		GridLayoutManager linearLayoutManager1 = new GridLayoutManager(this,4);
		mRvAddPicture.setLayoutManager(linearLayoutManager1);
		mRvAddPicture.setAdapter(mImageAdapter);

		mImageAdapter.setmImageAdapterListener(new ImageAdapter.ImageAdapterLister() {
			@Override
			public void imageAddListener() {


				PictureSelector.create(ReleaseActivity.this)
						.openGallery(PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
						.theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
						.maxSelectNum(4)// 最大图片选择数量
						.minSelectNum(1)// 最小选择数量
						.imageSpanCount(4)// 每行显示个数
						.selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
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
						.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

			}
		});
	}

	private void initView() {
		mRvAddPicture = findViewById(R.id.rv_add_pic) ;
		mIvAddPicture = findViewById(R.id.iv_add_pic) ;
		mRVFeedBack = findViewById(R.id.rl_feekback) ;
		mLLCommit = findViewById(R.id.ll_commit) ;
		mLLBack = findViewById(R.id.ll_back) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mLLBack.setVisibility(View.VISIBLE);
		mLLBack.setOnClickListener(this);
		mLLCommit.setOnClickListener(this);
		mIvAddPicture.setOnClickListener(this);
	}

	private void initMeidaList(List<MediaInfo> infoList){
		mImageAdapter.notifyImageListChange(infoList);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_feek_back);
 		initView() ;
		initData() ;
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

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			/*if(mPhotoList != null && mPhotoList.size() > 0){
				mIvAddPicture.setVisibility(View.GONE);
				mRvAddPicture.setVisibility(View.VISIBLE);
			}else{
				mIvAddPicture.setVisibility(View.VISIBLE);
				mRvAddPicture.setVisibility(View.GONE);
			}

			List<MediaInfo> mediaInfos = new ArrayList<>() ;

			for(int i = 0 ;i < mPhotoList.size() ;i++){
				MediaInfo mMediaOrg = new MediaInfo();
				mMediaOrg.setType(0);
				mMediaOrg.setFilePath(mPhotoList.get(0).getPhotoPath());
				mediaInfos.add(mMediaOrg) ;
			}

			initMeidaList(mediaInfos);*/
		}
	} ;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_back :
				finish();
				break ;
			case R.id.ll_commit :
				break ;
			case R.id.iv_add_pic :
				/*BJCommonImageCropHelper.openImageMulti(FeedBackActivity.this, mPhotoList,4,
						new ThemeConfig.Builder().setMainElementsColor(Color.parseColor("#00ccff")).setTitlebarRightButtonText(R.string.complete).build(), new BJCommonImageCropHelper.OnHandlerResultCallback(){

							@Override
							public void onHandlerSuccess(List<PhotoInfo> resultList) {
								mPhotoList = resultList ;
								mHandler.sendEmptyMessage(0) ;
							}

							@Override
							public void onHandlerFailure(String errorMsg) {

							}

						});*/
				break ;
		}
	}

}
