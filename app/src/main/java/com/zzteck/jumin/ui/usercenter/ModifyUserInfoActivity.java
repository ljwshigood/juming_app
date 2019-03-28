package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baijiahulian.common.crop.BJCommonImageCropHelper;
import com.baijiahulian.common.crop.ThemeConfig;
import com.baijiahulian.common.crop.model.PhotoInfo;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.MediaInfo;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.PictureUtil;
import com.zzteck.jumin.webmanager.ApiCall;
import com.zzteck.jumin.webmanager.CountingRequestBody;
import com.zzteck.jumin.webmanager.RequestBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class ModifyUserInfoActivity extends BaseActivity implements OnClickListener{

	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private RelativeLayout mRlUploadPhoto ;

    private String response ;

    private OkHttpClient client ;

    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    private void upload(final File file,final String url) throws Exception {

        new AsyncTask<Integer, Integer, String>() {

            @Override
            protected String doInBackground(Integer... params) {
             /*   try {*/
                    //response= uploadFiles(file);
                    // publishProgress();
                    String fileExtention = getFileExt(file.getName());
                    String filename = file.getName();
                   // Log.d("getAbsolutePath ", file.getAbsolutePath() + "");
                   // Log.d("getCanonicalPath ", file.getCanonicalPath() + "");
                    MultipartBody body = RequestBuilder.uploadRequestBody(filename, fileExtention, "", file);


                    CountingRequestBody monitoredRequest = new CountingRequestBody(body, new CountingRequestBody.Listener() {
                        @Override
                        public void onRequestProgress(long bytesWritten, long contentLength) {
                            //Update a progress bar with the following percentage
                            float percentage = 100f * bytesWritten / contentLength;
                            if (percentage >= 0) {
                                publishProgress(Math.round(percentage));
                                Log.e("progress ", percentage + "");
                            } else {
                                Log.e("No progress ", 0 + "");
                            }
                        }
                    });

                    Request request = new Request.Builder()
                            .url(url)
                            .post(monitoredRequest)
                            .build();
                    Call response = client.newCall(request) ;

                    response.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("liujw","####################onFailure");
                            Log.e("liujw","####################onFailure");
                            Log.e("liujw","####################onFailure");
                            Log.e("liujw","####################onFailure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String responseStr = response.body().string();
                            Log.e("liujw","####################onResponse"+responseStr);
                            Log.e("liujw","####################onResponse"+responseStr);
                            Log.e("liujw","####################onResponse"+responseStr);
                            Log.e("liujw","####################onResponse"+responseStr);
                        }
                    });

                    //response = ApiCall.POST(client, url, monitoredRequest);
               /* } catch (IOException e) {
                    e.printStackTrace();
                }*/
                return "";
            }

            @Override
            protected void onPostExecute(String result) {
                //progressBar.setVisibility(View.GONE);
                //txt.setText(result);
               // mBuilder.setContentText("Upload complete");
                // Removes the progress bar
               // mBuilder.setProgress(0, 0, false);
               // mNotifyManager.notify(0, mBuilder.build());
            }

            @Override
            protected void onPreExecute() {
               /* txt.setText("Task Starting...");
                mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(context);
                mBuilder.setContentTitle("Uploading")
                        .setContentText("Upload in progress")
                        .setSmallIcon(R.drawable.ic_certificate_box);
                Toast.makeText(context, "Uploading files... The upload progress is on notification bar.", Toast.LENGTH_LONG).show();*/
            }

            @Override
            protected void onProgressUpdate( Integer... values) {
                /*txt.setText("Running..." + values[0]);
                progressBar.setProgress(values[0]);
                if ((values[0])%25==0){
                    mBuilder.setProgress(100, values[0], false);
                    // Displays the progress bar on notification
                    mNotifyManager.notify(0, mBuilder.build());
                }*/

            }


        }.execute();
    }



    private void initView() {
		mRlUploadPhoto = findViewById(R.id.rl_upload_photo) ;
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("修改头像");
		mRlBack.setVisibility(View.VISIBLE);
		mRlUploadPhoto.setOnClickListener(this);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_modify_user_info);
        client = new OkHttpClient() ;
		mContext = ModifyUserInfoActivity.this ;
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
		switch (v.getId()){
			case R.id.ll_back :
				finish();
				break ;
			case R.id.rl_upload_photo:

				BJCommonImageCropHelper.openImageSingleAblum(ModifyUserInfoActivity.this, BJCommonImageCropHelper.PhotoCropType.None,
						new ThemeConfig.Builder().setMainElementsColor(Color.parseColor("#00ccff")).setTitlebarRightButtonText(R.string.complete).build(), new BJCommonImageCropHelper.OnHandlerResultCallback(){

							@Override
							public void onHandlerSuccess(List<PhotoInfo> resultList) {

								MediaInfo media = new MediaInfo();
								media.setType(0);
								media.setFilePath(resultList.get(0).getPhotoPath());
								mPictureList.add(media);

								if(mPictureList.size() > 0){
									//mIvAdd.setVisibility(View.GONE);
								}

								//initMediaData();
								compressFileList();

							}

							@Override
							public void onHandlerFailure(String errorMsg) {

							}

						});
				break ;
		}
	}


    // upload files
    private Handler mUploadHandler = new Handler(){

        public void handleMessage(Message msg) {

            for(int i = 0 ;i < mPictureList.size() ;i++){
                MediaInfo info = mPictureList.get(i) ;
                if(info.getCompressFile() != null){
                    try {
                        upload(new File(info.getCompressFile()), Constants.HOST) ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    };

    private List<MediaInfo> mPictureList = new ArrayList<MediaInfo>() ;

    private void compressFileList(){
        new Thread(){

            public void run() {

                for(int i = 0 ;i < mPictureList.size() ;i++){
                    MediaInfo info = mPictureList.get(i) ;
                    if(info.getIsCompressFile() == 0){
                        String compressString = PictureUtil.compressPicture(mContext,info.getFilePath());
                        if(compressString != null){
                            info.setCompressFile(compressString);
                            info.setIsCompressFile(1);
                        }else{
                            info.setLoadFlag(2);
                        }
                    }
                }
                mUploadHandler.sendEmptyMessage(0);
            }

        }.start();
    }

}
