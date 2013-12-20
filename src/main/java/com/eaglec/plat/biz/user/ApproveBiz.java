package com.eaglec.plat.biz.user;


import java.util.List;

import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.ApprovedDetail;
import com.eaglec.plat.domain.base.OrgRegisterApproval;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.service.ServiceOrgRelation;
import com.eaglec.plat.view.JSONData;

/**
 * 网站用户审核管理<br/>
 * 
 * @author Xiadi
 * @since 2013-8-22
 * 
 */
public interface ApproveBiz {
	
	/**
	 * 查找所有未审核用户申请
	 *@author Xiadi
	 *@since 2013-8-24 
	 *
	 *@param username 申请会员名
	 *@param enterpriseName 申请实名
	 *@param applyTimeBegin 开始时间
	 *@param applyTimeEnd 结束时间
	 *@param applyDesc 申请动作
	 *@param start
	 *@param limit
	 *@return
	 */
	public abstract JSONData<ApprovedDetail> findAll(String username, String enterpriseName, 
			String applyTimeBegin, String applyTimeEnd, Integer applyType, int start, int limit);
	
	/**
	 * 审核用户申请
	 *@author Xiadi
	 *@since 2013-8-26 
	 *
	 *@param approvedDetail 未审核的申请
	 *@return approvedDetail 审核后的申请
	 */
	public abstract ApprovedDetail addApprove(ApprovedDetail approvedDetail);
	
	/**
	 * 查询企业申请服务机构的服务列表
	 *@author Xiadi
	 *@since 2013-8-29 
	 *
	 *@param eid 企业id
	 *@return
	 */
	public abstract List<ServiceOrgRelation> findRelationListByEid(Integer eid);
	
	/**
	 * 根据用户id查询认证申请列表
	 *@author Xiadi
	 *@since 2013-8-31 
	 *
	 *@param uid
	 *@return List<ApprovedDetail>
	 */
	public abstract List<ApprovedDetail> findApprListByUid(Integer uid);
	
	/**
	 * 添加用户认证申请，并返回该用户的申请列表
	 *@author Xiadi
	 *@since 2013-8-31 
	 *
	 *@param approvedDetail
	 *@return boolean<br>true : 成功添加申请  false : 已有同类申请，添加失败  
	 */
	public abstract boolean addApply(ApprovedDetail approvedDetail);
	
	/**
	 * 添加服务机构注册申请
	 * @author Xiadi
	 * @since 2013-11-5 
	 *
	 * @param registerApproval
	 */
	public abstract OrgRegisterApproval addOrgRegister(OrgRegisterApproval registerApproval);
	
	/**
	 * 根据ID得到服务机构注册申请
	 * @author Xiadi
	 * @since 2013-11-5 
	 *
	 * @param registerApproval
	 */
	public abstract OrgRegisterApproval getOrgRegister(Integer rid);

	/**
	 * 查找服务机构注册 处理中的 列表
	 * @author Xiadi
	 * @since 2013-11-14 
	 *
	 * @param username
	 * @param enterpriseName
	 * @param applyTimeBegin
	 * @param applyTimeEnd
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract JSONData<OrgRegisterApproval> findOrgRegisterlist(String username,
			String enterpriseName, String applyTimeBegin, String applyTimeEnd,
			Integer start, Integer limit);

	/**
	 * 添加服务机构注册审核<br/>
	 * 当审核通过时 返回 user对象， 打回则返回null
	 * @author Xiadi
	 * @since 2013-11-5 
	 *
	 * @param orgRegisterApproval 服务机构注册申请
	 * @param status 审核状态
	 * @param manager 管理员
	 * @return a user object or null 
	 */
	public abstract User addOrgRegisterApprove(OrgRegisterApproval orgRegisterApproval, Integer status, Manager manager);

	/**
	 * 根据 用户名 查找 处理中的服务机构注册列表 
	 * @author Xiadi
	 * @since 2013-11-14 
	 *
	 * @param username
	 * @return
	 */
	public abstract List<OrgRegisterApproval> findOrgRegisterlistByUsername(String username);

	/**
	 * 根据Id取得申请记录
	 * @author Xiadi
	 * @since 2013-12-3 
	 *
	 * @param id
	 */
	public abstract ApprovedDetail findById(Integer id);
}
