package com.eaglec.plat.view;

import java.io.Serializable;

/**
 * 订单和招标数据封装
 * @author xuwf
 * @since 2013-10-10
 *
 */
public class OrderAndBiddingView implements Serializable {

	private static final long serialVersionUID = 3374210484838127406L;
	private int boId;					//订单Id
	private String boNum;				//订单编号
	private int serviceId;				//服务商品Id
	private String serviceName;			//服务名称
	private String categoryName;		//类别名称
	private Integer price;				//价格
	private String buyer;				//买家
	private String seller;				//卖家
	private Integer status;				//状态
	private String createTime;			//下单时间
	private Integer orderSource; 		//订单来源
	public OrderAndBiddingView() {
	}

	public OrderAndBiddingView(int boId,String boNum,int serviceId, String serviceName,
			String categoryName, Integer price, String buyer, String seller,
			Integer status, String createTime,Integer orderSource) {
		super();
		this.boId = boId;
		this.boNum = boNum;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.categoryName = categoryName;
		this.price = price;
		this.buyer = buyer;
		this.seller = seller;
		this.status = status;
		this.createTime = createTime;
		this.orderSource = orderSource;
	}

	public int getBoId() {
		return boId;
	}

	public void setBoId(int boId) {
		this.boId = boId;
	}

	public String getBoNum() {
		return boNum;
	}
	public void setBoNum(String boNum) {
		this.boNum = boNum;
	}
	
	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	@Override
	public String toString() {
		return "OrderAndBiddingView [boId=" + boId + ", boNum=" + boNum
				+ ", serviceId=" + serviceId + ", serviceName=" + serviceName
				+ ", categoryName=" + categoryName + ", price=" + price
				+ ", buyer=" + buyer + ", seller=" + seller + ", status="
				+ status + ", createTime=" + createTime + ", orderSource="
				+ orderSource + "]";
	}

}
