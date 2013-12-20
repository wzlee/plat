Ext.define('plat.view.system.DruidPanel',{
	extend:'Ext.panel.Panel',
	xtype:'druidpanel',
    title:'系统数据库监控',
	closable:true,
    closeAction:'hide',
    initComponent: function() {
        Ext.apply(this, {
//        	loader: {
//		        url: 'druid/index.html',
//		        autoLoad: true
//		    }
        });
        this.callParent();
    }
});