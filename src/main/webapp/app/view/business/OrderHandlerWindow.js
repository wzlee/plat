Ext.define('plat.view.business.OrderHandlerWindow', {
    extend: 'Ext.window.Window',
    xtype: 'orderhandlerwindow',
    width: 480,    
    height: 380,
    buttonAlign:'center',
    layout: 'fit',
    modal:true,
    closeAction : 'hide',
    title: '订单处理',
    initComponent: function() {
        var me = this;
		
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id:'orderhandlerform',
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
				        	xtype: 'hiddenfield',
				        	name:'id'
				        },
				        {
				        	xtype: 'displayfield',
				        	name:'orderNumber',
				        	fieldLabel:'订单编号',
				        	width:200,
				        	submitValue:true
				        },
				        {
				        	xtype: 'displayfield',
				        	name:'phone',
				        	fieldLabel:'联系电话',
				        	width:200,
				        	submitValue:true
				        },	
				        {
				        	xtype: 'displayfield',
				        	name:'userName',
				        	fieldLabel:'申请人姓名',
				        	width:200,
				        	submitValue:true
				        },	
				        {
				        	xtype: 'displayfield',
				        	name:'service.serviceName',
				        	fieldLabel:'申请的服务',
				        	width:220,
				        	submitValue:true
				        },
				        {
				        	xtype: 'displayfield',
				        	name:'email',
				        	fieldLabel:'邮箱',
				        	width:200,
				        	submitValue:true
				        },
				        {
				        	xtype: 'displayfield',
				        	name:'transactionPrice',
				        	fieldLabel:'价格',
				        	width:200,
				        	submitValue:true
				        },				        
				        {
				        	xtype:'hiddenfield',
				        	name:'createTime'
				        },
				        {
				        	xtype:'hiddenfield',
				        	name:'remark'	
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'buyer.id'
				        },
				        {
				        	xtype: 'hiddenfield',
				        	name:'service.id'
				        },
                        {
                            xtype: 'displayfield',                            
                            fieldLabel: '买家',                            
                            width: 200,
                            submitValue: true,
                            name:'buyer.enterprise.name'   
                        },
                        {
                            xtype: 'displayfield',                            
                            fieldLabel: '卖家',                            
                            width: 200,
                            submitValue: true,
                            name:'service.enterprise.name'  
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