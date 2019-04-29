package com.zzteck.jumin.ui.business;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.icechn.videorecorder.ui.RecordingActivity2;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.ExternalInfo;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.DeviceUtil;
import com.zzteck.jumin.utils.FileUtils;
import com.zzteck.jumin.utils.UtilsTools;

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

public class ReleaseActivity extends BaseActivity implements View.OnClickListener {
	
	private Context mContext ;

	private TextView mTvMainInfo ;

	private RelativeLayout mRlBack;

	private LinearLayout mLLAddVideo ;

	private LinearLayout mLLDaymic ;

	private ImageView mIvVideoThumb ;




	private void initView(){

		mIvVideoThumb= findViewById(R.id.iv_video_thumb) ;
		mLLDaymic = findViewById(R.id.ll_daymic) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mLLAddVideo = findViewById(R.id.ll_add_video) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mRlBack.setVisibility(View.VISIBLE);
		mLLAddVideo.setOnClickListener(this);

	}

	private HashMap mHashExtra = new HashMap() ;

	private void getExternelInfo(String catId,String id){

		if(TextUtils.isEmpty(mCatId) || TextUtils.isEmpty(mId)){
			return ;
		}

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Info.Typeoptions") ;
		map.put("catid",catId) ;
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
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						String message = new String(responseStr.getBytes()) ;
						Gson gson = new Gson() ;
						ExternalInfo bean = gson.fromJson(message,ExternalInfo.class) ;
						daymicLayout(bean);
					}
				});
			}
		});
	}

	private String mCatId ;

	private String mId ;

	private void initData(){
		mTvMainInfo.setText("发布");
		mCatId = getIntent().getStringExtra("catId") ;
		mId = getIntent().getStringExtra("subCatId") ;
	}

	/**
	 * 动态添加线性布局
	 */
	private void addLinearLayout() {
		//initMissionList：存储几条测试数据
		for (int i = 0; i < 4 ; i++) {
			//LinearLayout默认是水平(0)居中，现在改为垂直居中
			mLLDaymic.setOrientation(LinearLayout.VERTICAL);
			//实例化一个LinearLayout
			LinearLayout linearLayout = new LinearLayout(this);
			//设置LinearLayout属性(宽和高)
			/*LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
			//设置边距
			layoutParams.setMargins(54, 0, 84, 0);
			//将以上的属性赋给LinearLayout
			linearLayout.setLayoutParams(layoutParams);
			//实例化一个TextView
			TextView tv = new TextView(this);
			//设置宽高以及权重
			LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
			//设置textview垂直居中
			tvParams.gravity = Gravity.CENTER_VERTICAL;
			tv.setLayoutParams(tvParams);
			tv.setTextSize(14);
			tv.setTextColor(getResources().getColor(R.color.blue));
			tv.setText("fafasfdsafs");

			RadioGroup radioGroup = new RadioGroup(this);
			radioGroup.setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 120));
			radioGroup.setOrientation(0);

			RadioGroup.LayoutParams rbtnParams = new RadioGroup.LayoutParams(72, 72);
			rbtnParams.gravity=Gravity.CENTER_VERTICAL;

			RadioGroup.LayoutParams rbtnParamsf = new RadioGroup.LayoutParams(72, 72);
			rbtnParamsf.gravity=Gravity.CENTER_VERTICAL;
			rbtnParamsf.leftMargin=84;
			rbtnParamsf.rightMargin=90;

			final RadioButton radioButtonF = new RadioButton(this);
			radioButtonF.setLayoutParams(rbtnParamsf);
			radioButtonF.setButtonDrawable(android.R.color.transparent);
			radioButtonF.setBackground(getResources().getDrawable(R.drawable.ic_tip));

			final RadioButton radioButtonT = new RadioButton(this);
			radioButtonT.setLayoutParams(rbtnParams);
			radioButtonT.setButtonDrawable(android.R.color.transparent);
			radioButtonT.setBackground(getResources().getDrawable(R.drawable.ic_tip));

			radioGroup.addView(radioButtonF);
			radioGroup.addView(radioButtonT);



			TextView tv1 = new TextView(this);
			tv1.setText("fdasf"+i);
			tv1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));

			TextView tv2 = new TextView(this);
			tv2.setText("tv222222fdasf"+i);
			tv2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));*/




			LinearLayout layoutLeft = new LinearLayout(this); // 线性布局方式
			layoutLeft.setOrientation(LinearLayout.HORIZONTAL); //
			layoutLeft.setBackgroundColor(mContext.getResources().getColor(R.color.colorRed));
			layoutLeft.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));


			LinearLayout layoutRight = new LinearLayout(this); // 线性布局方式
			layoutRight.setOrientation(LinearLayout.HORIZONTAL); //
			layoutRight.setBackgroundColor(mContext.getResources().getColor(R.color.colorGreen));
			layoutRight.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));


			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(R.drawable.ic_loading_rotate);
			LinearLayout.LayoutParams PARA = new LinearLayout.LayoutParams(50, 50);//
			imageView.setLayoutParams(PARA);
			layoutRight.addView(imageView);

			//添加一个TextView，设置成layout_width:wrap_content;layout_height:wrap_content;
			TextView tv33 = new TextView(this); // 普通聊天对话
			tv33.setText("我和猫猫是新添加的");
			tv33.setBackgroundColor(Color.GRAY);
			LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			tv33.setLayoutParams(LP_WW);
			layoutLeft.addView(tv33);



			linearLayout.addView(layoutLeft);
			linearLayout.addView(layoutRight);

			/*linearLayout.addView(tv);
			linearLayout.addView(radioGroup);*/

			mLLDaymic.addView(linearLayout);
		}

	}

	private void daymicLayout(ExternalInfo info){

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
			linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
			linearLayoutRight.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.3f));

			if(info.getData().get(i).getType().equals("radio")){

				RadioGroup radioGroup = new RadioGroup(this);
				radioGroup.setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
				radioGroup.setOrientation(LinearLayout.HORIZONTAL);

				String choices = info.getData().get(i).getExtra().getChoices();
				String[] arrayChoices = choices.split("\\r\\n") ;

				for(int j = 0 ; j< arrayChoices.length ; j++){
					final RadioButton radioButton = new RadioButton(this) ;
					int index = arrayChoices[j].indexOf("=") ;
					radioButton.setText(arrayChoices[j].substring(index+1,arrayChoices[j].length()));
					radioButton.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT)) ;
					radioGroup.addView(radioButton);
				}

				linearLayoutRight.addView(radioGroup);


			}else if(info.getData().get(i).getType().equals("number")){

				EditText tvPrice = new EditText(this);
				tvPrice.setHint("请输入价格");
				tvPrice.setLayoutParams(new LinearLayout.LayoutParams(200,ViewGroup.LayoutParams.WRAP_CONTENT,0.8f));

				TextView tvUnit = new TextView(this);
				tvUnit.setText("元");
				tvUnit.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

				linearLayoutRight.addView(tvPrice);
				linearLayoutRight.addView(tvUnit);

			}else if(info.getData().get(i).getType().equals("select")){


				MaterialSpinner spinner = new MaterialSpinner(mContext) ;
				spinner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release);
		mContext = this ;
		initView() ;
		initData();
		getExternelInfo(mCatId,mId);
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
			case R.id.ll_back:
				finish();
				break ;
			case R.id.ll_add_video :
				intent = new Intent(mContext, RecordingActivity2.class);
				startActivityForResult(intent,1122);
				break ;
		}
	}
}
