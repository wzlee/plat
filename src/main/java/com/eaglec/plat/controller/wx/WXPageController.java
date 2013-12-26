package com.eaglec.plat.controller.wx;

import java.io.IOException;
import java.util.Date;
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
import com.eaglec.plat.biz.business.GoodsOrderBiz;
import com.eaglec.plat.biz.business.OrderInfoBiz;
import com.eaglec.plat.biz.business.OrderOperateLogBiz;
import com.eaglec.plat.biz.info.MessageBiz;
import com.eaglec.plat.biz.info.MessageClassBiz;
import com.eaglec.plat.biz.info.ReceiverMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderGroupMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderMessageRelationshipBiz;
import com.eaglec.plat.biz.policy.PolicyBiz;
import com.eaglec.plat.biz.policy.PolicyCategoryBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.service.MyFavoritesBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.biz.wx.WXNewsBiz;
import com.eaglec.plat.controller.BaseController;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.BiddingService;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.domain.business.OrderInfo;
import com.eaglec.plat.domain.business.OrderOperateLog;
import com.eaglec.plat.domain.info.Message;
import com.eaglec.plat.domain.info.MessageClass;
import com.eaglec.plat.domain.info.ReceiverMessageRelationship;
import com.eaglec.plat.domain.info.SenderGroupMessageRelationship;
import com.eaglec.plat.domain.info.SenderMessageRelationship;
import com.eaglec.plat.domain.policy.Policy;
import com.eaglec.plat.domain.policy.PolicyCategory;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.domain.wx.WeiXinUser;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.bean.business.GoodsOrderSyncBean;
import com.eaglec.plat.sync.bean.business.OrderInfoSyncBean;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.Dao;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.utils.wx.WeixinUtil;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.MyFavoritesView;
import com.eaglec.plat.view.ServiceView;

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
	
	@Autowired
	private OrderOperateLogBiz orderOperLogBiz;
	
	@Autowired
	private GoodsOrderBiz orderBiz;
	
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	
	@Autowired
	private MessageClassBiz messageClassBiz;
	
	@Autowired
	private MessageBiz messageBiz;
	
	@Autowired
	private SenderMessageRelationshipBiz senderMessageRelationshipBiz;
	
	@Autowired
	private ReceiverMessageRelationshipBiz receiverMessageRelationshipBiz;
	
	@Autowired
	private UserBiz userBiz;
	
	@Autowired
	private Dao dao;
	
	@Autowired
	private SenderGroupMessageRelationshipBiz senderGroupMessageRelationshipBiz;
	
	@Autowired
	private MyFavoritesBiz myFavoritesBiz;
	
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
		User user =(User) request.getSession().getAttribute("user");
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
		case 4: 
			/**
			 * 平台动态加载更多
			 * 
			 * */
			result.put("news", wXNewsBiz.findList(null, start, limit));
			break;
		case 5: 
			/**
			 * 用户中心我的招标加载更多
			 *   
			 * */
			result.put("biddings", biddingServiceBiz.findBiddingService(user.getId(),null,null,null,null,null,start,limit));
			break;
		case 6: 
			/**
			 * 用户中心我的收藏加载更多
			 *   
			 * */
			Integer userType= (Integer)request.getSession().getAttribute("usertype");
			JSONRows<MyFavoritesView> jsonList = favoritesBiz.queryByUserId(user.getId(), start, limit, "time", userType);
			result.put("favorties",jsonList.getRows());
			break;
		case 7: 
			/**
			 * 用户中心我的服务加载更多
			 *   
			 * */
			JSONRows<Service> userServiceJsons = serviceBiz.findServiceList("3,4,7",user.getEnterprise().getId(),null,null,start,limit);
			result.put("myservices", userServiceJsons.getRows());
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
		Category serviceCategory = categoryBiz.findById("service", id);
		request.setAttribute("serviceCategory", serviceCategory);
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
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			if(myFavoritesBiz.isExisted(user.getId(), id)) {    // 如果当前登录的用户已经收藏此服务,则页面上显示"取消收藏"按钮
				request.setAttribute("collected", true);
			}
		}
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
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				return "wx/user_center_auth_nolbind";
			}
		}else{
			User user =(User)request.getSession().getAttribute("user");
			//用户已授权登录
			if(user!=null ){
				if(user.getEnterprise().getType()<2){
					Integer userType= (Integer)request.getSession().getAttribute("usertype");
					JSONRows<MyFavoritesView> jsonList = favoritesBiz.queryByUserId(user.getId(), 0, 6, "time", userType);
					List<MyFavoritesView> myFavortiesList = jsonList.getRows();
					request.setAttribute("myFavortiesList", myFavortiesList);
					return "wx/user_index";
				}else{
					//查询所有服务
					JSONRows<Service> userServiceJsons = serviceBiz.findServiceList("1,2,3,4,5,6,7",user.getEnterprise().getId(),null,null,0,6);
					List<Service> userService = userServiceJsons.getRows();
					request.setAttribute("userService", userService);
					return "wx/user_service";
				}
			//绑定未登录
			}else if(request.getSession().getAttribute("concernUsers")!=null){
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
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
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				return "wx/user_center_auth_nolbind";
			}
		}else{
			User user =(User)request.getSession().getAttribute("user");
			//用户已授权登录
			if(user!=null ){
				Integer userType= (Integer)request.getSession().getAttribute("usertype");
				JSONRows<MyFavoritesView> jsonList = favoritesBiz.queryByUserId(user.getId(), 0, 6, "time", userType);
				List<MyFavoritesView> myFavortiesList = jsonList.getRows();
				request.setAttribute("myFavortiesList", myFavortiesList);
				return "wx/user_collect";
			//绑定未登录
			}else if(request.getSession().getAttribute("concernUsers")!=null){
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
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
					return "wx/user_center_auth_nologin";
				//未绑定
				}else{
					return "wx/user_center_auth_nolbind";
				}
		}else{
			User user =(User)request.getSession().getAttribute("user");
			//用户已授权登录
			if(user!=null ){
				return "wx/user_info";
			//绑定未登录
			}else if(request.getSession().getAttribute("concernUsers")!=null){
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
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
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				return "wx/user_center_auth_nolbind";
			}
		}else{
			User user =(User)request.getSession().getAttribute("user");
			//用户已授权登录
			if(user!=null ){
				if(user.getEnterprise().getType()>1){
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
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
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
				return "wx/user_center_auth_nologin";
			//未绑定
			}else{
				return "wx/user_center_auth_nolbind";
			}
		}
		//用户已授权登录
		if(request.getSession().getAttribute("user")!=null){
			User user = (User)request.getSession().getAttribute("user");
			Integer userType= (Integer)request.getSession().getAttribute("usertype");
			JSONRows<MyFavoritesView> jsonList = favoritesBiz.queryByUserId(user.getId(), 0, 6, "time", userType);
			List<MyFavoritesView> myFavortiesList = jsonList.getRows();
			request.setAttribute("myFavortiesList", myFavortiesList);
			return "wx/user_index";
		//绑定未登录
		}else if(request.getSession().getAttribute("concernUsers")!=null){
			return "wx/user_center_auth_nologin";
		//未绑定
		}else{
			return "wx/user_center_auth_nolbind";
		}
	}
	/**
	 * 进入申请服务页面
	 * @author huyj
	 * @sicen 2013-12-19
	 * @param id  服务id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/userApply")
	public String toWXUserApply(Integer id,HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("weixinUser")==null){
			WeiXinUser weixinUser =WeixinUtil.getWXUserInfo(request.getParameter("code"),request);
			request.getSession().setAttribute("weixinUser", weixinUser);
		}
		//用户已授权登录
		if(request.getSession().getAttribute("user")!=null){
			Service service = serviceBiz.findServiceById(id);
			request.setAttribute("applyService", service);
			return "wx/user_apply";
		//绑定未登录
		}else if(request.getSession().getAttribute("concernUsers")!=null){
			return "wx/user_center_auth_nologin";
		//未绑定
		}else{
			return "wx/user_center_auth_nolbind";
		}
		
	}
	
	/**
	 * 服务申请功能
	 * @author huyj
	 * @since 2013-12-19
	 * 
	 * @param id		服务id
	 * @param linkMan	联系人
	 * @param tel		电话
	 * @param email		邮箱
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/byApply")
	public String byApply(Integer id,String linkMan,String tel,String email,String remark,
			HttpServletRequest request,HttpServletResponse response){
		try {
			Service service = serviceBiz.findServiceById(id);	//申请的服务
			GoodsOrder order = null;					//申请服务产生一张订单
			OrderInfo orderInfo = null;					//订单流水
			OrderOperateLog orderOperateLog = null;		//订单操作日志
			//订单编号的生成规则
			String orderNumber = "S"+StrUtils.formateDate("YYYYMMdd", new Date())+Common.random();
			//当前时间作为订单下单时间
			String currentTime = StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date());
			//登录用户(主账号登录即操作者)
			if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
				User user = (User)request.getSession().getAttribute("user");
				order = new GoodsOrder(orderNumber, Constant.WAIT_SELLER_CONFIRM, 
					service.getCostPrice(), linkMan, tel, email, remark, currentTime, user, 
					user.getEnterprise().getId(), service.getEnterprise().getId(),
					service.getServiceName(), service, Constant.ORDER_SOURCE_S);
				
				//添加订单详细信息
				orderInfo = new OrderInfo(order, order.getOrderStatus(), 
						remark,user,currentTime,Constant.ACTION_BUYER_ORDER_SUBMIT);
				orderBiz.saveGoodsOrder(order);
				orderInfoBiz.saveOrderInfo(orderInfo);
				
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(new GoodsOrderSyncBean(order, SyncType.ONE)),true);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(new OrderInfoSyncBean(orderInfo, SyncType.ONE)));
				
				//添加订单操作日志
				orderOperateLog = new OrderOperateLog(order.getOrderNumber(),
						user.getUserName(),Constant.WAIT_SELLER_CONFIRM_STR,
						StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
			}else{//子账号登录
				Staff staff = (Staff) request.getSession().getAttribute("user");
				
				if(staff.getStaffRole().isApply()){//可以申请服务
					order = new GoodsOrder(orderNumber, Constant.WAIT_SELLER_CONFIRM, 
							service.getCostPrice(), linkMan, tel, email, remark, currentTime, staff, 
							staff.getParent().getEnterprise().getId(), service.getEnterprise().getId(),
							service.getServiceName(), service, Constant.ORDER_SOURCE_S);
					//添加订单详细信息
					orderInfo = new OrderInfo(order, order.getOrderStatus(), 
							remark,staff,currentTime,Constant.ACTION_BUYER_ORDER_SUBMIT);
					//添加订单操作日志
					orderOperateLog = new OrderOperateLog(order.getOrderNumber(),
							staff.getUserName(),Constant.WAIT_SELLER_CONFIRM_STR,
							StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
				}else{
					response.sendRedirect("/error/nosession?type=zizhanghaowuquanxian");
				}
				orderBiz.saveGoodsOrder(order);
				orderInfoBiz.saveOrderInfo(orderInfo);
			}

			orderOperLogBiz.saveOrderOperLog(orderOperateLog);
			
			//申请订单,给卖家发送消息
			User seller = userBiz.findUserByEnterprise(order.getSeller_id());
			Message message = getTransactionMessage();
			sendTransactionMessage(Constant.LOGIN_USER, message,seller.getId(), seller.getUserName(), true, orderNumber, 1);
			request.setAttribute("orderNumber", order.getOrderNumber());
			request.setAttribute("serviceName", service.getServiceName());
		} catch (Exception e) {
			return "wx/apply_service_faild";
		}
		return "wx/apply_service_success";
	}
	
	/**
	 * 得到交易信息类别下的信息(存在发给买卖双方的信息直接取信息)
	 * @author hyj
	 * @since 2013-11-19
	 * 
	 * @return
	 */
	public Message getTransactionMessage(){
		//得到消息类别,选择(交易信息)
		MessageClass messageClass = messageClassBiz.find(Constant.MESSAGE_TYPE_NAME);
		Message message = new Message();
		message.setMessageClass(messageClass);
		messageBiz.save(message);
		return message;
	}
	
	/**
	 * 系统自动发送交易信息
	 * @author hyj
	 * @since 2013-11-19
	 * 
	 * @param sender			//发送人	('系统自动发送')
	 * @param userType			//用户类型,确定接收方的信息(user或者staff)
	 * @param messge			//消息
	 * @param receiverId		//接收人id(user或者staff)
	 * @param receiver			//接收人名称(user或者staff)
	 * @param confirmSend		//是否发送
	 * @param orderNumber		//订单号或者标单号
	 * @param flag				//交易操作标识(根据交易不同标识返回不同信息)
	 */
	public String sendTransactionMessage(Integer userType,Message message,Integer receiverId,String receiver,boolean confirmSend,String orderNumber,Integer flag){
		String messages = null;
		if(confirmSend){//确定是否发送
			//发送者消息关联类
			SenderMessageRelationship smrs = new SenderMessageRelationship(message, receiver);
			//接收者消息关联类
			ReceiverMessageRelationship rmrs = new ReceiverMessageRelationship(message, Constant.TRANSACTIONS_INFO_SENDER);
				
			if(userType == Constant.LOGIN_USER){//主账号
				rmrs.setReceiverUserId(receiverId);
			}else if(userType == Constant.LOGIN_STAFF){//子账号
				rmrs.setReceiverStaffId(receiverId);
			}
			String querystaffsql = " select id from staff where staffStatus=1 and parent_id = "+receiverId;
			List<Map<String,Object>> staffIds = dao.find(querystaffsql);
			if(staffIds.size() > 0){//存在子账号发送给该账号下的所有能用的子账号
				Long l1 = System.currentTimeMillis();
				SenderGroupMessageRelationship s = new SenderGroupMessageRelationship();
				s.setMessage(message);
				senderGroupMessageRelationshipBiz.save(s);
				Object[][] sucobjs = new Object[staffIds.size()][1];
				String insertstaffsql = "INSERT INTO receivermessagerelationship (deleteSign,readSign,receiverStaffId,sender,message_id) VALUES(0,0,?,'"+Constant.TRANSACTIONS_INFO_SENDER+"',"+message.getId()+")";
				for(int i = 0 ;i< staffIds.size();i++){
				    Map<String, Object> map =staffIds.get(i);
				    sucobjs[i][0] = map.get("id");
				}
				dao.batchUpdate(insertstaffsql, sucobjs);
			}
			if (flag == 1) {// 订单申请 接收人:卖家
				messages = Common.buyApply + orderNumber;
			} else if (flag == 2) {// 卖家确认订单 接收人:买家
				messages = Common.sellerConfirmMessage1 + message.getSendTime()
						+ Common.sellerConfirmMessage2 + orderNumber;
			} else if (flag == 3) {// 卖家取消订单 接收人:买家
				messages = Common.sellerCancel + orderNumber;
			} else if (flag == 4) {// 买家关闭,卖家未关闭 接收人:卖家
				messages = Common.buyerCloseFirst + orderNumber;
			} else if (flag == 5) {// 卖家关闭,买家未关闭 接收人:买家
				messages = Common.sellerCloseFirst + orderNumber;
			} else if (flag == 6) {// 买家关闭然后卖家关闭或者卖家关闭然后买家关闭 接收人:买家、卖家
				messages = Common.orderOver + orderNumber;
			} else if (flag == 7) {// 运营人员关闭订单(申诉处理一致) 接收人:买家、卖家
				messages = Common.platCloseOrder + orderNumber;
			} else if (flag == 8) {// 运营人员取消订单(申诉处理一致) 接收人:买家、卖家
				messages = Common.platCancelOrder + orderNumber;
			} else if (flag == 9) {// 招标审核通过 接收人:招标方-即买家
				messages = Common.biddingAuditOK + orderNumber;
			} else if (flag == 10) {// 招标审核驳回 接收人:招标方-即买家
				messages = Common.biddingAuditFail + orderNumber;
			} else if (flag == 11) {// 选择应标 接收人:应标方-即卖家
				messages = Common.selectResponse + orderNumber;
			} else if (flag == 12) {// 取消招标 接收人:所有应标方
				messages = Common.cancelBiddingService + orderNumber;
			}
			message.setContent(messages);
			messageBiz.update(message);
			senderMessageRelationshipBiz.save(smrs);
			receiverMessageRelationshipBiz.save(rmrs);
		}
		System.out.println(message);
		return messages;
	}
	
	/**
	 * 
	 * @author huyj
	 * @sicen 2013-12-20
	 * @description TODO
	 * actionPath eg:
	 * @return
	 */
	@RequestMapping(value="toPage")
	public String toPage(String page,HttpServletRequest request,
			HttpServletResponse response){
		//String a =(String) request.getAttribute("page");
		return "wx/"+page;
	}
	
	/**
	 * 搜索
	 * @author huyj
	 * @sicen 2013-12-23
	 * @param title
	 * @param type 搜索的类别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/search")
	public String search(
			String title,
			String type,
			Integer limit,
			HttpServletRequest request, HttpServletResponse response){
		//搜索服务
		if(type.equals("service")){
			JSONData<ServiceView>  servicelist = serviceBiz.queryByName(title, "serviceNum", 0, null, null, 0 , limit);
			request.setAttribute("resultList", servicelist.getData());
		//搜索机构
		}else if(type.equals("org")){
			JSONData<Enterprise> orglist = enterpriseBiz.findByName(title, limit, 0);
			request.setAttribute("resultList", orglist.getData());
		//搜索政策
		}else if(type.equals("policy")){
			JSONData<Policy>  policyList =policyBiz.queryByParams(title, null, 0, 0, limit);
			request.setAttribute("resultList", policyList.getData());
		}
		request.setAttribute("content", title);
		return "wx/"+type+"_search";
	}
	
	/**
	 * 搜索
	 * @author huyj
	 * @sicen 2013-12-23
	 * @param title
	 * @param type 搜索的类别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getMore")
	public void getMore(
			String title,
			String type,
			Integer limit,
			Integer start,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		title = StrUtils.FormatIsoToUTF(title);
		//搜索服务
		if(type.equals("service")){
			JSONData<ServiceView>  servicelist = serviceBiz.queryByName(title, "serviceNum", 0, null, null, start, limit);
			result.put("serviceList", servicelist);
		//搜索机构
		}else if(type.equals("org")){
			JSONData<Enterprise> orglist = enterpriseBiz.findByName(title, limit, start);
			result.put("enterprises", orglist);
		//搜索政策
		}else if(type.equals("policy")){
			JSONData<Policy>  policyList =policyBiz.queryByParams(title, null, 0, start, limit);
			result.put("policies", policyList);
		}
		outJson(response, result);
	}
	
	/**
	 * 用户中心--我的服务加载更多
	 * @author huyj
	 * @sicen 2013-12-24
	 * @param title 服务名称
	 * @param uid  企业id
	 * @param status 0 查询3,4,7
	 * 				 1 查询1,2,5,6
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getMoreUserService")
	public void getMoreUserService(
			String title,
			String eid,
			String status,
			Integer start,
			Integer limit,
			HttpServletRequest request, HttpServletResponse response){
		title = StrUtils.FormatIsoToUTF(title);
		List<Service> list = serviceBiz.findMoreService(title, eid, status, start, limit);
		outJson(response, list);
	}
	
	/**
	 * 微信用户中心--我的招标加载更多与搜索
	 * @author huyj
	 * @sicen 2013-12-25
	 * @param title  服务名称
	 * @param status 招标状态
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getMoreUserBid")
	public void getMoreUserBid(
			String title,
			String status,
			Integer start,
			Integer limit,
			HttpServletRequest request, HttpServletResponse response){
		title = StrUtils.FormatIsoToUTF(title);
		User user =(User) request.getSession().getAttribute("user");
		JSONRows<BiddingService> jsonBid=  biddingServiceBiz.findBiddingService(user.getId(),null,title,null,null,status,start,limit);
		request.setAttribute("userBidList", jsonBid.getRows());
		outJson(response, jsonBid.getRows());
	}
}
