Ext.define('plat.view.wx.WXNewsWin', {
	extend : 'Ext.window.Window',
	xtype : 'wxnewswindow',
    height : 400,
    width: 680,
    frame : true,
    layout : 'fit',
    closeAction : 'hide',
    modal:true,
    buttonAlign : 'center',
    items : {
    	xtype : 'wxnewsform'
    },
    buttons : [{
    	text : '提交',
    	name : 'submit'
    }, {
    	text : '取消',
    	name : 'cancel'
    }],
    init : function () {
    	console.log('WXNewsWin was initialized!!!');
    	this.callParent(arguments);
    }
});