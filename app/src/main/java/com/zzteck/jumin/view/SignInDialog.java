package com.zzteck.jumin.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.zzteck.jumin.R;


public class SignInDialog extends Dialog{


	private Context mContext;

	private LinearLayout mLLQianDao ;

	public SignInDialog(@NonNull Context context) {
		super(context,R.style.CustomDialogStyle);
		this.mContext = context;
	}
	public SignInDialog(@NonNull Context context,String hint) {
		super(context,R.style.CustomDialogStyle);
		this.mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sign,null,false);
		setContentView(view);

		mLLQianDao= view.findViewById(R.id.ll_qiandao) ;

		mLLQianDao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
	}

}
