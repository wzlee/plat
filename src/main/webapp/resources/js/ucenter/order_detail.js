$(function(){
	orderLog();
	biddingLog();
});

/** *******************操作日志************************ */
function orderLog(){
	var grid = $('#order-log').datagrid({
		url : 'order/getOrderLog',
		method : 'post',
		singleSelect : true,
		autoRowHeight : false,
		remoteSort : false,
		pagination : true,
		pageSize : 5,
		pageList : [5, 10, 20, 30, 40, 50],
		queryParams : {
			orderId : $('#orderId').val()
		},
		columns : [[{
					field : 'processor.userName',
					title : '操作人',
					align : 'center',
					width : 110,
					formatter : function(value, rowData, rowIndex) {
						if(rowData.processor){
							return rowData.processor.userName;
						}else if(rowData.staff){
							return rowData.staff.userName;
						}else if(rowData.manager){
							return rowData.manager.username;
						}else{
							return '卖家窗口';
						}
					}
				}, {
					field : 'action',
					title : '动作',
					align : 'center',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return PlatMap.OrderInfo.action[value];
					}
				}, {
					field : 'processTime',
					title : '时间',
					align : 'center',
					width : 150
				}, {
					field : 'remark',
					title : '备注',
					align : 'center',
					width : 200
				}, {
					field : 'orderStatus',
					title : '订单状态',
					width : 120,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						return PlatMap.GoodsOrder.orderStatus[value];
					}
				}]],
		onLoadSuccess : function() {
		}
	});
}
function biddingLog(){
	var grid = $('#bidding-log').datagrid({
		url : 'bidding/getBiddingLog',
		method : 'post',
		singleSelect : true,
		autoRowHeight : false,
		remoteSort : false,
		pagination : true,
		pageSize : 5,
		pageList : [5, 10, 20, 30, 40, 50],
		queryParams : {
			bid : $('#bid').val()
		},
		columns : [[{
					field : 'user.userName',
					title : '操作人',
					align : 'center',
					width : 110,
					formatter : function(value, rowData, rowIndex) {
						if(rowData.user){
							return rowData.user.userName;
						}else if(rowData.staff){
							return rowData.staff.userName;
						}else{
							return rowData.manager.username;
						}
					}
				}, {
					field : 'action',
					title : '动作',
					align : 'center',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return PlatMap.BiddingServiceDetail.action[value];
					}
				}, {
					field : 'processTime',
					title : '时间',
					align : 'center',
					width : 150
				}, {
					field : 'remark',
					title : '备注',
					align : 'center',
					width : 200
				}, {
					field : 'biddingStatus',
					title : '订单状态',
					width : 120,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						return PlatMap.BiddingService.status[value];
					}
				}]],
		onLoadSuccess : function() {
		}
	});
}