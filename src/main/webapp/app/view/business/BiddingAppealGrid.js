
Ext.define('plat.view.business.BiddingAppealGrid',{
	extend:'Ext.grid.Panel',
	xtype:'biddingappealgrid',  
	id:'biddingappealgrid',
//	closable:true,
    closeAction:'hide',
	columnLines:true,
	emptyText:'暂无相关数据......',
    store:'business.BiddingAppealStore',
    cls:'font-size:14px',
    initComponent: function() {
      	var me = this;
        Ext.apply(this, {
			columns: [
						{xtype:'rownumberer',text:'#',align:'center',width:30},
				        { text: '申诉ID',align:'center', dataIndex: 'id',hidden:true},
				        { text: '申诉时间',align:'center',width:145, dataIndex: 'appealTime'},
				        { text: '申诉原因',align:'center',width:100, dataIndex: 'reason',
				        	renderer:function(value,metaData,record){                	
                				return PlatMap.Appeal.reason[value];              
				        	}
				        },
				        { text: '附件',align:'center',width:150, dataIndex: 'attachment'},
				        { text: '申诉备注',align:'center',width:200, dataIndex: 'remark'}
		    		]
        });
        this.callParent();
    }
});