package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 */

public class ReleaseEditBean implements Serializable {


    /**
     * ret : 200
     * data : {"id":"27571","title":"吧","catid":"11","cityid":"6","areaid":"89","streetid":"1463","begintime":"1563263853","activetime":"0","endtime":"0","content":"啦啦啦","userid":"13417434862","contact_who":"魔攻","qq":"11349814566","weixin":"13417434862","email":"","tel":"13417434862","updatetime":"0","hit":"0","ismember":"1","manage_pwd":"","ip":"116.24.66.42","ip2area":"app","info_level":"0","img_path":"","img_count":"1","upgrade_type":"1","upgrade_time":"0","upgrade_type_list":"1","upgrade_time_list":"0","ifred":"0","ifbold":"0","certify":"1","dir_typename":"shouji","upgrade_type_index":"0","upgrade_time_index":"0","upgrade_type_allindex":null,"upgrade_time_allindex":null,"mappoint":null,"latitude":null,"longitude":null,"web_address":"罗湖蔡屋围","catname":"二手手机","gid":"1","video":"/attachment/videos/201907/1563263816560.mp4","upgrade_type_index_video":"0","upgrade_time_index_video":"0","areaname":"罗湖","streetname":"蔡屋围","parentid":"1","template_info":"info","usecoin":"0","modid":"28","usertoid":"13417434862","uid":"11254","image":[{"prepath":"/attachment/information/201907/pre_1563263827ktpoe.png","path":"/attachment/information/201907/1563263827ktpoe.png"}],"extra":{"mbrand":"1","price":800,"newold":"1","from":"1"}}
     * msg :
     * debug : {"stack":["[#0 - 0ms - PHALAPI_INIT]/datas/wwwroot/api/public/index.php(8)","[#1 - 0.4ms - PHALAPI_RESPONSE]/datas/wwwroot/api/vendor/phalapi/kernal/src/PhalApi.php(46)","[#2 - 9.5ms - PHALAPI_FINISH]/datas/wwwroot/api/vendor/phalapi/kernal/src/PhalApi.php(74)"],"sqls":["[1 - 0.47ms]SELECT a.*,b.areaname,c.streetname FROM `my_information` AS a LEFT JOIN  `my_area` AS b ON a.areaid = b.areaid LEFT JOIN `my_street` AS c ON a.streetid = c.streetid WHERE a.id = '27571'  ;","[2 - 0.14ms]SELECT parentid,template_info,usecoin,catname,dir_typename,modid FROM `my_category` WHERE catid = '11';","[3 - 0.2ms]SELECT * FROM `my_member` WHERE userid ='13417434862';","[4 - 0.15ms]SELECT prepath,path FROM `my_info_img` WHERE infoid = '27571' ORDER BY id ASC;","[5 - 0.11ms]SELECT * FROM `my_information_28` WHERE id ='27571';","[6 - 0.76ms]SELECT title,identifier,rules,type FROM `my_info_typeoptions` WHERE classid >0 ORDER BY displayorder DESC;"],"version":"2.5.0"}
     */

    private int ret;
    private DataBean data;
    private String msg;
    private DebugBean debug;

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

    public DebugBean getDebug() {
        return debug;
    }

    public void setDebug(DebugBean debug) {
        this.debug = debug;
    }

    public static class DataBean {
        /**
         * id : 27571
         * title : 吧
         * catid : 11
         * cityid : 6
         * areaid : 89
         * streetid : 1463
         * begintime : 1563263853
         * activetime : 0
         * endtime : 0
         * content : 啦啦啦
         * userid : 13417434862
         * contact_who : 魔攻
         * qq : 11349814566
         * weixin : 13417434862
         * email :
         * tel : 13417434862
         * updatetime : 0
         * hit : 0
         * ismember : 1
         * manage_pwd :
         * ip : 116.24.66.42
         * ip2area : app
         * info_level : 0
         * img_path :
         * img_count : 1
         * upgrade_type : 1
         * upgrade_time : 0
         * upgrade_type_list : 1
         * upgrade_time_list : 0
         * ifred : 0
         * ifbold : 0
         * certify : 1
         * dir_typename : shouji
         * upgrade_type_index : 0
         * upgrade_time_index : 0
         * upgrade_type_allindex : null
         * upgrade_time_allindex : null
         * mappoint : null
         * latitude : null
         * longitude : null
         * web_address : 罗湖蔡屋围
         * catname : 二手手机
         * gid : 1
         * video : /attachment/videos/201907/1563263816560.mp4
         * upgrade_type_index_video : 0
         * upgrade_time_index_video : 0
         * areaname : 罗湖
         * streetname : 蔡屋围
         * parentid : 1
         * template_info : info
         * usecoin : 0
         * modid : 28
         * usertoid : 13417434862
         * uid : 11254
         * image : [{"prepath":"/attachment/information/201907/pre_1563263827ktpoe.png","path":"/attachment/information/201907/1563263827ktpoe.png"}]
         * extra : {"mbrand":"1","price":800,"newold":"1","from":"1"}
         */

