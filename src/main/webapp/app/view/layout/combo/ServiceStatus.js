Ext.define('plat.view.layout.combo.ServiceStatus',{
	extend:'Ext.form.field.ComboBox',
	xtype:'servicestatus',
	editable:false,
	width:80,
	queryMode: 'local',
    displayField: 'serviceStatus',
    valueField: 'id',
    initComponent:function(){
    	this.store = new Ext.data.SimpleStore({
    		fields:['id','serviceStatus'],
    		data:[[1,'新服务'],[2,'上架审核中'],[3,'已上架'],
    		[4,'变更审核中'],[5,'已删除'],[6,'已下架'],[7,'下架审核中']
    		]
    	});
    	this.callParent(arguments);
    }
})