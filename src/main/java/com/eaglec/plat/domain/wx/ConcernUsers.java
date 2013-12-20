package com.eaglec.plat.domain.wx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @date: 2013-12-12
 * @author lwch
 * @description：从微信端关注了平台的用户
 */
@Entity
@Table(name = "concern_users")
public class ConcernUsers implements Serializable{
	
	private static final long serialVersionUID = 3655157235142772839L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String username;	//平台用户帐号
	
	private Integer enterprise_id;	//企业id
	
	private String enterprise_name;//企业名称
	
	private String wxUserToken;	//加密过后的微信帐号
	
	private String wx_user_name;	//微信帐号昵称
	
	private String wx_user_headimgurl;	//微信用户头像
	
	private String wx_user_sex; 		//微信用户性别
	
	private String wx_user_language; 	//微信用户使用的语言
	
	private String wx_user_country;		//微信用户所属的国家
	
	private String wx_user_province;	//微信用户所在的省份
	
	private String wx_user_city;		//微信用户所在的城市
	
	private String wx_scan_token;	//微信用户扫描二维码后生成的token号
	
	private String subscribe_time;	//微信用户扫描二维码时间
	
	private Boolean concern_status;	//微信用户关注的状态

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

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public String getWxUserToken() {
		return wxUserToken;
	}

	public void setWxUserToken(String wxUserToken) {
		this.wxUserToken = wxUserToken;
	}

	public String getWx_scan_token() {
		return wx_scan_token;
	}

	public void setWx_scan_token(String wx_scan_token) {
		this.wx_scan_token = wx_scan_token;
	}

	public Boolean getConcern_status() {
		return concern_status;
	}

	public void setConcern_status(Boolean concern_status) {
		this.concern_status = concern_status;
	}

	public String getEnterprise_name() {
		return enterprise_name;
	}

	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}

	public String getWx_user_name() {
		return wx_user_name;
	}

	public void setWx_user_name(String wx_user_name) {
		this.wx_user_name = wx_user_name;
	}

	public String getWx_user_headimgurl() {
		return wx_user_headimgurl;
	}

	public void setWx_user_headimgurl(String wx_user_headimgurl) {
		this.wx_user_headimgurl = wx_user_headimgurl;
	}

	public String getWx_user_sex() {
		return wx_user_sex;
	}

	public void setWx_user_sex(String wx_user_sex) {
		this.wx_user_sex = wx_user_sex;
	}

	public String getWx_user_language() {
		return wx_user_language;
	}

	public void setWx_user_language(String wx_user_language) {
		this.wx_user_language = wx_user_language;
	}

	public String getWx_user_country() {
		return wx_user_country;
	}

	public void setWx_user_country(String wx_user_country) {
		this.wx_user_country = wx_user_country;
	}

	public String getWx_user_province() {
		return wx_user_province;
	}

	public void setWx_user_province(String wx_user_province) {
		this.wx_user_province = wx_user_province;
	}

	public String getWx_user_city() {
		return wx_user_city;
	}

	public void setWx_user_city(String wx_user_city) {
		this.wx_user_city = wx_user_city;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	@Override
	public String toString() {
		return "ConcernUsers [id=" + id + ", username=" + username
				+ ", enterprise_id=" + enterprise_id + ", enterprise_name="
				+ enterprise_name + ", wxUserToken=" + wxUserToken
				+ ", wx_user_name=" + wx_user_name + ", wx_user_headimgurl="
				+ wx_user_headimgurl + ", wx_user_sex=" + wx_user_sex
				+ ", wx_user_language=" + wx_user_language
				+ ", wx_user_country=" + wx_user_country
				+ ", wx_user_province=" + wx_user_province + ", wx_user_city="
				+ wx_user_city + ", wx_scan_token=" + wx_scan_token
				+ ", subscribe_time=" + subscribe_time + ", concern_status="
				+ concern_status + "]";
	}
}
