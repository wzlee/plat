Ext.define('plat.model.wx.UserChangeModel', {
	extend : 'Ext.data.Model',
	fields: [
		{name: 'id', type: 'int'},
		{name: 'username', type: 'String'},//平台用户帐号
		{name: 'wxUserToken', type: 'String'},//加密过后的微信帐号
		{name: 'enterprise_id', type: 'int'},//企业id
		{name: 'enterprise_name', type: 'String'},//企业名称
		{name: 'change_time', type: 'String'},//微信用户变更时间
		{name: 'change_status', type: 'int'}//微信用户关注的状态
	]
});
