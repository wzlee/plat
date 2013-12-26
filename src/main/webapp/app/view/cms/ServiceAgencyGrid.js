Ext.define('plat.view.cms.ServiceAgencyGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.serviceagencygrid',
	store : 'cms.ServiceAgencyStore',
//	title : '',
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
	initComponent : function () {
		//console.log('ServiceAgencyGrid was initialized!!!');
		this.callParent(arguments);
	},
	tbar : [{
		xtype : 'textfield',
		width : 150,
		name : 'enterpName',
		emptyText : '输入企业全名进行搜索...'
	}, '-', {
		iconCls : 'icon-search',
		text : '查找',
		action : 'search'
	}],
	dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'cms.ServiceAgencyStore',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }],
	columns : [{
		xtype: 'rownumberer'
	}, {
		header : '企业全名', 
		dataIndex : 'mname', 
		flex : 1, 
		align : 'center'
	}, {
		header : '所属窗口', 
		dataIndex : 'industryType', 
		flex : 1, 
		align : 'center',
		renderer : function (text) {
			return PlatMap.Enterprises.industryType[text];
		}
	}
		]
});
