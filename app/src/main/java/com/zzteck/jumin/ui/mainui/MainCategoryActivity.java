package com.zzteck.jumin.ui.mainui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MainCategoryAdapter;
import com.zzteck.jumin.adapter.ComFragmentAdapter;
import com.zzteck.jumin.bean.ChapterInfo;
import com.zzteck.jumin.bean.CourseInfo;
import com.zzteck.jumin.bean.SectionInfo;
import com.zzteck.jumin.fragment.RecommandFragment;
import com.zzteck.jumin.utils.ScreenUtil;
import com.zzteck.jumin.view.ColorFlipPagerTitleView;
import com.zzteck.jumin.view.JudgeNestedScrollView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;


public class MainCategoryActivity extends BaseActivity implements View.OnClickListener{

	private RecyclerView mRecyclerView;

	private CourseInfo mCourseInfo;

	private Toolbar toolbar ;

	private String [] mString = new String[]{"出租" ,"出售","商铺","地区"} ;

	private ViewPager viewPager ;

	private int[] mRes = new int[] {R.mipmap.icon_chushou_dalei,R.mipmap.icon_chuzu_dalei,
									R.mipmap.icon_diqu_dalei,R.mipmap.icon_shangpu_dalei} ;

	private List<String> mDataList = new ArrayList<>();

	private MagicIndicator magicIndicator;

	private void initMagicIndicator() {
		mDataList.add("推荐") ;
		CommonNavigator commonNavigator = new CommonNavigator(this);
		commonNavigator.setScrollPivotX(0.65f);
		commonNavigator.setAdapter(new CommonNavigatorAdapter() {
			@Override
			public int getCount() {
				return mDataList == null ? 0 : mDataList.size();
			}

			@Override
			public IPagerTitleView getTitleView(Context context, final int index) {
				SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
				simplePagerTitleView.setText(mDataList.get(index));
				simplePagerTitleView.setNormalColor(ContextCompat.getColor(MainCategoryActivity.this, R.color.mainBlack));
				simplePagerTitleView.setSelectedColor(ContextCompat.getColor(MainCategoryActivity.this, R.color.mainBlack));
				simplePagerTitleView.setTextSize(16);
				simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						viewPager.setCurrentItem(index, false);
					}
				});
				return simplePagerTitleView;
			}

