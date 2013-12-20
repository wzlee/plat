Ext.define('plat.view.business.ApprBiddingServiceWindow', {
    extend: 'Ext.window.Window',
	xtype:'apprbiddingservicewindow',
	id:'apprbiddingservicewindow',
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
		            	id:'apprbiddingserviceform',
		            	layout: {
					        type: 'column'
					    },
					    bodyPadding:'10',
					    items:[{
				                    xtype: 'hiddenfield',
				                    name:'id'
				                },
				                {
				                	xtype:'hiddenfield',
				                	name:'status'
				                },
				                {
									xtype     : 'textareafield',
							        grow      : true,
							        name      : 'remark',
							        emptyText : '审核信息',
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
					action:'cancel'
				}
			]
        });
        me.callParent(arguments);
    }
});