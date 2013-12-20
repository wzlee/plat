package com.eaglec.plat.view;

import java.io.Serializable;

/**
 * 服务收藏视图类
 * @author liuliping
 * @since 2013-11-04
 * */
public class MyFavoritesView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2191274925299931496L;

	// 主键id
	private Integer id;
	
	// 服务类别名称
	private String categoryName; 
	
	// 收藏时间
	private String time;
	
	// 服务id
	private Integer serviceId;
	
	// 服务名称
	private String serviceName;
	
	// 服务价格
	private Integer price;
	
	// 服务描述 
	private String serviceProcedure;
	
	// 服务图片
	private String picture;
	

	public MyFavoritesView() {
		super();
	}

	public MyFavoritesView(Integer id, String categoryName, String time,
			Integer serviceId, String serviceName, Integer price,
			String serviceProcedure, String picture) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.time = time;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.price = price;
		this.serviceProcedure = serviceProcedure;
		this.picture = picture;
	}




	public String getCategoryName() {
		return categoryName;
	}

	public Integer getId() {
		return id;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getTime() {
		return time;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getServiceProcedure() {
		return serviceProcedure;
	}

	public void setServiceProcedure(String serviceProcedure) {
		this.serviceProcedure = serviceProcedure;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
