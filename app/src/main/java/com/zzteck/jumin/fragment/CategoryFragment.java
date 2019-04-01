package com.zzteck.jumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.CategoryListAdapter;
import com.zzteck.jumin.bean.ChildCategoryBean;
import com.zzteck.jumin.bean.MainCategoryBean;
import com.zzteck.jumin.bean.User;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

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

public class CategoryFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRlCate;

    private CategoryListAdapter mGategoryListAdapter;

    private void initView(View view) {
        mRlCate = view.findViewById(R.id.rl_cate) ;
    }

    private void initData() {
        mRlCate.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mGategoryListAdapter = new CategoryListAdapter(getActivity(),null) ;
        mRlCate.setAdapter(mGategoryListAdapter) ;
    }

    private List<MainCategoryBean.DataBean.ChildrenBean> mCategoryList ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_category, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCategoryList = (List<MainCategoryBean.DataBean.ChildrenBean>) bundle.getSerializable("item");
            mGategoryListAdapter.notifyCategoryList(mCategoryList);

        }
        return mMainView;
    }

}
