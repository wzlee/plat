package com.eaglec.plat.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.business.AppealBiz;
import com.eaglec.plat.biz.business.BiddingServiceBiz;
import com.eaglec.plat.biz.business.BiddingServiceDetailBiz;
import com.eaglec.plat.biz.business.GoodsOrderBiz;
import com.eaglec.plat.biz.business.OrderEvaluationBiz;
import com.eaglec.plat.biz.business.OrderInfoBiz;
import com.eaglec.plat.biz.business.OrderOperateLogBiz;
import com.eaglec.plat.biz.business.ResponseBiddingBiz;
import com.eaglec.plat.biz.info.MessageBiz;
import com.eaglec.plat.biz.info.MessageClassBiz;
import com.eaglec.plat.biz.info.ReceiverMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderGroupMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderMessageRelationshipBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.EnterpriseCreditBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.EnterpriseCredit;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.Appeal;
import com.eaglec.plat.domain.business.BiddingService;
import com.eaglec.plat.domain.business.BiddingServiceDetail;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.domain.business.OrderEvaluation;
import com.eaglec.plat.domain.business.OrderInfo;
import com.eaglec.plat.domain.business.OrderOperateLog;
import com.eaglec.plat.domain.info.Message;
import com.eaglec.plat.domain.info.MessageClass;
import com.eaglec.plat.domain.info.ReceiverMessageRelationship;
import com.eaglec.plat.domain.info.SenderGroupMessageRelationship;
import com.eaglec.plat.domain.info.SenderMessageRelationship;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.bean.ServiceSyncBean;
import com.eaglec.plat.sync.bean.business.AppealSyncBean;
import com.eaglec.plat.sync.bean.business.GoodsOrderSyncBean;
import com.eaglec.plat.sync.bean.business.OrderEvaluationSyncBean;
import com.eaglec.plat.sync.bean.business.OrderInfoSyncBean;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.Dao;
import com.eaglec.plat.utils.MD5;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.OrderAndBiddingAppeal;
import com.eaglec.plat.view.OrderAndBiddingView;

/**
 * 服务订单控制层
 * @author xuwf
 * @since 2013-9-11
 *
 */
@Controller
@RequestMapping(value="/order")
public class GoodsOrderController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsOrderController.class);
	
	@Autowired
	private GoodsOrderBiz orderBiz;
	@Autowired
	private ServiceBiz serviceBiz; 
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	@Autowired
	private OrderOperateLogBiz orderOperLogBiz;
	@Autowired
	private OrderEvaluationBiz orderEvaluationBiz;
	@Autowired
	private AppealBiz appealBiz;
	@Autowired
	private BiddingServiceBiz biddingServiceBiz;
	@Autowired 
	private BiddingServiceDetailBiz biddingServiceDetailBiz;
	@Autowired
	private ResponseBiddingBiz responseBiddingBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private EnterpriseCreditBiz enterpriseCreditBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private StaffBiz staffBiz;
	@Autowired
	private MessageClassBiz messageClassBiz;
	@Autowired
	private MessageBiz messageBiz;
	@Autowired
	private SenderMessageRelationshipBiz senderMessageRelationshipBiz;
	@Autowired
	private ReceiverMessageRelationshipBiz receiverMessageRelationshipBiz;
	@Autowired
	private Dao dao;
	@Autowired
	private SenderGroupMessageRelationshipBiz senderGroupMessageRelationshipBiz;
	/**
	 * 得到交易信息类别下的信息(存在发给买卖双方的信息直接取信息)
	 * @author xuwf
	 * @since 2013-11-07
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
	 * @author xuwf
	 * @since 2013-11-06
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
				logger.info("方法耗时："+(System.currentTimeMillis()-l1));
			}
			if(flag == 1){//订单申请							接收人:卖家
				messages = Common.buyApply+orderNumber;
			}
			if(flag == 2 )//卖家确认订单							接收人:买家
				messages = Common.sellerConfirmMessage1+message.getSendTime()+Common.sellerConfirmMessage2+orderNumber;
			if(flag == 3)//卖家取消订单							接收人:买家
				messages = Common.sellerCancel+orderNumber;
			if(flag == 4)//买家关闭,卖家未关闭						接收人:卖家
				messages = Common.buyerCloseFirst+orderNumber;
			if(flag == 5)//卖家关闭,买家未关闭						接收人:买家
				messages = Common.sellerCloseFirst+orderNumber;
			if(flag == 6)//买家关闭然后卖家关闭或者卖家关闭然后买家关闭		接收人:买家、卖家
				messages = Common.orderOver+orderNumber;
			if(flag == 7)//运营人员关闭订单(申诉处理一致)				接收人:买家、卖家
				messages = Common.platCloseOrder+orderNumber;
			if(flag == 8)//运营人员取消订单(申诉处理一致)				接收人:买家、卖家
				messages = Common.platCancelOrder+orderNumber;
			if(flag == 9)//招标审核通过							接收人:招标方-即买家
				messages = Common.biddingAuditOK+orderNumber;
			if(flag == 10)//招标审核驳回 							接收人:招标方-即买家
				messages = Common.biddingAuditFail+orderNumber;
			if(flag == 11)//选择应标							接收人:应标方-即卖家
				messages = Common.selectResponse+orderNumber;
			if(flag == 12)//取消招标							接收人:所有应标方
				messages = Common.cancelBiddingService+orderNumber;
			
			message.setContent(messages);
			messageBiz.update(message);
			senderMessageRelationshipBiz.save(smrs);
			receiverMessageRelationshipBiz.save(rmrs);
		}
		return messages;
	}
	
	/**
	 * 服务申请功能
	 * @author xuwf
	 * @since 2013-9-10
	 * 
	 * @param id		服务id
	 * @param linkMan	联系人
	 * @param tel		电话
	 * @param email		邮箱
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="applyService")
	public void applyService(Integer id,String linkMan,String tel,String email,String remark,
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
				logger.info("添加订单详细信息");	
				
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
			logger.info("添加订单操作日志");
			this.outJson(response,new JSONResult(true,"申请服务成功!"));
			logger.info("[ "+order.getOrderNumber()+" ]添加成功!");
			
			//申请订单,给卖家发送消息
			User seller = userBiz.findUserByEnterprise(order.getSeller_id());
			Message message = getTransactionMessage();
			sendTransactionMessage(Constant.LOGIN_USER, message,seller.getId(), seller.getUserName(), true, orderNumber, 1);
		} catch (Exception e) {
			this.outJson( response, new JSONResult(false, 
					"申请服务失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("申请服务失败!异常信息:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 通过跨域请求，申请服务
	 * @author liuliping
	 * @param sid 服务uuid
	 * @param sm_login 登录后cookie中的值
	 * @param linkMan 联系人
	 * @param tel 联系人电话号码
	 * @param email 联系人邮箱
	 * @param remark 备注
	 * @since 2013-11-09
	 */
