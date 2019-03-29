package com.zzteck.jumin.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {


    /**
     * ret : 200
     * data : {"id":"11254","userid":"13417434862","openid":"","openid_wx":"","catid":"","areaid":"","cname":"","tname":"","introduce":"","sex":"男","tel":"","address":"","busway":"","mappoint":"","latitude":"0.00000000000000000","longitude":"0.00000000000000000","qq":"","msn":"","email":"","template":"","keywords":"","logo":"","prelogo":"","banner":"","safequestion":"","safeanswer":"","levelid":"1","money_own":"0","credit":"0","credits":"1","score":"0","joinip":"183.15.177.146","loginip":"","jointime":"1553850436","logintime":"1553850682","qdtime":"0","web":"","per_certify":"0","com_certify":"0","if_corp":"0","ifindex":"1","iflist":"1","mobile":"13417434862","levelup_time":"0","hit":"0","cityid":"0","streetid":"0","status":"0","nickname":null,"sign":"c95f95416477c2eef0368fea721f7bc8","info":"登陆成功","is_login":true}
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
         * id : 11254
         * userid : 13417434862
         * openid :
         * openid_wx :
         * catid :
         * areaid :
         * cname :
         * tname :
         * introduce :
         * sex : 男
         * tel :
         * address :
         * busway :
         * mappoint :
         * latitude : 0.00000000000000000
         * longitude : 0.00000000000000000
         * qq :
         * msn :
         * email :
         * template :
         * keywords :
         * logo :
         * prelogo :
         * banner :
         * safequestion :
         * safeanswer :
         * levelid : 1
         * money_own : 0
         * credit : 0
         * credits : 1
         * score : 0
         * joinip : 183.15.177.146
         * loginip :
         * jointime : 1553850436
         * logintime : 1553850682
         * qdtime : 0
         * web :
         * per_certify : 0
         * com_certify : 0
         * if_corp : 0
         * ifindex : 1
         * iflist : 1
         * mobile : 13417434862
         * levelup_time : 0
         * hit : 0
         * cityid : 0
         * streetid : 0
         * status : 0
         * nickname : null
         * sign : c95f95416477c2eef0368fea721f7bc8
         * info : 登陆成功
         * is_login : true
         */

        private String id;
        private String userid;
        private String openid;
        private String openid_wx;
        private String catid;
        private String areaid;
        private String cname;
        private String tname;
        private String introduce;
        private String sex;
        private String tel;
        private String address;
        private String busway;
        private String mappoint;
        private String latitude;
        private String longitude;
        private String qq;
        private String msn;
        private String email;
        private String template;
        private String keywords;
        private String logo;
        private String prelogo;
        private String banner;
        private String safequestion;
        private String safeanswer;
        private String levelid;
        private String money_own;
        private String credit;
        private String credits;
        private String score;
        private String joinip;
        private String loginip;
        private String jointime;
        private String logintime;
        private String qdtime;
        private String web;
        private String per_certify;
        private String com_certify;
        private String if_corp;
        private String ifindex;
        private String iflist;
        private String mobile;
        private String levelup_time;
        private String hit;
        private String cityid;
        private String streetid;
        private String status;
        private Object nickname;
        private String sign;
        private String info;
        private boolean is_login;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getOpenid_wx() {
            return openid_wx;
        }

        public void setOpenid_wx(String openid_wx) {
            this.openid_wx = openid_wx;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusway() {
            return busway;
        }

        public void setBusway(String busway) {
            this.busway = busway;
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

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getPrelogo() {
            return prelogo;
        }

        public void setPrelogo(String prelogo) {
            this.prelogo = prelogo;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getSafequestion() {
            return safequestion;
        }

        public void setSafequestion(String safequestion) {
            this.safequestion = safequestion;
        }

        public String getSafeanswer() {
            return safeanswer;
        }

        public void setSafeanswer(String safeanswer) {
            this.safeanswer = safeanswer;
        }

        public String getLevelid() {
            return levelid;
        }

        public void setLevelid(String levelid) {
            this.levelid = levelid;
        }

        public String getMoney_own() {
            return money_own;
        }

        public void setMoney_own(String money_own) {
            this.money_own = money_own;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getCredits() {
            return credits;
        }

        public void setCredits(String credits) {
            this.credits = credits;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getJoinip() {
            return joinip;
        }

        public void setJoinip(String joinip) {
            this.joinip = joinip;
        }

        public String getLoginip() {
            return loginip;
        }

        public void setLoginip(String loginip) {
            this.loginip = loginip;
        }

        public String getJointime() {
            return jointime;
        }

        public void setJointime(String jointime) {
            this.jointime = jointime;
        }

        public String getLogintime() {
            return logintime;
        }

        public void setLogintime(String logintime) {
            this.logintime = logintime;
        }

        public String getQdtime() {
            return qdtime;
        }

        public void setQdtime(String qdtime) {
            this.qdtime = qdtime;
        }

        public String getWeb() {
            return web;
        }

        public void setWeb(String web) {
            this.web = web;
        }

        public String getPer_certify() {
            return per_certify;
        }

        public void setPer_certify(String per_certify) {
            this.per_certify = per_certify;
        }

        public String getCom_certify() {
            return com_certify;
        }

        public void setCom_certify(String com_certify) {
            this.com_certify = com_certify;
        }

        public String getIf_corp() {
            return if_corp;
        }

        public void setIf_corp(String if_corp) {
            this.if_corp = if_corp;
        }

        public String getIfindex() {
            return ifindex;
        }

        public void setIfindex(String ifindex) {
            this.ifindex = ifindex;
        }

        public String getIflist() {
            return iflist;
        }

        public void setIflist(String iflist) {
            this.iflist = iflist;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getLevelup_time() {
            return levelup_time;
        }

        public void setLevelup_time(String levelup_time) {
            this.levelup_time = levelup_time;
        }

        public String getHit() {
            return hit;
        }

        public void setHit(String hit) {
            this.hit = hit;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getStreetid() {
            return streetid;
        }

        public void setStreetid(String streetid) {
            this.streetid = streetid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public boolean isIs_login() {
            return is_login;
        }

        public void setIs_login(boolean is_login) {
            this.is_login = is_login;
        }
    }
}
