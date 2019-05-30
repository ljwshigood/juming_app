package com.zzteck.jumin.ui.business;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;
import com.icechn.videorecorder.ui.RecordingActivity2;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.CheckAdapter;
import com.zzteck.jumin.adapter.FullyGridLayoutManager;
import com.zzteck.jumin.adapter.GridImageAdapter;
import com.zzteck.jumin.adapter.ImageAdapter;
import com.zzteck.jumin.adapter.QoneAdapter;
import com.zzteck.jumin.adapter.StreamAdapter;
import com.zzteck.jumin.bean.AreaInfo;
import com.zzteck.jumin.bean.CheckInfo;
import com.zzteck.jumin.bean.ExternalInfo;
import com.zzteck.jumin.bean.ImageInfo;
import com.zzteck.jumin.bean.LinkCat;
import com.zzteck.jumin.bean.MediaInfo;
import com.zzteck.jumin.bean.QoneInfo;
import com.zzteck.jumin.bean.ReleaseRet;
import com.zzteck.jumin.bean.VideoInfo;
import com.zzteck.jumin.pop.LinkCatAdapter;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.DeviceUtil;
import com.zzteck.jumin.utils.FileUtils;
import com.zzteck.jumin.utils.SharePerfenceUtil;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.webmanager.CountingRequestBody;
import com.zzteck.jumin.webmanager.RequestBuilder;
import com.zzteck.zzview.WindowsToast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.yokeyword.indexablerv.EntityWrapper;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReleaseActivity extends BaseActivity implements View.OnClickListener {
	
	private Context mContext ;

	private TextView mTvMainInfo ;

	private RelativeLayout mRlBack;

	private LinearLayout mLLAddVideo ;

	private LinearLayout mLLDaymic ;

	private ImageView mIvVideoThumb ;

	private LinearLayout mLLComplete ;

	private TextView mTvSelectQone ;

	private RecyclerView mRlStream ;

	private EditText mEtTitle ;

	private EditText mEtDes ;

	private TextView mTvCat ;

	private EditText mEtContact ;

	private EditText mEtMobile ;

	private String mQone ;

	private EditText mEtWeiXin ;

	private EditText mEtQQ ;

	private RecyclerView mRvPic ;

	private GridImageAdapter mGridAdapter;


	private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
		@Override
		public void onAddPicClick() {

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
					.enableCrop(false)// 是否裁剪
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

	};

	private void initView(){
		mRvPic = findViewById(R.id.rv_pic) ;
		mIvAddPicture = findViewById(R.id.iv_add_pic) ;
		mEtWeiXin = findViewById(R.id.et_weixin) ;
		mEtQQ = findViewById(R.id.et_qq) ;
		mEtContact = findViewById(R.id.et_cantact) ;
		mEtMobile = findViewById(R.id.et_mobile) ;
		mTvCat = findViewById(R.id.tv_cat) ;
		mEtTitle = findViewById(R.id.et_title) ;
		mEtDes = findViewById(R.id.et_des) ;
		mTvSelectQone = findViewById(R.id.tv_select_qone) ;
		mLLComplete = findViewById(R.id.ll_complete) ;
		mIvVideoThumb= findViewById(R.id.iv_video_thumb) ;
		mLLDaymic = findViewById(R.id.ll_daymic) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mLLAddVideo = findViewById(R.id.ll_add_video) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mRlBack.setVisibility(View.VISIBLE);
		mLLAddVideo.setOnClickListener(this);
		mLLComplete.setOnClickListener(this);
		mTvSelectQone.setOnClickListener(this);
		mIvAddPicture.setOnClickListener(this);

		mImageAdapter = new ImageAdapter(mContext,null) ;


		FullyGridLayoutManager manager = new FullyGridLayoutManager(ReleaseActivity.this, 4, GridLayoutManager.VERTICAL, false);
		mRvPic.setLayoutManager(manager);
		mGridAdapter = new GridImageAdapter(ReleaseActivity.this, onAddPicClickListener);
		mGridAdapter.setList(selectList);
		mGridAdapter.setSelectMax(6);
		mRvPic.setAdapter(mGridAdapter);

	}

	private HashMap mHashExtra = new HashMap() ;

	private void getExternelInfo(String catId,String id){

		if(TextUtils.isEmpty(mCatId) || TextUtils.isEmpty(mSubId)){
			return ;
		}

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Info.Typeoptions") ;
		map.put("catid",mSubId) ;
		map.put("id",id) ;

		map.put("sign", UtilsTools.getSign(mContext,"App.Info.Typeoptions")) ;

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

				Log.e("liujw","##########################getExternelInfo responseStr : "+responseStr);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						try{
							String message = new String(responseStr.getBytes()) ;
							Gson gson = new Gson() ;
							mExternalInfo = gson.fromJson(message,ExternalInfo.class) ;
							daymicLayout(mExternalInfo);
						}catch (Exception e){
							e.printStackTrace();
						}
					}
				});
			}
		});
	}

	private ExternalInfo mExternalInfo ;

	private String mCatId ;

	private String mSubId;

	private String mCategoryName ;

	private void initData(){
		mTvMainInfo.setText("发布");
		mCatId = getIntent().getStringExtra("catId") ;
		mSubId = getIntent().getStringExtra("subCatId") ;
        mCategoryName = getIntent().getStringExtra("category") ;
	}


	public enum Location {

		LEFT,
		RIGHT,
		TOP,
		BOTTOM;

	}

	private PopupWindow popupWindow ;

	private int from = 0 ;

	private List<LinkCat.DataBean> initPopData(){

		return null ;
	}

	private List<LinkCat.DataBean> mDatas = new ArrayList<>() ;

	private LinkCatAdapter mLinkCatadapter ;

	private QoneAdapter mQoneAdpater ;

	private StreamAdapter mStreamAdapter ;

	private CheckAdapter mCheckAdapter ;

	TextView ptv  ;
	ImageView piv ;


	protected void initCheckPopupWindow(List<CheckInfo> list , final String info , boolean isShowBack){

		if(popupWindow != null){
			popupWindow.dismiss();
			popupWindow = null ;
		}

		View popupWindowView = getLayoutInflater().inflate(R.layout.right_qone_pop_memu, null);
		popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
		popupWindow.setAnimationStyle(R.style.AnimationRightFade);

		ColorDrawable dw = new ColorDrawable(0xffffffff);
		popupWindow.setBackgroundDrawable(dw);

		ptv = popupWindowView.findViewById(R.id.tv_info) ;
		piv = popupWindowView.findViewById(R.id.iv_back) ;
		ptv.setText(info);

		if(isShowBack){
			piv.setVisibility(View.VISIBLE);
		}else{
			piv.setVisibility(View.INVISIBLE);
		}

		piv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				piv.setVisibility(View.INVISIBLE);
				ptv.setText(info);

			}
		});

		popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_release, null), Gravity.RIGHT, 0, 500);

		backgroundAlpha(0.5f);
		popupWindow.setOnDismissListener(new popupDismissListener());


		mRlArea = popupWindowView.findViewById(R.id.rv_area);
		mRlArea.setLayoutManager(new LinearLayoutManager(this));
		mCheckAdapter = new CheckAdapter(this,list);
		mRlArea.setAdapter(mCheckAdapter);


		mRlStream = popupWindowView.findViewById(R.id.rv_stram) ;
		mStreamAdapter = new StreamAdapter(mContext,null) ;
		mRlStream.setLayoutManager(new LinearLayoutManager(this));
		mRlStream.setAdapter(mStreamAdapter);

		mRlArea.setVisibility(View.VISIBLE);
		mRlStream.setVisibility(View.GONE);


		mCheckAdapter.setmIOnItemClick(new CheckAdapter.IOnCityItemLister() {
			@Override
			public void onItemCityClick(QoneInfo.DataBean bean) {

			}
		});

		popupWindowView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
	}


	private String selectLink = "" ;

	protected void initPopupWindow(final String info , final String link, boolean isShowBack){

		if(popupWindow != null){
			popupWindow.dismiss();
			popupWindow = null ;
		}

		View popupWindowView = getLayoutInflater().inflate(R.layout.right_pop_memu, null);
		popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
		popupWindow.setAnimationStyle(R.style.AnimationRightFade);

		ColorDrawable dw = new ColorDrawable(0xffffffff);
		popupWindow.setBackgroundDrawable(dw);

		ptv = popupWindowView.findViewById(R.id.tv_info) ;
		piv = popupWindowView.findViewById(R.id.iv_back) ;
		ptv.setText(info);

		if(isShowBack){
			piv.setVisibility(View.VISIBLE);
		}else{
			piv.setVisibility(View.INVISIBLE);
		}

		piv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				piv.setVisibility(View.INVISIBLE);
				ptv.setText(info);
				linkcat(link);
			}
		});

		popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_release, null), Gravity.RIGHT, 0, 500);

		backgroundAlpha(0.5f);
		popupWindow.setOnDismissListener(new popupDismissListener());

        IndexableLayout indexableLayout = popupWindowView.findViewById(R.id.indexableLayout);
		indexableLayout.setLayoutManager(new LinearLayoutManager(this));

		indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);

		mLinkCatadapter = new LinkCatAdapter(this);
		indexableLayout.setAdapter(mLinkCatadapter);

		mLinkCatadapter.setDatas(mDatas, new IndexableAdapter.IndexCallback<LinkCat.DataBean>() {
			@Override
			public void onFinished(List<EntityWrapper<LinkCat.DataBean>> datas) {
				// 数据处理完成后回调
			}
		});

		linkcat(link+"");

		// set Center OverlayView
		indexableLayout.setOverlayStyle_Center();

		// set Listener
		mLinkCatadapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<LinkCat.DataBean>() {
			@Override
			public void onItemClick(View v, int originalPosition, int currentPosition, LinkCat.DataBean entity) {
				if (originalPosition >= 0) {
					piv.setVisibility(View.VISIBLE);
					ptv.setText(entity.getCatname());

					mSelectLinkList.add(entity) ;
					//mHashExtra.put()

					linkcat(entity.getCatid());

					//WindowsToast.makeText(ReleaseActivity.this, "选中:" + entity.getCatname() + "  当前位置:" + currentPosition + "  原始所在数组位置:" + originalPosition).show();
				} else {
					//WindowsToast.makeText(ReleaseActivity.this, "选中Header:" + entity.getCatname() + "  当前位置:" + currentPosition);
				}
			}
		});

		mLinkCatadapter.setOnItemTitleClickListener(new IndexableAdapter.OnItemTitleClickListener() {
			@Override
			public void onItemClick(View v, int currentPosition, String indexTitle) {
				//WindowsToast.makeText(ReleaseActivity.this, "选中:" + indexTitle + "  当前位置:" + currentPosition);
			}
		});


		popupWindowView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				/*if( popupWindow!=null && popupWindow.isShowing()){
					popupWindow.dismiss();
					popupWindow=null;
				}*/

				return false;
			}
		});
	}

	private RecyclerView mRlArea ;

	private String mAreaId = "" ;

	private String mStreetId ="";

	private String  mQoneString = "";

	protected void initQonePopupWindow(final String info , boolean isShowBack){

		if(popupWindow != null){
			popupWindow.dismiss();
			popupWindow = null ;
		}

		View popupWindowView = getLayoutInflater().inflate(R.layout.right_qone_pop_memu, null);
		popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
		popupWindow.setAnimationStyle(R.style.AnimationRightFade);

		ColorDrawable dw = new ColorDrawable(0xffffffff);
		popupWindow.setBackgroundDrawable(dw);

		ptv = popupWindowView.findViewById(R.id.tv_info) ;
		piv = popupWindowView.findViewById(R.id.iv_back) ;
		ptv.setText(info);

		if(isShowBack){
			piv.setVisibility(View.VISIBLE);
		}else{
			piv.setVisibility(View.INVISIBLE);
		}

		piv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				piv.setVisibility(View.INVISIBLE);
				ptv.setText(info);

				mRlArea.setVisibility(View.VISIBLE) ;
				mRlStream.setVisibility(View.GONE) ;
			}
		});

		popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_release, null), Gravity.RIGHT, 0, 500);

		backgroundAlpha(0.5f);
		popupWindow.setOnDismissListener(new popupDismissListener());


		mRlArea = popupWindowView.findViewById(R.id.rv_area);
		mRlArea.setLayoutManager(new LinearLayoutManager(this));
		mQoneAdpater = new QoneAdapter(this,null);
		mRlArea.setAdapter(mQoneAdpater);


		mRlStream = popupWindowView.findViewById(R.id.rv_stram) ;
		mStreamAdapter = new StreamAdapter(mContext,null) ;
		mRlStream.setLayoutManager(new LinearLayoutManager(this));
		mRlStream.setAdapter(mStreamAdapter);

		mRlArea.setVisibility(View.VISIBLE);
		mRlStream.setVisibility(View.GONE);

		mStreamAdapter.setmIOnItemClick(new StreamAdapter.IOnCityItemLister() {
			@Override
			public void onItemCityClick(AreaInfo.DataBean bean) {
				mStreetId = bean.getStreetid() ;
				mQoneString += bean.getStreetname() ;
				popupWindow.dismiss();

				mTvSelectQone.setText(mQoneString);
			}
		});

		mQoneAdpater.setmIOnItemClick(new QoneAdapter.IOnCityItemLister() {
			@Override
			public void onItemCityClick(QoneInfo.DataBean bean) {
				mAreaId = bean.getAreaid() ;
				mQoneString += bean.getAreaname() ;
				AppStreamArea(bean.getAreaid()) ;
			}
		});

		popupWindowView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});

		AppCityArea(mCityId) ;

	}

	private void AppStreamArea(String areaid){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.City.Street") ;
		map.put("areaid",areaid) ;

		map.put("sign",UtilsTools.getSign(mContext,"App.City.Street")) ;

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

				Log.e("liujw","##########################linkcat : "+responseStr);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						mRlArea.setVisibility(View.GONE);
						mRlStream.setVisibility(View.VISIBLE);
						piv.setVisibility(View.VISIBLE);

						try{
							String message = new String(responseStr.getBytes()) ;
							Gson gson = new Gson() ;
							AreaInfo bean = gson.fromJson(message,AreaInfo.class) ;
							mStreamAdapter.notifyVideoListChange(bean.getData());
						}catch (Exception e){
							e.printStackTrace();
						}
					}
				});
			}
		});


	}

	private void AppCityArea(String cityid){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.City.Area") ;
		map.put("cityid",cityid) ;

		map.put("sign",UtilsTools.getSign(mContext,"App.City.Area")) ;

		OkHttpClient client = new OkHttpClient();
		//构造Request对象
		//采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
		Request request = new Request.Builder().get().url(Constants.HOST+"?"+ UtilsTools.getMapToString(map)).build();
		Call call = client.newCall(request);
		//异步调用并设置回调函数
		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				Log.e("liujw","##########################IOException : "+e.toString());
			}

			@Override
			public void onResponse(Call call, final Response response) throws IOException {
				final String responseStr = response.body().string();

				Log.e("liujw","##########################linkcat : "+responseStr);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						String message = new String(responseStr.getBytes()) ;
						Gson gson = new Gson() ;
						QoneInfo bean = gson.fromJson(message,QoneInfo.class) ;
						mQoneAdpater.notifyVideoListChange(bean.getData());

					}
				});
			}
		});


	}


	class popupDismissListener implements PopupWindow.OnDismissListener{

		@Override
		public void onDismiss() {
			backgroundAlpha(1f);
		}

	}


	private HashMap<String,Object> mHashMapViews = new HashMap<>() ;

	/**
	 *
	 */

	public void backgroundAlpha(float bgAlpha){
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; //0.0-1.0
		getWindow().setAttributes(lp);
	}

	private int mRadioButtinId = 0 ;


	private String mLinkName = "" ;

	private TextView mTvLink ;


	private void daymicLayout(final ExternalInfo info){

		if(info == null || info.getData() == null){
			return ;
		}

		mLLDaymic.removeAllViews();
		mLLDaymic.setOrientation(LinearLayout.VERTICAL);

		for(int i = 0 ; i< info.getData().size() ;i++){


			LinearLayout linearLayoutLeft  = new LinearLayout(mContext) ;
			linearLayoutLeft.setLayoutParams(new ViewGroup.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
			linearLayoutLeft.setOrientation(LinearLayout.HORIZONTAL);
			linearLayoutLeft.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.7f));

			TextView tvTitle = new TextView(this);
			tvTitle.setGravity(Gravity.CENTER);
			tvTitle.setTextSize(16f);
			tvTitle.setText(info.getData().get(i).getTitle());
			linearLayoutLeft.addView(tvTitle);

			LinearLayout linearLayoutRight  = new LinearLayout(mContext) ;
			linearLayoutRight.setLayoutParams(new ViewGroup.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
			linearLayoutRight.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.3f));

			if(info.getData().get(i).getType().equals("radio")){

				linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
				RadioGroup radioGroup = new RadioGroup(this);
				radioGroup.setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
				radioGroup.setOrientation(LinearLayout.HORIZONTAL);

				final int positon  = i ;

				String choices = info.getData().get(i).getExtra().getChoices();
				final String[] arrayChoices = choices.split("\\r\\n") ;

				for(int j = 0 ; j< arrayChoices.length ; j++){
					final RadioButton radioButton = new RadioButton(this) ;
					radioButton.setId(mRadioButtinId++) ;
					int index = arrayChoices[j].indexOf("=") ;
					radioButton.setText(arrayChoices[j].substring(index+1,arrayChoices[j].length())) ;
					radioButton.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT)) ;
					radioGroup.addView(radioButton) ;
					final String[] selectText = arrayChoices[j].split("=") ;
					radioButton.setTag(selectText[0]);
				}

				radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						int id = group.getCheckedRadioButtonId();
						RadioButton choise = findViewById(id);
						String selectText = (String) choise.getTag();
						mHashExtra.put(info.getData().get(positon).getIdentifier(),selectText) ;
					}
				});

				linearLayoutRight.addView(radioGroup);


			}else if(info.getData().get(i).getType().equals("number")){

				linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
				EditText etPrice = new EditText(this);
				etPrice.setHint("请输入"+info.getData().get(i).getTitle());
				etPrice.setBackground(null);
				etPrice.setLayoutParams(new LinearLayout.LayoutParams(200,ViewGroup.LayoutParams.WRAP_CONTENT,0.8f));


				mHashMapViews.put(info.getData().get(i).getIdentifier(),etPrice) ;

				TextView tvUnit = new TextView(this);
				tvUnit.setText(info.getData().get(i).getExtra().getUnits());
				tvUnit.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
				tvUnit.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

				linearLayoutRight.addView(etPrice);
				linearLayoutRight.addView(tvUnit);


			}else if(info.getData().get(i).getType().equals("select")){

				linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
				final MaterialSpinner spinner = new MaterialSpinner(mContext) ;
				spinner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

				final String[] attray = info.getData().get(i).getExtra().getChoices().split("\\r\\n") ;

				List<String> spinnerList = new ArrayList<>() ;

				for(int j = 0 ;j < attray.length ;j++){
					int index = attray[j].indexOf("=") ;
					String temp = attray[j].substring(index+1,attray[j].length());
					spinnerList.add(temp);
				}

				spinner.setItems(spinnerList);
				spinner.setTag(info.getData().get(i).getIdentifier());
				spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
					@Override
					public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
						String temp = attray[position] ;
						String[] splites = temp.split("=") ;
						String identifier = (String) spinner.getTag();
						mHashExtra.put(identifier,splites[0]) ;

						Log.e("liujw","########setOnItemSelectedListener mHashExtra : "+mHashExtra.toString()) ;
					}
				});

				linearLayoutRight.addView(spinner);
			}else if(info.getData().get(i).getType().equals("text")){
				linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
				EditText tvText = new EditText(this);
				tvText.setHint("请输入"+info.getData().get(i).getTitle());
				tvText.setSingleLine();
				tvText.setBackground(null);
				tvText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

				mHashMapViews.put(info.getData().get(i).getIdentifier(),tvText) ;

				linearLayoutRight.addView(tvText) ;


			}else if(info.getData().get(i).getType().equals("textarea")){
				linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
				EditText tvText = new EditText(this);
				tvText.setHint("请输入"+info.getData().get(i).getTitle());
				tvText.setBackground(null);
				tvText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

				mHashMapViews.put(info.getData().get(i).getIdentifier(),tvText) ;

				linearLayoutRight.addView(tvText) ;



			}else if(info.getData().get(i).getType().equals("checkbox")){


				linearLayoutRight.setOrientation(LinearLayout.VERTICAL);

				String choices = info.getData().get(i).getExtra().getChoices();
				final String[] arrayChoices = choices.split("\\r\\n") ;

				TextView tvText = new TextView(this);
				tvText.setHint("请选择"+info.getData().get(i).getTitle());
				tvText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

				linearLayoutRight.addView(tvText) ;
				final String title = info.getData().get(i).getTitle() ;
				tvText.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {

						List<CheckInfo> list = new ArrayList<>() ;

						for(int k = 0 ;k < arrayChoices.length ;k++){
							CheckInfo bean = new CheckInfo() ;
							String[] text = arrayChoices[k].split("=") ;
							bean.setInfo(text[1]);
							bean.setId(text[0]) ;
							list.add(bean) ;
						}

						initCheckPopupWindow(list,title,false) ;
					}
				});

				/*linearLayoutRight.setOrientation(LinearLayout.VERTICAL);

			*//*	LinearLayout llContainer = new LinearLayout(this);
				llContainer.setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
				llContainer.setOrientation(LinearLayout.VERTICAL);

				String choices = info.getData().get(i).getExtra().getChoices();
				String[] arrayChoices = choices.split("\\r\\n") ;

				LinearLayout groupLl = null ;

				for(int j = 0 ; j< arrayChoices.length ; j++){

					final CheckBox checkButton = new CheckBox(this) ;
					int index = arrayChoices[j].indexOf("=") ;
					checkButton.setText(arrayChoices[j].substring(index+1,arrayChoices[j].length()));
					checkButton.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT)) ;

					checkButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {

						}
					});

					if(j % 3 == 0){
						groupLl = new LinearLayout(this);
						groupLl.setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
						groupLl.setOrientation(LinearLayout.HORIZONTAL);
						llContainer.addView(groupLl);
					}
					groupLl.addView(checkButton);
				}*//*

				linearLayoutRight.addView(llContainer);*/

			}else if(info.getData().get(i).getType().equals("link")){

				linearLayoutRight.setOrientation(LinearLayout.VERTICAL);
                mLinkName = info.getData().get(i).getIdentifier() ;
				mTvLink = new TextView(this);
				mTvLink.setHint("请选择"+info.getData().get(i).getTitle());
				mTvLink.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

				mTvLink.setSingleLine(true);

				linearLayoutRight.addView(mTvLink) ;

                final String link = info.getData().get(i).getExtra().getParentid()  ;
				final String title = info.getData().get(i).getTitle() ;
				mTvLink.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						initPopupWindow(title,link,false) ;
					}
				});

			}

			LinearLayout ll = new LinearLayout(this);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, DeviceUtil.dip2px(mContext,50)) ;

			ll.setLayoutParams(params);
			ll.setOrientation(LinearLayout.HORIZONTAL);

			ll.addView(linearLayoutLeft);
			ll.addView(linearLayoutRight);

			ll.setGravity(Gravity.CENTER_VERTICAL);

			mLLDaymic.addView(ll);
		}
	}

	private List<LinkCat.DataBean> mSelectLinkList = new ArrayList<>() ;

	private void linkcat(String catid){

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Category.Linkcat") ;
        map.put("catid",catid) ;

        map.put("sign",UtilsTools.getSign(mContext,"App.Category.Linkcat")) ;

        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url(Constants.HOST+"?"+ UtilsTools.getMapToString(map)).build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("liujw","##########################IOException : "+e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();

				Log.e("liujw","##########################linkcat : "+responseStr);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try{
                            String message = new String(responseStr.getBytes()) ;
                            Gson gson = new Gson() ;
                            LinkCat bean = gson.fromJson(message,LinkCat.class) ;
                            mLinkCatadapter.setDatas(bean.getData(), new IndexableAdapter.IndexCallback<LinkCat.DataBean>() {
                                @Override
                                public void onFinished(List<EntityWrapper<LinkCat.DataBean>> datas) {
                                    // 数据处理完成后回调
                                }
                            });
                        }catch (Exception e){

                        	if(mTvLink != null && mSelectLinkList != null){
								String links = "" ;
                        		for(int i = 0 ;i < mSelectLinkList.size() ;i++){
									links += mSelectLinkList.get(i).getCatname()+" / " ;
								}

								links = links.substring(0,links.lastIndexOf("/")) ;

								mTvLink.setText(links);
							}

							if(popupWindow != null){
								popupWindow.dismiss();
								popupWindow = null ;
							}
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    private OkHttpClient client ;

	private String mCityId ;

	private ImageView mIvAddPicture ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release);
		mContext = this ;
		client = new OkHttpClient() ;
		mCityId = (String) SharePerfenceUtil.getParam(mContext,"city_id","");
		initView() ;
		initData();
		getExternelInfo(mCatId, mSubId);
		mTvCat.setText(mCategoryName);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(data == null){
			return ;
		}

		if(requestCode == 1122){
			String filePath = data.getStringExtra("filepath") ;
			mIvVideoThumb.setImageBitmap(FileUtils.getVideoThumb(filePath));

			try {
				uploadVideo(new File(filePath),Constants.HOST);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(requestCode == PictureConfig.CHOOSE_REQUEST && data != null){
			// 图片选择结果回调
			selectList = PictureSelector.obtainMultipleResult(data);

			// 例如 LocalMedia 里面返回三种path
			// 1.media.getPath(); 为原图path
			// 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
			// 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
			// 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的


			/*if (selectList != null && selectList.size() > 0) {
				mIvAddPicture.setVisibility(View.GONE);
			} else {
				mIvAddPicture.setVisibility(View.VISIBLE);
			}*/


			mHandler.sendEmptyMessage(2) ;

			/*for (LocalMedia media : selectList) {

				//mPhotoList = resultList ;


				//Log.i("图片-----》", media.getPath());
			}*/
		}

	}

	private List<MediaInfo> mPictureList = new ArrayList<>() ;

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if(msg.what == 2) {

				mGridAdapter.setList(selectList);
				mGridAdapter.notifyDataSetChanged();

				mPictureList = new ArrayList<>();

				for (int i = 0; i < selectList.size(); i++) {
					MediaInfo mMediaOrg = new MediaInfo();
					mMediaOrg.setType(0);
					mMediaOrg.setFilePath(selectList.get(i).getCompressPath());
					mPictureList.add(mMediaOrg);
				}

				for(int i = 0 ;i < mPictureList.size() ;i++){
					MediaInfo info = mPictureList.get(i) ;
					try {
						uploadImage(new File(info.getFilePath()),Constants.HOST);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
	} ;

	private ImageAdapter mImageAdapter ;

	private List<LocalMedia> selectList = new ArrayList<>();

	@Override
	public void onClick(View view) {
		Intent intent = null ;
		switch (view.getId()){
			case R.id.iv_add_pic :

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

				break ;
			case R.id.tv_select_qone :

				initQonePopupWindow("选择城市",false);

				break ;
			case R.id.ll_back:
				finish();
				break ;
			case R.id.ll_add_video :
				intent = new Intent(mContext, RecordingActivity2.class);
				startActivityForResult(intent,1122);
				break ;
			case R.id.ll_complete :

				if(mHashMapViews.size() > 0){
					Iterator<Map.Entry<String, Object>> iterator = mHashMapViews.entrySet().iterator();
					boolean isPass = true ;
					if(mExternalInfo != null){
						while (iterator.hasNext()) {
							Map.Entry<String, Object> entry = iterator.next();

							for(int i = 0 ;i < mExternalInfo.getData().size();i++){
								if(entry.getKey().equals(mExternalInfo.getData().get(i).getIdentifier())){
									if(mExternalInfo.getData().get(i).getType().equals("number")){
										EditText et = (EditText) entry.getValue();
										if(TextUtils.isEmpty(et.getText().toString().trim())){
											WindowsToast.makeText(mContext,mExternalInfo.getData().get(i).getTitle()+"不能为空").show();
											isPass = false ;
											break  ;
										}else{
											mHashExtra.put(mExternalInfo.getData().get(i).getIdentifier(),et.getText().toString().trim()) ;
										}

									}else if(mExternalInfo.getData().get(i).getType().equals("text")){
										EditText et = (EditText) entry.getValue();
										if(TextUtils.isEmpty(et.getText().toString().trim())){
											WindowsToast.makeText(mContext,mExternalInfo.getData().get(i).getTitle()+"不能为空").show();
											isPass = false ;
											break  ;
										}else{
											mHashExtra.put(mExternalInfo.getData().get(i).getIdentifier(),et.getText().toString().trim()) ;
										}
									}else if(mExternalInfo.getData().get(i).getType().equals("texterea")){
										EditText et = (EditText) entry.getValue();
										if(TextUtils.isEmpty(et.getText().toString().trim())){
											WindowsToast.makeText(mContext,mExternalInfo.getData().get(i).getTitle()+"不能为空").show();
											isPass = false ;
											break  ;
										}else{
											mHashExtra.put(mExternalInfo.getData().get(i).getIdentifier(),et.getText().toString().trim()) ;
										}
									}
								}

							}

						}
					}

					if(!isPass){
						return ;
					}
				}

				if(TextUtils.isEmpty(mEtTitle.getText().toString().trim())){
					WindowsToast.makeText(mContext,"标题不能为空").show();
					return ;
				}else if(TextUtils.isEmpty(mEtDes.getText().toString().trim())){
					WindowsToast.makeText(mContext,"描述不能为空").show();
					return ;
				}else if(TextUtils.isEmpty(mEtContact.getText().toString().trim())){
					WindowsToast.makeText(mContext,"联系人不能为空").show();
					return ;
				}else if(TextUtils.isEmpty(mEtMobile.getText().toString().trim())){
					WindowsToast.makeText(mContext,"手机号不能为空").show();
					return ;
				}else if(TextUtils.isEmpty(mEtWeiXin.getText().toString().trim())){
					WindowsToast.makeText(mContext,"微信号不能为空").show();
					return ;
				}else if(TextUtils.isEmpty(mEtQQ.getText().toString().trim())){
					WindowsToast.makeText(mContext,"QQ号不能为空").show();
					return ;
				}
                if(!TextUtils.isEmpty(mLinkName)){
                	for(int i = 0 ;i< mSelectLinkList.size() ;i++){
						mSelectLinkList.get(i).setType("link");
					}
                    mHashExtra.put(mLinkName,mSelectLinkList) ;
                }

				String extra = "";
				if(mHashExtra.size() > 0){
					Gson gson = new Gson() ;
					extra =  gson.toJson(mHashExtra);
				}

				if(TextUtils.isEmpty(mImageUrl)){
					WindowsToast.makeText(mContext,"请上传图片").show();
					return ;
				}

				if(TextUtils.isEmpty(mVideUrl)){
					WindowsToast.makeText(mContext,"请上传视频").show();
					return ;
				}

				String imgs = mImageUrl.substring(0,mImageUrl.lastIndexOf(","));

				SYSDiaLogUtils.showProgressDialog(this, SYSDiaLogUtils.SYSDiaLogType.IosType, "加载中...", false, new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {

					}
				});


				AppInfoAdd(mSubId,mEtTitle.getText().toString().trim(),mAreaId,mStreetId,
						mEtDes.getText().toString().trim(),"","","",mTvSelectQone.getText().toString().trim(),
						extra,mVideUrl,imgs,mEtContact.getText().toString().trim(),mEtMobile.getText().toString().trim(),
						mEtQQ.getText().toString().trim(),mEtWeiXin.getText().toString().trim()) ;

				break ;
		}
	}

	private String mImageUrl = "";

	private String mVideUrl= "" ;

	private void uploadImage(final File file,final String url) throws Exception {

		new AsyncTask<Integer, Integer, String>() {

			@Override
			protected String doInBackground(Integer... params) {

				MultipartBody body = RequestBuilder.uploadRequestBody3(ReleaseActivity.this, file);

				CountingRequestBody monitoredRequest = new CountingRequestBody(body, new CountingRequestBody.Listener() {
					@Override
					public void onRequestProgress(long bytesWritten, long contentLength) {
						float percentage = 100f * bytesWritten / contentLength;
						if (percentage >= 0) {
							publishProgress(Math.round(percentage));
						} else {
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
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Log.e("liujw","####################responseStr upload File : "+responseStr);
								Gson gson = new Gson() ;
								ImageInfo bean = gson.fromJson(responseStr,ImageInfo.class) ;
								mImageUrl += bean.getData().getImg() +",";

								for(int i = 0 ;i < selectList.size() ;i++){
									if(selectList.get(i).getCompressPath().equals(file.getAbsolutePath())){
										selectList.get(i).setStatus(1);
									}
								}

								mGridAdapter.setList(selectList);
								mGridAdapter.notifyDataSetChanged() ;
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


	private void uploadVideo(final File file,final String url) throws Exception {

		new AsyncTask<Integer, Integer, String>() {

			@Override
			protected String doInBackground(Integer... params) {

				MultipartBody body = RequestBuilder.uploadRequestBody4(ReleaseActivity.this, file);

				CountingRequestBody monitoredRequest = new CountingRequestBody(body, new CountingRequestBody.Listener() {
					@Override
					public void onRequestProgress(long bytesWritten, long contentLength) {
						float percentage = 100f * bytesWritten / contentLength;
						if (percentage >= 0) {
							publishProgress(Math.round(percentage));
						} else {
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
						Log.e("liujw","####################uploadVideo responseStr ; "+responseStr);
						Gson gson = new Gson() ;
						VideoInfo bean = gson.fromJson(responseStr,VideoInfo.class) ;
						mVideUrl = bean.getData().getVideo() ;
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

	public void AppInfoAdd(String catid,String title,String areaid,String streetid,String content,String lat,String lng,
						   String mappoint,String webAddress,String extra,String video,String img,String conatctWho,String tel,
						   String qq,String wexin){
		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Info.Add") ;
		map.put("catid",catid) ;
		map.put("title",title) ;
		map.put("areaid",areaid) ;
		map.put("streetid",streetid) ;
		map.put("content",content) ;
		map.put("lat",lat) ;
		map.put("lng",lng) ;
		map.put("mappoint",mappoint) ;
		map.put("web_address",webAddress) ;
		map.put("extra",extra) ;
		map.put("video",video) ;
		map.put("img",img) ;
		map.put("contact_who",conatctWho) ;
		map.put("tel",tel) ;
		map.put("qq",qq) ;
		map.put("weixin",wexin) ;
		map.put("sign",UtilsTools.getSign(mContext,"App.Info.Add")) ;

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

						Log.e("liujw","############################message AppInfoAdd: "+message);
						Gson gson = new Gson() ;
						ReleaseRet bean = gson.fromJson(message,ReleaseRet.class) ;
						if(Integer.valueOf(bean.getData().getId()) > 0 ){
							WindowsToast.makeText(mContext,bean.getData().getMsg()).show();
							SYSDiaLogUtils.dismissProgress();
							finish();
						}
					}
				});
			}
		});

	}


}
