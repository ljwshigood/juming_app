/**
 */
package com.zzteck.jumin.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.Window;

import com.gyf.barlibrary.ImmersionBar;
import com.zzteck.jumin.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 *
 *  author : liujw modify :
 *  2020-1-6 上午10:50:05
 *
 */
public class BaseActivity extends FragmentActivity {

    public Context mContext;
    public int mWindowWidth;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getRequestedOrientation () != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation ( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.mContext = BaseActivity.this;
        super.onCreate ( savedInstanceState );

        ImmersionBar.with(this)
                .navigationBarColor(R.color.colorPrimary)
                .init();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ImmersionBar.with(this).init();
    }

    @Override
    protected void onStart() {
        super.onStart ();
    }

    @Override
    protected void onStop() {
        super.onStop ();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        // 添加记录crash前的webview浏览记录（crash的activity中含有webview组件）
       // CrabSDK.urlRecordEvent(event, this);

        // 添加行为记录（可选）
        // 默认记录最近5步，如需修改记录步数，调用 CrabSDK.setBehaviorRecordLimit(int)设置搜集条数;
        // 详情请参看使用文档
        //CrabSDK.behaviorRecordEvent(event, this);
        return super.dispatchTouchEvent ( event );
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources ();
        Configuration config = new Configuration ();
        config.setToDefaults ();
        res.updateConfiguration ( config, res.getDisplayMetrics () );
        return res;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
    }

    @Override
    protected void onResume() {
        super.onResume ();
        //CrabSDK.onResume(this);
        ImmersionBar.with(this).destroy();

    }

    @Override
    protected void onPause() {
        super.onPause ();
       // CrabSDK.onPause(this);

    }

}
