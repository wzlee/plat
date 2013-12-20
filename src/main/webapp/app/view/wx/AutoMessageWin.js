Ext.define('plat.view.wx.AutoMessageWin', {
	extend : 'Ext.window.Window',
	xtype : 'automessagewindow',
    height : 265,
    width: 460,
    frame : true,
    layout : 'fit',
    closeAction : 'hide',
    modal:true,
    buttonAlign : 'center',
    items : {
    	xtype : 'automessageform'
    },
    buttons : [{
    	text : '提交',
    	name : 'submit'
    }, {
    	text : '取消',
    	name : 'cancel'
    }],
    init : function () {
    	console.log('AutoMessageWin was initialized!!!');
    	this.callParent(arguments);
    }
});