Ext.define('plat.view.layout.combo.IndustryTypeCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'industrytypecombo',
	displayField: 'text',
	valueField: 'id',
	store:new Ext.data.SimpleStore({
		fields:['id','text'],
		data:[
			[0,''],
			[1,'电子装备'],
			[2,'服装'],
			[3,'港澳资源'],
			[4,'工业设计'],
			[5,'机械'],
			[6,'家具'],
			[7,'LED'],
			[8,'软件'],
			[9,'物流'],
			[10,'物联网'],
			[11,'新材料'],
			[12,'医疗器械'],
			[13,'钟表'],
			[14,'珠宝'],
			[16,'其他'],
			[16,'枢纽平台']
			]
		}		
		)
});