package com.zzteck.jumin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.itheima.view.BridgeWebView;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.utils.JavaSctiptMethods;


public class ListActivity extends BaseActivity implements OnClickListener{

	private BridgeWebView mWeview;

	private void initView() {

		mWeview = findViewById(R.id.weview);
		mWeview.addBridgeInterface(new JavaSctiptMethods(ListActivity.this, mWeview));//设置js和android通信桥梁方法
		mWeview.loadUrl("file:///android_asset/code/list.html");//本地模板

	}

	private void setWebViewClient() {

		mWeview.setWebViewClient(new WebViewClient());
		mWeview.setWebChromeClient(new WebChromeClient());

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		mContext = ListActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;
		setWebViewClient() ;

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		}
	}
}
