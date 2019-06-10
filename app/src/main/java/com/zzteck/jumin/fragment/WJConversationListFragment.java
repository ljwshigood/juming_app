package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.HistoryAdapter;
import com.zzteck.jumin.adapter.MessageAdapter;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.PushBean;
import com.zzteck.jumin.db.PushDAO;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.PopupWindowUtil;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WJConversationListFragment extends Fragment {
	
	private Context mContext ;
	
	private TextView mTvMainInfo ;

	private RecyclerView mRVMessage ;

	private MessageAdapter mMessageAdapter ;

	private List<PushBean> mPushList = new ArrayList<>() ;

	private PopupWindow mPopupWindow;

	private int mX ,mY ;

	private View getPopupWindowContentView(final int position) {
		int layoutId = R.layout.popup_content_layout;
		View contentView = LayoutInflater.from(getActivity()).inflate(layoutId, null);
		View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				int position = mRVMessage.getChildAdapterPosition(v) ;
			//	PushDAO.getInstance(getActivity()).deletePush(mPushList.get(position).getId());
			/*	mMessageAdapter.setMessageList(PushDAO.getInstance(mContext).selectPushList());
				if (mPopupWindow != null) {
					mPopupWindow.dismiss();
				}*/
			}
		};
		contentView.findViewById(R.id.menu_item2).setOnClickListener(menuItemOnClickListener);
		return contentView;
	}

	private void showPopupWindow(View anchorView,int position) {
		View contentView = getPopupWindowContentView(position);
		mPopupWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		mPopupWindow.setBackgroundDrawable(new ColorDrawable());
		int windowPos[] = PopupWindowUtil.calculatePopWindowPos(anchorView, contentView, mX, mY);
		mPopupWindow.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
	}
//

	public void requestMessageList(int currentPage){
		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Common.Notice") ;
		map.put("pages",currentPage+"") ;

		map.put("sign", UtilsTools.getSign(mContext,"App.Common.Notice")) ;

		OkHttpClient client = new OkHttpClient();
		//构造Request对象
		//采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
		Request request = new Request.Builder().get().url(Constants.HOST+"?"+ UtilsTools.getMapToString(map)).build();
		Call call = client.newCall(request);
		//异步调用并设置回调函数
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
						PushBean bean = gson.fromJson(message,PushBean.class) ;

						mMessageAdapter.notifyMessageList(bean.getData());

					}
				});
			}
		});
	}

	private void initData(){

		mRVMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRVMessage.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));

		mMessageAdapter = new MessageAdapter(getActivity(), null) ;

		mMessageAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view) {

			}

			@Override
			public void onItemLongClick(View view) {
			//	showPopupWindow(view,mRVMessage.getChildLayoutPosition(view)) ;
			}

			@Override
			public void onTouchListener(int x, int y) {
				mX = x ;
				mY = y ;
			}
		});

		mRVMessage.setAdapter(mMessageAdapter) ;

	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		mContext = getActivity() ;
		View convertionList = inflater.inflate(R.layout.conversation_list,container, false);
		mTvMainInfo = convertionList.findViewById(R.id.tv_main_info) ;
		mTvMainInfo.setText("消息");
		mRVMessage = convertionList.findViewById(R.id.rv_message) ;
		initData() ;
		requestMessageList(1) ;
		return convertionList;
	}
	
}
