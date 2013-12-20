package com.eaglec.plat.biz.impl.publik;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.publik.SMEIdentifieBiz;
import com.eaglec.plat.dao.publik.SMEIdentifieDao;
import com.eaglec.plat.domain.base.SMEIdentifie;
import com.eaglec.plat.view.JSONData;

/**
 * 中小企业认证业务层实现类
 * 
 * @author huyj
 * */
@Service
public class SMEIdentifieBizImpl implements SMEIdentifieBiz {
	@Autowired
	SMEIdentifieDao identifieDao;

	@Override
	public SMEIdentifie add(SMEIdentifie smeIdentifie) {
		return identifieDao.save(smeIdentifie);
	}

	@Override
	public SMEIdentifie update(SMEIdentifie smeIdentifie) {
		return identifieDao.update(smeIdentifie);
	}

	@Override
	public void delete(SMEIdentifie smeIdentifie) {
		identifieDao.delete(smeIdentifie);
	}

	@Override
	public void delete(Integer id) {
		identifieDao.delete(id);
	}

	@Override
	public List<SMEIdentifie> findByUserId(Integer userId) {
		String hql = "from SMEIdentifie where user.id =" + userId;
		return identifieDao.findList(hql);
	}

	@Override
	public JSONData<SMEIdentifie> findByStatus(Integer status,
			String companyName,Integer statr,Integer limit) {
		String hql = "from SMEIdentifie where 1=1";
		if (null != status) {
			hql += " and approveStatus =" + status;
		}
		if (!StringUtils.isEmpty(companyName)) {
			hql += " and companyName like '%" + companyName + "%'";
		}

		return identifieDao.outJSONData(hql, statr, limit);
	}

	@Override
	public SMEIdentifie findById(Integer id) {
		return identifieDao.get(id);
	}

}
