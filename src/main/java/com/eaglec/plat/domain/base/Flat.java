package com.eaglec.plat.domain.base;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述枢纽平台或者窗口平台的基本情况。枢纽平台用于统筹全国服务资源为中小企业提供服务。窗口平台的基本情况，用于使中小企业了解窗口服务平台，找到合适的服务资源
 * 
 * @author liuliping
 * @since 2013-10-12
 */
@Entity
@Table(name="flat")
public class Flat implements Serializable {
	private static final long serialVersionUID = 4688116315494099775L;

	private Integer id;    // 平台ID
	// 平台名称
	/**
	 * 长度100个字符
	 */
	private String flatName;
	
	// 平台类型
	/**
	 * 长度为1 ;1、综合服务平台，2、产业服务平台
	 */
	private Integer flatType;
	
	// 平台性质
	/**
	 * 1企业，2事业单位，3社会团体，4民办非企业 长度1个字符
	 * 
	 */
	private String orgProperty;

	// 成立时间
	private String establishTime;
	
	// 法定代表人
	/**
	 * 2-30字符
	 */
	private String legalPerson;

	// 网址
	/**
	 * 2-200字符
	 */
	private String domain;

	// IP地址
	/**
	 * 1-3位数字 + “.”+ 1-3位数字 + “.”+1-3位数字 + “.”+1-3位数字
	 */
	private String ip;

	/**
	 * 端口号
	 */
	private String port;

	// 平台介绍
	/**
	 * 2-3000字符
	 */
	private String mainRemark;

	// 联系人
	/**
	 * 长度20个字符
	 */
	private String linkMan;

	// 联系电话
	/**
	 * 支持手机+座机 手机规则：11位数字，以1开头 座机规则：0 + 两位或者三位数字 + “-”+ 七位或者八位数字
	 */
	private String tel;

	// 传真
	/**
	 * 长度20个字符
	 */
	private String fax;

	// 电子邮箱
	/**
	 * 长度20个字符
	 */
	private String email;

	// 邮政编码
	/**
	 * 6位字符（只能为数字)
	 */
	private String postcode;

	// 联系地址
	/**
	 * 2-200字符
	 */
	private String address;
	
	//平台编码
	/**
	 * 2-20字符
	 * */
	private String flatCode;
	
	/**
	 * 客户端ID
	 */
	private String client_id;
	
	/**
	 * 客户端ID
	 */
	private String client_secret;
	
	/**
	 * 授权成功回调地址
	 */
	private String redirect_url;
	
	/**
	 * Ucenter api请求url
	 */
	private String ucenter_api;
	
	/**
	 * Ucenter同步开关
	 */
	private boolean sync_switch = false;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlatName() {
		return flatName;
	}

	public void setFlatName(String flatName) {
		this.flatName = flatName;
	}

	public Integer getFlatType() {
		return flatType;
	}

	public void setFlatType(Integer flatType) {
		this.flatType = flatType;
	}

	public String getOrgProperty() {
		return orgProperty;
	}

	public void setOrgProperty(String orgProperty) {
		this.orgProperty = orgProperty;
	}

	public String getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public String getMainRemark() {
		return mainRemark;
	}

	public void setMainRemark(String mainRemark) {
		this.mainRemark = mainRemark;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFlatCode() {
		return flatCode;
	}

	public void setFlatCode(String flatCode) {
		this.flatCode = flatCode;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public String getUcenter_api() {
		return ucenter_api;
	}

	public void setUcenter_api(String ucenter_api) {
		this.ucenter_api = ucenter_api;
	}

	public boolean isSync_switch() {
		return sync_switch;
	}

	public void setSync_switch(boolean sync_switch) {
		this.sync_switch = sync_switch;
	}

}