//	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/applyServiceByJsonP")
	public void applyServiceByJsonP(String sid, String sm_login, String linkMan, String tel, String email, String remark,
			String jsoncallback, HttpServletRequest request, HttpServletResponse response){
		JSONResult jr = new JSONResult(false, null);
		if(StringUtils.isEmpty(sm_login)) {
			jr.setMessage("校验码不能为空");
			outJsonP(response, jsoncallback, jr);
			return;
		}
		// 通过解析字符串sm_login,来检验请求合法性,获取用户类型和用户对象
		Map<String, Object> map = checkLogin(sm_login);
		boolean success = (Boolean) map.get("success"); 
		if(!success) {
			jr.setMessage((String)map.get("message"));
			outJsonP(response, jsoncallback, jr);
			return;
		}
		
		try {
//			Service service = serviceBiz.findServiceById(id);	//申请的服务
			Service service = serviceBiz.findServiceByUuid(sid);//通过sid来查找服务
			GoodsOrder order = null;					//申请服务产生一张订单
			OrderInfo orderInfo = null;					//订单流水
			OrderOperateLog orderOperateLog = null;		//订单操作日志
			//订单编号的生成规则
			String orderNumber = "S"+StrUtils.formateDate("YYYYMMdd", new Date())+Common.random();
			//当前时间作为订单下单时间
			String currentTime = StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date());
			
			//登录用户(主账号登录即操作者)
			if("user".equals(map.get("userType"))){
//				User user = (User)request.getSession().getAttribute("user");
				User user = (User)map.get("userOrStaff");
				
				order = new GoodsOrder(orderNumber, Constant.WAIT_SELLER_CONFIRM, 
					service.getCostPrice(), linkMan, tel, email, remark, currentTime, user, 
					user.getEnterprise().getId(), service.getEnterprise().getId(),
					service.getServiceName(), service, Constant.ORDER_SOURCE_S);
				//添加订单详细信息
				orderInfo = new OrderInfo(order, order.getOrderStatus(), 
						remark,user,currentTime,Constant.ACTION_BUYER_ORDER_SUBMIT);
				//添加订单操作日志
				orderOperateLog = new OrderOperateLog(order.getOrderNumber(),
						user.getUserName(),Constant.WAIT_SELLER_CONFIRM_STR,
						StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
			}else{//子账号登录
//				Staff staff = (Staff) request.getSession().getAttribute("user");
				Staff staff = (Staff) map.get("userOrStaff");
				
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
				} else{
//					response.sendRedirect("/error/nosession?type=zizhanghaowuquanxian");
					jr.setSuccess(false);
					jr.setMessage("子账号无权限申请");
					outJsonP(response, jsoncallback, jr);
					return;
				}
			}

			orderBiz.saveGoodsOrder(order);
			
			orderInfoBiz.saveOrderInfo(orderInfo);
			logger.info("添加订单详细信息");		
			orderOperLogBiz.saveOrderOperLog(orderOperateLog);
			logger.info("添加订单操作日志");
			
			// 同步至窗口 服务
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(new GoodsOrderSyncBean(order, SyncType.ONE)),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(new OrderInfoSyncBean(orderInfo, SyncType.ONE)));
			
			logger.info("[ "+order.getOrderNumber()+" ]添加成功!");
			
			//申请订单,给卖家发送消息
			User seller = userBiz.findUserByEnterprise(order.getSeller_id());
			Message message = getTransactionMessage();
			sendTransactionMessage(Constant.LOGIN_USER, message,seller.getId(), seller.getUserName(), true, orderNumber, 1);
			jr.setSuccess(true);
			jr.setMessage("申请成功");
		} catch (Exception e) {
			jr.setMessage("申请服务失败!异常信息:" + e.getLocalizedMessage());
			logger.info("申请服务失败!异常信息:" + e.getLocalizedMessage());
		} finally {
			outJsonP(response, jsoncallback, jr);
		}
	}
	
	
	/**
	 * 混合条件查询订单
	 * @author xuwf
	 * @since  2013-9-11
	 * 
	 * @param flag			记号(买家0，卖家1)	
	 * @param userId		登录用户
	 * @param orderNo		订单编号
	 * @param orderStatus	订单状态
	 * @param startTime		下单时间
	 * @param endTime		结束时间
	 * @param page
	 * @param rows
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/queryorder")
	public void queryOrder(
			@RequestParam("flag")int flag,
			@RequestParam("userId")Integer userId,
			@RequestParam(value="orderNo") String orderNo,
			@RequestParam(value="serviceName") String serviceName,
			@RequestParam(value="orderStatus") String orderStatus,
			@RequestParam(value="startTime") String startTime,
			@RequestParam(value="endTime") String endTime,
			@RequestParam(value="page",required=false)int page,
			@RequestParam(value="rows",required=false)int rows,
			HttpServletRequest request,
			HttpServletResponse response) {
		if(!StringUtils.isEmpty(userId)){
			JSONRows<GoodsOrder> jrows = orderBiz.query(userId, orderNo,serviceName,flag,
					orderStatus, startTime, endTime, rows * (page - 1), rows);
			this.outJson(response, jrows);
		}		
	}
	
	/**
	 * 混合条件查询招单和订单
	 * @author xuwf
	 * @since  2013-10-10
	 * 
	 * @param flag			记号(买家0，卖家1)	
	 * @param userId		登录用户
	 * @param orderNo		订单编号
	 * @param orderStatus	订单状态
	 * @param startTime		下单时间
	 * @param endTime		结束时间
	 * @param page
	 * @param rows
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/querybo")
	public void queryBo(
			@RequestParam("flag")int flag,
			@RequestParam("userId")Integer userId,
			@RequestParam(value="orderNo") String orderNo,
			@RequestParam(value="serviceName") String serviceName,
			@RequestParam(value="orderStatus") String orderStatus,
			@RequestParam(value="startTime") String startTime,
			@RequestParam(value="endTime") String endTime,
			@RequestParam(value="page",required=false)int page,
			@RequestParam(value="rows",required=false)int rows,
			HttpServletRequest request,
			HttpServletResponse response) {
		if(!StringUtils.isEmpty(userId)){
			JSONRows<OrderAndBiddingView> jrows = orderBiz.queryBO(userId, orderNo,serviceName,flag,
					orderStatus, startTime, endTime, rows * (page - 1), rows);
			this.outJson(response, jrows);
		}		
	}
	
	/**
	 * 根据状态查询订单
	 * @author xuwf
	 * @since  2013-9-11
	 * 
	 * @param flag			记号(买家0，卖家1)
	 * @param userId		登录用户
	 * @param orderStatus	订单状态
	 * @param page
	 * @param rows
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/querystatus")
	public void queryByStatus(
			@RequestParam("flag")int flag,
			@RequestParam("userId")Integer userId,
			@RequestParam(value="orderStatus") String orderStatus,
			@RequestParam(value="page",required=false)int page,
			@RequestParam(value="rows",required=false)int rows,
			HttpServletRequest request,
			HttpServletResponse response) {
		if(!StringUtils.isEmpty(userId)){
			JSONRows<GoodsOrder> jr = orderBiz.queryByStatus(userId, orderStatus,
					flag, rows * (page - 1), rows);
			this.outJson(response, jr);
		}		
	}
	
	/**
	 * 查看订单详情
	 * @author xuwf
	 * @since  2013-9-11
	 * 
	 * @param orderId	
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/querydetail")
	public String queryOrderDetail(
			@RequestParam("orderId")Integer orderId,
			HttpServletRequest request,
			HttpServletResponse response,Model model) {
		if(!StringUtils.isEmpty(orderId)){
			GoodsOrder order = orderBiz.findById(orderId);
			//得到买家评价
			Object buyerEval = orderEvaluationBiz.getBuyerSatisfaction(order);
			//得到卖家评价
			Object sellerEval = orderEvaluationBiz.getSellerSatisfaction(order);
			model.addAttribute("goodsorder", order);
			model.addAttribute("buyerEval", buyerEval);
			model.addAttribute("sellerEval", sellerEval);
			
//			Map<String, Object> rows = new HashMap<String, Object>();
//			rows.put("order", order);
//			rows.put("infoList", orderInfoList);
//			rows.put("evalList", evalList);
//			this.outJson(response, new JSONMap(true, rows));
		}
		return "ucenter/order_detail";
	}
	
	/**
	 * 订单操作日志
	 * @author xuwf
	 * @since 2013-10-14 
	 *
	 * @param orderId
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/getOrderLog")
	public void getBiddingLog(Integer orderId, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "0", required = true)Integer page, 
			@RequestParam(defaultValue = "20", required = true)Integer rows){
		JSONRows<OrderInfo> jr = orderInfoBiz.findByOrderId(orderId,rows * (page - 1), rows);
		this.outJson(response, jr);
	}
	
	
	 /**
	 * 卖家确认订单	
	 * @author xuwf
	 * @since 2013-9-14
	 *
	 * @param orderId		//订单编号
	 * @param remark		//orderInfo备注
	 * @param price			//交易价格
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/orderconfirm")
	public void confirmOrder(Integer orderId,String remark,Integer price,
			HttpServletRequest request,HttpServletResponse response) {
		try {	
			GoodsOrder goodsOrder = orderBiz.findById(orderId);
			//订单确认成功状态改为"交易进行中"
			goodsOrder.setTransactionPrice(price);
			goodsOrder.setOrderStatus(Constant.TRANSACTIONS_IN);
			orderBiz.updateGoodsOrder(goodsOrder);
			//添加订单详细信息
			OrderInfo orderInfo = null;
			//添加订单操作日志
			OrderOperateLog orderOperateLog = null;
			//登录用户(即操作者---子账号或者主账号)
			if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
				User user = (User)request.getSession().getAttribute("user");
				orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
						remark,user,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()),
						Constant.ACTION_SELLER_ORDER_CONFIRM);
				orderInfoBiz.saveOrderInfo(orderInfo);
				logger.info("添加订单详细信息");
				
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(new GoodsOrderSyncBean(goodsOrder, SyncType.ONE)),true);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(new OrderInfoSyncBean(orderInfo, SyncType.ONE)));
				
				orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
						user.getUserName(),Constant.TRANSACTIONS_IN_STR,
						StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
			}else{//子账号登录
				Staff staff = (Staff)request.getSession().getAttribute("user");
				orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
						remark,staff,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()),
						Constant.ACTION_SELLER_ORDER_CONFIRM);
				orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
						staff.getUserName(),Constant.TRANSACTIONS_IN_STR,
						StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
				orderInfoBiz.saveOrderInfo(orderInfo);
				logger.info("添加订单详细信息");
			}
			
			orderOperLogBiz.saveOrderOperLog(orderOperateLog);
			logger.info("添加订单操作日志");
			//卖家确定订单,发送消息给买家
			User buyer = userBiz.findUserByEnterprise(goodsOrder.getPurchaser_id());
			Message message = getTransactionMessage();
			sendTransactionMessage(Constant.LOGIN_USER, message,buyer.getId(), buyer.getUserName(), true, goodsOrder.getOrderNumber(), 2);
			
			this.outJson(response,new JSONResult(true,"订单【"+goodsOrder.getOrderNumber()+"】确认成功!"));
			logger.info("订单[ "+goodsOrder.getOrderNumber()+" ]确认成功!");
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"订单确认 失败!异常信息:"+e.getLocalizedMessage()));
			logger.info("订单确认失败!异常信息:"+e.getLocalizedMessage());
		}	
	}
	
	/**
	 * 卖家取消订单
	 * @author xuwf
	 * @since 2013-9-14
	 *
	 * @param orderId		//订单编号
	 * @param remark		//orderInfo备注
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/ordercancel")
	public void cancelOrder(Integer orderId,String remark,HttpServletRequest request,
			HttpServletResponse response) {
		try {	
			GoodsOrder goodsOrder = orderBiz.findById(orderId);
			//订单取消成功状态改为"取消订单"
			goodsOrder.setOrderStatus(Constant.ORDER_CANCEL);
			orderBiz.updateGoodsOrder(goodsOrder);
			//添加订单详细信息
			OrderInfo orderInfo = null;
			OrderOperateLog orderOperateLog = null;
			if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
				User user = (User)request.getSession().getAttribute("user");
				
				orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
					remark,user,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()),
					Constant.ACTION_SELLER_ORDER_CANCEL);
				orderInfoBiz.saveOrderInfo(orderInfo);
				logger.info("添加订单详细信息");		
				
				// 同步至窗口
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(new GoodsOrderSyncBean(goodsOrder, SyncType.ONE)),true);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(new OrderInfoSyncBean(orderInfo, SyncType.ONE)));
				
				orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
					user.getUserName(),Constant.ORDER_CANCEL_STR,
					StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
			}else{//子账号登录
				Staff staff = (Staff)request.getSession().getAttribute("user");
				
				orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
						remark,staff,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()),
						Constant.ACTION_SELLER_ORDER_CANCEL);
					
					orderInfoBiz.saveOrderInfo(orderInfo);
					logger.info("添加订单详细信息");			

					orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
						staff.getUserName(),Constant.ORDER_CANCEL_STR,
						StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
			}	
			orderOperLogBiz.saveOrderOperLog(orderOperateLog);
			logger.info("添加订单操作日志");		
			//卖家取消订单，系统自动发送给买家
			User buyer = userBiz.findUserByEnterprise(goodsOrder.getPurchaser_id());
			Message message = getTransactionMessage();
			sendTransactionMessage(Constant.LOGIN_USER,message, buyer.getId(), buyer.getUserName(), true, goodsOrder.getOrderNumber(), 3);
			
			this.outJson(response,new JSONResult(true,"订单【"+goodsOrder.getOrderNumber()+"】取消成功!"));
			logger.info("订单[ "+goodsOrder.getOrderNumber()+" ]取消成功!");
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"订单取消 失败!异常信息:"+e.getLocalizedMessage()));
			logger.info("订单取消失败!异常信息:"+e.getLocalizedMessage());
		}	
	}
	
	/**
	 * 关闭交易
	 * @author xuwf
	 * @since 2013-9-14
	 *
	 * @param flag 				买家0，卖家1
	 * @param orderId			订单id
	 * @param satisfaction 		满意度
	 * @param remark			评价备注		
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/closedeal")
	public void closeDeal(Integer flag,Integer orderId,Integer satisfaction,
			String remark,HttpServletRequest request,HttpServletResponse response) {
		try {	
			//当前时间
			String currentTime = StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date());
			GoodsOrder goodsOrder = orderBiz.findById(orderId);	//订单
			//添加订单评价
			OrderEvaluation orderEvaluation = null;
			//登录用户(即操作者---子账号或者主账号)
			if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
				User user = (User)request.getSession().getAttribute("user");
				orderEvaluation = new OrderEvaluation(remark,currentTime, 
						user,goodsOrder, satisfaction);
			}else{//子账号登录
				Staff staff = (Staff)request.getSession().getAttribute("user");
				orderEvaluation = new OrderEvaluation(remark,currentTime, 
						staff, goodsOrder, satisfaction);
			}
			
			orderEvaluationBiz.saveOrderEvaluation(orderEvaluation);
			logger.info("添加订单评价");
			
			OrderInfo orderInfo = null;//订单详细信息
			OrderOperateLog orderOperateLog = null; //订单操作日志
			
			if(goodsOrder.getOrderStatus() == Constant.TRANSACTIONS_IN){//订单状态是交易进行中，证明买家和卖家都没有关闭
				if(flag == Constant.FLAG_SELLER){//卖家登录
					//订单关闭交易后状态改为"等待买家关闭"
					goodsOrder.setOrderStatus(Constant.WAIT_BUYER_CLOSE);
					orderBiz.updateGoodsOrder(goodsOrder);//更新订单状态
					/****************************只是为了暂时变更招标的一些状态(最后可能不要)***************************/
					if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
						//修改招单服务的状态和插入招标的流水----
						BiddingService biddingService = goodsOrder.getBiddingService();
						biddingService.setStatus(Constant.BIDDING_BUYER_CLOSING);
						biddingServiceBiz.addOrModify(biddingService);
						logger.info("修改招单服务状态");
						BiddingServiceDetail biddingServiceDetail = null;
						//主账号登录
						if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
							User user = (User)request.getSession().getAttribute("user");
							biddingServiceDetail = new BiddingServiceDetail(biddingService, 
								biddingService.getStatus(), remark, user, currentTime, Constant.ACTION_SELLER_CLOSE_BIDDING);
						}else{//子账号登录
							Staff staff = (Staff)request.getSession().getAttribute("user");
							biddingServiceDetail = new BiddingServiceDetail(biddingService, 
									biddingService.getStatus(), remark, staff, currentTime, Constant.ACTION_SELLER_CLOSE_BIDDING);
						} 
						biddingServiceDetailBiz.add(biddingServiceDetail);
						logger.info("新增招单流水");
					}
					/*******************************************************/
					//主账号登录
					if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
						User user = (User)request.getSession().getAttribute("user");
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
							remark,user,currentTime,Constant.ACTION_SELLER_EVALUATION);	
						
						orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
							user.getUserName(),Constant.WAIT_BUYER_CLOSE_STR,currentTime);
					}else{//子账号登录
						Staff staff = (Staff)request.getSession().getAttribute("user");
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
							remark,staff,currentTime,Constant.ACTION_SELLER_EVALUATION);	
							
						orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
							staff.getUserName(),Constant.WAIT_BUYER_CLOSE_STR,currentTime);
					}
					
					
					//卖家关闭订单买家还未关闭,发送消息给买家
					User buyer = userBiz.findUserByEnterprise(goodsOrder.getPurchaser_id());
					Message message = getTransactionMessage();
					sendTransactionMessage(Constant.LOGIN_USER, message,buyer.getId(), buyer.getUserName(), true, goodsOrder.getOrderNumber(), 5);
					
				}else if(flag == Constant.FLAG_BUYER){//买家登录
					//订单关闭交易后状态改为"等待卖家关闭"
					goodsOrder.setOrderStatus(Constant.WAIT_SELLER_CLOSE);
					orderBiz.updateGoodsOrder(goodsOrder);//更新订单状态
					/****************************只是为了暂时变更招标的一些状态(最后可能不要)***************************/
					if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
						//修改招单服务的状态和插入招标的流水----
						BiddingService biddingService = goodsOrder.getBiddingService();
						biddingService.setStatus(Constant.BIDDING_SELLER_CLOSING);
						biddingServiceBiz.addOrModify(biddingService);
						logger.info("修改招单服务状态");
						BiddingServiceDetail biddingServiceDetail = null;
						//主账号登录
						if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
							User user = (User)request.getSession().getAttribute("user");
							biddingServiceDetail = new BiddingServiceDetail(biddingService, 
								biddingService.getStatus(), remark, user, currentTime, Constant.ACTION_BUYER_CLOSE_BIDDING);
						}else{//子账号登录
							Staff staff = (Staff)request.getSession().getAttribute("user");
							biddingServiceDetail = new BiddingServiceDetail(biddingService, 
								biddingService.getStatus(), remark, staff, currentTime, Constant.ACTION_BUYER_CLOSE_BIDDING);
						}
						biddingServiceDetailBiz.add(biddingServiceDetail);
						logger.info("新增招单流水");
					}
					/*********************************************************************/
					//主账号登录
					if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
						User user = (User)request.getSession().getAttribute("user");
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
							remark,user,currentTime,Constant.ACTION_BUYER_EVALUATION);	
						
						orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
							user.getUserName(),Constant.WAIT_SELLER_CLOSE_STR,currentTime);
					}else{//子账号登录
						Staff staff = (Staff)request.getSession().getAttribute("user");
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
							remark,staff,currentTime,Constant.ACTION_BUYER_EVALUATION);	
							
						orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
							staff.getUserName(),Constant.WAIT_SELLER_CLOSE_STR,currentTime);
					}
					//买家关闭订单卖家还未关闭,发送消息给卖家
					User seller = userBiz.findUserByEnterprise(goodsOrder.getSeller_id());
					Message message = getTransactionMessage();
					sendTransactionMessage(Constant.LOGIN_USER, message,seller.getId(), seller.getUserName(), true, goodsOrder.getOrderNumber(), 4);
				}
				
			}else{	//订单状态为等待买家或卖家关闭时,下一步都是交易结束
				//订单关闭交易后状态改为"交易结束"
				goodsOrder.setOrderStatus(Constant.TRANSACTIONS_END);
				orderBiz.updateGoodsOrder(goodsOrder);//更新订单状态
				if(flag == Constant.FLAG_SELLER){//卖家登录	
					if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
						//修改招单服务的状态和插入招标的流水----
						BiddingService biddingService = goodsOrder.getBiddingService();
						biddingService.setStatus(Constant.BIDDING_TRADE_END);
						biddingServiceBiz.addOrModify(biddingService);
						logger.info("修改招单服务状态");
						BiddingServiceDetail biddingServiceDetail = null;
						//主账号登录
						if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
							User user = (User)request.getSession().getAttribute("user");
							biddingServiceDetail = new BiddingServiceDetail(biddingService, 
								biddingService.getStatus(), remark, user, currentTime, Constant.ACTION_SELLER_CLOSE_BIDDING);
						}else{//子账号登录
							Staff staff = (Staff)request.getSession().getAttribute("user");
							biddingServiceDetail = new BiddingServiceDetail(biddingService, 
								biddingService.getStatus(), remark, staff, currentTime, Constant.ACTION_SELLER_CLOSE_BIDDING);
						}
						biddingServiceDetailBiz.add(biddingServiceDetail);
						logger.info("新增招单流水");
					}
					//主账号登录
					if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
						User user = (User)request.getSession().getAttribute("user");
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
							remark,user,currentTime,Constant.ACTION_SELLER_EVALUATION);
						
						orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
								user.getUserName(),Constant.TRANSACTIONS_END_STR,currentTime);
					}else{//子账号登录
						Staff staff = (Staff)request.getSession().getAttribute("user");
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
							remark,staff,currentTime,Constant.ACTION_SELLER_EVALUATION);
						orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
								staff.getUserName(),Constant.TRANSACTIONS_END_STR,currentTime);
					}
					
				}else if(flag == Constant.FLAG_BUYER){//买家登录
					if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
						//修改招单服务的状态和插入招标的流水----
						BiddingService biddingService = goodsOrder.getBiddingService();
						biddingService.setStatus(Constant.BIDDING_TRADE_END);
						biddingServiceBiz.addOrModify(biddingService);
						logger.info("修改招单服务状态");
						BiddingServiceDetail biddingServiceDetail = null;
						//主账号登录
						if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
							User user = (User)request.getSession().getAttribute("user");
							biddingServiceDetail = new BiddingServiceDetail(biddingService, 
								biddingService.getStatus(), remark, user, currentTime, Constant.ACTION_BUYER_CLOSE_BIDDING);
						}else{//子账号登录
							Staff staff = (Staff)request.getSession().getAttribute("user");
							biddingServiceDetail = new BiddingServiceDetail(biddingService, 
									biddingService.getStatus(), remark, staff, currentTime, Constant.ACTION_BUYER_CLOSE_BIDDING);
						}
						biddingServiceDetailBiz.add(biddingServiceDetail);
						logger.info("新增招单流水");
					}
					//主账号登录
					if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
						User user = (User)request.getSession().getAttribute("user");
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
							remark,user,currentTime,Constant.ACTION_BUYER_EVALUATION);
						
						orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
							user.getUserName(),Constant.TRANSACTIONS_END_STR,currentTime);
					}else{//子账号登录
						Staff staff = (Staff)request.getSession().getAttribute("user");
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
							remark,staff,currentTime,Constant.ACTION_BUYER_EVALUATION);
						orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
							staff.getUserName(),Constant.TRANSACTIONS_END_STR,currentTime);
					}
					
				}
				/***************************卖家信誉信息*****************************/
				//得到该企业
				Enterprise enterprise = enterpriseBiz.findByEid(goodsOrder.getSeller_id());
				//得到该企业的信誉表
				EnterpriseCredit ec = enterpriseCreditBiz.queryByEnterprise(enterprise.getId());
				Integer sellCount = 0;	//卖出次数
				Integer sellScore = 0;	//卖出总评分
				double sellCredit = 0;	//卖家信誉	= 卖出总评分/卖出次数
				if(!StringUtils.isEmpty(ec)){//之前存在该企业信誉信息
					if(!StringUtils.isEmpty(ec.getSellCount())){//卖出服务次数
						sellCount = ec.getSellCount()+ 1;
					}else{
						sellCount = 1;
					}
					if(!StringUtils.isEmpty(ec.getSellScore())){//卖出总评分
						sellScore = ec.getSellScore() + satisfaction;
					}else{
						sellScore = satisfaction;
					}
					sellCredit = (double)sellScore/sellCount;
				}else{//不存在该企业的企业信誉信息
					sellCount = 1;
					sellScore = satisfaction;
					sellCredit = (double)sellScore/sellCount;
					//添加卖家企业信誉
					ec = new EnterpriseCredit();
				}
				ec.setSellCount(sellCount);
				ec.setSellScore(sellScore);
				ec.setSellCredit(Common.parseDouble(sellCredit));
				ec.setEnterprise(enterprise);
				enterpriseCreditBiz.saveEnterpriseCredit(ec);
				//企业信誉发生改变，随之保存最新的登录者信誉到session
				request.getSession().setAttribute("enterpriseCredit", ec);
				/***************************end卖家信誉信息*****************************/
				/***************************买家信誉信息*****************************/
				//得到该企业
				Enterprise buyerEnterprise = enterpriseBiz.findByEid(goodsOrder.getPurchaser_id());
				//得到该企业的信誉表
				EnterpriseCredit buyEc = enterpriseCreditBiz.queryByEnterprise(buyerEnterprise.getId());
				Integer buyCount = 0;	//购买次数
				Integer buyScore = 0;	//购买总评分
				double buyerCredit = 0;	//买家信誉	= 购买总评分/购买次数
				if(!StringUtils.isEmpty(buyEc)){//之前存在该企业信誉信息
					if(!StringUtils.isEmpty(buyEc.getBuyCount())){//购买服务次数
						buyCount = buyEc.getBuyCount()+ 1;
					}else{
						buyCount = 1;
					}
					if(!StringUtils.isEmpty(buyEc.getBuyScore())){//购买总评分
						buyScore = buyEc.getBuyScore() + satisfaction;
					}else{
						buyScore = satisfaction;
					}
					buyerCredit = (double)buyScore/buyCount;
				}else{//不存在该企业的企业信誉信息
					buyCount = 1;
					buyScore = satisfaction;
					buyerCredit = (double)buyScore/buyCount;
					//添加买家企业信誉
					buyEc = new EnterpriseCredit();
				}
				buyEc.setBuyCount(buyCount);
				buyEc.setBuyScore(buyScore);
				buyEc.setBuyCredit(Common.parseDouble(buyerCredit));
				buyEc.setEnterprise(buyerEnterprise);
				enterpriseCreditBiz.saveEnterpriseCredit(buyEc);
				//企业信誉发生改变，随之保存最新的登录者信誉到session
				request.getSession().setAttribute("enterpriseCredit", buyEc);
				/***************************end买家信誉信息*****************************/
				if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_S){//普通服务有服务评价
					//添加服务的评价交易信息
					Service service = goodsOrder.getService();
					//得到买家的满意度评价
					Integer buyerSat = (Integer) orderEvaluationBiz.getBuyerSatisfaction(goodsOrder);
					//总分=之前的总分+买家满意度
					service.setTotalScore(service.getTotalScore()+buyerSat);
					//服务次数+1
					service.setServiceNum(service.getServiceNum()+1);
					serviceBiz.update(service);
					//服务评价=总分/服务次数
					service.setEvaluateScore(Common.parseDouble(service.getTotalScore()/service.getServiceNum()));
					serviceBiz.update(service);
					
					// 服务修改了同步服务至窗口
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(
							new ServiceSyncBean(service, SyncFactory.getSyncType(service))), true);
				}
				
				
				//买家关闭订单卖家已关闭或者卖家关闭订单时买家已关闭,发送消息给买家和卖家
				User buyer = userBiz.findUserByEnterprise(goodsOrder.getPurchaser_id());
				User seller = userBiz.findUserByEnterprise(goodsOrder.getSeller_id());
				Message message = getTransactionMessage();
				sendTransactionMessage(Constant.LOGIN_USER,message, seller.getId(), seller.getUserName(), true, goodsOrder.getOrderNumber(), 6);
				sendTransactionMessage(Constant.LOGIN_USER,message, buyer.getId(), buyer.getUserName(), true, goodsOrder.getOrderNumber(), 6);
			}
			orderInfoBiz.saveOrderInfo(orderInfo);
			logger.info("添加订单详细信息");
			
			// 同步订单至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(new GoodsOrderSyncBean(goodsOrder, SyncType.ONE)),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(new OrderInfoSyncBean(orderInfo, SyncType.ONE)));
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderEvaluation>(new OrderEvaluationSyncBean(orderEvaluation, SyncType.ONE)));
			
			orderOperLogBiz.saveOrderOperLog(orderOperateLog);
			logger.info("添加订单操作日志");

			this.outJson(response,new JSONResult(true,"订单【"+goodsOrder.getOrderNumber()+"】关闭交易成功!"));
			logger.info("订单[ "+goodsOrder.getOrderNumber()+" ]关闭交易成功!");
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"交易关闭失败!异常信息:"+e.getLocalizedMessage()));
			logger.info("交易关闭失败!异常信息:"+e.getLocalizedMessage());
		}	
	}
	
