package com.eaglec.plat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.eaglec.plat.biz.cms.ChannelBiz;
import com.eaglec.plat.biz.flat.FlatBiz;
import com.eaglec.plat.biz.news.NewsBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.EnterpriseCreditBiz;
import com.eaglec.plat.biz.user.SendEmailBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.StaffMenuBiz;
import com.eaglec.plat.biz.user.StaffRoleBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.controller.BaseController;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.EnterpriseCredit;
import com.eaglec.plat.domain.base.Flat;
import com.eaglec.plat.domain.base.SendEmail;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.StaffMenu;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.cms.News;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.bean.UserSyncBean;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
import com.eaglec.plat.utils.Base64;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.MD5;
import com.eaglec.plat.utils.StaffTree;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.ServiceView;
import com.eaglec.plat.utils.CookieHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private CategoryBiz categoryBiz;
	@Autowired
	private NewsBiz newsBiz;
	@Autowired
	private ServiceBiz serviceBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private SendEmailBiz sendEmailBiz;
	@Autowired
	private FlatBiz flatBiz;
	@Autowired
	private StaffBiz staffBiz;
	@Autowired
	private StaffMenuBiz staffMenuBiz;
	@Autowired
	private StaffRoleBiz staffRoleBiz;
	@Autowired
	private EnterpriseCreditBiz ecBiz;

	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：枢纽平台首页加载
	 */
	@RequestMapping(value = "/")
	public String index(HttpServletRequest request, HttpServletResponse response,Locale locale,Model model) {
		logger.info("Welcome index! The client locale is:"+locale+",ip is:"+request.getRemoteAddr()+",userAgent is:"+request.getHeader("user-agent"));
		try {
			// 加载枢纽平台平道列表
			List<Channel> cl = channelBiz.findChnnelByCtype(Constant.CATEGORY_ID);
			ServletContext application = request.getSession().getServletContext();
			application.setAttribute("channelList", cl);
			// 加载枢纽平台的行业新闻
			List<News> newsList = newsBiz.getIndexNews(0, Common.newsIndexNum);
			request.setAttribute("newsList", newsList);
			// 加载枢纽平台热门服务
			List<Service> hotList = serviceBiz.getHotService(0, Common.serviceHotNum);
			for (Service hs : hotList) {
				String sp = hs.getServiceProcedure();
				hs.setServiceProcedure(StrUtils.replaceHTML(sp, 0, 35));
			}
			request.setAttribute("hotService", hotList);
			// 加载枢纽平台最新服务
			List<Service> newList = serviceBiz.getNewService(0,
					Common.serviceNewNum);
			for (Service ns : newList) {
				String sp = ns.getServiceProcedure();
				ns.setServiceProcedure(StrUtils.replaceHTML(sp, 0, 35));
			}
			request.setAttribute("newService", newList);

			String direction = request.getRequestURI();
			if(request.getSession().getAttribute("direction")!=null){
				request.getSession().removeAttribute("direction");
				request.getSession().setAttribute("direction", direction);
			}else{
				request.getSession().setAttribute("direction", direction);
			}
			request.setAttribute("pic_phone", 1);	//在请求中添加此变量,使得仅在首页头部显示电话号码图标
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}

	}

	/**
	 * 进入管理后台逻辑判断
	 * 
	 * @param request
	 * @param response
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String home(HttpServletRequest request,
			HttpServletResponse response, Locale locale, Model model) {
		logger.info("Welcome SMEMall! The client locale is:" + locale
				+ ",ip is:" + request.getRemoteAddr() + ",userAgent is:"
				+ request.getHeader("user-agent"));
		Manager manager = (Manager) request.getSession()
				.getAttribute("manager");
		if (manager == null) {
			return "manage/login";
		} else {
			request.setAttribute("manager", JSON.toJSONString(manager));
			request.setAttribute("CENTER_WEBSITE", Common.CENTER_WEBSITE);
			return "manage/home";
		}
	}

	/**
	 * 组件测试:eg：http://localhost/test/upload
	 * 
	 * @param component
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/test/{page}", method = RequestMethod.GET)
	public String testPage(@PathVariable String page,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		model.addAttribute("page", page);
		return page;
	}

	/**
	 * 根据行业协会跳转至view下相应的目录的index.jsp
	 * 
	 * @param guild
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{guild}", method = RequestMethod.GET)
	public String autoRequest(@PathVariable String guild,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		request.setAttribute("guild", guild);
		String direction = request.getRequestURI();
		if(request.getSession().getAttribute("direction")!=null){
			request.getSession().removeAttribute("direction");
			request.getSession().setAttribute("direction", direction);
		}else{
			request.getSession().setAttribute("direction", direction);
		}
		return "window";
	}

	/**
	 * 全部服务分类
	 * 
	 * @author liuliping
	 * @since 2013-08-23
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return node/category 返回jewelry.jsp
	 */
	@RequestMapping(value = "/categoryComponent")
	public String categoryComponent(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Category> cy = categoryBiz.findCategoryParent();
		model.addAttribute("categoryList", cy);
		return "layout/category";
	}

	/**
	 * 导航栏
	 * 
	 * @author liuliping
	 * @since 2013-08-23
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return node/category 返回jewelry.jsp
	 */
	@RequestMapping(value = "/navigator")
	public String navigator(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("cid");
		List<Channel> cl = channelBiz.findChnnelByCtype(Integer.parseInt(id));
		model.addAttribute("channelList", cl);
		return "layout/node/navigator";
	}

	/**
	 * 行业新闻,返回最新的五条新闻
	 * 
	 * @author liuliping
	 * @since 2013-08-23
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return node/newsComponent 返回newsComponent.jsp
	 */
	@RequestMapping(value = "/newsComponent")
	public String newsComponent(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String hql = "FROM News ORDER BY id DESC";
		List<News> newsList = newsBiz.getNewsList(hql, 0, 5);
		model.addAttribute("newsList", newsList);
		return "layout/node/newsComponent";
	}

	/**
	 * 查询服务机构Top10企业
	 * @author xuwf
	 * @since 2013-9-13
	 * @param type	类型：服务机构
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/topTen")
	public String findTopTen(Integer type,
			HttpServletRequest request,HttpServletResponse response, Model model){
		List<Enterprise> enterpriseList = enterpriseBiz.findTopTen(Constant.ENTERPRISE_TYPE_ORG);
		model.addAttribute("enterpriseList", enterpriseList);
		return "layout/node/organize";
	}
	
	/**
	 * 服务排行
	 * 
	 * @author liuliping
	 * @since 2013-08-23
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return node/serviceRunklist 返回serviceRunklist.jsp
	 */
	@RequestMapping(value = "/serviceRunklist")
	public String serviceRunklist(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JSONData<ServiceView> jd = serviceBiz.findServicesData(0, 8,
				"serviceNum DESC,", null);
		model.addAttribute("serviceList", jd.getData());
		return "layout/node/serviceRunklist";
	}

	/**
	 * 子窗口珠宝首页顶部搜索服务
	 * 
	 * @author liuliping
	 * @since 2013-08-28
	 * @param name
	 *            服务名称
	 * @param start
	 *            起始
	 * @param limit
	 *            分页条数
	 * @param request
	 * @param response
	 * @param model
	 * @return node/serviceList 跳转到serviceList.jsp
	 */
	@RequestMapping(value = "/searchService")
	public String searchService(String name, int start, int limit,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<Service> serviceList = serviceBiz.findServiceListByName(name,
				limit, start);
		model.addAttribute("serviceList", serviceList);
		return "node/serviceList";
	}

	/**
	 * @author cs 注册第一步
	 */
	@RequestMapping(value = "/signup")
	public String register_step_1(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "register";
	}

	/**
	 * 邮箱激活
	 * 
	 * @author cs
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/active")
	public String active(HttpServletRequest request, Model model,HttpServletResponse response) {
		String checkcode = request.getParameter("checkcode");
		String username = request.getParameter("username");
		if (checkcode != null && !"".equals(checkcode) && username != null&& !"".equals(username)) {
			User user = userBiz.findUserByName(username).get(0);
			if (userBiz.findUserByName(username) != null&& userBiz.findUserByName(username).size() != 0) {
				// String check=checkcode.split("\\|")[0];
				long data = System.currentTimeMillis()- Long.parseLong(checkcode.split("\\|")[1]);
				
				//不管激活是否成功，只要地址正确，就让sendEmail类的处理字段变为真，默然为false
				if(!user.getSendEmail().isHandle()){
					SendEmail sendEmail = user.getSendEmail();
					sendEmail.setHandle(true);
					sendEmailBiz.saveOrUpdate(sendEmail);
				}
				
				// 当时间小于24小时有效
				if (data < 86400000) {
					if (user.getCheckcode().equals(checkcode)) {
						if (user.getIsApproved()) {
							request.setAttribute("message", "你已经激活了，不需要再次激活");
							return "active_success";
						} else {
							user.setApproved(true);
							userBiz.update(user);
							// 同步至窗口
							SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
									UserSyncBean(user, SyncFactory.getSyncType(user.getEnterprise()))));
							
							request.setAttribute("message", "恭喜你，邮箱成功激活");
							return "active_success";
						}
					} else {
						request.setAttribute("message", "激活码不正确!");
						return "active_success";
					}
				} else {
					request.setAttribute("message", "验证时间已过24小时，请重新申请激活!");
					return "active_success";
				}
			} else {
				request.setAttribute("message", "用户名不正确!");
				return "active_success";
			}
		} else {
			request.setAttribute("message", "链接地址不正确!");
			return "active_success";
		}
	}

	/**
	 * @author wzlee 跳转至登录页面
	 */
	@RequestMapping(value = "/login")
	public String userLogin(@RequestParam(required=false,defaultValue="")String redirect_url,HttpServletRequest request,HttpServletResponse response, Model model) {
		String _redirect_url = redirect_url.equals("")?request.getHeader("referer"):redirect_url;
		model.addAttribute("redirect_url", _redirect_url);
		return "login";
	}

	/**
	 * 用户退出
	 * @param request
	 * @param response
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/logout")
	public String logout(@RequestParam(required=false,defaultValue="")String redirect_url,HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		String _redirect_url = redirect_url.equals("")?request.getHeader("referer"):redirect_url;
		Cookie SM_LOGIN = CookieHelper.getCookieByName(request, ResourceBundle.getBundle("config").getString("ucenter.cookie.pre"));
		if(SM_LOGIN == null){
			return "redirect:"+_redirect_url;
		}else{
			response.addHeader("P3P"," CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
			Cookie cookie = new Cookie(ResourceBundle.getBundle("config").getString("ucenter.cookie.pre"),"");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			List<String> srcs = new ArrayList<String>();
			for(Flat flat:flatBiz.queryExClient_id(ResourceBundle.getBundle("config").getString("oauth.client_id"))){
				Long time = System.currentTimeMillis();
				String params = MD5.toMD5(flat.getClient_secret());
				String code = new String(Base64.encode(params.getBytes("utf-8")));
				srcs.add(flat.getUcenter_api()+"?time="+time+"&action=synlogout&code="+URLEncoder.encode(code, "utf-8"));
			}
			model.addAttribute("message", "退出成功");
			model.addAttribute("srcs", srcs);
			model.addAttribute("redirect_url", redirect_url);
			return "oauth/sync";
		}
	}
	
	/**
	 * 入口 ：localhost的登录
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/verify")
	public void userVerify(@RequestParam String username, @RequestParam String password,@RequestParam String authcode,@RequestParam String redirect_url,HttpServletRequest request, HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		if (authcode.equals(request.getSession().getAttribute("authcode").toString())) {
			Flat flat = flatBiz.queryByClient_id(ResourceBundle.getBundle("config").getString("oauth.client_id"));
			List<User> list = userBiz.findUserByName(username);
			if (list != null && list.size() != 0) {
				User user = list.get(0);
				if (user.getPassword().equals(password)) {
					if(user.getUserStatus()== Constant.EFFECTIVE){
						user.setStatus(new Date().getTime());
						userBiz.update(user);
						request.getSession().setAttribute("user", user);
						request.getSession().setAttribute("usertype", Constant.LOGIN_USER);
						//保存user登录情况下的user对象(目的：区分个人用户和企业用户)
						request.getSession().setAttribute("loginUser", user);
						if(!user.getIsPersonal()){
							//保存登录者企业信息
							request.getSession().setAttribute("loginEnterprise", user.getEnterprise());
							//得到登录者企业的信誉评价并保存
							EnterpriseCredit ec = ecBiz.queryByEnterprise(user.getEnterprise().getId());
							request.getSession().setAttribute("enterpriseCredit", ec);
						}
						Long time = System.currentTimeMillis();
						String params = MD5.toMD5(flat.getClient_secret())+"&user|"+user.getUid()+"|"+MD5.toMD5(user.getUserName()+user.getPassword())+"&"+redirect_url;
						String code = new String(Base64.encode(params.getBytes("utf-8")));
						this.outJson(response, new JSONResult(true,flat.getRedirect_url()+"?time="+time+"&code="+URLEncoder.encode(code,"utf-8")));
					}else if(user.getUserStatus()== Constant.DISABLED){
						this.outJson(response, new JSONResult(false, "账号被禁用！"));
					}else{
						this.outJson(response, new JSONResult(false, "账号被删除！"));
					}
				} else {
					this.outJson(response, new JSONResult(false, "密码输入有误!"));
				}
			} else if (staffBiz.findByUserName(username) != null) {
				Staff staff = staffBiz.findByUserName(username);
				if (staff.getPassword().equals(password)) {
					if(staff.getStaffStatus() == Constant.EFFECTIVE){
						request.getSession().setAttribute("user", staff);
						request.getSession().setAttribute("usertype", Constant.LOGIN_STAFF);
						//保存登录者企业信息
						request.getSession().setAttribute("loginEnterprise", staff.getParent().getEnterprise());
						//得到登录者企业的信誉评价并保存
						EnterpriseCredit ec = ecBiz.queryByEnterprise(staff.getParent().getEnterprise().getId());
						request.getSession().setAttribute("enterpriseCredit", ec);
						
						List<StaffMenu> staffmenu = staffMenuBiz.findMenus(staff.getStaffRole().getMenuIds());
						request.getSession().setAttribute("staffmenu", StaffTree.getResult(staffmenu));
						staff.setStatus(new Date().getTime());
						staffBiz.update(staff);
						Long time = System.currentTimeMillis();
						String params = MD5.toMD5(flat.getClient_secret())+"&staff|"+staff.getStid()+"|"+MD5.toMD5(staff.getUserName()+staff.getPassword())+"&"+redirect_url;
						String code = new String(Base64.encode(params.getBytes("utf-8")));
						this.outJson(response, new JSONResult(true,flat.getRedirect_url()+"?time="+time+"&code="+URLEncoder.encode(code,"utf-8")));
					}else if(staff.getStaffStatus() == Constant.DISABLED){
						this.outJson(response, new JSONResult(false, "账号被禁用！"));
					}else{
						this.outJson(response, new JSONResult(false, "账号被删除！"));
					}
				} else {
					this.outJson(response, new JSONResult(false, "密码输入有误!"));
				}
			} else {
				this.outJson(response, new JSONResult(false, "用户名不存在!"));
			}
		} else {
			this.outJson(response, new JSONResult(false, "验证码输入错误!"));
		}
	}

	/**
	 * 联系我们
	 * 
	 * @author huyj
	 * @sicen 2013-9-12
	 * @param request
	 * @param response
	 * @return 联系我们的页面
	 */
	@RequestMapping(value = "/contact")
	public String contact(HttpServletRequest request,
			HttpServletResponse response) {
		return "contact";
	}

	/**
	 * 关于我们
	 * 
	 * @author huyj
	 * @sicen 2013-9-12
	 * @param request
	 * @param response
	 * @return 关于我们的页面
	 */
	@RequestMapping(value = "/about")
	public String about(HttpServletRequest request, HttpServletResponse response) {
		return "about";
	}

	/**
	 * 平台目标
	 * 
	 * @author huyj
	 * @sicen 2013-9-12
	 * @param request
	 * @param response
	 * @return 关于我们的页面
	 */
	@RequestMapping(value = "/target")
	public String target(HttpServletRequest request,
			HttpServletResponse response) {
		return "target";
	}
	
	/**
	 * 验证session失败页面
	 * @author Xiadi
	 * @since 2013-9-25
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/error/{url}")
	public String userVerify(@PathVariable String url, HttpServletRequest request, HttpServletResponse response) {
		return "error/" + url;
	}
	
	/**
	 * @author wzlee
	 * 窗口平台请求跳转
	 */
	@RequestMapping(value = "window/{code}")
	public String directWindow(@PathVariable String code,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		Flat flat = flatBiz.queryByFlatCode(code);
		if(flat == null){
			return "error/404";
		}else{
			model.addAttribute("code", flat.getFlatCode());
			model.addAttribute("name", flat.getFlatName());
			model.addAttribute("src", flat.getDomain());
			return "window";
		}
	}
}
