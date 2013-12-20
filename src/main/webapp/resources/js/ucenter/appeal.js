/**
 * 企业管理中心-买家申诉js xuwf 2013-9-9
 */
$(function(){ 
	$('#easyui-linkbutton1').click(function(){
		myappeal();
	});
	$('#easyui-linkbutton2').click(function(){
		beappeal();
	});
	
	$('.easyui-tabs').tabs({
	    border:false,
	    onSelect:function(title){
	   		switch(title){
	   			case '我的申诉': myappeal();
	   				break;
	   			case '我被申诉' : beappeal();
	   				break;
	   		}
    	}
    });
 });
	/*********************我的申诉Tab*************************/
    function myappeal(){
	 	$('#my-appeal').datagrid({
	    	method:'post',
	        url:'order/appeal',
	        singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			remoteSort:false,
			pageSize:10,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	flag:0,
	        	appealType:1,
	        	orderNo:$("#myorderNo").val(),
	        	startTime:$("#mystartTime").datebox('getValue'),
	        	endTime:$("#myendTime").datebox('getValue'),
	        	userId:userId
	        },
	        columns:[[ 
	        	{field:'rowNum',title:'序号',align:'center',width:30,
	        		formatter:function(value,rowData,rowIndex){
		        		
		        		return rowIndex+1;
        			}
        		},
	        	{field:'oaNum',title:'订单编号',align:'center',width:110,
	        		formatter:function(value,rowData,rowIndex){
	        			return rowData.goodsOrder.orderNumber;
            		}
	        	},
	            {field:'serviceName',title:'服务商品',align:'center',width:145,
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData.goodsOrder.serviceName;
//	            		if(rowData.goodsOrder){
//	            			return rowData.goodsOrder.service.serviceName;
//	            		}else{
//	            			return rowData.biddingService.name;
//	            		}
	            	}
	            },  
	            {field:'price',title:'价格',width:80,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		if(rowData.goodsOrder.orderSource == 1){
            				return rowData.goodsOrder.service.costPrice;
	            		}else if(rowData.goodsOrder.orderSource == 2){
	            			if(rowData.goodsOrder.biddingService.minPrice && rowData.goodsOrder.biddingService.maxPrice){
	            				return rowData.goodsOrder.biddingService.minPrice+'-'+rowData.goodsOrder.biddingService.maxPrice;
	            			}else if(!rowData.goodsOrder.biddingService.minPrice){
	            				return rowData.goodsOrder.biddingService.maxPrice;
	            			}else if(!rowData.goodsOrder.biddingService.maxPrice){
	            				return rowData.goodsOrder.biddingService.minPrice;
	            			}else{
	            				return '面议';
	            			}
	            		}
	            	}
	            },
	            {field:'tranPrice',title:'交易价格',width:65,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData.goodsOrder.transactionPrice;	
	            	}
	            },
	            {field:'seller',title:'卖家',width:101,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		if(rowData.goodsOrder.orderSource == 1){
	            			return rowData.goodsOrder.service.enterprise.name;
	            		}else if(rowData.goodsOrder.orderSource == 2){
	            			return rowData.goodsOrder.biddingService.rname;
	            		}
	            	}
	            },
	            {field:'goodsOrderTime',title:'下单时间',align:'center',width:75,
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData.goodsOrder.createTime.substr(0,10);
	            	}
	            },
	            {field:'reason',title:'申诉原因',align:'center',width:65,
	            	formatter:function(value,rowData,rowIndex){
	            		switch(value){
	            			case 1:
	            				value = "买家违约";
	            				break;
	            			case 2:
	            				value = "卖家违约";
	            				break;
	            			case 3:
	            				value = "其他";
	            				break;
	            		}
            			return value;
	            	}
	            },
	            {field:'handlerRemark',title:'客服备注',align:'center',width:100,
	            	formatter:function(value,rowData,rowIndex){
	            		value = rowData.handlerRemark;
	            		if(value){
	            			var temp = value.replace(/<[^>].*?>/g,'').substring(0,4);
	            			value = "<div class='zb-description'>"+temp+"<div class='zb-description-all'>"+value+"</div></div>";
	            		}
	            		return value;
	            	},
	            	styler:function(value,rowData,rowIndex){
	            		return 'overflow:visible';
	            	}
	            },
	            {field:'processorTime',title:'处理时间',align:'center',width:75,
	            	formatter:function(value,rowData,rowIndex){
	            		if(rowData.processorTime){
	            			return rowData.processorTime.substr(0,10);
	            		}
	            	}
	            }
	        ]],
	        onLoadSuccess:function(){
	        	$('.zb-description').hover(function(){
					$(this).children().show();
				},function(){
					$(this).children().hide();
				});
	        }
	    });  
    }
   /*********************我被申诉Tab*************************/
	function beappeal(){
		$('#be-appeal').datagrid({
			method:'post',
	        url:'order/appeal',
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	flag:0,
	        	appealType:2,
	        	orderNo:$("#beorderNo").val(),
	        	startTime:$("#bestartTime").datebox('getValue'),
	        	endTime:$("#beendTime").datebox('getValue'),
	        	userId:userId
	        },
			columns:[[ 
				{field:'rowNum',title:'序号',align:'center',width:30,
	        		formatter:function(value,rowData,rowIndex){
		        		return rowIndex+1;
        			}
        		},
				{field:'oaNum',title:'订单编号',align:'center',width:110,
					formatter:function(value,rowData,rowIndex){
	        			return rowData.goodsOrder.orderNumber;
            		}
				},
	            {field:'serviceName',title:'服务商品',align:'center',width:145,
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData.goodsOrder.serviceName;
	            	}
	            },  
	            {field:'price',title:'价格',width:80,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		if(rowData.goodsOrder.orderSource == 1){
            				return rowData.goodsOrder.service.costPrice;
	            		}else if(rowData.goodsOrder.orderSource == 2){
	            			if(rowData.goodsOrder.biddingService.minPrice && rowData.goodsOrder.biddingService.maxPrice){
	            				return rowData.goodsOrder.biddingService.minPrice+'-'+rowData.goodsOrder.biddingService.maxPrice;
	            			}else if(!rowData.goodsOrder.biddingService.minPrice){
	            				return rowData.goodsOrder.biddingService.maxPrice;
	            			}else if(!rowData.goodsOrder.biddingService.maxPrice){
	            				return rowData.goodsOrder.biddingService.minPrice;
	            			}else{
	            				return '面议';
	            			}
	            		}
	            	}
	            },
	            {field:'tranPrice',title:'交易价格',width:65,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData.goodsOrder.transactionPrice;	
	            	}
	            },
	            {field:'seller',title:'卖家',width:101,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		if(rowData.goodsOrder.orderSource == 1){
	            			return rowData.goodsOrder.service.enterprise.name;
	            		}else if(rowData.goodsOrder.orderSource == 2){
	            			return rowData.goodsOrder.biddingService.rname;
	            		}
	            	}
	            },
	            {field:'goodsOrderTime',title:'下单时间',align:'center',width:75,
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData.goodsOrder.createTime.substr(0,10);
	            	}
	            },
	            {field:'reason',title:'申诉原因',align:'center',width:65,
	            	formatter:function(value,rowData,rowIndex){
	            		switch(value){
	            			case 1:
	            				value = "买家违约";
	            				break;
	            			case 2:
	            				value = "卖家违约";
	            				break;
	            			case 3:
	            				value = "其他";
	            				break;
	            		}
            			return value;
	            	}
	            },
	            {field:'handlerRemark',title:'客服备注',align:'center',width:100,
	            	formatter:function(value,rowData,rowIndex){
	            		value = rowData.handlerRemark;
	            		if(value){
	            			var temp = value.replace(/<[^>].*?>/g,'').substring(0,4);
	            			value = "<div class='zb-description'>"+temp+"<div class='zb-description-all'>"+value+"</div></div>";
	            		}
	            		return value;
	            	},
	            	styler:function(value,rowData,rowIndex){
	            		return 'overflow:visible';
	            	}
	            },
	            {field:'processorTime',title:'处理时间',align:'center',width:75,
	            	formatter:function(value,rowData,rowIndex){
	            		if(rowData.processorTime){
	            			return rowData.processorTime.substr(0,10);
	            		}
	            	}
	            }
			]],
			onLoadSuccess:function(){
				$('.zb-description').hover(function(){
					$(this).children().show();
				},function(){
					$(this).children().hide();
				});
			}
		});  
    }
