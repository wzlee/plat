package com.eaglec.plat.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

public class Common {

	public static String superAdmin;	//超级管理员名称
	public static String uploadPath;	//文件上传路径
	public static String attachmentPath;	//申诉附件上传路径
	public static int serviceHotNum;	//热门服务加载多少条数据
	public static int serviceNewNum;	//最新服务加载多少条数据
	public static int newsIndexNum;		//行业新闻加载多少条数据
	public static int resultNumJ;		//服务搜索结果分页显示数量（矩形）
	public static int resultNumL;		//服务搜索结果分页显示数量（列表形）
	public static int newsPageNum;		//新闻频道中的新闻列表，每页显示多少条数据
	public static String channelCode;	//频道编码	
	public static String CENTER_WEBSITE;		//枢纽平台网址	
	
	public static String chatUploadPath;		//在线客服上传文件路径
	public static String chatDomain;		//在线客服服务器地址
	public static int chatPort;		//在线客服服务器端口
	public static String chatDataBaseDriver;		//在线客服数据库驱动
	public static String chatDataBaseURL;		//在线客服数据库连接URL
	public static String chatDataBaseUser;		//在线客服数据库登录用户名
	public static String chatDataBasePassword;		//在线客服数据库登录密码
	public static String defaultUserImg;		//在线客服用户默认图像地址
	/**
	 * 系统自动发消息信息
	 * @author xuwf
	 * @since 2013-11-07
	 */
	public static String buyApply;					//订单申请时发送卖家的消息
	public static String sellerConfirmMessage1;		//卖家确认发送买家消息
	public static String sellerConfirmMessage2;		
	public static String sellerCancel;				//卖家取消订单发送买家消息
	public static String buyerCloseFirst;			//买家先关闭发送卖家消息
	public static String sellerCloseFirst;			//卖家先关闭发送买家消息
	public static String orderOver;					//买家关闭、卖家关闭变成交易结束发送消息给买卖双方
	public static String platCloseOrder;			//平台关闭订单发送买卖双方
	public static String platCancelOrder;			//平台取消订单发送买卖双方
	public static String biddingAuditOK;			//平台审核通过发送给买家（招标方）
	public static String biddingAuditFail;			//平台审核驳回发送给买家（招标方）
	public static String selectResponse;			//招标方选择应标发送给卖家 应标方
	public static String cancelBiddingService;		//招标方取消应标发送给所有应标方
	
	public static String appMoblieToken;            //移动端和服务端通信token
	public static String messageServer;				//平台app推送消息接口地址
	public static String messageCallback;			//平台消息推送回调接口地址
	public static String PLAT_DOMAIN;				//枢纽平台域名
	public static Integer WINDOW_ID;					//窗口对象的16个窗口号==企业表中的所属窗口
	
	//微信号信息
	public static String WXAPPID ;
	public static String WXAPPSECRET;
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		uploadPath = bundle.getString("uploadPath");
		attachmentPath = bundle.getString("attachmentPath");
		serviceHotNum = Integer.parseInt(bundle.getString("service.hotNum"));
		serviceNewNum = Integer.parseInt(bundle.getString("service.newNum"));
		newsIndexNum = Integer.parseInt(bundle.getString("news.indexNum"));
		resultNumJ = Integer.parseInt(bundle.getString("result.numJ"));
		resultNumL = Integer.parseInt(bundle.getString("result.numL"));
		newsPageNum = Integer.parseInt(bundle.getString("news.pageNum"));
		channelCode = bundle.getString("channel.code");		
		CENTER_WEBSITE = bundle.getString("center.website");
		
		chatUploadPath = bundle.getString("chat.uploadPath");
		chatDomain = bundle.getString("chat.domain");
		chatPort = Integer.parseInt(bundle.getString("chat.port"));
		chatDataBaseDriver = bundle.getString("chat.dataBaseDriver");
		chatDataBaseURL = bundle.getString("chat.dataBaseURL");
		chatDataBaseUser = bundle.getString("chat.dataBaseUser");
		chatDataBasePassword = bundle.getString("chat.dataBasePassword");
		defaultUserImg = bundle.getString("chat.defaultUserImg");
		
		buyApply = bundle.getString("message.buyApply");
		sellerConfirmMessage1 = bundle.getString("message.sellerConfirm1");
		sellerConfirmMessage2 = bundle.getString("message.sellerConfirm2");
		sellerCancel = bundle.getString("message.sellerCancel");
		buyerCloseFirst = bundle.getString("message.buyerCloseFirst");
		sellerCloseFirst = bundle.getString("message.sellerCloseFirst");
		orderOver = bundle.getString("message.orderOver");
		platCloseOrder = bundle.getString("message.platCloseOrder");
		platCancelOrder = bundle.getString("message.platCancelOrder");
		biddingAuditOK = bundle.getString("message.biddingAuditOK");
		biddingAuditFail = bundle.getString("message.biddingAuditFail");
		selectResponse = bundle.getString("message.selectResponse");
		cancelBiddingService = bundle.getString("message.cancelBiddingService");
		
