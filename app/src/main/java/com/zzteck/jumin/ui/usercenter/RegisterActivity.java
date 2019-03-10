package com.zzteck.jumin.ui.usercenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.QiandaoBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.zzview.WindowsToast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RegisterActivity extends BaseActivity implements OnClickListener{

	private TextView mTvMainInfo ;

	private RelativeLayout mLLBack ;

	private TextView mTvGetCode ;

	private EditText mEtUserName ;

	private EditText mEtPwd ;

	private EditText mCode ;

	private LinearLayout mLlRegister  ;

	private EditText mEtCode ;

	private void initView() {
		mEtCode = findViewById(R.id.et_code) ;
		mLlRegister = findViewById(R.id.ll_login) ;
		mEtPwd = findViewById(R.id.et_user_pwd) ;
		mEtUserName = findViewById(R.id.et_user_name) ;
		mTvGetCode = findViewById(R.id.tv_get_code) ;
		mLLBack = findViewById(R.id.ll_back) ;
		mTvMainInfo =  findViewById(R.id.tv_main_info);
		mLLBack.setOnClickListener(this);
		mTvMainInfo.setText("注册");
		mLLBack.setVisibility(View.VISIBLE);
		mTvGetCode.setOnClickListener(this);
		mLlRegister.setOnClickListener(this);
		mTvMainInfo.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mContext = RegisterActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;
	}

	private void getCode(String mobile) {

		Map<String, String> map = new HashMap<>();
		map.put("s", "App.Member.Sendcode");
		map.put("mobile", mobile);

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Log.e("liujw", "##########################IOException : " + e.toString());
			}

			@Override
			public void onResponse(Call call, final Response response) throws IOException {
				final String responseStr = response.body().string();
				((Activity)mContext).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Gson gson = new Gson();
					//	QiandaoBean bean = gson.fromJson(responseStr, QiandaoBean.class);

					}
				});
			}
		});

	}

	private void register(String mobile,String userPwd,String code) {

		Map<String, String> map = new HashMap<>();
		map.put("s", "App.Member.Register");
		map.put("mobile", mobile);
		map.put("userpwd", userPwd + "");
		map.put("code", code + "");

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Log.e("liujw", "##########################IOException : " + e.toString());
			}

			@Override
			public void onResponse(Call call, final Response response) throws IOException {
				final String responseStr = response.body().string();
				((Activity)mContext).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Gson gson = new Gson();

						WindowsToast.makeText(mContext,"注册成功").show();
						App.getInstance().exit();
						Intent intent = new Intent(mContext, MainActivity.class) ;
						startActivity(intent);
						finish();

					}
				});
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
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_back :
				finish();
				break ;
			case R.id.ll_login :
				if(!TextUtils.isEmpty(mEtUserName.getText().toString().trim())){
					WindowsToast.makeText(mContext,"手机号不能为空").show();
				} else if (!TextUtils.isEmpty(mEtPwd.getText().toString().trim())) {
					WindowsToast.makeText(mContext,"密码不能为空").show();
				}else if(!TextUtils.isEmpty(mEtCode.getText().toString().trim())){
					WindowsToast.makeText(mContext,"验证码不能为空").show();
				}else {
					register(mEtUserName.getText().toString().trim(), mEtPwd.getText().toString().trim(), mEtCode.getText().toString().trim());
				}
				break ;
			case R.id.tv_get_code:
				if(!TextUtils.isEmpty(mEtUserName.getText().toString().trim())){
					getCode(mEtUserName.getText().toString().trim());
				}else{
					WindowsToast.makeText(mContext,"手机号不能为空").show();
				}
				break;
		}
	}

}
