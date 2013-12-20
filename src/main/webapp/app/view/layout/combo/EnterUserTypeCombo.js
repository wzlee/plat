/**
 * 企业用户类型下拉框 xuwf	2013-8-22
 */
Ext.define('plat.view.layout.combo.EnterUserTypeCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'entertype',
	editable:false,
	width:80,
	queryMode: 'local',
    displayField: 'enterType',
    valueField: 'id',
    initComponent:function(){
    	this.store = new Ext.data.SimpleStore({
    		fields:['id','enterType'],
    		data:[['不限','不限'],['普通企业','普通企业'],['认证企业','认证企业'],['服务机构','服务机构'],
    			['政府机构','政府机构']
    		]
    	});
    	this.callParent(arguments);
    }
})