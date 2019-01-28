package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.ui.mainui.BaseActivity;


public class ForgetPwdActivity extends BaseActivity implements OnClickListener{

	private String TAG = "liujw" ;

	private void initView() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_forget_pwd);

		mContext = ForgetPwdActivity.this ;
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

		}
	}

	@Override
	public void onBackPressed() {
	}
}
