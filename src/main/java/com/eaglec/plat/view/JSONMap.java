package com.eaglec.plat.view;

import java.util.Map;
/**
 * 用于封装多个集合或者对象输出json
 * @author xuwf
 * @since 2013-9-18
 *
 */
public class JSONMap {
	private boolean success = false;
	private Map<String,Object> rows;
	public JSONMap() {
	}
	public JSONMap(boolean success, Map<String, Object> rows) {
		super();
		this.success = success;
		this.rows = rows;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Map<String, Object> getRows() {
		return rows;
	}
	public void setRows(Map<String, Object> rows) {
		this.rows = rows;
	}

}
