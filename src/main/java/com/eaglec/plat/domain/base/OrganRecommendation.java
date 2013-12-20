
package com.eaglec.plat.domain.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 窗口首页top10信息
 * 
 * @author huyj
 * 
 */
@Entity
@Table(name = "organrecommendation")
public class OrganRecommendation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 * 
	 **/
	private Integer id;
	/**
	 * 服务机构
	 * 
	 * */
	private Enterprise enterprise;

	/**
	 * 显示顺序 显示1到10
	 * 
	 * */
	private Integer sort;

	/**
	 * 添加时间
	 * 
	 * */
	private String addTime = DateFormatUtils.format(new Date(),
			"yyyy-MM-dd HH:mm:ss");

	public OrganRecommendation() {

	}

	public OrganRecommendation(Enterprise enterprise, Integer sort,
			String addTime) {
		super();
		this.enterprise = enterprise;
		this.sort = sort;
		this.addTime = DateFormatUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
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

}