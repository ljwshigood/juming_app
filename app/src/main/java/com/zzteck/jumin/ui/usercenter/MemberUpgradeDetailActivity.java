package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MemeberAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.UpgradeMemeberBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.view.NormalDecoration;

import java.util.ArrayList;
import java.util.List;


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

    private void initView() {

        mIvMemeber = findViewById(R.id.iv_memeber) ;
        mRlBack = findViewById(R.id.ll_back);
        mTvTitle = findViewById(R.id.tv_main_info);
        mTvTitle.setText("会员升级");
        mRlBack.setOnClickListener(this);
        mRlBack.setVisibility(View.VISIBLE);

        mSpinnerUserType = findViewById(R.id.sp_user_type);
        mSpinnerUserType.setItems("一个月", "两个月", "半年", "一年", "五年");
        mSpinnerUserType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Logger.e(item);
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
        }
    }

}
