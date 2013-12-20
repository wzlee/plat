package com.eaglec.plat.view;

import java.io.Serializable;

/**
 * 服务的部分数据和审核的部分数据封装成的对象，用来在页面上显示
 * 
 * @author pangyf
 * @since 2013-09-24
 * */
public class ServiceAndApproval implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6228967211562625798L;
	private Integer id;
	private String serviceName;		
	private Integer currentStatus;	//服务状态
	private Integer operatStatus;	//审核状态
	private String operatorTime;	//操作时间
	private String context;	        //处理意见	
	

	public ServiceAndApproval(){		
	}
	
	public ServiceAndApproval(Integer id,String serviceName,Integer currentStatus,Integer operatStatus,String operatorTime,String context){
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.currentStatus = currentStatus;
		this.operatStatus = operatStatus;
		this.operatorTime = operatorTime;
		this.context = context;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Integer getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Integer getOperatStatus() {
		return operatStatus;
	}
	public void setOperatStatus(Integer operatStatus) {
		this.operatStatus = operatStatus;
	}
	public String getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(String operatorTime) {
		this.operatorTime = operatorTime;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "ServiceAndApproval [id=" + id + ", serviceName=" + serviceName
				+ ", currentStatus=" + currentStatus + ", operatStatus="
				+ operatStatus + ", operatorTime=" + operatorTime
				+ ", context=" + context + "]";
	}
}
