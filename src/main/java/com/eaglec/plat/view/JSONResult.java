package com.eaglec.plat.view;

public class JSONResult {
	private boolean success = false;
	private String message;
	private Object backupfield;
	
	public JSONResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	public JSONResult(boolean success, String message, Object backupfield) {
		super();
		this.success = success;
		this.message = message;
		this.backupfield = backupfield;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getBackupfield() {
		return backupfield;
	}
	public void setBackupfield(Object backupfield) {
		this.backupfield = backupfield;
	}
	
}
