Ext.define('plat.view.policy.FinancialCategoryWin', {
	extend : 'Ext.window.Window',
	xtype : 'financialcategorywindow',
	title : '添加类别',
    height : 280,
    width: 450,
    frame : true,
    layout : 'fit',
    modal : true,
    closeAction : 'hide',
    buttonAlign : 'center',
    items : {
    	xtype : 'financialcategoryform'
    },
    init : function () {
    	console.log('FinancialCategoryWin was initialized!!!');
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