Ext.define('plat.view.policy.PolicyCategoryGrid', {
    extend : 'Ext.grid.Panel',
    xtype : 'policycategorygrid',
    store : 'policy.PolicyCategoryStore',
    title : '政策指引类别管理',
    closable : true,
    columnLines : true,
    viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
    id : 'zczylbgl',
    initComponent : function () {
        console.log('PolicyCategoryGrid was initialized!!!');
        Ext.apply(this, {
        	columns : [
		        new Ext.grid.column.RowNumberer({
		                    text : '#',
		                    align : 'center',
		                    width : 30
		            }),
		        {header : '类别名称', dataIndex : 'text', width : 140, align : 'center'},
		        {header : '类别描述', dataIndex : 'description', flex : 1, align : 'center'},
		        {header : '添加时间', dataIndex : 'time', width : 140, align : 'center'}
		    ]
        });
        this.callParent(arguments);
    },
    
    tbar : [{
        text : '新增',
        iconCls : 'icon-add',
        name : 'add'
    }, '-', {
        text : '修改',
        iconCls : 'icon-edit',
        name : 'modify'
    }, '-', {
        text : '删除',
        iconCls : 'icon-remove',
        name : 'delete'
    }],
    dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'policy.PolicyCategoryStore',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }]
});
