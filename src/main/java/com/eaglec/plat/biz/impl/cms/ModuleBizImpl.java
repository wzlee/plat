package com.eaglec.plat.biz.impl.cms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.cms.ModuleBiz;
import com.eaglec.plat.dao.cms.ChannelDao;
import com.eaglec.plat.dao.cms.ModuelDao;
import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.cms.Module;
import com.eaglec.plat.utils.Constant;

@Service
public class ModuleBizImpl implements ModuleBiz {

	@Autowired
	private ModuelDao moduelDao;

	@Autowired
	private ChannelDao channelDao;

	@Override
	public List<Module> findAll(String mchannel) {
		return moduelDao.findList("from Module where mchannel='" + mchannel + "' order by mposition");
	}

	public List<Module> findByMchannel(String mchannel) {
		return moduelDao.findList("from Module where mchannel like '%" + mchannel + "%'");
	}

	@Override
	public List<?> findAllTemplate() {
		moduelDao.findList("");
		return null;
	}

	@Override
	public int add(Module m) {
		return moduelDao.add(m);
	}

	@Override
	public void delete(Module m) {
		moduelDao.delete(m);
		List<Channel> channel = channelDao.executeHql("from Channel where ccode='"+ m.getMchannel() +"'");
		if (channel.size() > 0) {
			Channel cMap = channel.get(0);
			Integer mnumber = cMap.getMnumber();
			if (mnumber > 0) {
				mnumber = mnumber - 1;
				cMap.setMnumber(mnumber);
				channelDao.update(cMap);
			}
		}
		//给大于自己的其他模块的索引值减一
		List<Module> mList = moduelDao.findList("from Module where mchannel='"+ m.getMchannel() +"' and mposition>'"+ m.getMposition() +"'");
		for (int i = 0; i < mList.size(); i++) {
			Module module = mList.get(i);
			module.setMposition(module.getMposition() - 1);
			moduelDao.update(module);
		}
	}
	
	@Override
	public void deleteModule(Module m) {
		moduelDao.delete(m);
	}

	@Override
	public void update(Module m) {
		moduelDao.update(m);
	}

	/**
	 * @date: 2013-09-25
	 * @author：liuliping
	 * @description：查找被推荐的服务机构并按按服务分组
	 * @return Map<String, List<Module>> 返回按照服务分类的服务机构;key是频道名称;value是模块链表
	 */
	public Map<String, List<Module>> findEnterprisesRecommended() {
		Map<String, List<Module>> sortedModules = new HashMap<String, List<Module>>(); // 声明返回的结果

		// 1.找到服务机构定制的频道编码
		String hql = "FROM Channel c WHERE c.pid = (SELECT id FROM Channel WHERE cname='"
				+ Constant.CHANNELName_RECOMMEND_ENTERPRISE + "')" ;
		List<Channel> channelList = channelDao.findList(hql); // "服务机构定制"
		StringBuilder ccodes = new StringBuilder();
		Map<String, String> channelMap = new HashMap<String, String>();    //key是频道编码,value是频道名称
		for (Iterator<Channel> it = channelList.iterator(); it.hasNext();) {
			Channel channel = it.next();
			ccodes.append("'").append(channel.getCcode()).append("'")
					.append(",");
			channelMap.put(channel.getCcode(), channel.getCname());
		}
		int length = ccodes.length();
		ccodes.delete(length - 1, length);

		// 2.通过频道编码找到模块
		List<Module> modules = moduelDao
				.findList("FROM Module WHERE mchannel in(" + ccodes.toString()
						+ ")");

		// 3.对模块进行分组和排序
		for (Iterator<Module> it = modules.iterator(); it.hasNext();) {
			Module module = it.next();
			String channelName = channelMap.get(module.getMchannel());
			if (sortedModules.containsKey(channelName)) { // 如果key已经存在,就把module放入List中去
				List<Module> list = sortedModules.get(channelName);
				list.add(module);
			} else {
				List<Module> list = new ArrayList<Module>();
				list.add(module);
				sortedModules.put(channelName, list);
			}
		}
		Collection<List<Module>> collection = sortedModules.values(); // 所有的模块列表
		Comparator<Module> comparator = new Comparator<Module>() {
			public int compare(Module arg0, Module arg1) {    //重载copare方法
				return arg0.getMposition().compareTo(arg1.getMposition());    //按位置来比较
			}
		};
		for (Iterator<List<Module>> it = collection.iterator(); it.hasNext();) {
			List<Module> list = it.next();
			Collections.sort(list, comparator);
		}

		return sortedModules;
	}
}
