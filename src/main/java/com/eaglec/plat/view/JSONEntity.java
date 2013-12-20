package com.eaglec.plat.view;

public class JSONEntity<T> {
	private boolean success = false;
	private T data;
	
	public JSONEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JSONEntity(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
