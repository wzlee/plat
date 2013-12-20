Ext.define('plat.view.mobileapp.AppMessageWin', {
	extend : 'Ext.window.Window',
	xtype : 'appmessagewindow',
    height : 250,
    width: 430,
    frame : true,
    title : '填写消息',
    buttonAlign : 'center',
    modal : true,
    layout : 'fit',
    closeAction : 'hide',
    items : {
    	xtype : 'appmessageform'
    },
    buttons : [{
    	text : '提交',
    	name : 'save'
    }, {
    	text : '取消',
    	name : 'cancel'
    }]
});