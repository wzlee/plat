package com.eaglec.plat.dao.impl.user;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.user.EnterpriseDao;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.view.CategoryGroup;

@Repository
public class EnterpriseDaoImpl extends BaseDaoImpl<Enterprise> implements
		EnterpriseDao {

	@Override
	public List<CategoryGroup> getCategoryGroupByEid(String hql) {
		Query query = getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<CategoryGroup> list = query.list();
		return list;
	}

	@Override
	public int add(Enterprise o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.add(o);
	}
	
	@Override
	public Enterprise save(Enterprise o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o);
	}
	
	@Override
	public Map<String, Object> save(Enterprise o, Object obj) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o, obj);
	}
	
	@Override
	public Enterprise saveOrUpdate(Enterprise o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.saveOrUpdate(o);
	}
	
	@Override
	public Enterprise update(Enterprise o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.update(o);
	}
}
