/**
 * 订单状态下拉框	xuwf 2013-9-24
 */
Ext.define('plat.view.layout.combo.OrderStatus',{
	extend:'Ext.form.field.ComboBox',
	xtype:'orderstatus',
	editable:false,
	width:80,
	queryMode: 'local',
    displayField: 'orderStatus',
    valueField: 'id',
    initComponent:function(){
    	this.store = new Ext.data.SimpleStore({
    		fields:['id','orderStatus'],
    		data:[[1,'等待卖家确认'],[6,'交易进行中'],[7,'等待买家关闭'],
    		[8,'等待卖家关闭'],[9,'申诉处理中'],[10,'交易结束'],[11,'订单取消']
    		]
    	});
    	this.callParent(arguments);
    }
})