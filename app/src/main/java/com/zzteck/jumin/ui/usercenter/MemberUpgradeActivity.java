package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MemeberAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.view.NormalDecoration;


public class MemberUpgradeActivity extends BaseActivity implements OnClickListener{

	private RecyclerView mRVMemeberUpgrade ;

	private MemeberAdapter mMemeberAdapter ;


	private void initView() {

		mRVMemeberUpgrade = findViewById(R.id.rv_memeber_upgrade) ;
		mRVMemeberUpgrade.setLayoutManager(new LinearLayoutManager(this));
		mRVMemeberUpgrade.addItemDecoration(new NormalDecoration(ContextCompat.getColor(this, R.color.mainGrayF8), (int) getResources().getDimension(R.dimen.one)));
		mMemeberAdapter = new MemeberAdapter(mContext,null) ;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_memeber_upgrade);

		mContext = MemberUpgradeActivity.this ;
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
		Intent intent = null ;
		switch (v.getId()) {
			case R.id.tv_forget_pwd :
				break ;
			case R.id.tv_register :
				break ;
			case R.id.ll_login :

				break ;
		}
	}

	@Override
	public void onBackPressed() {

	}
}
