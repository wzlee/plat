
package com.eaglec.plat.utils.wx;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.eaglec.plat.domain.wx.AccessToken;
import com.eaglec.plat.domain.wx.Button;
import com.eaglec.plat.domain.wx.CommonButton;
import com.eaglec.plat.domain.wx.ComplexButton;
import com.eaglec.plat.domain.wx.Menu;
import com.eaglec.plat.domain.wx.WeiXinUser;
import com.eaglec.plat.utils.Common;

/**
 * 公众平台通用接口工具类
 * @author huyj
 */
public class WeixinUtil{
	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod  请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String method, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			// TrustManager[] tm = { new MyX509TrustManager() };
			// SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			// sslContext.init(null, tm, new java.security.SecureRandom());
			// // 从上述SSLContext对象中得到SSLSocketFactory对象
			// SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			// httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(false);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(method);
			httpUrlConn.connect();
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
		} catch (Exception e) {
		}
		return jsonObject;
	}

	/**
	 * 获取access_token
	 * 
	 * @author huyj
	 * @sicen 2013-12-12
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static AccessToken getAccess_token(HttpServletRequest request,String appid, String secret) {
		AccessToken accessToken = null;
		String requestUrl = Constants.GET_TOKEN_URL.replace("APPID", Common.WXAPPID).replace("APPSECRET", Common.WXAPPSECRET);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				request.getSession().getServletContext().setAttribute("wx_access_token", accessToken.getToken());
			} catch (JSONException e) {
				accessToken = null;
			}
		}
		return accessToken;
	}

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(String accessToken) {
		Menu menu = getSZMenu();
		int result = 0;
		// 拼装创建菜单的url
		String url = Constants.CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = net.sf.json.JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
			}
		}
		return result;
	}

	/**
	 * 深商联按钮
	 * @author huyj
	 * @sicen 2013-12-5
	 * @return
	 */
	public static Menu getSZMenu() {
		// 平台服务子菜单 政策扶持、金融服务、平台资 源、平台活动。
		CommonButton zcfcBtn = new CommonButton();
		zcfcBtn.setName("政策扶持");
		zcfcBtn.setType("click");
		zcfcBtn.setKey("ZCFC");

		CommonButton jrfwBtn = new CommonButton();
		jrfwBtn.setName("服务资源");
		jrfwBtn.setType("click");
		jrfwBtn.setKey("FWZY");

		CommonButton ptzyBtn = new CommonButton();
		ptzyBtn.setName("服务机构");
		ptzyBtn.setType("click");
		ptzyBtn.setKey("FWJG");

		// ：我的资料、我的申报、我的需 求、我的供应、我的收藏

		CommonButton wdzlBtn = new CommonButton();
		wdzlBtn.setName("我的资料");
		wdzlBtn.setType("click");
		wdzlBtn.setKey("WDZL");

		CommonButton wdsbBtn = new CommonButton();
		wdsbBtn.setName("我的申报");
		wdsbBtn.setType("click");
		wdsbBtn.setKey("WDSB");

		CommonButton wdxqBtn = new CommonButton();
		wdxqBtn.setName("我的需求");
		wdxqBtn.setType("click");
		wdxqBtn.setKey("WDXQ");

		CommonButton wdgyBtn = new CommonButton();
		wdgyBtn.setName("我的供应");
		wdgyBtn.setType("click");
		wdgyBtn.setKey("WDGY");

		CommonButton wdscBtn = new CommonButton();
		wdscBtn.setName("我的收藏");
		wdscBtn.setType("click");
		wdscBtn.setKey("WDSC");

		// 平台概况、平台动态、反馈留言
		CommonButton ptgkBtn = new CommonButton();
		ptgkBtn.setName("平台概况");
		ptgkBtn.setType("click");
		ptgkBtn.setKey("PTGK");

		CommonButton ptdtBtn = new CommonButton();
		ptdtBtn.setName("平台动态");
		ptdtBtn.setType("click");
		ptdtBtn.setKey("PTDT");

		CommonButton fklyBtn = new CommonButton();
		fklyBtn.setName("反馈留言");
		fklyBtn.setType("click");
		fklyBtn.setKey("FKLY");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("平台服务");
		mainBtn1.setSub_button(new Button[] { zcfcBtn, jrfwBtn, ptzyBtn});
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("我的平台");
		mainBtn2.setSub_button(new Button[] { wdzlBtn, wdsbBtn, wdxqBtn, wdgyBtn, wdscBtn });
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("关于平台");
		mainBtn3.setSub_button(new Button[] { ptgkBtn, ptdtBtn, fklyBtn });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		return menu;
	}

	/**
	 * 获取菜单信息
	 * 
	 * @author huyj
	 * @sicen 2013-12-12
	 * @return
	 */
	public static Menu getMenu(String accessToken) {
		Menu menu = null;
		// 拼装创建菜单的url
		String url = Constants.GET_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", null);
		if (null != jsonObject) {
			menu = (Menu) jsonObject.get("menu");
		}
		return menu;
	}

	/**
	 * 删除按钮
	 * 
	 * @author huyj
	 * @sicen 2013-12-12
	 * @return
	 */
	public static int deleteMenu(String access_token) {
		int result = 0;
		// 拼装创建菜单的url
		String url = Constants.DELETE_MENU_URL.replace("ACCESS_TOKEN", access_token);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", null);
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
			}
		}
		return result;
	}

	public static JSONObject getGroup(String access_token) {
		String url = Constants.GET_USER_GROUP_URL.replace("ACCESS_TOKEN", access_token);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", null);
		// groups = (Groups) jsonObject;
		return jsonObject;
	}

	/**
	 * 获取用户信息
	 * @author huyj
	 * @sicen 2013-12-18
	 * @param code 用户从微信进入到页面时获取的code
	 * @param request
	 * @return
	 */
	public static WeiXinUser getWXUserInfo(String code,HttpServletRequest request){
		String url = Constants.GET_ACCESS_TOKEN_URL.replace("APPID", Common.WXAPPID).replace("SECRET", Common.WXAPPSECRET).replace("CODE", code);
		JSONObject object = WeixinUtil.httpRequest(url, "GET", null);
		String openid = object.getString("openid");
		System.out.println("---WeiXinUser-------getWXUserInfo-------openid---------"+ openid);
		ServletContext servletContext = request.getSession().getServletContext();
		String access_token = (String)servletContext.getAttribute("wx_access_token");
		
		if(StringUtils.isEmpty(access_token)){
			access_token = WeixinUtil.getAccess_token(request,Common.WXAPPID,Common.WXAPPSECRET).getToken();
		}
		String getUserInfoUrl = Constants.GET_USER_INFO_URL.replace("ACCESS_TOKEN",access_token).replace("OPENID", openid);
		JSONObject userJsonObj = WeixinUtil.httpRequest(getUserInfoUrl, "GET", null);
		System.out.println("---WeiXinUser-------getWXUserInfo-------userJsonObj---------"+ userJsonObj);
		String subscribe = userJsonObj.getString("subscribe");
		String useropenid = userJsonObj.getString("openid");
		String nickname = userJsonObj.getString("nickname");
		String sex = userJsonObj.getString("sex");
		String language = userJsonObj.getString("language");
		String city = userJsonObj.getString("city");
		String province = userJsonObj.getString("province");
		String country = userJsonObj.getString("country");
		String headimgurl = userJsonObj.getString("headimgurl");
		String subscribe_time = userJsonObj.getString("subscribe_time");
		WeiXinUser user = new WeiXinUser(subscribe, useropenid, nickname, sex, language, city, province, country, headimgurl, subscribe_time);
		if(null!=user){
			request.getSession().setAttribute("weixinUser", user);
		}
		return user;
		
//		WeiXinUser user = new WeiXinUser(null, "oCq0vuIYY5b1hXn5wXXejw1U27J0", null, null, null, null, null, null, null, null);
//		request.getSession().setAttribute("weixinUser", user);
//		return user;
	}
	
	public static void clearUserSession(HttpServletRequest request){
		//如果发现用户还为绑定微信帐号，那么就初始化用户的一些session数据
		request.getSession().setAttribute("concernUsers", null);
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("usertype", null);
		request.getSession().setAttribute("loginEnterprise", null);
		request.getSession().setAttribute("staffmenu", null);
	}
}