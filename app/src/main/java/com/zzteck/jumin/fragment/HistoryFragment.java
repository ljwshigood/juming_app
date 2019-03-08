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
import com.zzteck.jumin.adapter.HistoryAdapter;
import com.zzteck.jumin.view.NormalDecoration;

public class HistoryFragment extends Fragment {

    private View mMainView;

    private Context mContext;


    private void initView(View view) {

    }

    private void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_history, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();
        return mMainView;
    }

    public void refreshTask() {

    }


}
