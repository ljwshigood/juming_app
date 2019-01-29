package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.ui.mainui.BaseActivity;


public class RegisterActivity extends BaseActivity implements OnClickListener{

	private TextView mTvMainInfo ;

	private RelativeLayout mLLBack ;

	private void initView() {
		mLLBack = findViewById(R.id.ll_back) ;
		mTvMainInfo =  findViewById(R.id.tv_main_info);
		mLLBack.setOnClickListener(this);
		mTvMainInfo.setText("注册");
		mLLBack.setVisibility(View.VISIBLE);
		mTvMainInfo.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mContext = RegisterActivity.this ;
		App.getInstance().addActivity(this);

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
			case R.id.ll_back :
				finish();
				break ;
		}
	}

}
