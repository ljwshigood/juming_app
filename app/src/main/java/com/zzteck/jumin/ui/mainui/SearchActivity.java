package com.zzteck.jumin.ui.mainui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.SearchAdapter;
import com.zzteck.jumin.bean.SearchBean;
import com.zzteck.jumin.ui.business.CategoryListActivity;
import com.zzteck.zzview.WJSpandGridView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout mLLBack ;

    private WJSpandGridView mGvSearchHint ;

    private List<SearchBean> mSearchList = new ArrayList<>();

    private SearchAdapter mSearchAdapter ;

    private TextView mTvSearch ;

    private void initView(){
        mTvSearch = findViewById(R.id.iv_search) ;
        mLLBack = findViewById(R.id.ll_back) ;
        mGvSearchHint = findViewById(R.id.gv_search) ;
        mLLBack.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);
    }

    private String[] searchArray = new String[]{"找工作","二手房","租房","房产","司机","找房子","兼职"} ;

    private void initData(){
        for(int i = 0 ;i < 7 ;i++){
            SearchBean bean = new SearchBean() ;
            bean.setSearch(searchArray[i]) ;
            mSearchList.add(bean);
        }

        mSearchAdapter = new SearchAdapter(mContext,mSearchList) ;
        mGvSearchHint.setAdapter(mSearchAdapter);
        mGvSearchHint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, CategoryListActivity.class) ;
                intent.putExtra("title","搜索") ;
                intent.putExtra("id",1+"") ;
                startActivity(intent);
            }
        });
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
            case R.id.iv_search :
                Intent intent = new Intent(mContext, CategoryListActivity.class) ;
                intent.putExtra("title","搜索") ;
                intent.putExtra("id",1+"") ;
                startActivity(intent);
                break ;
        }
    }
}
