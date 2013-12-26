package com.eaglec.plat.biz.impl.app.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.eaglec.plat.biz.app.mobile.MobileAppBiz;
import com.eaglec.plat.biz.business.GoodsOrderBiz;
import com.eaglec.plat.biz.business.OrderInfoBiz;
import com.eaglec.plat.biz.business.OrderOperateLogBiz;
import com.eaglec.plat.biz.flat.FlatBiz;
import com.eaglec.plat.biz.info.MessageBiz;
import com.eaglec.plat.biz.info.MessageClassBiz;
import com.eaglec.plat.biz.info.ReceiverMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderGroupMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderMessageRelationshipBiz;
import com.eaglec.plat.biz.policy.PolicyBiz;
import com.eaglec.plat.biz.policy.PolicyCategoryBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.service.ServiceConsumerBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.EnterpriseCreditBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.base.EnterpriseCredit;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.domain.business.OrderInfo;
import com.eaglec.plat.domain.business.OrderOperateLog;
import com.eaglec.plat.domain.info.Message;
import com.eaglec.plat.domain.info.MessageClass;
import com.eaglec.plat.domain.info.ReceiverMessageRelationship;
import com.eaglec.plat.domain.info.SenderGroupMessageRelationship;
import com.eaglec.plat.domain.info.SenderMessageRelationship;
import com.eaglec.plat.domain.policy.PolicyCategory;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.domain.service.ServiceConsumer;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.bean.business.GoodsOrderSyncBean;
import com.eaglec.plat.sync.bean.business.OrderInfoSyncBean;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.Dao;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONResult;

@org.springframework.stereotype.Service
public class MobileAppBizImpl implements MobileAppBiz {
	private static final Logger logger = LoggerFactory.getLogger(MobileAppBizImpl.class);
	
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private EnterpriseCreditBiz ecBiz;
	@Autowired
	private StaffBiz staffBiz;
	@Autowired
	private ReceiverMessageRelationshipBiz receiverMessageRelationshipBiz;
	@Autowired
	private GoodsOrderBiz goodsOrderBiz;
	@Autowired
	private ServiceBiz serviceBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private PolicyBiz policyBiz;
	@Autowired
	private PolicyCategoryBiz policyCategoryBiz;
	@Autowired
	private FlatBiz flatBiz;
	@Autowired
	private ServiceConsumerBiz serviceConsumerBiz;
	@Autowired
	private GoodsOrderBiz orderBiz;
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	@Autowired
	private OrderOperateLogBiz orderOperLogBiz;
	@Autowired
	private MessageClassBiz messageClassBiz;
	@Autowired
	private MessageBiz messageBiz;
	@Autowired
	private Dao dao;
	@Autowired
	private SenderGroupMessageRelationshipBiz senderGroupMessageRelationshipBiz;
	@Autowired
	private SenderMessageRelationshipBiz senderMessageRelationshipBiz;
	
	
	@Override
	public Map<String, Object> getServices(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>(); // 返回结果
		Object cid = jsonObject.get("reqData"); // 服务大类id
		if ((cid != null) && (StringUtils.isNumeric(cid.toString()))) {
			result.put("resData", serviceBiz.findServiceByPCid((Integer)cid));
			result.put("statusCode", 0); 
		} else {
			result.put("statusCode", 1); // 验证码输入错误
			result.put("resData", "参数有误");
		}
		return result;
	}

	@Override
	public Map<String, Object> getServiceDetail(JSONObject jsonObject) {
		Map<String, Object> result = new HashMap<String, Object>();	//返回结果
		Object serviceId = jsonObject.get("reqData");
		if((serviceId != null) && (serviceId instanceof Integer)) {
			List<?> list = serviceBiz.findServiceView((Integer)serviceId);
			if(list.size() > 0) {
				result.put("resData", list.get(0));
				result.put("statusCode", 0);
			} else {
				result.put("resData", null);
				result.put("statusCode", 0);
			}
		} else {
			result.put("statusCode", 1); // 参数有误
			result.put("resData", "参数有误");
		}
		return result;
	}

