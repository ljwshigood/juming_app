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
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zzteck.jumin.R;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.MediaInfo;
import com.zzteck.jumin.bean.ModifyBean;
import com.zzteck.jumin.bean.User;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.business.ReleaseActivity;
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

    private List<LocalMedia> selectList = new ArrayList<>();

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.ll_back :
				finish();
				break ;
			case R.id.rl_upload_photo:

                PictureSelector.create(ModifyUserInfoActivity.this)
                        .openGallery(PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(4)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(true)// 是否可预览视频
                        .enablePreviewAudio(true) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(false)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled(true) // 裁剪是否可旋转图片
                        //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
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
