package com.eaglec.plat.view;

/**
 * api授权/调用返回结果视图
 * @author wzlee
 *
 */
public class APIResult {
	boolean success = true;
	int code = 0;
	String message = "";
	public APIResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public APIResult(boolean success, int code, String message) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "APIResult [success=" + success + ", code=" + code
				+ ", message=" + message + "]";
	}
	
}