	@Override
	public Map<String, Object> addServiceConsumer(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		ServiceConsumer sc = null;
		try {
			Object reqData = jsonObject.get("reqData");
			sc = JSONObject.parseObject(reqData.toString(), ServiceConsumer.class);
		} catch (Exception e) {
			result.put("statusCode", 1);
			result.put("resData", "请求参数有误");
			return result;
		}
		serviceConsumerBiz.save(sc);
		result.put("statusCode", 0);
		result.put("resData", "");
		return result;
	}
	
	/**
	 * 平台APP服务申请功能
	 * @author liuliping
	 * @since 2013-12-23
	 * @param jsonObject 内含(服务id,linkMan联系人,tel电话,email邮箱,authStr检验码)
	 * @param request
	 * @param response
	 */
	public Map<String, Object> saveApplyService(JSONObject jsonObject) {
		Map<String, Object> result = new HashMap<String, Object>(); // 返回结果
		JSONObject reqData = jsonObject.getJSONObject("reqData");
		Integer id = reqData.getInteger("id");    // 服务id
		String authStr = reqData.getString("authStr");    // 用户authStr
		if(!StringUtils.isEmpty(authStr)) {
			String[] array = authStr.split("\\|"); // auth的格式为"用户类型|时间戳|用户编码"
			if(array.length == 3) { // auth授权码格式正确
				Service service = serviceBiz.findServiceById(id);	//申请的服务
				GoodsOrder order = null;					//申请服务产生一张订单
				OrderInfo orderInfo = null;					//订单流水
				OrderOperateLog orderOperateLog = null;		//订单操作日志
				String tel = reqData.getString("tel");	//电话
				String linkMan = reqData.getString("linkMan");	//联系人
				String email = reqData.getString("email");	//邮箱
				String remark = reqData.getString("remark");    //备注
				//订单编号的生成规则
				String orderNumber = "S"+StrUtils.formateDate("YYYYMMdd", new Date())+Common.random();
				//当前时间作为订单下单时间
				String currentTime = StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date());
				 // auth授权码格式正确
				if ("u".equals(array[0])) { // auth授权码内含的是主帐号用户
					User user = userBiz.findUserByUidAndModifyTime(array[2],
							array[1]); // 根据用户编码和时间戳查找用户
					if (user != null) { // 授权码正确
						if (user.getUserStatus() == Constant.EFFECTIVE) {
							order = new GoodsOrder(orderNumber, Constant.WAIT_SELLER_CONFIRM, 
									service.getCostPrice(), linkMan, tel, email, remark, currentTime, user, 
									user.getEnterprise().getId(), service.getEnterprise().getId(),
									service.getServiceName(), service, Constant.ORDER_SOURCE_S);
								
							//添加订单详细信息
							orderInfo = new OrderInfo(order, order.getOrderStatus(), 
									remark,user,currentTime,Constant.ACTION_BUYER_ORDER_SUBMIT);
							try {
								orderBiz.saveGoodsOrder(order);
								orderInfoBiz.saveOrderInfo(orderInfo);
								logger.info("通过平台app添加订单详细信息");	
								
								// 同步至窗口
								SyncFactory.executeTask(new SaveOrUpdateToWinImpl<GoodsOrder>(new GoodsOrderSyncBean(order, SyncType.ONE)),true);
								SyncFactory.executeTask(new SaveOrUpdateToWinImpl<OrderInfo>(new OrderInfoSyncBean(orderInfo, SyncType.ONE)));
								
								//添加订单操作日志
								orderOperateLog = new OrderOperateLog(order.getOrderNumber(),
										user.getUserName(),Constant.WAIT_SELLER_CONFIRM_STR,
										StrUtils.formateDate("YYYY-MM-dd HH:mm:ss", new Date()));
								
								orderOperLogBiz.saveOrderOperLog(orderOperateLog);
								logger.info("添加订单操作日志");
								logger.info("[ "+order.getOrderNumber()+" ]添加成功!");
								
								//申请订单,给卖家发送消息
								User seller = userBiz.findUserByEnterprise(order.getSeller_id());
								Message message = getTransactionMessage();
								sendTransactionMessage(Constant.LOGIN_USER, message,seller.getId(), seller.getUserName(), true, orderNumber, 1);
								
								result.put("statusCode", 0); // 成功
							} catch (Exception e) {
								e.printStackTrace();
								logger.info("申请服务失败!异常信息:" + e.getLocalizedMessage());
								result.put("statusCode", 1); 
								result.put("resData", "系统发生异常");
							}
						} else if (user.getUserStatus() == Constant.DISABLED) {
							result.put("statusCode", 1); // 帐号被禁用
							result.put("resData", "帐号被禁用");
						} else {
							result.put("statusCode", 1); // 账号被删除
							result.put("resData", "账号被删除");
						}
					} else { // 授权码已失效
						result.put("statusCode", 1);
						result.put("resData", "授权码已失效");
					}
				} else if ("s".equals(array[0])) { // auth授权码内含的是子帐号用户
					Staff staff = staffBiz.findByCodeAndStatus(array[2],
							array[1]);
					if (staff != null) { // 授权码正确
						if (staff.getStaffStatus() == Constant.EFFECTIVE) {
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
								try {
									orderBiz.saveGoodsOrder(order);
									orderInfoBiz.saveOrderInfo(orderInfo);
									orderOperLogBiz.saveOrderOperLog(orderOperateLog);
									logger.info("添加订单操作日志");
									logger.info("[ "+order.getOrderNumber()+" ]添加成功!");
									
									//申请订单,给卖家发送消息
									User seller = userBiz.findUserByEnterprise(order.getSeller_id());
									Message message = getTransactionMessage();
									sendTransactionMessage(Constant.LOGIN_USER, message,seller.getId(), seller.getUserName(), true, orderNumber, 1);
									
									result.put("statusCode", 0); // 成功
								} catch (Exception e) {
									e.printStackTrace();
									logger.info("申请服务失败!异常信息:" + e.getLocalizedMessage());
									result.put("statusCode", 1); 
									result.put("resData", "系统发生异常");
								}
							}else{
								result.put("statusCode", 1); // 子账号无权限
								result.put("resData", "帐号被禁用");
							}
						} else if (staff.getStaffStatus() == Constant.DISABLED) {
							result.put("statusCode", 1); // 帐号被禁用
							result.put("resData", "帐号被禁用");
						} else {
							result.put("statusCode", 1); // 账号被删除
							result.put("resData", "账号被删除");
						}
					} else {
						result.put("statusCode", 1);
						result.put("resData", "授权码错误,请重新登录");
					}
				} else { // auth授权码格式错误
					result.put("statusCode", 1);
					result.put("resData", "授权码错误,请重新登录");
				}
			} else {
				result.put("statusCode", 1);
				result.put("resData", "授权码错误,请重新登录");
			}
		} else {    // 验证码为空
			result.put("statusCode", 1);
			result.put("resData", "请重新登录");
		}
		return result;
	}

	@Override
	public Map<String, Object> getEnterprises() {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>(); // 返回结果
		result.put("resData", enterpriseBiz.getEnterprisesOfAllCategories());
		result.put("statusCode", 0); 
		return result;
	}

	@Override
	public Map<String, Object> getRules() {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> resData = new ArrayList<Map<String, Object>>();
		List<PolicyCategory> categories = policyCategoryBiz.findAll(null);	//政策类别
		for(int i = 0; i < categories.size(); i++) {
			PolicyCategory pc = categories.get(i);
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", pc.getId());
			item.put("text", pc.getText());
			item.put("policys", policyBiz.findByPCid(pc.getId()));			//根据政策大类查找政策信息
			resData.add(item);
		}
		result.put("resData", resData);
		result.put("statusCode", 0);
		return result;
	}

	@Override
	public Map<String, Object> getWindows() {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resData", flatBiz.findFlatView());
		result.put("statusCode", 0);
		return result;
	}

	@Override
	public Map<String, Object> useLogin(JSONObject json,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>(); // 返回结果
		Map<String, Object> resData = new HashMap<String, Object>();
		// 1.获取username和password
		Object obj1 = json.getJSONObject("reqData").get("username");
		Object obj2 = json.getJSONObject("reqData").get("password");
		String username = (obj1 == null) ? null : (String) obj1;
		String password = (obj2 == null) ? null : (String) obj2;
		// 2.校验
		if ((!"".equals(username)) && (!"".equals(password))) {
			List<User> list = userBiz.findUserByName(username);
			if (list != null && list.size() != 0) {
				User u = list.get(0);
				if (u.getPassword().equals(password)) {
					if (u.getUserStatus() == Constant.EFFECTIVE) {
						u.setStatus(new Date().getTime());
						userBiz.update(u);
						// request.getSession().setAttribute("user", u);
						// request.getSession().setAttribute("usertype",
						// Constant.LOGIN_USER);
						// 保存user登录情况下的user对象(目的：区分个人用户和企业用户)
						// request.getSession().setAttribute("loginUser", u);
						if (!u.getIsPersonal()) {
							// 保存登录者企业信息
							// request.getSession().setAttribute("loginEnterprise",
							// u.getEnterprise());
							// 得到登录者企业的信誉评价并保存
							EnterpriseCredit ec = ecBiz.queryByEnterprise(u
									.getEnterprise().getId());
							// request.getSession().setAttribute("enterpriseCredit",
							// ec);
							resData.put("orgName", u.getEnterprise().getName());
							resData.put("type", Constant.MOBILE_ENTERPRISE); // 用户类型为企业用户
							resData.put("certified", u.getEnterprise()
									.getIsApproved() == true ? 1 : 0); // 被认证
							if (ec != null) {
								resData.put("star",	Common.round(
										ec.getSellScore()/ ec.getSellCount()).intValue()); // 信誉,星颗数
							} else {
								resData.put("star", 0); // 信誉,星颗数
							}
						} else {
							resData.put("orgName", u.getUserName());
							resData.put("certified",
									u.getIsApproved() == true ? 1 : 0); // 被认证
							resData.put("type", Constant.MOBILE_PERSONAL); // 用户类型为个人用户
							resData.put("star", 0); // 信誉,个人用户目前星星是0颗
						}
						resData.put("unreadMsg", receiverMessageRelationshipBiz
								.countUnreadMessage(u)); // 未读消息数
						String authStr = "u|" + u.getStatus() + "|"
								+ u.getUid(); // authStr相当于cookie
						resData.put("authStr", authStr);
						result.put("statusCode", 0); // 成功
						result.put("resData", resData);
					} else if (u.getUserStatus() == Constant.DISABLED) {
						result.put("statusCode", 1); // 帐号被禁用
						result.put("resData", "帐号被禁用");
					} else {
						result.put("statusCode", 1); // 账号被删除
						result.put("resData", "账号被删除");
					}
				} else {
					result.put("statusCode", 1); // 密码输入有误
					result.put("resData", "密码输入有误");
				}
			} else if (staffBiz.findByUserName(username) != null) {
				Staff staff = staffBiz.findByUserName(username);
				if (staff.getPassword().equals(password)) {
					if (staff.getStaffStatus() == Constant.EFFECTIVE) {
						// request.getSession().setAttribute("user", staff);
						// request.getSession().setAttribute("usertype",
						// Constant.LOGIN_STAFF);
						// 保存登录者企业信息
						// request.getSession().setAttribute("loginEnterprise",
						// staff.getParent().getEnterprise());
						// 得到登录者企业的信誉评价并保存
						EnterpriseCredit ec = ecBiz.queryByEnterprise(staff
								.getParent().getEnterprise().getId());
						// request.getSession().setAttribute("enterpriseCredit",
						// ec);

						staff.setStatus(new Date().getTime());
						staffBiz.update(staff);
						String authStr = "s|" + staff.getStatus() + "|"
								+ staff.getStid();
						resData.put("orgName", staff.getParent()
								.getEnterprise().getName()); // 机构名称
						resData.put("authStr", authStr); // 授权
						resData.put("certified", staff.getParent()
								.getEnterprise().getIsApproved() == true ? 1
								: 0); // 认证
						if (ec != null) {
							resData.put("star",	Common.round(
									ec.getSellScore()/ ec.getSellCount()).intValue()); // 信誉,星颗数
						} else {
							resData.put("star", 0); // 信誉,星颗数
						}
						resData.put("unreadMsg", receiverMessageRelationshipBiz
								.countUnreadMessage(staff)); // 未读消息数
						resData.put("type", Constant.LOGIN_STAFF); // 用户类型为子账号
						result.put("statusCode", 0); // 成功
						result.put("resData", resData);
					} else if (staff.getStaffStatus() == Constant.DISABLED) {
						result.put("statusCode", 1); // 帐号被禁用
						result.put("resData", "帐号被禁用");
					} else {
						result.put("statusCode", 1); // 账号被删除
						result.put("resData", "账号被删除");
					}
				} else {
					result.put("statusCode", 1); // 密码输入有误
					result.put("resData", "密码输入有误");
				}
			} else {
				result.put("statusCode", 1); // 用户名不存在
				result.put("resData", "用户名不存在");
			}
		} else {
			result.put("statusCode", 1); // 验证码输入错误
			result.put("resData", "验证码输入错误");
		}
		// 3.根据校验结果获取

		return result;
	}

	@Override
	public Map<String, Object> getBaseInfo(JSONObject jsonObject,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>(); // 返回结果
		Map<String, Object> resData = new HashMap<String, Object>();
		Object auth = jsonObject.get("auth"); // 授权码
		if (auth != null) { // 验证授权码,
			String authStr = auth.toString();
			String[] array = authStr.split("\\|"); // auth的格式为"用户类型|时间戳|用户编码"
			if (array.length == 3) { // auth授权码格式正确
				if ("u".equals(array[0])) { // auth授权码内含的是主帐号用户
					User user = userBiz.findUserByUidAndModifyTime(array[2],
							array[1]); // 根据用户编码和时间戳查找用户
					if (user != null) { // 授权码正确
						if (user.getUserStatus() == Constant.EFFECTIVE) {
							if (!user.getIsPersonal()) {
								// 得到登录者企业的信誉评价并保存
								EnterpriseCredit ec = ecBiz
										.queryByEnterprise(user.getEnterprise()
												.getId());
								resData.put("orgName", user.getEnterprise()
										.getName());
								resData.put("type", Constant.MOBILE_ENTERPRISE); // 用户类型为企业用户
								resData.put("certified", user.getEnterprise()
										.getIsApproved() == true ? 1 : 0); // 被认证
								resData.put(
										"star",
										Common.round(
												ec.getSellScore()
														/ ec.getSellCount())
												.intValue()); // 信誉,星颗数
							} else {
								resData.put("orgName", user.getUserName());
								resData.put("certified", user.getIsApproved() == true ? 1 : 0); // 被认证
								resData.put("type", Constant.MOBILE_PERSONAL); // 用户类型为个人用户
								resData.put("star", 0); // 信誉,个人用户目前星星是0颗
							}
							user.setStatus(new Date().getTime());
							userBiz.update(user);
							String newAuthStr = "u|" + user.getStatus() + "|"
									+ user.getUid(); // authStr相当于cookie
							resData.put("unreadMsg",
									receiverMessageRelationshipBiz
											.countUnreadMessage(user)); // 未读消息数
							resData.put("authStr", newAuthStr);
							result.put("statusCode", 0); // 成功
							result.put("resData", resData);
						} else if (user.getUserStatus() == Constant.DISABLED) {
							result.put("statusCode", 1); // 帐号被禁用
							result.put("resData", "帐号被禁用");
						} else {
							result.put("statusCode", 1); // 账号被删除
							result.put("resData", "账号被删除");
						}
					} else { // 授权码已失效
						result.put("statusCode", 1);
						result.put("resData", "授权码已失效");
					}
				} else if ("s".equals(array[0])) { // auth授权码内含的是子帐号用户
					Staff staff = staffBiz.findByCodeAndStatus(array[2],
							array[1]);
					if (staff != null) { // 授权码正确
						if (staff.getStaffStatus() == Constant.EFFECTIVE) {
							// 得到登录者企业的信誉评价并保存
							EnterpriseCredit ec = ecBiz.queryByEnterprise(staff
									.getParent().getEnterprise().getId());
							staff.setStatus(new Date().getTime());
							staffBiz.update(staff);
							String newAuthStr = "s|" + staff.getStatus() + "|"
									+ staff.getStid();
							resData.put("orgName", staff.getParent()
									.getEnterprise().getName()); // 机构名称
							resData.put("authStr", newAuthStr); // 授权
							resData.put("certified",
									staff.getParent().getEnterprise()
											.getIsApproved() == true ? 1 : 0); // 认证
							resData.put(
									"star",
									Common.round(
											ec.getSellScore()
													/ ec.getSellCount())
											.intValue()); // 信誉,星颗数
							resData.put("unreadMsg",
									receiverMessageRelationshipBiz
											.countUnreadMessage(staff)); // 未读消息数
							result.put("statusCode", 0); // 成功
							result.put("resData", resData);
						} else if (staff.getStaffStatus() == Constant.DISABLED) {
							result.put("statusCode", 1); // 帐号被禁用
							result.put("resData", "帐号被禁用");
						} else {
							result.put("statusCode", 1); // 账号被删除
							result.put("resData", "账号被删除");
						}
					} else {
						result.put("statusCode", 1);
						result.put("resData", "授权码错误,请重新登录");
					}
				} else { // auth授权码格式错误
					result.put("statusCode", 1);
					result.put("resData", "授权码错误,请重新登录");
				}

			} else {
				result.put("statusCode", 1);
				result.put("resData", "授权码错误,请重新登录");
			}
		} else {
			result.put("statusCode", 1);
			result.put("resData", "请重新登录");
		}

		return result;
	}

	@Override
	public Map<String, Object> getOrders(JSONObject jsonObject,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>(); // 返回结果
		// 1.校验
		// 2.根据校验结果,成功则取对应的用户订单数据;反正则返回失败原因
		Object auth = jsonObject.get("auth"); // 授权码
		if (auth != null) { // 验证授权码,
			String authStr = auth.toString();
			String[] array = authStr.split("\\|"); // auth的格式为"用户类型|时间戳|用户编码"
			if (array.length == 3) { // auth授权码格式正确
				if ("u".equals(array[0])) { // auth授权码内含的是主帐号用户
					User user = userBiz.findUserByUidAndModifyTime(array[2],
							array[1]); // 根据用户编码和时间戳查找用户
					if (user != null) { // 授权码正确
						if (user.getUserStatus() == Constant.EFFECTIVE) {
							if (!user.getIsPersonal()) {
								// 得到买单
								List<Object> purchaseOrders = goodsOrderBiz
										.getPurchaseOrders(user.getEnterprise()
												.getId());
								result.put("resData", purchaseOrders);
							} else {
								result.put("resData", null);
							}
							result.put("statusCode", 0); // 成功
						} else if (user.getUserStatus() == Constant.DISABLED) {
							result.put("statusCode", 1); // 帐号被禁用
							result.put("resData", "帐号被禁用");
						} else {
							result.put("statusCode", 1); // 账号被删除
							result.put("resData", "账号被删除");
						}
					} else { // 授权码已失效
						result.put("statusCode", 1);
						result.put("resData", "授权码已失效");
					}
				} else if ("s".equals(array[0])) { // auth授权码内含的是子帐号用户
					Staff staff = staffBiz.findByCodeAndStatus(array[2],
							array[1]);
					if (staff != null) { // 授权码正确
						if (staff.getStaffStatus() == Constant.EFFECTIVE) {
							// 得到买单
							List<Object> purchaseOrders = goodsOrderBiz
									.getPurchaseOrders(staff.getParent().getEnterprise().getId());
							result.put("resData", purchaseOrders);
							result.put("statusCode", 0); // 成功
						} else if (staff.getStaffStatus() == Constant.DISABLED) {
							result.put("statusCode", 1); // 帐号被禁用
							result.put("resData", "帐号被禁用");
						} else {
							result.put("statusCode", 1); // 账号被删除
							result.put("resData", "账号被删除");
						}
					} else {
						result.put("statusCode", 1);
						result.put("resData", "授权码错误,请重新登录");
					}
				} else { // auth授权码格式错误
					result.put("statusCode", 1);
					result.put("resData", "授权码错误,请重新登录");
				}

			} else {
				result.put("statusCode", 1);
				result.put("resData", "授权码错误,请重新登录");
			}
		} else {
			result.put("statusCode", 1);
			result.put("resData", "请重新登录");
		}

		return result;
	}

	private Message getTransactionMessage(){
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
	
	public static void main(String[] args) {
		String msg = "a|123123123|sdfasdfasdf";
		String[] array = msg.split("\\|");
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
