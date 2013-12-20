Ext.define('plat.store.service.ServiceCategoryStore', {
    extend: 'Ext.data.TreeStore',
    xtype:'servicecategorystore',
    
    fields : ['id', 'text', 'pid', 'leaf', 'code', 'idxtype'],
	proxy : {
		extraParams : {
			clazz : 'service'
		},
		type : 'ajax',
		url : 'category/load'
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
