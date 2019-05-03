package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class ImageInfo implements Serializable {


    /**
     * ret : 200
     * data : {"img":"/attachment/information/201905/1556856833ugnn5.jpg","preimg":"/attachment/information/201905/pre_1556856833ugnn5.jpg","imgid":[],"info":"上传成功"}
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
         * img : /attachment/information/201905/1556856833ugnn5.jpg
         * preimg : /attachment/information/201905/pre_1556856833ugnn5.jpg
         * imgid : []
         * info : 上传成功
         */

        private String img;
        private String preimg;
        private String info;
        private List<?> imgid;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPreimg() {
            return preimg;
        }

        public void setPreimg(String preimg) {
            this.preimg = preimg;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public List<?> getImgid() {
            return imgid;
        }

        public void setImgid(List<?> imgid) {
            this.imgid = imgid;
        }
    }
}
