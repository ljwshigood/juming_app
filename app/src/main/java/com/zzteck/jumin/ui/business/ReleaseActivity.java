package com.zzteck.jumin.ui.business;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.icechn.videorecorder.ui.RecordingActivity2;
import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.FileUtils;

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
	
	private void initData(){
		mTvMainInfo.setText("发布");
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

	private void daymicLayout(){

		mLLDaymic.removeAllViews();
		mLLDaymic.setOrientation(LinearLayout.VERTICAL);
		//根据商品数量,动态生成布局
		for(int i = 0 ; i< 4;i++){

			//生成三个TextView
			TextView tv1 = new TextView(this);
			TextView tv2 = new TextView(this);
			TextView tv3 = new TextView(this);
			//文字居中
			tv1.setGravity(Gravity.CENTER);
			tv2.setGravity(Gravity.CENTER);
			tv3.setGravity(Gravity.CENTER);
			tv1.setTextSize(16f);
			tv2.setTextSize(16f);
			tv3.setTextSize(16f);
			//设置权重
			tv1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
			tv2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
			tv3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
			//设置内容
			tv1.setText("aa:"+i);//项目
			tv2.setText("bb:"+i);
			tv3.setText("否");

			//每一行的属性
			LinearLayout ll = new LinearLayout(this);
			ll.setLayoutParams(new ViewGroup.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
			ll.setOrientation(LinearLayout.HORIZONTAL);


			ll.addView(tv1);
			ll.addView(tv2);
			ll.addView(tv3);

			mLLDaymic.addView(ll);
		}



		/*LinearLayout.LayoutParams lpLeftWeight = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams lpRightWeight = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


		LinearLayout layoutLeft = new LinearLayout(this); // 线性布局方式
		layoutLeft.setOrientation(LinearLayout.HORIZONTAL); //
		layoutLeft.setBackgroundColor(0xff00ffff);
		layoutLeft.setLayoutParams(lpLeftWeight);


		LinearLayout layoutRight = new LinearLayout(this); // 线性布局方式
		layoutRight.setOrientation(LinearLayout.HORIZONTAL); //
		layoutRight.setBackgroundColor(0xff00ffff);
		layoutRight.setLayoutParams(lpRightWeight);


		layoutLeft.addView(layoutRight);*/


		/*//添加一个ImageView,设置成layout_width:50;layout_height:50;
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundResource(R.drawable.ic_loading_rotate);
		LinearLayout.LayoutParams PARA = new LinearLayout.LayoutParams(50, 50);//
		imageView.setLayoutParams(PARA);
		layoutLeft.addView(imageView);

		//添加一个TextView，设置成layout_width:wrap_content;layout_height:wrap_content;
		TextView tv = new TextView(this); // 普通聊天对话
		tv.setText("我和猫猫是新添加的");
		tv.setBackgroundColor(Color.GRAY);
		LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		tv.setLayoutParams(LP_WW);
		layoutLeft.addView(tv);*/

		//将动态增加的布局添加到当前布局中；
		//mLLDaymic.addView(layoutLeft);
		//mLLDaymic.addView(layoutRight);


	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release);
		mContext = this ;
		initView() ;
		initData();
		daymicLayout();
		//addLinearLayout() ;
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
