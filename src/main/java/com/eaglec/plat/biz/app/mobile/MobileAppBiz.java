package com.eaglec.plat.biz.app.mobile;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;


public interface MobileAppBiz {
	
	/**
	 * 获取服务列表   
	 * @param jsonObject 从jsonObject中获取服务大类ID,参数名是"reqData"
	 * @return 
	 * */
	Map<String, Object> getServices(JSONObject jsonObject);
	
	/**
	 * 获取服务详情  typeCode:11
	 * * */
	Map<String, Object> getServiceDetail(JSONObject jsonObject);
	
	/**
	 * 预约购买服务 typeCode:12
	 * * 
	 * @param jsonObject */
	Map<String, Object> addServiceConsumer(JSONObject jsonObject);
	
	/**
	 * 申请服务 typeCode:12 
	 * * 
	 * @param jsonObject */
	Map<String, Object> saveApplyService(JSONObject jsonObject);
	
	/**
	 * 获取机构列表  typeCode:13
	 * * 
	 * @param jsonObject */
	Map<String, Object> getEnterprises();
	
	/**
	 * 获取法规列表(不明确) typeCode:14
	 * * */
	Map<String, Object> getRules();
	
	/**
	 * 获取平台窗口列表  typeCode:15
	 * * */
	Map<String, Object> getWindows();
	
	/**
	 * 登录  typeCode:16
	 * @param jsonObject msgContent经过base64解码转换成JSONObject
	 * * */
	Map<String, Object> useLogin(JSONObject jsonObject, HttpServletRequest request);
	
	/**
	 * 查看我的基本信息（认证接口，有auth参数） typeCode:17
	 * * 
	 * @param request 
	 * @param jsonObject */
	Map<String, Object> getBaseInfo(JSONObject jsonObject, HttpServletRequest request);
	
	/**
	 * 查看我的买单（认证接口，有auth参数）  typeCode:18
	 * * 
	 * @param request 
	 * @param jsonObject */
	Map<String, Object> getOrders(JSONObject jsonObject, HttpServletRequest request);
}
