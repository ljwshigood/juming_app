package com.zzteck.jumin.bean;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class ChapterInfo extends BaseInfo {
    public String name;

    public int res ;
    public int chapterIndex;
    public List<SectionInfo> sectionInfos = new ArrayList<>();



}

/*
public class ChapterInfo extends BaseInfo {

    public String name;
    public int chapterIndex;

    public List<SectionInfo> sectionInfos = new ArrayList<>();
}

public class SectionInfo extends BaseInfo {

    public String name;
    public int chapterIndex;
    public int sectionIndex;
}

public class BaseInfo implements Serializable {
}
*/
