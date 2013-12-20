Ext.define('plat.view.layout.combo.UserStatus',{
	extend:'Ext.form.field.ComboBox',
	alias:'widget.userstatus',
	width:50,
	store:new Ext.data.SimpleStore({fields:['value','text'],data:[[0,'未激活'],[1,'已激活'],[-1,'已锁定']]})
});