package com.eaglec.plat.domain.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * 对订单操作日志
 * @author xuwf
 * @since  2013-9-10
 * 
 */
@Entity
@Table(name="orderoperatelog")
public class OrderOperateLog implements Serializable {  
	private static final long serialVersionUID = -6001816535805330580L;
	
	private Integer id;			//主键id

	private String orderNumber;	//订单号	订单号,取GoodsOrder的orderNumber

	private String operateUser;	//操作人 	取该笔订单的操作人（服务的申请人、服务提供人、平台运营人员）
   
	/** 
	 * 订单状态：1、等待卖家确认 2、交易进行中 3、等待买家关闭 4、等待卖家关闭 5、申诉处理中 6、交易结束 7、订单取消
	 * 此处存储value，无需再去通过key去查
	 * 
	 */
	private String orderStatus;
  
	private String operateTime; //操作时间

	public OrderOperateLog() {
	}

	public OrderOperateLog(String orderNumber, String operateUser,
			String orderStatus, String operateTime) {
		super();
		this.orderNumber = orderNumber;
		this.operateUser = operateUser;
		this.orderStatus = orderStatus;
		this.operateTime = operateTime;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Override
	public String toString() {
		return "OrderOperateLog [id=" + id + ", orderNumber=" + orderNumber
				+ ", operateUser=" + operateUser + ", orderStatus="
				+ orderStatus + ", operateTime=" + operateTime + "]";
	}
	
}