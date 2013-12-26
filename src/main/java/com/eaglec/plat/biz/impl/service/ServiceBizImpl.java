package com.eaglec.plat.biz.impl.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.eaglec.plat.biz.service.MyFavoritesBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.dao.mall.MallCategoryDao;
import com.eaglec.plat.dao.publik.CategoryDao;
import com.eaglec.plat.dao.publik.FileManagerDao;
import com.eaglec.plat.dao.service.ServiceDao;
import com.eaglec.plat.dao.service.ServiceDetailDao;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.FileManager;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.mall.MallCategory;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.domain.service.ServiceDetail;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.RandomUtils;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.MallParam;
import com.eaglec.plat.view.ServiceAndApproval;
import com.eaglec.plat.view.ServiceParam;
import com.eaglec.plat.view.ServiceView;

@org.springframework.stereotype.Service
public class ServiceBizImpl implements ServiceBiz, ServletContextAware {

	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ServiceDetailDao serviceDetailDao;
	@Autowired
	private FileManagerDao fileManagerDaoImpl;
	@Autowired
	private MallCategoryDao mallCategoryDao;
	@Autowired
	private MyFavoritesBiz myFavoritesBiz ;

	@Override
	public Integer add(Service service) {
		/* 新增服务时,服务对象的图片要和资源文件管理FileManager建立关联,方便统一管理资源文件 */
		if (!StringUtils.isEmpty(service.getPicture())) {
			FileManager fileManager = fileManagerDaoImpl.findOne(service.getPicture());
			fileManager.setClazz("Service");
			fileManager.setStatus(true);
			fileManagerDaoImpl.update(fileManager);
		}
		Map<String, Object> objMap = serviceDao.save(service, null);
		if (objMap.size() > 0) {
//			Service _service = (Service)objMap.get("class");
//			ServiceDetail sd = new ServiceDetail(_service, Constant.SERVICE_AUDIT_NORMAL, _service.getCurrentStatus());
//			serviceDetailDao.save(sd);
			return (Integer)objMap.get("id");
		} else {
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @description：根据服务ID查询该服务和在线客服组之间的关联
	 */
	public List<?> getServerAndChatGroupRelate(Integer sid){
		return serviceDao.getServerAndChatGroupRelate(sid);
	}
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @description：添加服务后，将服务和在线客服的某个客服组关联起来
	 */
	public void addServiceAndChatGroupRelate(Integer eid, Integer sid, Integer chatgroupid){
		serviceDao.addServiceAndChatGroupRelate(eid, sid, chatgroupid);
	}
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @description：修改服务与在线客服组的关联ID
	 */
	@Override
	public void updateServerAndChatGroupRelate(Integer id, Integer chatgroupid){
		serviceDao.updateServerAndChatGroupRelate(id, chatgroupid);
	}
	
	/**
	 * @date: 2013-12-5
	 * @author：lwch
	 * @description：删除服务和在线客服之间的关联
	 */
	@Override
	public void deleteServerAndChatUserRelate(Integer id){
		serviceDao.deleteServerAndChatUserRelate(id);
	}

	@Override
	public Service findServiceById(Integer id) {
		return serviceDao.get(id);
	}

	@Override
	public Service findServiceByName(String serviceName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", serviceName);
		return serviceDao.get("from Service where serviceName=:name", map);
	}

	@Override
	public void delete(Service service) {
		serviceDao.delete(service);
		ServiceDetail sd = new ServiceDetail(service.getCurrentStatus(),
				service.getId(), service.getServiceName(),
				service.getCurrentStatus());
		serviceDetailDao.save(sd);
	}

	@Override
	public List<Service> queryServiceByStatus(String serviceStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", serviceStatus);
		return serviceDao.findList("from Service where currentStatus=:status",
				map);
	}

	// xuwf
	@Override
	public JSONData<Service> queryStatistics(String currentStatus,
			String orgName, String servicename, int start, int limit) {
		String hql = "from Service s where 1=1";
		if (!"".equals(currentStatus) && null != currentStatus) {
			hql += " and s.currentStatus in (" + currentStatus + ")";
		}
		if (!"".equals(servicename) && null != servicename) {
			hql += " and s.serviceName like '%" + servicename + "%'";
		}

		if (!"".equals(orgName) && null != orgName) {
			hql += " and s.enterprise.name like '%" + orgName + "%'";
		}
		hql += " order by s.registerTime desc";
		return serviceDao.outJSONData(hql, start, limit);
	}

	// xuwf
	@Override
	public JSONData<Service> queryByCatId(String sname, Integer catId,
			int start, int limit) {
		String hql = "from Service s where s.category.clazz = 'service'";
		if (!"".equals(sname.trim()) && null != sname) {
			hql += " and s.serviceName like '%" + sname + "%'";
		}
		if (catId != 1 && null != catId) {// 等于1代表所有类型
			Category category = categoryDao.get(catId); // 根据id得到该类别对象
			if (category.getLeaf() == false && category.getPid() != null) {
				hql += " and (s.category.pid = " + catId
						+ " or s.category.id = " + catId + ")";
			} else {
				hql += " and s.category.id = " + catId;
			}
		}

		hql += " order by s.registerTime desc";
		return serviceDao.outJSONData(hql, start, limit);
	}

	@Override
	public List<Service> findServiceListByStatus(String queryStatus, int start,
			int limit) {
		// begin 2013-8-12 by huyj
		String hql = "from Service where currentStatus in (" + queryStatus
				+ ")";
		return serviceDao.findList(hql, start, limit);
		// end 2013-8-12 by huyj
	}

	@Override
	public List<Service> findServiceListByStatus(String queryStatus, int start,
			int limit, String column) {
		String hql = "from Service where currentStatus in (" + queryStatus
				+ ") order by " + column + " desc";
		return serviceDao.findList(hql, start, limit);
	}

	@Override
	public List<Service> findServiceListByName(String serviceName, int limit,
			int start) {
		// begin 2013-8-12 by huyj
		String hql = "from Service where serviceName like '%" + serviceName
				+ "%'";
		return serviceDao.findList(hql, start, limit);
		// end 2013-8-12 by huyj
	}

	@Override
	public List<Service> findAll() {
		// begin 2013-8-12 by huyj
		String hql = "from Service where 1=1 order by id";
		return serviceDao.findList(hql);
		// end 2013-8-12 by huyj
	}

	@Override
	public List<Service> findServiceListById(String id, int start, int limit) {
		// begin 2013-8-12 by huyj
		String hql = "from Service where id in (" + id + ")";
		return serviceDao.findList(hql, start, limit);
		// end 2013-8-12 by huyj
	}


	@Override
	public void updateService(Service service) {
		// begin 2013-8-12 by huyj
		serviceDao.update(service);
		// end 2013-8-12 by huyj
	}

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
	@Override
	public JSONRows<Service> findServiceList(String queryStatus, Integer eid,
			String serviceName, Integer cid, int page, int rows) {
		String hql = "from Service where currentStatus in(" + queryStatus
				+ ") and enterprise.id=" + eid;
		if (StringUtils.isNotEmpty(serviceName)) {
			hql += " and serviceName like '%" + serviceName + "%' ";
		}
		if (cid != null) {
			hql += " and category.id =" + cid;
		}
		hql += " order by registerTime desc";
		return serviceDao.outJSONRows(hql, page, rows);
	}

	@Override
	public JSONResult updateAudit(Service service, int access, String context) {
		JSONResult jr = new JSONResult(false, null);
		Integer cs = null; // 服务当前状态
		Integer ls = null; // 服务上一个状态
		ServiceDetail sd = null; // 服务流水
		try {
			// Service service = serviceDao.get(id);
			cs = service.getCurrentStatus();
			ls = service.getLastStatus();

			if (access == 0) { // 通过审核,更改为下一个服务状态
				switch (cs) {
				case Constant.SERVICE_STATUS_ADDED_AUDIT: // 上架审核中
					service.setCurrentStatus(Constant.SERVICE_STATUS_ADDED); // 已上架
					break;
				case Constant.SERVICE_STATUS_CHANGE_AUDIT: // 变更审核中
					service.setCurrentStatus(ls);
					break;
				case Constant.SERVICE_STATUS_DOWN_AUDIT: // 下架审核中
					service.setCurrentStatus(Constant.SERVICE_STATUS_DOWN); // 已下架
					break;
				default:
					break;
				}
			} else { // 未通过审核
				service.setCurrentStatus(ls); // 恢复上一个状态
			}
			service.setLastStatus(cs);
			service.setLocked(false); // 解锁编辑

			/*
			 * 当拒绝变更审核时候,将流水表数据状态更新; 反之,将流水表的数据更新到服务表中去
			 */
			if ((cs == Constant.SERVICE_STATUS_CHANGE_AUDIT) && (access == 1)) { // 拒绝变更变更审核时候
				sd = serviceDetailDao.get(service.getDetailsId());
				sd.setOperatStatus(Constant.SERVICE_AUDIT_FAIL);
				Service entity = serviceDao.get(service.getId());
				entity.setCurrentStatus(Constant.SERVICE_STATUS_ADDED);
				entity.setLastStatus(Constant.SERVICE_STATUS_CHANGE_AUDIT);
				serviceDao.update(entity);
			} else {
				serviceDao.update(service);
				sd = new ServiceDetail(service.getCurrentStatus(),
						service.getId(), service.getServiceName(),
						service.getCurrentStatus());
			}
			sd.setContext(context);
			serviceDetailDao.save(sd);
			jr.setSuccess(true);
			jr.setMessage("更新服务状态成功");
			return jr;
		} catch (Exception e) {
			jr.setMessage("更新服务状态失败!");
			e.printStackTrace();
			return jr;
		}
	}

	/**
	 * 服务审核
	 * 
	 * @author liuliping
	 * @since 2013-10-15
	 * */
	@Override
	public Map<String, Object> updateAudit2(Service service, int access, String context, String operationUser) {
		Map<String, Object> resultMap = new HashMap<String, Object>();	//返回值
		JSONResult jr = new JSONResult(false, null);
		Integer cs = null; // 服务当前状态
		Integer ls = null; // 服务上一个状态
		ServiceDetail nsd = null;
		try {
			// Service service = serviceDao.get(id);
			cs = service.getCurrentStatus();
			ls = service.getLastStatus();

			if (access == 0) { // 通过审核,更改为下一个服务状态
				switch (cs) {
				case Constant.SERVICE_STATUS_ADDED_AUDIT: // 上架审核中
					service.setCurrentStatus(Constant.SERVICE_STATUS_ADDED); // 已上架
					break;
				case Constant.SERVICE_STATUS_CHANGE_AUDIT: // 变更审核中
					service.setCurrentStatus(ls);
					break;
				case Constant.SERVICE_STATUS_DOWN_AUDIT: // 下架审核中
					service.setCurrentStatus(Constant.SERVICE_STATUS_DOWN); // 已下架
					break;
				default:
					break;
				}
				service.setLastStatus(cs);
				nsd = new ServiceDetail(Constant.SERVICE_AUDIT_PASS,
						service.getId(), service.getServiceName(),
						service.getLastStatus());
			} else { // 未通过审核
				service.setCurrentStatus(ls); // 恢复上一个状态
				service.setLastStatus(cs);
				nsd = new ServiceDetail(Constant.SERVICE_AUDIT_FAIL,
						service.getId(), service.getServiceName(),
						service.getLastStatus());
			}
			service.setLocked(false); // 解锁编辑

			/*
			 * 当拒绝变更审核时候,将流水表数据状态更新; 反之,将流水表的数据更新到服务表中去
			 */
			if ((cs == Constant.SERVICE_STATUS_CHANGE_AUDIT) && (access == 1)) { // 拒绝变更变更审核时候
				Service entity = serviceDao.get(service.getId());
				entity.setCurrentStatus(Constant.SERVICE_STATUS_ADDED);
				entity.setLastStatus(Constant.SERVICE_STATUS_CHANGE_AUDIT);
				serviceDao.update(entity);
			} else {
				serviceDao.update(service);
			}
			nsd.setBelongPlat(Common.WINDOW_ID);
			nsd.setOperationUser(operationUser);
			nsd.setUserType(Constant.LOGIN_MANAGER);
			nsd.setContext(context);
			serviceDetailDao.save(nsd);
			jr.setSuccess(true);
			jr.setMessage("更新服务状态成功");
			resultMap.put("jsonResult", jr);
			resultMap.put("serviceDetail", nsd);
			resultMap.put("success", true);
			return resultMap;
		} catch (Exception e) {
			jr.setMessage("更新服务状态失败!");
			resultMap.put("jsonResult", jr);
			resultMap.put("success", false);
			e.printStackTrace();
			return resultMap;
		}
	}

	@Override
	public Service update(Service service) {
		
		Service entity = serviceDao.findServiceBySno(service.getServiceNo());

		/*
		 * 上传的图片与已有的图片不相同时有以下几种情况 1.上传了图片，服务原本没有图片 2.上传了图片，服务原本有图片
		 * 3.未上传图片，服务原本有图片
		 */
		String picture = service.getPicture();
		String e_picture = entity.getPicture();
		if ((picture != null) && (!picture.equals(e_picture))) { // 服务的图片名和持久化对象的图片名不同时,进行更新
			if (!StringUtils.isEmpty(e_picture)) {
				FileManager fileManager = fileManagerDaoImpl.findOne(e_picture);
				if (fileManager != null) {
					File file = new File("");
					// servletContext.getRealPath(File.separator
					// + "upload" + File.separator + e_picture));
					file.delete();
					fileManagerDaoImpl.delete(fileManager);
				}
			}
			if (!StringUtils.isEmpty(picture)) { // 上传的图片不为空时，就建立与service的关联
				FileManager f = fileManagerDaoImpl.findOne(picture);
				f.setStatus(true);
				f.setClazz("Service");
				fileManagerDaoImpl.update(f);
			}
		}
		BeanUtils.copyProperties(service, entity); // 复制service的属性值到持久化对象entity	
		
		return serviceDao.update(entity);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {

	}

	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：根据类别ID查询该类别下的所有服务(前10条)
	 */
	@Override
	public List<Service> queryServiceByCategoryId(int categoryId, int start,
			int limit) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Service where category.id = ").append(categoryId)
		  .append(" and currentStatus in (")
		  .append(Constant.SERVICE_STATUS_ADDED).append(",")
		  .append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
		  .append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")")
		  .append(" and enterprise.status = ").append(Constant.ENTERPRISE_STATUS_EFFECTIVE);
		return serviceDao.findList(sb.toString(), start, limit);
	}

	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：根据类别ID查询该类别下的所有服务
	 */
	@Override
	public List<Service> queryServiceByCategoryId(int categoryId) {
		String hql = "from Service where category.id=" + categoryId
				+ " and currentStatus=" + Constant.SERVICE_STATUS_ADDED;
		return serviceDao.findList(hql);
	}

	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：获取热门服务(默认前六条)
	 */
	@Override
	public List<Service> getHotService(int start, int limit) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Service where currentStatus in (")
		  .append(Constant.SERVICE_STATUS_ADDED).append(",")
		  .append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
		  .append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")")
		  .append(" and enterprise.status = ").append(Constant.ENTERPRISE_STATUS_EFFECTIVE)
		  .append(" order by serviceNum desc");
		return serviceDao.findList(sb.toString(), start, limit);
	}

	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：获取最新服务(默认前三条)
	 */
	public List<Service> getNewService(int start, int limit) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Service where currentStatus in (")
		  .append(Constant.SERVICE_STATUS_ADDED).append(",")
		  .append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
		  .append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")")
		  .append(" and enterprise.status = ").append(Constant.ENTERPRISE_STATUS_EFFECTIVE)
		  .append(" order by registerTime desc");
		return serviceDao.findList(sb.toString(), start, limit);
	}

	/**
	 * @date: 2013-08-28
	 * @author：liuliping
	 * @description：首页 获取服务对象部分数据,默认按id降序
	 * @param start
	 *            起始
	 * @param limit
	 *            分页条数
	 * @param order
	 *            hql语句排序,例如"serviceNum DESC,"
	 * @param cid
	 *            服务分类id
	 * @return list 返回服务部分数据列表
	 */
	public JSONData<ServiceView> findServicesData(int start, int limit,
			String order, Integer cid) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT new com.eaglec.plat.view.ServiceView(s.id,s.picture,s.serviceName,s.costPrice,s.serviceProcedure,s.enterprise.id,s.enterprise.name,s.enterprise.status,s.enterprise.isApproved,s.registerTime,s.serviceNum,s.tel,s.email) FROM Service s WHERE s.currentStatus in (")
		  .append(Constant.SERVICE_STATUS_ADDED).append(",")
	      .append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
		  .append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")")
		  .append(" and s.enterprise.status = ").append(Constant.ENTERPRISE_STATUS_EFFECTIVE);
		if ((cid != null) && (cid.intValue() > 0)) {
			sb.append(" and s.category.id=").append(cid);
		}
		sb.append(" ORDER BY ").append(order).append(" s.id DESC");
		JSONData<ServiceView> jd = serviceDao.findObjects(sb.toString(), start,
				limit);
		return jd;
	}

	@Override
	public void buyService(Integer sid, Integer uid, String name,
			String telNum, String email) {

	}

	@Override
	public JSONData<Service> findServiceListByQuerytatusAndServiceName(
			String queryStatus, String orgName, String serviceName, int start,
			int limit) {
		String hql = "from Service where currentStatus in(" + queryStatus + ")";
		if (queryStatus
				.contains(Integer.toString(Constant.SERVICE_STATUS_DOWN))
				&& queryStatus.contains(Integer
						.toString(Constant.SERVICE_STATUS_ADDED_AUDIT))) {
			hql += " and lastStatus not in (" + Constant.SERVICE_STATUS_NEW
					+ ")";
		}
		if (StringUtils.isNotEmpty(serviceName)) {
			hql += " and serviceName like '%" + serviceName + "%'";
		}
		if (StringUtils.isNotEmpty(orgName)) {
			hql += " and enterprise.name like '%" + orgName + "%'";
		}
		hql += "order by registerTime DESC";
		return serviceDao.outJSONData(hql, start, limit);
	}

	/**
	 * 查找待审核的服务
	 * */
	public JSONData<Service> findServiceAudit(String queryStatus,
			String orgName, String serviceName, int start, int limit) {
		/* 第一步，查询出待审核的服务 */
		StringBuilder hql4Service = new StringBuilder();
		hql4Service.append("FROM Service WHERE currentStatus in(")
				.append(queryStatus).append(")");
		if (!StringUtils.isEmpty(serviceName)) {
			hql4Service.append(" AND serviceName like '%").append(serviceName).append("%'");
		}
		if (!StringUtils.isEmpty(orgName)) {
			hql4Service.append(" AND enterprise.name  like '%").append(orgName)
					.append("%'");
		}
		hql4Service.append(" ORDER BY id DESC");
		JSONData<Service> jd = serviceDao.outJSONData(hql4Service.toString(),
				start, limit);

		/* 第二步，查询变更审核的服务流水 */
		for (Service temp : jd.getData()) {
			if ((temp.getDetailsId() != null)
					&& (temp.getCurrentStatus().intValue() == Constant.SERVICE_STATUS_CHANGE_AUDIT)) {
				ServiceDetail detail = serviceDetailDao
						.get(temp.getDetailsId());
				if (null != detail) {
					temp.setLinkMan(detail.getLinkMan());
					temp.setTel(detail.getTel());
					temp.setEmail(detail.getEmail());
					temp.setServiceMethod(detail.getServiceMethod());
					temp.setServiceProcedure(detail.getServiceProcedure());
					temp.setChargeMethod(detail.getChargeMethod());
					temp.setCostPrice(detail.getCostPrice());
					temp.setPicture(detail.getPicture());
				}
			}
		}
		/* 第三部，输出 */
		jd.setSuccess(true);
		return jd;
	}

	/**
	 * 根据服务名称分页查询
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
	 * @return List<Service>
	 */
	public JSONData<ServiceView> queryByName(String serviceName, String order,
			int upOrDown, Integer max, Integer min, int start, int limit) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT new com.eaglec.plat.view.ServiceView(s.id,s.picture,s.serviceName,s.costPrice,s.serviceProcedure,s.enterprise.id,s.enterprise.name,s.enterprise.status,s.enterprise.isApproved,s.registerTime,s.serviceNum,s.tel,s.email,s.evaluateScore) FROM Service s WHERE s.currentStatus in (")
				.append(Constant.SERVICE_STATUS_ADDED).append(",")
				.append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
				.append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")")
				.append(" and s.enterprise.status = ").append(Constant.ENTERPRISE_STATUS_EFFECTIVE);
		if (!StringUtils.isEmpty(serviceName)) {
			sb.append(" AND s.serviceName like '%");
			sb.append(serviceName).append("%'");
		}
		if (max != null) {
			sb.append(" AND s.costPrice <= ").append(max.intValue());
		}
		if (min != null) {
			sb.append(" AND s.costPrice >= ").append(min.intValue());
		}
		sb.append(" ORDER BY s.").append(order);
		if (upOrDown == 0) {
			sb.append(" DESC");
		} else {
			sb.append(" ASC");
		}
		JSONData<ServiceView> jd = serviceDao.findObjects(sb.toString(), start,
				limit);
		return jd;
	}

	/**
	 * 
	 */
	@Override
	public Service updateUservice(Service service) {
		// 申请变更时，添加一条流水信息，记录要更改的内容
		ServiceDetail serviceDetail = new ServiceDetail(service,
				Constant.SERVICE_AUDIT_NORMAL,
				Constant.SERVICE_STATUS_CHANGE_AUDIT);
		serviceDetail = serviceDetailDao.save(serviceDetail);
		// 通过serviceid获取服务
		service = serviceDao.get(service.getId());
		// 设置服务流水标识
		service.setDetailsId(serviceDetail.getId());
		service.setLastStatus(Constant.SERVICE_STATUS_ADDED);
		service.setCurrentStatus(Constant.SERVICE_STATUS_CHANGE_AUDIT);
		return serviceDao.update(service);
	}

	@Override
	public List<ServiceView> findServiceByEid(String eid, int start, int limit) {
		String hql = " SELECT new com.eaglec.plat.view.ServiceView(s.id,s.picture,s.serviceName,s.costPrice,s.serviceProcedure,s.enterprise.id,s.enterprise.name,s.enterprise.status,s.enterprise.isApproved,s.registerTime,s.serviceNum,s.tel,s.email) from Service s where s.enterprise.id='"
				+ eid
				+ "' and s.currentStatus in ("
				+ Constant.SERVICE_STATUS_ADDED
				+ ","
				+ Constant.SERVICE_STATUS_CHANGE_AUDIT
				+ ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT
				+ ")"+" and s.enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE
				+ " order by  s.registerTime DESC";
		return serviceDao.findServiceByEid(hql, start, limit);
	}

	@Override
	public JSONData<ServiceView> findOrgService(ServiceParam param) {
		StringBuffer hql = new StringBuffer(
				"select new com.eaglec.plat.view.ServiceView(id, serviceName, registerTime,"
						+ "serviceNum, serviceProcedure, costPrice, picture, evaluateScore) "
						+ "from Service where enterprise.id = :eid and currentStatus in("
						+ Constant.SERVICE_STATUS_ADDED + ","
						+ Constant.SERVICE_STATUS_CHANGE_AUDIT + ","
						+ Constant.SERVICE_STATUS_DOWN_AUDIT + ")"
						+ " and enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eid", param.getEid());
		if (param.getBeignPrice() != null) {
			hql.append(" and costPrice >= :beignPrice");
			map.put("beignPrice", param.getBeignPrice());
		}
		if (param.getEndPrice() != null) {
			hql.append(" and costPrice <= :endPrice");
			map.put("endPrice", param.getEndPrice());
		}
		if (param.getCategoryId() != null) {
			hql.append(" and category.id = :categoryId");
			map.put("categoryId", param.getCategoryId());
		}
		if (param.getServiceName() != null && !param.getServiceName().isEmpty()) {
			hql.append(" and serviceName like :serviceName");
			map.put("serviceName", "%" + param.getServiceName() + "%");
		}
		if (param.getOrderName() != null && !param.getOrderName().isEmpty()) {
			hql.append(" order by " + param.getOrderName() + " ");
		}
		if (param.getOrderType() != null && !param.getOrderType().isEmpty()) {
			hql.append(param.getOrderType());
		}
		return serviceDao.findOrgServiceList(hql.toString(), map,
				param.getStart(), param.getLimit());
	}

	@Override
	public Long findServiceCountByEid(String eid) {
		String hql = "SELECT COUNT(*) from Service s where s.enterprise.id='"
				+ eid + "' and s.currentStatus in ("
				+ Constant.SERVICE_STATUS_ADDED + ","
				+ Constant.SERVICE_STATUS_CHANGE_AUDIT + ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT
				+ ")"+" and s.enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE
				+" order by  s.registerTime DESC";
		return serviceDao.count(hql);
	}

	@Override
	public JSONRows<ServiceAndApproval> findApprovalServiceByEid(Integer eid,
			Integer sid, int page, int rows) {
		String hql = "select new com.eaglec.plat.view.ServiceAndApproval(s.id, s.serviceName, sd.currentStatus, sd.operatStatus, sd.operatorTime, sd.context)"
				+ "from Service s,ServiceDetail sd where sd.serviceId = s.id and s.enterprise.id = "
				+ eid + " and s.id=" + sid + " order by sd.operatorTime desc";
		return serviceDao.outJSONRow(hql, page, rows);
	}

	@Override
	public void addDetail(ServiceDetail sd) {
		serviceDetailDao.add(sd);
	}

	@Override
	public List<com.eaglec.plat.domain.service.Service> loadService(Integer id) {
		String hql = "from Service where mallId = " + id;
		return serviceDao.findList(hql);
	}

	@Override
	public List<Service> getMallHotService(Integer mallId, int start, int limit) {
		String hql = "from Service where currentStatus in ("
				+ Constant.SERVICE_STATUS_ADDED
				+ ","
				+ Constant.SERVICE_STATUS_CHANGE_AUDIT
				+ ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT
				+ ")"+" and enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE//企业有效条件
				+ " and mallId in (select id from MallCategory where pid in (select id from MallCategory where pid ="
				+ mallId + ")) order by serviceNum desc";
		return serviceDao.findList(hql, start, limit);
	}

	@Override
	public List<Service> getMallNewService(Integer mallId, int start, int limit) {
		String hql = "from Service where  currentStatus in ("
				+ Constant.SERVICE_STATUS_ADDED
				+ ","
				+ Constant.SERVICE_STATUS_CHANGE_AUDIT
				+ ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT
				+ ")"+" and enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE//企业有效条件
				+" and mallId in (select id from MallCategory where pid in (select id from MallCategory where pid ="
				+ mallId + ")) order by registerTime desc";
		return serviceDao.findList(hql, start, limit);
	}

	@Override
	public List<Service> findServiceByMallId(Integer mallId, int start,
			int limit) {
		String hql = "from Service where mallId in (select id from MallCategory where pid ="
				+ mallId
				+ ") and  currentStatus in ("
				+ Constant.SERVICE_STATUS_ADDED
				+ ","
				+ Constant.SERVICE_STATUS_CHANGE_AUDIT
				+ ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT + ")"
				+ ") " + " and enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE;//企业有效条件
		return serviceDao.findList(hql, start, limit);
	}

	@Override
	public List<Service> getMallGoodService(Integer mallId, int start, int limit) {
		String hql = "from Service where mallId in (select id from MallCategory where pid in (select id from MallCategory where pid ="
				+ mallId
				+ ")) and currentStatus in ("
				+ Constant.SERVICE_STATUS_ADDED
				+ ","
				+ Constant.SERVICE_STATUS_CHANGE_AUDIT
				+ ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT
				+ ") " + " and enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE//企业有效条件
				+" order by (evaluateScore) desc";
		return serviceDao.findList(hql, start, limit);
	}

	@Override
	public List<Service> getMallRecommendService(Integer mallId, int i,
			int mallServiceLimit) {
		return null;
	}

	@Override
	public List<Service> findMallListService(MallParam mparam) {
		Integer mallId = mparam.getMallId();
		MallCategory mallCategory = mallCategoryDao.get(mallId);
		StringBuffer hql = new StringBuffer();
		hql.append("from Service where currentStatus in ("
				+ Constant.SERVICE_STATUS_ADDED + ","
				+ Constant.SERVICE_STATUS_CHANGE_AUDIT + ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT + ")"
				+ " and enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE);
		if (!mallCategory.getChildren().isEmpty()) {
			hql.append(" and mallId in(select id from MallCategory where pid="
					+ mallId + ")");
		} else {
			hql.append(" and mallId =" + mallId);
		}
		if (null != mparam.getBeginPrice()) {
			hql.append(" and costPrice >=" + mparam.getBeginPrice());
		}
		if (null != mparam.getEndPrice()) {
			hql.append(" and costPrice <=" + mparam.getEndPrice());
		}
		if (!StringUtils.isEmpty(mparam.getServiceName())) {
			hql.append(" and serviceName like '%" + mparam.getServiceName()
					+ "%'");
		}
		if (!StringUtils.isEmpty(mparam.getOrderName())
				&& !StringUtils.isEmpty(mparam.getOrderType())) {
			hql.append(" order by " + mparam.getOrderName() + " "
					+ mparam.getOrderType());
		} else if (!!StringUtils.isEmpty(mparam.getOrderName())
				&& "evaluateScore".equals("evaluateScore")) {
			hql.append(" order by (totalScore/serviceNum) DESC ");
		} else {
			hql.append(" order by serviceNum DESC");
		}

		return serviceDao.findList(hql.toString(), mparam.getStart(),
				mparam.getLimit());
	}

	@Override
	public Long findMallListServiceTotal(MallParam mparam) {
		Integer mallId = mparam.getMallId();
		MallCategory mallCategory = mallCategoryDao.get(mallId);
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from Service where currentStatus in ("
				+ Constant.SERVICE_STATUS_ADDED + ","
				+ Constant.SERVICE_STATUS_CHANGE_AUDIT + ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT + ")"
				+ " and enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE);
		if (!mallCategory.getChildren().isEmpty()) {
			hql.append(" and mallId in(select id from MallCategory where pid="
					+ mallId + ")");
		} else {
			hql.append(" and mallId =" + mallId);
		}
		if (null != mparam.getBeginPrice()) {
			hql.append(" and costPrice >=" + mparam.getBeginPrice());
		}
		if (null != mparam.getEndPrice()) {
			hql.append(" and costPrice <=" + mparam.getEndPrice());
		}
		if (!StringUtils.isEmpty(mparam.getServiceName())) {
			hql.append(" and serviceName like '%" + mparam.getServiceName()
					+ "%'");
		}
		if (!StringUtils.isEmpty(mparam.getOrderName())
				&& !StringUtils.isEmpty(mparam.getOrderType())) {
			hql.append(" order by " + mparam.getOrderName() + " "
					+ mparam.getOrderType());
		} else {
			hql.append(" order by serviceNum DESC");
		}

		return serviceDao.count(hql.toString());
	}

	@Override
	public List<Service> findMallAddService(String serviceName,
			Integer categoryId,Integer industryType) {
		StringBuffer hql = new StringBuffer("from Service where 1=1");
		if (!StringUtils.isEmpty(serviceName)) {
			hql.append(" and serviceName like '%" + serviceName + "%'");
		}
		if (null != categoryId) {
			hql.append(" and category.id=" + categoryId);
		}
		if(null != industryType && 0 != industryType.intValue()){
			hql.append(" and enterprise.industryType = "+industryType);
		}
		hql.append(" and mallId is null");
		hql.append(" and currentStatus in (" + Constant.SERVICE_STATUS_ADDED
				+ "," + Constant.SERVICE_STATUS_CHANGE_AUDIT + ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT + ")"
				+ " and enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE);
		return serviceDao.findList(hql.toString());
	}

	@Override
	public List<Service> findRecomService(String serviceName, Integer mallId) {
		StringBuffer hql = new StringBuffer("from Service where 1=1");
		if (!StringUtils.isEmpty(serviceName)) {
			hql.append(" and serviceName like '%" + serviceName + "%'");
		}
		if (null != mallId) {
			hql.append(" and mallId in( select id from MallCategory where pid ="
					+ mallId + ")");
		} else {
			hql.append(" and mallId != null");
		}
		hql.append(" and currentStatus in (" + Constant.SERVICE_STATUS_ADDED
				+ "," + Constant.SERVICE_STATUS_CHANGE_AUDIT + ","
				+ Constant.SERVICE_STATUS_DOWN_AUDIT + ")");
		return serviceDao.findList(hql.toString());
	}
	
	@Override
	public List<Service> findRecommendByUser(User user, int count, 
			int currentRecommendIndex, int limit) {
		StringBuffer hql = new StringBuffer(); // 感兴趣的sql
		hql.append("from Service where currentStatus in (")
			.append(Constant.SERVICE_STATUS_ADDED).append(",")
			.append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
			.append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")")
			.append(" and enterprise.status =").append(Constant.ENTERPRISE_STATUS_EFFECTIVE);
		
		String hql_other = hql.toString(); // 其他sql
		
		// STEP.1 感兴趣的服务提取
		StringBuffer category_sb = new StringBuffer();
		for (Category c : user.getInterestServices()) {
			category_sb.append(c.getId() + ",");
		}
		if (category_sb.toString().length() > 1) { 
			String categoryIds = category_sb.toString().substring(0, category_sb.toString().length() - 1);
			hql.append(" and category.id in (" + categoryIds + ")");
			hql_other += " and category.id not in (" + categoryIds + ")";
		}
		hql.append(" order by evaluateScore desc");
		hql_other += " order by evaluateScore desc";
		List<Service> list = serviceDao.findList(hql.toString());
		
		// 推荐次数限定次数 recommendLimit 生成
		int recommendLimit = list.size()/count;
		
		// 判断感兴趣的服务 是否大于 limit && 当前推荐次数 < 推荐次数限定次数
		List<Service> ret = new ArrayList<Service>();
		if(list.size() > limit && currentRecommendIndex < recommendLimit){
			// 取count
			Integer[] randoms_back = RandomUtils.generateNotRepeatRandomNumber(list.size(), count);
			for (int index : randoms_back) {
				ret.add(list.get(index));
			}
			return ret;
		}else if(list.size() > 0){
			// 取1
			Integer[] randoms_back = RandomUtils.generateNotRepeatRandomNumber(list.size(), 1);
			ret.add(list.get(randoms_back[0]));
		}
		
		// STEP.2 其他服务提取 
		List<Service> list_other = serviceDao.findList(hql_other);
		Integer[] randoms = RandomUtils.generateNotRepeatRandomNumber(list_other.size(), count - ret.size());
		for (int index : randoms) {
			ret.add(list_other.get(index));
		}
		return ret;
	}

	@Override
	public List<Service> findPersonalRecommendByUser(User user, int count, 
			int currentRecommendIndex, int limit) {
		StringBuffer hql = new StringBuffer(); // 感兴趣的sql
		hql.append("from Service where currentStatus in (")
			.append(Constant.SERVICE_STATUS_ADDED).append(",")
			.append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
			.append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")");
//			.append(" and enterprise.status =").append(Constant.ENTERPRISE_STATUS_EFFECTIVE);
		
		String hql_other = hql.toString(); // 其他sql
		
		// STEP.1 感兴趣的服务提取
		StringBuffer category_sb = new StringBuffer();
		for (Category c : user.getInterestServices()) {
			category_sb.append(c.getId() + ",");
		}
		if (category_sb.toString().length() > 1) { 
			String categoryIds = category_sb.toString().substring(0, category_sb.toString().length() - 1);
			hql.append(" and category.id in (" + categoryIds + ")");
			hql_other += " and category.id not in (" + categoryIds + ")";
		}
		hql.append(" order by evaluateScore desc");
		hql_other += " order by evaluateScore desc";
		List<Service> list = serviceDao.findList(hql.toString());
		
		// 推荐次数限定次数 recommendLimit 生成
		int recommendLimit = list.size()/count;
		
		// 判断感兴趣的服务 是否大于 limit && 当前推荐次数 < 推荐次数限定次数
		List<Service> ret = new ArrayList<Service>();
		if(list.size() > limit && currentRecommendIndex < recommendLimit){
			// 取count
			Integer[] randoms_back = RandomUtils.generateNotRepeatRandomNumber(list.size(), count);
			for (int index : randoms_back) {
				ret.add(list.get(index));
			}
			return ret;
		}else if(list.size() > 0){
			// 取1
			Integer[] randoms_back = RandomUtils.generateNotRepeatRandomNumber(list.size(), 1);
			ret.add(list.get(randoms_back[0]));
		}
		
		// STEP.2 其他服务提取 
		List<Service> list_other = serviceDao.findList(hql_other);
		Integer[] randoms = RandomUtils.generateNotRepeatRandomNumber(list_other.size(), count - ret.size());
		for (int index : randoms) {
			ret.add(list_other.get(index));
		}
		return ret;
	}
	
	@Override
	public Service saveOrUpdate(Service service) {
		return serviceDao.saveOrUpdate(service);
	}

	@Override
	public Service findServiceBySno(String sno) {				
		return serviceDao.findServiceBySno(sno);
	}

	@Override
	public Service addBySync(Service service) {
		Service _service = serviceDao.save(service);
		ServiceDetail sd = new ServiceDetail(_service,
				Constant.SERVICE_AUDIT_NORMAL, _service.getCurrentStatus());
		serviceDetailDao.save(sd);
		return _service;
	}

	@Override
	public List<Service> findServiceByCidAndEid(Integer categoryId,
			Integer enterpriseId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Service where category.id=").append(categoryId)
			.append(" and currentStatus <>").append(Constant.SERVICE_STATUS_DELETEED)
			.append(" and enterprise.id = ").append(enterpriseId);
		return serviceDao.findList(hql.toString());
	}
	
	/**
	 * @date: 2013-11-11
	 * @author：lwch
	 * @description：根据系统中的服务ID去查找该服务属于本企业中那个客服组负责资讯
	 */
	@Override
	public Map<String, Object> getChatGroupIDByServiceID(Integer sid){
		return serviceDao.getChatGroupIDByServiceID(sid);
	}

	@Override
	public List<Map<String, Object>> findRecommendByUser2(User user,
			int count, int currentRecommendIndex, int limit) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(limit);    //返回结果
		List<Service> services = findRecommendByUser(user, count, currentRecommendIndex, limit);    //推荐的服务
		
		//遍历服务列表,检查每个服务是否被该用户收藏,封装并返回结果
		Iterator<Service> it = services.iterator();
		while (it.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Service temp = it.next();
			map.put("collected", myFavoritesBiz.isExisted(user.getId(), temp.getId()));
			map.put("service", temp);    //检验用户是否已经收藏该服务
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> findPersonalRecommendByUser2(User user, int count,
			int currentRecommendIndex, int limit) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(limit);    //返回结果
		List<Service> services = findPersonalRecommendByUser(user, count, currentRecommendIndex, limit);    //推荐的服务
		
		//遍历服务列表,检查每个服务是否被该用户收藏,封装并返回结果
		Iterator<Service> it = services.iterator();
		while (it.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Service temp = it.next();
			map.put("collected", myFavoritesBiz.isExisted(user.getId(), temp.getId()));
			map.put("service", temp);    //检验用户是否已经收藏该服务
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> findServiceByPCid(Integer cid) {
		//1.根据大类id,找到中类
		String hql4category = "FROM Category WHERE pid = " + cid;
		List<Category> categories = categoryDao.findList(hql4category);
		//2.根据中类找到所属服务
		String hql4Service = "SELECT new com.eaglec.plat.view.ServiceView(s.id, s.category.id, s.serviceName) FROM Service s WHERE category.id IN(SELECT c.id FROM Category c WHERE c.pid = " + cid + ")";
		List<Object> serviceViews = serviceDao.findObjects(hql4Service);
		//3.封装数据格式
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			Map<String, Object> re = new HashMap<String, Object>();
			re.put("text", category.getText());
			re.put("id", category.getId());
			List<ServiceView> svs = new ArrayList<ServiceView>();
			for(int j = 0; j < serviceViews.size(); j++) {
				ServiceView sv = (ServiceView)serviceViews.get(j);
				if((sv != null) && (sv.getCid().equals(category.getId()))) {
					svs.add(sv);
					serviceViews.set(j, null);
				}	
			}
			re.put("items", svs);
			result.add(re);
		}
		return result;
	}

	@Override
	public List<Object> findServiceView(Integer id) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT NEW com.eaglec.plat.view.ServiceView(s.picture, s.serviceName, s.costPrice, s.serviceProcedure, s.enterprise.name, s.evaluateScore) FROM Service s WHERE s.id = ").
			append(id);
		return serviceDao.findObjects(sb.toString());
	}

	@Override
	public Service findServiceByUuid(String uuid) {
		return serviceDao.get("from Service where sid ='" + uuid + "'");
	}

	@Override
	public List<Service> findServiceByWX() {
		StringBuffer hql = new StringBuffer("from Service where 1=1");
		 hql.append(" and currentStatus in (")
		.append(Constant.SERVICE_STATUS_ADDED).append(",")
		.append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
		.append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")");
		hql.append(" order by registerTime desc");
		return serviceDao.findList(hql.toString(),0,4);
	}

	@Override
	public List<Service> findMoreService(String title,String eid,String status,Integer start,Integer limit) {
		StringBuffer hql = new StringBuffer("from Service where 1=1 ");
		if(!StringUtils.isEmpty(title)){
			hql.append(" AND serviceName like '%"+title+"%'");
		}
		if(!StringUtils.isEmpty(eid)){
			hql.append(" AND enterprise.id = "+eid);
		}
		if(StringUtils.equals("0", status)){
			hql.append(" AND currentStatus in(")
		.append(Constant.SERVICE_STATUS_ADDED).append(",")
		.append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
		.append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")");;
		}else if(StringUtils.equals("1", status)){
			hql.append(" AND currentStatus in (")
		.append(Constant.SERVICE_STATUS_NEW).append(",")
		.append(Constant.SERVICE_STATUS_ADDED_AUDIT).append(",")
		.append(Constant.SERVICE_STATUS_DOWN).append(",")
		.append(Constant.SERVICE_STATUS_DELETEED).append(")");
		}
		hql.append(" order by registerTime desc");
		return serviceDao.findList(hql.toString(),start,limit);
	}
}