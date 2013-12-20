package com.eaglec.plat.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.eaglec.plat.biz.cms.ChannelBiz;
import com.eaglec.plat.biz.mall.AdvertisementBiz;
import com.eaglec.plat.biz.mall.MallCategoryBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.cms.Module;
import com.eaglec.plat.domain.mall.Advertisement;
import com.eaglec.plat.domain.mall.MallCategory;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.MallCategoryView;
import com.eaglec.plat.view.MallParam;

/**
 * 服务商城 Controller
 * 
 * @author huyj
 * 
 */
@Controller
@RequestMapping(value = "/mall")
public class MallController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(MallController.class);

	@Autowired
	private MallCategoryBiz mallCategoryBiz;
	@Autowired
	private ServiceBiz serviceBiz;
	@Autowired
	private ChannelBiz channelBiz;

	@Autowired
	private AdvertisementBiz advertisementBiz;

	/**
	 * 进入服务商城首页
	 * 
	 * @author huyj
	 * @sicen 2013-9-24
	 * @description TODO actionPath eg:
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(
			@RequestParam(value = "mallId", required = true) Integer mallId,
			HttpServletRequest request, HttpServletResponse response) {

		// 加载bannner
		List<Module> banneList = channelBiz.findModelByMallId(mallId);
		request.setAttribute("banneList", banneList);

		// 加载轮播广告位
		List<Advertisement> topAdList = advertisementBiz.getTopAdList(mallId,
				Constant.MALL_SERVICEAD_TOP, 0,
				Constant.MALL_RECOMSERVICE_LIMIT);
		request.setAttribute("topAdList", topAdList);

		// 加载服务商城热门服务
		List<Service> mallHotServiceList = serviceBiz.getMallHotService(mallId,
				0, Constant.Mall_SERVICE_LIMIT);
		request.setAttribute("mallHotService", mallHotServiceList);
		// 加载枢纽平台最新服务
		List<Service> mallNewServiceList = serviceBiz.getMallNewService(mallId,
				0, Constant.Mall_SERVICE_LIMIT);

		request.setAttribute("mallNewService", mallNewServiceList);
		// 加载推荐服务
		List<Advertisement> mallRecommendServiceList = advertisementBiz
				.getTopAdList(mallId, Constant.MALL_RECOMSERVICE_TOP, 0,
						Constant.Mall_SERVICE_LIMIT);

		request.setAttribute("mallRecommendService", mallRecommendServiceList);

		// 加载好评服务
		List<Service> mallGoodServiceList = serviceBiz.getMallGoodService(
				mallId, 0, Constant.Mall_SERVICE_LIMIT);
		request.setAttribute("mallGoodService", mallGoodServiceList);

		// 加载所有类别
		List<MallCategory> mallCategoryList = mallCategoryBiz
				.findMallCategoryChildren(mallId);

		List<MallCategoryView> allList = new ArrayList<MallCategoryView>();
		for (MallCategory mallcategory : mallCategoryList) {
			MallCategoryView categoryView = new MallCategoryView();
			categoryView.setCategory(mallcategory);
			categoryView.setRecomServiceList(advertisementBiz.getRecomAdList(
					mallcategory.getId(), Constant.MALL_RECOMSERVICE, 0,
					Constant.MALL_RECOMSERVICE_LIMIT));
			categoryView.setServiceAdList(advertisementBiz.getRecomAdList(
					mallcategory.getId(), Constant.MALL_SERVICEAD, 0,
					Constant.MALL_SERVICEAD_TOP_LMIT));
			if (!mallcategory.getHide()) {
				allList.add(categoryView);
			}
		}
		request.setAttribute("allList", allList);
		request.setAttribute("mallId", mallId);
		request.setAttribute("mallCategory",
				mallCategoryBiz.findById("service", mallId));
		return "mall/mall_index";
	}

	/**
	 * 服务商城列表页
	 * 
	 * @author huyj
	 * @sicen 2013-10-16
	 * @param mparam
	 * @param view
	 * @param start
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/mallList")
	public String mallList(
			MallParam mparam,
			@RequestParam(defaultValue = "list", required = true) String view,
			@RequestParam(value = "pager.offset", defaultValue = "0", required = true) int start,
			HttpServletRequest request, HttpServletResponse response) {
		// mallId二级分类
		Integer mallId = mparam.getMallId();
		mparam.setStart(start);
		mparam.setLimit(view.equals("list") ? Constant.MALL_CATEGORY_LIST_FIVE
				: Constant.MALL_CATEGORY_LIST_FIVETEEN);
		try {
			mparam.setServiceName(mparam.getServiceName() != null
					&& !"".equals(mparam.getServiceName()) ? new String(mparam
					.getServiceName().getBytes("ISO-8859-1"), "UTF-8") : null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Long total = serviceBiz.findMallListServiceTotal(mparam);
		mparam.setTotal(total);
		request.setAttribute("view", view);
		request.setAttribute("mparam", mparam);
		// 当前二级分类对象
		MallCategory currentCategory = mallCategoryBiz.findById("service",
				mallId);

		List<MallCategory> mallCategoryList = mallCategoryBiz
				.findAllMallCatetoryByPid(currentCategory.getPid());

		// 设置当前激活的类别
		if (currentCategory.getChildren().isEmpty()) {
			request.setAttribute("currentCategory", currentCategory);
			request.setAttribute(
					"currentPCategory",
					mallCategoryBiz.findById("service",
							currentCategory.getPid()));
		} else {
			request.setAttribute("currentPCategory", currentCategory);
			request.setAttribute("currentCategory", null);

		}
		List<Service> serviceList = serviceBiz.findMallListService(mparam);
		// 设计类别
		request.setAttribute("mallcategoryList", mallCategoryList);
		// 设置服务列表
		request.setAttribute("serviceList", serviceList);

		return "mall/mall_list";
	}

	/**
	 * 加载类别树 ，及类别服务
	 * 
	 * @author huyj
	 * @sicen 2013-9-24
	 * @description
	 */
