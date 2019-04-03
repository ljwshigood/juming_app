package com.zzteck.jumin.ui.mainui;

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
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.fragment.MainCategoryFragment;
import com.zzteck.jumin.fragment.HomeFragment;
import com.zzteck.jumin.fragment.UserFragment;
import com.zzteck.jumin.fragment.WJConversationListFragment;
import com.zzteck.jumin.ui.business.ReleaseCategoryActivity;
import com.zzteck.jumin.ui.usercenter.LoginActivity;
import com.zzteck.zzview.WJViewPaper;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.functions.Consumer;


public class MainActivity extends BaseActivity implements OnClickListener{

	 private Fragment mHomeFragmemt;
	 
	 private Fragment mCategoryFragment;
	 
	 private Fragment mReleaseFragment ;

	 private Fragment mMessageFragment ;
	 
	 private Fragment mUserFragment ;
	 
	 private WJViewPaper mWJViewPaper ;
	 
	 private ImageView mIvHome ;
	 
	 private ImageView mIvHistory;
	 
	 private ImageView mIvRelease ;
	 
	 private ImageView mIvMessage ;
	 
	 private ImageView mIvUser ;
	 
	 private TextView mTvHome ;
	 
	 private TextView mTvHistory;
	 
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
		 mRlNear = findViewById(R.id.rl_history) ;
		 mRlRelease = findViewById(R.id.ll_realease) ;
		 mRlMessage = findViewById(R.id.rl_message) ;
		 mRlUser = findViewById(R.id.rl_user) ;
		 
		 mRlHome.setOnClickListener(this);
		 mRlNear.setOnClickListener(this);
		 mRlRelease.setOnClickListener(this);
		 mRlMessage.setOnClickListener(this);
		 mRlUser.setOnClickListener(this);
		 
		 
		 mIvHome = findViewById(R.id.iv_home) ;
		 mIvHistory = findViewById(R.id.iv_history) ;
		 mIvRelease = findViewById(R.id.iv_release) ;
		 mIvMessage = findViewById(R.id.iv_message) ;
		 mIvUser = findViewById(R.id.iv_user) ;
		 
		 mTvHome = findViewById(R.id.tv_home) ;
		 mTvHistory = findViewById(R.id.tv_history) ;
		 mTvRelease = findViewById(R.id.tv_release) ;
		 mTvMessage = findViewById(R.id.tv_message) ;
		 mTvUser = findViewById(R.id.tv_user) ;
		 
		 mWJViewPaper = findViewById(R.id.content) ;
		 mHomeFragmemt = new HomeFragment();
		 mCategoryFragment = new MainCategoryFragment();
		 mMessageFragment = new WJConversationListFragment();
		 mUserFragment = new UserFragment();
		 
		 mFragment.add(mHomeFragmemt) ;
		 mFragment.add(mCategoryFragment) ;
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

		mWJViewPaper.setScrollble(true);
		mWJViewPaper.setAdapter ( fragmentPagerAdapter );
		mWJViewPaper.setOffscreenPageLimit ( 4 );

		mWJViewPaper.setCurrentItem ( 0 ) ;


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
				}
			}
		});

		setStyleCustom() ;

	}


	/**
	 * 设置通知栏样式 - 定义通知栏Layout
	 */
	private void setStyleCustom() {
		CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(this, R.layout.customer_notitfication_layout, R.id.icon, R.id.title, R.id.text);
		builder.layoutIconDrawable = R.mipmap.ic_launcher;
		builder.developerArg0 = "developerArg2";
		JPushInterface.setPushNotificationBuilder(2, builder);
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
		case R.id.ll_realease:
			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));

			if(UserDAO.getInstance(mContext).isExistRecordLogin()){
				Intent intent = new Intent(mContext, ReleaseCategoryActivity.class) ;
				startActivity(intent);
			}else{
				Intent intent = new Intent(mContext, LoginActivity.class) ;
				startActivity(intent);
			}

			break;
		case R.id.rl_home:
			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));

			mIvHome.setImageResource(R.mipmap.icon_shouye_pre);
			mIvHistory.setImageResource(R.mipmap.icon_lishi_nor);
			mIvUser.setImageResource(R.mipmap.icon_wode_nor);
			mIvMessage.setImageResource(R.mipmap.icon_xiaoxi_nor);


			mWJViewPaper.setCurrentItem(0);
			break ;
		case R.id.rl_history:
			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));


			mIvHome.setImageResource(R.mipmap.icon_shouye_nor);
			mIvHistory.setImageResource(R.mipmap.icon_lishi_pre);
			mIvUser.setImageResource(R.mipmap.icon_wode_nor);
			mIvMessage.setImageResource(R.mipmap.icon_xiaoxi_nor);

			mWJViewPaper.setCurrentItem(1);
			break ;
		case R.id.rl_user:
			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mWJViewPaper.setCurrentItem(3);

			mIvHome.setImageResource(R.mipmap.icon_shouye_nor);
			mIvHistory.setImageResource(R.mipmap.icon_lishi_nor);
			mIvUser.setImageResource(R.mipmap.icon_wode_pre);
			mIvMessage.setImageResource(R.mipmap.icon_xiaoxi_nor);

			((UserFragment)mUserFragment).initData() ;

			break ;
		case R.id.rl_message :
			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));

			mIvHome.setImageResource(R.mipmap.icon_shouye_nor);
			mIvHistory.setImageResource(R.mipmap.icon_lishi_nor);
			mIvUser.setImageResource(R.mipmap.icon_wode_nor);
			mIvMessage.setImageResource(R.mipmap.icon_xiaoxi_pre);


			mWJViewPaper.setCurrentItem(2);
			break ;

		default:
			break;
		}
	}
}
