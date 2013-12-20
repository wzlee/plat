Ext.define('plat.view.policy.PolicyWin', {
	extend : 'Ext.window.Window',
	xtype : 'policywindow',
    height : 500,
    width: 678,
    frame : true,
    title : '新增/修改资讯',
    buttonAlign : 'center',
    modal : true,
    layout : 'fit',
    closeAction : 'hide',
    items : {
    	xtype : 'policyform'
    },
    buttons : [{
    	text : '提交',
    	name : 'save'
    }, {
    	text : '取消',
    	name : 'cancel'
    }]
  
});