/**
 * 订单流水 xuwf 2013-9-29
 */

Ext.define('plat.view.business.BiddingServiceDetailGrid',{
	extend:'Ext.grid.Panel',
	xtype:'biddingservicedetailgrid',  
	id:'biddingservicedetailgrid',
	closeAction:'hide',
	columnLines:true,
	emptyText:'暂无相关数据......',
    store:'business.BiddingServiceDetailStore',
    initComponent: function() {
      	var me = this;
        Ext.apply(this, {
			columns: [
						{xtype:'rownumberer',text:'#',align:'center',width:30},
				        { text: '操作人',align:'center',width:90, dataIndex: 'user.userName',
				        	renderer:function(value,metaData,record){
				        		if(record.data.user){
				        			return value;
				        		}else if(record.data.staff){
				        			return record.get('staff.userName');
				        		}else{
				        			return record.get('manager.username');
				        		}	
				        	}
				        }, 
				        { text: '动作',align:'center',width:123, dataIndex: 'action',
				        	renderer:function(v){                	
                				return PlatMap.BiddingServiceDetail.action[v];             
				        	}
				        },
				        { text: '时间',align:'center',width:145, dataIndex: 'processTime'},
				        { text: '备注',align:'center',width:120, dataIndex: 'remark'},
				        { text: '订单状态',align:'center',width:93, dataIndex: 'biddingStatus',
				        	renderer:function(v){
				        		return PlatMap.BiddingService.status[v];
				        	}
				        }
				      
		    		]
        });
        this.callParent();
    }
});