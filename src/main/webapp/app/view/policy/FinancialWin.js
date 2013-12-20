Ext.define('plat.view.policy.FinancialWin', {
	extend : 'Ext.window.Window',
	xtype : 'financialwin',
    height : 600,
    width: 755,
    frame : true,
    title : '新增/修改资讯',
    buttonAlign : 'center',
    modal : true,
    layout : 'fit',
    closeAction : 'hide',
    items : {
    	xtype : 'financialform'
    },
    buttons : [{
    	text : '提交',
    	name : 'save'
    }, {
    	text : '取消',
    	name : 'cancel'
    }]
  
});