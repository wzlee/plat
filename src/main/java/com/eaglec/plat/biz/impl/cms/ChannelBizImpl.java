package com.eaglec.plat.biz.impl.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.cms.ChannelBiz;
import com.eaglec.plat.dao.cms.ChannelDao;
import com.eaglec.plat.dao.cms.ModuelDao;
import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.cms.Module;

@Service
public class ChannelBizImpl implements ChannelBiz {

	@Autowired
	private ChannelDao channelDao;

	@Autowired
	private ModuelDao moduelDao;

	@Override
	public List<Channel> findAll() {
		return channelDao.findList("from Channel where pid is null");
	}

	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：根据频道类别，获取该类别下所有的频道
	 */
	@Override
	public List<Channel> findChnnelByCtype(int id) {
		return channelDao.findList("from Channel where pid = '" + id + "' and isChannel = 1 order by cindex");
	}

	@Override
	public int add(Channel c) {
		if (c.getPid() == 0) {
			c.setPid(null);
			c.setCindex(null);
			c.setLeaf(true);
		} else {
			int cindex = channelDao.getMaxCindex(c.getPid().toString()) + 1;
			c.setCindex(cindex);
			c.setCcode(c.getCcode() + "-" + cindex);
			if (cindex == 1) {
				channelDao.executeSql("update channel set leaf = 0 where id = '" + c.getPid() + "'");
			}
		}
		return channelDao.add(c);
	}

	@Override
	public void delete(Channel c) {
		channelDao.delete(c);
		if (c.getLeaf()) {
			channelDao.executeSql("update channel set leaf = 1 where id = '" + c.getPid() + "'");
		}
	}

	@Override
	public void update(Channel c) {
		if (c.getPid() == 0) {
			c.setPid(null);
		}
		channelDao.update(c);
	}

	@Override
	public void updateSeqencing(int cid, int cindex, int oid, int oindex) {
		channelDao.updateSeqencing(cid, cindex, oid, oindex);
	}

	@Override
	public Channel findById(int id) {
		return channelDao.get(id);
	}

	@Override
	public List<Module> findModelByMallId(Integer mallId) {
		String findChannelHql = "from Channel where chttp like '%mall/index?mallId=" + mallId + "'";
		Channel channel = channelDao.get(findChannelHql);
		String findModelHql = "from Module where mchannel='" + channel.getCcode() + "'";

		return moduelDao.findList(findModelHql);
	}

	@Override
	public Channel findByName(String mallName) {
		String hql = "from Channel where cname ='" + mallName + "'";
		return channelDao.get(hql);
	}
}
