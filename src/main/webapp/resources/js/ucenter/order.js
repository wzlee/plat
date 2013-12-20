/**
 * 企业管理中心-买家管理中心js xuwf 2013-9-6
 */
$(function(){ 
	$('#buy-order').tabs({
	    border:false,
	    onSelect:function(title){
	   		switch(title){
	   			case '订单查询': orderList();
	   				break;
	   			case '待我关闭' : waitClose();
	   				break;
	   		}
    	}
    });
	 	
});

//var userId = $("#userId").val();
 /*********************买家管理-订单查询Tab*************************/
 function orderList(){
 	var grid = $('#order-list').datagrid({
        url:'order/queryorder',
    	method:'post',
		singleSelect:true,
		autoRowHeight:false,
		remoteSort:false,
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50],
        queryParams:{
        	flag:0,
        	userId:userId,
        	orderNo:$("#orderNo").val(),
        	serviceName:$("#serviceName").val(),
        	orderStatus:$("#orderStatus").combobox('getValue'),
        	startTime:$("#startTime").datebox('getValue'),
        	endTime:$("#endTime").datebox('getValue')
        },
         columns:[[ 
       	 	{field:'rowNum',title:'序号',align:'center',width:29,formatter:function(value,rowData,rowIndex){
        		return rowIndex+1;
        	}},        	
        	{field:'boNumber',title:'订单编号',align:'center',width:115,
        		formatter:function(value,rowData,rowIndex){
        			return "<a target='_blank' href='order/querydetail?op=8&orderId="+rowData.id+"'>"+rowData.orderNumber+'</a>';
        		}
        	},
            {field:'serviceName',title:'服务商品',align:'center',width:145,
            	formatter:function(value,rowData,rowIndex){
            		if(rowData.orderSource == 1){
	            		return '<a href="service/detail?id='+rowData.service.id
	            		+'" target="_blank">'+rowData.serviceName+'</a>';
            		}else if(rowData.orderSource == 2){
            			return '<a href="bidding/toDetail?op=8&bid='+rowData.biddingService.id
	            		+'" target="_blank">'+rowData.serviceName+'</a>';
            		}
            	}
            },  
            {field:'categoryName',title:'服务类别',align:'center',width:90,
	            formatter:function(value,rowData,rowIndex){
	            	if(rowData.orderSource == 1){	
	            		return rowData.service.category.text;
	            	}else if(rowData.orderSource == 2){
	            		return rowData.biddingService.category.text;
	            	}
	            }
            }, 
            {field:'transactionPrice',title:'交易价格(￥)',sortable:true,width:75,align:'center'},
            {field:'seller',title:'卖家',width:101,align:'center',
            	 formatter:function(value,rowData,rowIndex){
            	 	if(rowData.orderSource == 1){	
            	 		return rowData.service.enterprise.name;
            	 	}else if(rowData.orderSource == 2){
            	 		return rowData.biddingService.rname;
            	 	}
            	 }
            },
            {field:'orderStatus',title:'订单状态',width:85,align:'center',
            	formatter:function(value){
            		switch(value){
            			case 1 : value = '<span>等待卖家确认</span>';
            				break;
            			case 6 : value = '<span>交易进行中</span>';
            				break; 
            			case 7 : value = '<span>等待买家关闭</span>';
            				break;
            			case 8 : value = '<span>等待卖家关闭</span>';
            				break;
            			case 9 : value = '<span>申诉处理中</span>';
            				break;
            			case 10 : value = '<span>交易结束</span>';
            				break;
            			case 11 : value = '<span>订单取消</span>';
            				break;
            			default: value = '<span>亲,状态呢</span>';
            				break;
            		}
            		return value;
            	}
            },
            {field:'createTime',title:'下单时间',align:'center',width:120,sortable:true,
            	formatter:function(value){            	
					return value.substr(0, 10);
            	}
            }
        ]],
        onLoadSuccess:function(){
        }
    });  
 };
  /*********************买家管理-待关闭Tab*************************/
 function waitClose(){
   var waitClose = $('#wait-close').datagrid({
    	method:'post',
        url:'order/querystatus',
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50],
        queryParams:{
        	flag:0,
        	userId:userId,
        	orderStatus:'6,7'
        },
        columns:[[ 
			{field:'rowNum',title:'序号',align:'center',width:29,formatter:function(value,rowData,rowIndex){
        		return rowIndex+1;
        	}},
        	{field:'orderNumber',title:'订单编号',align:'center',width:115,
        		formatter:function(value,rowData,rowIndex){
        			return "<a target='_blank' href='order/querydetail?op=9&orderId="+rowData.id+"'>"+rowData.orderNumber+'</a>';
        		}
        	},
            {field:'serviceName',title:'服务商品',align:'center',width:145,
            	formatter:function(value,rowData,rowIndex){
            		if(rowData.orderSource == 1){
	            		return '<a href="service/detail?id='+rowData.service.id
	            		+'" target="_blank">'+rowData.service.serviceName+'</a>';
            		}else if(rowData.orderSource == 2){
            			return '<a href="bidding/toDetail?op=9&bid='+rowData.biddingService.id
	            		+'" target="_blank">'+rowData.biddingService.name+'</a>';
            		}
            	}
            },  
            {field:'transactionPrice',title:'交易价格(￥)',sortable:true,width:75,align:'center'},
            {field:'seller',title:'卖家',width:101,align:'center',
            	 formatter:function(value,rowData,rowIndex){
            	 	if(rowData.orderSource == 1){	
            	 		return rowData.service.enterprise.name;
            	 	}else if(rowData.orderSource == 2){
            	 		return rowData.biddingService.rname;
            	 	}
            	 }
            },
            {field:'orderStatus',title:'订单状态',width:85,align:'center',
            	formatter:function(value){
            		switch(value){
            			case 6 : value = '<span>交易进行中</span>';
            				break; 
            			case 7 : value = '<span>等待买家关闭</span>';
            				break;
            			default: value = '<span>亲,状态呢</span>';
            				break;
            		}
            		return value;
            	}
            },
            {field:'createTime',title:'下单时间',align:'center',width:90,sortable:true,
            	formatter:function(value){            	
					return value.substr(0, 10);
            	}
            },
            {field:'caozuo',title:'交易操作',width:100,align:'center',
            	formatter:function(value,rowData,rowIndex){
            		if(rowData.orderSource == 1){
	     				return "<a target='_blank' href='ucenter/buyer_close?op=8&id="+rowData.id+"'>关闭交易</a> |"+
	     					"<a target='_blank' href='ucenter/buyer_appealform?op=8&id="+rowData.id+"'>申诉</a>"
            		}else if(rowData.orderSource == 2){
            			return "<a target='_blank' href='ucenter/buyer_bidding_close?op=8&id="+rowData.id+"'>关闭交易</a> |"+
	     					"<a target='_blank' href='ucenter/buyer_bidding_appeal?op=8&id="+rowData.id+"'> 申诉</a>"
            		}
            	}
            }
        ]],
        onLoadSuccess:function(){	
        }
    });  
 };

