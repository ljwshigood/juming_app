package com.zzteck.jumin.ui.business;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChargeActivity extends BaseActivity implements View.OnClickListener {
	
	private Context mContext ;

	private TextView mTvMainInfo ;

	private RelativeLayout mLLBack ;

	private LinearLayout mLLCharge ;

	private void charge(String payid,String money){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Member.Pay") ;
		map.put("payid",payid) ;
		map.put("money",money) ;

		map.put("sign", UtilsTools.getSign(mContext,"App.Member.Pay")) ;

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
					/*	LoginBean bean = gson.fromJson(message,LoginBean.class) ;
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


	private void initView(){
		mLLCharge = findViewById(R.id.ll_charge) ;
		mLLCharge.setOnClickListener(this);
		mLLBack = findViewById(R.id.ll_back) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mLLBack.setVisibility(View.VISIBLE);
		mLLBack.setOnClickListener(this);
	}
	
	private void initData(){
		mTvMainInfo.setText("充值");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge);
		initView() ;
		initData();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.ll_back:
				finish();
				break ;
			case R.id.ll_charge :
				charge(6+"",5+"");
			    /*Intent intent = new Intent(this,ChargeFailActivity.class) ;
			    startActivity(intent);*/
				break ;
		}
	}
}
