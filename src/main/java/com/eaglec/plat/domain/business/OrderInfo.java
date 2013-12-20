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
 * 服务商品订单的交易过程详细信息
 * @author xuwf
 * @since 2013-9-10
 * 
 */
@Entity
@Table(name="orderinfo")
public class OrderInfo implements Serializable {  
	private static final long serialVersionUID = 3917044787845346439L;

	private Integer id;				 //id	主键id(自增长)
	
	private GoodsOrder goodsOrder;	 //订单	作为外键关联GoodsOrder主键id		

	/** 
    * 订单状态：1、等待卖家确认 2、交易进行中 3、等待买家关闭 4、等待卖家关闭 5、申诉处理中 6、交易结束 7、订单取消
    */
	private Integer orderStatus;		

	private String remark;			 //备注	长度:不超过50个字符

   	private User processor;			 //处理人	作为外键关联User表的主键id
   	
   	private Staff staff;			 //处理人	作为外键关联Staff表的主键id
   	
   	private Manager manager;		 //处理人	作为外键关联Manager表的主键id
   	
   	private String processTime;		 //处理时间	系统当前时间
 	
   	/**
   	 * 订单动作：1、买家提交订单	2、卖家确认订单	3、进入交易	4、买家关闭交易	5、卖家关闭交易	
   	 * 	6、平台关闭交易	7、卖家取消订单	8、平台取消订单	9、买家申诉	10、卖家申诉 
   	 *  11、申诉处理，关闭订单  12、申诉处理，取消订单
   	 */
   	private Integer action;			 //动作(对订单操作时产生的动作)
   	
	private String oiid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

	private Long modifyTime = System.currentTimeMillis();
   	
   	public OrderInfo() {
	}
   	
   	//用于企业用户处理订单的构造方法
	public OrderInfo(GoodsOrder goodsOrder, Integer orderStatus, String remark,
			User processor, String processTime, Integer action) {
		super();
		this.goodsOrder = goodsOrder;
		this.orderStatus = orderStatus;
		this.remark = remark;
		this.processor = processor;
		this.processTime = processTime;
		this.action = action;
	}
	
	//用于企业子账号用户处理订单的构造方法
	public OrderInfo(GoodsOrder goodsOrder, Integer orderStatus, String remark,
			Staff staff, String processTime, Integer action) {
		super();
		this.goodsOrder = goodsOrder;
		this.orderStatus = orderStatus;
		this.remark = remark;
		this.staff = staff;
		this.processTime = processTime;
		this.action = action;
	}
	
	//用于平台人员处理订单的构造方法
	public OrderInfo(GoodsOrder goodsOrder, Integer orderStatus, String remark,
			Manager manager, String processTime, Integer action) {
		super();
		this.goodsOrder = goodsOrder;
		this.orderStatus = orderStatus;
		this.remark = remark;
		this.manager = manager;
		this.processTime = processTime;
		this.action = action;
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

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@ManyToOne
	@JoinColumn(name="processor_id")
	public User getProcessor() {
		return processor;
	}

	public void setProcessor(User processor) {
		this.processor = processor;
	}
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@ManyToOne
	@JoinColumn(name="manager_id")
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}
	
	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	@Column(unique = true)
	public String getOiid() {
		return oiid;
	}

	public void setOiid(String oiid) {
		this.oiid = oiid;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "OrderInfo [id=" + id + ", goodsOrder=" + goodsOrder
				+ ", orderStatus=" + orderStatus + ", remark=" + remark
				+ ", processor=" + processor + ", staff=" + staff
				+ ", manager=" + manager + ", processTime=" + processTime
				+ ", action=" + action + "]";
	}

}