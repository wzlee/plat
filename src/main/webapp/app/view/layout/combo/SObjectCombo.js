Ext.define('plat.view.layout.combo.SObjectCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'sobjectcombo',	
	store:new Ext.data.SimpleStore({
		fields:['value','text'],
		data:[
			[1,'微型企业'],
			[2,'小型企业'],
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