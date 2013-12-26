package com.eaglec.plat.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.business.BiddingServiceBiz;
import com.eaglec.plat.biz.business.GoodsOrderBiz;
import com.eaglec.plat.biz.business.ResponseBiddingBiz;
import com.eaglec.plat.biz.flat.FlatBiz;
import com.eaglec.plat.biz.info.MessageBiz;
import com.eaglec.plat.biz.info.MessageClassBiz;
import com.eaglec.plat.biz.info.ReceiverMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderMessageRelationshipBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.publik.FileManagerBiz;
import com.eaglec.plat.biz.service.MyFavoritesBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.ApproveBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.SendEmailBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.StaffRoleBiz;
import com.eaglec.plat.biz.user.UserApproveBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.ApprovedDetail;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.Flat;
import com.eaglec.plat.domain.base.OrgRegisterApproval;
import com.eaglec.plat.domain.base.SendEmail;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.StaffRole;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.base.UserApprovedDetail;
import com.eaglec.plat.domain.business.BiddingService;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.domain.info.Message;
import com.eaglec.plat.domain.info.MessageClass;
import com.eaglec.plat.domain.info.ReceiverMessageRelationship;
import com.eaglec.plat.domain.info.SenderMessageRelationship;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.bean.ApprovedDetailSyncBean;
import com.eaglec.plat.sync.bean.EnterpriseSyncBean;
import com.eaglec.plat.sync.bean.UserSyncBean;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
import com.eaglec.plat.utils.Base64;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.MD5;
import com.eaglec.plat.utils.RandomUtils;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.APIResult;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.MyFavoritesView;

