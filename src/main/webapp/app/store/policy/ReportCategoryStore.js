Ext.define('plat.store.policy.ReportCategoryStore', {
	extend : 'Ext.data.TreeStore',
    xtype : 'reportcategorystore',
    
    fields : ['id', 'text', 'pid', 'leaf'],
	proxy : {
		type : 'ajax',
		url : 'policy/findReportCategory'
	},
	root : {
		text : '类别',
		expanded : true
	},
	reader : {
		type : 'json',
		root : '',
		messageProperty : "message"
	},
	folderSort : true,
	autoLoad:false,
	sorters : [{
				property : 'id',
				direction : 'ASC'
		}]
});
