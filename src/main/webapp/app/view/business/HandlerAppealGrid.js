/**
 * 申诉处理 xuwf 2013-9-25
 */
Ext.define('plat.view.business.HandlerAppealGrid',{
	extend:'Ext.grid.Panel',
	xtype:'handlerappealgrid',  
	title:'申诉处理',
	id:'sscl',
	closable:true,
    closeAction:'hide',
	columnLines:true,
	emptyText:'没有找到相关的数据,请检查输出条件是否存在......',
    store:'business.AppealStore',
    cls:'font-size:14px',
    tbar :['-',
    			{
    				xtype:'triggerfield',
    				width:200,
    				name:'orderNumber',
    				fieldLabel:'订单号',
    				emptyText:'输入订单关键字...',
    				labelWidth:60,
    				labelStyle:'font-size:13',
    				triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
    				onTriggerClick:function(){
						this.reset();
					}
    			},'-',
    			{
    				xtype:'appealtype',
    				width:200,
    				fieldLabel:'申诉类型',
    				name:'appealType',
    				labelWidth:60,
    				forceSelection:true,
    				labelStyle:'font-size:13',
    				multiSelect:true,
    				emptyText:'选择申诉类型...'
    			},
    			'-',
    			{iconCls:'icon-search',text:'查找',action:'search'},
    			'->'
	],
    initComponent: function() {
      	var me = this;
        Ext.apply(this, {
			columns: [
						{xtype:'rownumberer',text:'#',align:'center',width:30},
				        { text: '订单ID',align:'center', dataIndex: 'goodsOrder.id',hidden:true},
				        { text: '订单编号',align:'center',width:123, dataIndex: 'goodsOrder.orderNumber',locked:true,
				        	renderer:function(value,metaData,record){                	
                				return '<a href="javascript:void(0);">' + value + '</a>';              
				        	}
				        },
				        { text: '服务商品',align:'center',width:150, dataIndex: 'goodsOrder.service.serviceName',locked:true,
				        	renderer:function(value,metaData,record){ 
				        		if(record.get('goodsOrder.orderSource') == 1){
                					return '<a href="javascript:void(0);">' + record.get('goodsOrder.service.serviceName') + '</a>';   
				        		}else if(record.get('goodsOrder.orderSource') == 2){
				        			return '<a href="javascript:void(0);">' + record.get('goodsOrder.biddingService.name') + '</a>';
				        		}
				        	}
				        },
//				        { text: '服务类别ID',align:'center', dataIndex: 'goodsOrder.service.category.id',hidden:true},
//				        { text: '服务类别',align:'center',width:90, dataIndex: 'goodsOrder.service.category.text',hidden:true}, 
				        { text: '订单状态',align:'center',width:93, dataIndex: 'orderStatus',hidden:true,
				        	renderer:function(v){
				        		return PlatMap.GoodsOrder.orderStatus[v];
				        	}
				        },
				        { text: '申诉时间',align:'center',width:140, dataIndex: 'appealTime',locked:true},
				        { text: '申诉类型',align:'center',width:100, dataIndex: 'appealType',locked:true,
				        	renderer:function(v){
				        		return PlatMap.Appeal.appealType[v];
				        	}
				        },
				        { text: '附件',align:'center',width:140, dataIndex: 'attachment',locked:true,
				        	renderer:function(value,metaData,record){                	
                				return '<a target="_blank" href="upload/files/'+value+'">' + value + '</a>';              
				        	}
				        },
				        {
				        	xtype:'actioncolumn',
				        	text:'操作',
				        	align:'center',
				            sortable:false,
				            width:50,
				            locked:true,
				            items:[
				            	{
					               	iconCls:'icon-up',
					                tooltip: '申诉处理',
					                handler: function(grid, rowIndex, colIndex, node, e, record, rowEl){
			                			this.fireEvent('appealclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);	
			                		}
					            }
				            ]
				        },
				        { text: '价格',align:'center',width:110, dataIndex: 'goodsOrder.service.costPrice',
				        	renderer:function(value,metaData,record){  
				        		if(record.get('goodsOrder.orderSource') == 1){
				        			return record.get('goodsOrder.service.costPrice');
				        		}else if(record.get('goodsOrder.orderSource') == 2){
				        			if(record.get('goodsOrder.biddingService.minPrice') && record.get('goodsOrder.biddingService.maxPrice')){
				       				return record.get('goodsOrder.biddingService.minPrice')+'-'+record.get('goodsOrder.biddingService.maxPrice');
					       			}else if(!record.get('goodsOrder.biddingService.minPrice')){
					       				return record.get('goodsOrder.biddingService.maxPrice');
					       			}else if(!record.get('goodsOrder.biddingService.maxPrice')){
					       				return record.get('goodsOrder.biddingService.minPrice');
					       			}else if(!record.get('goodsOrder.biddingService.minPrice') && !record.get('goodsOrder.biddingService.maxPrice')){
					       				return 0;
					       			}
					        	}
				        	}
				        },
					    { text: '交易价格',align:'center',width:71, dataIndex: 'goodsOrder.transactionPrice'},
					    { text: '买家',align:'center',width:115, dataIndex: 'goodsOrder.buyer.enterprise.name'},
					    { text: '卖家',align:'center',width:115, dataIndex: 'goodsOrder.service.enterprise.name',
					    	renderer:function(value,metaData,record){     
					    		if(record.get('goodsOrder.orderSource') == 1){
					    			return record.get('goodsOrder.service.enterprise.name');
					    		}else if(record.get('goodsOrder.orderSource') == 2){
					    			return record.get('goodsOrder.biddingService.rname');
					    		}
					    	}
					    },
					    { text: '下单时间',align:'center',width:140, dataIndex: 'goodsOrder.createTime'},
					    { text: '备注',align:'center',width:140, dataIndex: 'remark'}
		    		],
		    		
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'business.AppealStore',  
					        dock: 'bottom',
					        displayInfo: true,
					        displayMsg:"显示第{0}条到第{1}条,共{2}条数据",      
       						emptyMsg:"查找不到任何数据"
					    }]
        });
        this.callParent();
    }
});