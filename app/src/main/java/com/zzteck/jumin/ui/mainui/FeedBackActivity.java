package com.zzteck.jumin.ui.mainui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.mainui.BaseActivity;


public class FeedBackActivity extends BaseActivity implements OnClickListener{


	private TextView mTvMainInfo ;

	private ImageView mIvBack ;

	private RelativeLayout mLLBack ;

	private LinearLayout mLLCharge ;

	private void initData(){
		mTvMainInfo.setText("用户反馈");
	}

	private void initView() {
		mLLBack = findViewById(R.id.ll_back) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mIvBack = findViewById(R.id.iv_back) ;
		mIvBack.setOnClickListener(this);
		mLLBack.setVisibility(View.VISIBLE);
		mLLBack.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_feek_back);


 		initView() ;
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		}
	}

}
