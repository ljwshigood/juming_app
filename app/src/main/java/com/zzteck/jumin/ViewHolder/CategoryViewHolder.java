package com.zzteck.jumin.ViewHolder;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.BaseViewHolder;
import com.zzteck.jumin.bean.HomeBean;
import com.zzteck.jumin.fragment.BusinessFragment;
import com.zzteck.jumin.fragment.RecommandFragment;
import com.zzteck.jumin.fragment.SecondHandFragment;
import com.zzteck.zzview.WJViewPaper;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class CategoryViewHolder extends BaseViewHolder<HomeBean> {

    private Context mContext ;

    private WJViewPaper mWJViewPaper ;

    private RecommandFragment mRecommandFragment ;

    private SecondHandFragment mSecondHandFragment ;

    private BusinessFragment mBusinessFragment ;

    private List<Fragment> mFragment = new ArrayList<>();

    private FragmentManager mFragmentManager ;

    private void initView(){

        mRecommandFragment = new RecommandFragment();
        mSecondHandFragment = new SecondHandFragment();
        mBusinessFragment = new BusinessFragment();

        mFragment.add(mRecommandFragment) ;
        mFragment.add(mSecondHandFragment) ;
        mFragment.add(mBusinessFragment) ;

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter (mFragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get ( position );
            }

            @Override
            public int getCount() {
                return mFragment.size ();
            }
        };
        mWJViewPaper.setScrollble(false);
        mWJViewPaper.setAdapter ( fragmentPagerAdapter );
        mWJViewPaper.setOffscreenPageLimit ( 4 );

        mWJViewPaper.setCurrentItem ( 0 );

    }

    public CategoryViewHolder(Context context , ViewGroup parent,FragmentManager fragmentManager) {
        super(parent, R.layout.item_home_category);
        this.mContext = context ;
        this.mFragmentManager = fragmentManager ;
        mWJViewPaper = itemView.findViewById(R.id.wj_paper) ;
        initView() ;
    }


    @Override
    public void refreshData(final HomeBean data, final int position,long paramDuration) {
        super.refreshData(data, position,paramDuration);
    }

}
