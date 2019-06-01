package com.zzteck.jumin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jianwei on 2018/4/1.
 */

public class SearchBean implements Serializable {


    /**
     * ret : 200
     * data : [{"id":"29","catid":"0","keywords":"找工作","num":"299","updatetime":"1558926921"},{"id":"60","catid":"3","keywords":"房产","num":"183","updatetime":"1558926753"},{"id":"34","catid":"0","keywords":"二手房","num":"172","updatetime":"1559181630"},{"id":"75","catid":"0","keywords":"租房","num":"154","updatetime":"1558884715"},{"id":"341","catid":"0","keywords":"司机","num":"113","updatetime":"1558510779"},{"id":"74","catid":"0","keywords":"找房子","num":"71","updatetime":"1558793747"},{"id":"44","catid":"0","keywords":"出租","num":"60","updatetime":"1558613455"},{"id":"114","catid":"3","keywords":"房屋出租","num":"52","updatetime":"1559181632"},{"id":"1314","catid":"0","keywords":"招聘","num":"52","updatetime":"1558926938"},{"id":"226","catid":"0","keywords":"房子","num":"51","updatetime":"1558919819"},{"id":"147","catid":"0","keywords":"工作","num":"35","updatetime":"1558793205"},{"id":"120","catid":"0","keywords":"兼职","num":"35","updatetime":"1558886921"}]
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
         * id : 29
         * catid : 0
         * keywords : 找工作
         * num : 299
         * updatetime : 1558926921
         */

        private String id;
        private String catid;
        private String keywords;
        private String num;
        private String updatetime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
