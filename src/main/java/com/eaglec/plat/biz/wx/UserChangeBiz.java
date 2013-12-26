package com.eaglec.plat.biz.wx;

import java.util.List;

import com.eaglec.plat.domain.wx.UserChange;

public interface UserChangeBiz {

	/**
	 * @date: 2013-12-17
	 * @author：lwch
	 * @description：获取微信帐号和平台帐号绑定的信息
	 */
	public List<UserChange> getUserChanges(String openid);
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：添加微信帐号和平台用户绑定的记录
	 */
	public Integer addConcernUsers(UserChange uc);
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：修改微信帐号绑定平台用户的相关信息
	 */
	public void updateConcernUsers(UserChange uc);
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：删除微信帐号绑定平台用户的记录
	 */
	public void deleteConcernUsers(UserChange uc);
}
