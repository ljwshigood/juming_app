package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class ReleaseDataBean implements Serializable{

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

    public static class ExtraBean implements Serializable{

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
