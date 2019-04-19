package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class AttentionList implements Serializable {


    /**
     * ret : 200
     * data : [{"id":"17414","catname":"招商加盟","infoid":"17414","url":"http://www.jumin.cn/information.php?id=17414","title":"果缤纷水果连锁国际品牌值得信赖","intime":"2分钟前"},{"id":"17416","catname":"招商加盟","infoid":"17416","url":"http://www.jumin.cn/information.php?id=17416","title":"加盟一点点奶茶店忠告","intime":"9分钟前"}]
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
         * id : 17414
         * catname : 招商加盟
         * infoid : 17414
         * url : http://www.jumin.cn/information.php?id=17414
         * title : 果缤纷水果连锁国际品牌值得信赖
         * intime : 2分钟前
         */

        private String id;
        private String catname;
        private String infoid;
        private String url;
        private String title;
        private String intime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getInfoid() {
            return infoid;
        }

        public void setInfoid(String infoid) {
            this.infoid = infoid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntime() {
            return intime;
        }

        public void setIntime(String intime) {
            this.intime = intime;
        }
    }
}
