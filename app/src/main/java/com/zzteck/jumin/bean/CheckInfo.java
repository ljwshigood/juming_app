package com.zzteck.jumin.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class CheckInfo implements Serializable {

    private String info ;

    private boolean isSelect ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id ;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
