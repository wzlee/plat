Ext.define('plat.view.flat.FlatGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.flatgrid',
	store : 'flat.FlatStore',
	title : '窗口信息',
	closable : true,
	closeAction :　'hide',
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
	id : 'ckxx',
	initComponent : function () {
		console.log('FlatGrid was initialized!!!');
		Ext.apply(this, {
			columns : [new Ext.grid.column.RowNumberer({
					text : '#',
					align : 'center',
					width : 30
				}),
				{header : '窗口平台', dataIndex : 'flatName', flex : 1, align : 'center'},
				{header : '平台类型', dataIndex : 'flatType', flex : 1, align : 'center'},
				{header : '法定代表人', dataIndex : 'legalPerson', flex : 1, align : 'center'},
				{header : '成立时间', dataIndex : 'establishTime', flex : 1, align : 'center'},
				{header : '联系人', dataIndex : 'linkMan', flex : 1, align : 'center'},
				{header : '联系电话', dataIndex : 'tel', flex : 1, align : 'center'}
			]
		});
		this.callParent(arguments);
	},
	/*tbar : [{
		text : '新增',
		iconCls : 'icon-add',
		name : 'add'
	}],*/
	dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'flat.FlatStore',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }]
	
});
