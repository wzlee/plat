package com.eaglec.plat.biz.wx;

import java.io.Serializable;
import java.util.List;

import com.eaglec.plat.domain.wx.WXUser;

public interface WXUserBiz {

	WXUser add(WXUser user);

	WXUser get(Serializable id);

	WXUser get(String name, String value);

	void delete(Serializable id);

	void delete(WXUser user);

	List<WXUser> getAll();

	List<WXUser> getByHql(String hql);

	WXUser update(WXUser user);

}
