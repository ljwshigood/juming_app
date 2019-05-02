package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class QoneInfo implements Serializable {


    /**
     * ret : 200
     * data : [{"areaid":"89","areaname":"罗湖","cityid":"6","displayorder":"1"},{"areaid":"90","areaname":"福田","cityid":"6","displayorder":"2"},{"areaid":"91","areaname":"南山","cityid":"6","displayorder":"3"},{"areaid":"92","areaname":"盐田","cityid":"6","displayorder":"4"},{"areaid":"93","areaname":"宝安","cityid":"6","displayorder":"5"},{"areaid":"94","areaname":"龙岗区","cityid":"6","displayorder":"6"},{"areaid":"95","areaname":"布吉","cityid":"6","displayorder":"7"},{"areaid":"96","areaname":"坪山新区","cityid":"6","displayorder":"8"},{"areaid":"97","areaname":"光明新区","cityid":"6","displayorder":"9"},{"areaid":"98","areaname":"龙华新区","cityid":"6","displayorder":"10"},{"areaid":"99","areaname":"大鹏新区","cityid":"6","displayorder":"11"},{"areaid":"100","areaname":"深圳周边","cityid":"6","displayorder":"12"}]
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

    public static class DataBean implements Serializable{
        /**
         * areaid : 89
         * areaname : 罗湖
         * cityid : 6
         * displayorder : 1
         */

        private String areaid;
        private String areaname;
        private String cityid;
        private String displayorder;


        private String streetid;
        private String streetname;

        public String getStreetid() {
            return streetid;
        }

        public void setStreetid(String streetid) {
            this.streetid = streetid;
        }

        public String getStreetname() {
            return streetname;
        }

        public void setStreetname(String streetname) {
            this.streetname = streetname;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getDisplayorder() {
            return displayorder;
        }

        public void setDisplayorder(String displayorder) {
            this.displayorder = displayorder;
        }
    }
}
