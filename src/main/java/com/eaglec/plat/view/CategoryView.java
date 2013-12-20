package com.eaglec.plat.view;

import java.io.Serializable;
import java.util.List;

/**
 * 服务的部分数据封装成的对象，用来在页面上显示
 * @author liuliping
 * @since 2013-08-28
 * */
public class CategoryView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4199944022558796754L;
	private Integer cid;
	private String categryName;
	private Integer pid;
	private List<ServiceView> services;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCategryName() {
		return categryName;
	}
	public void setCategryName(String categryName) {
		this.categryName = categryName;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public List<ServiceView> getServices() {
		return services;
	}
	public void setServices(List<ServiceView> services) {
		this.services = services;
	}
	
}
