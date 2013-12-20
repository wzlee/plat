package com.eaglec.plat.domain.base;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

import com.eaglec.plat.domain.auth.Manager;

/**
 * 办理中小企业证明需要提交的资料
 * 
 * */
@Entity
@Table(name = "smeidentifie")
public class SMEIdentifie implements Serializable {

	private static final long serialVersionUID = -6305691876390498974L;
   	/**
	 * 主键ID
	 * 
	 * */
   private Integer id;

   	/**
	 * 申请材料 保存提交附件的路径,多个附件以|隔开,长度500
	 * 
	 * */
   private String applicationReport;

   	/**
	 * 企业营业执照、企业组织机构代码证复印件 保存提交附件的路径,多个附件以|隔开,长度500
	 * 
	 * */
   private String copiesOfDocuments;

   	/**
	 * 企业（包括分公司）上一个月社保缴费明细表（加盖社保局社保费缴纳清单证明章）保存提交附件的路径,多个附件以|隔开,长度500
	 * 
	 * */
   private String socialSecurityDetails;

   	/**
	 * 企业上年度审计报告复印件 保存提交附件的路径,多个附件以|隔开,长度500
	 * 
	 * */
   private String copyOfTheAuditReport;

   	/**
	 * 其他证明企业资质或荣誉的证明文件 保存提交附件的路径,多个附件以|隔开,长度500
	 * 
	 * */
   private String otherSupportingDocuments;

   	/**
	 * 深圳市中小微企业划型证明申请承诺书（企业盖章） 保存提交附件的路径,多个附件以|隔开,长度500
	 * 
	 * */
   private String applicationUndertaking;

   	/**
	 * 企业的投标文件 保存提交附件的路径,多个附件以|隔开,长度500
	 * 
	 * */
   private String tenderDocuments;

   	/**
	 * 办理中小企业证明企业信息表 保存提交附件的路径,多个附件以|隔开,长度500
	 * 
	 * */
   private String enterpriseInformationTable;

   	/**
	 * 公司名称 长度500
	 * 
	 * */
   private String companyName;

   	/**
	 * 成立日期 长度100
	 * 
	 * */
	private String registerDate;

	/**
	 * 公司地址 长度100
	 * 
	 * */
   private String companyAddress;

   	/**
	 * 邮编 长度100
	 * 
	 * */
	private String postcode;

   	/**
	 * 所从事的行业 长度10
	 * 
	 * */
   private String trades;

   	/**
	 * 网址长度20
	 * 
	 * */
   private String webSite;

   	/**
	 * 公司负责人 长度10
	 * 
	 * */
   private String responsiblePerson;
   	/**
	 * 公司负责人职务 长度20
	 * 
	 * */
   private String responsiblePosition;

   	/**
	 * 公司负责人电话 长度20
	 * 
	 * */
   private String responsiblePhone;

   	/**
	 * 公司负责人手机 长度20
	 * 
	 * */
   private String responsibleMobile;

   	/**
	 * 公司联系人长度20
	 * 
	 **/
   private String contactPserson;

   	/**
	 * 公司联系人姓名长度20
	 * 
	 * */
   private String contactName;

   	/**
	 * 公司联系人职务 长度20
	 * 
	 * */
   private String contactPosition;

   	/**
	 * 公司联系人电话 长度20
	 * 
	 * */
   private String contactPhone;

   	/**
	 * 公司联系人传真 长度20
	 * 
	 * */
   private String contactFax;

   	/**
	 * 公司联系人手机 长度20
	 * 
	 **/
   private String contactMobile;

   	/**
	 * 公司联系人邮箱 长度20
	 * 
	 * */
   private String contactEmail;

   	/**
	 * 其他联系人 长度20
	 * 
	 **/
   private String otherContactPerson;

   	/**
	 * 主营业务/产品 长度500
	 * 
	 * */
   private String mainBusinessProducts;

   	/**
	 * 已取得资质 长度500
	 * 
	 * */
   private String qualification;

   	/**
	 * 企业规模简述 长度500
	 * 
	 * */
   private String scaleIntroduction;

   	/**
	 * 发展中存在的问题 长度500
	 * 
	 * */
   private String problem;

   	/**
	 * 办理中小企业证件类型长度500
	 * 
	 * */
   private String documenType;

	/**
	 * 办证日期长度20
	 * 
	 * 
	 */
   private String rushDate;

	/**
	 * 申请人外键关联User，申请人只能为已通过认证的企业或者服务机构主帐号
	 * 
	 * */
	private User user;

