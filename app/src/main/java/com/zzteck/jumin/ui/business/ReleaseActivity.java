package com.zzteck.jumin.ui.business;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;
import com.icechn.videorecorder.ui.RecordingActivity2;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.CheckAdapter;
import com.zzteck.jumin.adapter.QoneAdapter;
import com.zzteck.jumin.adapter.StreamAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.AreaInfo;
import com.zzteck.jumin.bean.CheckInfo;
import com.zzteck.jumin.bean.ExternalInfo;
import com.zzteck.jumin.bean.LinkCat;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.ModifyBean;
import com.zzteck.jumin.bean.QoneInfo;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.pop.LinkCatAdapter;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.ui.usercenter.ModifyUserInfoActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.DeviceUtil;
import com.zzteck.jumin.utils.FileUtils;
import com.zzteck.jumin.utils.GlideCircleTransform;
import com.zzteck.jumin.utils.ScreenUtil;
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

	private void initView(){

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

	private void initData(){
		mTvMainInfo.setText("发布");
		mCatId = getIntent().getStringExtra("catId") ;
		mSubId = getIntent().getStringExtra("subCatId") ;
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

			}
		});

		mQoneAdpater.setmIOnItemClick(new QoneAdapter.IOnCityItemLister() {
			@Override
			public void onItemCityClick(QoneInfo.DataBean bean) {
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

						String message = new String(responseStr.getBytes()) ;
						Gson gson = new Gson() ;
						AreaInfo bean = gson.fromJson(message,AreaInfo.class) ;
						mStreamAdapter.notifyVideoListChange(bean.getData());
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
				tvUnit.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

				linearLayoutRight.addView(etPrice);
				linearLayoutRight.addView(tvUnit);


			}else if(info.getData().get(i).getType().equals("select")){

				linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
				MaterialSpinner spinner = new MaterialSpinner(mContext) ;
				spinner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

				String[] attray = info.getData().get(i).getExtra().getChoices().split("\\r\\n") ;

				List<String> spinnerList = new ArrayList<>() ;

				for(int j = 0 ;j < attray.length ;j++){
					int index = attray[j].indexOf("=") ;
					String temp = attray[j].substring(index+1,attray[j].length());
					spinnerList.add(temp);
				}

				spinner.setItems(spinnerList);
				spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
					@Override
					public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
						//Logger.e(item);
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

				TextView tvText = new TextView(this);
				tvText.setHint("请选择"+info.getData().get(i).getTitle());
				tvText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

				linearLayoutRight.addView(tvText) ;

                final String link = info.getData().get(i).getExtra().getParentid()  ;
				final String title = info.getData().get(i).getTitle() ;
				tvText.setOnClickListener(new View.OnClickListener() {
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

                        String message = new String(responseStr.getBytes()) ;
                        Gson gson = new Gson() ;
                        LinkCat bean = gson.fromJson(message,LinkCat.class) ;
						mLinkCatadapter.setDatas(bean.getData(), new IndexableAdapter.IndexCallback<LinkCat.DataBean>() {
							@Override
							public void onFinished(List<EntityWrapper<LinkCat.DataBean>> datas) {
								// 数据处理完成后回调

							}
						});
                    }
                });
            }
        });
    }

    private OkHttpClient client ;

	private String mCityId ;

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
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(data == null){
			return ;
		}

		String filePath = data.getStringExtra("filepath") ;

		mIvVideoThumb.setImageBitmap(FileUtils.getVideoThumb(filePath));
	}

	@Override
	public void onClick(View view) {
		Intent intent = null ;
		switch (view.getId()){
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




				break ;
		}
	}


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
						//	Log.e("progress ", percentage + "");
						} else {
						//	Log.e("No progress ", 0 + "");
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
						final ModifyBean bean = gson.fromJson(responseStr,ModifyBean.class) ;
						if(bean.getData().isIs_success() == true ){

						}

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
							//	Log.e("progress ", percentage + "");
						} else {
							//	Log.e("No progress ", 0 + "");
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
						final ModifyBean bean = gson.fromJson(responseStr,ModifyBean.class) ;
						if(bean.getData().isIs_success() == true ){

						}

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
