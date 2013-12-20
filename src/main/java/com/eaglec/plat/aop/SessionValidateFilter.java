package com.eaglec.plat.aop;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eaglec.plat.HomeController;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.CookieHelper;
import com.eaglec.plat.utils.MD5;
/**
 * Session验证过滤器
 * 
 * @author Xiadi
 * @since 2013-9-25
 */
public class SessionValidateFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*HttpServletRequest httpServletRequset = (HttpServletRequest)request;
		Cookie SM_LOGIN = CookieHelper.getCookieByName(httpServletRequset, "SM_LOGIN");
		if(SM_LOGIN == null){
	        @SuppressWarnings("unchecked")
			Enumeration<String> em = httpServletRequset.getSession().getAttributeNames();
	        
	        while(em.hasMoreElements()){
	        	httpServletRequset.getSession().removeAttribute(em.nextElement().toString());
	        }
	        httpServletRequset.getSession().invalidate();
	        logger.info("session清除成功!");
		}else{
			String[] _code = SM_LOGIN.getValue().split("\\|");
			String type = _code[0];
			String uid = _code[1];
			String token = _code[2];
			if(type.equals("user")){
				User user = userBiz.findUserByUid(uid);
				if(user != null && MD5.toMD5(user.getUserName()+user.getPassword()).equals(token)){
					httpServletRequset.getSession().setAttribute("user", user);
					httpServletRequset.getSession().setAttribute("usertype", Constant.LOGIN_USER);
					logger.info("["+user.getUserName()+"]同步登陆成功!");
				}
			}else if(type.equals("staff")){
				Staff staff = staffBiz.findByStid(uid);
				if(staff != null && MD5.toMD5(staff.getUserName()+staff.getPassword()).equals(token)){
					httpServletRequset.getSession().setAttribute("user", staff);
					httpServletRequset.getSession().setAttribute("usertype", Constant.LOGIN_STAFF);
					logger.info("["+staff.getUserName()+"]同步登陆成功!");
				}
			}
		}*/
		 SysContent.setRequest((HttpServletRequest) request);
		 SysContent.setResponse((HttpServletResponse) response);
		 chain.doFilter(request, response); 
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
