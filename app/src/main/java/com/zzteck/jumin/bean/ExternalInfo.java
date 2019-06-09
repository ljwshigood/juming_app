package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class ExternalInfo implements Serializable {


    /**
     * ret : 200
     * data : [{"required":1,"title":"来源","type":"radio","placeholder":"","identifier":"from","extra":{"choices":"1=个人\r\n2=商家"},"value":""},{"required":1,"title":"价格","type":"number","placeholder":"","identifier":"price","extra":{"units":"元","choices":"1~1000=1000以内\r\n1000~3000=1000~3000\r\n3000~5000=3000~5000\r\n5000~10000=5000~10000\r\n10000~=10000以上"},"value":""},{"required":1,"title":"新旧程度","type":"select","placeholder":"","identifier":"new_old","extra":{"choices":"1=全新\r\n2=9成新\r\n3=7成新\r\n4=5成新"},"value":""}]
     * msg :
     */

    private int ret;
    private String msg;
    private Map<String,ReleaseDataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }


}