@Controller
@RequestMapping(value="/ucenter")
public class UcenterController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(UcenterController.class);
	@Autowired
	private MessageBiz messageBiz;
	@Autowired
	private MessageClassBiz messageClassBiz;
	@Autowired
	private ReceiverMessageRelationshipBiz receiverMessageRelationshipBiz;
	@Autowired
	private SenderMessageRelationshipBiz senderMessageRelationshipBiz;
	@Autowired
	private UserBiz userBiz;	
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private FileManagerBiz fileManagerBiz;
	@Autowired
	private CategoryBiz categoryBiz;
	@Autowired
	private ApproveBiz approveBiz;
	@Autowired
	private ServiceBiz serviceBiz;
	@Autowired
	private GoodsOrderBiz orderBiz;
	@Autowired
	private BiddingServiceBiz biddingServiceBiz;
	@Autowired
	private ResponseBiddingBiz responseBiddingBiz;
	@Autowired
	private SendEmailBiz sendEmailBiz;
	@Autowired
	private StaffBiz staffBiz;
	@Autowired 
	private StaffRoleBiz staffRoleBiz;
	@Autowired
	private UserApproveBiz userApproveBiz;
	@Autowired
	private MyFavoritesBiz myFavoritesBiz;
	@Autowired
	private ReceiverMessageRelationshipBiz rmrsBiz;
	@Autowired
	private FlatBiz flatBiz;
	
	/**
	 * 同步登陆
	 * @param client_id
	 * @param redirect_url
	 * @param response_type
	 * @param state
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String oauthAuthorize(@RequestParam(required=true,defaultValue="")String client_id,@RequestParam(required=false,defaultValue="")String redirect_url,@RequestParam(required=false,defaultValue="code")String response_type,@RequestParam(required=false)String state,HttpServletRequest request,HttpServletResponse response, Model model) {
		if(client_id.equals("")){
			model.addAttribute("errorCode", 10100);
			model.addAttribute("errorMessage", "MISSING　REQUIRED PARAMS[缺少必需参数:client_id(客户端ID)]");
			return "oauth/error";
		}else{
			Flat flat = flatBiz.queryByClient_id(client_id);
			if(flat == null){
				model.addAttribute("errorCode", 10101);
				model.addAttribute("errorMessage", "CLIENT_ID INVALID[客户端ID无效]");
				return "oauth/error";
			}else{
				model.addAttribute("client_id", client_id);
				model.addAttribute("redirect_url", redirect_url.equals("")?request.getHeader("referer"):redirect_url);
				model.addAttribute("response_type", response_type);
				model.addAttribute("state", state);
				return "oauth/login";
			}
		}
	}
	
	/**
	 * 同步登陆
	 * @param client_id
	 * @param redirect_url
	 * @param response_type
	 * @param state
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/signup")
	public String oauthSignup(@RequestParam(required=true,defaultValue="")String client_id,@RequestParam(required=false,defaultValue="")String redirect_url,@RequestParam(required=false,defaultValue="code")String response_type,@RequestParam(required=false)String state,HttpServletRequest request,HttpServletResponse response, Model model) {
		if(client_id.equals("")){
			model.addAttribute("errorCode", 10100);
			model.addAttribute("errorMessage", "MISSING　REQUIRED PARAMS[缺少必需参数:client_id(客户端ID)]");
			return "oauth/500";
		}else{
			Flat flat = flatBiz.queryByClient_id(client_id);
			if(flat == null){
				model.addAttribute("errorCode", 10101);
				model.addAttribute("errorMessage", "CLIENT_ID INVALID[客户端ID无效]");
				return "oauth/500";
			}else{
				model.addAttribute("client_id", client_id);
				model.addAttribute("redirect_url", redirect_url.equals("")?request.getHeader("referer"):redirect_url);
				return "register";
			}
		}
	}
	
	/**
	 * 客户端获取应用信息
	 * @param client_id
	 * @param client_secret
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/clients")
	public void oauthClients(@RequestParam(required=true,defaultValue="")String client_id,@RequestParam(required=true,defaultValue="")String client_secret,HttpServletRequest request,HttpServletResponse response, Model model) {
		if(client_id.equals("") || client_secret.equals("")){
			this.outJson(response, new APIResult(false, 10100, "MISSING REQUIRED　PARAMS[缺少必需参数]"));
		}else{
			Flat flat = flatBiz.queryByClient_idAndCliet_secret(client_id, client_secret);
			if(flat == null){
				this.outJson(response, new APIResult(false, 10101, "INVALID CLIENT[无效的客户端]"));
			}else{
				PropertyFilter propertyFilter = new PropertyFilter() {
					@Override
					public boolean apply(Object source, String name,Object value) {
						if(name.equals("ucenter_api") || name.equals("client_secret")){
							return true;
						}
						return false;
					}
				};
				this.outJson(response, flatBiz.queryExClient_id(client_id),propertyFilter);
			}
		}
	}
	
	/**
	 * 同步登陆用户验证
	 * @param authcode
	 * @param username
	 * @param password
	 * @param client_id
	 * @param redirect_url
	 * @param response_type
	 * @param state
	 * @param request
	 * @param response
	 * @param model
	 * @param attr
	 * @throws IOException
	 */
	@RequestMapping(value = "/verify")
	public void userVerify(@RequestParam String authcode,@RequestParam String username,@RequestParam String password,@RequestParam String client_id,@RequestParam String redirect_url,@RequestParam String response_type,@RequestParam String state,HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes attr) throws IOException {
		if (authcode.equals(request.getSession().getAttribute("authcode").toString())) {
			List<User> user_list = userBiz.findUserByName(username);
			Flat flat = flatBiz.queryByClient_id(client_id);
			if (user_list.size() == 0){
				Staff staff = staffBiz.findByUserName(username);
				if(staff == null){
					this.outJson(response, new JSONResult(false, "用户名不存在!"));
				}else{
					if(staff.getPassword().equals(password)){
						if(staff.getStaffStatus() == Constant.EFFECTIVE){
							Long time = System.currentTimeMillis();
							String params = MD5.toMD5(flat.getClient_secret())+"&staff|"+staff.getStid()+"|"+MD5.toMD5(staff.getUserName()+staff.getPassword())+"&"+redirect_url;
							String code = new String(Base64.encode(params.getBytes("utf-8")));
							this.outJson(response, new JSONResult(true,flat.getRedirect_url()+"?time="+time+"&code="+URLEncoder.encode(code,"utf-8")));
						}else if(staff.getStaffStatus()== Constant.DISABLED){
							this.outJson(response, new JSONResult(false, "账号被禁用！"));
						}else{
							this.outJson(response, new JSONResult(false, "账号被删除！"));
						}
					}
				}
			}else {
				User u = user_list.get(0);
				if (u.getPassword().equals(password)) {
					if(u.getUserStatus() == Constant.EFFECTIVE){
						Long time = System.currentTimeMillis();
						String params = MD5.toMD5(flat.getClient_secret())+"&user|"+u.getUid()+"|"+MD5.toMD5(u.getUserName()+u.getPassword())+"&"+redirect_url;
						String code = new String(Base64.encode(params.getBytes("utf-8")));
						this.outJson(response, new JSONResult(true,flat.getRedirect_url()+"?time="+time+"&code="+URLEncoder.encode(code,"utf-8")));
					}else if(u.getUserStatus()== Constant.DISABLED){
						this.outJson(response, new JSONResult(false, "账号被禁用！"));
					}else{
						this.outJson(response, new JSONResult(false, "账号被删除！"));
					}
				} else {
					this.outJson(response, new JSONResult(false, "密码输入有误!"));
				}
			}
		} else {
			this.outJson(response, new JSONResult(false, "验证码输入错误!"));
		}
	}
	
	/**
	 * 同步登陆UCENTER相关API
	 * @param time
	 * @param code
	 * @param request
	 * @param response
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/api")
	@ResponseBody
	public String syncLogin(@RequestParam Long time,@RequestParam String action,@RequestParam String code,HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes attr){
		if(System.currentTimeMillis() - time > 1000*60*10){
			logger.info("AUTHRACATION EXPIRIED");
			return "AUTHRACATION EXPIRIED";
		}
		String _code = new String(Base64.decode(code.toCharArray()));
		String[] params = _code.split("&");
		if(params[0].equals(MD5.toMD5(ResourceBundle.getBundle("config").getString("oauth.client_secret")))){
			if(action.equals("synlogin")) {
				response.addHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
				int $cookietime = 31536000;
				Cookie cookie = new Cookie(ResourceBundle.getBundle("config").getString("ucenter.cookie.pre"),params[1]);
				cookie.setMaxAge($cookietime);
				cookie.setPath("/");
				response.addCookie(cookie);
				logger.info("SYNLOGIN API_RETURN_SUCCEED");
				return "SYNLOGIN API_RETURN_SUCCEED";
			} else if(action.equals("synlogout")) {
				response.addHeader("P3P"," CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
				Cookie cookie = new Cookie(ResourceBundle.getBundle("config").getString("ucenter.cookie.pre"),"");
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
				logger.info("SYNLOGOUT API_RETURN_SUCCEED");
				return "SYNLOGOUT API_RETURN_SUCCEED";
			} else {
				logger.info("API_RETURN_FORBIDDEN");
				return "API_RETURN_FORBIDDEN";
			}		
		}else{
			logger.info("API_RETURN_FORBIDDEN");
			return "API_RETURN_FORBIDDEN";
		}
	}
//	public String syncLogin(@RequestParam Long time,@RequestParam String code,HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes attr){
//		if(System.currentTimeMillis() - time > 1000*60*10){
//			return "AUTHRACATION EXPIRIED";
//		}else{
//			String _code = new String(Base64.decode(code.toCharArray()));
//			model.addAttribute("message", _code);
//			return "error/500";
//		}
		
		
//		if(code == null || code.isEmpty()) return "API_RETURN_FORBIDDEN";
//		Map<String,String> paramMap = new HashMap<String, String>();
//		String _code;
//		try {
//			_code = UcenterUtils.uc_authcode(code, "DECODE", ResourceBundle.getBundle("config").getString("oauth.client_secret"), 0);
//			String[] params = _code.split("&");
//			for(int i=0;i<params.length;i++){
//				String[] items = params[i].split("=");
//				if(items.length==2){
//					paramMap.put(items[0], items[1]);
//				}else if(items.length ==1){
//					paramMap.put(items[0], "");
//				}
//			}
//			if(paramMap.isEmpty()) {
//				return "INVALID REQUEST";
//			}
//			if(new Date().getTime() - Long.parseLong(paramMap.get("time")) > 3600) {
//				return "AUTHRACATION EXPIRIED";
//			}
//			
//			String action = paramMap.get("action");
//			if(action == null || action.isEmpty()) return "API_RETURN_SUCCEED";
//			if(action.equals("synlogin")) {
//				response.addHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
//				int $cookietime = 31536000;
//				Cookie cookie = new Cookie(ResourceBundle.getBundle("config").getString("ucenter.cookie.pre"),paramMap.get("type")+"|"+paramMap.get("uid")+"|"+paramMap.get("token"));
//				cookie.setMaxAge($cookietime);
//				cookie.setPath("/");
//				response.addCookie(cookie);
//				return "SYNLOGIN API_RETURN_SUCCEED";
//			} else if(action.equals("synlogout")) {
//				response.addHeader("P3P"," CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
//				Cookie cookie = new Cookie(ResourceBundle.getBundle("config").getString("ucenter.cookie.pre"),"");
//				cookie.setMaxAge(0);
//				response.addCookie(cookie);
//				return "SYNLOGOUT API_RETURN_SUCCEED";
//			} else {
//				return "API_RETURN_FORBIDDEN";
//			}		
//		} catch (Exception e) {
//			return "DECODE CODE　EXCEPTION";
//		}
//	}
	
	/**
	 * 同步登陆核心处理流程
	 * @param time
	 * @param code
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/sync")
	public String UcenterSync(@RequestParam Long time,@RequestParam String code,HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		if(System.currentTimeMillis() - time > 1000*60*10){
			model.addAttribute("message", "AUTHRACATION EXPIRIED[请求超时!]");
			return "error/500";
		}else{
			String _code = new String(Base64.decode(code.toCharArray()));
			String[] params = _code.split("&");
			if(params[0].equals(MD5.toMD5(ResourceBundle.getBundle("config").getString("oauth.client_secret")))){
				response.addHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
				int $cookietime = 31536000;
				Cookie cookie = new Cookie(ResourceBundle.getBundle("config").getString("ucenter.cookie.pre"),params[1]);
				cookie.setMaxAge($cookietime);
				cookie.setPath("/");
				response.addCookie(cookie);
				
				List<Flat> flats = flatBiz.queryExClient_id(ResourceBundle.getBundle("config").getString("oauth.client_id"));
				List<String> srcs = new ArrayList<String>();
				for(Flat flat:flats){
					String login_params = MD5.toMD5(flat.getClient_secret())+"&"+params[1];
					String login_code = new String(Base64.encode(login_params.getBytes("utf-8")));
					srcs.add(flat.getUcenter_api()+"?time="+time+"&action=synlogin&code="+URLEncoder.encode(login_code, "utf-8"));
				}
				model.addAttribute("message", "登陆成功");
				model.addAttribute("srcs", srcs);
				model.addAttribute("redirect_url", params[2]);
				return "oauth/sync";
			}else{
				model.addAttribute("message", "CLIENT_SECRET_INVALID[客户端密钥无效]");
				return "error/500";
			}
		}
	}
//	public String UcenterSync(@RequestParam Long time,@RequestParam String code,HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {
//		Map<String,String> paramMap = new HashMap<String, String>();
//		String _code;
//		try {
//			_code = UcenterUtils.uc_authcode(code, "DECODE", ResourceBundle.getBundle("config").getString("oauth.client_secret"), 0);
//		} catch (Exception e) {
//			model.addAttribute("message", "DECODE CODE　EXCEPTION[CODE解码异常!]");
//			return "error/500";
//		}
//		String[] params = _code.split("&");
//		for(int i=0;i<params.length;i++){
//			String[] items = params[i].split("=");
//			if(items.length==2){
//				paramMap.put(items[0], items[1]);
//			}else if(items.length ==1){
//				paramMap.put(items[0], "");
//			}
//		}
//		if(paramMap.isEmpty()) {
//			model.addAttribute("message", "INVALID REQUEST[请求无效!]");
//			return "error/500";
//		}
//		if(new Date().getTime() - Long.parseLong(paramMap.get("time")) > 3600) {
//			model.addAttribute("message", "AUTHRACATION EXPIRIED[请求超时!]");
//			return "error/500";
//		}
////		response.addHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
//		int $cookietime = 31536000;
//		Cookie cookie = new Cookie(ResourceBundle.getBundle("config").getString("ucenter.cookie.pre"),paramMap.get("type")+"|"+paramMap.get("uid")+"|"+paramMap.get("token"));
//		cookie.setMaxAge($cookietime);
//		cookie.setPath("/");
//		response.addCookie(cookie);
//		
//		List<Flat> flats = flatBiz.queryExClient_id(ResourceBundle.getBundle("config").getString("oauth.client_id"));
//		List<String> srcs = new ArrayList<String>();
//		for(Flat flat:flats){
//			srcs.add(flat.getUcenter_api()+"?time="+time+"&code="+URLEncoder.encode(UcenterUtils.uc_authcode("action=synlogin&type="+paramMap.get("type")+"&uid="+paramMap.get("uid")+"&token="+paramMap.get("token")+"&time="+time, "ENCODE", flat.getClient_secret(),0),"utf-8"));
//		}
//		model.addAttribute("message", "同步登陆成功");
//		model.addAttribute("srcs", srcs);
//		model.addAttribute("redirect_url", paramMap.get("redirect_url"));
//		return "oauth/sync";
//	}
	
	/**
	 * 用户管理中心首页加载数据
	 * @author xuwf
	 * @since 2013-9-23
	 * 
	 *
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response,Model model){
		Category root = categoryBiz.findRootByClazz("service");
		request.setAttribute("root", root);
		if (super.getCurrentUserType(request) == Constant.LOGIN_STAFF) {
			this.keepUserSession(request);
			Staff staff = staffBiz.getStaff(super.getStaffFromSession(request).getId());
			//消息盒子
			int[] messages = rmrsBiz.findMyMessage(staff.getId(), Constant.LOGIN_STAFF);
			if(messages[0] != 0 || messages[1] != 0){//保存消息盒子数据
				request.getSession().setAttribute("messages", messages);
			}
			return "ucenter/staff_index";			
		} else {
			User user = userBiz.findUserById(super.getUserFromSession(request).getId());
			request.getSession().setAttribute("user", user);
			//消息盒子
			int[] messages = rmrsBiz.findMyMessage(user.getId(), Constant.LOGIN_USER);
			if(messages[0] != 0 || messages[1] != 0){//保存消息盒子数据
				request.getSession().setAttribute("messages", messages);
			}
			if(user.getIsPersonal()){//个人用户
				return this.getPersonalIndex(request,user);
			}
			return this.getIndexURL(request, user);
		}
	}
	
	/**
	 * 根据不同个人用户类型加载数据(未认证用户，认证用户)
	 * @author xuwf
	 * @since 2013-10-25
	 *
	 * @param request
	 * @param user 登录用户 
	 * @return
	 */
	private String getPersonalIndex(HttpServletRequest request, User user){
		String ret = "ucenter/index";
		if(!StringUtils.isEmpty(user.getCertSign())){
			if(user.getCertSign() != Constant.APPROVED_APPLYTYPE_REALNAME){//未认证用户
				// 我的认证
				List<UserApprovedDetail> list = userApproveBiz.findApprListByUid(user.getId());
				if (list != null && list.size() > 0)
					request.setAttribute("lastappr", list.get(list.size() - 1));
				
				ret = "ucenter/index_4";
			}else{//认证用户
				// 给我推送  暂时推送5个招标
				request.setAttribute("mypushs", biddingServiceBiz.findPersonalBiddingList(user.getId(), null,
					null, null, null, Constant.BIDDING_DOING + "", 0, 5).getRows());
				ret = "ucenter/index_5";
			}
		}
		// 暂时推荐3个服务
		request.getSession().setAttribute("currentRecommendIndex", 0);
		request.setAttribute("recommends", serviceBiz.findPersonalRecommendByUser2(user, 3, 0, 30));
		
		JSONRows<MyFavoritesView> myFavoritesView = myFavoritesBiz.queryByUserId(user.getId(), 0, 3, "id", getCurrentUserType(request));
		// 显示不多于3个服务收藏
		request.setAttribute("myFavorites", myFavoritesView.getRows() );
		return ret;
	}
	
	/**
	 * 根据不同企业类型加载数据
	 * @author Xiadi
	 * @since 2013-10-16 
	 *
	 * @param request
	 * @param user 登录用户 
	 * @return
	 */
	private String getIndexURL(HttpServletRequest request, User user){
		String ret = "ucenter/index";
		
		if (user.getEnterprise().getType() == Constant.ENTERPRISE_TYPE_PUBLIC) {
			// 我的认证
			List<ApprovedDetail> list = approveBiz.findApprListByUid(user.getId());
			if (list != null && list.size() > 0)
				request.setAttribute("lastappr", list.get(list.size() - 1));
			ret = "ucenter/index_1";
		} else {
			// 我的买单
			int[] mybuys = orderBiz.findMyTradingOfBuy(user.getEnterprise().getId());
			int mybidding = biddingServiceBiz.getCountOfBidding(user);
			if (mybuys[0] != 0 || mybuys[1] != 0 || mybuys[2] != 0 || mybidding != 0) {
				request.setAttribute("mybuys", mybuys);
				request.setAttribute("mybidding", mybidding);
			} else { // 没有订单和招标记录
				// 热门服务
				request.setAttribute("hotServices", serviceBiz.getHotService(0, Common.serviceHotNum));
			}
			ret = "ucenter/index_2";
			if (user.getEnterprise().getType() == Constant.ENTERPRISE_TYPE_ORG) {
				// 我的卖单 ...应标待确认
				int[] mysells = orderBiz.findMyTradingOfSell(user.getEnterprise().getId());
				if (mysells[0] != 0 || mysells[1] != 0 ) {
					request.setAttribute("mysells", mysells);
				}
				// 给我推送  暂时推送3个招标
				request.setAttribute("mypushs", biddingServiceBiz.findBiddingList(user.getId(), null,
								null, null, null, Constant.BIDDING_DOING + "", 0, 3).getRows());
				ret = "ucenter/index_3";
			}
		}
		// 暂时推荐3个服务 
		request.getSession().setAttribute("currentRecommendIndex", 0);
//		request.setAttribute("recommends", serviceBiz.findRecommendByUser(user, 3, 0, 30));
		request.setAttribute("recommends", serviceBiz.findRecommendByUser2(user, 3, 0, 30));
		
		JSONRows<MyFavoritesView> myFavoritesView = myFavoritesBiz.queryByUserId(user.getId(), 0, 3, "id", getCurrentUserType(request));
		// 显示不多于3个服务收藏
		request.setAttribute("myFavorites", myFavoritesView.getRows() );
		return ret;
	}
	
	/**
	 * 换一换 —— 企业用户中心首页 
	 * @author Xiadi
	 * @since 2013-11-1 
	 *
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/recommendChange")
	public void recommendChange(HttpServletRequest request, HttpServletResponse response){
		try{
			User user = userBiz.findUserById(super.getUserFromSession(request).getId());
			// 换一换次数 + 1
			// 暂时推荐3个服务 
			int currentRecommendIndex = StrUtils.toInt(request.getSession().getAttribute("currentRecommendIndex"));
			request.getSession().setAttribute("currentRecommendIndex", ++currentRecommendIndex);
			if(user.getIsPersonal()){//个人用户
//				super.outJson(response, serviceBiz.findPersonalRecommendByUser(user, 3, currentRecommendIndex, 30));
				super.outJson(response, serviceBiz.findPersonalRecommendByUser2(user, 3, currentRecommendIndex, 30));
			}else{
//				super.outJson(response, serviceBiz.findRecommendByUser(user, 3, currentRecommendIndex, 30));
				super.outJson(response, serviceBiz.findRecommendByUser2(user, 3, currentRecommendIndex, 30));
			}
		}catch(Exception e){
			logger.info("查找推荐服务失败,异常信息:" + e.getMessage());
			super.outJson(response, new ArrayList<Service>());
		}
	}
	
	/**
	 * 企业用户管理中心 -- 认证入口
	 * 
	 * @author Xiadi
	 * @since 2013-8-31
	 * 
	 * @param request
	 * @param response
	 * @return "ucenter/auth_list" 当用户已有认证申请记录时<br/>
	 *         "ucenter/auth_guide" 当用户首次认证申请时<br/>
	 * @eg : http://localhost/ucenter/auth
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/auth")
	public String toAuth(HttpServletRequest request, HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		request.getSession().setAttribute("user", userBiz.findUserById(user.getId()));
		if(user.getIsPersonal()){//个人用户	xuwf
			List<UserApprovedDetail> list = userApproveBiz.findApprListByUid(user.getId());
			request.setAttribute("apprlist", list);
			if(list == null || list.size() < 1)
				return "ucenter/auth_guide1";
			
			return "ucenter/auth_list1";	//有认证流水记录
		}else{//企业用户
			List<ApprovedDetail> list = approveBiz.findApprListByUid(user.getId());
			request.setAttribute("apprlist", list);
			if(user.getEnterprise().getType() != Constant.ENTERPRISE_TYPE_ORG
					&& list.size() < 1)
				return "ucenter/auth_guide";
			if(list == null || list.size() == 0){//服务机构注册审核的服务机构的用户中心的认证管理页面 xuwf 20131219
				List<OrgRegisterApproval> orgRegList = approveBiz.
					findOrgApprListByOrgName(user.getEnterprise().getId());
				request.setAttribute("apprlist", orgRegList);
				return "ucenter/auth_list2";
			}
			return "ucenter/auth_list"; 	//有认证流水记录
		}	
	}
	
	/**
	 * 转至申请表单页面
	 * 
	 * @author Xiadi
	 * @since 2013-8-31
	 * 
	 * @param url
	 * @param request
	 * @param response
	 *            eg : http://localhost/ucenter/auth_form
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/auth_{url}")
	public String toAuthForm(@PathVariable String url, HttpServletRequest request, 
			HttpServletResponse response){
		this.keepUserSession(request);
		return "ucenter/auth_" + url;
	}
	
	/**
	 * 转至前台添加服务页面
	 * 
	 * @author pangyf
	 * @since 2013-8-31
	 * @param url
	 * @param request
	 * @param response
	 *            eg : http://localhost/ucenter/service_add
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/service_add")
	public String toServiceadd(HttpServletRequest request,HttpServletResponse response){
		this.keepUserSession(request);
		return "ucenter/service_add";
	}
	
	/**
	 * 转至前台服务详情页面
	 * @author pangyf
	 * @since 2013-10-12
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/service_detail")
	public String toServicedetail(@RequestParam("id")Integer sid, HttpServletRequest request,HttpServletResponse response){
		this.keepUserSession(request);
		request.getSession().setAttribute("service", serviceBiz.findServiceById(sid));
		/**
		 * @date: 2013-12-04
		 * @author：lwch
		 * @description：在服务修改的时候，去获取该服务与哪个客服管理起来了
		 * =========================================================================
		 */
		Map<String, Object> sgMap = serviceBiz.getChatGroupIDByServiceID(sid);
		if (sgMap.size() > 0) {
			request.setAttribute("chatgroupid", (Integer)sgMap.get("chatgroupid"));
		}
		/**
		 * =========================================================================
		 */
		return "ucenter/service_detail";
	}
	
	/**
	 * 转至前台审批记录页面
	 * @author pangyf
	 * @since 2013-10-12
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/approval_list")
	public String toapprovallist(HttpServletRequest request,HttpServletResponse response){				
		String sid = request.getParameter("sid");
		this.keepUserSession(request);
		request.getSession().setAttribute("service", serviceBiz.findServiceById(Integer.parseInt(sid)));	
		return "ucenter/approval_list";
	}
	/**
	 * 验证组织机构号是否存在
	 * @author Xiadi
	 * @since 2013-9-24 
	 *
	 * @param icRegNumber
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/validateIcRegNumber")
	public void validateIcRegNumber(String icRegNumber, HttpServletRequest request, 
			HttpServletResponse response){
//		try {
//			List<Enterprise> list = enterpriseBiz.findEnterpriseByIcRegNumber(icRegNumber);
//			if(list.size() > 0 && 
//					list.get(0).getStatus() != Constant.ENTERPRISE_STATUS_DELETED &&
//					!list.get(0).getId().toString().equals(request.getParameter("eid"))){
//				super.outJson(response, new JSONResult(false, "组织机构号已存在！"));
//			}else{
				super.outJson(response, new JSONResult(true, "组织机构号可用！"));
//			}
//		} catch (Exception e) {
//			logger.info("验证组织机构号！异常信息:"+e.getLocalizedMessage());
//			super.outJson(response, new JSONResult(false, "服务器异常！"));
//		}
	}
	
	/**
	 * 验证公司实名是否存在
	 * @author Xiadi
	 * @since 2013-9-24 
	 *
	 * @param name
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/validateEname")
	public void validateEname(String name, HttpServletRequest request, 
			HttpServletResponse response){
		try {
			List<Enterprise> list = enterpriseBiz.findEnterpriseByName(name);
			if(list.size() > 0 && !list.get(0).getId().toString().equals(request.getParameter("eid"))){
				super.outJson(response, new JSONResult(false, "公司实名已存在！"));
			}else{
				super.outJson(response, new JSONResult(true, "公司实名可用！"));
			}
		} catch (Exception e) {
			logger.info("验证公司实名！异常信息:"+e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "服务器异常！"));
		}
	}
	
	/**
	 * 用户提交实名认证申请
	 * 
	 * @author Xiadi
	 * @since 2013-9-2
	 * 
	 * @param approvedDetail
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/apply")
	public void applyRealName(ApprovedDetail approvedDetail, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(approvedDetail.toString());
		try {		
			if(!approveBiz.addApply(approvedDetail)){
				super.outJson(response, new JSONResult(false, "提交失败！该用户已有相关申请,请先审核！"));
				return;
			}
			List<ApprovedDetail> list = approveBiz.findApprListByUid(approvedDetail.getUser().getId());
			// 与文件类关联
			if(approvedDetail.getApplyType() == Constant.APPROVED_APPLYTYPE_REALNAME || 
					(approvedDetail.getApplyType() == Constant.APPROVED_APPLYTYPE_ORG && 
					!approvedDetail.getEnterprise().getIsApproved())) { // 申请实名认证 或 直接申请服务机构
				fileManagerBiz.addRelateClazz(approvedDetail.getLicenceDuplicate(), "ApprovedDetail");
				fileManagerBiz.addRelateClazz(approvedDetail.getBusinessLetter(), "ApprovedDetail");
			}
			request.setAttribute("apprlist", list);
			// 同步至窗口
			approvedDetail.setEnterprise(enterpriseBiz.findByEid(approvedDetail.getEnterprise().getId()));
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ApprovedDetail>(new 
					ApprovedDetailSyncBean(approvedDetail, SyncFactory.getSyncType(approvedDetail.getEnterprise()))));
			logger.info("[ "+approvedDetail.getUser().getUserName()+" ]提交了认证申请!");
			super.outJson(response, new JSONResult(true, "认证申请提交成功！"));
		} catch (Exception e) {
			logger.info("申请实名认证失败！异常信息:"+e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "认证申请提交失败！"));
		}
	}
	
	/**
	 * 个人用户提交实名认证申请
	 * 
	 * @author xuwf
	 * @since 2013-10-28
	 * 
	 * @param userapprovedDetail
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/applyPersonal")
	public void applyPersonalRealName(UserApprovedDetail approvedDetail, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(approvedDetail.toString());
		try {		
			if(!userApproveBiz.addApply(approvedDetail)){
				super.outJson(response, new JSONResult(false, "提交失败！该用户已有相关申请,请先审核！"));
				return;
			}
			List<UserApprovedDetail> list = userApproveBiz.findApprListByUid(approvedDetail.getUser().getId());
			
			// 与文件类关联
			if(approvedDetail.getApplyType() == Constant.APPROVED_APPLYTYPE_REALNAME) {// 申请实名认证
				fileManagerBiz.addRelateClazz(approvedDetail.getPersonalPhoto(), "UserApprovedDetail");
				fileManagerBiz.addRelateClazz(approvedDetail.getIdCardPhoto(), "UserApprovedDetail");
			}
			request.setAttribute("apprlist", list);
			logger.info("[ "+approvedDetail.getUser().getUserName()+" ]提交了认证申请!");
			super.outJson(response, new JSONResult(true, "认证申请提交成功！"));
		} catch (Exception e) {
			logger.info("申请实名认证失败！异常信息:"+e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "认证申请提交失败！"));
		}
	}
	
	/**
	 * 分页查询指定类别的用户申请
	 * 
	 * @author xuwf
	 * @since 2013-10-28
	 * 
	 * @param username 		  	 申请会员名
	 * @param enterpriseName 	申请实名
	 * @param applyTimeBegin 	开始时间
	 * @param applyTimeEnd   	结束时间
	 * @param applyDesc 	   	申请类型
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/userapprovelist")
	public void queryUserApproveList(String username,String enterpriseName,String applyTimeBegin, 
		String applyTimeEnd,Integer applyType, Integer start, Integer limit,
		HttpServletRequest request, HttpServletResponse response) {
		try {
			this.outJson(response, userApproveBiz.findAll(username,enterpriseName,applyTimeBegin, 
					applyTimeEnd, applyType, start, limit));
		} catch (Exception e) {
			this.outJson( response, new JSONResult(false, 
					"查询失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("查询失败!异常信息:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 分页查询指定类别的用户申请
	 * 
	 * @author Xiadi
	 * @since 2013-8-24
	 * 
	 * @param username 申请会员名
	 * @param enterpriseName 申请实名
	 * @param applyTimeBegin 开始时间
	 * @param applyTimeEnd  结束时间
	 * @param applyDesc 申请类型
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/approvelist")
	public void queryApproveList(String username, String enterpriseName, 
			String applyTimeBegin, String applyTimeEnd,
			Integer applyType, Integer start, Integer limit,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			this.outJson(response, approveBiz.findAll(username, enterpriseName, applyTimeBegin, 
					applyTimeEnd, applyType, start, limit));
		} catch (Exception e) {
			this.outJson( response, new JSONResult(false, 
					"查询失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("查询失败!异常信息:" + e.getLocalizedMessage());
		}
	}

	/**
	 * 审核个人用户申请
	 * 
	 * @author xuwf
	 * @since 2013-10-28
	 * 
	 * @param approvedDetail
	 *            未审核的申请
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/userApprove")
	public void userApprove(UserApprovedDetail userapprovedDetail, HttpServletRequest request, 
			HttpServletResponse response){
		try {
			Manager manager = (Manager)request.getSession().getAttribute("manager");
			userapprovedDetail.setManager(manager);
			UserApprovedDetail ret = userApproveBiz.addApprove(userapprovedDetail);
			if(ret.getApproveStatus() != null){
				this.outJson(response,new JSONResult(true,"审核成功!"));
				if(ret.getApproveStatus() == 0) return;// 0 表示未通过 无需改变企业信息
				if(ret.getApplyType() == 1) { // 申请实名认证 
					ret.getUser().setRealName(ret.getName());
					ret.getUser().setPersonalPhoto(ret.getPersonalPhoto());
					ret.getUser().setIdCardPhoto(ret.getIdCardPhoto());
					ret.getUser().setCertSign(Constant.APPROVED_APPLYTYPE_REALNAME);
				}
				userBiz.update(ret.getUser());
				//用户的基本信息改变，随之改变session中的保存用户
				request.getSession().setAttribute("loginUser", ret.getUser());
				request.getSession().setAttribute("user", ret.getUser());
			}
		} catch (Exception e) {
			this.outJson( response, new JSONResult(false, 
					"查询失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("查询失败!异常信息:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 审核用户申请
	 * 
	 * @author Xiadi
	 * @since 2013-8-26
	 * 
	 * @param approvedDetail
	 *            未审核的申请
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/approve")
	public void approve(ApprovedDetail approvedDetail, HttpServletRequest request, 
			HttpServletResponse response){
		try {
			Manager manager = (Manager)request.getSession().getAttribute("manager");
			approvedDetail.setManager(manager);
			ApprovedDetail ret = approveBiz.addApprove(approvedDetail);
			if(ret.getApproveStatus() != null){
				this.outJson(response,new JSONResult(true,"审核成功!"));
				if(ret.getApproveStatus() == 0) return;// 0 表示未通过 无需改变企业信息
				if(ret.getApplyType() == 1 || 
						(ret.getApplyType() == 2 && !ret.getEnterprise().getIsApproved())) { // 申请实名认证 或 直接申请服务机构
					ret.getEnterprise().setIcRegNumber(ret.getIcRegNumber());
					ret.getEnterprise().setLicenceDuplicate(ret.getLicenceDuplicate());
					ret.getEnterprise().setBusinessLetter(ret.getBusinessLetter());
					ret.getEnterprise().setName(ret.getName());
					ret.getEnterprise().setIsApproved(true);
					ret.getEnterprise().setType(Constant.ENTERPRISE_TYPE_REALNAME);
				}
				if(ret.getApplyType() == 2) { // 实名过后 申请服务机构
					ret.getEnterprise().setType(Constant.ENTERPRISE_TYPE_ORG);
				}
				enterpriseBiz.save(ret.getEnterprise());
				
				//企业的类型改变，随之改变session中的保存用户)(支撑平台审核通过，不改变。必须需要用户重新登录)
//				request.getSession().removeAttribute("loginEnterprise");
//				request.getSession().setAttribute("loginEnterprise", ret.getEnterprise());
				// 同步至窗口
				approvedDetail = approveBiz.findById(approvedDetail.getId());
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
						EnterpriseSyncBean(ret.getEnterprise(), SyncFactory.getSyncType(ret.getEnterprise()))),true);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<ApprovedDetail>(new 
						ApprovedDetailSyncBean(approvedDetail, SyncType.ONE)));
			}
		} catch (Exception e) {
			this.outJson( response, new JSONResult(false, 
					"查询失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("查询失败!异常信息:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 查看指定企业申请服务机构的服务列表
	 * 
	 * @author Xiadi
	 * @since 2013-8-29
	 * 
	 * @param eid
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/applyServiceList")
	public void applyServiceList(Integer eid, HttpServletRequest request, 
			HttpServletResponse response){
		try {
			this.outJson(response, approveBiz.findRelationListByEid(eid));
		} catch (Exception e) {
			this.outJson( response, new JSONResult(false, 
					"查询失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("查询失败!异常信息:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 查看指定企业申请服务机构的服务列表
	 * 
	 * @author pangyf
	 * @since 2013-9-6
	 *  
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/service_list")
	public String toService(HttpServletRequest request,	HttpServletResponse response){
		this.keepUserSession(request);
		return "ucenter/service_list";
	}
	
	/**
	 * 卖家订单
	 * @author xuwf
	 * @since 2013-9-6
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/seller_order")
	public String sellerOrder(HttpServletRequest request, 
			HttpServletResponse response){
		return "ucenter/seller_order";
	}
	
	/**
	 * 卖家申诉
	 * @author xuwf
	 * @since 2013-9-9
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/seller_appeal")
	public String sellerAppeal(HttpServletRequest request, 
			HttpServletResponse response){
		return "ucenter/seller_appeal";
	}
	
	/**
	 * 买家申诉
	 * @author xuwf
	 * @since 2013-9-9
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/buyer_appeal")
	public String buyerAppeal(HttpServletRequest request, 
			HttpServletResponse response){
		return "ucenter/buyer_appeal";
	}
	
	/**
	 * 买家订单
	 * @author xuwf
	 * @since 2013-9-7
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/buyer_order")
	public String buyerOrder(HttpServletRequest request, 
			HttpServletResponse response){
		return "ucenter/buyer_order";
	}
	
	/**
	 * 转至买家关闭交易/申诉    页面 
	 * @author xuwf
	 * @since 2013-9-27
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/buyer_{url}")
	public String toBuyerClose(@PathVariable String url,HttpServletRequest request, 
			HttpServletResponse response,Model model){
		String idStr = request.getParameter("id");	//得到订单id字符串
		logger.info("id:"+idStr);
		Integer id;
		try {
			id = Integer.parseInt(idStr);
			GoodsOrder order = orderBiz.findById(id);
			model.addAttribute("goodsorder", order);
		} catch (NumberFormatException e) {
			id = 0;
			logger.info("字符串转成Integer出错");
		}
		return "ucenter/buyer_"+url;
	}
	
	/**
	 * 转至招标方关闭交易/申诉    页面 
	 * @author xuwf
	 * @since 2013-10-11
	 * 
	 * @param url
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/buyer_bidding_{url}")
	public String toBuyerBiddingClose(@PathVariable String url,HttpServletRequest request, 
			HttpServletResponse response,Model model){
		String idStr = request.getParameter("id");	//得到招单id字符串
		logger.info("id:"+idStr);
		Integer id;
		try {
			id = Integer.parseInt(idStr);
			GoodsOrder order = orderBiz.findById(id);
			BiddingService bs = order.getBiddingService();
			model.addAttribute("goodsOrder", order);
			if(bs.getRid() != null)
				model.addAttribute("responseBidding", responseBiddingBiz.getResponseBiddingById(bs.getRid()));
		} catch (NumberFormatException e) {
			id = 0;
			logger.info("字符串转成Integer出错");
		}
		return "ucenter/buyer_bidding_"+url;
	}
	
	/**
	 *  转至卖家关闭交易/确认/申诉    页面 
	 *  
	 * @author xuwf
	 * @since 2013-9-27
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/seller_{url}")
	public String toSellerClose(@PathVariable String url,HttpServletRequest request, 
			HttpServletResponse response,Model model){
		String idStr = request.getParameter("id");	//得到订单id字符串
		logger.info("id:"+idStr);
		Integer id;
		try {
			id = Integer.parseInt(idStr);
			GoodsOrder order = orderBiz.findById(id);
			model.addAttribute("goodsorder", order);
		} catch (NumberFormatException e) {
			id = 0;
			logger.info("字符串转成Integer出错");
		}
		return "ucenter/seller_"+url;
	}

	/**
	 * 转至应标方关闭交易/申诉    页面 
	 * @author xuwf
	 * @since 2013-10-11
	 * 
	 * @param url
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/seller_bidding_{url}")
	public String toSellerBiddingClose(@PathVariable String url,HttpServletRequest request, 
			HttpServletResponse response,Model model){
		String idStr = request.getParameter("id");	//得到招单id字符串
		logger.info("id:"+idStr);
		Integer id;
		try {
			id = Integer.parseInt(idStr);
			GoodsOrder order = orderBiz.findById(id);
			BiddingService bs = order.getBiddingService();
			model.addAttribute("goodsOrder", order);
			if(bs.getRid() != null)
				model.addAttribute("responseBidding", responseBiddingBiz.getResponseBiddingById(bs.getRid()));
		} catch (NumberFormatException e) {
			id = 0;
			logger.info("字符串转成Integer出错");
		}
		return "ucenter/seller_bidding_"+url;
	}
	
	/**
	 * 跳转至 主帐号管理 
	 * @author Xiadi
	 * @since 2013-9-29 
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/user_manage")
	public String toUserManage(HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");		
		user = userBiz.findUserById(user.getId());		
		request.getSession().setAttribute("user", user);
		Category root = categoryBiz.findRootByClazz("service");
		request.setAttribute("root", root);
		
		if(user.getIsPersonal()){//个人用户跳转到个人用户的账号管理
			return "ucenter/personalUser_manage";
		}
		if(user.getEnterprise().getType() == Constant.ENTERPRISE_TYPE_ORG){//服务机构主账号管理
			return "ucenter/orgUser_manage";
		}
		return "ucenter/user_manage";
	}
	
	/**
	 * 前台  给我推送  页面
	 * @author pangyf
	 * @since 2013-10-8
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/response_push")
	public String toPushBidding(HttpServletRequest request, HttpServletResponse response){
		this.keepUserSession(request);		
		return "ucenter/response_push";	
	};
	
	/**
	 * 前台  应标方应标  页面
	 * @author pangyf
	 * @since 2013-10-8
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/response_bid")
	public String toResponseBidding(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("bid");
		this.keepUserSession(request);
		request.getSession().setAttribute("bidding",biddingServiceBiz.findBiddingServiceById(Integer.parseInt(id)));
		return "ucenter/response_bid";
	};
	
	/**
	 * 前台  我的应标  页面
	 * @author pangyf
	 * @since 2013-10-8
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/my_response")
	public String toMyResponse(HttpServletRequest request, HttpServletResponse response){
		this.keepUserSession(request);
		return "ucenter/my_response";
	};
	/**
	 * 主账号管理 修改基本信息
	 * 
	 * @author huyj
	 * @sicen 2013-10-9
	 * @param user
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/saveUserInfo")
	public void saveUserInfo(User user, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			User oldUser = userBiz.findUserById(user.getId());
			oldUser.setEmail(user.getEmail());
			oldUser.setRegTime(user.getRegTime());
			oldUser.setPersonal(false);
			Enterprise oldenEnterprise = enterpriseBiz
					.loadEnterpriseByEid(oldUser.getEnterprise().getId());
			// 会员名：邮箱：企业简称：企业全称：用户类型：企业简介
			oldenEnterprise.setForShort(user.getEnterprise().getForShort());
			oldenEnterprise.setName(user.getEnterprise().getName());
			oldenEnterprise.setProfile(user.getEnterprise().getProfile());
			oldUser.setEnterprise(oldenEnterprise);
			userBiz.add(oldUser);
			
			// 同步至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
					EnterpriseSyncBean(oldUser.getEnterprise(), SyncFactory.getSyncType(oldUser.getEnterprise()))),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
					UserSyncBean(oldUser, SyncFactory.getSyncType(oldUser.getEnterprise()))));
			
			this.outJson(response, new JSONResult(true, "保存成功"));
		} catch (Exception e) {
			this.outJson(
					response,
					new JSONResult(false, "基本信息修改失败!异常信息:"
							+ e.getLocalizedMessage()));
		}
	}
	
	/**
	 * 账号管理 修改基本信息(个人用户)
	 * 
	 * @author xuwf
	 * @sicen 2013-10-26
	 * @param user
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/updateUserInfo")
	public void updateUserInfo(User user, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			User oldUser = userBiz.findUserById(user.getId());
			oldUser.setEmail(user.getEmail());
			oldUser.setAddress(user.getAddress());
			oldUser.setCertSign(user.getCertSign());
			oldUser.setRealName(user.getRealName());
			oldUser.setMobile(user.getMobile());
			oldUser.setNickName(user.getNickName());
			oldUser.setSex(user.getSex());
			userBiz.update(oldUser);
			//用户的基本信息更改，随之session的用户更改
			request.getSession().setAttribute("loginUser", oldUser);
			request.getSession().setAttribute("user", oldUser);
			this.outJson(response, new JSONResult(true, "保存成功"));
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false, "基本信息修改失败!异常信息:" 
				+ e.getLocalizedMessage()));
		}
	}

	/**
	 * 主账号管理 修改企业信息
	 * 
	 * @author huyj
	 * @sicen 2013-10-9
	 * @param user
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/saveEnterInfo")
	public void saveEnterInfo(User user, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 所属窗口：所属行业：单位性质：经营模式：年营业额：成立日期：员工人数：法人名称：企业公函：组织机构号：组织机构代码：主营产品/服务：
			User oldUser = userBiz.findUserById(user.getId());
			Enterprise oldEnterprise = enterpriseBiz
					.loadEnterpriseByEid(oldUser.getEnterprise().getId());
//			oldEnterprise.setIndustryType(user.getEnterprise()
//					.getIndustryType());// 所属窗口	  不能修改 xuwf 2013-11-25
			//企业所属行业 xuwf 2013-11-25
			oldEnterprise.setEnterpriseIndustry(user.getEnterprise().getEnterpriseIndustry());
			oldEnterprise.setEnterpriseProperty(user.getEnterprise()
					.getEnterpriseProperty());// 单位性质
			oldEnterprise.setMainRemark(user.getEnterprise().getMainRemark());// 主营产品或业务
			oldEnterprise.setBusinessPattern(user.getEnterprise()
					.getBusinessPattern());// 经营模式
			oldEnterprise.setSalesRevenue(user.getEnterprise()
					.getSalesRevenue());// 年营业额
			oldEnterprise.setStaffNumber(user.getEnterprise().getStaffNumber());// 员工人数
			oldEnterprise.setOpenedTime(user.getEnterprise().getOpenedTime()); // 成立时间
			oldEnterprise.setLegalPerson(user.getEnterprise().getLegalPerson()); // 法人名称
			oldEnterprise.setEnterpriseCode(user.getEnterprise()
					.getEnterpriseCode());// 组织机构代码
			oldEnterprise.setIcRegNumber(user.getEnterprise().getIcRegNumber());// 组织机构号
			oldUser.setEnterprise(oldEnterprise);
			userBiz.update(oldUser);
			
			// 同步至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
					EnterpriseSyncBean(oldUser.getEnterprise(), SyncFactory.getSyncType(oldUser.getEnterprise()))),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
					UserSyncBean(oldUser, SyncFactory.getSyncType(oldUser.getEnterprise()))));
			
			//用户的基本信息更改，随之session的用户更改
			request.getSession().setAttribute("loginUser", oldUser);
			request.getSession().setAttribute("user", oldUser);
			request.getSession().setAttribute("loginEnterprise", oldEnterprise);
			this.outJson(response, new JSONResult(true, "企业信息保存成功"));
		} catch (Exception e) {
			this.outJson(
					response,
					new JSONResult(false, "企业信息修改失败!异常信息:"
							+ e.getLocalizedMessage()));
		}
	}
	
	/**
	 * 主账号管理 修改机构信息
	 * 
	 * @author xuwf
	 * @sicen 2013-11-25
	 * @param user
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/saveOrgEnterInfo")
	public void saveOrgEnterInfo(User user, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			/** 
			 *	行业：机构性质：经营范围：优势服务领域：总资产：年营业额：利润或净收入:成立日期：职工人数：组织机构代码：
			 *	机构电话：机构传真：公司网址：机构地址:社会荣誉：专业资质：主营产品/服务：
			*/
			User oldUser = userBiz.findUserById(user.getId());
			Enterprise oldEnterprise = enterpriseBiz
					.loadEnterpriseByEid(oldUser.getEnterprise().getId());
			
			oldEnterprise.setOrgIndustry(user.getEnterprise().getOrgIndustry());//行业
			oldEnterprise.setOrgProperty(user.getEnterprise().getOrgProperty());// 机构性质
			oldEnterprise.setBusiness(user.getEnterprise().getBusiness());// 经营范围
			//优势服务领域
			oldEnterprise.setAdvantageServiceAreas(user.getEnterprise().getAdvantageServiceAreas());
			oldEnterprise.setSalesRevenue(user.getEnterprise().getSalesRevenue());// 年营业额
			oldEnterprise.setTotalAssets(user.getEnterprise().getTotalAssets());//总资产
			oldEnterprise.setProfit(user.getEnterprise().getProfit());	//利润或净收入
			oldEnterprise.setOpenedTime(user.getEnterprise().getOpenedTime()); // 成立时间
			oldEnterprise.setStaffNumber(user.getEnterprise().getStaffNumber());// 职工人数
			oldEnterprise.setIcRegNumber(user.getEnterprise().getIcRegNumber());// 组织机构代码
			oldEnterprise.setTel(user.getEnterprise().getTel());	//机构电话
			oldEnterprise.setOrgFax(user.getEnterprise().getOrgFax());	//机构传真
			oldEnterprise.setOrgWebsite(user.getEnterprise().getOrgWebsite());	//公司网址
			oldEnterprise.setAddress(user.getEnterprise().getAddress());	//机构地址
			oldEnterprise.setHonorSociety(user.getEnterprise().getHonorSociety());//社会荣誉
			oldEnterprise.setProfessionalQualifications(user.getEnterprise()
					.getProfessionalQualifications());	//专业资质
			oldEnterprise.setMainRemark(user.getEnterprise().getMainRemark());// 主营产品或业务
			oldUser.setEnterprise(oldEnterprise);
			userBiz.update(oldUser);
			
			// 同步至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
					EnterpriseSyncBean(oldUser.getEnterprise(), SyncFactory.getSyncType(oldUser.getEnterprise()))),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
					UserSyncBean(oldUser, SyncFactory.getSyncType(oldUser.getEnterprise()))));
			
			//用户的基本信息更改，随之session的用户更改
			request.getSession().setAttribute("loginUser", oldUser);
			request.getSession().setAttribute("user", oldUser);
			request.getSession().setAttribute("loginEnterprise", oldEnterprise);
			this.outJson(response, new JSONResult(true, "企业信息保存成功"));
		} catch (Exception e) {
			this.outJson(
					response,
					new JSONResult(false, "企业信息修改失败!异常信息:"
							+ e.getLocalizedMessage()));
		}
	}

	/**
	 * 主账号管理修改联系信息
	 * 
	 * @author huyj
	 * @sicen 2013-10-9
	 * @param user
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/saveLinkInfo")
	public void saveLinkInfo(User user, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 联系人 联系电话 地址
			User oldUser = userBiz.findUserById(user.getId());
			Enterprise oldEnterprise = enterpriseBiz
					.loadEnterpriseByEid(oldUser.getEnterprise().getId());
			oldEnterprise.setLinkman(user.getEnterprise().getLinkman());
			oldEnterprise.setTel(user.getEnterprise().getTel());
			oldEnterprise.setAddress(user.getEnterprise().getAddress());
			oldEnterprise.setCity(user.getEnterprise().getCity());
			oldEnterprise.setDistrict(user.getEnterprise().getDistrict());
			oldUser.setEnterprise(oldEnterprise);
			userBiz.update(oldUser);
			
			// 同步至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
					EnterpriseSyncBean(oldUser.getEnterprise(), SyncFactory.getSyncType(oldUser.getEnterprise()))),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
					UserSyncBean(oldUser, SyncFactory.getSyncType(oldUser.getEnterprise()))));
			
			this.outJson(response, new JSONResult(true, "联系信息保存成功"));
		} catch (Exception e) {
			this.outJson(
					response,
					new JSONResult(false, "联系信息修改失败!异常信息:"
							+ e.getLocalizedMessage()));
		}
	}
	
	/**
	 * 主账号管理修改服务机构联系信息
	 * 
	 * @author xuwf
	 * @sicen 2013-11-25
	 * @param user
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/saveOrgLinkInfo")
	public void saveOrgLinkInfo(User user, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			//法定代表人、手机、Email，总经理姓名、手机、Email, 联系人、手机、Email
			User oldUser = userBiz.findUserById(user.getId());
			Enterprise oldEnterprise = enterpriseBiz
					.loadEnterpriseByEid(oldUser.getEnterprise().getId());
			oldEnterprise.setLegalPerson(user.getEnterprise().getLegalPerson());//法定代表人
			//手机
			oldEnterprise.setLegalRepresentativeMobile(user.getEnterprise().getLegalRepresentativeMobile());
			oldEnterprise.setEmail(user.getEnterprise().getEmail());	//Email
			oldEnterprise.setGeneralName(user.getEnterprise().getGeneralName());//总经理姓名
			oldEnterprise.setGeneralManagerMobile(user.getEnterprise().getGeneralManagerMobile());
			oldEnterprise.setGeneralManagerEmail(user.getEnterprise().getGeneralManagerEmail());
			oldEnterprise.setLinkman(user.getEnterprise().getLinkman());//联系人姓名
			oldEnterprise.setContactNameMobile(user.getEnterprise().getContactNameMobile());
			oldEnterprise.setContactNameEmail(user.getEnterprise().getContactNameEmail());
			oldUser.setEnterprise(oldEnterprise);
			userBiz.update(oldUser);
			
			// 同步至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
					EnterpriseSyncBean(oldUser.getEnterprise(), SyncFactory.getSyncType(oldUser.getEnterprise()))),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
					UserSyncBean(oldUser, SyncFactory.getSyncType(oldUser.getEnterprise()))));
			
			this.outJson(response, new JSONResult(true, "联系信息保存成功"));
		} catch (Exception e) {
			this.outJson(
					response,
					new JSONResult(false, "联系信息修改失败!异常信息:"
							+ e.getLocalizedMessage()));
		}
	}

	/**
	 * 主账号管理 保存关注的领域
	 * 
	 * @author huyj
	 * @sicen 2013-10-9
	 * @param user
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/saveInteresServiceInfo")
	public void saveInteresServiceInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Integer uid = Integer.parseInt(request.getParameter("uid"));
			String ids = request.getParameter("ids");
			String[] idarray = ids.split(",");
			List<Category> interesService = new ArrayList<Category>();
			if(!ids.isEmpty()) {
				for (int i = 0; i < idarray.length; i++) {
					interesService.add(categoryBiz.findById("service",
							Integer.parseInt(idarray[i])));
				}
			}
			User oldUser = userBiz.findUserById(uid);
			oldUser.setInterestServices(interesService);
			userBiz.update(oldUser);
			
			// 同步至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
					EnterpriseSyncBean(oldUser.getEnterprise(), SyncFactory.getSyncType(oldUser.getEnterprise()))),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
					UserSyncBean(oldUser, SyncFactory.getSyncType(oldUser.getEnterprise()))));
			
			this.outJson(response, new JSONResult(true, "关注服务领域保存成功"));
		} catch (Exception e) {
			this.outJson(
					response,
					new JSONResult(false, "关注服务领域保存失败!异常信息:"
							+ e.getLocalizedMessage()));
		}
	}

	/**
	 * 账号管理 保存自己的服务领域(针对个人用户)
	 * 
	 * @author xuwf
	 * @sicen 2013-10-26
	 * @param user
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/saveUserServiceInfo")
	public void saveUserServiceInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Integer uid = Integer.parseInt(request.getParameter("uid"));
			String ids = request.getParameter("ids");
			String[] idarray = ids.split(",");
			// 是否10个以内
			if(idarray.length > 10){
				this.outJson(response, new JSONResult(false, "保存失败！我的服务领域不能大于 10 项"));
			}else{
				List<Category> myservice = new ArrayList<Category>();
				if(!ids.isEmpty()) {
					for (int i = 0; i < idarray.length; i++) {
						myservice.add(categoryBiz.findById("service",
								Integer.parseInt(idarray[i])));
					}
				}
				User oldUser = userBiz.findUserById(uid);
//				Enterprise enterprise = oldUser.getEnterprise();
				// 判断 被移除的类别 是否还有未删除的服务存在 有 则不允许移除
				Category cannot = null;
				List<Category> oldmyservice = oldUser.getUserServices();
				for(Category c : oldmyservice){
					if(!myservice.contains(c)){
						if (serviceBiz.queryServiceByCategoryId(c.getId()).size() > 0) {
							cannot = c;
						}
					}
				}
				if(cannot == null){
					oldUser.setUserServices(myservice.size() == 0 ? null : myservice);
					userBiz.update(oldUser);
					
					this.outJson(response, new JSONResult(true, "我的服务领域信息保存成功"));
				}else{
					this.outJson(response, new JSONResult(false, "'" + cannot.getText()
									+ "' 移除失败！请确认是否有未删除的服务."));
				}
			}
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "我的服务领域信息保存失败!异常信息:"
					+ e.getLocalizedMessage()));
		}
	}
	
	/**
	 * 主账号管理 保存自己的服务领域
	 * 
	 * @author huyj
	 * @sicen 2013-10-9
	 * @param user
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/saveMyServiceInfo")
	public void saveMyServiceInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Integer uid = Integer.parseInt(request.getParameter("uid"));
			String ids = request.getParameter("ids");
			String[] idarray = ids.split(",");
			// 是否10个以内
			if(idarray.length > 10){
				this.outJson(response, new JSONResult(false, "保存失败！我的服务领域不能大于 10 项"));
			}else{
				List<Category> myservice = new ArrayList<Category>();
				if(!ids.isEmpty()) {
					for (int i = 0; i < idarray.length; i++) {
						myservice.add(categoryBiz.findById("service",
								Integer.parseInt(idarray[i])));
					}
				}
				User oldUser = userBiz.findUserById(uid);
				Enterprise enterprise = oldUser.getEnterprise();
				// 判断 被移除的类别 是否还有未删除的服务存在 有 则不允许移除
				Category cannot = null;
				List<Category> oldmyservice = enterprise.getMyServices();
				for(Category c : oldmyservice){
					if(!myservice.contains(c)){
						if (serviceBiz.findServiceByCidAndEid(c.getId(), enterprise.getId()).size() > 0) {
							cannot = c;
						}
					}
				}
				if(cannot == null){
					enterprise.setMyServices(myservice.size() == 0 ? null : myservice);
					enterpriseBiz.save(enterprise);
					
					// 同步至窗口
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
							EnterpriseSyncBean(enterprise, SyncFactory.getSyncType(enterprise))));
					
					this.outJson(response, new JSONResult(true, "我的服务领域信息保存成功"));
				}else{
					this.outJson(response, new JSONResult(false, "'" + cannot.getText()
									+ "' 移除失败！请确认是否有未删除的服务."));
				}
			}
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "我的服务领域信息保存失败!异常信息:"
					+ e.getLocalizedMessage()));
		}
	}
	
	
	/**
	 * 设置个人用户头像
	 * @author xuwf
	 * @since 2013-10-26
	 *
	 * @param enterprise
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/updateHeadPortraint")
	public void updateHeadPortraint(@RequestParam(value="photo",required=false)String photo, 
			HttpServletRequest request, HttpServletResponse response){
		try {
			if(!StringUtils.isEmpty(photo)){
				Integer uid = Integer.parseInt(request.getParameter("uid"));
				User user = userBiz.findUserById(uid);
				user.setHeadPortraint(photo);
				userBiz.update(user);
				this.outJson(response, new JSONResult(true, "用户头像上传成功"));
			}
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "用户头像上传失败!服务器异常:" + e.getLocalizedMessage()));
		}
	}
	
	/**
	 * 设置企业图像
	 * @author Xiadi
	 * @since 2013-10-24 
	 *
	 * @param enterprise
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/updatePhoto")
	public void updatePhoto(@RequestParam(value="photo",required=false)String photo, Enterprise enterprise, HttpServletRequest request, HttpServletResponse response){
		try {
			if(!StringUtils.isEmpty(photo)){
				Enterprise old = enterpriseBiz.findByEid(enterprise.getId());
				old.setPhoto(photo);
				enterpriseBiz.save(old);
				
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
						EnterpriseSyncBean(old, SyncFactory.getSyncType(old))));
				
				//用户的基本信息更改，随之session的用户更改
				request.getSession().setAttribute("loginEnterprise", old);
				this.outJson(response, new JSONResult(true, "企业图像上传成功"));
			}
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "企业图像上传失败!服务器异常:" + e.getLocalizedMessage()));
		}
	}
	
	/**
	 * 前台  应标详情  页面
	 * @author pangyf
	 * @since 2013-10-8
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/response_detail")
	public String toResponseDetail(HttpServletRequest request, HttpServletResponse response){
		this.keepUserSession(request);
		Integer rid = Integer.parseInt(request.getParameter("rid"));
		request.getSession().setAttribute("res",responseBiddingBiz.getResponseBiddingById(rid));
		return "ucenter/response_detail";		
	};
	
	/**
	 * @author cs
	 * 用户安全中心
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping("security")
	public String security(){
		return "ucenter/security";
	}
	/**
	 * @author cs
	 * 安全中心修改密码连接
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping("security_upwd")
	public String security_upwd(){
		return "ucenter/security_upwd";
	}
	/**
	 * @author cs
	 * 安全中心邮箱验证连接
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping("security_uemail")
	public String security_uemail(){
		return "ucenter/security_uemail";
	}
	/**
	 * @author cs
	 * 修改密码
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping("upwd")
	public void upwd(HttpServletRequest request,HttpServletResponse response,String oldpassword,String newpassword,Integer id){
		User u = userBiz.findUserById(id);
		if(u.getPassword().equals(oldpassword)){
			u.setPassword(newpassword);
			userBiz.add(u);
			this.outJson(response, new JSONResult(true, "修改密码成功！"));
		}else{
			this.outJson(response, new JSONResult(false, "原密码输入错误！"));
		}
	}
	
	/**
	 * 检查邮箱是否可用,并且保存到被更改的用户下面去
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @param username
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/checkEmail")
	public void checkEmail(HttpServletRequest request, Model model,HttpServletResponse response,String email,Integer id) {
		User user = userBiz.findUserById(id);
		if(StringUtils.isEmpty(user.getEmail())){//邮箱为空时修改邮箱即增加个邮箱xuwf  2013-11-23
			user.setEmail(email);
			if(!user.getIsPersonal()){
				user.getEnterprise().setEmail(email);	
			}
			userBiz.update(user);
			
			// 同步至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
					EnterpriseSyncBean(user.getEnterprise(), SyncFactory.getSyncType(user.getEnterprise()))),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
					UserSyncBean(user, SyncFactory.getSyncType(user.getEnterprise()))));
			
			request.getSession().removeAttribute("user");
			request.getSession().setAttribute("user", user);
			this.outJson(response, new JSONResult(true, "更改用户的邮箱成功！"));
		}else if(!StringUtils.isEmpty(user.getEmail()) || user.getSendEmail().isHandle()){//加了一个不为空的判断 xuwf 2013-11-22
			if(userBiz.findUserByEmail(email)!=null&&userBiz.findUserByEmail(email).size()!=0){
				this.outJson(response, new JSONResult(false, "邮箱已经被其他人注册，请更换邮箱"));
			}else{
				user.setEmail(email);
				
				if(!user.getIsPersonal()){//企业用户把user.email设置给企业email
					user.getEnterprise().setEmail(email);	
				}
				
				user.setApproved(false);
				userBiz.update(user);
				
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
						EnterpriseSyncBean(user.getEnterprise(), SyncFactory.getSyncType(user.getEnterprise()))),true);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
						UserSyncBean(user, SyncFactory.getSyncType(user.getEnterprise()))));
				
				request.getSession().removeAttribute("user");
				request.getSession().setAttribute("user", user);
				this.outJson(response, new JSONResult(true, "更改用户的邮箱成功！"));
			}
		}else{
			this.outJson(response, new JSONResult(false, "当前邮箱有存在验证之中的邮件，不能更换邮箱！"));
		}
	}
	/**
	 * 用户中心发送邮箱验证
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @param username
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/sendEmail")
	public void sendEmail(HttpServletRequest request, Model model,HttpServletResponse response,Integer id) {
		//获取当前时间的0点0分0秒的毫秒数
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long zero = cal.getTimeInMillis();
        
		User user = userBiz.findUserById(id);
		//系统时间
		Long systime = System.currentTimeMillis();
		//上次发送邮件的时间
		Long sentime = Long.parseLong(user.getCheckcode().split("\\|")[1]);
		//上次发送邮件的随机码
		String rand = user.getCheckcode().split("\\|")[0];
		//用来计数的，看一天之类是否小于5次
		Long index = Long.parseLong(String.valueOf(rand.charAt(rand.length()-1)));
		
		//今天发送的，并且次数小于5次的，还要间隔十分钟
//		boolean b1 = (systime-sentime) <= 600000&&index<5;
		
		//是昨天发送的
		boolean b2 = sentime < zero ;
//				&& sentime < zero+86400000;
		if(b2){
				sendEmail(1L,systime,user,response,request);
		}else{
			if (index >= 5) {
				this.outJson(response, new JSONResult(false,"你今天已经发满5次激活邮件，请明天再试！"));
			} else if ((systime - sentime) < 600000) {
				this.outJson(response, new JSONResult(false, "请间隔十分钟在发送激活邮件！"));
			} else {
				index++;
				sendEmail(index, systime, user, response,request);
			}
		}
	}
	
	/**
	 * @author cs
	 * 封装发邮件
	 */
	private void sendEmail(Long index,Long systime,User user,HttpServletResponse response,HttpServletRequest request){
		SendEmail sendEmail = new SendEmail();
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/active";
		
		try{
			String s = RandomUtils.generateRandomNumber();
			String check= s+index+"|"+systime;
			
			HtmlEmail mailer = new HtmlEmail();
			mailer.setCharset("utf-8");
			mailer.setHostName(ResourceBundle.getBundle("config").getString("email.host"));
			mailer.setSSLOnConnect(true);
			mailer.setAuthenticator(new DefaultAuthenticator(ResourceBundle.getBundle("config").getString("email.name"), ResourceBundle.getBundle("config").getString("email.password")));
			mailer.addTo(user.getEmail());
			mailer.setFrom(ResourceBundle.getBundle("config").getString("email.name"),"中小企业公共服务平台");
			mailer.setSubject("中小企业公共服务平台账号激活邮件<系统自动发出,请勿回复>");
			
			URL logo_url = null;
			try {
				try {
					logo_url = new URL(ResourceBundle.getBundle("config").getString("email.log"));
				} catch (Exception e) {
					logo_url = new URL(ResourceBundle.getBundle("config").getString("email.defaultlog"));
				}
				String logo_src = mailer.embed(logo_url, "smemall-logo");
				String htmlEmailTemplate = (
						"<html>" +
								"<div style='border-bottom:1px solid #ccc;'>" +
								"<a href='#'>" +
								"<img src='cid:"+logo_src+"'>" +
								"</a>" +
								"</div>"
								+ "<div style='padding:10px 0;'>"
								+ "尊敬的"+user.getUserName()+"用户,您好！<br>"
								+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;欢迎注册深圳市中小企业公共服务平台:<a href='http://smemall.net'>www.smemall.net</a>,您已注册成功，请立即激活帐号，"
								+ "点击 <a href='"+basePath+"?checkcode="+check+"&username="+user.getUserName()
								+ "'>立即激活</a> 完成账号激活!帐号激活后，您将享有平台所提供的服务。</p>"
								+ "</div>"
								+ "<div style='font-size:12px;border-top:1px solid #ccc;'>"
								+ "<p style='margin:0;padding:0;'>网址:<a href='http://www.smemall.net' target='_blank'>http://www.smemall.net</a></p>"
								+ "<p style='margin:0;padding:0;'>邮箱:service@smemall.net</p>"
								+ "</div>" +
						"</html>");
				mailer.setHtmlMsg(htmlEmailTemplate);
				mailer.setTextMsg("您的邮箱不支持HTML格式的邮件!");
				mailer.send();
				

				sendEmail.setContent(htmlEmailTemplate);
				sendEmail.setOrigin(2);
				sendEmail.setSender(ResourceBundle.getBundle("config").getString("email.name"));
				sendEmail.setReceiver(user.getEmail());
				
				sendEmail=sendEmailBiz.saveOrUpdate(sendEmail);
				//保存新的激活码
				user.setSendEmail(sendEmail);
				user.setCheckcode(check);
				userBiz.update(user);
				
				request.getSession().removeAttribute("user");
				request.getSession().setAttribute("user", user);
				this.outJson(response, new JSONResult(true, "验证邮件已发送,请立即登录邮箱进行验证"));
			} catch (Exception e) {
				logger.info(e.getLocalizedMessage());
				this.outJson(response, new JSONResult(false, "验证邮件已发送失败"));
			}
		}catch(EmailException e){
			logger.info(e.getLocalizedMessage());
			this.outJson(response, new JSONResult(false, "验证邮件已发送失败"));
		}
	}
	/**
	 * 转至子账号添加/编辑    页面 
	 * @author xuwf
	 * @since 2013-10-17
	 * 
	 * @param url
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/staff_{url}")
	public String toStaffAdd(@PathVariable String url,HttpServletRequest request, 
			HttpServletResponse response,Model model){
		String idStr = request.getParameter("id");	//得到子账号id字符串
		String typeStr = request.getParameter("type");	//得到类型字符串
		
		logger.info("id:"+idStr+",type:"+typeStr);
		List<StaffRole> staffRoleList = new ArrayList<StaffRole>();
		if(!StringUtils.isEmpty(typeStr)){
			Integer enterpriseType;
			try {
				enterpriseType = Integer.parseInt(typeStr);
				if(enterpriseType >= Constant.ENTERPRISE_TYPE_ORG){//机构用户
					staffRoleList = staffRoleBiz.queryRole(Constant.ROLE_SELLER_ENTERPRISE);
				}else{//企业用户
					staffRoleList = staffRoleBiz.queryRole(Constant.ROLE_BUYER_ENTERPRISE);
				}
				model.addAttribute("staffRoleList", staffRoleList);
			} catch (NumberFormatException e) {
				enterpriseType = 0;
				logger.info("企业类型字符串转换成Integer出错");
			}
		}
		if (!StringUtils.isEmpty(idStr)) {// id不为空进入编辑子窗口
			Integer id;
			try {
				id = Integer.parseInt(idStr);
				Staff staff = staffBiz.getStaff(id);
				model.addAttribute("staff", staff);
			} catch (NumberFormatException e) {
				id = 0;
				logger.info("子账号ID字符串转成Integer出错");
			}
		}
		return "ucenter/staff_"+url;
	}
	
	/**
	 * 跳转子帐号管理页面
	 * @author pangyf
	 * @since 2013-10-17
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/staff_list")
	public String toStaffList(HttpServletRequest request, HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		user = userBiz.findUserById(user.getId());		
		request.getSession().setAttribute("user",user);
		return "ucenter/staff_list";		
	}	
	
	/**
	 * 站内消息
	 * @author cs
	 * @since 2013-10-23
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/info")
	public String info(HttpServletRequest request, HttpServletResponse response){
		return "ucenter/info";		
	}	
	
	/**
	 * 消息类别
	 * @author cs
	 * @since 2013-10-23
	 * @param request
	 * @param response
	 * @return
	 */
