package com.eaglec.plat.domain.business;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;

/**
 * 招标方所发布的服务需求信息
 * 
 * @author Xiadi
 * @since 2013-9-27
 */
@Entity
@Table(name="biddingservice")
public class BiddingService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1870330746161005427L;

	/**
	 * 招标单主键ID
	 * 
	 */
	@Id
	@GeneratedValue
	private Integer id; // 主键ID
	
	/**
	 * D+年月8位+5位随机数字
	 * 
	 */
	private String bidNo; // 招标单号
	
	@Column(length = 50)
	private String createTime = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss"); // 创建时间
	
	/**
	 * 必填,5到50个字符
	 * 
	 */
	@Column(length = 50)
	private String name; // 服务名称
	
	/**
	 * （非必填），内容为不小于0的数字
	 * 
	 */
	private Integer minPrice; // 招标价格（起)
	
	/**
	 * （非必填），内容为不小于0的数字,招标价格（止）应大于等于招标价格（起)
	 * 
	 */
	private Integer maxPrice; // 招标价格（止)
	
	/**
	 * 1000个字符，非必填,最多上传5个附件，每个附件不超过10M
	 * 
	 */
	@Column(length = 1000)
	private String attachment; // 附件
	
	/**
	 * （非必填）字段长度2-3000 
	 * 
	 */
	@Column(length = 3000)
	private String description; // 描述
	
	/**
	 * 必填项 长度100个字符
	 * 
	 */
	@Column(length = 100)
	private String linkMan; // 联系人
	
	/**
	 * 必填项 长度20个字符
	 * 
	 */
	@Column(length = 20)
	private String tel; // 联系电话
	
	/**
	 * 长度100个字符
	 * 
	 */
	@Column(length = 100)
	private String email; // 邮箱
	
	/**
	 * 长度2个字符,默认为1，状态代码：1、待发布 2、待审核 3、平台退回 4、招标中 5、应标中 6、交易进行中 7、等待买家关闭 8、等待卖家关闭
	 * 9、申诉处理中 10、交易结束 11、招标取消 12、订单取消
	 * 
	 */
	@Column(length = 2)
	private Integer status; // 招标单状态
	
	/**
	 * 作为外键关联Category类的id属性
	 * 
	 */
	@ManyToOne
	private Category category; // 服务分类
	
	/**
	 * 作为外键关联User类的id属性，标识是企业或者服务机构主账号申请人
	 * 
	 */
	@ManyToOne
	private User user; // 招标申请人（企业主帐号)
	
	/**
	 * 作为外键关联Staff类的id属性，标识是企业或者服务机构子账号申请人
	 * 
	 */
	@ManyToOne
	private Staff staff; // 招标申请人（企业子帐号)
	
	private Integer rid; // 选择的应标方id
	
	private String rname; // 选择的应标方企业实名

	public BiddingService(){}

	public BiddingService(Integer id, String bidNo, String createTime,
			String name, Integer minPrice, Integer maxPrice, String attachment,
			String description, String linkMan, String tel, String email,
			Integer status, Category category, User user,
			com.eaglec.plat.domain.base.Staff staff, Integer rid, String rname) {
		super();
		this.id = id;
		this.bidNo = bidNo;
		this.createTime = createTime;
		this.name = name;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.attachment = attachment;
		this.description = description;
		this.linkMan = linkMan;
		this.tel = tel;
		this.email = email;
		this.status = status;
		this.category = category;
		this.user = user;
		this.staff = staff;
		this.rid = rid;
		this.rname = rname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBidNo() {
		return bidNo;
	}

	public void setBidNo(String bidNo) {
		this.bidNo = bidNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer mixPrice) {
		this.minPrice = mixPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	@Override
	public String toString() {
		return "BiddingService [id=" + id + ", bidNo=" + bidNo
				+ ", createTime=" + createTime + ", name=" + name
				+ ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", attachment=" + attachment + ", description=" + description
				+ ", linkMan=" + linkMan + ", tel=" + tel + ", email=" + email
				+ ", status=" + status + ", category=" + category + ", user="
				+ user + ", staff=" + staff + ", rid=" + rid + ", rname="
				+ rname + "]";
	}

}