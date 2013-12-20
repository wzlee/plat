Ext.define('plat.view.mall.MallCategoryPanel',{
	extend:'Ext.panel.Panel',
	xtype:'mallcategorypanel',
	
    title:'服务类别管理',
    id:'fwlbgl',
	closable:true,
    closeAction:'hide',
    layout:'border',
    initComponent: function() {
        Ext.apply(this, {
        	items :[
	    		{
	        		xtype: 'mallcategorygrid',
	        		region:'west',
	        		width:'60%',
	        		collapsible:true,
	        		split:true
	        	},
	        	{
	        		xtype:'mallservicegrid',
	        		region:'center'
	        		
	        	}
	    	]
        });
        this.callParent();
    }
});