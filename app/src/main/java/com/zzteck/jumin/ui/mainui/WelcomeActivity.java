package com.zzteck.jumin.ui.mainui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.service.LocationService;


public class WelcomeActivity extends BaseActivity implements OnClickListener{


	 private void initView(){
		 
	 }

	 private Handler mHandler = new Handler(){
		 @Override
		 public void handleMessage(Message msg) {
			 super.handleMessage(msg);

			 Intent intent = new Intent(WelcomeActivity.this,MainActivity.class) ;
			 startActivity(intent);
			 finish();

			/* if(UserDAO.getInstance(mContext).isExistRecordLogin()){
				 Intent intent = new Intent(WelcomeActivity.this,MainActivity.class) ;
				 startActivity(intent);
				 finish();
			 }else{

			 }*/


		 }
	 } ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		mContext = WelcomeActivity.this ;
 		initView() ;
		App.getInstance().addActivity(this);
		mHandler.sendEmptyMessageDelayed(0,2000) ;

		Intent intent = new Intent(this, LocationService.class) ;
		startService(intent) ;
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
	}
}
