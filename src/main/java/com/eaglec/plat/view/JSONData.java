package com.eaglec.plat.view;

import java.util.List;

public class JSONData<T> {
	private boolean success = false;
	private List<T> data;
	private int total;
	
	public JSONData(boolean success, List<T> data, int total) {
		super();
		this.success = success;
		this.data = data;
		this.total = total;
	}
	public JSONData() {
		// TODO Auto-generated constructor stub
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
