package com.eaglec.plat.domain.wx;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="automessage")
public class AutoMessage implements Serializable {
	private static final long serialVersionUID = 7409598167123776811L;
	private Integer id;
	private String reqKey;
	private String type;
	private String content;
	private String newsCount;
	private List<ArticleInfo> newsList;
	private String clickKey;
	
	public AutoMessage() {
		super();
	}

	/**
	 * 不带newsList属性的构造方法
	 * */
	public AutoMessage(Integer id, String reqKey, String type, String content,
			String newsCount, String clickKey) {
		super();
		this.id = id;
		this.reqKey = reqKey;
		this.type = type;
		this.content = content;
		this.newsCount = newsCount;
		this.clickKey = clickKey;
	}
	
	/**
	 * 不带newsList属性的构造方法
	 * */
	public AutoMessage(AutoMessage autoMessage) {
		super();
		this.id = autoMessage.getId();
		this.reqKey = autoMessage.getReqKey();
		this.type = autoMessage.getType();
		this.content = autoMessage.getContent();
		this.newsCount = autoMessage.getNewsCount();
		this.clickKey = autoMessage.getClickKey();
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return this.id;
	}

	public String getReqKey() {
		return this.reqKey;
	}

	public String getType() {
		return this.type;
	}

	public String getContent() {
		return this.content;
	}

	public String getNewsCount() {
		return this.newsCount;
	}

	@OneToMany(mappedBy = "autoMessage", fetch = FetchType.EAGER)
	public List<ArticleInfo> getNewsList() {
		return this.newsList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setReqKey(String reqKey) {
		this.reqKey = reqKey;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setNewsCount(String newsCount) {
		this.newsCount = newsCount;
	}

	public void setNewsList(List<ArticleInfo> newsList) {
		this.newsList = newsList;
	}

	public String getClickKey() {
		return clickKey;
	}

	public void setClickKey(String clickKey) {
		this.clickKey = clickKey;
	}
}