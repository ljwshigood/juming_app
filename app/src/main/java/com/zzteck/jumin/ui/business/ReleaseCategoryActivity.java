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

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MainCategoryAdapter;
import com.zzteck.jumin.adapter.ReleaseCategoryAdapter;
import com.zzteck.jumin.bean.ChapterInfo;
import com.zzteck.jumin.bean.CourseInfo;
import com.zzteck.jumin.bean.SectionInfo;
import com.zzteck.jumin.ui.mainui.BaseActivity;

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

	private CourseInfo mCourseInfo;

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


	private void initData(){

		mCourseInfo = new CourseInfo();
		mCourseInfo.name = "标题";
		for(int i = 0; i < 11; i++){
			ChapterInfo chapterInfo = new ChapterInfo();
			chapterInfo.name = resInfo[i];
			chapterInfo.res = res[i] ;
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



		mTvMainInfo.setText("发布");

		mRVCate.setLayoutManager(new LinearLayoutManager(this));
		mReleaseCategoryAdapter = new ReleaseCategoryAdapter(this,mCourseInfo);
		mRVCate.setAdapter(mReleaseCategoryAdapter);

		mReleaseCategoryAdapter.setOnItemClickListener(new ReleaseCategoryAdapter.OnRecyclerViewItemClickListener() {
			@Override
			public void onClick(View view, ReleaseCategoryAdapter.ViewName viewName, int chapterIndex, int sectionIndex) {
				switch (viewName){
					case CHAPTER_ITEM:
						if(mCourseInfo.chapterInfos.get(chapterIndex).sectionInfos.size() > 0){
							if(chapterIndex + 1 == mCourseInfo.chapterInfos.size()){
								//如果是最后一个，则滚动到展开的最后一个item
								mRVCate.smoothScrollToPosition(mReleaseCategoryAdapter.getItemCount());
							}
						}else{
							//onClickChapter(chapterIndex);
						}
						break;
					case CHAPTER_ITEM_PRACTISE:

						Intent intent = new Intent(mContext,ReleaseActivity.class) ;
						startActivity(intent);

						//onClickPractise(chapterIndex);
						break;
					case SECTION_ITEM:
						Intent intent1 = new Intent(mContext,ReleaseActivity.class) ;
						startActivity(intent1);
						//onClickSection(chapterIndex, sectionIndex);
						break;
				}
			}
		});

		//以下是对布局进行控制，让课时占据一行，小节每四个占据一行，结果就是相当于一个ListView嵌套GridView的效果

		final GridLayoutManager manager = new GridLayoutManager(this, 4);

		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				return mReleaseCategoryAdapter.getItemViewType(position) == MainCategoryAdapter.VIEW_TYPE_CHAPTER ? 4 : 1;
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
		initData();
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