//	public void handlerBiddingService(GoodsOrder goodsOrder,HttpServletRequest request)
	
	/**
	 * 订单申诉
	 * @author xuwf
	 * @since 2013-9-16
	 * @param flag			买家0,卖家1
	 * @param orderId		订单id
	 * @param attachment	附件路径
	 * @param reason		申诉原因
	 * @param remark		申诉备注
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/orderappeal")
	public void orderAppeal(int flag,Integer orderId,String attachment,Integer reason,
			String remark,HttpServletRequest request,HttpServletResponse response){
		try {
			GoodsOrder goodsOrder = orderBiz.findById(orderId);	
			
			//订单提交申诉后状态改为"申诉处理中"
			goodsOrder.setOrderStatus(Constant.APPEAL_PROCESSING);
			orderBiz.updateGoodsOrder(goodsOrder);//更新订单状态
			
			//提交申诉后添加申诉单
			Appeal appeal = null;
			OrderInfo orderInfo = null;	//订单详细信息
			if(flag == Constant.FLAG_BUYER){//买家申诉
				//主账号登录
				if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
					User user = (User)request.getSession().getAttribute("user");
					appeal = new Appeal(goodsOrder, attachment, remark, reason,Constant.APPEAL_TYPE_BUYER,
						user,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
				}else{//子账号登录
					Staff staff = (Staff)request.getSession().getAttribute("user");
					appeal = new Appeal(goodsOrder, attachment, remark, reason,Constant.APPEAL_TYPE_BUYER,
							staff,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
				}
				if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
					//修改招单服务的状态和插入招标的流水----
					BiddingService biddingService = goodsOrder.getBiddingService();
					biddingService.setStatus(Constant.BIDDING_APPEAL_DEALING);
					biddingServiceBiz.addOrModify(biddingService);
					logger.info("修改招单服务状态");
					BiddingServiceDetail biddingServiceDetail =null;
					//主账号登录
					if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
						User user = (User)request.getSession().getAttribute("user");
						biddingServiceDetail= new BiddingServiceDetail(biddingService, 
								biddingService.getStatus(), remark, user, StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()), Constant.ACTION_BUYER_APPEAL_BIDDING);
					}else{//子账号登录
						Staff staff = (Staff)request.getSession().getAttribute("user");
						biddingServiceDetail= new BiddingServiceDetail(biddingService, 
								biddingService.getStatus(), remark, staff, StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()), Constant.ACTION_BUYER_APPEAL_BIDDING);
					}
					biddingServiceDetailBiz.add(biddingServiceDetail);
					logger.info("新增招单流水");
				}
				//主账号登录
				if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
					User user = (User)request.getSession().getAttribute("user");
					orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
						remark,user,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()),
						Constant.ACTION_BUYER_APPEAL);
				}else{//子账号登录
					Staff staff = (Staff)request.getSession().getAttribute("user");
					orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
						remark,staff,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()),
						Constant.ACTION_BUYER_APPEAL);
				}
			}else if(flag == Constant.FLAG_SELLER){//卖家申诉
				//主账号登录
				if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
					User user = (User)request.getSession().getAttribute("user");
					appeal = new Appeal(goodsOrder, attachment, remark, reason,Constant.APPEAL_TYPE_SELLER,
						user,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
				}else{//子账号登录
					Staff staff = (Staff)request.getSession().getAttribute("user");
					appeal = new Appeal(goodsOrder, attachment, remark, reason,Constant.APPEAL_TYPE_SELLER,
						staff,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
				}
				
				if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
					//修改招单服务的状态和插入招标的流水----
					BiddingService biddingService = goodsOrder.getBiddingService();
					biddingService.setStatus(Constant.BIDDING_APPEAL_DEALING);
					biddingServiceBiz.addOrModify(biddingService);
					logger.info("修改招单服务状态");
					BiddingServiceDetail biddingServiceDetail = null;
					//主账号登录
					if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
						User user = (User)request.getSession().getAttribute("user");
						biddingServiceDetail = new BiddingServiceDetail(biddingService, 
							biddingService.getStatus(), remark, user, StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()), Constant.ACTION_SELLER_APPEAL_BIDDING);
					}else{//子账号登录
						Staff staff = (Staff)request.getSession().getAttribute("user");
						biddingServiceDetail = new BiddingServiceDetail(biddingService, 
							biddingService.getStatus(), remark, staff, StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()), Constant.ACTION_SELLER_APPEAL_BIDDING);
					}
					
					biddingServiceDetailBiz.add(biddingServiceDetail);
					logger.info("新增招单流水");
				}
				//主账号登录
				if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
					User user = (User)request.getSession().getAttribute("user");
					orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
						remark,user,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()),
						Constant.ACTION_SELLER_APPEAL);
				}else{//子账号登录
					Staff staff = (Staff)request.getSession().getAttribute("user");
					orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), 
						remark,staff,StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()),
						Constant.ACTION_SELLER_APPEAL);
				}
			}
			appealBiz.saveAppeal(appeal);
			logger.info("添加申诉信息");
			orderInfoBiz.saveOrderInfo(orderInfo);
			logger.info("添加订单详细信息");

			// 同步订单至窗口
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(new GoodsOrderSyncBean(goodsOrder, SyncType.ONE)),true);
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(new OrderInfoSyncBean(orderInfo, SyncType.ONE)));
			SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Appeal>(new AppealSyncBean(appeal, SyncType.ONE)));
			
			//添加订单操作日志
			OrderOperateLog orderOperateLog = null;
			//主账号登录
			if(request.getSession().getAttribute("usertype").equals(Constant.LOGIN_USER)){
				User user = (User)request.getSession().getAttribute("user");
				orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
						user.getUserName(),Constant.APPEAL_PROCESSING_STR,
						StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
			}else{//子账号登录
				Staff staff = (Staff)request.getSession().getAttribute("user");
				orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(),
						staff.getUserName(),Constant.APPEAL_PROCESSING_STR,
						StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
			}
			orderOperLogBiz.saveOrderOperLog(orderOperateLog);
			logger.info("添加订单操作日志");
				
			this.outJson(response,new JSONResult(true,"订单【"+goodsOrder.getOrderNumber()+"】提交成功!"));
			logger.info("[ "+goodsOrder.getOrderNumber()+" ]提交申诉成功!");

		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, 
					"提交申诉失败!异常信息:" + e.getLocalizedMessage()));
			logger.info("提交申诉失败!异常信息:" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 查询申诉
	 * @author cs
	 * @since  2013-9-11
	 * 
	 * @param flag			记号(买家0，卖家1)	
	 * @param appealType	0：我的申诉 1：我被申诉
	 * @param userId		登录用户
	 * @param orderNo		订单编号
	 * @param orderStatus	订单状态
	 * @param startTime		下单时间
	 * @param endTime		结束时间
	 * @param page
	 * @param rows
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/appeal")
	public void myappeal(
			@RequestParam("flag")int flag,
			@RequestParam("appealType")int appealType,
			@RequestParam("userId")Integer userId,
			@RequestParam(value="orderNo") String orderNo,
			@RequestParam(value="startTime") String startTime,
			@RequestParam(value="endTime") String endTime,
			@RequestParam(value="page",required=false)int page,
			@RequestParam(value="rows",required=false)int rows,
			HttpServletRequest request,
			HttpServletResponse response) {
		if(!StringUtils.isEmpty(userId)){
			JSONRows<Appeal> jrows = appealBiz.query(userId, appealType,orderNo,flag,startTime, endTime, rows * (page - 1), rows);
			this.outJson(response, jrows);
		}		
	}
	
	/**
	 * 查询订单，按状态、订单状态、下单时间（模糊查询）查询(支撑运营平台)
	 * 
	 * @author xuwf
	 * @since 2013-9-24
	 * 
	 * @param status		服务状态
	 * @param start			起始值
	 * @param limit			每页条数
	 * @param request	
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/find")
	public void find(@RequestParam(value="orderStatus",required=false)String orderStatus,
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="serviceName",required=false)String serviceName,
			@RequestParam(value="startTime",required=false)String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="20", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		if(null == orderStatus){
			orderStatus = "";
		}
		
		JSONData<GoodsOrder> orders = orderBiz.queryOrder(orderNumber, serviceName,orderStatus, startTime, endTime, start, limit);
		this.outJson(response,orders);
	}
	

	/**
	 * 订单处理 - 支撑运营平台
	 *@author xuwf
	 *@since 2013-9-24
	 *
	 *@param goodsOrder 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/edit")
	public void update(Integer appealId,Integer orderId,Integer handlerMode,
			String handlerRemark,HttpServletRequest request,HttpServletResponse response) {
			//得到订单
			GoodsOrder goodsOrder = orderBiz.findById(orderId);
			
			//平台人员处理订单
			Manager manager = (Manager) request.getSession().getAttribute("manager");
			//申诉单
			Appeal appeal;
			try {
				if(handlerMode == Constant.HANDLER_MODE_CLOSE){//处理方式：关闭订单
					OrderInfo orderInfo;
					//添加订单操作
					OrderOperateLog orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(), 
							manager.getUsername(),Constant.TRANSACTIONS_END_STR,  StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()));
					orderOperLogBiz.saveOrderOperLog(orderOperateLog);
					
					//申诉处理对订单的操作
					if(!StringUtils.isEmpty(appealId) || goodsOrder.getOrderStatus() == Constant.APPEAL_PROCESSING){
						//添加订单详情:申诉处理，关闭订单
						//修改订单状态并保存
						goodsOrder.setOrderStatus(Constant.TRANSACTIONS_END);
						orderBiz.updateGoodsOrder(goodsOrder);
						
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), handlerRemark,
								manager, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),
								Constant.ACTION_ORDER_APPEAL_CLOSE);
						orderInfoBiz.saveOrderInfo(orderInfo);
						
						if(StringUtils.isEmpty(appealId)){
							appeal = appealBiz.queryByOrderId(orderId);
						}else{
							appeal = appealBiz.findById(appealId);
						}
						//为申诉单完善资料
						appeal.setAppealStatus(Constant.APPEAL_STATUS＿CLOSE_DEAL);
						appeal.setManager(manager);
						appeal.setProcessMode(Constant.HANDLER_MODE_CLOSE);
						appeal.setProcessTime(StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()));
						appeal.setHandlerRemark(handlerRemark);
						appealBiz.update(appeal);
						
						//同步申诉到卖家窗口
						SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Appeal>(
							new AppealSyncBean(appeal, SyncType.ONE)));
						
						if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
							//修改招单服务的状态和插入招标的流水----
							BiddingService biddingService = goodsOrder.getBiddingService();
							biddingService.setStatus(Constant.BIDDING_TRADE_END);
							biddingServiceBiz.addOrModify(biddingService);
							logger.info("修改招单服务状态");
							BiddingServiceDetail biddingServiceDetail = new BiddingServiceDetail(biddingService, 
									biddingService.getStatus(), handlerRemark, manager, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_CLOSE_APPEAL_BIDDING);
							biddingServiceDetailBiz.add(biddingServiceDetail);
							logger.info("新增招单流水");
						}
					}else{//管理员关闭订单操作
						//修改订单状态并保存
						goodsOrder.setOrderStatus(Constant.TRANSACTIONS_END);
						orderBiz.updateGoodsOrder(goodsOrder);
						
						//添加订单详情
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), handlerRemark,
								manager, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),
								Constant.ACTION_CLOSE_DEAL);
						orderInfoBiz.saveOrderInfo(orderInfo);

						if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
							//修改招单服务的状态和插入招标的流水----
							BiddingService biddingService = goodsOrder.getBiddingService();
							biddingService.setStatus(Constant.BIDDING_TRADE_END);
							biddingServiceBiz.addOrModify(biddingService);
							logger.info("修改招单服务状态");
							BiddingServiceDetail biddingServiceDetail = new BiddingServiceDetail(biddingService, 
									biddingService.getStatus(), handlerRemark, manager, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_PLAT_CLOSE_BIDDING);
							biddingServiceDetailBiz.add(biddingServiceDetail);
							logger.info("新增招单流水");
						}
					}	
				
					//普通服务才有服务评价(管理员正常关闭或者申诉关闭都默认服务满意度5分)
					if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_S){
						//添加服务的评价交易信息
						Service service = goodsOrder.getService();
						//得到买家的满意度评价,如果为空，默认5分
						Integer buyerSat = (Integer) orderEvaluationBiz.getBuyerSatisfaction(goodsOrder);
						if(StringUtils.isEmpty(buyerSat)){
							buyerSat = Constant.SATISFACTION_VERY;
						}
						//总分=之前的总分+买家满意度
						service.setTotalScore(service.getTotalScore()+buyerSat);
						//服务次数+1
						service.setServiceNum(service.getServiceNum()+1);
						serviceBiz.update(service);
						//服务评价=总分/服务次数
						service.setEvaluateScore(Common.parseDouble(service.getTotalScore()/service.getServiceNum()));
						serviceBiz.update(service);
						
						//同步服务到窗口
						SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(
								new ServiceSyncBean(service, SyncFactory.getSyncType(service))));
					}	
					/***************************买家卖家信誉信息*****************************/
					//得到买家企业
					Enterprise buyEnterprise = enterpriseBiz.findByEid(goodsOrder.getPurchaser_id());
					
					//得到买家企业的信誉表
					EnterpriseCredit ec = enterpriseCreditBiz.queryByEnterprise(buyEnterprise.getId());
					Integer buyCount = 0;	//购买次数
					Integer buyScore = 0;	//购买总评分
					double buyCredit = 0;	//买家信誉	= 购买总评分/购买次数
					if(!StringUtils.isEmpty(ec)){//之前存在该企业信誉信息
						if(!StringUtils.isEmpty(ec.getBuyCount())){//购买服务次数
							buyCount = ec.getBuyCount()+ 1;
						}else{
							buyCount = 1;
						}
						if(!StringUtils.isEmpty(ec.getBuyScore())){//购买总评分
							buyScore = ec.getBuyScore() + Constant.SATISFACTION_VERY;
						}else{
							buyScore = Constant.SATISFACTION_VERY;
						}
						buyCredit = (double)buyScore/buyCount;
					}else{//不存在该企业的企业信誉信息
						buyCount = 1;
						buyScore = Constant.SATISFACTION_VERY;
						buyCredit = (double)buyScore/buyCount;
						//添加买家企业信誉
						ec = new EnterpriseCredit();
					}
					ec.setBuyCount(buyCount);
					ec.setBuyScore(buyScore);
					ec.setBuyCredit(Common.parseDouble(buyCredit));
					ec.setEnterprise(buyEnterprise);
					enterpriseCreditBiz.saveEnterpriseCredit(ec);
					//得到卖家企业
					Enterprise sellEnterprise = enterpriseBiz.findByEid(goodsOrder.getSeller_id());
					//得到卖家企业的信誉表
					EnterpriseCredit sellEc = enterpriseCreditBiz.queryByEnterprise(sellEnterprise.getId());
					Integer sellCount = 0;	//卖出次数
					Integer sellScore = 0;	//卖出总评分
					double sellCredit = 0;	//卖家信誉
					if(!StringUtils.isEmpty(sellEc)){//之前存在该企业信誉信息
						if(!StringUtils.isEmpty(sellEc.getSellCount())){//购买服务次数
							sellCount = sellEc.getSellCount()+ 1;
						}else{
							sellCount = 1;
						}
						if(!StringUtils.isEmpty(sellEc.getSellScore())){//购买总评分
							sellScore = sellEc.getSellScore() + Constant.SATISFACTION_VERY;
						}else{
							sellScore = Constant.SATISFACTION_VERY;
						}
						sellCredit = (double)sellScore/sellCount;
					}else{//不存在该企业的企业信誉信息
						sellCount = 1;
						sellScore = Constant.SATISFACTION_VERY;
						sellCredit = (double)sellScore/sellCount;
						//添加买家企业信誉
						sellEc = new EnterpriseCredit();
					}
					sellEc.setSellCount(sellCount);
					sellEc.setSellScore(sellScore);
					sellEc.setSellCredit(Common.parseDouble(sellCredit));
					sellEc.setEnterprise(sellEnterprise);
					enterpriseCreditBiz.saveEnterpriseCredit(sellEc);
					/***************************买家卖家信誉信息*****************************/
					
					//同步订单至卖家窗口
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(
							new GoodsOrderSyncBean(goodsOrder, SyncType.ONE)),true);
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(
							new OrderInfoSyncBean(orderInfo, SyncType.ONE)));
					
					//管理员关闭订单（申诉一致）,发送消息给买家和卖家
					User buyer = userBiz.findUserByEnterprise(goodsOrder.getPurchaser_id());
					User seller = userBiz.findUserByEnterprise(goodsOrder.getSeller_id());
					Message message = getTransactionMessage();
					sendTransactionMessage(Constant.LOGIN_USER,message, seller.getId(), seller.getUserName(), true, goodsOrder.getOrderNumber(), 7);
					sendTransactionMessage(Constant.LOGIN_USER, message,buyer.getId(), buyer.getUserName(), true, goodsOrder.getOrderNumber(), 7);
				}else if(handlerMode == Constant.HANDLER_MODE_CANCEL){//处理方式：取消订单
					OrderInfo orderInfo;//订单流水
					
					//添加订单操作
					OrderOperateLog orderOperateLog = new OrderOperateLog(goodsOrder.getOrderNumber(), 
							manager.getUsername(),Constant.ORDER_CANCEL_STR,  StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()));
					orderOperLogBiz.saveOrderOperLog(orderOperateLog);
					
					if(!StringUtils.isEmpty(appealId) || goodsOrder.getOrderStatus() == Constant.APPEAL_PROCESSING){//申诉处理对订单的操作
						//修改订单状态保存
						goodsOrder.setOrderStatus(Constant.ORDER_CANCEL);
						orderBiz.updateGoodsOrder(goodsOrder);
						//添加订单详情
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), handlerRemark,
								manager, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),
								Constant.ACTION_ORDER_APPEAL_CANCEL);
						orderInfoBiz.saveOrderInfo(orderInfo);
						
						if(StringUtils.isEmpty(appealId)){
							appeal = appealBiz.queryByOrderId(orderId);
						}else{
							appeal = appealBiz.findById(appealId);
						}
						//为申诉单完善资料
						appeal.setAppealStatus(Constant.APPEAL_STATUS_ORDER_CANCEL);
						appeal.setManager(manager);
						appeal.setProcessMode(Constant.HANDLER_MODE_CANCEL);
						appeal.setProcessTime(StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()));
						appeal.setHandlerRemark(handlerRemark);
						appealBiz.update(appeal);
						
						//同步申诉到卖家窗口
						SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Appeal>(
							new AppealSyncBean(appeal, SyncType.ONE)));
						
						if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
							//修改招单服务的状态和插入招标的流水----
							BiddingService biddingService = goodsOrder.getBiddingService();
							biddingService.setStatus(Constant.BIDDING_ORDER_CANCEL);
							biddingServiceBiz.addOrModify(biddingService);
							logger.info("修改招单服务状态");
							BiddingServiceDetail biddingServiceDetail = new BiddingServiceDetail(biddingService, 
									biddingService.getStatus(), handlerRemark, manager, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_CANCEL_APPEAL_BIDDING);
							biddingServiceDetailBiz.add(biddingServiceDetail);
							logger.info("新增招单流水");
						}
					}else{//管理员取消订单操作
						//修改订单状态并保存
						goodsOrder.setOrderStatus(Constant.ORDER_CANCEL);
						orderBiz.updateGoodsOrder(goodsOrder);
						//添加订单详情
						orderInfo = new OrderInfo(goodsOrder, goodsOrder.getOrderStatus(), handlerRemark,
								manager, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),
								Constant.ACTION_MANAGER_ORDER_CANCEL);
						orderInfoBiz.saveOrderInfo(orderInfo);
						
						if(goodsOrder.getOrderSource() == Constant.ORDER_SOURCE_B){//来源招单服务
							//修改招单服务的状态和插入招标的流水----
							BiddingService biddingService = goodsOrder.getBiddingService();
							biddingService.setStatus(Constant.BIDDING_ORDER_CANCEL);
							biddingServiceBiz.addOrModify(biddingService);
							logger.info("修改招单服务状态");
							BiddingServiceDetail biddingServiceDetail = new BiddingServiceDetail(biddingService, 
									biddingService.getStatus(), handlerRemark, manager, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_PLAT_CANCEL_BIDDING_ORDER);
							biddingServiceDetailBiz.add(biddingServiceDetail);
							logger.info("新增招单流水");
						}
					}
					
					//同步订单至卖家窗口
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(
							new GoodsOrderSyncBean(goodsOrder, SyncType.ONE)), true);
					SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(
							new OrderInfoSyncBean(orderInfo, SyncType.ONE)));
					
					//管理员取消订单（申诉一致）,发送消息给买家和卖家
					User buyer = userBiz.findUserByEnterprise(goodsOrder.getPurchaser_id());
					User seller = userBiz.findUserByEnterprise(goodsOrder.getSeller_id());
					Message message = getTransactionMessage();
					sendTransactionMessage(Constant.LOGIN_USER,message, seller.getId(), seller.getUserName(), true, goodsOrder.getOrderNumber(), 8);
					sendTransactionMessage(Constant.LOGIN_USER,message, buyer.getId(), buyer.getUserName(), true, goodsOrder.getOrderNumber(), 8);
				}
				logger.info("[ "+goodsOrder.getOrderNumber()+" ]处理成功!");
				this.outJson(response,new JSONResult(true,"订单[" + goodsOrder.getOrderNumber() + "]处理成功"));
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"订单[" + goodsOrder.getOrderNumber() + "]处理失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("订单处理失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	
	/**
	 * 查询申诉处理中的订单	支撑平台
	 * @author xuwf
	 * @since 2013-9-25
	 * 
	 * @param orderStatus		订单状态
	 * @param appealType		申诉类型1.买家 申诉2.卖家申诉
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findAppeal")
	public void findAppeal(@RequestParam(value="orderStatus",required=false)Integer orderStatus,
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="appealType",required=false)String appealType,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="20", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		JSONData<Appeal> appeals = appealBiz.findByStatus(orderStatus,orderNumber,appealType, start, limit);
		this.outJson(response,appeals);
	}
	
	/**
	 * 申诉查询		支撑平台
	 * @author xuwf
	 * @since 2013-9-26
	 * 
	 * @param orderNum		订单编号
	 * @param appealType	申诉类型1.买家申诉 2.卖家申诉
	 * @param startTime		处理开始时间
	 * @param endTime		结束时间
	 * @param buyer			买家
	 * @param seller		卖家
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/qAppealList")
	public void queryIntegrated(@RequestParam(value="orderNumber",required=false)String orderNum,
			@RequestParam(value="appealType",required=false)String appealType,
			@RequestParam(value="startTime",required=false)String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="buyer",required=false)String buyer,
			@RequestParam(value="seller",required=false)String seller,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="20", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		JSONData<OrderAndBiddingAppeal> appeals = appealBiz.queryIntegrated(orderNum, appealType, startTime,
				endTime, buyer, seller, start, limit);
		this.outJson(response,appeals);
	}
	
	/**
	 * 根据订单id查看该订单的流水信息
	 * @param orderId		订单id
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findOrderInfo")
	public void findOrderInfo(
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="100", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		JSONData<OrderInfo> jdInfo = orderInfoBiz.findOrderInfo(orderId, start, limit);
		this.outJson(response, jdInfo);
	}
	
	/**
	 * 根据订单id查看该订单的评价信息
	 * @param orderId		订单id
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findEval")
	public void findEval(
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="100", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		JSONData<OrderEvaluation> jdEval = orderEvaluationBiz.findEval(orderId, start, limit);
		this.outJson(response, jdEval);
	}
	
	/**
	 * 校验用户是否合法
	 * @param sm_login 请求中带有的cookie值
	 * @return  数据集合 
	 * */
	private Map<String, Object> checkLogin(String sm_login) {
		Map<String, Object> result = new HashMap<String, Object>();
 		String[] strArray = sm_login.split("\\|");
 		if(strArray.length != 3) {
 			result.put("success", false);
			result.put("message", "校验码不合法");
			return result;
 		}
 		String userType = strArray[0];
 		String uuid = strArray[1];
 		String secret = strArray[2];
 		if("user".equals(userType)) {
 			User user = userBiz.findUserByUid(uuid);		//根据uid获取用户
 			if(user == null) {
 				result.put("success", false);
 				result.put("message", "不存在该用户");
 				return result;
 			}
 			String secret2 = MD5.toMD5(user.getUserName() + user.getPassword());
 			if(secret2.equals(secret)) {
 				result.put("success", true);
 				result.put("userOrStaff", user);
 			} else {
 				result.put("success", false);
 				result.put("message", "校验未通过");
 			}
 		} else {
 			Staff staff = staffBiz.findByStid(uuid);
 			if(staff == null) {
 				result.put("success", false);
 				result.put("message", "不存在该用户");
 				return result;
 			}
 			String secret2 = MD5.toMD5(staff.getUserName() + staff.getPassword());
 			if(secret2.equals(secret)) {
 				result.put("success", true);
 				result.put("userOrStaff", staff);
 			} else {
 				result.put("success", false);
 				result.put("message", "校验未通过");
 			}
 		}
 		result.put("userType", userType);
		return result;
	}
}
