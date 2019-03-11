package com.zzteck.jumin.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class QiandaoBean implements Serializable{


    /**
     * ret : 200
     * data : {"is_success":false,"info":""}
     * msg :
     */

    private int ret;
    private DataBean data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * is_success : false
         * info :
         */

        private boolean is_success;
        private String info;

        public boolean isIs_success() {
            return is_success;
        }

        public void setIs_success(boolean is_success) {
            this.is_success = is_success;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
