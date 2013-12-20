package com.eaglec.plat.view;

import java.util.List;

/**
 * 面包屑类
 * @author liuliping
 * @since 2013-10-09
 * */
public class Breadcrumb {
	private List<String> names;    //按照面包屑的层级关系,封装了页面上的每一片面包屑的名称.例如"服务机构A类","政企服务"

	public Breadcrumb() {}
	
	public Breadcrumb(List<String> names) {
		this.names = names;
	}
	
	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
}
