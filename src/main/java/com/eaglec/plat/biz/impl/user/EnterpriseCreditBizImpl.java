package com.eaglec.plat.biz.impl.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.user.EnterpriseCreditBiz;
import com.eaglec.plat.dao.user.EnterpriseCreditDao;
import com.eaglec.plat.domain.base.EnterpriseCredit;

@Service
public class EnterpriseCreditBizImpl implements EnterpriseCreditBiz {

	@Autowired
	private EnterpriseCreditDao enterpriseCreditDao;
	
	@Override
	public EnterpriseCredit queryByEnterprise(Integer enterpriseId) {
		String hql = "from EnterpriseCredit ec where ec.enterprise.id = :enterpriseId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enterpriseId", enterpriseId);
		return enterpriseCreditDao.get(hql, params);
	}

	@Override
	public void saveEnterpriseCredit(EnterpriseCredit enterpriseCredit) {
		enterpriseCreditDao.save(enterpriseCredit);
	}

	@Override
	public void updateEnterpriseCredit(EnterpriseCredit enterpriseCredit) {
		enterpriseCreditDao.update(enterpriseCredit);
	}

	@Override
	public EnterpriseCredit queryById(Integer id) {
		return enterpriseCreditDao.get(id);
	}

}
