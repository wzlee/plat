package com.eaglec.plat.biz.impl.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.wx.UserChangeBiz;
import com.eaglec.plat.dao.wx.UserChangeDao;
import com.eaglec.plat.domain.wx.UserChange;

@Service
public class UserChangeBizImpl implements UserChangeBiz{
	
	@Autowired
	private UserChangeDao userChangeDao;

	/**
	 * @date: 2013-12-17
	 * @author：lwch
	 * @description：获取微信帐号和平台帐号绑定的信息
	 */
	@Override
	public List<UserChange> getUserChanges(String openid){
		return userChangeDao.findList("from UserChange where wxUserToken='"+ openid +"'");
	}
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：添加微信帐号和平台用户绑定的记录
	 */
	@Override
	public Integer addConcernUsers(UserChange uc){
		return userChangeDao.add(uc);
	}
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：修改微信帐号绑定平台用户的相关信息
	 */
	@Override
	public void updateConcernUsers(UserChange uc){
		userChangeDao.update(uc);
	}
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：删除微信帐号绑定平台用户的记录
	 */
	@Override
	public void deleteConcernUsers(UserChange uc){
		userChangeDao.delete(uc);
	}
}
