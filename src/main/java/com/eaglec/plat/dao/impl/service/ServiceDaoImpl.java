package com.eaglec.plat.dao.impl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.service.ServiceDao;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.ServiceAndApproval;
import com.eaglec.plat.view.ServiceView;

@Repository
public class ServiceDaoImpl extends BaseDaoImpl<Service> implements ServiceDao {
	
	public JSONData<ServiceView> findObjects(String hql, int start, int limit) {
		int total = 0;
		JSONData<ServiceView> jd = new JSONData<ServiceView>();
		Query query = getCurrentSession().createQuery(hql);
		total = query.list().size();
		
		@SuppressWarnings("unchecked")
		List<ServiceView> list = query.setFirstResult(start).setMaxResults(limit).list();
		jd.setData(list);
		jd.setTotal(total);
		return jd;
	}

	public List<ServiceView> findServiceByEid(String hql, int start, int limit) {
		Query query = getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<ServiceView> list = query.setFirstResult(start).setMaxResults(limit).list();
		return list;
	}

	@Override
	public JSONData<ServiceView> findOrgServiceList(String hql, Map<String, Object> params, int start, int limit) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		int total = query.list().size();
		@SuppressWarnings("unchecked")
		List<ServiceView> list = query.setFirstResult(start).setMaxResults(limit).list();
		return new JSONData<ServiceView>(true,list,total);
	}
	
	@SuppressWarnings("unchecked")
	public JSONRows<ServiceAndApproval> outJSONRow(String hql, int page, int rows){
		
		Query query = getCurrentSession().createQuery(hql);
		int total = query.list().size();
		List<ServiceAndApproval> list = query.setFirstResult(page).setMaxResults(rows).list();
		return new JSONRows<ServiceAndApproval>(true,list,total);
	}

	@Override
	public Service findServiceBySno(String sno) {
		String hql = "from Service where serviceNo = '" + sno + "'";		
		return this.get(hql);
	}
	
	/**
	 * @date: 2013-11-11
	 * @author：lwch
	 * @description：根据系统中的服务ID去查找该服务属于本企业中那个客服组负责资讯
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getChatGroupIDByServiceID(Integer sid){
		String sql = "select * from service_chatgroup where serviceid=" + sid;
		Map<String, Object> map = new HashMap<String, Object>();
		List<?> list = super.executeSqlQueryMapList(sql);
		if (list.size() > 0) {
			map = (Map<String, Object>) list.get(0);
		}
		return map;
	}
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @description：根据服务ID查询该服务和在线客服组之间的关联
	 */
	public List<?> getServerAndChatGroupRelate(Integer sid){
		String sql = "select * from `service_chatgroup` where `serviceid`='"+ sid +"'";
		return super.executeSqlQueryMapList(sql);
	}
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @description：添加服务后，将服务和在线客服的某个客服组关联起来
	 */
	@Override
	public void addServiceAndChatGroupRelate(Integer eid, Integer sid, Integer chatgroupid){
		String sql = "insert into `service_chatgroup` (`eid`, `serviceid`, `chatgroupid`) values ('"+ eid +"', '"+ sid +"', '"+ chatgroupid +"')";
		super.executeSql(sql);
	}
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @description：修改服务与在线客服组的关联ID
	 */
	@Override
	public void updateServerAndChatGroupRelate(Integer id, Integer chatgroupid){
		String sql = "update `service_chatgroup` set `chatgroupid`='"+ chatgroupid +"' where `id`='"+ id +"'";
		super.executeSql(sql);
	}
	
	/**
	 * @date: 2013-12-5
	 * @author：lwch
	 * @description：删除服务和在线客服之间的关联
	 */
	@Override
	public void deleteServerAndChatUserRelate(Integer id){
		String sql = "delete from `service_chatgroup` where `id`='"+ id +"'";
		super.executeSql(sql);
	}
	
	@Override
	public int add(Service o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.add(o);
	}
	
	@Override
	public Service save(Service o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o);
	}
	
	@Override
	public Map<String, Object> save(Service o, Object obj) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o, obj);
	}
	
	@Override
	public Service saveOrUpdate(Service o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.saveOrUpdate(o);
	}
	
	@Override
	public Service update(Service o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.update(o);
	}
}


