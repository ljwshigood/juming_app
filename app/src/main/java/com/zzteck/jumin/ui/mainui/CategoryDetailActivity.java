package com.zzteck.jumin.ui.mainui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.SearchBean;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout mLLBack ;

    private List<SearchBean> mSearchList = new ArrayList<>();

    private void initView(){
        mLLBack = findViewById(R.id.ll_back) ;
        mLLBack.setOnClickListener(this);
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
