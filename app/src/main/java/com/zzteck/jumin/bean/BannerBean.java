package com.zzteck.jumin.bean;

import java.io.Serializable;
import java.util.List;

public class BannerBean implements Serializable {


    /**
     * ret : 200
     * data : [{"img_path":"http://www.jumin.cn/m/template/images/15466955149792.jpg","url":""}]
     * msg :
     */

    private int ret;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * img_path : http://www.jumin.cn/m/template/images/15466955149792.jpg
         * url :
         */

        private String img_path;
        private String url;

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
