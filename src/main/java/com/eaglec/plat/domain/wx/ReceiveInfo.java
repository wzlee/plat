package com.eaglec.plat.domain.wx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据库存储的接收的消息
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name="receiveinfo")
public class ReceiveInfo implements Serializable {

	private static final long serialVersionUID = -4464338434700278877L;

	private int id;// 主键

	private String toUserName;// 开发者微信号

	private String fromUserName;// 发送方帐号（一个OpenID）

	private long createTime;// 消息创建时间 （整型）

	private String msgType;// text

	private String content;// 文本消息内容

	private String msgId;// 消息id，64位整型

	private String picUrl;// 图片链接

	private String location_X;// 地理位置纬度

	private String location_Y;// 地理位置经度

	private String scale;// 地图缩放大小

	private String label;// 地理位置信息

	private String title; // 消息标题

	private String description; // 消息描述

	private String url;// 消息链接

	private String event;// 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)

	private String eventKey;// 事件KEY值，与自定义菜单接口中KEY值对应

	public ReceiveInfo() {
	}

	public ReceiveInfo(String toUserName, String fromUserName, long createTime,
			String msgType, String content, String msgId, String picUrl,
			String location_X, String location_Y, String scale, String label,
			String title, String description, String url, String event,
			String eventKey) {
		super();
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.content = content;
		this.msgId = msgId;
		this.picUrl = picUrl;
		this.location_X = location_X;
		this.location_Y = location_Y;
		this.scale = scale;
		this.label = label;
		this.title = title;
		this.description = description;
		this.url = url;
		this.event = event;
		this.eventKey = eventKey;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getLocation_X() {
		return location_X;
	}

	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}

	public String getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@Override
	public String toString() {
		return "ReceiveInfo [id=" + id + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + ", content=" + content
				+ ", msgId=" + msgId + ", picUrl=" + picUrl + ", location_X="
				+ location_X + ", location_Y=" + location_Y + ", scale="
				+ scale + ", label=" + label + ", Title=" + title
				+ ", Description=" + description + ", Url=" + url + ", Event="
				+ event + ", EventKey=" + eventKey + "]";
	}
}
