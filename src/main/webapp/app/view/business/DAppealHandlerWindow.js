Ext.define('plat.view.business.DAppealHandlerWindow', {
    extend: 'Ext.window.Window',
    xtype: 'dappealhandlerwindow',
    width: 480,    
    height: 380,
    buttonAlign:'center',
    layout: 'fit',
    modal:true,
    closeAction : 'hide',
    title: '申诉处理',
    initComponent: function() {
        var me = this;
		
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id:'dappealhandlerform',
                    bodyPadding: 10,
                    layout: 'column',                    
    				defaults:{
					        labelWidth:80,
					        labelAlign:'right',
					        msgTarget: 'side',
					        margin:'2'
					    },
                    items: [ 
                    	{
                    		xtype:'hiddenfield',
                    		name:'id'
                    	},
                    	{
				        	xtype: 'hiddenfield',
				        	name:'goodsOrder.id'
				        },
				        {
				        	xtype: 'displayfield',
				        	name:'goodsOrder.orderNumber',
				        	fieldLabel:'订单编号',
				        	width:200,
				        	submitValue:true
				        },
				        {
				        	xtype: 'displayfield',
				        	name:'goodsOrder.phone',
				        	fieldLabel:'联系电话',
				        	width:200,
				        	submitValue:true
				        },	
				        {
				        	xtype: 'displayfield',
				        	name:'goodsOrder.userName',
				        	fieldLabel:'联系人姓名',
				        	width:200,
				        	submitValue:true
				   
				        },	
				        {
				        	xtype: 'displayfield',
				        	name:'goodsOrder.biddingService.name',
				        	fieldLabel:'申请的服务',
				        	width:220,
				        	submitValue:true
				        },
				        {
				        	xtype: 'displayfield',
				        	name:'goodsOrder.email',
				        	fieldLabel:'邮箱',
				        	width:200,
				        	submitValue:true
				        },
				        {
				        	xtype: 'displayfield',
				        	name:'goodsOrder.transactionPrice',
				        	fieldLabel:'价格',
				        	width:200,
				        	submitValue:true
				        },				        
				        {
				        	xtype:'hiddenfield',
				        	name:'goodsOrder.createTime'
				        },
				        {
				        	xtype:'hiddenfield',
				        	name:'goodsOrder.remark'	
				        },
//				        {
//				        	xtype: 'hiddenfield',
//				        	name:'goodsOrder.buyer.id'
//				        },
//				        {
//				        	xtype: 'hiddenfield',
//				        	name:'goodsOrder.service.id'
//				        },
                        {
                            xtype: 'displayfield',                            
                            fieldLabel: '买家',                            
                            width: 200,
                            submitValue: true,
                            name:'goodsOrder.biddingService.user.enterprise.name'   
                        },
                        {
                            xtype: 'displayfield',                            
                            fieldLabel: '卖家',                            
                            width: 200,
                            submitValue: true,
                            name:'goodsOrder.biddingService.rname'  
                        },
                        {
				        	xtype: 'handlermode',				        					        	
				        	fieldLabel: '处理方式',
				        	allowBlank:false,
				            afterLabelTextTpl: required,
				        	width:250,
				        	name:'handlerMode'
				        },                       
                        {
				        	xtype: 'htmleditor',				        	
				        	width: 400,
				        	height:130,
				        	fieldLabel: '备注',
				        	name:'handlerRemark'
				        }
                    ]
                }
            ],
            buttons:[
				{
					text:'提交',
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