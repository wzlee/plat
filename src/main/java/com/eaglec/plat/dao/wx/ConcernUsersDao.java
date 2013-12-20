package com.eaglec.plat.dao.wx;

import java.util.List;

import com.eaglec.plat.domain.wx.ConcernUsers;

public interface ConcernUsersDao extends BaseDao<ConcernUsers>{

	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：获取微信帐号已经绑定平台的用户列表
	 */
	public List<ConcernUsers> getConcernUsers(Integer page, Integer limit);
	
}
