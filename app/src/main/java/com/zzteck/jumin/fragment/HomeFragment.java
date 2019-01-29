package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.ComFragmentAdapter;
import com.zzteck.jumin.adapter.FeaturedPagerAdapter;
import com.zzteck.jumin.bean.HomeBean;
import com.zzteck.jumin.ui.mainui.SearchActivity;
import com.zzteck.jumin.ui.mainui.ZxingActivity;
import com.zzteck.jumin.view.ColorFlipPagerTitleView;
import com.zzteck.jumin.view.JudgeNestedScrollView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2018/7/6.
 * Describe:
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{

    private List<HomeBean> data;

    private int height;

    private SmartRefreshLayout refreshLayout ;

    private JudgeNestedScrollView scrollView;

    private MagicIndicator magicIndicator;

    private String[] mTitles = new String[]{"推荐", "二手房", "商家"};

    private List<String> mDataList = Arrays.asList(mTitles);

    private RecommandFragment mRecomandFragment ;

    private SecondHandFragment mSecondHandFragment ;

    private BusinessFragment mBusinessFragment ;

    private ViewPager viewPagerHome;

    private LinearLayout mPoints ;

    private ViewPager mBannerViewPaper ;

    private TextView mTvSearch ;

    private List<Fragment> getFragments() {

        List<Fragment> fragments = new ArrayList<>();

        mRecomandFragment = new RecommandFragment() ;
        mSecondHandFragment = new SecondHandFragment() ;
        mBusinessFragment = new BusinessFragment() ;

        fragments.add(mRecomandFragment);
        fragments.add(mSecondHandFragment);
        fragments.add(mBusinessFragment);

        return fragments;
    }

    private List<String> modelList = new ArrayList<>() ;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home ;
    }

    private int mPlayTimeInterval = 2500 ;

    private void initViewPager() {
        mPlayTimeInterval = (int) 2.5 * 2000;
        if (modelList != null && modelList.size() > 0) {
            for (int i = 0; i < modelList.size(); i++) {
                View view = new View(getActivity());
                view.setBackgroundResource(R.drawable.dot_normal);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
                params.leftMargin = 18 ;
                view.setLayoutParams(params) ;
                if (mPoints.getChildCount() == i) {
                    mPoints.addView(view) ;
                }
            }

            mPoints.getChildAt(0).setBackgroundResource(R.drawable.dot_focused);
            //设置适配器
            FeaturedPagerAdapter adapter = new FeaturedPagerAdapter(getActivity(), modelList,0);
            mBannerViewPaper.setAdapter(adapter);
            mBannerViewPaper.setOffscreenPageLimit(3);
            mBannerViewPaper.setCurrentItem(0);

            mBannerViewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    mPoints.getChildAt(prePosition).setBackgroundResource(R.drawable.dot_normal) ;
                    mPoints.getChildAt(position % mPoints.getChildCount()).setBackgroundResource(R.drawable.dot_focused) ;
                    prePosition = position % mPoints.getChildCount() ;
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int arg0) {

                }
            }) ;
        }

        if (modelList != null && modelList.size() > 1) {
            startPlay();

            mBannerViewPaper.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int action = MotionEventCompat.getActionMasked(event);

                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            stopPlay();
                            break;

                        case MotionEvent.ACTION_MOVE:
                            stopPlay();
                            break;

                        case MotionEvent.ACTION_UP:
                            startPlay();
                            break;
                    }

                    return false;
                }
            });
        }
    }

    private boolean mIsPlay = false ;

    private int prePosition ;

    /**
     * 描述：开始自动轮播.
     */
    public void startPlay() {
        if (mHandler != null) {
            mIsPlay = true;
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, mPlayTimeInterval);
        }
    }

    /**
     * 描述：停止自动轮播.
     */
    public void stopPlay() {
        if (mHandler != null) {
            mIsPlay = false;
            mHandler.removeCallbacks(mRunnable);
        }
    }

    /**
     * 用于轮播的Runnable
     */
    private Runnable mRunnable = new Runnable() {
        public void run() {
            if (mBannerViewPaper != null) {
                mHandler.sendEmptyMessage(0);
            }
        }
    };

    /**
     * 用于轮播的handler
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int currentItem = mBannerViewPaper.getCurrentItem();
                mBannerViewPaper.setCurrentItem(currentItem + 1);
                mHandler.postDelayed(mRunnable, 3000);
            }
        }

    };


    private ImageView mIvQianDao ;

    private ImageView mIvZxing ;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){

            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                // mEtBarCode.setText(result) ;
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                //  mEtBarCode.setText("") ;
            }
        }
    }

    @Override
    public void initView(View view) {

        mIvQianDao= view.findViewById(R.id.iv_qiandao) ;
        mIvZxing = view.findViewById(R.id.iv_zxing) ;
        mTvSearch = view.findViewById(R.id.tv_search) ;
        mPoints = view.findViewById(R.id.layout_points) ;
        viewPagerHome = view.findViewById(R.id.view_pager) ;
        mBannerViewPaper = view.findViewById(R.id.vp_banner) ;
        refreshLayout = view.findViewById(R.id.refreshLayout) ;
        scrollView = view.findViewById(R.id.scrollView) ;
        magicIndicator = view.findViewById(R.id.magic_indicator) ;

        mTvSearch.setOnClickListener(this);
        mIvQianDao.setOnClickListener(this);
        mIvZxing.setOnClickListener(this);

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
               /* mOffset = offset / 2;
                ivHeader.setTranslationY(mOffset - mScrollY);*/
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
               /* mOffset = offset / 2;
                ivHeader.setTranslationY(mOffset - mScrollY);*/
            }
        });

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = DensityUtil.dp2px(170);
            int color = ContextCompat.getColor(getActivity(), R.color.mainWhite) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                magicIndicator.getLocationOnScreen(location);
                int yPosition = location[1];
               /* if (yPosition < toolBarPositionY) {
                    magicIndicatorTitle.setVisibility(View.VISIBLE);
                    scrollView.setNeedScroll(false);
                } else {
                    magicIndicatorTitle.setVisibility(View.GONE);
                    scrollView.setNeedScroll(true);

                }*/

                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                  /*  mScrollY = scrollY > h ? h : scrollY;
                    buttonBarLayout.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    ivHeader.setTranslationY(mOffset - mScrollY);*/
                }
                /*if (scrollY == 0) {
                    ivBack.setImageResource(R.drawable.back_white);
                    ivMenu.setImageResource(R.drawable.icon_menu_white);
                } else {
                    ivBack.setImageResource(R.drawable.back_black);
                    ivMenu.setImageResource(R.drawable.icon_menu_black);
                }*/

                lastScrollY = scrollY;
            }
        });

        viewPagerHome.setAdapter(new ComFragmentAdapter(getActivity().getSupportFragmentManager(), getFragments()));
        viewPagerHome.setOffscreenPageLimit(10);
        initMagicIndicator();

        String[] images= new String[] {
                "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
                "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
                "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
                "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
                "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
                "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};

        for(int i = 0 ;i < images.length ;i++){
            modelList.add(images[i]) ;
        }

        initViewPager() ;

    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(getActivity(), R.color.mainBlack));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(getActivity(), R.color.mainBlack));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPagerHome.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(getActivity(), R.color.mainRed));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPagerHome);
    }

    @Override
    public void lazyLoad() {

    }


    @Override
    public void onClick(View view) {
        Intent intent = null ;
        switch (view.getId()){
            case R.id.tv_search :
                intent = new Intent(getActivity(), SearchActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_zxing:
                intent = new Intent(getActivity(),ZxingActivity.class) ;
                startActivityForResult(intent,1122) ;
                break ;
        }
    }
}
