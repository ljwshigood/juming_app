package com.zzteck.zzview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class WJSpandGridView extends GridView {

	public WJSpandGridView(Context context) {
		super(context);
	}
	public WJSpandGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public WJSpandGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
