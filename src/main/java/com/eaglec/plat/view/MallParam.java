package com.eaglec.plat.view;

public class MallParam {

	private Integer mallId; // 商城类别ID not null
	private int start = 0; // start not null
	private int limit = 15; // limit not null
	private Integer beginPrice; // 服务类别Id
	private Integer endPrice; // 起始价格
	private String serviceName; // 服务名称
	private String orderType; // 排序方式
	private String orderName; // 排序属性
	private Long total; // 总数

	public Integer getMallId() {
		return mallId;
	}

	public int getStart() {
		return start;
	}

	public int getLimit() {
		return limit;
	}

	public Integer getBeginPrice() {
		return beginPrice;
	}

	public Integer getEndPrice() {
		return endPrice;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getOrderType() {
		return orderType;
	}

	public String getOrderName() {
		return orderName;
	}

	public Long getTotal() {
		return total;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setBeginPrice(Integer beginPrice) {
		this.beginPrice = beginPrice;
	}

	public void setEndPrice(Integer endPrice) {
		this.endPrice = endPrice;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
