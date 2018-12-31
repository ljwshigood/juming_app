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

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

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
       // setTranslucentStatus ();
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
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
        }

        ImmersionBar.with(this).destroy();

    }

    /**
     *
     * @param permissions
     * @since 2.5.0
     *
     */
    private void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class,
                            int.class});

                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     *
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23){
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer)checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean)shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                        needRequestPermissonList.add(perm);
                    }
                }
            } catch (Throwable e) {

            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     *
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限，请点击设置--权限--打开所需权限");

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause ();
       // CrabSDK.onPause(this);

    }

}
