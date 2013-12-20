$(function(){
	biddingLog();
	
	// 发布招标 
	$('#issue').click(function(){
		$.post('bidding/updateIssueOrChannl',{bid : $('#bid').val(), status : 2}, function(ret){
			if(ret.success){
				$('#apply_ok').show();
			}else{
				$('#apply_err').show();
			}
		},'json');
	});
	
	// 取消招标 
	$('#esc').click(function(){
		$.post('bidding/updateIssueOrChannl',{bid : $('#bid').val(), status : 11}, function(ret){
			if(ret.success){
				$('#apply_ok').show();
			}else{
				$('#apply_err').show();
			}
		},'json');
	});
});

/** *******************操作日志************************ */
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
						return '<span>'+PlatMap.BiddingService.status[value]+'</span>';
					}
				}]],
		onLoadSuccess : function() {
		}
	});
}