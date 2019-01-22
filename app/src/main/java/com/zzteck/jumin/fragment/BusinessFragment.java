package com.zzteck.jumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.BusinessAdapter;

public class BusinessFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRlBusiness;

    private BusinessAdapter mBusinessAdapter;

    private void initView(View view) {
        mRlBusiness = view.findViewById(R.id.rl_business) ;
    }

    private void initData() {
        mBusinessAdapter = new BusinessAdapter(getActivity(),null) ;
        mRlBusiness.setAdapter(mBusinessAdapter) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_business, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();
        return mMainView;
    }

}