	/**
	 * 申请时间 长度20
	 * 
	 * */
	private String applicationTime  = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");
	/**
	 * 审核人外键关联Manager，审核人只能为运营人员
	 * 
	 * */
	private Manager manager;

	/**
	 * 审核时间 长度20
	 * 
	 */
   private String approvedTime;

   	/**
	 * 审批状态 0未通过 1通过 2处理中 默认为2
	 * 
	 * */
	private Integer approveStatus = 2;

	/**
	 * 审核意见 长度500
	 * 
	 */
	private String approvedOpinion;

	/**
	 * 修改时间
	 */
	private String modifyTime;

	public SMEIdentifie() {
	}

	public SMEIdentifie(String applicationReport, String copiesOfDocuments,
			String socialSecurityDetails, String copyOfTheAuditReport,
			String otherSupportingDocuments, String applicationUndertaking,
			String tenderDocuments, String enterpriseInformationTable,
			String companyName, String registerDate, String companyAddress,
			String postcode, String trades, String webSite,
			String responsiblePerson, String responsiblePosition,
			String responsiblePhone, String responsibleMobile,
			String contactPserson, String contactName, String contactPosition,
			String contactPhone, String contactFax, String contactMobile,
			String contactEmail, String otherContactPerson,
			String mainBusinessProducts, String qualification,
			String scaleIntroduction, String problem, String documenType,
			String rushDate, User user, String applicationTime,
			Manager manager, String approvedTime, Integer approveStatus,
			String approvedOpinion, String modifyTime) {
		super();
		this.applicationReport = applicationReport;
		this.copiesOfDocuments = copiesOfDocuments;
		this.socialSecurityDetails = socialSecurityDetails;
		this.copyOfTheAuditReport = copyOfTheAuditReport;
		this.otherSupportingDocuments = otherSupportingDocuments;
		this.applicationUndertaking = applicationUndertaking;
		this.tenderDocuments = tenderDocuments;
		this.enterpriseInformationTable = enterpriseInformationTable;
		this.companyName = companyName;
		this.registerDate = registerDate;
		this.companyAddress = companyAddress;
		this.postcode = postcode;
		this.trades = trades;
		this.webSite = webSite;
		this.responsiblePerson = responsiblePerson;
		this.responsiblePosition = responsiblePosition;
		this.responsiblePhone = responsiblePhone;
		this.responsibleMobile = responsibleMobile;
		this.contactPserson = contactPserson;
		this.contactName = contactName;
		this.contactPosition = contactPosition;
		this.contactPhone = contactPhone;
		this.contactFax = contactFax;
		this.contactMobile = contactMobile;
		this.contactEmail = contactEmail;
		this.otherContactPerson = otherContactPerson;
		this.mainBusinessProducts = mainBusinessProducts;
		this.qualification = qualification;
		this.scaleIntroduction = scaleIntroduction;
		this.problem = problem;
		this.documenType = documenType;
		this.rushDate = rushDate;
		this.user = user;
		this.applicationTime = DateFormatUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss");
		this.manager = manager;
		this.approvedTime = approvedTime;
		this.approveStatus = approveStatus;
		this.approvedOpinion = approvedOpinion;
		this.modifyTime = modifyTime;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicationReport() {
		return applicationReport;
	}

	public void setApplicationReport(String applicationReport) {
		this.applicationReport = applicationReport;
	}

	public String getCopiesOfDocuments() {
		return copiesOfDocuments;
	}

	public void setCopiesOfDocuments(String copiesOfDocuments) {
		this.copiesOfDocuments = copiesOfDocuments;
	}

	public String getSocialSecurityDetails() {
		return socialSecurityDetails;
	}

	public void setSocialSecurityDetails(String socialSecurityDetails) {
		this.socialSecurityDetails = socialSecurityDetails;
	}

	public String getCopyOfTheAuditReport() {
		return copyOfTheAuditReport;
	}

	public void setCopyOfTheAuditReport(String copyOfTheAuditReport) {
		this.copyOfTheAuditReport = copyOfTheAuditReport;
	}

	public String getOtherSupportingDocuments() {
		return otherSupportingDocuments;
	}

	public void setOtherSupportingDocuments(String otherSupportingDocuments) {
		this.otherSupportingDocuments = otherSupportingDocuments;
	}

	public String getApplicationUndertaking() {
		return applicationUndertaking;
	}

	public void setApplicationUndertaking(String applicationUndertaking) {
		this.applicationUndertaking = applicationUndertaking;
	}

	public String getTenderDocuments() {
		return tenderDocuments;
	}

	public void setTenderDocuments(String tenderDocuments) {
		this.tenderDocuments = tenderDocuments;
	}

	public String getEnterpriseInformationTable() {
		return enterpriseInformationTable;
	}

	public void setEnterpriseInformationTable(String enterpriseInformationTable) {
		this.enterpriseInformationTable = enterpriseInformationTable;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTrades() {
		return trades;
	}

	public void setTrades(String trades) {
		this.trades = trades;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getResponsiblePosition() {
		return responsiblePosition;
	}

	public void setResponsiblePosition(String responsiblePosition) {
		this.responsiblePosition = responsiblePosition;
	}

	public String getResponsiblePhone() {
		return responsiblePhone;
	}

	public void setResponsiblePhone(String responsiblePhone) {
		this.responsiblePhone = responsiblePhone;
	}

	public String getResponsibleMobile() {
		return responsibleMobile;
	}

	public void setResponsibleMobile(String responsibleMobile) {
		this.responsibleMobile = responsibleMobile;
	}

	public String getContactPserson() {
		return contactPserson;
	}

	public void setContactPserson(String contactPserson) {
		this.contactPserson = contactPserson;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPosition() {
		return contactPosition;
	}

	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactFax() {
		return contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getOtherContactPerson() {
		return otherContactPerson;
	}

	public void setOtherContactPerson(String otherContactPerson) {
		this.otherContactPerson = otherContactPerson;
	}

	public String getMainBusinessProducts() {
		return mainBusinessProducts;
	}

	public void setMainBusinessProducts(String mainBusinessProducts) {
		this.mainBusinessProducts = mainBusinessProducts;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getScaleIntroduction() {
		return scaleIntroduction;
	}

	public void setScaleIntroduction(String scaleIntroduction) {
		this.scaleIntroduction = scaleIntroduction;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getDocumenType() {
		return documenType;
	}

	public void setDocumenType(String documenType) {
		this.documenType = documenType;
	}

	public String getRushDate() {
		return rushDate;
	}

	public void setRushDate(String rushDate) {
		this.rushDate = rushDate;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(String applicationTime) {
		this.applicationTime = applicationTime;
	}

	@ManyToOne
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(String approvedTime) {
		this.approvedTime = approvedTime;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApprovedOpinion() {
		return approvedOpinion;
	}

	public void setApprovedOpinion(String approvedOpinion) {
		this.approvedOpinion = approvedOpinion;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "SMEIdentifie [id=" + id + ", applicationReport="
				+ applicationReport + ", copiesOfDocuments="
				+ copiesOfDocuments + ", socialSecurityDetails="
				+ socialSecurityDetails + ", copyOfTheAuditReport="
				+ copyOfTheAuditReport + ", otherSupportingDocuments="
				+ otherSupportingDocuments + ", applicationUndertaking="
				+ applicationUndertaking + ", tenderDocuments="
				+ tenderDocuments + ", enterpriseInformationTable="
				+ enterpriseInformationTable + ", companyName=" + companyName
				+ ", registerDate=" + registerDate + ", companyAddress="
				+ companyAddress + ", Postcode=" + postcode + ", trades="
				+ trades + ", webSite=" + webSite + ", responsiblePerson="
				+ responsiblePerson + ", responsiblePosition="
				+ responsiblePosition + ", responsiblePhone="
				+ responsiblePhone + ", responsibleMobile=" + responsibleMobile
				+ ", contactPserson=" + contactPserson + ", contactName="
				+ contactName + ", contactPosition=" + contactPosition
				+ ", contactPhone=" + contactPhone + ", contactFax="
				+ contactFax + ", contactMobile=" + contactMobile
				+ ", contactEmail=" + contactEmail + ", otherContactPerson="
				+ otherContactPerson + ", mainBusinessProducts="
				+ mainBusinessProducts + ", qualification=" + qualification
				+ ", scaleIntroduction=" + scaleIntroduction + ", problem="
				+ problem + ", documenType=" + documenType + ", rushDate="
				+ rushDate + ", user=" + user + ", applicationTime="
				+ applicationTime + ", manager=" + manager + ", approvedTime="
				+ approvedTime + ", approveStatus=" + approveStatus
				+ ", approvedOpinion=" + approvedOpinion + ", modifyTime="
				+ modifyTime + "]";
	}
	
}