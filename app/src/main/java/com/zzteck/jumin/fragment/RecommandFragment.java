package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.RecommandAdapter;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.jumin.ui.business.CategoryDetailActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecommandFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRlCategory;

    private RecommandAdapter recommandAdapter;

    private void initView(View view) {
        mRlCategory = view.findViewById(R.id.rl_history) ;
    }

    private void initData(HomeInfo info) {

        mRlCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlCategory.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));
        recommandAdapter = new RecommandAdapter(getActivity(),info.getData()) ;
        mRlCategory.setAdapter(recommandAdapter) ;

        recommandAdapter.setNoMore(R.layout.view_no_more);
        recommandAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if(mCategoryId != null){
                    mCurrentPage++ ;
                    getInfosList(mCategoryId,0+"",mCurrentPage+"") ;
                }

            }

            @Override
            public void onMoreClick() {

            }
        });

        recommandAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), CategoryDetailActivity.class) ;
                startActivity(intent);
            }
        });

    }

    private int mCurrentPage  = 1 ;

    private String mCategoryId ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_recommand, container, false);
        mContext = getActivity();
        initView(mMainView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mCategoryId = bundle.getString("item");
            Log.e("liujw","#################item : "+mCategoryId) ;
            getInfosList(mCategoryId,0+"",mCurrentPage+"") ;
        }

        return mMainView;
    }


    /**
     * @param catId
     * @param cityId
     * @param pages
     */
    private void getInfosList(final String catId, final String cityId, String pages){

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Info.Getinfos") ;
        map.put("catid",catId) ;
        map.put("cityid",cityId) ;
        map.put("pages",pages) ;


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

                Log.e("liujw","##########################getInfosList catId : "+catId+" #####################: "+responseStr);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String message = new String(responseStr) ;
                        Gson gson = new Gson() ;
                        HomeInfo info = gson.fromJson(message,HomeInfo.class) ;
                        if(mCurrentPage == 1){
                            initData(info);
                        }else{
                            if(recommandAdapter != null){
                                recommandAdapter.addAll(info.getData());
                            }

                        }


                    }
                });
            }
        });

    }


}
