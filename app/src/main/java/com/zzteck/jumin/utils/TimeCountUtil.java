package com.zzteck.jumin.utils;

import android.os.CountDownTimer;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;


public class TimeCountUtil extends CountDownTimer {

	private LinearLayout mLLGetCode ;
	
	private TextView mTvGetCode ;

	public TimeCountUtil(long millisInFuture, long countDownInterval,LinearLayout llGetCode) {
		super(millisInFuture, countDownInterval);
		this.mLLGetCode = llGetCode ;
		mTvGetCode = (TextView)mLLGetCode.findViewById(R.id.tv_code);
	}

	@Override
	public void onTick(long millisUntilFinished) {
		mLLGetCode.setClickable(false);
		mTvGetCode.setText(millisUntilFinished / 1000 + "秒");
		//Spannable span = new SpannableString(mTvGetCode.getText().toString());
		//span.setSpan(new ForegroundColorSpan(Color.RED), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		//mTvGetCode.setText(span);
	}

	@Override
	public void onFinish() {
		mTvGetCode.setText("重新获取验证码");
		mLLGetCode.setClickable(true);
		//mLLGetCode.setBackground(mActivity.getResources().getDrawable(R.drawable.ic_code_nomal));
	}

}
