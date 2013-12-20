/**
 * 申诉类型下拉框	xuwf 2013-9-26
 */
Ext.define('plat.view.layout.combo.AppealTypeCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'appealtype',
	editable:false,
	width:80,
	queryMode: 'local',
    displayField: 'appealType',
    valueField: 'id',
    initComponent:function(){
    	this.store = new Ext.data.SimpleStore({
    		fields:['id','appealType'],
    		data:[[1,'买家申诉'],[2,'卖家申诉']]
    	});
    	this.callParent(arguments);
    }
})