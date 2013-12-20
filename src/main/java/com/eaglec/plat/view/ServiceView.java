package com.eaglec.plat.view;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.eaglec.plat.utils.Common;

/**
 * 服务的部分数据封装成的对象，用来在页面上显示
 * 
 * @author liuliping
 * @since 2013-08-28
 * */
public class ServiceView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4199944022558796754L;
	private Integer id;
	private Integer cid;                //类别id
	private String picture;
	private String serviceName;
	private Integer costPrice;			//服务价格
	private String serviceProcedure;	//服务介绍
	private Integer enterpriseId;		//企业id
	private String enterpriseName;		//企业全称
	private String organization;		//企业全称
	private Integer enterpriseStatus;	//企业状态
	private Boolean isApproved;			//企业是否认证
	private String registerTime;		//服务添加时间
	private Integer serviceNum;			//服务被申请次数
	private String tel;					//联系电话
	private String email;				//邮箱
	private double evaluateScore;		//服务评价=总评分/服务次数
	private int stars;					//星级
	
	public ServiceView() {
	}
	
	//加入了企业status的构造方法	xuwf 2013-11-04
	public ServiceView(Integer id, String picture, String serviceName,
			Integer costPrice, String serviceProcedure, Integer enterpriseId,
			String enterpriseName, Integer enterpriseStatus,
			Boolean isApproved, String registerTime, Integer serviceNum,
			String tel, String email, double evaluateScore) {
		super();
		this.id = id;
		this.picture = picture;
		this.serviceName = serviceName;
		this.costPrice = costPrice;
		this.serviceProcedure = serviceProcedure;
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.enterpriseStatus = enterpriseStatus;
		this.isApproved = isApproved;
		this.registerTime = registerTime;
		this.serviceNum = serviceNum;
		this.tel = tel;
		this.email = email;
		this.evaluateScore = evaluateScore;
	}

	public ServiceView(Integer id, String picture, String serviceName,
			Integer costPrice, String serviceProcedure, Integer enterpriseId,
			String enterpriseName, Boolean isApproved, String registerTime,
			Integer serviceNum, String tel, String email, double evaluateScore) {
		super();
		this.id = id;
		this.picture = picture;
		this.serviceName = serviceName;
		this.costPrice = costPrice;
		this.serviceProcedure = serviceProcedure;
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.isApproved = isApproved;
		this.registerTime = registerTime;
		this.serviceNum = serviceNum;
		this.tel = tel;
		this.email = email;
		this.evaluateScore = evaluateScore;
	}
	//加了个企业状态
	public ServiceView(Integer id, String picture, String serviceName,
			Integer costPrice, String serviceProcedure, Integer enterpriseId,
			String enterpriseName,Integer enterpriseStatus, Boolean isApproved, String registerTime,
			Integer serviceNum, String tel, String email) {
		super();
		this.id = id;
		this.picture = picture;
		this.serviceName = serviceName;
		this.costPrice = costPrice;
		this.serviceProcedure = serviceProcedure;
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.enterpriseStatus = enterpriseStatus;
		this.isApproved = isApproved;
		this.registerTime = registerTime;
		this.serviceNum = serviceNum;
		this.tel = tel;
		this.email = email;
	}
	
	public ServiceView(Integer id, String serviceName, String registerTime,
			Integer serviceNum, String serviceProcedure, Integer costPrice,
			String picture, double evaluateScore){
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.registerTime = registerTime;
		this.serviceNum = serviceNum;
		this.serviceProcedure = serviceProcedure;
		this.costPrice = costPrice;
		this.picture = picture;
		this.evaluateScore = evaluateScore;
	}

	/**
	 * 次构造类用于构造符合手机端接受的数据格式
	 * */
	public ServiceView(String picture, String serviceName,
			Integer costPrice, String serviceProcedure, String enterpriseName,
			double evaluateScore) {
		super();
		if(StringUtils.isEmpty(picture)) {
			this.picture = Common.PLAT_DOMAIN + "/upload/default_service_pic.gif";
		} else {
			this.picture = Common.PLAT_DOMAIN + "/upload/" + picture;
		}
		this.serviceName = serviceName;
		this.costPrice = costPrice;
		this.serviceProcedure = serviceProcedure;
		this.stars =  Common.round(evaluateScore).intValue();
		this.organization = enterpriseName;
	}

	public ServiceView(Integer id, Integer cid, String serviceName) {
		super();
		this.id = id;
		this.cid = cid;
		this.serviceName = serviceName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Integer costPrice) {
		this.costPrice = costPrice;
	}

	public String getServiceProcedure() {
		return serviceProcedure;
	}

	public void setServiceProcedure(String serviceProcedure) {
		this.serviceProcedure = serviceProcedure;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public double getEvaluateScore() {
		return evaluateScore;
	}

	public void setEvaluateScore(double evaluateScore) {
		this.evaluateScore = evaluateScore;
	}
	
	public Integer getEnterpriseStatus() {
		return enterpriseStatus;
	}

	public void setEnterpriseStatus(Integer enterpriseStatus) {
		this.enterpriseStatus = enterpriseStatus;
	}

	@Override
	public String toString() {
		return "ServiceView [id=" + id + ", picture=" + picture
				+ ", serviceName=" + serviceName + ", costPrice=" + costPrice
				+ ", serviceProcedure=" + serviceProcedure + ", enterpriseId="
				+ enterpriseId + ", enterpriseName=" + enterpriseName
				+ ", enterpriseStatus=" + enterpriseStatus + ", isApproved="
				+ isApproved + ", registerTime=" + registerTime
				+ ", serviceNum=" + serviceNum + ", tel=" + tel + ", email="
				+ email + ", evaluateScore=" + evaluateScore + "]";
	}
	
}