// buyer_close_deal.jsp 点击 "关闭交易"
function closeDeal(){
	$('#close').ajaxSubmit({
		url : 'order/closedeal',
		type : 'post',
		dataType : 'json',
		data:{
			flag:0,
			orderId:$("#close input[name=orderId]").val(),
			satisfaction:$("#close input[name=satisfaction]:checked").val(),
			remark:$("#close textarea[name=remark]").val()
		},
		success : function(ret) {
			if(ret.success){
				$('#apply_ok').show();
			}else{
				$('#apply_err').show();
			}	
		},
		error : function(ret) {
			art.dialog({
				title: '提示',
				content: '关闭交易失败,请稍后再试',
				ok: function () {
				},
				okValue:'确定'
			});			
		}
	});		
}

//关闭招标交易
function closeBidding(){
	$('#biddingclose').ajaxSubmit({
		url : 'order/closeBidding',
		type : 'post',
		dataType : 'json',
		data:{
			flag:0,
			bidId:$("#biddingclose input[name=bidId]").val(),
			satisfaction:$("#biddingclose input[name=satisfaction]").val(),
			remark:$("#biddingclose textarea[name=remark]").val()
		},
		success : function(ret) {
			if(ret.success){
				$('#apply_ok').show();
			}else{
				$('#apply_err').show();
			}	
		},
		error : function(ret) {
			art.dialog({
				title: '提示',
				content: '关闭交易失败,请稍后再试',
				ok: function () {
				},
				okValue:'确定'
			});
		}
	});		
}

