package com.eaglec.plat.controller;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;

@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryBiz categoryBiz;
	@Autowired
	private ServiceBiz serviceBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public void queryCategory(String clazz, HttpServletRequest request,HttpServletResponse response) {
		logger.info(request.getParameterMap().toString());
		this.outJson(response, categoryBiz.findRootByClazz(clazz));
	}
	
	/**
	 * 加载所有类别
	 * @author xuwf
	 * @since 2013-8-12
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value="/findAll")
	public void findCategory(HttpServletRequest request,HttpServletResponse response){
		this.outJson(response, categoryBiz.findAll());
	}
	
	/**
	 * 前台加载所有服务类别
	 * @author pangyf
	 * @since 2013-9-9
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/findServiceCategory")
	public void findServiceCategory(HttpServletRequest request,HttpServletResponse response){
		this.outJson(response, categoryBiz.findAllCategory());
	}
	
	/**
	 * @date: 2013-8-30
	 * @author：lwch
	 * @description：一次性加载所有的类别和该类别下的子类别并且加载出子类别下的服务
	 */
	@RequestMapping(value="/findAllCategoryAndService", method = RequestMethod.POST)
	public void findAllCategoryAndService(HttpServletRequest request, HttpServletResponse response){
		StringBuffer html = new StringBuffer();
		try {
			//获取所有的父类别
			List<Category> c = categoryBiz.findCategoryParent();
			for (int i = 0; i < c.size(); i++) {
				Category cy = c.get(i);
				/******************父类别的title****bengin*********************/
				html.append("<li id=\""+ cy.getId() +"\" pid=\""+ cy.getPid() +"\">");
				html.append("<div class=\"name-wrap\">");
				html.append("<div class=\"name-column\"><h4 class=\"s"+ (i + 1) +"\">");
				html.append("<a href=\"javascript:void(0)\">"+ cy.getText() +"</a>");
				html.append("</h4></div>");
				html.append("<i class=\"arrow\"></i>");
				html.append("</div>");
				/******************end*********************/
				
				/*******************子类别加载****bengin*****************/
				html.append("<div class=\"mod-sub-cate\">");
				List<Category> cc = cy.getChildren();
				for (Category ccy : cc) {
					//过滤某个子类别下还未添加任何服务的情况
					int count = categoryBiz.loadCategoryToServerCount(ccy.getId());
					if (count != 0) {
						html.append("<dl>");
						html.append("<dt>");
						html.append("<a target=\"_blank\" href=\"service/paging?cid=").append(ccy.getId());
						html.append("\">").append(ccy.getText()).append("</a>");
						html.append("</dt>");
						/*******************特色服务加载****bengin*****************/
						List<Service> s =  serviceBiz.queryServiceByCategoryId(ccy.getId(), 0, 3);
						if(s.size() > 0){
							for (Service se : s) {
								String sname = se.getServiceName();
								html.append("<dd>");
								html.append("<a target='_blank' href=\"service/detail?id=").append(se.getId());
								html.append("\">•"+ (sname.length() > 14 ? sname.substring(0, 15)+"..." : sname) +"</a>");
								html.append("</dd>");
							}
						}
						html.append("</dl>");
					}
				}
				html.append("</div></li>");
			}
			super.outJson(response, new JSONResult(true, html.toString()));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "数据加载失败！"));
		}
	}
	
	
	
	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：加载所有服务下的父类别
	 */
	@RequestMapping(value="/findCategoryParent")
	public void findCategoryParent(HttpServletRequest request,HttpServletResponse response) {
		try {
			//获取服务类别下所有的父类别
			List<Category> c = categoryBiz.findCategoryParent();
			for (Category cy : c) {
				//获取父类别下子类别
				List<Category> cc = cy.getChildren();
				for (Category cgy : cc) {
					//根据子类别的ID获取对应的服务List
					List<Category> newCList = new ArrayList<Category>();
					List<Service> s =  serviceBiz.queryServiceByCategoryId(cgy.getId());
					for (Service se : s) {
						cgy.setLeaf(false);
						Category newC = new Category();
						newC.setChildren(null);
						newC.setText(se.getServiceName());
						newC.setPid(cgy.getId());
						newC.setLeaf(true);
						newC.setPicture(se.getPicture());
						//这个其实就是自己为了方便定义的该服务的连接地址而已。
						newC.setClazz("service/detail?id="+ se.getId());
						newC.setDescription(StrUtils.replaceHTML(se.getServiceProcedure(), 0, 35));
						newCList.add(newC);
					}
					cgy.setChildren(newCList);
				}
			}
			this.outJson(response, c);
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "数据加载失败！"));
		}
	}
	
	/**
	 * @date: 2013-9-13
	 * @author：lwch
	 * @description：查询所有的服务，包括子服务
	 */
	@RequestMapping(value="/queryAllCategory", method = RequestMethod.GET)
	public String queryAllCategory(HttpServletRequest request,HttpServletResponse response){
		List<Category> c;
		try {
			c = categoryBiz.findAllCategory();
			request.setAttribute("categoryList", c);
			return "category";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}
	
	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：根据父类别ID，查找子类别
	 */
	@RequestMapping(value="/findCategoryChildren")
	public void findCategoryChildren(
			@RequestParam("id")Integer id,
			@RequestParam("limit")Integer limit,
			HttpServletRequest request,
			HttpServletResponse response){
		StringBuffer html = new StringBuffer("<div class=\"mod-sub-cate\" style=\"display: inline;\"><div class=\"sub-item\">");
		try {
			List<Category> c = categoryBiz.findCategoryChildren(id);
			for (Category cy : c) {
				html.append("<dl class=\"list\">");
				html.append("<dt class=\"p\">");
				html.append("<a href=\"#\">"+ cy.getText() +"</a>");
				html.append("</dt>");
				List<Service> s =  serviceBiz.queryServiceByCategoryId(cy.getId(), 0, limit);
				if(s.size() > 0){
					html.append("<dd class=\"c\">");
					for (Service se : s) {
						html.append("<a href=\"\">"+ se.getServiceName() +"</a>");
					}
					html.append("</dd>");
				}
				html.append("</dl>");
			}
			html.append("</div>");
			html.append("<div class=\"sub-special\">");
			html.append("<h3>特色服务</h3>");
			html.append("<ul class=\"special-list\">");
			html.append("<li><a href=\"\"></a></li>");
			html.append("<li><a href=\"\"></a></li>");
			html.append("<li><a href=\"\"></a></li>");
			html.append("<li><a href=\"\"></a></li>");
			html.append("</ul>");
			html.append("</div>");
			html.append("</div>");
			super.outJson(response, new JSONResult(true, html.toString()));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "加载子类别失败!"));
		}
	}
	
	/**
	 * 添加类别
	 *@author Xiadi
	 *@since 2013-8-16 
	 *
	 *@param category
	 *@param request
	 *@param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value="/add")
	public void add(Category category, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info(category.toString());
			Category ret = categoryBiz.addOrUpdate(category);
			List<Category> list = new ArrayList<Category>();
			list.add(ret);
			super.outJson(response, new JSONData<Category>(true, list, list.size()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("类别失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "操作失败!"));
		}
	}
	
	/**
	 * 修改类别
	 *@author Xiadi
	 *@since 2013-8-16 
	 *
	 *@param data 对应CategoryStore.js writer.root
	 *@param request
	 *@param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value="/update")
	public void update(String data, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Category category = JSON.parseObject(data, Category.class);
			categoryBiz.addOrUpdate(category);
			super.outJson(response, new JSONResult(true, "修改成功!"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("类别失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(true, "操作失败!"));
		}
	}
	
	/**
	 * 删除类别
	 *@author Xiadi
	 *@since 2013-8-16 
	 *
	 *@param data 对应CategoryStore.js writer.root
	 *@param request
	 *@param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value="/delete")
	public void delete(String data, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Category category = JSON.parseObject(data, Category.class);
			categoryBiz.deleteById(category.getId());
			super.outJson(response, new JSONResult(true, "删除成功!"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("类别失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(true, "操作失败!"));
		}
	}
	
	/**
	 * 运营平台加载该机构的服务领域（修改:根据服务机构名称查找服务机构的服务领域 xuwf 2013-11-28）
	 * @author pangyf
	 * @since 2013-10-16
	 * @param icRegNumber
	 * @param enterpriseName
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/loadUserCategory")
	public void loadUserCategory(@RequestParam(value="enterpriseName",defaultValue="",required=false)String enterpriseName, HttpServletRequest request,HttpServletResponse response) {
		logger.info(request.getParameterMap().toString());
		try {
			if(!enterpriseName.isEmpty()){
//				List<Enterprise> list = enterpriseBiz.findEnterpriseByIcRegNumber(enterpriseName);
				List<Enterprise> list = enterpriseBiz.findEnterpriseByEnterName(enterpriseName);
				Enterprise enterprise = list.get(0);				
				this.outJson(response,enterprise.getMyServices());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("类别加载失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "类别加载失败!"));
		}
	}
	
	/**
	 * 通过跨域请求此接口,获取服务全部分类数据
	 * @author liuliping
	 * @since 2013-11-09
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getAllCategoryByJsonP")
	public void getAllCategoryByJsonP(String jsoncallback, HttpServletRequest request, HttpServletResponse response) {
		StringBuffer html = new StringBuffer();
		try {
			//获取所有的父类别
			List<Category> c = categoryBiz.findCategoryParent();
			for (int i = 0; i < c.size(); i++) {
				Category cy = c.get(i);
				/******************父类别的title****bengin*********************/
				html.append("<li id=\""+ cy.getId() +"\" pid=\""+ cy.getPid() +"\">");
				html.append("<div class=\"name-wrap\">");
				html.append("<div class=\"name-column\"><h4 class=\"s"+ (i + 1) +"\">");
				html.append("<a href=\"javascript:void(0)\">"+ cy.getText() +"</a>");
				html.append("</h4></div>");
				html.append("<i class=\"arrow\"></i>");
				html.append("</div>");
				/******************end*********************/
				
				/*******************子类别加载****bengin*****************/
				html.append("<div class=\"mod-sub-cate\">");
				List<Category> cc = cy.getChildren();
				for (Category ccy : cc) {
					//过滤某个子类别下还未添加任何服务的情况
					int count = categoryBiz.loadCategoryToServerCount(ccy.getId());
					if (count != 0) {
						html.append("<dl>");
						html.append("<dt>");
						html.append("<a target=\"_blank\" href=\"service/paging?cid=").append(ccy.getId());
						html.append("\">").append(ccy.getText()).append("</a>");
						html.append("</dt>");
						/*******************特色服务加载****bengin*****************/
						List<Service> s =  serviceBiz.queryServiceByCategoryId(ccy.getId(), 0, 3);
						if(s.size() > 0){
							for (Service se : s) {
								String sname = se.getServiceName();
								html.append("<dd>");
								html.append("<a target='_blank' href=\"service/detail?id=").append(se.getId());
								html.append("\">•"+ (sname.length() > 14 ? sname.substring(0, 15)+"..." : sname) +"</a>");
								html.append("</dd>");
							}
						}
						html.append("</dl>");
					}
				}
				html.append("</div></li>");
			}
			outJsonP(response, jsoncallback, html.toString());
		} catch (Exception e) {
			e.printStackTrace();
			outJsonP(response, jsoncallback, html.toString());
		}
	}
}
