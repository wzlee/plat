/**
 * 订单处理方式下拉框	xuwf 2013-9-24
 */
Ext.define('plat.view.layout.combo.HandlerMode',{
	extend:'Ext.form.field.ComboBox',
	xtype:'handlermode',
	editable:false,
	width:80,
	queryMode: 'local',
    displayField: 'handlermode',
    valueField: 'id',
    initComponent:function(){
    	this.store = new Ext.data.SimpleStore({
    		fields:['id','handlermode'],
    		data:[[1,'关闭订单'],[2,'取消订单']]
    	});
    	this.callParent(arguments);
    }
})