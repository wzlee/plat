Ext.define('plat.view.flat.FlatManagerGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'flatmanagergrid',
	store : 'flat.FlatManagerStore',
	title : '窗口管理员',
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
	initComponent : function () {
		console.log('FlatManagerGrid was initialized!!!');
		Ext.apply(this, {
			columns : [new Ext.grid.column.RowNumberer({
					text : '#',
					align : 'center',
					width : 30
				}),
				{header : '窗口平台', dataIndex : 'flatName', flex : 1, align : 'center'},
				{header : '用户名', dataIndex : 'username', flex : 1, align : 'center', sortable : false},
				{header : '创建时间', dataIndex : 'registerTime', flex : 1, align : 'center', renderer : function (text) {
					return Ext.util.Format.substr(text, 0, 10);
				}},
				{header : '备注', dataIndex : 'remark', flex : 1, align : 'center', sortable : false},
				{header : '帐号状态', dataIndex : 'userStatus', flex : 1, align : 'center',
					renderer : function (text) {
						return PlatMap.Staff.status[text];
					}
				}
			]
		});
		this.callParent(arguments);
	},
	tbar : [{
		xtype : 'combo',
		fieldLabel: '窗口平台',
        name: 'flatId',
        editable : false,
        labelWidth : 60,
        emptyText : '请选择...',
//        store : 'flat.FlatStore',
        store:  Ext.create('Ext.data.Store', {
				    fields: ['id', 'flatName'],
				    data : [{
			        	id : 0,
			        	flatName : '-----------全部-----------'
		        	}]
        }),
        queryMode : 'local',
	    displayField: 'flatName',
	    valueField: 'id'
	}, {
		text : '搜索',
		iconCls : 'icon-search',
		name : 'search'
	}, {
		text : '新增管理员',
		iconCls : 'icon-add',
		name : 'add'
	}],
	dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'flat.FlatManagerStore',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }]
	
});
