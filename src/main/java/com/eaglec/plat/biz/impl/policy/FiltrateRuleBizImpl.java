package com.eaglec.plat.biz.impl.policy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.policy.FiltrateRuleBiz;
import com.eaglec.plat.dao.policy.FiltrateRuleDao;
import com.eaglec.plat.domain.policy.FiltrateRule;
import com.eaglec.plat.view.PolicyParam;

@Service
public class FiltrateRuleBizImpl implements FiltrateRuleBiz {
	@Autowired
	FiltrateRuleDao ruleDao;

	@Override
	public FiltrateRule add(FiltrateRule filtrateRule) {
		return ruleDao.save(filtrateRule);
	}

	@Override
	public List<FiltrateRule> findReporingId(String ruleId) {
		String hql = "from FiltrateRule where ruleId ='"
				+ ruleId + "'";
		return ruleDao.findList(hql);
	}

	@Override
	public List<FiltrateRule> findRuleByParam(PolicyParam param) {
		String hql = " from FiltrateRule ";

		if (null != param.getAddr()) {
			hql += " where ruleId = '" + param.getAddr() + "'";
		}

		if (null != param.getEnterprise()) {
			if (hql.contains("where")) {
				hql += " or ruleId = '" + param.getEnterprise() + "'";
			} else {
				hql += " where ruleId = '" + param.getEnterprise() + "'";
			}

		}

		if (null != param.getAddr()) {
			if (hql.contains("where")) {
				hql += " or ruleId = '" + param.getAddr() + "'";
			} else {
				hql += " where ruleId = '" + param.getAddr() + "'";
			}

		}
		if (null != param.getTime()) {
			if (hql.contains("where")) {
				hql += " or ruleId = '" + param.getTime() + "'";
			} else {
				hql += " where ruleId = '" + param.getTime() + "'";
			}

		}
		if (null != param.getTotalIncome()) {
			if (hql.contains("where")) {
				hql += " or ruleId = '" + param.getTotalIncome() + "'";
			} else {
				hql += " where ruleId = '" + param.getTotalIncome() + "'";
			}

		}
		if (null != param.getBusinessIncome()) {
			if (hql.contains("where")) {
				hql += " or ruleId = '" + param.getBusinessIncome() + "'";
			} else {
				hql += " where ruleId = '" + param.getBusinessIncome() + "'";
			}
		}

		hql += " group by policy.id";
		return ruleDao.findList(hql);
	}

	@Override
	public void deleteRuleByRuleId(String ruleId) {
		String hql = "from FiltrateRule where ruleId='" + ruleId + "'";
		List<FiltrateRule> list = ruleDao.findList(hql);
		if (!list.isEmpty()) {
			ruleDao.delete(list);
		}
	}
}

