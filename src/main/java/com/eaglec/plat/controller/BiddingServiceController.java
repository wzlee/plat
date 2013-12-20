package com.eaglec.plat.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.business.AppealBiz;
import com.eaglec.plat.biz.business.BiddingServiceBiz;
import com.eaglec.plat.biz.business.BiddingServiceDetailBiz;
import com.eaglec.plat.biz.business.GoodsOrderBiz;
import com.eaglec.plat.biz.business.ResponseBiddingBiz;
import com.eaglec.plat.biz.info.MessageBiz;
import com.eaglec.plat.biz.info.MessageClassBiz;
import com.eaglec.plat.biz.info.ReceiverMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderGroupMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderMessageRelationshipBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.publik.FileManagerBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.Appeal;
import com.eaglec.plat.domain.business.BiddingService;
import com.eaglec.plat.domain.business.BiddingServiceDetail;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.domain.business.ResponseBidding;
import com.eaglec.plat.domain.info.Message;
import com.eaglec.plat.domain.info.MessageClass;
import com.eaglec.plat.domain.info.ReceiverMessageRelationship;
import com.eaglec.plat.domain.info.SenderGroupMessageRelationship;
import com.eaglec.plat.domain.info.SenderMessageRelationship;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.Dao;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.JSONRows;


/**
 * 招标/应标 控制层
 * 
 * @author Xiadi
 * @since 2013-9-29
 */
