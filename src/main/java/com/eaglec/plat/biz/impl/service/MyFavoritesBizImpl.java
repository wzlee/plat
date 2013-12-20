package com.eaglec.plat.biz.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.service.MyFavoritesBiz;
import com.eaglec.plat.dao.service.MyFavoritesDao;
import com.eaglec.plat.dao.service.ServiceDao;
import com.eaglec.plat.dao.user.StaffDao;
import com.eaglec.plat.dao.user.UserDao;
import com.eaglec.plat.domain.base.MyFavorites;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.MyFavoritesView;

/**
 * 服务收藏的业务层实现类
 * @author liuliping
 * @since 2013-11-02
 * */
@Service
public class MyFavoritesBizImpl implements MyFavoritesBiz{

	@Autowired
	private MyFavoritesDao myFavoritesDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private ServiceDao serviceDao;
	
	@Override
	public void addOrUpdate(MyFavorites myFavorites) {
		if (myFavorites.getId() == null) {    //新增
			myFavoritesDao.add(myFavorites);
		} else {    //修改
			myFavoritesDao.update(myFavorites);
		}
	}

	@Override
	public void addOrUpdate(Integer userId, Integer serviceId, int userType) {
		MyFavorites myFavorites = null;
		// 用主账号收藏服务
		if (userType == Constant.LOGIN_USER) {    //主帐号收藏服务
			myFavorites = new MyFavorites(userDao.get(userId), serviceDao.get(serviceId));
		} else {    //子账号的主帐号收藏服务
			myFavorites = new MyFavorites(staffDao.get(userId).getParent(), serviceDao.get(serviceId));
		}
		addOrUpdate(myFavorites);
	}
	
	@Override
	public void delete(Integer id) {
		myFavoritesDao.delete(id);
	}

	@Override
	public JSONRows<MyFavoritesView> queryByUserId(Integer userId, int start,
			int limit, String orderBy, int userType) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT new com.eaglec.plat.view.MyFavoritesView(m.id, m.service.category.text, m.time, m.service.id, m.service.serviceName, m.service.costPrice, m.service.serviceProcedure, m.service.picture) FROM MyFavorites m WHERE m.user.id = ");
		if (userType == Constant.LOGIN_USER) {    //主帐号
			sb.append(userId);
		} else {    //子账号
			sb.append(staffDao.get(userId).getParent().getId());
		}
		
		//排序
		if (!StringUtils.isEmpty(orderBy)) {
			sb.append(" ORDER BY ").append(orderBy).append(" DESC");
		}
		return myFavoritesDao.findViews(sb.toString(), start, limit);
	}

	public boolean isExisted(Integer userId, Integer serviceId) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM MyFavorites m WHERE m.user.id = ").append(userId).append(" AND m.service.id = ").append(serviceId);
		int count = myFavoritesDao.getCount(sb.toString());
		if (count > 0) {
			return true;
		}
		return false;
	} 
	
}