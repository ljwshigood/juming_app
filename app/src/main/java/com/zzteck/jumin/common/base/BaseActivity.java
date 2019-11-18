package com.zzteck.jumin.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zzteck.jumin.R;
import com.zzteck.jumin.common.widget.dialog.CbLoadingDialog;


public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext;
    public Bundle savedInstanceState;
    public ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResource());
        this.savedInstanceState = savedInstanceState;
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();  //所有子类都将继承这些相同的属性
        mContext = this;
        initView();
        initData();
        initList();
    }

    public void start(Class clazz) {
        startActivity(new Intent(mContext, clazz));
    }

    public abstract int getContentViewResource();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initList();

    public void UpTitle(String title) {
        if (findViewById(R.id.vBar) == null) return;
        mImmersionBar.titleBar(R.id.vBar).statusBarDarkFont(true, 0.2f).init();
        findViewById(R.id.tvTitleBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ((TextView) findViewById(R.id.tvTitleBar)).setText(title == null ? "" : title);
    }



    private CbLoadingDialog mProgressDialog;

    public void showProgressDialog(String msg) {
        if (this.mProgressDialog == null)
            this.mProgressDialog = new CbLoadingDialog(mContext);
        if (!TextUtils.isEmpty(msg)) {
            this.mProgressDialog.setMessage(msg);
        } else {
            this.mProgressDialog.setMessage("");
        }
        this.mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        if (this.mProgressDialog != null)
            this.mProgressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}
