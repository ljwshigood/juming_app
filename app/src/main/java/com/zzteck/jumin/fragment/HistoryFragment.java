package com.zzteck.jumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zzteck.jumin.R;

public class HistoryFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private void initView(View view) {

    }

    private void initData() {

    }


    private LinearLayout mLLTypeTitle;

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
