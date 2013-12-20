package com.eaglec.plat.view;

import java.io.Serializable;

public class FlatView implements Serializable{
	private static final long serialVersionUID = 3464950176174875331L;

	private Integer id;
	private String flatName; // 平台名称
	private String website; // 平台网址
	
	public FlatView() {
		super();
	}
	public FlatView(Integer id, String flatName, String website) {
		super();
		this.id = id;
		this.flatName = flatName;
		this.website = website;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFlatName() {
		return flatName;
	}
	public void setFlatName(String flatName) {
		this.flatName = flatName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
}
