package com.zzteck.jumin.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.CategoryBean;
import com.zzteck.jumin.bean.QiandaoBean;
import com.zzteck.jumin.bean.User;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.GlideCircleTransform;
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


public class SignInDialog extends Dialog{


	private Context mContext;

	private LinearLayout mLLQianDao ;

	public SignInDialog(@NonNull Context context) {
		super(context,R.style.CustomDialogStyle);
		this.mContext = context;
	}
	public SignInDialog(@NonNull Context context,String hint) {
		super(context,R.style.CustomDialogStyle);
		this.mContext = context;
	}

	private void getQiandao(String uid) {

		Map<String, String> map = new HashMap<>();
		map.put("s", "App.Member.Qiandao");
		map.put("userid", uid + "");

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
						QiandaoBean bean = gson.fromJson(responseStr, QiandaoBean.class);
						if(bean.getData().isIs_success() == true){

						}else{
							WindowsToast.makeText(mContext,bean.getData().getInfo()).show();
						}

						dismiss();

					}
				});
			}
		});

	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sign,null,false);
		setContentView(view);

		mLLQianDao = view.findViewById(R.id.ll_qiandao) ;

		mLLQianDao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				User user = UserDAO.getInstance(mContext).selectUserByIsLogin(1) ;
				getQiandao(user.getUserid()) ;
			}
		});
	}

}
