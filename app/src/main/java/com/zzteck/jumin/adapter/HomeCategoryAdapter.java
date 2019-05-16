package com.zzteck.jumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.Favorite;
import com.zzteck.jumin.bean.MainCategoryBean;
import com.zzteck.jumin.utils.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    private Context mContext;

    private List<MainCategoryBean.DataBean> mFavoriteList;

    public void setOnItemClickListener(MessageAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private MessageAdapter.OnItemClickListener onItemClickListener ;

    public static interface OnItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
        void onTouchListener(int x ,int y) ;

    }

    public HomeCategoryAdapter(Context context, List<MainCategoryBean.DataBean> list) {
        this.mContext = context;
        this.mFavoriteList = list;
    }

    @Override
    public HomeCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);
        ViewHolder holder = new HomeCategoryAdapter.ViewHolder(view) ;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view);
                }
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(HomeCategoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(mFavoriteList.get(position).getCatname());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.default_pic)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(mFavoriteList.get(position).getIcon())
                .apply(options)
                .into(holder.mIv);

       /* Glide.with(mContext)
                .load(mFavoriteList.get(position).getIcon())
                .placeholder(R.mipmap.default_pic)
                .error(R.mipmap.default_pic)
                .crossFade(300)
                .transform(new GlideCircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mIv);*/
    }

    @Override
    public int getItemCount() {
        return mFavoriteList == null ? 0 : mFavoriteList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView mIv ;
        public ViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv) ;
            name = itemView.findViewById(R.id.tv_title);
        }
    }


}
