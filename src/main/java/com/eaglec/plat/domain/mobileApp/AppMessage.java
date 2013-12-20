package com.eaglec.plat.domain.mobileApp;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 给APP推送的消息
 * */
@Entity
@Table(name="appmessage")
public class AppMessage implements Serializable {

	private static final long serialVersionUID = 7812462980723911535L;
	
	private Integer id; 		// 主键
	private String title; 		// 标题,限制12个字符
	private String content;		// 消息内容,限制200个字符以内
	private String time;		// 时间戳,格式如1386122532745
	private String picture;		// 图片,仅限一张
	private Integer flag;		// 标识,0成功,1失败,2发送中
	private String message;		// 推送结果,成功时为空,出错时是错误信息

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AppMessage [id=" + id + ", title=" + title + ", content="
				+ content + ", time=" + time + ", picture=" + picture
				+ ", flag=" + flag + "]";
	}

}
