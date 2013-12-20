Ext.define('plat.view.staffrole.SAuthPanel',{
	extend:'Ext.panel.Panel',
	xtype:'sauthpanel',
	
    title:'权限管理',
	closable:true,
    closeAction:'hide',
    layout:'border',
    initComponent: function() {
        Ext.apply(this, {
        	items :[
	    		{
	        		xtype: 'srolesgrid',
	        		region:'west',
	        		width:'70%',
	        		collapsible:true,
	        		split:true
	        	},
	        	{
	        		xtype:'smenugrid',
	        		region:'center'
	        	}
	    	]
        });
        this.callParent();
    }
});