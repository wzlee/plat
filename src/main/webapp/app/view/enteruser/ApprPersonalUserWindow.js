Ext.define('plat.view.enteruser.ApprPersonalUserWindow', {
    extend: 'Ext.window.Window',
	xtype:'apprpersonaluserwindow',
	
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
		            	id:'apprpersonaluserform',
		            	layout: {
					        type: 'column'
					    },
					    bodyPadding:'10',
					    items:[{
				                    xtype: 'hiddenfield',
				                    name:'id'
				                },{
				                    xtype: 'hiddenfield',
				                    name:'approveStatus'
				                },{
									xtype     : 'textareafield',
							        grow      : true,
							        name      : 'approvedContext',
							        emptyText : '审核说明',
							        validateBlank : true,
							        width     : 270
								}]
		            	}
            		],
			buttons:[
				{
					text:'确定',
					action:'submit'
				},
				{
					text:'取消',
					action:'esc'
				}
			]
        });
        me.callParent(arguments);
    }
});