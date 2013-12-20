package com.eaglec.plat.domain.info;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//存储消息类别，只有一级，没有层级关系

@Entity
@Table(name="messageclass")
public class  MessageClass implements Serializable {  
	
	private static final long serialVersionUID = 1L;
   
	private Integer id;// 主键ID
	   
	private String messageType;//类别名称
	   
	private String remark; //类别描述

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "MessageClass [id=" + id + ", messageType=" + messageType
				+ ", remark=" + remark + "]";
	}
}