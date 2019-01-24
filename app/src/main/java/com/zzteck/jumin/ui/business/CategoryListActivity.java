package com.zzteck.jumin.ui.business;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import com.hjhrq1991.library.tbs.BridgeHandler;
import com.hjhrq1991.library.tbs.CallBackFunction;
import com.hjhrq1991.library.tbs.DefaultHandler;
import com.hjhrq1991.library.tbs.SimpleBridgeWebViewClientListener;
import com.hjhrq1991.library.tbs.TbsBridgeWebView;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.ui.mainui.BaseActivity;


public class CategoryListActivity extends BaseActivity implements OnClickListener{

	TbsBridgeWebView webView;

	private String TAG = "liujw" ;

	private void initView() {
		webView = findViewById(R.id.webView);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setSaveFormData(false);
		webView.getSettings().setSavePassword(false);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setDisplayZoomControls(false);
		webView.getSettings().setLoadWithOverviewMode(true);
		//设定支持h5viewport
		webView.getSettings().setUseWideViewPort(true);
		// 自适应屏幕.
		webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

		//=======================js桥使用改方法替换原有setWebViewClient()方法==========================
		webView.setBridgeWebViewClientListener(new SimpleBridgeWebViewClientListener() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.i(TAG, "超链接：" + url);
				return false;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap bitmap) {

			}

			@Override
			public void onPageFinished(WebView view, String url) {

			}

			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

			}

			@Override
			public boolean onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
				String message;
				switch (sslError.getPrimaryError()) {
					case android.net.http.SslError.SSL_UNTRUSTED:
						message = "证书颁发机构不受信任";
						break;
					case android.net.http.SslError.SSL_EXPIRED:
						message = "证书过期";
						break;
					case android.net.http.SslError.SSL_IDMISMATCH:
						message = "网站名称与证书不一致";
						break;
					case android.net.http.SslError.SSL_NOTYETVALID:
						message = "证书无效";
						break;
					case android.net.http.SslError.SSL_DATE_INVALID:
						message = "证书日期无效";
						break;
					case android.net.http.SslError.SSL_INVALID:
					default:
						message = "证书错误";
						break;
				}
				return true;
			}
		});
		//=======================此方法必须调用==========================
		webView.setDefaultHandler(new DefaultHandler());
		webView.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient() {
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
			}

			@Override
			public void onProgressChanged(WebView view, int newProgress) {

				super.onProgressChanged(view, newProgress);
			}
		});


		//description：如需使用自定义桥名，调用以下方法即可，
		// 传空或不调用setCustom方法即使用默认桥名。
		// 默认桥名：WebViewJavascriptBridge
		//=======================使用自定义桥名时调用以下代码即可==========================
//        webView.setCustom("桥名");
		webView.setCustom("TestJavascriptBridge");

		//=======================以下4个web调用native示例方法==========================
		webView.registerHandler("initSignNetPay", new BridgeHandler() {
			@Override
			public void handler(String data, CallBackFunction function) {
				Log.i(TAG, "回传结果：" + data);
				//Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
			}
		});

		webView.registerHandler("initSignNetShare", new BridgeHandler() {

			@Override
			public void handler(String data, CallBackFunction function) {
				Log.i(TAG, "回传结果：" + data);
				//Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
			}
		});

		webView.registerHandler("jsHandler1", new BridgeHandler() {

			@Override
			public void handler(String data, CallBackFunction function) {
				Log.i(TAG, "回传结果：" + data);
			}
		});

		webView.registerHandler("jsHandler2", new BridgeHandler() {

			@Override
			public void handler(String data, CallBackFunction function) {
				Log.i(TAG, "回传结果：" + data);
			}
		});


		//=======================招行一网通js桥回调==========================
		webView.registerHandler("initCmbSignNetPay", new BridgeHandler() {

			@Override
			public void handler(String data, CallBackFunction function) {
				//在这里解析回调数据并执行处理
				Log.i(TAG, "回传结果：" + data);
			}
		});

		webView.loadUrl("file:///android_asset/code/list.html");//本地模板

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_list);

		getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		mContext = CategoryListActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;
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

	@Override
	public void onBackPressed() {
		if (webView.canGoBack())
			webView.goBack();
		else
			finish();
	}
}
