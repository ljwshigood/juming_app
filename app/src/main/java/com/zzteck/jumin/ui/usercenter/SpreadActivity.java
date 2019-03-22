package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.zzview.WJViewPaper;


public class SpreadActivity extends BaseActivity implements OnClickListener{

	private String TAG = "liujw" ;

	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private TextView mTvNickName ;

	private LinearLayout mLLTuiguangTaoc ;

	private LinearLayout mLLBillRecord ;

	private WJViewPaper mViewPaper ;

	private void initView() {

		mViewPaper = findViewById(R.id.content) ;
		mLLTuiguangTaoc  = findViewById(R.id.ll_tuiguang_taoc) ;
		mLLBillRecord = findViewById(R.id.ll_bill_record) ;
		mTvNickName = findViewById(R.id.tv_nick_name) ;
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("我要推广");
		mRlBack.setVisibility(View.VISIBLE);

		mLLTuiguangTaoc.setOnClickListener(this);
		mLLBillRecord.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spread);

		mContext = SpreadActivity.this ;
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
			case R.id.ll_tuiguang_taoc :

				break ;
			case R.id.ll_bill_record :

				break ;
		}
	}
}
