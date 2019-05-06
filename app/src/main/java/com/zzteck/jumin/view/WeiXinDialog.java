package com.zzteck.jumin.view;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.usercenter.LoginActivity;


public class WeiXinDialog extends Dialog {

	private TextView positiveButton, negativeButton;
	private TextView mTvTitle, mTvContent;
	private Context mContext;

	public WeiXinDialog(final Context context, final String content) {
		super(context, R.style.dialog_style);
		this.mContext = context;
		View view = LayoutInflater.from(getContext()).inflate(R.layout.global_dialog, null); // 通过LayoutInflater获取布局

		mTvTitle =  view.findViewById(R.id.title);
		mTvContent =  view.findViewById(R.id.content);
		mTvContent.setText(content);

		negativeButton = view.findViewById(R.id.refusebtn) ;
		positiveButton = view.findViewById(R.id.acceptbtn) ;
		negativeButton.setVisibility(View.GONE);
        positiveButton.setText("复制");
		setContentView(view);

		negativeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
		positiveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				ClipboardManager myClipboard = (ClipboardManager)context.getSystemService(context.CLIPBOARD_SERVICE);

				ClipData myClip = ClipData.newPlainText("text", content);
				myClipboard.setPrimaryClip(myClip);

				dismiss();

			}
		});

	}

	// 设置内容
	public void setContent(String contentString) {
		mTvContent.setText(contentString);
	}

	// 确定按钮监听
	public void setOnPositiveListener(View.OnClickListener listener) {
		positiveButton.setOnClickListener(listener);
	}

	// 否定按钮监听
	public void setOnNegativeListener(View.OnClickListener listener) {
		negativeButton.setOnClickListener(listener);
	}
}
