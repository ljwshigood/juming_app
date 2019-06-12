package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class FilterInfo implements Serializable {


    /**
     * ret : 200
     * data : [{"required":1,"title":"手机品牌","type":"select","placeholder":"","identifier":"mbrand","extra":{"choices":"1=iphone\r\n2=三星\r\n3=小米\r\n4=乐视\r\n5=华为\r\n6=联想\r\n7=锤子\r\n8=诺基亚\r\n9=HTC\r\n10=山寨/高仿机\r\n11=MOTO\r\n12=中兴\r\n13=索尼\r\n14=其他"}},{"required":1,"title":"价格","type":"number","placeholder":"","identifier":"price","extra":{"units":"元","choices":"1~1000=1000以内\r\n1000~3000=1000~3000\r\n3000~5000=3000~5000\r\n5000~10000=5000~10000\r\n10000~=10000以上"}},{"required":1,"title":"新旧程度","type":"select","placeholder":"","identifier":"new_old","extra":{"choices":"1=全新\r\n2=9成新\r\n3=7成新\r\n4=5成新"}},{"required":1,"title":"来源","type":"radio","placeholder":"","identifier":"from","extra":{"choices":"1=个人\r\n2=商家"}}]
     * msg :
     */

    private int ret;
    private String msg;

    public Map<String, DataBean> getData() {
        return data;
    }

    public void setData(Map<String, DataBean> data) {
        this.data = data;
    }

    private Map<String,DataBean> data;

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

    public static class DataBean {
        /**
         * required : 1
         * title : 手机品牌
         * type : select
         * placeholder :
         * identifier : mbrand
         * extra : {"choices":"1=iphone\r\n2=三星\r\n3=小米\r\n4=乐视\r\n5=华为\r\n6=联想\r\n7=锤子\r\n8=诺基亚\r\n9=HTC\r\n10=山寨/高仿机\r\n11=MOTO\r\n12=中兴\r\n13=索尼\r\n14=其他"}
         */

        private int required;
        private String title;
        private String type;
        private String placeholder;
        private String identifier;
        private ExtraBean extra;

        public int getRequired() {
            return required;
        }

        public void setRequired(int required) {
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

        public static class ExtraBean {
            /**
             * choices : 1=iphone
             2=三星
             3=小米
             4=乐视
             5=华为
             6=联想
             7=锤子
             8=诺基亚
             9=HTC
             10=山寨/高仿机
             11=MOTO
             12=中兴
             13=索尼
             14=其他
             */

            private String choices;

            public String getChoices() {
                return choices;
            }

            public void setChoices(String choices) {
                this.choices = choices;
            }
        }
    }
}
