package com.eaglec.plat.domain.wx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wx_change_users")
public class UserChange implements Serializable{
	private static final long serialVersionUID = 1341388609457408800L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String username;	//平台用户帐号
	
	private String wxUserToken;	//加密过后的微信帐号 
	
	private Integer enterprise_id;	//企业id
	
	private String enterprise_name;//企业名称
	
	private String change_time;		//变更时间
	
	private Integer change_status;	//变更状态（0：已关注，1：已绑定，2：已解绑，3：取消关注）

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWxUserToken() {
		return wxUserToken;
	}

	public void setWxUserToken(String wxUserToken) {
		this.wxUserToken = wxUserToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public String getEnterprise_name() {
		return enterprise_name;
	}

	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}

	public String getChange_time() {
		return change_time;
	}

	public void setChange_time(String change_time) {
		this.change_time = change_time;
	}

	public Integer getChange_status() {
		return change_status;
	}

	public void setChange_status(Integer change_status) {
		this.change_status = change_status;
	}

	@Override
	public String toString() {
		return "UserChange [id=" + id + ", wxUserToken=" + wxUserToken
				+ ", username=" + username + ", enterprise_id=" + enterprise_id
				+ ", enterprise_name=" + enterprise_name + ", change_time="
				+ change_time + ", change_status=" + change_status + "]";
	}
}
