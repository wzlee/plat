package com.eaglec.plat.biz.impl.app.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.eaglec.plat.biz.app.mobile.MobileAppBiz;
import com.eaglec.plat.biz.business.GoodsOrderBiz;
import com.eaglec.plat.biz.flat.FlatBiz;
import com.eaglec.plat.biz.info.ReceiverMessageRelationshipBiz;
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
import com.eaglec.plat.domain.policy.PolicyCategory;
import com.eaglec.plat.domain.service.ServiceConsumer;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;

@org.springframework.stereotype.Service
public class MobileAppBizImpl implements MobileAppBiz {
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
								resData.put(
										"star",
										Common.round(
												ec.getSellScore()
														/ ec.getSellCount())
												.intValue()); // 信誉,星颗数
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
						resData.put(
								"star",
								Common.round(
										ec.getSellScore() / ec.getSellCount())
										.intValue()); // 信誉,星颗数
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

	public static void main(String[] args) {
		String msg = "a|123123123|sdfasdfasdf";
		String[] array = msg.split("\\|");
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
