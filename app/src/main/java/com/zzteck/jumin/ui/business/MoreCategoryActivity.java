package com.zzteck.jumin.ui.business;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MoreCategoryAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.MoreCategory;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.view.NormalDecoration;

import java.util.ArrayList;
import java.util.List;


public class MoreCategoryActivity extends BaseActivity implements OnClickListener{

	private String TAG = "MoreCategoryActivity" ;

	private RecyclerView mRvCategory ;

	private void initView() {
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mIvBack = findViewById(R.id.iv_back) ;
		mIvBack.setOnClickListener(this);
		mTvTitle.setText("更多");
		mRvCategory = findViewById(R.id.rv_more_cate);
	}

	private TextView mTvTitle ;

	private ImageView mIvBack ;

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

		for(int i = 0 ;i < res.length ;i++){
			MoreCategory bean = new MoreCategory() ;
			bean.setInfo(resInfo[i]);
			bean.setRes(res[i]);
			mMoreCategoryList.add(bean) ;
		}

		mRvCategory.setLayoutManager(new GridLayoutManager(this,2));
		mRvCategory.addItemDecoration(new NormalDecoration(ContextCompat.getColor(this, R.color.mainGrayF8), (int) getResources().getDimension(R.dimen.one)));
		mMoreCategoryAdapter = new MoreCategoryAdapter(mContext,mMoreCategoryList) ;
		mRvCategory.setAdapter(mMoreCategoryAdapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_more_category);

		mContext = MoreCategoryActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;
		initData() ;
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
			case R.id.iv_back:
				finish();
				break ;
		}
	}
}
