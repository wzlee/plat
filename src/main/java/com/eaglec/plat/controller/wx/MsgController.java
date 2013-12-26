package com.eaglec.plat.controller.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eaglec.plat.biz.app.mobile.MobileAppBiz;
import com.eaglec.plat.biz.policy.PolicyBiz;
import com.eaglec.plat.biz.policy.PolicyCategoryBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.wx.ArticleBiz;
import com.eaglec.plat.biz.wx.ArticleInfoBiz;
import com.eaglec.plat.biz.wx.AutoMessageBiz;
import com.eaglec.plat.biz.wx.ConcernUsersBiz;
import com.eaglec.plat.biz.wx.ReceiveBiz;
import com.eaglec.plat.biz.wx.ReplyBiz;
import com.eaglec.plat.biz.wx.UserChangeBiz;
import com.eaglec.plat.biz.wx.WXUserBiz;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.policy.Policy;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.domain.wx.ArticleInfo;
import com.eaglec.plat.domain.wx.AutoMessage;
import com.eaglec.plat.domain.wx.ConcernUsers;
import com.eaglec.plat.domain.wx.ReceiveInfo;
import com.eaglec.plat.domain.wx.Reply;
import com.eaglec.plat.domain.wx.ReplyInfo;
import com.eaglec.plat.domain.wx.UserChange;
import com.eaglec.plat.domain.wx.WeiXinUser;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.utils.wx.Constants;
import com.eaglec.plat.utils.wx.MessageUtil;
import com.eaglec.plat.utils.wx.SignUtil;
import com.eaglec.plat.utils.wx.WeixinUtil;



/**
 * 消息管理的Controller
 * 
 * @author huyj
 * 
 */
@Controller
@RequestMapping(value = "/")
public class MsgController {
	
	@Autowired
	private ReceiveBiz receiveBiz;
	@Autowired
	private ArticleBiz articleBiz;
	@Autowired
	private WXUserBiz userBiz;
	@Autowired
	AutoMessageBiz autoMessageBiz;
	@Autowired
	ReplyBiz replyBiz;
	
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
	private ConcernUsersBiz concernUsersBiz;
	
	@Autowired
	private UserChangeBiz userChangeBiz;
	
	@Autowired
	private ArticleInfoBiz articleInfoBiz;

	private String ids = "";
	
