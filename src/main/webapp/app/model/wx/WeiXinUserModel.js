Ext.define('plat.model.wx.WeiXinUserModel', {
	extend : 'Ext.data.Model',
	fields: [
		{name: 'id', type: 'int'},
		{name: 'username', type: 'String'},//平台用户帐号
		{name: 'enterprise_id', type: 'int'},//企业id
		{name: 'enterprise_name', type: 'String'},//企业名称
		{name: 'wxUserToken', type: 'String'},//加密过后的微信帐号
		{name: 'wx_user_name', type: 'String'},//微信帐号昵称
		{name: 'wx_user_headimgurl', type: 'String'},//微信用户头像
		{name: 'wx_user_sex', type: 'String'},//微信用户性别
		{name: 'wx_user_language', type: 'String'},//微信用户使用的语言
		{name: 'wx_user_country', type: 'String'},//微信用户所属的国家
		{name: 'wx_user_province', type: 'String'},//微信用户所在的省份
		{name: 'wx_user_city', type: 'String'},//微信用户所在的城市
		{name: 'subscribe_time', type: 'String'},//微信用户扫描二维码时间
		{name: 'concern_status', type: 'int'}//微信用户关注的状态
	]
});
