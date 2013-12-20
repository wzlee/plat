$(function(){
	responseList();
	
	// 选择
	$('#select').click(function(){
		if(selectedRid < 0){
			art.dialog({title: '提示',content: '请先选择您需要的服务，谢谢！',okValue:'确定',ok:function(){},lock: true});
			return;
		}
		$.post('bidding/selectResponse',{bid : $('#bid').val(), rid : selectedRid}, function(ret){
			if(ret.success){
				$('#apply_ok').show();
			}else{
				$('#apply_err').show();
			}
		},'json');
	});
	// 取消招标
	$('#cancel').click(function(){
		$.post('bidding/updateIssueOrChannl',{bid : $('#bid').val(), status : 11}, function(ret){
			if(ret.success){
				$('#apply_ok').show();
			}else{
				$('#apply_err').show();
			}
		},'json');
	});
});

var selectedRowIndex = -1; // 选中行
var selectedPageIndex = -1; // 选中页
var selectedRid = -1; // 选中应标ID

/** *******************应标列表************************ */
function responseList(){
	var grid = $('#response-list').datagrid({
		url : 'bidding/getResponseBidding',
		method : 'post',
		singleSelect : true,
		autoRowHeight : false,
		remoteSort : false,
		pagination : true,
		pageSize : 2,
		pageList : [2, 5, 10, 20, 30, 40, 50],
		queryParams : {
			bid : $('#bid').val()
		},
		me : this,
		columns : [[{
					field : 'id',
					title : '选择',
					align : 'center',
					width : 50,
					formatter : function(value, rowData, rowIndex) {
						return '<input type="radio" name="response" value="'+ value +'" />';
					}
				}, {
					field : 'temp1',
					title : '序号',
					align : 'center',
					width : 50,
					formatter : function(value, rowData, rowIndex) {
						var pageNumber = $('#response-list').datagrid('options').pageNumber;
		        		var pageSize = $('#response-list').datagrid('options').pageSize;
		        		var rowIndex=(pageNumber-1)*pageSize+rowIndex+1;
						return rowIndex;
					}
				}, {
					field : 'user.userName',
					title : '用户名',
					align : 'center',
					width : 100,
					formatter : function(value, rowData, rowIndex) {
						return rowData.user.userName;
					}
				}, {
					field : 'bidPrice',
					title : '应标价格',
					align : 'center',
					width : 100
				}, {
					field : 'attachment',
					title : '附件',
					width : 100,
					align : 'center'
				}, {
					field : 'description',
					title : '备注',
					width : 100,
					align : 'center'
				}, {
					field : 'user.enterprise.name',
					title : '企业名称',
					width : 100,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						return rowData.user.enterprise.name;
					}
				}, {
					field : 'linkMan',
					title : '联系人',
					width : 100,
					align : 'center'
				}, {
					field : 'tel',
					title : '联系电话',
					width : 100,
					align : 'center'
				}, {
					field : 'responseTime',
					title : '应标时间',
					width : 100,
					align : 'center',
					formatter : function(value, rowData, rowIndex) {
						return value.substring(0,11);
					}
				}]],
		onLoadSuccess : function() {
			var pageNumber = $('#response-list').datagrid('options').pageNumber;
			$('input[name="response"]').click(function(){
				if($(this).is(':checked')){
					selectedRowIndex = $('input[name="response"]').index($(this));
					selectedPageIndex = pageNumber;
					selectedRid = $(this).val();
				}
			});
			if(pageNumber == selectedPageIndex){
				$('input[name="response"]').eq(selectedRowIndex).attr('checked',true);
			}
		}
	});
}