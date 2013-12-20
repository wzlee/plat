Ext.define('plat.view.layout.combo.OrgEnterpriseCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'orgenterprisecombo',
	id:'orgenterprisecombo',
	queryMode: 'local',
    displayField: 'text',
    valueField: 'id',    
    initComponent:function(){
    	this.store = Ext.create('Ext.data.ArrayStore',{				            			
			fields:['id','name'],
			proxy:{
				type:'ajax',
				actionMethods: {  
					read: 'POST'
				},				
			url:'enter/loadOrgEnterprise'				    			
		}
		});
    	this.callParent(arguments);
    }
});