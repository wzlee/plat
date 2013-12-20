package com.eaglec.plat.domain.wx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 回复消息 数据库结构
 * 
 * @author huyj
 * 
 */
@Entity
@Table(name="replyinfo")
public class ReplyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4556632304879836764L;

	private int id; // 主键，自动增长

	private String fromUserName;// 接收方帐号（收到的OpenID）

	private String toUserName; // 开发者微信号

	private String msgType; // 消息类型

	private String content; // 文本消息回复的消息内容,长度不超过2048字节

	private String articleCount; // 图文消息的图文数量

	private long createTime; // 回复时间

	private int receiveId; // 对应哪一条接收的消息

	private String articleIds; // 图文消息id

	public ReplyInfo() {
	}

	public ReplyInfo(String fromUserName, String toUserName, String msgType,
			String content, String articleCount, long createTime,
			int receiveId, String articleIds) {
		super();
		this.fromUserName = fromUserName;
		this.toUserName = toUserName;
		this.msgType = msgType;
		this.content = content;
		this.articleCount = articleCount;
		this.createTime = createTime;
		this.receiveId = receiveId;
		this.articleIds = articleIds;
	}

	public ReplyInfo(Reply resMsg, int reId, String ids) {
		this.fromUserName = resMsg.getFromUserName();
		this.toUserName = resMsg.getToUserName();
		this.msgType = resMsg.getMsgType();
		this.content = resMsg.getContent();
		this.articleCount = resMsg.getArticleCount();
		this.createTime = resMsg.getCreateTime();
		this.receiveId = reId;
		this.articleIds = ids;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
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

	public String getArticleCount() {
		return articleCount;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
	}

	public String getArticleIds() {
		return articleIds;
	}

	public void setArticleIds(String articleIds) {
		this.articleIds = articleIds;
	}

	@Override
	public String toString() {
		return "ReplyInfo [id=" + id + ", fromUserName=" + fromUserName
				+ ", toUserName=" + toUserName + ", msgType=" + msgType
				+ ", content=" + content + ", articleCount=" + articleCount
				+ ", createTime=" + createTime + ", receiveId=" + receiveId
				+ ", articleIds=" + articleIds + "]";
	}

}
