package com.zzteck.jumin.bean;
import java.io.Serializable;

/**
 * 
 * @description:
 *
 * author : liujw
 * modify : 
 * 2015-3-2 下午4:35:46
 *
 * 
 */
public class ConfigBean implements Serializable {

	private int res ;

	private String info ;

	private int isSelect ;

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(int isSelect) {
		this.isSelect = isSelect;
	}
}
