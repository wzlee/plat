package com.eaglec.plat.controller.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eaglec.plat.biz.wx.AutoMessageBiz;
import com.eaglec.plat.controller.BaseController;
import com.eaglec.plat.domain.wx.AutoMessage;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;

/**
 * 自动消息控制类
 */
@Controller
@RequestMapping(value = "/automessage")
public class AutoMessageController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(AutoMessageController.class);
	
	@Autowired
	private AutoMessageBiz autoMessageBiz;

	/**
	 * (支撑平台)添加/修改响应消息
	 * 
	 * @author liuliping
	 * @sicen 2013-12-16
	 * @param 
	 * @param request
	 * @param response
	 * @return 
	 */
//	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(AutoMessage autoMessage, HttpServletRequest request,
			HttpServletResponse response) {
		JSONResult jr = new JSONResult(false, null);
		try {
			if((autoMessage.getId() == null)) {    // 添加
				autoMessageBiz.add(autoMessage);
			} else {    // 修改
				autoMessageBiz.update(autoMessage);
			}
			jr.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加/修改响应消息[" + autoMessage.getId() + "]失败");
			jr.setMessage("系统异常");
			e.printStackTrace();
		} finally {
			outJson(response, jr);
		}
	}
	
	/**
	 * (支撑平台)删除响应消息
	 * 
	 * @author liuliping
	 * @sicen 2013-12-16
	 * @param idStr 平台动态id字符串  example:"1,2,3...."
	 * @param request
	 * @param response
	 * @return 
	 */
//	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/delete")
	public void delete(String idStr, HttpServletRequest request,
			HttpServletResponse response) {
		JSONResult jr = new JSONResult(false, null);
		try {
			autoMessageBiz.delete(idStr);
			jr.setSuccess(true);
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			logger.error("由于外键关联删,除响应消息["+ idStr +"]失败");
			jr.setMessage("删除失败,存在关联的推送消息");
		} catch (Exception e) {
			logger.error("删除响应消息["+ idStr +"]失败");
			jr.setMessage("系统异常");
			e.printStackTrace();
		} finally {
			outJson(response, jr);
		}
	}
	
	
	/**
	 * (支撑平台)查询平台动态
	 * 
	 * @author liuliping
	 * @sicen 2013-12-18
	 * @param start 
	 * @param limit 
	 * @param title	标题
	 * @param request
	 * @param response
	 * @return 
	 */
//	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/query")
	public void query(String reqKey, String clickKey, int start, int limit, String title, HttpServletRequest request,
			HttpServletResponse response) {
		JSONData<AutoMessage> jd = null;
		try {
			jd = autoMessageBiz.query(reqKey, clickKey, start, limit);
		} catch (Exception e) {
			jd = new JSONData<AutoMessage>(false, null, 0);
			logger.error("查询平台动态出错");
			e.printStackTrace();
		} finally {
			outJson(response, jd);
		}
	}
	
	
}
