/**
 * 订单状态下拉框	xuwf 2013-9-24
 */
Ext.define('plat.view.layout.combo.BiddingServiceStatus',{
	extend:'Ext.form.field.ComboBox',
	xtype:'biddingservicestatus',
	editable:false,
	width:80,
	queryMode: 'local',
    displayField: 'status',
    valueField: 'id',
    initComponent:function(){
    	this.store = new Ext.data.SimpleStore({
    		fields:['id','status'],
    		data:[[1,'待发布'],[2,'待审核'],[3,'平台退回'],
    		[4,'招标中'],[5,'应标中'],[6,'交易进行中'],[7,'等待买家关闭'],
    		[8,'等待卖家关闭'],[9,'申诉处理中'],[10,'交易结束'],[11,'招标取消']
    		]
    	});
    	this.callParent(arguments);
    }
})