package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.MyReleaseActivity;

public class ReleaseVideoFragment extends Fragment implements OnClickListener {

	private View mMainView;

	private Context mContext;


	private void initView(View view) {

	}


	private void initData() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mMainView = inflater.inflate(R.layout.fragment_release_video, container, false);
		mContext = getActivity();
		initView(mMainView);
		initData();
		return mMainView;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null ;
		switch (v.getId()) {

		default:
			break;
		}
	}

}