package com.eaglec.plat.domain.base;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="access_oauth")
public class AccessOauth {
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")     
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned") 
	private String accessToken;
	private Long accessExpire = new Date().getTime()+360000;
	private String type;
	
	public AccessOauth() {
		super();
	}
	public AccessOauth(String type, String accessToken) {
		super();
		this.type = type;
		this.accessToken = accessToken;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getAccessExpire() {
		return accessExpire;
	}
	public void setAccessExpire(Long accessExpire) {
		this.accessExpire = accessExpire;
	}
	@Override
	public String toString() {
		return "AccessAuth [accessToken=" + accessToken + ", accessExpire="
				+ accessExpire + "]";
	}
	
}
