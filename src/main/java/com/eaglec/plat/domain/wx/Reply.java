package com.eaglec.plat.domain.wx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 回复消息结构 微信结构
 * 
 * @author Administrator
 * 
 */
public class Reply {

	private String ToUserName; // 接收方帐号（收到的OpenID）

	private String FromUserName; // 开发者微信号

	private long CreateTime; // 消息创建时间

	private String MsgType; // text

	private String Content; // 回复的消息内容,长度不超过2048字节

	private String MusicUrl; // 音乐链接

	private String HQMusicUrl; // 高质量音乐链接，WIFI环境优先使用该链接播放音乐

	private String ArticleCount; // 图文消息个数，限制为10条以内

	private List<Article> Articles; // 多条图文消息信息，默认第一个item为大图

	public Reply() {
	}

	public Reply(AutoMessage msg) {
		CreateTime = new Date().getTime();
		MsgType = msg.getType();
		Content = msg.getContent();
		if (!msg.getNewsList().isEmpty()) {
			Articles = new ArrayList<Article>();
			ArticleCount = Integer.toString((msg.getNewsList().size()));
			for (ArticleInfo a : msg.getNewsList()) {
				Articles.add(new Article(a));
			}
		}

	}

	public Reply(String toUserName, String fromUserName, long createTime,
			String msgType, String content, String musicUrl, String hQMusicUrl,
			String articleCount, List<Article> articles) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Content = content;
		MusicUrl = musicUrl;
		HQMusicUrl = hQMusicUrl;
		ArticleCount = articleCount;
		Articles = articles;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

	@Override
	public String toString() {
		return "Reply [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + ", Content=" + Content + ", MusicUrl=" + MusicUrl
				+ ", HQMusicUrl=" + HQMusicUrl + ", ArticleCount="
				+ ArticleCount + ", Articles=" + Articles + "]";
	}

}
