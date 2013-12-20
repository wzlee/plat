package com.eaglec.plat.biz.user;


import java.util.List;

import com.eaglec.plat.domain.base.UserApprovedDetail;
import com.eaglec.plat.view.JSONData;

/**
 * 网站个人用户审核流水管理<br/>
 * 
 * @author xuwf	
 * @since 2013-10-28
 * 
 */
public interface UserApproveBiz {
	
	/**
	 * 查找所有未审核用户申请
	 * @author XUWF
	 * @since 2013-10-28
	 * 
	 * @param username			申请的实名
	 * @param applyTimeBegin	申请时间
	 * @param applyTimeEnd		结束时间
	 * @param applyType			申请类型
	 * @param start		
	 * @param limit
	 * @return
	 */
	public abstract JSONData<UserApprovedDetail> findAll(String username,String realName, 
		String applyTimeBegin, String applyTimeEnd, Integer applyType, int start, int limit);
	
	/**
	 * 审核用户申请
	 * @author xuwf
	 * @since 2013-10-28
	 * 
	 * @param userapprovedDetail
	 * @return
	 */
	public abstract UserApprovedDetail addApprove(UserApprovedDetail userapprovedDetail);
	
	/**
	 * 根据用户id查询认证申请列表
	 *@author xuwf
	 *@since 2013-10-28
	 *
	 *@param uid
	 *@return List<UserApprovedDetail>
	 */
	public abstract List<UserApprovedDetail> findApprListByUid(Integer uid);
	
	/**
	 * 添加用户认证申请，并返回该用户的申请列表
	 *@author xuwf
	 *@since 2013-10-28
	 *
	 *@param approvedDetail
	 *@return boolean<br>true : 成功添加申请  false : 已有同类申请，添加失败  
	 */
	public abstract boolean addApply(UserApprovedDetail userapprovedDetail);
}
