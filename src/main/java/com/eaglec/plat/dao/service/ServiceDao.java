package com.eaglec.plat.dao.service;

import java.util.List;
import java.util.Map;
import com.eaglec.plat.dao.BaseDao;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.ServiceAndApproval;
import com.eaglec.plat.view.ServiceView;

/**
 * 服务Dao层
 * 提供对服务的数据操作
 * <br/>
 * 注：该层已继承了BaseDao，所有基本方法不须再写
 * @author Xiadi
 * @since 2013-8-12
 * 
 */
public interface ServiceDao extends BaseDao<Service>{
	public JSONData<ServiceView> findObjects(String hql, int start, int limit);
	
	/**
	 * 前台分页输出jsonrows
	 * @author pangyf
	 * @since 2013-9-24 
	 *
	 * @param hql
	 * @param start
	 * @param limit
	 * @return
	 */
	public JSONRows<ServiceAndApproval> outJSONRow(String hql, int page, int rows);
	
	/**
	 * 根绝hql语句查询Service返回ServiceView
	 */
	public List<ServiceView> findServiceByEid(String hql, int start, int limit);
	
	/**
	 * 分页并按条件查询指定服务机构的相关服务
	 * @author Xiadi
	 * @since 2013-9-18 
	 *
	 * @param hql
	 * @param map
	 * @param start
	 * @param limit
	 * @return
	 */
	public JSONData<ServiceView> findOrgServiceList(String hql, Map<String, Object> map, int start, int limit);
	
	/**
	 * 根据服务编号查找服务
	 * @author pangyf
	 * @since 2013-10-29
	 * @param sno
	 * @return
	 */
	public Service findServiceBySno(String sno);
	
	/**
	 * @date: 2013-11-11
	 * @author：lwch
	 * @description：根据系统中的服务ID去查找该服务属于本企业中那个客服组负责资讯
	 */
	public Map<String, Object> getChatGroupIDByServiceID(Integer sid);
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @description：根据服务ID查询该服务和在线客服组之间的关联
	 */
	public List<?> getServerAndChatGroupRelate(Integer sid);
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @description：添加服务后，将服务和在线客服的某个客服组关联起来
	 */
	public void addServiceAndChatGroupRelate(Integer eid, Integer sid, Integer chatgroupid);
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @description：修改服务与在线客服组的关联ID
	 */
	public void updateServerAndChatGroupRelate(Integer id, Integer chatgroupid);
	
	/**
	 * @date: 2013-12-5
	 * @author：lwch
	 * @description：删除服务和在线客服之间的关联
	 */
	public void deleteServerAndChatUserRelate(Integer id);

}