package com.eaglec.plat.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.biz.flat.FlatBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.CookieHelper;
import com.eaglec.plat.utils.MD5;
import com.eaglec.plat.view.APIResult;

@Controller
@RequestMapping(value="/oauth")
public class OauthController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(OauthController.class);
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private StaffBiz staffBiz;
	@Autowired
	private FlatBiz flatBiz;
	
	@RequestMapping(value = "/openid")
	public void userOpenid(@RequestParam String type,@RequestParam String code,@RequestParam String status,HttpServletRequest request,HttpServletResponse response) {
		String client_ip = request.getRemoteAddr();
		String client_host = request.getRemoteHost();
		if(code == null || code.isEmpty() || status == null || status.isEmpty()|| type == null || type.isEmpty()){
			this.outJson(response, new APIResult(false,10001,"传入参数有误!"));
		}else if(type.equals("u")){
			if(new Date().getTime() - Long.parseLong(status) > 3600000){
				this.outJson(response, new APIResult(false,99999,"请求已超过有效期!"));
			}else{
				User user = userBiz.findUserByCodeAndStatus(code, status);
				if(user == null){
					this.outJson(response, new APIResult(false,10000,"传入参数有误!"));
				}else{
					this.outJson(response,new APIResult(true,00000,user.getUid()));
					logger.info(client_host+"["+client_ip+"]通过同步登陆获取用户："+user.getUserName()+"["+user.getId()+"]的openID成功!");
				}
			}
		}else if(type.equals("s")){
			if(new Date().getTime() - Long.parseLong(status) > 3600000){
				this.outJson(response, new APIResult(false,99999,"请求已超过有效期!"));
			}else{
				Staff staff = staffBiz.findByCodeAndStatus(code, status);
				if(staff == null){
					this.outJson(response, new APIResult(false,10000,"传入参数有误!"));
				}else{
					this.outJson(response,new APIResult(true,00000,staff.getStid()));
					logger.info(client_host+"["+client_ip+"]通过同步登陆获取用户："+staff.getStaffName()+"["+staff.getId()+"]的openID成功!");
				}
			}
		}else{
			this.outJson(response, new APIResult(false,100011,"["+type+"]不是有效的用户类型!"));
		}
	}
	
	/**
	 * @author wzlee
	 * 窗口平台单点登录获取枢纽平台用户信息
	 */
	@RequestMapping(value = "/user")
	public void userInfo(@RequestParam String type,@RequestParam String uid,@RequestParam String token,HttpServletRequest request,HttpServletResponse response) {
		String client_ip = request.getRemoteAddr();
		String client_host = request.getRemoteHost();
		if(type == null || type.isEmpty() || uid == null || uid.isEmpty() || token == null || token.isEmpty()){
			this.outJson(response, new APIResult(false,10102,"missing required params code[缺少必要参数(type/uid/token)]"));
		}else{
			if(type.equals("user")){
				User user = userBiz.findUserByUid(uid);
				if(user == null){
					this.outJson(response, new APIResult(false,101003,"不存在与请求参数匹配的用户"));
				}else{
					if(MD5.toMD5(user.getUserName()+user.getPassword()).equals(token)){
						if(user.getIsPersonal()){
							this.outJson(response,new User(user.getUserName(),user.getSex(), user.getUserStatus(),user.getRegTime(), user.getIsPersonal(), user.getEmail(),user.getIsApproved(),user.getSourceId(),user.getRemark(),null,user.getUid()));
							logger.info(client_host+"["+client_ip+"]通过同步登陆获取个人用户："+user.getUserName()+"["+user.getId()+"]的信息成功!");
						}else{
							this.outJson(response,new User(user.getUserName(),user.getSex(), user.getUserStatus(),user.getRegTime(), user.getIsPersonal(), user.getEmail(),user.getIsApproved(),user.getSourceId(),user.getRemark(),
									new Enterprise(user.getEnterprise().getEid(),user.getEnterprise().getEnterpriseProperty(),user.getEnterprise().getEnterpriseCode(),
											user.getEnterprise().getName(),user.getEnterprise().getForShort(), user.getEnterprise().getType(),user.getEnterprise().getLegalPerson(),
											user.getEnterprise().getLinkman(),user.getEnterprise().getTel(), user.getEnterprise().getEmail(),user.getEnterprise().getAddress(),
											user.getEnterprise().getIndustryType(),user.getEnterprise().getMainRemark(), user.getEnterprise().getOpenedTime(),
											user.getEnterprise().getIsApproved(), user.getEnterprise().getIcRegNumber(), user.getEnterprise().getProfile()),user.getUid()));
							logger.info(client_host+"["+client_ip+"]通过同步登陆获取用户："+user.getUserName()+"["+user.getId()+"]的信息成功!");
						}
					}else{
						this.outJson(response, new APIResult(false,101000,"请求参数[token]不匹配"));
					}
				}
			}else if(type.equals("staff")){
				Staff staff = staffBiz.findByStid(uid);
				if(staff == null){
					this.outJson(response, new APIResult(false,101003,"不存在与请求参数匹配的用户"));
				}else{
					if(MD5.toMD5(staff.getUserName()+staff.getPassword()).equals(token)){
						this.outJson(response, staff);
					}else{
						this.outJson(response, new APIResult(false,101000,"请求参数[token]不匹配"));
					}
				}
			}else{
				this.outJson(response, new APIResult(false,101000,"请求参数[type]有误"));
			}
		}
	}
	
	/**
	 * @author wzlee
	 * 客户端返回枢纽首页
	 */
	@RequestMapping(value = "/home")
	public String oauthIndex(HttpServletRequest request,HttpServletResponse response, Model model) {
		Cookie token = CookieHelper.getCookieByName(request, "token");
		if(token == null){
			
		}else{
			String[] _token = token.getValue().split("\\|");
			if(_token[0].equals("u")){
				User user = userBiz.findUserByCodeAndStatus(_token[2], _token[1]);
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("usertype", Constant.LOGIN_USER);
				request.getSession().setAttribute("loginEnterprise", user.getEnterprise());
			}else if(_token[0].equals("s")){
				Staff staff = staffBiz.findByCodeAndStatus(_token[2], _token[1]);
				request.getSession().setAttribute("user", staff);
				request.getSession().setAttribute("usertype", Constant.LOGIN_STAFF);
				request.getSession().setAttribute("loginEnterprise", staff.getParent().getEnterprise());
			}else{
				return "error/404";
			}
		}
		return "redirect:/";
	}
	
}
