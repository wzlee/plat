package com.eaglec.plat.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.chatonline.ChatOnlineBiz;
import com.eaglec.plat.biz.cms.ChannelBiz;
import com.eaglec.plat.biz.mall.MallCategoryBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.service.MyFavoritesBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.service.ServiceDetailBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.mall.MallCategory;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.domain.service.ServiceDetail;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.bean.ServiceDetailSyncBean;
import com.eaglec.plat.sync.bean.ServiceSyncBean;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.Dao;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.ServiceView;

@Controller
@RequestMapping(value = "/service")
public class ServiceController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	@Autowired
	private ServiceBiz serviceBiz;
	@Autowired
	private CategoryBiz categoryBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private MallCategoryBiz mallCategoryBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private MyFavoritesBiz myFavoritesBiz;
	@Autowired
	private ChatOnlineBiz chatOnlineBiz;
	@Autowired
	private Dao dao;
	@Autowired
	ServiceDetailBiz serviceDetailBiz;
	@Autowired
	private GoodsOrderController goodsOrderController;
	
	/**
	 * 为运营平台自己添加服务
	 *@author lizhiwei
	 *@since 2013-8-20 
	 *
	 *@param service
	 *@return 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/save")
	public void add(Service service,HttpServletRequest request,HttpServletResponse response) {
		try {
//			//得到运营支撑系统的工商注册号
//			String icRegNumber = Common.icRegNumber;	
//			service.setServiceNo(service.getCategory().getCode()+"-"+Long.toString(new Date().getTime()));
//			List<Enterprise> list = enterpriseBiz.findEnterpriseByIcRegNumber(icRegNumber);			
//			Enterprise enterprise = list.get(0);
//			service.setEnterprise(enterprise);
//			serviceBiz.add(service);
//			//serviceBiz.saveService(service);
//			this.outJson(response,new JSONResult(true,"服务添加成功!"));
//			logger.info("[ "+service.getServiceName()+" ]添加成功!");
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"服务保存失败!异常信息:"+e.getLocalizedMessage()));
			logger.info("服务保存失败!异常信息:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 为机构添加服务
	 *@author lizhiwei
	 *@since 2013-8-20 
	 *
	 *@param service
	 *@return 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/saveforuser")
	public void addForUser(Service service,HttpServletRequest request,HttpServletResponse response) {
		try {
			service.setServiceNo(categoryBiz.findById("service",service.getCategory().getId()).getCode()+"-"+Long.toString(new Date().getTime()));			
//			List<Enterprise> list = enterpriseBiz.findEnterpriseByIcRegNumber((service.getEnterprise()).getIcRegNumber());
			Enterprise enterprise = enterpriseBiz.findByEid(service.getEnterprise().getId());
			if(!StringUtils.isEmpty(enterprise)){
				service.setEnterprise(enterprise);
				
				//保存服务
				serviceBiz.add(service);				
				//同步服务到该窗口
				ServiceSyncBean sb = new ServiceSyncBean(serviceBiz.findServiceByUuid(service.getSid()), SyncType.ONE);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb),true);
				
				//保存流水
				ServiceDetail sd = new ServiceDetail(service, Constant.SERVICE_AUDIT_NORMAL, service.getCurrentStatus());
				sd = serviceDetailBiz.create(sd);
				//同步流水到该窗口
				ServiceDetailSyncBean _sb = new ServiceDetailSyncBean(sd, SyncType.ONE);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(_sb),false);		
				
				this.outJson(response,new JSONResult(true,"服务【"+service.getServiceName()+"】添加成功!"));
				logger.info("服务[ "+service.getServiceName()+" ]添加成功!");
			}else{
				this.outJson(response,new JSONResult(false,"数据有误！"));
			}
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"服务保存失败!异常信息:"+e.getLocalizedMessage()));
			logger.info("服务保存失败!异常信息:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 前台添加服务
	 *@author pangyf
	 *@since 2013-9-10
	 *@param action
	 *@param service
	 *@return 
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/addservice")
	public void userAdd(
			@RequestParam("action")String action,
			@RequestParam("chatgroup")Integer chatgroupid,
			Service service,
			HttpServletRequest request,HttpServletResponse response) {
		try {
			String userName;
			if(this.getCurrentUserType(request)==Constant.LOGIN_USER){
				User user = this.getUserFromSession(request);
				request.getSession().setAttribute("user",user);
				userName = user.getUserName();
			}else {
				Staff staff = this.getStaffFromSession(request);
				request.getSession().setAttribute("user",staff);
				userName = staff.getUserName();
			}
			service.setServiceSource(Constant.SERVICE_SOURCE_ORG);
			Category category = categoryBiz.findById("service", service.getCategory().getId());
			service.setCategory(category);
			service.setServiceNo(category.getCode()+"-"+Long.toString(new Date().getTime()));
			service.setEnterprise(enterpriseBiz.findByEid(service.getEnterprise().getId()));
			if(action.equals("saveandup")){
				service.setLastStatus(service.getCurrentStatus());
				service.setCurrentStatus(Constant.SERVICE_STATUS_ADDED_AUDIT);
			}
			
			//添加服务
			service  = serviceBiz.saveOrUpdate(service);		
			Integer sid = service.getId();
			//同步服务到该窗口
			ServiceSyncBean sb = new ServiceSyncBean(service, SyncType.ONE);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb),true);
			
			//添加流水
			ServiceDetail sd = new ServiceDetail(service, Constant.SERVICE_AUDIT_NORMAL, service.getCurrentStatus());
			sd.setOperationUser(userName);
			sd.setUserType(Constant.LOGIN_USER);
			sd.setBelongPlat(Common.WINDOW_ID);
			sd = serviceDetailBiz.create(sd);
			//同步流水到该窗口
			ServiceDetailSyncBean _sb = new ServiceDetailSyncBean(sd, SyncType.ONE);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(_sb),false);
			
			this.outJson(response,new JSONResult(true,"服务【" + service.getServiceName() + "】添加成功!"));									
			/**
			 * @date: 2013-11-13
			 * @author：lwch
			 * @description：添加服务后，将服务和在线客服的某个客服组关联起来
			 * ===============================================================================================
			 */
			if (sid != null) {
				try {
					if (chatgroupid != 0) {
						serviceBiz.addServiceAndChatGroupRelate(service.getEnterprise().getId(), sid, chatgroupid);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/**
			 * ===============================================================================================
			 */
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"服务保存失败!异常信息:"+e.getLocalizedMessage()));
			logger.info("服务保存失败!异常信息:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 查询服务，按状态查询
	 * 
	 * @author lizhiwei
	 * @since 2013-8-15
	 * 
	 * @param service
	 * @return
	 */
	@RequestMapping(value = "/query")
	public void query(
			@RequestParam("queryStatus")String queryStatus,
			@RequestParam(value = "orgName", defaultValue = "", required = false) String orgName,
			@RequestParam(value="serviceName",defaultValue="",required=false) String serviceName,
			@RequestParam(value="start",defaultValue="0",required=false)int start,
			@RequestParam(value="limit",defaultValue="25",required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[ queryStatus:"+queryStatus+" ]");
		this.outJson(response, serviceBiz
				.findServiceListByQuerytatusAndServiceName(queryStatus,
						orgName, serviceName, start, limit));
	}
	
	/**
	 * 前台查询服务，按状态以及企业ID查询
	 * 
	 * @author pangyf
	 * @since 2013-9-6
	 * 
	 * @param queryStatus
	 * @param serviceName
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/queryservice")
	public void queryService(
			@RequestParam("queryStatus")String queryStatus,
			@RequestParam("eid")Integer eid,
			@RequestParam(value="serviceName",defaultValue="",required=false) String serviceName,
			@RequestParam("cid")Integer cid,
			@RequestParam(value="page",required=false)int page,
			@RequestParam(value="rows",required=false)int rows,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[ queryStatus:"+queryStatus+" ]");
		if(eid != null){
			this.outJson(response, serviceBiz.findServiceList(queryStatus, eid,serviceName, cid, rows * (page - 1), rows));
		}		
	}
	
	/**
	 * 审核服务查询
	 * 
	 * @author liuliping
	 * @since 2013-09-14
	 * 
	 * @param queryStatus 查询状态
	 * @param serviceName 服务名称
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/queryaudit")
	public void queryaudit(
			@RequestParam("queryStatus") String queryStatus,
			@RequestParam(value = "orgName", defaultValue = "", required = false) String orgName,
			@RequestParam(value = "serviceName", defaultValue = "", required = false) String serviceName,
			@RequestParam(value = "start", defaultValue = "0", required = false) int start,
			@RequestParam(value = "limit", defaultValue = "25", required = false) int limit,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("queryParams[ queryStatus:" + queryStatus + " ]");
		JSONData<Service> jd = serviceBiz.findServiceAudit(queryStatus,
				orgName, serviceName, start, limit);
		outJson(response, jd);
	}

	/**
	 * 查询服务，按某列降序
	 * 
	 * @author liuliping
	 * @since 2013-08-24
	 * 
	 * @param 
	 * @return
	 * @eg
	 */
	@RequestMapping(value = "/queryByColumn")
	public void queryByColumn(
			@RequestParam("queryStatus")String queryStatus,
			@RequestParam(value="column", defaultValue="id", required=false)String column,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="12", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[ queryStatus:"+queryStatus+" ]");
		List<Service> list = serviceBiz.findServiceListByStatus(queryStatus, start, limit, column);
		outJson(response, list);
	}
	
	/**
	 * 跳转服务详情页面
	 * 
	 * @author liuliping
	 * @since 2013-08-30
	 * 
	 * @param id 服务对象的id
	 * @return
	 * @eg	
	 */
	@RequestMapping(value = "/detail")
	public String toDetail(Integer id,String op,HttpServletRequest request, HttpServletResponse response, Model model) {
		String ret = "module/service/detail";
		if ((id != null) && (id.intValue() > 0)) {
			Service service = serviceBiz.findServiceById(id);
			model.addAttribute("service", service);
			User fuser = userBiz.findUserByEnterprise(service.getEnterprise().getId());
			model.addAttribute("beattentionid",fuser.getId());
			
			/* 当用户已收藏此服务,返回已收藏状态;反之,返回为收藏状态.*/
			Object obj = request.getSession().getAttribute("user");    //session中保存的用户数据
			if (obj != null) {    //用户已登录,则检验用户是否已经收藏此服务,并返回信息
				Integer userType = (Integer)request.getSession().getAttribute("usertype");    //用户类型
				User user = null;    //主帐号
				if(userType.intValue() == Constant.LOGIN_STAFF){    //当前用户是子账号, 则获取主帐号
					Staff staff = (Staff)obj;
					user = staff.getParent();
				} else {    //主帐号
					user = (User)obj;
				}
				model.addAttribute("attentionid",user.getId());

				//如果已经被关注了则。。。。
				if(dao.count("SELECT COUNT(*) from ts_user_follow WHERE uid ="+user.getId() +" AND fid ="+fuser.getId(),null)>0){
					model.addAttribute("isattention", "true");
				}else{
					model.addAttribute("isattention", "false");
				}
				model.addAttribute("collected", myFavoritesBiz.isExisted(user.getId(), id));
			} else {
				model.addAttribute("collected", false);
			}
			
			if("mall".equals(op)){//服务商城进入服务详情页面
				//得到该服务的服务商城类别
				if(service.getMallId() != null){//关联的只是id，数据有问题时查不到，防止NullPointerException
					MallCategory mcategory = mallCategoryBiz.findById("service", service.getMallId());
					if(mcategory.getPid() != null){
						MallCategory pmcategory = mallCategoryBiz.findById("service", mcategory.getPid());
						model.addAttribute("mcategory", mcategory);
						model.addAttribute("pmcategory", pmcategory);
					}
				}
				ret =  "mall/mall_detail";
			}
			//查询该服务所属类别
			Category category = service.getCategory();
			//如果此类别还有父类，得到父类
			if(category.getPid() !=null && category.getLeaf()){
				Category pcategory = categoryBiz.findById("service", category.getPid());
				model.addAttribute("pcategory", pcategory);
			}
			model.addAttribute("category",category);
			
			if("enter".equals(op)){//服务机构频道进入服务详情页
				ret = "enterprise/enter_service_info";
			}
			/**
			 * @date: 2013-11-11
			 * @author：lwch
			 * @description：根据系统中的服务ID去查找该服务属于本企业中那个客服组负责资讯
			 * ==========================================================================================
			 */
			Map<String, Object> chatMap = serviceBiz.getChatGroupIDByServiceID(service.getId());
			if (chatMap.size() > 0) {
				//这里按照分组ID来随机获取和该组下那个用户聊天
				model.addAttribute("chatlineuser", chatMap.get("chatgroupid"));
				model.addAttribute("isRelate", true);//已关联
				
			} else {
				//如果该服务还未与客服关联，那就去在线客服系统中根据企业id和角色查找该企业的管理员，将这服务由管理员来处理
				List<Map<String, Object>> chatUserList = chatOnlineBiz.getChatUsersByEidAndRole(service.getEnterprise().getId(), Constant.CHAT_ROLE_ADMIN);
				if (chatUserList.size() > 0) {
					//这里传管理员的id
					Map<String, Object> chatUserMap = (Map<String, Object>)chatUserList.get(0);
					model.addAttribute("chatlineuser", chatUserMap.get("id"));
					model.addAttribute("isRelate", false);//未关联
				} else {
					model.addAttribute("chatlineuser", null);
				}
			}
			/**
			 * ==========================================================================================
			 */
		}
		
		return ret;
	}
	
	/**
	 * 添加关注
	 * 
	 * @author cs
	 * @since 2013-08-24
	 * 
	 * @param 
	 * @return
	 * @eg
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/addattention")
	public void addattention(
			@RequestParam("attentionid")Integer attentionid,
			@RequestParam("beattentionid")Integer beattentionid,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if(dao.count("SELECT COUNT(*) from ts_user WHERE uid ="+attentionid,null)>0&&dao.count("SELECT COUNT(*) from ts_user WHERE uid ="+beattentionid,null)>0	){
				dao.update("INSERT INTO ts_user_follow (uid,fid) VALUES("+attentionid+","+beattentionid+")");
				outJson(response, new JSONResult(true, "关注成功"));
			}else{
				outJson(response, new JSONResult(false, "关注失败"));
			}
		} catch (SQLException e) {
			outJson(response, new JSONResult(false, "关注失败"));
			e.printStackTrace();
		}
	}
	
	/**
	 * window跨域取消关注
	 * 
	 * @author cs
	 * @since 2013-08-24
	 * 
	 * @param 
	 * @return
	 * @eg
	 */
	@RequestMapping(value = "/cancelAddattentionJsonP")
	public void cancelAddattentionJsonP(
			@RequestParam("beattentionid")Integer beattentionid,
			String sm_login,
			String jsoncallback, HttpServletRequest request, HttpServletResponse response) {
		Integer attentionid;
		JSONResult jr = new JSONResult(false, null);
		if(StringUtils.isEmpty(sm_login)) {
			jr.setMessage("校验码不能为空");
			outJsonP(response, jsoncallback, jr);
			return;
		}
		// 通过解析字符串sm_login,来检验请求合法性,获取用户类型和用户对象
		Map<String, Object> map = goodsOrderController.checkLogin(sm_login);
		boolean success = (Boolean) map.get("success"); 
		if(!success) {
			jr.setMessage((String)map.get("message"));
			outJsonP(response, jsoncallback, jr);
			return;
		}
		
		//登录用户(主账号登录即操作者)
		if("user".equals(map.get("userType"))){
			User user = (User)map.get("userOrStaff");
			attentionid = user.getId();
		//子账号登录
		}else{
			Staff staff = (Staff) map.get("userOrStaff");
			attentionid = staff.getParent().getId();
		}
		
		try {
			dao.update("DELETE FROM ts_user_follow  WHERE uid ="+attentionid +" AND fid = "+beattentionid);
			jr.setSuccess(true);
			jr.setMessage("取消关注成功");
			outJsonP(response, jsoncallback, jr);
			return;
		} catch (Exception e) {
			jr.setSuccess(true);
			jr.setMessage("取消关注失败");
			outJsonP(response, jsoncallback, jr);
			jr.setMessage("取消关注失败!异常信息:" + e.getLocalizedMessage());
			return;
		} finally {
			outJsonP(response, jsoncallback, jr);
		}
	}
	
	/**
	 * window跨域添加关注
	 * 
	 * @author cs
	 * @since 2013-08-24
	 * 
	 * @param 
	 * @return
	 * @eg
	 */
	@RequestMapping(value = "/addattentionJsonP")
	public void addattentionJsonP(
			@RequestParam("beattentionid")Integer beattentionid,
			String sm_login,
			String jsoncallback, HttpServletRequest request, HttpServletResponse response) {
		Integer attentionid;
		JSONResult jr = new JSONResult(false, null);
		if(StringUtils.isEmpty(sm_login)) {
			jr.setMessage("校验码不能为空");
			outJsonP(response, jsoncallback, jr);
			return;
		}
		// 通过解析字符串sm_login,来检验请求合法性,获取用户类型和用户对象
		Map<String, Object> map = goodsOrderController.checkLogin(sm_login);
		boolean success = (Boolean) map.get("success"); 
		if(!success) {
			jr.setMessage((String)map.get("message"));
			outJsonP(response, jsoncallback, jr);
			return;
		}
		
		//登录用户(主账号登录即操作者)
		if("user".equals(map.get("userType"))){
			User user = (User)map.get("userOrStaff");
			attentionid = user.getId();
			//子账号登录
		}else{
			Staff staff = (Staff) map.get("userOrStaff");
			attentionid = staff.getParent().getId();
		}
		
		try {
			if(attentionid == beattentionid){
				jr.setSuccess(false);
				jr.setMessage("不能关注自己的服务");
				outJsonP(response, jsoncallback, jr);
				return;
			}else{
				if(dao.count("SELECT COUNT(*) from ts_user WHERE uid ="+attentionid,null)>0&&dao.count("SELECT COUNT(*) from ts_user WHERE uid ="+beattentionid,null)>0	){
					dao.update("INSERT INTO ts_user_follow (uid,fid) VALUES("+attentionid+","+beattentionid+")");
					jr.setSuccess(true);
					jr.setMessage("关注成功");
					outJsonP(response, jsoncallback, jr);
					return;
				}else{
					jr.setSuccess(false);
					jr.setMessage("关注失败");
					outJsonP(response, jsoncallback, jr);
					return;
				}
			}
		} catch (Exception e) {
			jr.setMessage("关注失败!异常信息:" + e.getLocalizedMessage());
		} finally {
			outJsonP(response, jsoncallback, jr);
		}
	}
	
	/**
	 * window跨域验证是否关注
	 * 
	 * @author cs
	 * @since 2013-08-24
	 * 
	 * @param 
	 * @return
	 * @eg
	 */
	@RequestMapping(value = "/valdateAddattentionJsonP")
	public void valdateAddattentionJsonP(
			@RequestParam("beattentionid")Integer beattentionid,
			String sm_login,
			String jsoncallback, HttpServletRequest request, HttpServletResponse response) {
		Integer attentionid;
		JSONResult jr = new JSONResult(false, null);
		if(StringUtils.isEmpty(sm_login)) {
			jr.setMessage("校验码不能为空");
			outJsonP(response, jsoncallback, jr);
			return;
		}
		// 通过解析字符串sm_login,来检验请求合法性,获取用户类型和用户对象
		Map<String, Object> map = goodsOrderController.checkLogin(sm_login);
		boolean success = (Boolean) map.get("success"); 
		if(!success) {
			jr.setMessage((String)map.get("message"));
			outJsonP(response, jsoncallback, jr);
			return;
		}
		
		//登录用户(主账号登录即操作者)
		if("user".equals(map.get("userType"))){
			User user = (User)map.get("userOrStaff");
			attentionid = user.getId();
			//子账号登录
		}else{
			Staff staff = (Staff) map.get("userOrStaff");
			attentionid = staff.getParent().getId();
		}
		try {
			if(dao.count("SELECT COUNT(*) from ts_user_follow WHERE uid ="+attentionid +" AND fid ="+beattentionid,null)>0){
				jr.setSuccess(true);
				jr.setMessage("已经成功");
				outJsonP(response, jsoncallback, jr);
				return;
			}else{
				jr.setSuccess(false);
				jr.setMessage("没有关注");
				outJsonP(response, jsoncallback, jr);
				return;
			}
		} catch (Exception e) {
			jr.setMessage("关注失败!异常信息:" + e.getLocalizedMessage());
		} finally {
			outJsonP(response, jsoncallback, jr);
		}
	}
	
	/**
	 * 取消关注
	 * 
	 * @author cs
	 * @since 2013-08-24
	 * 
	 * @param 
	 * @return
	 * @eg
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/cancelattention")
	public void cancelattention(
			@RequestParam("attentionid")Integer attentionid,
			@RequestParam("beattentionid")Integer beattentionid,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			dao.update("DELETE FROM ts_user_follow  WHERE uid ="+attentionid +" AND fid = "+beattentionid);
			outJson(response, new JSONResult(true, "取消关注成功"));
		} catch (SQLException e) {
			outJson(response, new JSONResult(false, "取消关注失败"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转到服务搜索结果页面 显示的样式分为两种:矩形和列表形
	 * @author liuliping
	 * @since 2013-08-30
	 * @param name 搜索服务的名字
	 * @param encodedName 编码过的搜索关键字(ASCII码16进制)
	 * @param type 服务结果显示的方式(0:矩形;1:列表形)
	 * @param order 查询结果按此参数降序 (服务次数:'serviceNum',评价:'',时间:'',价格:'')
	 * @param upOrDown 查询的结果升序或者降序(0:降序, 1:升序, 默认0)
	 * @param max 查询条件价格最大值
	 * @param min 查询条件价格最大值
	 * @return
	 * @eg
	 */
	@RequestMapping(value = "/result")
	public String toResult(
			@RequestParam(value = "name", defaultValue = "", required = true) String name,
			String encodedName,
			@RequestParam(value = "type", defaultValue = "0", required = true) int type,
			@RequestParam(value = "order", defaultValue = "serviceNum", required = false) String order,
			@RequestParam(value = "upOrDown", defaultValue = "0", required = true)int upOrDown,
			Integer max,
			Integer min,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		int limit = 0;
		String redirect = null; // 重定向的访问地址
		String start = request.getParameter("pager.offset");
		start = StringUtils.isEmpty(start) ? "0" : start;
		if (type ==Constant.RESULT_TYPE_BIG) {
			limit = Common.resultNumJ; // 分页查询单页结果数量(大图)
			redirect = "module/service/result_1";
		} else {
			limit = Common.resultNumL; // 分页查询单页结果数量(列表图)
			redirect = "module/service/result_2";
		}

		/*
		 * 由于分页标签pager只能通过get请求传递分页的参数,所以需要对中文进行编码成unicode十六进制码,
		 * encodeName这个参数就是name的编码后的字符串
		 */
		if ((StringUtils.isEmpty(name)) && !(StringUtils.isEmpty(encodedName))) {
			try {
				name = URLDecoder.decode(encodedName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isEmpty(encodedName)) {
			try {
				encodedName = URLEncoder.encode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		JSONData<ServiceView> jd = serviceBiz.queryByName(name, order, upOrDown, max, min,
				Integer.parseInt(start), limit); // 分页查询

		// 加载枢纽平台平道列表	FIXME
		@SuppressWarnings("unchecked")
		List<Channel> cl = (List<Channel>) request.getSession().getAttribute(
				"channelList");
		if (cl == null) {
			cl = channelBiz.findChnnelByCtype(Constant.CATEGORY_ID);
			request.getSession().setAttribute("channelList", cl);
		}
   
		model.addAttribute("name", name);
		model.addAttribute("encodedName", encodedName);
		model.addAttribute("order", order);
		model.addAttribute("upOrDown", upOrDown);
		if (max != null) {
			model.addAttribute("max", max);
		}
		if (min != null) {
			model.addAttribute("min", min);
		}
		/*****把serviceview里面的评价先四舍五入*****/
		List<ServiceView> serviceviews = jd.getData();
		for (ServiceView serviceView : serviceviews) {
			serviceView.setEvaluateScore(Common.round(serviceView.getEvaluateScore()).intValue());
		}
		/**********************************/
		model.addAttribute("serviceViews", jd.getData());
		model.addAttribute("total", jd.getTotal());
		return redirect;
	}
	
	/**
	 * 服务列表页的分页
	 * @author liuliping
	 * @param order 服务端按照次列名来数据查询排序
	 * @param cid 服务类别id
	 * @param serviceName 服务名称
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/paging")
	public String paging(@RequestParam(value="order", defaultValue="", required=false)String order,
			@RequestParam(value="limit", defaultValue="9", required=false)int limit,
			@RequestParam(value="cid", defaultValue="", required=false)Integer cid,
			String serviceName, HttpServletRequest request,	HttpServletResponse response, Model model) throws Exception {
		String start = request.getParameter("pager.offset");	//分页起始参数
		start = StringUtils.isEmpty(start) ? "0" : start;
		if (!StringUtils.isEmpty(order)) {    //查询结果根据order来降序
			String[] columns = order.split(",");                                                                                                                                                                                                                                                                                                    
			for (int i = 0; i < columns.length; i++) {
				order = columns[i] + " DESC,";
			}
		}
		JSONData<ServiceView> jd = serviceBiz.findServicesData(
				Integer.parseInt(start), limit, order, cid);
		
		/*加载枢纽平台平道列表*/
		@SuppressWarnings("unchecked")
		List<Channel> cl = (List<Channel>) request.getSession().getAttribute("channelList");
		if (cl == null) {
			cl = channelBiz.findChnnelByCtype(Constant.CATEGORY_ID);
			request.getSession().setAttribute("channelList", cl);
		}
		
		/*服务类别信息,面包屑显示*/
		Category category = categoryBiz.findById("service", cid);
		Category pcategory = categoryBiz.findById("service", category.getPid());

		model.addAttribute("cid", cid);
		model.addAttribute("pcategoryName", pcategory.getText());
		model.addAttribute("categoryName", category.getText());
		model.addAttribute("start", start);
		model.addAttribute("serviceViews", jd.getData());
		model.addAttribute("total", jd.getTotal());
		return "module/service/list";
	}
	
	/**
	 * 服务查询输出部分数据，结果集按某列降序
	 * @author liuliping
	 * @since 2013-08-24
	 * 
	 * @param order 结果排序hql语句，默认是"",例如"serviceNum, id"
	 * @param start 起始数
	 * @param limit 分页大小
	 * @return
	 * @eg
	 */
	@RequestMapping(value = "/getServiceData")
	public void getServiceData(@RequestParam(value="order", defaultValue="", required=false)String order,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="15", required=false)int limit,
			@RequestParam(value="cid", defaultValue="", required=false)Integer cid,
			HttpServletRequest request, HttpServletResponse response) {
		if (!StringUtils.isEmpty(order)) {
			String[] columns = order.split(",");
			for (int i = 0; i < columns.length; i++) {
				order = columns[i] + " DESC,";
			}
		}
		JSONData<ServiceView> jd  = serviceBiz.findServicesData(
				start, limit, order, cid);
		outJson(response, jd.getData());
	}

	/**
	 * * 查询服务，按状态和服务名称（模糊查询）查询
	 * 
	 * @author xuwf
	 * @since 2013-8-15
	 * 
	 * @param status	服务状态
	 * @param currentPage	当前页
	 * @param start			起始值
	 * @param limit			每页条数
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/find")
	public void find(@RequestParam(value="status",required=false)String status,
			@RequestParam(value = "orgName", required = false) String orgName,
			@RequestParam(value = "sname", required = false) String sname,
			@RequestParam("page")Integer page,
			@RequestParam("start")Integer start,
			@RequestParam("limit")Integer limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[ status:"+status+",sname,"+sname+"currentPage:"+page+",start:"+start+",limit:"+limit+"]");
		if(null == status){
			status = "";
		}
		JSONData<Service> services = serviceBiz.queryStatistics(status,
				orgName, sname, start, limit);
		this.outJson(response,services);
	}
	
	/**
	 * * 查询服务，按类别和服务名称（模糊查询）查询
	 * 
	 * @author xuwf
	 * @since 2013-8-19
	 * 
	 * @param sname	服务名称
	 * @param catId	类别编号
	 * @param currentPage	当前页
	 * @param start			起始值
	 * @param limit			每页条数
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findByCate")
	public void findByCate(@RequestParam(value="sname",required=false)String sname,
			@RequestParam(value="catId",required=false)String cateId,
			@RequestParam("page")Integer page,
			@RequestParam("start")Integer start,
			@RequestParam("limit")Integer limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[catId:"+cateId+",sname,"+sname+"currentPage:"+page+",start:"+start+",limit:"+limit+"]");
		Integer catId = null;
		try {
			catId = Integer.parseInt(cateId);
		} catch (NumberFormatException e) {
			catId = 1;
		}
		JSONData<Service> services = serviceBiz.queryByCatId(sname, catId, start, limit);
		this.outJson(response,services);
	}
	
	/**
	 * 服务编辑
	 *@author pangyf
	 *@since 2013-8-14 
	 *
	 *@param service
	 *@return 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/edit")
	public void update(Service service,HttpServletRequest request,HttpServletResponse response) {
		logger.info("queryString:"+request.getQueryString());
		try {
			String[] properties = {"serviceName","serviceMethod","costPrice","picture","serviceProcedure","linkMan","tel","email"};
			Service _service= serviceBiz.findServiceById(service.getId());
			Common.copyProperties(service, _service, properties);
			_service.setCategory(categoryBiz.findById("service", service.getCategory().getId()));
			
			//保存服务
			service = serviceBiz.update(_service);
			//同步服务到该窗口
			ServiceSyncBean sb = new ServiceSyncBean(_service, SyncFactory.getSyncType(_service));
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb),true);
			
			//保存流水
			ServiceDetail sd = new ServiceDetail(service, Constant.SERVICE_AUDIT_NORMAL, service.getCurrentStatus());			
			sd = serviceDetailBiz.create(sd);
			//同步流水到该窗口
			ServiceDetailSyncBean _sb = new ServiceDetailSyncBean(sd, SyncType.ONE);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(_sb),false);
			
			//serviceBiz.save(service);
			logger.info("[ "+service.getServiceName()+" ]编辑成功!");
			this.outJson(response,new JSONResult(true, service.getCurrentStatus() + "|服务编辑成功!"));
		} catch (Exception e) {
			this.outJson(response,
					new JSONResult(false, service.getCurrentStatus()
							+ "|服务编辑失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("服务编辑失败!异常信息:" + e.getLocalizedMessage());
		}
	}

	
	
	
	/**
	 * 服务更新(上下架,变更，删除)
	 *@author pangyf
	 *@since 2013-8-14 
	 *
	 *@param service
	 *@return 
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/update")
	//public void apply(@RequestParam("service")String service,HttpServletRequest request,HttpServletResponse response) {
	public void apply(Service service,HttpServletRequest request,HttpServletResponse response) {
		logger.info("queryString:"+request.getQueryString());
			try {
				//Service _service = JSON.parseObject(service, Service.class);
				String[] properties = {"currentStatus","lastStatus"};
				Service _service= serviceBiz.findServiceById(service.getId());
				Common.copyProperties(service, _service, properties);
				
				//保存服务
				service = serviceBiz.update(_service);
				//同步服务到该窗口
				ServiceSyncBean sb = new ServiceSyncBean(_service, SyncFactory.getSyncType(_service));
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb),true);
				
				//保存流水
				ServiceDetail sd = new ServiceDetail(service, Constant.SERVICE_AUDIT_NORMAL, service.getCurrentStatus());			
				sd = serviceDetailBiz.create(sd);
				//同步流水到该窗口
				ServiceDetailSyncBean _sb = new ServiceDetailSyncBean(sd, SyncType.ONE);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(_sb),false);
				
				logger.info("[ "+service.getServiceName()+" ]更新成功!");
				this.outJson(response,new JSONResult(true,"服务更新成功!"));
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"服务更新失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("服务更新失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	/**
	 * 前台服务状态更新(上架,下架,删除)
	 *@author pangyf
	 *@since 2013-9-7
	 *@param id
	 *@param currentStatus
	 *@return 
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/statuschange")	
	public void apply2(@RequestParam("id")Integer id,
						@RequestParam("currentStatus")Integer currentStatus,						
						HttpServletRequest request,HttpServletResponse response) {
		logger.info("queryString:"+request.getQueryString());
			try {
				this.keepUserSession(request);
				Service service = serviceBiz.findServiceById(id);
				service.setLastStatus(service.getCurrentStatus());
				service.setCurrentStatus(currentStatus);
				service.setLocked(true);
				Boolean is = false;
				if(currentStatus == Constant.SERVICE_STATUS_ADDED_AUDIT || currentStatus == Constant.SERVICE_STATUS_NEW || currentStatus == Constant.SERVICE_STATUS_DOWN){					
					List<Category> list = service.getEnterprise().getMyServices();
					for(int i=0;i<list.size();i++){
						if(service.getCategory().getId() == list.get(i).getId()){						
							is = true;
							break;
						}
					}
					if(is){
						//保存服务
						service = serviceBiz.update(service);
						//同步服务到该窗口
						ServiceSyncBean sb = new ServiceSyncBean(service, SyncFactory.getSyncType(service));
						SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb),true);
						
						//保存流水
						ServiceDetail sd = new ServiceDetail(service, Constant.SERVICE_AUDIT_NORMAL, service.getCurrentStatus());
						sd = serviceDetailBiz.create(sd);
						//同步流水到该窗口
						ServiceDetailSyncBean _sb = new ServiceDetailSyncBean(sd, SyncType.ONE);
						SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(_sb),false);
						
						this.outJson(response,new JSONResult(true,"服务更新成功!"));
					}else {
						this.outJson(response,new JSONResult(false,"该服务类别不属于你的服务领域"));
					}
				}else {
					service = serviceBiz.update(service);
					//同步服务到该窗口
					ServiceSyncBean sb = new ServiceSyncBean(service, SyncFactory.getSyncType(service));
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb),true);
					
					//保存流水
					ServiceDetail sd = new ServiceDetail(Constant.SERVICE_AUDIT_NORMAL,
							service.getId(), service.getServiceName(),
							service.getCurrentStatus());
					sd = serviceDetailBiz.create(sd);
					//同步流水到该窗口
					ServiceDetailSyncBean _sb = new ServiceDetailSyncBean(sd, SyncType.ONE);
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(_sb),false);
					
					this.outJson(response,new JSONResult(true,"服务更新成功!"));
				}
				
				
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"服务更新失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("服务更新失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	
	/**
	 * 用户在前台页面申请服务,即购买服务
	 *@author liuliping
	 *@since 2013-8-31 
	 *
	 *@param sid 服务id(必须)
	 *@param uid 用户id(必须)
	 *@param name 申请人姓名(必须)
	 *@param telNum 联系电话(必须)
	 *@param email 邮箱(必须)
	 *@return 
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/buy")
	public void buyService(Integer sid, Integer uid, String name, 
			String telNum, String email, HttpServletRequest request,
			HttpServletResponse response) {
		serviceBiz.buyService(sid, uid, name, telNum, email);	
	}
	
	/**
	 * 删除服务
	 *@author lizhiwei
	 *@since 2013-8-15 
	 *
	 *@param service
	 *@return 
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/delete")
	public void delete(@RequestParam("services")String services,HttpServletRequest request,HttpServletResponse response) {
		logger.info("queryString:"+request.getQueryString());
		List<Service> list = JSON.parseArray(services, Service.class);
		try {
			for(Service service:list){
				serviceBiz.delete(service);
				logger.info("[ "+service.getServiceName()+" ]删除成功!");
			}
			this.outJson(response,new JSONResult(true,"服务删除成功!"));
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"服务删除失败!异常信息:"+e.getLocalizedMessage()));
			logger.info("服务删除失败!异常信息:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 审核服务
	 *@author liuliping
	 *@since 2013-08-15 
	 *
	 *@param id 服务主键
	 *@return access 服务审核:0通过,1未通过	 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/audit")
	public void audit(Service service, Integer access, String context, 
			HttpServletRequest request,HttpServletResponse response) {
		//Service obj = serviceBiz.findServiceById(service.getId());
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		Map<String, Object> resultMap = serviceBiz.updateAudit2(service, access, context, manager.getUsername());
		Boolean success = (Boolean)resultMap.get("success");
		if(success.booleanValue() == true) {	//业务层执行成功
			// 同步至窗口 服务
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(new 
					ServiceSyncBean(service, SyncFactory.getSyncType(service))), true);
			ServiceDetail nsd = (ServiceDetail)resultMap.get("serviceDetail");		//服务流水
			// 同步至窗口 服务流水
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(new
					ServiceDetailSyncBean(nsd, SyncType.ONE)));
			
		} 
//		else {	//业务层执行出现异常
//			
//		}
		outJson(response, resultMap.get("jsonResult"));
	}
	
	/**
	 * @author huyj
	 * @sicen 2013-8-14
	 * @description 更新服务内容
	 * @param service
	 *            服务对象
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/updateComments")
	public void updateComments(Service service, HttpServletRequest request,
			HttpServletResponse response) {
		//service.setLastStatus(service.getCurrentStatus());
		//service.setCurrentStatus(Constant.SERVICE_STATUS_CHANGE_AUDIT);
		try {
			//serviceBiz.update(service);
			
			
			// 申请变更时，添加一条流水信息，记录要更改的内容
			ServiceDetail serviceDetail = new ServiceDetail(service,
					Constant.SERVICE_AUDIT_NORMAL,
					Constant.SERVICE_STATUS_CHANGE_AUDIT);
			serviceDetail = serviceDetailBiz.create(serviceDetail);
			//同步流水到该窗口
			ServiceDetailSyncBean _sb = new ServiceDetailSyncBean(serviceDetail, SyncType.ONE);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(_sb),true);
			
			// 通过serviceid获取服务
			service = serviceBiz.findServiceById(service.getId());
			// 设置服务流水标识
			service.setDetailsId(serviceDetail.getId());
			service.setLastStatus(Constant.SERVICE_STATUS_ADDED);
			service.setCurrentStatus(Constant.SERVICE_STATUS_CHANGE_AUDIT);
			
			//保存服务
			service = serviceBiz.update(service);
			//同步服务到该窗口
			ServiceSyncBean sb = new ServiceSyncBean(service, SyncFactory.getSyncType(service));
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb),false);
			
			//serviceBiz.updateUservice(service);
			logger.info("[ " + service.getServiceName() + " ]申请变更成功!");
			this.outJson(response, new JSONResult(true, "服务申请变更成功!"));
		} catch (Exception e) {
			this.outJson(
					response,
					new JSONResult(false, "服务申请变更失败!异常信息:"
							+ e.getLocalizedMessage()));
		}
	}
	
	/**
	 * 前台修改服务信息（新服务、已删除服务、申请变更）
	 * @author pangyf
	 * @since 2013-9-14
	 * @param service 对象 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/changeService")
	public void changeService(
			Service service,
			@RequestParam("chatgroup")Integer chatgroupid,
			HttpServletRequest request, HttpServletResponse response) {
		Service _service = new Service();
		BeanUtils.copyProperties(serviceBiz.findServiceById(service.getId()), _service); // 复制数据库中该service的属性值到持久化对象_service
		String[] properties = {"serviceMethod","costPrice","picture","serviceProcedure","linkMan","tel","email"};
		Common.copyProperties(service, _service, properties);
		String tips = "服务修改";
		
		try {
			if(this.getCurrentUserType(request)==Constant.LOGIN_USER){
				User user = this.getUserFromSession(request);
				request.getSession().setAttribute("user",user);				
			}else {
				Staff staff = this.getStaffFromSession(request);
				request.getSession().setAttribute("user",staff);					
			}							
			if(_service.getCurrentStatus() == Constant.SERVICE_STATUS_ADDED){
				_service.setLastStatus(service.getCurrentStatus());
				_service.setCurrentStatus(Constant.SERVICE_STATUS_CHANGE_AUDIT);
				_service.setLocked(true);
				tips = "服务申请变更";
				
				// 申请变更时，添加一条流水信息，记录要更改的内容
				ServiceDetail serviceDetail = new ServiceDetail(_service,
						Constant.SERVICE_AUDIT_NORMAL,
						Constant.SERVICE_STATUS_CHANGE_AUDIT);
				serviceDetail = serviceDetailBiz.create(serviceDetail);
				//同步流水到该窗口
				ServiceDetailSyncBean _sb = new ServiceDetailSyncBean(serviceDetail, SyncType.ONE);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(_sb),true);
				
				// 通过serviceid获取服务
				service = serviceBiz.findServiceById(_service.getId());
				// 设置服务流水标识
				service.setDetailsId(serviceDetail.getId());
				service.setLastStatus(Constant.SERVICE_STATUS_ADDED);
				service.setCurrentStatus(Constant.SERVICE_STATUS_CHANGE_AUDIT);
				
				//保存服务
				service = serviceBiz.update(service);
				//同步服务到该窗口
				ServiceSyncBean sb = new ServiceSyncBean(service, SyncFactory.getSyncType(service));
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb),false);
				
			}
			else {
				if(_service.getCurrentStatus() == Constant.SERVICE_STATUS_NEW){
					_service.setServiceName(service.getServiceName());
					_service.setCategory(categoryBiz.findById("service", service.getCategory().getId()));
				}
				
				//保存服务
				_service = serviceBiz.update(_service);
				//同步服务到该窗口
				ServiceSyncBean sb = new ServiceSyncBean(_service, SyncFactory.getSyncType(_service));
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb),true);
				
				//保存流水
				ServiceDetail sd = new ServiceDetail(service, Constant.SERVICE_AUDIT_NORMAL, service.getCurrentStatus());
				sd = serviceDetailBiz.create(sd);
				//同步流水到该窗口
				ServiceDetailSyncBean _sb = new ServiceDetailSyncBean(sd, SyncType.ONE);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ServiceDetail>(_sb),false);
				
			}
			logger.info("[ " + service.getServiceName() + " ]" + tips + "成功!");
			this.outJson(response, new JSONResult(true, tips + "成功!"));
			/**
			 * @date: 2013-11-13
			 * @author：lwch
			 * @description：修改服务与在线客服组的关联ID
			 * =========================================================================================
			 */
			try {
				List<?> list = serviceBiz.getServerAndChatGroupRelate(service.getId());
				//判断该服务是否与客服有过管理
				if (list.size() > 0) {
					Map<String, Object> map = (Map<String, Object>)list.get(0);
					Integer id = (Integer)map.get("id");
					//更改该服务关联的客服
					if(chatgroupid != 0){
						serviceBiz.updateServerAndChatGroupRelate(id, chatgroupid);
					} else {
						//删除该服务与客服的关联
						serviceBiz.deleteServerAndChatUserRelate(id);
					}
				} else {
					//添加服务与客服之间的关联
					if(chatgroupid != 0){
						serviceBiz.addServiceAndChatGroupRelate(service.getEnterprise().getId(), service.getId(), chatgroupid);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			/**
			 * =========================================================================================
			 */
						
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false, tips + "失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("服务申请变更失败!异常信息:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 前台查询该机构的该服务审核情况
	 * @author pangyf
	 * @since 2013-9-14 
	 * @param eid
	 * @param sid
	 * @param page
	 * @param rows
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/queryApproval")
	public void queryApproval(
			@RequestParam("eid")Integer eid,
			@RequestParam("sid")Integer sid,
			@RequestParam(value="page",required=false)int page,
			@RequestParam(value="rows",required=false)int rows,
			HttpServletRequest request, HttpServletResponse response) {					
		try {
			if(this.getCurrentUserType(request)==Constant.LOGIN_USER){
				User user = this.getUserFromSession(request);
				request.getSession().setAttribute("user",user);				
			}else {
				Staff staff = this.getStaffFromSession(request);
				request.getSession().setAttribute("user",staff);							
			}
			this.outJson(response, serviceBiz.findApprovalServiceByEid(eid, sid, rows * (page - 1), rows));
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * 检查该服务的类别是否在该企业的服务领域范围内
	 * @author pangyf
	 * @since 2013-10-28
	 * @param sid
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/checkService")
	public void checkService(@RequestParam("sid")Integer sid,HttpServletRequest request, HttpServletResponse response){
		Service service = serviceBiz.findServiceById(sid);
		List<Category> list = service.getEnterprise().getMyServices();
		Integer cid = service.getCategory().getId();
		try {
			if(!list.isEmpty()){
				for(int i = 0;i<list.size();i++){
					if(cid == list.get(i).getId()){
						this.outJson(response, new JSONResult(true,"该服务在服务领域内"));
						break;
					}else if(i == list.size()-1){
						this.outJson(response, new JSONResult(false,"该服务不在服务领域内"));
					}
				}
			}else {
				this.outJson(response, new JSONResult(false,"该服务机构还没有添加服务领域"));
			}
		} catch  (Exception e) {
			this.outJson(response, new JSONResult(false,"服务器产生异常，请联系管理员"));
		}
	}
	
}
