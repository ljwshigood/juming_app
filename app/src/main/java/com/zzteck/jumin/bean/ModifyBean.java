package com.zzteck.jumin.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class ModifyBean implements Serializable {


    /**
     * ret : 200
     * data : {"logo":"http://www.jumin.cn/attachment/face/201903/15538511783dxaq.png","prelogo":"http://www.jumin.cn/attachment/face/201903/pre_15538511783dxaq.png","is_success":true,"info":"修改成功"}
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
         * logo : http://www.jumin.cn/attachment/face/201903/15538511783dxaq.png
         * prelogo : http://www.jumin.cn/attachment/face/201903/pre_15538511783dxaq.png
         * is_success : true
         * info : 修改成功
         */

        private String logo;
        private String prelogo;
        private boolean is_success;
        private String info;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getPrelogo() {
            return prelogo;
        }

        public void setPrelogo(String prelogo) {
            this.prelogo = prelogo;
        }

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
