package com.eaglec.plat.domain.auth;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 枢纽平台、窗口平台用户
 * @author cs
 * @description  
 */
@Entity
@Table(name="manager")
public class Manager implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;//主键
	private String username;//用户名
	private String password;//密码
	private Integer userStatus;//账号状态 ；1有效；2禁用；3删除
	private Integer userType;//用户类型
	private String remark;//备注
	private Integer flatId;//平台id
	private Role role;//所属角色
	private String registerTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(Integer id, String username, String password,Integer userStatus, Integer userType, String remark,Integer flatId, Role role, String registerTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userStatus = userStatus;
		this.userType = userType;
		this.remark = remark;
		this.flatId = flatId;
		this.role = role;
		this.registerTime = registerTime;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getFlatId() {
		return flatId;
	}

	public void setFlatId(Integer flatId) {
		this.flatId = flatId;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", username=" + username + ", password="
				+ password + ", userStatus=" + userStatus + ", userType="
				+ userType + ", remark=" + remark + ", flatId=" + flatId
				+ ", role=" + role + ", registerTime=" + registerTime + "]";
	}
}
