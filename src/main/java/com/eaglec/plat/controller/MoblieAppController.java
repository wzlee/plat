package com.eaglec.plat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.Base64;
import com.eaglec.plat.biz.app.mobile.MobileAppBiz;
import com.eaglec.plat.biz.mobileApp.AppMessageBiz;
import com.eaglec.plat.biz.service.ServiceConsumerBiz;
import com.eaglec.plat.domain.mobileApp.AppMessage;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.MD5;
import com.eaglec.plat.view.JSONResult;

@Controller
@RequestMapping(value = "/mobileApp")
public class MoblieAppController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(MoblieAppController.class);
	
	@Autowired
	private MobileAppBiz mobileAppBiz;
	@Autowired
	private ServiceConsumerBiz serviceConsumerBiz;
	@Autowired
	private AppMessageBiz appMessageBiz;
	
	/**
	 * @author：liuliping
	 * @date: 2013-11-18
	 * @description:处理手机端发起的请求,根据参数typeCode的值选择执行相应的业务
	 * 1.解析messageString
	 * 2.校验header
	 * 3.执行业务
	 * @param data 请求的参数 json格式字符串:{"typeCode": 10, "reqData": ...} 
	 */
	@RequestMapping(value = "/handle")
	public void handle(@RequestParam(required=true)String data, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = null;
		// 1.校验header
		String decodedContent = verifyHeader(data);
		// 2.执行业务
		if (decodedContent == null) {
			result = new HashMap<String, Object>();
			result.put("statusCode", 1);
			result.put("resData", "请求校验未通过");
		} else {
			JSONObject jsonObject = null;
			try {
				jsonObject =  JSONObject.parseObject(decodedContent);
//				jsonObject =  JSONObject.parseObject(data);
			} catch (com.alibaba.fastjson.JSONException e) {
				result = new HashMap<String, Object>();
				result.put("statusCode", 1);
				result.put("resData", "请求参数格式有误");
				outJson(response, result);
				return;
			}
			Object typeCode = jsonObject.get("typeCode");
			switch (typeCode == null ? 0 : (Integer)typeCode) {
			case 10:     //获取服务列表
				result = mobileAppBiz.getServices(jsonObject);
				break;
			case 11:    //获取服务详情
				result = mobileAppBiz.getServiceDetail(jsonObject);
				break;
			case 12:    //预约购买服务
				result = mobileAppBiz.saveApplyService(jsonObject);
				break;
			case 13:    //查看服务机构列表
				result = mobileAppBiz.getEnterprises();
				break;
			case 14:    //获取政策列表
				result = mobileAppBiz.getRules();		
				break;
			case 15:    //获取平台窗口列表
				result = mobileAppBiz.getWindows();		
				break;
			case 16:	//登录
				result = mobileAppBiz.useLogin(jsonObject, request);
				break;
			case 17:	//查看我的基本信息
				result = mobileAppBiz.getBaseInfo(jsonObject, request);
				break;
			case 18:	//查看我的买单
				result = mobileAppBiz.getOrders(jsonObject, request);
				break;
			default:
				break;
			}
		}
		outJson(response, result);
	}

	/**
	 * 页面上推送消息调用此接口,服务端将保存该消息
	 * */
//	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/message")
	public void sendMessage(AppMessage appMessage, HttpServletRequest request, HttpServletResponse response) {
		appMessage.setTime(String.valueOf(new Date().getTime()/1000));
		//保存发送的信息
		int id = appMessageBiz.save(appMessage);
		//发送信息到服务器
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("id", String.valueOf(id)));
        nvps.add(new BasicNameValuePair("title", appMessage.getTitle()));
        nvps.add(new BasicNameValuePair("content", appMessage.getContent()));
        nvps.add(new BasicNameValuePair("time", appMessage.getTime()));
