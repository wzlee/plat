package com.eaglec.plat.utils;

/**
 * Plat中存在的数据状态常量
 * 
 * @author Xiadi
 * @since 2013-8-13
 */
public class Constant {
	/*---------------Service-------------------*/
	/**
	 * 新服务
	 */
	public final static int SERVICE_STATUS_NEW = 1;

	/**
	 * 上架审核中
	 */
	public final static int SERVICE_STATUS_ADDED_AUDIT = 2;

	/**
	 * 已上架
	 */
	public final static int SERVICE_STATUS_ADDED = 3;

	/**
	 * 变更审核中
	 */
	public final static int SERVICE_STATUS_CHANGE_AUDIT = 4;

	/**
	 * 已删除
	 */
	public final static int SERVICE_STATUS_DELETEED = 5;

	/**
	 * 已下架
	 */
	public final static int SERVICE_STATUS_DOWN = 6;

	/**
	 * 下架审核中
	 */
	public final static int SERVICE_STATUS_DOWN_AUDIT = 7;

	/**
	 * 服务来源：1服务机构本身上传 2运营管理人员代理录入 3 运营服务
	 * */
	public final static int SERVICE_SOURCE_ORG = 1;

	public final static int SERVICE_SOURCE_PLAT = 2;

	public final static int SERVICE_SOURCE_OPERATOR = 3;

	/*---------------ServiceDetail-------------------*/
	/**
	 * 未通过
	 */
	public final static int SERVICE_AUDIT_FAIL = 0;

	/**
	 * 通过
	 */
	public final static int SERVICE_AUDIT_PASS = 1;

	/**
	 * 未审核
	 */
	public final static int SERVICE_AUDIT_NORMAL = 2;

	/**
	 * 服务流水
	 * */
	public final static String SERIAL_Id = "A01";

	public final static int CATEGORY_ID = 1;

	/*-------------------Enterprise---------------*/
	/**
	 * 普通企业
	 */
	public final static int ENTERPRISE_TYPE_PUBLIC = 1;

	/**
	 * 认证企业
	 */
	public final static int ENTERPRISE_TYPE_REALNAME = 2;

	/**
	 * 服务机构
	 */
	public final static int ENTERPRISE_TYPE_ORG = 3;

	/**
	 * 政府机构
	 */
	public final static int ENTERPRISE_TYPE_GOV = 4;
	
	/**
	 * 个人用户
	 */
	public final static int PERSONERAL_TYPE = 5; 
	
	/**
	 * 企业状态 —— 有效1
	 */
	public final static int ENTERPRISE_STATUS_EFFECTIVE = 1;
	
	/**
	 * 企业状态 —— 禁用0
	 */
	public final static int ENTERPRISE_STATUS_DISABLED = 0;
	
	/**
	 * 企业状态 —— 删除2
	 */
	public final static int ENTERPRISE_STATUS_DELETED = 2;

	/*-------------------ApprovedDetail---------------*/
	/**
	 * 实名认证
	 */
	public final static int APPROVED_APPLYTYPE_REALNAME = 1;

	/**
	 * 服务机构认证
	 */
	public final static int APPROVED_APPLYTYPE_ORG = 2;

	/*---------------GoodsOrder-------------------*/

	/**
	 * 订单状态：1、等待卖家确认
	 */
	public final static int WAIT_SELLER_CONFIRM = 1;
	public final static String WAIT_SELLER_CONFIRM_STR = "等待卖家确认";

	/**
	 * 订单状态：6、交易进行中
	 */
	public final static int TRANSACTIONS_IN = 6;
	public final static String TRANSACTIONS_IN_STR = "交易进行中";

	/**
	 * 订单状态：7、等待买家关闭
	 */
	public final static int WAIT_BUYER_CLOSE = 7;
	public final static String WAIT_BUYER_CLOSE_STR = "等待买家关闭";

	/**
	 * 订单状态：8、等待卖家关闭
	 */
	public final static int WAIT_SELLER_CLOSE = 8;
	public final static String WAIT_SELLER_CLOSE_STR = "等待卖家关闭";

	/**
	 * 订单状态：9、申诉处理中
	 */
	public final static int APPEAL_PROCESSING = 9;
	public final static String APPEAL_PROCESSING_STR = "申诉处理中";

	/**
	 * 订单状态：10、交易结束
	 */
	public final static int TRANSACTIONS_END = 10;
	public final static String TRANSACTIONS_END_STR = "交易结束";

	/**
	 * 订单状态：11、订单取消
	 */
	public final static int ORDER_CANCEL = 11;
	public final static String ORDER_CANCEL_STR = "订单取消";

