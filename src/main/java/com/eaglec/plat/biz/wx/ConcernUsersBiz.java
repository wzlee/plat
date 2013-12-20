package com.eaglec.plat.biz.wx;

import java.util.List;

import com.eaglec.plat.domain.wx.ConcernUsers;

public interface ConcernUsersBiz {

	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：获取微信帐号已经绑定平台的用户列表
	 */
	public List<ConcernUsers> getConcernUsers(Integer page, Integer limit);
	
	/**
	 * @date: 2013-12-17
	 * @author：lwch
	 * @description：获取微信帐号和平台帐号绑定的信息
	 */
	public ConcernUsers getWeixinBoundInfo(String openid);
	
	/**
	 * @date: 2013-12-17
	 * @author：lwch
	 * @description：根据微信帐号加密后的ID或者是平台帐号，获取绑定
	 */
	public ConcernUsers getWeixinBoundInfo(String openid, String username);
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：添加微信帐号和平台用户绑定的记录
	 */
	public Integer addConcernUsers(ConcernUsers cu);
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：修改微信帐号绑定平台用户的相关信息
	 */
	public void updateConcernUsers(ConcernUsers cu);
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：删除微信帐号绑定平台用户的记录
	 */
	public void deleteConcernUsers(ConcernUsers cu);
}
