package com.zzteck.jumin.cityselect.entity;

/**
 * Created by next on 2016/3/24.
 */
public class EventCity {

    private String name;
    private String pinyin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id ;

    public EventCity(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }

    public EventCity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
