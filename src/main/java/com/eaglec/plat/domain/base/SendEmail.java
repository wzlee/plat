package com.eaglec.plat.domain.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 
 * 每次发送邮箱验证用来保存相关数据的表
 * 
 * @author Xiadi
 * @since 2013-10-15
 */
@Entity
@Table(name="sendemail")
public class SendEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id; 
	
	private Integer origin;//邮件来源     1,注册    2,用户中心   3,客服
	
	private String sender;//发送方
	
	private String receiver;//接收方
	
	@Lob
	@Column(name="content", columnDefinition="TEXT", nullable=true)
	private String content;//邮件内容
	
	private boolean handle = false;//是否处理;
	
	@Column(length = 100)
	private String regTime = DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"); // 发送时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isHandle() {
		return handle;
	}

	public void setHandle(boolean handle) {
		this.handle = handle;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	@Override
	public String toString() {
		return "SendEmail [id=" + id + ", origin=" + origin + ", sender="
				+ sender + ", receiver=" + receiver + ", content=" + content
				+ ", handle=" + handle + ", regTime=" + regTime + "]";
	}

}