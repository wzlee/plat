Ext.define('plat.view.layout.combo.MClassCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'mclasscombo',	
		
	store:new Ext.data.SimpleStore({
		fields:['value','text'],
		data:[
			[1001,'法律法规信息查询'],
			[1002,'政策信息查询'],
			[3,'中型企业'],
			[4,'大型企业'],
			[5,'创业个人或团队'],
			[6,'个体工商户'],
			[7,'事业单位'],
			[8,'社会团体'],
			[9,'民办非企业'],
			[0,'其他']],		
		displayField: 'value'})
});