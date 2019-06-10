package com.zzteck.jumin.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.ConfigBean;
import com.zzteck.jumin.bean.MediaInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class ConfigAdapter extends RecyclerView.Adapter<ConfigAdapter.ViewHolder> {

    private Context mContext;

    private List<ConfigBean> mMediaInfoList;

    public ConfigAdapter(Context context, List<ConfigBean> list) {
        this.mContext = context;
        this.mMediaInfoList = list;
    }

    public void notifyImageListChange(List<ConfigBean> list){
        this.mMediaInfoList = list ;
        notifyDataSetChanged();
    }

    @Override
    public ConfigAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_config, parent, false);
        return new ConfigAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ConfigAdapter.ViewHolder holder, final int position) {


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_pic)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        if(mMediaInfoList.get(position).getIsSelect() == 1){
            Glide.with(mContext)
                    .load(mMediaInfoList.get(position).getRes())
                    .apply(options)
                    .into(holder.mIvPicture);
            holder.mTv.setText(mMediaInfoList.get(position).getInfo());
        }else{
            Glide.with(mContext)
                    .load(R.mipmap.jingyong)
                    .apply(options)
                    .into(holder.mIvPicture);
            holder.mTv.setText(mMediaInfoList.get(position).getInfo());
            holder.mTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG) ;

            /*
            *
            *
            * textView.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
                textView.getPaint().setAntiAlias(true);//抗锯齿
                textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
                setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
                textView.getPaint().setFlags(0);  // 取消设置的的划线
            *
            * */
        }








    }

    @Override
    public int getItemCount() {

        return mMediaInfoList == null ? 0 : mMediaInfoList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvPicture;

        private TextView mTv ;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv_info) ;
            mIvPicture = itemView.findViewById(R.id.iv_picture) ;
        }
    }


}
