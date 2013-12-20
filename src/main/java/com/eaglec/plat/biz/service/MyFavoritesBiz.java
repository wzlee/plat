package com.eaglec.plat.biz.service;

import com.eaglec.plat.domain.base.MyFavorites;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.MyFavoritesView;

/**
 * 服务收藏业务层接口 
 * @author liuliping
 * @since 2013-11-01
 * 
 */
public interface MyFavoritesBiz {
	
	/**
	 * 添加/修改服务收藏对象
	 * @param myFavorites 服务收藏对象
	 * */
	void addOrUpdate(MyFavorites myFavorites);
	
	/**
	 * 添加/修改服务收藏对象
	 *@param userId 用户id
	 *@param serviceId 服务id
	 *@param userType 用户类型 1:主帐号 2:子账号
	 * */
	void addOrUpdate(Integer userId, Integer serviceId, int userType);
	
	/**
	 * 删除
	 * @param id 服务收藏对象的主键id
	 * */
	void delete(Integer id);
	
	/**
	 * 根据用户查询服务收藏
	 * @param userId 用户对象主键id
	 * @param start 分页起始
	 * @param limit 单页数据条数
	 * @param userType 用户的类型
	 * @return 服务收藏视图
	 * */
	JSONRows<MyFavoritesView> queryByUserId(Integer userId, int start, int limit, String orderBy, int userType);
	
	/**
	 * 服务收藏对象是否已存在
	 * @param userId 用户对象主键id
	 * @param serviceId 服务id
	 * @return true : 已存在; false : 不存在
	 * */
	public boolean isExisted(Integer userId, Integer serviceId);
}