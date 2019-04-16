package com.zzteck.jumin.ui.business;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.CategoryPagerAdapter;
import com.zzteck.jumin.adapter.FeaturedPagerAdapter;
import com.zzteck.jumin.bean.BannerBean;
import com.zzteck.jumin.bean.CatoryDetailInfo;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;

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

public class CategoryDetailActivity extends BaseActivity implements View.OnClickListener{

    private TextView mTvTitle ;

    private RelativeLayout mRlBack;

    ///////////////////// 轮播图
    private ViewPager mBannerViewPaper ;

    private int mPlayTimeInterval = 2500 ;

    private LinearLayout mPoints ;

    private List<String> modelList = new ArrayList<>() ;

    private void initViewPager() {
        mPlayTimeInterval = (int) 2.5 * 2000;
        if (modelList != null && modelList.size() > 0) {
            for (int i = 0; i < modelList.size(); i++) {
                View view = new View(mContext);
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
            CategoryPagerAdapter adapter = new CategoryPagerAdapter(mContext, modelList,0);
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

    ///////////////////

    private TextView mTvContentTitle ;

    private TextView mTvPlace ;

    private TextView mTvDes ;

    private void initView(){
        mTvDes = findViewById(R.id.tv_des) ;
        mTvPlace = findViewById(R.id.tv_place) ;
        mTvContentTitle = findViewById(R.id.tv_title) ;
        mPoints = findViewById(R.id.layout_points);
        mBannerViewPaper = findViewById(R.id.vp_banner);

        mTvTitle = findViewById(R.id.tv_main_info) ;
        mRlBack = findViewById(R.id.ll_back) ;
        mRlBack.setOnClickListener(this);
        mTvTitle.setText("详情");
        mRlBack.setVisibility(View.VISIBLE);
    }

    private void getCatoryDetail(String id){

        if(TextUtils.isEmpty(id)){
            return ;
        }

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Info.Show") ;
        map.put("id",id) ;

        map.put("sign", UtilsTools.getSign(mContext,"App.Info.Show")) ;

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
                        CatoryDetailInfo bean = gson.fromJson(message,CatoryDetailInfo.class) ;
                        modelList.add(bean.getData().getImg_path()) ;
                        initViewPager() ;
                        setDataView(bean) ;
                    }
                });
            }
        });


    }

    private String mId ;

    private void initData(){
        mId = getIntent().getStringExtra("id") ;
    }

    private void setDataView(CatoryDetailInfo bean){
        mTvContentTitle.setText(bean.getData().getTitle());
        mTvPlace.setText(bean.getData().getAreaname());
        mTvDes.setText(bean.getData().getContent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView(R.layout.activity_category_detail);
        initView() ;
        initData();
        getCatoryDetail(mId) ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back :
                finish();
                break ;
        }
    }
}
