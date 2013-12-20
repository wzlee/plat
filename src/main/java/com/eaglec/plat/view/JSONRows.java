package com.eaglec.plat.view;

import java.util.List;

public class JSONRows<T> {
	private boolean success = false;
	private List<T> rows;
	private int total;
	
	public JSONRows(boolean success, List<T> rows) {
		super();
		this.success = success;
		this.rows = rows;
	}
	public JSONRows(boolean success, List<T> rows, int total) {
		super();
		this.success = success;
		this.rows = rows;
		this.total = total;
	}
	public JSONRows() {
		// TODO Auto-generated constructor stub
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public JSONRows<T> add(JSONRows<T> t){
		this.rows.addAll(t.getRows());
		this.total+=t.getTotal();
		return this;
	}
	
}
