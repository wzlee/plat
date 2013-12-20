Ext.define('plat.store.mall.MallChildrenCategoryStore', {
    extend: 'Ext.data.Store',
    xtype:'mallchildrencategorystore',
    
    fields : ['id', 'text', 'pid', 'leaf', 'code', 'idxtype'],
    extraParams: {
            pid: null
    },
	proxy : {
		type : 'ajax',
		url : 'mall/findChildren'
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