		appMoblieToken = bundle.getString("app.mobile.token");
		messageServer = bundle.getString("app.mobile.messageServer");
		messageCallback = bundle.getString("app.mobile.messageCallback");
		
		PLAT_DOMAIN = bundle.getString("plat.domain");
		WINDOW_ID = Integer.parseInt(bundle.getString("window.id"));
		
		//WX
		WXAPPID = bundle.getString("wxappid");
		WXAPPSECRET = bundle.getString("wxappsecret");
		
	}

	/**
	 * @date：2013-3-19
	 * @author：lwch
	 * @description：设置cookie内容
	 */
	public static void setCookies(HttpServletResponse response, String cookieName, String cookieValue, int limitTime, String path){
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(limitTime);	//cookie保存一个季度(90天)
		cookie.setPath(path);
		response.addCookie(cookie);
	}
	
	/**
	 * @date：2013-3-19
	 * @author：lwch
	 * @description：删除cookiesName[]中的cookies
	 */
	public static void removeCookies(HttpServletRequest request, HttpServletResponse response, String cookiesName[]){
		Cookie[] cookies = request.getCookies();  
        if (cookies != null) {
            for (Cookie cookie : cookies) {
            	for (int i = 0; i < cookiesName.length; i++) {
					if (cookiesName[i].equals(cookie.getName())) {  
						cookie.setValue("");
						cookie.setMaxAge(0); 
						cookie.setPath("/");
						response.addCookie(cookie); 
					}  
				}
            }  
        }
	}
	
	/**
	 * @date: 2013-8-26
	 * @author：lwch
	 * @description：随机产生一个10位的随机数
	 */
	public static String getPictureFileName() {
		String s = "";
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int n = random.nextInt(36);
			if (n >= 0 && n <= 9) {
				s += String.valueOf(n);
			}else{
				n = n-10;
				s += (char)(97+n);
			}
		}
		return s;
	}
	

	/**
	 * @date: 2013-9-11
	 * @author：xuwf
	 * @description：随机产生一个5位的随机数
	 */
	public static String random() {
		String s = "";
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			int n = random.nextInt(9);	
			s += String.valueOf(n);	
		}
		return s;
	}	
	
	/**
	 * 保留double小数点2位
	 * @author xuwf
	 * @since 2013-10-24
	 * @param num
	 * @return
	 */
	public static double parseDouble(double num){
		DecimalFormat df = new DecimalFormat("0.00");
		double db = Double.parseDouble(df.format(num));
		return db;
//		return ((int)(num*100))/100;
	}
	
	/**
	 * double四舍五入
	 * @author xuwf
	 * @since 2013-10-24
	 * @param num
	 * @return
	 */
	public static Long round(double num){
		return Math.round(num);
	}
	
	/**
	 * 两个对象根据properties复制属性
	 * @author pangyf
	 * @since 2013-10-30
	 * @param source
	 * @param target
	 * @param properties
	 */
	public static void copyProperties(Object source, Object target, String[] properties){		
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
						throw new FatalBeanException("复制属性失败", ex);
					}
				}				
			}		
		}
	}
	
	private static String apiurl(String path) {
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return String.format("http://%s:%d/%s%s", chatDomain, chatPort, path, "");
	}
	
	private static String encodeData(Map<String, String> data) throws Exception {
		List<String> list = new ArrayList<String>();
		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			list.add(pair.getKey() + "=" + URLEncoder.encode(pair.getValue(), "utf-8"));
		}
		return listJoin("&", list);
	}
	
	private static String listJoin(String sep, List<String> groups) {
		boolean first = true;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < groups.size(); i++) {
			if (first) {
				sb.append(groups.get(i));
				first = false;
			} else {
				sb.append(sep);
				sb.append(groups.get(i));
			}
		}
		return sb.toString();
	}
	
	private static void initConn(HttpURLConnection conn) {
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
	}

	private static String readResonpse(HttpURLConnection conn) throws IOException {
		StringBuffer response = new StringBuffer();
		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			System.out.println(response.toString());
		} finally {
			if(rd != null) rd.close();
		}
		return response.toString();
	}
	
	public static String httpost(String path, Map<String, String> data) throws Exception {
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL(apiurl(path));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			String urlParameters = encodeData(data);
			conn.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			initConn(conn);
			DataOutputStream wr = null;
			try {
				wr = new DataOutputStream(conn.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
			} finally {
				if(wr != null) wr.close();
			}
			return readResonpse(conn);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
}



