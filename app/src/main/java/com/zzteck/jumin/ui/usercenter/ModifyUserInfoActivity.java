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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.MediaInfo;
import com.zzteck.jumin.bean.ModifyBean;
import com.zzteck.jumin.bean.User;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.ui.mainui.MainActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.FileUtils;
import com.zzteck.jumin.utils.GlideCircleTransform;
import com.zzteck.jumin.utils.PictureUtil;
import com.zzteck.jumin.webmanager.CountingRequestBody;
import com.zzteck.jumin.webmanager.RequestBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ModifyUserInfoActivity extends BaseActivity implements OnClickListener{

	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private RelativeLayout mRlUploadPhoto ;

    private String response ;

    private OkHttpClient client ;

    private void upload(final File file,final String url) throws Exception {

        new AsyncTask<Integer, Integer, String>() {

            @Override
            protected String doInBackground(Integer... params) {

                MultipartBody body = RequestBuilder.uploadRequestBody(ModifyUserInfoActivity.this, file);

                CountingRequestBody monitoredRequest = new CountingRequestBody(body, new CountingRequestBody.Listener() {
                    @Override
                    public void onRequestProgress(long bytesWritten, long contentLength) {
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
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseStr = response.body().string();
                        Gson gson = new Gson() ;
                        final ModifyBean bean = gson.fromJson(responseStr,ModifyBean.class) ;

                        if(bean.getData().isIs_success() == true ){
                            if(users != null && users.size() > 0){
                                users.get(0).setHeader(bean.getData().getLogo());
                                UserDAO.getInstance(mContext).editorUserInfo(users.get(0));
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    RequestOptions options = new RequestOptions()
                                            .placeholder(com.yalantis.ucrop.R.color.ucrop_color_grey)
                                            .centerCrop()
                                            .diskCacheStrategy(DiskCacheStrategy.ALL);

                                    Glide.with(mContext)
                                            .load(Constants.PIC_HOST+bean.getData().getLogo())
                                            .transition(DrawableTransitionOptions.withCrossFade())
                                            .apply(options)
                                            .into(mIvHeader);

                                   /* Glide.with(mContext)
                                            .load(bean.getData().getLogo())
                                            .error(R.mipmap.default_pic)
                                            .crossFade(300)
                                            .transform(new GlideCircleTransform(mContext))
                                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                            .into(mIvHeader);*/

                                }
                            });

                        }

                    }
                });

                return "";
            }

            @Override
            protected void onPostExecute(String result) {
            }

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onProgressUpdate( Integer... values) {

            }


        }.execute();
    }



    private void initView() {
        mIvHeader = findViewById(R.id.iv_user_icon) ;
		mRlUploadPhoto = findViewById(R.id.rl_upload_photo) ;
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRlBack = findViewById(R.id.ll_back) ;
		mRlBack.setOnClickListener(this);
		mTvTitle.setText("修改头像");
		mRlBack.setVisibility(View.VISIBLE);
		mRlUploadPhoto.setOnClickListener(this);

	}

	private ImageView mIvHeader ;

    private List<User> users ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_modify_user_info);
        client = new OkHttpClient() ;
		mContext = ModifyUserInfoActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;

		 users = UserDAO.getInstance(mContext).selectUserList() ;

 		if(users != null && users.size() > 0){

            RequestOptions options = new RequestOptions()
                    .placeholder(com.yalantis.ucrop.R.color.ucrop_color_grey)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .load(Constants.PIC_HOST+users.get(0).getHeader())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(options)
                    .into(mIvHeader);

        }

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

				/*BJCommonImageCropHelper.openImageSingleAblum(ModifyUserInfoActivity.this, BJCommonImageCropHelper.PhotoCropType.None,
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

						});*/
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
