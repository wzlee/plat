/**
 * 订单流水 xuwf 2013-9-29
 */

Ext.define('plat.view.business.OrderInfoGrid',{
	extend:'Ext.grid.Panel',
	xtype:'orderinfogrid',  
	id:'orderinfogrid',
	closeAction:'hide',
	columnLines:true,
	emptyText:'暂无相关数据......',
    store:'business.OrderInfoStore',
    initComponent: function() {
      	var me = this;
        Ext.apply(this, {
			columns: [
						{xtype:'rownumberer',text:'#',align:'center',width:30},
				        { text: '动作',align:'center',width:123, dataIndex: 'action',
				        	renderer:function(v){                	
                				return PlatMap.OrderInfo.action[v];             
				        	}
				        },
				        { text: '操作人',align:'center',width:90, dataIndex: 'processor.userName',
				        	renderer:function(value,metaData,record){
				        		if(record.data.processor){
				        			return value;
				        		}else if(record.data.staff){
				        			return record.get('staff.userName');
				        		}else if(record.data.manager){
				        			return record.get('manager.username');
				        		}else{
				        			return '卖家窗口';
				        		}
//				        		if('' == value){
//				        			return record.get('manager.username');
//				        		}else{
//				        			return value;
//				        		}	
				        	}
				        }, 
				        { text: '下单时间',align:'center',width:150, dataIndex: 'processTime'},
				        { text: '订单状态',align:'center',width:93, dataIndex: 'orderStatus',
				        	renderer:function(v){
				        		return PlatMap.GoodsOrder.orderStatus[v];
				        	}
				        }
				      
		    		]
        });
        this.callParent();
    }
});