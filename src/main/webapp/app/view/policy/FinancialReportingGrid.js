Ext.define('plat.view.policy.FinancialReportingGrid', {
			extend : 'Ext.grid.Panel',
			xtype : 'financialreportinggrid',
			id : 'zzdh',
			title : '资助导航',
			closable : true,
			closeAction : 'hide',
			layout : 'fit',
			store: 'policy.PolicyReportingStore',
			
			tbar : [ {
						xtype : 'treecombo',
						fieldLabel : '类别',
						name : 'pcid',
						labelWidth : 40,
						emptyText : '请选择...',
						queryMode : 'local',
						displayField : 'text',
						valueField : 'id',
						store: Ext.data.StoreManager.lookup('reportcategorystore') ? 
	                    		Ext.data.StoreManager.lookup('reportcategorystore'):
	                    		Ext .create( 'plat.store.policy.ReportCategoryStore',
										{
											storeId : 'reportcategorystore'
						}),
						width:400,
						canSelectFolders:false,
	                    rootVisible:false,
	                    editable:false
					}, '-', {
						iconCls : 'icon-search',
						text : '查找',
						name : 'search'
					},'-',{
						text : '新增',
						iconCls : 'icon-add',
						name : 'add'
					}],

			columns : [
					{xtype:'rownumberer',text:'序号',align:'center',width:80},
					{
						header : '',
						dataIndex : 'id',
						width : 140,
						align : 'center',
						hidden:true
					},
					{
						header : '标题',
						dataIndex : 'title',
						width : 420,
						align : 'center'
					}, {
						header : '类别',
						dataIndex : 'policyCategory.text',
						width : 330,
						align : 'center'
					}, {
						header : '添加时间',
						dataIndex : 'time',
						width:156,
						align : 'center'
					},{
						text : '删除',
						width : 80,
						name:'delReport',
						menuDisabled : true,
						xtype : 'actioncolumn',
						tooltip : '删除',
						align : 'center',
						icon : 'resources/images/delete.png',
						handler : function(grid, rowIndex, colIndex) {
							var record = grid.getStore().getAt(rowIndex);
							console.log(record.data.id);
							Ext.MessageBox.confirm('警告', '删除之后数据无法恢复,确定删除【 '+ record.data + ' 】吗?',
								function(btn) {
									if (btn == 'yes') {
										grid.getStore().destroy({params : record.data,callback:function(record,operation){
				    						var result =Ext.JSON.decode(operation.response.responseText);								    						
				    						if(result.success){
					    						grid.getStore().on('beforeload',function(store) {});
												grid.getStore().load();
				    						}else {
				    							Ext.Msg.alert('提示','删除失败');
				    						}
							    	}
								});
							}
						});
					}
				}],

			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'policy.PolicyReportingStore', // same store GridPanel is using
						dock : 'bottom',
						displayInfo : true
					}]
});
