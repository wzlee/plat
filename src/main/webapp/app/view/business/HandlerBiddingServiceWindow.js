Ext.define('plat.view.business.HandlerBiddingServiceWindow', {
    extend: 'Ext.window.Window',
	xtype:'handlerbiddingservicewindow',
	id:'handlerbiddingservicewindow',
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
		            	id:'handlerbiddingserviceform',
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
							        emptyText : '处理信息',
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