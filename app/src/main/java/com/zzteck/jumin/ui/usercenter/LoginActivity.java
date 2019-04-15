package com.zzteck.jumin.ui.usercenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.zzview.WindowsToast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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

		mEtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

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

					/*
					*
					* */

					SYSDiaLogUtils.showProgressDialog(this, SYSDiaLogUtils.SYSDiaLogType.IosType, "加载中...", false, new DialogInterface.OnCancelListener() {
						@Override
						public void onCancel(DialogInterface dialog) {

						}
					});

					Map<String, String> map = new HashMap<>() ;
					map.put("s","App.Member.Login") ;
					map.put("username",mEtUserName.getText().toString().trim()) ;
					map.put("userpwd",mEtPwd.getText().toString().trim()) ;

					map.put("sign",UtilsTools.getSign(mContext,"App.Member.Login")) ;

					OkHttpClient client = new OkHttpClient();
					//构造Request对象
					//采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
					Request request = new Request.Builder().get().url(Constants.HOST+"?"+ UtilsTools.getMapToString(map)).build();
					Call call = client.newCall(request);
					//异步调用并设置回调函数
					call.enqueue(new Callback() {

						@Override
						public void onFailure(Call call, IOException e) {
							Log.e("liujw","##########################IOException : "+e.toString());
						}

						@Override
						public void onResponse(Call call, final Response response) throws IOException {
							final String responseStr = response.body().string();
							runOnUiThread(new Runnable() {
								@Override
								public void run() {

									String message = new String(responseStr.getBytes()) ;
									Gson gson = new Gson() ;
									LoginBean bean = gson.fromJson(message,LoginBean.class) ;

									SYSDiaLogUtils.dismissProgress();

									if(bean.getData().isIs_login()){

										UserDAO.getInstance(mContext).editorUserTable(bean.getData());

										Intent intent = new Intent(mContext,MainActivity.class) ;
										startActivity(intent);
										finish();
									}

								}
							});
						}
					});

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
