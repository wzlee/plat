package com.eaglec.plat.view;

/**
 * 服务查询参数封装<br>
 * 用于服务机构下的服务列表查询
 * 
 * @author Xiadi
 * @since 2013-9-18
 */
public class ServiceParam {
	private Integer eid;			//机构ID not null
	private Integer start = 0;		//start not null
	private Integer limit = 15;		//limit not null
	private Integer beignPrice;		//服务类别Id
	private Integer endPrice;		//起始价格
	private Integer categoryId;		//结束价格
	private String serviceName;		//服务名称
	private String orderName;		//排序属性名
	private String orderType;		//排序类型（asc or desc）
	
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getBeignPrice() {
		return beignPrice;
	}
	public void setBeignPrice(Integer beignPrice) {
		this.beignPrice = beignPrice;
	}
	public Integer getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(Integer endPrice) {
		this.endPrice = endPrice;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	@Override
	public String toString() {
		return "ServiceParam [eid=" + eid + ", start=" + start + ", limit="
				+ limit + ", beignPrice=" + beignPrice + ", endPrice="
				+ endPrice + ", categoryId=" + categoryId + ", serviceName="
				+ serviceName + ", orderName=" + orderName + ", orderType="
				+ orderType + "]";
	}
	
}
