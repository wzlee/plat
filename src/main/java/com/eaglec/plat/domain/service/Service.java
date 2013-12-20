package com.eaglec.plat.domain.service;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.utils.MD5;

/**
 * 服务 <br/>
 * 描述枢纽平台、窗口平台或者服务机构对自己提供的每一类服务项目的情况，用于使中小企业了解由枢纽平台、窗口平台或者服务机构提供的服务内容 、方式和过程。
 * 
 * @author Xiadi
 * @since 2013-8-9
 * 
 */
@Entity
@Table(name = "service")
public class Service implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 服务id
	private Integer id;

	// 服务编码
	private String serviceNo;

	// 服务来源 ：1服务机构本身上传 2运营管理人员代理录入 3 运营服务
	private Integer serviceSource;

	// 服务类别
	private Category category;

	// 服务名称
	private String serviceName;

	// 添加时间
	private String registerTime = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");

	// 服务次数
	private Integer serviceNum = 0;

	// 联系人
	private String linkMan;

	// 联系电话
	private String tel;

	// 邮箱
	private String email;

	// 服务方式:1柜台式服务；2电话服务；3上门服务；4刊物服务；5信函服务；6网络服务；7其他服务；（填写文字，可以多选，服务之间用“,”分隔）
	private String serviceMethod;

	// 描述
	private String serviceProcedure;

	// 收费模式
	private String chargeMethod;

	// 服务价格
	private Integer costPrice;

	// 服务状态:1新服务,2上架审核中，3已上架，4变更审核中，5已删除,6已下架,7下架审核中
	private Integer currentStatus = 1;

	// 上次服务状态: 1新服务,2上架审核中，3已上架，4变更审核中，5已删除,6已下架,7下架审核中
	private Integer lastStatus;

	private boolean locked = false;

	private String picture = "default_service_pic.gif"; // 当前服务图片

	private double totalScore;  //总评分

	/**
	 * 服务评价=总评分/服务次数
	 */
	private double evaluateScore;

	private Enterprise enterprise; // 企业对象
	
	private Integer detailsId;	//流水表的ID
	
	private Integer mallId; // 服务商城类别id
	
	private String sid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

	private Long modifyTime = System.currentTimeMillis();

	@Column(unique = true)
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Service() {
		// TODO Auto-generated constructor stub
	}

	public Service(String serviceNo, Integer serviceSource, Category category,
			Enterprise enterprise, String serviceName, String registerTime,
			Integer serviceNum, String linkMan, String tel, String email,
			String serviceObject, String serviceMethod,
			String serviceProcedure, String chargeMethod, Integer costPrice,
			String currentStatus, String lastStatus, boolean locked,
			String picture, Integer mallId) {
		super();
		this.serviceNo = serviceNo;
		this.serviceSource = serviceSource;
		this.category = category;
		this.enterprise = enterprise;
		this.serviceName = serviceName;
		this.registerTime = registerTime;
		this.serviceNum = serviceNum;
		this.linkMan = linkMan;
		this.tel = tel;
		this.email = email;
		this.serviceMethod = serviceMethod;
		this.serviceProcedure = serviceProcedure;
		this.chargeMethod = chargeMethod;
		this.costPrice = costPrice;
		this.locked = locked;
		this.picture = picture;
		this.mallId = mallId;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public Integer getServiceSource() {
		return serviceSource;
	}

	public void setServiceSource(Integer serviceSource) {
		this.serviceSource = serviceSource;
	}

	@ManyToOne
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(Integer serviceNum) {
		this.serviceNum = serviceNum;
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

	public String getServiceMethod() {
		return serviceMethod;
	}

	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}

	@Column(length = 5000)
	public String getServiceProcedure() {
		return serviceProcedure;
	}

	public void setServiceProcedure(String serviceProcedure) {
		this.serviceProcedure = serviceProcedure;
	}

	public String getChargeMethod() {
		return chargeMethod;
	}

	public void setChargeMethod(String chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public Integer getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Integer costPrice) {
		this.costPrice = costPrice;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Integer getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(Integer lastStatus) {
		this.lastStatus = lastStatus;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	public double getEvaluateScore() {
		return evaluateScore;
	}

	public void setEvaluateScore(double evaluateScore) {
		this.evaluateScore = evaluateScore;
	}

	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public Integer getMallId() {
		return mallId;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", serviceNo=" + serviceNo
				+ ", serviceSource=" + serviceSource + ", category=" + category
				+ ", serviceName=" + serviceName + ", registerTime="
				+ registerTime + ", serviceNum=" + serviceNum + ", linkMan="
				+ linkMan + ", tel=" + tel + ", email=" + email
				+ ", serviceMethod=" + serviceMethod + ", serviceProcedure="
				+ serviceProcedure + ", chargeMethod=" + chargeMethod
				+ ", costPrice=" + costPrice + ", currentStatus="
				+ currentStatus + ", lastStatus=" + lastStatus + ", locked="
				+ locked + ", picture=" + picture + ", totalScore="
				+ totalScore + ", evaluateScore=" + evaluateScore
				+ ", enterprise=" + enterprise + ", detailsId=" + detailsId
				+ ", mallId=" + mallId + "]";
	}

}