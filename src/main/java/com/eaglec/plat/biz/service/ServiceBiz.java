package com.eaglec.plat.biz.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.domain.service.ServiceDetail;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.MallParam;
import com.eaglec.plat.view.ServiceAndApproval;
import com.eaglec.plat.view.ServiceParam;
import com.eaglec.plat.view.ServiceView;

/**
 * 服务Service<br/>
 * 封装对服务的相关操作
 * 
 * @author Xiadi
 * @since 2013-8-9
 * 
 */
public interface ServiceBiz {

	/**
	 * 添加服务
	 * 
	 * @author Xiadi
	 * @since 2013-8-12
	 * 
	 * @param service
	 * @return Service
	 */
	public abstract Integer add(Service service);
	public abstract Service saveOrUpdate(Service service);

	/**
	 * 申请服务
	 * 
	 * @author liuliping
	 * @since 2013-8-31
	 * 
	 * @param sid
	 *            服务id
	 * @param uid
	 *            用户id
	 * @param name
	 *            申请人姓名
	 * @param telNum
	 *            联系电话
	 * @param email
	 *            邮箱
	 * @return
	 */
	public abstract void buyService(Integer sid, Integer uid, String name,
			String telNum, String email);

	/**
	 * 保存服务(saveOrUpdate)
	 * 
	 * @author lizhiwei
	 * @since 2013-8-15
	 * 
	 * @param service
	 * @return Service
	 */
	// public abstract Service save(Service service);

	/**
	 * 保存服务(saveOrUpdate)
	 * 
	 * @author lizhiwei
	 * @since 2013-8-15
	 * 
	 * @param service
	 * @return Service
	 */
	public abstract Service update(Service service);

	/**
	 * 根据id得到服务
	 * 
	 * @author Xiadi
	 * @since 2013-8-12
	 * 
	 * @param id
	 *            服务id
	 * @return Service
	 */
	public abstract Service findServiceById(Integer id);

	/**
	 * 根据名称得到单个服务
	 * 
	 * @author Xiadi
	 * @since 2013-8-12
	 * 
	 * @param serviceName
	 * @return Service
	 */
	public abstract Service findServiceByName(String serviceName);

	/**
	 * 查询某个状态下的所有服务
	 * 
	 * @author Xiadi
	 * @since 2013-8-12
	 * 
	 * @param serviceName
	 * @return Service
	 */
	public abstract List<Service> queryServiceByStatus(String serviceStatus);

	/**
	 * 删除服务对象
	 * 
	 * @author Xiadi
	 * @since 2013-8-12
	 * 
	 * @param service
	 *            服务对象
	 */
	public abstract void delete(Service service);

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-8-12
	 * @description 根据服务状态查询服务，支持多状态查询
	 * @param serviceStatus
	 *            服务状态 使用,分割 例：01,02,03
	 */
	public abstract List<Service> findServiceListByStatus(String queryStatus,
			int start, int limit);

	/**
	 * 
	 * @author liuliping
	 * @sicen 2013-8-12
	 * @description 根据服务状态查询服务，支持多状态查询 ,并按某一列降序
	 * @param serviceStatus
	 *            服务状态 使用,分割 例：01,02,03
	 * @param column
	 *            按次列来降序查询
	 */
	public abstract List<Service> findServiceListByStatus(String queryStatus,
			int start, int limit, String column);

	/**
	 * 
	 * @author lizw
	 * @param serviceName2
	 * @sicen 2013-8-12
	 * @description 根据服务状态查询服务，支持多状态查询
	 * @param serviceStatus
	 *            服务状态 使用,分割 例：01,02,03
	 */
	public abstract JSONData<Service> findServiceListByQuerytatusAndServiceName(
			String queryStatus, String serviceName, String serviceName2,
			int start, int limit);

	/**
	 * 前台根据服务状态，企业ID，服务名称查询服务
	 * 
	 * @author pangyf
	 * @since 2013-9-6
	 * @param queryStatus
	 * @param eid
	 * @param serviceName
	 * @param page
	 * @param rows
	 * @return
	 */
	public abstract JSONRows<Service> findServiceList(String queryStatus,
			Integer eid, String serviceName, Integer cid, int page, int rows);

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-8-12
	 * @description 根据服务名称查询服务，支持模糊查询
	 * @param serviceStatus
	 *            服务名称
	 */
	public abstract List<Service> findServiceListByName(String serviceName,
			int start, int limit);

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-8-12
	 * @description 根据服务id查询服务
	 * @param id
	 *            服务名称 支持多id查询 每个id使用,分隔
	 */
	public abstract List<Service> findServiceListById(String id, int start,
			int limit);

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-8-12
	 * @description 查找所有服务
	 * @return 所有服务
	 */
	public List<Service> findAll();

