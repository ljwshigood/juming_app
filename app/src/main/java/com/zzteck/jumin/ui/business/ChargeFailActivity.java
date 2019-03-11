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

public class ChargeFailActivity extends BaseActivity implements View.OnClickListener {
	
	private Context mContext ;

	private TextView mTvMainInfo ;

	private ImageView mIvBack ;

	private RelativeLayout mLLBack ;

	private LinearLayout mLLCharge ;

	private void initView(){
		mLLCharge = findViewById(R.id.ll_charge_fail) ;
		mLLCharge.setOnClickListener(this);
		mLLBack = findViewById(R.id.ll_back) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mIvBack = findViewById(R.id.iv_back) ;
		mIvBack.setOnClickListener(this);
		mLLBack.setVisibility(View.VISIBLE);
		mLLBack.setOnClickListener(this);
	}
	
	private void initData(){
		mTvMainInfo.setText("充值失败");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge_fail);
		initView() ;
		initData();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.ll_back:
				finish();
				break ;
			case R.id.ll_charge_fail :

				break ;
		}
	}
}
