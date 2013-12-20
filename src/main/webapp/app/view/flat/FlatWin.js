Ext.define('plat.view.flat.FlatWin', {
	extend : 'Ext.window.Window',
	xtype : 'flatwindow',
	title : '窗口信息',
    height : 400,
    width: 600,
    frame : true,
    layout : 'fit',
    closeAction : 'hide',
    buttonAlign : 'center',
    items : {
    	xtype : 'flatinfodisplay'
    },
    init : function () {
    	console.log('FlatWin was initialized!!!');
    	this.callParent(arguments);
    }
});