Ext.define('plat.view.service.ApprServiceWindow', {
    extend: 'Ext.window.Window',
	xtype:'apprservicewindow',
	
    width: 300,
    height: 160,
	layout:'fit',
	closeAction : 'hide',
	buttonAlign:'center',
	modal : true,
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
		            {	
		            	xtype:'form',
		            	id:'apprserviceform',
		            	layout: 'column',					    
					    bodyPadding:'10',
					    items:[{
				                    xtype: 'hiddenfield',
				                    name:'rowIndex'
				                },
				                {
									xtype     : 'textarea',
							        grow      : true,
							        name      : 'context',
							        emptyText : '审批说明',
							        validateBlank : true,
							        width     : 270
								}]
		            	}
            		],
			buttons:[
				{
					text:'确定',
					action:'enter'
				},
				{
					text:'取消',
					action:'cancle'
				}
			]
        });
        me.callParent(arguments);
    }
});