	/**
	 * 
	 * @author lizhiwei
	 * @sicen 2013-8-15
	 * @description 添加服务
	 * @param service
	 *            服务对象
	 */
	// public void saveService(Service service);

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-8-12
	 * @description 修改服务信息及状态
	 * @param service
	 *            服务对象
	 */
	public void updateService(Service service);

	/**
	 * 根据服务状态和服务名字分页查询
	 * 
	 * @author xuwf
	 * @since 2013-8-15
	 * 
	 * @param serviceStatus
	 *            服务状态
	 * @param servicename
	 *            服务名称 支持模糊查询
	 * @param sname
	 * @param start
	 *            起始
	 * @param limit
	 *            限制
	 * @return JSONData<Service> service列表 JSON格式
	 */
	public abstract JSONData<Service> queryStatistics(String currentStatus,
			String servicename, String sname, int start, int limit);

	/**
	 * 根据服务类别和服务名字查询
	 * 
	 * @author xuwf
	 * @since 2013-8-19
	 * 
	 * @param sname
	 *            服务名称 支持模糊查询
	 * @param catId
	 *            所属类别id
	 * @param start
	 *            起始
	 * @param limit
	 *            限制
	 * @return JSONData<Service> service列表 JSON格式
	 */
	public abstract JSONData<Service> queryByCatId(String sname, Integer catId,
			int start, int limit);

	/**
	 * 审核服务
	 * 
	 * @author liuliping
	 * @since 2013-8-12
	 * 
	 * @param service
	 *            服务对象
	 * @param access
	 *            服务审核通过:0,未通过:1
	 * @param context 
	 * @return JSONResult 审核结果返回信息
	 */
	public abstract JSONResult updateAudit(Service service, int access, String context);

	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @param categoryId
	 *            类别ID
	 * @description：根据类别ID查询该类别下的所有服务(前10条)
	 */
	public abstract List<Service> queryServiceByCategoryId(int categoryId,
			int start, int limit);

	/**
	 * 根据服务名称查询
	 * 
	 * @author liuliping
	 * @since 2013-8-12
	 * 
	 * @param serviceName
	 *            服务名称
	 * @param order
	 *            查询结果按此降序
	 * @param upOrDown
	 *            查询的结果升序或者降序(0:降序, 1:升序, 默认0)
	 * @param start
	 *            分页起始
	 * @param limit
	 *            每页结果数量
	 * @return JSONData<ServiceView>
	 */
	public abstract JSONData<ServiceView> queryByName(String serviceName,
			String order, int upOrDown, Integer max, Integer min, int start,
			int limit);

	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @param categoryId
	 *            类别ID
	 * @description：根据类别ID查询该类别下的所有服务
	 */
	public abstract List<Service> queryServiceByCategoryId(int categoryId);

	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：获取热门服务(默认前六条)
	 */
	public abstract List<Service> getHotService(int start, int limit);

	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：获取最新服务(默认前三条)
	 */
	public abstract List<Service> getNewService(int start, int limit);

	/**
	 * @date: 2013-08-28
	 * @author：liuliping
	 * @description： 获取服务对象部分数据,默认按id降序
	 * @param start
	 *            起始
	 * @param limit
	 *            分页条数
	 * @param order
	 *            hql语句排序,例如"serviceNum DESC,"
	 * @param cid
	 *            服务分类id
	 * @return JSONData
	 */
	public abstract JSONData<ServiceView> findServicesData(int start,
			int limit, String order, Integer cid);

	/**
	 * 根据企业id获取该企业下的服务
	 * 
	 * @author huyj
	 * @sicen 2013-9-17
	 * @description 用户服务机构 主页显示 一页显示9条记录
	 * @param eid
	 *            企业id
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract List<ServiceView> findServiceByEid(String eid, int start,
			int limit);

	/**
	 * 分页并按条件查询指定服务机构的相关服务
	 * 
	 * @author Xiadi
	 * @since 2013-9-17
	 * 
	 * @param param
	 *            ServiceParam 对象
	 * @return JSONData<ServiceView>
	 */
	public abstract JSONData<ServiceView> findOrgService(ServiceParam param);

