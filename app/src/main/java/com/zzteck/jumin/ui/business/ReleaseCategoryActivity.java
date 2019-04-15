package com.zzteck.jumin.ui.business;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.ReleaseCategoryAdapter;
import com.zzteck.jumin.bean.MainCategoryBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
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

public class ReleaseCategoryActivity extends BaseActivity implements View.OnClickListener {
	
	private Context mContext ;

	private TextView mTvMainInfo ;

	private RelativeLayout mLLBack;

	private RecyclerView mRVCate ;
	
	private void initView(){
		mRVCate = findViewById(R.id.rl_cate) ;
		mTvMainInfo = findViewById(R.id.tv_main_info) ;
		mLLBack = findViewById(R.id.ll_back) ;
		mLLBack.setOnClickListener(this);
		mLLBack.setVisibility(View.VISIBLE);
	}

	private ReleaseCategoryAdapter mReleaseCategoryAdapter;

	private void getMainCategoryList(){

		Map<String, String> map = new HashMap<>();
		map.put("s", "App.Category.Lists");
		map.put("sign", UtilsTools.getSign(mContext,"jumin_"+"App.Category.Lists"));

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, final Response response) throws IOException {

				final String responseStr = response.body().string();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						String message = new String(responseStr);
						Gson gson = new Gson();
						MainCategoryBean bean = gson.fromJson(message,MainCategoryBean.class) ;
						initData(bean);
					}
				});

			}
		});
	}

	private void initData(final MainCategoryBean bean){

		if(bean == null){
			return ;
		}

		mTvMainInfo.setText("发布");

		mRVCate.setLayoutManager(new LinearLayoutManager(this));
		mReleaseCategoryAdapter = new ReleaseCategoryAdapter(this,bean);
		mRVCate.setAdapter(mReleaseCategoryAdapter);

		mReleaseCategoryAdapter.setOnItemClickListener(new ReleaseCategoryAdapter.OnRecyclerViewItemClickListener() {
			@Override
			public void onClick(View view, ReleaseCategoryAdapter.ViewName viewName, int chapterIndex, int sectionIndex,String catId,String subCatId) {
				switch (viewName){
					case CHAPTER_ITEM:
						if(bean.getData().get(chapterIndex).getChildren().size() > 0){
							if(chapterIndex + 1 == bean.getData().size()){
								//如果是最后一个，则滚动到展开的最后一个item
								mRVCate.smoothScrollToPosition(mReleaseCategoryAdapter.getItemCount());
							}
						}else{
							//onClickChapter(chapterIndex);
						}
						break;
					case CHAPTER_ITEM_PRACTISE:
						break;
					case SECTION_ITEM:
						Intent intent1 = new Intent(mContext,ReleaseActivity.class) ;
						intent1.putExtra("catId",catId) ;
						intent1.putExtra("subCatId",subCatId) ;
						startActivity(intent1);
						break;
				}
			}
		});

		//以下是对布局进行控制，让课时占据一行，小节每四个占据一行，结果就是相当于一个ListView嵌套GridView的效果

		final GridLayoutManager manager = new GridLayoutManager(this, 3);

		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				return mReleaseCategoryAdapter.getItemViewType(position) == ReleaseCategoryAdapter.VIEW_TYPE_CHAPTER ? 3 : 1;
			}
		});

		mRVCate.setLayoutManager(manager);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release_cat);
		mContext = this ;
		initView() ;
		getMainCategoryList() ;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.ll_back:
				finish();
				break ;
		}
	}
}
