package com.zzteck.jumin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.SearchBean;

import java.util.List;

/**
 * Created by Jianwei on 2018/4/1.
 */

public class SearchAdapter extends BaseAdapter {

    private Context mContext ;

    private List<SearchBean> mSearchBeanList;

    private LayoutInflater mLayoutInflater;

    public SearchAdapter(Context context,List<SearchBean> chartBeanList){
        this.mContext = context ;
        this.mSearchBeanList = chartBeanList ;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mSearchBeanList == null ? 0 : mSearchBeanList.size();
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
        ViewHolder viewHolder = null ;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_search,null);
            viewHolder = new ViewHolder();
            viewHolder.mTvSearch =  convertView.findViewById(R.id.tv_search);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SearchBean bean = mSearchBeanList.get(position) ;
        viewHolder.mTvSearch.setText(bean.getSearch());
        return convertView;

    }

    class ViewHolder{
        TextView mTvSearch ;
    }


}
