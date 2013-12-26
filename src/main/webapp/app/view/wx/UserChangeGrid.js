Ext.define('plat.view.wx.UserChangeGrid', {
	extend : 'Ext.grid.Panel',
	store : 'wx.UserChangeStore',
	xtype : 'userchangegrid',
	title: '用户变更记录',
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
	initComponent : function () {
		this.callParent(arguments);
	},
	dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'wx.UserChangeStore',
        dock: 'bottom',
        displayInfo: true
    }],
	columns : [
		new Ext.grid.column.RowNumberer({
			text : '#',
			align : 'center',
			width : 30
		}),
		{header : 'id', dataIndex: 'id', hidden: true, align: 'center'},
		{header : '平台用户', dataIndex : 'username', width : 100, align : 'center'},
		{header : '企业ID', dataIndex : 'enterprise_id', hidden: true, align : 'center' },
		{header : 'Openid', dataIndex : 'wxUserToken', hidden: true, align : 'center'},
		{header : '状态', dataIndex : 'change_status', width : 80, align : 'center',renderer: function(v){
	        if (v == 0) {
	            return '<span style="color:blue;">已关注</span>';
	        } else if(v == 1) {
	        	return '<span style="color:green;">已绑定</span>';
	        } else if(v == 2) {
	        	return '<span style="color:red;">已解绑</span>';
	        } else {
	        	return '<span style="color:gray;">取消关注</span>';
	        }
	    }},
		{header : '关注时间', dataIndex : 'change_time', width : 140, align : 'center'},
		{header : '企业名称', dataIndex : 'enterprise_name', width : 200, align : 'center'}
	]
});
