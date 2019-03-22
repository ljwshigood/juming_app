package com.zzteck.jumin.ui.mainui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.FeedAdapter;
import com.zzteck.jumin.bean.FeedBack;
import com.zzteck.jumin.ui.mainui.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class FeedBackActivity extends BaseActivity implements OnClickListener{


	private TextView mTvMainInfo ;

	private RelativeLayout mLLBack ;

	private String [] mString = new String[]{"反馈原因1","反馈原因1","反馈原因1","反馈原因1","反馈原因1","反馈原因1","反馈原因1","反馈原因1"} ;

	private LinearLayout mLLCommit ;

	private FeedAdapter mFeedBackApdater ;

	private RecyclerView mRVFeedBack ;

	private void initData(){
		List<FeedBack> list = new ArrayList<>() ;
		for(int i = 0 ;i < mString.length ;i++){
			FeedBack bean = new FeedBack();
			bean.setFeedBack(mString[i]);
			list.add(bean) ;
		}

		mFeedBackApdater = new FeedAdapter(mContext,list) ;

		GridLayoutManager linearLayoutManager = new GridLayoutManager(this,4);
		mRVFeedBack.setLayoutManager(linearLayoutManager);

		mRVFeedBack.setAdapter(mFeedBackApdater);

		mTvMainInfo.setText("用户反馈");
	}

	private void initView() {

		mRVFeedBack = findViewById(R.id.rl_feekback) ;
		mLLCommit = findViewById(R.id.ll_commit) ;
		mLLBack = findViewById(R.id.ll_back) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mLLBack.setVisibility(View.VISIBLE);
		mLLBack.setOnClickListener(this);
		mLLCommit.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_feek_back);
 		initView() ;
		initData() ;
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
			case R.id.ll_commit :
				break ;
		}
	}

}
