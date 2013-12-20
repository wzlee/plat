package com.eaglec.plat.domain.auth;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 菜单权限实体类
 * @author chens
 * @description  菜单
 */
@Entity
@Table(name="menu")
public class Menu implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String authCode;//权限代码
	private String text;//权限名称   对应着菜单名称
	private boolean leaf= true;
	private String remark;//备注
	private Date createTime;//创建时间
	private Integer pid;//父id可找到上级菜单
	private List<Menu> children;
	private String idxtype;//对应前台   id和xtype   yhzhgl|passportpanel
	private boolean select=false;//透明参数 用于控制前台是否勾选

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Transient
	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public String getIdxtype() {
		return idxtype;
	}

	public void setIdxtype(String idxtype) {
		this.idxtype = idxtype;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", authCode=" + authCode + ", text=" + text
				+ ", leaf=" + leaf + ", remark=" + remark + ", createTime="
				+ createTime + ", pid=" + pid + ", idxtype=" + idxtype + "]";
	}
	@Transient
	public boolean isSelect() {
		return select;
	}
	@Transient
	public void setSelect(boolean select) {
		this.select = select;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
