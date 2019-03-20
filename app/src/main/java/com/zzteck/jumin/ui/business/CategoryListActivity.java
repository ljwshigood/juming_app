package com.zzteck.jumin.ui.business;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MainFilterAdapter;
import com.zzteck.jumin.adapter.RecommandAdapter;
import com.zzteck.jumin.adapter.SubFilterAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.FilterBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CategoryListActivity extends Activity implements OnClickListener{

	private String TAG = "liujw" ;

	private View mMainView ;

	private Context mContext ;

	private RecyclerView mGvCommand ;

	private RecommandAdapter mCommandAdapter ;

	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private void initView(){
		mGvCommand = findViewById(R.id.rl_cate_list) ;
		mLLBlogTypeTitle = findViewById(R.id.ll_blog_type);
		mLLTypeTitle = findViewById(R.id.ll_filter_title);

		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("二手房");
		mRlBack.setVisibility(View.VISIBLE);

	}

	private void initData(){
		/*mCommandAdapter = new RecommandAdapter(mContext, null) ;
		mGvCommand.setAdapter(mCommandAdapter);*/
	}

	private LayoutInflater mLayoutInflater ;

	public int mScreenWidth = 0;

	public int mScreenHeight = 0;

	private List<String> mTitleMainList = new ArrayList<String>();

	private int mItemWidth = 0;

	private LinearLayout mLLBlogTypeTitle;

	private PopupWindow mPopupWindow = null;

	/**
	 * 初始化 PopupWindow
	 *
	 */

	private View mFilterView = null;

	private ListView mLvTitleMain = null;

	private ListView mLvTitleSub = null;

	private MainFilterAdapter mMainFilterAdapter = null;

	private SubFilterAdapter mSubFilterAdapter = null;

	private TranslateAnimation mTranslaterAnimation;

	private String[] priceArray ;

	private List<FilterBean> mFilterList = new ArrayList<>() ;

	private void initFilter() {

		priceArray = getResources().getStringArray(R.array.price);

		for (int i = 0; i < priceArray.length ; i++) {
			FilterBean bean = new FilterBean();
			bean.setDetail(priceArray[i]);
			mFilterList.add(bean);
		}
	}

	public void initPopuWindow() {

		mFilterView = LayoutInflater.from(mContext).inflate(R.layout.layout_filter_view, null);
		mLlMainFilter =  mFilterView.findViewById(R.id.ll_main_filter);
		mLLSubFilter =  mFilterView.findViewById(R.id.ll_sub_filter);
		mLvTitleMain =  mFilterView.findViewById(R.id.lv_filter_main);
		mLvTitleSub =  mFilterView.findViewById(R.id.lv_filter_sub);

		mLvTitleMain.requestFocus();
		mLvTitleSub.requestFocus();

		RelativeLayout.LayoutParams lpMainFilter = (RelativeLayout.LayoutParams) mLlMainFilter.getLayoutParams();
		RelativeLayout.LayoutParams lpSubFilter = (RelativeLayout.LayoutParams) mLLSubFilter.getLayoutParams();

		lpMainFilter.height = mScreenHeight * 6 / 10;
		lpSubFilter.height = mScreenHeight * 6 / 10;

		mLlMainFilter.setLayoutParams(lpMainFilter);
		mLLSubFilter.setLayoutParams(lpSubFilter);

		mPopupWindow = new PopupWindow(mFilterView);
		mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

		mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x55000000));

		mPopupWindow.setFocusable(true);
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.update();
		mPopupWindow.setTouchable(true);

		mFilterView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mPopupWindow.dismiss();
				return false;
			}
		});
		mFilterView.setFocusableInTouchMode(true);
	}

	private LinearLayout mLlMainFilter;

	private LinearLayout mLLSubFilter;

	private int mCurrentPosition;

	private void showTitilePopWindows(View v, int position) {
		showFilterPop(v, position);
	}

	private HashMap<Integer, List<FilterBean>> mHashMapSubTitle = new HashMap<Integer, List<FilterBean>>();

	private String mFilterRegion ;

	private void showFilterPop(View view, int position) {

		mCurrentPosition = position;
		if (mPopupWindow == null) {
			initPopuWindow();
		}

		mLLSubFilter.setVisibility(View.GONE);

		final List<FilterBean> filterList = mHashMapSubTitle.get(position);

		mMainFilterAdapter = new MainFilterAdapter(mContext, mFilterList);
		mLvTitleMain.setAdapter(mMainFilterAdapter);

		int[] location = new int[2];
		view.getLocationOnScreen(location);

		mTranslaterAnimation = new TranslateAnimation(0, 0, -700, location[0]);
		mTranslaterAnimation.setDuration(500);

		mFilterView.setAnimation(mTranslaterAnimation);
		mFilterView.startAnimation(mTranslaterAnimation);
		mPopupWindow.showAsDropDown(view);

		mLvTitleMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				FilterBean mFilterBean = mFilterList.get(position);
				TextView tv = (TextView)(mLLBlogTypeTitle.getChildAt(mCurrentPosition)).findViewById(R.id.tv_filter);
				tv.setText(mFilterBean.getDetail());
				switch (mCurrentPosition) {
					case 0:
						if(position == 0){
							mFilterRegion = mFilterBean.getDetail();
							if (mPopupWindow != null && mPopupWindow.isShowing()) {
								mPopupWindow.dismiss();
							}
						}else{

							mFilterRegion = mFilterBean.getDetail() + "区";
							if (mPopupWindow != null && mPopupWindow.isShowing()) {
								mPopupWindow.dismiss();
							}
						}
						break;
					case 1:
						if (mPopupWindow != null && mPopupWindow.isShowing()) {
							mPopupWindow.dismiss();
						}
						if(mFilterBean.getDetail().equals("600以上")){
							String filter = mFilterBean.getDetail().replace("-", ",");
						}else {

							String filter = mFilterBean.getDetail().replace("-", ",");
							/*WebReleaseManager.getInstance(mContext).setmIReleaseManager(NearFragent.this);
							WebReleaseManager.getInstance(mContext).findTask("", "", "", 0, 20, filter);*/
						}


						break;
					case 2:
						if (mPopupWindow != null && mPopupWindow.isShowing()) {
							mPopupWindow.dismiss();
						}
						break;
					case 3:
						if (mPopupWindow != null && mPopupWindow.isShowing()) {
							mPopupWindow.dismiss();
						}
						break;
					default:
						break;
				}



			}
		});

		mLvTitleMain.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					if (mPopupWindow != null && mPopupWindow.isShowing()) {
						mPopupWindow.dismiss();
					}
				}
				return true;
			}
		});
	}

	private void intTitleCount() {
		mTitleMainList.add("距离");
		mTitleMainList.add("报酬");
		mTitleMainList.add("星级");
	}

	private void initBlogTitleColumn() {

		mLayoutInflater = LayoutInflater.from(mContext);
		mScreenWidth = ScreenUtil.getWindowsWidth(this);
		mScreenHeight = ScreenUtil.getWindowsHeight(this);
		mItemWidth = mScreenWidth / mTitleMainList.size();

		mLLBlogTypeTitle.removeAllViews();
		int count = mTitleMainList.size();
		for (int i = 0; i < count; i++) {

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.CENTER;
			final View view = mLayoutInflater.inflate(R.layout.item_filter, null);
			TextView tv = view.findViewById(R.id.tv_filter);
			view.setId(i);
			tv.setTextSize(15);

			tv.setTextColor(mContext.getResources().getColor(R.color.dark));
			tv.setText(mTitleMainList.get(i));

			view.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					if (mPopupWindow != null && mPopupWindow.isShowing()) {
						mPopupWindow.dismiss();
					}

					for (int i = 0; i < mLLBlogTypeTitle.getChildCount(); i++) {
						View localView = mLLBlogTypeTitle.getChildAt(i);
						if (localView != v){
						/*	CheckBox cb = (CheckBox)localView.findViewById(R.id.cb_triangle);
							cb.setChecked(false);*/
						}else {
							/*CheckBox cb = (CheckBox)localView.findViewById(R.id.cb_triangle);
							cb.setChecked(true);*/
							if(i == 1){
								showTitilePopWindows(view, i);
							}
						}
					}
				}
			});

			mLLBlogTypeTitle.addView(view, i, params);
		}
	}

	private LinearLayout mLLTypeTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_categroy_list);
		mContext = CategoryListActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;

		initFilter();
		intTitleCount() ;
		initBlogTitleColumn() ;

		initData();
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
	public void onBackPressed() {

	}
}
