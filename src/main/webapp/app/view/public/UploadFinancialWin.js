Ext.define('plat.view.public.UploadFinancialWin', {
	extend : 'Ext.window.Window',
	xtype : 'uploadfinancialwin',
    height : 120,
    width: 330,
    layout : 'fit',
    closeAction : 'hide',
    items : {
    	xtype : 'uploadfinancialform'
    },
    modal : true,
    buttons : [{
    	text : '提交',
    	name : 'submit'
    }, {
    	text : '取消',
    	name : 'cancel'
    }]
});