package com.eaglec.plat.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eaglec.plat.biz.user.StaffMenuBiz;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.StaffMenu;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
/**
 * Session AOP切面 
 * 
 * @author Xiadi
 * @since 2013-9-25
 */
@Component
@Aspect
public class SessionAOP {
	
	@Autowired
	private StaffMenuBiz staffMenuBiz;

	//全局url，属于staffmenu表里面的所有菜单连接
	static List<String> allurl = null; 
	private List<String> getAllurl(){
		if(allurl==null){
			allurl = staffMenuBiz.findAllCode(Constant.ROLE_UCENTER_ENTERPRISE);
			return allurl;
		}else{
			return allurl;
		}
	}
	@Around(value = "@annotation(com.eaglec.plat.aop.NeedSession)")
	public Object aroundManager(ProceedingJoinPoint pj) throws Exception {
		HttpServletRequest request = SysContent.getRequest();
		HttpServletResponse response = SysContent.getResponse();
		HttpSession session = SysContent.getSession();

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		SessionType type = this.getSessionType(pj);
		if (type == null) {
			throw new Exception("The value of NeedSession is must.");
		}

		Object uobj = session.getAttribute("user");
		Object mobj = session.getAttribute("manager");
		
		boolean isUser = type == SessionType.USER && uobj != null;
		boolean isManager = type == SessionType.MANAGER && mobj != null;
		boolean isUserOrManager = type == SessionType.OR&& (mobj != null || uobj != null);
		try {
			if (isUser || isManager || isUserOrManager) {
				if(uobj != null){
					String errorUrl = this.validateUser(uobj, request, response, session);
					if("".equals(errorUrl)){
						return pj.proceed();
					}else{
						response.sendRedirect(basePath + errorUrl);
					}
				}else{
					return pj.proceed();
				}
			} else {
				if (request.getHeader("x-requested-with") != null    
				        && request.getHeader("x-requested-with").equalsIgnoreCase(    //ajax超时处理     
				                "XMLHttpRequest")) {     
				    response.addHeader("sessionstatus", "timeout"); 
				    // 解决EasyUi问题
				    response.getWriter().print("{\"rows\":[],\"success\":false,\"total\":0}");     
				}else{//http超时的处理     
					response.sendRedirect(basePath + "error/nosession");
				}  
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private SessionType getSessionType(ProceedingJoinPoint pj) {
		// 获取切入的 Method
		MethodSignature joinPointObject = (MethodSignature) pj.getSignature();
		Method method = joinPointObject.getMethod();
		boolean flag = method.isAnnotationPresent(NeedSession.class);
		if (flag) {
			NeedSession annotation = method.getAnnotation(NeedSession.class);
			return annotation.value();
		}
		return null;
	}
	
	/**
	 * 企业用户中心权限验证，返回 "url" or ""<br/>
	 * 
	 * "url"	表示未通过，需要重定向至错误页面<br/>
	 * "" 		表示通过，不需要重定向至错误页面
	 * @author Xiadi
	 * @since 2013-10-21 
	 *
	 * @param uobj
	 * @param request
	 * @param response
	 * @return null or error url
	 */
	private String validateUser(Object uobj, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String ret = "";
		Object smobj = session.getAttribute("staffmenu");
		
		if(this.getAllurl().contains(request.getRequestURI().substring(1))){
			if (uobj instanceof User) {
				User user = (User)uobj;
				String surl="";
				if(!user.getIsPersonal()){
					//普通企业
					if(user.getEnterprise().getType()==Constant.ENTERPRISE_TYPE_PUBLIC){
						surl = ResourceBundle.getBundle("config").getString("user.noapprove");
						//认证企业
					}else if (user.getEnterprise().getType()==Constant.ENTERPRISE_TYPE_REALNAME){
						surl = ResourceBundle.getBundle("config").getString("user.approve");
						//服务机构
					}else if (user.getEnterprise().getType()==Constant.ENTERPRISE_TYPE_ORG){
						surl = ResourceBundle.getBundle("config").getString("user.organization");
					}
				}else{
					surl = ResourceBundle.getBundle("config").getString("user.organization");
				}
				
				if (!surl.contains(request.getRequestURI().substring(1))) {
					ret = "error/nosession?reason=zhuzhanghao";
				}
			} else if (uobj instanceof Staff) {
				if (smobj != null) {
					@SuppressWarnings("unchecked")
					List<StaffMenu> sm = (List<StaffMenu>) smobj;
					// surl表示当前子账号存放在session中的菜单连接集合
					List<String> surl = recursion(sm);
					// 当请求url不属于srul
					if (!surl.contains(request.getRequestURI().substring(1))) {
						ret = "error/nosession?reason=zizhanghao";
					}
				} else {
					ret = "error/nosession?reason=zizhanghaonull";
				}
			} else {
				ret = "error/nosession?reason=feifayonghu";
			}
		}
		return ret;
	}
	
	//根据子账号权限列表递归封装出链接地址集合
	List<String> have = new ArrayList<String>();
	private List<String> recursion(List<StaffMenu> sm ){
		for(StaffMenu s:sm){
			if(s.isLeaf()){
				have.add(s.getAuthCode());
			}else{
				recursion(s.getChildren());
			}
		}
		return have;
	}
}
