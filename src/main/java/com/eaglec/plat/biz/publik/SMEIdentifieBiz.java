package com.eaglec.plat.biz.publik;

import java.util.List;

import com.eaglec.plat.domain.base.SMEIdentifie;
import com.eaglec.plat.view.JSONData;

public interface SMEIdentifieBiz {

	/**
	 * 添加中小企业证明
	 * 
	 * @author huyj
	 * @sicen 2013-11-21
	 * @description TODO
	 * @param smeIdentifie
	 * @return
	 */
	public abstract SMEIdentifie add(SMEIdentifie smeIdentifie);

	/**
	 * 修改中小企业证明
	 * 
	 * @author huyj
	 * @sicen 2013-11-21
	 * @description TODO
	 * @param smeIdentifie
	 * @return
	 */
	public abstract SMEIdentifie update(SMEIdentifie smeIdentifie);

	/**
	 * 删除中小企业证明
	 * 
	 * @author huyj
	 * @sicen 2013-11-21
	 * @description TODO
	 * @param smeIdentifie
	 */
	public abstract void delete(SMEIdentifie smeIdentifie);

	/**
	 * 根据主键id删除中小企业证明
	 * 
	 * @author huyj
	 * @sicen 2013-11-21
	 * @description TODO
	 * @param id
	 */
	public abstract void delete(Integer id);

	/**
	 * 根据用户id获取当前用户提交的认证申请集合
	 * 
	 * @author huyj
	 * @sicen 2013-11-21
	 * @description TODO
	 * @param userId
	 *            用户id
	 * @return
	 */
	public abstract List<SMEIdentifie> findByUserId(Integer userId);


	/**
	 * 根据认证状态及公司名称获取认证集合
	 * 
	 * @author huyj
	 * @sicen 2013-11-21
	 * @description TODO actionPath eg:
	 * @param status
	 *            认证状态 为空查询全部
	 * @param companyName
	 *            公司名称 支持模糊查找
	 * @return
	 */
	public abstract JSONData<SMEIdentifie> findByStatus(Integer status,
			String companyName, Integer statr, Integer limit);

	/**
	 * 根绝id获取认定信息
	 * 
	 * @author huyj
	 * @sicen 2013-11-22
	 * @description TODO actionPath eg:
	 * @param id
	 * @return
	 */
	public abstract SMEIdentifie findById(Integer id);
}