			@Override
			public IPagerIndicator getIndicator(Context context) {


				LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
				linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
				linePagerIndicator.setColors(ContextCompat.getColor(MainCategoryActivity.this, R.color.mainRed));

				return linePagerIndicator;
			}
		});
		magicIndicator.setNavigator(commonNavigator);
		ViewPagerHelper.bind(magicIndicator, viewPager);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_main);
		initData();
		initView();
		initMagicIndicator() ;
	}

	private void initData(){

		mCourseInfo = new CourseInfo();
		mCourseInfo.name = "标题";
		for(int i = 0; i < 4; i++){
			ChapterInfo chapterInfo = new ChapterInfo();
			chapterInfo.name = mString[i];
			chapterInfo.res = mRes[i] ;
			chapterInfo.chapterIndex = i;
			if(i==0){
				for(int j = 0; j < 2; j++){
					SectionInfo sectionInfo = new SectionInfo();
					sectionInfo.name = "商铺单租";
					sectionInfo.chapterIndex = i;
					sectionInfo.sectionIndex = j;
					chapterInfo.sectionInfos.add(sectionInfo);
				}
			}else if(i==1){
				for(int j = 0; j < 3; j++){
					SectionInfo sectionInfo = new SectionInfo();
					sectionInfo.name = "商铺单租";
					sectionInfo.chapterIndex = i;
					sectionInfo.sectionIndex = j;
					chapterInfo.sectionInfos.add(sectionInfo);
				}
			}else if(i==2){
				for (int j = 0; j < 6; j++) {
					SectionInfo sectionInfo = new SectionInfo();
					sectionInfo.name = "商铺单租";
					sectionInfo.chapterIndex = i;
					sectionInfo.sectionIndex = j;
					chapterInfo.sectionInfos.add(sectionInfo);
				}
			}else{
				for (int j = 0; j < 4; j++) {
					SectionInfo sectionInfo = new SectionInfo();
					sectionInfo.name = "商铺单租";
					sectionInfo.chapterIndex = i;
					sectionInfo.sectionIndex = j;
					chapterInfo.sectionInfos.add(sectionInfo);
				}
			}
			mCourseInfo.chapterInfos.add(chapterInfo);
		}
	}

	private RecommandFragment mRecomandFragment ;

	private List<Fragment> getFragments() {

		mRecomandFragment = new RecommandFragment() ;
		Bundle bundle = new Bundle();
		bundle.putString("item", "41");
		mRecomandFragment.setArguments(bundle);
		List<Fragment> fragments = new ArrayList<>();
		fragments.add(mRecomandFragment);

		return fragments;
	}

	private JudgeNestedScrollView scrollView;

	private MainCategoryAdapter mMainCategoryAdapter;

	private int mScrollY = 0;

	private TextView mTvTitle ;

	private RelativeLayout mLLBack;

	private void initView(){

		mTvTitle = findViewById(R.id.tv_main_info) ;
		mLLBack = findViewById(R.id.ll_back) ;
		mLLBack.setOnClickListener(this);
		mTvTitle.setText("二手房");

		mLLBack.setVisibility(View.VISIBLE);

		viewPager = findViewById(R.id.view_pager) ;
		magicIndicator = findViewById(R.id.magic_indicator) ;
		scrollView = findViewById(R.id.scrollView) ;
		toolbar = findViewById(R.id.home_toolbar) ;
		mRecyclerView = findViewById(R.id.rv_expand);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mMainCategoryAdapter = new MainCategoryAdapter(this,mCourseInfo);
		mRecyclerView.setAdapter(mMainCategoryAdapter);
		mMainCategoryAdapter.setOnItemClickListener(new MainCategoryAdapter.OnRecyclerViewItemClickListener() {
			@Override
			public void onClick(View view, MainCategoryAdapter.ViewName viewName, int chapterIndex, int sectionIndex) {

				switch (viewName){
					case CHAPTER_ITEM:
						if(mCourseInfo.chapterInfos.get(chapterIndex).sectionInfos.size() > 0){
							if(chapterIndex + 1 == mCourseInfo.chapterInfos.size()){
								//如果是最后一个，则滚动到展开的最后一个item
								mRecyclerView.smoothScrollToPosition(mMainCategoryAdapter.getItemCount());
							}
						}else{
							onClickChapter(chapterIndex);
						}
						break;
					case CHAPTER_ITEM_PRACTISE:
						onClickPractise(chapterIndex);
						break;
					case SECTION_ITEM:
						onClickSection(chapterIndex, sectionIndex);
						break;
				}
			}
		});

		//以下是对布局进行控制，让课时占据一行，小节每四个占据一行，结果就是相当于一个ListView嵌套GridView的效果

		final GridLayoutManager manager = new GridLayoutManager(this, 4);

		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				return mMainCategoryAdapter.getItemViewType(position) == MainCategoryAdapter.VIEW_TYPE_CHAPTER ? 4 : 1;
			}
		});

		mRecyclerView.setLayoutManager(manager);

		toolbar.post(new Runnable() {
			@Override
			public void run() {
				dealWithViewPager();
			}
		});


		scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
			int lastScrollY = 0;
			int maxh = DensityUtil.dp2px(80);
			int minh = DensityUtil.dp2px(40) ;
			int color = ContextCompat.getColor(MainCategoryActivity.this, R.color.mainWhite) & 0x00ffffff;

			@Override
			public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
				int[] location = new int[2];
			//	magicIndicator.getLocationOnScreen(location);
				int yPosition = location[1];
				if (yPosition < toolBarPositionY) {
				//	magicIndicatorTitle.setVisibility(View.VISIBLE);
					scrollView.setNeedScroll(false);
				} else {
				//	magicIndicatorTitle.setVisibility(View.GONE);
					scrollView.setNeedScroll(true);
				}

				if (lastScrollY > maxh) {
					scrollY = Math.min(maxh, scrollY);
					mScrollY = scrollY > maxh ? maxh : scrollY;
					toolbar.setVisibility(View.VISIBLE);
					toolbar.setBackgroundColor(((255 * mScrollY / maxh) << 24) | color);
				}

				if(lastScrollY < minh){
					toolbar.setVisibility(View.GONE);

				}

				lastScrollY = scrollY;
			}
		});

		toolbar.setVisibility(View.GONE);
		toolbar.setBackgroundColor(0);

		viewPager.setAdapter(new ComFragmentAdapter(MainCategoryActivity.this.getSupportFragmentManager(), getFragments()));
		viewPager.setOffscreenPageLimit(10);


	}

	private int toolBarPositionY = 0;

	private void dealWithViewPager() {
		toolBarPositionY = toolbar.getHeight();
		ViewGroup.LayoutParams params = viewPager.getLayoutParams();
		params.height = ScreenUtil.getScreenHeightPx(this) - toolBarPositionY /*- magicIndicator.getHeight() */+ 1;
		viewPager.setLayoutParams(params);
	}


	private void onClickChapter(int chapterIndex){

	}

	private void onClickSection(int chapterIndex, int sectionIndex){

	}

	private void onClickPractise(int chapterIndex){

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