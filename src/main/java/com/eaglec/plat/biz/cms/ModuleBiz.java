package com.eaglec.plat.biz.cms;

import java.util.List;
import java.util.Map;

import com.eaglec.plat.domain.cms.Module;

public interface ModuleBiz {
	
	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：获取该频道下的所有的模块
	 */
	public List<Module> findAll(String mchannel);
	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：获取所有的模版
	 */
	public List<?> findAllTemplate();
	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：添加模块
	 */
	public int add(Module m);
	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：删除模块
	 */
	public void delete(Module m);
	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：修改模块
	 */
	public void update(Module m);
	
	/**
	 * @date: 2013-09-25
	 * @author：liuliping
	 * @description：通过频道编码查找模块
	 */
	public List<Module> findByMchannel(String mchannel); 
	
	/**
	 * @date: 2013-09-25
	 * @author：liuliping
	 * @description：查找被推荐的服务机构并按按服务分组
	 */
	public Map<String, List<Module>> findEnterprisesRecommended();
}
