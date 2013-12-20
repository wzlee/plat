package com.eaglec.plat.domain.service;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.MD5;

/**
 * 服务流水 <br/>
 * 描述服务机构对自己提供的每一类服务项目的操作流水情况
 * 
 * @author Xiadi
 * @since 2013-8-9
 * 
 */
@Entity
@Table(name="servicedetail")
public class ServiceDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 流水id
	@Id
	@GeneratedValue
	private Integer id;
	
	// 流水号 格式'A01' + yyyyMMddHHmmss : A0120131230235959
	private String serialId;
	
	// 审核状态 0未通过 1通过 2 未审核
	private Integer operatStatus;
	
	// 操作时间
	private String operatorTime =  DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	
	private Integer serviceId;    //服务id
	
	private String serviceName;    //服务名称
	
	// 服务状态:1新服务,2上架审核中，3已上架，4变更审核中，5已删除,6已下架,7下架审核中
	private Integer currentStatus;

	// 联系人
	private String linkMan;

	// 联系电话
	private String tel;

	// 邮箱
	private String email;

	// 服务方式:1柜台式服务；2电话服务；3上门服务；4刊物服务；5信函服务；6网络服务；7其他服务；（填写文字，可以多选，服务之间用“,”分隔）
	private String serviceMethod;

	// 描述
	@Column(length = 5000)
	private String serviceProcedure;

	// 收费模式
	private String chargeMethod;

	// 服务价格
	private Integer costPrice;

	// 当前服务图片
	private String picture;
	
	// 操作用户
	private String operationUser;
	
	// 所属平台
	private Integer belongPlat;
	
	// 用户类型
	private Integer userType;
	
	// 处理意见
	@Column(length = 1024)
	private String context;
	
	@Column(unique = true)
	private String sdid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识
	
	private Long modifyTime = System.currentTimeMillis();	

	public ServiceDetail() {
		Date date = new Date();
		this.operatorTime = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		this.serialId = Constant.SERIAL_Id
				+ DateFormatUtils.format(date, "yyyyMMddHHmmss");
	}

	public ServiceDetail(Integer operatStatus, Integer serviceId,
			String serviceName,Integer currentStatus) {
		super();
		Date date = new Date();
		this.operatorTime = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		this.serialId = Constant.SERIAL_Id
				+ DateFormatUtils.format(date, "yyyyMMddHHmmss");
		this.operatStatus = operatStatus;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.currentStatus = currentStatus;
	}

	public ServiceDetail(String serialId, Integer operatStatus,
			String operatorTime, Integer serviceId, String serviceName,
			String linkMan, String tel, String email, String serviceMethod,
			String serviceProcedure, String chargeMethod, Integer costPrice,
			String picture, Integer check) {
		super();
		Date date = new Date();
		this.operatorTime = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		this.serialId = Constant.SERIAL_Id
				+ DateFormatUtils.format(date, "yyyyMMddHHmmss");
		this.operatStatus = operatStatus;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.linkMan = linkMan;
		this.tel = tel;
		this.email = email;
		this.serviceMethod = serviceMethod;
		this.serviceProcedure = serviceProcedure;
		this.chargeMethod = chargeMethod;
		this.costPrice = costPrice;
		this.picture = picture;
	}

	public ServiceDetail(Service service, Integer operatStatus,Integer currentStatus) {
		super();
		Date date = new Date();
		this.operatorTime = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		this.serialId = Constant.SERIAL_Id
				+ DateFormatUtils.format(date, "yyyyMMddHHmmss");
		this.currentStatus = currentStatus;
		this.operatStatus = operatStatus;
		this.serviceId = service.getId();
		this.serviceName = service.getServiceName();
		this.linkMan = service.getLinkMan();
		this.tel = service.getTel();
		this.email = service.getEmail();
		this.serviceMethod = service.getServiceMethod();
		this.serviceProcedure = service.getServiceProcedure();
		this.chargeMethod = service.getChargeMethod();
		this.costPrice = service.getCostPrice();
		this.picture = service.getPicture();
	}
	
	public ServiceDetail(Service service, Integer operatStatus) {
		Date date = new Date();
		this.operatorTime = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		this.serialId = Constant.SERIAL_Id
				+ DateFormatUtils.format(date, "yyyyMMddHHmmss");
		this.operatStatus = operatStatus;
		this.serviceId = service.getId();
		this.serviceName = service.getServiceName();
		this.linkMan = service.getLinkMan();
		this.tel = service.getTel();
		this.email = service.getEmail();
		this.serviceMethod = service.getServiceMethod();
		this.serviceProcedure = service.getServiceProcedure();
		this.chargeMethod = service.getChargeMethod();
		this.costPrice = service.getCostPrice();
		this.picture = service.getPicture();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public Integer getOperatStatus() {
		return operatStatus;
	}

	public void setOperatStatus(Integer operatStatus) {
		this.operatStatus = operatStatus;
	}
	
	public Integer getcurrentStatus() {
		return currentStatus;
	}

	public void setcurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(String operatorTime) {
		this.operatorTime = operatorTime;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public String getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public String getServiceMethod() {
		return serviceMethod;
	}

	public String getServiceProcedure() {
		return serviceProcedure;
	}

	public String getChargeMethod() {
		return chargeMethod;
	}

	public Integer getCostPrice() {
		return costPrice;
	}

	public String getPicture() {
		return picture;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}

	public void setServiceProcedure(String serviceProcedure) {
		this.serviceProcedure = serviceProcedure;
	}

	public void setChargeMethod(String chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public void setCostPrice(Integer costPrice) {
		this.costPrice = costPrice;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public String getOperationUser() {
		return operationUser;
	}

	public void setOperationUser(String operationUser) {
		this.operationUser = operationUser;
	}

	public Integer getBelongPlat() {
		return belongPlat;
	}

	public void setBelongPlat(Integer belongPlat) {
		this.belongPlat = belongPlat;
	}
	
	public String getSdid() {
		return sdid;
	}

	public void setSdid(String sdid) {
		this.sdid = sdid;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}	

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "ServiceDetail [id=" + id + ", serialId=" + serialId
				+ ", operatStatus=" + operatStatus + ", operatorTime="
				+ operatorTime + ", serviceId=" + serviceId + ", serviceName="
				+ serviceName + ", currentStatus=" + currentStatus
				+ ", linkMan=" + linkMan + ", tel=" + tel + ", email=" + email
				+ ", serviceMethod=" + serviceMethod + ", serviceProcedure="
				+ serviceProcedure + ", chargeMethod=" + chargeMethod
				+ ", costPrice=" + costPrice + ", picture=" + picture
				+ ", operationUser=" + operationUser + ", belongPlat="
				+ belongPlat + ", userType=" + userType + ", context="
				+ context + "]";
	}

}