Ext.define('plat.view.wx.AutoMessageGrid', {
	extend : 'Ext.grid.Panel',
	store : 'wx.AutoMessageStore',
	xtype : 'automessagegrid',
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
    closable : true,
	id : 'xyxx',
	initComponent : function () {
		console.log('AutoMessageGrid was initialized!!!');
		Ext.apply(this, {
			columns : [new Ext.grid.column.RowNumberer({
					text : '#',
					align : 'center',
					width : 30
				}),
				{header : '输入指令', dataIndex : 'reqKey', width : 150, align : 'center'},
				{header : '点击指令', dataIndex : 'clickKey', width : 150, align : 'center'},
				{header : '内容', dataIndex : 'content', flex : 1, align : 'center' },
				
				{
			        xtype:'actioncolumn',
			        text:'修改',
			        align:'center',
			        menuDisabled : true,
			        width: 50,
			        items: [{
			            iconCls:'icon-edit',
			            tooltip: '修改',
			            handler: function(grid, rowIndex, colIndex, item, e, record, row) {
					    	var articleinfowindow = Ext.ComponentQuery.query('automessagewindow[name=automessage]')[0];
					    	if (!articleinfowindow) {
					    		articleinfowindow = Ext.widget('automessagewindow',{
					    			title:'响应消息修改',
					    			name : 'automessage'
					    		}).show();
					    	} else {
					    		articleinfowindow.setTitle('响应消息修改');
					    		articleinfowindow.show();
					    	}
					    	var Eform = Ext.ComponentQuery.query('automessageform')[0];
					    	Eform.getForm().loadRecord(record);
			            }
			       }]
		       }, {
			        xtype:'actioncolumn',
			        text:'删除',
			        align:'center',
			        menuDisabled : true,
			        width: 50,
			        items: [{
			            iconCls:'icon-remove',
			            tooltip: '删除',
			            handler: function(grid, rowIndex, colIndex) {
			                var record = grid.getStore().getAt(rowIndex);
			                Ext.MessageBox.confirm('警告','确定删除该新闻吗?',function(btn){
					    		if(btn == 'yes'){
//					    			grid.getStore().remove(record);
					    			Ext.Ajax.request({
									    type:'POST',
									    url: 'automessage/delete',
									    params : {
									    	'idStr' : record.data.id
									    },
									    success: function(response) {
									    	var result = Ext.decode(response.responseText);
									    	if(result.success){
									    		grid.getStore().reload();
									    	}else{
									    		Ext.Msg.alert('提示', result.message);
									    	}
									    },
										failure: function(response) {
											Ext.Msg.alert('提示', '服务端访问异常');
										}
									});
					    		}
					    	});
			            }
			       }]
		       }
				]
		});
		this.callParent(arguments);
	},
	
	tbar : [{
		text : '新增',
		iconCls : 'icon-add',
		name : 'add'
	}, '-', /*{
		text : '修改',
		iconCls : 'icon-edit',
		name : 'modify'
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		name : 'delete'
	}, '-', */{
		xtype : 'textfield',
		width : 150,
		name : 'reqKey',
		emptyText : '输入指令...'
	}, '-', {
		xtype : 'textfield',
		width : 150,
		name : 'clickKey',
		emptyText : '点击指令...'
	}, '-', {
		iconCls : 'icon-search',
		text : '查找',
		action : 'search'
	}],
	dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'wx.AutoMessageStore',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }]
});
