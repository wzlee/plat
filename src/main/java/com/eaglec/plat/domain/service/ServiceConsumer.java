package com.eaglec.plat.domain.service;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 手机端用户预约服务的信息保存在此类中
 * 
 * @author liuliping
 * @since 2013-11-28
 * 
 */
@Entity
@Table(name = "serviceconsumer")
public class ServiceConsumer implements Serializable {
	private static final long serialVersionUID = -2489361704861315304L;

	private Integer id;
	private String name; // 客户名字
	private String phone; // 客户电话
	private Integer sid; // 服务id

	public ServiceConsumer() {
		super();
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

}
