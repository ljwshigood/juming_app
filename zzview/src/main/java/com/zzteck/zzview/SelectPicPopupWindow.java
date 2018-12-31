package com.zzteck.zzview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


public class SelectPicPopupWindow extends PopupWindow {

	private LinearLayout mLlCapture ;
	
	private LinearLayout mLlCancel ;
	
	private View mMenuView;
	
	private TextView mTvCapture ;
	
	private TextView mTvGallery ;
	
	public void setPopWindowsStatus(int type){
		if(type == 0){
			mLLVideo.setVisibility(View.VISIBLE);
			mLlCapture.setVisibility(View.GONE);
			
		}else if(type == 1){
			mLLVideo.setVisibility(View.VISIBLE);
			mLlCapture.setVisibility(View.VISIBLE);
			
			TextView tvCapture = (TextView)mLlCapture.findViewById(R.id.tv_capture);
			tvCapture.setText("从相册中选择");
			
			TextView tvGallery = (TextView)mLLVideo.findViewById(R.id.tv_video);
			tvGallery.setText("拍照");
		}
	}
	
	private LinearLayout mLLModifyPhoto ;
	
	private int mAction ;
	
	
	public int getmAction() {
		return mAction;
	}
	
	private LinearLayout mLLVideo ;

	private TextView mTvWeiXin ;
	
	private TextView mTvSMS ;
	
	public void setViewData(){
		mTvSMS = (TextView)mMenuView.findViewById(R.id.tv_video);
		mTvWeiXin = (TextView)mMenuView.findViewById(R.id.tv_capture);
		//mTvSMS.setText(mContext.getString(R.string.sms));
		//mTvWeiXin.setText(mContext.getString(R.string.wei_xin));
	}
	
	private Context mContext ;
	
	public SelectPicPopupWindow(Activity context,OnClickListener itemsOnClick) {
		super(context);
		
		mContext = context ;
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.item_select_media, null);
		mLLVideo = (LinearLayout)mMenuView.findViewById(R.id.ll_video);
		mLLModifyPhoto  = (LinearLayout)mMenuView.findViewById(R.id.ll_modify_photo);
		mLlCapture = (LinearLayout) mMenuView.findViewById(R.id.ll_capture);
		mLlCancel = (LinearLayout) mMenuView.findViewById(R.id.ll_cancel);
		mLlCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dismiss();
			}
		});
		mLlCapture.setOnClickListener(itemsOnClick);
		mLLVideo.setOnClickListener(itemsOnClick);
		mLLModifyPhoto.setOnClickListener(itemsOnClick);
		
		this.setContentView(mMenuView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.AnimBottom);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		});

	}

}
