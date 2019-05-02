package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class AreaInfo implements Serializable {


    /**
     * ret : 200
     * data : [{"streetid":"1516","streetname":"西乡","areaid":"93","displayorder":"1"},{"streetid":"1517","streetname":"新安","areaid":"93","displayorder":"2"},{"streetid":"1518","streetname":"中心区","areaid":"93","displayorder":"3"},{"streetid":"1519","streetname":"龙华","areaid":"93","displayorder":"4"},{"streetid":"1520","streetname":"民治","areaid":"93","displayorder":"5"},{"streetid":"1521","streetname":"梅林关口","areaid":"93","displayorder":"6"},{"streetid":"1522","streetname":"新中心区","areaid":"93","displayorder":"7"},{"streetid":"1523","streetname":"翻身路","areaid":"93","displayorder":"8"},{"streetid":"1524","streetname":"桃源居","areaid":"93","displayorder":"9"},{"streetid":"1525","streetname":"福永","areaid":"93","displayorder":"10"},{"streetid":"1526","streetname":"沙井","areaid":"93","displayorder":"11"},{"streetid":"1527","streetname":"石岩","areaid":"93","displayorder":"12"},{"streetid":"1528","streetname":"松岗","areaid":"93","displayorder":"13"},{"streetid":"1529","streetname":"滢水山庄","areaid":"93","displayorder":"14"},{"streetid":"1530","streetname":"潜龙花园","areaid":"93","displayorder":"15"},{"streetid":"1531","streetname":"锦绣江南","areaid":"93","displayorder":"16"},{"streetid":"1532","streetname":"万科城","areaid":"93","displayorder":"17"},{"streetid":"1533","streetname":"观澜","areaid":"93","displayorder":"18"},{"streetid":"1534","streetname":"美丽365花园","areaid":"93","displayorder":"19"},{"streetid":"1535","streetname":"公明","areaid":"93","displayorder":"20"},{"streetid":"1536","streetname":"其他","areaid":"93","displayorder":"21"}]
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
         * streetid : 1516
         * streetname : 西乡
         * areaid : 93
         * displayorder : 1
         */

        private String streetid;
        private String streetname;
        private String areaid;
        private String displayorder;

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

        public String getDisplayorder() {
            return displayorder;
        }

        public void setDisplayorder(String displayorder) {
            this.displayorder = displayorder;
        }
    }
}
