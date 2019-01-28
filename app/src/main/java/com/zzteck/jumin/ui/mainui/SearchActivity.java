package com.zzteck.jumin.ui.mainui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.SearchAdapter;
import com.zzteck.jumin.bean.SearchBean;
import com.zzteck.zzview.WJSpandGridView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout mLLBack ;

    private WJSpandGridView mGvSearchHint ;

    private List<SearchBean> mSearchList = new ArrayList<>();

    private SearchAdapter mSearchAdapter ;

    private void initView(){
        mLLBack = findViewById(R.id.ll_back) ;
        mGvSearchHint = findViewById(R.id.gv_search) ;
        mLLBack.setOnClickListener(this);
    }

    private String[] searchArray = new String[]{"春秋战国","明清时期","秦汉时期","三国两晋南北朝","上古三代","宋元时期","隋唐五代","新石器时代"} ;

    private void initData(){
        for(int i = 0 ;i < 8 ;i++){
            SearchBean bean = new SearchBean() ;
            bean.setSearch(searchArray[i]) ;
            mSearchList.add(bean);
        }

        mSearchAdapter = new SearchAdapter(mContext,mSearchList) ;
        mGvSearchHint.setAdapter(mSearchAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView(R.layout.activity_search);
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
