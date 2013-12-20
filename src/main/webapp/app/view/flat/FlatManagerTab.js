
Ext.define('plat.view.flat.FlatManagerTab', {
	extend : 'Ext.tab.Panel',
//	title : '窗口信息',
	id : 'ckgly',
	xtype: 'flatmanagertab',
	closable : true,
	closeAction : 'hide',
	autoScroll : true,
    items: [{
    	xtype : 'flatmanagergrid'
    }, {
    	xtype : 'flatusergrid'
    }]
});

