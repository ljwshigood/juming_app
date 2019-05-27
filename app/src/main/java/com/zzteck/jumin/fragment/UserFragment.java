package com.zzteck.jumin.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zzteck.jumin.R;
import com.zzteck.jumin.bean.User;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.ui.business.ChargeActivity;
import com.zzteck.jumin.ui.mainui.FeedBackActivity;
import com.zzteck.jumin.ui.usercenter.ContactUsActivity;
import com.zzteck.jumin.ui.usercenter.FavitorActivity;
import com.zzteck.jumin.ui.usercenter.IdentityActivity;
import com.zzteck.jumin.ui.usercenter.LoginActivity;
import com.zzteck.jumin.ui.usercenter.MemberUpgradeActivity;
import com.zzteck.jumin.ui.usercenter.ModifyUserInfoActivity;
import com.zzteck.jumin.ui.usercenter.MyReleaseActivity;
import com.zzteck.jumin.ui.usercenter.SpreadActivity;
import com.zzteck.jumin.utils.Constants;
import com.zzteck.jumin.utils.GlideCircleTransform;
import com.zzteck.jumin.utils.UtilsTools;
import com.zzteck.jumin.view.MyDialog;
import com.zzteck.jumin.view.SignInDialog;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment implements OnClickListener {

    private View mMainView;

    private Context mContext;

    private RelativeLayout mRlMyRelease;

    private RelativeLayout mRlMyBill;

    private RelativeLayout mRlMyFeedBack;

    private RelativeLayout mRlMyAttendtion;

    private RelativeLayout mRlMyRenzhen;

    private RelativeLayout mRlHuiyuanshengji;

    private RelativeLayout mRlRuheFaBuXuqiu;

    private TextView mTvCharge;

    private TextView mTvQianDao;

    private TextView mTvLoginOut ;

    private TextView mTvNickName ;

    private CircleImageView mCvPhoto ;

    private LinearLayout mLLEditor ;

    private LinearLayout mLLHeader ;

    private void initView(View view) {

        mLLHeader = view.findViewById(R.id.ll_header) ;
        mLLHeader.setOnClickListener(this);
        mLLEditor = view.findViewById(R.id.ll_modify_user_info) ;
        mCvPhoto = view.findViewById(R.id.iv_user_icon) ;
        mTvNickName = view.findViewById(R.id.tv_nick_name) ;
        mTvLoginOut = view.findViewById(R.id.tv_login_out);

        mTvCharge = view.findViewById(R.id.tv_chongzhi);
        mTvQianDao = view.findViewById(R.id.tv_qiandao);

        mRlMyRelease = view.findViewById(R.id.rl_my_release);
        mRlMyBill = view.findViewById(R.id.rl_my_fav);
        mRlMyFeedBack = view.findViewById(R.id.rl_my_feedback);
        mRlMyAttendtion = view.findViewById(R.id.rl_contact_us);
        mRlMyRenzhen = view.findViewById(R.id.rl_my_spread);
        mRlHuiyuanshengji = view.findViewById(R.id.rl_ruiyuanshengji);
        mRlRuheFaBuXuqiu = view.findViewById(R.id.rl_identity);

        mTvQianDao.setOnClickListener(this);
        mTvCharge.setOnClickListener(this);
        mRlMyRelease.setOnClickListener(this);
        mRlMyBill.setOnClickListener(this);
        mRlMyFeedBack.setOnClickListener(this);
        mRlMyAttendtion.setOnClickListener(this);
        mRlMyRenzhen.setOnClickListener(this);
        mRlHuiyuanshengji.setOnClickListener(this);
        mRlRuheFaBuXuqiu.setOnClickListener(this);

        mTvLoginOut.setOnClickListener(this);
    }


    public void initData() {
        List<User> userList = UserDAO.getInstance(mContext).selectUserList() ;
        if(userList != null && userList.size() > 0){


            RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.default_pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .load(Constants.PIC_HOST+userList.get(0).getHeader())
                    .apply(options)
                    .into(mCvPhoto);

            if(TextUtils.isEmpty(userList.get(0).getMobile())){
                mTvNickName.setVisibility(View.GONE);
            }else {
                mTvNickName.setVisibility(View.VISIBLE);
                mTvNickName.setText(userList.get(0).getMobile() + "");
            }
            mLLEditor.setVisibility(View.GONE);

        }else{

            mTvNickName.setText("点击登陆");
            mLLEditor.setVisibility(View.GONE);
        }
    }

    private List<User> userList ;


    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(userList != null && userList.size() > 0){

            }
        }
    } ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_user, container, false);
        mContext = getActivity();
        initView(mMainView);
        initData();

        userList = UserDAO.getInstance(mContext).selectUserList() ;

        return mMainView;
    }

    /**
     * 拨打电话（直接拨打电话）
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.ll_header :
                if(userList != null && userList.size() > 0){
                    intent = new Intent(getActivity(), ModifyUserInfoActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

                break ;
            case R.id.tv_login_out :
                MyDialog dialog1 = new MyDialog(getActivity(),0) ;
                dialog1.show();
                break ;
            case R.id.tv_chongzhi:
                intent = new Intent(getActivity(), ChargeActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_qiandao :
                if(userList != null && userList.size() > 0){
                    SignInDialog dialog = new SignInDialog(mContext);
                    dialog.show();
                }else{
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }


                break;
            case R.id.rl_my_fav:
                if(userList != null && userList.size() > 0){
                    intent = new Intent(getActivity(), FavitorActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

                break;

            case R.id.rl_my_release:

                if(userList != null && userList.size() > 0){
                    intent = new Intent(getActivity(), MyReleaseActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.rl_my_feedback:
                if(userList != null && userList.size() > 0){
                    intent = new Intent(getActivity(), FeedBackActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.rl_contact_us:
                final MyDialog dialog2 = new MyDialog(getActivity(),1) ;
                dialog2.setContent("400-800-9995");
                dialog2.show();

                dialog2.setOnPositiveListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callPhone("4008009995") ;
                        dialog2.dismiss();
                    }
                });

                break;
            case R.id.rl_my_spread:

                if(userList != null && userList.size() > 0){
                    intent = new Intent(getActivity(), SpreadActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }


                break;
            case R.id.rl_ruiyuanshengji:
                if(userList != null && userList.size() > 0){
                    intent = new Intent(getActivity(), MemberUpgradeActivity.class);
                    startActivity(intent);
                }else{

                }
                break;
            case R.id.rl_identity:
                if(userList != null && userList.size() > 0){
                    intent = new Intent(getActivity(), IdentityActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

                break;
            default:
                break;
        }
    }

}
