package com.iasii.app.citylist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.iasii.app.citylist.R;
import com.iasii.app.citylist.entity.City;
import com.iasii.app.citylist.entity.CityCompentBean;

import java.util.List;

/**
 * Created by next on 2016/3/25.
 */
public class HotCityAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<CityCompentBean.DataBeanX.HotcityBean> hotCities;

    public IOnItemClickListener getmOnItemClick() {
        return mOnItemClick;
    }

    public void setmOnItemClick(IOnItemClickListener mOnItemClick) {
        this.mOnItemClick = mOnItemClick;
    }

    private IOnItemClickListener mOnItemClick ;

    public interface IOnItemClickListener{

        public void onItemClick(CityCompentBean.DataBeanX.HotcityBean bean) ;

    }

    public HotCityAdapter(Context context, List<CityCompentBean.DataBeanX.HotcityBean> hotCities) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.hotCities = hotCities;
    }

    @Override
    public int getCount() {
        return hotCities.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_city_cell, null);
        TextView city = (TextView) convertView.findViewById(R.id.city);
        city.setText(hotCities.get(position).getCityname());

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClick != null){
                    mOnItemClick.onItemClick(hotCities.get(position));
                }
            }
        });

        return convertView;
    }
}
