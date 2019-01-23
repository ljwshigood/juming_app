package com.zzteck.jumin.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.BaseActivity;

public class ReleaseActivity extends BaseActivity {
	
	private View mMainView ;
	
	private Context mContext ;

	private TextView mTvMainInfo ;
	
	private void initView(){
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
	}
	
	private void initData(){
		mTvMainInfo.setText("发布");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release);
		initView() ;
		initData();
	}

}
