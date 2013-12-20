package com.eaglec.plat.dao.service;

import com.eaglec.plat.dao.BaseDao;
import com.eaglec.plat.domain.base.MyFavorites;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.MyFavoritesView;

/**
 * 服务收藏Dao层
 * @author liuliping
 * @since 2013-11-01
 * 
 */
public interface MyFavoritesDao extends BaseDao<MyFavorites>{
	/**
	 * 查询视图
	 * @param hql hql语句
	 * @param start 分页起始数
	 * @param limit 单页数据条数
	 * */
	public JSONRows<MyFavoritesView> findViews(String hql, int start, int limit);
	
	/**
	 * 收藏的服务个数
	 * @param hql hql语句
	 * @param start 分页起始数
	 * @param limit 单页数据条数
	 * */
	public int getCount(String hql);
}