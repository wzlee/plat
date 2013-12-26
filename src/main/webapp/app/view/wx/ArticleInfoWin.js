Ext.define('plat.view.wx.ArticleInfoWin', {
	extend : 'Ext.window.Window',
	xtype : 'articleinfowindow',
    height : 460,
    width: 680,
    frame : true,
    layout : 'fit',
    closeAction : 'hide',
    modal:true,
    buttonAlign : 'center',
    items : {
    	xtype : 'articleinfoform'
    },
    buttons : [{
    	text : '提交',
    	name : 'submit'
    }, {
    	text : '取消',
    	name : 'cancel'
    }],
    init : function () {
    	console.log('ArticleInfoWin was initialized!!!');
    	this.callParent(arguments);
    }
});