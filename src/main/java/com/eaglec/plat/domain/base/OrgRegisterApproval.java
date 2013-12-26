package com.eaglec.plat.domain.base;

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

import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.utils.MD5;

/**
 * 注册服务机构信息的审核<br/>
 * @author Xiadi
 * @since 2013-11-4
 */
@Entity
@Table(name = "orgregisterapproval")
public class OrgRegisterApproval implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id; // id

	@Column(length = 20)
	private String userName; // 会员名

	@Column(length = 100)
	private String password; // 密码

	@Column(length = 100)
	private String orgName; // 公司实名

	/**
	 * 1、电子装备 2、服装 3、港澳资源 4、工业设计 5、机械 6、家具 7、LED 8、软件 9、物流10、物联网11、新材料
	 * 12、医疗器械13、钟表14、珠宝15、其他
	 */
	@Column
	private Integer industryType; // 所属窗口

	@Column(length = 100)
	private String email; // 电子邮箱

	@Column(length = 100)
	private String icRegNumber; // 组织机构号

	@Column(length = 100)
	private String licenceDuplicate; // 公司营业执照

	@Column(length = 100)
	private String businessLetter; // 企业公函

	@Column(length = 100)
	private String regTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"); // 申请时间

	@Column(length = 20)
	private String openedTime;	//成立时间
	
	/**
	 * 长度为20个字符
	 */
	@Column
	private Integer staffNumber;//员工人数 
    
	/**
	 * 1、金融 2、软件及信息服务 3、 物流 4、物联网 5、法律 6、财税 7、科技 8、咨询 9、其它
	 * 不能为空
	 */
	@Column
	private Integer orgIndustry; // 机构行业(对应服务机构注册表单“行业”栏位,下拉框选项，只能选择其一)
	
	/**
	 * 1、国营 2、民营 3、三资 4、股份制 5、合伙制 6、民非  7、其他
	 * 不能为空
	 */
	@Column
	private Integer orgProperty; // 机构性质(对应服务机构注册表单“机构性质”栏位，下拉框选项，只能选择其一)
	
	/**
	 * 长度100个字符,不能为空
	 */
	@Column(length = 100)
	private String business;    // 经营范围(对应服务机构注册表单“经营范围”栏位，文本框)
	
	/**
	 * 长度100个字符,不能为空
	 */
	@Column(length = 100)
	private String advantageServiceAreas; //优势服务领域
	
	/**
	 * 不能为空
	 */
	@Column(columnDefinition="double default 0")
	private double totalAssets = 0; //总资产（上年、万元）
	
	
	/**
	 * 不能为空
	 */
	@Column(columnDefinition="double default 0")
	private double turnover = 0; //年营业额（上年、万元）
	
	/**
	 * 不能为空
	 */
	@Column(columnDefinition="double default 0")
	private double profit = 0;//利润或净收入（上年、万元）
    
	/**
	 * 长度100个字符,不能为空
	 */
	@Column(length = 100)
	private String honorSociety;//社会荣誉
	
	
	/**
	 * 长度100个字符,不能为空
	 */
	@Column(length = 100)
	private String professionalQualifications;//专业资质
	
	/**
	 * 长度10个字符,不能为空
	 */
	@Column(length = 10)
	private String legalRepresentative;//法定代表人
	
	/**
	 * 长度20个字符,不能为空
	 */
	@Column(length = 20)
	private String legalRepresentativeMobile;//手机
	
	/**
	 * 长度50个字符,不能为空
	 */
	@Column(length = 50)
	private String legalRepresentativeEmail;//法定代表人email
	
	/**
	 * 长度10个字符,不能为空
	 */
	@Column(length = 10)
	private String generalName;//总经理姓名
	
	/**
	 * 长度20个字符,不能为空
	 */
	@Column(length = 20)
	private String generalManagerMobile;//总经理手机
	
	/**
	 * 长度50个字符,不能为空
	 */
	@Column(length = 50)
	private String generalManagerEmail;//总经理email
	
	/**
	 * 长度10个字符,不能为空
	 */
	@Column(length = 10)
	private String contactName;//联系人姓名
	
	/**
	 * 长度20个字符,不能为空
	 */
	@Column(length = 20)
	private String contactNameMobile;//联系人手机
	
	/**
	 * 长度50个字符,不能为空
	 */
	@Column(length = 50)
	private String contactNameEmail;//联系人email
	
	/**
	 * 长度50个字符,不能为空
	 */
	@Column(length = 50)
	private String orgAddress;//机构地址
	
	/**
	 * 长度20个字符,不能为空
	 */
	@Column(length = 20)
	private String orgPhone;//机构电话
	
	/**
	 * 长度20个字符,不能为空
	 */
	@Column(length = 20)
	private String orgFax;//机构传真
	
	/**
	 * 长度20个字符,不能为空
	 */
	@Column(length = 100)
	private String orgWebsite;//公司网址
	
	/**
	 * 长度5000个字符,可以为空
	 */
	@Column(length = 5000)
	private String profile;//机构介绍
	
	/**
	 * 0未通过 1通过 2处理中 默认为2 ,长度1
	 */
	@Column
	private Integer approveStatus = 2; // 审核状态

	@Column(length = 100)
	private String approveTime; // 审核时间

	@ManyToOne
	private Manager manager; // 审核人
	
	@ManyToOne
	private Enterprise enterprise;	//审核通过之后关联企业xuwf2013-12-19
	
	@Column(unique = true)
	private String oraid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

	@Column
	private Long modifyTime = System.currentTimeMillis();

	public String getOraid() {
		return oraid;
	}
	public void setOraid(String oraid) {
		this.oraid = oraid;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getIndustryType() {
		return industryType;
	}

	public void setIndustryType(Integer industryType) {
		this.industryType = industryType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
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

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public Integer getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}

	public Integer getOrgIndustry() {
		return orgIndustry;
	}

	public void setOrgIndustry(Integer orgIndustry) {
		this.orgIndustry = orgIndustry;
	}

	public Integer getOrgProperty() {
		return orgProperty;
	}

	public void setOrgProperty(Integer orgProperty) {
		this.orgProperty = orgProperty;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getAdvantageServiceAreas() {
		return advantageServiceAreas;
	}

	public void setAdvantageServiceAreas(String advantageServiceAreas) {
		this.advantageServiceAreas = advantageServiceAreas;
	}

	public double getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public String getHonorSociety() {
		return honorSociety;
	}

	public void setHonorSociety(String honorSociety) {
		this.honorSociety = honorSociety;
	}

	public String getProfessionalQualifications() {
		return professionalQualifications;
	}

	public void setProfessionalQualifications(String professionalQualifications) {
		this.professionalQualifications = professionalQualifications;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getLegalRepresentativeMobile() {
		return legalRepresentativeMobile;
	}

	public void setLegalRepresentativeMobile(String legalRepresentativeMobile) {
		this.legalRepresentativeMobile = legalRepresentativeMobile;
	}

	public String getLegalRepresentativeEmail() {
		return legalRepresentativeEmail;
	}

	public void setLegalRepresentativeEmail(String legalRepresentativeEmail) {
		this.legalRepresentativeEmail = legalRepresentativeEmail;
	}

	public String getGeneralName() {
		return generalName;
	}

	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}

	public String getGeneralManagerMobile() {
		return generalManagerMobile;
	}

	public void setGeneralManagerMobile(String generalManagerMobile) {
		this.generalManagerMobile = generalManagerMobile;
	}

	public String getGeneralManagerEmail() {
		return generalManagerEmail;
	}

	public void setGeneralManagerEmail(String generalManagerEmail) {
		this.generalManagerEmail = generalManagerEmail;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNameMobile() {
		return contactNameMobile;
	}

	public void setContactNameMobile(String contactNameMobile) {
		this.contactNameMobile = contactNameMobile;
	}

	public String getContactNameEmail() {
		return contactNameEmail;
	}

	public void setContactNameEmail(String contactNameEmail) {
		this.contactNameEmail = contactNameEmail;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public String getOrgPhone() {
		return orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

	public String getOrgFax() {
		return orgFax;
	}

	public void setOrgFax(String orgFax) {
		this.orgFax = orgFax;
	}

	public String getOrgWebsite() {
		return orgWebsite;
	}

	public void setOrgWebsite(String orgWebsite) {
		this.orgWebsite = orgWebsite;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getOpenedTime() {
		return openedTime;
	}

	public void setOpenedTime(String openedTime) {
		this.openedTime = openedTime;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	@Override
	public String toString() {
		return "OrgRegisterApproval [id=" + id + ", userName=" + userName
				+ ", password=" + password + ", orgName=" + orgName
				+ ", industryType=" + industryType + ", email=" + email
				+ ", icRegNumber=" + icRegNumber + ", licenceDuplicate="
				+ licenceDuplicate + ", businessLetter=" + businessLetter
				+ ", regTime=" + regTime + ", openedTime=" + openedTime
				+ ", staffNumber=" + staffNumber + ", orgIndustry="
				+ orgIndustry + ", orgProperty=" + orgProperty + ", business="
				+ business + ", advantageServiceAreas=" + advantageServiceAreas
				+ ", totalAssets=" + totalAssets + ", turnover=" + turnover
				+ ", profit=" + profit + ", honorSociety=" + honorSociety
				+ ", professionalQualifications=" + professionalQualifications
				+ ", legalRepresentative=" + legalRepresentative
				+ ", legalRepresentativeMobile=" + legalRepresentativeMobile
				+ ", legalRepresentativeEmail=" + legalRepresentativeEmail
				+ ", generalName=" + generalName + ", generalManagerMobile="
				+ generalManagerMobile + ", generalManagerEmail="
				+ generalManagerEmail + ", contactName=" + contactName
				+ ", contactNameMobile=" + contactNameMobile
				+ ", contactNameEmail=" + contactNameEmail + ", orgAddress="
				+ orgAddress + ", orgPhone=" + orgPhone + ", orgFax=" + orgFax
				+ ", orgWebsite=" + orgWebsite + ", profile=" + profile
				+ ", approveStatus=" + approveStatus + ", approveTime="
				+ approveTime + ", manager=" + manager + ", enterprise="
				+ enterprise + ", oraid=" + oraid + ", modifyTime="
				+ modifyTime + "]";
	}
}