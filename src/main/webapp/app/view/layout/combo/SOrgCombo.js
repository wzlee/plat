Ext.define('plat.view.layout.combo.SOrgCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'sorgcombo',
	
	queryMode: 'remote',
    displayField: 'servcieStatus',
    valueField: 'servcieStatus',
    
    initComponent:function(){
    	this.store = Ext.create('Ext.data.ArrayStore',{
    		fields:['serviceStatus'],
    		proxy:{
    			type:'ajax',
    			url:'service/loadOrg'
    		}
    	});
    	this.callParent(arguments);
    }
});