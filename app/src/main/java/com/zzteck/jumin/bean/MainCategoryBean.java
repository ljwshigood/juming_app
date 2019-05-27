package com.zzteck.jumin.bean;

import java.io.Serializable;
import java.util.List;

public class MainCategoryBean extends BaseInfo implements Serializable {


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

    public static class DataBean extends BaseInfo implements Serializable{

        private String catid;
        private String catname;
        private String color;
        private String if_view;
        private String dir_typename;
        private String usecoin;
        private String icon;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        private boolean isSelect ;
        private List<ChildrenBean> children;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        private int index ;

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

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getIf_view() {
            return if_view;
        }

        public void setIf_view(String if_view) {
            this.if_view = if_view;
        }

        public String getDir_typename() {
            return dir_typename;
        }

        public void setDir_typename(String dir_typename) {
            this.dir_typename = dir_typename;
        }

        public String getUsecoin() {
            return usecoin;
        }

        public void setUsecoin(String usecoin) {
            this.usecoin = usecoin;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean extends BaseInfo implements Serializable{
            /**
             * catid : 41
             * catname :  租房/出租
             * if_view : 2
             * color :
             * dir_typename : chuzu
             * usecoin : 0
             */

            private String catid;
            private String catname;
            private String if_view;
            private String color;
            private String dir_typename;
            private String usecoin;

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

            public String getIf_view() {
                return if_view;
            }

            public void setIf_view(String if_view) {
                this.if_view = if_view;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getDir_typename() {
                return dir_typename;
            }

            public void setDir_typename(String dir_typename) {
                this.dir_typename = dir_typename;
            }

            public String getUsecoin() {
                return usecoin;
            }

            public void setUsecoin(String usecoin) {
                this.usecoin = usecoin;
            }
        }
    }
}
