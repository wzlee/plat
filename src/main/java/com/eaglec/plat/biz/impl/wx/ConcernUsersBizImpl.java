package com.eaglec.plat.biz.impl.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.wx.ConcernUsersBiz;
import com.eaglec.plat.dao.wx.ConcernUsersDao;
import com.eaglec.plat.domain.wx.ConcernUsers;

@Service
public class ConcernUsersBizImpl implements ConcernUsersBiz{
	
	@Autowired
	private ConcernUsersDao concernUsersDao;

	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：获取微信帐号已经绑定平台的用户列表
	 */
	@Override
	public List<ConcernUsers> getConcernUsers(Integer page, Integer limit){
		return null;
	}
	
	/**
	 * @date: 2013-12-17
	 * @author：lwch
	 * @description：获取微信帐号和平台帐号绑定的信息
	 */
	@Override
	public ConcernUsers getWeixinBoundInfo(String openid){
		return concernUsersDao.get("from ConcernUsers where wxUserToken='"+ openid +"'");
	}
	
	/**
	 * @date: 2013-12-17
	 * @author：lwch
	 * @description：根据微信帐号加密后的ID或者是平台帐号，获取绑定
	 */
	@Override
	public ConcernUsers getWeixinBoundInfo(String openid, String username){
		return concernUsersDao.get("from ConcernUsers where username='"+ username +"' or wxUserToken='"+ openid +"'");
	}
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：添加微信帐号和平台用户绑定的记录
	 */
	@Override
	public Integer addConcernUsers(ConcernUsers cu){
		return concernUsersDao.add(cu);
	}
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：修改微信帐号绑定平台用户的相关信息
	 */
	@Override
	public void updateConcernUsers(ConcernUsers cu){
		concernUsersDao.update(cu);
	}
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：删除微信帐号绑定平台用户的记录
	 */
	@Override
	public void deleteConcernUsers(ConcernUsers cu){
		concernUsersDao.delete(cu);
	}
}
