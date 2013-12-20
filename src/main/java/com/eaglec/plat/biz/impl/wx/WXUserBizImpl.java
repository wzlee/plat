package com.eaglec.plat.biz.impl.wx;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.wx.WXUserBiz;
import com.eaglec.plat.dao.wx.WXUserDao;
import com.eaglec.plat.domain.wx.WXUser;

@Service
public class WXUserBizImpl implements WXUserBiz {

	@Autowired
	WXUserDao userDao;

	@Override
	public WXUser add(WXUser user) {
		return userDao.save(user);
	}

	@Override
	public WXUser get(Serializable id) {
		return userDao.get(id);
	}

	@Override
	public WXUser get(String name, String value) {
		return userDao.get(name, value);
	}

	@Override
	public void delete(Serializable id) {
		userDao.delete(id);
	}

	@Override
	public void delete(WXUser user) {
		userDao.delete(user);
	}

	@Override
	public List<WXUser> getAll() {
		String hql = "from User where 1=1";
		return userDao.findList(hql);
	}

	@Override
	public List<WXUser> getByHql(String hql) {
		return userDao.findList(hql);
	}

	@Override
	public WXUser update(WXUser user) {
		return userDao.update(user);
	}
}
