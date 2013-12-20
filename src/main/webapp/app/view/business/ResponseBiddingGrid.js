
Ext.define('plat.view.business.ResponseBiddingGrid',{
	extend:'Ext.grid.Panel',
	xtype:'responsebiddinggrid',  
	id:'responsebiddinggrid',
    closeAction:'hide',
    autoScroll:true,
	columnLines:true,
	emptyText:'暂无相关数据......',
    store:'business.ResponseBiddingStore',
    cls:'font-size:14px',
    initComponent: function() {
      	var me = this;
        Ext.apply(this, {
			columns: [
						{ xtype:'rownumberer',text:'#',align:'center',width:30},
						{ text:'用户名',aligin:'center',width:100,dataIndex:'user.userName',
							renderer:function(value,metaData,record){
								if(record.data.user){
									return value;
								}else{
									return record.get('staff.userName');
								}
							}
						},
						{ text:'企业名称',align:'center',width:120,dataIndex:'user.enterprise.name',
							renderer:function(value,metaData,record){
								if(record.data.user){
									return value;
								}else{
									return record.get('staff.parent.enterprise.name');
								}
							}
						},
				       	{ text: '应标价格',align:'center',width:100, dataIndex: 'bidPrice'},
				       	{ text: '应标时间',align:'center',width:145, dataIndex: 'responseTime'},
				       	{ text: '附件',align:'center',width:100,dataIndex: 'attachment'},
				       	{ text: '联系人',align:'center',width:80,dataIndex: 'linkMan'},
				       	{ text: '联系电话',align:'center',width:90,dataIndex: 'tel'},
				       	{ text: '邮箱',align:'center',width:110,dataIndex: 'email'},
				       	{ text: '应标备注',align:'center',width:100,dataIndex: 'description'}
					]
        });
        this.callParent();
    }
});