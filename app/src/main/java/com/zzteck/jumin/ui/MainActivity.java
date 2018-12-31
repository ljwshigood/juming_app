package com.zzteck.jumin.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
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
import com.zzteck.jumin.fragment.HomeFragment;
import com.zzteck.jumin.fragment.NearFragent;
import com.zzteck.jumin.fragment.ReleaseFragment;
import com.zzteck.jumin.fragment.UserFragment;
import com.zzteck.jumin.fragment.WJConversationListFragment;
import com.zzteck.zzview.WJViewPaper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class MainActivity extends BaseActivity implements OnClickListener{

	 private Fragment mHomeFragmemt;
	 
	 private Fragment mNearFragment ;
	 
	 private Fragment mReleaseFragment ;

	 private Fragment mMessageFragment ;
	 
	 private Fragment mUserFragment ;
	 
	 private WJViewPaper mWJViewPaper ;
	 
	 private ImageView mIvHome ;
	 
	 private ImageView mIvNear ;
	 
	 private ImageView mIvRelease ;
	 
	 private ImageView mIvMessage ;
	 
	 private ImageView mIvUser ;
	 
	 private TextView mTvHome ;
	 
	 private TextView mTvNear ;
	 
	 private TextView mTvRelease ;
	 
	 private TextView mTvMessage ;
	 
	 private TextView mTvUser ;
	 
	 private RelativeLayout mRlHome ;
	 
	 private RelativeLayout mRlNear ;
	 
	 private LinearLayout mRlRelease ;
	 
	 private RelativeLayout mRlMessage ;
	 
	 private RelativeLayout mRlUser ;
	 
	 private List<Fragment> mFragment = new ArrayList<>();
	 
	 private void initView(){
		 
		 mRlHome = findViewById(R.id.rl_home) ;
		 mRlNear = findViewById(R.id.rl_near) ;
		 mRlRelease = findViewById(R.id.ll_realease) ;
		 mRlMessage = findViewById(R.id.rl_message) ;
		 mRlUser = findViewById(R.id.rl_user) ;
		 
		 mRlHome.setOnClickListener(this);
		 mRlNear.setOnClickListener(this);
		 mRlRelease.setOnClickListener(this);
		 mRlMessage.setOnClickListener(this);
		 mRlUser.setOnClickListener(this);
		 
		 
		 mIvHome = findViewById(R.id.iv_home) ;
		 mIvNear = findViewById(R.id.iv_near) ;
		 mIvRelease = findViewById(R.id.iv_release) ;
		 mIvMessage = findViewById(R.id.iv_message) ;
		 mIvUser = findViewById(R.id.iv_user) ;
		 
		 mTvHome = findViewById(R.id.tv_home) ;
		 mTvNear = findViewById(R.id.tv_near) ;
		 mTvRelease = findViewById(R.id.tv_release) ;
		 mTvMessage = findViewById(R.id.tv_message) ;
		 mTvUser = findViewById(R.id.tv_user) ;
		 
		 mWJViewPaper = findViewById(R.id.content) ;
		 mHomeFragmemt = new HomeFragment();
		 mNearFragment = new NearFragent();
		 mReleaseFragment = new ReleaseFragment();
		 mMessageFragment = new WJConversationListFragment();
		 mUserFragment = new UserFragment();
		 
		 mFragment.add(mHomeFragmemt) ;
		 mFragment.add(mNearFragment) ;
		 mFragment.add(mMessageFragment) ;
		 mFragment.add(mUserFragment) ;

		  FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter ( getSupportFragmentManager () ) {
	            @Override
	            public Fragment getItem(int position) {
	                return mFragment.get ( position );
	            }

	            @Override
	            public int getCount() {
	                return mFragment.size ();
	            }
	        };
	        mWJViewPaper.setScrollble(false);
	        mWJViewPaper.setAdapter ( fragmentPagerAdapter );
	        mWJViewPaper.setOffscreenPageLimit ( 4 );

	        mWJViewPaper.setCurrentItem ( 0 );

	 }
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = MainActivity.this ;
 		initView() ;
		App.getInstance().addActivity(this);

		RxPermissions rxPermissions1 = new RxPermissions(this);

		rxPermissions1.requestEach(
				Manifest.permission.ACCESS_FINE_LOCATION,
				Manifest.permission.ACCESS_COARSE_LOCATION,
				Manifest.permission.CAMERA,
				Manifest.permission.RECORD_AUDIO,
				Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
			@Override
			public void accept(Permission permission) throws Exception {
				if (permission.granted) {

				}else{
					Toast.makeText(getApplicationContext(),"您没有授权该权限，请在设置中打开授权"+permission.name,Toast.LENGTH_LONG).show();
					//finish();
				}
			}
		});
		
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
		if(resultCode == 3322){
			((NearFragent)mNearFragment).refreshTask();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_realease:
		/*	mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvNear.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark_blue));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));*/
			break;
		case R.id.rl_home:
			/*mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark_blue));
			mTvNear.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));*/
			mWJViewPaper.setCurrentItem(0);
			break ;
		case R.id.rl_near:
			/*mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvNear.setTextColor(mContext.getResources().getColor(R.color.dark_blue));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));*/
			mWJViewPaper.setCurrentItem(1);
			break ;
		case R.id.rl_user:
			/*mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvNear.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark_blue));*/
			mWJViewPaper.setCurrentItem(3);
			break ;
		case R.id.rl_message :
			/*mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvNear.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark_blue));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));*/
			mWJViewPaper.setCurrentItem(2);
			break ;

		default:
			break;
		}
	}
}
