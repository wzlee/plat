Ext.define('plat.view.layout.combo.ServiceOrgCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'serviceorgcombo',
	
	queryMode: 'remote',
    displayField: 'orgName',	
    valueField: 'orgName',
    
    initComponent:function(){
    	this.store = Ext.create('Ext.data.ArrayStore',{
    		fields:['id','orgName'],    		
    		
    		proxy:{
    			type:'ajax',    			
    			url:'serviceorg/load'
    		}
    	});
    	this.callParent(arguments);
    }
});