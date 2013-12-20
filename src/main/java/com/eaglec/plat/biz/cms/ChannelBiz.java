package com.eaglec.plat.biz.cms;

import java.util.List;

import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.cms.Module;

public interface ChannelBiz {

	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：获取所有的频道
	 */
	public List<Channel> findAll();

	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：根据频道类别，获取该类别下所有的频道
	 */
	public List<Channel> findChnnelByCtype(int id);

	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：添加频道(ctypev：类别编码，数据库中未存)
	 */
	public int add(Channel c);

	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：删除频道
	 */
	public void delete(Channel c);

	/**
	 * @date: 2013-8-14
	 * @author：lwch
	 * @description：修改频道
	 */
	public void update(Channel c);

	/**
	 * @date: 2013-8-23
	 * @author：lwch
	 * @description：频道排序
	 */
	public void updateSeqencing(int cid, int cindex, int oid, int oindex);

	/**
	 * 根据id查找频道
	 * 
	 * @author huyj
	 * @sicen 2013-10-14
	 * @description TODO actionPath eg:
	 * @param id
	 * @return
	 */
	public Channel findById(int id);

	/**
	 * 根据商城id查找Banner
	 * 
	 * @author huyj
	 * @sicen 2013-10-15
	 * @param mallId
	 * @return
	 */
	public List<Module> findModelByMallId(Integer mallId);

	/**
	 * 根据频道名称获取频道，不支持模糊查询
	 * 
	 * @author huyj
	 * @sicen 2013-11-19
	 * @description TODO actionPath eg:
	 * @param mallName
	 * @return
	 */
	public Channel findByName(String mallName);
}
