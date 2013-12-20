package com.eaglec.plat.dao.impl.auth;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.auth.ManagerDao;
import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.view.FlatManagerView;
import com.eaglec.plat.view.JSONData;

@Repository
public class ManagerDaoImpl extends BaseDaoImpl<Manager> implements ManagerDao {

	@Override
	public JSONData<FlatManagerView> findObjects(String hql, int start,
			int limit) {
		int total = 0;
		JSONData<FlatManagerView> jd = new JSONData<FlatManagerView>();
		Query query = getCurrentSession().createQuery(hql);
		total = query.list().size();
		
		@SuppressWarnings("unchecked")
		List<FlatManagerView> list = query.setFirstResult(start)
				.setMaxResults(limit).list();
		jd.setData(list);
		jd.setTotal(total);
		return jd;
	}
}
