package com.zzteck.jumin.bean;

import java.io.Serializable;
import java.util.List;

public class CategoryDetailHeader implements Serializable {


    private String filePath ;

    private int type ;

    private String thumbPath ;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }
}