//	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findServiceByMall", method = RequestMethod.POST)
	public void findServiceByMall(HttpServletRequest request,
			HttpServletResponse response) {
		Integer mallId = Integer.parseInt(request.getParameter("mallId"));
		try {
			String html = mallCategoryBiz.findServiceByMall(mallId, 0,
					Constant.MALL_CTEGORY_SERVICE_LIMIT);
			super.outJson(response, new JSONResult(true, html));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "数据加载失败！"));
		}
	}

	/**
	 * 加载所有服务商城服务类别 运营支撑平台
	 * 
	 * @author huyj
	 * @since 2013-8-12
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/findAll")
	public void findMallCategory(String hide, HttpServletRequest request,
			HttpServletResponse response) {
		List<MallCategory> list = mallCategoryBiz.findAll();

		// 衣服隐藏的类别 移除三遍，将没有子类显示的父类也隐藏
		removeHideCategory(list, hide);
		removeHideCategory(list, hide);
		removeHideCategory(list, hide);

		this.outJson(response, list);
	}

	/**
	 * 去掉隐藏的类别
	 * 
	 * @author huyj
	 * @sicen 2013-10-16
	 * @description TODO actionPath eg:
	 * @param list
	 * @param hide
	 */
	private void removeHideCategory(List<MallCategory> list, String hide) {
		if (!StringUtils.isEmpty(hide)) {
			List<MallCategory> temp = new ArrayList<MallCategory>(list);
			for (MallCategory mallCategory : temp) {
				if (hide.equals("0") ? mallCategory.getHide() : !mallCategory
						.getHide() && mallCategory.getChildren().size() < 1) {
					list.remove(mallCategory);
				}
				if (mallCategory.getChildren().size() > 0) {
					removeHideCategory(mallCategory.getChildren(), hide);
				}
			}
		}
	}

	/**
	 * 根据类别ID获取该类别下所有服务 运营支撑平台
	 * 
	 * @author huyj
	 * @sicen 2013-9-24
	 * @param id
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/loadService")
	public void loadService(
			@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request, HttpServletResponse response) {
		List<Service> list = serviceBiz.loadService(id);

		this.outJson(response, list);
	}

	/**
	 * 添加服务商城类别 运营支撑平台
	 * 
	 * @author hyj
	 * @since 2013-9-24
	 * 
	 * @param category
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/add")
	public void add(MallCategory mallcategory, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info(mallcategory.toString());
			MallCategory ret = mallCategoryBiz.addOrUpdate(mallcategory);
			List<MallCategory> list = new ArrayList<MallCategory>();
			list.add(ret);
			super.outJson(response,
					new JSONData<MallCategory>(true, list, list.size()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("类别失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "操作失败!"));
		}
	}

	/**
	 * 修改服务商城类别 运营支撑平台
	 * 
	 * @author huyj
	 * @since 2013-9-24
	 * 
	 * @param data
	 *            对应MallCategoryStore.js
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/update")
	public void update(MallCategory mallCategory, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			mallCategoryBiz.update(mallCategory);
			super.outJson(response, new JSONResult(true, "修改成功!"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("类别失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "操作失败!"));
		}
	}

	/**
	 * 删除服务商城类别 运营支撑平台
	 * 
	 * @author hyj
	 * @since 2013-9-24
	 * 
	 * @param data
	 *            对应MallCategoryStore.js
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/delete")
	public void delete(String data, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			MallCategory mallcategory = JSON.parseObject(data,
					MallCategory.class);
			mallCategoryBiz.deleteById(mallcategory.getId());
			super.outJson(response, new JSONResult(true, "删除成功!"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("类别失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "操作失败!"));
		}
	}

	/**
	 * 批量删除类别
	 * 
	 * @author huyj
	 * @sicen 2013-10-16
	 * @description TODO actionPath eg:
	 * @param mallIds
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/delecteMalls")
	public void delecteMalls(String mallIds, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String[] ids = mallIds.split(",");
			for (String id : ids) {
				mallCategoryBiz.deleteById(Integer.parseInt(id));
			}
			super.outJson(response, new JSONResult(true, "删除成功!"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("类别失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "操作失败!"));
		}
	}

	/**
	 * 查找可以添加到服务商城的服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-11
	 * @param cid
	 *            服务类别id
	 * @param serviceName
	 *            服务名称
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findAddService")
	public void findAddService(
			@RequestParam("cid") String cid,
			@RequestParam(value = "serviceName", defaultValue = "", required = false) String serviceName,
			Integer industryType,
			HttpServletRequest request, HttpServletResponse response) {
		Integer id;
		if (!StringUtils.isEmpty(cid)) {
			id = Integer.parseInt(cid);
		} else {
			id = null;
		}
		this.outJson(response, serviceBiz.findMallAddService(serviceName, id,industryType));
	}

	/**
	 * 服务商城添加服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-11
	 * @param cid
	 *            服务类别id
	 * @param serviceName
	 *            服务名称
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/mallAddService")
	public void mallAddService(@RequestParam("mallId") Integer mallId,
			@RequestParam("serviceIds") String serviceIds,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String[] ids = serviceIds.split(",");
			for (String id : ids) {
				Service service = serviceBiz.findServiceById(Integer
						.parseInt(id));
				service.setMallId(mallId);
				serviceBiz.update(service);
			}
			super.outJson(response, new JSONResult(true, "服务商城添加服务成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("商城添加服务失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "商城添加服务失败!"));
		}
	}
	
	/**
	 * 服务商城下移除服务
	 * 
	 * @author huyj
	 * @sicen 2013-11-7
	 * @param serviceIds
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/mallRemoveService")
	public void mallRemoveService(
			@RequestParam("serviceIds") String serviceIds,
			HttpServletRequest request, HttpServletResponse response) {
		try {
		String[] ids = serviceIds.split(",");
		for (String id : ids) {
			Service service = serviceBiz.findServiceById(Integer
					.parseInt(id));
			service.setMallId(null);
			serviceBiz.update(service);
		}
			super.outJson(response, new JSONResult(true, "服务商城移除服务成功！"));
	} catch (Exception e) {
		e.printStackTrace();
			logger.error("商城移除服务失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "商城移除服务失败!"));
	}

	}

	/**
	 * Banner配置 查找对应商城
	 * 
	 * @author huyj
	 * @sicen 2013-10-16
	 * @description TODO actionPath eg:
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findBannerCategory")
	public void findBannerCategory(HttpServletRequest request,
			HttpServletResponse response) {
		Channel mallChannel = channelBiz.findByName(Constant.MALL_NAME);
		this.outJson(response,
				channelBiz.findChnnelByCtype(mallChannel.getId()));
	}

	/**
	 * banner配置中，更改banner数量
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @param channel
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/updateBannerNum")
	public void update(Channel channel, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryString:" + request.getQueryString());
		try {
			channelBiz.update(channel);
			logger.info("[ " + channel.getCname() + " ]编辑成功!");
			this.outJson(response, new JSONResult(true, channel.getCname()
					+ "编辑成功!"));
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, channel.getCname()
					+ "编辑失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("编辑失败!异常信息:" + e.getLocalizedMessage());
		}
	}

	/**
	 * 获取类别
	 * 
	 * @author huyj
	 * @sicen 2013-10-14
	 * @description TODO actionPath eg:
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/getRecomCategory")
	public void getRecomCategory(HttpServletRequest request,
			HttpServletResponse response) {
		this.outJson(response, mallCategoryBiz.findMallCategoryChildren(null));
	}

	/**
	 * 获取商城一级分类
	 * 
	 * @author huyj
	 * @sicen 2013-10-18
	 * @description TODO actionPath eg:
	 * @param pid
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findChildren")
	public void findChildren(Integer pid, HttpServletRequest request,
			HttpServletResponse response) {
		this.outJson(response, mallCategoryBiz.findChildren(pid));
	}
}
