package com.eaglec.plat.controller.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eaglec.plat.biz.wx.WXNewsBiz;
import com.eaglec.plat.controller.BaseController;
import com.eaglec.plat.domain.wx.WXNews;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;

/**
 * 微信平台动态控制类
 */
@Controller
@RequestMapping(value = "/wxnews")
public class WXNewsController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(WXNewsController.class);
	
	@Autowired
	private WXNewsBiz wXNewsBiz;

	
	
	
	/**
	 * (支撑平台)添加平台动态
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
	public void addOrUpdate(WXNews wXNews, HttpServletRequest request,
			HttpServletResponse response) {
		JSONResult jr = new JSONResult(false, null);
		try {
			if(wXNews.getId() == null) {    // 添加
				wXNewsBiz.add(wXNews);
			} else {    // 修改
				wXNewsBiz.update(wXNews);
			}
			jr.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加/修改[" + wXNews.getTitle() + "]失败");
			jr.setMessage("系统异常");
		} finally {
			outJson(response, jr);
		}
	}
	
	/**
	 * (支撑平台)删除平台动态
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
			wXNewsBiz.delete(idStr);
			jr.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除平台动态["+ idStr +"]失败");
			jr.setMessage("系统异常");
		} finally {
			outJson(response, jr);
		}
	}
	
	/**
	 * (支撑平台)查询平台动态
	 * 
	 * @author liuliping
	 * @sicen 2013-12-16
	 * @param start 
	 * @param limit 
	 * @param title	标题
	 * @param request
	 * @param response
	 * @return 
	 */
//	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/query")
	public void query(int start, int limit, String title, HttpServletRequest request,
			HttpServletResponse response) {
		JSONData<WXNews> jd = null;
		try {
			jd = wXNewsBiz.query(title, start, limit == 0 ? 15 : limit);
		} catch (Exception e) {
			logger.error("查询平台动态出错");
			jd = new JSONData<WXNews>(false, null, 0);
		} finally {
			outJson(response, jd);
		}
	}
	
}
