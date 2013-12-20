Ext.define('plat.view.business.HOrderDetailWindow', {
    extend: 'Ext.window.Window',
    xtype: 'horderdetailwindow',
    width: 740,    
    height: 450,
    buttonAlign:'center',
    layout: {
    	type:'border'
    },
    modal:true,
    closeAction : 'hide',
    title: '订单详情',
    defaults:{
    	split:true
    },
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
            	{
            		xtype:'form',
            		region:'west',
            		id:'horderdetailform',
//            		title:'订单信息',
            		layout: 'column',   
            		width:235,
            		height:320,
    				defaults:{
						labelWidth:80,
					 	labelAlign:'right',
					 	msgTarget: 'side',
					    margin:'2'
					},
            		items:[
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
				        	fieldLabel:'申请人姓名',
				        	width:200,
				        	submitValue:true
				        },	
				        {
				        	xtype: 'displayfield',
				        	name:'goodsOrder.serviceName',
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
                        {
                            xtype: 'displayfield',                            
                            fieldLabel: '买家',                            
                            width: 200,
                            submitValue: true,
                            name:'goodsOrder.buyer.enterprise.name'   
                        },
                        {
                        	xtype:'hiddenfield',
                        	name:'goodsOrder.service.id'
                        }, 
                        {
                            xtype: 'displayfield',                            
                            fieldLabel: '卖家',                            
                            width: 200,
                            submitValue: true,
                            name:'goodsOrder.service.enterprise.name'  
                        }
            		]
            	},
            	{
            		xtype:'evaluationgrid',
            		width:235,
            		region:'south',
            		height:110
//            		title:'评价信息'
            	},
            	{
            		xtype:'orderinfogrid',
            		region:'center'
//            		title:'订单流水'
            	}
            ]
        });
        me.callParent(arguments);
    }
});