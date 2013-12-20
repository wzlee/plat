package com.eaglec.plat.domain.info;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

// 存储站内消息

@Entity
@Table(name="message")
public class  Message implements Serializable {  

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Lob
	@Column(name="content", columnDefinition="TEXT", nullable=true)
	private String content;//消息内容
	   
	private String sendTime =  DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");//发送时间
	   
	private MessageClass messageClass;
	
	public Message() {
	}
	
	public Message(String content, MessageClass messageClass) {
		this.content = content;
		this.messageClass = messageClass;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	@ManyToOne
	public MessageClass getMessageClass() {
		return messageClass;
	}

	public void setMessageClass(MessageClass messageClass) {
		this.messageClass = messageClass;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", sendTime="
				+ sendTime + ", messageClass=" + messageClass + "]";
	}
}