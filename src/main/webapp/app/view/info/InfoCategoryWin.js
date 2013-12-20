Ext.define('plat.view.info.InfoCategoryWin', {
	extend : 'Ext.window.Window',
	xtype : 'infocategorywin',
	title : '添加消息类别',
    height : 280,
    width: 450,
    frame : true,
    modal:true,
    layout : 'fit',
    closeAction : 'hide',
    buttonAlign : 'center',
    items : {
    	xtype : 'infocategoryform'
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