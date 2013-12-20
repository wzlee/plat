Ext.define('plat.view.business.OrderDetailWindow', {
    extend: 'Ext.window.Window',
    xtype: 'orderdetailwindow',
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
            		id:'orderdetailform',
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
				        	name:'serviceName',
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
                            xtype: 'displayfield',                            
                            fieldLabel: '买家',                            
                            width: 200,
                            submitValue: true,
                            name:'buyer.enterprise.name'   
                        },
                        {
                        	xtype:'hiddenfield',
                        	name:'service.id'
                        },
                        {
                            xtype: 'displayfield',                            
                            fieldLabel: '卖家',                            
                            width: 200,
                            submitValue: true,
                            name:'service.enterprise.name'
                            
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