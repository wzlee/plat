Ext.define('plat.view.service.UServiceGrid',{
	extend:'Ext.grid.Panel',
	xtype:'uservicegrid',
    
	title:'服务列表',
	id:'ysjfwgl',
	closable:true,
    closeAction:'hide',
	columnLines:true,
    store:'service.UServiceStore',
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
    			},'-',
    			{xtype: 'triggerfield',name:'orgName',width:200,triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
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
				'双击服务可以查看服务详情'
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
				        	renderer:function(v){
					      	  // 服务状态:1新服务,2上架审核中，3已上架，4变更审核中，5已删除,6已下架,7下架审核中
				        		return PlatMap.Service.currentStatus[v];
					        }
				        },
				        { text: '服务机构',align:'center',width:120,dataIndex:'enterprise.name',locked:true},
				        { text: '所属窗口',align:'center',width:80, dataIndex: 'enterprise.industryType',locked:true,
		                	renderer:function(value){                		
		                		return PlatMap.Enterprises.industryType[value];
		                	}
		                },
				        {
				       		header : '配图',
				       		dataIndex : 'picture',
				       		width : 50,
				       		locked : true,
				       		toolTip : '55',
				       		align : 'center',
				       		renderer : function (value) {				       			
				       			
				       			if (value) {
				       				if(value.indexOf('http') > -1){
				       					return "<a href='" + value + "' class='fancybox'><img src='jsLib/extjs/resources/themes/icons/scan.png' /></a>";
				       				}else {
				       					return "<a href='upload/" + value + "' class='fancybox'><img src='jsLib/extjs/resources/themes/icons/scan.png' /></a>";
				       				}					       			
				       			} else {
				       				return "<a href='resources/images/nopic.gif' class='fancybox'><img src='jsLib/extjs/resources/themes/icons/scan.png' /></a>";
				       			}
				       		}
				       }, {
				            xtype:'actioncolumn',
				            text:'变更',
				            align:'center',
				            sortable:false,
				            locked:true,
				            width:50,
				            items: [
				            	{
					                iconCls:'icon-edit',
					                tooltip: '申请变更',
					                handler: function(grid, rowIndex, colIndex) {
					                    var record = grid.getStore().getAt(rowIndex);
					                    var serviceWindow;
										var serviceWindows = Ext.ComponentQuery.query('uservicewindow');
								    	if(serviceWindows.length == 0){
								    		serviceWindow = Ext.widget('uservicewindow',{
								    			title:'申请服务变更',
								    			id:'uservicewindow'
								    		});
								    		serviceWindow.getComponent('userviceform').form.reset(true);
											serviceWindow.getComponent('userviceform').form.loadRecord(record);
											serviceWindow.show();
											if (serviceWindow.getKindeditor()) {
												serviceWindow.getKindeditor().html(record.data.serviceProcedure);
											} else {
												setTimeout(function () {
													serviceWindow.getKindeditor().html(record.data.serviceProcedure);
												}, 1000);
											}
								    	}else{
								    		serviceWindows[0].getComponent('userviceform').form.reset(true);
											serviceWindows[0].getComponent('userviceform').form.loadRecord(record);
								    		serviceWindows[0].show();
								    		if (serviceWindows[0].getKindeditor()) {
												serviceWindows[0].getKindeditor().html(record.data.serviceProcedure);
											} else {
												setTimeout(function () {
													serviceWindows[0].getKindeditor().html(record.data.serviceProcedure);
												}, 1000);
											}
								    	}
					                },
					                isDisabled:function(grid, rowIndex, colIndex){
					                	return grid.getStore().getAt(rowIndex).data.currentStatus != "3";
					                }
					            }
				            ]
				        },
				        {
				            xtype:'actioncolumn',
				            text:'下架',
				            align:'center',
				            sortable:false,
				            locked:true,
				            width:50,
				            items: [
					            {
					            
					                icon:'resources/images/applydown.png',
					                tooltip: '申请下架',
					                handler: function(grid, rowIndex, colIndex) {
					                    var record = grid.getStore().getAt(rowIndex);
					                    Ext.MessageBox.confirm('警告','确定将【 '+record.data.serviceName+' 】申请下架吗?',function(btn){
								    		if(btn == 'yes'){
								    			record.set('currentStatus',7);
								    			record.set('lastStatus',3);
								    			record.set('locked','1');
												//grid.getStore().sync();
								    			grid.getStore().update({
								    				params:record.data,
								    				callback:function(record,operation){
								    						var result =Ext.JSON.decode(operation.response.responseText);								    						
								    						if(result.success){
								    							Ext.example.msg('','<p align="center">服务【'+operation.params.serviceName+'】申请下架成功</p>');
								    						}else {
								    							Ext.Msg.alert('提示','服务【'+operation.params.serviceName+'】申请下架失败');
								    						}
									    					
								    					
								    				}
								    			});
											}
										});
					                },
					                isDisabled:function(grid, rowIndex, colIndex){
					                	return grid.getStore().getAt(rowIndex).data.currentStatus != "3";
					               }
					            }
					            
				            ]
				        },
				        { text: '联系人',align:'center',width:70, dataIndex: 'linkMan'},
				        { text: '联系电话',align:'center',width:124, dataIndex: 'tel'},
				        { text: '邮箱',align:'center',width:156, dataIndex: 'email'},
				        { text: '服务类别ID',align:'center', dataIndex: 'category.id',hidden:true},
				        { text: '服务类别',align:'center',width:100, dataIndex: 'category.text'},
					    { text: '来源',align:'center',width:100, dataIndex: 'serviceSource',renderer:function(v){
					    	//服务来源 ：1服务机构本身上传  2运营管理人员代理录入 3 运营服务
				        	return PlatMap.Service.serviceSource[v];
					    }},
				        { text: '服务方式',align:'center',width:100, dataIndex: 'serviceMethod'},
				        //{ text: '收费方式',align:'center',width:80, dataIndex: 'chargeMethod'},
				        { text: '服务次数',align:'center',width:80, dataIndex: 'serviceNum'}
		    		],
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'service.UServiceStore',
					        dock: 'bottom',
					        displayInfo: true
					    }]
        });
        this.callParent();
    }
});