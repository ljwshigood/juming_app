package com.zzteck.jumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.CategoryListAdapter;
import com.zzteck.jumin.view.NormalDecoration;

public class CategoryFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRlCate;

    private CategoryListAdapter mGategoryListAdapter;

    private void initView(View view) {
        mRlCate = view.findViewById(R.id.rl_cate) ;
    }

    private void initData() {
        mRlCate.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlCate.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));
        mGategoryListAdapter = new CategoryListAdapter(getActivity(),null) ;
        mRlCate.setAdapter(mGategoryListAdapter) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_category, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();
        return mMainView;
    }

}
