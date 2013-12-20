package com.eaglec.plat.view;

import com.eaglec.plat.domain.base.Enterprise;

public class AuthInfo {
	
	private String userName;
	private Integer sex;
	private Integer status;
	private Enterprise enterprise;
	
	public AuthInfo() {
		super();
	}
	public AuthInfo(String userName, Integer sex, Integer status,
			Enterprise enterprise) {
		super();
		this.userName = userName;
		this.sex = sex;
		this.status = status;
		this.enterprise = enterprise;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	@Override
	public String toString() {
		return "AuthInfo [userName=" + userName + ", sex=" + sex + ", status="
				+ status + ", enterprise=" + enterprise + "]";
	}
}
