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

import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;

/**
 * 根据招标方所发布的服务需求，应标方（服务机构）应标后的信息
 * 
 * @author Xiadi
 * @since 2013-9-27
 */
@Entity
@Table(name="responsebidding")
public class ResponseBidding implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 122876682538806095L;

	/**
	 * 主键ID
	 * 
	 */
	@Id
	@GeneratedValue
	private Integer id; // 主键ID
	
	/**
	 * （必填），空值或者不小于0的数字;
	 *  a.应标价格可以为空，为空默认"面议" 
	 *  b.招标价格不为空且应标价格不为空时，应标价格必需在招标价格范围之内
	 * 
	 */
	private Integer bidPrice; // 应标价格
	
	@Column(length = 50)
	private String responseTime = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");; // 应标时间
	
	/**
	 * （非必填）字段长度2-3000
	 * 
	 */
	@Column(length = 3000)
	private String description; // 描述
	
	/**
	 * 必填项 长度100个字符;自动带出机构联系人
	 * 
	 */
	@Column(length = 100)
	private String linkMan; // 联系人
	
	/**
	 * 必填项 长度20个字符;自动带出联系电话，可编辑，校验规则支持座机和手机。
	 * 
	 */
	@Column(length = 20)
	private String tel; // 联系电话
	
	/**
	 * 长度100个字符;自动带出邮箱，可编辑，添加校验规则
	 * 
	 */
	@Column(length = 100)
	private String email; // 邮箱
	
	/**
	 * 1000个字符，非必填,最多上传5个附件，每个附件不超过10M
	 * 
	 */
	@Column(length = 1000)
	private String attachment; // 附件
	
	/**
	 * 长度2个字符,默认为5，状态代码：5、应标中  6、应标失败  7、交易进行中
	 * 
	 */
	@Column(length = 2)
	private Integer status; // 应标状态
	
	/**
	 * 作为外键关联User类的id属性，标识是服务机构主账号申请人
	 * 
	 */
	@ManyToOne
	private User user; // 应标人（服务机构主帐号)
	
	/**
	 * 作为外键关联Staff类的id属性，标识是服务机构子账号申请人
	 * 
	 */
	@ManyToOne
	private Staff staff; // 应标人（服务机构子帐号)
	
	/**
	 * 作为外键关联招标单Bidding属性id
	 * 
	 */
	@ManyToOne
	private BiddingService biddingService; // 招标单
	
	public ResponseBidding(){}

	public ResponseBidding(Integer id, Integer bidPrice, String responseTime,
			String description, String linkMan, String tel, String email,
			String attachment, Integer status, User user, Staff staff,
			BiddingService biddingService) {
		super();
		this.id = id;
		this.bidPrice = bidPrice;
		this.responseTime = responseTime;
		this.description = description;
		this.linkMan = linkMan;
		this.tel = tel;
		this.email = email;
		this.attachment = attachment;
		this.status = status;
		this.user = user;
		this.staff = staff;
		this.biddingService = biddingService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Integer bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public BiddingService getBiddingService() {
		return biddingService;
	}

	public void setBiddingService(BiddingService biddingService) {
		this.biddingService = biddingService;
	}

	@Override
	public String toString() {
		return "ResponseBidding [id=" + id + ", bidPrice=" + bidPrice
				+ ", responseTime=" + responseTime + ", description="
				+ description + ", linkMan=" + linkMan + ", tel=" + tel
				+ ", email=" + email + ", attachment=" + attachment
				+ ", status=" + status + ", user=" + user + ", staff=" + staff
				+ ", biddingService=" + biddingService + "]";
	}
	
}