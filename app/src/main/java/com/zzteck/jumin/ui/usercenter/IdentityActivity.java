package com.zzteck.jumin.ui.usercenter;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class IdentityActivity extends BaseActivity implements OnClickListener{


	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private LinearLayout mLLPersion ;

	private LinearLayout mLLOrg ;

	private LinearLayout mLLComplete ;

	private EditText mEtName ;

	private EditText mEtNumber ;

	private void initView() {
		mEtName = findViewById(R.id.et_name) ;
		mEtNumber = findViewById(R.id.et_number) ;
		mLLComplete = findViewById(R.id.ll_complete) ;
		mLLOrg = findViewById(R.id.ll_org) ;
		mLLPersion  = findViewById(R.id.ll_persion_identity) ;
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("我的认证");
		mRlBack.setVisibility(View.VISIBLE);
		mLLPersion.setOnClickListener(this);
		mLLOrg.setOnClickListener(this);
		mLLComplete.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_identity);

		mContext = IdentityActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;
	}

	private void identity(String cname,String idc){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Member.Personcer") ;
		map.put("cname",cname) ;
		map.put("idc",idc) ;

		map.put("sign",UtilsTools.getSign(mContext,"App.Member.Personcer")) ;

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().get().url(Constants.HOST+"?"+ UtilsTools.getMapToString(map)).build();
		Call call = client.newCall(request);
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

						/*LoginBean bean = gson.fromJson(message,LoginBean.class) ;
						if(bean.getData().isIs_login()){

							UserDAO.getInstance(mContext).editorUserTable(bean.getData());

							Intent intent = new Intent(mContext,MainActivity.class) ;
							startActivity(intent);
							finish();
						}*/

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
		Intent intent = null ;
		switch (v.getId()){
			case R.id.ll_back :
				finish();
				break ;
			case R.id.ll_persion_identity :
				intent = new Intent(mContext,PersionIdentityActivity.class) ;
				startActivity(intent);
				break ;
			case R.id.ll_org :
				intent = new Intent(mContext,OrgIdentityActivity.class) ;
				startActivity(intent);
				break ;
			case R.id.ll_complete :
				if(TextUtils.isEmpty(mEtName.getText().toString().trim())){
					return ;
				}else if(TextUtils.isEmpty(mEtNumber.getText().toString().trim())){
					return ;
				}else {
					identity(mEtName.getText().toString().trim(),mEtNumber.getText().toString().trim());
				}
				break ;
		}

	}
}
