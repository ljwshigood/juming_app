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
import com.zzteck.jumin.adapter.BusinessAdapter;
import com.zzteck.jumin.view.NormalDecoration;

public class BusinessFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRVBusiness;

    private BusinessAdapter mBusinessAdapter;

    private void initView(View view) {
        mRVBusiness = view.findViewById(R.id.rv_business) ;
    }

    private void initData() {

        mRVBusiness.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRVBusiness.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));
        mBusinessAdapter = new BusinessAdapter(getActivity(),null) ;
        mRVBusiness.setAdapter(mBusinessAdapter) ;

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
