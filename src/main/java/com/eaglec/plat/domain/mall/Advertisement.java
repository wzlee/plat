package com.eaglec.plat.domain.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.service.Service;

/**
 * 记录广告及服务推荐信息（目前只存储服务商城首页广告、商城tab服务、推荐服务）
 * 
 * @author huyj
 * 
 */
@Entity
@Table(name = "advertisement")
public class Advertisement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 * 
	 */
	private Integer id; // id

	/**
	 * 编号规则：配置类型-所属频道-2位数字。
	 * 
	 * 如：B（banner）-SC（服务商城）gy（工业设计商城）-01 编号规则：配置类型-所属频道-2位数字。
	 * 
	 * 如：B（banner）-SC（服务商城）gy（工业设计商城）-01 编号规则：配置类型-所属频道-2位数字。
	 * 
	 * 如：B（banner）-SC（服务商城）gy（工业设计商城）-01 编号规则：配置类型-所属频道-2位数字。
	 * 
	 * 如：B（banner）-SC（服务商城）gy（工业设计商城）-01
	 * 
	 */
	private String advertisementNo; // 服务广告编号

	/**
	 * 作为外键关联Channel类的id属性
	 * 
	 */
	private Channel channel; // 所属频道

	/**
	 * 广告显示位置:1、服务广告轮播位 2、商城Tab服务推荐位 3、分类服务推荐位 4、左侧服务广告位
	 * 
	 */
	private Integer position; // 所属位置

	/**
	 * 作为外键关联MallCategory类的id属性
	 * 
	 */
	private MallCategory mallCategory; // 所属商城分类

	/**
	 * 默认显示0 顺序：填写0为按照添加时间倒序显示，填写1~10为按照数字从小到大的顺序先后显示
	 * 
	 */
	private Integer sort; // 显示顺序

	/**
	 * 记录上传的图片地址
	 * 
	 */
	private String uploadImage; // 上传服务广告图片

	/**
	 * 作为外键关联Service类的id属性
	 * 
	 */
	private Service service; // 对应服务

	/**
	 * 链接所指向的服务内容页地址
	 * 
	 */
	private String pageLink; // 配置页面连接

	/**
	 * 添加时间
	 * 
	 */
	private String createTime = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");// 添加时间

	/**
	 * 最后操作时间
	 * 
	 */
	private String lastEditTime; // 操作时间

	public Advertisement() {
	}

	public Advertisement(String advertisementNo, Channel channel,
			Integer position, MallCategory mallCategory, Integer sort,
			String uploadImage, Service service, String pageLink,
			String createTime, String lastEditTime) {
		super();
		this.advertisementNo = advertisementNo;
		this.channel = channel;
		this.position = position;
		this.mallCategory = mallCategory;
		this.sort = sort;
		this.uploadImage = uploadImage;
		this.service = service;
		this.pageLink = pageLink;
		this.lastEditTime = lastEditTime;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public String getAdvertisementNo() {
		return advertisementNo;
	}

	@ManyToOne
	public Channel getChannel() {
		return channel;
	}

	public Integer getPosition() {
		return position;
	}

	@ManyToOne
	public MallCategory getMallCategory() {
		return mallCategory;
	}

	public Integer getSort() {
		return sort;
	}

	public String getUploadImage() {
		return uploadImage;
	}

	@ManyToOne
	public Service getService() {
		return service;
	}

	public String getPageLink() {
		return pageLink;
	}

	public String getLastEditTime() {
		return lastEditTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAdvertisementNo(String advertisementNo) {
		this.advertisementNo = advertisementNo;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public void setMallCategory(MallCategory mallCategory) {
		this.mallCategory = mallCategory;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setUploadImage(String uploadImage) {
		this.uploadImage = uploadImage;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}

	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", AdvertisementNo="
				+ advertisementNo + ", Channel=" + channel + ", position="
				+ position + ", mallCategory=" + mallCategory + ", sort="
				+ sort + ", uploadImage=" + uploadImage + ", service="
				+ service + ", pageLink=" + pageLink + ", createTime="
				+ createTime + ", lastEditTime=" + lastEditTime + "]";
	}

}