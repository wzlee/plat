Ext.define('plat.view.flat.FlatUserGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'flatusergrid',
	store : 'flat.FlatUserStore',
	title : '用户查询',
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
	initComponent : function () {
		console.log('FlatUserGrid was initialized!!!');
		Ext.apply(this, {
			columns : [new Ext.grid.column.RowNumberer({
					text : '#',
					align : 'center',
					width : 30
				}),
				{header : '窗口平台', dataIndex : 'flatName', flex : 1, align : 'center'},
				{header : '用户名', dataIndex : 'username', flex : 1, align : 'center', sortable : false},
				{header : '用户角色', dataIndex : 'rolename', flex : 1, align : 'center', sortable : false},
				{header : '创建时间', dataIndex : 'registerTime', flex : 1, align : 'center', renderer : function (text) {
					return Ext.util.Format.substr(text, 0, 10);
				}},
				{header : '帐号状态', dataIndex : 'userStatus', flex : 1, align : 'center',
					renderer : function (text) {
						return PlatMap.Staff.status[text];
					}
				},
				{header : '备注', dataIndex : 'remark', flex : 1, align : 'center', sortable : false}
			]
		});
		this.callParent(arguments);
	},
	tbar : [{
		xtype : 'combo',
		fieldLabel: '窗口平台',
        name: 'flatId',
        labelWidth : 60,
        editable : false,
        emptyText : '请选择...',
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
		xtype : 'combo',
		fieldLabel: '帐号状态',
        name: 'userStatus',
        editable : false,
        labelWidth : 60,
        emptyText : '请选择...',
//        store : 'flat.FlatStore',
        store:  Ext.create('Ext.data.Store', {
				    fields: ['value', 'text'],
				    data : [{
				    		value : 0, text : '-----------全部-----------'
				    	}, {
				    		value : 1, text : '有效'
				    	}, {
				    		value : 2, text : '禁用'
				    	}, {
				    		value : 3, text : '删除'
				    	}]
        }),
        queryMode : 'local',
	    displayField: 'text',
	    valueField: 'value'
	},{
		text : '搜索',
		iconCls : 'icon-search',
		name : 'search'
	}],
	dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'flat.FlatManagerStore',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }]
	
});
