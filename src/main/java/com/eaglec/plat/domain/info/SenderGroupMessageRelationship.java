package com.eaglec.plat.domain.info;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sendergroupmessagerelationship")
public class SenderGroupMessageRelationship implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;//主键ID
   
	private Integer sendManagerId;//管理员id
   
	@OneToOne
	private Message message;

	private Integer deleteSign = 0;//删除标记 0未删除；1删除  默认为0
   
	private Integer receiver;//接收人  01全部用户 02企业用户 03机构用户 04个人用户

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

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getDeleteSign() {
		return deleteSign;
	}

	public void setDeleteSign(Integer deleteSign) {
		this.deleteSign = deleteSign;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "SenderGroupMessageRelationship [id=" + id + ", sendManagerId="
				+ sendManagerId + ", message=" + message + ", deleteSign="
				+ deleteSign + ", receiver=" + receiver + "]";
	}
}