/*******************************************************************/

// 提交申诉
function appealSubmit(){	
	var files = $('input[type="file"]');
	if('' == files.val()){//不上传附件
		$('#appeal').ajaxSubmit({
			url : 'order/orderappeal',
			type : 'post',
			dataType : 'json',
			data:{
				flag:0,
				orderId:$("#appeal input[name=orderId]").val(),
				reason:$("#appeal #cc").val(),
				remark:$("#appeal textarea[name=remark]").val(),
				attachment:''
			},
			success : function(ret) {
				if(ret.success){
				$('#apply_ok').show();
				}else{
					$('#apply_err').show();
				}				
			},
			error : function(ret) {
				art.dialog({
					title: '提示',
					content: '订单申诉失败',
					ok: function () {
					},
					okValue:'确定'
				});				
			}
		});
		return;
	}
	var form = $('<form action="public/uploadAttachment" method="post" '
		+ 'enctype="multipart/form-data"></form>');
	form.append(files.clone());
	form.ajaxSubmit({
		url : 'public/uploadAttachment',
		type : 'post',
		dataType : 'json',
		clearForm : false,
		success : function(data) {
			if(data.success){
				files.next().val(data.message);
				$('#appeal').ajaxSubmit({
					url : 'order/orderappeal',
					type : 'post',
					dataType : 'json',
					data:{
						flag:0,
						orderId:$("#appeal input[name=orderId]").val(),
						reason:$("#appeal #cc").val(),
						remark:$("#appeal textarea[name=remark]").val(),
						attachment:$("#appeal input[name=attachment]").val()
					},
					success : function(ret) {
						if(ret.success){
							$('#apply_ok').show();
						}else{
							$('#apply_err').show();
						}				
					},
					error : function(ret) {
						art.dialog({
							title: '提示',
							content: '订单申诉失败',
							ok: function () {
							},
							okValue:'确定'
						});						
					}
				});	
			}			
		},
		error : function() {
			art.dialog({
				title: '提示',
				content: '附件上传失败',
				ok: function () {
				},
				okValue:'确定'
			});			
		}
	});
}
//招单申诉
function appealBidding(){	
	var files = $('input[type="file"]');
	if('' == files.val()){//不上传附件
		$('#biddingappeal').ajaxSubmit({
			url : 'order/biddingappeal',
			type : 'post',
			dataType : 'json',
			data:{
				flag:0,
				bidId:$("#biddingappeal input[name=bidId]").val(),
				reason:$("#biddingappeal #cc").val(),
				remark:$("#biddingappeal textarea[name=remark]").val(),
				attachment:''
			},
			success : function(ret) {
				if(ret.success){
				$('#apply_ok').show();
				}else{
					$('#apply_err').show();
				}				
			},
			error : function(ret) {
				art.dialog({
					title: '提示',
					content: '订单申诉失败',
					ok: function () {
					},
					okValue:'确定'
				});
			}
		});
		return;
	}
	var form = $('<form action="public/uploadAttachment" method="post" '
		+ 'enctype="multipart/form-data"></form>');
	form.append(files.clone());
	form.ajaxSubmit({
		url : 'public/uploadAttachment',
		type : 'post',
		dataType : 'json',
		clearForm : false,
		success : function(data) {
			if(data.success){
				files.next().val(data.message);
				$('#biddingappeal').ajaxSubmit({
					url : 'order/biddingappeal',
					type : 'post',
					dataType : 'json',
					data:{
						flag:0,
						bidId:$("#biddingappeal input[name=bidId]").val(),
						reason:$("#biddingappeal #cc").val(),
						remark:$("#biddingappeal textarea[name=remark]").val(),
						attachment:$("#biddingappeal input[name=attachment]").val()
					},
					success : function(ret) {
						if(ret.success){
							$('#apply_ok').show();
						}else{
							$('#apply_err').show();
						}					
					},
					error : function(ret) {
						art.dialog({
							title: '提示',
							content: '订单申诉失败',
							ok: function () {
							},
							okValue:'确定'
						});						
					}
				});	
			}			
		},
		error : function() {
			art.dialog({
				title: '提示',
				content: '附件上传失败',
				ok: function () {
				},
				okValue:'确定'
			});			
		}
	});
}






