package com.eaglec.plat.domain.base;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

import com.eaglec.plat.utils.MD5;

/**
 * 网站用户<br/>
 * 普通企业用户、认证企业用户、服务机构用户、个人用户
 * 
 * @author Xiadi
 * @since 2013-8-22
 */
@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id; // 用户id
	
	/**
	 * 长度为2-20个字符,编码规则为英文字符(大小写都可)、数字混编,不允许为纯数字.
	 * 英文字符为公司名字缩写.会员名在系统中具有唯一性即不允许重复.会员名一旦注册不能修改,会员名为必填项。
	 * 
	 * 长度20个字符
	 */
	@Column(length = 20)
	private String userName; // 会员名

	/**
	 * 采用MD5加密 长度100个字符
	 */
	@Column(length = 100)
	private String password; // 密码

	private Integer sex;// 性别，0先生、1女士

	private String checkcode;// 激活邮箱需要的验证码和时间戳

	/**
	 * 1有效；2禁用；3删除 ，默认为1
	 */
	@Column(length = 2)
	private Integer userStatus; // 帐号状态
	
	private String ip; // 注册时候的ip

	/**
	 * 长度100个字符
	 */
	@Column(length = 100)
	private String regTime = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss"); // 注册时间

	/**
	 * true为个人用户，false为企业用户
	 */
	private boolean isPersonal; // 是否个人用户

	/**
	 * 当是个人用户注册时，需要填写个人电子邮箱。 长度50个字符
	 */
	@Column(length = 50)
	private String email; // 个人电子邮箱

	/**
	 * 为true时是已通过邮箱验证,false未通过邮箱验证
	 */
	private boolean isApproved; // 邮箱是否验证

	/**
	 * 0表示从平台网站注册的用户 1表示从业务运营支撑系统录入的用户 长度1个字符
	 */
	@Column(length = 1)
	private Integer sourceId; // 数据来源

	/**
	 * 长度1024个字符
	 */
	@Column(length = 1024)
	private String remark; // 备注

	/**
	 * 外键关联Enterprise
	 */
	@ManyToOne
	@JoinColumn(name="enterprise_id",unique=true)
	private Enterprise enterprise; // 企业对象
	
	/**
	 * 外键关联Enterprise
	 */
	@ManyToOne
	@JoinColumn(name="sendemail_id",unique=true)
	private SendEmail sendEmail; // 发送完邮件保存的对象
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_category",inverseJoinColumns={@JoinColumn(name="category_id")})
	private List<Category> interestServices; // 我关注的服务领域

	private Long status;//登陆状态标识符
	
	private String nickName;								//昵称
	
	private String headPortraint = "default_logo.jpg";		//头像
	
	private String personalPhoto;			//个人近照图片
	
	private String idCardPhoto;				//身份证附件图片
	
	private String realName;				//真实姓名
	
	@Column(length=20)
	private String mobile;					//联系电话
		
	private String address;					//联系地址
	
//	private boolean isDomainExpert;			//是否领域专家
	
	private Integer certSign = 0;				//认证标识	(只针对于个人用户认证：0未认证用户 	1认证用户)
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "personaluser_category",inverseJoinColumns={@JoinColumn(name="category_id")})
	private List<Category>  userServices;	//我的服务领域 (只针对于个人用户的服务领域)
	
	@Column(unique = true)
	private String uid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

	@Column
	private Long modifyTime = System.currentTimeMillis();

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	public User() {
		super();
	}

	public User(String userName, Integer sex, Integer userStatus,
			String regTime, boolean isPersonal, String email,
			boolean isApproved, Integer sourceId, String remark,Enterprise enterprise,String uid) {
		super();
		this.userName = userName;
		this.sex = sex;
		this.userStatus = userStatus;
		this.regTime = regTime;
		this.isPersonal = isPersonal;
		this.email = email;
		this.isApproved = isApproved;
		this.sourceId = sourceId;
		this.remark = remark;
		this.enterprise = enterprise;
		this.uid = uid;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public String getEmail() {
		return email;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public Integer getId() {
		return id;
	}

	public boolean getIsApproved() {
		return isApproved;
	}

	public boolean getIsPersonal() {
		return isPersonal;
	}

	public String getPassword() {
		return password;
	}

	public String getRegTime() {
		return regTime;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getSex() {
		return sex;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public String getUserName() {
		return userName;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPersonal(boolean isPersonal) {
		this.isPersonal = isPersonal;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	
	public List<Category> getInterestServices() {
		return interestServices;
	}

	public void setInterestServices(List<Category> interestServices) {
		this.interestServices = interestServices;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public SendEmail getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(SendEmail sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPortraint() {
		return headPortraint;
	}

	public void setHeadPortraint(String headPortraint) {
		this.headPortraint = headPortraint;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCertSign() {
		return certSign;
	}

	public void setCertSign(Integer certSign) {
		this.certSign = certSign;
	}

	public List<Category> getUserServices() {
		return userServices;
	}

	public void setUserServices(List<Category> userServices) {
		this.userServices = userServices;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", sex=" + sex + ", checkcode=" + checkcode
				+ ", userStatus=" + userStatus + ", ip=" + ip + ", regTime="
				+ regTime + ", isPersonal=" + isPersonal + ", email=" + email
				+ ", isApproved=" + isApproved + ", sourceId=" + sourceId
				+ ", remark=" + remark + ", enterprise=" + enterprise
				+ ", sendEmail=" + sendEmail + ", interestServices="
				+ interestServices + ", status=" + status + ", nickName="
				+ nickName + ", headPortraint=" + headPortraint
				+ ", personalPhoto=" + personalPhoto + ", idCardPhoto="
				+ idCardPhoto + ", realName=" + realName + ", mobile=" + mobile
				+ ", address=" + address + ", certSign=" + certSign
				+ ", userServices=" + userServices + ", uid=" + uid
				+ ", modifyTime=" + modifyTime + "]";
	}
	
}