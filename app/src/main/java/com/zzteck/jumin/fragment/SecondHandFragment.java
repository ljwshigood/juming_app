package com.zzteck.jumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.FavoriteAdapter;
import com.zzteck.jumin.adapter.SecondHandAdapter;

public class SecondHandFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRlFavorite ;

    private SecondHandAdapter mFavoriteAdapter ;

    private void initView(View view) {
        mRlFavorite = view.findViewById(R.id.rl_favorite) ;
    }

    private void initData() {
        mFavoriteAdapter = new SecondHandAdapter(getActivity(),null) ;
        mRlFavorite.setAdapter(mFavoriteAdapter) ;
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
