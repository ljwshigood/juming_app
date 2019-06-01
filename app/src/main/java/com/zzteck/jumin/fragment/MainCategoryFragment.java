package com.zzteck.jumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.HomeCategoryAdapter;
import com.zzteck.jumin.adapter.MessageAdapter;
import com.zzteck.jumin.bean.MainCategoryBean;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.NormalDecoration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainCategoryFragment extends Fragment implements View.OnClickListener{


    private RecyclerView mRecyleView;

    private FrameLayout mFrameLayout;

    private View mMainView;

    private Context mContext;

    private List<MainCategoryBean.DataBean> mTitlesList = new ArrayList<>() ;

    private void getMainCategoryList(){

        Map<String, String> map = new HashMap<>();
        map.put("s", "App.Category.Lists");

        map.put("sign", UtilsTools.getSign(getActivity(),"App.Category.Lists"));

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

                Log.e("liujw", "##########################getMainCategoryList responseStr : " + responseStr);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = new String(responseStr);
                        Gson gson = new Gson();
                        MainCategoryBean bean = gson.fromJson(message,MainCategoryBean.class) ;
                        initData(bean);
                    }
                });
            }
        });
    }

    private HomeCategoryAdapter mHomeCategory ;

    private void initView(View view) {

        mRecyleView = view.findViewById(R.id.scrollview);
        mFrameLayout = view.findViewById(R.id.framelayout);
    }

    public class SmoothScrollLayoutManager extends LinearLayoutManager {

        public SmoothScrollLayoutManager(Context context) {
            super(context);
        }

        public SmoothScrollLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public SmoothScrollLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView,
                                           RecyclerView.State state, final int position) {

            LinearSmoothScroller smoothScroller =
                    new LinearSmoothScroller(recyclerView.getContext()) {
                        @Override
                        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                            return 150f / displayMetrics.densityDpi;
                        }
                    };

            smoothScroller.setTargetPosition(position);
            startSmoothScroll(smoothScroller);
        }
    }

    private SmoothScrollLayoutManager mSmoothScrollLayoutManager ;

    private void initData(final MainCategoryBean bean) {
        mSmoothScrollLayoutManager = new SmoothScrollLayoutManager(getActivity()) ;
        mRecyleView.setLayoutManager(mSmoothScrollLayoutManager);
        mRecyleView.addItemDecoration(new NormalDecoration(ContextCompat.getColor(getActivity(), R.color.mainGrayF8), (int) getActivity().getResources().getDimension(R.dimen.one)));

        for(int i = 0 ;i < bean.getData().size() ;i++){
            mTitlesList.add(bean.getData().get(i)) ;
        }


        Fragment fragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", (Serializable) bean.getData().get(0).getChildren());
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
        mHomeCategory = new HomeCategoryAdapter(getActivity(),mTitlesList) ;
        mHomeCategory.setHasStableIds(true);
        mRecyleView.setAdapter(mHomeCategory);

        mHomeCategory.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {


                int position = mRecyleView.getChildAdapterPosition(view);

                for(int i = 0 ;i < mTitlesList.size() ;i++){
                    if(i == position){
                        mTitlesList.get(i).setSelect(true);
                        mHomeCategory.notifyItemChanged(i);
                    }else{
                        mTitlesList.get(i).setSelect(false);
                        mHomeCategory.notifyItemChanged(i);
                    }
                }



                Fragment fragment = new CategoryFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", (Serializable) bean.getData().get(position).getChildren());
                fragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();

            }

            @Override
            public void onItemLongClick(View view) {

            }

            @Override
            public void onTouchListener(int x, int y) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_main_category, container, false);
        mContext = getActivity();
        initView(mMainView);

        getMainCategoryList();

        return mMainView;
    }


}

