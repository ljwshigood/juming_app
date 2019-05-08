package com.zzteck.jumin.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class VersionInfo implements Serializable {


    /**
     * ret : 200
     * data : {"id":"1","title":"1.0.0","description":"1","inputtime":"1513174991","version":"1000","appurl":"jumin.cn/apk/jumin1000.apk","force":"2"}
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

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * title : 1.0.0
         * description : 1
         * inputtime : 1513174991
         * version : 1000
         * appurl : jumin.cn/apk/jumin1000.apk
         * force : 2
         */

        private String id;
        private String title;
        private String description;
        private String inputtime;
        private String version;
        private String appurl;
        private String force;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getAppurl() {
            return appurl;
        }

        public void setAppurl(String appurl) {
            this.appurl = appurl;
        }

        public String getForce() {
            return force;
        }

        public void setForce(String force) {
            this.force = force;
        }
    }
}
