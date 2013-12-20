Ext.define('plat.view.policy.RulePanel',{
	extend:'Ext.panel.Panel',
	xtype:'rulepanel',
	
    title:'规则配置',
    id:'gzpz',
	closable:true,
    closeAction:'hide',
    layout:'border',
    initComponent: function() {
        Ext.apply(this, {
        	items :[
	    		{
	        		xtype: 'rulegrid',
	        		region:'west',
	        		width:'40%',
	        		collapsible:true,
	        		split:true
	        	},
	        	{
	        		xtype:'rulereportinggrid',
	        		region:'center'
	        		
	        	}
	    	]
        });
        this.callParent();
    }
});