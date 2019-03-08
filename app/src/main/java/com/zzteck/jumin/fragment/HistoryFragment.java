package com.zzteck.jumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.HistoryAdapter;
import com.zzteck.jumin.adapter.HomeCategoryAdapter;
import com.zzteck.jumin.adapter.MessageAdapter;
import com.zzteck.jumin.bean.VideoBean;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class HistoryFragment extends Fragment implements View.OnClickListener{

    private String[] titles = {"常用分类","潮流女装","品牌男装","内衣配饰","家用电器","手机数码","电脑办公","个护化妆","母婴频道","食物生鲜","酒水饮料","家居家纺","整车车品","鞋靴箱包","运动户外","图书","玩具乐器","钟表","居家生活","珠宝饰品","音像制品","家具建材","计生情趣","营养保健","奢侈礼品","生活服务","旅游出行"};

    private RecyclerView mScrollView;

    private FrameLayout mFrameLayout;

    //装装ScrollView的item的TextView的数组
    private TextView[] textViewArray;

    //装ScrollView的item的数组
    private View[] views;

    private View mMainView;

    private Context mContext;

    private void getCategoryList(){

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Category.Lists");
        map.put("catid", "0");


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
                    }
                });
            }
        });
    }


   /* *//**
     * 给ScrollView添加子View
     *//*
    private void addView(View view) {
        LinearLayout mLinearLayout = view.findViewById(R.id.linearlayout);

        View tmpView;
        for(int x = 0 ; x < titles.length ; x++){
            tmpView = View.inflate(getActivity(), R.layout.item_scrollview, null);
            tmpView.setId(x);
            tmpView.setOnClickListener(this);
            TextView tv = tmpView.findViewById(R.id.textview);
            tv.setText(titles[x]);

            mLinearLayout.addView(tmpView);

            textViewArray[x] = tv;
            views[x] = view;

        }
    }*/

    /**
     * 改变textView的颜色
     * @param id
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < textViewArray.length; i++) {
            if(i!=id){
               /* textViewArray[i].setBackgroundResource(android.R.color.transparent);
                textViewArray[i].setTextColor(0xff000000);*/
            }else {
                /*textViewArray[id].setBackgroundResource(android.R.color.white);
                textViewArray[id].setTextColor(0xffff5d5e);*/
            }
        }

    }

    private HomeCategoryAdapter mHomeCategory ;

    private void initView(View view) {

        textViewArray = new TextView[titles.length];
        views = new View[titles.length];
        mScrollView = view.findViewById(R.id.scrollview);

        //addView(view);
        changeTextColor(0);

        mFrameLayout = (FrameLayout)view.findViewById(R.id.framelayout);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new CategoryFragment()).commit();
    }

    public class SmoothScrollLayoutManager extends LinearLayoutManager {

        public SmoothScrollLayoutManager(Context context) {
            super(context);
        }

        public SmoothScrollLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public SmoothScrollLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView,
                                           RecyclerView.State state, final int position) {

            LinearSmoothScroller smoothScroller =
                    new LinearSmoothScroller(recyclerView.getContext()) {
                        @Override
                        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                            return 150f / displayMetrics.densityDpi;
                        }
                    };

            smoothScroller.setTargetPosition(position);
            startSmoothScroll(smoothScroller);
        }
    }

    private SmoothScrollLayoutManager mSmoothScrollLayoutManager ;

    private void initData() {
        mSmoothScrollLayoutManager = new SmoothScrollLayoutManager(getActivity()) ;
        mScrollView.setLayoutManager(mSmoothScrollLayoutManager);
        mScrollView.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));

        mHomeCategory = new HomeCategoryAdapter(getActivity(),titles) ;

        mScrollView.setAdapter(mHomeCategory);

        mHomeCategory.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {

                int first = mSmoothScrollLayoutManager.findFirstVisibleItemPosition() ;
                int last = mSmoothScrollLayoutManager.findLastVisibleItemPosition() ;

                if(mScrollView.getChildLayoutPosition(view) == first){

                    mScrollView.smoothScrollToPosition(mScrollView.getChildLayoutPosition(view) - 1);

                }else if(last == mScrollView.getChildLayoutPosition(view)){

                    mScrollView.smoothScrollToPosition(mScrollView.getChildLayoutPosition(view)+1);

                }

            }

            @Override
            public void onItemLongClick(View view) {

            }

            @Override
            public void onTouchListener(int x, int y) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        changeTextColor((int) v.getId());
        changeTextLocation((int) v.getId());

        Fragment fragment=null;

        switch (v.getId()){
            case 0:
                fragment=new CategoryFragment();
                break;
        }

        if(fragment != null){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
        }
    }

    /**
     * 改变栏目位置
     */
    private void changeTextLocation(int index) {
        // int x = (views[index].getTop() - mScrollView.getHeight() / 2);
        mScrollView.smoothScrollToPosition(index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_history, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();
        return mMainView;
    }


}

