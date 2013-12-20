package com.eaglec.plat.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.chatonline.ChatOnlineBiz;
import com.eaglec.plat.biz.flat.FlatBiz;
import com.eaglec.plat.biz.user.ApproveBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.SendEmailBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.OrgRegisterApproval;
import com.eaglec.plat.domain.base.SendEmail;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.bean.EnterpriseSyncBean;
import com.eaglec.plat.sync.bean.OrgRegisterSyncBean;
import com.eaglec.plat.sync.bean.UserSyncBean;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.MD5;
import com.eaglec.plat.utils.RandomUtils;
import com.eaglec.plat.utils.mail.MessageUtil;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONEntity;
import com.eaglec.plat.view.JSONResult;
//import com.eaglec.plat.sync.UserSyncBean;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserBiz userBiz;	
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private StaffBiz staffBiz;
	@Autowired
	private ApproveBiz approveBiz;
	@Autowired
	private SendEmailBiz sendEmailBiz;
	@Autowired
	private ChatOnlineBiz chatOnlineBiz;
	@Autowired
	private FlatBiz flatBiz;
	/**
	 * 根据混合条件分页查询企业用户
	 * @author xuwf
	 * @since 2013-8-22
	 * 
	 * @param username	
	 * @param entershort
	 * @param entername
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/queryMultiple")
	public void queryMultiple(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="entershort",required=false)String entershort,
			@RequestParam(value="entername",required=false)String entername,
			@RequestParam(value = "enterpriseType", required = false) Integer enterpriseType,
			@RequestParam(value="startTime",required=false)String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			Integer industryType,
			@RequestParam("start")Integer start,
			@RequestParam("limit")Integer limit,
			HttpServletRequest request,
			HttpServletResponse response){
		logger.info("queryParams[ username:"+username+",entershort:"+entershort+",entername:"+entername
				+",startTime:"+startTime+",endTime:"+endTime+"]");

		JSONData<User> users = userBiz.queryMultiple(username, entershort,entername,enterpriseType,
				startTime,endTime, industryType, start, limit);
		this.outJson(response,users);
	}
	
	/**
	 * 根据用户名或企业简称查询所有的企业用户
	 * 
	 * @author pangyf
	 * @since 2013-8-22 
	 * 
	 * @param uName 用户名
	 * @param enterpriseName 企业全称	
	 * @param userStatus 用户帐号状态
	 */
	@RequestMapping(value = "/query")
	public void query(
			@RequestParam(value="uName",required=false)String uName,
			@RequestParam(value="enterpriseName",required=false)String enterpriseName,
			@RequestParam(value = "enterpriseType", required = false) Integer enterpriseType,
			Integer industryType,
			@RequestParam(value="userStatus",required=false)String userStatus,
			@RequestParam(value="start",required=false)int start,
			@RequestParam(value="limit",required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		this.outJson(response, userBiz.findAll(uName,enterpriseName,enterpriseType,industryType,userStatus,start, limit));
	} 
	
	/**
	 * 用户状态更改(禁用,删除,恢复)
	 *@author pangyf
	 *@since 2013-8-14
	 *
	 *@param user对象
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/update")
	public void update(User user,HttpServletRequest request,HttpServletResponse response) {		
		try {
				
			User _user = userBiz.findUserById(user.getId());
			if(_user.getIsPersonal()){//个人
				_user.setEnterprise(null);		
			}
			_user.setUserStatus(user.getUserStatus());
			userBiz.update(_user);
			
			/***********************主账号状态发生改变,旗下子账号统一变更(禁用主账号——子账号全部禁用)***************/
			if(!_user.getIsPersonal()){
				List<Staff> staffList = staffBiz.findByParent(_user);
				for (Staff staff : staffList) {
					if(null != staff){
						if(_user.getUserStatus() == Constant.EFFECTIVE){
							staff.setStaffStatus(_user.getUserStatus());
						}else if(_user.getUserStatus() == Constant.DISABLED){
							staff.setStaffStatus(_user.getUserStatus());
						}else if(_user.getUserStatus() == Constant.DELETED){
							staff.setStaffStatus(_user.getUserStatus());
						}
						staffBiz.update(staff);
					}
				}
				/***********************主账号状态发生改变,企业统一变更(禁用除主账号——企业禁用,删除主账号——企业删除)***************/
				if(user.getUserStatus() == Constant.EFFECTIVE){
					_user.getEnterprise().setStatus(Constant.ENTERPRISE_STATUS_EFFECTIVE);
				}else if(user.getUserStatus() == Constant.DISABLED){
					_user.getEnterprise().setStatus(Constant.ENTERPRISE_STATUS_DISABLED);
				}else if(user.getUserStatus() == Constant.DELETED){
					_user.getEnterprise().setStatus(Constant.ENTERPRISE_STATUS_DELETED);
				}
				enterpriseBiz.update(_user.getEnterprise());
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
						EnterpriseSyncBean(_user.getEnterprise(), SyncFactory.getSyncType(_user.getEnterprise()))),true);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
						UserSyncBean(_user, SyncFactory.getSyncType(_user.getEnterprise()))));
				/*******************************************************************************/
			}
			/*******************************************************************************/
			logger.info("[ "+user.getUserName()+" ]修改状态成功!");
			this.outJson(response,new JSONResult(true,"帐号[" + user.getUserName() + "]修改状态成功!"));
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"帐号修改状态失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("帐号修改状态失败!异常信息:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 用户编辑
	 *@author pangyf
	 *@since 2013-8-26
	 *
	 *@param user 对象
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/edit")
	public void edit(@RequestParam(value="isreset",required=false)boolean isreset,User user,HttpServletRequest request,HttpServletResponse response) {
		
		try {
			User _user = userBiz.findUserById(user.getId());
			_user.setRemark(user.getRemark());
			if(isreset){
				_user.setPassword(MD5.toMD5(user.getPassword()));
			}	
			if(_user.getIsPersonal()){//个人用户
				_user.setMobile(user.getMobile());
				_user.setAddress(user.getAddress());
				userBiz.update(_user);				
			}else{
//				userBiz.update(_user);	
				Enterprise enterprise = enterpriseBiz.loadEnterpriseByEid(user.getEnterprise().getId());
				String[] properties = {"forShort","name","enterpriseProperty","businessPattern","industryType","legalPerson","openedTime","salesRevenue",
						"staffNumber","linkman","tel","email","photo","address","mainRemark"};			
				this.copyProperties(user.getEnterprise(), enterprise, properties);
				_user.setEnterprise(enterprise);
				enterpriseBiz.update(enterprise);
				
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
						EnterpriseSyncBean(_user.getEnterprise(), SyncFactory.getSyncType(_user.getEnterprise()))),true);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
						UserSyncBean(_user, SyncFactory.getSyncType(_user.getEnterprise()))));
				
			}
			logger.info("[ "+user.getUserName()+" ]修改成功!");
			this.outJson(response,new JSONResult(true,"帐号[" + user.getUserName() + "]修改成功"));
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"帐号[" + user.getUserName() + "]修改失败!异常信息:"+e.getLocalizedMessage()));
			logger.info("帐号修改失败!异常信息:"+e.getLocalizedMessage());
		}
	}
	
