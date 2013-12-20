package com.eaglec.plat.interceptor;

import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eaglec.plat.HomeController;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.CookieHelper;
import com.eaglec.plat.utils.MD5;

@Repository
public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private StaffBiz staffBiz;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Cookie SM_LOGIN = CookieHelper.getCookieByName(request, ResourceBundle.getBundle("config").getString("ucenter.cookie.pre"));
		if(SM_LOGIN == null){
			if(request.getSession().getAttribute("user") != null && request.getSession().getAttribute("usertype") != null){
				request.getSession().removeAttribute("user");
				request.getSession().removeAttribute("usertype");
				logger.info("用户登陆信息清除成功!");
			}
		}else{
			String[] _code = SM_LOGIN.getValue().split("\\|");
			String type = _code[0];
			String uid = _code[1];
			String token = _code[2];
			if(type.equals("user")){
				User user = userBiz.findUserByUid(uid);
				if(user != null){
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("usertype", Constant.LOGIN_USER);
					request.getSession().setAttribute("loginUser", user);
					if(!user.getIsPersonal()){
						request.getSession().setAttribute("loginEnterprise", user.getEnterprise());
					}
					logger.info("["+user.getUserName()+"]同步登陆成功!");
				}
			}else if(type.equals("staff")){
				Staff staff = staffBiz.findByStid(uid);
				if(staff != null && MD5.toMD5(staff.getUserName()+staff.getPassword()).equals(token)){
					request.getSession().setAttribute("user", staff);
					request.getSession().setAttribute("usertype", Constant.LOGIN_STAFF);
					//保存登录者企业信息
					request.getSession().setAttribute("loginEnterprise", staff.getParent().getEnterprise());
					logger.info("["+staff.getUserName()+"]同步登陆成功!");
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
