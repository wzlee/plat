Ext.define('plat.view.layout.combo.SMethodCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'smethodcombo',	
	store:new Ext.data.SimpleStore({
		fields:['value','text'],
		data:[
			[1,'柜台式服务'],
			[2,'电话服务'],
			[3,'上门服务'],
			[4,'刊物服务'],
			[5,'信函服务'],
			[6,'网络服务'],
			[7,'其他服务']
			],		
		displayField: 'value'})
});