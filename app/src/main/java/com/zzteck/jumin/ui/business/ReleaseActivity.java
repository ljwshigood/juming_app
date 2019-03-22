package com.zzteck.jumin.ui.business;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.mainui.BaseActivity;

public class ReleaseActivity extends BaseActivity implements View.OnClickListener {
	
	private Context mContext ;

	private TextView mTvMainInfo ;

	private RelativeLayout mRlBack;
	
	private void initView(){
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mRlBack.setVisibility(View.VISIBLE);
	}
	
	private void initData(){
		mTvMainInfo.setText("发布");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release);
		mContext = this ;
		initView() ;
		initData();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.ll_back:
				finish();
				break ;
		}
	}
}
