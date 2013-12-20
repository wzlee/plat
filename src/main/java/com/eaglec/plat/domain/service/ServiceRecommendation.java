
package com.eaglec.plat.domain.service;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 窗口首页top10信息
 * 
 * @author huyj
 * 
 */
@Entity
@Table(name = "servicerecommendation")
public class ServiceRecommendation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer topthree;
	
	@OneToOne
	private Service service;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTopthree() {
		return topthree;
	}

	public void setTopthree(Integer topthree) {
		this.topthree = topthree;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}


	
}