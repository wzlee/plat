package com.eaglec.plat.controller.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eaglec.plat.biz.wx.WXUserBiz;

/**
 * 
 * @author huyj
 * 
 */
@Controller
@RequestMapping(value = "/wxuser")
public class WXUserController {
	@Autowired
	 WXUserBiz userBiz;

	@RequestMapping(value = "/query")
	public String quert(HttpServletRequest request, HttpServletResponse response) {

		return "";
	}

	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		com.eaglec.plat.domain.wx.WXUser user = userBiz.get(id);
		request.setAttribute("user", user);
		return "detail";

	}
}
