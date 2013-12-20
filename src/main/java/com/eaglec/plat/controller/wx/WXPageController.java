package com.eaglec.plat.controller.wx;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.biz.app.mobile.MobileAppBiz;
import com.eaglec.plat.biz.business.BiddingServiceBiz;
import com.eaglec.plat.biz.policy.PolicyBiz;
import com.eaglec.plat.biz.policy.PolicyCategoryBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.service.MyFavoritesBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.wx.WXNewsBiz;
import com.eaglec.plat.controller.BaseController;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.BiddingService;
import com.eaglec.plat.domain.policy.PolicyCategory;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.domain.wx.WeiXinUser;
import com.eaglec.plat.utils.wx.WeixinUtil;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.MyFavoritesView;

/**
 * 
 * @author huyj
 * 
 */
@Controller
@RequestMapping(value = "/wxpage")
public class WXPageController extends BaseController {
	
	@Autowired
	private CategoryBiz categoryBiz;
	
	@Autowired
	private ServiceBiz serviceBiz;
	
	@Autowired
	private MobileAppBiz mobielAppBiz;
	
	@Autowired
	private PolicyCategoryBiz policyCategoryBiz;
	
	@Autowired
	private PolicyBiz policyBiz;
	
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	@Autowired
	private WXNewsBiz wXNewsBiz;
	
	@Autowired
	private MyFavoritesBiz favoritesBiz;
	
	@Autowired
	private BiddingServiceBiz biddingServiceBiz;
	