//        nvps.add(new BasicNameValuePair("callback", Common.messageCallback));
        nvps.add(new BasicNameValuePair("auth", MD5.toMD5UpperCase(generateAuth(appMessage))));
        nvps.add(new BasicNameValuePair("push", "1"));    //发送推送, 0是不推送
        String picture = appMessage.getPicture();
        if(picture != null && !"".equals(picture)) {
        	nvps.add(new BasicNameValuePair("pic_url", appMessage.getPicture()));
        }
        AppMessage entity = appMessageBiz.get(id);
		try {
			com.eaglec.plat.utils.HttpClientUtils.post(Common.messageServer, nvps);
			entity.setFlag(2);	//发送中
			appMessageBiz.update(entity);
			outJson(response, new JSONResult(true, "推送任务已提交"));
		} catch (Exception e) {
			e.printStackTrace();
			entity.setFlag(1);	//发送中
			appMessageBiz.update(entity);
			outJson(response, new JSONResult(false, "推送任务生成失败"));
		}
	}
	
	@RequestMapping(value = "/callback")
	public void messageCallback(@RequestParam(required=true, value="id", defaultValue="0")Integer id, 
			@RequestParam(required=true, value="auth", defaultValue="")String auth, 
			@RequestParam(required=true, value="status", defaultValue="2")Integer status, 
			@RequestParam(required=true, value="errmsg", defaultValue="")String errmsg,
			HttpServletRequest request, HttpServletResponse response) {
		AppMessage appMessage = appMessageBiz.get(id);
		String auth2 = generateAuth(appMessage);
		if(!auth.equals(auth2)) {	// 检验码不匹配成功,停止后续执行代码
			return;
		}
		if(status.intValue() == 0) {	// 推送消息成功,修改信息状态
			appMessage.setFlag(0);
		} else {	// 推送消息失败,记录失败原因
			appMessage.setMessage(errmsg);
			appMessage.setFlag(1);
		}
		appMessageBiz.update(appMessage);
	}
	
	/**
	 * 分页查询历史消息
	 * @author liuliping
	 * @since 2013-12-06
	 * @param start
	 * @param limit
	 * @param title
	 * */
//	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/query")
	public void query(@RequestParam(value = "start", defaultValue = "0", required = true)int start,
			@RequestParam(value = "limit", defaultValue = "1", required = true)int limit, String title, 
			HttpServletRequest request, HttpServletResponse response) {
		outJson(response, appMessageBiz.find(start, limit, title));
	}
	
	/**
	 * 删除历史消息
	 * @author liuliping
	 * @since 2013-12-06
	 * @param idStr
	 * */
//	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/removeMessages")
	public void removeMessages(@RequestParam(value = "idStr", defaultValue = "", required = true)String idStr,
			HttpServletRequest request, HttpServletResponse response) {
		JSONResult jr = new JSONResult(false, null);
		try {
			appMessageBiz.removeMessages(idStr);
			jr.setSuccess(true);
			jr.setMessage("删除成功");
		} catch (Exception e) {
			logger.info("删除消息["+ idStr +"]失败");
			jr.setMessage("删除失败");
			e.printStackTrace();
		} finally {
			outJson(response, jr);
		}
	}
	
	/**
	 * 校验msgString
	 * 将messageString拆分成header（截取前16位）和msgContent（17位起到末尾）
	 * 计算token+msgContent的32位大写MD5值，并截取第5-20位，得到genHeader
	 * */
	private String verifyHeader(String msgString) {
		String header = msgString.substring(0, 16);
		String msgContent = msgString.substring(16);
		String genHeader = MD5.toMD5UpperCase(Common.appMoblieToken + msgContent).substring(4, 20);
		if (genHeader.equals(header)) {    //header和genHeader相同,返回base64解码后的msgContent
			byte[] b = Base64.decodeFast(msgContent);
			return new String(b);
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		String msgContent = "eyJyZXFEYXRhIjoiIiwicmVxVHlwZSI6MTR9";
//		String token = Common.appMoblieToken;
//		String md5 = MD5.toMD5UpperCase(token + msgContent);
////		System.out.println(md5);
////		76B811C0EB29C5CF3FD9258B19A5F825
////		String msg = "5Lit5paH";
////		String a = new String(Base64.decodeFast(msg));
//		System.out.println(md5);
//	}
	
	/**
	 * 生成auth校验码
	 * */
	 private String generateAuth(AppMessage appMessage) {
		 StringBuilder sb = new StringBuilder();
		 sb.append(appMessage.getId()).append(appMessage.getTitle()).
		 	append(appMessage.getContent()).append(appMessage.getTime()).
		 	append(Common.appMoblieToken);
		 return MD5.toMD5UpperCase(sb.toString());
	 }
	 
	 public static void main(String[] args) {
		 System.out.println(new Date().getTime()/1000);
		System.out.println(String.valueOf(new Date().getTime()));
	}
}


