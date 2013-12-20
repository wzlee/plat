package com.eaglec.plat.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }  
	}
	 
	 
	 
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){ 
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	 /**
     * Convenience method to set a cookie
     * @param response
     * @param name
     * @param value
     * @param path
     */
    public static void setCookie(HttpServletResponse response, String name,
                                 String value, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
//        cookie.setMaxAge(Constants.COOKIE_INVALID_TIME);
        response.addCookie(cookie);
 //       logger.info("setCookie 完成.......");
    }
    
    /** 
     * 清空cookie 
     */  
    public static void clearCookie(HttpServletRequest request,HttpServletResponse response, String path,String domain) {  
      Cookie[] cookies = request.getCookies();  
      try  
      {  
           for(int i=0;i<cookies.length;i++)    
           {  
            Cookie cookie = new Cookie(cookies[i].getName(), null);  
            cookie.setMaxAge(0);
            cookie.setDomain(domain);
            cookie.setPath(path);//根据你创建cookie的路径进行填写      
            response.addCookie(cookie);  
           }  
      }catch(Exception ex)  
      {  
           System.out.println("清空Cookies发生异常！");  
      }   
    }  
}
