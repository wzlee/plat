package com.eaglec.plat.biz.policy;

import java.util.List;

import com.eaglec.plat.domain.policy.Policy;
import com.eaglec.plat.view.JSONData;

public interface PolicyBiz {
	/**
	 * 根据id查询政策指引内容
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @description TODO actionPath eg:
	 * @param id
	 * @return
	 */

	public abstract Policy findById(Integer id);

	/**
	 * 添加政策指引内容
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @description TODO actionPath eg:
	 * @param policy
	 * @return
	 */
	public abstract Policy add(Policy policy);

	/**
	 * 修改政策指引内容
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @description TODO actionPath eg:
	 * @param policy
	 * @return
	 */
	public abstract Policy update(Policy policy);

	/**
	 * 删除政策指引内容
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @description TODO actionPath eg:
	 * @param id
	 */
	public abstract void delete(Integer id);

	/**
	 * 根据类别id获取该类别下所有内容
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @description TODO actionPath eg:
	 * @param id
	 * @return
	 */
	public abstract List<Policy> findByCid(Integer id, String policyName,
			int start, int limit);

	/**
	 * 查找热门服务，按查点击率最高的10条记录
	 * 
	 * @author huyj
	 * @sicen 2013-10-19
	 * @return
	 */
	public abstract List<Policy> findHotPolicy();

	/**
	 * 根据标题,类别分页查询
	 * @param title 标题
	 * @param pcid 政策指引类别id
	 * @param type 类别
	 * @param start 分页起始
	 * @param limit 单页数据条数
	 * */
	public abstract JSONData<Policy> queryByParams(String title, Integer pcid,
			Integer type, int start, int limit);
	
	/**
	 * 根据类别id查找该类别下policy总数
	 * 
	 * @author huyj
	 * @sicen 2013-10-21
	 * @description TODO actionPath eg:
	 * @param id
	 * @param policyName
	 * @return
	 */
	public Long findCountByCid(Integer id, String policyName);

	/**
	 * 查找没有相关资金资助的政策
	 * 
	 * @author huyj
	 * @sicen 2013-10-24
	 * @return
	 */
	public abstract List<Policy> findPolicyNoReport();

	/**
	 * 查找所有资金资助的政策
	 * 
	 * @author huyj
	 * @sicen 2013-11-20
	 * @description TODO actionPath eg:
	 * @return
	 */
	public abstract List<Policy> findAllReportingPolicy();

	/**
	 * 根据类别查找置顶的政策
	 * 
	 * @author huyj
	 * @sicen 2013-11-26
	 * @description TODO actionPath eg:
	 * @param id
	 * @return
	 */
	public abstract Policy findTopPlicy(Integer id);
	
	/**
	 * 根据服务大类Id来搜索所属类别政策部分信息
	 * @author liuliping
	 * @since 2013-11-28
	 * @param cid 服务大类id
	 * @return
	 * */
	List<Object> findByPCid(Integer cid);


	/**
	 * 获取微信首页加载政策
	 * @author huyj
	 * @sicen 2013-12-18
	 * @return
	 */
	public abstract List<Policy> findPolicyByWX();

}
