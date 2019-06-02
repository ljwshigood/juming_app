package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class ExternalInfo implements Serializable {


    /**
     * ret : 200
     * data : [{"required":1,"title":"来源","type":"radio","placeholder":"","identifier":"from","extra":{"choices":"1=个人\r\n2=商家"},"value":""},{"required":1,"title":"价格","type":"number","placeholder":"","identifier":"price","extra":{"units":"元","choices":"1~1000=1000以内\r\n1000~3000=1000~3000\r\n3000~5000=3000~5000\r\n5000~10000=5000~10000\r\n10000~=10000以上"},"value":""},{"required":1,"title":"新旧程度","type":"select","placeholder":"","identifier":"new_old","extra":{"choices":"1=全新\r\n2=9成新\r\n3=7成新\r\n4=5成新"},"value":""}]
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

        private String required;
        private String title;
        private String type;
        private String placeholder;
        private String identifier;
        private ExtraBean extra;
        private String value;

        public String getRequired() {
            return required;
        }

        public void setRequired(String required) {
            this.required = required;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static class ExtraBean {

            public String getUnits() {
                return units;
            }

            public void setUnits(String units) {
                this.units = units;
            }



            private String units ;

            private String choices ;

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            private String parentid ;

            public String getChoices() {
                return choices;
            }

            public void setChoices(String choices) {
                this.choices = choices;
            }
        }
    }






}
