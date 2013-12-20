/**
 * 订单流水 xuwf 2013-9-29
 */

Ext.define('plat.view.business.EvaluationGrid',{
	extend:'Ext.grid.Panel',
	xtype:'evaluationgrid',  
	id:'evaluationgrid',
	closeAction:'hide',
	columnLines:true,
	emptyText:'暂无相关数据......',
    store:'business.EvaluationStore',
    initComponent: function() {
      	var me = this;
        Ext.apply(this, {
			columns: [
						{xtype:'rownumberer',text:'#',align:'center',width:30},
				        { text: '评价人',align:'center',width:123, dataIndex: 'user.userName',
				        	renderer:function(value,metaData,record){
				        		if(record.data.user){
				        			return value;
				        		}else{
				        			return record.get('staff.userName');
				        		}
				        	}
				        },  
				        { text: '评价时间',align:'center',width:150, dataIndex: 'dtime'},
				        { text: '满意度',align:'center',width:93, dataIndex: 'satisfaction',
				        	renderer:function(v){
				        		return PlatMap.OrderEvaluation.satisfaction[v];
				        	}
				        },
				        { text: '备注',align:'center',width:200, dataIndex: 'remark'}
				      
		    		]
        });
        this.callParent();
    }
});