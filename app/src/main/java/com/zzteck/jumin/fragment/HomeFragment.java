package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.HomeAdapter;
import com.zzteck.jumin.bean.HomeBean;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/7/6.
 * Describe:
 */

public class HomeFragment extends BaseFragment {
    private LinearLayout mToolbar;
    private LRecyclerView mRecyclerView;
    private List<HomeBean> data;
    private int height;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;

    @Override
    public int getLayoutId() {
        return R.layout.app_fragment_home;
    }


    @Override
    public void initView(View mView) {
        mToolbar = mView.findViewById(R.id.toolbar);
        mRecyclerView = mView.findViewById(R.id.rv_home);
    }

    private List<HomeBean> mHomeList = new ArrayList<>() ;

    @Override
    public void lazyLoad() {

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(gridLayoutManager);

        String jsonData = new String(getAssertsFile(getContext(), "content.json"));

        data = new Gson().fromJson(jsonData, new TypeToken<List<HomeBean>>() {}.getType());

        for(int i = 0 ;i < 3;i++){
            HomeBean bean = new HomeBean() ;
            bean.setType(i);
            mHomeList.add(bean) ;
        }

        HomeAdapter adapter = new HomeAdapter(getActivity(),mHomeList);


        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter) ;

        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        mLRecyclerViewAdapter.addHeaderView(getHeaderView(mRecyclerView));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy <= height) {
                    float alpha = (float) totalDy / height;
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(getContext(), R.color.white), alpha));
                } else {
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(getContext(), R.color.white), 1));
                }
            }
        });

        mRecyclerView.setNestedScrollingEnabled(false);
    }

    private View getHeaderView(RecyclerView v) {
        List<String> bannerImg = new ArrayList<>();
        bannerImg.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1546918492&di=062431ef3056895f59346be28583583f&src=http://b.hiphotos.baidu.com/zhidao/pic/item/14ce36d3d539b600d3924a1feb50352ac65cb73e.jpg");
        bannerImg.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1546918492&di=062431ef3056895f59346be28583583f&src=http://b.hiphotos.baidu.com/zhidao/pic/item/14ce36d3d539b600d3924a1feb50352ac65cb73e.jpg");
        bannerImg.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1546918492&di=062431ef3056895f59346be28583583f&src=http://b.hiphotos.baidu.com/zhidao/pic/item/14ce36d3d539b600d3924a1feb50352ac65cb73e.jpg");
        bannerImg.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1546918492&di=062431ef3056895f59346be28583583f&src=http://b.hiphotos.baidu.com/zhidao/pic/item/14ce36d3d539b600d3924a1feb50352ac65cb73e.jpg");

        View convertView = LayoutInflater
                .from(getContext())
                .inflate(R.layout.app_include_home_header, (ViewGroup) v.getParent(), false);

        Banner mBanner = convertView.findViewById(R.id.app_home_header_banner);

        mBanner.setImages(bannerImg)
                .setDelayTime(3000)
                .start();

        ViewGroup.LayoutParams bannerParams = mBanner.getLayoutParams();
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = getContext().getResources().getDimensionPixelSize(resourceId);
        height = bannerParams.height - statusBarHeight - 104;

        return convertView;
    }


    public static byte[] getAssertsFile(Context context, String fileName) {
        InputStream inputStream = null;
        AssetManager assetManager = context.getAssets();
        try {
            inputStream = assetManager.open(fileName);
            if (inputStream == null) {
                return null;
            }

            BufferedInputStream bis = null;
            int length;
            try {
                bis = new BufferedInputStream(inputStream);
                length = bis.available();
                byte[] data = new byte[length];
                bis.read(data);

                return data;
            } catch (IOException e) {

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {

                    }
                }
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
