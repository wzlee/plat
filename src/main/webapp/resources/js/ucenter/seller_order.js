/**
 * 企业管理中心-卖家管理中心js xuwf 2013-9-6
 */

$(function(){ 
	$('#seller-order').tabs({
	    border:false,
	    onSelect:function(title){
	   		switch(title){
	   			case '订单查询': orderList();
	   				break;
	   			case '待我处理' : waitHandler();
	   				break;
	   			case '待我确认' : waitConfirm();
	   				break;
	   			case '待我关闭' : waitClose();
	   				break;
	   		}
    	}
    });	 	
});

 /*********************卖家管理-订单查询Tab*************************/
 function orderList(){
 	var grid = $('#order-list').datagrid({
    	method:'post',
        url:'order/queryorder',
		singleSelect:true,
		autoRowHeight:false,
		remoteSort:false,
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50],
		
        queryParams:{
        	flag:1,
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
        	{field:'boNum',title:'订单编号',align:'center',width:115,
        		formatter:function(value,rowData,rowIndex){
        			return "<a target='_blank' href='order/querydetail?op=11&orderId="+rowData.id+"'>"+rowData.orderNumber+'</a>';
        		}
        	},
            {field:'serviceName',title:'服务商品',align:'center',width:145,
            	formatter:function(value,rowData,rowIndex){
            		if(rowData.orderSource == 1){
	            		return '<a href="service/detail?id='+rowData.service.id
	            		+'" target="_blank">'+rowData.serviceName+'</a>';
            		}else if(rowData.orderSource == 2){
            			return '<a href="bidding/toDetail?op=11&bid='+rowData.biddingService.id
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
            {field:'transactionPrice',title:'交易价格(￥)',sortable:true,width:75,align:'right'},
            {field:'buyer',title:'买家',width:101,align:'center',
            	 formatter:function(value,rowData,rowIndex){
            	 	if(rowData.orderSource == 1){	
            	 		return rowData.buyer.enterprise.name;
            	 	}else if(rowData.orderSource == 2){
            	 		return rowData.biddingService.user.enterprise.name;
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
 
 
 /*********************待处理Tab*************************/
 function waitHandler(){
   var waitHandler = $('#wait-handler').datagrid({
    	method:'post',
        url:'order/querystatus',
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50],
        queryParams:{
        	flag:1,
        	userId:userId,
        	orderStatus:'1,6,8' 	
        },
        columns:[[ 
        	{field:'rowNum',title:'序号',align:'center',width:29,formatter:function(value,rowData,rowIndex){
        		return rowIndex+1;
        	}}, 
        	{field:'orderNumber',title:'订单编号',aligin:'center',width:115,
        		formatter:function(value,rowData,rowIndex){
        			return "<a target='_blank' href='order/querydetail?op=11&orderId="+rowData.id+"'>"+rowData.orderNumber+'</a>';
        		}
        	},
           	{field:'serviceName',title:'服务商品',align:'center',width:145,
            	formatter:function(value,rowData,rowIndex){
            		if(rowData.orderSource == 1){
	            		return '<a href="service/detail?id='+rowData.service.id
	            		+'" target="_blank">'+rowData.service.serviceName+'</a>';
            		}else if(rowData.orderSource == 2){
            			return '<a href="bidding/toDetail?op=11&bid='+rowData.biddingService.id
	            		+'" target="_blank">'+rowData.biddingService.name+'</a>';
            		}
            	}
            }, 
            {field:'transactionPrice',title:'价格(￥)',sortable:true,width:75,align:'right'},
            {field:'buyer',title:'买家',width:101,align:'center',
            	formatter:function(value,rowData,rowIndex){
            	 	if(rowData.orderSource == 1){	
            	 		return rowData.buyer.enterprise.name;
            	 	}else if(rowData.orderSource == 2){
            	 		return rowData.biddingService.user.enterprise.name;
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
            			case 8 : value = '<span>等待卖家关闭</span>';	
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
	            		if(rowData.orderStatus == 1){
	     					return "<a target='_blank' href='ucenter/seller_confirm?op=11&id="+rowData.id+"' >确认</a>"   							
	            		}else if(rowData.orderStatus == 6 || rowData.orderStatus == 8){
	            			if(rowData.orderSource == 1){
								return "<a target='_blank' href='ucenter/seller_close?op=11&id="+rowData.id+"' >关闭交易</a> | " +
	     							"<a target='_blank' href='ucenter/seller_appealform?op=11&id="+rowData.id+"' > 申诉</a>" 	            			
	            			}else if(rowData.orderSource == 2){
			            		return "<a target='_blank' href='ucenter/seller_bidding_close?op=11&id="+rowData.id+"'>关闭交易</a> |"+
	     							"<a target='_blank' href='ucenter/seller_bidding_appeal?op=11&id="+rowData.id+"'> 申诉</a>"
	            			}
	            		}
            	}
            }
        ]],
        onLoadSuccess:function(){
        }
    });  
 };
 /*********************待确认Tab*************************/
 function waitConfirm(){
 	var waitHandler = $('#wait-confirm').datagrid({
    	method:'post',
        url:'order/querystatus',
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50],
        queryParams:{
        	flag:1,
        	userId:userId,
        	orderStatus:'1'
        },
        columns:[[ 
        	{field:'rowNum',title:'序号',align:'center',width:29,formatter:function(value,rowData,rowIndex){
        		return rowIndex+1;
        	}}, 
        	{field:'orderNumber',title:'订单编号',aligin:'center',width:115,
        		formatter:function(value,rowData,rowIndex){
        			return "<a target='_blank' href='order/querydetail?op=12&orderId="+rowData.id+"'>"+rowData.orderNumber+'</a>';
        		}
        	},
           	{field:'serviceName',title:'服务商品',align:'center',width:145,
            	formatter:function(value,rowData,rowIndex){
            		if(rowData.orderSource == 1){
	            		return '<a href="service/detail?id='+rowData.service.id
	            		+'" target="_blank">'+rowData.service.serviceName+'</a>';
            		}else if(rowData.orderSource == 2){
            			return '<a href="bidding/toDetail?op=12&bid='+rowData.biddingService.id
	            		+'" target="_blank">'+rowData.biddingService.name+'</a>';
            		}
            	}
            }, 
            {field:'transactionPrice',title:'价格(￥)',sortable:true,width:75,align:'right'},
            {field:'buyer',title:'买家',width:101,align:'center',
            	formatter:function(value,rowData,rowIndex){
            	 	if(rowData.orderSource == 1){	
            	 		return rowData.buyer.enterprise.name;
            	 	}else if(rowData.orderSource == 2){
            	 		return rowData.biddingService.user.enterprise.name;
            	 	}
            	 }
            },
            {field:'orderStatus',title:'订单状态',width:85,align:'center',
            	formatter:function(value){
            		switch(value){
            			case 1 : value = '<span>等待卖家确认</span>';
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
     				return "<a target='_blank' href='ucenter/seller_confirm?op=12&id="+rowData.id+"' >确认</a> "
            	}
            }
        ]],
        onLoadSuccess:function(){
        }
    });  
 };
 
 /*********************待关闭Tab*************************/
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
        	flag:1,
        	userId:userId,
        	orderStatus:'6,8'
        },
        columns:[[ 
        	{field:'rowNum',title:'序号',align:'center',width:29,formatter:function(value,rowData,rowIndex){
        		return rowIndex+1;
        	}}, 
        	{field:'orderNumber',title:'订单编号',aligin:'center',width:115,
        		formatter:function(value,rowData,rowIndex){
        			return "<a target='_blank' href='order/querydetail?op=12&orderId="+rowData.id+"'>"+rowData.orderNumber+'</a>';
        		}
        	},
           	{field:'serviceName',title:'服务商品',align:'center',width:145,
            	formatter:function(value,rowData,rowIndex){
            		if(rowData.orderSource == 1){
	            		return '<a  href="service/detail?id='+rowData.service.id
	            		+'" target="_blank">'+rowData.service.serviceName+'</a>';
            		}else if(rowData.orderSource == 2){
            			return '<a href="bidding/toDetail?op=12&bid='+rowData.biddingService.id
	            		+'" target="_blank">'+rowData.biddingService.name+'</a>';
            		}
            	}
            }, 
            {field:'transactionPrice',title:'价格(￥)',sortable:true,width:75,align:'right'},
            {field:'buyer',title:'买家',width:101,align:'center',
            	formatter:function(value,rowData,rowIndex){
            	 	if(rowData.orderSource == 1){	
            	 		return rowData.buyer.enterprise.name;
            	 	}else if(rowData.orderSource == 2){
            	 		return rowData.biddingService.user.enterprise.name;
            	 	}
            	 }
            },
            {field:'orderStatus',title:'订单状态',width:85,align:'center',
            	formatter:function(value){
            		switch(value){
            			case 6 : value = '<span>交易进行中</span>';
            				break; 
            			case 8 : value = '<span>等待卖家关闭</span>';
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
	     				return "<a target='_blank' href='ucenter/seller_close?op=12&id="+rowData.id+"'>关闭交易</a> |"+
	     				"<a target='_blank' href='ucenter/seller_appealform?op=12&id="+rowData.id+"'> 申诉</a>"
            		}else if(rowData.orderSource == 2){
            			return "<a target='_blank' href='ucenter/seller_bidding_close?op=12&id="+rowData.id+"'>关闭交易</a> |"+
	     					"<a target='_blank' href='ucenter/seller_bidding_appeal?op=12&id="+rowData.id+"'> 申诉</a>"
            		}	
//	     			"<a href='javascript:void(0);' onclick='appeal(" + JSON.stringify(rowData) +"," + rowIndex + ");' iconCls='icon-search'>申诉</a>" 
            	}
            }
        ]],
        onLoadSuccess:function(){	
        }
    });  
 };

/*******************************确认、取消订单************************************/
/**
 * 弹出确认窗口
 * @param {} rowIndex 某项数据
 
function confirm(rowData,rowIndex){
	$('#win').window('open');
	$('#confirm #orderId').val(rowData.id);
	$('#confirm #orderNumber').text(rowData.orderNumber);
	$('#confirm #serviceName').text(rowData.service.serviceName);
	$('#confirm #price').numberbox('setValue',rowData.transactionPrice),
	$('#confirm #buyer').text(rowData.buyer.userName);
	$('#confirm #userName').text(rowData.userName);
	$('#confirm #phone').text(rowData.phone);
	$('#confirm #email').text(rowData.email);
	$('#confirm #buyRemark').html(rowData.remark);
	$('#confirm #remark').val('');
};
*/
 
// seller_confirm 点击 确认提交
function orderConfirm(){
	if(validatePrice()){
	$('#confirm').ajaxSubmit({
		url : 'order/orderconfirm',
		type : 'post',
		dataType : 'json',
		data:{
			orderId:$("#confirm input[name=orderId]").val(),
			price:$("#confirm input[name=price]").val(),
			remark:$("#confirm textarea[name=remark]").val()
		},
		success : function(ret) {
			if(ret.success){
				$('#apply_ok').show();
			}else{
				$('#apply_err').show();
			}
//			$.messager.alert('提示',ret.message);
//			window.location.href='ucenter/seller_order?op=4';	
		},
		error : function(ret) {
			art.dialog({
				title: '提示',
				content: '订单确认失败,请稍后再试',
				ok: function () {
				},
				okValue:'确定'
			});			
		}
	});	
	}else{
		$('#validate_err').show();
	}
}
//关闭验证提示窗口
function closeValidate(){
	$('#validate_err').css('display','none');
}
// 确认窗口 点击 取消订单
function orderCancel(){
//	console.log($("#confirm input[name=orderId]").val());
//	console.log($("#confirm input[name=price]").val());
//	console.log($("#confirm textarea[name=remark]").val());
	if(validatePrice()){
	$('#confirm').ajaxSubmit({
		url : 'order/ordercancel',
		type : 'post',
		dataType : 'json',
		data:{
			orderId:$("#confirm input[name=orderId]").val(),
			price:$("#confirm input[name=price]").val(),
			remark:$("#confirm textarea[name=remark]").val()
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
				content: '订单取消失败,请稍后再试',
				ok: function () {
				},
				okValue:'确定'
			});			
		}
	});	
	}else{
		$('#validate_err').show();
	}
}
/******************************交易结束*************************************/

/**
 *弹出关闭交易窗口

function dealClose(rowData,rowIndex){
	$('#dealEndWin').window('open');
	$('#close #orderId').val(rowData.id);
	$('#close #orderNumber').text(rowData.orderNumber);
	$('#close #serviceName').text(rowData.service.serviceName);
	$('#close #price').text('￥'+rowData.transactionPrice);
	$('#close #buyer').text(rowData.buyer.userName);
	$('#close #userName').text(rowData.userName);
	$('#close #phone').text(rowData.phone);
	$('#close #email').text(rowData.email);
	$('#close #remark').val('');
};
 */

// seller_close_deal.jsp 点击 "关闭交易"
function closeDeal(){
	$('#close').ajaxSubmit({
		url : 'order/closedeal',
		type : 'post',
		dataType : 'json',
		data:{
			flag:1,
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
//			$('#dealEndWin').window('close');
//			var tab = $('#seller-order').tabs('getSelected');	//得到选中的tab
//			var index = $('#seller-order').tabs('getTabIndex',tab);	//得到选中的tab的index
//			if(index == 1){//选中哪个tab就重新加载哪个tab的数据
//				waitHandler();
//			}else if(index == 3){
//				waitClose();	
//			}	
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
//关闭招标交易 seller_bidding_close.jsp
function closeBidding(){
	$('#biddingclose').ajaxSubmit({
		url : 'order/closeBidding',
		type : 'post',
		dataType : 'json',
		data:{
			flag:1,
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
/*****************************申诉**************************************/

/**
*弹出申诉窗口
function appeal(rowData,rowIndex){
	$('#appealWin').window('open');
	$('#appeal #orderId').val(rowData.id);
	$('#appeal #orderNumber').text(rowData.orderNumber);
	$('#appeal #serviceName').text(rowData.service.serviceName);
	$('#appeal #price').text('￥'+rowData.transactionPrice);
	$('#appeal #buyer').text(rowData.buyer.userName);
	$('#appeal #userName').text(rowData.userName);
	$('#appeal #phone').text(rowData.phone);
	$('#appeal #email').text(rowData.email);
	
	$('#appeal #remark').val('');
	$('#appeal #attachment').val('');
};
*/

//seller_appealform.jsp 提交申诉 xwf
function appealSubmit(){	
//	console.log($("#appeal input[name=orderId]").val());
//	console.log($("#appeal #cc").val());
//
//	console.log($("#appeal textarea[name=remark]").val());
//	console.log($("#appeal input[name=attachment]").val());
	var files = $('input[type="file"]');
	if('' == files.val()){//不上传附件
		$('#appeal').ajaxSubmit({
			url : 'order/orderappeal',
			type : 'post',
			dataType : 'json',
			data:{
				flag:1,
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
						flag:1,
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
				flag:1,
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
						flag:1,
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

	/** *******************表单验证************************ */
	function validatePrice(){
			var ele = $('#price');
			if(ele.siblings('label').length==0){
				ele.parent().append('<label class="info"></label>');
			}
			var info = ele.siblings('label');
			var patt = /^\d*$/;
			if(ele.val() == ''){
				info.removeClass('right').addClass('error').html('价格不能为空');
				return false;
			}else{
				if(!patt.test(ele.val())){
					info.removeClass('right').addClass('error').html('价格必须为数字');
					return false;
				}else{
					info.removeClass('error').addClass('right').html('');
					return true;
				}
			}
	}

/*****************************订单详情**************************************/
/**
 * 查看订单详情

function orderDetail(id){
	$.ajax({
		type : 'POST',
		url : 'order/querydetail',
		data: {orderId:id},
		dataType : 'json',
		beforeSend:function(){					
		},
		success : function(data) {
			if (data.success) {
				$("#orderDetailWin").window("open");
				loadOrderData(data.rows);
			}else {
				$.messager.alert('提示','服务器正忙,请稍后再试');
			}
		},
		failure : function(response) {
			$.messager.alert('提示','服务器正忙,请稍后再试');
		}			
	});	
}
 */

/**
 * 加载订单详情
 * @param {} rows
function loadOrderData(rows){
	$('.orderDetail #orderNumber').text(rows.order.orderNumber);
	$('.orderDetail #serviceName').text(rows.order.service.serviceName);
	$('.orderDetail #price').text(rows.order.transactionPrice);
	$('.orderDetail #buyer').text(rows.order.buyer.enterprise.name);
	$('.orderDetail #userName').text(rows.order.userName);
	$('.orderDetail #phone').text(rows.order.phone);
	$('.orderDetail #email').text(rows.order.email);
	
	if(rows.evalList.length == 0){//订单的买家和卖家都没有评价
		$('.orderDetail #buyerEval').text('暂无');
		$('.orderDetail #sellerEval').text('暂无');
	}else{
		for (var index = 0; index < rows.evalList.length; index++) { 
			if(rows.evalList[index].user.id == rows.order.buyer.id){
				$('.orderDetail #buyerEval').text(getStaisfaction(rows.evalList[index].satisfaction));
			}else{
				$('.orderDetail #sellerEval').text(getStaisfaction(rows.evalList[index].satisfaction));
				
			}
		}
	}
		
	var html = '';
	if(rows.infoList.length > 0){
		for (var index = 0; index < rows.infoList.length; index++) {
			html += '<tr>';
			html += ' <td>'+getAction(rows.infoList[index].action)+'</td>';
			html += ' <td>'+rows.infoList[index].processTime+'</td>';
			html += ' <td>'+rows.infoList[index].processor.userName+'</td>';
			html += ' <td>'+getOrderStatus(rows.infoList[index].orderStatus)+'</td>';
			html += '</tr>';
		}
	}
	$("#orderDetailWin .orderInfo").html('<tr><th>动作</th><th>时间</th><th>操作人</th><th>订单状态</th</tr>');
	$("#orderDetailWin .orderInfo").append(html);
}
*/

/**
 * orderEvaluation评价由数字转换成实际字符串
 * @param {} satisfaction
 * @return {}
function getStaisfaction(satisfaction){
	switch(satisfaction){
		case 1:
			satisfaction = '差';
			break;
		case 2:
			satisfaction = '不满意';
			break;
		case 3:
			satisfaction = '一般';
			break;
		case 4:
			satisfaction = '满意';
			break;
		case 5:
			satisfaction = '差十分满意';
			break;
		default:
			satisfaction = '无';
			break;
	}
	return satisfaction;
}
*/

/**
 * order状态由数字转换成实际字符串
 * @param {} status
 * @return {}

function getOrderStatus(status){
	switch(status){
		case 1:
			status = '等待卖家确认';
			break;
		case 2:
			status = '交易进行中';
			break;
		case 3:
			status = '等待买家关闭';
			break;
		case 4:
			status = '等待卖家关闭';
			break;
		case 5:
			status = '申诉处理中';
			break;
		case 6:
			status = '交易结束';
			break;
		case 7:
			status = '订单取消';
			break;
		default:
			status = '无';
			break;
	}
	return status;
}
*/

/**
 * orderInfo动作由数字转换成实际字符串
 * @param {} action
 * @return {}
function getAction(action){
	switch(action){
		case 1:
			action = '买家提交订单';
			break;
		case 2:
			action = '卖家确认订单';
			break;
		case 3:
			action = '进入交易';
			break;
		case 4:
			action = '买家评价';
			break;
		case 5:
			action = '卖家评价';
			break;
		case 6:
			action = '管理员关闭交易';
			break;
		case 7:
			action = '卖家取消订单';
			break;
		case 8:
			action = '管理员取消订单';
			break;
		case 9:
			action = '买家申诉';
			break;
		case 10:
			action = '卖家申诉';
			break;
		default:
			action = '无';
			break;
	}
	return action;
}
 */