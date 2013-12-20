Ext.define('plat.view.policy.PolicyGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'policygrid',
	store : 'policy.PolicyStore',
	title : '资讯信息',
	closable : true,
	closeAction : 'hide',
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
	id : 'zczygl',
	initComponent : function () {
		console.log('PolicyGrid was initialized!!!');
		Ext.apply(this, {
			columns : [new Ext.grid.column.RowNumberer({
					text : '#',
					align : 'center',
					width : 30
				}),
				{header : '标题', dataIndex : 'title', flex : 1, align : 'center'},
				{header : '添加时间', dataIndex : 'time', width : 140, align : 'center'},
				{header : '来源', dataIndex : 'source', width : 140, align : 'center'},
				{header : '类别', dataIndex : 'policyCategory.text', width : 140, align : 'center'},
				{
				    xtype:'actioncolumn',
					text:'修改',
					align:'center',
					width:50,
					items: [{
					        iconCls:'icon-edit',
					        tooltip: '编辑',
					        handler: function(grid, rowIndex, colIndex) {
					            var record = grid.getStore().getAt(rowIndex);
					            var window = Ext.ComponentQuery.query('policywindow');
					            if (window.length == 0) {
					            	window = Ext.create('plat.view.policy.PolicyWin');
					            } else {
					            	window = window[0];
					            }
					            window.show();
								var _form = window.down('form');
								if (_form.getKindeditor()) {
									_form.loadRecord(record);
									_form.getKindeditor().html(record.data.text);
								} else {
									setTimeout(function () {
										_form.loadRecord(record);
										_form.getKindeditor().html(record.data.text);
									}, 1000);
								}
								
							}
						}
					]
				},{
					
				    xtype:'actioncolumn',
					text:'置顶',
					align:'center',
					width:50,
					items: [
						{
					        iconCls:'icon-up',
					        tooltip: '置顶',
					        handler: function(grid, rowIndex, colIndex) {
					            var record = grid.getStore().getAt(rowIndex);
							    	Ext.Ajax.request({
									    type:'POST',
									    url: 'policy/configTop',
									    params:{'id':record.data.id},
									    success: function(response) {
									    	var result = Ext.decode(response.responseText);
									    	if(result.success){
									    		grid.getStore().reload();
									    		Ext.Msg.alert('配置成功',result.message);
									    	}else{
									    		Ext.Msg.alert('配置失败',result.message);
									    	}
									    },
										failure: function(response) {}
								});
							},isDisabled : function(view, rowIdx, colIdx, item, record){
                        	if (record.data.top=='1') {
                        		return true;
                        	}
                      }
			        	}
					]
					 
				
				},
				{
				    xtype:'actioncolumn',
					text:'删除',
					align:'center',
					width:50,
					items: [
						{
					        iconCls:'icon-remove',
					        tooltip: '删除',
					        handler: function(grid, rowIndex, colIndex) {
					            var record = grid.getStore().getAt(rowIndex);
					                Ext.MessageBox.confirm('警告','确定删除该政策指引吗?',function(btn){
							    		if(btn == 'yes'){
							    			Ext.Ajax.request({
											    type:'POST',
											    url: 'policy/delete',
											    params:{'id':record.data.id},
											    success: function(response) {
											    	var result = Ext.decode(response.responseText);
											    	if(result.success){
											    		grid.getStore().reload();
											    	}else{
											    		Ext.Msg.alert('不能删除',result.message);
											    	}
											    },
												failure: function(response) {}
											});
										}
									});
			        		}
						}
					]
				}
				]
		});
		this.callParent(arguments);
	},
	/*features: [{
		ftype:'grouping',
		groupHeaderTpl: [
		    '{columnName}: ',
		    '<span>{name:this.formatName}</span>',
		    {
		        formatName: function(name) {
		        	switch (name) {
						case 1 : 
							name = "展会信息";
							break;
						case 2 : 
							name = "最新推荐";
							break;
						case 3 : 
							name = "政策法规";
							break;
						case 4 : 
							name = "最新公告";
							break;
						case 5 : 
							name = "新闻动态";
					}
		            return name;
		        }
		    }
		]
	}],*/
	tbar : [{
		text : '新增',
		iconCls : 'icon-add',
		name : 'add'
	},/* '-', {
		text : '修改',
		iconCls : 'icon-edit',
		name : 'modify'
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		name : 'delete'
	}, */'-', {
		xtype : 'textfield',
//		width : 150,
		labelWidth : 60,
		name : 'title',
		fieldLabel: '政策标题',
		emptyText : '输入标题进行搜索...'
	}, '-', {
        xtype: 'treecombo',
        name:'pcid',
        labelWidth : 60,
        allowBlank:false,
        queryMode : 'local',
        store: Ext.data.StoreManager.lookup('PolicyCategoryStore1') ? 
				                    		Ext.data.StoreManager.lookup('PolicyCategoryStore1'):
				                    		Ext .create( 'plat.store.policy.PolicyCategoryStore1',
													{
														storeId : 'policycategorystore1'
													}),
        displayField:'text',
		valueField:'id',
		canSelectFolders:false,
        rootVisible:false,
        editable:false,
        emptyText:'请选择服务类别...',
        fieldLabel: '服务类别'
    },/*, {
		xtype : 'combo',
		fieldLabel: '政策类别',
        name: 'pcid',
        editable : false,
        labelWidth : 60,
        emptyText : '请选择...',
//        store : 'flat.FlatStore',
        store:  Ext.create('Ext.data.Store', {
				    fields: ['id', 'text'],
//				    proxy : {
//				    	type: 'ajax',
//				        url : 'policy/queryCategory',
//				        actionMethods: {  
//				    		read: 'POST'
//				        },
//				        reader : {
//				        	type : 'json',
//					        root : 'data'
//				        }
				    data : [{
				    	id : 0,
				    	text : '----------全部----------'
				    }]
        }),
        queryMode : 'local',
	    displayField: 'text',
	    valueField: 'id'
	}*/, '-', {
		iconCls : 'icon-search',
		text : '查找',
		name : 'search'
	}, '-'],
	dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'policy.PolicyStore',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }]
});
