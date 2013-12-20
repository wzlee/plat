package com.eaglec.plat.view;

import java.io.Serializable;

import com.eaglec.plat.utils.Common;

public class EnterpriseView implements Serializable{

	private static final long serialVersionUID = -4248864544687051135L;

	private Integer id;	
	
	private String name;	//机构名称
	
	private String url;		//机构介绍主页

	public EnterpriseView() {
		super();
	}

	public EnterpriseView(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.url = new StringBuilder().
				append(Common.PLAT_DOMAIN).append("/enter/detailEnter?eid=").
				append(id).toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}