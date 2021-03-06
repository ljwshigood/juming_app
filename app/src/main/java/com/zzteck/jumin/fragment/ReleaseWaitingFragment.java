package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.usercenter.MyReleaseActivity;

public class ReleaseWaitingFragment extends Fragment implements OnClickListener {

	private View mMainView;

	private Context mContext;


	private void initView(View view) {

	}


	private void initData() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mMainView = inflater.inflate(R.layout.fragment_release_complete, container, false);
		mContext = getActivity();
		initView(mMainView);
		initData();
		return mMainView;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null ;
		switch (v.getId()) {
		case R.id.rl_my_fav:
			break;

		case R.id.rl_my_release:
			intent = new Intent(getActivity(), MyReleaseActivity.class) ;
			startActivity(intent);

			break;
		case R.id.rl_contact_us:
			break;
		case R.id.rl_my_spread:

			break;
		case R.id.rl_ruiyuanshengji:
			break;
		case R.id.rl_identity:
			break;
		default:
			break;
		}
	}

}