        private String id;
        private String title;
        private String catid;
        private String cityid;
        private String areaid;
        private String streetid;
        private String begintime;
        private String activetime;
        private String endtime;
        private String content;
        private String userid;
        private String contact_who;
        private String qq;
        private String weixin;
        private String email;
        private String tel;
        private String updatetime;
        private String hit;
        private String ismember;
        private String manage_pwd;
        private String ip;
        private String ip2area;
        private String info_level;
        private String img_path;
        private String img_count;
        private String upgrade_type;
        private String upgrade_time;
        private String upgrade_type_list;
        private String upgrade_time_list;
        private String ifred;
        private String ifbold;
        private String certify;
        private String dir_typename;
        private String upgrade_type_index;
        private String upgrade_time_index;
        private Object upgrade_type_allindex;
        private Object upgrade_time_allindex;
        private Object mappoint;
        private Object latitude;
        private Object longitude;
        private String web_address;
        private String catname;
        private String gid;
        private String video;
        private String upgrade_type_index_video;
        private String upgrade_time_index_video;
        private String areaname;
        private String streetname;
        private String parentid;
        private String template_info;
        private String usecoin;
        private String modid;
        private String usertoid;
        private String uid;

        public Map getExtra() {
            return extra;
        }

        public void setExtra(Map extra) {
            this.extra = extra;
        }

        private Map extra;
        private List<ImageBean> image;

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

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getStreetid() {
            return streetid;
        }

        public void setStreetid(String streetid) {
            this.streetid = streetid;
        }

        public String getBegintime() {
            return begintime;
        }

        public void setBegintime(String begintime) {
            this.begintime = begintime;
        }

        public String getActivetime() {
            return activetime;
        }

