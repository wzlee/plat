package com.eaglec.plat.domain.business;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.MD5;


/** 
 * 企业或者服务机构作为买家和卖家都可以针对服务进行申诉
 * @author xuwf
 * @since 2013-9-10
 * 
 */
@Entity
@Table(name="appeal")
public class Appeal implements Serializable {  
	private static final long serialVersionUID = 1154022963392071346L;

	private Integer id;				//申诉id,主键id(自增长)
	
	/**
	 * 订单
	 * 作为外键关联GoodsOrder的主键id;状态为“交易进行中”和“等待买/卖家评论”的订单可以进行申诉
	 * 
	 */
	private GoodsOrder goodsOrder;	
   
	private String attachment;		//附件	保存上传附件的路径
  
	private String remark;			//备注	长度不超过50个字符

	private Integer reason;			//申诉原因	下拉框显示，规则：1、买家违约； 2、卖家违约；3、其他	
	
	private Integer appealType;		//申诉类型	1.买家申诉，2、卖家申诉

	private User user;				//申诉人	作为外键关联User表主键id
	
	private Staff staff;			//申诉人	作为外键关联Staff表主键id
	
	private String appealTime;		//申诉时间	取系统当前时间
	
	/** 
	 * 申诉状态 ：1、交易结束  2、订单取消
     * 存储规则：当处理方式选择“关闭订单”，交易正常结束，默认给被投诉方满意（5分）评价。订单状态为：交易结束
     * 当处理方式选择“取消订单”，交易终止，不做服务交易评价，订单状态为：订单取消
     * 
     */
	private Integer appealStatus;
   
	private Manager manager;		//处理人	作为外键关联Manager表的主键id	
   
	private Integer processMode;	//处理方式	下拉框显示，规则：1、关闭订单； 2、取消订单
   
	private String processTime;		//处理时间
	
	private String handlerRemark;	//客服备注
	
	private String aid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

	private Long modifyTime = System.currentTimeMillis();
	
	public Appeal() {
	}
	
	/**
	 * 缺少申诉处理 4个字段的带参构造方法		主账号申诉
	 * @param goodsOrder
	 * @param attachment
	 * @param remark
	 * @param reason
	 * @param user
	 * @param appealTime
	 * @param appealStatus
	 */
	public Appeal(GoodsOrder goodsOrder, String attachment, String remark,
			Integer reason,Integer appealType, User user, String appealTime) {
		super();
		this.goodsOrder = goodsOrder;
		this.attachment = attachment;
		this.remark = remark;
		this.reason = reason;
		this.appealType = appealType;
		this.user = user;
		this.appealTime = appealTime;
	}
	
	/**
	 * 缺少申诉处理 4个字段的带参构造方法		子账号申诉
	 * @param goodsOrder
	 * @param attachment
	 * @param remark
	 * @param reason
	 * @param user
	 * @param appealTime
	 * @param appealStatus
	 */
	public Appeal(GoodsOrder goodsOrder, String attachment, String remark,
			Integer reason,Integer appealType, Staff staff, String appealTime) {
		super();
		this.goodsOrder = goodsOrder;
		this.attachment = attachment;
		this.remark = remark;
		this.reason = reason;
		this.appealType = appealType;
		this.staff = staff;
		this.appealTime = appealTime;
	}
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="order_id")
	public GoodsOrder getGoodsOrder() {
		return goodsOrder;
	}
	
	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public String getAttachment() {
		return attachment;
	}


	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public Integer getAppealType() {
		return appealType;
	}

	public void setAppealType(Integer appealType) {
		this.appealType = appealType;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getAppealTime() {
		return appealTime;
	}

	public void setAppealTime(String appealTime) {
		this.appealTime = appealTime;
	}

	public Integer getAppealStatus() {
		return appealStatus;
	}

	public void setAppealStatus(Integer appealStatus) {
		this.appealStatus = appealStatus;
	}
	
	@ManyToOne
	@JoinColumn(name="manager_id")
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Integer getProcessMode() {
		return processMode;
	}

	public void setProcessMode(Integer processMode) {
		this.processMode = processMode;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	public String getHandlerRemark() {
		return handlerRemark;
	}

	public void setHandlerRemark(String handlerRemark) {
		this.handlerRemark = handlerRemark;
	}
	
	@Column(unique = true)
	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Appeal [id=" + id + ", goodsOrder=" + goodsOrder
				+ ", attachment=" + attachment + ", remark=" + remark
				+ ", reason=" + reason + ", appealType=" + appealType
				+ ", user=" + user + ", staff=" + staff + ", appealTime="
				+ appealTime + ", appealStatus=" + appealStatus + ", manager="
				+ manager + ", processMode=" + processMode + ", processTime="
				+ processTime + ", handlerRemark=" + handlerRemark + "]";
	}
	
}