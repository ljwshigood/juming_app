package com.zzteck.jumin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.FilterBean;

import java.util.List;


public class MainFilterAdapter extends BaseAdapter {

	Context mContext;
	
	List<FilterBean> mFilterList;
	
	public MainFilterAdapter(Context context, List<FilterBean> str) {
		mContext = context;
		this.mFilterList = str;
	}

	@Override
	public int getCount() {
		return mFilterList == null ? 0 : mFilterList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		FilterBean filter = mFilterList.get(position) ;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_filter_main, null);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_release_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv.setText(filter.getDetail());
		
		return convertView;
	}

	static class ViewHolder {
		TextView tv;
	}
}
