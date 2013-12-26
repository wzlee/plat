Ext.define('plat.view.service.NServiceGrid',{
	extend:'Ext.grid.Panel',
	xtype:'nservicegrid',
    
	title:'服务列表',
	id:'xzfwgl',
	closable:true,
    closeAction:'hide',
	columnLines:true,
    store:'service.NServiceStore',
    tbar :['-',				
				{xtype: 'triggerfield',name:'serviceName',width:200,triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
					display:false,	//自定义变量判断triggercls是否display
					fieldLabel : '服务名称',
    				emptyText: '请输入服务名称关键字...',  
    				labelWidth : 60,
    				onTriggerClick:function(){
    					this.reset();
    					this.display = false;
	    				this.triggerCell.setDisplayed(false);
	    				this.setWidth(200);
    				}
    			},'-',	{xtype: 'triggerfield',name:'orgName',width:200,triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
    				display:false,	//自定义变量判断triggercls是否display
    				fieldLabel : '机构名称',
    				emptyText: '请输入机构名称关键字...',    
    				labelWidth : 60,
    				onTriggerClick:function(){
    					this.reset();
    					this.display = false;
	    				this.triggerCell.setDisplayed(false);
	    				this.setWidth(200);
    				}
    			},'-',			
				{iconCls:'icon-search',text:'查找',action:'search'},'-',
//				{iconCls:'icon-add',text:'添加',name:'add',menu: [
//			        {text: '机构服务',name:'assist'},
//			        {text: '运营服务',name:'add'}
//    			]},
    			{iconCls:'icon-add',text:'添加服务',name:'add'},
    	'-','双击服务可以查看或者编辑服务'
	],
    initComponent: function() {
    	var me = this;
        Ext.apply(this, {
			columns: [
						new Ext.grid.column.RowNumberer({text:'#',align:'center',width:30}),
				        { text: '服务ID',align:'center', dataIndex: 'id',hidden:true},
//				        { text: '服务编码',align:'center',width:162, dataIndex: 'serviceNo',locked:true},
				        { text: '服务名称',align:'center',width:120, dataIndex: 'serviceName',locked:true},
				        { text: '添加时间',align:'center',width:144, dataIndex: 'registerTime',locked:true },
				        { text: '服务状态',align:'center',width:84, dataIndex: 'currentStatus',locked:true,
				        	renderer:function(value){
				        		return PlatMap.Service.currentStatus[value];
				        	}
				        },
				        { text: '服务机构',align:'center',width:120,dataIndex:'enterprise.name',locked:true},
				        { text: '所属窗口',align:'center',width:80, dataIndex: 'enterprise.industryType',locked:true,
		                	renderer:function(value){                		
		                		return PlatMap.Enterprises.industryType[value];
		                	}
		                },
		                {
				            xtype:'actioncolumn',
				            text:'配图',
				            align:'center',
				            sortable:false,
				            locked:true,
				            width:50,
				            items: [
				            	{
				            		icon:'jsLib/extjs/resources/themes/icons/scan.png',
					                tooltip: '查看服务配图',
					                handler: function(grid, rowIndex, colIndex, node, e, record, rowEl){
			                			this.fireEvent('pictureclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);	
					                }
					            }
				            ]
				        },
//				        {
//				       		header : '配图',
//				       		dataIndex : 'picture',
//				       		width : 50,
//				       		locked : true,
//				       		toolTip : '55',
//				       		align : 'center',
//				       		renderer : function (value) {
//				       			
//				       			if (value) {
//				       				if(value.indexOf('http') > -1){
//				       					return "<a href='" + value + "' class='fancybox'><img src='jsLib/extjs/resources/themes/icons/scan.png' /></a>";
//				       				}else {
//				       					return "<a href='upload/" + value + "' class='fancybox'><img src='jsLib/extjs/resources/themes/icons/scan.png' /></a>";
//				       				}					       			
//				       			} else {
//				       				return "<a href='resources/images/nopic.gif' class='fancybox'><img src='jsLib/extjs/resources/themes/icons/scan.png' /></a>";
//				       			}
//				       		}
//				        },
				        {
				            xtype:'actioncolumn',
				            text:'删除',
				            align:'center',
				            sortable:false,
				            locked:true,
				            width:50,
				            items: [
				            	{
					                iconCls:'icon-backup',
					                tooltip: '删除',
					                handler: function(grid, rowIndex, colIndex) {
					                    var record = grid.getStore().getAt(rowIndex);
					                    Ext.MessageBox.confirm('警告','确定删除【 '+record.data.serviceName+' 】吗?',function(btn){
								    		if(btn == 'yes'){
								    			record.set('lastStatus',record.data.currentStatus);
								    			record.set('currentStatus',5);
								    			record.set('locked',1);
								    			grid.getStore().update({
								    				params:record.data,
								    				callback:function(record,operation){
								    						var result =Ext.JSON.decode(operation.response.responseText);								    						
								    						if(result.success){
								    							Ext.example.msg('','<p align="center">服务【'+operation.params.serviceName+'】删除成功</p>');
								    						}else {
								    							Ext.Msg.alert('提示','服务【'+operation.params.serviceName+'】删除失败');
								    						}
								    					
								    				}
								    			});
//								    			grid.getStore().sync({
//										    			success: function(){
//										    				console.log('删除服务【'+record.data.serviceName+'】成功');
//										    				Ext.example.msg('','<p align="center">服务【'+record.data.serviceName+'】删除成功</p>');
//										    				},
//										    			failure:function(){
//										    				console.log('删除服务【'+record.data.serviceName+'】失败');
//										    				Ext.example.msg('','<p align="center">服务【'+record.data.serviceName+'】删除失败</p>');
//										    				}
//									    			});
													    			
											}
										});
					                },
					                isDisabled:function(grid, rowIndex, colIndex){
					                	return grid.getStore().getAt(rowIndex).data.currentStatus != 1;
					                }
				            	}
					        ]
				        },
				        {
				            xtype:'actioncolumn',
				            text:'上架',
				            align:'center',
				            sortable:false,
				            locked:true,
				            width:50,
				            items: [
				            	{
					                iconCls:'icon-up',
					                tooltip: '申请上架',
					                handler: function(grid, rowIndex, colIndex) {
					                    var record = grid.getStore().getAt(rowIndex);
					                    Ext.MessageBox.confirm('警告','确定申请上架【 '+record.data.serviceName+' 】吗?',function(btn){
								    		if(btn == 'yes'){
								    			Ext.Ajax.request({
    												url: 'service/checkService',
												    params: {
												        sid: record.data.id
												    },
												    success: function(response){
												        var result = Ext.JSON.decode(response.responseText);
												        if(result.success){
												        	record.set('lastStatus',record.data.currentStatus);
											    			record.set('currentStatus',2);
											    			record.set('locked',1);
											    			grid.getStore().update({
											    				params:record.data,
											    				callback:function(record,operation){
											    						var result =Ext.JSON.decode(operation.response.responseText);								    						
											    						if(result.success){
											    							Ext.example.msg('','<p align="center">服务【'+operation.params.serviceName+'】申请上架成功</p>');
											    						}else {
											    							Ext.Msg.alert('提示','服务【'+operation.params.serviceName+'】申请上架失败');
											    						}
												    					
											    					
											    				}
											    			});
												        }else {
												        	Ext.Msg.alert('提示',result.message+",无法上架");
												        }												        
												    }
												});
								    		}
					                    });
					                },
					                isDisabled:function(grid, rowIndex, colIndex){
					                	return grid.getStore().getAt(rowIndex).data.currentStatus != 1;
					                }
					            }
				            ]
				        },
				        { text: '联系人',align:'center',width:70, dataIndex: 'linkMan'},
				        { text: '联系电话',align:'center',width:124, dataIndex: 'tel'},
				        { text: '邮箱',align:'center',width:156, dataIndex: 'email'},
				        { text: '服务类别ID',align:'center', dataIndex: 'category.id',hidden:true},
				        { text: '服务类别',align:'center',width:100, dataIndex: 'category.text'},
					    { text: '来源',align:'center',width:100, dataIndex: 'serviceSource',renderer:function(value){
					    	return PlatMap.Service.serviceSource[value];
					    }},
				        //{ text: '服务机构ID',align:'center', dataIndex: 'organizeId',hidden:true},
				        //{ text: '服务机构',align:'center',width:80, dataIndex: 'organizeName'},
				        //{ text: '服务对象',align:'center',width:100, dataIndex: 'serviceObject'},
				        { text: '服务方式',align:'center',width:100, dataIndex: 'serviceMethod'},
				        //{ text: '服务描述',align:'center',width:150, dataIndex: 'serviceProcedure'},
				        //{ text: '收费方式',align:'center',width:80, dataIndex: 'chargeMethod'},
				        { text: '服务次数',align:'center',width:80, dataIndex: 'serviceNum'}
		    		],
//		    plugins: [{
//		        ptype: 'rowexpander',
//		        rowBodyTpl : new Ext.XTemplate(
//		            '<p><b>来源:</b> {serviceSource}</p>',
//		            '<p><b>联系人:</b> {linkMan}</p><br>'
//	            )
//		    }],
//	    	features :[{ftype:'groupingsummary',id:'servicegroupingsummary',startCollapsed:true,groupHeaderTpl: '<font color="green">{name}</font> : 共[ <font color="green">{rows.length}</font> ]个服务'}],
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'service.NServiceStore',
					        dock: 'bottom',
					        displayInfo: true
					    }]
        });
        this.callParent();
    }
});