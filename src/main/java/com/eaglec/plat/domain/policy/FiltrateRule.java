package com.eaglec.plat.domain.policy;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 资助一键通查询规则
 * 
 */
@Entity
@Table(name = "filtraterule")
public class FiltrateRule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键Id
	 */
	private Integer id;

	/**
	 * 条件栏位 01 企业创立时间 02 创立地点 03 企业所属 04 上年度主营业务收入 05 上年度销售总收入
	 * 
	 */
	private String conditionField;


	/**
	 * 规则编号 0101 企业创立时间：较今日满一年以上 0102 企业创立时间：较今日不满一年 0201 创立地点：深圳市 0202
	 * 创立地点：不在深圳市 0301 企业所属：进出口外贸 0302 企业所属：互联网产业 0303 企业所属：新能源产业 0304
	 * 企业所属：新材料产业 0305 企业所属：生物产业 0306 企业所属：新一代信息技术产业 0307 企业所属：会展业 0308
	 * 企业所属：军工配套等新兴产业 0309 企业所属：其他 0401 上年主营业务收入:大于300万小于1亿 0402 上年主营收入：大于或等于1亿
	 * 0403 上年主营业务收入:小于或等于300万 0501 上年总收入：小于或等于2亿 0502 上年总收入：大于2亿
	 * 
	 * */
	private String ruleId;




	/**
	 * 资金资助政策 外键关联FinancialReporting的id属性
	 * 
	 * */
	private Policy policy;

	public FiltrateRule() {

	}

	public FiltrateRule(String conditionField, String ruleId, String ruleName,
			Policy policy) {
		super();
		this.conditionField = conditionField;
		this.ruleId = ruleId;
		this.policy = policy;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConditionField() {
		return conditionField;
	}

	public void setConditionField(String conditionField) {
		this.conditionField = conditionField;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}


	@ManyToOne
	public Policy getPolicy() {
		return this.policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	@Override
	public String toString() {
		return "FiltrateRule [id=" + id + ", conditionField=" + conditionField
				+ ", ruleId=" + ruleId + ", policy=" + policy + "]";
	}


}