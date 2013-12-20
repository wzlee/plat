package com.eaglec.plat.domain.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.eaglec.plat.domain.auth.Manager;

/**
 * 认证流水<br/>
 * 记录个人用户实名认证的流水
 * 
 * @author xuwf
 * @since 2013-10-26
 */

@Entity
@Table(name="userapproveddetail")
public class UserApprovedDetail implements Serializable {

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
	 * 记录操作的动作，1=实名认证 
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
	private String name; // 申请时的用户姓名认证后即真实姓名
	
	/**
	 * 存储图片存放路径 长度100个字符
	 * 字段不许为空
	 */
	@Column(length = 100)
	private String personalPhoto; // 申请时的个人近照
	
	/**
	 * 存储图片存放路径 长度100个字符
	 * 字段不许为空
	 */
	@Column(length = 100)
	private String idCardPhoto; // 申请时的身份证附件图片

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

	public String getPersonalPhoto() {
		return personalPhoto;
	}

	public void setPersonalPhoto(String personalPhoto) {
		this.personalPhoto = personalPhoto;
	}

	public String getIdCardPhoto() {
		return idCardPhoto;
	}

	public void setIdCardPhoto(String idCardPhoto) {
		this.idCardPhoto = idCardPhoto;
	}

	@Override
	public String toString() {
		return "UserApprovedDetail [id=" + id + ", serialId=" + serialId
				+ ", approveStatus=" + approveStatus + ", approveTime="
				+ approveTime + ", user=" + user + ", manager=" + manager
				+ ", applyType=" + applyType + ", applyTime=" + applyTime
				+ ", approvedContext=" + approvedContext + ", name=" + name
				+ ", personalPhoto=" + personalPhoto + ", idCardPhoto="
				+ idCardPhoto + "]";
	}

}