Ext.define('plat.view.layout.combo.AgencyLevel',{
	extend:'Ext.form.field.ComboBox',
	alias:'widget.agencylevel',
	
	storeId:'level',
	width:80,
	queryMode: 'local',
    displayField: 'name',
    valueField: 'value',
    
    initComponent:function(){
    	this.store = new Ext.data.SimpleStore({
    		fields:['value','name'],
    		data:[[1,'1星推荐'],[2,'2星推荐'],[3,'3星推荐'],[4,'4星推荐'],[5,'5星推荐']]
    	});
    	this.callParent(arguments);
    }
});