	/**
	 * 买家记号
	 */
	public final static int FLAG_BUYER = 0;
	/**
	 * 卖家记号
	 */
	public final static int FLAG_SELLER = 1;

	/**
	 * 普通服务
	 */
	public final static int ORDER_SOURCE_S = 1;
	/**
	 * 招标服务
	 */
	public final static int ORDER_SOURCE_B = 2;

	/*--------------------OrderInfo-----------------------------*/

	/**
	 * 订单动作：1、买家提交订单
	 */
	public final static int ACTION_BUYER_ORDER_SUBMIT = 1;

	/**
	 * 订单动作：2、卖家确认订单
	 */
	public final static int ACTION_SELLER_ORDER_CONFIRM = 2;

	/**
	 * 订单动作：3、进入交易
	 */
	public final static int ACTION_TRANSACTIONS_IN = 3;

	/**
	 * 订单动作：4、买家关闭交易
	 */
	public final static int ACTION_BUYER_EVALUATION = 4;

	/**
	 * 订单动作：5、卖家关闭交易
	 */
	public final static int ACTION_SELLER_EVALUATION = 5;

	/**
	 * 订单动作：6、平台关闭交易
	 */
	public final static int ACTION_CLOSE_DEAL = 6;

	/**
	 * 订单动作：7、卖家取消订单
	 */
	public final static int ACTION_SELLER_ORDER_CANCEL = 7;

	/**
	 * 订单动作：8、平台取消订单
	 */
	public final static int ACTION_MANAGER_ORDER_CANCEL = 8;

	/**
	 * 订单动作：9、买家申诉
	 */
	public final static int ACTION_BUYER_APPEAL = 9;

	/**
	 * 订单动作：10、卖家申诉
	 */
	public final static int ACTION_SELLER_APPEAL = 10;
	/**
	 * 订单动作：11、申诉处理，关闭交易
	 */
	public final static int ACTION_ORDER_APPEAL_CLOSE = 11;
	/**
	 * 订单动作：12、申诉处理，取消订单
	 */
	public final static int ACTION_ORDER_APPEAL_CANCEL = 12;

	/*--------------------Appeal-----------------------------*/

	/**
	 * 申诉原因：1.买方违约
	 */
	public final static int BUYER_BREAK_A_CONTRACT = 1;

	/**
	 * 申诉原因：2.卖方违约
	 */
	public final static int SELLER_BREAK_A_CONTRACT = 2;

	/**
	 * 申诉原因：3.其他
	 */
	public final static int APPEAL_OTHER = 3;

	/**
	 * 申诉类型：1.买家申诉 支撑平台
	 */
	public final static int APPEAL_TYPE_BUYER = 1;

	/**
	 * 申诉类型：2.卖家申诉 支撑平台
	 */
	public final static int APPEAL_TYPE_SELLER = 2;

	/**
	 * 申诉状态:1.交易终止 支撑平台
	 */
	public final static int APPEAL_STATUS＿CLOSE_DEAL = 1;

	/**
	 * 申诉状态:2.订单取消 支撑平台
	 */
	public final static int APPEAL_STATUS_ORDER_CANCEL = 2;

	/**
	 * 处理方式：关闭订单 (支撑平台人员对订单的操作)
	 */
	public final static int HANDLER_MODE_CLOSE = 1;

	/**
	 * 处理方式：取消订单 (支撑平台人员对订单的操作)
	 */
	public final static int HANDLER_MODE_CANCEL = 2;

	/*--------------------OrderEvaluation-----------------------------*/

	/**
	 * 满意度：5、十分满意
	 */
	public final static int SATISFACTION_VERY = 5;

	/**
	 * 满意度：4、满意
	 */
	public final static int SATISFACTION_OK = 4;

	/**
	 * 满意度：3、一般
	 */
	public final static int SATISFACTION_NORMAL = 3;

	/**
	 * 满意度：2、不满意
	 */
	public final static int SATISFACTION_NOT = 2;

	/**
	 * 满意度：1、差
	 */
	public final static int SATISFACTION_BAD = 1;

	/*--------------------UserController-----------------------------*/

	/**
	 * 用户状态 1:有效
	 */
	public final static int EFFECTIVE = 1;

	/**
	 * 用户状态 2：禁用
	 */
	public final static int DISABLED = 2;

	/**
	 * 用户状态 3:删除
	 */
	public final static int DELETED = 3;

	/*--------------------StaffController-----------------------------*/

	/**
	 * 企业子账号数量限制
	 */
	public final static int CUSER_NUM = 20;

	/*-------------------ServiceController--------------------------*/
	/**
	 * 搜索结果页大图模式
	 * */
	public static final int RESULT_TYPE_BIG = 0;

