/**
 * 订单管理 xuwf 2013-9-24
 */
Ext.apply(Ext.form.field.VTypes, {
        daterange1: function(val, field) {
            var date = field.parseDate(val);
            if (!date) {
                return false;
            }
            if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
                var start = field.up('buttongroup').down('#' + field.startDateField);
                start.setMaxValue(date);
                start.validate();
                this.dateRangeMax = date;
            }
            else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
                var end = field.up('buttongroup').down('#' + field.endDateField);
                end.setMinValue(date);
                end.validate();
                this.dateRangeMin = date;
            }
            return true;
        }        
});
Ext.define('plat.view.business.OrderGrid',{
	extend:'Ext.grid.Panel',
	xtype:'ordergrid',  
	title:'订单管理',
	id:'ddgl',
	closable:true,
    closeAction:'hide',
	columnLines:true,
	emptyText:'没有找到相关的数据,请检查输出条件是否存在......',
    store:'business.OrderStore',
    cls:'font-size:14px',
    tbar:[{
        xtype: 'buttongroup',
        columns: 3,
        title: '',
        items: [
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
    		},
        	{
				xtype:'triggerfield',
				triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
				width:200,
				name:'serviceName',
				fieldLabel:'服务商品',
				labelWidth:60,
				emptyText:'请输入服务商品...',
				labelStyle:'font-size:13',
    			onTriggerClick:function(){
					this.reset();
				}
			},
			{
    				xtype:'orderstatus',
    				width:200,
    				fieldLabel:'订单状态',
    				name:'orderStatus',
    				labelWidth:60,
    				forceSelection:true,
    				labelStyle:'font-size:13',
    				multiSelect:true,
    				emptyText:'选择订单状态...'
    		},
			{
//				dateRange:{begin:'beginDate1',end:'endDate1'}, //用于VType类型dateRange
				xtype:'datefield',
				name:'startdt',
				width:200,
				labelWidth:60,
				emptyText:'请选中起始时间...',
				fieldLabel:'下单时间',
				labelStyle:'font-size:13',
				itemId: 'startdt',
		        vtype: 'daterange',
		        endDateField: 'enddt' // id of the end date field
			},
			{
				xtype:'datefield',
				name:'enddt',
				width:200,
				labelWidth:60,
				emptyText:'请选中结束时间...',
				fieldLabel:'结束时间',
				labelStyle:'font-size:13',
				itemId: 'enddt',
		        vtype: 'daterange',
		        startDateField: 'startdt' // id of the start date field
			},
        	{iconCls:'icon-search',text:'查找',action:'search'}
        ]
    }],
//	features: [{
//		ftype:'grouping',
//		name:'group',
//		id:'group',
//		groupByText:'按属性分组',
//		showGroupsText:'显示分组',
//		startCollapsed:false,
//		groupHeaderTpl: '<font color="green">{name}</font> : 共[ <font color="green">{rows.length}</font> ]个订单'
//	}],
    initComponent: function() {
      	var me = this;
        Ext.apply(this, {
			columns: [
						{xtype:'rownumberer',text:'#',align:'center',width:30},
				        { text: '订单ID',align:'center', dataIndex: 'id',hidden:true},
				        { text: '订单编号',align:'center',width:123, dataIndex: 'orderNumber',
				        	renderer:function(value,metaData,record){                	
                				return '<a href="javascript:void(0);">' + value + '</a>';              
				        	}
				        },
				        { text: '服务商品',align:'center',width:150, dataIndex: 'service.serviceName',
				        	renderer:function(value,metaData,record){   
				        		if(record.data.orderSource == 1){
                					return '<a href="javascript:void(0);">' + record.data.serviceName + '</a>';   
				        		}else{
				        			return '<a href="javascript:void(0);">' + record.data.serviceName + '</a>';   
				        		}
				        	}
				        },
				        { text: '服务类别ID',align:'center', dataIndex: 'service.category.id',hidden:true},
				        { text: '服务类别',align:'center',width:90, dataIndex: 'service.category.text',
				        	renderer:function(value,metaData,record){   
				        		if(record.data.orderSource == 1){
                					return  record.get('service.category.text');   
				        		}else{
				        			return  record.get('biddingService.category.text') ;   
				        		}
				        	}
				        }, 
				        { text: '订单状态',align:'center',width:93, dataIndex: 'orderStatus',
				        	renderer:function(v){
				        		return PlatMap.GoodsOrder.orderStatus[v];
				        	}
				        },
				        { text: '下单时间',align:'center',width:138, dataIndex: 'createTime'},
				        {
				        	xtype:'actioncolumn',
				        	text:'操作',
				        	align:'center',
				            sortable:false,
				            width:50,
				            items:[
				            	{
					               	iconCls:'icon-up',
					                tooltip: '订单处理',
					                handler: function(grid, rowIndex, colIndex, node, e, record, rowEl){
			                			this.fireEvent('handleclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);	
			                		},
					                isDisabled:function(grid, rowIndex, colIndex){
					                	var orderStatus = grid.getStore().getAt(rowIndex).data.orderStatus;
					                	if(orderStatus == 10 || orderStatus == 11){
					                		return true;
					                	}
					                	return false;
					                }
					            }
				            ]
				        },
				        { text: '价格',align:'center',width:53, dataIndex: 'service.costPrice',
				        	renderer:function(value,metaData,record){  
				        		if(record.data.orderSource == 1){
				        			return record.get('service.costPrice');
				        		}else if(record.data.orderSource == 2){
				        			if(record.get('biddingService.minPrice') && record.get('biddingService.maxPrice')){
				       				return record.get('biddingService.minPrice')+'-'+record.get('biddingService.maxPrice');
					       			}else if(!record.get('biddingService.minPrice')){
					       				return record.get('biddingService.maxPrice');
					       			}else if(!record.get('biddingService.maxPrice')){
					       				return record.get('biddingService.minPrice');
					       			}else if(!record.get('biddingService.minPrice') && !record.get('biddingService.maxPrice')){
					       				return 0;
					       			}
					        	}
				        	}
				        },
					    { text: '交易价格',align:'center',width:71, dataIndex: 'transactionPrice'},
					    { text: '买家',align:'center',width:115, dataIndex: 'buyer.enterprise.name'},
					    { text: '卖家',align:'center',width:115, dataIndex: 'service.enterprise.name',
					    	renderer:function(value,metaData,record){     
					    		if(record.data.orderSource == 1){
					    			return record.get('service.enterprise.name');
					    		}else if(record.data.orderSource == 2){
					    			return record.get('biddingService.rname');
					    		}
					    	}
					    },
					   	{ text: '联系人',align:'center',width:80, dataIndex: 'userName'},
					   	{ text: '联系电话',align:'center',width:100, dataIndex: 'phone'},
					   	{ text: '邮箱',align:'center',width:120, dataIndex: 'email'}
		    		],
		    		
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'business.OrderStore',  
					        dock: 'bottom',
					        displayInfo: true,
					        displayMsg:"显示第{0}条到第{1}条,共{2}条数据",      
       						emptyMsg:"查找不到任何数据"
					    }]
        });
        this.callParent();
    }
});