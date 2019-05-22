package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class CatoryDetailInfo implements Serializable {


    /**
     * ret : 200
     * data : {"id":"20719","title":"高考生学车推荐","catid":"100","cityid":"4","areaid":"63","streetid":"1037","begintime":"1558246798","activetime":"0","endtime":"0","content":"南坪学车我推荐国企渝运驾校，学车有保障<br />\r\n自主预约学车，方便快捷<br />\r\n国有驾校，信誉保证<br />\r\n高标准培训服务<br />\r\n透明的收费<br />\r\n一对一教学<br />\r\n全程一对一专属学车助理服务","userid":"18166450422","contact_who":"渝运驾校","qq":"18166450422","weixin":null,"email":"","tel":"18166450422","updatetime":"0","hit":"0","ismember":"1","manage_pwd":"","ip":"14.111.51.147","ip2area":"重庆市电信","info_level":"1","img_path":"/attachment/information/201905/pre_1558246798ooxyp.jpg","img_count":"6","upgrade_type":"1","upgrade_time":"0","upgrade_type_list":"1","upgrade_time_list":"0","ifred":"0","ifbold":"0","certify":"0","dir_typename":"jiaxiao","upgrade_type_index":"0","upgrade_time_index":"0","upgrade_type_allindex":null,"upgrade_time_allindex":null,"mappoint":"","latitude":"0.00000000000000000","longitude":"0.00000000000000000","web_address":"南坪万达广场对面渝运驾校","catname":"驾校服务","gid":"9","video":"/attachment/videos/201905/1558246798764.mp4","upgrade_type_index_video":"0","upgrade_time_index_video":"0","areaname":"南岸","streetname":"南坪","parentid":"9","template_info":"info","usecoin":"0","modid":"1","usertoid":"18166450422","image":[{"prepath":"/attachment/information/201905/pre_1558246798ooxyp.jpg","path":"/attachment/information/201905/1558246798ooxyp.jpg"},{"prepath":"/attachment/information/201905/pre_1558246798kzpgl.jpg","path":"/attachment/information/201905/1558246798kzpgl.jpg"},{"prepath":"/attachment/information/201905/pre_1558246798mt32d.jpg","path":"/attachment/information/201905/1558246798mt32d.jpg"},{"prepath":"/attachment/information/201905/pre_1558246798c0kw5.jpg","path":"/attachment/information/201905/1558246798c0kw5.jpg"},{"prepath":"/attachment/information/201905/pre_1558246798npn8k.jpg","path":"/attachment/information/201905/1558246798npn8k.jpg"}]}
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
         * id : 20719
         * title : 高考生学车推荐
         * catid : 100
         * cityid : 4
         * areaid : 63
         * streetid : 1037
         * begintime : 1558246798
         * activetime : 0
         * endtime : 0
         * content : 南坪学车我推荐国企渝运驾校，学车有保障<br />
         自主预约学车，方便快捷<br />
         国有驾校，信誉保证<br />
         高标准培训服务<br />
         透明的收费<br />
         一对一教学<br />
         全程一对一专属学车助理服务
         * userid : 18166450422
         * contact_who : 渝运驾校
         * qq : 18166450422
         * weixin : null
         * email :
         * tel : 18166450422
         * updatetime : 0
         * hit : 0
         * ismember : 1
         * manage_pwd :
         * ip : 14.111.51.147
         * ip2area : 重庆市电信
         * info_level : 1
         * img_path : /attachment/information/201905/pre_1558246798ooxyp.jpg
         * img_count : 6
         * upgrade_type : 1
         * upgrade_time : 0
         * upgrade_type_list : 1
         * upgrade_time_list : 0
         * ifred : 0
         * ifbold : 0
         * certify : 0
         * dir_typename : jiaxiao
         * upgrade_type_index : 0
         * upgrade_time_index : 0
         * upgrade_type_allindex : null
         * upgrade_time_allindex : null
         * mappoint :
         * latitude : 0.00000000000000000
         * longitude : 0.00000000000000000
         * web_address : 南坪万达广场对面渝运驾校
         * catname : 驾校服务
         * gid : 9
         * video : /attachment/videos/201905/1558246798764.mp4
         * upgrade_type_index_video : 0
         * upgrade_time_index_video : 0
         * areaname : 南岸
         * streetname : 南坪
         * parentid : 9
         * template_info : info
         * usecoin : 0
         * modid : 1
         * usertoid : 18166450422
         * image : [{"prepath":"/attachment/information/201905/pre_1558246798ooxyp.jpg","path":"/attachment/information/201905/1558246798ooxyp.jpg"},{"prepath":"/attachment/information/201905/pre_1558246798kzpgl.jpg","path":"/attachment/information/201905/1558246798kzpgl.jpg"},{"prepath":"/attachment/information/201905/pre_1558246798mt32d.jpg","path":"/attachment/information/201905/1558246798mt32d.jpg"},{"prepath":"/attachment/information/201905/pre_1558246798c0kw5.jpg","path":"/attachment/information/201905/1558246798c0kw5.jpg"},{"prepath":"/attachment/information/201905/pre_1558246798npn8k.jpg","path":"/attachment/information/201905/1558246798npn8k.jpg"}]
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
        private Object weixin;
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
        private String mappoint;
        private String latitude;
        private String longitude;
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

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
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

        public String getMappoint() {
            return mappoint;
        }

        public void setMappoint(String mappoint) {
            this.mappoint = mappoint;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
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

        public List<ImageBean> getImage() {
            return image;
        }

        public void setImage(List<ImageBean> image) {
            this.image = image;
        }

        public static class ImageBean {
            /**
             * prepath : /attachment/information/201905/pre_1558246798ooxyp.jpg
             * path : /attachment/information/201905/1558246798ooxyp.jpg
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
}