	/**
	 * 搜索结果页大图模式
	 * */
	public static final int RESULT_TYPE_LINE = 1;

	/*-------------------ServiceController--------------------------*/
	/**
	 * 服务机构主页加载服务每页显示条数
	 */
	public static final int ORG_SERVICE_LIMIT = 9;

	/*-------------------服务机构推荐------------------------------*/
	/**
	 * 服务机构频道顶部4个推荐服务机构模版所属的频道编码
	 * */
	public static final String SERVER_AGENCY_1 = "recommend-4-9-1";

	public static final String CHANNELName_RECOMMEND_ENTERPRISE = "服务机构配置";

	/*-------------------招标单状态 BiddingService.status ------------------------------*/
	/**
	 * 待发布(即新招标)
	 */
	public static final int BIDDING_NEW = 1;
	/**
	 * 待审核
	 */
	public static final int BIDDING_ADDED_AUDIT = 2;
	/**
	 * 平台退回
	 */
	public static final int BIDDING_PLAT_REJECT = 3;
	/**
	 * 招标中
	 */
	public static final int BIDDING_DOING = 4;
	/**
	 * 应标中
	 */
	public static final int BIDDING_RESPONSE = 5;
	/**
	 * 交易进行中
	 */
	public static final int BIDDING_TRADE_ING = 6;
	/**
	 * 等待买家关闭
	 */
	public static final int BIDDING_BUYER_CLOSING = 7;
	/**
	 * 等待卖家关闭
	 */
	public static final int BIDDING_SELLER_CLOSING = 8;
	/**
	 * 申诉处理中
	 */
	public static final int BIDDING_APPEAL_DEALING = 9;
	/**
	 * 交易结束
	 */
	public static final int BIDDING_TRADE_END = 10;
	/**
	 * 招标取消
	 */
	public static final int BIDDING_CANCEL = 11;
	/**
	 * 订单取消
	 */
	public static final int BIDDING_ORDER_CANCEL = 12;

	/*-------------------招标流水动作 BiddingServiceDetail.action ------------------------------*/
	/**
	 * 1、保存招标信息
	 */
	public static final int ACTION_SAVE_BIDDING_SERVICE = 1;
	/**
	 * 2.发布招标服务
	 */
	public static final int ACTION_ISSUE_BIDDING_SERVICE = 2;
	/**
	 * 3、取消招标
	 */
	public static final int ACTION_CANCEL_BIDDING = 3;
	/**
	 * 4、选择应标服务
	 */
	public static final int ACTION_CHOOSE_RESPONSE_BIDDING = 4;
	/**
	 * 5、买家关闭交易
	 */
	public static final int ACTION_BUYER_CLOSE_BIDDING = 5;
	/**
	 * 6、买家申诉
	 */
	public static final int ACTION_BUYER_APPEAL_BIDDING = 6;
	/**
	 * 7、申请应标
	 */
	public static final int ACTION_APPLY_BIDDING_SERVICE = 7;
	/**
	 * 8、卖家申诉
	 */
	public static final int ACTION_SELLER_APPEAL_BIDDING = 8;
	/**
	 * 9、卖家关闭交易
	 */
	public static final int ACTION_SELLER_CLOSE_BIDDING = 9;
	/**
	 * 10、平台审核通过
	 */
	public static final int ACTION_BIDDING_AUDIT_SUCCESS = 10;
	/**
	 * 11.平台审核退回
	 */
	public static final int ACTION_BIDDING_AUDIT_FAILED = 11;
	/**
	 * 12、申诉处理，关闭交易
	 */
	public static final int ACTION_CLOSE_APPEAL_BIDDING = 12;
	/**
	 * 13、申诉处理，取消订单
	 */
	public static final int ACTION_CANCEL_APPEAL_BIDDING = 13;
	/**
	 * 14.平台关闭交易
	 */
	public static final int ACTION_PLAT_CLOSE_BIDDING = 14;
	/**
	 * 15.平台取消招标
	 */
	public static final int ACTION_PLAT_CANCEL_BIDDING = 15;
	
	/**
	 * 16.平台取消订单
	 */
	public static final int ACTION_PLAT_CANCEL_BIDDING_ORDER = 16;
	/*-------------------应标状态 ResponseBidding.status ------------------------------*/
	/**
	 * 应标中
	 */
	public static final int RESPONSE_DOING = 5;
	/**
	 * 应标失败
	 */
	public static final int RESPONSE_FAILE = 6;
	/**
	 * 交易进行中
	 */
	public static final int RESPONSE_TRADE = 7;

	/*-------------------服务商城首页推荐服务广告位置 Advertisement------------------------------*/

