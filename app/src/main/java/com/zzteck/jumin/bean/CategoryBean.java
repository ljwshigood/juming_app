package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class CategoryBean implements Serializable{


    /**
     * ret : 200
     * data : [{"catid":"1","catname":"二手转让","icon":"http://www.jumin.cn/template/default/images/index/icon_ershou.gif"},{"catid":"2","catname":"车辆买卖","icon":"http://www.jumin.cn/template/default/images/index/icon_che.gif"},{"catid":"3","catname":"房屋租售","icon":"http://www.jumin.cn/template/default/images/index/icon_fang.gif"},{"catid":"4","catname":"全职招聘","icon":"http://www.jumin.cn/template/default/images/index/icon_zhaopin.gif"}]
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
         * catid : 1
         * catname : 二手转让
         * icon : http://www.jumin.cn/template/default/images/index/icon_ershou.gif
         */

        private String catid;
        private String catname;
        private String icon;

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
