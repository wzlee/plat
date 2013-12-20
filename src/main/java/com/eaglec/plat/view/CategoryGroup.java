package com.eaglec.plat.view;

import java.io.Serializable;

/**
 * 用户服务机构显示服务分组
 * 
 * @author huyj
 * 
 */
public class CategoryGroup implements Serializable {

	private static final long serialVersionUID = -4199944022558796718L;

	private String categoryName; // 服务所属类别名称

	private Integer id; // 服务所属类别ID

	private Long serviceNum; // 该类别中服务的总数

	public CategoryGroup() {
	}

	public CategoryGroup(String categoryName, Integer id, Long serviceNum) {
		super();
		this.categoryName = categoryName;
		this.id = id;
		this.serviceNum = serviceNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Integer getId() {
		return id;
	}

	public Long getServiceNum() {
		return serviceNum;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setServiceNum(Long serviceNum) {
		this.serviceNum = serviceNum;
	}

	@Override
	public String toString() {
		return "CategoryGroup [categoryName=" + categoryName + ", id=" + id
				+ ", serviceNum=" + serviceNum + "]";
	}

}
