package com.eaglec.plat.domain.base;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** 
 * 买家卖家信誉
 * @author xuwf
 * @since 2013-9-26
 * 
 */
@Entity
@Table(name="enterprisecredit")
public class  EnterpriseCredit implements Serializable {  

	private static final long serialVersionUID = 6407744422815358629L;

	private Integer id;				//主键id

	private Integer buyCount;		//购买服务次数
   
	private Integer buyScore;		//购买总评分

	private Integer sellCount;		//卖出服务次数
   	
	private Integer sellScore;		//卖出总评分
	
	private double buyCredit;		//买家信誉

	private double sellCredit;		//卖家信誉

	private Enterprise enterprise;	//作为外键关联Enterprise的id属性
 
	public EnterpriseCredit() {
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getBuyScore() {
		return buyScore;
	}

	public void setBuyScore(Integer buyScore) {
		this.buyScore = buyScore;
	}

	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

	public Integer getSellScore() {
		return sellScore;
	}

	public void setSellScore(Integer sellScore) {
		this.sellScore = sellScore;
	}

	public double getBuyCredit() {
		return buyCredit;
	}

	public void setBuyCredit(double buyCredit) {
		this.buyCredit = buyCredit;
	}

	public double getSellCredit() {
		return sellCredit;
	}

	public void setSellCredit(double sellCredit) {
		this.sellCredit = sellCredit;
	}
	
	@ManyToOne
	@JoinColumn(name="enterprise_id",unique=true)
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Override
	public String toString() {
		return "EnterpriseCredit [id=" + id + ", buyCount=" + buyCount
				+ ", buyScore=" + buyScore + ", sellCount=" + sellCount
				+ ", sellScore=" + sellScore + ", buyCredit=" + buyCredit
				+ ", sellCredit=" + sellCredit + ", enterprise=" + enterprise
				+ "]";
	}
}