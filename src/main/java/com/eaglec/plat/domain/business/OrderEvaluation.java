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

import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.utils.MD5;

/** 
 * 企业购买服务生成订单后对订单的评价或者提供服务商品的服务机构对订单的评价
 * @author xuwf
 * @since 2013-9-10
 *
 */
@Entity
@Table(name="orderevaluation")
public class OrderEvaluation implements Serializable {  
	private static final long serialVersionUID = -148986200950777537L;

	private Integer id;					//订单评价id,主键id
	
  	private String remark;				//备注

  	private String dtime;				//评价时间
  	
  	private User user;					//主账号评价人（企业用户或者机构用户主账户)	外键关联User表的主键id

  	private Staff staff;				//子账户评价人(企业用户子账户或者机构用户子账户)	外键关联Staff表的主键id
  	
  	private Service service;			//服务	外键关联Service的主键id,可能关联招标服务，这列后期未用到
   
  	private GoodsOrder goodsOrder;		//服务商品订单	外键关联GoodsOrder的主键id

  	private Integer satisfaction;		//满意度	5、很满意 4、满意 3、一般 2、不满意 1、差
  	
	private String oeid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

	private Long modifyTime = System.currentTimeMillis();

  	public OrderEvaluation() {
		super();
	}
  	//主账号评价构造方法
	public OrderEvaluation(String remark, String dtime, User user, GoodsOrder goodsOrder, Integer satisfaction) {
		super();
		this.remark = remark;
		this.dtime = dtime;
		this.user = user;
		this.goodsOrder = goodsOrder;
		this.satisfaction = satisfaction;
	}
	//子账号评价构造方法
	public OrderEvaluation(String remark, String dtime, Staff staff, GoodsOrder goodsOrder, Integer satisfaction) {
		super();
		this.remark = remark;
		this.dtime = dtime;
		this.staff = staff;
		this.goodsOrder = goodsOrder;
		this.satisfaction = satisfaction;
	}
	
	@Id
  	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
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
	
	@ManyToOne
	@JoinColumn(name="service_id")
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@ManyToOne
	@JoinColumn(name="order_id")
	public GoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}
	
	@Column(unique = true)
	public String getOeid() {
		return oeid;
	}
	public void setOeid(String oeid) {
		this.oeid = oeid;
	}
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Override
	public String toString() {
		return "OrderEvaluation [id=" + id + ", remark=" + remark + ", dtime="
				+ dtime + ", user=" + user + ", staff=" + staff + ", service="
				+ service + ", goodsOrder=" + goodsOrder + ", satisfaction="
				+ satisfaction + "]";
	}

}