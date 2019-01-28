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
import android.widget.ImageView;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.HistoryAdapter;
import com.zzteck.jumin.adapter.MessageAdapter;
import com.zzteck.jumin.view.NormalDecoration;


public class WJConversationListFragment extends Fragment {
	
	private Context mContext ;
	
	private TextView mTvMainInfo ;

	private RecyclerView mRVMessage ;

	private MessageAdapter mMessageAdapter ;

	private void initData(){

		mRVMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRVMessage.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));
		mMessageAdapter = new MessageAdapter(getActivity(),null) ;
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
		return convertionList;
	}
	
}
