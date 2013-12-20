package com.eaglec.plat.domain.service;

/***********************************************************************
 * Module:  ServiceOrgRelation.java
 * Author:  Administrator
 * Purpose: Defines the Class ServiceOrgRelation
 ***********************************************************************/

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 描述服务机构与其所包含的服务的对应关系 */
@Entity
@Table(name="serviceorgrelation")
public class ServiceOrgRelation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id; // 主键id
	/** 逻辑主键,同时作为外键关联Enterprise的id属性 */
	private Integer enterpriseId;// 服务机构id
	/** 逻辑主键，同时作为外键关联Catergory的id属性 */
	private Integer categoryId;// 服务分类id
	private String categoryName;// 服务分类Name

	private Integer categoryPid;// 父类id
	private String categoryPname;// 父类Name

	private String remark; // 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryPid() {
		return categoryPid;
	}

	public void setCategoryPid(Integer categoryPid) {
		this.categoryPid = categoryPid;
	}

	public String getCategoryPname() {
		return categoryPname;
	}

	public void setCategoryPname(String categoryPname) {
		this.categoryPname = categoryPname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ServiceOrgRelation [id=" + id + ", enterpriseId="
				+ enterpriseId + ", categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", categoryPid="
				+ categoryPid + ", categoryPname=" + categoryPname
				+ ", remark=" + remark + "]";
	}

}