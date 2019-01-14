package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zzteck.jumin.ViewHolder.CategoryViewHolder;
import com.zzteck.jumin.ViewHolder.PictureViewHolder;
import com.zzteck.jumin.ViewHolder.VideoViewHolder;
import com.zzteck.jumin.bean.HomeBean;
import com.zzteck.jumin.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class HomeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext ;

    private List<HomeBean> mHomeList ;

    private FragmentManager mFragmentManager ;

    public HomeAdapter(Context context,List<HomeBean> list,FragmentManager fragmentManager) {
        this.mContext = context ;
        this.mHomeList = list ;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        BaseViewHolder viewHolder = null;
        switch (viewType) {
            case Constants.HOME_VIDEO:
                viewHolder = new VideoViewHolder(mContext,parent);
                break ;
            case Constants.PICTURE_LOGO:
                viewHolder = new PictureViewHolder(mContext,parent);
                break;
            case Constants.CATEGORY:
                viewHolder = new CategoryViewHolder(mContext,parent,mFragmentManager);
                break;
        }

        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return Constants.HOME_VIDEO ;
        }else if(position == 1){
            return Constants.PICTURE_LOGO ;
        }else if(position == 2){
            return Constants.CATEGORY ;
        }
        return 0 ;
    }

    @Override
    public int getItemCount() {
        return mHomeList == null ? 0 : mHomeList.size() ;
    }


}
