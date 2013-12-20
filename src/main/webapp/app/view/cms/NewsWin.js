Ext.define('plat.view.cms.NewsWin', {
	extend : 'Ext.window.Window',
	xtype : 'newswindow',
    height : 400,
    width: 680,
    frame : true,
    layout : 'fit',
    closeAction : 'hide',
    modal:true,
    buttonAlign : 'center',
    items : {
    	xtype : 'newsform'
    },
    buttons : [{
    	text : '提交',
    	name : 'add'
    }, {
    	text : '提交',
    	name : 'modify',
    	hidden : true
    }, {
    	text : '取消',
    	name : 'cancel'
    }],
    init : function () {
    	console.log('NewsWin was initialized!!!');
    	this.callParent(arguments);
    }
});