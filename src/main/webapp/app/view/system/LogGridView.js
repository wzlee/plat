Ext.define('plat.view.system.LogGridView',{
	extend:'Ext.grid.Panel',
	alias:'widget.loggrid',
	
	title:'系统日志查询',
	id:'loggrid',
	titleCollapse:true,
	closable:true,
	closeAction:'hide',
	autoDestroy :false,
	margins:1,
	columnLines:true,
	emptyText:'无数据或者数据加载失败！',
	store:'system.LogStore',
	initComponent:function(){
    	this.tbar = ['-',
	    				{xtype:'textfield',width:100,name:'keywords',emptyText:'输入关键字...'},'-',
	    				{xtype:'datefield',width:100,name:'begindate',format:'Y-m-d',emptyText:'选择开始日期...'},'-',
	    				{xtype:'datefield',width:100,name:'enddate',format:'Y-m-d',emptyText:'选择截止日期...'},'-',
	    				{xtype:'levelcombo',width:100,name:'level',emptyText:'选择日志级别...'},'-',
    					{text:'查询',iconCls:'icon-search',action:'search'},'-'
	    ];
	    this.columns = [
	    				{text:'ID',dataIndex:'ID',width:50,align:'center'},
	    				{text:'日志时间',dataIndex:'DATE',width:150,align:'center'},
	    				{text:'线程',dataIndex:'THREAD',width:200,align:'center'},
	    				{text:'级别',dataIndex:'LEVEL',width:50,align:'center'},
	    				{text:'系统消息',dataIndex:'MESSAGES',flex:1,align:'left'}
						];
    	this.dockedItems = [{
						        xtype: 'pagingtoolbar',
						        store: 'system.LogStore',
						        dock: 'bottom',
						        displayInfo: true
						    }]
	    this.callParent(arguments);
    }
});