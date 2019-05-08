package com.zzteck.jumin.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MoreCategoryAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.MoreCategory;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;
import com.zzteck.jumin.view.TimeItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MoreCategoryActivity extends BaseActivity implements OnClickListener{

	private String TAG = "MoreCategoryActivity" ;

	private RecyclerView mRvCategory ;

	private void initView() {
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("更多");
		mRvCategory = findViewById(R.id.rv_more_cate);
		mRlBack.setVisibility(View.VISIBLE);
	}

	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private MoreCategoryAdapter mMoreCategoryAdapter ;

	private int[] res = new int[]{R.mipmap.icon_cheliang_gengduo,
			R.mipmap.icon_chongwu_gengduo,
			R.mipmap.icon_ershou_gengduo,
			R.mipmap.icon_fangwu_gengduo,
			R.mipmap.icon_jianzhi_gengduo,
			R.mipmap.icon_jiaoyu_gengduo,
			R.mipmap.icon_jiaoyou_gengduo,
			R.mipmap.icon_qiuzhi_gengduo,
			R.mipmap.icon_quanzhi_gengduo,
			R.mipmap.icon_shangjia_gengduo,
			R.mipmap.icon_shenghuo_gengduo} ;

	private String[] resInfo = new String[]{"车辆",
			"宠物",
			"二手",
			"房屋",
			"兼职",
			"教育",
			"交友",
			"求职",
			"全职",
			"商家",
			"生活"} ;


	private List<MoreCategory> mMoreCategoryList = new ArrayList<>() ;

	private void initData(){

		mRvCategory.setLayoutManager(new GridLayoutManager(this,3));
		mRvCategory.addItemDecoration(new TimeItemDecoration(this));
		//mRvCategory.addItemDecoration(new NormalDecoration(ContextCompat.getColor(this, R.color.mainGrayF8), (int) getResources().getDimension(R.dimen.one)));
		mMoreCategoryAdapter = new MoreCategoryAdapter(mContext,null) ;
		mRvCategory.setAdapter(mMoreCategoryAdapter);
	}

	private void AppCategoryLists(){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Category.Lists") ;
		map.put("catid",0+"") ;

		map.put("sign", UtilsTools.getSign(mContext,"App.Category.Lists")) ;

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
						MoreCategory bean = gson.fromJson(message,MoreCategory.class) ;
						mMoreCategoryAdapter.notifyMoreCategoryAdapter(bean.getData());
					}
				});
			}
		});

	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_more_category);

		mContext = MoreCategoryActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;
		initData() ;
		AppCategoryLists() ;
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
			case R.id.ll_back:
				finish();
				break ;
		}
	}
}
