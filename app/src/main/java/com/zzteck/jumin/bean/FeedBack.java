package com.zzteck.jumin.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class FeedBack implements Serializable {

    private String feedBack ;

    private boolean isSelect ;

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
