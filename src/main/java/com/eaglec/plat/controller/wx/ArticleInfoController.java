package com.eaglec.plat.controller.wx;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eaglec.plat.biz.wx.ArticleInfoBiz;
import com.eaglec.plat.biz.wx.AutoMessageBiz;
import com.eaglec.plat.controller.BaseController;
import com.eaglec.plat.domain.wx.ArticleInfo;
import com.eaglec.plat.domain.wx.AutoMessage;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;

/**
 * 图文消息信息控制类
 */
@Controller
@RequestMapping(value = "/articleinfo")
public class ArticleInfoController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(ArticleInfoController.class);
	
	@Autowired
	private ArticleInfoBiz articleInfoBiz;

	@Autowired
	private AutoMessageBiz autoMessageBiz;
	
	
	/**
	 * (支撑平台)添加平台动态
	 * 
	 * @author liuliping
	 * @sicen 2013-12-16
	 * @param 
	 * @param request
	 * @param response
	 * @param articleInfo 推送消息对象
	 * @param autoMessageId 响应消息id
	 * @return 
	 */
//	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(ArticleInfo articleInfo, Integer autoMessageId, HttpServletRequest request,
			HttpServletResponse response) {
		JSONResult jr = new JSONResult(false, null);
		try {
			articleInfo.setPubdate(DateFormatUtils.format(new Date(),	//设置发布时间
					"yyyy-MM-dd HH:mm:ss"));
			if(autoMessageId != null) {    //响应消息不为空,表示该推送消息对象有配置响应
				AutoMessage autoMessage = autoMessageBiz.get(autoMessageId);
				if(autoMessage.getNewsList().size() > 1) {    //超过数量限制,则返回提示
					jr.setMessage("响应消息限制关联10条推送消息");
					return;
				}
				articleInfo.setAutoMessage(autoMessage);
			}
			if((articleInfo.getId() == null) || (articleInfo.getId() == 0)) {    // 添加
				articleInfoBiz.add(articleInfo);
			} else {    // 修改
				articleInfoBiz.update(articleInfo);
			}
			jr.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加/修改[" + articleInfo.getTitle() + "]失败");
			jr.setMessage("系统异常");
			e.printStackTrace();
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
			articleInfoBiz.delete(idStr);
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
		JSONData<ArticleInfo> jd = null;
		try {
			jd = articleInfoBiz.query(title, start, limit == 0 ? 15 : limit);
		} catch (Exception e) {
			logger.error("查询平台动态出错");
			jd = new JSONData<ArticleInfo>(false, null, 0);
			e.printStackTrace();
		} finally {
			outJson(response, jd);
		}
	}
	
	
}
