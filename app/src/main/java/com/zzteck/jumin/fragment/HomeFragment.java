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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.ComFragmentAdapter;
import com.zzteck.jumin.adapter.FeaturedPagerAdapter;
import com.zzteck.jumin.adapter.VideoAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.BannerBean;
import com.zzteck.jumin.bean.CategoryBean;
import com.zzteck.jumin.bean.HomeBean;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.VideoBean;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.business.MoreCategoryActivity;
import com.zzteck.jumin.ui.location.ActivityLocation;
import com.zzteck.jumin.ui.location.LocationActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.ui.mainui.SearchActivity;
import com.zzteck.jumin.ui.mainui.ZxingActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.GlideCircleTransform;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.ColorFlipPagerTitleView;
import com.zzteck.jumin.view.JudgeNestedScrollView;
import com.zzteck.jumin.view.SignInDialog;
import com.zzteck.jumin.webmanager.HttpUtils;
import com.zzteck.jumin.webmanager.VolleyInterface;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

  //  private String[] mTitles = new String[]{"推荐", "二手房", "商家"};

    private List<String> mDataList = new ArrayList<>();

    private RecommandFragment mRecomandFragment ;

    private SecondHandFragment mSecondHandFragment ;

    private BusinessFragment mBusinessFragment ;

    private ViewPager viewPagerHome;

    private LinearLayout mPoints ;

    private ViewPager mBannerViewPaper ;

    private TextView mTvSearch ;

    private LinearLayout mLLeft ;

    private List<Fragment> getFragments(List<String> data) {

        List<Fragment> fragments = new ArrayList<>();

        if(data != null && data.size() > 0){

            for(int i = 0 ;i < data.size() ;i++){

                mRecomandFragment = new RecommandFragment() ;
                Bundle bundle = new Bundle();
                bundle.putString("item", data.get(i));
                mRecomandFragment.setArguments(bundle);
                fragments.add(mRecomandFragment);

            }
        }

        //fragments.add(mSecondHandFragment);
        //fragments.add(mBusinessFragment);

        return fragments;
    }

    private List<BannerBean.DataBean> modelList = new ArrayList<>() ;

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

    private LinearLayout mLLMore ;

    private RecyclerView mRVVideo ;

    private VideoAdapter mVideoAdapter ;

    private List<VideoBean.DataBean> mVideoList = new ArrayList<>();

    private void initData(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRVVideo.setLayoutManager(linearLayoutManager);
        mVideoAdapter = new VideoAdapter(getActivity(),mVideoList);
        mRVVideo.setAdapter(mVideoAdapter);

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Info.Getvideoinfo") ;
        map.put("cityid","1") ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.HOST+"?"+ UtilsTools.getMapToString(map), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String message = new String(response.getBytes()) ;
                Gson gson = new Gson() ;
                VideoBean bean = gson.fromJson(message,VideoBean.class) ;

                if(bean != null && bean.getData() != null && bean.getData().size() > 0){
                    for(int i = 0 ;i < bean.getData().size() ;i++){
                        mVideoList.add(bean.getData().get(i)) ;
                    }
                }
                mVideoAdapter.notifyVideoListChange(mVideoList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setTag("");

        App.getHttpQueues().add(stringRequest);

    }

    private ImageView mIvOne ;

    private ImageView mIvTwo ;

    private ImageView mIvThree ;

    private ImageView mIvFour ;

    private TextView mTvOne ;

    private TextView mTvTwo ;

    private TextView mTvThree ;

    private TextView mTvFour ;

    @Override
    public void initView(View view) {

        mRVVideo = view.findViewById(R.id.rv_video) ;
        mLLMore = view.findViewById(R.id.ll_more) ;
        mLLeft = view.findViewById(R.id.ll_left) ;
        mIvQianDao= view.findViewById(R.id.iv_qiandao) ;
        mIvZxing = view.findViewById(R.id.iv_zxing) ;
        mTvSearch = view.findViewById(R.id.tv_search) ;
        mPoints = view.findViewById(R.id.layout_points) ;
        viewPagerHome = view.findViewById(R.id.view_pager) ;
        mBannerViewPaper = view.findViewById(R.id.vp_banner) ;
        refreshLayout = view.findViewById(R.id.refreshLayout) ;
        scrollView = view.findViewById(R.id.scrollView) ;
        magicIndicator = view.findViewById(R.id.magic_indicator) ;

        mIvOne = view.findViewById(R.id.iv_one) ;
        mIvTwo = view.findViewById(R.id.iv_two) ;
        mIvThree = view.findViewById(R.id.iv_three) ;
        mIvFour = view.findViewById(R.id.iv_four) ;

        mTvOne = view.findViewById(R.id.tv_one) ;
        mTvTwo = view.findViewById(R.id.tv_two) ;
        mTvThree = view.findViewById(R.id.tv_three) ;
        mTvFour = view.findViewById(R.id.tv_four) ;

        mIvErShouFang = view.findViewById(R.id.iv_ershoufang) ;
        mIvXinFang = view.findViewById(R.id.iv_xinfang) ;
        mIvChuZu = view.findViewById(R.id.iv_chuzu) ;
        mIvShangpuChuzu = view.findViewById(R.id.iv_shangpuchuzu) ;
        mIvShangpuZhuanrang = view.findViewById(R.id.iv_shangpuzhuanrang) ;

        mIvCategoryList.add(mIvOne) ;
        mIvCategoryList.add(mIvTwo) ;
        mIvCategoryList.add(mIvThree) ;
        mIvCategoryList.add(mIvFour) ;

        mTvCategoryList.add(mTvOne) ;
        mTvCategoryList.add(mTvTwo) ;
        mTvCategoryList.add(mTvThree) ;
        mTvCategoryList.add(mTvFour) ;


        mImagePicLogo.add(mIvErShouFang) ;
        mImagePicLogo.add(mIvXinFang) ;
        mImagePicLogo.add(mIvChuZu) ;
        mImagePicLogo.add(mIvShangpuChuzu) ;
        mImagePicLogo.add(mIvShangpuZhuanrang) ;

        mTvSearch.setOnClickListener(this);
        mIvQianDao.setOnClickListener(this);
        mIvZxing.setOnClickListener(this);
        mLLeft.setOnClickListener(this);
        mLLMore.setOnClickListener(this);

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

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Index.Banner") ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.HOST+"?"+ UtilsTools.getMapToString(map), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String message = new String(response.getBytes()) ;
                Gson gson = new Gson() ;
                BannerBean bean = gson.fromJson(message,BannerBean.class) ;

                if(bean != null && bean.getData() != null && bean.getData().size() > 0){
                    for(int i = 0 ;i < bean.getData().size() ;i++){
                        modelList.add(bean.getData().get(i)) ;
                    }
                }
                initViewPager() ;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setTag("");
        App.getHttpQueues().add(stringRequest);

        getCategoryTitle1() ;
        getCategoryTitle2();

        getCategoryTitle3();

    }

    private List<ImageView> mIvCategoryList = new ArrayList<>();

    private List<TextView> mTvCategoryList = new ArrayList<>() ;

    private void getCategoryTitle1(){

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Category.Pushcat") ;
        map.put("type",1+"") ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.HOST+"?"+ UtilsTools.getMapToString(map), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String message = new String(response.getBytes()) ;
                Gson gson = new Gson() ;
                CategoryBean bean = gson.fromJson(message,CategoryBean.class) ;
                if(bean.getData() != null && bean.getData().size() > 0){
                    for(int i = 0 ;i < bean.getData().size();i++){
                        Glide.with(getActivity())
                                .load(bean.getData().get(i).getIcon())
                                .placeholder(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher)
                                .crossFade(300)
                                .transform(new GlideCircleTransform(getActivity()))
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .into(mIvCategoryList.get(i));
                        mTvCategoryList.get(i).setText(bean.getData().get(i).getCatname());
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setTag("");
        App.getHttpQueues().add(stringRequest);

    }

    private ImageView mIvErShouFang ;

    private ImageView mIvXinFang ;

    private ImageView mIvChuZu ;

    private ImageView mIvShangpuChuzu ;

    private ImageView mIvShangpuZhuanrang ;

    private List<ImageView>  mImagePicLogo = new ArrayList<>();

    private void getCategoryTitle2(){

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Category.Pushcat") ;
        map.put("type",2+"") ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.HOST+"?"+ UtilsTools.getMapToString(map), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String message = new String(response.getBytes()) ;
                Gson gson = new Gson() ;
                CategoryBean bean = gson.fromJson(message,CategoryBean.class) ;
                if(bean.getData() != null && bean.getData().size() > 0){
                    for(int i = 0 ;i < bean.getData().size();i++){
                        Glide.with(getActivity())
                                .load(bean.getData().get(i).getIcon())
                                .placeholder(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher)
                                .crossFade(300)
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .into(mImagePicLogo.get(i));
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setTag("");
        App.getHttpQueues().add(stringRequest);

    }

    private void getCategoryTitle3(){

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Category.Pushcat") ;
        map.put("type",3+"") ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.HOST+"?"+ UtilsTools.getMapToString(map), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String message = new String(response.getBytes()) ;
                Gson gson = new Gson() ;
                CategoryBean bean = gson.fromJson(message,CategoryBean.class) ;
                if(bean.getData() != null && bean.getData().size() > 0){
                    for(int i = 0 ;i < bean.getData().size() ;i++){
                        mDataList.add(bean.getData().get(i).getCatname()) ;
                    }

                    initMagicIndicator() ;

                    viewPagerHome.setAdapter(new ComFragmentAdapter(getActivity().getSupportFragmentManager(), getFragments(mDataList)));
                    viewPagerHome.setOffscreenPageLimit(10);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setTag("");
        App.getHttpQueues().add(stringRequest);

    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setScrollPivotX(0.65f);
      //  commonNavigator.setAdjustMode(true);
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
                        viewPagerHome.setCurrentItem(index);
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
        initData();
    }


    @Override
    public void onClick(View view) {
        Intent intent = null ;
        switch (view.getId()){
            case R.id.ll_more:
                intent = new Intent(getActivity(), MoreCategoryActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.ll_left:
                intent = new Intent(getActivity(), ActivityLocation.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_qiandao :
                SignInDialog dialog = new SignInDialog(getActivity()) ;
                dialog.show();
                break ;
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
