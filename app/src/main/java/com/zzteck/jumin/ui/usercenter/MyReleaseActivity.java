package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zzteck.jumin.R;
import com.zzteck.jumin.fragment.ReleaseCompleteFragment;
import com.zzteck.jumin.fragment.ReleaseTuiguangFragment;
import com.zzteck.jumin.fragment.ReleaseVideoFragment;
import com.zzteck.jumin.fragment.ReleaseWaitingFragment;
import com.zzteck.jumin.ui.mainui.BaseActivity;

import java.util.ArrayList;


public class MyReleaseActivity extends BaseActivity implements OnClickListener{


	private String[] mTitles = new String [4];

	private ArrayList<Fragment> mFragments = new ArrayList<>();

	private SlidingTabLayout slidingTab;

	private ViewPager segVp;

	private RelativeLayout mRlTitle ;

	private TextView mTvMainInfo ;

	private void initView() {

		mTitles[0] = "已完成";
		mFragments.add(new ReleaseCompleteFragment());

		mTitles[1] = "待上线";
		mFragments.add(new ReleaseWaitingFragment());

		mTitles[2] = "视频";
		mFragments.add(new ReleaseVideoFragment());

		mTitles[3] = "推广中";
		mFragments.add(new ReleaseTuiguangFragment());

		slidingTab = findViewById(R.id.slidingTab);
		segVp = findViewById(R.id.wj_paper);
		mRlTitle = findViewById(R.id.rl_include_title) ;
		mRlTitle.setBackgroundColor(mContext.getResources().getColor(R.color.white));
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mTvMainInfo.setText("全部发布");
		mTvMainInfo.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
		initSegTab();
	}


	private void initSegTab() {
		segVp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		slidingTab.setViewPager(segVp);
		slidingTab.setOnTabSelectListener(new OnTabSelectListener() {

			@Override
			public void onTabSelect(int position) {
				segVp.setCurrentItem(position);
			}

			@Override
			public void onTabReselect(int position) {

			}

		});
		segVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				slidingTab.setCurrentTab(position);

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		segVp.setCurrentItem(0);
	}

	private class MyPagerAdapter extends FragmentStatePagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTitles[position];
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_release);
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