//	/**
//	 * 封装发送激活码的邮件
//	 * @author xuwf
//	 * @since 2013-11-12
//	 * 
//	 * @param user
//	 * @param request
//	 * @param response
//	 */
//	public String sendEmailActive(User user,HttpServletRequest request, HttpServletResponse response){
//		SendEmail sendEmail = new SendEmail();
//		String path = request.getContextPath();
//		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/active";
//		
//		String s = RandomUtils.generateRandomNumber();
//		String check= s+"1|"+System.currentTimeMillis();
//		if(!StringUtils.isEmpty(user.getEmail())){//邮箱不为空即发送激活
//			HtmlEmail mailer = new HtmlEmail();
//			mailer.setCharset("utf-8");
//			mailer.setHostName(ResourceBundle.getBundle("config").getString("email.host"));
//			mailer.setSSLOnConnect(true);
//			mailer.setAuthenticator(new DefaultAuthenticator(ResourceBundle.getBundle("config").getString("email.name"), ResourceBundle.getBundle("config").getString("email.password")));
//			String htmlEmailTemplate;
//			try {
//				mailer.addTo(user.getEmail());
//				mailer.setFrom(ResourceBundle.getBundle("config").getString("email.name"),"中小企业公共服务平台");
//				mailer.setSubject("中小企业公共服务平台账号激活邮件<系统自动发出,请勿回复>");
//				
//				URL logo_url;
//				logo_url = new URL("http://api.pservice.cn/logo/logo_smemall.png");
//				String logo_src = mailer.embed(logo_url, "smemall-logo");
//				htmlEmailTemplate = (
//						"<html>" +
//								"<div style='border-bottom:1px solid #ccc;'>" +
//								"<a href='#'>" +
//								"<img src='cid:"+logo_src+"'>" +
//								"</a>" +
//								"</div>"
//								+ "<div style='padding:10px 0;'>"
//								+ "<p>"
//								+ "深圳市中小企业公共服务平台:<a href='http://smemall.com'>smemall.com</a><br>"
//								+ "</p>"
//								+ "<p>注册成功!点击 <a href='"+basePath+"?checkcode="+check+"&username="+user.getUserName()
//								+ "'>立即激活</a> 完成账号激活!</p>"
//								+ "</div>"
//								+ "<div style='font-size:12px;border-top:1px solid #ccc;'>"
//								+ "<p style='margin:0;padding:0;'>网址:<a href='http://www.smemall.com' target='_blank'>http://www.smemall.com</a></p>"
//								+ "<p style='margin:0;padding:0;'>邮箱:admin@pservice.cn</p>"
//								+ "</div>" +
//						"</html>");
//				mailer.setHtmlMsg(htmlEmailTemplate);
//				mailer.setTextMsg("您的邮箱不支持HTML格式的邮件!");
//				sendEmail.setContent(htmlEmailTemplate);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			} catch (EmailException e) {
//				e.printStackTrace();
//			}
//			
//			sendEmail.setOrigin(1);
//			sendEmail.setHandle(true);
//			sendEmail.setSender(ResourceBundle.getBundle("config").getString("email.name"));
//			sendEmail.setReceiver(user.getEmail());
//			sendEmailBiz.saveOrUpdate(sendEmail);
//			try {
//				mailer.send();
//			} catch (EmailException e) {
//				e.printStackTrace();
//			}
//		}
//		return check;
//	}
	
	/**
	 * 添加用户
	 *@author pangyf
	 *@since 2013-8-26
	 *
	 *@param user 对象
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/add")
	public void add(User user,HttpServletRequest request,HttpServletResponse response) {
		
			List<User> list = userBiz.findUserListByName(user.getUserName());
			try {
				if(list.isEmpty()){
					String s = RandomUtils.generateRandomNumber();
					String check = s+"1|"+System.currentTimeMillis();
					Enterprise _enterprise = new Enterprise();
					if(user.getEnterprise().getPhoto().equals("")){
						user.getEnterprise().setPhoto(_enterprise.getPhoto());
					}
					_enterprise = user.getEnterprise();	
					_enterprise.setType(Constant.ENTERPRISE_TYPE_PUBLIC);
					Enterprise enterprise = enterpriseBiz.save(_enterprise);
					user.setPassword(MD5.toMD5(user.getPassword()));
					user.setEnterprise(enterprise);
					user.setCheckcode(check);	//bug#248 缺少checkcode属性值验证邮箱
					userBiz.add(user);	
					
					// 同步至窗口
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
							EnterpriseSyncBean(user.getEnterprise(), SyncFactory.getSyncType(user.getEnterprise()))),true);
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
							UserSyncBean(user, SyncFactory.getSyncType(user.getEnterprise()))));
					logger.info("[ "+user.getUserName()+" ]添加成功!");
					this.outJson(response,new JSONResult(true,"帐号[" + user.getUserName() + "]添加成功!"));	
				}else{
					this.outJson(response,new JSONResult(false,"该用户名已存在!"));
				}
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"帐号[" + user.getUserName() + "]添加失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("帐号添加失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	
	/**
	 * 添加个人用户
	 *@author xuwf
	 *@since 2013-10-29
	 *
	 *@param user 对象
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/addPersonal")
	public void addPersonal(User user,HttpServletRequest request,HttpServletResponse response) {
		
			List<User> list = userBiz.findUserListByName(user.getUserName());
			try {
				if(list.isEmpty()){
					String s = RandomUtils.generateRandomNumber();
					String check = s+"1|"+System.currentTimeMillis();
					user.setPassword(MD5.toMD5(user.getPassword()));
					user.setPersonal(true);
					user.setCheckcode(check);
					userBiz.add(user);					
					logger.info("[ "+user.getUserName()+" ]添加成功!");
					this.outJson(response,new JSONResult(true,"帐号[" + user.getUserName() + "]添加成功!"));	
				}else{
					this.outJson(response,new JSONResult(false,"该用户名已存在!"));
				}
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"帐号[" + user.getUserName() + "]添加失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("帐号添加失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	
	/**
	 * @author cs
	 * 发送邮件
	 */
	@RequestMapping(value = "/sendmail")
	public void sendmail(HttpServletRequest request, Model model,HttpServletResponse response, String email) {
		String s = RandomUtils.generateRandomNumber();
		if (MessageUtil.sendMail("邮箱激活","请点击以下链接激活你的账号" + "www.baidu.com",email)) {
			request.getSession().removeAttribute(email);
			request.getSession().setAttribute(email, s);
			this.outJson(response, new JSONResult(true, "成功发送邮箱验证码"));
		} else {
			this.outJson(response, new JSONResult(false, "不能发送邮箱验证码"));
		}
	}
	/**
	 * @author cs
	 * 核对验证码
	 */
	@RequestMapping(value = "/checkcode")
	public void checkcode(HttpServletRequest request, Model model,HttpServletResponse response, String checkcode,String email) {
		if(checkcode!=null&&request.getSession().getAttribute(email).equals(checkcode)){
			this.outJson(response, new JSONResult(true, "验证码正确"));
		}else{
			this.outJson(response, new JSONResult(false, "验证码错误"));
		}
	}
	
	/**
	 * @author cs
	 * @since 找回会员名
	 */
	@RequestMapping(value = "/find_user")
	public String find_user(HttpServletRequest request) {
		String direction = request.getParameter("direction");
		if(direction!=null&&!"".equals(direction)){
			request.setAttribute("direction", direction);
		}
		return "find_user";
	}
	
	/**
	 * @author cs
	 * @since 处理找回会员名
	 */
	@RequestMapping(value = "/handelfind_user")
	public void handelfind_user(HttpServletRequest request, Model model,HttpServletResponse response,String email) {
		User user =userBiz.findUserByEmail(email).get(0);
		try{
			HtmlEmail mailer = new HtmlEmail();
			mailer.setCharset("utf-8");
			mailer.setHostName(ResourceBundle.getBundle("config").getString("email.host"));
			mailer.setSSLOnConnect(true);
			mailer.setAuthenticator(new DefaultAuthenticator(ResourceBundle.getBundle("config").getString("email.name"), ResourceBundle.getBundle("config").getString("email.password")));
			mailer.addTo(user.getEmail());
			mailer.setFrom(ResourceBundle.getBundle("config").getString("email.name"),"中小企业公共服务平台");
			mailer.setSubject("SMEmall用户取回用户名确认邮件");
			
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
								+ "尊敬的用户：<br>"
								+ "您好!<br>"
								+ "<p>您在<a href='http://www.smemall.net' target='_blank'>http://www.SMEmall.net</a>网站的用户名为"
								+ "<span style='font-size:15px;font-weight:bold;'>&nbsp;&nbsp;"
								+ user.getUserName()+"</span>，请您妥善保管。</p>"
								+ "<p>如您有任何问题或需要帮助，请联系我们。</p>"
								+ "</div>"
								+ "<div style='font-size:12px;border-top:1px solid #ccc;'>"
								+ "<p style='margin:0;padding:0;'>网址:<a href='http://www.smemall.net' target='_blank'>http://www.SMEmall.net</a></p>"
								+ "<p style='margin:0;padding:0;'>邮箱:admin@pservice.cn</p>"
								+ "</div>" +
						"</html>");
				mailer.setHtmlMsg(htmlEmailTemplate);
				mailer.setTextMsg("您的邮箱不支持HTML格式的邮件!");
				mailer.send();
				
				this.outJson(response, new JSONResult(true, "用户名已发送至邮箱,请立即登录邮箱进行查看"));
			} catch (Exception e) {
				logger.info(e.getLocalizedMessage());
				this.outJson(response, new JSONResult(false, "邮件发送失败，请稍后再试"));
			}
		}catch(EmailException e){
			logger.info(e.getLocalizedMessage());
			this.outJson(response, new JSONResult(false, "邮件发送失败，请稍后再试"));
		}
		/*********************************************************/
