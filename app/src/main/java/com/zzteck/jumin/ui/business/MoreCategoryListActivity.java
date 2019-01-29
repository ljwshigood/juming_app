package com.zzteck.jumin.ui.business;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zzteck.jumin.R;
import com.zzteck.jumin.fragment.CategoryFragment;


public class MoreCategoryListActivity extends FragmentActivity implements View.OnClickListener {

    private String[] titles={"常用分类","潮流女装","品牌男装","内衣配饰","家用电器","手机数码","电脑办公","个护化妆","母婴频道","食物生鲜","酒水饮料","家居家纺","整车车品","鞋靴箱包","运动户外","图书","玩具乐器","钟表","居家生活","珠宝饰品","音像制品","家具建材","计生情趣","营养保健","奢侈礼品","生活服务","旅游出行"};
    private ScrollView mScrollView;
    private FrameLayout mFrameLayout;
    //装装ScrollView的item的TextView的数组
    private TextView[] textViewArray;
    //装ScrollView的item的数组
    private View[] views;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_more_list);
        mContext =this;

        textViewArray = new TextView[titles.length];
        views = new View[titles.length];

        initView();

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new CategoryFragment()).commit();
    }

    private void initView() {
        mScrollView= (ScrollView) findViewById(R.id.scrollview);
        addView();
        changeTextColor(0);

        mFrameLayout= (FrameLayout) findViewById(R.id.framelayout);

    }

    /**
     * 给ScrollView添加子View
     */
    private void addView() {
        LinearLayout mLinearLayout= (LinearLayout) findViewById(R.id.linearlayout);

        View view;
        for(int x=0;x<titles.length;x++){
            view = View.inflate(this, R.layout.item_scrollview, null);
            view.setId(x);
            view.setOnClickListener(this);
            TextView tv= (TextView) view.findViewById(R.id.textview);
            tv.setText(titles[x]);
            mLinearLayout.addView(view);

            textViewArray[x]=tv;
            views[x]=view;

        }
    }

    @Override
    public void onClick(View v) {

        changeTextColor((int) v.getId());
        changeTextLocation((int) v.getId());

        Fragment fragment=null;

        switch (v.getId()){
            case 0:
                fragment=new CategoryFragment();
                break;
        }
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
        }
    }
    /**
     * 改变textView的颜色
     * @param id
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < textViewArray.length; i++) {
            if(i!=id){
                textViewArray[i].setBackgroundResource(android.R.color.transparent);
                textViewArray[i].setTextColor(0xff000000);
            }else {
                textViewArray[id].setBackgroundResource(android.R.color.white);
                textViewArray[id].setTextColor(0xffff5d5e);
            }
        }

    }

    /**
     * 改变栏目位置
     */
    private void changeTextLocation(int index) {

        //views[clickPosition].getTop()针对其父视图的顶部相对位置
        int x = (views[index].getTop() - mScrollView.getHeight() / 2);
        mScrollView.smoothScrollTo(0, x);
    }

}
