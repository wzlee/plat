Ext.define('plat.view.wx.WXUserPanel', {
	extend : 'Ext.panel.Panel',
	id : 'weixinuser',
	xtype : 'wxuserpanel',
	title : '会员管理',
	closable : true,
	layout: 'border',
	initComponent : function () {
		this.callParent(arguments);
	},
	tbar : ["帐号查询：",{
		id: 'weixinUser',
		xtype : 'textfield',
		width : 150,
		emptyText : '根据平台用户名查询……'
	}, '状态选择：', {
		id: 'status',
		xtype : 'combobox',
		width : 100,
		store: Ext.create('Ext.data.Store', {
		    fields: ['id', 'text'],
		    data : [
		        {"id":"0", "text":"已关注"},
		        {"id":"1", "text":"已绑定"},
		        {"id":"2", "text":"已解绑"},
		        {"id":"3", "text":"取消关注"}
		    ]
		}),
		queryMode: 'local',
	    valueField: 'id',
	    displayField: 'text',
		emptyText : '请选择……'
	}, '开始时间：', {
		id: 'starttime',
		value: new Date(),
		format: 'Y-m-d',
		xtype : 'datefield',
		width : 120
	}, '结束时间：', {
		id: 'endtime',
		format: 'Y-m-d',
		xtype : 'datefield',
		width : 120
	}, '-', {
		xtype: 'button',
		text : '查找',
		iconCls : 'icon-search',
		action : 'search'
	}],
	items:[{
		xtype:'weixinusergrid',
		width:'65%',
		region:'center'
	}, {
		xtype: 'userchangegrid',
		region:'east',
		width:'35%',
		collapsible:true,
		split:true
	}]
});
