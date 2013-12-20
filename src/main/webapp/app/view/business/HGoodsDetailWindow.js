Ext.define('plat.view.business.HGoodsDetailWindow', {
    extend: 'Ext.window.Window',
	xtype:'hgoodsdetailwindow',
    width: 600,
    height: 510,
	layout:'fit',
	modal:true,
	buttonAlign:'center',
	closeAction : 'hide',
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
		            {	
		            	xtype:'form',
		            	id:'hgoodsdetailform',
		            	layout: {
					        type: 'column'
					    },
					    defaults:{
					        labelWidth:60,
					        labelAlign:'right',
					        margin:'2',
//						    disabled:true,
						    editable:false,
					        emptyText:'--暂无数据--'
					    },
					    bodyPadding:'10',
					    items:[
					    	 	{
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'goodsOrder.service.serviceNo',
				                    submitValue: true,
				                    fieldLabel: '服务编码'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,				                    
				                    //disabled:true,				                    
				                    fieldLabel: '服务来源',		
				                    name:'goodsOrder.service.serviceSource',
				                    renderer:function(v){
				                    	return PlatMap.Service.serviceSource[v];
				                    },
				                    submitValue:true
//				                    items: [
//				                        {
//				                            xtype: 'radiofield',
//				                            boxLabel: '运营平台',
//				                            inputValue: '1',
//				                            readOnly:true,
//				                            columnWidth:50,				                            
//				                            name: 'serviceSource'
//				                            
//				                            
//				                        },
//				                        {
//				                            xtype: 'radiofield',
//				                            boxLabel: '服务机构',
//				                            inputValue: '2',
//				                            readOnly:true,
//				                            columnWidth:50,				                            
//				                            name: 'serviceSource'
//				                            
//				                        }
//				                    ]
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'goodsOrder.service.enterprise.name',
				                    width: 259,
				                    fieldLabel: '所属企业'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'goodsOrder.service.serviceName',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '服务名称'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'goodsOrder.service.currentStatus',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '当前状态',
				                    renderer:function(v){
				        				return PlatMap.Service.currentStatus[v];
				        			}
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'goodsOrder.service.lastStatus',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '上一状态',
				                    renderer:function(v){
				        				return PlatMap.Service.currentStatus[v]==null ? '':PlatMap.Service.currentStatus[v];
				        			}  
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,
				                    name:'goodsOrder.service.registerTime',
				                    submitValue: true,
				                    fieldLabel: '注册时间'				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'goodsOrder.service.serviceNum',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '服务次数'
				                    
				                },
				                 {				                		
					            	xtype: 'displayfield',
					            	width: 259,
					            	name:'goodsOrder.service.costPrice',
					              	fieldLabel: '服务价格'
					           	},
				                {
				                	xtype: 'displayfield',
				                    name:'goodsOrder.service.picture',
				                    width: 259,
				                    fieldLabel: '服务图片'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 350,
				                    name:'goodsOrder.service.category.text',				                    
				                    fieldLabel: '服务类别'
				                },
				                {
				                    xtype: 'displayfield',
				                    fieldLabel: '服务方式',
				                    width: 550,
				                    name:'goodsOrder.service.serviceMethod'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 180,
				                    name:'goodsOrder.service.linkMan',
				                    fieldLabel: '联系人'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'goodsOrder.service.tel',
				                    width: 180,
				                    fieldLabel: '电话'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 180,
				                    name:'goodsOrder.service.email',
				                    fieldLabel: '邮箱',
				                    vtype:'email'
				                },
//				                {
//				                    xtype: 'displayfield',
//				                    width: 350,
//				                    name:'goodsOrder.service.chargeMethod',
//				                    labelWidth:61,
//				                    fieldLabel: '收费模式'
//				                },
				                {
				                    xtype: 'htmleditor',
				                    height: 150,
//				                    disabled:true,
				                    width:550,
				                    name:'goodsOrder.service.serviceProcedure',
				                    fieldLabel: '服务描述'
				                }
					    	]
		            	}
            		]
            		
        });

        me.callParent(arguments);
    }

});