        public void setActivetime(String activetime) {
            this.activetime = activetime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getContact_who() {
            return contact_who;
        }

        public void setContact_who(String contact_who) {
            this.contact_who = contact_who;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getHit() {
            return hit;
        }

        public void setHit(String hit) {
            this.hit = hit;
        }

        public String getIsmember() {
            return ismember;
        }

        public void setIsmember(String ismember) {
            this.ismember = ismember;
        }

        public String getManage_pwd() {
            return manage_pwd;
        }

        public void setManage_pwd(String manage_pwd) {
            this.manage_pwd = manage_pwd;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getIp2area() {
            return ip2area;
        }

        public void setIp2area(String ip2area) {
            this.ip2area = ip2area;
        }

        public String getInfo_level() {
            return info_level;
        }

        public void setInfo_level(String info_level) {
            this.info_level = info_level;
        }

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public String getImg_count() {
            return img_count;
        }

        public void setImg_count(String img_count) {
            this.img_count = img_count;
        }

        public String getUpgrade_type() {
            return upgrade_type;
        }

        public void setUpgrade_type(String upgrade_type) {
            this.upgrade_type = upgrade_type;
        }

        public String getUpgrade_time() {
            return upgrade_time;
        }

        public void setUpgrade_time(String upgrade_time) {
            this.upgrade_time = upgrade_time;
        }

        public String getUpgrade_type_list() {
            return upgrade_type_list;
        }

        public void setUpgrade_type_list(String upgrade_type_list) {
            this.upgrade_type_list = upgrade_type_list;
        }

        public String getUpgrade_time_list() {
            return upgrade_time_list;
        }

        public void setUpgrade_time_list(String upgrade_time_list) {
            this.upgrade_time_list = upgrade_time_list;
        }

        public String getIfred() {
            return ifred;
        }

        public void setIfred(String ifred) {
            this.ifred = ifred;
        }

        public String getIfbold() {
            return ifbold;
        }

        public void setIfbold(String ifbold) {
            this.ifbold = ifbold;
        }

        public String getCertify() {
            return certify;
        }

        public void setCertify(String certify) {
            this.certify = certify;
        }

        public String getDir_typename() {
            return dir_typename;
        }

        public void setDir_typename(String dir_typename) {
            this.dir_typename = dir_typename;
        }

        public String getUpgrade_type_index() {
            return upgrade_type_index;
        }

        public void setUpgrade_type_index(String upgrade_type_index) {
            this.upgrade_type_index = upgrade_type_index;
        }

        public String getUpgrade_time_index() {
            return upgrade_time_index;
        }

        public void setUpgrade_time_index(String upgrade_time_index) {
            this.upgrade_time_index = upgrade_time_index;
        }

        public Object getUpgrade_type_allindex() {
            return upgrade_type_allindex;
        }

        public void setUpgrade_type_allindex(Object upgrade_type_allindex) {
            this.upgrade_type_allindex = upgrade_type_allindex;
        }

        public Object getUpgrade_time_allindex() {
            return upgrade_time_allindex;
        }

        public void setUpgrade_time_allindex(Object upgrade_time_allindex) {
            this.upgrade_time_allindex = upgrade_time_allindex;
        }

        public Object getMappoint() {
            return mappoint;
        }

        public void setMappoint(Object mappoint) {
            this.mappoint = mappoint;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getWeb_address() {
            return web_address;
        }

        public void setWeb_address(String web_address) {
            this.web_address = web_address;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getUpgrade_type_index_video() {
            return upgrade_type_index_video;
        }

        public void setUpgrade_type_index_video(String upgrade_type_index_video) {
            this.upgrade_type_index_video = upgrade_type_index_video;
        }

        public String getUpgrade_time_index_video() {
            return upgrade_time_index_video;
        }

        public void setUpgrade_time_index_video(String upgrade_time_index_video) {
            this.upgrade_time_index_video = upgrade_time_index_video;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getStreetname() {
            return streetname;
        }

        public void setStreetname(String streetname) {
            this.streetname = streetname;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getTemplate_info() {
            return template_info;
        }

        public void setTemplate_info(String template_info) {
            this.template_info = template_info;
        }

        public String getUsecoin() {
            return usecoin;
        }

        public void setUsecoin(String usecoin) {
            this.usecoin = usecoin;
        }

        public String getModid() {
            return modid;
        }

        public void setModid(String modid) {
            this.modid = modid;
        }

        public String getUsertoid() {
            return usertoid;
        }

        public void setUsertoid(String usertoid) {
            this.usertoid = usertoid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public List<ImageBean> getImage() {
            return image;
        }

        public void setImage(List<ImageBean> image) {
            this.image = image;
        }

        /*public static class ExtraBean {
            *//**
             * mbrand : 1
             * price : 800
             * newold : 1
             * from : 1
             *//*

            private String mbrand;
            private int price;
            private String newold;
            private String from;

            public String getMbrand() {
                return mbrand;
            }

            public void setMbrand(String mbrand) {
                this.mbrand = mbrand;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getNewold() {
                return newold;
            }

            public void setNewold(String newold) {
                this.newold = newold;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }
        }*/

        public static class ImageBean {
            /**
             * prepath : /attachment/information/201907/pre_1563263827ktpoe.png
             * path : /attachment/information/201907/1563263827ktpoe.png
             */

            private String prepath;
            private String path;

            public String getPrepath() {
                return prepath;
            }

            public void setPrepath(String prepath) {
                this.prepath = prepath;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }

    public static class DebugBean {
        /**
         * stack : ["[#0 - 0ms - PHALAPI_INIT]/datas/wwwroot/api/public/index.php(8)","[#1 - 0.4ms - PHALAPI_RESPONSE]/datas/wwwroot/api/vendor/phalapi/kernal/src/PhalApi.php(46)","[#2 - 9.5ms - PHALAPI_FINISH]/datas/wwwroot/api/vendor/phalapi/kernal/src/PhalApi.php(74)"]
         * sqls : ["[1 - 0.47ms]SELECT a.*,b.areaname,c.streetname FROM `my_information` AS a LEFT JOIN  `my_area` AS b ON a.areaid = b.areaid LEFT JOIN `my_street` AS c ON a.streetid = c.streetid WHERE a.id = '27571'  ;","[2 - 0.14ms]SELECT parentid,template_info,usecoin,catname,dir_typename,modid FROM `my_category` WHERE catid = '11';","[3 - 0.2ms]SELECT * FROM `my_member` WHERE userid ='13417434862';","[4 - 0.15ms]SELECT prepath,path FROM `my_info_img` WHERE infoid = '27571' ORDER BY id ASC;","[5 - 0.11ms]SELECT * FROM `my_information_28` WHERE id ='27571';","[6 - 0.76ms]SELECT title,identifier,rules,type FROM `my_info_typeoptions` WHERE classid >0 ORDER BY displayorder DESC;"]
         * version : 2.5.0
         */

        private String version;
        private List<String> stack;
        private List<String> sqls;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public List<String> getStack() {
            return stack;
        }

        public void setStack(List<String> stack) {
            this.stack = stack;
        }

        public List<String> getSqls() {
            return sqls;
        }

        public void setSqls(List<String> sqls) {
            this.sqls = sqls;
        }
    }
}
