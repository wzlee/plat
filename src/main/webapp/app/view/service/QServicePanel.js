/**
 * xuwf statistics服务数据统计
 */
Ext.define('plat.view.service.QServicePanel',{
	extend:'Ext.panel.Panel',
	xtype:'qservicepanel',
    title:'服务数据统计查询',
	id: 'qservicepanel',
	closable:true,
    closeAction:'hide',
    layout:'border',
    initComponent: function() {
         Ext.apply(this, {
        	items :[
        	
	        	{
	        		xtype: 'qservicegrid',
	        		width:600,
	        		region:'center'
	        	}
	    	]
        });
        this.callParent();
    }
});