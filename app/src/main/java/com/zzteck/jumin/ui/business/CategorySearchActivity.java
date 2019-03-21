package com.zzteck.jumin.ui.business;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.SearchAdapter;
import com.zzteck.jumin.bean.SearchBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.zzview.WJSpandGridView;

import java.util.ArrayList;
import java.util.List;

public class CategorySearchActivity extends BaseActivity implements View.OnClickListener{

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
        setContentView(R.layout.activity_category_search);
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
