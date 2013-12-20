package com.eaglec.plat.domain.base;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.eaglec.plat.utils.MD5;

/**
 * 企业<br/>
 * 企业基本信息包括普通企业、认证的企业、服务机构、政府机构基础信息
 * 
 * @author Xiadi
 * @since 2013-8-21
 */
@Entity
@Table(name="enterprise")
public class Enterprise implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id; // 企业ID
	
	/**
	 * 1企业；2事业单位；3社会团体；4个体工商户；5民办非企业；9其他； 必填项 下拉框显示
	 * 
	 * 长度20个字符
	 */
	@Column(length = 2)
	private Integer enterpriseProperty; // 单位性质
	
	/**
	 * 长度100个字符
	 */
	@Column(length = 100)
	private String enterpriseCode; // 
	
	/**
	 * 必填项 长度100个字符
	 * 英文字符(大小写均可)、数字、中文字符任意一个或者多个组合,长度必须为2-50个字符,但不允许为纯数字
	 */
	@Column(length = 100)
	private String name; // 企业全称
	
	/**
	 * 公司简称字段长度为2-20个字符，该字段可用中文字符+英文字符(大小写都可)+数字三者混编,不允许用纯数字.其余组合方式不限.
	 * 
	 * 长度20个字符
	 */
	@Column(length = 20)
	private String forShort; // 企业简称
	
	/**
	 * 1普通企业；2认证企业;3服务机构；4政府机构； 注册默认为1
	 */
	@Column(length = 1)
	private Integer type = 1; // 用户类型
	
	/**
	 * 必填项 长度20个字符
	 */
	@Column(length = 20)
	private String tel; // 联系电话==机构电话
	
	/**
	 * 长度100个字符
	 */
	@Column(length = 100)
	private String email; // 电子邮箱

	/**
	 * 长度100个字符
	 */
	@Column(length = 100)
	private String address; // 企业地址==机构地址

	/**
	 * 1、电子装备 2、服装 3、港澳资源 4、工业设计 5、机械 6、家具 7、LED 8、软件 9、物流10、物联网11、新材料 12、医疗器械13、钟表14、珠宝15、其他
	 * 16、枢纽平台
	 * 长度20个字符
	 */
	@Column(length = 20)
	private Integer industryType; // 行业代码==所属窗口
	
	/**
	 *  1、农、林、牧、渔业
	 *	2、工业 3、建筑业 4、批发业 5、零售业 6、交通运输业 7、仓储业 8、邮政业
     *	9、住宿业 10、餐饮业 11、信息传输业 12、软件和信息技术服务业 13、房地产开发经营
	 *	14、物业管理 15、租赁和商务服务业 16、其他未列明行业 17、金融
	 */
	@Column(length = 20)
	private Integer enterpriseIndustry;	//企业所属行业	2013-11-23 xuwf
	
	/**
	 * 长度100个字符
	 */
	@Column(length = 5000)
	private String mainRemark; // 主要产品或业务

	/**
	 * 格式：YYY-MM 长度20个字符
	 */
	@Column(length = 20)
	private String openedTime; // 成立日期
	
	/**
	 * 长度100个字符
	 */
	@Column(length = 100)
	private String photo = "enterprise_logo.jpg"; // 企业logo图片
	
	/**
	 * 存储图片存放路径 长度100个字符
	 * 字段不许为空
	 */
	@Column(length = 100)
	private String licenceDuplicate; // 工商营业执照
	
	/**
	 * 存储图片存放路径 长度100个字符
	 * 字段不许为空
	 */
	@Column(length = 100)
	private String businessLetter; // 企业公函
	
	/**
	 * 附件字段不限制文档类型（可为word 图片等等）,多个文件存储路径时以“;”号隔开,非必填字段
	 * 存储文件路径+名称 长度500个字符
	 */
	@Column(length = 500)
	private String attachment; //  附件字段不限制文档类型,（可为word、Excel、 图片等等）
	
	/**
	 * 长度1个字符
	 */
	private boolean isApproved; // 是否实名认证
	
	/**
	 * 字段长度为2-50个字符,字段只能是纯数字,不允许填写除数字外的任何字符.
	 * 长度100个字符
	 */
	@Column(length = 100)
	private String icRegNumber; // 组织机构代码

	/**
	 * 长度5000个字符
	 */
	@Column(length = 5000)
	private String profile;//企业简介==机构介绍
	
	/**
	 * 长度为50个字符
	 * 1生产型；2贸易型；3服务性；4生产性、贸易型；5贸易型、服务型；6生产性、贸易型、服务性；
	 * 在企业用户管理中心以下拉框形式展现
	 */
	@Column(length = 50)
	private String businessPattern;//经营模式
	
	/**
	 * 长度为20个字符
	 */
	@Column
	private Integer staffNumber;//员工人数 
	
	@Column
	private double salesRevenue = 0;//年营业额    可以为空
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "enterprise_category",
			inverseJoinColumns={@JoinColumn(name="category_id")})
	private List<Category> myServices; //服务领域
	
	/**
	 * 1-有效 0-禁用 2-删除
	 */
	private Integer status = 1; // 企业状态  
	
	/**
	 * 地址所在市
	 */
	// "深圳市|0","深圳市外|1"
	private Integer city;

	/**
	 * 地址所在区
	 */

	// "罗湖区|1","福田区|2","南山区|3","宝安区|4","龙岗区|5","其它区|6"
	private Integer district;
	
	/**
	 * 1、金融 2、软件及信息服务 3、 物流 4、物联网 5、法律 6、财税 7、科技 8、咨询 9、其他
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
	 *  不能为空
	 */
	@Column(columnDefinition="double default 0")
	private double profit = 0;//利润或净收入（上年、万元）
    
	/**
	 * 长度100个字符,可以为空
	 */
	@Column(length = 100)
	private String honorSociety;//社会荣誉
	
	
	/**
	 * 长度100个字符,可以为空
	 */
	@Column(length = 100)
	private String professionalQualifications;//专业资质
	
	/**
	 * 长度100个字符
	 */
	@Column(length = 100)
	private String legalPerson; // 法定代表人
	
	/**
	 * 长度20个字符,不能为空
	 */
	@Column(length = 20)
	private String legalRepresentativeMobile;//手机
	
	/**
	 * 长度10个字符,可以为空
	 */
	@Column(length = 10)
	private String generalName;//总经理姓名
	
	/**
	 * 长度20个字符,可以为空
	 */
	@Column(length = 20)
	private String generalManagerMobile;//总经理手机
	
	/**
	 * 长度50个字符,可以为空
	 */
	@Column(length = 50)
	private String generalManagerEmail;//总经理email
	
	/**
	 * 必填项 长度100个字符 可以为空
	 */
	@Column(length = 100)
	private String linkman; // 联系人姓名
	
	/**
	 * 长度20个字符,可以为空
	 */
	@Column(length = 20)
	private String contactNameMobile;//联系人手机
	
	/**
	 * 长度50个字符,可以为空
	 */
	@Column(length = 50)
	private String contactNameEmail;//联系人email
	

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
	
	@Column(unique = true)
	private String eid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

	@Column
	private Long modifyTime = System.currentTimeMillis();

	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Enterprise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Enterprise(String eid,Integer enterpriseProperty, String enterpriseCode,
			String name, String forShort, Integer type, String legalPerson,
			String linkman, String tel, String email, String address,
			Integer industryType, String mainRemark, String openedTime,
			boolean isApproved, String icRegNumber, String profile) {
		super();
		this.eid = eid;
		this.enterpriseProperty = enterpriseProperty;
		this.enterpriseCode = enterpriseCode;
		this.name = name;
		this.forShort = forShort;
		this.type = type;
		this.legalPerson = legalPerson;
		this.linkman = linkman;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.industryType = industryType;
		this.mainRemark = mainRemark;
		this.openedTime = openedTime;
		this.isApproved = isApproved;
		this.icRegNumber = icRegNumber;
		this.profile = profile;
	}
	public Enterprise(String eid,Integer enterpriseProperty, String enterpriseCode,
			String name, String forShort, Integer type, String legalPerson,
			String linkman, String tel, String email, String address,
			Integer industryType, String mainRemark, String openedTime,
			String photo, String licenceDuplicate, String businessLetter,
			String attachment, boolean isApproved, String icRegNumber,
			String profile, String businessPattern, Integer staffNumber,
			double salesRevenue, List<Category> myServices, Integer status,
			Integer city, Integer district) {
		super();
		this.eid = eid;
		this.enterpriseProperty = enterpriseProperty;
		this.enterpriseCode = enterpriseCode;
		this.name = name;
		this.forShort = forShort;
		this.type = type;
		this.legalPerson = legalPerson;
		this.linkman = linkman;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.industryType = industryType;
		this.mainRemark = mainRemark;
		this.openedTime = openedTime;
		this.photo = photo;
		this.licenceDuplicate = licenceDuplicate;
		this.businessLetter = businessLetter;
		this.attachment = attachment;
		this.isApproved = isApproved;
		this.icRegNumber = icRegNumber;
		this.profile = profile;
		this.businessPattern = businessPattern;
		this.staffNumber = staffNumber;
		this.salesRevenue = salesRevenue;
		this.myServices = myServices;
		this.status = status;
		this.city = city;
		this.district = district;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEnterpriseProperty() {
		return enterpriseProperty;
	}

	public void setEnterpriseProperty(Integer enterpriseProperty) {
		this.enterpriseProperty = enterpriseProperty;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForShort() {
		return forShort;
	}

	public void setForShort(String forShort) {
		this.forShort = forShort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
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
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIndustryType() {
		return industryType;
	}

	public void setIndustryType(Integer industryType) {
		this.industryType = industryType;
	}

	public String getMainRemark() {
		return mainRemark;
	}

	public void setMainRemark(String mainRemark) {
		this.mainRemark = mainRemark;
	}
	public String getOpenedTime() {
		return openedTime;
	}

	public void setOpenedTime(String openedTime) {
		this.openedTime = openedTime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getIcRegNumber() {
		return icRegNumber;
	}

	public void setIcRegNumber(String icRegNumber) {
		this.icRegNumber = icRegNumber;
	}
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String getBusinessPattern() {
		return businessPattern;
	}

	public void setBusinessPattern(String businessPattern) {
		this.businessPattern = businessPattern;
	}

	public Integer getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}

	public double getSalesRevenue() {
		return salesRevenue;
	}

	public void setSalesRevenue(double salesRevenue) {
		this.salesRevenue = salesRevenue;
	}
	
	public List<Category> getMyServices() {
		return myServices;
	}

	public void setMyServices(List<Category> myServices) {
		this.myServices = myServices;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getDistrict() {
		return this.district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
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
	public String getLegalRepresentativeMobile() {
		return legalRepresentativeMobile;
	}
	public void setLegalRepresentativeMobile(String legalRepresentativeMobile) {
		this.legalRepresentativeMobile = legalRepresentativeMobile;
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
	
	public Integer getEnterpriseIndustry() {
		return enterpriseIndustry;
	}
	public void setEnterpriseIndustry(Integer enterpriseIndustry) {
		this.enterpriseIndustry = enterpriseIndustry;
	}
	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", enterpriseProperty="
				+ enterpriseProperty + ", enterpriseCode=" + enterpriseCode
				+ ", name=" + name + ", forShort=" + forShort + ", type="
				+ type + ", tel=" + tel + ", email=" + email + ", address="
				+ address + ", industryType=" + industryType
				+ ", enterpriseIndustry=" + enterpriseIndustry
				+ ", mainRemark=" + mainRemark + ", openedTime=" + openedTime
				+ ", photo=" + photo + ", licenceDuplicate=" + licenceDuplicate
				+ ", businessLetter=" + businessLetter + ", attachment="
				+ attachment + ", isApproved=" + isApproved + ", icRegNumber="
				+ icRegNumber + ", profile=" + profile + ", businessPattern="
				+ businessPattern + ", staffNumber=" + staffNumber
				+ ", salesRevenue=" + salesRevenue + ", myServices="
				+ myServices + ", status=" + status + ", city=" + city
				+ ", district=" + district + ", orgIndustry=" + orgIndustry
				+ ", orgProperty=" + orgProperty + ", business=" + business
				+ ", advantageServiceAreas=" + advantageServiceAreas
				+ ", totalAssets=" + totalAssets + ", profit=" + profit
				+ ", honorSociety=" + honorSociety
				+ ", professionalQualifications=" + professionalQualifications
				+ ", legalPerson=" + legalPerson
				+ ", legalRepresentativeMobile=" + legalRepresentativeMobile
				+ ", generalName=" + generalName + ", generalManagerMobile="
				+ generalManagerMobile + ", generalManagerEmail="
				+ generalManagerEmail + ", linkman=" + linkman
				+ ", contactNameMobile=" + contactNameMobile
				+ ", contactNameEmail=" + contactNameEmail + ", orgFax="
				+ orgFax + ", orgWebsite=" + orgWebsite + "]";
	}
}