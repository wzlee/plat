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
 * 网站用户子账号
 * 记录企业员工的详细信息
 * 
 * @author xuwf
 * @since 2013-8-23
 * 
 */
@Entity
@Table(name="staff")
public class Staff implements Serializable {  

   private static final long serialVersionUID = -4669682766075952703L;
   
   @Id
   @GeneratedValue
   private Integer id;			//员工id 主键
   
   @Column(length=50,nullable=false)
   private String userName;	    //用户名
   
   @Column(length=100,nullable=false)
   private String password;		//密码	采用MD5加密 长度100个字符
   
   @Column(length=50)
   private String staffName;	//员工姓名
   
   @Column(length=2)
   private Integer sex;			//性别0男;1女
   
   @Column(length=15)
   private String tel;			//办公电话
   
   @Column(length=20)
   private String mobile;		//手机
  
   @Column(length=100)
   private String email;		//电子邮箱
   
   @Column(length=200)
   private String address;		//住址
   
   @Column(length=50)
   private String assignTime;	//分配时间
   
   @Column(length=1000)
   private String remark;		//备注
   
   /**
    * 账号状态
    *1有效；2禁用；3删除 
    */
   @Column(length=2)
   private Integer staffStatus;
   
   private Long status;//登陆状态标识符
   /**
    * 父账号 
    * 作为外键关联User中的主键id
    * 
    */
   @ManyToOne
   private User parent;	
   
   /**
    * (分配人)企业用户
    * 作为外键关联User中的主键id,当子账号（员工账号）分配人为企业或者服务机构时，赋值给enterpriseUserId
    * 
    */
   @ManyToOne
   private User assigner;
	
   @ManyToOne
   private StaffRole staffRole;
   
   /** 
    * (分配人)运营人员用户id
    * 作为外键关联Manager中的主键id,当子账号（员工账号）分配人为运营人员时，赋值给managerId
    * 
    */
   @ManyToOne
   private Manager manager;
   

   @Column(unique = true)
   private String stid = MD5.toMD5(UUID.randomUUID().toString()); // 同步唯一标识

   @Column
   private Long modifyTime = System.currentTimeMillis();
   
   public Staff(){
	   
   }
   //小坑p有调用
   public Staff(Integer id,String userName,String staffName,Integer sex,String assignTime,String mobile,Integer staffstatus,StaffRole staffRole,User parent){
	   this.id = id;
	   this.userName = userName;
	   this.staffName = staffName;
	   this.sex = sex;
	   this.assignTime = assignTime;
	   this.mobile = mobile;	  
	   this.staffStatus = staffstatus;
	   this.staffRole = staffRole;	   
   }
   
	public Staff(String userName,String staffName, Integer sex, String tel, String mobile,
		String email, String address, String assignTime, String remark,
		Integer staffStatus, String openID) {
	super();
	this.userName = userName;
	this.staffName = staffName;
	this.sex = sex;
	this.tel = tel;
	this.mobile = mobile;
	this.email = email;
	this.address = address;
	this.assignTime = assignTime;
	this.remark = remark;
	this.staffStatus = staffStatus;
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
	
	public User getAssigner() {
		return assigner;
	}

	public void setAssigner(User assigner) {
		this.assigner = assigner;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(String assignTime) {
		this.assignTime = assignTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(Integer staffStatus) {
		this.staffStatus = staffStatus;
	}

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public StaffRole getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(StaffRole staffRole) {
		this.staffRole = staffRole;
	}
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
	}
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", userName=" + userName + ", password="
				+ password + ", staffName=" + staffName + ", sex=" + sex
				+ ", tel=" + tel + ", mobile=" + mobile + ", email=" + email
				+ ", address=" + address + ", assignTime=" + assignTime
				+ ", remark=" + remark + ", staffStatus=" + staffStatus
				+ ", status=" + status + ", parent=" + parent + ", assigner="
				+ assigner + ", staffRole=" + staffRole + ", manager="
				+ manager + ", stid=" + stid + ", modifyTime=" + modifyTime
				+ "]";
	}
	
}