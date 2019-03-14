package com.zzteck.jumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.CategoryListAdapter;
import com.zzteck.jumin.bean.ChildCategoryBean;
import com.zzteck.jumin.bean.MainCategoryBean;
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

public class CategoryFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRlCate;

    private CategoryListAdapter mGategoryListAdapter;

    private void initView(View view) {
        mRlCate = view.findViewById(R.id.rl_cate) ;
    }

    private void getCategoryList(String catid) {

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Category.Lists");
        map.put("catid", catid);


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
                if(getActivity() == null){
                    return ;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = new String(responseStr);
                        Gson gson = new Gson();
                        ChildCategoryBean bean = gson.fromJson(message, ChildCategoryBean.class);
                        mGategoryListAdapter.notifyCategoryList(bean.getData());
                    }
                });
            }
        });
    }

    private void initData() {
        mRlCate.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRlCate.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));
        mGategoryListAdapter = new CategoryListAdapter(getActivity(),null) ;
        mRlCate.setAdapter(mGategoryListAdapter) ;
    }

    private String mCategoryId ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_category, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCategoryId = bundle.getString("item");
            Log.e("liujw","#################item : "+mCategoryId) ;
            getCategoryList(mCategoryId) ;
        }
        return mMainView;
    }

}
