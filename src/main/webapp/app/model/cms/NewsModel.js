Ext.define('plat.model.cms.NewsModel', {
	extend : 'Ext.data.Model',
	fields: ['id','title', 'picture', 'content', 'author', 'pubdate',
		'source', 'cid', 'isTop'
	]
});