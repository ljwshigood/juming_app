package com.zzteck.jumin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.FilterBean;

import java.util.List;


public class SubFilterAdapter extends BaseAdapter implements OnItemClickListener{

	Context mContext;
	
	List<FilterBean> filterBean ;
	
	private ISubFilterOnItemClick onSubFilterItemClick ;

	public SubFilterAdapter(Context context,ISubFilterOnItemClick onSubFilterItemClick) {
		mContext = context;
		this.onSubFilterItemClick = onSubFilterItemClick ;
	}

	public void setDatas(List<FilterBean> filterList) {
		this.filterBean = filterList ;
	}
	
	@Override
	public int getCount() {
		
		return filterBean == null ? 0 : filterBean.size();
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
		
		FilterBean bean = filterBean.get(position);
		
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_filter_sub, null);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_release_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv.setText(bean.getDetail());

		return convertView;
	}

	static class ViewHolder {
		TextView tv;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		FilterBean bean = filterBean.get(position);
		onSubFilterItemClick.onSubFilterItemClick(bean);
	}
	
	public interface ISubFilterOnItemClick{
		public void onSubFilterItemClick(FilterBean bean);
	}

}
