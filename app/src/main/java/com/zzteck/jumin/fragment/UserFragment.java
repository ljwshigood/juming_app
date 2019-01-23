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

public class UserFragment extends Fragment implements OnClickListener {

	private View mMainView;

	private Context mContext;

	private RelativeLayout mRlMyRelease;

	private RelativeLayout mRlMyBill;

	private RelativeLayout mRlMyJinku;

	private RelativeLayout mRlMyAttendtion;

	private RelativeLayout mRlMyRenzhen;

	private RelativeLayout mRlRuheDingDan;

	private RelativeLayout mRlRuheFaBuXuqiu;


	private void initView(View view) {
		mRlMyRelease =  view.findViewById(R.id.rl_my_release);
		mRlMyBill =  view.findViewById(R.id.rl_my_bill);
		mRlMyJinku =  view.findViewById(R.id.rl_my_jinku);
		mRlMyAttendtion =  view.findViewById(R.id.rl_my_guanzhu);
		mRlMyRenzhen =  view.findViewById(R.id.rl_my_renzhen);
		mRlRuheDingDan =  view.findViewById(R.id.rl_ruhejiedan);
		mRlRuheFaBuXuqiu =  view.findViewById(R.id.rl_ruhe_fabuxuqiu);

		mRlMyRelease.setOnClickListener(this);
		mRlMyBill.setOnClickListener(this);
		mRlMyJinku.setOnClickListener(this);
		mRlMyAttendtion.setOnClickListener(this);
		mRlMyRenzhen.setOnClickListener(this);
		mRlRuheDingDan.setOnClickListener(this);
		mRlRuheFaBuXuqiu.setOnClickListener(this);

	}


	private void initData() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mMainView = inflater.inflate(R.layout.fragment_user, container, false);
		mContext = getActivity();
		initView(mMainView);
		initData();
		return mMainView;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null ;
		switch (v.getId()) {
		case R.id.rl_my_bill:
			break;

		case R.id.rl_my_release:
			intent = new Intent(getActivity(), MyReleaseActivity.class) ;
			startActivity(intent);

			break;
		case R.id.rl_my_jinku:
			break;
		case R.id.rl_my_guanzhu:
			break;
		case R.id.rl_my_renzhen:

			break;
		case R.id.rl_ruhejiedan:
			break;
		case R.id.rl_ruhe_fabuxuqiu:
			break;
		default:
			break;
		}
	}

}
