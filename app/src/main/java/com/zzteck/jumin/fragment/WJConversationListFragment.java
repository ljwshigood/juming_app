package com.zzteck.jumin.fragment;

//import io.rong.imkit.RongContext;
//import io.rong.imkit.fragment.ConversationListFragment;
//import io.rong.imlib.model.Conversation;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzteck.jumin.R;


public class WJConversationListFragment extends Fragment {
	
	private Context mContext ;
	
	private TextView mTvMainInfo ;
	
	private ImageView mIvBack ;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		mContext = getActivity() ;
		View convertionList = inflater.inflate(R.layout.conversation_list,container, false);
		mTvMainInfo = convertionList.findViewById(R.id.tv_main_info) ;
		mIvBack  = convertionList.findViewById(R.id.iv_back) ;
		mIvBack.setVisibility(View.INVISIBLE);
		mTvMainInfo.setText("消息");
		return convertionList;
	}
	
	
    private void enterFragment() {
    	
    }
	

}
