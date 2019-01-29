package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.zzview.WindowsToast;


public class LoginActivity extends BaseActivity implements OnClickListener{


	private EditText mEtPwd ;

	private EditText mEtUserName ;

	private TextView mTvRegister ;

	private TextView mTvForgetPwd ;

	private LinearLayout mLLLogin ;

	private ImageView mIvBack ;


	private void initView() {

		mLLLogin = findViewById(R.id.ll_login) ;
		mIvBack = findViewById(R.id.iv_back) ;
		mEtPwd = findViewById(R.id.et_user_pwd) ;
		mEtUserName = findViewById(R.id.et_user_name) ;
		mTvForgetPwd = findViewById(R.id.tv_forget_pwd) ;
		mTvRegister = findViewById(R.id.tv_register) ;

		mTvRegister.setOnClickListener(this);
		mTvForgetPwd.setOnClickListener(this);
		mLLLogin.setOnClickListener(this);
		mIvBack.setOnClickListener(this);
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
				if(!TextUtils.isEmpty(mEtUserName.getText().toString()) && !TextUtils.isEmpty(mEtPwd.getText().toString())){
					intent = new Intent(mContext,MainActivity.class) ;
					startActivity(intent);
					finish();
				}else{
					WindowsToast.makeText(mContext,"用户名或密码为空").show();
				}
				break ;
			case R.id.iv_back :
				finish();
				break ;
		}
	}
}
