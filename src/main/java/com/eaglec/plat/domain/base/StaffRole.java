package com.eaglec.plat.domain.base;

 import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="staffrole")
public class StaffRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//主键
	private String rolename;//角色名字
	private String roledesc;//角色描述
	private Date createTime = new Date();//创建时间
//	private List<Menu> menu;//拥有的权限列表
	private String menuIds;
	private Integer roleType;//1：企业用户菜单，2：机构用户菜单
	private boolean apply = false ;//是否可以申请服务
	
	public StaffRole(){
		
	}
	
	public StaffRole(Integer id,String rolename){
		this.id = id;
		this.rolename = rolename;
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "StaffRole [id=" + id + ", rolename=" + rolename + ", roledesc="
				+ roledesc + ", createTime=" + createTime + ", menuIds="
				+ menuIds + ", roleType=" + roleType + "]";
	}

	public boolean isApply() {
		return apply;
	}

	public void setApply(boolean apply) {
		this.apply = apply;
	}
}
