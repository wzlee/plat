Ext.define('plat.view.layout.combo.PropertyCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'propertycombo',
	valueField: 'id',
	displayField: 'text',
	store:new Ext.data.SimpleStore({
		fields:['id','text'],
		data:[
			[0,''],
			[1,'企业'],
			[2,'事业单位'],
			[3,'社会团体'],
			[4,'个体工商户'],
			[5,'民办非企业'],
			[6,'其他']			
			]	
		})
});