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
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.ui.mainui.BaseActivity;


public class RegisterActivity extends BaseActivity implements OnClickListener{

	private TextView mTvMainInfo ;

	private ImageView mIvBack ;

	private void initView() {
		mIvBack = findViewById(R.id.iv_back) ;
		mTvMainInfo =  findViewById(R.id.tv_main_info);
		mIvBack.setOnClickListener(this);
		mTvMainInfo.setText("注册");
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
			case R.id.iv_back :
				finish();
				break ;
		}
	}

	@Override
	public void onBackPressed() {

	}
}
