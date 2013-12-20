/**
 * 申诉处理 xuwf 2013-9-25
 */
Ext.apply(Ext.form.field.VTypes, {
        daterange: function(val, field) {
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
            /*
             * Always return true since we're only using this vtype to set the
             * min/max allowed values (these are tested for after the vtype test)
             */
            return true;
        }        
});
Ext.define('plat.view.business.QAppealGrid',{
	extend:'Ext.grid.Panel',
	xtype:'qappealgrid',  
	title:'申诉查询',
	id:'sscx',
	closable:true,
    closeAction:'hide',
	columnLines:true,
	emptyText:'没有找到相关的数据,请检查输出条件是否存在......',
    store:'business.OrderAndBiddingAppealStore',
    cls:'font-size:14px',
    tbar:[{
        xtype: 'buttongroup',
        columns: 4,
        title: '',
        items: [
        	{
				xtype:'triggerfield',
				triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
				width:200,
				name:'orderNumber',
				fieldLabel:'订单编号',
				labelWidth:60,
				emptyText:'输入订单编号...',
				labelStyle:'font-size:13',
				display:false,	//自定义变量判断triggercls是否display
    			onTriggerClick:function(){
					this.reset();
				}
			},{
				xtype:'triggerfield',
				triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
				width:200,	
				name:'buyer',
				fieldLabel:'买家企业',
				labelWidth:60,
				emptyText:'输入企业全称...',
				labelStyle:'font-size:13',
				display:false,	//自定义变量判断triggercls是否display
    			onTriggerClick:function(){
					this.reset();
					this.display = false;
		    		this.triggerCell.setDisplayed(false);
		    		this.setWidth(200);
				}
			},{
				xtype:'triggerfield',
				triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
				width:200,
				name:'seller',
				fieldLabel:'卖家企业',
				labelWidth:60,
				emptyText:'输入企业全称...',
				labelStyle:'font-size:13',
				display:false,	//自定义变量判断triggercls是否display
    			onTriggerClick:function(){
					this.reset();
					this.display = false;
		    		this.triggerCell.setDisplayed(false);
		    		this.setWidth(200);
				}
			},
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
			{
//				dateRange:{begin:'beginDate1',end:'endDate1'}, //用于VType类型dateRange
				xtype:'datefield',
				name:'startdt',
				width:200,
				labelWidth:60,
				emptyText:'请选中起始时间...',
				fieldLabel:'处理时间',
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
    initComponent: function() {
      	var me = this;
        Ext.apply(this, {
			columns: [
						{xtype:'rownumberer',text:'#',align:'center',width:30},
//				        { text: '订单ID',align:'center', dataIndex: 'goodsOrder.id',hidden:true},
				        { text: '订单编号',align:'center',width:123, dataIndex: 'oaNum',
				        	renderer:function(value,metaData,record){    
				        		return '<a href="javascript:void(0);">' + value + '</a>'; 
				        	}
				        },
				        { text: '服务商品',align:'center',width:150, dataIndex: 'serviceName',
				        	renderer:function(value,metaData,record){    
				        		if(record.data.oaNum.indexOf('S')!=-1){
                					return '<a href="javascript:void(0);">' + value + '</a>'; 
				        		}else{
				        			return '<a href="javascript:void(0);">'+value+'</a>';
				        		}
				        	}
				        },
//				        { text: '服务类别ID',align:'center', dataIndex: 'goodsOrder.service.category.id',hidden:true},
//				        { text: '服务类别',align:'center',width:90, dataIndex: 'goodsOrder.service.category.text',hidden:true}, 	   
				        { text: '价格',align:'center',width:90, dataIndex: 'price'
//				        	renderer:function(value,metaData,record){ 
//				        		if(record.data.oaNum.indexOf('S')!=-1){
//				        			return value;
//				        		}else if(!record.get('goodsOrder.id')){
//				        			if(record.get('biddingService.minPrice') && record.get('biddingService.maxPrice')){
//						       			return record.get('biddingService.minPrice')+'-'+record.get('biddingService.maxPrice');
//						       		}else if(!record.get('biddingService.minPrice')){
//						       			return record.get('biddingService.maxPrice');
//						       		}else if(!record.get('biddingService.maxPrice')){
//						       			return record.get('biddingService.minPrice');
//						       		}else if(!record.get('biddingService.minPrice') && !record.get('biddingService.maxPrice')){
//						       			return 0;
//						       		}
//				        		}
//				        	}
				        },
					    { text: '交易价格',align:'center',width:71, dataIndex: 'tranPrice'
//					    	renderer:function(value,metaData,record){ 
//					    		if(record.get('goodsOrder.id')){
//					    			return value;
//					    		}else if(!record.get('goodsOrder.id')){
//					    			return record.get('responseBidding.bidPrice');
//					    		}
//					    	}
					    },
					    { text: '买家',align:'center',width:115, dataIndex: 'buyer'
//					    	renderer:function(value,metaData,record){ 
//					    		if(record.get('goodsOrder.id')){
//					    			return value;
//					    		}else if(!record.get('goodsOrder.id')){
//					    			return record.get('biddingService.user.enterprise.name');
//					    		}
//					    	}
					    },
					    { text: '卖家',align:'center',width:115, dataIndex: 'seller'
//					    	renderer:function(value,metaData,record){ 
//					    		if(record.get('goodsOrder.id')){
//					    			return value;
//					    		}else if(!record.get('goodsOrder.id')){
//					    			return record.get('responseBidding.user.enterprise.name');
//					    		}
//					    	}
					    },
					    { text: '申诉类型',align:'center',width:90, dataIndex: 'appealType',
				        	renderer:function(v){
				        		return PlatMap.Appeal.appealType[v];
				        	}
				        },
				        { text: '客服备注',align:'center',width:100, dataIndex: 'handlerRemark'},
					    { text: '处理时间',align:'center',width:140, dataIndex: 'processorTime'}
		    		],
		    		
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'business.OrderAndBiddingAppealStore',  
					        dock: 'bottom',
					        displayInfo: true,
					        displayMsg:"显示第{0}条到第{1}条,共{2}条数据",      
       						emptyMsg:"查找不到任何数据"
					    }]
        });
        this.callParent();
    }
});