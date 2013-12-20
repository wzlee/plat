Ext.define('plat.store.mall.MallServiceAdStore', {
    extend: 'Ext.data.TreeStore',
    xtype:'mallserviceadstore',
    
    fields : ['id', 'text', 'pid', 'leaf', 'code', 'idxtype'],
	proxy : {
		type : 'ajax',
		url : 'mall/findAll'
	},
	root : {
		text : '所有类别',
		expanded : true
	},
	reader : {
		type : 'json',
		root : '',
		messageProperty : "message"
	},
	folderSort : true,
	sorters : [{
				property : 'id',
				direction : 'ASC'
			}]
});
