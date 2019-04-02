package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MemeberAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.UpgradeMemeberBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.view.NormalDecoration;

import java.util.ArrayList;
import java.util.List;


public class MemberUpgradeDetailActivity extends BaseActivity implements OnClickListener{



	private void initView() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_memeber_upgrade_detail);

		mContext = MemberUpgradeDetailActivity.this ;
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
