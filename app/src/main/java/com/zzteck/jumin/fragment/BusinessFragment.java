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
import com.zzteck.jumin.adapter.FavoriteAdapter;

public class BusinessFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRlFavorite ;

    private BusinessAdapter mFavoriteAdapter ;

    private void initView(View view) {
        mRlFavorite = view.findViewById(R.id.rl_favorite) ;
    }

    private void initData() {
        mFavoriteAdapter = new BusinessAdapter(getActivity(),null) ;
        mRlFavorite.setAdapter(mFavoriteAdapter) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_business, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();
        return mMainView;
    }

    public void refreshTask() {
    }


}
