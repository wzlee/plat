Ext.define('plat.view.public.UploadWin', {
	extend : 'Ext.window.Window',
	xtype : 'uploadwindow',
    height : 120,
    width: 330,
    layout : 'fit',
    closeAction : 'hide',
    items : {
    	xtype : 'uploadform'
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