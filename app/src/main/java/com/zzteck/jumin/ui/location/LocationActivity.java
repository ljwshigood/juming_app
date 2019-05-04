package com.zzteck.jumin.ui.location;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iasii.app.citylist.db.DatabaseHelper;
import com.iasii.app.citylist.entity.CityCompentBean;
import com.iasii.app.citylist.entity.EventCity;
import com.iasii.app.citylist.utils.DensityUtil;
import com.iasii.app.citylist.utils.PingYinUtil;
import com.iasii.app.citylist.view.LetterListView;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.CityListAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.SharePerfenceUtil;
import com.zzteck.jumin.utils.UtilsTools;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LocationActivity extends BaseActivity implements LetterListView.OnTouchingLetterChangedListener, AbsListView.OnScrollListener ,View.OnClickListener{

    private ListView city_container;
    private LetterListView letter_container;

    private List<CityCompentBean.DataBeanX.DataBean> allCities = new ArrayList<>();
    private List<CityCompentBean.DataBeanX.HotcityBean> hotCities = new ArrayList<>();
    private List<String> historyCities = new ArrayList<>();
    private List<CityCompentBean.DataBeanX.DataBean> citiesData;
    private Map<String, Integer> letterIndex = new HashMap<>();
    private CityListAdapter cityListAdapter;


    private TextView letterOverlay; // 对话框首字母textview
    private OverlayThread overlayThread; // 显示首字母对话框
    private DatabaseHelper databaseHelper;

    private boolean isScroll;
    private boolean isOverlayReady;
    private Handler handler;

    private ImageView mIvBack ;

    private void initView(){
        mIvBack = findViewById(R.id.iv_back) ;
        mIvBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        city_container = findViewById(R.id.city_container);
        letter_container = findViewById(R.id.letter_container);

        databaseHelper = new DatabaseHelper(this);
        handler = new Handler();
        initView() ;
        initCity();
        //initHotCity();
       // initHistoryCity();
        setupView();
        initOverlay();

        getCityist() ;

        EventBus.getDefault().register(this);


        if(cityListAdapter != null && !TextUtils.isEmpty(App.getInstance().locationCity)){
            cityListAdapter.notifyLocationCity(App.getInstance().locationCity);
        }

    }

    private void getCityist() {

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.City.Index");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("liujw", "##########################IOException : " + e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = new String(responseStr);
                        Gson gson = new Gson();
                        CityCompentBean bean  = gson.fromJson(message,CityCompentBean.class) ;
                        citiesData = getCityList(bean);
                        allCities.addAll(citiesData);
                        cityListAdapter.setHotCities(hotCities);

                        cityListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }


    @Subscriber
    public void onEventMainThread(String event) {
       /*if(cityListAdapter != null){
           cityListAdapter.notifyLocationCity(event);
       }*/
    }

    @Subscriber
    public void onEventMainThread(EventCity event) {
        SharePerfenceUtil.setParam(mContext,"city_id",event.getId()+"");
        SharePerfenceUtil.setParam(mContext,"city_name",event.getName()) ;
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initCity() {
        CityCompentBean.DataBeanX.DataBean cityLocation = new CityCompentBean.DataBeanX.DataBean(); // 当前定位城市
        cityLocation.setCityname("定位");
        cityLocation.setCityid("0");
        allCities.add(cityLocation);

        CityCompentBean.DataBeanX.DataBean cityHot = new CityCompentBean.DataBeanX.DataBean();
        cityHot.setCityname("热门");
        cityHot.setCityid("2");

        allCities.add(cityHot);

        CityCompentBean.DataBeanX.DataBean cityALL = new CityCompentBean.DataBeanX.DataBean();
        cityALL.setCityname("全部");
        cityALL.setCityid("3");

        allCities.add(cityALL);

    }

    /**
     * 热门城市
     */
    public void initHotCity() {


       /* CityCompentBean.DataBeanX.DataBean city = new CityCompentBean.DataBeanX.DataBean("北京", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("上海", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("广州", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("深圳", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("武汉", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("天津", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("西安", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("南京", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("杭州", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("成都", "2");
        hotCities.add(city);
        city = new CityCompentBean.DataBeanX.DataBean("重庆", "2");
        hotCities.add(city);*/


    }

    private void initHistoryCity() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recent_city order by date desc limit 0, 3", null);
        while (cursor.moveToNext()) {
            historyCities.add(cursor.getString(1));
        }
        cursor.close();
        db.close();
    }

    public void addHistoryCity(String name) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recent_city where name = '" + name + "'", null);
        if (cursor.getCount() > 0) {
            db.delete("recent_city", "name = ?", new String[]{name});
        }
        db.execSQL("insert into recent_city(name, date) values('" + name + "', " + System.currentTimeMillis() + ")");
        db.close();
    }


    private ArrayList<CityCompentBean.DataBeanX.DataBean> getCityList(CityCompentBean bean) {
        ArrayList<CityCompentBean.DataBeanX.DataBean> list = new ArrayList<>();
        /*ArrayList<City> list = new ArrayList<>();

        Cursor cursor = DatabaseManager.getInstance(this).getmSQLiteDatabase().rawQuery("select * from city", null);
        City city;
        while (cursor.moveToNext()) {
            city = new City(cursor.getString(3), cursor.getString(1));
            list.add(city);
        }
        cursor.close();*/

        if(bean != null && bean.getData() != null && bean.getData().getData() != null){
            for(int i = 0 ;i < bean.getData().getData().size() ;i++){
                list.add(bean.getData().getData().get(i)) ;
            }
        }

        Collections.sort(list, comparator);

        hotCities = bean.getData().getHotcity() ;


        return list;
    }


    /**
     * a-z排序
     */
    Comparator comparator = new Comparator<CityCompentBean.DataBeanX.DataBean>() {
        @Override
        public int compare(CityCompentBean.DataBeanX.DataBean lhs, CityCompentBean.DataBeanX.DataBean rhs) {
            String a = lhs.getCitypy().substring(0, 1);
            String b = rhs.getCitypy().substring(0, 1);
            int flag = a.compareTo(b);
            if (flag == 0) {
                return a.compareTo(b);
            } else {
                return flag;
            }
        }
    };

    private void setupView() {
        city_container.setOnScrollListener(this);
        letter_container.setOnTouchingLetterChangedListener(this);

        cityListAdapter = new CityListAdapter(this, allCities, hotCities, historyCities, letterIndex);
        city_container.setAdapter(cityListAdapter);


        city_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                CityCompentBean.DataBeanX.DataBean bean = (CityCompentBean.DataBeanX.DataBean) cityListAdapter.getItem(position);

                App.getInstance().isSelectCity = true ;
                SharePerfenceUtil.setParam(mContext,"city_name",bean.getCityname()) ;
                SharePerfenceUtil.setParam(mContext,"city_id",bean.getCityid()+"");
                finish();

            }
        });


    }


    // 初始化汉语拼音首字母弹出提示框
    private void initOverlay() {
        overlayThread = new OverlayThread();
        isOverlayReady = true;
        LayoutInflater inflater = LayoutInflater.from(this);
        letterOverlay = (TextView) inflater.inflate(R.layout.v_letter_overlay, null);
        letterOverlay.setVisibility(View.INVISIBLE);

        int width = DensityUtil.dp2px(this, 65);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                width, width,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(letterOverlay, lp);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL || scrollState == SCROLL_STATE_FLING) {
            isScroll = true;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!isScroll) {
            return;
        }
        if (isOverlayReady) {
            String text;
            String name = allCities.get(firstVisibleItem).getCityname();
            String pinyin = allCities.get(firstVisibleItem).getCitypy();
            if (firstVisibleItem < 4) {
                text = name;
            } else {
                text = PingYinUtil.converterToFirstSpell(pinyin).substring(0, 1).toUpperCase();
            }
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            if (pattern.matcher(text).matches()) {
                letterOverlay.setTextSize(40);
            } else {
                letterOverlay.setTextSize(20);
            }
            letterOverlay.setText(text);
            letterOverlay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            // 延迟一秒后执行，让overlay为不可见
            handler.postDelayed(overlayThread, 1000);
        }
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        isScroll = false;
        if (letterIndex.get(s) != null) {
            int position = letterIndex.get(s);
            city_container.setSelection(position);
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            if (pattern.matcher(s).matches()) {
                letterOverlay.setTextSize(40);
            } else {
                letterOverlay.setTextSize(20);
            }
            letterOverlay.setText(s);
            letterOverlay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            handler.postDelayed(overlayThread, 1000);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back :
                finish();
                break ;
        }
    }

    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            letterOverlay.setVisibility(View.GONE);
        }
    }
}
