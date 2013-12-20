Ext.define('plat.view.layout.combo.AgencyStatus',{
	extend:'Ext.form.field.ComboBox',
	alias:'widget.agencystatus',
	width:50,
	store:new Ext.data.SimpleStore({fields:['value','text'],data:[[0,'未激活'],[1,'已激活'],[-1,'已关闭']]})
});