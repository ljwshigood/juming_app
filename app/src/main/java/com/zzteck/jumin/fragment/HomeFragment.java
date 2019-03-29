package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baijiahulian.common.crop.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.ComFragmentAdapter;
import com.zzteck.jumin.adapter.FeaturedPagerAdapter;
import com.zzteck.jumin.adapter.VideoAdapter;
import com.zzteck.jumin.bean.BannerBean;
import com.zzteck.jumin.bean.CategoryBean;
import com.zzteck.jumin.bean.User;
import com.zzteck.jumin.bean.VideoBean;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.business.CategoryListActivity;
import com.zzteck.jumin.ui.business.MoreCategoryActivity;
import com.zzteck.jumin.ui.location.LocationActivity;
import com.zzteck.jumin.ui.mainui.MainCategoryActivity;
import com.zzteck.jumin.ui.mainui.SearchActivity;
import com.zzteck.jumin.ui.mainui.ZxingActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.GlideCircleTransform;
import com.zzteck.jumin.utils.ScreenUtil;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.ColorFlipPagerTitleView;
import com.zzteck.jumin.view.JudgeNestedScrollView;
import com.zzteck.jumin.view.SignInDialog;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

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

public class HomeFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = HomeFragment.class.getSimpleName();

    private SmartRefreshLayout refreshLayout;

    private ViewPager viewPager;

    private JudgeNestedScrollView scrollView;

    private MagicIndicator magicIndicator;

    private MagicIndicator magicIndicatorTitle ;

    private Toolbar toolbar ;

    private int toolBarPositionY = 0;

    private int mOffset = 0;

    private int mScrollY = 0;

    private List<String> mDataList = new ArrayList<>();

    private View mMainView ;

    private Context mContext ;

    private RecommandFragment mRecomandFragment ;

    private LinearLayout mPoints ;

    private ViewPager mBannerViewPaper ;

    private TextView mTvSearch ;

    private LinearLayout mLLeft ;

    private TextView mTvLocation ;

    private List<Fragment> getFragments(List<CategoryBean.DataBean> data) {

        List<Fragment> fragments = new ArrayList<>();

        if(data != null && data.size() > 0){

            for(int i = 0 ;i < data.size() ;i++){

                mRecomandFragment = new RecommandFragment() ;
                Bundle bundle = new Bundle();
                bundle.putString("item", data.get(i).getCatid());
                mRecomandFragment.setArguments(bundle);
                fragments.add(mRecomandFragment);

            }
        }
        return fragments;
    }

    private List<BannerBean.DataBean> modelList = new ArrayList<>() ;

    @Subscriber
    public void onEventMainThread(String event) {
        mTvLocation.setText(event);
    }

    private void initData() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRVVideo.setLayoutManager(linearLayoutManager);
        mVideoAdapter = new VideoAdapter(getActivity(), mVideoList);
        mRVVideo.setAdapter(mVideoAdapter);

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Info.Getvideoinfo");
        map.put("cityid", "1");

        List<User> userList = UserDAO.getInstance(mContext).selectUserList() ;

        if(userList != null && userList.size() > 0){
            map.put("sign", UtilsTools.md5("jumin_"+"App.Info.Getvideoinfo")+userList.get(0).getToken());
        }else{
            map.put("sign", UtilsTools.md5("jumin_"+"App.Info.Getvideoinfo"));
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("liujw", "##########################IOException : " + e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = new String(responseStr);
                        Gson gson = new Gson();
                        VideoBean bean = gson.fromJson(message, VideoBean.class);

                        if (bean != null && bean.getData() != null && bean.getData().size() > 0) {
                            for (int i = 0; i < bean.getData().size(); i++) {
                                mVideoList.add(bean.getData().get(i));
                            }
                        }
                        mVideoAdapter.notifyVideoListChange(mVideoList);
                    }
                });
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mMainView = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        initView(mMainView);
        EventBus.getDefault().register(this);
        initData();

        return mMainView ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private LinearLayout mLLMore ;

    private RecyclerView mRVVideo ;

    private VideoAdapter mVideoAdapter ;

    private List<VideoBean.DataBean> mVideoList = new ArrayList<>();

    private ImageView mIvOne ;

    private ImageView mIvTwo ;

    private ImageView mIvThree ;

    private ImageView mIvFour ;

    private TextView mTvOne ;

    private TextView mTvTwo ;

    private TextView mTvThree ;

    private TextView mTvFour ;

    private ImageView mIvErShouFang ;

    private ImageView mIvXinFang ;

    private ImageView mIvChuZu ;

    private ImageView mIvShangpuChuzu ;

    private ImageView mIvShangpuZhuanrang ;

    private List<ImageView>  mImagePicLogo = new ArrayList<>();

    private List<ImageView> mIvCategoryList = new ArrayList<>();

    private List<TextView> mTvCategoryList = new ArrayList<>() ;

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

    private void getCategoryTitle3() {

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Category.Pushcat");
        map.put("type", 3 + "");


        List<User> userList = UserDAO.getInstance(mContext).selectUserList() ;

        if(userList != null && userList.size() > 0){
            map.put("sign", UtilsTools.md5("jumin_"+"App.Category.Pushcat")+userList.get(0).getToken());
        }else{
            map.put("sign", UtilsTools.md5("jumin_"+"App.Category.Pushcat"));
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("liujw", "##########################IOException : " + e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();

                Log.e("liujw","######################responseStr : "+responseStr);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        CategoryBean bean = gson.fromJson(responseStr, CategoryBean.class);
                        if (bean.getData() != null && bean.getData().size() > 0) {
                            for (int i = 0; i < bean.getData().size(); i++) {
                                mDataList.add(bean.getData().get(i).getCatname());
                            }

                            initMagicIndicator();
                            initMagicIndicatorTitle();
                            viewPager.setAdapter(new ComFragmentAdapter(getActivity().getSupportFragmentManager(), getFragments(bean.getData())));
                            viewPager.setOffscreenPageLimit(10);

                        }
                    }
                });
            }
        });

    }

    private void getCategoryTitle1() {

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Category.Pushcat");
        map.put("type", 1 + "");


        List<User> userList = UserDAO.getInstance(mContext).selectUserList() ;

        if(userList != null && userList.size() > 0){
            map.put("sign", UtilsTools.md5("jumin_"+"App.Category.Pushcat")+userList.get(0).getToken());
        }else{
            map.put("sign", UtilsTools.md5("jumin_"+"App.Category.Pushcat"));
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("liujw", "##########################IOException : " + e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        CategoryBean bean = gson.fromJson(responseStr, CategoryBean.class);
                        if (bean.getData() != null && bean.getData().size() > 0) {
                            for (int i = 0; i < bean.getData().size(); i++) {
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
                });
            }
        });

    }

    private void getCategoryTitle2() {


        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Category.Pushcat");
        map.put("type", 2 + "");


        List<User> userList = UserDAO.getInstance(mContext).selectUserList() ;

        if(userList != null && userList.size() > 0){
            map.put("sign", UtilsTools.md5("jumin_"+"App.Category.Pushcat")+userList.get(0).getToken());
        }else{
            map.put("sign", UtilsTools.md5("jumin_"+"App.Category.Pushcat"));
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("liujw", "##########################IOException : " + e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson() ;

                        CategoryBean bean = gson.fromJson(responseStr, CategoryBean.class);
                        if (bean.getData() != null && bean.getData().size() > 0) {
                            for (int i = 0; i < bean.getData().size(); i++) {
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
                });
            }
        });

    }

    private TextView mTvMoreVideo ;

    private void initView(View view) {

        mTvMoreVideo = view.findViewById(R.id.tv_more_video) ;

        mTvLocation = view.findViewById(R.id.tv_location) ;
        refreshLayout = view.findViewById(R.id.refreshLayout) ;
        toolbar = view.findViewById(R.id.home_toolbar) ;
        viewPager = view.findViewById(R.id.view_pager) ;
        scrollView = view.findViewById(R.id.scrollView) ;
        magicIndicator = view.findViewById(R.id.magic_indicator) ;
        magicIndicatorTitle = view.findViewById(R.id.magic_indicator_title) ;

        mRVVideo = view.findViewById(R.id.rv_video);
        mLLMore = view.findViewById(R.id.ll_more);
        mLLeft = view.findViewById(R.id.ll_left);
        mIvQianDao = view.findViewById(R.id.iv_qiandao);
        mIvZxing = view.findViewById(R.id.iv_zxing);
        mTvSearch = view.findViewById(R.id.tv_search);
        mPoints = view.findViewById(R.id.layout_points);
        mBannerViewPaper = view.findViewById(R.id.vp_banner);

        mIvOne = view.findViewById(R.id.iv_one) ;
        mIvTwo = view.findViewById(R.id.iv_two) ;
        mIvThree = view.findViewById(R.id.iv_three) ;
        mIvFour = view.findViewById(R.id.iv_four) ;


        mIvOne.setOnClickListener(this);
        mIvTwo.setOnClickListener(this);
        mIvThree.setOnClickListener(this);
        mIvFour.setOnClickListener(this);

        mTvOne = view.findViewById(R.id.tv_one) ;
        mTvTwo = view.findViewById(R.id.tv_two) ;
        mTvThree = view.findViewById(R.id.tv_three) ;
        mTvFour = view.findViewById(R.id.tv_four) ;

        mIvErShouFang = view.findViewById(R.id.iv_ershoufang) ;
        mIvXinFang = view.findViewById(R.id.iv_xinfang) ;
        mIvChuZu = view.findViewById(R.id.iv_chuzu) ;
        mIvShangpuChuzu = view.findViewById(R.id.iv_shangpuchuzu) ;
        mIvShangpuZhuanrang = view.findViewById(R.id.iv_shangpuzhuanrang) ;


        mIvErShouFang.setOnClickListener(this);
        mIvXinFang.setOnClickListener(this);
        mIvChuZu.setOnClickListener(this);
        mIvShangpuChuzu.setOnClickListener(this);
        mIvShangpuZhuanrang.setOnClickListener(this);

        mTvMoreVideo.setOnClickListener(this);

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



        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Index.Banner") ;


        List<User> userList = UserDAO.getInstance(mContext).selectUserList() ;

        if(userList != null && userList.size() > 0){
            map.put("sign", UtilsTools.md5("jumin_"+"App.Index.Banner")+userList.get(0).getToken());
        }else{
            map.put("sign", UtilsTools.md5("jumin_"+"App.Index.Banner"));
        }
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson() ;
                        BannerBean bean = gson.fromJson(responseStr,BannerBean.class) ;

                        if(bean != null && bean.getData() != null && bean.getData().size() > 0){
                            for(int i = 0 ;i < bean.getData().size() ;i++){
                                modelList.add(bean.getData().get(i)) ;
                            }
                        }
                        initViewPager() ;
                    }
                });
            }
        });

        getCategoryTitle1() ;
        getCategoryTitle2();

        getCategoryTitle3();


        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });

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
            int color = ContextCompat.getColor(getActivity(), R.color.mainWhite) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                magicIndicator.getLocationOnScreen(location);
                int yPosition = location[1];
                if (yPosition < toolBarPositionY) {
                    magicIndicatorTitle.setVisibility(View.VISIBLE);
                    scrollView.setNeedScroll(false);
                } else {
                    magicIndicatorTitle.setVisibility(View.GONE);
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

    }

    private void dealWithViewPager() {
        toolBarPositionY = toolbar.getHeight();
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getActivity()) - toolBarPositionY - magicIndicator.getHeight() + 1;
        viewPager.setLayoutParams(params);
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
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
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(getActivity(), R.color.mainBlack));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(getActivity(), R.color.mainBlack));
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
                linePagerIndicator.setColors(ContextCompat.getColor(getActivity(), R.color.mainRed));

                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initMagicIndicatorTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
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
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(getActivity(), R.color.mainBlack));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(getActivity(), R.color.mainBlack));
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
                linePagerIndicator.setColors(ContextCompat.getColor(getActivity(), R.color.mainRed));

                return linePagerIndicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, viewPager);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null ;
        switch (view.getId()){
            case R.id.tv_more_video:
                intent = new Intent(getActivity(), CategoryListActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_one :
                intent = new Intent(getActivity(), CategoryListActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_two :
                intent = new Intent(getActivity(), CategoryListActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_three :
                intent = new Intent(getActivity(), CategoryListActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_four :
                intent = new Intent(getActivity(), CategoryListActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_xinfang :
                intent = new Intent(getActivity(), MainCategoryActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_chuzu :
                intent = new Intent(getActivity(), MainCategoryActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_shangpuchuzu :
                intent = new Intent(getActivity(), MainCategoryActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_ershoufang :
                intent = new Intent(getActivity(), MainCategoryActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.iv_shangpuzhuanrang :
                intent = new Intent(getActivity(), MainCategoryActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.ll_more:
                intent = new Intent(getActivity(), MoreCategoryActivity.class) ;
                startActivity(intent);
                break ;
            case R.id.ll_left:
                intent = new Intent(getActivity(), LocationActivity.class) ;
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
