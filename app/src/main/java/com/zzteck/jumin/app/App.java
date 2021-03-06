/**
 * PublcNumber
 * App.java
 * 2015-1-6-上午10:26:00
 * 2015 万家恒通公司-版权所有
 */
package com.zzteck.jumin.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.baidu.crabsdk.CrabSDK;
import com.baidu.crabsdk.OnAnrCrashListener;
import com.baidu.crabsdk.OnCrashExceedListener;
import com.mob.MobSDK;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 *
 */
public class App extends MultiDexApplication {

    private List<Activity> activityList = new LinkedList<Activity> ();

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    private static Context mContext;

    public static int mType = 0;

    public ArrayList<String> mConversationUserList = new ArrayList<String> ();

    public static Context getAppContext() {
        return mContext;
    }

    private static App mInstance;

    public boolean isSelectCity ;

    public String locationCity ;

    public App() {
        super ();
    }

    public static App getInstance() {
        if (null == mInstance) {
            mInstance = new App ();
        }
        return mInstance;
    }

    public void addActivity(Activity activity) {
        activityList.add ( activity );
    }

    public void exit() {
        for (Activity activity : activityList) {
            activity.finish ();
        }
        activityList.clear ();
        mConversationUserList.clear ();
}
    
    public void exit2() {
        for (Activity activity : activityList) {
        	if(!activity.getComponentName().equals("MainActivity")){
        		 activity.finish ();
        	}
           
        }
        activityList.clear ();
        mConversationUserList.clear ();
    }
    
    public boolean isWelcome = false ;
    
    @Override
    public void onTerminate() {
    	super.onTerminate();
    	isWelcome = false ;
    }

    public void initBaiduCrab(){
        // 在打开app初始化时回调，故此方法要再init之前调用
        // 不设置的情况下默认值是-1，即为关闭，故实现接口前必须设置CrabSDK.setConstantSameCrashExceedLimit(3);且>0时回调才有效
        CrabSDK.setConstantSameCrashExceedLimit(3);
        CrabSDK.setOnCrashExceedListener(new OnCrashExceedListener() {
            @Override
            public void onCrashExceedCallback() {
                Log.e("^^^^^^^^^^^^^^^^^", "同一crash连续发生，回调此方法！");
            }
        });

        // ------------------------------------------------------------------------------------------
        // 初始化sdk，传入appkey；appkey需要去品台申请，详情参见：https://crab.baidu.com/
        CrabSDK.init(this, "27f31ed47d2f3cd7");

        // 开启卡顿捕获功能, 传入每天上传卡顿信息个数，-1代表不限制, 已自动打开
        CrabSDK.enableBlockCatch(-1);

        // 关闭卡顿功能，默认时间
//      CrabSDK.disableBlockCatch();

        // 自定义卡顿阈值，默认是2000ms，强烈建议阈值不宜小于500ms
        CrabSDK.setBlockThreshold(2000);

        // 设置行为记录功能，在BaseActivity中调用 CrabSDK.dispatchTouchEvent(event, this)即为开启
        // 设置行为记录最大条目（不设置的情况下默认值为5）
        CrabSDK.setBehaviorRecordLimit(8);

        // 设置行为记录功能，在BaseActivity中调用 CrabSDK.urlRecordEvent(event, this)即为开启
        // 设置行为记录最大条目（不设置的情况下默认值为10）
        CrabSDK.setUrlRecordLimit(8);

        //以下是对可配置参数的设置，详情请参见平台安装指南：http://crab.baidu.com/wiki/sdk
        // 设置同一crash一天上传的上限，-1代表无上限，默认是10
        CrabSDK.setUploadLimitOfSameCrashInOneday(-1);

        // 设置一天上传的crash总数上限，-1代表无上限，默认是30
        CrabSDK.setUploadLimitOfCrashInOneday(-1);

        // 设置一天上传anr的上限，-1代表无上限，默认是10
        CrabSDK.setUploadLimitOfAnrInOneday(-1);

        // 设置渠道号
        CrabSDK.setChannel("管管");

        // 开启ndk捕获功能，默认关闭，
        // 若app涉及NDK编程，可去平台下载NativeSDK集成后，打开此开关即可
//        CrabSDK.openNativeCrashHandler(); // Native crash收集

        // 开启截屏收集功能，默认关闭
        CrabSDK.setCollectScreenshot(true);

        // 设置crab的log开关，默认开启
        CrabSDK.setEnableLog(true);

        // 设置自定义字段的两种方式 1.直接传一个字段；2.传一个map
        // 二选一，同时调用只显示后者
//		CrabSDK.setUsersCustomKV("key", "value");

        HashMap<String, String> customMap = new HashMap<String, String>();
        customMap.put("key1", "value1");
        CrabSDK.setUsersCustomKV(customMap);

        // 设置对crash anr发生时回调接口
        CrabSDK.setOnAnrCrashListener(new OnAnrCrashListener() {


            @Override
            public void onAnrStarted(Map map) {
            }

            @Override
            public void onCrashStarted(Thread arg0, Throwable arg1) {

            }

            @Override
            public void onNativeCrashStarted(Error arg0, String arg1, int arg2) {

            }
        });
    }

    public void initImageLoader(Context context) {

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder ( context );
        config.threadPriority ( Thread.NORM_PRIORITY - 2 );
        config.denyCacheImageMultipleSizesInMemory ();
        config.diskCacheFileNameGenerator ( new Md5FileNameGenerator() );
        config.diskCacheSize ( 50 * 1024 * 1024 ); // 50 MiB
        config.tasksProcessingOrder ( QueueProcessingType.LIFO );
        config.writeDebugLogs (); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance ().init ( config.build () );
    }

    @Override
    public void onCreate() {
        super.onCreate ();
        ZXingLibrary.initDisplayOpinion(this);
        mContext = getApplicationContext ();
        initBaiduCrab();
        initImageLoader(this) ;
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        MobSDK.init(this);

    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory ();
        System.gc ();
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory ( level );
        System.gc ();
    }

}
