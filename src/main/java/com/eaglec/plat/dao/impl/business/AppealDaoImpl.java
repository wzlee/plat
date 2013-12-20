package com.eaglec.plat.dao.impl.business;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.business.AppealDao;
import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.domain.business.Appeal;

@Repository
public class AppealDaoImpl extends BaseDaoImpl<Appeal> implements AppealDao {
	@Override
	public int add(Appeal o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.add(o);
	}
	
	@Override
	public Appeal save(Appeal o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o);
	}
	
	@Override
	public Map<String, Object> save(Appeal o, Object obj) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o, obj);
	}
	
	@Override
	public Appeal saveOrUpdate(Appeal o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.saveOrUpdate(o);
	}
	
	@Override
	public Appeal update(Appeal o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.update(o);
	}
}
