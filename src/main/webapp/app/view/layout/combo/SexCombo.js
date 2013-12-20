Ext.define('plat.view.layout.combo.SexCombo',{
	extend:'Ext.form.field.ComboBox',
	alias:'widget.sexcombo',
	width:50,
	store:new Ext.data.SimpleStore({fields:['value','text'],data:[[1,'男'],[2,'女']]})
});