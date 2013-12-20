Ext.define('plat.view.manager.AuthPanel',{
	extend:'Ext.panel.Panel',
	xtype:'authpanel',
	
    title:'权限管理',
	closable:true,
    closeAction:'hide',
    layout:'border',
    initComponent: function() {
        Ext.apply(this, {
        	items :[
	    		{
	        		xtype: 'rolesgrid',
	        		region:'west',
	        		width:'70%',
	        		collapsible:true,
	        		split:true
	        	},
	        	{
	        		xtype:'menugrid',
	        		region:'center'
	        	}
	    	]
        });
        this.callParent();
    }
});