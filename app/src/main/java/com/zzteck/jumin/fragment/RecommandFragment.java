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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.RecommandAdapter;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.jumin.ui.business.CategoryDetailActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.SharePerfenceUtil;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.IOException;
import java.util.ArrayList;
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

    private EasyRecyclerView mRVCategory;

    private RecommandAdapter recommandAdapter;

    private void initView(View view) {
        mRVCategory = view.findViewById(R.id.rl_history) ;
    }

    public ITransferScrollListener getmITransferScrollListener() {
        return mITransferScrollListener;
    }

    public void setmITransferScrollListener(ITransferScrollListener mITransferScrollListener) {
        this.mITransferScrollListener = mITransferScrollListener;
    }

    private ITransferScrollListener mITransferScrollListener ;

    public interface  ITransferScrollListener{
        public void transferScrollListener(boolean flag) ;
    }

    private void initData(HomeInfo info) {

        mRVCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRVCategory.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));

        if(mITransferScrollListener != null){
            if(info == null || info.getData() == null || info.getData().size() == 0){
                mITransferScrollListener.transferScrollListener(true);
            }else{
                mITransferScrollListener.transferScrollListener(false);
            }
        }

        recommandAdapter = new RecommandAdapter(getActivity(),info.getData()) ;
        mRVCategory.setAdapter(recommandAdapter) ;


        mRVCategory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("liujw","#################onTouch mRVCategory");

                return false;
            }
        });
        recommandAdapter.setNoMore(R.layout.view_no_more);
        recommandAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if(mCategoryId != null){
                    mCurrentPage++ ;
                    getInfosList(mCategoryId,mCurrentPage+"") ;
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
                HomeInfo.DataBean bean = recommandAdapter.getItem(position) ;
                intent.putExtra("id",bean.getId()) ;
                startActivity(intent);
            }
        });

    }

    private int mCurrentPage  = 1 ;

    private String mCategoryId ;

    @Subscriber
    public void onEventMainThread(int event) {
        mCurrentPage = 1 ;
        getInfosList(mCategoryId,mCurrentPage+"") ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_recommand, container, false);
        mContext = getActivity();
        initView(mMainView);

        EventBus.getDefault().register(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mCategoryId = bundle.getString("item");
            Log.e("liujw","#################item : "+mCategoryId) ;
            getInfosList(mCategoryId,mCurrentPage+"") ;
        }

        return mMainView;
    }

    public void refresh(){
        mCurrentPage = 1;
        getInfosList(mCategoryId,mCurrentPage+"") ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /**
     * @param catId
     * @param pages
     */
    private void getInfosList(final String catId,String pages){

        String cityId = (String) SharePerfenceUtil.getParam(mContext,"city_id","");

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Info.Getinfos") ;
        map.put("catid",catId) ;
       // map.put("cityid",cityId) ;
        map.put("cityid",95+"") ;
        map.put("pages",pages) ;

        map.put("sign", UtilsTools.getSign(getActivity(),"jumin_"+"App.Info.Getinfos"));

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

                Log.e("liujw","##########################getInfosList message : "+responseStr);

                if(getActivity() == null){
                    return ;
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try{

                            String message = new String(responseStr) ;

                            Log.e("liujw","##########################getInfosList catId : "+catId+" #####################: "+responseStr);
                            Gson gson = new Gson() ;
                            HomeInfo info = gson.fromJson(message,HomeInfo.class) ;
                            if(mCurrentPage == 1){
                                initData(info);
                            }else{
                                if(recommandAdapter != null){
                                    recommandAdapter.addAll(info.getData());
                                }
                            }
                        }catch (Exception e){
                            if(mITransferScrollListener != null){
                                mITransferScrollListener.transferScrollListener(true);
                            }
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }


}