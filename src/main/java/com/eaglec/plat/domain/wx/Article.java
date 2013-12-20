package com.eaglec.plat.domain.wx;


/**
 * 图文消息信息类 微信格式
 * 
 * @author huyj
 * 
 */
public class Article {

	private String Title; // 图文消息标题
	private String Description; // 图文消息描述
	private String PicUrl; // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。
	private String Url; // 点击图文消息跳转链接

	public Article() {
	}

	public Article(ArticleInfo a) {
		Title = a.getTitle();
		Description = a.getDescription();
		PicUrl = a.getPicUrl();
		Url = a.getUrl();
	}

	public Article(String title, String description, String picUrl, String url) {
		super();
		Title = title;
		Description = description;
		PicUrl = picUrl;
		Url = url;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public String getDescription() {
		return this.Description == null ? "" : this.Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getPicUrl() {
		return this.PicUrl == null ? "" : this.PicUrl;
	}

	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}

	public String getUrl() {
		return this.Url == null ? "" : this.Url;
	}

	public void setUrl(String url) {
		this.Url = url;
	}

	@Override
	public String toString() {
		return "Article [Title=" + Title + ", Description=" + Description
				+ ", PicUrl=" + PicUrl + ", Url=" + Url + "]";
	}
}
