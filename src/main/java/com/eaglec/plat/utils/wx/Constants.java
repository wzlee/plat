package com.eaglec.plat.utils.wx;

public class Constants {

	/**
	 * 接收方帐号
	 */
	public static String TO_USER_NAME = "ToUserName";

	/**
	 * 发送方帐号
	 */
	public static String FROM_USER_NAME = "FromUserName";

	/**
	 * 消息创建时间
	 */
	public static String CREATE_TIME = "CreateTime";

	/**
	 * 消息类型 包括 text（文本消息）,music（音频消息）,link(链接消息),image(图片消息) news （图文消息）
	 * location
	 * （地理位置信息）,event(事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件))
	 */
	public static String MSG_TYPE = "MsgType";

	/**
	 * 消息id，64位整型
	 */
	public static String MSG_ID = "MsgId";

	/**
	 * 图片链接
	 */
	public static String PIC_URL = "PicUrl";

	/**
	 * 消息标题
	 */
	public static String TITLE = "Title";

	/**
	 * 消息描述
	 */
	public static String DESCRIPTION = "Description";

	/**
	 * 消息链接
	 */
	public static String URL = "Url";

	/**
	 * 地理位置纬度
	 */
	public static String LOCATION_X = "Location_X";

	/**
	 * 地理位置经度
	 */
	public static String LOCATION_Y = "Location_Y";

	/**
	 * 地图缩放大小
	 */
	public static String SCALE = "Scale";

	/**
	 * 地理位置信息
	 */
	public static String LABEL = "Label";

	/**
	 * 
	 * 文本消息内容
	 */
	public static String CONTENT = "Content";

	/**
	 * 
	 */
	public static String MEDIA_ID = "MediaId";

	/**
	 * 
	 */
	public static String FORMAT = "Format";

	/**
	 * 
	 */
	public static String FUNC_FLAG = "FuncFlag";

	/**
	 * 
	 */
	public static String MUSIC_URL = "MusicUrl";

	/**
	 * 
	 */
	public static String HQ_MUSIC_URL = "HQMusicUrl";

	/**
	 * 
	 */
	public static String ARTICLE_COUNT = "ArticleCount";

	/**
	 * 
	 */
	public static String ARTICLES = "Articles";

	/**
	 * 
	 */
	public static String EVENT = "Event";

	/**
	 * 
	 */
	public static String TYPE = "Type";

	/**
	 * 订阅
	 */
	public static String SUBSCRIBE = "subscribe";

	/**
	 * 取消订阅
	 */
	public static String UNSUBSCRIBE = "unsubscribe";

	/**
	 * 自定义菜单
	 */
	public static String CLICK = "CLICK";

	// -----------------------接口请求地址-------------------------//
	/**
	 * 获取token请求地址 请求方式：GET
	 */
	public static String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * 创建菜单请求地址 请求方式：POST
	 */
	public static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 获取菜单请求地址 请求地址 请求方式：POST
	 */
	public static String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	/**
	 * 删除菜单请求地址 请求方式：POST
	 */
	public static String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/********************** 用户管理 *********************/

	/**
	 * 创建用户组请求地址 请求方式：POST
	 */
	public static String CREATE_USER_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	/**
	 * 获取用户组请求地址 请求方式：GET
	 */
	public static String GET_USER_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";

	/**
	 * 修改分组名称请求地址 请求方式：POST
	 */
	public static String UPDATE_GROUP_NAME_URL = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";

	/**
	 * 移动用户到分组请求地址 请求方式：POST
	 */
	public static String MOVE_USER_TO_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	/**
	 * 获取用户信息请求地址 请求方式：GET
	 */
	public static String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";

	/**
	 * 获取所有关注者请求地址 请求方式：GET
	 */
	public static String GET_FANS_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

	/**
	 * 用户授权请求地址 请求方式：GET
	 */
	public static String USER_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	/**
	 * 获取二维码ticket请求地址 请求方式：POST
	 */
	public static String GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

	/**
	 * 通过ticket获取二维码请求地址 请求方式：GET
	 */
	public static String GET_SHOWQRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

	/**
	 * 获取access_token请求地址 请求方式：GET
	 */
	public static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
}
