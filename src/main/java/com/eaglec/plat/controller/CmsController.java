package com.eaglec.plat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.cms.ChannelBiz;
import com.eaglec.plat.biz.cms.ModuleBiz;
import com.eaglec.plat.biz.cms.ModuleTemplateBiz;
import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.cms.Module;
import com.eaglec.plat.domain.cms.ModuleTemplate;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;

@Controller
@RequestMapping(value = "/cms")
public class CmsController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsController.class);
	
	@Autowired
	private ModuleBiz moduleBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private ModuleTemplateBiz moduleTemplateBiz;
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：获取所有的频道
	 */
	@RequestMapping(value = "/findAllChannel")
	public void findAllChannel(HttpServletRequest request, HttpServletResponse response){
		try {
			List<Channel> c = channelBiz.findAll();
			this.outJson(response, c);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：添加频道
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/addChannel", method = RequestMethod.POST)
	public void addChannel(Channel channel, HttpServletRequest request, HttpServletResponse response){
		try {
			int id = channelBiz.add(channel);
			channel.setId(id);
			logger.info(channel.getCname()+ "---添加成功！");
			this.outJson(response, new JSONResult(true, "添加成功", channel));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：删除频道
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/deleteChannel")
	public void deleteChannel(Channel channel, HttpServletRequest request, HttpServletResponse response){
		try {
			channelBiz.delete(channel);
			logger.info(channel.getCname()+ "---删除成功！");
			this.outJson(response, new JSONResult(true, "删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：修改频道
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/updateChannel", method = RequestMethod.POST)
	public void updateChannel(Channel channel, HttpServletRequest request, HttpServletResponse response){
		try {
			channelBiz.update(channel);
			logger.info(channel.getCname()+ "---修改成功！");
			this.outJson(response, new JSONResult(true, "修改成功", channel));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：获取某个频道下的已配置的模块
	 */
	@RequestMapping(value = "/findAllModuel")
	public void findAllModuel(@RequestParam("mchannel")String mchannel, HttpServletRequest request, HttpServletResponse response){
		try {
			List<Module> m = moduleBiz.findAll(mchannel);
			this.outJson(response, new JSONData<Module>(true, m, m.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：频道排序
	 */
	@RequestMapping(value = "/seqencing")
	public void seqencing(
			@RequestParam("cid")Integer cid, 
			@RequestParam("cindex")Integer cindex, 
			@RequestParam("oid")Integer oid,
			@RequestParam("oindex")Integer oindex, 
			HttpServletRequest request, 
			HttpServletResponse response){
		try {
			channelBiz.updateSeqencing(cid, cindex, oid, oindex);
			logger.info("频道排序成功！");
			this.outJson(response, new JSONResult(true, "频道排序成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：获取某个频道下所有的模版
	 */
	@RequestMapping(value = "/findAllTempalte")
	public void findAllTempalte(HttpServletRequest request, HttpServletResponse response){
		try {
			List<ModuleTemplate> mt = moduleTemplateBiz.findAll();
			this.outJson(response,
					new JSONData<ModuleTemplate>(true, mt, mt.size()));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：添加模块
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/addModule", method = RequestMethod.POST)
	public void addModule(Module module, HttpServletRequest request, HttpServletResponse response){
		try {
			int mid = moduleBiz.add(module);
			logger.info(module.getMname()+ "---添加成功！");
			this.outJson(response, new JSONResult(true, module.getMicon(), mid));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：删除模块
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/deleteModule")
	public void deleteModule(Module module, HttpServletRequest request, HttpServletResponse response){
		try {
			moduleBiz.delete(module);
			logger.info("删除模块成功！");
			this.outJson(response, new JSONResult(true, "删除模块成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-8-15
	 * @author：lwch
	 * @description：修改模块
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/updateModule", method = RequestMethod.POST)
	public void updateModule(Module module, HttpServletRequest request, HttpServletResponse response){
		try {
			moduleBiz.update(module);
			logger.info(module.getMname()+ "---修改成功！");
			this.outJson(response, new JSONResult(true, module.getMicon()));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-10-23
	 * @author：lwch
	 * @description：加载枢纽平台的banner图片
	 */
	@RequestMapping(value = "/loadBanner")
	public String loadBanner(HttpServletRequest request, HttpServletResponse response){
		try {
			List<Module> m = moduleBiz.findAll(Common.channelCode);
			request.getSession().setAttribute("bannerList", m);
			return "layout/banner";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}
	
	/**
	 * 通过跨域请求，获取枢纽首页的导航栏数据
	 * @author liuliping
	 * @since 2013-11-09
	 */
	@RequestMapping(value = "/getChannelsByJsonP")
	public void getChannelsByJsonP(String jsoncallback, HttpServletRequest request, HttpServletResponse response){
		// 加载枢纽平台平道列表
		List<Channel> cl = channelBiz.findChnnelByCtype(Constant.CATEGORY_ID);
		outJsonP(response, jsoncallback, cl);
	}
	
	
	
}



