package com.eaglec.plat.domain.wx;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wxuser")
public class WXUser {

	private int id; // 主键id，自动增长

	private String name; // 用户名称，值为微信传过来的字符串

	private long createTime; // 添加时间

	private String description;// 用户描述

	private String nName; // 用户昵称,只能通过后台手动设置

	private int subscribe; // 用户是否关注了公众账号

	public WXUser() {
	}

	public WXUser(String name, long createTime, String description, String nName,
			int subscribe) {
		super();
		this.name = name;
		this.createTime = createTime;
		this.description = description;
		this.nName = nName;
		this.subscribe = subscribe;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public String getnName() {
		return nName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", createTime="
				+ createTime + ", description=" + description + ", nName="
				+ nName + ", subscribe=" + subscribe + "]";
	}

}
