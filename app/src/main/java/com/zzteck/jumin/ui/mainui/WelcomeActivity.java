package com.zzteck.jumin.ui.mainui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.fragment.HistoryFragment;
import com.zzteck.jumin.fragment.HomeFragment;
import com.zzteck.jumin.fragment.UserFragment;
import com.zzteck.jumin.fragment.WJConversationListFragment;
import com.zzteck.jumin.ui.business.ReleaseActivity;
import com.zzteck.jumin.ui.usercenter.LoginActivity;
import com.zzteck.zzview.WJViewPaper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class WelcomeActivity extends BaseActivity implements OnClickListener{


	 private void initView(){
		 
	 }

	 private Handler mHandler = new Handler(){
		 @Override
		 public void handleMessage(Message msg) {
			 super.handleMessage(msg);

			 if(UserDAO.getInstance(mContext).isExistRecordLogin()){
				 Intent intent = new Intent(WelcomeActivity.this,MainActivity.class) ;
				 startActivity(intent);
				 finish();
			 }else{
				 Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class) ;
				 startActivity(intent);
				 finish();
			 }


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
