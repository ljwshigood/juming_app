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
import com.zzteck.jumin.adapter.RecommandAdapter;
import com.zzteck.jumin.view.NormalDecoration;

public class RecommandFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRlFavorite ;

    private RecommandAdapter recommandAdapter;

    private void initView(View view) {
        mRlFavorite = view.findViewById(R.id.rl_favorite) ;
    }

    private void initData() {

        mRlFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlFavorite.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));
        recommandAdapter = new RecommandAdapter(getActivity(),null) ;
        mRlFavorite.setAdapter(recommandAdapter) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_recommand, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();
        return mMainView;
    }


}