//
// /*********************买家管理-订单查询Tab*************************/
// function orderList(){
// 	var grid = $('#order-list').datagrid({
//        url:'order/querybo',
//    	method:'post',
//		singleSelect:true,
//		autoRowHeight:false,
//		remoteSort:false,
//		pagination:true,
//		pageSize:10,
//		pageList:[10,20,30,40,50],
//        queryParams:{
//        	flag:0,
//        	userId:userId,
//        	orderNo:$("#orderNo").val(),
//        	serviceName:$("#serviceName").val(),
//        	orderStatus:$("#orderStatus").combobox('getValue'),
//        	startTime:$("#startTime").datebox('getValue'),
//        	endTime:$("#endTime").datebox('getValue')
//        },
//         columns:[[ 
//       	 	{field:'rowNum',title:'序号',align:'center',width:29,formatter:function(value,rowData,rowIndex){
//        		return rowIndex+1;
//        	}},        	
//        	{field:'boNumber',title:'订单编号',align:'center',width:115,
//        		formatter:function(value,rowData,rowIndex){
//        			return "<a target='_blank' href='order/querydetail?op=3&orderId="+rowData.boId+"'>"+rowData.boNum+'</a>';
//        		}
//        	},
//            {field:'serviceName',title:'服务商品',align:'center',width:145,
//            	formatter:function(value,rowData,rowIndex){
//            		if(rowData.orderSource == 1){
//	            		return '<a href="service/detail?id='+rowData.serviceId
//	            		+'" target="_blank">'+rowData.serviceName+'</a>';
//            		}else if(rowData.orderSource == 2){
//            			return '<a href="bidding/toDetail?op=3&bid='+rowData.serviceId
//	            		+'" target="_blank">'+rowData.serviceName+'</a>';
//            		}
//            	}
//            },  
//            {field:'categoryName',title:'服务类别',align:'center',width:90
////	            formatter:function(value,rowData,rowIndex){
////	            	if(rowData.orderSource == 1){	
////	            		return rowData.service.category.text;
////	            	}else if(rowData.orderSource == 2){
////	            		return rowData.biddingService.category.text;
////	            	}
////	            }
//            }, 
//            {field:'price',title:'交易价格(￥)',sortable:true,width:75,align:'center'},
//            {field:'seller',title:'卖家',width:101,align:'center'
////            	 formatter:function(value,rowData,rowIndex){
////            	 	if(rowData.orderSource == 1){	
////            	 		return rowData.service.enterprise.name;
////            	 	}else if(rowData.orderSource == 2){
////            	 		return rowData.biddingService.rname;
////            	 	}
////            	 }
//            },
//            {field:'status',title:'订单状态',width:85,align:'center',
//            	formatter:function(value){
//            		switch(value){
//            			case 1 : value = '<font color=#800080>等待卖家确认</font>';
//            				break;
//            			case 6 : value = '<font color=#800080>交易进行中</font>';
//            				break; 
//            			case 7 : value = '<font color=#800080>等待买家关闭</font>';
//            				break;
//            			case 8 : value = '<font color=#800080>等待卖家关闭</font>';
//            				break;
//            			case 9 : value = '<font color=#800080>申诉处理中</font>';
//            				break;
//            			case 10 : value = '<font color=#800080>交易结束</font>';
//            				break;
//            			case 11 : value = '<font color=#800080>订单取消</font>';
//            				break;
//            			default: value = '<font color=#800080>亲,状态呢</font>';
//            				break;
//            		}
//            		return value;
//            	}
//            },
//            {field:'createTime',title:'下单时间',align:'center',width:120,sortable:true,
//            	formatter:function(value){            	
//					return value.substr(0, 10);
//            	}
//            }
//        ]],
//        onLoadSuccess:function(){
//        }
//    });  
// };