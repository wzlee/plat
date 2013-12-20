Ext.define('plat.view.cms.CmsPanel',{
	extend:'Ext.panel.Panel',
	alias:'widget.cmspanel',
    border : true,
    layout : 'border',
    closable:true,
    closeAction:'hide',
    initComponent: function() {
        this.callParent();
    },
    items: [{
    	xtype: 'channelTree'
    },{
    	xtype: 'templatePanel'
    }]
});