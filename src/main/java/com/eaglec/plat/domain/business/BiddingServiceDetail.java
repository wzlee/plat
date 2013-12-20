package com.eaglec.plat.domain.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;

/** 
 * 招标服务订单的交易过程详细信息
 * @author xuwf
 * @since 2013-9-30
 * 
 */
@Entity
@Table(name="biddingservicedetail")
public class BiddingServiceDetail implements Serializable {  
	private static final long serialVersionUID = 3917044787845346439L;

	private Integer id;				 //id	主键id(自增长)
	
	private BiddingService biddingService;	 //招标服务单(订单)	作为外键关联BiddingService主键id		
	
	/** 
    * 招标服务订单状态： 
    * 1、待发布 2、待审核 3、平台退回 4、招标中 5、应标中 6、交易进行中 7、等待买家关闭 8、等待卖家关闭
	 * 9、申诉处理中 10、交易结束 11、招标取消   
    */
	private Integer biddingStatus;		

	private String remark;			 //备注	长度:不超过50个字符

   	private User user;			 	//操作人	作为外键关联User表的主键id;应标方和招标方操作人
   	
   	private Staff staff;			//操作人	作为外键关联Staff表的主键id
   	
   	private Manager manager;		//操作人	作为外键关联Manager表的主键id；运营支撑系统运营管理人员打回标单申请或者通过标单申请 。
   	
   	private String processTime;		 //处理时间	系统当前时间
   	
   	/**
   	 * 招标服务订单动作：1、保存招标信息 	2、发布招标信息 3、取消招标 4、 选择应标服务  5、买家关闭交易 6、买家申诉 7、申请应标 8、卖家申诉
   	 * 9、卖家关闭交易   10、平台审核通过  11、平台审核退回   12、申诉处理，关闭交易    13、申诉处理，取消订单  14、平台关闭交易 15、平台取消招标	
   	 * 
   	 */
   	private Integer action;

   	public BiddingServiceDetail() {
	}
   	//主账号操作
	public BiddingServiceDetail(BiddingService biddingService,
			Integer biddingStatus, String remark, User user,
			String processTime, Integer action) {
		super();
		this.biddingService = biddingService;
		this.biddingStatus = biddingStatus;
		this.remark = remark;
		this.user = user;
		this.processTime = processTime;
		this.action = action;
	}
	//子账号操作
	public BiddingServiceDetail(BiddingService biddingService,
			Integer biddingStatus, String remark, Staff staff,
			String processTime, Integer action) {
		super();
		this.biddingService = biddingService;
		this.biddingStatus = biddingStatus;
		this.remark = remark;
		this.staff = staff;
		this.processTime = processTime;
		this.action = action;
	}


	public BiddingServiceDetail( BiddingService biddingService,
			Integer biddingStatus, String remark, Manager manager,
			String processTime, Integer action) {
		super();
		this.biddingService = biddingService;
		this.biddingStatus = biddingStatus;
		this.remark = remark;
		this.manager = manager;
		this.processTime = processTime;
		this.action = action;
	}
	
	public BiddingServiceDetail( BiddingService biddingService,
			Integer biddingStatus, User user,
			String processTime, Integer action) {
		super();
		this.biddingService = biddingService;
		this.biddingStatus = biddingStatus;
		this.user = user;
		this.processTime = processTime;
		this.action = action;
	}
	
	public BiddingServiceDetail( BiddingService biddingService,
			Integer biddingStatus, Staff staff,
			String processTime, Integer action) {
		super();
		this.biddingService = biddingService;
		this.biddingStatus = biddingStatus;
		this.staff = staff;
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
	public BiddingService getBiddingService() {
		return biddingService;
	}

	public void setBiddingService(BiddingService biddingService) {
		this.biddingService = biddingService;
	}

	public Integer getBiddingStatus() {
		return biddingStatus;
	}

	public void setBiddingStatus(Integer biddingStatus) {
		this.biddingStatus = biddingStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@ManyToOne
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

	@Override
	public String toString() {
		return "BiddingServiceDetail [id=" + id + ", biddingService="
				+ biddingService + ", biddingStatus=" + biddingStatus
				+ ", remark=" + remark + ", user=" + user + ", staff=" + staff
				+ ", manager=" + manager + ", processTime=" + processTime
				+ ", action=" + action + "]";
	}
 	
}