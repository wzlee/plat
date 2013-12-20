package com.eaglec.plat.biz.user;

import com.eaglec.plat.domain.base.EnterpriseCredit;

/**
 * 
 * 封装对企业信誉的相关操作
 * 
 * @author xuwf
 * @since 2013-10-24
 * 
 */
public interface EnterpriseCreditBiz {
	
	/**
	 * 根据企业id得到该企业的信誉（买家、卖家信誉）
	 * @author xuwf
	 * @since 2013-10-24
	 * 
	 * @param enterpriseId
	 * @return
	 */
	public abstract EnterpriseCredit queryByEnterprise(Integer enterpriseId);
	
	/**
	 * 保存企业信誉信息
	 * @author xuwf
	 * @since 2013-10-24
	 * 
	 * @param enterpriseCredit
	 */
	public abstract void saveEnterpriseCredit(EnterpriseCredit enterpriseCredit);
	
	/**
	 * 更新企业信誉信息
	 * @author xuwf
	 * @since 2013-10-24
	 * 
	 * @param enterpriseCredit
	 */
	public abstract void updateEnterpriseCredit(EnterpriseCredit enterpriseCredit);
	
	/**
	 * 根据id查找该企业信誉信息
	 * @author xuwf
	 * @since 2013-1024
	 * 
	 * @param id
	 * @return
	 */
	public abstract EnterpriseCredit queryById(Integer id);
}
