Ext.define('plat.view.wx.WXNewsGrid', {
	extend : 'Ext.grid.Panel',
	store : 'wx.WXNewsStore',
	xtype : 'wxnewsgrid',
	title : '平台动态管理',
	closable : true,
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
	id : 'ptdtgl',
	initComponent : function () {
		console.log('WXNewsGrid was initialized!!!');
		Ext.apply(this, {
			columns : [new Ext.grid.column.RowNumberer({
					text : '#',
					align : 'center',
					width : 30
				}),
				{header : '标题', dataIndex : 'title', flex : 1, align : 'center'},
				{header : '描述', dataIndex : 'description', flex : 1, align : 'center'},
				{header : '发布人', dataIndex : 'author', width : 100, align : 'center' },
				{header : '更新时间', dataIndex : 'pubdate', width : 140, align : 'center'},
				{
					xtype : 'actioncolumn',
					text : '配图',
					align : 'center',
					sortable : false,
					menuDisabled : true,
					width : 50,
					items : [{
						iconCls : 'icon-scan',
						tooltip : '查看新闻配图',
						handler : function(grid, rowIndex, colIndex, item, e, record, row) {
							var picture = record.data.picture;
							var $scanImage = $("#scan-image a");
							if (picture) {
								$scanImage.attr("href", "upload/" + picture);
							} else {
								$scanImage.attr("href", "resources/images/nopic.gif");
							}
							$("#scan-image a").trigger("click");
							var clone = $(".fancybox-wrap").clone();
							$("body > .fancybox-wrap").remove();
							$(".fancybox-overlay").append(clone);
						}
					}]
				}, {
			        xtype:'actioncolumn',
			        text:'修改',
			        align:'center',
			        menuDisabled : true,
			        width: 50,
			        items: [{
			            iconCls:'icon-edit',
			            tooltip: '修改',
			            handler: function(grid, rowIndex, colIndex, item, e, record, row) {
					    	var wxnewsWindows = Ext.ComponentQuery.query('wxnewswindow')[0];
					    	if (!wxnewsWindows) {
					    		wxnewsWindows = Ext.widget('wxnewswindow',{
					    			title:'平台动态修改'
					    		}).show();
					    	} else {
					    		wxnewsWindows.setTitle('平台动态修改');
					    		wxnewsWindows.show();
					    	}
					    	var Eform = Ext.ComponentQuery.query('wxnewsform')[0];
					    	Eform.getForm().loadRecord(record);
					    	if (Eform.getKindeditor()) {
								Eform.loadRecord(record);
								Eform.getKindeditor().html(record.data.content);
							} else {
								setTimeout(function () {
									Eform.loadRecord(record);
									Eform.getKindeditor().html(record.data.content);
								}, 1000);
							}
					    	
					    	var EoriginalPic = Eform.query('textfield[name=originalPic]')[0];
							if (record.data.picture) {	//当新闻图片存在时，隐藏输入框做标记
								EoriginalPic.setValue(record.data.picture);
							}
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
									    url: 'wxnews/delete',
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
		       }]
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
		name : 'title',
		emptyText : '输入标题进行搜索...'
	}, '-', {
		iconCls : 'icon-search',
		text : '查找',
		action : 'search'
	}],
	dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'wx.WXNewsStore',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }]
});
