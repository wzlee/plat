package com.eaglec.plat.view;

import java.io.Serializable;

import com.eaglec.plat.domain.auth.Manager;

/**
 * 管理员信息和平台名称视图
 * 
 * @author liuliping
 * @since 2013-10-17
 * */
public class FlatManagerView implements Serializable {

	private static final long serialVersionUID = 6579047294200859852L;

	private Manager manager;

	private String flatName;

	public FlatManagerView(Manager manager, String flatName) {
		this.manager = manager;
		this.flatName = flatName;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getFlatName() {
		return flatName;
	}

	public void setFlatName(String flatName) {
		this.flatName = flatName;
	}
}
