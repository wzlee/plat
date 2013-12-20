package com.eaglec.plat.domain.base;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.utils.MD5;

/**
 * 认证流水<br/>
 * 记录普通企业到认证企业、认证企业到服务机构、政府机构实名认证的流水
 * 
 * @author Xiadi
 * @since 2013-8-21
 */

@Entity
@Table(name="approveddetail")
public class ApprovedDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id; // ID
	
	/**
	 *时间戳
	 */
	@Column(length = 20)
	private String serialId; // 申请编号 
	
	/**
	 * 0未通过 1通过 2处理中 默认为2 ,长度1个字符
	 */
	@Column(length = 1)
	private Integer approveStatus; // 处理状态 
	
	/**
	 * 长度20个字符
	 */
	@Column(length = 20)
	private String approveTime; // 处理时间 
	
	/**
	 * 作为外键关联用户表User
	 */
	@ManyToOne
	private User user; // 申请人
	
	/**
	 * 作为外键关联Manager类属性id
	 */
	@ManyToOne
	private Manager manager; // 审核人
	/**
	 * 记录操作的动作，1=实名认证 2=服务机构认证
	 */
	@Column(length = 100)
	private Integer applyType; // 申请类型
	
	/**
	 * 申请时间为提交表单时的系统时间
	 * 长度20个字符
	 */
	@Column(length = 20)
	private String applyTime; // 申请时间 
	
	/**
	 * 处理意见
	 */
	@Column(length = 1024)
	private String approvedContext; // 处理意见
	
	/**
	 * 必填项 长度100个字符
	 * 
	 */
	@Column(length = 100)
	private String name; // 申请时的企业实名 认证后即企业全称

	/**
	 * 服务机构认证时所选择服务类别id组合。
	 */
	private String categoryIds; // 申请时的服务类别
	
	/**
	 * 字段长度为2-50个字符,字段只能是纯数字,不允许填写除数字外的任何字符.
	 * 长度100个字符
	 */
	@Column(length = 100)
	private String icRegNumber; // 申请时的组织机构号
	
	/**
	 * 存储图片存放路径 长度100个字符
	 * 字段不许为空
	 */
	@Column(length = 100)
	private String licenceDuplicate; // 申请时的工商营业执照
	
	/**
	 * 存储图片存放路径 长度100个字符
	 * 字段不许为空
	 */
	@Column(length = 100)
	private String businessLetter; // 申请时的企业公函
	
	@ManyToOne
	private Enterprise enterprise; // 企业对象

	@Column(unique = true)
	private String adid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

	@Column
	private Long modifyTime = System.currentTimeMillis();

	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
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

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getApprovedContext() {
		return approvedContext;
	}

	public void setApprovedContext(String approvedContext) {
		this.approvedContext = approvedContext;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getIcRegNumber() {
		return icRegNumber;
	}

	public void setIcRegNumber(String icRegNumber) {
		this.icRegNumber = icRegNumber;
	}

	public String getLicenceDuplicate() {
		return licenceDuplicate;
	}

	public void setLicenceDuplicate(String licenceDuplicate) {
		this.licenceDuplicate = licenceDuplicate;
	}

	public String getBusinessLetter() {
		return businessLetter;
	}

	public void setBusinessLetter(String businessLetter) {
		this.businessLetter = businessLetter;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Override
	public String toString() {
		return "ApprovedDetail [id=" + id + ", serialId=" + serialId
				+ ", approveStatus=" + approveStatus + ", approveTime="
				+ approveTime + ", user=" + user + ", manager=" + manager
				+ ", applyType=" + applyType + ", applyTime=" + applyTime
				+ ", approvedContext=" + approvedContext + ", name=" + name
				+ ", categoryIds=" + categoryIds + ", icRegNumber="
				+ icRegNumber + ", licenceDuplicate=" + licenceDuplicate
				+ ", businessLetter=" + businessLetter + ", enterprise="
				+ enterprise + "]";
	}

}