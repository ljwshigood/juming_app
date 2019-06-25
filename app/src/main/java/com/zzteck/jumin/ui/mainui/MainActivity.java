package com.zzteck.jumin.ui.mainui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.icechn.videorecorder.ui.RecordingActivity2;
import com.maning.updatelibrary.InstallUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.AuthBean;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.User;
import com.zzteck.jumin.bean.VersionInfo;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.fragment.HomeFragment;
import com.zzteck.jumin.fragment.MainCategoryFragment;
import com.zzteck.jumin.fragment.UserFragment;
import com.zzteck.jumin.fragment.WJConversationListFragment;
import com.zzteck.jumin.ui.business.ReleaseCategoryActivity;
import com.zzteck.jumin.ui.usercenter.LoginActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.PermissionUtils;
import com.zzteck.jumin.utils.SharePerfenceUtil;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.VersionDialog;
import com.zzteck.zzview.WJViewPaper;
import com.zzteck.zzview.WindowsToast;

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


	private NotificationManager mNotificationManager;

	private Notification mNotification;

	private String CHANNEL_ID = "my_channel_01";

	private static final int NOTIFY_ID = 0;


	@TargetApi(Build.VERSION_CODES.O)
	@SuppressWarnings("deprecation")
	private void setUpNotification(String packageName, Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = getString(R.string.app_name);
			String description = getString(R.string.app_name);
			int importance = NotificationManager.IMPORTANCE_LOW;
			@SuppressLint("WrongConstant") NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
			mChannel.setDescription(description);
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			notificationManager.createNotificationChannel(mChannel);


			mNotification = new Notification.Builder(MainActivity.this)
					.setContentTitle("正在下载")
					.setContentText("请稍等")
					.setWhen(System.currentTimeMillis())
					.setSmallIcon(R.mipmap.ic_launcher)
					.setChannelId(CHANNEL_ID)
					.build();

			mNotification.flags = Notification.FLAG_ONGOING_EVENT;
			RemoteViews contentView = new RemoteViews(packageName, R.layout.download_notification_layout);
			mNotification.contentView = contentView;

			mNotificationManager.notify(NOTIFY_ID, mNotification);

		}else{
			setUpNotificationLow(packageName,context) ;
		}

	}

	@SuppressWarnings("deprecation")
	private void setUpNotificationLow(String packageName, Context context) {
		mNotification = new Notification(R.mipmap.ic_launcher, "正在下载",  System.currentTimeMillis());
		mNotification.flags = Notification.FLAG_ONGOING_EVENT;
		RemoteViews contentView = new RemoteViews(packageName, R.layout.download_notification_layout);
		mNotification.contentView = contentView;

		mNotificationManager.notify(NOTIFY_ID, mNotification);

	}


	private InstallUtils.DownloadCallBack downloadCallBack;

	private String apkDownloadPath;

	private void installApk(String path) {
		InstallUtils.installAPK(MainActivity.this, path, new InstallUtils.InstallCallBack() {
			@Override
			public void onSuccess() {
				//onSuccess：表示系统的安装界面被打开
				//防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
				Toast.makeText(mContext, "正在安装程序", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFail(Exception e) {
				//tv_info.setText("安装失败:" + e.toString());
			}
		});
	}

	public static final String APK_SAVE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/jumin.apk";

	private void initCallBack() {
		downloadCallBack = new InstallUtils.DownloadCallBack() {
			@Override
			public void onStart() {
				Log.e("liujw", "InstallUtils---onStart");
				setUpNotification(mContext.getPackageName(),mContext);
			}

			@Override
			public void onComplete(String path) {
				mNotificationManager.cancel(NOTIFY_ID);
				Log.e("liujw", "InstallUtils---onComplete:" + path);
				apkDownloadPath = path ;
				//先判断有没有安装权限
				InstallUtils.checkInstallPermission(MainActivity.this, new InstallUtils.InstallPermissionCallBack() {
					@Override
					public void onGranted() {
						//去安装APK
						installApk(apkDownloadPath);
					}

					@Override
					public void onDenied() {
						//弹出弹框提醒用户
						AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
								.setTitle("温馨提示")
								.setMessage("必须授权才能安装APK，请设置允许安装")
								.setNegativeButton("取消", null)
								.setPositiveButton("设置", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										//打开设置页面
										InstallUtils.openInstallPermissionSetting(MainActivity.this, new InstallUtils.InstallPermissionCallBack() {
											@Override
											public void onGranted() {
												//去安装APK
												installApk(apkDownloadPath);
											}

											@Override
											public void onDenied() {
												//还是不允许咋搞？
												//Toast.makeText(MainActivity.this, "强制更新就退出应用程序吧！", Toast.LENGTH_SHORT).show();
											}
										});
									}
								})
								.create();
						alertDialog.show();
					}
				});
			}

			@Override
			public void onLoading(long total, long current) {
				//内部做了处理，onLoading 进度转回progress必须是+1，防止频率过快
				Log.e("liujw", "InstallUtils----onLoading:-----total:" + total + ",current:" + current);
				int progress = (int) (current * 100 / total);

				RemoteViews contentview = mNotification.contentView;
				contentview.setProgressBar(R.id.pb_download, 100, progress, false);
				mNotificationManager.notify(NOTIFY_ID, mNotification);
			}

			@Override
			public void onFail(Exception e) {
				mNotificationManager.cancel(NOTIFY_ID);
				Log.e("liujw", "InstallUtils---onFail:" + e.getMessage());
			}

			@Override
			public void cancle() {
				mNotificationManager.cancel(NOTIFY_ID);
				Log.e("liujw", "InstallUtils---cancle");
			}
		};
	}


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
									 //initDownload(url,des) ;

                                     if (!PermissionUtils.isGrantSDCardReadPermission(MainActivity.this)) {
                                         PermissionUtils.requestSDCardReadPermission(MainActivity.this, 100);
                                     } else {
                                         InstallUtils.with(MainActivity.this)
                                                 //必须-下载地址
                                                 .setApkUrl(url)
                                                 //非必须-下载保存的文件的完整路径+name.apk
                                                 .setApkPath(APK_SAVE_PATH)
                                                 //非必须-下载回调
                                                 .setCallBack(downloadCallBack)
                                                 //开始下载
                                                 .startDownload();
                                     }
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
		 mRlNear = findViewById(R.id.rv_history) ;
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

	private ImageView mIv ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = MainActivity.this ;
 		initView() ;
		App.getInstance().addActivity(this);


		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);

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
		initCallBack() ;

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
		}

	}

	private void memberScan(String code){

		List<User> userList = UserDAO.getInstance(mContext).selectUserList() ;
		Map<String, String> map = new HashMap<>() ;
		map.put("s","App.Member.Scan") ;
		map.put("code",code) ;
		map.put("userid",userList.get(0).getMobile()) ;
		map.put("sign", userList.get(0).getWanjiaToken()) ;

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
						AuthBean bean = gson.fromJson(message,AuthBean.class) ;

						if(bean != null && bean.getData() != null && bean.getData().isIs_success()){
							WindowsToast.makeText(mContext,"授权成功").show();
						}

					}
				});
			}
		});
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
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
		case R.id.rv_history:
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

			((WJConversationListFragment)mMessageFragment).requestMessageList(1);

			break ;

		default:
			break;
		}
	}
}
