Ext.define('plat.view.staffrole.EAuthPanel',{
	extend:'Ext.panel.Panel',
	xtype:'eauthpanel',
	
    title:'权限管理',
	closable:true,
    closeAction:'hide',
    layout:'border',
    initComponent: function() {
        Ext.apply(this, {
        	items :[
	    		{
	        		xtype: 'erolesgrid',
	        		region:'west',
	        		width:'70%',
	        		collapsible:true,
	        		split:true
	        	},
	        	{
	        		xtype:'emenugrid',
	        		region:'center'
	        	}
	    	]
        });
        this.callParent();
    }
});