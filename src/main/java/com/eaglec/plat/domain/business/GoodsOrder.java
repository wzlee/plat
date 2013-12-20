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
 * 针对服务商品ServiceGoods申请的每笔记录
 * @author xuwf
 *@since 2013-9-10
 *
 */
@Entity
@Table(name="goodsorder")
public class GoodsOrder implements Serializable {

	private static final long serialVersionUID = -2709141134761407201L;

	private int id;							//订单ID,主键自增长
	
	private String orderNumber;				//订单号	规则：S+YYYYMMDD+5位随机数字;例如：S2010101718830
	
	/** 
	 * 订单状态：1、等待卖家确认 6、交易进行中 7、等待买家关闭 8、等待卖家关闭 9、申诉处理中 10、交易结束 11、订单取消
	 * 
	 */
	private Integer orderStatus;	

	private Integer transactionPrice;	 	//服务商品成交价 	 必填，不小于0的数字
		
	private String userName;			 	//申请人姓名
	
	private String phone;				 	//联系电话
	
	private String email;				 	//邮箱

	private String remark;				 	//备注	长度：500字符以内

	private String createTime;			 	//创建时间
	
	private User buyer;					 	//买家	作为外键关联User表主键id
	
	private Staff staff;					//买家	作为外键关联Staff表主键id
	
	private Integer purchaser_id;		 	//买方企业id
	
	private Integer  seller_id;			 	//卖家	关联的是企业id

	private String serviceName;				//服务名称	保存招标服务、商城服务的服务名称	
	
	private Service service;			 	//服务	关联服务(首页申请服务)
	
	private BiddingService biddingService;	//招标服务	关联BiddingService
	
	/**
	 * 订单来源： 1 普通服务 2招标服务
	 */
	private Integer orderSource;		//订单来源
	
	private String oid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

	private Long modifyTime = System.currentTimeMillis();
	
	public GoodsOrder() {
	}
	
	//主账号-招标服务
	public GoodsOrder(String orderNumber, Integer orderStatus,
			Integer transactionPrice, String userName, String phone,
			String email, String remark, String createTime, User buyer,Integer purchaser_id,
			Integer seller_id,String serviceName,BiddingService biddingService, Integer orderSource) {
		super();
		this.orderNumber = orderNumber;
		this.orderStatus = orderStatus;
		this.transactionPrice = transactionPrice;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.remark = remark;
		this.createTime = createTime;
		this.buyer = buyer;
		this.purchaser_id = purchaser_id;	
		this.seller_id =seller_id;
		this.serviceName = serviceName;
		this.biddingService = biddingService;
		this.orderSource = orderSource;
	}

	// 主账号-普通服务
	public GoodsOrder(String orderNumber, Integer orderStatus,
			Integer transactionPrice, String userName, String phone,
			String email, String remark, String createTime, User buyer,
			Integer purchaser_id, Integer seller_id, String serviceName,
			Service service, Integer orderSource) {
		super();
		this.orderNumber = orderNumber;
		this.orderStatus = orderStatus;
		this.transactionPrice = transactionPrice;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.remark = remark;
		this.createTime = createTime;
		this.buyer = buyer;
		this.purchaser_id = purchaser_id;
		this.seller_id = seller_id;
		this.serviceName = serviceName;
		this.service = service;
		this.orderSource = orderSource;
	}

	// 子账号-招标服务
	public GoodsOrder(String orderNumber, Integer orderStatus,
			Integer transactionPrice, String userName, String phone,
			String email, String remark, String createTime, Staff staff,
			Integer purchaser_id, Integer seller_id, String serviceName,
			BiddingService biddingService, Integer orderSource) {
		super();
		this.orderNumber = orderNumber;
		this.orderStatus = orderStatus;
		this.transactionPrice = transactionPrice;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.remark = remark;
		this.createTime = createTime;
		this.staff = staff;
		this.purchaser_id = purchaser_id;
		this.seller_id = seller_id;
		this.serviceName = serviceName;
		this.biddingService = biddingService;
		this.orderSource = orderSource;
	}

	// 子账号-普通服务
	public GoodsOrder(String orderNumber, Integer orderStatus,
			Integer transactionPrice, String userName, String phone,
			String email, String remark, String createTime, Staff staff,
			Integer purchaser_id, Integer seller_id, String serviceName,
			Service service, Integer orderSource) {
		super();
		this.orderNumber = orderNumber;
		this.orderStatus = orderStatus;
		this.transactionPrice = transactionPrice;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.remark = remark;
		this.createTime = createTime;
		this.staff = staff;
		this.purchaser_id = purchaser_id;
		this.seller_id = seller_id;
		this.serviceName = serviceName;
		this.service = service;
		this.orderSource = orderSource;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="buyer_id")
	public User getBuyer() {
		return buyer;
	}
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
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
	@JoinColumn(name="biddingService_id")
	public BiddingService getBiddingService() {
		return biddingService;
	}

	public void setBiddingService(BiddingService biddingService) {
		this.biddingService = biddingService;
	}

	public Integer getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(Integer transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	public Integer getPurchaser_id() {
		return purchaser_id;
	}

	public void setPurchaser_id(Integer purchaser_id) {
		this.purchaser_id = purchaser_id;
	}

	public Integer getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@Column(unique = true)
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "GoodsOrder [id=" + id + ", orderNumber=" + orderNumber
				+ ", orderStatus=" + orderStatus + ", transactionPrice="
				+ transactionPrice + ", userName=" + userName + ", phone="
				+ phone + ", email=" + email + ", remark=" + remark
				+ ", createTime=" + createTime + ", buyer=" + buyer
				+ ", staff=" + staff + ", purchaser_id=" + purchaser_id
				+ ", seller_id=" + seller_id + ", serviceName=" + serviceName
				+ ", service=" + service + ", biddingService=" + biddingService
				+ ", orderSource=" + orderSource + "]";
	}

}
