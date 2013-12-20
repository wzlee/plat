package com.eaglec.plat.domain.policy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 发布的具体政策信息
 * 
 */
@Entity
@Table(name = "policy")
public class Policy implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 长度不超过50个字符
	 */
	private String title;

	/**
	 * 类型longtext；界面栏位名“内容”
	 */
	private String text;

	/**
	 * 外键关联PolicyCategory类的id属性;界面栏位名“类别”，下拉框显示
	 */
	private PolicyCategory policyCategory;

	/**
	 * 支持图片、视频。存储图片或视频的相对路径
	 */
	private String uploadFile;

	/**
	 * 文件类型:'image'或者'video'
	 * */
	private String fileType;

	/**
	 * 长度不超过30个字符
	 */
	private String description;

	/**
	 * 来源
	 */
	private String source;

	/**
	 * 添加时间,新增界面，不需要显示，查询需要显示
	 * 
	 */
	private String time = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 记录政策的点击数
	 * 
	 */
	private Integer pv;

	/**
	 * 申报资格
	 */
	private String qualifications;
	/**
	 * 所需材料
	 */
	private String material;
	/**
	 * 受理时限
	 */
	private String timeLimit;
	/**
	 * 申报流程
	 */
	private String process;
	/**
	 * 备案要求
	 */
	private String validity;

	/**
	 * 截止日期
	 */
	private String overTime;

	/**
	 * 置顶
	 */
	private Integer top;



	public Policy() {
	}


	public Policy(String title, String text, PolicyCategory policyCategory,
			String uploadFile, String fileType, String description,
			String source, String time, Integer pv, String qualifications,
			String material, String timeLimit, String process, String validity,
			String overTime, Integer top) {
		super();
		this.title = title;
		this.text = text;
		this.policyCategory = policyCategory;
		this.uploadFile = uploadFile;
		this.fileType = fileType;
		this.description = description;
		this.source = source;
		this.time = time;
		this.pv = pv;
		this.qualifications = qualifications;
		this.material = material;
		this.timeLimit = timeLimit;
		this.process = process;
		this.validity = validity;
		this.overTime = overTime;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "text",length=5000, nullable = true)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	public PolicyCategory getPolicyCategory() {
		return policyCategory;
	}

	public void setPolicyCategory(PolicyCategory policyCategory) {
		this.policyCategory = policyCategory;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	@Column(name = "qualifications", length=5000, nullable = true)
	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	@Column(name = "material", length=5000, nullable = true)
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "timelimit", length=5000, nullable = true)
	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name = "process", length=5000, nullable = true)
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	@Column(name = "validity", length=5000, nullable = true)
	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", title=" + title + ", text=" + text
				+ ", policyCategory=" + policyCategory + ", uploadFile="
				+ uploadFile + ", fileType=" + fileType + ", description="
				+ description + ", source=" + source + ", time=" + time
				+ ", pv=" + pv + ", qualifications=" + qualifications
				+ ", material=" + material + ", timeLimit=" + timeLimit
				+ ", process=" + process + ", validity=" + validity
				+ ", overTime=" + overTime + ", top=" + top + "]";
	}

}