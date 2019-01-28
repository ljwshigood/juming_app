package com.zzteck.jumin.bean;
import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userid ;//return data
	
	private String username ;
	
	private String header ;
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPasssalt() {
		return passsalt;
	}

	public void setPasssalt(String passsalt) {
		this.passsalt = passsalt;
	}

	private String passsalt ;
	
	private String email ;
	
	private String mobile ;
	
	private String pwd ;

	private String truename ;
	
	private String ackPwd ;
	
	private String role ;
	
	private String groupid ;
	
	private String valide ;

	public String getWanjiaToken() {
		return wanjiaToken;
	}

	public void setWanjiaToken(String wanjiaToken) {
		this.wanjiaToken = wanjiaToken;
	}

	private String wanjiaToken ;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token ;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	private String avatar ;
	
	public String getValide() {
		return valide;
	}

	public void setValide(String valide) {
		this.valide = valide;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAckPwd() {
		return ackPwd;
	}

	public void setAckPwd(String ackPwd) {
		this.ackPwd = ackPwd;
	}

	public String getTrueName() {
		return truename;
	}

	public void setTrueName(String trueName) {
		this.truename = trueName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
