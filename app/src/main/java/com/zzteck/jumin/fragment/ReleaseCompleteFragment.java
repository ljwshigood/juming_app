package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MyReleaseAdapter;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.MyReleaseBean;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.ui.usercenter.MyReleaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
		AppInfoMyinfos(0) ;
		return mMainView;
	}

	private void AppInfoMyinfos(int page){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Info.Myinfos") ;
		map.put("pages",page+"") ;

		map.put("sign", UtilsTools.getSign(mContext,"App.Info.Myinfos")) ;

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().get().url(Constants.HOST+"?"+ UtilsTools.getMapToString(map)).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				Log.e("liujw","##########################IOException : "+e.toString());
			}

			@Override
			public void onResponse(Call call, final Response response) throws IOException {
				final String responseStr = response.body().string();
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						String message = new String(responseStr.getBytes()) ;
						Gson gson = new Gson() ;
						MyReleaseBean bean = gson.fromJson(message,MyReleaseBean.class) ;
						mMyReleaseAdapter.notifyMyReleaseAdapter(bean.getData());
					}
				});
			}
		});


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
