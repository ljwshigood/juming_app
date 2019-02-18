package com.zzteck.jumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.RecommandAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

import java.util.HashMap;
import java.util.Map;

public class RecommandFragment extends Fragment {

    private View mMainView;

    private Context mContext;

    private RecyclerView mRlFavorite ;

    private RecommandAdapter recommandAdapter;

    private void initView(View view) {
        mRlFavorite = view.findViewById(R.id.rl_history) ;
    }

    private void initData() {

        mRlFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlFavorite.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));
        recommandAdapter = new RecommandAdapter(getActivity(),null) ;
        mRlFavorite.setAdapter(recommandAdapter) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_recommand, container, false);
        mContext = getActivity();
        initView(mMainView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String item = bundle.getString("item");
            Log.e("liujw","#################item : "+item) ;
            getInfosList(item,0+"",1+"") ;
        }
        initData();
        return mMainView;
    }


    /**
     * @param catId
     * @param cityId
     * @param pages
     */
    private void getInfosList(String catId,String cityId,String pages){

        Map<String, String> map = new HashMap<>() ;
        map.put("s","App.Info.Getinfos") ;
        map.put("catid",catId) ;
        map.put("cityid",cityId) ;
        map.put("pages",pages) ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.HOST+"?"+ UtilsTools.getMapToString(map), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String message = new String(response.getBytes()) ;
                Gson gson = new Gson() ;
                HomeInfo info = gson.fromJson(message,HomeInfo.class) ;



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setTag("");
        App.getHttpQueues().add(stringRequest);

    }


}
