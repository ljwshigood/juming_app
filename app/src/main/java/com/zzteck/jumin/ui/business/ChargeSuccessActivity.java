package com.zzteck.jumin.ui.business;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.mainui.BaseActivity;

public class ChargeSuccessActivity extends BaseActivity implements View.OnClickListener {
	
	private Context mContext ;

	private TextView mTvMainInfo ;

	private ImageView mIvBack ;

	private RelativeLayout mLLBack ;

	private LinearLayout mLLChargeSuccess ;


	private void initView(){
		mLLChargeSuccess = findViewById(R.id.ll_charge_success) ;
		mLLBack = findViewById(R.id.ll_back) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mIvBack = findViewById(R.id.iv_back) ;
		mIvBack.setOnClickListener(this);
		mLLBack.setVisibility(View.VISIBLE);
		mLLBack.setOnClickListener(this);
		mLLChargeSuccess.setOnClickListener(this);
	}
	
	private void initData(){
		mTvMainInfo.setText("充值成功");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge_success);
		initView() ;
		initData();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.ll_back:
				finish();
				break ;
			case R.id.ll_charge_success :
				finish();
				break ;
		}
	}
}
