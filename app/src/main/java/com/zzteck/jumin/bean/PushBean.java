package com.zzteck.jumin.bean;

import java.io.Serializable;
import java.util.List;

public class PushBean implements Serializable {


    /**
     * ret : 200
     * data : [{"id":"1","title":"聚民网APP下载","redirecturl":"","titlecolor":"","content":"聚民网APP下载","author":"admin","pubdate":"1556973801","begintime":"0","endtime":"0","hits":"0","cityid":"0"}]
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
         * id : 1
         * title : 聚民网APP下载
         * redirecturl :
         * titlecolor :
         * content : 聚民网APP下载
         * author : admin
         * pubdate : 1556973801
         * begintime : 0
         * endtime : 0
         * hits : 0
         * cityid : 0
         */

        private String id;
        private String title;
        private String redirecturl;
        private String titlecolor;
        private String content;
        private String author;
        private String pubdate;
        private String begintime;
        private String endtime;
        private String hits;
        private String cityid;

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

        public String getRedirecturl() {
            return redirecturl;
        }

        public void setRedirecturl(String redirecturl) {
            this.redirecturl = redirecturl;
        }

        public String getTitlecolor() {
            return titlecolor;
        }

        public void setTitlecolor(String titlecolor) {
            this.titlecolor = titlecolor;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPubdate() {
            return pubdate;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }

        public String getBegintime() {
            return begintime;
        }

        public void setBegintime(String begintime) {
            this.begintime = begintime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }
    }
}
