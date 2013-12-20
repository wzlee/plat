package com.eaglec.plat.domain.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/** 
 * 系统日志
 * @author xuwf
 * @since 2013-9-10
 * 
 */
@Entity
@Table(name="systemlog")
public class SystemLog implements Serializable {  

	private static final long serialVersionUID = 439011281101186585L;

	private Integer id;				//主键id
   
	private String operator;		//操作人

	private String logInfo;			//操作内容	

	private String logTime;			//写日志时间

	private String loginIp;			//登录者登录IP
   
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	@Override
	public String toString() {
		return "SystemLog [id=" + id + ", operator=" + operator + ", logInfo="
				+ logInfo + ", logTime=" + logTime + ", loginIp=" + loginIp
				+ "]";
	}
	
}