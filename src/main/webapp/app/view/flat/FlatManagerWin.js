Ext.define('plat.view.flat.FlatManagerWin', {
	extend : 'Ext.window.Window',
	xtype : 'flatmanagerwindow',
	title : '添加平台管理员',
    height : 200,
    width: 360,
    frame : true,
    layout : 'fit',
    closeAction : 'hide',
    buttonAlign : 'center',
    items : {
    	xtype : 'flatmanagerform'
    },
    init : function () {
    	console.log('FlatManagerWin was initialized!!!');
    	this.callParent(arguments);
    },
    buttons : [{
    	text : '保存',
    	name : 'save'
    }, {
    	text : '取消',
    	name : 'cancel'
    }]
});