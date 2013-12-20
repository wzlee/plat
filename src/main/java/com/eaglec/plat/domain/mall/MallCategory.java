package com.eaglec.plat.domain.mall;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.annotations.ForeignKey;

/**
 * 服务商场类别实体类
 * 
 * @author wzlee
 * @description 管理服务商城类别
 */
@Entity
@Table(name = "mallcategory")
public class MallCategory implements Serializable {
	private static final long serialVersionUID = 7723505770170212140L;

	private Integer id; // 主键ID
	private String code; // 类别编码
	private String text; // 类别名称
	private Integer pid; // 父类ID
	private Boolean leaf = true; // 叶子节点
	private String description; // 类别描述
	private String clazz; // 所属实体类
	private String picture;// 类别下面的具体服务图片(不会写入类别表中，透明的)
	private Boolean hide = true; // 显示还是隐藏(1为显示，0为隐藏)
	private Integer sort; // 排列顺序
	// 添加时间
	private String addTime = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");
	// 修改时间
	private String lastEditTime = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");
	private List<MallCategory> children;

	public MallCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MallCategory(String code, String text, Integer pid, Boolean leaf,
			String description, String clazz, String picture, Boolean hide,
			Integer sort, String addTime, String lastEditTime,
			List<MallCategory> children) {
		super();
		this.code = code;
		this.text = text;
		this.pid = pid;
		this.leaf = leaf;
		this.description = description;
		this.clazz = clazz;
		this.picture = picture;
		this.hide = hide;
		this.sort = sort;
		this.addTime = DateFormatUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		this.lastEditTime = lastEditTime;
		this.children = children;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@ForeignKey(name = "pid")
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	@Transient
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@OneToMany(mappedBy = "pid", cascade = CascadeType.ALL)
	public List<MallCategory> getChildren() {
		return children;
	}

	public void setChildren(List<MallCategory> children) {
		this.children = children;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Boolean getHide() {
		return hide;
	}

	public void setHide(Boolean hide) {
		this.hide = hide;
	}

	@Override
	public String toString() {
		return "MallCategory [id=" + id + ", code=" + code + ", text=" + text
				+ ", pid=" + pid + ", leaf=" + leaf + ", description="
				+ description + ", clazz=" + clazz + ", picture=" + picture
				+ ", hide=" + hide + ", sort=" + sort + ", addTime=" + addTime
				+ ", lastEditTime=" + lastEditTime + ", children=" + children
				+ "]";
	}

}
