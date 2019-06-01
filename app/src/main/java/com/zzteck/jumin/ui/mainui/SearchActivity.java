package com.zzteck.jumin.ui.mainui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.SearchAdapter;
import com.zzteck.jumin.bean.CategoryBean;
import com.zzteck.jumin.bean.SearchBean;
import com.zzteck.jumin.bean.SearchListBean;
import com.zzteck.jumin.ui.business.CategoryListActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.GlideCircleTransform;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.zzview.WJSpandGridView;

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

public class SearchActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout mLLBack ;

    private WJSpandGridView mGvSearchHint ;

    private List<SearchBean> mSearchList = new ArrayList<>();

    private SearchAdapter mSearchAdapter ;

    private TextView mTvSearch ;

    private void initView(){
        mEtSearch = findViewById(R.id.tv_search) ;
        mTvSearch = findViewById(R.id.iv_search) ;
        mLLBack = findViewById(R.id.ll_back) ;
        mGvSearchHint = findViewById(R.id.gv_search) ;
        mLLBack.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);
    }

    private void getSearch(String catid,String keywords,String cityId,String page) {

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Info.Search");
        if(!TextUtils.isEmpty(catid)){
            map.put("catid", catid);
        }

        if(!TextUtils.isEmpty(cityId)){
            map.put("cityId", cityId);
        }
        map.put("keywords", keywords);
        map.put("page", page);
        map.put("sign", UtilsTools.getSign(this,"App.Info.Search"));

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

                        try{
                            Gson gson = new Gson();
                            SearchListBean  searchListBean = gson.fromJson(responseStr, SearchListBean.class);
                            if (searchListBean.getData() != null && searchListBean.getData().size() > 0) {
                                Intent intent = new Intent(mContext, CategoryListActivity.class) ;
                                intent.putExtra("title","搜索") ;
                                intent.putExtra("id",1+"") ;
                                startActivity(intent);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    private void getHotSearchPort(String catid) {

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Info.Hotsearch");

        if(!TextUtils.isEmpty(catid)){
            map.put("catid", catid);
        }

        map.put("sign", UtilsTools.getSign(this,"App.Info.Hotsearch"));

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
                        Gson gson = new Gson();
                        SearchBean searchBean = gson.fromJson(responseStr, SearchBean.class);
                        if (searchBean.getData() != null && searchBean.getData().size() > 0) {
                           mSearchAdapter.notifySearchDataSet(searchBean.getData());
                        }
                    }
                });
            }
        });

    }

    private String mCatId  ;

    private EditText mEtSearch ;

    private void initData(){

        Intent intent = getIntent() ;
        if(intent != null){
            mCatId = intent.getStringExtra("catid") ;
        }
        mSearchAdapter = new SearchAdapter(mContext,null) ;
        mGvSearchHint.setAdapter(mSearchAdapter);
        mGvSearchHint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                getSearch("",mSearchAdapter.getmSearchBeanList().get(i).getKeywords(),"",1+"");
               /* Intent intent = new Intent(mContext, CategoryListActivity.class) ;
                intent.putExtra("title","搜索") ;
                intent.putExtra("id",1+"") ;
                startActivity(intent);*/
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView(R.layout.activity_search);
        initView() ;
        initData();
        getHotSearchPort(mCatId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back :
                finish();
                break ;
            case R.id.iv_search :
                String keywords = "" ;
                if(TextUtils.isEmpty(mEtSearch.getText().toString().trim())){
                    keywords = mEtSearch.getHint().toString().trim() ;
                }else{
                    keywords = mEtSearch.getText().toString().trim() ;
                }
                getSearch("",keywords,"",1+"");
                break ;
        }
    }
}
