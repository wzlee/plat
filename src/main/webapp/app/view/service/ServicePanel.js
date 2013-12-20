Ext.define('plat.view.service.ServicePanel',{
	extend:'Ext.panel.Panel',
	xtype:'servicepanel',
	
    title:'服务管理',
	id: 'servicepanel',
	closable:true,
    closeAction:'hide',
    layout:'border',
    initComponent: function() {
        Ext.apply(this, {
        	items :[
	    		{
	        		xtype: 'categorygrid',
	        		region:'west',
	        		width:200,
	        		collapsible:true,
	        		split:true
	        	},
	        	{
	        		xtype: 'servicegrid',
	        		region:'center'
	        	}
	    	]
        });
        this.callParent();
    }
});