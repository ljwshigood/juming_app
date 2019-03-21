package com.zzteck.jumin.ui.business;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.SearchBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailActivity extends BaseActivity implements View.OnClickListener{

    private TextView mTvTitle ;

    private RelativeLayout mRlBack;

    private void initView(){
        mTvTitle = findViewById(R.id.tv_main_info) ;
        mRlBack = findViewById(R.id.ll_back) ;
        mRlBack.setOnClickListener(this);
        mTvTitle.setText("详情");
        mRlBack.setVisibility(View.VISIBLE);
    }

    private void initData(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView(R.layout.activity_category_detail);
        initView() ;
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back :
                finish();
                break ;
        }
    }
}
