package com.eaglec.plat.domain.wx;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 微信平台动态实体类 
 * */
@Entity
@Table(name="wxnews")
public class WXNews {

	@Id
	@GeneratedValue
	private Integer id; // 主键id，自动增长

	@Column(length=100)
	private String title;	//标题
	
	private String description;    //描述
	
	@Column(length=1000000)
	private String content;	//内容
	
	@Column(length=20)
	private String author;	//作者
	
	private String pubdate = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");	//发布时间
	
	private String picture;    //图片

	public WXNews() {
		super();
	}

	public WXNews(Integer id, String title, String description, String content,
			String author, String pubdate, String picture) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.author = author;
		this.pubdate = pubdate;
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public Integer getId() {
		return id;
	}

	public String getPicture() {
		return picture;
	}

	public String getPubdate() {
		return pubdate;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
