package com.eaglec.plat.domain.auth;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色实体类
 * @author cs
 * @description  角色
 */
@Entity
@Table(name="role")
public class Role implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;//主键
	private String rolename;//角色名字
	private String roledesc;//角色描述
	private Date createTime;//创建时间
//	private List<Menu> menu;//拥有的权限列表
	private String menuIds;

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

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", roledesc="
				+ roledesc + ", createTime=" + createTime + ", menuIds="
				+ menuIds + "]";
	}

}
