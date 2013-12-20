package com.eaglec.plat.domain.wx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 图文消息信息类 数据库结构
 * 
 * @author huyj
 * 
 */
@Entity
@Table(name="articleinfo")
public class ArticleInfo implements Serializable {

	private static final long serialVersionUID = 7488194485512714722L;

	private Integer id; // 主键 自动增长

	private String title; // 图文消息标题

	private String description; // 图文消息描述

	private String picUrl; // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。

	private String url; // 点击图文消息跳转链接

	private AutoMessage autoMessage; // 标识属于哪一回复消息
	
	private String pubdate;	//发布时间
	
	public ArticleInfo() {
	}

	/**
	 * 构造方法,去掉autoMessage中的newList,方便转换成jsonobject
	 * */
	public ArticleInfo(ArticleInfo articleInfo) {
		super();
		this.id = articleInfo.getId();
		this.title = articleInfo.getTitle();
		this.description = articleInfo.getDescription();
		this.picUrl = articleInfo.getPicUrl();
		this.url = articleInfo.getUrl();
		this.pubdate = articleInfo.getPubdate();
		if(articleInfo.getAutoMessage() != null) {
			this.autoMessage = articleInfo.getAutoMessage();
			this.autoMessage.setNewsList(null);
		}
	}


	@ManyToOne
	@JoinColumn(name = "msgId")
	public AutoMessage getAutoMessage() {
		return autoMessage;
	}

	public String getDescription() {
		return this.description == null ? "" : this.description;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public String getPicUrl() {
		return this.picUrl == null ? "" : this.picUrl;
	}

	public String getPubdate() {
		return pubdate;
	}

	public String getTitle() {
		return this.title;
	}

	public String getUrl() {
		return this.url == null ? "" : this.url;
	}

	public void setAutoMessage(AutoMessage autoMessage) {
		this.autoMessage = autoMessage;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		return "ArticleInfo [id=" + id + ", title=" + title + ", description="
				+ description + ", picUrl=" + picUrl + ", url=" + url
				+ ", autoMessage=" + autoMessage + ", pubdate=" + pubdate + "]";
	}

}