	/**
	 * 服务商城
	 */
	public static final String MALL_NAME = "服务商城";
	/**
	 * 服务商城首页，加载推荐服务、热点服务、好评服务、最新服务个数
	 */
	public static final int Mall_SERVICE_LIMIT = 4;
	/**
	 * 服务广告轮播位
	 */
	public static final int MALL_SERVICEAD_TOP = 1;
	/**
	 * 商城Tab服务推荐位
	 */
	public static final int MALL_RECOMSERVICE_TOP = 2;
	/**
	 * 分类服务推荐位
	 */
	public static final int MALL_RECOMSERVICE = 3;
	/**
	 * 左侧服务广告位
	 */
	public static final int MALL_SERVICEAD = 4;

	/**
	 * 分类推荐服务加载条数
	 */
	public static final int MALL_RECOMSERVICE_LIMIT = 10;

	/**
	 * top服务广告加载条数
	 */
	public static final int MALL_SERVICEAD_TOP_LMIT = 8;

	/**
	 * 服务商城首页，加载推荐服务、热点服务、好评服务、最新服务个数
	 */
	public static final int MALL_CTEGORY_SERVICE_LIMIT = 3;

	/**
	 * 类表形式服务显示个数
	 */
	public static final int MALL_CATEGORY_LIST_FIVE = 5;

	/**
	 * 块形式服务显示个数
	 */
	public static final int MALL_CATEGORY_LIST_FIVETEEN = 15;
	/******************************StaffRole*********************************/
	/**
	 * 企业用户角色
	 */
	public static final int ROLE_BUYER_ENTERPRISE = 1;
	/**
	 * 机构用户角色
	 */
	public static final int ROLE_SELLER_ENTERPRISE = 2;
	/**
	 * 用户中心所有菜单
	 */
	public static final int ROLE_UCENTER_ENTERPRISE = 3;
	/******************************session中用户的TYPE*********************************/
	/**
	 * 主账号
	 */
	public static final int LOGIN_USER = 1;
	/**
	 * 子账号
	 */
	public static final int LOGIN_STAFF = 2;
	/**
	 * 管理员
	 */
	public static final int LOGIN_MANAGER = 3;
	
	/***********************系统自动发送交易消息********************************/
	
	/**
	 * 交易消息类别
	 */
	public static final Integer MESSAGE_TYPE_NAME = 2;
	/**
	 * 系统消息类别
	 */
	public static final Integer MESSAGE_TYPE_SYSTEM = 1;
	/**
	 * 发送人
	 */
	public static final String TRANSACTIONS_INFO_SENDER = "系统自动发送";
	
	
	/**
	 * 在线客服的一些常量
	 */
	public static final int CHAT_USER_STATUS_OFFLINE = 0;	//离线
	
	public static final int CHAT_USER_STATUS_ONLINE = 1;	//在线

	public static final int CHAT_USER_STATUS_HIDING = 2;	//隐身
	
	public static final int CHAT_USER_STATUS_BUSY = 3;		//忙碌
	
	public static final int CHAT_USER_STATUS_LEAVE = 4;		//离开
	
	public static final String CHAT_ROLE_ADMIN = "admin";	//管理员用户
	
	public static final String CHAT_ROLE_USER = "user"; 	//普通用户

	public static final String CHAT_ROLE_SERVICE = "server";//客服用户
	
	public static final String CHAT_DEFAULT_ROBOT_NAME = "默认机器人";
	
	public static final String CHAT_DEFAULT_ROBOT_REMARK = "该机器人为还未指定机器人客服组解答客户在线咨询的疑问。";
	
	public static final String CHAT_DEFAULTGROUP_NAME = "默认组";
	
	public static final String CHAT_DEFAULTGROUP_IMG = "default_group.png";
	
	public static final String CHAT_DEFAULTGROUP_DESC = "该组专门解决未指派咨询的服务！";

	/**
	 * 移动端用户类型
	 * */
	public static final int MOBILE_PERSONAL = 0;
	
	public static final int MOBILE_ENTERPRISE = 1;
	
	public static final int MOBILE_STAFF = 2;
	
	/**
	 * 用户数据来源 0表示从平台网站注册的用户 
	 */
	public static final Integer USER_FROM_REGISTER = 0;

	/**
	 * 用户数据来源 1表示从业务运营支撑系统录入的用户
	 */
	public static final Integer USER_FROM_ADMIN = 1;

	/**
	 * 中小微企业认定默认状态
	 */
	public static final Integer SME_APPROVE_STATUS = 2;

	/**
	 * 政策的top值
	 */
	public static final Integer POLICY_TOP = 1;
}
