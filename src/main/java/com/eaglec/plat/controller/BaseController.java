package com.eaglec.plat.controller;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.StrUtils;

public class BaseController {
	
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private StaffBiz staffBiz;
	
	public void outHTML(HttpServletResponse response,String html) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(html);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void outHTMLP(HttpServletResponse response,String callback,String html) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(callback+"("+html+")");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public void outJson(HttpServletResponse response,Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteEnumUsingToString,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void outJson2(HttpServletResponse response, Object object) {
		try {
			response.setContentType("textml;charset=utf-8");
			String json = JSON.toJSONString(object,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
//			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("textml;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void outJson(HttpServletResponse response,List<?> list) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(list.toArray(),SerializerFeature.WriteDateUseDateFormat));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void outJson(HttpServletResponse response,List<?> list,PropertyFilter propertyFilter) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(list.toArray(),propertyFilter,SerializerFeature.WriteDateUseDateFormat));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出JSONP(跨域响应)
	 * @param object
	 */
	public void outJsonP(HttpServletResponse response,String callback,Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(callback+"("+json+")");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void outMallJson(HttpServletResponse response, Object object) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(
					JSON.toJSONString(object,
							SerializerFeature.WriteDateUseDateFormat,
							SerializerFeature.DisableCircularReferenceDetect));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void outJsonP(HttpServletResponse response, String callback, List<?> list) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(callback+"("+JSON.toJSONString(list.toArray(),SerializerFeature.WriteDateUseDateFormat)+")");			
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取当前登录用户类型
	 * 1 - 主帐号
	 * 2 - 子帐号
	 * 0 - 未登录
	 * @author Xiadi
	 * @since 2013-10-19 
	 *
	 * @param request
	 * @return
	 */
	public int getCurrentUserType(HttpServletRequest request) {
		Object o = request.getSession().getAttribute("usertype");
		return StrUtils.toInt(o);
	}
	
	public User getUserFromSession(HttpServletRequest request){
		Object o = request.getSession().getAttribute("user");
		return (User) o;
	}
	
	public Staff getStaffFromSession(HttpServletRequest request){
		Object o = request.getSession().getAttribute("user");
		return (Staff) o;
	}
	
	/**
	 * 保持用户的Session
	 * @author pangyf
	 * @since 2013-10-23
	 * @param request
	 */
	public void keepUserSession(HttpServletRequest request){
		if(this.getCurrentUserType(request)==Constant.LOGIN_USER){
			User user = this.getUserFromSession(request);
			user = userBiz.findUserById(user.getId());
			request.getSession().setAttribute("user",user);
			request.getSession().setAttribute("loginEnterprise", user.getEnterprise());			
		}else {
			Staff staff = this.getStaffFromSession(request);
			staff = staffBiz.getStaff(staff.getId());
			request.getSession().setAttribute("user",staff);
			request.getSession().setAttribute("loginEnterprise", staff.getParent().getEnterprise());
		}
	}
	
	/**
	 * 两个对象根据properties复制属性
	 * @author pangyf
	 * @since 2013-10-30
	 * @param source
	 * @param target
	 * @param properties
	 */
	public void copyProperties(Object source, Object target, String[] properties){		
		PropertyDescriptor sourcePd,targetPd;
		if(properties != null){
			for (String propertie : properties) {				
				sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), propertie);
				targetPd = BeanUtils.getPropertyDescriptor(target.getClass(), propertie);
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						Method writeMethod = targetPd.getWriteMethod();
						if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
							writeMethod.setAccessible(true);
						}
						writeMethod.invoke(target, value);
					}
					catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
				
			}		
		}
	}
	
}