	/**
	 * 
	 * @author huyj
	 * @sicen 2013-8-22
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             actionPath eg:localhost/wx 注意请求方式为get
	 */
	@RequestMapping(value = "wx", method = RequestMethod.GET)
	public void token(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-8-22
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             actionPath eg:localhost/wx 注意请求方式为Post
	 */
	@RequestMapping(value = "wx", method = RequestMethod.POST)
	public void res(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String respMessage = sendMsg(request, response);
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}

	/**
	 * 点击依格欣进入到首页
	 * 
	 * @author huyj
	 * @sicen 2013-8-22
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 *             actionPath eg:localhost/index
	 */
	@RequestMapping(value = "wx/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		WeiXinUser weiXinUser = (WeiXinUser)request.getSession().getAttribute("weixinUser");
		if(weiXinUser == null){
			String code = request.getParameter("code");
			weiXinUser = WeixinUtil.getWXUserInfo(code, request);
		}
		String openid = weiXinUser.getOpenid();
		if (!"".equals(openid)) {
			ConcernUsers cu = concernUsersBiz.getWeixinBoundInfo(openid);
			if (cu != null && cu.getConcern_status() == 1) {
				request.getSession().setAttribute("concernUsers", cu);
			} else {
				WeixinUtil.clearUserSession(request);
			}
		} else {
			WeixinUtil.clearUserSession(request);
			//获取openid异常
			return "error/500";
		}
		//获取服务机构
		List<Enterprise> enterpriseList = enterpriseBiz.findEnterprieByWX();
		//获取服务
		List<Service> serviceList = serviceBiz.findServiceByWX();
		//获取政策
		List<Policy> policyList = policyBiz.findPolicyByWX();
		//推送消息
		List<ArticleInfo> articleList = articleInfoBiz.findList("", 0, 6);
		request.setAttribute("indexPolicyList", policyList);
		request.setAttribute("indexServiceList", serviceList);
		request.setAttribute("indexEnterpriseList", enterpriseList);
		request.setAttribute("articleList", articleList);
		return "wx/index";
	}

	public void outHTML(HttpServletResponse response, String html) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(html);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送消息
	 * 
	 * @author huyj
	 * @sicen 2013-12-11
	 * @param request
	 * @param response
	 * @return
	 */
	public String sendMsg(HttpServletRequest request, HttpServletResponse response) {
		String respMessage = "请求处理异常，请稍候尝试！";
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);// new
			String fromUserName = (String) requestMap.get("FromUserName");
			String toUserName = (String) requestMap.get("ToUserName");
			String msgType = (String) requestMap.get("MsgType");
			String msgContext = (String) requestMap.get("Content");// (String)
			String createTime = (String) requestMap.get("CreateTime");
			// 设置回复信息基本内容
			Reply resMsg = new Reply();
			// 想数据库添加一条接收的信息
			ReceiveInfo receiveInfo = addReqMsg(requestMap);
			// 设置基本信息
			resMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			resMsg.setCreateTime(new Date().getTime());
			resMsg.setFromUserName(toUserName);
			resMsg.setToUserName(fromUserName);
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				resMsg = buliderAutoMsg("reqKey", msgContext, toUserName, fromUserName);
				if (resMsg.getMsgType().equals(MessageUtil.RESP_MESSAGE_TYPE_NEWS)) {
					respMessage = MessageUtil.newsMessageToXml(resMsg);
				} else {
					respMessage = MessageUtil.textMessageToXml(resMsg);
				}

			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {

			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {

			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				resMsg.setContent("收到了您的地理位置消息");
				respMessage = MessageUtil.textMessageToXml(resMsg);
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String event = requestMap.get(Constants.EVENT);
				if (event.equals(Constants.SUBSCRIBE)) {
					String time = StrUtils.timestampToDate(createTime, true);
					ConcernUsers cu = concernUsersBiz.getWeixinBoundInfo(fromUserName);
					UserChange ubc = new UserChange();
					if (cu != null) {
						cu.setConcern_status(0);
						cu.setSubscribe_time(time);
						concernUsersBiz.updateConcernUsers(cu);
						ubc.setUsername(cu.getUsername());	//用户名
						ubc.setEnterprise_id(cu.getEnterprise_id());	//企业ID
						ubc.setEnterprise_name(cu.getEnterprise_name());	//企业名称
					}
					
					//往用户变更表里面添加一条，微信用户关注平台的时间和微信帐号
					ubc.setWxUserToken(fromUserName);	//加密后的微信帐号
					ubc.setChange_status(0);	//已关注
					ubc.setChange_time(time);	//关注时间
					userChangeBiz.addConcernUsers(ubc);
					
					resMsg = buliderAutoMsg("clickKey", "subsrcibe", toUserName, fromUserName);
					
					if (resMsg.getMsgType().equals(MessageUtil.RESP_MESSAGE_TYPE_NEWS)) {
						respMessage = MessageUtil.newsMessageToXml(resMsg);
					} else {
						respMessage = MessageUtil.textMessageToXml(resMsg);
					}
				} else if (event.equals(Constants.UNSUBSCRIBE)) {
					String time = StrUtils.getNow("yyyy-MM-dd HH:mm:ss");
					ConcernUsers concernUsers = concernUsersBiz.getWeixinBoundInfo(fromUserName);
					UserChange ubc = new UserChange();
					if(null != concernUsers){
						concernUsers.setConcern_status(3);	//取消关注
						concernUsers.setSubscribe_time(time);
						concernUsersBiz.updateConcernUsers(concernUsers);
						ubc.setUsername(concernUsers.getUsername());	//用户名
						ubc.setEnterprise_id(concernUsers.getEnterprise_id());	//企业ID
						ubc.setEnterprise_name(concernUsers.getEnterprise_name());	//企业名称
					}
					
					ubc.setWxUserToken(fromUserName);	//加密后的微信帐号
					ubc.setChange_status(3);	//已关注
					ubc.setChange_time(time);	//关注时间
					userChangeBiz.addConcernUsers(ubc);
					
				} else if (event.equals(Constants.CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					resMsg = buliderAutoMsg("clickKey", eventKey, toUserName, fromUserName);
					if (resMsg.getMsgType().equals(MessageUtil.RESP_MESSAGE_TYPE_NEWS)) {
						respMessage = MessageUtil.newsMessageToXml(resMsg);
					} else {
						respMessage = MessageUtil.textMessageToXml(resMsg);
					}
				}
			}
			addReplyInfo(resMsg, receiveInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	
	/**
	 * 添加一条回复信息
	 * @author huyj
	 * @sicen 2013-12-18
	 * @description TODO
	 * actionPath eg:
	 * @param resMsg
	 * @param reId
	 */
	private void addReplyInfo(Reply resMsg, int reId) {
		ReplyInfo info = new ReplyInfo(resMsg, reId, ids);
		replyBiz.save(info);

	}

	/**
	 * @author huyj
	 * @sicen 2013-9-2
	 * @description 根据用户发送的消息，构建不同的回复内容
	 * @param msgContext
	 * @return 回复信息内容
	 */
	private Reply buliderAutoMsg(String param, String msgContext, String toUserName, String fromUserName) {
		AutoMessage autoMsg = autoMessageBiz.findByReqKey(param, msgContext);
		Reply reply;
		if (null != autoMsg) {
			reply = new Reply(autoMsg);
			reply.setCreateTime(new Date().getTime());
			reply.setToUserName(fromUserName);
			reply.setFromUserName(toUserName);
			reply.setMsgType(autoMsg.getType());
			if (autoMsg.getType().equals(MessageUtil.RESP_MESSAGE_TYPE_NEWS)) {
				for (ArticleInfo a : autoMsg.getNewsList()) {
					ids += a.getId() + ",";
				}
				ids = StringUtils.substringBeforeLast(ids, ",");
			}

		} else {
			reply = new Reply(autoMessageBiz.findByReqKey("reqKey", "0"));
			reply.setMsgType("text");
			reply.setCreateTime(new Date().getTime());
			reply.setToUserName(fromUserName);
			reply.setFromUserName(toUserName);
		}
		return reply;
	}

	/**
	 * 向数据库添加一条信息
	 * 
	 * @author huyj
	 * @sicen 2013-8-23 actionPath eg:
	 * @param requestMap
	 */
	public ReceiveInfo addReqMsg(Map<String, String> requestMap) {
		ReceiveInfo reqMsg = new ReceiveInfo();
		String msgType = requestMap.get(Constants.MSG_TYPE);
		reqMsg.setFromUserName(requestMap.get(Constants.FROM_USER_NAME));
		reqMsg.setToUserName(requestMap.get(Constants.TO_USER_NAME));
		reqMsg.setCreateTime(new Date().getTime());
		reqMsg.setMsgType(msgType);
		if (!reqMsg.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			reqMsg.setMsgId((String) requestMap.get(Constants.MSG_ID));
		}
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			// 文本消息 只需添加文本
			reqMsg.setContent(requestMap.get(Constants.CONTENT));
		} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			// 事件消息,添加事件类型
			reqMsg.setEvent(requestMap.get(Constants.EVENT));
		} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
			// 图片消息,添加图片url
			reqMsg.setPicUrl(requestMap.get(Constants.PIC_URL));
		} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
			// 链接信息
			reqMsg.setTitle(Constants.TITLE);
			reqMsg.setDescription(Constants.DESCRIPTION);
			reqMsg.setUrl(Constants.URL);
		} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
			// 地理位置消息
			reqMsg.setLocation_X(Constants.LOCATION_X);
			reqMsg.setLocation_Y(Constants.LOCATION_Y);
			reqMsg.setScale(Constants.SCALE);
			reqMsg.setLabel(Constants.LABEL);
		} else {
			reqMsg.setMsgType("sorry ,I don't know");
		}
		return receiveBiz.save(reqMsg);
	}

	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * 判断是否是表情
	 * 
	 * @author huyj
	 * @sicen 2013-8-22
	 * @param content
	 *            接收的消息内容
	 * @return
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(new Object[0]);
	}
}
