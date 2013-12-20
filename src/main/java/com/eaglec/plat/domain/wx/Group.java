package com.eaglec.plat.domain.wx;

/**
 * 组信息
 * 
 * @author huyj
 * 
 */
public class Group {

	private int id; // 编号id，微信指定
	private String name; // 组名
	private String count; // 人数

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
