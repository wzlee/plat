package com.eaglec.plat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.service.MyFavoritesBiz;
import com.eaglec.plat.domain.base.MyFavorites;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.wx.ConcernUsers;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.JSONRows;


@Controller
@RequestMapping(value = "/myFavorites")
public class MyFavoritesController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MyFavoritesController.class);
	
	@Autowired 
	private MyFavoritesBiz myFavoritesBiz;
	
	/**
	 * 新增服务收藏
	 *@author liuliping
	 *@param userId 用户id
	 *@param serviceId 服务id
	 *@since 2013-11-02 
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/add")
	public void add(Integer serviceId, 
			HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		if (myFavoritesBiz.isExisted(user.getId(), serviceId)) {    //服务已收藏, 返回信息提示
			outJson(response, new JSONResult(false, "已经收藏过此服务,去看看<a href='service/result' style='color:#0000FF;' target='_blank'>其他服务</a>"));
		} else {    //服务未收藏
			try {
				myFavoritesBiz.addOrUpdate(user.getId(), serviceId, getCurrentUserType(request));
				outJson(response, new JSONResult(true, "收藏成功"));
			} catch (Exception e) {
				// TODO: handle exception
				StringBuilder sb = new StringBuilder();
				logger.info(sb.append("用户Id[").append(user.getId()).
						append("]收藏服务Id[").append(serviceId).append("]失败").toString());
				outJson(response, new JSONResult(true, "操作失败"));
			}
		}
		
	}
	
	/**
	 * 微信端新增服务收藏
	 *@author liuliping
	 *@param serviceId 服务id
	 *@since 2013-11-02 
	 */
//	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/addFromWX")
	public String addFromWX(Integer serviceId, 
			HttpServletRequest request,HttpServletResponse response, Model model){
		User user = (User)request.getSession().getAttribute("user");
		String url = null;
		if(user == null) {    // 用户未登录
			ConcernUsers cu = (ConcernUsers)request.getSession().getAttribute("concernUsers");
			if(cu == null){
				url = "wx/user_bind";
			} else {
				url = "wx/user_bind_autho";
			}
		} else if(myFavoritesBiz.isExisted(user.getId(), serviceId)) {    
//			url = "wx/collect_service_success";    //服务已收藏, 返回信息提示
			url = "wx/response";    //跳转成功页面
			model.addAttribute("title", "操作成功");
			model.addAttribute("content", "恭喜您，您已经成功收藏该服务");
		} else {    //服务未收藏
			try {
				myFavoritesBiz.addOrUpdate(user.getId(), serviceId, getCurrentUserType(request));
				url = "wx/response";    //跳转成功页面
				model.addAttribute("title", "操作成功");
				model.addAttribute("content", "恭喜您，您已经成功收藏该服务");
			} catch (Exception e) {
				e.printStackTrace();
				StringBuilder sb = new StringBuilder();
				logger.info(sb.append("用户Id[").append(user.getId()).
						append("]收藏服务Id[").append(serviceId).append("]失败").toString());
				url = "wx/response";    //跳转失败页面
				model.addAttribute("title", "操作失败");
				model.addAttribute("content", "啊呀,不好意思,出了点意外");
			}
		}
		return url;
	}
	
	/**
	 * 微信端取消服务收藏
	 *@author liuliping
	 *@param serviceId 服务id
	 *@since 2013-11-02 
	 */
	@RequestMapping(value = "/deleteFromWX")
	public String deleteFromWX(Integer id, HttpServletRequest request, HttpServletResponse response,
			Model model){
		User user = (User)request.getSession().getAttribute("user");
		String url = null;
		if(user == null) {    // 用户未登录
			ConcernUsers cu = (ConcernUsers)request.getSession().getAttribute("concernUsers");
			if(cu == null){
				url = "wx/user_bind";
			} else {
				url = "wx/user_bind_autho";
			}
		} else {    
			try {
				myFavoritesBiz.delete(id);
				url = "wx/response";    //跳转成功页面
				model.addAttribute("title", "操作成功");
				model.addAttribute("content", "您已经取消收藏该服务");
			} catch (Exception e) {
				e.printStackTrace();
				StringBuilder sb = new StringBuilder();
				logger.info(sb.append("删除服务收藏Id[").append(id).append("]").append("]失败").toString());
				url = "wx/response";    //跳转失败页面
				model.addAttribute("title", "操作失败");
				model.addAttribute("content", "啊呀,不好意思,出了点意外");
			}
		}
		return url;
	}
	
	/**
	 * 删除服务收藏
	 *@author liuliping
	 *@param userId 用户id
	 *@param serviceId 服务id
	 *@since 2013-11-02 
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/delete")
	public void delete(Integer id, HttpServletRequest request,HttpServletResponse response){
		try {
			myFavoritesBiz.delete(id);
			outJson(response, new JSONResult(true, "操作成功"));
		} catch (Exception e) {
			// TODO: handle exception
			StringBuilder sb = new StringBuilder();
			logger.info(sb.append("删除服务收藏Id[").append(id).append("]").append("]失败").toString());
			outJson(response, new JSONResult(true, "操作失败"));
		}
	}
	
	/**
	 * 通过帐号id查找主账号的服务收藏
	 *@author liuliping
	 *@param userId 用户id
	 *@param serviceId 服务id
	 *@param start 分页结果起始
	 *@param limit 单页结果数量
	 *@param orderBy 结果按此字段排序
	 *@since 2013-11-02 
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/queryByUserId")
	public void queryByUserId(/*@RequestParam(required = true, value = "userId")Integer userId,*/ 
			@RequestParam(required = true, value = "page", defaultValue = "1")int page, 
			@RequestParam(required = true, value = "rows", defaultValue = "20")int limit, String orderBy,
			HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		try {
			outJson(response, myFavoritesBiz.queryByUserId(user.getId(), (page - 1) * limit, limit, orderBy, getCurrentUserType(request)));
		} catch (Exception e) {
			// TODO: handle exception
			StringBuilder sb = new StringBuilder();
			logger.info(sb.append("查询用户Id[").append(user.getId()).
					append("]的服务收藏出错").toString());
			outJson(response, new JSONRows<MyFavorites>(false, null, 0));
		}
	}
	
	/**
	 * 跳转到服务收藏列表页
	 *@author liuliping
	 *@since 2013-11-02 
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/toList")
	public String toList(HttpServletRequest request,HttpServletResponse response, Model model){
		return "ucenter/my_favorites_list";
	}
}
