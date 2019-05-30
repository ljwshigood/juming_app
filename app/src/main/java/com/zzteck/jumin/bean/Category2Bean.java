package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class Category2Bean implements Serializable{


    /**
     * ret : 200
     * data : {"isyes":1,"catid":3,"img":[{"catid":"43","catname":"二手房出售","icon":"/web/image/f_1.jpg"},{"catid":"46","catname":"商铺出售","icon":"/web/image/f_2.jpg"},{"catid":"45","catname":"商铺出租","icon":"/web/image/f_3.jpg"},{"catid":"41","catname":"房屋出租","icon":"/web/image/f_4.jpg"},{"catid":"251","catname":"商铺转让","icon":"/web/image/f_5.jpg"}],"area":[],"extra":[{"catname":"出租","icon":"/web/image/z_1.png","list":[{"catname":"租房/出租","catid":"41"},{"catname":"日租/短租","catid":"42"},{"catname":"求租","catid":"50"},{"catname":"商铺出租","catid":"45"},{"catname":"写字楼出租","catid":"47"},{"catname":"厂房出租","catid":"49","extr":"factorytype=1"},{"catname":"仓库出租","catid":"49","extr":"factorytype=3"},{"catname":"土地出租","catid":"49","extr":"factorytype=5"},{"catname":"车位出租","catid":"49","extr":"factorytype=7"}]},{"catname":"出售","icon":"/web/image/z_2.png","list":{"0":{"catname":"房屋出租","catid":"46"},"1":{"catname":"二手房出售","catid":"43"},"2":{"catname":"写字楼出售","catid":"48"},"3":{"catname":"厂房出售","catid":"49","extr":"factorytype=2"},"4":{"catname":"仓库出售","catid":"49","extr":"factorytype=4"},"5":{"catname":"土地出售","extr":"factorytype=6"},"25":{"catid":"49"},"6":{"catname":"车位出售","catid":"49","extr":"factorytype=8"}}},{"catname":"商铺","icon":"/web/image/z_3.png","list":[{"catname":"商铺出租","catid":"45"},{"catname":"商铺出售","catid":"46"},{"catname":"商铺转让","catid":"251"},{"catname":"写字楼出租","catid":"47"},{"catname":"写字楼出售","catid":"48"}]},{"catname":"厂房","icon":"/web/image/z_4.png","list":[{"catname":"厂房出租","catid":"49","extr":"factory_type=1"},{"catname":"厂房出售","catid":"49","extr":"factory_type=2"},{"catname":"仓库出租","catid":"49","extr":"factory_type=3"},{"catname":"仓库出售","catid":"49","extr":"factory_type=4"},{"catname":"土地出租","catid":"49","extr":"factory_type=5"},{"catname":"土地出售","catid":"49","extr":"factory_type=6"},{"catname":"车位出租","catid":"49","extr":"factory_type=7"},{"catname":"车位出售","catid":"49","extr":"factory_type=8"}]}]}
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
         * isyes : 1
         * catid : 3
         * img : [{"catid":"43","catname":"二手房出售","icon":"/web/image/f_1.jpg"},{"catid":"46","catname":"商铺出售","icon":"/web/image/f_2.jpg"},{"catid":"45","catname":"商铺出租","icon":"/web/image/f_3.jpg"},{"catid":"41","catname":"房屋出租","icon":"/web/image/f_4.jpg"},{"catid":"251","catname":"商铺转让","icon":"/web/image/f_5.jpg"}]
         * area : []
         * extra : [{"catname":"出租","icon":"/web/image/z_1.png","list":[{"catname":"租房/出租","catid":"41"},{"catname":"日租/短租","catid":"42"},{"catname":"求租","catid":"50"},{"catname":"商铺出租","catid":"45"},{"catname":"写字楼出租","catid":"47"},{"catname":"厂房出租","catid":"49","extr":"factorytype=1"},{"catname":"仓库出租","catid":"49","extr":"factorytype=3"},{"catname":"土地出租","catid":"49","extr":"factorytype=5"},{"catname":"车位出租","catid":"49","extr":"factorytype=7"}]},{"catname":"出售","icon":"/web/image/z_2.png","list":{"0":{"catname":"房屋出租","catid":"46"},"1":{"catname":"二手房出售","catid":"43"},"2":{"catname":"写字楼出售","catid":"48"},"3":{"catname":"厂房出售","catid":"49","extr":"factorytype=2"},"4":{"catname":"仓库出售","catid":"49","extr":"factorytype=4"},"5":{"catname":"土地出售","extr":"factorytype=6"},"25":{"catid":"49"},"6":{"catname":"车位出售","catid":"49","extr":"factorytype=8"}}},{"catname":"商铺","icon":"/web/image/z_3.png","list":[{"catname":"商铺出租","catid":"45"},{"catname":"商铺出售","catid":"46"},{"catname":"商铺转让","catid":"251"},{"catname":"写字楼出租","catid":"47"},{"catname":"写字楼出售","catid":"48"}]},{"catname":"厂房","icon":"/web/image/z_4.png","list":[{"catname":"厂房出租","catid":"49","extr":"factory_type=1"},{"catname":"厂房出售","catid":"49","extr":"factory_type=2"},{"catname":"仓库出租","catid":"49","extr":"factory_type=3"},{"catname":"仓库出售","catid":"49","extr":"factory_type=4"},{"catname":"土地出租","catid":"49","extr":"factory_type=5"},{"catname":"土地出售","catid":"49","extr":"factory_type=6"},{"catname":"车位出租","catid":"49","extr":"factory_type=7"},{"catname":"车位出售","catid":"49","extr":"factory_type=8"}]}]
         */

        private int isyes;
        private int catid;
        private List<ImgBean> img;
        private List<?> area;
        private List<ExtraBean> extra;

        public int getIsyes() {
            return isyes;
        }

        public void setIsyes(int isyes) {
            this.isyes = isyes;
        }

        public int getCatid() {
            return catid;
        }

        public void setCatid(int catid) {
            this.catid = catid;
        }

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public List<?> getArea() {
            return area;
        }

        public void setArea(List<?> area) {
            this.area = area;
        }

        public List<ExtraBean> getExtra() {
            return extra;
        }

        public void setExtra(List<ExtraBean> extra) {
            this.extra = extra;
        }

        public static class ImgBean {
            /**
             * catid : 43
             * catname : 二手房出售
             * icon : /web/image/f_1.jpg
             */

            private String catid;
            private String catname;
            private String icon;

            public String getCatid() {
                return catid;
            }

            public void setCatid(String catid) {
                this.catid = catid;
            }

            public String getCatname() {
                return catname;
            }

            public void setCatname(String catname) {
                this.catname = catname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class ExtraBean extends BaseInfo{
            /**
             * catname : 出租
             * icon : /web/image/z_1.png
             * list : [{"catname":"租房/出租","catid":"41"},{"catname":"日租/短租","catid":"42"},{"catname":"求租","catid":"50"},{"catname":"商铺出租","catid":"45"},{"catname":"写字楼出租","catid":"47"},{"catname":"厂房出租","catid":"49","extr":"factorytype=1"},{"catname":"仓库出租","catid":"49","extr":"factorytype=3"},{"catname":"土地出租","catid":"49","extr":"factorytype=5"},{"catname":"车位出租","catid":"49","extr":"factorytype=7"}]
             */

            private String catname;
            private String icon;

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            private List<ListBean> list;

            public List<ListBean> getMainList() {
                return mainList;
            }

            public void setMainList(List<ListBean> mainList) {
                this.mainList = mainList;
            }

            public List<ListBean> getChildList() {
                return childList;
            }

            public void setChildList(List<ListBean> childList) {
                this.childList = childList;
            }

            private List<ListBean> childList ;

            private List<ListBean> mainList ;

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int index ;

            public String getCatname() {
                return catname;
            }

            public void setCatname(String catname) {
                this.catname = catname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public static class ListBean extends BaseInfo{
                /**
                 * catname : 租房/出租
                 * catid : 41
                 * extr : factorytype=1
                 */

                private String catname;
                private String catid;
                private String extr;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                private int type ;

                public String getCatname() {
                    return catname;
                }

                public void setCatname(String catname) {
                    this.catname = catname;
                }

                public String getCatid() {
                    return catid;
                }

                public void setCatid(String catid) {
                    this.catid = catid;
                }

                public String getExtr() {
                    return extr;
                }

                public void setExtr(String extr) {
                    this.extr = extr;
                }
            }
        }
    }
}
