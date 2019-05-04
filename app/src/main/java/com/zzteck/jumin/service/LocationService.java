package com.zzteck.jumin.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.iasii.app.citylist.entity.CityCompentBean;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.SharePerfenceUtil;
import com.zzteck.jumin.utils.UtilsTools;

import org.simple.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/2/28 0028.
 */

public class LocationService extends Service {

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();


    /**
     * 初始化定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void initLocation(){
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {

            if (null != loc) {

                EventBus.getDefault().post(loc.getCity());
                App.getInstance().locationCity  = loc.getCity() ;

                String city = loc.getCity().substring(0,loc.getCity().length() - 1) ;

                for(int i= 0 ;i < citiesData.size() ;i++){
                    if(city.equals(citiesData.get(i).getCityname())){
                        SharePerfenceUtil.setParam(mContext,"city_id",citiesData.get(i).getCityid());
                        break ;
                    }
                }

            }
        }
    };

    // 根据控件的选择，重新设置定位参数
    private void resetOption() {
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(false);
        // 设置是否开启缓存
        locationOption.setLocationCacheEnable(false);
        // 设置是否单次定位
        locationOption.setOnceLocation(false);
        //设置是否等待设备wifi刷新，如果设置为true,会自动变为单次定位，持续定位时不要使用
        locationOption.setOnceLocationLatest(false);
        //设置是否使用传感器
        locationOption.setSensorEnable(false);
        //设置是否开启wifi扫描，如果设置为false时同时会停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        String strInterval = 2000+"";
        if (!TextUtils.isEmpty(strInterval)) {
            try{
                // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
                locationOption.setInterval(Long.valueOf(strInterval));
            }catch(Throwable e){
                e.printStackTrace();
            }
        }

        String strTimeout = 30000+"";
        if(!TextUtils.isEmpty(strTimeout)){
            try{
                // 设置网络请求超时时间
                locationOption.setHttpTimeOut(Long.valueOf(strTimeout));
            }catch(Throwable e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 开始定位
     *
     */
    private void startLocation(){
        //根据控件的选择，重新设置定位参数
        resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     *
     */
    private void stopLocation(){
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     *
     */
    private void destroyLocation(){
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /**
     * 默认的定位参数
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Context mContext ;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("liujw","#############################AMapLocationClientOption oncreate");
        Log.e("liujw","#############################AMapLocationClientOption oncreate");
        Log.e("liujw","#############################AMapLocationClientOption oncreate");
        Log.e("liujw","#############################AMapLocationClientOption oncreate");

        mContext = this ;
        initLocation();
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        getCityist() ;

    }

    private List<CityCompentBean.DataBeanX.DataBean> citiesData;

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
                String message = new String(responseStr);
                Gson gson = new Gson();
                CityCompentBean bean  = gson.fromJson(message,CityCompentBean.class) ;
                citiesData = getCityList(bean);

                startLocation();
            }
        });
    }


    private ArrayList<CityCompentBean.DataBeanX.DataBean> getCityList(CityCompentBean bean) {
        ArrayList<CityCompentBean.DataBeanX.DataBean> list = new ArrayList<>();
        if(bean != null && bean.getData() != null && bean.getData().getData() != null){
            for(int i = 0 ;i < bean.getData().getData().size() ;i++){
                list.add(bean.getData().getData().get(i)) ;
            }
        }

        Collections.sort(list, comparator);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }
}
