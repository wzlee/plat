package com.eaglec.plat.biz.cms;

import java.util.List;

import com.eaglec.plat.domain.cms.ModuleTemplate;

public interface ModuleTemplateBiz {

	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：获取所有的模版
	 */
	public List<ModuleTemplate> findAll();
	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：添加模版
	 */
	public void add(ModuleTemplate m);
	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：删除模版
	 */
	public void delete(ModuleTemplate m);
	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：修改模版
	 */
	public void update(ModuleTemplate m);
}
