package com.zzteck.jumin.ui.mainui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baijiahulian.common.crop.BJCommonImageCropHelper;
import com.baijiahulian.common.crop.ThemeConfig;
import com.baijiahulian.common.crop.model.PhotoInfo;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.FeedAdapter;
import com.zzteck.jumin.adapter.ImageAdapter;
import com.zzteck.jumin.bean.FeedBack;
import com.zzteck.jumin.bean.MediaInfo;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.usercenter.PersionIdentityActivity;

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

	private List<PhotoInfo> mPhotoList = new ArrayList<>();

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


				BJCommonImageCropHelper.openImageMulti(FeedBackActivity.this, mPhotoList,4,
						new ThemeConfig.Builder().setMainElementsColor(Color.parseColor("#00ccff")).setTitlebarRightButtonText(R.string.complete).build(), new BJCommonImageCropHelper.OnHandlerResultCallback(){

							@Override
							public void onHandlerSuccess(List<PhotoInfo> resultList) {

								mPhotoList = resultList ;
								mHandler.sendEmptyMessage(0) ;
							}

							@Override
							public void onHandlerFailure(String errorMsg) {

							}

						});
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
			if(mPhotoList != null && mPhotoList.size() > 0){
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

			initMeidaList(mediaInfos);
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
				BJCommonImageCropHelper.openImageMulti(FeedBackActivity.this, mPhotoList,4,
						new ThemeConfig.Builder().setMainElementsColor(Color.parseColor("#00ccff")).setTitlebarRightButtonText(R.string.complete).build(), new BJCommonImageCropHelper.OnHandlerResultCallback(){

							@Override
							public void onHandlerSuccess(List<PhotoInfo> resultList) {
								mPhotoList = resultList ;
								mHandler.sendEmptyMessage(0) ;
							}

							@Override
							public void onHandlerFailure(String errorMsg) {

							}

						});
				break ;
		}
	}

}
