package com.zzteck.jumin.ui.business;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.CategoryPagerAdapter;
import com.zzteck.jumin.bean.AttentionInfo;
import com.zzteck.jumin.bean.CategoryDetailHeader;
import com.zzteck.jumin.bean.CatoryDetailInfo;
import com.zzteck.jumin.bean.CheckInfo;
import com.zzteck.jumin.bean.ExternalInfo;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.DeviceUtil;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.ShareDialog;
import com.zzteck.jumin.view.WeiXinDialog;
import com.zzteck.zzview.WindowsToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;*/

public class CategoryDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvTitle;

    private RelativeLayout mRlBack;

    ///////////////////// 轮播图
    private ViewPager mBannerViewPaper;

    private int mPlayTimeInterval = 2500;

    private LinearLayout mPoints;

    private List<CategoryDetailHeader> modelList = new ArrayList<>();

    private LinearLayout mLLCall;

    private LinearLayout mLLWeiXin;

    private LinearLayout mLLMessage;

    private void initViewPager() {
        mPlayTimeInterval = (int) 2.5 * 2000;
        if (modelList != null && modelList.size() > 0) {
            for (int i = 0; i < modelList.size(); i++) {
                View view = new View(mContext);
                view.setBackgroundResource(R.drawable.dot_normal);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
                params.leftMargin = 18;
                view.setLayoutParams(params);
                if (mPoints.getChildCount() == i) {
                    mPoints.addView(view);
                }
            }

            mPoints.getChildAt(0).setBackgroundResource(R.drawable.dot_focused);
            //设置适配器
            CategoryPagerAdapter adapter = new CategoryPagerAdapter(mContext, modelList, 0);
            mBannerViewPaper.setAdapter(adapter);
            mBannerViewPaper.setOffscreenPageLimit(3);
            mBannerViewPaper.setCurrentItem(0);

            mBannerViewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    mPoints.getChildAt(prePosition).setBackgroundResource(R.drawable.dot_normal);
                    mPoints.getChildAt(position % mPoints.getChildCount()).setBackgroundResource(R.drawable.dot_focused);
                    prePosition = position % mPoints.getChildCount();
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int arg0) {

                }
            });
        }

        if (modelList != null && modelList.size() > 1) {
            startPlay();

            mBannerViewPaper.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int action = MotionEventCompat.getActionMasked(event);

                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            stopPlay();
                            break;

                        case MotionEvent.ACTION_MOVE:
                            stopPlay();
                            break;

                        case MotionEvent.ACTION_UP:
                            startPlay();
                            break;
                    }

                    return false;
                }
            });
        }
    }

    private boolean mIsPlay = false;

    private int prePosition;

    /**
     * 描述：开始自动轮播.
     */
    public void startPlay() {
       /* if (mHandler != null) {
            mIsPlay = true;
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, mPlayTimeInterval);
        }*/
    }

    /**
     * 描述：停止自动轮播.
     */
    public void stopPlay() {
        if (mHandler != null) {
            mIsPlay = false;
            mHandler.removeCallbacks(mRunnable);
        }
    }

    /**
     * 用于轮播的Runnable
     */
    private Runnable mRunnable = new Runnable() {
        public void run() {
            if (mBannerViewPaper != null) {
                mHandler.sendEmptyMessage(0);
            }
        }
    };

    /**
     * 用于轮播的handler
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int currentItem = mBannerViewPaper.getCurrentItem();
                mBannerViewPaper.setCurrentItem(currentItem + 1);
                mHandler.postDelayed(mRunnable, 3000);
            }
        }

    };

    ///////////////////

    private TextView mTvContentTitle;

    private TextView mTvPlace;

    private TextView mTvDes;

    private LinearLayout mLLWechat;

    private LinearLayout mLLOrder;

    private ImageView mIvShare;

    private ImageView mIvAttention;

    private ImageView mIvDot;
/*
    private void showShare(String platform) {
        final OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        //启动分享
        oks.show(this);
    }*/


    private void showShare() {

       /* OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(getString(R.string.share));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);*/
    }

    private TextView mTvPrice ;

    private TextView mTvBaseInfo ;

    private TextView mTvTipInfo ;

    private LinearLayout mLLPro ;



    private void daymicLayout(final CatoryDetailInfo info){

       /* if(info == null || info.getData() == null){
            return ;
        }

        mLLPro.removeAllViews();
        mLLPro.setOrientation(LinearLayout.VERTICAL);

        for(int i = 0 ; i< info.getData().getExtra().size() ;i++){


            LinearLayout linearLayoutLeft  = new LinearLayout(mContext) ;
            linearLayoutLeft.setLayoutParams(new ViewGroup.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
            linearLayoutLeft.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutLeft.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.7f));

            TextView tvTitle = new TextView(this);
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setTextSize(16f);
            tvTitle.setText(info.getData().get(i).getTitle());
            linearLayoutLeft.addView(tvTitle);

            LinearLayout linearLayoutRight  = new LinearLayout(mContext) ;
            linearLayoutRight.setLayoutParams(new ViewGroup.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
            linearLayoutRight.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.3f));

            if(info.getData().get(i).getType().equals("radio")){

                linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
                RadioGroup radioGroup = new RadioGroup(this);
                radioGroup.setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                radioGroup.setOrientation(LinearLayout.HORIZONTAL);

                final int positon  = i ;

                String choices = info.getData().get(i).getExtra().getChoices();
                final String[] arrayChoices = choices.split("\\r\\n") ;

                for(int j = 0 ; j< arrayChoices.length ; j++){
                    final RadioButton radioButton = new RadioButton(this) ;
                    radioButton.setId(mRadioButtinId++) ;
                    int index = arrayChoices[j].indexOf("=") ;
                    radioButton.setText(arrayChoices[j].substring(index+1,arrayChoices[j].length())) ;
                    radioButton.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT)) ;
                    radioGroup.addView(radioButton) ;
                    final String[] selectText = arrayChoices[j].split("=") ;
                    radioButton.setTag(selectText[0]);
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int id = group.getCheckedRadioButtonId();
                        RadioButton choise = findViewById(id);
                        String selectText = (String) choise.getTag();
                        mHashExtra.put(info.getData().get(positon).getIdentifier(),selectText) ;
                    }
                });

                linearLayoutRight.addView(radioGroup);


            }else if(info.getData().get(i).getType().equals("number")){

                linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
                EditText etPrice = new EditText(this);
                etPrice.setHint("请输入"+info.getData().get(i).getTitle());
                etPrice.setBackground(null);
                etPrice.setLayoutParams(new LinearLayout.LayoutParams(200,ViewGroup.LayoutParams.WRAP_CONTENT,0.8f));


                mHashMapViews.put(info.getData().get(i).getIdentifier(),etPrice) ;

                TextView tvUnit = new TextView(this);
                tvUnit.setText(info.getData().get(i).getExtra().getUnits());
                tvUnit.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
                tvUnit.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

                linearLayoutRight.addView(etPrice);
                linearLayoutRight.addView(tvUnit);


            }else if(info.getData().get(i).getType().equals("select")){

                linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
                final MaterialSpinner spinner = new MaterialSpinner(mContext) ;
                spinner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

                final String[] attray = info.getData().get(i).getExtra().getChoices().split("\\r\\n") ;

                List<String> spinnerList = new ArrayList<>() ;

                for(int j = 0 ;j < attray.length ;j++){
                    int index = attray[j].indexOf("=") ;
                    String temp = attray[j].substring(index+1,attray[j].length());
                    spinnerList.add(temp);
                }

                spinner.setItems(spinnerList);
                spinner.setTag(info.getData().get(i).getIdentifier());
                spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        String temp = attray[position] ;
                        String[] splites = temp.split("=") ;
                        String identifier = (String) spinner.getTag();
                        mHashExtra.put(identifier,splites[0]) ;

                        Log.e("liujw","########setOnItemSelectedListener mHashExtra : "+mHashExtra.toString()) ;
                    }
                });

                linearLayoutRight.addView(spinner);
            }else if(info.getData().get(i).getType().equals("text")){
                linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
                EditText tvText = new EditText(this);
                tvText.setHint("请输入"+info.getData().get(i).getTitle());
                tvText.setSingleLine();
                tvText.setBackground(null);
                tvText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

                mHashMapViews.put(info.getData().get(i).getIdentifier(),tvText) ;

                linearLayoutRight.addView(tvText) ;


            }else if(info.getData().get(i).getType().equals("textarea")){
                linearLayoutRight.setOrientation(LinearLayout.HORIZONTAL);
                EditText tvText = new EditText(this);
                tvText.setHint("请输入"+info.getData().get(i).getTitle());
                tvText.setBackground(null);
                tvText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

                mHashMapViews.put(info.getData().get(i).getIdentifier(),tvText) ;

                linearLayoutRight.addView(tvText) ;



            }else if(info.getData().get(i).getType().equals("checkbox")){


                linearLayoutRight.setOrientation(LinearLayout.VERTICAL);

                String choices = info.getData().get(i).getExtra().getChoices();
                final String[] arrayChoices = choices.split("\\r\\n") ;

                TextView tvText = new TextView(this);
                tvText.setHint("请选择"+info.getData().get(i).getTitle());
                tvText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

                linearLayoutRight.addView(tvText) ;
                final String title = info.getData().get(i).getTitle() ;
                tvText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        List<CheckInfo> list = new ArrayList<>() ;

                        for(int k = 0 ;k < arrayChoices.length ;k++){
                            CheckInfo bean = new CheckInfo() ;
                            String[] text = arrayChoices[k].split("=") ;
                            bean.setInfo(text[1]);
                            bean.setId(text[0]) ;
                            list.add(bean) ;
                        }

                        initCheckPopupWindow(list,title,false) ;
                    }
                });

                *//*linearLayoutRight.setOrientation(LinearLayout.VERTICAL);

                 *//**//*	LinearLayout llContainer = new LinearLayout(this);
				llContainer.setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
				llContainer.setOrientation(LinearLayout.VERTICAL);

				String choices = info.getData().get(i).getExtra().getChoices();
				String[] arrayChoices = choices.split("\\r\\n") ;

				LinearLayout groupLl = null ;

				for(int j = 0 ; j< arrayChoices.length ; j++){

					final CheckBox checkButton = new CheckBox(this) ;
					int index = arrayChoices[j].indexOf("=") ;
					checkButton.setText(arrayChoices[j].substring(index+1,arrayChoices[j].length()));
					checkButton.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT)) ;

					checkButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {

						}
					});

					if(j % 3 == 0){
						groupLl = new LinearLayout(this);
						groupLl.setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
						groupLl.setOrientation(LinearLayout.HORIZONTAL);
						llContainer.addView(groupLl);
					}
					groupLl.addView(checkButton);
				}*//**//*

				linearLayoutRight.addView(llContainer);*//*

            }else if(info.getData().get(i).getType().equals("link")){

                linearLayoutRight.setOrientation(LinearLayout.VERTICAL);

                TextView tvText = new TextView(this);
                tvText.setHint("请选择"+info.getData().get(i).getTitle());
                tvText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,0.2f));

                linearLayoutRight.addView(tvText) ;

                final String link = info.getData().get(i).getExtra().getParentid()  ;
                final String title = info.getData().get(i).getTitle() ;
                tvText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        initPopupWindow(title,link,false) ;
                    }
                });

            }

            LinearLayout ll = new LinearLayout(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, DeviceUtil.dip2px(mContext,50)) ;

            ll.setLayoutParams(params);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            ll.addView(linearLayoutLeft);
            ll.addView(linearLayoutRight);

            ll.setGravity(Gravity.CENTER_VERTICAL);

            mLLDaymic.addView(ll);
        }*/
    }


    private void initView() {

        mLLPro = findViewById(R.id.ll_add_pro) ;
        mTvTipInfo = findViewById(R.id.tv_tip_contnet) ;
        mTvBaseInfo = findViewById(R.id.tv_base_info) ;
        mTvPrice = findViewById(R.id.tv_price) ;
        mIvAttention = findViewById(R.id.iv_guanzhu);
        mIvDot = findViewById(R.id.iv_dian);
        mIvShare = findViewById(R.id.iv_share);

        mLLWechat = findViewById(R.id.ll_wechat);
        mLLMessage = findViewById(R.id.ll_message);
        mLLOrder = findViewById(R.id.ll_order);
        mTvDes = findViewById(R.id.tv_des);
        mTvPlace = findViewById(R.id.tv_place);
        mTvContentTitle = findViewById(R.id.tv_title);
        mPoints = findViewById(R.id.layout_points);
        mBannerViewPaper = findViewById(R.id.vp_banner);

        mTvTitle = findViewById(R.id.tv_main_info);
        mRlBack = findViewById(R.id.ll_back);
        mRlBack.setOnClickListener(this);
        mTvTitle.setText("详情");
        mRlBack.setVisibility(View.VISIBLE);

        mLLWechat.setOnClickListener(this);
        mLLMessage.setOnClickListener(this);
        mLLOrder.setOnClickListener(this);

        mIvAttention.setOnClickListener(this);
        mIvDot.setOnClickListener(this);
        mIvShare.setOnClickListener(this);
    }


    private void attentAction(String id) {

        if (TextUtils.isEmpty(id)) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Shoucang.Add");
        map.put("infoid", id);

        map.put("sign", UtilsTools.getSign(mContext, "App.Shoucang.Add"));

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("liujw", "##########################IOException : " + e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = new String(responseStr.getBytes());
                        Gson gson = new Gson();
                        AttentionInfo bean = gson.fromJson(message, AttentionInfo.class);

                        if (bean.getData().isIs_success()) {
                            WindowsToast.makeText(mContext, bean.getData().getInfo()).show();
                            mIvAttention.setImageResource(R.mipmap.btn_guanzhu_pre);
                        } else {
                            WindowsToast.makeText(mContext, bean.getData().getInfo()).show();
                        }
                    }
                });
            }
        });


    }

    private CatoryDetailInfo mCategoryBean;

    private void getCatoryDetail(String id) {

        if (TextUtils.isEmpty(id)) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Info.Show");
        map.put("id", id);

        map.put("sign", UtilsTools.getSign(mContext, "App.Info.Show"));

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(Constants.HOST + "?" + UtilsTools.getMapToString(map)).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("liujw", "##########################IOException : " + e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = new String(responseStr.getBytes());
                        Gson gson = new Gson();
                        mCategoryBean = gson.fromJson(message, CatoryDetailInfo.class);

                        List<CatoryDetailInfo.DataBean.ImageBean> images = mCategoryBean.getData().getImage();

                        if(!TextUtils.isEmpty(mCategoryBean.getData().getVideo())){
                            CategoryDetailHeader bean = new CategoryDetailHeader() ;
                            bean.setFilePath(mCategoryBean.getData().getVideo());
                            bean.setType(1);
                            bean.setThumbPath(mCategoryBean.getData().getImg_path());
                            modelList.add(bean) ;
                        }

                        for(int i = 0 ;i < images.size() ;i++){
                            CategoryDetailHeader bean = new CategoryDetailHeader() ;
                            bean.setFilePath(images.get(i).getPath());
                            bean.setType(0);
                            modelList.add(bean) ;
                        }

                        initViewPager();
                        setDataView(mCategoryBean);
                    }
                });
            }
        });


    }


    /*
    *
        /m/template/images/qq.png  这是QQ的图标
        /m/template/images/01.png  这是电话的图标

    * */
    private String mId;

    private void initData() {
        mId = getIntent().getStringExtra("id");
    }

    private void setDataView(CatoryDetailInfo bean) {

        mTvContentTitle.setText(bean.getData().getTitle());
        mTvPlace.setText(bean.getData().getAreaname());
        mTvDes.setText(Html.fromHtml(bean.getData().getContent()));

        String extra = "" ;

        if(bean.getData().getExtra() != null){
            for(int i = 0 ;i < bean.getData().getExtra().size() ;i++){
                extra += bean.getData().getExtra().get(i).getTitle()+" : "+bean.getData().getExtra().get(i).getValue()+"\n\n";
            }
        }

        mTvBaseInfo.setText("联系人 ："+bean.getData().getContact_who()+"\n\n"
                            +"QQ ："+bean.getData().getQq()+"\n\n"
                            +"微信 ："+bean.getData().getWeixin()+"\n\n"
                            +extra);
        mTvTipInfo.setText("1: 聚民网不承担任何交易损\n\n2 :本页信息由用户和第三方平台提供\n\n3: 联系我时，说是在聚民网看到的");
    }

    private ShareDialog mShareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        initView();
        initData();
        //mId = "20719" ;
       // mId = "21349" ;
        getCatoryDetail(mId);

        /*mShareDialog = new ShareDialog(mContext);
        mShareDialog.setOnClickListener(new ShareDialog.OnClickListener() {
            @Override
            public void OnClick(View v, int position) {
               *//* switch (position) {
                    case 0:
                        showShare(QQ.NAME);
                        break;
                    case 1:
                        showShare(Wechat.NAME);
                        break;
                    case 2:
                        showShare(WechatMoments.NAME);
                        break;
                    case 3:
                        showShare(SinaWeibo.NAME);
                        break;
                }*//*
            }
        });*/
    }

    /**
     * 拨打电话（直接拨打电话）
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    public void doSendSMSTo(String phoneNumber,String message){
       // if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            intent.putExtra("sms_body", message);
            startActivity(intent);
      // }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_back :
                finish();
                break ;
            case R.id.ll_message :
                if(mCategoryBean != null){
                    doSendSMSTo(mCategoryBean.getData().getTel(),"") ;
                }

                break ;
            case R.id.ll_order :
                if(mCategoryBean != null) {
                    callPhone(mCategoryBean.getData().getTel());
                }
                break ;
            case R.id.ll_wechat :
                String info = "" ;
                if(TextUtils.isEmpty((String)mCategoryBean.getData().getWeixin())){
                    info = "微信号: 暂无" ;
                }else{
                    info = "微信号："+(String)mCategoryBean.getData().getWeixin() ;
                }
                WeiXinDialog dialog1 = new WeiXinDialog(this,info) ;
                dialog1.show();
                break ;
            case R.id.iv_share :
                if(!mShareDialog.isShowing()){
                    mShareDialog.show();
                }
               // showShare(QQ.NAME) ;
                break ;
            case R.id.iv_guanzhu :
                attentAction(mId) ;
                break ;
            case R.id.iv_dian :

                break ;
        }
    }
}
