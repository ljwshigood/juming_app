package com.zzteck.jumin.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.HomeInfo;
import com.zzteck.jumin.bean.SearchListBean;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.zzview.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class SearchListAdapter extends RecyclerArrayAdapter<SearchListBean.DataBean> {

    private Context mContext ;

    public List<SearchListBean.DataBean> getmHomeList() {
        return mHomeList;
    }

    public void setmHomeList(List<SearchListBean.DataBean> mHomeList) {
        this.mHomeList = mHomeList;
    }

    private List<SearchListBean.DataBean> mHomeList ;

    private int[] res = new int[]{R.mipmap.v0,R.mipmap.v1,R.mipmap.v2,R.mipmap.v3} ;

    public SearchListAdapter(Context context, List<SearchListBean.DataBean> objects) {
        super(context, objects);
        this.mContext = context ;
        this.mHomeList = objects ;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommandViewHolder(parent);
    }

    public class RecommandViewHolder extends BaseViewHolder<SearchListBean.DataBean> {

        private ImageView mIvLogo ;

        private TextView mTvTitle ;

        private TextView mTvDetail ;

        private TextView mTvValue ;

        private LinearLayout mLLTop ;

        private ImageView mIvTop ;

        private TextView mTvAddress ;

        private TextView mTvBeginTime ;

        private LinearLayout mllocation ;

        public RecommandViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_recommand);

            mIvLogo = $(R.id.iv_pic) ;
            mTvTitle = $(R.id.tv_title) ;
            mTvDetail = $(R.id.tv_detail) ;
            mTvValue = $(R.id.tv_value) ;
            mLLTop = $(R.id.ll_top) ;
            mIvTop= $(R.id.iv_top) ;
            mTvAddress = $(R.id.tv_address) ;
            mTvBeginTime = $(R.id.tv_begin_time);
            mllocation= $(R.id.ll_location) ;
        }

        @Override
        public void setData(SearchListBean.DataBean data) {
            super.setData(data);



            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.default_pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .load(Constants.PIC_HOST+data.getImg_path())
                    .apply(options)
                    .into(mIvLogo);

            mTvTitle.setText(data.getTitle());
            mTvDetail.setText(data.getContent());
            if(!TextUtils.isEmpty(data.getWeb_address())){
                mTvAddress.setText(data.getWeb_address());
                mllocation.setVisibility(View.VISIBLE);
            }else{
                mllocation.setVisibility(View.GONE);
            }

            if(!TextUtils.isEmpty(data.getBegintime())){
                mTvBeginTime.setText(data.getBegintime());
            }
            mTvValue.setVisibility(View.GONE);
           /* if(TextUtils.isEmpty(data.getPrice())){
                mTvValue.setVisibility(View.GONE);
            }else{
                mTvValue.setVisibility(View.VISIBLE);
                mTvValue.setText(data.getPrice());
            }
*/

            mLLTop.setVisibility(View.GONE);
            /*if(data.getTop() == 1){
                mLLTop.setVisibility(View.VISIBLE);

                if(data.getLevelid().equals("1")){
                    Glide.with(mContext)
                            .load(res[0])
                            .apply(options)
                            .into(mIvTop);
                }else if(data.getLevelid().equals("2")){
                    Glide.with(mContext)
                            .load(res[1])
                            .apply(options)
                            .into(mIvTop);
                }else if(data.getLevelid().equals("3")){
                    Glide.with(mContext)
                            .load(res[2])
                            .apply(options)
                            .into(mIvTop);
                }else if(data.getLevelid().equals("4")){
                    Glide.with(mContext)
                            .load(res[3])
                            .apply(options)
                            .into(mIvTop);
                }

            }else{
                mLLTop.setVisibility(View.GONE);
            }*/

        }
    }


    public class ViewHolder extends BaseViewHolder{

        private RoundImageView mIvLogo ;

        private TextView mTvTitle ;

        private TextView mTvDetail ;

        private TextView mTvValue ;

        private TextView mTvAddress ;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvAddress = itemView.findViewById(R.id.tv_address) ;
            mIvLogo = itemView.findViewById(R.id.iv_pic) ;
            mTvTitle = itemView.findViewById(R.id.tv_title) ;
            mTvDetail = itemView.findViewById(R.id.tv_detail) ;
            mTvValue = itemView.findViewById(R.id.tv_value) ;
        }
    }

}
