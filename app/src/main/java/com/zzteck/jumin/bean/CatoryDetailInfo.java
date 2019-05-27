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
     * data : {"id":"21349","title":"哈多利球体博美出售 博美价格 北京京博犬舍","catid":"95","cityid":"1","areaid":"4","streetid":"166","begintime":"1558836275","activetime":"0","endtime":"0","content":"1．可以与您签订活体销售协议来保证购买宝宝的健康和纯种。&nbsp;<br />\r\n2．出售的每只狗宝宝都有自己的健康证书。&nbsp;<br />\r\n3．我们会随狗宝宝赠送爱心卡片内容包括：宝宝生日、疫苗注射情况、幼犬喂养注意事项。&nbsp;<br />\r\n4．24小时开通售后服务热线以解答您饲养中的不懂的小难题。&nbsp;<br />\r\n5．幼犬售出十日内发生任何疫苗范围内的传染性疾病为您保更换，一月内有任何疾病免费治疗，终生享受我们的医疗服务，保证宝宝在您身边健康快乐的成长。&nbsp;<br />\r\n6．可以提供看狗和送狗班车以解决您的交通不便，只需您一个电话，宝宝就会送到您家。&nbsp;<br />\r\n7．会随您所购买的狗宝宝赠送一册切实可行，简单易懂的饲养训练手册，帮您解决养幼犬期间的问题及麻烦。&nbsp;<br />\r\n8．凡是在我们这里购买的宝宝，都会赠送一袋宝宝平时用的狗粮，以保证换新的环境的也可以吃习惯。&nbsp;<br />\r\n9．凭购买协议，在我们的店里购买狗粮和狗的一切用品包括美容，终生享受8折优惠\u201c我们对售后的宝宝做终生的售后服务\u201c包括宝宝的身份证\u2018赛级证书\u2019血统证书\u201d。&nbsp;<br />\r\n相信我们，我们不会让您失望，专业的养殖和优良的品种及完善的售后服务，会让您毫无后顾之忧，务必做到让您买得放心，养得开心。","userid":"13910749461","contact_who":"陈先生","qq":"1919231932","weixin":null,"email":"","tel":"13910749461","updatetime":"0","hit":"2","ismember":"1","manage_pwd":"","ip":"106.118.221.121","ip2area":"河北省唐山市电信","info_level":"1","img_path":"/attachment/information/201905/pre_1558836275goaga.jpg","img_count":"7","upgrade_type":"1","upgrade_time":"0","upgrade_type_list":"1","upgrade_time_list":"0","ifred":"0","ifbold":"0","certify":"0","dir_typename":"dog","upgrade_type_index":"0","upgrade_time_index":"0","upgrade_type_allindex":null,"upgrade_time_allindex":null,"mappoint":"","latitude":"0.00000000000000000","longitude":"0.00000000000000000","web_address":"北京","catname":"狗狗","gid":"8","video":"","upgrade_type_index_video":"0","upgrade_time_index_video":"0","areaname":"西城","streetname":"西单","parentid":"8","template_info":"info_dog","usecoin":"0","modid":"20","usertoid":"13910749461","image":[{"prepath":"/attachment/information/201905/pre_1558836275goaga.jpg","path":"/attachment/information/201905/1558836275goaga.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275hnsvt.jpg","path":"/attachment/information/201905/1558836275hnsvt.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275nguym.jpg","path":"/attachment/information/201905/1558836275nguym.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275e7a93.jpg","path":"/attachment/information/201905/1558836275e7a93.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275388et.jpg","path":"/attachment/information/201905/1558836275388et.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275rguwh.jpg","path":"/attachment/information/201905/1558836275rguwh.jpg"}],"extra":[{"title":"狗狗品种","value":"37785"},{"title":"体形","value":"小型犬"},{"title":"价格","value":"1600元/只"},{"title":"发布人身份","value":"个人"},{"title":"疫苗情况","value":"3针"},{"title":"年龄","value":"3个月"},{"title":"犬只血统","value":"纯种"},{"title":"驱虫情况","value":"已驱虫"},{"title":"待售只数","value":"23只"},{"title":"供求","value":"狗狗出售"},{"title":"狗狗品种名称","value":"博美"},{"title":"功能","value":"玩具犬"},{"title":"特性","value":"友善"},{"title":"公母","value":"公母都有"}]}
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
         * id : 21349
         * title : 哈多利球体博美出售 博美价格 北京京博犬舍
         * catid : 95
         * cityid : 1
         * areaid : 4
         * streetid : 166
         * begintime : 1558836275
         * activetime : 0
         * endtime : 0
         * content : 1．可以与您签订活体销售协议来保证购买宝宝的健康和纯种。&nbsp;<br />
         2．出售的每只狗宝宝都有自己的健康证书。&nbsp;<br />
         3．我们会随狗宝宝赠送爱心卡片内容包括：宝宝生日、疫苗注射情况、幼犬喂养注意事项。&nbsp;<br />
         4．24小时开通售后服务热线以解答您饲养中的不懂的小难题。&nbsp;<br />
         5．幼犬售出十日内发生任何疫苗范围内的传染性疾病为您保更换，一月内有任何疾病免费治疗，终生享受我们的医疗服务，保证宝宝在您身边健康快乐的成长。&nbsp;<br />
         6．可以提供看狗和送狗班车以解决您的交通不便，只需您一个电话，宝宝就会送到您家。&nbsp;<br />
         7．会随您所购买的狗宝宝赠送一册切实可行，简单易懂的饲养训练手册，帮您解决养幼犬期间的问题及麻烦。&nbsp;<br />
         8．凡是在我们这里购买的宝宝，都会赠送一袋宝宝平时用的狗粮，以保证换新的环境的也可以吃习惯。&nbsp;<br />
         9．凭购买协议，在我们的店里购买狗粮和狗的一切用品包括美容，终生享受8折优惠“我们对售后的宝宝做终生的售后服务“包括宝宝的身份证‘赛级证书’血统证书”。&nbsp;<br />
         相信我们，我们不会让您失望，专业的养殖和优良的品种及完善的售后服务，会让您毫无后顾之忧，务必做到让您买得放心，养得开心。
         * userid : 13910749461
         * contact_who : 陈先生
         * qq : 1919231932
         * weixin : null
         * email :
         * tel : 13910749461
         * updatetime : 0
         * hit : 2
         * ismember : 1
         * manage_pwd :
         * ip : 106.118.221.121
         * ip2area : 河北省唐山市电信
         * info_level : 1
         * img_path : /attachment/information/201905/pre_1558836275goaga.jpg
         * img_count : 7
         * upgrade_type : 1
         * upgrade_time : 0
         * upgrade_type_list : 1
         * upgrade_time_list : 0
         * ifred : 0
         * ifbold : 0
         * certify : 0
         * dir_typename : dog
         * upgrade_type_index : 0
         * upgrade_time_index : 0
         * upgrade_type_allindex : null
         * upgrade_time_allindex : null
         * mappoint :
         * latitude : 0.00000000000000000
         * longitude : 0.00000000000000000
         * web_address : 北京
         * catname : 狗狗
         * gid : 8
         * video :
         * upgrade_type_index_video : 0
         * upgrade_time_index_video : 0
         * areaname : 西城
         * streetname : 西单
         * parentid : 8
         * template_info : info_dog
         * usecoin : 0
         * modid : 20
         * usertoid : 13910749461
         * image : [{"prepath":"/attachment/information/201905/pre_1558836275goaga.jpg","path":"/attachment/information/201905/1558836275goaga.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275hnsvt.jpg","path":"/attachment/information/201905/1558836275hnsvt.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275nguym.jpg","path":"/attachment/information/201905/1558836275nguym.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275e7a93.jpg","path":"/attachment/information/201905/1558836275e7a93.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275388et.jpg","path":"/attachment/information/201905/1558836275388et.jpg"},{"prepath":"/attachment/information/201905/pre_1558836275rguwh.jpg","path":"/attachment/information/201905/1558836275rguwh.jpg"}]
         * extra : [{"title":"狗狗品种","value":"37785"},{"title":"体形","value":"小型犬"},{"title":"价格","value":"1600元/只"},{"title":"发布人身份","value":"个人"},{"title":"疫苗情况","value":"3针"},{"title":"年龄","value":"3个月"},{"title":"犬只血统","value":"纯种"},{"title":"驱虫情况","value":"已驱虫"},{"title":"待售只数","value":"23只"},{"title":"供求","value":"狗狗出售"},{"title":"狗狗品种名称","value":"博美"},{"title":"功能","value":"玩具犬"},{"title":"特性","value":"友善"},{"title":"公母","value":"公母都有"}]
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
        private List<ExtraBean> extra;

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

        public List<ExtraBean> getExtra() {
            return extra;
        }

        public void setExtra(List<ExtraBean> extra) {
            this.extra = extra;
        }

        public static class ImageBean {
            /**
             * prepath : /attachment/information/201905/pre_1558836275goaga.jpg
             * path : /attachment/information/201905/1558836275goaga.jpg
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

        public static class ExtraBean {
            /**
             * title : 狗狗品种
             * value : 37785
             */

            private String title;
            private String value;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
