Ext.define('plat.model.info.InfoModel', {
	extend : 'Ext.data.Model',
	fields : [
		{name : 'id', type : 'int' , mapping : '[0].id'},
		{name : 'content', type : 'String', defaultValue : '暂无',mapping : '[0].content' }, 
		{name : 'sendTime',type : 'String',mapping : '[0].sendTime'}, 
		{name : 'messageClass.messageType',type : 'String',mapping : '[0].messageClass.messageType' },
		{name : 'receiver',type : 'String',defaultValue : '未保存收信人',mapping : '[1]'}
	]
});
