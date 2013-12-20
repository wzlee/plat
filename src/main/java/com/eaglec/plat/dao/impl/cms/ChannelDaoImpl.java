package com.eaglec.plat.dao.impl.cms;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.cms.ChannelDao;
import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.domain.cms.Channel;

/**
 * @date: 2013-8-14
 * @author：lwch
 * @description：频道管理的业务实现层
 */
@Repository
public class ChannelDaoImpl extends BaseDaoImpl<Channel> implements ChannelDao {
	/**
	 * @date: 2013-8-21
	 * @author：lwch
	 * @description：获取某个类别下面频道最大的索引值
	 */
	@Override
	public int getMaxCindex(String pid){
		Integer cindex = (Integer)super.executeSqlQuery("select MAX(cindex) from channel where pid='"+ pid +"'");
		if(cindex != null){
			return cindex;
		} else {
			return 0;
		}
	}
	
	/**
	 * @date: 2013-8-21
	 * @author：lwch
	 * @param cid 自己的id
	 * @param cindex 自己排序的索引值
	 * @param oid 目标频道的id
	 * @param oindex 目标频道的索引值
	 * @description：频道上移下移
	 */
	@Override
	public void updateSeqencing(int cid, int cindex, int oid, int oindex){
		String sql = "call updateSeqencing('"+ cid +"', '"+ cindex +"', '"+ oid +"', '"+ oindex +"')";
		super.executeSql(sql);
	}
}


