package com.zzteck.jumin.ui.mainui;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;
import com.mainaer.wjoklib.okhttp.download.DownLoadTask;
import com.mainaer.wjoklib.okhttp.download.DownloadManager;
import com.mainaer.wjoklib.okhttp.download.DownloadTaskListener;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.VersionInfo;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.fragment.MainCategoryFragment;
import com.zzteck.jumin.fragment.HomeFragment;
import com.zzteck.jumin.fragment.UserFragment;
import com.zzteck.jumin.fragment.WJConversationListFragment;
import com.zzteck.jumin.ui.business.ReleaseCategoryActivity;
import com.zzteck.jumin.ui.usercenter.LoginActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.SharePerfenceUtil;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.utils.ZZNotificationManager;
import com.zzteck.jumin.view.MyDialog;
import com.zzteck.jumin.view.VersionDialog;
import com.zzteck.zzview.WJViewPaper;
import com.zzteck.zzview.WindowsToast;

import junit.runner.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends BaseActivity implements OnClickListener{

	 private Fragment mHomeFragmemt;
	 
	 private Fragment mCategoryFragment;
	 
	 private Fragment mReleaseFragment ;

	 private Fragment mMessageFragment ;
	 
	 private Fragment mUserFragment ;
	 
	 private WJViewPaper mWJViewPaper ;
	 
	 private ImageView mIvHome ;
	 
	 private ImageView mIvHistory;
	 
	 private ImageView mIvRelease ;
	 
	 private ImageView mIvMessage ;
	 
	 private ImageView mIvUser ;
	 
	 private TextView mTvHome ;
	 
	 private TextView mTvHistory;
	 
	 private TextView mTvRelease ;
	 
	 private TextView mTvMessage ;
	 
	 private TextView mTvUser ;
	 
	 private RelativeLayout mRlHome ;
	 
	 private RelativeLayout mRlNear ;
	 
	 private LinearLayout mRlRelease ;
	 
	 private RelativeLayout mRlMessage ;
	 
	 private RelativeLayout mRlUser ;
	 
	 private List<Fragment> mFragment = new ArrayList<>();

	public static int getLocalVersion(Context ctx) {
		int localVersion = 0;
		try {
			PackageInfo packageInfo = ctx.getApplicationContext()
					.getPackageManager()
					.getPackageInfo(ctx.getPackageName(), 0);
			localVersion = packageInfo.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return localVersion;
	}


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showNotification(Context context, String id, String title, String content) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ZZNotificationManager.sendNotification(context, Integer.valueOf(id), ZZNotificationManager.Channel.COMMENT, context.getString(R.string.app_name), content);

        } else {

            Intent intent = new Intent(context, MainActivity.class);

            Notification notification1 = new NotificationCompat.Builder(context)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker(context.getString(R.string.app_name))
                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText(content)
                    .setWhen(System.currentTimeMillis())
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setOngoing(false)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setContentIntent(PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT))
                    .build();

            NotificationManager notificationManager1 = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            notificationManager1.notify(Integer.valueOf(id), notification1);
        }
    }

	@RequiresApi(api = Build.VERSION_CODES.O)
	private void initDownload(String url, String des){

		showNotification(mContext,1+"",mContext.getResources().getString(R.string.app_name),des) ;
		DownLoadTask task = new DownLoadTask.Builder().setId(URL_ID).setUrl(url).setListener(new DownloadTaskListener(){

			@Override
			public void onDownloading(DownLoadTask downloadTask, long completedSize, long totalSize, int percent) {
				ZZNotificationManager.setProgress(mContext,percent,1);
			}

			@Override
			public void onPause(DownLoadTask downloadTask, long completedSize, long totalSize, int percent) {

			}

			@Override
			public void onCancel(DownLoadTask downloadTask) {

			}

			@Override
			public void onDownloadSuccess(DownLoadTask downloadTask, File file) {
				ZZNotificationManager.deleteChannel(mContext,mContext.getResources().getString(R.string.app_name));
			}

			@Override
			public void onError(DownLoadTask downloadTask, int errorCode) {


			}

		}).build();

		downloadManager.addDownloadTask(task);
	}

	private static String URL_ID = "url";

	private DownloadManager downloadManager ;

	private void commonVersion(){

		 Map<String, String> map = new HashMap<>() ;
		 map.put("s","App.Common.Version") ;

		 map.put("sign",UtilsTools.getSign(mContext,"App.Common.Version")) ;

		 OkHttpClient client = new OkHttpClient();
		 Request request = new Request.Builder().get().url(Constants.HOST+"?"+ UtilsTools.getMapToString(map)).build();
		 Call call = client.newCall(request);
		 call.enqueue(new Callback() {

			 @Override
			 public void onFailure(Call call, IOException e) {
				 Log.e("liujw","##########################IOException : "+e.toString());
			 }

			 @Override
			 public void onResponse(Call call, final Response response) throws IOException {
				 final String responseStr = response.body().string();
				 runOnUiThread(new Runnable() {
					 @Override
					 public void run() {

						 String message = new String(responseStr.getBytes()) ;
						 Gson gson = new Gson() ;
						 VersionInfo bean = gson.fromJson(message,VersionInfo.class) ;

						 if(Integer.valueOf(bean.getData().getVersion()) > getLocalVersion(mContext)){
							 VersionDialog dialog1 = new VersionDialog(mContext,bean.getData()) ;

							 dialog1.setmIDownloadClickListener(new VersionDialog.IDownloadClickListener() {
								 @RequiresApi(api = Build.VERSION_CODES.O)
								 @Override
								 public void downloadClick(String url,String des) {
									 initDownload(url,des) ;
								 }
							 });

							 dialog1.show();
						 }

					 }
				 });
			 }
		 });


	 }

	 private void initView(){
		 
		 mRlHome = findViewById(R.id.rl_home) ;
		 mRlNear = findViewById(R.id.rl_history) ;
		 mRlRelease = findViewById(R.id.ll_realease) ;
		 mRlMessage = findViewById(R.id.rl_message) ;
		 mRlUser = findViewById(R.id.rl_user) ;
		 
		 mRlHome.setOnClickListener(this);
		 mRlNear.setOnClickListener(this);
		 mRlRelease.setOnClickListener(this);
		 mRlMessage.setOnClickListener(this);
		 mRlUser.setOnClickListener(this);
		 
		 
		 mIvHome = findViewById(R.id.iv_home) ;
		 mIvHistory = findViewById(R.id.iv_history) ;
		 mIvRelease = findViewById(R.id.iv_release) ;
		 mIvMessage = findViewById(R.id.iv_message) ;
		 mIvUser = findViewById(R.id.iv_user) ;
		 
		 mTvHome = findViewById(R.id.tv_home) ;
		 mTvHistory = findViewById(R.id.tv_history) ;
		 mTvRelease = findViewById(R.id.tv_release) ;
		 mTvMessage = findViewById(R.id.tv_message) ;
		 mTvUser = findViewById(R.id.tv_user) ;
		 
		 mWJViewPaper = findViewById(R.id.content) ;
		 mHomeFragmemt = new HomeFragment();
		 mCategoryFragment = new MainCategoryFragment();
		 mMessageFragment = new WJConversationListFragment();
		 mUserFragment = new UserFragment();
		 
		 mFragment.add(mHomeFragmemt) ;
		 mFragment.add(mCategoryFragment) ;
		 mFragment.add(mMessageFragment) ;
		 mFragment.add(mUserFragment) ;

		 FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter ( getSupportFragmentManager () ) {
			@Override
			public Fragment getItem(int position) {
				return mFragment.get ( position );
			}

			@Override
			public int getCount() {
				return mFragment.size ();
			}
		};

		mWJViewPaper.setScrollble(true);
		mWJViewPaper.setAdapter ( fragmentPagerAdapter );
		mWJViewPaper.setOffscreenPageLimit ( 4 );

		mWJViewPaper.setCurrentItem ( 0 ) ;


	 }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		App.getInstance().isSelectCity = false ;
		SharePerfenceUtil.clearSharePrefrences(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = MainActivity.this ;
 		initView() ;
		App.getInstance().addActivity(this);

		downloadManager = DownloadManager.getInstance();

		RxPermissions rxPermissions1 = new RxPermissions(this);

		rxPermissions1.requestEach(
				Manifest.permission.CALL_PHONE,
				Manifest.permission.ACCESS_FINE_LOCATION,
				Manifest.permission.ACCESS_COARSE_LOCATION,
				Manifest.permission.CAMERA,
				Manifest.permission.RECORD_AUDIO,
				Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
			@Override
			public void accept(Permission permission) throws Exception {
				if (permission.granted) {

				}else{
					Toast.makeText(getApplicationContext(),"您没有授权该权限，请在设置中打开授权"+permission.name,Toast.LENGTH_LONG).show();
				}
			}
		});

		setStyleCustom() ;

		commonVersion() ;


	}


	/**
	 * 设置通知栏样式 - 定义通知栏Layout
	 */
	private void setStyleCustom() {
		CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(this, R.layout.customer_notitfication_layout, R.id.icon, R.id.title, R.id.text);
		builder.layoutIconDrawable = R.mipmap.ic_launcher;
		builder.developerArg0 = "developerArg2";
		JPushInterface.setPushNotificationBuilder(2, builder);
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

		if(requestCode == 1122 && data != null){
			Bundle bundle = data.getExtras()  ;

			String code = bundle.getString(CodeUtils.RESULT_STRING) ;
			memberScan(code);

			WindowsToast.makeText(mContext,"@@@@@@@@@@code :"+code).show();
		}

	}

	private void memberScan(String code){

		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Member.Scan") ;
		map.put("code",code) ;

		map.put("sign", UtilsTools.getSign(mContext,"App.Member.Scan")) ;

		OkHttpClient client = new OkHttpClient();
		//构造Request对象
		//采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
		Request request = new Request.Builder().get().url(Constants.HOST+"?"+ UtilsTools.getMapToString(map)).build();
		Call call = client.newCall(request);
		//异步调用并设置回调函数
		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				Log.e("liujw","##########################IOException : "+e.toString());
			}

			@Override
			public void onResponse(Call call, final Response response) throws IOException {
				final String responseStr = response.body().string();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						String message = new String(responseStr.getBytes()) ;
						Gson gson = new Gson() ;
						LoginBean bean = gson.fromJson(message,LoginBean.class) ;

						if(bean != null && bean.getData() != null && bean.getData().isIs_login()){
							UserDAO.getInstance(mContext).editorUserTable(bean.getData());
							WindowsToast.makeText(mContext,"登录成功").show();
						}

					}
				});
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_realease:

			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));

			if(UserDAO.getInstance(mContext).isExistRecordLogin()){
				Intent intent = new Intent(mContext, ReleaseCategoryActivity.class) ;
				startActivity(intent);
			}else{
				Intent intent = new Intent(mContext, LoginActivity.class) ;
				startActivity(intent);
			}

			break;
		case R.id.rl_home:
			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));

			mIvHome.setImageResource(R.mipmap.icon_shouye_pre);
			mIvHistory.setImageResource(R.mipmap.icon_lishi_nor);
			mIvUser.setImageResource(R.mipmap.icon_wode_nor);
			mIvMessage.setImageResource(R.mipmap.icon_xiaoxi_nor);


			mWJViewPaper.setCurrentItem(0,false);
			break ;
		case R.id.rl_history:
			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));


			mIvHome.setImageResource(R.mipmap.icon_shouye_nor);
			mIvHistory.setImageResource(R.mipmap.icon_lishi_pre);
			mIvUser.setImageResource(R.mipmap.icon_wode_nor);
			mIvMessage.setImageResource(R.mipmap.icon_xiaoxi_nor);

			mWJViewPaper.setCurrentItem(1,false);
			break ;
		case R.id.rl_user:
			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mWJViewPaper.setCurrentItem(3,false);

			mIvHome.setImageResource(R.mipmap.icon_shouye_nor);
			mIvHistory.setImageResource(R.mipmap.icon_lishi_nor);
			mIvUser.setImageResource(R.mipmap.icon_wode_pre);
			mIvMessage.setImageResource(R.mipmap.icon_xiaoxi_nor);

			((UserFragment)mUserFragment).initData() ;

			break ;
		case R.id.rl_message :
			mTvHome.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvHistory.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvRelease.setTextColor(mContext.getResources().getColor(R.color.dark));
			mTvMessage.setTextColor(mContext.getResources().getColor(R.color.dark_red));
			mTvUser.setTextColor(mContext.getResources().getColor(R.color.dark));

			mIvHome.setImageResource(R.mipmap.icon_shouye_nor);
			mIvHistory.setImageResource(R.mipmap.icon_lishi_nor);
			mIvUser.setImageResource(R.mipmap.icon_wode_nor);
			mIvMessage.setImageResource(R.mipmap.icon_xiaoxi_pre);


			mWJViewPaper.setCurrentItem(2,false);
			break ;

		default:
			break;
		}
	}
}
