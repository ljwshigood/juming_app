package com.zzteck.jumin.ui.business;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.mainui.BaseActivity;

public class ChargeActivity extends BaseActivity implements View.OnClickListener {
	
	private Context mContext ;

	private TextView mTvMainInfo ;

	private RelativeLayout mLLBack ;

	private LinearLayout mLLCharge ;

	private void initView(){
		mLLCharge = findViewById(R.id.ll_charge) ;
		mLLCharge.setOnClickListener(this);
		mLLBack = findViewById(R.id.ll_back) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mLLBack.setVisibility(View.VISIBLE);
		mLLBack.setOnClickListener(this);
	}
	
	private void initData(){
		mTvMainInfo.setText("充值");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge);
		initView() ;
		initData();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.ll_back:
				finish();
				break ;
			case R.id.ll_charge :
			    Intent intent = new Intent(this,ChargeFailActivity.class) ;
			    startActivity(intent);
				break ;
		}
	}
}
