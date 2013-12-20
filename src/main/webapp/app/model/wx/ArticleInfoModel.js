Ext.define('plat.model.wx.ArticleInfoModel', {
	extend : 'Ext.data.Model',
	fields: ['id', 'title', 'picUrl', 'url',
		'description',  'pubdate', {name : 'autoMessageId', mapping : 'autoMessage.id'},
		{name : 'content', mapping : 'autoMessage.content'},
		{name : 'reqKey', mapping : 'autoMessage.reqKey'},
		{name : 'clickKey', mapping : 'autoMessage.clickKey'}
	]
});
