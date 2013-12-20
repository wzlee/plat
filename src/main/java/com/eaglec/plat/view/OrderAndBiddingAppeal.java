package com.eaglec.plat.view;

import java.io.Serializable;

import com.eaglec.plat.domain.business.BiddingService;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.domain.business.ResponseBidding;

/**
 * 订单和招标申诉信息封装
 * @author xuwf
 * @since 2013-10-14
 *
 */
public class OrderAndBiddingAppeal implements Serializable {

	private static final long serialVersionUID = 3374210484838127406L;
	private int id;						//订单Id
	private String oaNum;				//订单编号
	private int serviceId;				//服务商品Id
	private String serviceName;			//服务名称
	private String categoryName;		//类别名称
	private String price;				//价格
	private Integer tranPrice;			//交易价格
	private String buyer;				//买家
	private String seller;				//卖家
	private Integer appealType;			//申诉类型
	private String handlerRemark;		//客服备注
	private String processorTime;		//处理时间
	private String goodsOrderTime;		//下单时间
	private Integer reason;				//申诉原因
	private GoodsOrder goodsOrder;			//订单
	public OrderAndBiddingAppeal() {
		super();
	}

	public OrderAndBiddingAppeal(int id, String oaNum, int serviceId,
			String serviceName, String categoryName, String price,
			Integer tranPrice, String buyer, String seller, Integer appealType,
			String handlerRemark, String processorTime,String goodsOrderTime,Integer reason) {
		super();
		this.id = id;
		this.oaNum = oaNum;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.categoryName = categoryName;
		this.price = price;
		this.tranPrice = tranPrice;
		this.buyer = buyer;
		this.seller = seller;
		this.appealType = appealType;
		this.handlerRemark = handlerRemark;
		this.processorTime = processorTime;
		this.goodsOrderTime = goodsOrderTime;
		this.reason = reason;
	}

	public OrderAndBiddingAppeal(int id, String oaNum, int serviceId,
			String serviceName, String categoryName, String price,
			Integer tranPrice, String buyer, String seller, Integer appealType,
			String handlerRemark, String processorTime, String goodsOrderTime,
			Integer reason, GoodsOrder goodsOrder) {
		super();
		this.id = id;
		this.oaNum = oaNum;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.categoryName = categoryName;
		this.price = price;
		this.tranPrice = tranPrice;
		this.buyer = buyer;
		this.seller = seller;
		this.appealType = appealType;
		this.handlerRemark = handlerRemark;
		this.processorTime = processorTime;
		this.goodsOrderTime = goodsOrderTime;
		this.reason = reason;
		this.goodsOrder = goodsOrder;
	}

	
	public GoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOaNum() {
		return oaNum;
	}
	public void setOaNum(String oaNum) {
		this.oaNum = oaNum;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getTranPrice() {
		return tranPrice;
	}
	public void setTranPrice(Integer tranPrice) {
		this.tranPrice = tranPrice;
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
	public Integer getAppealType() {
		return appealType;
	}
	public void setAppealType(Integer appealType) {
		this.appealType = appealType;
	}
	public String getHandlerRemark() {
		return handlerRemark;
	}
	public void setHandlerRemark(String handlerRemark) {
		this.handlerRemark = handlerRemark;
	}
	public String getProcessorTime() {
		return processorTime;
	}
	public void setProcessorTime(String processorTime) {
		this.processorTime = processorTime;
	}
	
	public String getGoodsOrderTime() {
		return goodsOrderTime;
	}

	public void setGoodsOrderTime(String goodsOrderTime) {
		this.goodsOrderTime = goodsOrderTime;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "OrderAndBiddingAppeal [id=" + id + ", oaNum=" + oaNum
				+ ", serviceId=" + serviceId + ", serviceName=" + serviceName
				+ ", categoryName=" + categoryName + ", price=" + price
				+ ", tranPrice=" + tranPrice + ", buyer=" + buyer + ", seller="
				+ seller + ", appealType=" + appealType + ", handlerRemark="
				+ handlerRemark + ", processorTime=" + processorTime
				+ ", goodsOrderTime=" + goodsOrderTime + ", reason=" + reason
				+ ", goodsOrder=" + goodsOrder + "]";
	}

}
