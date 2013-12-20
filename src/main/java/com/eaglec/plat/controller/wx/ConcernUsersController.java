package com.eaglec.plat.controller.wx;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.biz.policy.PolicyBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.StaffMenuBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.biz.wx.ArticleInfoBiz;
import com.eaglec.plat.biz.wx.ConcernUsersBiz;
import com.eaglec.plat.controller.BaseController;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.StaffMenu;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.policy.Policy;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.domain.wx.ArticleInfo;
import com.eaglec.plat.domain.wx.ConcernUsers;
import com.eaglec.plat.domain.wx.WeiXinUser;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.StaffTree;
import com.eaglec.plat.utils.wx.WeixinUtil;
import com.eaglec.plat.view.JSONEntity;
import com.eaglec.plat.view.JSONResult;

@Controller
@RequestMapping(value = "/concernusers")
public class ConcernUsersController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ConcernUsersController.class);
	
	@Autowired
	private ConcernUsersBiz concernUsersBiz;
	
	@Autowired
	private UserBiz userBiz;
	
	@Autowired
	private StaffBiz staffBiz;
	
	@Autowired
	private PolicyBiz policyBiz;
	
	@Autowired
	private ServiceBiz serviceBiz;
	
	@Autowired
	private StaffMenuBiz staffMenuBiz;
	
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	@Autowired
	private ArticleInfoBiz articleInfoBiz;
	
	/**
	 * @date: 2013-12-14
	 * @author：lwch
	 * @description：用户在微信端扫描平台二维码后关注平台
	 */
	public void userConcern(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	
	@RequestMapping(value = "/loginVerify")
	public String loginVerify(
			@RequestParam("openid")String openid,	//微信加密后的帐号
			HttpServletRequest request, HttpServletResponse response){
		System.out.println("-------openid----------"+ openid);
		ConcernUsers cu = concernUsersBiz.getWeixinBoundInfo(openid);
		System.out.println("-------ConcernUsers----------"+ cu);
		if (cu != null) {
			request.getSession().setAttribute("concernUsers", cu);
			return "wx/user_bind_autho";	//授权登录
		} else {
			WeixinUtil.clearUserSession(request);
			return "wx/user_bind";	//跳转到微信帐号和平台用户绑定的页面
		}
	}
	
	
	/**
	 * @date: 2013-12-14
	 * @author：lwch
	 * @description：微信帐号绑定平台用户
	 */
	@RequestMapping(value = "/userBoundPlat")
	public void userBoundPlat(
			@RequestParam("username")String username,	//平台用户名
			@RequestParam("password")String password,	//密码
			@RequestParam("authcode")String authcode,	//验证码
			@RequestParam("openid")String openid,	//微信加密后的帐号
			HttpServletRequest request, HttpServletResponse response){
		String verifCode = (String)request.getSession().getAttribute("authcode");
		System.out.println(verifCode+"-----authcode----"+authcode);
		if (verifCode.equals(authcode)) {
			try {
				ConcernUsers cu = concernUsersBiz.getWeixinBoundInfo(username, openid);
				if(cu == null){
					List<User> users = userBiz.getUserInfo(username);
					System.out.println("---users---"+ users.toString());
					if (users.size() > 0) {
						User user = users.get(0);
						System.out.println("---user---"+ users);
						Map<String, Object> result = this.setupUserInfo(user, openid, password, true, request, response);
						Boolean sussce = (Boolean)result.get("sussce");
						if (sussce) {
							logger.info("平台用户："+ username +"绑定成功！");
							this.outJson(response, new JSONResult(true, "微信帐号和平台帐号绑定成功！"));
						} else {
							this.outJson(response, new JSONResult(false, (String)result.get("message"), (String)result.get("errorclass")));
						}
					} else {
						List<Staff> staffs = staffBiz.findStaffByUserName(username);
						if (staffs.size() > 0) {
							Staff staff = staffs.get(0);
							Map<String, Object> result = this.setupUserInfo(staff, openid, password, true, request, response);
							Boolean sussce = (Boolean)result.get("sussce");
							if (sussce) {
								logger.info("平台用户："+ username +"绑定成功！");
								this.outJson(response, new JSONResult(true, "微信帐号和平台帐号绑定成功！"));
							} else {
								this.outJson(response, new JSONResult(false, (String)result.get("message"), (String)result.get("errorclass")));
							}
						} else {
							this.outJson(response, new JSONResult(false, "帐号不存在！", "w-username"));
						}
					}
				} else {
					this.outJson(response, new JSONResult(false, "该帐号已经绑定过了！", "w-username"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("查找企业用户异常" + e.getLocalizedMessage());
				this.outJson(response, new JSONResult(false, "查找企业用户异常" + e.getLocalizedMessage()));
			}
		} else {
			System.out.println("------------------w-check----------------");
			this.outJson(response, new JSONResult(false, "验证码错误!", "w-check"));
		}
	}
	
	/**
	 * @date: 2013-12-14
	 * @author：lwch
	 * @description：微信端授权登录平台
	 */
	@RequestMapping(value = "/wxUserLogin")
	public void wxUserLogin(@RequestParam("openid")String openid, HttpServletRequest request, HttpServletResponse response){
		ConcernUsers cu = null;
		System.out.println("---------wxUserLogin---------openid-----"+ openid);
		cu = (ConcernUsers)request.getSession().getAttribute("concernUsers");
		System.out.println("---------wxUserLogin---------ConcernUsers-----"+ cu);
		if (cu != null) {
			System.out.println("---------wxUserLogin---------ConcernUsers---------session-------");
			this.userlogin(openid, cu, request, response);
		} else {
			cu = concernUsersBiz.getWeixinBoundInfo(openid);
			System.out.println("---------wxUserLogin---------ConcernUsers---------sql-----cu--"+ cu);
			if (cu != null) {
				System.out.println("---------wxUserLogin---------ConcernUsers---------sql-------");
				request.getSession().setAttribute("concernUsers", cu);
				this.userlogin(openid, cu, request, response);
			} else {
				this.outJson(response, new JSONResult(false, "你的微信帐号还未绑定平台用户！"));
			}
		}
	}
	
	private void userlogin(String openid, ConcernUsers cu, HttpServletRequest request, HttpServletResponse response){
		String username = cu.getUsername();
		List<User> users = userBiz.getUserInfo(username);
		if (users.size() > 0) {
			User user = users.get(0);
			Map<String, Object> result = this.setupUserInfo(user, openid, null, false, request, response);
			Boolean sussce = (Boolean)result.get("sussce");
			if (sussce) {
				logger.info("平台用户："+ username +"登录成功！");
				this.outJson(response, new JSONResult(true, "登录成功！"));
			} else {
				this.outJson(response, new JSONResult(false, (String)result.get("message")));
			}
		} else {
			List<Staff> staffs = staffBiz.findStaffByUserName(username);
			if (staffs.size() > 0) {
				Staff staff = staffs.get(0);
				Map<String, Object> result = this.setupUserInfo(staff, openid, null, false, request, response);
				Boolean sussce = (Boolean)result.get("sussce");
				if (sussce) {
					logger.info("平台用户："+ username +"登录成功！");
					this.outJson(response, new JSONResult(true, "登录成功！"));
				} else {
					this.outJson(response, new JSONResult(false, (String)result.get("message")));
				}
			} else {
				this.outJson(response, new JSONResult(false, "帐号不存在！"));
			}
		}
	}
	
	/**
	 * @date: 2013-12-18
	 * @author：lwch
	 * @description：用户授权登录成功后跳转到首页
	 */
	@RequestMapping(value = "/pageSkip")
	public String pageSkip(
			@RequestParam("openid")String openid,
			@RequestParam("page")String page,
			HttpServletRequest request, HttpServletResponse response){
		System.out.println("-------request.getSession().getAttribute----concernUsers-----------"+request.getSession().getAttribute("concernUsers"));
		System.out.println("--------pageSkip----------openid---------"+openid);
		System.out.println("--------pageSkip----------page---------"+page);
		ConcernUsers cu = concernUsersBiz.getWeixinBoundInfo(openid);
		System.out.println("--------pageSkip----------ConcernUsers---------"+cu);
		if (cu != null) {
			request.getSession().setAttribute("concernUsers", cu);
			if (page.equals("wx/index")) {
				//获取服务机构
				List<Enterprise> enterpriseList = enterpriseBiz.findEnterprieByWX();
				//获取服务
				List<Service> serviceList = serviceBiz.findServiceByWX();
				//获取政策
				List<Policy> policyList = policyBiz.findPolicyByWX();
				//推送消息
				List<ArticleInfo> articleList = articleInfoBiz.findList("", 0, 6);
				request.setAttribute("indexPolicyList", policyList);
				request.setAttribute("indexServiceList", serviceList);
				request.setAttribute("indexEnterpriseList", enterpriseList);
				request.setAttribute("articleList", articleList);
			}
			return page;
		} else {
			return "error/500";
		}
	}
	
	/**
	 * @date: 2013-12-17
	 * @author：lwch
	 * @description：用户成功登录后，保存他的相关信息到session里面
	 */
	private Map<String, Object> setupUserInfo(
			Object object,
			String openid,
			String password,
			Boolean isBound,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User user = (User)object;
			System.out.println("---setupUserInfo-----users---"+ user);
			Integer status = user.getUserStatus();
			System.out.println("---setupUserInfo-----status---"+ status);
			switch (status) {
				case 1:
					if (isBound) {
						String passd = user.getPassword();
						System.out.println("---setupUserInfo-----passd---"+ passd);
						if(passd.equals(password)){
							System.out.println("---setupUserInfo-----passd---success");
							this.getWeixinUserInfo(user, openid, request);
							System.out.println("---setupUserInfo-----getWeixinUserInfo---success");
						} else {
							result.put("sussce", false);
							result.put("errorclass", "w-pwd");
							result.put("message", "密码不正确！");
							return result;
						}
					} else {
						user.setStatus(new Date().getTime());
						userBiz.update(user);
						request.getSession().setAttribute("user", user);
						request.getSession().setAttribute("usertype", Constant.LOGIN_USER);
						if(!user.getIsPersonal()){
							//保存登录者企业信息
							request.getSession().setAttribute("loginEnterprise", user.getEnterprise());
						}
					}
					result.put("sussce", true);
					break;
				case 2:
					result.put("sussce", false);
					result.put("errorclass", "w-username");
					result.put("message", "帐号已被禁用！");
					break;
				case 3:
					result.put("sussce", false);
					result.put("errorclass", "w-username");
					result.put("message", "帐号已被删除！");
					break;
			}
			return result;
			
		} catch (Exception e) {
			System.out.println("---setupUserInfo--------eeeeee------");
			Staff staff = (Staff)object;
			Integer status = staff.getStaffStatus();
			System.out.println("---setupUserInfo--------eeeeee---status---"+ status);
			switch (status) {
				case 1:
					if (isBound) {
						String passd = staff.getPassword();
						System.out.println("---setupUserInfo--------eeeeee---passd---"+ passd);
						if(passd.equals(password)){
							System.out.println("---setupUserInfo--------eeeeee---success---");
							this.getWeixinUserInfo(staff, openid, request);
							System.out.println("---setupUserInfo--------eeeeee-------getWeixinUserInfo-----success---");
						} else {
							result.put("sussce", false);
							result.put("errorclass", "w-pwd");
							result.put("message", "密码不正确！");
							return result;
						}
					} else {
						staff.setStatus(new Date().getTime());
						staffBiz.update(staff);
						//保存登录者企业信息
						request.getSession().setAttribute("user", staff);
						request.getSession().setAttribute("usertype", Constant.LOGIN_STAFF);
						request.getSession().setAttribute("loginEnterprise", staff.getParent().getEnterprise());
						List<StaffMenu> staffmenu = staffMenuBiz.findMenus(staff.getStaffRole().getMenuIds());
						request.getSession().setAttribute("staffmenu", StaffTree.getResult(staffmenu));
						System.out.println("---setupUserInfo--------eeeeee----------success---");
					}
					result.put("sussce", true);
					break;
				case 2:
					result.put("sussce", false);
					result.put("errorclass", "w-username");
					result.put("message", "帐号已被禁用！");
					break;
				case 3:
					result.put("sussce", false);
					result.put("errorclass", "w-username");
					result.put("message", "帐号已被删除！");
					break;
			}
			return result;
		}
	}
	
	/**
	 * @date: 2013-12-16
	 * @author：lwch
	 * @description：添加微信帐号和平台用户绑定的记录
	 */
	private void getWeixinUserInfo(Object object, String openid, HttpServletRequest request){
		System.out.println("---getWeixinUserInfo-----");
		try {
			WeiXinUser wxu = (WeiXinUser)request.getSession().getAttribute("weixinUser");
			System.out.println("---getWeixinUserInfo-----WeiXinUser-----"+ wxu);
			if (wxu != null) {
				ConcernUsers cu = new ConcernUsers();
				System.out.println("---getWeixinUserInfo-----ConcernUsers-----");
				try {
					User u = (User)object;
					System.out.println("---1111111111111111111-----");
					cu.setUsername(u.getUserName());
					cu.setEnterprise_id(u.getEnterprise().getId());
					cu.setEnterprise_name(u.getEnterprise().getName());
					System.out.println("---2222222222222222-----");
				} catch (Exception e) {
					Staff s = (Staff)object;
					System.out.println("---3333333333333-----");
					cu.setUsername(s.getUserName());
					cu.setEnterprise_id(s.getParent().getEnterprise().getId());
					cu.setEnterprise_name(s.getParent().getEnterprise().getName());
					System.out.println("---444444444444444-----");
				}
				System.out.println("---55555555555555555-----");
				cu.setWxUserToken(wxu.getOpenid());//加密过后的微信帐号
				cu.setWx_user_name(wxu.getNickname());//微信帐号昵称
				cu.setWx_user_headimgurl(wxu.getHeadimgurl());//微信用户头像
				cu.setWx_user_sex(wxu.getSex());//微信用户性别
				cu.setWx_user_language(wxu.getLanguage());//微信用户使用的语言
				cu.setWx_user_country(wxu.getCountry());//微信用户所属的国家
				cu.setWx_user_province(wxu.getProvince());//微信用户所在的省份
				cu.setWx_user_city(wxu.getCity());//微信用户所在的城市
				cu.setSubscribe_time(wxu.getSubscribe_time());//微信用户扫描二维码时间
				cu.setConcern_status(true);//微信用户关注的状态
				System.out.println("---66666666666666666666-----");
				concernUsersBiz.addConcernUsers(cu);
				System.out.println("---7777777777777777777-----");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-12-18
	 * @author：lwch
	 * @description：根据微信帐号加密后的ID或者是平台帐号，获取绑定
	 */
	@RequestMapping(value = "/getBindUser")
	public void getBindUser(
			@RequestParam("openid")String openid,
			@RequestParam("username")String username,
			HttpServletRequest request, HttpServletResponse response){
		try {
			ConcernUsers cu = concernUsersBiz.getWeixinBoundInfo(openid, username);
			this.outJson(response, new JSONEntity<ConcernUsers>(true, cu));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, "获取绑定用户异常", e.getLocalizedMessage()));
		}
	}
}