//		StringBuilder html = new StringBuilder("<h4>尊敬的用户：</h4>");
//		html.append("您好!<p><p>");
//		html.append("您在SMEmall.com.cn网站的会员名为<span style='font-size:14px;font-weight:bold;'>"+user.getUserName()+"</span>,请您妥善保管.");
//		html.append("<p>如您有任何问题或需要帮帮助,请联系我们.");
//		
//		if(MessageUtil.sendMail("SMEmall用户取回会员名确认邮件", html.toString(), email)){
//			this.outJson(response, new JSONResult(true, "会员名已发送至邮箱"));
//		}else{
//			this.outJson(response, new JSONResult(false, "邮箱发送失败，请稍后再试"));
//		}
	}
	
	/**
	 * @author cs
	 * @since 找回密码
	 */
	@RequestMapping(value = "/find_password")
	public String find_password(HttpServletRequest request) {
		String direction = request.getParameter("direction");
		if(direction!=null&&!"".equals(direction)){
			request.setAttribute("direction", direction);
		}
		return "find_password";
	}
	/**
	 * @author cs
	 * @since 处理找回密码
	 */
	@RequestMapping(value = "/handelfind_password")
	public void handelfind_password(HttpServletRequest request, Model model,HttpServletResponse response, String username,String email) {
		User user =userBiz.findUserByEmail(email).get(0);
		try{
			String s = RandomUtils.generateRandomNumber();	//新密码
			HtmlEmail mailer = new HtmlEmail();
			mailer.setCharset("utf-8");
			mailer.setHostName(ResourceBundle.getBundle("config").getString("email.host"));
			mailer.setSSLOnConnect(true);
			mailer.setAuthenticator(new DefaultAuthenticator(ResourceBundle.getBundle("config").getString("email.name"), ResourceBundle.getBundle("config").getString("email.password")));
			mailer.addTo(user.getEmail());
			mailer.setFrom(ResourceBundle.getBundle("config").getString("email.name"),"中小企业公共服务平台");
			mailer.setSubject("SMEmall用户取回密码确认邮件");
			
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
								+ "尊敬的用户：<br>"
								+ "您好!<br>"
								+ "<p>您在<a href='http://www.smemall.net' target='_blank'>http://www.SMEmall.net</a>网站的用户名为"
								+ "&nbsp;&nbsp;<span style='font-size:14px;font-weight:bold;'>"
								+user.getUserName()
								+ "</span>的新密码为&nbsp;&nbsp;<span style='font-size:14px;font-weight:bold;'>"+s
								+"</span>，请您尽快登录修改密码妥善保管。</p>"
								+ "<p>如您有任何问题或需要帮助，请联系我们。</p>"
								+ "</div>"
								+ "<div style='font-size:12px;border-top:1px solid #ccc;'>"
								+ "<p style='margin:0;padding:0;'>网址:<a href='http://www.smemall.net' target='_blank'>http://www.SMEmall.net</a></p>"
								+ "<p style='margin:0;padding:0;'>邮箱:admin@pservice.cn</p>"
								+ "</div>" +
						"</html>");
				mailer.setHtmlMsg(htmlEmailTemplate);
				mailer.setTextMsg("您的邮箱不支持HTML格式的邮件!");
				mailer.send();
				user.setPassword(MD5.toMD5(s));
				userBiz.update(user);
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
						UserSyncBean(user, SyncFactory.getSyncType(user.getEnterprise()))));
				this.outJson(response, new JSONResult(true, "新密码已发送至邮箱,请立即登录邮箱进行查看"));
			} catch (Exception e) {
				logger.info(e.getLocalizedMessage());
				this.outJson(response, new JSONResult(false, "邮件发送失败，请稍后再试"));
			}
		}catch(EmailException e){
			logger.info(e.getLocalizedMessage());
			this.outJson(response, new JSONResult(false, "邮件发送失败，请稍后再试"));
		}
		/*
		User u =userBiz.findUserByName(username).get(0);
		if(u.getEmail().equals(email)){
			String s = RandomUtils.generateRandomNumber();
			u.setPassword(MD5.toMD5(s));
			userBiz.update(u);
			if(MessageUtil.sendMail("重置密码", "你用户名为"+username+"的账号的新密码为"+s+"，请你速度登录更换密码！", email)){
				this.outJson(response, new JSONResult(true, "密码已发送至邮箱"));
			}else{
				this.outJson(response, new JSONResult(false, "邮箱发送失败，请稍后再试"));
			}
		}else{
			this.outJson(response, new JSONResult(false, "您填写的邮箱与用户名不匹配"));
		}*/
	}
	
	/**
	 * @author cs
	 * @since 用户登录
	 */
	@RequestMapping(value = "/user_login")
	public String user_login(HttpServletRequest request) {
		String direction = request.getParameter("direction");
		if(direction!=null&&!"".equals(direction)){
			request.setAttribute("direction", direction);
		}
		return "user_login";
	}
	
	/**
	 * 入口：http://localhost/user/user_login
	 * 会员登录验证
	 * @param page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userlogin")
	public String userlogin(HttpServletRequest request,HttpServletResponse response,Model model,String username,String password,String checkcode,String direction) {
		List<User> list = userBiz.findUserByName(username);
		if(checkcode.equals(request.getSession().getAttribute("authcode").toString())){
			if(list!=null&&list.size()!=0){
				User u = list.get(0);
				if(u.getPassword().equals(MD5.toMD5(password))){
					request.getSession().setAttribute("user", u);
					if(direction!=null&&!"".equals(direction)){
						return direction;
					}else{
						return "index";
					}
	//				this.outJson(response, new JSONResult(true, "登录成功"));
				}else{
					request.setAttribute("msg", "密码错误");
					return "main/user_login";
	//				this.outJson(response, new JSONResult(false, "密码错误"));
				}
			}else{
				request.setAttribute("msg", "用户名不存在");
				return "main/user_login";
	//			this.outJson(response, new JSONResult(false, "用户名不存在！"));
			}
		}else{
			request.setAttribute("msg", "验证码错误");
			return "main/user_login";
		}
	}
	
	/**
	 * @author cs
	 * 注册完毕
	 */
	@RequestMapping(value = "/registerover")
	public String registerover(@RequestParam("licenceDuplicatefile")CommonsMultipartFile licenceDuplicatefile,@RequestParam("businessLetterfile")CommonsMultipartFile businessLetterfile,
							HttpServletRequest request, Model model,HttpServletResponse response, 
							Enterprise enterprise,Integer sex,String occupation,Integer id,
							String tel1,String tel2) {
		String uploadPath = "upload";
		String contextPath = request.getSession().getServletContext().getRealPath(uploadPath);
		File filepath = new File(contextPath);
		if (!filepath.exists()) {
			filepath.mkdir();
			logger.info("["+filepath.getAbsolutePath()+"]创建成功!");
		}
		
		if (!licenceDuplicatefile.isEmpty()) {
		   String fileName = licenceDuplicatefile.getOriginalFilename();
		   String fileType = fileName.substring(fileName.lastIndexOf("."));
//				   System.out.println(fileType); 
		   File file = new File(contextPath+"/images",new Date().getTime() + fileType); //新建一个文件
		   try {
			   licenceDuplicatefile.getFileItem().write(file); //将上传的文件写入新建的文件中
			   enterprise.setLicenceDuplicate(file.getName());
		   } catch (Exception e) {
			    e.printStackTrace();
		   }
		}
		if (!businessLetterfile.isEmpty()) {
			String fileName = businessLetterfile.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
//				System.out.println(fileType); 
			File file = new File(contextPath+"/images",new Date().getTime() + fileType); //新建一个文件
			try {
				businessLetterfile.getFileItem().write(file); //将上传的文件写入新建的文件中
				enterprise.setBusinessLetter(file.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		enterprise.setTel(tel1+tel2);
		User u =userBiz.findUserById(id);
		/*u.setEid(enterpriseBiz.save(enterprise).getId());
		u.setOccupation(occupation);*/
		userBiz.update(u);
		return "redirect:/";
//		this.outJson(response, new JSONResult(true, "资料已完善"));
	}
	
	/**
	 * 检查用户名
	 * @param request
	 * @param model
	 * @param response
	 * @param username
	 */
	@RequestMapping(value = "/checkname")
	public void checkname(HttpServletRequest request, Model model,HttpServletResponse response, String username) {
		if((userBiz.findUserByName(username)==null||userBiz.findUserByName(username).size()==0)&&staffBiz.findByUserName(username)==null){
			// 去服务机构注册中 是否存在 处理中 的 userName 与之相同
			List<OrgRegisterApproval> list = approveBiz.findOrgRegisterlistByUsername(username);
			if(list.size() > 0){
				this.outJson(response, new JSONResult(false, "该用户名已被注册，请更换用户名"));
			}else{
				this.outJson(response, new JSONResult(true, "一旦注册成功不能修改"));
			}
		}else{
			this.outJson(response, new JSONResult(false, "该用户名已被注册，请更换用户名"));
		}
	}
	
	/**
	 * 检查用户名和邮箱是否匹配
	 * @param request
	 * @param model
	 * @param response
	 * @param username
	 */
	@RequestMapping(value = "/nameMateEmail")
	public void nameMateEmail(HttpServletRequest request, Model model,HttpServletResponse response, String username,String email) {
		if(userBiz.findUserByName(username)!=null&&userBiz.findUserByName(username).size()!=0){
			User u = userBiz.findUserByName(username).get(0);
			if(u.getEmail().equals(email)){
				this.outJson(response, new JSONResult(true, "用户名和邮箱匹配"));
			}else{
				this.outJson(response, new JSONResult(false, "用户名和邮箱不匹配"));
			}
		}else{
			this.outJson(response, new JSONResult(false, "用户名不存在"));
		}
	}
	
	/**
	 * 检查邮箱是否可用
	 * @param request
	 * @param model
	 * @param response
	 * @param username
	 */
	@RequestMapping(value = "/checkEmail")
	public void checkEmail(HttpServletRequest request, Model model,HttpServletResponse response,String email) {
		if(userBiz.findUserByEmail(email)!=null&&userBiz.findUserByEmail(email).size()!=0){
				this.outJson(response, new JSONResult(false, "邮箱已经被注册，请更换邮箱"));
			}else{
				this.outJson(response, new JSONResult(true, "邮箱没有被注册，可以使用该邮箱"));
			}
	}
	
	/**
	 * @author cs
	 * 注册第二步
	 * @param enterprise_type 行业代码==所属窗口 1——15
	 * @param enterpriseType 企业类型 1 企业  3  机构
	 * @param enterpriseName 企业名称
	 * @param user 用户实体
	 * @throws Exception 
	 */
	@RequestMapping(value = "/register_step_2")
	public void registerStep2(
			Model model,
			User user,
			Integer enterprise_type,
			String enterpriseName,
			Integer enterpriseType,
			Integer type,
			Integer enterpriseIndustry,	//xuwf 2013-11-23添加一个企业属性:企业所属行业
			HttpServletRequest request, HttpServletResponse response){
		String s = RandomUtils.generateRandomNumber();
		String check= s+"1|"+System.currentTimeMillis();
		SendEmail sendEmail = new SendEmail();
		sendEmail.setOrigin(1);
		sendEmail.setHandle(true);
		sendEmail.setSender(ResourceBundle.getBundle("config").getString("email.name"));
		sendEmail.setReceiver(user.getEmail());
			
		if(userBiz.findUserByName(user.getUserName())==null||userBiz.findUserByName(user.getUserName()).size()==0){
			//非个人用户
			if(type == 1){
				//保存企业
				Enterprise enterprise = new Enterprise();
				enterprise.setIndustryType(enterprise_type);
				enterprise.setEnterpriseIndustry(enterpriseIndustry);	//新增企业所属行业属性
				enterprise.setName(enterpriseName);
				enterprise.setType(1);
				enterprise.setEmail(user.getEmail());
				
				user.setApproved(false);
				user.setPersonal(false);
				user.setCheckcode(check);
				user.setPassword(user.getPassword());
				user.setUserStatus(1);
				user.setSourceId(0);
				user.setStatus(new java.util.Date().getTime());
				
				user = userBiz.saveRegisterTransaction(sendEmail, enterprise, user,8);
				
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new 
						EnterpriseSyncBean(user.getEnterprise(), SyncFactory.getSyncType(user.getEnterprise()))),true);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new 
						UserSyncBean(user, SyncFactory.getSyncType(user.getEnterprise()))));
				this.outJson(response, new JSONResult(true, String.valueOf(1)));
				/**
				 *************************************************************************************
				 * @author：lwch
				 * @date: 2013-11-09
				 * @description：如果用户注册成功，那么就立刻向在线客服系统也注册该企业的初始化相关信息
				 */
				Map<String, Object> eMap = new HashMap<String, Object>();
				eMap.put("company", enterpriseName);
				eMap.put("email", user.getEmail());
				eMap.put("plateid", user.getEnterprise().getId());
				eMap.put("login", user.getUserName());
				eMap.put("password", user.getPassword());
				chatOnlineBiz.addChatEnterpriseRegister(eMap);
				/**
				 * ***********************************************************************************
				 */
				//发送邮件到这里
				sendEmailBiz.saveOrUpdate(saveEmail(user.getSendEmail(),user,request,check));
				
			//个人用户	
			}else if(type == 2){
				user.setApproved(false);
				user.setPersonal(true);
				user.setCheckcode(check);
				user.setPassword(user.getPassword());
				user.setUserStatus(1);
				user.setCertSign(0);	//设置未认证
				user.setSourceId(0);
				user.setStatus(new java.util.Date().getTime());
				userBiz.saveRegisterTransaction(sendEmail, null, user,3);
				
				this.outJson(response, new JSONResult(true, String.valueOf(2)));
				//发送邮件到这里
				sendEmailBiz.saveOrUpdate(saveEmail(user.getSendEmail(),user,request,check));
			}
		}else{
			this.outJson(response, new JSONResult(false, "用户名重复，请更换用户名"));
		}
	}
	
	
	//陈胜封装上面注册中的邮件发送
	private SendEmail saveEmail(SendEmail sendEmail,User user,HttpServletRequest request,String check){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/active";
		
		HtmlEmail mailer = new HtmlEmail();
		mailer.setCharset("utf-8");
		mailer.setHostName(ResourceBundle.getBundle("config").getString("email.host"));
		mailer.setSSLOnConnect(true);
		mailer.setAuthenticator(new DefaultAuthenticator(ResourceBundle.getBundle("config").getString("email.name"), ResourceBundle.getBundle("config").getString("email.password")));
		String htmlEmailTemplate;
		try {
			mailer.addTo(user.getEmail());
			mailer.setFrom(ResourceBundle.getBundle("config").getString("email.name"),"中小企业公共服务平台");
			mailer.setSubject("中小企业公共服务平台账号激活邮件<系统自动发出,请勿回复>");
			
			URL logo_url = null;
			
			logo_url = new URL(ResourceBundle.getBundle("config").getString("email.log"));
			
			String logo_src = mailer.embed(logo_url, "smemall-logo");
			htmlEmailTemplate = (
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
			sendEmail.setContent(htmlEmailTemplate);
			mailer.send();
			return sendEmail;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return sendEmail;
		} catch (EmailException e) {
			e.printStackTrace();
			return sendEmail;
		}
	}
	
	/**
	 * @author cs
	 * 注册第二步
	 */
	@RequestMapping(value = "/register_success")
	public String register_success(HttpServletRequest request, Model model,HttpServletResponse response,Integer enterpriseType) {
		String direction = request.getParameter("direction");
		if(direction!=null&&!"".equals(direction)){
			request.setAttribute("direction", direction);
		}
		return "register_leading"+enterpriseType;
	}
	
	/**
	 * 服务机构注册申请成功
	 * @author Xiadi
	 * @since 2013-11-5 
	 *
	 * @param request
	 * @param model
	 * @param response
	 * @param enterpriseType
	 * @return
	 */
	@RequestMapping(value = "/orgregister_success")
	public String orgregister_success(HttpServletRequest request, Model model,HttpServletResponse response) {
		return "orgregister_success";
	}
	
	/**
	 * 服务机构注册
	 * @author Xiadi
	 * @since 2013-11-5 
	 *
	 * @param request
	 * @param response
	 * @param registerApproval
	 * @return
	 */
	@RequestMapping(value = "/org_register")
	public void org_register(HttpServletRequest request, HttpServletResponse response, OrgRegisterApproval registerApproval) {
		try {
			approveBiz.addOrgRegister(registerApproval);
			// 同步至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrgRegisterApproval>(new 
					OrgRegisterSyncBean(registerApproval, SyncType.ONE)));
			this.outJson( response, new JSONResult(true,  "服务机构注册申请成功"));
		} catch (Exception e) {
			this.outJson( response, new JSONResult(false, 
					"服务机构注册申请失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("服务机构注册申请失败!异常信息:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 获取服务机构注册列表
	 * @author Xiadi
	 * @since 2013-11-5 
	 *
	 * @param username
	 * @param enterpriseName
	 * @param applyTimeBegin
	 * @param applyTimeEnd
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/orgRegisterlist")
	public void getOrgRegisterlist(String username, String enterpriseName, 
			String applyTimeBegin, String applyTimeEnd,
			Integer applyType, Integer start, Integer limit,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			this.outJson(response, approveBiz.findOrgRegisterlist(username,
					enterpriseName, applyTimeBegin, applyTimeEnd, start, limit));
		} catch (Exception e) {
			this.outJson( response, new JSONResult(false, 
					"查询失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("查询失败!异常信息:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 审核服务机构注册
	 * @author Xiadi
	 * @since 2013-11-5 
	 *
	 * @param approvedDetail
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/approveOrgRegister")
	public void approve(Integer rid, Integer status, HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		try {
			Manager manager = (Manager)request.getSession().getAttribute("manager");
			OrgRegisterApproval orgRegisterApproval = approveBiz.getOrgRegister(rid);
			// 通过时，判断用户或企业是否存在
			if (status == Constant.SERVICE_AUDIT_PASS) {
				if (userBiz.findUserByName(orgRegisterApproval.getUserName()).size() > 0) {
					this.outJson(response,new JSONResult(false, "审核失败！会员名已经存在"));
					return;
				}
				if (userBiz.findUserByEmail(orgRegisterApproval.getEmail()).size() > 0) {
					this.outJson(response, new JSONResult(false, "审核失败！注册邮箱已经存在"));
					return;
				}
				if (enterpriseBiz.findEnterpriseByName(orgRegisterApproval.getOrgName()).size() > 0) {
					this.outJson(response, new JSONResult(false, "审核失败！公司实名已经存在"));
					return;
				}
//				if (enterpriseBiz.findByIcRegNumber(orgRegisterApproval.getIcRegNumber()) != null) {
//					this.outJson(response, new JSONResult(false, "审核失败！组织机构号已经存在"));
//					return;
//				}
			}
			User user = approveBiz.addOrgRegisterApprove(orgRegisterApproval, status, manager);
			if (user != null) {
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(new EnterpriseSyncBean(user.getEnterprise(), SyncType.ALL)));
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(new UserSyncBean(user, SyncType.ALL)));
			}
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrgRegisterApproval>(new 
					OrgRegisterSyncBean(orgRegisterApproval, SyncType.ONE)));
			this.outJson(response, new JSONResult(true, "服务机构注册审核成功"));
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "服务器异常!异常信息:" + e.getLocalizedMessage()));
			logger.info("服务器异常!异常信息:" + e.getLocalizedMessage());
			throw new Exception("审核服务机构注册异常");
		}
	}
	
	/**
	 * @date: 2013-12-13
	 * @author：lwch
	 * @description：获取平台用户信息
	 */
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	public void getUserInfo(
			@RequestParam("username")String username,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<User> users = userBiz.getUserInfo(username);
			if (users.size() > 0) {
				User user = users.get(0);
				this.outJson(response, new JSONEntity(true, user));
			} else {
				List<Staff> staffs = staffBiz.findStaffByUserName(username);
				if (staffs.size() > 0) {
					Staff staff = staffs.get(0);
					this.outJson(response, new JSONEntity(true, staff));
				} else {
					this.outJson(response, new JSONResult(false, "对不起您输入的帐号不存在！"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查找企业用户异常" + e.getLocalizedMessage());
			this.outJson(response, new JSONResult(false, "查找企业用户异常" + e.getLocalizedMessage()));
		}
		
	}
}
