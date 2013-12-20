package com.eaglec.plat.domain.info;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sendermessagerelationship")
public class  SenderMessageRelationship implements Serializable {  
   
	private static final long serialVersionUID = 1L;
	
	private int id;//主键ID
	   
	private Integer sendManagerId;//发送人(运营人员)  外键关联Manager类的id
	
	private Integer sendUserId;//发送人(企业用户或者机构用户或个人用户)   外键关联User类的id
	   
	private Integer sendStaffId;//发送人(企业或者机构子帐号)  外键关联Staff类的id
	   
	private Integer deleteSign = 0; //删除标记 0未删除；1删除  默认为0
	   
	private Message message;//消息
	
	private String receiver;//点对点接收人名字
	
	public SenderMessageRelationship() {
	}
	
	public SenderMessageRelationship(Message message, String receiver) {
		this.message = message;
		this.receiver = receiver;
	}


	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getSendManagerId() {
		return sendManagerId;
	}

	public void setSendManagerId(Integer sendManagerId) {
		this.sendManagerId = sendManagerId;
	}

	public Integer getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}

	public Integer getSendStaffId() {
		return sendStaffId;
	}

	public void setSendStaffId(Integer sendStaffId) {
		this.sendStaffId = sendStaffId;
	}

	public Integer getDeleteSign() {
		return deleteSign;
	}

	public void setDeleteSign(Integer deleteSign) {
		this.deleteSign = deleteSign;
	}

	@OneToOne
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "SenderMessageRelationship [id=" + id + ", sendManagerId="
				+ sendManagerId + ", sendUserId=" + sendUserId
				+ ", sendStaffId=" + sendStaffId + ", deleteSign=" + deleteSign
				+ ", message=" + message + ", receiver=" + receiver + "]";
	}
}