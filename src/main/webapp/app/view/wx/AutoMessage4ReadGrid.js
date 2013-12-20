Ext.define('plat.view.wx.AutoMessage4ReadGrid', {
	extend : 'Ext.grid.Panel',
	store : 'wx.AutoMessageStore',
	xtype : 'automessage4readgrid',
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
	initComponent : function () {
		console.log('AutoMessage4ReadGrid was initialized!!!');
		Ext.apply(this, {
			columns : [new Ext.grid.column.RowNumberer({
					text : '#',
					align : 'center',
					width : 30
				}),
				{header : '输入指令', dataIndex : 'reqKey', width : 150, align : 'center'},
				{header : '点击指令', dataIndex : 'clickKey', width : 150, align : 'center'},
				{header : '内容', dataIndex : 'content', flex : 1, align : 'center' }
				]
		});
		this.callParent(arguments);
	},
	
	tbar : [{
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
