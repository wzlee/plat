package com.eaglec.plat.dao.cms;


import com.eaglec.plat.dao.BaseDao;
import com.eaglec.plat.domain.cms.Channel;

/**
 * @date: 2013-8-14
 * @author：lwch
 * @description：频道管理借口层
 */
public interface ChannelDao extends BaseDao<Channel>{
	/**
	 * @date: 2013-8-21
	 * @author：lwch
	 * @description：获取某个类别下面频道最大的索引值
	 */
	public int getMaxCindex(String pid);
	
	/**
	 * @date: 2013-8-23
	 * @author：lwch
	 * @description：频道排序
	 */
	public void updateSeqencing(int cid, int cindex, int oid, int oindex);
}
