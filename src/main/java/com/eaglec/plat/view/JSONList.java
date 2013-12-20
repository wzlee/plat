package com.eaglec.plat.view;

import java.util.List;
/**
 * 用于封装多个集合或者对象输出json
 * @author xuwf
 * @since 2013-9-18
 *
 */
public class JSONList<T>{
	
	private boolean success = false;
	private List<T> list;
	
	public JSONList() {
	}
	
	public JSONList(boolean success, List<T> list) {
		super();
		this.success = success;
		this.list = list;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}
