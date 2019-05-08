package com.zzteck.jumin.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.VersionInfo;


public class VersionDialog extends Dialog {

	private TextView positiveButton, negativeButton;

	private TextView mTvTitle, mTvContent;

	private Context mContext;

	public IDownloadClickListener getmIDownloadClickListener() {
		return mIDownloadClickListener;
	}

	public void setmIDownloadClickListener(IDownloadClickListener mIDownloadClickListener) {
		this.mIDownloadClickListener = mIDownloadClickListener;
	}

	private IDownloadClickListener mIDownloadClickListener ;

	public interface IDownloadClickListener{

		public void downloadClick(String url,String des) ;

	}

	public VersionDialog(Context context, final VersionInfo.DataBean bean) {
		super(context, R.style.dialog_style);
		this.mContext = context;
		View view = LayoutInflater.from(getContext()).inflate(R.layout.global_dialog, null); // 通过LayoutInflater获取布局

		mTvTitle =  view.findViewById(R.id.title);
		mTvContent =  view.findViewById(R.id.content);

		mTvContent.setText("发现新版本");

		negativeButton = view.findViewById(R.id.refusebtn) ;
		positiveButton = view.findViewById(R.id.acceptbtn) ;
		setContentView(view); // 设置view

		if(Integer.valueOf(bean.getForce()) == 1){
            negativeButton.setVisibility(View.GONE);
		}else{
            negativeButton.setVisibility(View.VISIBLE);
        }

		negativeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
		positiveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
				if(mIDownloadClickListener != null){
					mIDownloadClickListener.downloadClick(bean.getAppurl(),bean.getDescription());
				}

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
