package com.zzteck.jumin.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class ExternalInfo1 implements Serializable {


    /**
     * ret : 200
     * data : {"mbrand":{"required":"1","title":"手机品牌","type":"select","placeholder":"","identifier":"mbrand","related":"","extra":{"choices":"1=iphone\r\n2=三星\r\n3=小米\r\n4=乐视\r\n5=华为\r\n6=联想\r\n7=锤子\r\n8=诺基亚\r\n9=HTC\r\n10=山寨/高仿机\r\n11=MOTO\r\n12=中兴\r\n13=索尼\r\n14=其他"},"value":""},"price":{"required":"1","title":"价格","type":"number","placeholder":"","identifier":"price","related":"","extra":{"units":"元","choices":"1~1000=1000以内\r\n1000~3000=1000~3000\r\n3000~5000=3000~5000\r\n5000~10000=5000~10000\r\n10000~=10000以上"},"value":""},"newold":{"required":"1","title":"新旧程度","type":"select","placeholder":"","identifier":"newold","related":"","extra":{"choices":"1=全新\r\n2=9成新\r\n3=7成新\r\n4=5成新"},"value":""},"from":{"required":"1","title":"发布人身份","type":"radio","placeholder":"","identifier":"from","related":"","extra":{"choices":"1=个人\r\n2=商家"},"value":""}}
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
         * mbrand : {"required":"1","title":"手机品牌","type":"select","placeholder":"","identifier":"mbrand","related":"","extra":{"choices":"1=iphone\r\n2=三星\r\n3=小米\r\n4=乐视\r\n5=华为\r\n6=联想\r\n7=锤子\r\n8=诺基亚\r\n9=HTC\r\n10=山寨/高仿机\r\n11=MOTO\r\n12=中兴\r\n13=索尼\r\n14=其他"},"value":""}
         * price : {"required":"1","title":"价格","type":"number","placeholder":"","identifier":"price","related":"","extra":{"units":"元","choices":"1~1000=1000以内\r\n1000~3000=1000~3000\r\n3000~5000=3000~5000\r\n5000~10000=5000~10000\r\n10000~=10000以上"},"value":""}
         * newold : {"required":"1","title":"新旧程度","type":"select","placeholder":"","identifier":"newold","related":"","extra":{"choices":"1=全新\r\n2=9成新\r\n3=7成新\r\n4=5成新"},"value":""}
         * from : {"required":"1","title":"发布人身份","type":"radio","placeholder":"","identifier":"from","related":"","extra":{"choices":"1=个人\r\n2=商家"},"value":""}
         */

        private MbrandBean mbrand;
        private PriceBean price;
        private NewoldBean newold;
        private FromBean from;

        public MbrandBean getMbrand() {
            return mbrand;
        }

        public void setMbrand(MbrandBean mbrand) {
            this.mbrand = mbrand;
        }

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
        }

        public NewoldBean getNewold() {
            return newold;
        }

        public void setNewold(NewoldBean newold) {
            this.newold = newold;
        }

        public FromBean getFrom() {
            return from;
        }

        public void setFrom(FromBean from) {
            this.from = from;
        }

        public static class MbrandBean {
            /**
             * required : 1
             * title : 手机品牌
             * type : select
             * placeholder :
             * identifier : mbrand
             * related :
             * extra : {"choices":"1=iphone\r\n2=三星\r\n3=小米\r\n4=乐视\r\n5=华为\r\n6=联想\r\n7=锤子\r\n8=诺基亚\r\n9=HTC\r\n10=山寨/高仿机\r\n11=MOTO\r\n12=中兴\r\n13=索尼\r\n14=其他"}
             * value :
             */

            private String required;
            private String title;
            private String type;
            private String placeholder;
            private String identifier;
            private String related;
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

            public String getRelated() {
                return related;
            }

            public void setRelated(String related) {
                this.related = related;
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

        public static class PriceBean {
            /**
             * required : 1
             * title : 价格
             * type : number
             * placeholder :
             * identifier : price
             * related :
             * extra : {"units":"元","choices":"1~1000=1000以内\r\n1000~3000=1000~3000\r\n3000~5000=3000~5000\r\n5000~10000=5000~10000\r\n10000~=10000以上"}
             * value :
             */

            private String required;
            private String title;
            private String type;
            private String placeholder;
            private String identifier;
            private String related;
            private ExtraBeanX extra;
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

            public String getRelated() {
                return related;
            }

            public void setRelated(String related) {
                this.related = related;
            }

            public ExtraBeanX getExtra() {
                return extra;
            }

            public void setExtra(ExtraBeanX extra) {
                this.extra = extra;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public static class ExtraBeanX {
                /**
                 * units : 元
                 * choices : 1~1000=1000以内
                 1000~3000=1000~3000
                 3000~5000=3000~5000
                 5000~10000=5000~10000
                 10000~=10000以上
                 */

                private String units;
                private String choices;

                public String getUnits() {
                    return units;
                }

                public void setUnits(String units) {
                    this.units = units;
                }

                public String getChoices() {
                    return choices;
                }

                public void setChoices(String choices) {
                    this.choices = choices;
                }
            }
        }

        public static class NewoldBean {
            /**
             * required : 1
             * title : 新旧程度
             * type : select
             * placeholder :
             * identifier : newold
             * related :
             * extra : {"choices":"1=全新\r\n2=9成新\r\n3=7成新\r\n4=5成新"}
             * value :
             */

            private String required;
            private String title;
            private String type;
            private String placeholder;
            private String identifier;
            private String related;
            private ExtraBeanXX extra;
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

            public String getRelated() {
                return related;
            }

            public void setRelated(String related) {
                this.related = related;
            }

            public ExtraBeanXX getExtra() {
                return extra;
            }

            public void setExtra(ExtraBeanXX extra) {
                this.extra = extra;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public static class ExtraBeanXX {
                /**
                 * choices : 1=全新
                 2=9成新
                 3=7成新
                 4=5成新
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

        public static class FromBean {
            /**
             * required : 1
             * title : 发布人身份
             * type : radio
             * placeholder :
             * identifier : from
             * related :
             * extra : {"choices":"1=个人\r\n2=商家"}
             * value :
             */

            private String required;
            private String title;
            private String type;
            private String placeholder;
            private String identifier;
            private String related;
            private ExtraBeanXXX extra;
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

            public String getRelated() {
                return related;
            }

            public void setRelated(String related) {
                this.related = related;
            }

            public ExtraBeanXXX getExtra() {
                return extra;
            }

            public void setExtra(ExtraBeanXXX extra) {
                this.extra = extra;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public static class ExtraBeanXXX {
                /**
                 * choices : 1=个人
                 2=商家
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
}
