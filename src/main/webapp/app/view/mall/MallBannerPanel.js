Ext.define('plat.view.mall.MallBannerPanel',{
	extend:'Ext.panel.Panel',
	xtype:'mallbannerpanel',
    id:'bnpzgl',
    title:'Banner配置管理',	
	closable:true,
    closeAction:'hide',
    layout:'border',
    initComponent: function() {
        Ext.apply(this, {
        	items :[
	    		{
	        		xtype: 'mallbannergrid',
	        		region:'west',
	        		width:'25%',
	        		collapsible:true,
	        		split:true
	        	},
	        	{
	        		xtype:'bannerPanel',
	        		region:'center'
	        		
	        	}
	    	]
        });
        this.callParent();
    }
});