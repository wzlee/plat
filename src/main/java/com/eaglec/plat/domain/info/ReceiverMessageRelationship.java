package com.eaglec.plat.domain.info;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="receivermessagerelationship")
public class  ReceiverMessageRelationship implements Serializable {  
	private static final long serialVersionUID = 1L;

	private int id;//主键ID
	   
	private Integer receiverUserId;//接收人(企业用户或者机构用户或个人用户) 外键关联User类的id
	   
	private Integer receiverStaffId;//接收人(企业或者机构子帐号) 外键关联Staff类的id
	   
	private Integer deleteSign = 0;//删除标记 0未删除；1删除  默认为0
	   
	private Integer readSign = 0;//   未读标记                  0未读；1已读  默认为0
	  
	private Message message;
	
	private String sender;		//点对点发送人名字

	public ReceiverMessageRelationship() {
	}
	
	public ReceiverMessageRelationship(Message message, String sender) {
		this.message = message;
		this.sender = sender;
	}


	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getReceiverUserId() {
		return receiverUserId;
	}

	public void setReceiverUserId(Integer sendUserId) {
		this.receiverUserId = sendUserId;
	}

	public Integer getReceiverStaffId() {
		return receiverStaffId;
	}

	public void setReceiverStaffId(Integer sendStaffId) {
		this.receiverStaffId = sendStaffId;
	}

	public Integer getDeleteSign() {
		return deleteSign;
	}

	public void setDeleteSign(Integer deleteSign) {
		this.deleteSign = deleteSign;
	}

	public Integer getReadSign() {
		return readSign;
	}

	public void setReadSign(Integer readSign) {
		this.readSign = readSign;
	}

	@OneToOne
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		return "ReceiverMessageRelationship [id=" + id + ", receiverUserId="
				+ receiverUserId + ", receiverStaffId=" + receiverStaffId
				+ ", deleteSign=" + deleteSign + ", readSign=" + readSign
				+ ", message=" + message + ", sender=" + sender + "]";
	}

}