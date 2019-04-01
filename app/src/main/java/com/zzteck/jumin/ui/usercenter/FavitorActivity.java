package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.zzteck.jumin.adapter.FavAdapter;
import com.zzteck.jumin.adapter.VideoAdapter;
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


public class FavitorActivity extends BaseActivity implements OnClickListener{


	private TextView mTvTitle ;

	private RelativeLayout mRlBack ;

	private RecyclerView mRecyleViewFav ;

	private int mCurrentPage = 1 ;

	private void favorList(){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Shoucang.Lists") ;
		map.put("page",mCurrentPage+"") ;

		map.put("sign",UtilsTools.getSign(mContext,"App.Shoucang.Lists")) ;

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

	private void initView() {

		mRecyleViewFav = findViewById(R.id.rv_fav) ;
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("我的收藏");
		mRlBack.setVisibility(View.VISIBLE);
	}

	private FavAdapter mFavAdapter ;

	private void initData(){
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyleViewFav.setLayoutManager(linearLayoutManager);
		mFavAdapter = new FavAdapter(this, null);
		mRecyleViewFav.setAdapter(mFavAdapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fav);

		mContext = FavitorActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;
		initData() ;

		favorList() ;

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
