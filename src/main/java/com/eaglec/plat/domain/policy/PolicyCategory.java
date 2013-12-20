package com.eaglec.plat.domain.policy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.annotations.ForeignKey;

/**
 * 政策指引分类主要对政策内容进行归类，类别可以不限层级
 * 
 */
@Entity
@Table(name = "policycategory")
public class PolicyCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 * 
	 */
	private Integer id;

	/**
	 * 分类范畴 0:政策法规 1：专项资金
	 */
	private Integer type;

	/**
	 * 类别名称 长度不超过30个字符
	 */
	private String text;

	/**
	 * 类别描述长度不超过100个字符
	 */
	private String description;

	/**
	 * 添加时间
	 */
	private String time = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 父类ID
	 */
	private Integer pid;

	/**
	 * false : 不是叶子节点; true ： 是叶子节点
	 * */
	private Boolean leaf;

	/**
	 * 子类别
	 */
	private List<PolicyCategory> children;

	public PolicyCategory() {
	}

	public PolicyCategory(Integer id, Integer type, String text,
			String description, String time, Integer pid, Boolean leaf,
			List<PolicyCategory> children) {
		super();
		this.id = id;
		this.type = type;
		this.text = text;
		this.description = description;
		this.time = time;
		this.pid = pid;
		this.leaf = leaf;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@ForeignKey(name = "pid")
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@OneToMany(mappedBy = "pid", cascade = CascadeType.ALL)
	public List<PolicyCategory> getChildren() {
		return children;
	}

	public void setChildren(List<PolicyCategory> children) {
		this.children = children;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	@Override
	public String toString() {
		return "PolicyCategory [id=" + id + ", type=" + type + ", text=" + text
				+ ", description=" + description + ", time=" + time + ", pid="
				+ pid + ", leaf=" + leaf + ", children=" + children + "]";
	}
}