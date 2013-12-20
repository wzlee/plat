Ext.define('plat.view.layout.combo.BClassCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'bclasscombo',	
		
	store:new Ext.data.SimpleStore({
		fields:['value','text'],
		data:[
			[10,'信息服务'],
			[11,'投融资服务'],
			[12,'创业服务'],
			[13,'人才与培训服务'],
			[14,'技术创新和质量服务'],
			[15,'管理咨询服务'],
			[16,'市场开拓服务'],
			[17,'法律服务'],
			[99,'其他服务']
			],		
		displayField: 'value'})
});