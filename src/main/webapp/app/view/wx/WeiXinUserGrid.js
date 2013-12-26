Ext.define('plat.view.wx.WeiXinUserGrid', {
	extend : 'Ext.grid.Panel',
	store : 'wx.WeiXinUserStore',
	xtype : 'weixinusergrid',
	title: '微信帐号列表',
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
        store: 'wx.WeiXinUserStore',
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
		{header : '平台用户', dataIndex : 'username', width : 80, align : 'center'},
		{header : '企业ID', dataIndex : 'enterprise_id', hidden: true, align : 'center' },
		{header : '企业名称', dataIndex : 'enterprise_name', hidden: true, align : 'center'},
		{header : 'Openid', dataIndex : 'wxUserToken', hidden: true, width : 230, align : 'center'},
		{header : '微信昵称', dataIndex : 'wx_user_name', width : 80, align : 'center'},
		{header : '微信头像', dataIndex : 'wx_user_headimgurl', hidden: true, align : 'center'},
		{header : '性别', dataIndex : 'wx_user_sex', width : 60, align : 'center',renderer: function(v){
	        if (v == 1) {
	            return '<span style="color:red;">男</span>';
	        } else {
	        	return '<span style="color:green;">女</span>';
	        }
	    }},
		{header : '语言', dataIndex : 'wx_user_language', width : 60, align : 'center'},
		{header : '国家', dataIndex : 'wx_user_country', width : 60, align : 'center'},
		{header : '省份', dataIndex : 'wx_user_province', width : 60, align : 'center'},
		{header : '城市', dataIndex : 'wx_user_city', width : 60, align : 'center'},
		{header : '当前状态', dataIndex : 'concern_status', width : 80, align : 'center',renderer: function(v){
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
	    {
	        xtype:'actioncolumn',
	        name: 'unbundling',
	        text:'解绑',
	        icon: 'resources/images/lock_open.png',
	        width: 45,
	        align:'center',
	        tooltip: '解绑',
         	emptyCellText: '',
            menuDisabled: true,
            sortable: false,
            getClass: function(v, metadata, record, colIndex, store){
            	//如果微信帐号和平台帐号不是已绑定的状态，那么就隐藏解绑的按钮
            	if(record.data.concern_status != 1){
            		return 'x-hidden';
            	}
            },
            isDisabled: function(view, rowIndex, colIndex, item, record){
            	//如果微信帐号和平台帐号不是已绑定的状态，那么就隐藏解绑的按钮
            	if(record.data.concern_status != 1){
            		return true;
            	}
            }
        },
		{header : '关注时间', dataIndex : 'subscribe_time', width : 140, align : 'center'}
	]
});
