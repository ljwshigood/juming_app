package com.zzteck.jumin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.iasii.app.citylist.R;
import com.iasii.app.citylist.adapter.HotCityAdapter;
import com.iasii.app.citylist.entity.CityCompentBean;
import com.iasii.app.citylist.entity.EventCity;

import org.simple.eventbus.EventBus;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by next on 2016/3/25.
 */
public class CityListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<CityCompentBean.DataBeanX.DataBean> allCities;

    public List<CityCompentBean.DataBeanX.HotcityBean> getHotCities() {
        return hotCities;
    }

    public void setHotCities(List<CityCompentBean.DataBeanX.HotcityBean> hotCities) {
        this.hotCities = hotCities;
    }

    private List<CityCompentBean.DataBeanX.HotcityBean> hotCities;
    private List<String> historyCities;
    private String[] firstLetterArray;// 存放存在的汉语拼音首字母
    private Map<String, Integer> letterIndex;
    private final int VIEW_TYPE = 5;

    private String mCity = "正在定位..." ;

    public void notifyLocationCity(String city){
        this.mCity = city ;
        notifyDataSetChanged();
    }

    public CityListAdapter(Context context, List<CityCompentBean.DataBeanX.DataBean> allCities, List<CityCompentBean.DataBeanX.HotcityBean> hotCities, List<String> historyCities, Map<String, Integer> letterIndex) {
        this.context = context;
        this.allCities = allCities;
        this.hotCities = hotCities;
        this.historyCities = historyCities;
        this.letterIndex = letterIndex;
        inflater = LayoutInflater.from(this.context);

        setup();
    }

    private void setup() {
        firstLetterArray = new String[allCities.size()];
        for (int i = 0; i < allCities.size(); i++) {
            // 当前汉语拼音首字母
            String currentStr = getAlpha(allCities.get(i).getCitypy());
            // 上一个汉语拼音首字母，如果不存在为" "
            String previewStr = (i - 1) >= 0 ? getAlpha(allCities.get(i - 1).getCitypy()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(allCities.get(i).getCitypy());
                letterIndex.put(name, i);
                firstLetterArray[i] = name;
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 3 ? position : 3;
    }

    @Override
    public int getCount() {
        return allCities == null ? 0 : allCities.size();
    }

    @Override
    public Object getItem(int position) {
        return allCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        if (viewType == 0) {//定位
            LocationHolder holder ;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_city_location, null);
                holder = new LocationHolder();
                holder.mTvLocation =  convertView.findViewById(R.id.tv_location);
                convertView.setTag(holder);
            }else{
                holder = (LocationHolder) convertView.getTag();
            }

            holder.mTvLocation.setText(mCity);

        } else if (viewType == 1) {

            convertView = inflater.inflate(R.layout.item_city_grid, null);
            final GridView hotCity =  convertView.findViewById(R.id.grid_city);
            HotCityAdapter adapter = new HotCityAdapter(context, this.hotCities) ;
            hotCity.setAdapter(adapter);
            adapter.setmOnItemClick(new HotCityAdapter.IOnItemClickListener() {
                @Override
                public void onItemClick(CityCompentBean.DataBeanX.HotcityBean bean) {
                    EventCity event = new EventCity(bean.getCityname(),"") ;
                    event.setId(bean.getCityid());
                    EventBus.getDefault().post(event);
                }
            });
            TextView hotHint =  convertView.findViewById(R.id.recentHint);
            hotHint.setText("热门城市");

        } else if (viewType == 2) {
            convertView = inflater.inflate(R.layout.item_city_total_tag, null);
        }else {
            Holder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_city_list, null);
                holder = new Holder();
                holder.letter =  convertView.findViewById(R.id.tv_letter);
                holder.name =  convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            if (position >= 1) {
                holder.name.setText(allCities.get(position).getCityname());
                String currentStr = getAlpha(allCities.get(position).getCitypy());
                String previewStr = (position - 1) >= 0 ? getAlpha(allCities.get(position - 1).getCitypy()) : " ";
                if (!previewStr.equals(currentStr)) {
                    holder.letter.setVisibility(View.VISIBLE);
                    holder.letter.setText(currentStr);
                } else {
                    holder.letter.setVisibility(View.GONE);
                }
            }
        }

        return convertView;
    }

    class LocationHolder{
        TextView mTvLocation ;
    }

    class Holder {
        TextView letter, name;
    }


    // 获得汉语拼音首字母
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals("0")) {
            return "定位";
        } else if (str.equals("1")) {
            return "最近";
        } else if (str.equals("2")) {
            return "热门";
        } else if (str.equals("3")) {
            return "全部";
        } else {
            return "#";
        }
    }
}
