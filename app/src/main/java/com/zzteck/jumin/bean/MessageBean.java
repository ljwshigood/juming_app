package com.zzteck.jumin.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class MessageBean implements Serializable{

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type ;

}
