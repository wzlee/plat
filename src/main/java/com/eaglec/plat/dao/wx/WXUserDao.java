package com.eaglec.plat.dao.wx;

import com.eaglec.plat.domain.wx.WXUser;

public interface WXUserDao extends BaseDao<WXUser> {

	WXUser get(String name, String value);

}