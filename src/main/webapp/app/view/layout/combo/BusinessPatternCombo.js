Ext.define('plat.view.layout.combo.BusinessPatternCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'businesspatterncombo',
	displayField: 'text',
	valueField: 'id',
	store:new Ext.data.SimpleStore({
		fields:['id','text'],
		data:[
			['0',''],
			['1','生产型'],
			['2','贸易型'],
			['3','服务型'],
			['4','生产型、贸易型'],
			['5','贸易型、服务型'],
			['6','生产型、贸易型、服务型']					
			]	
		})
});