	void setServletContext(ServletContext servletContext);

	public abstract Long findServiceCountByEid(String eid);

	public abstract JSONRows<ServiceAndApproval> findApprovalServiceByEid(
			Integer eid, Integer sid, int page, int rows);

	/**
	 * 添加流水
	 * 
	 * @author pangyf
	 * @since 2013-9-24
	 * 
	 * @param sd
	 *            ServiceDetail对象
	 * @return
	 */
	public abstract void addDetail(ServiceDetail sd);

	/**
	 * @author huyj
	 * @since 2013-9-25 根据服务商城类别id获取服务
	 */
	public abstract List<Service> loadService(Integer id);

	/**
	 * 获取服务商城热门服务(默认前4条)
	 * 
	 * @author huyj
	 * @sicen 2013-9-24
	 * @param mallId
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract List<Service> getMallHotService(Integer mallId, int start,
			int limit);

	/**
	 * 获服务商城最新服务(默认前4条)
	 * 
	 * @author huyj
	 * @sicen 2013-9-24
	 * @param mallId
	 *            服务商城类别一级类别
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract List<Service> getMallNewService(Integer mallId, int start,
			int limit);

	/**
	 * 根据服务商城id加载服务
	 * 
	 * @author huyj
	 * @sicen 2013-9-25
	 * @param mallId
	 *            服务商城类别一级类别id
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract List<Service> findServiceByMallId(Integer mallId,
			int start, int limit);

	/**
	 * 获取服务商城好评服务(默认前四条)
	 * 
	 * @author huyj
	 * @sicen 2013-9-25
	 * @param mallId
	 *            服务商城类别一级类别id
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract List<Service> getMallGoodService(Integer mallId, int start,
			int limit);

	/**
	 * 获取服务商城推荐服务(默认前四条)
	 * 
	 * @author huyj
	 * @sicen 2013-9-26
	 * @description TODO actionPath eg:
	 * @param mallId
	 * @param start
	 * @param limti
	 * @return
	 */
	public abstract List<Service> getMallRecommendService(Integer mallId,
			int start, int mallServiceLimit);

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-9-14
	 * @description 已上架服务，申请变更
	 * @param service
	 *            服务对象
	 * @return
	 */
	public abstract Service updateUservice(Service service);

	public abstract JSONData<Service> findServiceAudit(String queryStatus,
			String serviceName, String orgName, int start, int limit);

	/**
	 * 服务商城列表页加载服务列表
	 * 
	 * @author huyj
	 * @sicen 2013-9-27
	 * @param mallId
	 * @param paramName
	 * @param method
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract List<Service> findMallListService(MallParam param);

	/**
	 * 服务商城列表页加载服务列表总数
	 * 
	 * @author huyj
	 * @sicen 2013-9-27
	 * @param mallId
	 * @param paramName
	 * @param method
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract Long findMallListServiceTotal(MallParam mparam);

	/**
	 * 查找可以添加到服务商城的服务列表
	 * 
	 * @author huyj
	 * @sicen 2013-10-11
	 * @description TODO actionPath eg:
	 * @param serviceName
	 * @param categoryId
	 * @return
	 */
	public abstract List<Service> findMallAddService(String serviceName,
			Integer categoryId,Integer industryType);

	public List<Service> findRecomService(String serviceName, Integer categoryId);

	Map<String, Object> updateAudit2(Service service, int access, String context,  String operationUser);
	
	/**
	 * 查找推荐服务
	 * <br/>若 感兴趣的服务数量为 servicenum , 推荐次数限定为  recommendLimit = (int)servicenum/count
	 * <br/> 如果 servicenum > limit && currentRecommendIndex < recommendLimit 在感兴趣的服务里随机
	 * <br/> 如果 servicenum > limit && currentRecommendIndex > recommendLimit 在感兴趣的服务里随机1个+其他服务随机2个
	 * <br/> 如果 servicenum < limit 在感兴趣的服务里随机1个+其他服务随机2个
	 * @author Xiadi
	 * @since 2013-10-17 
	 *
	 * @param user 推荐的用户
	 * @param count 推荐数量
	 * @param currentRecommendIndex 当前推荐次数 
	 * @param limit 感兴趣的服务限定数量
	 * @return
	 */
	public abstract List<Service> findRecommendByUser(User user, int count, 
			int currentRecommendIndex, int limit);
	
