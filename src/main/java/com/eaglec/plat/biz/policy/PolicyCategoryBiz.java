package com.eaglec.plat.biz.policy;

import java.util.List;

import com.eaglec.plat.domain.policy.PolicyCategory;
import com.eaglec.plat.view.JSONResult;

public interface PolicyCategoryBiz {

	/**
	 * 查询所有政策指引类别
	 * 
	 * @author huyj
	 * @param type
	 * @sicen 2013-10-19
	 * @description TODO actionPath eg:
	 * @return
	 */
	public abstract List<PolicyCategory> findAll(Integer type);

	/**
	 * 根据政策指引类别id查询类别
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @param id
	 * @return
	 */

	public abstract PolicyCategory findById(Integer id);

	/**
	 * 添加政策指引类别
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @param policyCategory
	 * @return
	 */
	public abstract PolicyCategory add(PolicyCategory policyCategory);

	/**
	 * 修改政策指引类别
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @param policyCategory
	 * @return
	 */
	public abstract PolicyCategory update(PolicyCategory policyCategory);

	/**
	 * 根据id删除政策指引类别
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @param id
	 * @return
	 */
	public abstract JSONResult delete(Integer id);

	/**
	 * 查找所以资金资助类别
	 * 
	 * @author huyj
	 * @sicen 2013-10-23
	 * @description TODO actionPath eg:
	 * @return
	 */
	public abstract List<PolicyCategory> findReportCategory();

	/**
	 * 查找政策法规二级类别
	 * 
	 * @author liuliping
	 * @sicen 2013-12-16
	 * @description TODO actionPath eg:
	 * @return
	 */
	List<PolicyCategory> findPolicyCategoryByPid(Integer pid);

}
