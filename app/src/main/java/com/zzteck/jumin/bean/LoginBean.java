package com.zzteck.jumin.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {

    private int errcode ;
    private String errmsg ;

    private Data data ;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{

        private String token ;

        private UserInfo userInfo ;

        private String rongyuantoken ;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public String getRongyuantoken() {
            return rongyuantoken;
        }

        public void setRongyuantoken(String rongyuantoken) {
            this.rongyuantoken = rongyuantoken;
        }
    }

    public class UserInfo{
        private String  name ;
        private int star ;
        private int working_years ;
        private String address ;
        private String mobile ;
        private String email ;
        private String service_count ;
        private String speciality ;
        private int is_authentication ;
        private int third_plat_id ;
        private int third_plat_type ;
        private int status ;
        private long created ;
        private long modified ;
        private int id ;

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        private String header ;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public int getWorking_years() {
            return working_years;
        }

        public void setWorking_years(int working_years) {
            this.working_years = working_years;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getService_count() {
            return service_count;
        }

        public void setService_count(String service_count) {
            this.service_count = service_count;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public int getIs_authentication() {
            return is_authentication;
        }

        public void setIs_authentication(int is_authentication) {
            this.is_authentication = is_authentication;
        }

        public int getThird_plat_id() {
            return third_plat_id;
        }

        public void setThird_plat_id(int third_plat_id) {
            this.third_plat_id = third_plat_id;
        }

        public int getThird_plat_type() {
            return third_plat_type;
        }

        public void setThird_plat_type(int third_plat_type) {
            this.third_plat_type = third_plat_type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreated() {
            return created;
        }

        public void setCreated(long created) {
            this.created = created;
        }

        public long getModified() {
            return modified;
        }

        public void setModified(long modified) {
            this.modified = modified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
