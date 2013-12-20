package com.eaglec.plat.domain.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="staffmenu")
public class StaffMenu {

	private Integer id;
	private String authCode;//链接名称
	private String text;//权限名称   对应着菜单名称
	private boolean leaf= true;
	private String remark;//备注
	private Date createTime = new Date();//创建时间
	private StaffMenu parent;//父id可找到上级菜单
	private List<StaffMenu> children = new ArrayList<StaffMenu>();
	private String idxtype;//对应前台   id和xtype   yhzhgl|passportpanel
	private boolean select=false;//透明参数 用于控制前台是否勾选
	private Integer menuType;
	
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

	@OneToMany(mappedBy="parent",cascade={CascadeType.ALL},fetch= FetchType.EAGER)
	//一对多双向的时候  把mappedBy设置多的那一方    一的类属性上面  
	public List<StaffMenu> getChildren() {
		return children;
	}

	public void setChildren(List<StaffMenu> children) {
		this.children = children;
	}
	
	@ManyToOne//多对一的时候要注明连接字段的名称   不注明就默认生成fuqin_id   
	@JoinColumn(name="pid")
	public StaffMenu getParent() {
		return parent;
	}

	public void setParent(StaffMenu parent) {
		this.parent = parent;
	}

	public String getIdxtype() {
		return idxtype;
	}

	public void setIdxtype(String idxtype) {
		this.idxtype = idxtype;
	}

	@Transient
	public boolean isSelect() {
		return select;
	}
	@Transient
	public void setSelect(boolean select) {
		this.select = select;
	}

	public Integer getMenuType() {
		return menuType;
	}
	
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
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
		StaffMenu other = (StaffMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StaffMenu [id=" + id + ", authCode=" + authCode + ", text="
				+ text + ", leaf=" + leaf + ", remark=" + remark
				+ ", createTime=" + createTime + ", parent=" + parent
				+ ", children=" + children + ", idxtype=" + idxtype
				+ ", select=" + select + ", menuType=" + menuType + "]";
	}

	
}
