package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MyReleaseAdapter;
import com.zzteck.jumin.ui.usercenter.MyReleaseActivity;
import com.zzteck.jumin.view.NormalDecoration;

public class ReleaseCompleteFragment extends Fragment implements OnClickListener {

	private View mMainView;

	private Context mContext;

	private MyReleaseAdapter mMyReleaseAdapter ;

	private RecyclerView mRv ;

	private void initView(View view) {
		mRv = view.findViewById(R.id.rv_release_compelte) ;
	}


	private void initData() {
		mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRv.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF0), (int) this.getResources().getDimension(R.dimen.fifteen)));
		mMyReleaseAdapter = new MyReleaseAdapter(mContext,null) ;
		mRv.setAdapter(mMyReleaseAdapter);
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
