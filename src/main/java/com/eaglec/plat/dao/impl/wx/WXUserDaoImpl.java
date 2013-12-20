package com.eaglec.plat.dao.impl.wx;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.wx.WXUserDao;
import com.eaglec.plat.domain.wx.WXUser;

@Repository
public class WXUserDaoImpl extends BaseDaoImpl<WXUser> implements WXUserDao {

	@Override
	public WXUser get(String name, String value) {
		String hql = "from User where " + name + "= '" + value + "'";
		return this.get(hql);
	}

}