	/**
	 * 微信页面数据接口
	 * 
	 * @author liuliping
	 * @sicen 2013-12-16
	 * @param request
	 * @param response
	 * @param type 编号,根据编号来返回相应的数据
	 * @param limit 分页大小限制
	 * @param start 分页起始  
	 * @param data 其他的参数       
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/reqData")
	public void reqData(int type, int limit, int start, String data, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		switch (type) {
		case 1: 
			/**
			 * 服务列表加载更多数据     
			 * data  服务二级分类id的值
			 * */
			result.put("services", serviceBiz.queryServiceByCategoryId(Integer.parseInt(data), start, limit));
			break;
		case 2: 
			/**
			 * 服务机构列表加载更多数据
			 * data  服务二级分类id的值
			 * */
			result.put("enterprises", enterpriseBiz.findEnterpriseListByCidWX(Integer.parseInt(data), null, null, null, start, limit));
			break;
		case 3: 
			/**
			 * 政策法规列表加载更多数据
			 * data  政策法规二级分类id的值
			 * */
			result.put("policies", policyBiz.findByCid(Integer.parseInt(data), null, start, limit));
			break;
		default:
			break;
		}
		outJson(response, result);
	}
	
	
	/**
	 * 微信服务大类页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-16
	 * @param request
	 * @param response
	 * @return 跳转到服务大类页
	 */
	@RequestMapping(value = "/maxService")
	public String toMaxServicePage(HttpServletRequest request,
			HttpServletResponse response) {
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
		}
		request.setAttribute("serviceCategroyList", categoryBiz.findAllCategory());
		return "wx/service_index";
	}
	
	/**
	 * 微信服务中类页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @param id 服务大类id 
	 * @return 跳转到服务中类页
	 */
	@RequestMapping(value = "/middleService")
	public String toMiddleServicePage(Integer id, HttpServletRequest request,
			HttpServletResponse respons) {
		request.setAttribute("serviceSubCategoryList", categoryBiz.findCategoryChildren(id));
		return "wx/service_sub";
	}
	
	/**
	 * 微信服务列表页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @param id 服务中类id 
	 * @return 跳转到服务列表
	 */
	@RequestMapping(value = "/serviceList")
	public String toServiceList(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("serviceList", serviceBiz.queryServiceByCategoryId(id, 0, 6));
		return "wx/service_list";
	}

	/**
	 * 微信服务详情
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @param id 服务id
	 * @return 跳转到服务列表
	 */
	@RequestMapping(value = "/serviceDetail")
	public String toServiceDetail(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("service", serviceBiz.findServiceById(id));
		return "wx/service_detail";
	}
	
	/**
	 * 微信服务机构大类页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @return 跳转到服务机构大类页
	 */
	@RequestMapping(value = "/maxOrg")
	public String toMaxOrgPage(HttpServletRequest request,
			HttpServletResponse response) {
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
		}
		request.setAttribute("orgCategroyList", categoryBiz.findAllCategory());
		return "wx/org_index";
	}
	
	/**
	 * 微信服务机构二级类页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @param id 服务大类id
	 * @return 跳转到服务机构二级类页
	 */
	@RequestMapping(value = "/middleOrg")
	public String toMiddleOrgPage(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("orgSubCategoryList", categoryBiz.findCategoryChildren(id));
		return "wx/org_sub";
	}
	
	/**
	 * 微信服务机构列表页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @param 服务二级类别id
	 * @return 跳转到服务机构列表页
	 */
	@RequestMapping(value = "/orgList")
	public String toOrgList(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("enterpriseList", enterpriseBiz.findEnterpriseListByCidWX(id, null, null, null, 0, 6));
		return "wx/org_list";
	}
	
	/**
	 * 微信服务机构列 详情页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @param 服务机构id
	 * @return 跳转到服务机构详情页
	 */
	@RequestMapping(value = "/orgDetail")
	public String toOrgDetail(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("enterprise", enterpriseBiz.findByEid(id));
		request.setAttribute("serviceList", serviceBiz.findServiceByEid(id.toString(), 0, 4));	//该服务机构提供的服务
		return "wx/org_detail";
	}
	
	/**
	 * 微信政策法规大类页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @return 跳转到服务机构列表页
	 */
	@RequestMapping(value = "/maxPolicy")
	public String toMaxPolicyPage(HttpServletRequest request,
			HttpServletResponse response) {
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
		}
		request.setAttribute("policyCategoryList", policyCategoryBiz.findAll(null));
		return "wx/policy_index";
	}
	
	/**
	 * 微信政策法规二级类目页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @param id 政策法规大类Id
	 * @return 跳转微信政策法规二级类目页
	 */
	@RequestMapping(value = "/middlePolicy")
	public String middlePolicy(
			@RequestParam("id")Integer id,
			HttpServletRequest request, HttpServletResponse response) {
		List<PolicyCategory> policyCategory = policyCategoryBiz.findPolicyCategoryByPid(id);
		request.setAttribute("policySubCategoryList", policyCategory);
		return "wx/policy_sub";
	}
	
	/**
	 * 微信政策法规列表
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @param id 政策法规二级类目id
	 * @return 跳转微信政策法规列表页
	 */
	@RequestMapping(value = "/policyList")
	public String toPolicyList(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("policyList", policyBiz.findByCid(id, null, 0, 6));
		return "wx/policy_list";
	}
	
	/**
	 * 微信政策法规详情
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @param id 政策法规id
	 * @return 跳转微信政策法规详情页
	 */
	@RequestMapping(value = "/policyDetail")
	public String toPolicyDetail(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("policy", policyBiz.findById(id));
		return "wx/policy_detail";
	}
	
	/**
	 * 微信关于平台页
	 * 
	 * @author liuliping
	 * @sicen 2013-12-14
	 * @param request
	 * @param response
	 * @return 跳转微信政策法规详情页
	 */
	@RequestMapping(value = "/platInfo")
	public String toPlatInfo(HttpServletRequest request,
			HttpServletResponse response) {
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
		}
		return "wx/about";
	}
	/**
	 * 微信平台动态列表页
	 * @author liuliping
	 * @since 2013-12-16
	 * @param 
	 * */
	@RequestMapping(value = "/wXNewsList")
	public String toWXNewsListPage(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
		}
		request.setAttribute("wXNewsList", wXNewsBiz.findList(null, 0, 6));
		return "wx/news";
	}

	/**
	 * 跳转平台动态详情页面
	 * @author liuliping
	 * @since 2013-12-16
	 * @param id 平台动态id
	 * @return 跳转到平台动态详情页面
	 * */
	@RequestMapping(value = "/wXNewsDetail")
	public String toWXNewsDetailPage(Integer id, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(wXNewsBiz.get(id));
		request.setAttribute("wXNews", wXNewsBiz.get(id));
		return "wx/news_info";
	}
	

	/**
	 * 用户中心,我的服务
	 * @author huyj
	 * @sicen 2013-12-18
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/userService")
	public String toWXUserService(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
			if(request.getSession().getAttribute("concernUsers")!=null){
				System.out.println("=====user service no login=====");
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				System.out.println("=====user service no bind=====");
				return "wx/user_center_auth_nolbind";
			}
		}else{
			User user =(User)request.getSession().getAttribute("user");
			//用户已授权登录
			if(user!=null ){
				if(user.getEnterprise().getType()<2){
					System.out.println("========success to user success ====");
					JSONRows<Service> userServiceJsons = serviceBiz.findServiceList("3,4,7",user.getEnterprise().getId(),null,null,0,6);
					List<Service> userService = userServiceJsons.getRows();
					request.setAttribute("userService", userService);
					return "wx/user_service";
				}else{
					System.out.println("======== user no service ========");
					Integer userType= (Integer)request.getSession().getAttribute("usertype");
					JSONRows<MyFavoritesView> jsonList = favoritesBiz.queryByUserId(user.getId(), 0, 6, "time", userType);
					List<MyFavoritesView> myFavortiesList = jsonList.getRows();
					request.setAttribute("myFavortiesList", myFavortiesList);
					return "wx/user_index";
				}
			//绑定未登录
			}else if(request.getSession().getAttribute("concernUsers")!=null){
				System.out.println("=====user service no login=====");
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				System.out.println("=====user service no bind=====");
				return "wx/user_center_auth_nolbind";
			}
		}
	}
	
	/**
	 * 用户中心---我的收藏
	 * @author huyj
	 * @sicen 2013-12-18
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/userCollect")
	public String toWXUserCollect(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
			if(request.getSession().getAttribute("concernUsers")!=null){
				System.out.println("============user collect nologin===========");
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				System.out.println("============user collect nobind===========");
				return "wx/user_center_auth_nolbind";
			}
		}else{
			User user =(User)request.getSession().getAttribute("user");
			//用户已授权登录
			if(user!=null ){
				System.out.println("============to user collect===========");
				Integer userType= (Integer)request.getSession().getAttribute("usertype");
				JSONRows<MyFavoritesView> jsonList = favoritesBiz.queryByUserId(user.getId(), 0, 6, "time", userType);
				List<MyFavoritesView> myFavortiesList = jsonList.getRows();
				request.setAttribute("myFavortiesList", myFavortiesList);
				return "wx/user_collect";
			//绑定未登录
			}else if(request.getSession().getAttribute("concernUsers")!=null){
				System.out.println("============user collect nologin===========");
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				System.out.println("============user collect nobind===========");
				return "wx/user_center_auth_nolbind";
			}
		}
	}
	
	/**
	 * 用户中心---我的资料
	 * @author huyj
	 * @sicen 2013-12-18
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/userInfo")
	public String toWXUserInfo(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
			 if(request.getSession().getAttribute("concernUsers")!=null){
					System.out.println("============ user no login===========");
					return "wx/user_center_auth_nologin";
				//未绑定
				}else{
					System.out.println("============ user no bind===========");
					return "wx/user_center_auth_nolbind";
				}
		}else{
			User user =(User)request.getSession().getAttribute("user");
			//用户已授权登录
			if(user!=null ){
				System.out.println("============to user info===========");
				return "wx/user_info";
			//绑定未登录
			}else if(request.getSession().getAttribute("concernUsers")!=null){
				System.out.println("============ user no login===========");
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				System.out.println("============ user no bind===========");
				return "wx/user_center_auth_nolbind";
			}
		}
	}
	
	/**
	 * 用户中心--我的订单
	 * @author huyj
	 * @sicen 2013-12-18
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/userBid")
	public String toWXUserBid(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
			if(request.getSession().getAttribute("concernUsers")!=null){
				System.out.println("=====user bid no login======");
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				System.out.println("=====user bid no bind======");
				return "wx/user_center_auth_nolbind";
			}
		}else{
			User user =(User)request.getSession().getAttribute("user");
			//用户已授权登录
			if(user!=null ){
				if(user.getEnterprise().getType()>1){
					System.out.println("=====login success to bid======");
					JSONRows<BiddingService> jsonBid=  biddingServiceBiz.findBiddingService(user.getId(),null,null,null,null,null,0,6);
					request.setAttribute("userBidList", jsonBid.getRows());
					return "wx/user_bid";
				}else{
					Integer userType= (Integer)request.getSession().getAttribute("usertype");
					JSONRows<MyFavoritesView> jsonList = favoritesBiz.queryByUserId(user.getId(), 0, 6, "time", userType);
					List<MyFavoritesView> myFavortiesList = jsonList.getRows();
					request.setAttribute("myFavortiesList", myFavortiesList);
					return "wx/user_index";
				}
			//绑定未登录
			}else if(request.getSession().getAttribute("concernUsers")!=null){
				System.out.println("=====user bid no login======");
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				System.out.println("=====user bid no bind======");
				return "wx/user_center_auth_nolbind";
			}
		}
	}
	
	/**
	 * 用户中心,首页
	 * @author huyj
	 * @sicen 2013-12-18
	 * @description TODO
	 * actionPath eg:
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/userIndex")
	public String toWXUserIndex(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
		request.getSession().setAttribute("weixinUser", weixinUser);
			if(request.getSession().getAttribute("concernUsers")!=null){
				System.out.println("=====user bid no login======");
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
			System.out.println("=====user bid no bind======");
				return "wx/user_center_auth_nolbind";
			}
		}
		//用户已授权登录
		if(request.getSession().getAttribute("user")!=null){
			System.out.println("==========succcess to user index=========");
			User user = (User)request.getSession().getAttribute("user");
			Integer userType= (Integer)request.getSession().getAttribute("usertype");
			JSONRows<MyFavoritesView> jsonList = favoritesBiz.queryByUserId(user.getId(), 0, 6, "time", userType);
			List<MyFavoritesView> myFavortiesList = jsonList.getRows();
			request.setAttribute("myFavortiesList", myFavortiesList);
			return "wx/user_index";
		//绑定未登录
		}else if(request.getSession().getAttribute("concernUsers")!=null){
			System.out.println("=====user index no login======");
			return "wx/user_center_auth_nologin";
		//未绑定
		}else{
			System.out.println("=====user index no bind======");
			return "wx/user_center_auth_nolbind";
		}
	}
}
