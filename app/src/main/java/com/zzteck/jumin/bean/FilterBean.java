package com.zzteck.jumin.bean;

import java.io.Serializable;

public class FilterBean implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String detail ;
	
	private String subDetail ;

	private boolean isSelect ;

	private int id ;
	
	private int type = 1; // 类型0：表示区域：其他表示 1;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSubDetail() {
		return subDetail;
	}

	public void setSubDetail(String subDetail) {
		this.subDetail = subDetail;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}
	
}