	/**
	 * 查找推荐服务,返回的每一个结果均获知用户是否已经收藏该服务;
	 * @author liuliping
	 * @since 2013-11-13
	 * @param user 推荐的用户
	 * @param count 推荐数量
	 * @param currentRecommendIndex 当前推荐次数 
	 * @param limit 感兴趣的服务限定数量
	 * @return 包含用户收藏状态的服务链表
	 * */
	public abstract List<Map<String, Object>> findRecommendByUser2(User user, int count, 
			int currentRecommendIndex, int limit);
	
	/**
	 * 查找推荐服务(个人用户user使用),返回的每一个结果均获知用户是否已经收藏该服务;
	 * <br/>若 感兴趣的服务数量为 servicenum , 推荐次数限定为  recommendLimit = (int)servicenum/count
	 * <br/> 如果 servicenum > limit && currentRecommendIndex < recommendLimit 在感兴趣的服务里随机
	 * <br/> 如果 servicenum > limit && currentRecommendIndex > recommendLimit 在感兴趣的服务里随机1个+其他服务随机2个
	 * <br/> 如果 servicenum < limit 在感兴趣的服务里随机1个+其他服务随机2个
	 * @author xuwf
	 * @since 2013-11-02 
	 *
	 * @param user 推荐的用户
	 * @param count 推荐数量
	 * @param currentRecommendIndex 当前推荐次数 
	 * @param limit 感兴趣的服务限定数量
	 * @return 包含用户收藏状态的服务链表
	 */
	public abstract List<Map<String, Object>> findPersonalRecommendByUser2(User user, int count, 
			int currentRecommendIndex, int limit);
	
	
	/**
	 * 查找推荐服务(个人用户user使用)
	 * <br/>若 感兴趣的服务数量为 servicenum , 推荐次数限定为  recommendLimit = (int)servicenum/count
	 * <br/> 如果 servicenum > limit && currentRecommendIndex < recommendLimit 在感兴趣的服务里随机
	 * <br/> 如果 servicenum > limit && currentRecommendIndex > recommendLimit 在感兴趣的服务里随机1个+其他服务随机2个
	 * <br/> 如果 servicenum < limit 在感兴趣的服务里随机1个+其他服务随机2个
	 * @author xuwf
	 * @since 2013-11-02 
	 *
	 * @param user 推荐的用户
	 * @param count 推荐数量
	 * @param currentRecommendIndex 当前推荐次数 
	 * @param limit 感兴趣的服务限定数量
	 * @return
	 */
	public abstract List<Service> findPersonalRecommendByUser(User user, int count, 
			int currentRecommendIndex, int limit);
	
	/**
	 * 根据服务编码查找服务
	 * @author pangyf
	 * @since 2013-10-29
	 * @param sno
	 * @return
	 */
	public abstract Service findServiceBySno(String sno);
	
	/**
	 * 同步添加窗口服务
	 * @author Xiadi
	 * @since 2013-10-31 
	 *
	 * @param service
	 */
	public abstract Service addBySync(Service service);
	
	/**
	 * 根据 <b>类别id</b> 和 <b>企业id</b> 查询 <b>未删除</b> 的服务列表
	 * @author Xiadi
	 * @since 2013-11-2 
	 *
	 * @param categoryId
	 * @param enterpriseId
	 * @return
	 */
	public abstract List<Service> findServiceByCidAndEid(Integer categoryId, Integer enterpriseId);
	
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
	
	/**
	 * @date: 2013-11-26
	 * @author：liuliping
	 * @description：通过服务大类cid来获取服务列表并按中类分组
	 */
	List<Map<String, Object>> findServiceByPCid(Integer cid);
	
	/**
	 * @author liuliping
	 * @since 2013-11-27
	 * @description: 通过服务id获取服务详情部分属性
	 * */
	List<Object> findServiceView(Integer id);
	
	public abstract Service findServiceByUuid(String uuid);
	/**
	 * 获取微信首页加载服务
	 * @author huyj
	 * @sicen 2013-12-18
	 * @return
	 */
	public abstract List<Service> findServiceByWX();
	/**
	 * 微信用户中心，我的服务加载更多
	 * @author huyj
	 * @sicen 2013-12-24
	 * @param title 服务名称
	 * @param eid 企业id
	 * @param status 
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract List<Service> findMoreService(String title,String eid,String status,Integer start,Integer limit);
}