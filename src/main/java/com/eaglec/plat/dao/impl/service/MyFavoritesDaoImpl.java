package com.eaglec.plat.dao.impl.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.service.MyFavoritesDao;
import com.eaglec.plat.domain.base.MyFavorites;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.MyFavoritesView;
import com.eaglec.plat.view.ServiceView;

@Repository
public class MyFavoritesDaoImpl extends BaseDaoImpl<MyFavorites> implements
		MyFavoritesDao {
	
	/**
	 * 查询视图
	 * @param hql hql语句
	 * @param start 分页起始数
	 * @param limit 单页数据条数
	 * */
	public JSONRows<MyFavoritesView> findViews(String hql, int start, int limit) {
		int total = 0;
		JSONRows<MyFavoritesView> jr = new JSONRows<MyFavoritesView>();
		Query query = getCurrentSession().createQuery(hql);
		total = query.list().size();
		
		@SuppressWarnings("unchecked")
		List<MyFavoritesView> list = query.setFirstResult(start)
				.setMaxResults(limit).list();
		jr.setRows(list);
		jr.setSuccess(true);
		jr.setTotal(total);
		return jr;
	}
	
	/**
	 * 收藏的服务个数
	 * @param hql hql语句
	 * @param start 分页起始数
	 * @param limit 单页数据条数
	 * */
	public int getCount(String hql) {
		int total = 0;
		Query query = getCurrentSession().createQuery(hql);
		total = query.list().size();
		
		return total;
	}
}
