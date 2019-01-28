package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.ui.mainui.BaseActivity;


public class LoginActivity extends BaseActivity implements OnClickListener{


	private EditText mEtPwd ;

	private EditText mEtUserName ;

	private TextView mTvRegister ;

	private TextView mTvForgetPwd ;

	private LinearLayout mLLLogin ;


	private void initView() {

		mLLLogin = findViewById(R.id.ll_login) ;

		mEtPwd = findViewById(R.id.et_user_pwd) ;
		mEtUserName = findViewById(R.id.et_user_name) ;
		mTvForgetPwd = findViewById(R.id.tv_forget_pwd) ;
		mTvRegister = findViewById(R.id.tv_register) ;

		mTvRegister.setOnClickListener(this);
		mTvForgetPwd.setOnClickListener(this);
		mLLLogin.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		mContext = LoginActivity.this ;
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
				intent = new Intent(mContext,ForgetPwdActivity.class) ;
				startActivity(intent);
				break ;
			case R.id.tv_register :
				intent = new Intent(mContext,RegisterActivity.class) ;
				startActivity(intent);
				break ;
			case R.id.ll_login :

				break ;
		}
	}

	@Override
	public void onBackPressed() {

	}
}
