package com.zzteck.jumin.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.adapter.MemeberAdapter;
import com.zzteck.jumin.app.App;
import com.zzteck.jumin.bean.UpgradeMemeberBean;
import com.zzteck.jumin.ui.mainui.BaseActivity;
import com.zzteck.jumin.view.NormalDecoration;

import java.util.ArrayList;
import java.util.List;


public class MemberUpgradeActivity extends BaseActivity implements OnClickListener{

	private RecyclerView mRVMemeberUpgrade ;

	private MemeberAdapter mMemeberAdapter ;

	private List<UpgradeMemeberBean> mMemberList = new ArrayList<>() ;

	private int[] res = new int[]{R.mipmap.pic_putong,R.mipmap.pic_huangjin,R.mipmap.pic_baiyin,R.mipmap.pic_zhuanshi} ;

	private TextView mTvTitle ;

	private RelativeLayout mRlBack;

	private String[] resInfo = new String[]{"普通","黄金","白金","砖石"} ;

	private void initData(){
        for(int i = 0 ;i< 4;i++){
            UpgradeMemeberBean bean = new UpgradeMemeberBean() ;
            bean.setInfo(resInfo[i]);
            bean.setRes(res[i]);
            mMemberList.add(bean) ;
        }
        mMemeberAdapter.notifyMemberAdapter(mMemberList) ;
	}


	private void initView() {
		mRlBack = findViewById(R.id.ll_back) ;
		mTvTitle = findViewById(R.id.tv_main_info) ;
		mRVMemeberUpgrade = findViewById(R.id.rv_memeber_upgrade) ;
		mRVMemeberUpgrade.setLayoutManager(new GridLayoutManager(this,2));
		mRVMemeberUpgrade.addItemDecoration(new NormalDecoration(ContextCompat.getColor(this, R.color.mainGrayF8), (int) getResources().getDimension(R.dimen.one)));
		mMemeberAdapter = new MemeberAdapter(mContext,null) ;
		mRVMemeberUpgrade.setAdapter(mMemeberAdapter) ;
		mTvTitle.setText("会员升级");
		mRlBack.setOnClickListener(this);
		mRlBack.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_memeber_upgrade);

		mContext = MemberUpgradeActivity.this ;
		App.getInstance().addActivity(this);

 		initView() ;
        initData() ;

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_back :
				finish();
				break ;
		}
	}

}