//	@NeedSession(SessionType.USER)
	@NeedSession(SessionType.MANAGER)
	@RequestMapping("category")
	public String category(HttpServletRequest request, HttpServletResponse response){
		return "ucenter/category";		
	}
	
	/**
	 * 加载消息类别
	 * @param request
	 * @param response
	 * @param messageClass
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/querycategory",method=RequestMethod.POST)
	public void querycategory(@RequestParam(value="page",required=false)int page,@RequestParam(value="rows",required=false)int rows,HttpServletRequest request,HttpServletResponse response,String cname){
		JSONRows<MessageClass> jrows = messageClassBiz.find(rows * (page - 1), rows);
		this.outJson(response, jrows);
	}
	
	/**
	 * 保存消息类别
	 * @param request
	 * @param response
	 * @param messageClass
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/savecategory",method=RequestMethod.POST)
	public void savecategory(HttpServletRequest request,HttpServletResponse response,String cname,String cdesc,Integer categoryid){
		if(cname!=null && !cname.equals("")){
			//修改
			if(categoryid != null && !"".equals(categoryid)){
				MessageClass messageClass = messageClassBiz.find(categoryid);
				if(messageClass.getMessageType().equals(cname)){
					messageClass.setRemark(cdesc);
					messageClassBiz.update(messageClass);
					logger.info("修改消息类别成功！" );
					this.outJson(response, new JSONResult(true, "修改成功"));
				}else{
					if(!messageClassBiz.exist(cname)){
						messageClass.setMessageType(cname);
						messageClass.setRemark(cdesc);
						messageClassBiz.update(messageClass);
						logger.info("修改消息类别成功！" );
						this.outJson(response, new JSONResult(true, "修改成功"));
					}else{
						this.outJson(response, new JSONResult(false, "不得修改成已经存在的消息类别！"));
					}
				}
				//增加
			}else{
				if(!messageClassBiz.exist(cname)){
					MessageClass messageClass = new MessageClass();
					messageClass.setMessageType(cname);
					messageClass.setRemark(cdesc);
					messageClassBiz.save(messageClass);
					logger.info("添加消息类别成功！" );
					this.outJson(response, new JSONResult(true, "添加成功"));
				}else{
					this.outJson(response, new JSONResult(false, "类别名称已经存在，请更换类别名称！"));
				}
			}
		}else{
			this.outJson(response, new JSONResult(false, "类别名称不能为空！"));
		}
	}
	
	/**
	 * 加载已发送，并且type==0
	 * @author cs
	 * @since  2013-9-11
	 * 
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/send")
	public void myappeal(@RequestParam(value="page",required=false)int page,@RequestParam(value="rows",required=false)int rows,HttpServletRequest request,HttpServletResponse response) {
		if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
			User user = (User)request.getSession().getAttribute("user");
			JSONRows<Object> jrows = senderMessageRelationshipBiz.find(user, 0, rows * (page - 1), rows);
			this.outJson(response, jrows);
		}else if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_STAFF)){
			Staff staff = (Staff)request.getSession().getAttribute("user");
			JSONRows<Object> jrows = senderMessageRelationshipBiz.find(staff, 0, rows * (page - 1), rows);
			this.outJson(response, jrows);
		}
	}
	
	/**
	 * 删除已经发送消息
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void deletes(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="ids",required=true)String ids){
		if(ids!=null&&!"".equals(ids)){
			try{
				if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
					User user = (User)request.getSession().getAttribute("user");
					this.outJson(response,new JSONResult(true, String.valueOf(senderMessageRelationshipBiz.update(user, ids))));
				}else if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_STAFF)){
					Staff staff = (Staff)request.getSession().getAttribute("user");
					this.outJson(response,new JSONResult(true, String.valueOf(senderMessageRelationshipBiz.update(staff, ids))));
				}
			}catch(Exception e){
				this.outJson(response,new JSONResult(false,"删除失败"));
			}
		}else{
			this.outJson(response,new JSONResult(false,"删除失败"));
		}
	}
	
	/**
	 * 加载已接收，并且type==0
	 * @author cs
	 * @since  2013-9-11
	 * 
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/receiver")
	public void receiver(@RequestParam(value="messageType",required=false)Integer messageType
			,@RequestParam(value="page",required=false)int page,@RequestParam(value="rows",required=false)int rows,HttpServletRequest request,HttpServletResponse response) {
		if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
			User user = (User)request.getSession().getAttribute("user");
			JSONRows<Object> jrows = receiverMessageRelationshipBiz.findtype(user, 0, messageType,rows * (page - 1), rows);
			this.outJson(response, jrows);
		}else if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_STAFF)){
			Staff staff = (Staff)request.getSession().getAttribute("user");
			JSONRows<Object> jrows = receiverMessageRelationshipBiz.findtype(staff, 0,messageType, rows * (page - 1), rows);
			this.outJson(response, jrows);
		}
	}
	
	/**
	 * 删除已经接收的消息
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/deleter",method=RequestMethod.POST)
	public void deleter(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="ids",required=true)String ids){
		if(ids!=null&&!"".equals(ids)){
			try{
				if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
					User user = (User)request.getSession().getAttribute("user");
					/***********************消息被删除时重新加载消息数量保存在session************************/
					//消息盒子
					int[] messages = rmrsBiz.findMyMessage(user.getId(), Constant.LOGIN_USER);
					if(messages[0] != 0 || messages[1] != 0){//保存消息盒子数据
						request.getSession().setAttribute("messages", messages);
					}
					/********************************end***************************************/
					this.outJson(response,new JSONResult(true, String.valueOf(receiverMessageRelationshipBiz.update(user, ids))));
				}else if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_STAFF)){
					Staff staff = (Staff)request.getSession().getAttribute("user");
					/***********************消息被删除时重新加载消息数量保存在session************************/
					//消息盒子
					int[] messages = rmrsBiz.findMyMessage(staff.getId(), Constant.LOGIN_STAFF);
					if(messages[0] != 0 || messages[1] != 0){//保存消息盒子数据
						request.getSession().setAttribute("messages", messages);
					}
					/*********************************end**************************************/
					this.outJson(response,new JSONResult(true, String.valueOf(receiverMessageRelationshipBiz.update(staff, ids))));
				}
			}catch(Exception e){
				this.outJson(response,new JSONResult(false,"删除失败"));
			}
		}else{
			this.outJson(response,new JSONResult(false,"删除失败"));
		}
	}
	
	/**
	 * 更改为已读标记
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/updatereadsign",method=RequestMethod.POST)
	public void updatereadsign(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id",required=true)Integer id){
		if(id!=null&&!"".equals(id)){
			try{
				if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
					User user = (User)request.getSession().getAttribute("user");
					receiverMessageRelationshipBiz.updateyetRead(user, id);
				}else if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_STAFF)){
					Staff staff = (Staff)request.getSession().getAttribute("user");
					receiverMessageRelationshipBiz.updateyetRead(staff, id);
				}
			}catch(Exception e){
				this.outJson(response,new JSONResult(false,"删除失败"));
			}
		}
	}
	
	/**
	 * 加载已删除，并且type==1
	 * @author cs
	 * @since  2013-9-11
	 * 
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public void delete(@RequestParam(value="page",required=false)int page,@RequestParam(value="rows",required=false)int rows,HttpServletRequest request,HttpServletResponse response) {
		List<Object> list = new ArrayList<Object>();
		//开始下标的位置
		int start = rows * (page - 1)<=0 ? 0 : rows * (page - 1);
		//结束下标的位置
		int end = start + rows -1;
		if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
			User user = (User)request.getSession().getAttribute("user");
			JSONRows<Object> jrows = receiverMessageRelationshipBiz.find(user, 1, 0, 0);
			jrows.add(senderMessageRelationshipBiz.find(user, 1, 0, 0));
			if(start<jrows.getRows().size()){
				end = end <jrows.getRows().size() ? end : jrows.getRows().size()-1;
				for(int i = start;i<=end;i++){
					list.add(jrows.getRows().get(i));
				}
			}
			
			jrows.setRows(list);
			this.outJson(response, jrows);
		}else if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_STAFF)){
			Staff staff = (Staff)request.getSession().getAttribute("user");
			JSONRows<Object> jrows = receiverMessageRelationshipBiz.find(staff, 1, 0, 0);
			jrows.add(senderMessageRelationshipBiz.find(staff, 1, 0, 0));
			if(start<jrows.getRows().size()){
				end = end <jrows.getRows().size() ? end : jrows.getRows().size()-1;
				for(int i = start;i<=end;i++){
					list.add(jrows.getRows().get(i));
				}
			}
			
			jrows.setRows(list);
			this.outJson(response, jrows);
		}
	}
	
	/**
	 * 发送消息
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=true)String rname,@RequestParam(required=true)String messagetype,@RequestParam(required=true)String content){
		if("".equals(rname) || "".equals(messagetype) || "".equals(content)){
			this.outJson(response,new JSONResult(false,"参数不正确！"));
		}else{
			if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
				User senduser = (User)request.getSession().getAttribute("user");
				try{
					if(userBiz.findUserByName(rname)!=null && userBiz.findUserByName(rname).size() != 0){
						User user = userBiz.findUserByName(rname).get(0);
						
						MessageClass messageClass = messageClassBiz.find(messagetype);
						
						Message message = new Message();
						message.setContent(content);
						message.setMessageClass(messageClass);
						
						ReceiverMessageRelationship r = new ReceiverMessageRelationship();
						r.setMessage(message);
						r.setSender(senduser.getUserName());
						r.setReceiverUserId(user.getId());
						
						SenderMessageRelationship s = new SenderMessageRelationship();
						s.setMessage(message);
						s.setReceiver(user.getUserName());
						s.setSendUserId(senduser.getId());
						
						messageBiz.save(message);
						receiverMessageRelationshipBiz.save(r);
						senderMessageRelationshipBiz.save(s);
						this.outJson(response,new JSONResult(true,"发送成功"));
					}else if(staffBiz.findByUserName(rname)!=null){
						Staff staff = staffBiz.findByUserName(rname);
						
						MessageClass messageClass = messageClassBiz.find(messagetype);
						
						Message message = new Message();
						message.setContent(content);
						message.setMessageClass(messageClass);
						
						ReceiverMessageRelationship r = new ReceiverMessageRelationship();
						r.setMessage(message);
						r.setSender(senduser.getUserName());
						r.setReceiverStaffId(staff.getId());
						
						SenderMessageRelationship s = new SenderMessageRelationship();
						s.setMessage(message);
						s.setReceiver(staff.getUserName());
						s.setSendUserId(senduser.getId());
						
						messageBiz.save(message);
						receiverMessageRelationshipBiz.save(r);
						senderMessageRelationshipBiz.save(s);
						this.outJson(response,new JSONResult(true,"发送成功"));
					}else{
						this.outJson(response,new JSONResult(false,"找不到收信人！"));
					}
				}catch(Exception e){
					this.outJson(response,new JSONResult(false,"信息发送异常，请稍后再试！"));
				}
				
			}else if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_STAFF)){
				Staff senduser = (Staff)request.getSession().getAttribute("user");
				try{
					if(userBiz.findUserByName(rname)!=null && userBiz.findUserByName(rname).size() != 0){
						User user = userBiz.findUserByName(rname).get(0);
						
						MessageClass messageClass = messageClassBiz.find(messagetype);
						
						Message message = new Message();
						message.setContent(content);
						message.setMessageClass(messageClass);
						
						ReceiverMessageRelationship r = new ReceiverMessageRelationship();
						r.setMessage(message);
						r.setSender(senduser.getUserName());
						r.setReceiverUserId(user.getId());
						
						SenderMessageRelationship s = new SenderMessageRelationship();
						s.setMessage(message);
						s.setReceiver(user.getUserName());
						s.setSendStaffId(senduser.getId());
						
						messageBiz.save(message);
						receiverMessageRelationshipBiz.save(r);
						senderMessageRelationshipBiz.save(s);
						this.outJson(response,new JSONResult(true,"发送成功"));
					}else if(staffBiz.findByUserName(rname)!=null){
						Staff staff = staffBiz.findByUserName(rname);
						
						MessageClass messageClass = messageClassBiz.find(messagetype);
						
						Message message = new Message();
						message.setContent(content);
						message.setMessageClass(messageClass);
						
						ReceiverMessageRelationship r = new ReceiverMessageRelationship();
						r.setMessage(message);
						r.setSender(senduser.getUserName());
						r.setReceiverStaffId(staff.getId());
						
						SenderMessageRelationship s = new SenderMessageRelationship();
						s.setMessage(message);
						s.setReceiver(staff.getUserName());
						s.setSendStaffId(senduser.getId());
						
						messageBiz.save(message);
						receiverMessageRelationshipBiz.save(r);
						senderMessageRelationshipBiz.save(s);
						this.outJson(response,new JSONResult(true,"发送成功"));
					}else{
						this.outJson(response,new JSONResult(false,"找不到收信人！"));
					}
				}catch(Exception e){
					this.outJson(response,new JSONResult(false,"信息发送异常，请稍后再试！"));
				}
			}
		}
	}
	
	/**
	 * 跳转到用户中心金融中心
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/financial")
	public String toFinancial(HttpServletRequest request,HttpServletResponse response){
		this.keepUserSession(request);
		return "ucenter/financial";
	}
	
}
