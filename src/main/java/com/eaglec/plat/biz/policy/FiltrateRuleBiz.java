package com.eaglec.plat.biz.policy;

import java.util.List;

import com.eaglec.plat.domain.policy.FiltrateRule;
import com.eaglec.plat.view.PolicyParam;


public interface FiltrateRuleBiz {

	/**
	 * 查找所有规则
	 * 
	 * @author huyj
	 * @sicen 2013-10-28
	 * @param filtrateRule
	 * @return
	 */
	public abstract FiltrateRule add(FiltrateRule filtrateRule);

	/**
	 * 根据规则ruleId查找对应reporting
	 * 
	 * @author huyj
	 * @sicen 2013-10-28
	 * @param ruleId
	 * @return
	 */
	public abstract List<FiltrateRule> findReporingId(String ruleId);

	/**
	 * 
	 * 根据条件计算符合要求的rule
	 * 
	 * @author huyj
	 * @sicen 2013-10-28
	 * @param param
	 */
	public abstract List<FiltrateRule> findRuleByParam(PolicyParam param);

	/**
	 * 删除规则
	 * 
	 * @author huyj
	 * @sicen 2013-10-30
	 * @param ruleId
	 */
	public abstract void deleteRuleByRuleId(String ruleId);

}
