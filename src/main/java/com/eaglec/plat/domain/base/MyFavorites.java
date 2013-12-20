package com.eaglec.plat.domain.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

import com.eaglec.plat.domain.service.Service;

@Entity
@Table(name = "my_favorites")
public class MyFavorites implements Serializable {

	private static final long serialVersionUID = -8273394114961503581L;

	// 主键id
	private Integer id;
	// 收藏人
	private User user;
	// 收藏时间
	private String time;
	// 服务
	private Service service;

	public MyFavorites() {
		super();
	}

	public MyFavorites(User user, Service service) {
		this.user = user;
		this.service = service;
		this.time = DateFormatUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@ManyToOne
	@JoinColumn
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
