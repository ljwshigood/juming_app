package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MemeberAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.AttentionList;
import com.zzteck.jumin.bean.UpgradeMemInfo;
import com.zzteck.jumin.bean.UpgradeMemeberBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

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


public class MemberUpgradeDetailActivity extends BaseActivity implements OnClickListener {

    private MaterialSpinner mSpinnerUserType;

    private TextView mTvTitle;

    private RelativeLayout mRlBack;

    private int mType;

    private ImageView mIvMemeber ;

    private int[] res = new int[]{R.mipmap.pic_putong,R.mipmap.pic_huangjin,R.mipmap.pic_baiyin,R.mipmap.pic_zhuanshi} ;

    private void initData() {

        mType = getIntent().getIntExtra("position", -1);

        if(mType != -1){
            Glide.with(mContext)
                    .load(res[mType])
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .crossFade(300)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mIvMemeber);
        }
    }

    private LinearLayout mLLComplete ;

    private int mLevelType ;

    private void initView() {

        mLLComplete = findViewById(R.id.ll_complete) ;
        mIvMemeber = findViewById(R.id.iv_memeber) ;
        mRlBack = findViewById(R.id.ll_back);
        mTvTitle = findViewById(R.id.tv_main_info);
        mTvTitle.setText("会员升级");
        mRlBack.setOnClickListener(this);
        mRlBack.setVisibility(View.VISIBLE);

        mLLComplete.setOnClickListener(this);

        mSpinnerUserType = findViewById(R.id.sp_user_type);
        mSpinnerUserType.setItems("一个月", "两个月", "半年", "一年", "五年");
        mSpinnerUserType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                mLevelType = position;
                //Logger.e(item);
            }
        });

    }

    private void levelUp(String forwardlevel,String veluptime){

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Member.Levelup") ;
        map.put("forwardlevel",forwardlevel) ;
        map.put("leveluptime",veluptime) ;

        map.put("sign", UtilsTools.getSign(mContext,"App.Member.Levelup")) ;

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
                        UpgradeMemInfo bean = gson.fromJson(message,UpgradeMemInfo.class) ;
                    }
                });
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_memeber_upgrade_detail);

        mContext = MemberUpgradeDetailActivity.this;
        App.getInstance().addActivity(this);

        initView();
        initData();
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
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_complete :
                levelUp("5",mLevelType+"");
                break ;
        }
    }

}
