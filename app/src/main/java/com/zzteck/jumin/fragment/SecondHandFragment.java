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
import com.zzteck.jumin.adapter.SecondHandAdapter;
import com.zzteck.jumin.view.NormalDecoration;

public class SecondHandFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRVSecond;

    private SecondHandAdapter mSecondAdapter;

    private void initView(View view) {
        mRVSecond = view.findViewById(R.id.rl_second_hand) ;
    }

    private void initData() {
        mRVSecond.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRVSecond.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));
        mSecondAdapter = new SecondHandAdapter(getActivity(),null) ;
        mRVSecond.setAdapter(mSecondAdapter) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_second_hand, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();
        return mMainView;
    }

}