@Controller
@RequestMapping(value="/bidding")
public class BiddingServiceController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BiddingServiceController.class);

	@Autowired
	private BiddingServiceBiz biddingServiceBiz;
	@Autowired
	private ResponseBiddingBiz responseBiddingBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private BiddingServiceDetailBiz biddingServiceDetailBiz;
	@Autowired
	private AppealBiz appealBiz;
	@Autowired
	private GoodsOrderBiz orderBiz;
	@Autowired
	private FileManagerBiz fileManagerBiz;
	@Autowired
	private CategoryBiz categoryBiz;
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
		//得到消息类别,选择(交易信息),固定,类别名称唯一,id自增可能会存在不确定因素
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
	 * 跳转至新增招标
	 * @author Xiadi
	 * @since 2013-9-29 
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/toAdd")
	public String toAddBidding(HttpServletRequest request, HttpServletResponse response){
		return "ucenter/bidding_add";
	}
	
	/**
	 * 跳转至待我处理
	 * @author Xiadi
	 * @since 2013-10-8 
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/toDeallist")
	public String toDealList(HttpServletRequest request, HttpServletResponse response){
		return "ucenter/bidding_deallist";
	}
	
	/**
	 * 跳转至我的招标
	 * @author Xiadi
	 * @since 2013-10-8 
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/toBiddingList")
	public String toBiddingList(HttpServletRequest request, HttpServletResponse response){
		return "ucenter/bidding_list";
	}
	
	/**
	 * 跳转至招标详情
	 * @author Xiadi
	 * @since 2013-10-8 
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/toDetail")
	public String toDetail(Integer bid, HttpServletRequest request, HttpServletResponse response){
		BiddingService bs = biddingServiceBiz.getBiddingServiceById(bid);
		request.setAttribute("bidding", bs);
		if(bs.getRid() != null)
			request.setAttribute("responseBidding", responseBiddingBiz.getResponseBiddingById(bs.getRid()));
		return "ucenter/bidding_detail";
	}
	
	/**
	 * 跳转编辑需求
	 * @author Xiadi
	 * @since 2013-10-8 
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/toEdit")
	public String toEdit(Integer bid, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("bidding", biddingServiceBiz.getBiddingServiceById(bid));
		return "ucenter/bidding_edit";
	}
	
	/**
	 * 跳转至 买家确认应标
	 * @author Xiadi
	 * @since 2013-10-9 
	 *
	 * @param bid
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/toSelect")
	public String toSelect(Integer bid, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("bidding", biddingServiceBiz.getBiddingServiceById(bid));
		return "ucenter/bidding_select";
	}
	
	/**
	 * 新增招标
	 * @author Xiadi
	 * @since 2013-9-29 
	 *
	 * @param biddingService
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/add")
	public void add(BiddingService biddingService, HttpServletRequest request, 
			HttpServletResponse response){
		try {
			Category category = categoryBiz.findById("service", biddingService.getCategory().getId());
			biddingService.setBidNo("D"+category.getCode()+"-"+Long.toString(new Date().getTime()));
			biddingServiceBiz.addOrModify(biddingService);
			BiddingServiceDetail biddingservicedetail = null; 
			if(super.getCurrentUserType(request) == Constant.LOGIN_STAFF){
				Staff staff = (Staff)request.getSession().getAttribute("user");
				biddingservicedetail = new BiddingServiceDetail(biddingService, Constant.BIDDING_NEW, null,
						staff, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_SAVE_BIDDING_SERVICE);
			}else if(super.getCurrentUserType(request) == Constant.LOGIN_USER){
				User user = (User)request.getSession().getAttribute("user");
				biddingservicedetail = new BiddingServiceDetail(biddingService, Constant.BIDDING_NEW, 
						user, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_SAVE_BIDDING_SERVICE);
			}
			biddingServiceDetailBiz.add(biddingservicedetail);
			// 关联文件
			String attachment = biddingService.getAttachment();
			if(attachment != null && !attachment.isEmpty()){
				String[] fileNames = attachment.split(",");
				for(String name : fileNames){
					fileManagerBiz.addRelateClazz(name, "BiddingService");
				}
			}
			super.outJson(response, new JSONResult(true, "新增招标成功"));
		} catch (Exception e) {
			logger.info("新增招标失败!异常信息:"+e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "新增招标失败"));
		}
	}
	
	/**
	 * 编辑需求
	 * @author Xiadi
	 * @since 2013-9-29 
	 *
	 * @param biddingService
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/edit")
	public void edit(BiddingService biddingService, HttpServletRequest request, 
			HttpServletResponse response){
		try {
			biddingServiceBiz.addOrModify(biddingService);
			super.outJson(response, new JSONResult(true, "修改招标成功"));
		} catch (Exception e) {
			logger.info("修改招标失败!异常信息:"+e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "修改招标失败"));
		}
	}
	
	/**
	 * 待我处理
	 * @author Xiadi
	 * @since 2013-9-29 
	 *
	 * @param request
	 * @param response
	 * @param categoryId 服务类别
	 * @param name 服务名称
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param status 招标状态  status == “” 则 查询 待发布、平台退回、招标中
	 * @param page
	 * @param rows
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/getDeallist")
	public void getDealList(HttpServletRequest request, HttpServletResponse response,
			Integer categoryId, String name, String beginTime, String endTime, String status, 
			@RequestParam(defaultValue = "0", required = true)Integer page, 
			@RequestParam(defaultValue = "20", required = true)Integer rows){
		int id = 0;
		if(super.getCurrentUserType(request) == Constant.LOGIN_STAFF){
			Staff staff = (Staff)request.getSession().getAttribute("user");
			id = staff.getParent().getId();
		}else if(super.getCurrentUserType(request) == Constant.LOGIN_USER){
			User user = (User)request.getSession().getAttribute("user");
			id = user.getId();
		}
		if(status == null || status.isEmpty()){
			status = Constant.BIDDING_NEW + ","
					+ Constant.BIDDING_PLAT_REJECT + ","
					+ Constant.BIDDING_DOING;
		}
		JSONRows<BiddingService> jr = biddingServiceBiz.findBiddingService(id, categoryId, name, 
				beginTime, endTime, status, rows * (page - 1), rows);
		this.outJson(response, jr);
	}
	
	/**
	 * 我的招标
	 * @author Xiadi
	 * @since 2013-9-29 
	 *
	 * @param request
	 * @param response
	 * @param categoryId 服务类别
	 * @param name 服务名称
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param status 招标状态  status == “” 则 查询 所有
	 * @param page
	 * @param rows
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/getBiddingList")
	public void getBiddingList(HttpServletRequest request, HttpServletResponse response,
			Integer categoryId, String name, String beginTime, String endTime, String status, 
			@RequestParam(defaultValue = "0", required = true)Integer page, 
			@RequestParam(defaultValue = "20", required = true)Integer rows){
		int id = 0;
		if(super.getCurrentUserType(request) == Constant.LOGIN_STAFF){
			Staff staff = (Staff)request.getSession().getAttribute("user");
			id = staff.getParent().getId();
		}else if(super.getCurrentUserType(request) == Constant.LOGIN_USER){
			User user = (User)request.getSession().getAttribute("user");
			id = user.getId();
		}
		JSONRows<BiddingService> jr = biddingServiceBiz.findBiddingService(id,
				categoryId, name, beginTime, endTime, status, rows * (page - 1), rows);
		this.outJson(response, jr);
	}
	
	/**
	 * 操作日志
	 * @author Xiadi
	 * @since 2013-10-9 
	 *
	 * @param bid
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/getBiddingLog")
	public void getBiddingLog(Integer bid, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "0", required = true)Integer page, 
			@RequestParam(defaultValue = "20", required = true)Integer rows){
		JSONRows<BiddingServiceDetail> jr = biddingServiceDetailBiz.findBiddingDetailByBid(bid, rows * (page - 1), rows);
		this.outJson(response, jr);
	}
	
	/**
	 * 发布或取消
	 * @author Xiadi
	 * @since 2013-10-9 
	 *
	 * @param bid
	 * @param status 
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/updateIssueOrChannl")
	public void updateIssueOrChannl(Integer bid, Integer status, 
			HttpServletRequest request, HttpServletResponse response){
		try {
			biddingServiceBiz.updateIssueOrCancel(bid, status);
			BiddingService biddingService = biddingServiceBiz.getBiddingServiceById(bid);
			BiddingServiceDetail biddingservicedetail = null;
			// 保存流水
			if(super.getCurrentUserType(request) == Constant.LOGIN_STAFF){
				Staff staff = (Staff)request.getSession().getAttribute("user");
				if(status == Constant.BIDDING_ADDED_AUDIT){
					biddingservicedetail = new BiddingServiceDetail(biddingService, Constant.BIDDING_ADDED_AUDIT, null,
							staff, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_ISSUE_BIDDING_SERVICE);
				}else if(status == Constant.BIDDING_CANCEL){
					biddingservicedetail = new BiddingServiceDetail(biddingService, Constant.BIDDING_CANCEL, null,
							staff, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_CANCEL_BIDDING);
				}
			}else if(super.getCurrentUserType(request) == Constant.LOGIN_USER){
				User user = (User)request.getSession().getAttribute("user");
				if(status == Constant.BIDDING_ADDED_AUDIT){
					biddingservicedetail = new BiddingServiceDetail(biddingService, Constant.BIDDING_ADDED_AUDIT, 
							user, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_ISSUE_BIDDING_SERVICE);
				}else if(status == Constant.BIDDING_CANCEL){
					biddingservicedetail = new BiddingServiceDetail(biddingService, Constant.BIDDING_CANCEL, 
							user, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_CANCEL_BIDDING);
				}
			}
			biddingServiceDetailBiz.add(biddingservicedetail);
			super.outJson(response, new JSONResult(true, "操作成功"));
			if(status == Constant.BIDDING_CANCEL){//取消招标
				//买家取消招标，发送消息给卖家（所有应标方）
				Message message = getTransactionMessage();
				//得到所有应标该服务的应标方
				List<ResponseBidding> rbList = responseBiddingBiz.findResponseBiddingByBid(bid, Constant.RESPONSE_DOING);
				if(rbList.size() > 0){
					for (ResponseBidding responseBidding : rbList) {
						sendTransactionMessage(Constant.LOGIN_USER, message,responseBidding.getUser().getId(),
								responseBidding.getUser().getUserName(), true, biddingService.getBidNo(), 12);
					}
				}
			}
		} catch (Exception e) {
			logger.info("修改招标状态失败!异常信息:"+e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "操作失败"));
		}
	}
	
	/**
	 * 买家确认应标——应标列表
	 * @author Xiadi
	 * @since 2013-10-9 
	 *
	 * @param bid
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/getResponseBidding")
	public void getResponseBidding(Integer bid, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "0", required = true)Integer page, 
			@RequestParam(defaultValue = "20", required = true)Integer rows){
		JSONRows<ResponseBidding> rj = responseBiddingBiz.
				findResponseBiddingByBid(bid, Constant.RESPONSE_DOING, rows * (page - 1), rows);
		super.outJson(response, rj);
	}
	
	/**
	 * 选择服务
	 * @author Xiadi
	 * @since 2013-10-10 
	 *
	 * @param bid 招标id
	 * @param rid 应标id
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/selectResponse")
	public void selectResponse(Integer bid, Integer rid, HttpServletRequest request, HttpServletResponse response){
		try {
			BiddingService bs = biddingServiceBiz.findBiddingServiceById(bid);
			ResponseBidding rb = responseBiddingBiz.getResponseBiddingById(rid);
			biddingServiceBiz.addSelectResponse(bs, rb);
			// 保存流水
			BiddingServiceDetail biddingservicedetail = null;
			// 生成订单 (2013-10-14讨论之后结果)
			GoodsOrder go = null;
			if(super.getCurrentUserType(request) == Constant.LOGIN_STAFF){
				Staff staff = (Staff)request.getSession().getAttribute("user");
				biddingservicedetail = new BiddingServiceDetail(bs, Constant.BIDDING_TRADE_ING, null,
						staff, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_CHOOSE_RESPONSE_BIDDING);
				go = new GoodsOrder("D" + StrUtils.formateDate("YYYYMMdd", new Date())+Common.random(),
						Constant.TRANSACTIONS_IN, rb.getBidPrice(), bs.getLinkMan(), bs.getTel(), bs.getEmail(), "", 
						StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()), staff, staff.getParent().getEnterprise().getId(),
						rb.getUser() == null ? rb.getStaff().getParent().getEnterprise().getId() : rb.getUser().getEnterprise().getId(),
						bs.getName(),bs, Constant.ORDER_SOURCE_B);
			}else if(super.getCurrentUserType(request) == Constant.LOGIN_USER){
				User user = (User)request.getSession().getAttribute("user");
				biddingservicedetail = new BiddingServiceDetail(bs, Constant.BIDDING_TRADE_ING, 
						user, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()), Constant.ACTION_CHOOSE_RESPONSE_BIDDING);
				go = new GoodsOrder("D" + StrUtils.formateDate("YYYYMMdd", new Date())+Common.random(),
						Constant.TRANSACTIONS_IN, rb.getBidPrice(), bs.getLinkMan(), bs.getTel(), bs.getEmail(), "", 
						StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()), user, user.getEnterprise().getId(), 
						rb.getUser() == null ? rb.getStaff().getParent().getEnterprise().getId() : rb.getUser().getEnterprise().getId(),
						bs.getName(),bs, Constant.ORDER_SOURCE_B);
			}
			biddingServiceDetailBiz.add(biddingservicedetail);
			orderBiz.saveGoodsOrder(go);
			super.outJson(response, new JSONResult(true, "选择服务成功"));
			//买家选择应标放，发送消息给卖家（应标方）
			Message message = getTransactionMessage();
			sendTransactionMessage(Constant.LOGIN_USER, message,rb.getUser().getId(),
				rb.getUser().getUserName(), true, bs.getBidNo(), 11);
		} catch (Exception e) {
			logger.info("选择服务失败!异常信息:"+e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "选择服务失败"));
		}
	}
	
	/**
	 * 混合条件查询招标单(支撑运营平台-招标管理)
	 * @author xuwf
	 * @since 2013-10-08
	 * 
	 * @param name			服务名称
	 * @param status		招标状态
	 * @param startTime		创建时间
	 * @param endTime		结束时间
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/query")
	public void find(@RequestParam(value="bidNo",required=false)String bidNo,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="status",required=false)String status,
			@RequestParam(value="startTime",required=false)String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="20", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[ status:"+status+",bidNo:"+bidNo
				+",startTime:"+startTime+",endTime:"+endTime+"]");
		if(null == status){
			status = "";
		}
		JSONData<BiddingService> jdbs = biddingServiceBiz.queryBidding(bidNo, name,status, 
				startTime, endTime, start, limit);
		this.outJson(response, jdbs);
	}
	

	/**
	 * 审核处理 - 支撑运营平台
	 *@author xuwf
	 *@since 2013-10-08
	 *
	 *@param 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/biddingAudit")
	public void biddingAudit(Integer id,Integer status,
			String remark,HttpServletRequest request,HttpServletResponse response) {
			logger.info("remark:"+remark+"status:"+status+",id:"+id);
			//得到招标单
			BiddingService biddingService = biddingServiceBiz.findBiddingServiceById(id);
			
			//平台人员处理招标单
			Manager manager = (Manager) request.getSession().getAttribute("manager");
			BiddingServiceDetail biddingservicedetail = null;
			try{
				if(status == Constant.BIDDING_DOING){//通过审核状态为： 4招标中
					biddingService.setStatus(status);
					biddingServiceBiz.addOrModify(biddingService);
					
					biddingservicedetail = new BiddingServiceDetail(biddingService,status, remark, manager,
						StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),Constant.ACTION_BIDDING_AUDIT_SUCCESS);
				}else if(status == Constant.BIDDING_PLAT_REJECT){//驳回审核状态为：3平台退回
					biddingService.setStatus(status);
					biddingServiceBiz.addOrModify(biddingService);
					
					biddingservicedetail = new BiddingServiceDetail(biddingService,status, remark, manager,
						StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),Constant.ACTION_BIDDING_AUDIT_FAILED);
				}
				biddingServiceDetailBiz.add(biddingservicedetail);
				logger.info("[ "+biddingService.getBidNo()+" ]审核成功!");
				this.outJson(response,new JSONResult(true,"招标单[" + biddingService.getBidNo() + "]审核成功"));
				//招标审核后发送给招标方审核消息
				Message message = getTransactionMessage();
				if(status == Constant.BIDDING_DOING){//通过审核状态为： 4招标中
					sendTransactionMessage(Constant.LOGIN_USER, message,biddingService.getUser().getId(),
						biddingService.getUser().getUserName(), true, biddingService.getBidNo(), 9);
				}else if(status == Constant.BIDDING_PLAT_REJECT){//驳回审核状态为：3平台退回
					sendTransactionMessage(Constant.LOGIN_USER, message,biddingService.getUser().getId(),
							biddingService.getUser().getUserName(), true, biddingService.getBidNo(), 10);
				}
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"招标单[" + biddingService.getBidNo() + "]审核失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("招标单审核失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	
	/**
	 * 取消招标 - 支撑运营平台
	 *@author xuwf
	 *@since 2013-10-09
	 *
	 *@param 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/handlerBidding")
	public void handlerBidding(Integer id,Integer status,
			String remark,HttpServletRequest request,HttpServletResponse response) {
			logger.info("remark:"+remark+"status:"+status+",id:"+id);
			//得到招标单
			BiddingService biddingService = biddingServiceBiz.findBiddingServiceById(id);
			
			//平台人员处理招标单
			Manager manager = (Manager) request.getSession().getAttribute("manager");
			BiddingServiceDetail biddingservicedetail = null;
			try{
				biddingService.setStatus(status);
				biddingServiceBiz.addOrModify(biddingService);
					
				biddingservicedetail = new BiddingServiceDetail(biddingService,status, remark, manager,
					StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),Constant.ACTION_PLAT_CANCEL_BIDDING);
				biddingServiceDetailBiz.add(biddingservicedetail);
					
				logger.info("[ "+biddingService.getBidNo()+" ]取消招标成功!");
				this.outJson(response,new JSONResult(true,"招标单[" + biddingService.getBidNo() + "]取消招标成功"));
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"招标单[" + biddingService.getBidNo() + "]处理失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("招标单处理失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	
	/**
	 * 申诉处理招标单 - 支撑运营平台
	 *@author xuwf
	 *@since 2013-10-12
	 *
	 *@param 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/appealHandlerBidding")
	public void appealHandlerBidding(Integer id,Integer status,
			String remark,HttpServletRequest request,HttpServletResponse response) {
			logger.info("remark:"+remark+"status:"+status+",id:"+id);
			//得到招标单
			BiddingService biddingService = biddingServiceBiz.findBiddingServiceById(id);
			//得到申诉单
			Appeal appeal = appealBiz.queryByBidId(id);
			//平台人员处理招标单
			Manager manager = (Manager) request.getSession().getAttribute("manager");
			BiddingServiceDetail biddingservicedetail = null;
			try{
				if(status == Constant.BIDDING_TRADE_END){//关闭交易后状态为： 10交易结束				
					biddingService.setStatus(status);
					biddingServiceBiz.addOrModify(biddingService);
					
					biddingservicedetail = new BiddingServiceDetail(biddingService,status, remark, manager,
						StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),Constant.ACTION_CLOSE_APPEAL_BIDDING);
					biddingServiceDetailBiz.add(biddingservicedetail);
					
					//完善申诉单信息
					appeal.setAppealStatus(Constant.APPEAL_STATUS＿CLOSE_DEAL);
					appeal.setManager(manager);
					appeal.setProcessMode(Constant.HANDLER_MODE_CLOSE);
					appeal.setProcessTime(StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()));
					appeal.setHandlerRemark(remark);
					appealBiz.update(appeal);
					
					logger.info("[ "+biddingService.getBidNo()+" ]关闭交易成功!");
					this.outJson(response,new JSONResult(true,"招标单[" + biddingService.getBidNo() + "]关闭交易成功"));
				}else if(status == Constant.BIDDING_CANCEL){//取消招标后状态为：11招标取消
					biddingService.setStatus(status);
					biddingServiceBiz.addOrModify(biddingService);
					
					biddingservicedetail = new BiddingServiceDetail(biddingService,status, remark, manager,
						StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),Constant.ACTION_CANCEL_APPEAL_BIDDING);
					biddingServiceDetailBiz.add(biddingservicedetail);
					
					//完善申诉单信息
					appeal.setAppealStatus(Constant.APPEAL_STATUS_ORDER_CANCEL);
					appeal.setManager(manager);
					appeal.setProcessMode(Constant.HANDLER_MODE_CANCEL);
					appeal.setProcessTime(StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()));
					appeal.setHandlerRemark(remark);
					appealBiz.update(appeal);
					
					logger.info("[ "+biddingService.getBidNo()+" ]取消招标成功!");
					this.outJson(response,new JSONResult(true,"招标单[" + biddingService.getBidNo() + "]取消招标成功"));
				}
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"招标单[" + biddingService.getBidNo() + "]处理失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("招标单处理失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	
	/**
	 * 根据招单id查看该招单的应标信息-支撑平台
	 * @author xuwf
	 * @since 2013-10-09
	 * 
	 * @param bidId		招单id
	 * @param request		
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findResponseBidding")
	public void findResponseBidding(
			@RequestParam(value="bidId",required=false)Integer bidId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="100", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[ bidId:"+bidId+",status:"+status+"]");
		
		JSONData<ResponseBidding> jdrb = responseBiddingBiz.getByBidService(bidId, status,start,limit);
		this.outJson(response, jdrb);
	}
	
	/**
	 * 根据招单id查看该招单的申诉信息-支撑平台
	 * @author xuwf
	 * @since 2013-10-12
	 * 
	 * @param bidId		招单id
	 * @param request		
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findBiddingAppeal")
	public void findBiddingAppeal(
			@RequestParam(value="bidId",required=false)Integer bidId,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="100", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[ bidId:"+bidId+"]");
		
		JSONData<Appeal> jdappeal = appealBiz.queryByBidId(bidId, start, limit);
		this.outJson(response, jdappeal);
	}
	
	/**
	 * 根据招单id查看该招单的流水信息-支撑平台
	 * @author xuwf
	 * @since 2013-10-09
	 * 
	 * @param bidId		招单id
	 * @param request		
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findBiddingServiceDetail")
	public void findBiddingServiceDetail(
			@RequestParam(value="bidId",required=false)Integer bidId,
			@RequestParam(value="start", defaultValue="0", required=false)int start,
			@RequestParam(value="limit", defaultValue="100", required=false)int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[ bidId:"+bidId+"]");
		
		JSONData<BiddingServiceDetail> jdbsd = biddingServiceDetailBiz.getByBidId(bidId, start, limit);
		this.outJson(response, jdbsd);
	}
	
	/**
	 * 给我推送
	 * 业务逻辑：排除（该服务机构发出的招标以及已经应过的招标）之后且在该服务机构的服务领域范围内的所有招标
	 * @author pangyf
	 * @since 2013-9-29
	 * @param id
	 * @param category
	 * @param name
	 * @param begintime
	 * @param endtime
	 * @param status
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/queryBidding")
	public void queryBiddingService(
			@RequestParam(value="uid",required=false)Integer uid,
			@RequestParam(value="categoryId",required=false)Integer categoryId,
			@RequestParam(value="name",required=false,defaultValue="")String name,				
			@RequestParam(value="page",required=false)int page,
			@RequestParam(value="rows",required=false)int rows,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("id = " + uid +",category = "+ categoryId + ",name = "+ name);
		try{
			if(this.getCurrentUserType(request)==Constant.LOGIN_USER){
				User user = this.getUserFromSession(request);
				request.getSession().setAttribute("user",userBiz.findUserById(user.getId()));
			}else {
				Staff staff = this.getStaffFromSession(request);
				request.getSession().setAttribute("user",staffBiz.getStaff(staff.getId()));
				uid = staff.getParent().getId();
			}
			String status = String.valueOf(Constant.BIDDING_DOING);
			this.outJson(response, biddingServiceBiz.findBiddingList(uid, categoryId,name, null, null, status, rows * (page - 1), rows));					
		}catch(Exception e){
			this.outJson(response,new JSONResult(false,"异常信息:"+e.getLocalizedMessage()));
			logger.info("异常信息:"+e.getLocalizedMessage());
		}
		
	};
	
	/**
	 * 前台应标方提交应标
	 *  
	 * @author pangyf
	 * @since 2013-10-8
	 * @param rb ResponseBidding对象
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="/submitResponse")
	public void submitResponse(ResponseBidding rb, HttpServletRequest request, 
			HttpServletResponse response){
		logger.info("ResponseBidding = " + rb.toString());		
		try{
			rb.setStatus(Constant.RESPONSE_DOING);
			BiddingService bs = biddingServiceBiz.findBiddingServiceById(rb.getBiddingService().getId());
			responseBiddingBiz.addOrModify(rb);
			if(this.getCurrentUserType(request)==Constant.LOGIN_USER){
				User user = this.getUserFromSession(request);
				request.getSession().setAttribute("user",userBiz.findUserById(user.getId()));
				BiddingServiceDetail biddingservicedetail = new BiddingServiceDetail(bs,bs.getStatus(), user, StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),Constant.ACTION_APPLY_BIDDING_SERVICE);
				biddingServiceDetailBiz.add(biddingservicedetail);
			}else {
				Staff staff = this.getStaffFromSession(request);
				request.getSession().setAttribute("user",staffBiz.getStaff(staff.getId()));
				BiddingServiceDetail biddingservicedetail = new BiddingServiceDetail(bs,bs.getStatus(), staff,StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()),Constant.ACTION_APPLY_BIDDING_SERVICE);
				biddingServiceDetailBiz.add(biddingservicedetail);
			}
			// 关联文件
			String attachment = rb.getAttachment();
			if(attachment != null && !attachment.isEmpty()){
				String[] fileNames = attachment.split(",");
				for(String name : fileNames){
					fileManagerBiz.addRelateClazz(name, "ResponseBidding");
				}
			} 
			this.outJson(response,new JSONResult(true,"恭喜您，应标成功，您是第"+ responseBiddingBiz.countResponseBiddingByBid(bs.getId()) +"位成功者"));					
		}catch(Exception e){
			this.outJson(response,new JSONResult(false,"应标失败，谢谢!异常信息:"+e.getLocalizedMessage()));
			logger.info("应标失败，谢谢!异常信息:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 我的应标
	 * 业务逻辑：所有该服务机构应过的招标
	 * @author pangyf
	 * @since 2013-9-29
	 * @param id
	 * @param category
	 * @param name
	 * @param begintime
	 * @param endtime
	 * @param status
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/queryResponse")
	public void queryResponse(
			@RequestParam(value="uid")Integer uid,
			@RequestParam(value="bCategoryId",required=false)Integer bCategoryId,
			@RequestParam(value="bName",required=false,defaultValue="")String bName,
			@RequestParam(value="begintime",required=false)String begintime,
			@RequestParam(value="endtime",required=false)String endtime,
			@RequestParam(value="bStatus",required=false)String bStatus,
			@RequestParam(value="page",required=false)int page,
			@RequestParam(value="rows",required=false)int rows,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("id = " + uid +",bCategory = "+ bCategoryId + ",bName = "+ bName + ",begintime = " + begintime +",endtime = "+ endtime + ",bStatus = " + bStatus);
		try{			
			if(this.getCurrentUserType(request)==Constant.LOGIN_USER){
				User user = this.getUserFromSession(request);
				request.getSession().setAttribute("user",userBiz.findUserById(user.getId()));
			}else {
				Staff staff = this.getStaffFromSession(request);
				request.getSession().setAttribute("user",staffBiz.getStaff(staff.getId()));
				uid = staff.getParent().getId();
			}
			this.outJson(response, responseBiddingBiz.findResponseList(uid, bCategoryId,bName, begintime, endtime, bStatus, rows * (page - 1), rows));					
		}catch(Exception e){
			this.outJson(response,new JSONResult(false,"异常信息:"+e.getLocalizedMessage()));
			logger.info("异常信息:"+e.getLocalizedMessage());
		}
		
	};
	

}
