Ext.define('plat.view.business.GoodsDetailWindow', {
    extend: 'Ext.window.Window',
	xtype:'goodsdetailwindow',
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
		            	id:'goodsdetailform',
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
				                    name:'service.serviceNo',
				                    submitValue: true,
				                    fieldLabel: '服务编码'
				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 259,				                    
				                    //disabled:true,				                    
				                    fieldLabel: '服务来源',		
				                    name:'service.serviceSource',
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
				                    name:'service.enterprise.name',
				                    width: 259,
				                    fieldLabel: '所属企业'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'service.serviceName',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '服务名称'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'service.currentStatus',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '当前状态',
				                    renderer:function(v){
				        				return PlatMap.Service.currentStatus[v];
				        			}
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'service.lastStatus',
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
				                    name:'service.registerTime',
				                    submitValue: true,
				                    fieldLabel: '注册时间'				                    
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'service.serviceNum',
				                    width: 259,
				                    submitValue: true,
				                    fieldLabel: '服务次数'
				                    
				                },
				                {				                		
					            	xtype: 'displayfield',
					            	width: 259,
					            	name:'service.costPrice',
					              	fieldLabel: '服务价格'
					           	},
				                {
				                	xtype: 'displayfield',
				                    name:'service.picture',
				                    width: 259,
				                    fieldLabel: '服务图片'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 350,
				                    name:'service.category.text',				                    
				                    fieldLabel: '服务类别'
				                },
				                {
				                    xtype: 'displayfield',
				                    fieldLabel: '服务方式',
				                    width: 550,
				                    name:'service.serviceMethod'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 180,
				                    name:'service.linkMan',
				                    fieldLabel: '联系人'
				                },
				                {
				                    xtype: 'displayfield',
				                    name:'service.tel',
				                    width: 180,
				                    fieldLabel: '电话'
				                },
				                {
				                    xtype: 'displayfield',
				                    width: 180,
				                    name:'service.email',
				                    fieldLabel: '邮箱',
				                    vtype:'email'
				                },
				              
				                {
				                    xtype: 'htmleditor',
				                    height: 150,
//				                    disabled:true,
				                    width:550,
				                    name:'service.serviceProcedure',
				                    fieldLabel: '服务描述'
				                }
					    	]
		            	}
            		]	
        });

        me.callParent(arguments);
    }

});