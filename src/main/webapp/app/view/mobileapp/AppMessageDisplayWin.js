Ext.define('plat.view.mobileapp.AppMessageDisplayWin', {
	extend : 'Ext.window.Window',
	xtype : 'appmessagedisplaywindow',
    height : 250,
    width: 430,
    frame : true,
    title : '消息内容',
    buttonAlign : 'center',
    modal : true,
    layout : 'fit',
    closeAction : 'hide',
    items : {
    	xtype : 'appmessagedisplayform'
    },
    buttons : [{
    	text : '关闭',
    	name : 'close'
    }]
});