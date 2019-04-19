package com.zzteck.jumin.ui.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ccj.poptabview.FilterConfig;
import com.ccj.poptabview.PopTabView;
import com.ccj.poptabview.base.BaseFilterTabBean;
import com.ccj.poptabview.bean.FilterGroup;
import com.ccj.poptabview.listener.OnPopTabSetListener;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.RecommandAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.FilterInfo;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.jumin.bean.MyFilterConfig;
import com.zzteck.jumin.bean.MyFilterParamsBean;
import com.zzteck.jumin.bean.MyFilterTabBean;
import com.zzteck.jumin.bean.MyPopEntityLoaderImp;
import com.zzteck.jumin.bean.MyResultLoaderImp;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

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


public class CategoryListActivity extends BaseActivity implements OnClickListener,OnPopTabSetListener<MyFilterParamsBean> {

	private String TAG = "liujw" ;

	private View mMainView ;

	private Context mContext ;

	private RecyclerView mGvCommand ;

	private RecommandAdapter mCommandAdapter ;

	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private PopTabView popTabView;

	private void initView(){
		mGvCommand = findViewById(R.id.rl_cate_list) ;
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("二手房");
		mRlBack.setVisibility(View.VISIBLE);

		popTabView = findViewById(R.id.expandpop);

	}

	private void getInfoExtra(final String catId){

		if(TextUtils.isEmpty(catId)){
			return ;
		}

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Info.Extra") ;
		map.put("catid",catId) ;

		map.put("sign", UtilsTools.getSign(mContext,"jumin_"+"App.Info.Extra"));

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

						String message = new String(responseStr) ;
						Gson gson = new Gson() ;
						FilterInfo info = gson.fromJson(message,FilterInfo.class) ;
						addMyMethod(info);

					}
				});
			}
		});

	}


	private String mCategoryId = "41" ;

	private int mCurrentPage = 1  ;

	/**
	 * @param catId
	 * @param cityId
	 * @param pages
	 *
	 */
	private void getInfosList(final String catId, final String cityId, String pages){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Info.Getinfos") ;
		map.put("catid",catId) ;
		map.put("cityid",cityId) ;
		map.put("pages",pages) ;


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

				Log.e("liujw","##########################getInfosList catId : "+catId+" #####################: "+responseStr);

				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						String message = new String(responseStr) ;
						Gson gson = new Gson() ;
						HomeInfo info = gson.fromJson(message,HomeInfo.class) ;
						if(mCurrentPage == 1){
							initData(info);
						}else{
							if(mCommandAdapter != null){
								mCommandAdapter.addAll(info.getData());
							}

						}


					}
				});
			}
		});

	}

	private void initData(HomeInfo info) {

		mGvCommand.setLayoutManager(new LinearLayoutManager(this));
		mGvCommand.addItemDecoration(new NormalDecoration(ContextCompat.getColor(this, R.color.mainGrayF8), (int) this.getResources().getDimension(R.dimen.one)));
		mCommandAdapter = new RecommandAdapter(this,info.getData()) ;
		mGvCommand.setAdapter(mCommandAdapter) ;

		mCommandAdapter.setNoMore(R.layout.view_no_more);
		mCommandAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
			@Override
			public void onMoreShow() {
				Log.e("liujw", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@onMoreShow");
				if(mCategoryId != null){
					mCurrentPage++ ;
					getInfosList(mCategoryId,0+"",mCurrentPage+"") ;
				}

			}

			@Override
			public void onMoreClick() {

			}
		});

		mCommandAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(int position) {

				Intent intent = new Intent(mContext, CategoryDetailActivity.class) ;
				startActivity(intent);

			}
		});

	}


	/**
	 * 模拟数据
	 * 筛选器的 数据格式 都是大同小异
	 * 要点:泛型处理,集合都用父类,实体都用子类表示.
	 * @return
	 */
	public FilterGroup getMyData(FilterInfo.DataBean bean, int groupType, int singleOrMutiply ) {

		FilterGroup filterGroup = new FilterGroup();

		filterGroup.setTab_group_name(bean.getTitle());
		filterGroup.setTab_group_type(groupType);
		filterGroup.setSingle_or_mutiply(singleOrMutiply);

		List<BaseFilterTabBean> singleFilterList = new ArrayList<>();

		String[] choices = bean.getExtra().getChoices().split("\\r\\n");


		for (int i = 0; i < choices.length ; i++) {//一级fitler
			MyFilterTabBean myFilterBean = new MyFilterTabBean();
			myFilterBean.setTab_name(choices[i]);
			myFilterBean.setTag_ids("tagid" + "_" + i );
			myFilterBean.setMall_ids("mallid" + "_" + i );
			myFilterBean.setCategory_ids("Categoryid" + "_" + i);

			/*List<MyFilterTabBean.MyChildFilterBean> childFilterList = new ArrayList<>();
			for (int j = 0; j < 5; j++) {//二级filter
				MyFilterTabBean.MyChildFilterBean myChildFilterBean = new MyFilterTabBean.MyChildFilterBean();
				myChildFilterBean.setTab_name(groupName + "_" + i + "__" + j);
				myChildFilterBean.setTag_ids("tagid" + "_" + i + "__" + j);
				myChildFilterBean.setMall_ids("mallid" + "_" + i + "__" + j);
				myChildFilterBean.setCategory_ids("Categoryid" + "_" + i + "__" + j);

				childFilterList.add(myChildFilterBean);
			}
			//增加二级tab
			myFilterBean.setTabs(childFilterList);*/

			//增加一级tab
			singleFilterList.add(myFilterBean);

		}

		filterGroup.setFilter_tab(singleFilterList);
		return filterGroup;

	}

	private void addMyMethod(FilterInfo info) {


		if(info == null || info.getData() == null){
			return ;
		}

		PopTabView ptv = popTabView.setOnPopTabSetListener(this).setPopEntityLoader(new MyPopEntityLoaderImp()).setResultLoader(new MyResultLoaderImp()) ;

		for(int i = 0 ;i < info.getData().size() ;i++){
			FilterGroup filterGroup = getMyData(info.getData().get(i), MyFilterConfig.TYPE_POPWINDOW_SINGLE,MyFilterConfig.FILTER_TYPE_SINGLE);
			ptv.addFilterItem(filterGroup.getTab_group_name(), filterGroup.getFilter_tab(), filterGroup.getTab_group_type(), filterGroup.getSingle_or_mutiply()) ;
		}

	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_categroy_list);
		mContext = CategoryListActivity.this ;
		App.getInstance().addActivity(this);
 		initView() ;
		initData() ;
		//addMyMethod() ;
		getInfoExtra(mId) ;
		getInfosList(mCategoryId,0+"",mCurrentPage+"") ;

	}

	private String mId ;

	private void initData(){
		Intent intent = getIntent() ;
		String title = intent.getStringExtra("title") ;
		mId = intent.getStringExtra("id") ;
		mTvTitle.setText(title+"");
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

	@Override
	public void onPopTabSet(int index, String lable, MyFilterParamsBean params, String value) {
		//TODO 数据更新在这里
//		Log.e("liujw","#################onPopTabSet : "+params.toString());
	}
}
