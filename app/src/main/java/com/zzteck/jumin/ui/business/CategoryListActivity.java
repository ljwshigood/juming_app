package com.zzteck.jumin.ui.business;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.ccj.poptabview.FilterConfig;
import com.ccj.poptabview.PopTabView;
import com.ccj.poptabview.base.BaseFilterTabBean;
import com.ccj.poptabview.bean.FilterGroup;
import com.ccj.poptabview.listener.OnPopTabSetListener;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.Recommand2Adapter;
import com.zzteck.jumin.adapter.RecommandAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.FilterInfo;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.jumin.bean.MyFilterConfig;
import com.zzteck.jumin.bean.MyFilterParamsBean;
import com.zzteck.jumin.bean.MyFilterTabBean;
import com.zzteck.jumin.bean.MyPopEntityLoaderImp;
import com.zzteck.jumin.bean.MyResultLoaderImp;
import com.zzteck.jumin.bean.ReleaseDataBean;
import com.zzteck.jumin.bean.SearchListBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.SharePerfenceUtil;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CategoryListActivity extends BaseActivity implements OnClickListener, OnPopTabSetListener<MyFilterParamsBean> {

    private String TAG = "liujw";

    private View mMainView;

    private Context mContext;

    private RecyclerView mGvCommand;

    private Recommand2Adapter mCommandAdapter;

    private TextView mTvTitle;

    private RelativeLayout mRlBack;

    private PopTabView popTabView;

    private void initView() {
        mGvCommand = findViewById(R.id.rl_cate_list);
        mTvTitle = findViewById(R.id.tv_main_info);
        mRlBack = findViewById(R.id.ll_back);
        mRlBack.setOnClickListener(this);
        mTvTitle.setText("二手房");
        mRlBack.setVisibility(View.VISIBLE);

        popTabView = findViewById(R.id.expandpop);

    }

    private void getInfoExtra(final String catId) {

        if (TextUtils.isEmpty(catId)) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Info.Extra");
        map.put("catid", catId);

        map.put("sign", UtilsTools.getSign(mContext, "jumin_" + "App.Info.Extra"));

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
                            String message = new String(responseStr);
                            Gson gson = new Gson();
                            FilterInfo info = gson.fromJson(message, FilterInfo.class);
                            addMyMethod(info);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }

    private int mCurrentPage = 1;

    /**
     * @param catId
     * @param cityId
     * @param pages
     */
    private void getInfosList(final String catId, final String cityId, String pages, String json) {

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Info.Getinfos");
        map.put("catid", catId);
        map.put("cityid", cityId);
        map.put("pages", pages);
        if (mJson != null) {
            map.put("extra", json);
        }

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
                //final String responseStr = "{\"ret\":200,\"data\":[],\"msg\":\"\"}" ;

                Log.e("liujw", "##########################getInfosList catId : " + catId + " #####################: " + responseStr);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try{
                            String message = new String(responseStr);
                            Gson gson = new Gson();
                            HomeInfo info = gson.fromJson(message, HomeInfo.class);
                            if (mCurrentPage == 1) {
                                initData(info);
                            } else {
                                if (mCommandAdapter != null) {
                                    mCommandAdapter.addAll(info.getData());
                                }

                            }
                        }catch (Exception e){

                            Log.e("liujw","####################getInfosList Exception "+e.toString());
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }


    public static int dip2px(Context context, float dipValue) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f); //+0.5是为了向上取整
    }

    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);//+0.5是为了向上取整
    }

    private void initData(HomeInfo info) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mGvCommand.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, dip2px(this,0.5f), 0,0);
        itemDecoration.setDrawLastItem(false);
        mGvCommand.addItemDecoration(itemDecoration);

        mCommandAdapter = new Recommand2Adapter(this, info.getData());
        mGvCommand.setAdapter(mCommandAdapter);

        mCommandAdapter.setNoMore(R.layout.view_no_more);
        mCommandAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                Log.e("liujw", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@onMoreShow");
                if (mId != null) {
                    mCurrentPage++;
                    getInfosList(mId, 0 + "", mCurrentPage + "", mJson);
                }

            }

            @Override
            public void onMoreClick() {

            }
        });

        mCommandAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(mContext, CategoryDetailActivity.class);
                intent.putExtra("id",mCommandAdapter.getmHomeList().get(position).getId());
                intent.putExtra("catid",mCommandAdapter.getmHomeList().get(position).getCatid()) ;
                startActivity(intent);

            }
        });

    }


    /**
     * 模拟数据
     * 筛选器的 数据格式 都是大同小异
     * 要点:泛型处理,集合都用父类,实体都用子类表示.
     *
     * @return
     */
    public FilterGroup getMyData(FilterInfo.DataBean bean, int groupType, int singleOrMutiply) {

        FilterGroup filterGroup = new FilterGroup();

        filterGroup.setTab_group_name(bean.getTitle());
        filterGroup.setTab_group_type(groupType);
        filterGroup.setSingle_or_mutiply(singleOrMutiply);

        List<BaseFilterTabBean> singleFilterList = new ArrayList<>();

        String[] choices = bean.getExtra().getChoices().split("\\r\\n");


        for (int i = 0; i < choices.length; i++) {//一级fitler

            String tempChoices = choices[i];

            String[] splite = tempChoices.split("=");

            MyFilterTabBean myFilterBean = new MyFilterTabBean();
            myFilterBean.setTab_name(splite[1]);
            myFilterBean.setTag_ids(bean.getIdentifier());
            myFilterBean.setMall_ids("mallid" + "_" + i);
            myFilterBean.setCategory_ids(splite[0]);

			/*List<MyFilterTabBean.MyChildFilterBean> childFilterList = new ArrayList<>();
			for (int j = 0; j < 5; j++) {//二级filter
				MyFilterTabBean.MyChildFilterBean myChildFilterBean = new MyFilterTabBean.MyChildFilterBean();
				myChildFilterBean.setTab_name(groupName + "_" + i + "__" + j);
				myChildFilterBean.setTag_ids("tagid" + "_" + i + "__" + j);
				myChildFilterBean.setMall_ids("mallid" + "_" + i + "__" + j);
				myChildFilterBean.setCategory_ids("Categoryid" + "_" + i + "__" + j);

				childFilterList.add(myChildFilterBean);
			}
			//增加二级tab
			myFilterBean.setTabs(childFilterList);*/

            //增加一级tab
            singleFilterList.add(myFilterBean);

        }

        filterGroup.setFilter_tab(singleFilterList);
        return filterGroup;

    }

    private void iteratorMap(Map map){
        if(map == null){
            return ;
        }
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            list.add((FilterInfo.DataBean) entry.getValue()) ;
        }
    }


    private List<FilterInfo.DataBean> list = new ArrayList<>() ;

    private void addMyMethod(FilterInfo info) {


        if (info == null || info.getData() == null) {
            return;
        }

        iteratorMap(info.getData()) ;


        PopTabView ptv = popTabView.setOnPopTabSetListener(this).setPopEntityLoader(new MyPopEntityLoaderImp()).setResultLoader(new MyResultLoaderImp());

        for (int i = 0; i < list.size(); i++) {
            FilterGroup filterGroup = getMyData(list.get(i), MyFilterConfig.TYPE_POPWINDOW_SINGLE, MyFilterConfig.FILTER_TYPE_SINGLE);
            ptv.addFilterItem(filterGroup.getTab_group_name(), filterGroup.getFilter_tab(), filterGroup.getTab_group_type(), filterGroup.getSingle_or_mutiply());
        }

    }


    private String mJson ;

    private  Map mFilterMap = new HashMap();

    private String mCityId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_categroy_list);
        mContext = CategoryListActivity.this;
        App.getInstance().addActivity(this);

        mCityId = (String) SharePerfenceUtil.getParam(mContext,"city_id","0");

        initView();
        initData();
        getInfoExtra(mId);


        getInfosList(mId, mCityId + "", mCurrentPage + "", mJson);


    }

    private String mId;

    private List<SearchListBean.DataBean> mSearchList ;

    private void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        mSearchList = (List<SearchListBean.DataBean>) intent.getSerializableExtra("list");
        mId = intent.getStringExtra("id");
        mTvTitle.setText(title + "");
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

    @Override
    public void onPopTabSet(int index, String lable, MyFilterParamsBean params, String value) {
        if (!TextUtils.isEmpty(value)) {
            mFilterMap.put(params.getBeanList().get(0).getTag_ids(),params.getBeanList().get(0).getCategory_ids()) ;
        }

        if(mFilterMap.size() > 0){

            mCurrentPage = 1 ;
            Gson gson = new Gson() ;
            mJson =  gson.toJson(mFilterMap);
            Log.e("liujw", "#################onPopTabSet json : " + mJson);
            getInfosList(mId, 0 + "", mCurrentPage + "", mJson);

        }

        Log.e("liujw", "#################onPopTabSet : " + "&筛选项=" + index + "\n&筛选传参=" + params + "\n&筛选值=" + value);
    }
}
