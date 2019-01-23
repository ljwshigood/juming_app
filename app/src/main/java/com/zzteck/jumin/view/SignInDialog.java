package com.zzteck.jumin.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.BaseActivity;


public class SignInDialog extends Dialog implements OnClickListener{


	private Context mContext;

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
	}

	@Override
	public void onClick(View view) {

	}
}
