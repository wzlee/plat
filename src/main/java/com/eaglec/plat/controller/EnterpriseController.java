package com.eaglec.plat.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.cms.ChannelBiz;
import com.eaglec.plat.biz.cms.ModuleBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.cms.Module;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.Dao;
import com.eaglec.plat.view.Breadcrumb;
import com.eaglec.plat.view.CategoryGroup;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONEntity;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.ServiceParam;
import com.eaglec.plat.view.ServiceView;

@Controller
@RequestMapping(value="/enter")
public class EnterpriseController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(EnterpriseController.class);
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private ServiceBiz serviceBiz;
	@Autowired
	private CategoryBiz categoryBiz;
	@Autowired
	private ModuleBiz moduleBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private Dao dao;
	
	
	/**
	 * 按eid查询所有的企业用户
	 * 
	 * @author pangyf
	 * @since 2013-8-22 
	 * 
	 */
	@RequestMapping(value = "/query")
	public void query(
			@RequestParam(value="eid",required=false)String eid,
			@RequestParam(value="start",defaultValue="0",required=false)int start,
			@RequestParam(value="limit",defaultValue="30",required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {	
		//System.out.println(userBiz.findAll(start, limit).toString());		
		this.outJson(response, enterpriseBiz.findEnterpriseListById(eid,start,limit));
	}
	
	/**
	 * 根据Eid查询企业信息
	 * 
	 * @author wzlee
	 * @since 2013-8-24 
	 * 
	 */
	@RequestMapping(value = "/load")
	public void load(@RequestParam(value="eid",required=false)int eid,
			HttpServletRequest request,
			HttpServletResponse response) {	
		//System.out.println(userBiz.findAll(start, limit).toString());		
		this.outJson(response, new JSONEntity<Enterprise>(true,enterpriseBiz.loadEnterpriseByEid(eid)));
	}
	
	/**
	 * 新服务关联企业
	 * 
	 * @author pangyf
	 * @since 2013-9-3
	 * 
	 */
	@RequestMapping(value = "/check")
	public void getEnterprise(@RequestParam(value="icRegNumber",required=false)String icRegNumber,
			HttpServletRequest request,
			HttpServletResponse response) {		
		List<Enterprise> list = enterpriseBiz.findEnterpriseByIcRegNumber(icRegNumber);		
		if(list.isEmpty()){			
			this.outJson(response,new JSONResult(false,"组织机构号输入有误,请重新再试!"));
		}else {
			Enterprise enterprise = list.get(0);			
			if(enterprise.getType()==Constant.ENTERPRISE_TYPE_ORG){
				this.outJson(response,new JSONResult(true,enterprise.getName()));
			}else{
				this.outJson(response,new JSONResult(false,"该企业没有认证为服务机构，不能添加服务"));
			}
		}
	}
	
	/**
	 * 根据组织机构号查找服务机构
	 * @author xuwf
	 * @since 2013-11-28
	 * 
	 * @param icRegNumber
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/loadOrgEnterprise")
	public void loadOrgEnterprise(@RequestParam(value="icRegNumber",defaultValue="",required=false)String icRegNumber, HttpServletRequest request,HttpServletResponse response) {
		logger.info(request.getParameterMap().toString());
		try {
			if(!icRegNumber.isEmpty()){
				List<Enterprise> list = enterpriseBiz.findEnterpriseByIcRegNumber(icRegNumber);
				this.outJson(response,list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("服务机构加载失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "服务机构加载失败!"));
		}
	}
	
	/**
	 * 服务机构主页 每次加载9条服务信息
	 * 
	 * @author huyj
	 * @sicen 2013-9-17
	 * @param eid
	 *            企业id
	 *@param pager.offset
	 *           分页参数start
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryEnter")
	public String queryEnter(Model model,
			@RequestParam(value = "eid", required = true) String eid,
			@RequestParam(value = "pager.offset", defaultValue = "0", required = false) int start,
			HttpServletRequest request, HttpServletResponse response) {
		Enterprise enterprise = enterpriseBiz.loadEnterpriseByEid(Integer.parseInt(eid));
		User fuser = userBiz.findUserByEnterprise(enterprise.getId());
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
		}
		
		List<CategoryGroup> groupList = enterpriseBiz
				.findCategoryGroupByEid(eid);
		List<ServiceView> serviceList = serviceBiz.findServiceByEid(eid, start,
				Constant.ORG_SERVICE_LIMIT);
		Long total = serviceBiz.findServiceCountByEid(eid);

		request.setAttribute("enterprise", enterprise);
		request.setAttribute("serviceGroup", groupList);
		request.setAttribute("serviceList", serviceList);
		request.setAttribute("total", total);
		return "enterprise/enterprise_index";
	}

	/**
	 * 服务机构信息
	 * 
	 * @author huyj
	 * @sicen 2013-9-18
	 * @param eid
	 *            服务机构id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/detailEnter")
	public String detailEnter(
			@RequestParam(value = "eid", required = true) String eid,
			HttpServletRequest request, HttpServletResponse response,Model model) {
		Enterprise enterprise = enterpriseBiz.loadEnterpriseByEid(Integer
				.parseInt(eid));
		request.setAttribute("enterprise", enterprise);
		List<CategoryGroup> groupList = enterpriseBiz
				.findCategoryGroupByEid(eid);
		User fuser = userBiz.findUserByEnterprise(enterprise.getId());
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
		}
		request.setAttribute("serviceGroup", groupList);
		return "enterprise/enterprise_info";
	}
	
	/**
	 * 服务机构首页
	 * 
	 * @author liuliping
	 * @sicen 2013-09-17
	 * @description TODO actionPath eg:
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/index")
	public String toIndexAgency(HttpServletRequest request, HttpServletResponse response, Model model) {
		//1.加载全部服务机构分类
		Map<String, Object> sc = categoryBiz.getServiceCategorys();
		
		//2. 9个服务类别下的模块
		Map<String, List<Module>> modulesMap = moduleBiz.findEnterprisesRecommended();
		
		//3.顶部4个服务机构 FIXME
		/* 通过模块的信息来找到对应的服务机构对象,并将信息输出的JSP页面*/
		List<Module> modules = modulesMap.get("顶部");
		Map<String, Enterprise> topEnterp = null;
		Map<String, List<ServiceView>> serviceMap = null;
		if (modules != null) {
			modulesMap.remove("顶部");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < modules.size(); i++) {
				sb.append(modules.get(i).getMindex()).append(",");
			}
			int length = sb.length();
			sb.delete(length - 1, length);
			List<Enterprise> enterprises = enterpriseBiz.
					findEnterpriseListById(sb.toString(), 0, 4).getData();    //通过id查询出服务机构
			topEnterp = new HashMap<String, Enterprise>(4);    //页面头部推荐的4个服务机构对象保存在此Map里
			
			Enterprise enterp = null;
			for (int i = 0; i < enterprises.size(); i++) {
				enterp = enterprises.get(i);
				topEnterp.put(enterp.getId().toString(), enterp);
			}
			/* 查询出4个服务机构对象所提供的服务,并保存在map中*/
			serviceMap = new HashMap<String, List<ServiceView>>(4);
			for (int j = 0; j < enterprises.size(); j++) {
				enterp = enterprises.get(j);
				serviceMap.put(enterp.getId().toString(), 
						serviceBiz.findServiceByEid(enterp.getId().toString(), 0, 6));
			}
		}
		
		model.addAttribute("categorysLev1", sc.get("parents"));    //1级服务分类
		model.addAttribute("categorysLev2", sc.get("children"));    //2级服务分类
		
		model.addAttribute("modules", modules);    //页面顶部推荐的4个模块对象 
		model.addAttribute("topEnterp", topEnterp);    //页面顶部推荐的4个服务机构对象
		model.addAttribute("serviceMap", serviceMap);    //页面顶部推荐的4个服务机构对象所提供的服务
		
		model.addAttribute("modulesMap", modulesMap);    //9个服务分类的推荐模块
		return "agency";
	}
	
	/**
	 * 跳转到服务机构搜索结果页
	 * 
	 * @author liuliping
	 * @sicen 2013-09-30
	 * @param name 服务机构名称
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/search")
	public String toResult(
			@RequestParam(value = "name", defaultValue = "", required = true)String name, 
			String encodedName, Integer industryType, String businessPattern,
			@RequestParam(required = true, defaultValue = "20", value = "limit")int limit, 
			HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String start = request.getParameter("pager.offset");    //分页起始参数
		start = StringUtils.isEmpty(start) ? "0" : start;
		Integer start_int = null;
		try{
			start_int = Integer.parseInt(start);
		} catch (NumberFormatException e) {
			logger.info(e.getLocalizedMessage());
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
		
		JSONData<Enterprise> jd = enterpriseBiz.findByName(name,
				limit, start_int.intValue());    //根据服务机构名称搜索
		
		model.addAttribute("total", jd.getTotal());
		model.addAttribute("enterprises", jd.getData());
		model.addAttribute("name", name);
		model.addAttribute("encodedName", encodedName);
		return "enterprise/org_search_result";
	}
	
	/**
	 * 跳转到服务机构列表页 
	 * @author liuliping
	 * @sicen 2013-10-08
	 * @param cid 服务机构分类的id
	 * @param name 服务机构名称
	 * @param industryId 所属行业
	 * @param businessPattern 经营模式
	 * @param start 分页起始
	 * @param limit 单页数据条数
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public String toList(Integer cid, String name, Integer industryType, String businessPattern,
			@RequestParam(required = true, defaultValue = "0", value = "pageType")int pageType,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		String toPage = null;    //请求跳转的页面
		String start = request.getParameter("pager.offset");    //分页起始参数
		start = StringUtils.isEmpty(start) ? "0" : start;
		
		//1.加载全部服务机构分类
		Map<String, Object> sc = categoryBiz.getServiceCategorys();
		Integer start_int = null;
		try{
			start_int = Integer.parseInt(start);
		} catch (NumberFormatException e) {
			logger.info(e.getLocalizedMessage());
		}
		
		try {
			name = StringUtils.isEmpty(name) ? null : new String(name.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getLocalizedMessage());
		}
		JSONData<Enterprise> jsondata = enterpriseBiz.findEnterpriseListByCid(cid,
				name, industryType, businessPattern, start_int.intValue(), 20);
		
		//3.获得cid对应的一级服务类别以及二级服务类别,作为面包屑数据来源
		Breadcrumb breadcrumb = categoryBiz.getBreadcrumbByCid(cid, "service");
		
		model.addAttribute("categorysLev1", sc.get("parents"));    //1级服务分类
		model.addAttribute("categorysLev2", sc.get("children"));    //2级服务分类
		model.addAttribute("enterprises", jsondata.getData());    //服务机构列表
		model.addAttribute("total", jsondata.getTotal());    //服务机构列表
		model.addAttribute("breadcrumb", breadcrumb);    //面包屑
		model.addAttribute("cid", cid); 
		model.addAttribute("pageType", pageType);
		model.addAttribute("name", name);
		model.addAttribute("industryType", industryType);
		model.addAttribute("businessPattern", businessPattern);
		
		if (pageType == 0) {
			toPage = "enterprise/org_search_list1";
		} else { 
			toPage = "enterprise/org_search_list2";
		}
		return toPage;
	}
	
	
	/**
	 * 查询服务机构下的服务列表
	 * @author Xiadi
	 * @since 2013-9-18 
	 *
	 * @param sparam ServiceParam 对象
	 * @param view 显示方式  "icon" or "list"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/orgservices")
	public String orgServices(ServiceParam sparam,
			@RequestParam(defaultValue = "icon", required = true) String view,
			@RequestParam(value = "pager.offset", defaultValue = "0", required = true) int start,
			HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			logger.info(sparam.toString());
			sparam.setStart(start);
			sparam.setLimit(view.equals("list") ? 5 : 15);
			
			sparam.setServiceName(sparam.getServiceName() != null && !"".equals(sparam.getServiceName()) 
					? new String(sparam.getServiceName().getBytes("ISO-8859-1"), "UTF-8") : null);
			
			request.setAttribute("view", view);
			request.setAttribute("sparam", sparam);
			JSONData<ServiceView> data = serviceBiz.findOrgService(sparam);
			Enterprise enterprise = enterpriseBiz.loadEnterpriseByEid(sparam.getEid());
			User fuser = userBiz.findUserByEnterprise(enterprise.getId());
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
			}
			
			request.setAttribute("enterprise", enterprise);
			request.setAttribute("serviceGroup", enterpriseBiz.
					findCategoryGroupByEid(sparam.getEid()+""));
			request.setAttribute("total", data.getTotal());
			request.setAttribute("list", data.getData());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "enterprise/orgservice_list";
	}
	
	/**
	 * 分页查询服务机构
	 * @author liuliping
	 * @since 2013-09-23 
	 *
	 * @param enterpName 企业名称
	 * @param limit 分页查询条数
	 * @param start 分页查询起始
	 * @param request
	 * @param response
	 * */
	@RequestMapping(value = "/queryServiceAgency")
	public void queryServiceAgency (String enterpName, int limit, int start, HttpServletRequest request, HttpServletResponse response) {
		JSONData<Enterprise> jd = enterpriseBiz.findByName(enterpName, limit, start);
		outJson(response, jd);
	}
}
