$(function(){
	loadstafflist();	  
	$("#search").click(function(){		
		loadstafflist();
	});
});
function loadstafflist(){	
	$('#my_favorites_list').datagrid({
    	method:'post',
        url:'myFavorites/queryByUserId',        
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,
		pageSize:20,
		remoteSort:false,
		pageList:[10,20,30,40,50],
        queryParams:{
        	     	
        },
        columns:[[
        	{field:'rowNum',title:'序号',align:'center',width:29,formatter:function(value,rowData,rowIndex){
        		return rowIndex+1;
        	}},
        	{field:'serviceName',title:'服务名称',align:'center',width:180, formatter : function(value,rowData,rowIndex) {
        		return "<a style='color:#0000FF;' href='service/detail?id=" + rowData.serviceId +"' target='_blank'>" + value + "</a>";
        	}},        	
            {field:'categoryName',title:'服务类别',align:'center',width:180 ,formatter:function(value,rowData,rowIndex){            	
            	return value;
            }},   
            {field:'time',title:'收藏时间',width:180,align:'center',formatter:function(value,rowData,rowIndex){
            	return value;
            }},
            {field:'controller',title:'操作',align:'center',width:180, sortable:true,formatter:function(value,rowData,rowIndex){
        		value = "<a style='color:#0000FF;' href='javascript:void(0);' onclick='waive_my_favorites("+ rowData.id + "," + rowIndex + ")'>取消收藏</a>";
            	return value;
            }}
        ]],
        onLoadSuccess:function(){
        	if($('#my_favorites_list').datagrid("getData").total == 0){
        		$('.datagrid-body').html('<div style="color:#666;margin-left: 350px; margin-top: 50px; ">您还没有收藏任何服务!</div>');
        	}
        }
    });
};

/**
 * 取消服务收藏
 * @param id 服务收藏id
 * @param rowIndex 序号
 * */
function waive_my_favorites(id, rowIndex){
	art.dialog({
		title: '提示',
	    content: '确认要取消收藏此服务吗?',
	    fixed : true,
	    lock : true,
	    okValue : '确认',
	    cancelValue : '关闭',
	    ok: function () {
	       $.ajax({
				type : 'POST',
				url : 'myFavorites/delete',
				data : {
					id : id
				},
				dataType : 'json',
				beforeSend : function(){					
				},
				success : function(data) {
					if (data.success) {
						$('#my_favorites_list').datagrid('reload',{});
						art.dialog({
						    title: '提示',
						    content: data.message,
						    fixed : true,
						    ok : function () {},
			    			okValue : '关闭'
						});
					}else {
						art.dialog({
						    title: '提示',
						    ok : function () {},
						    okValue : '关闭',
						    content: data.message,
						    fixed : true,
						    lock : true
						});
					}
				},
				failure : function(response) {
					art.dialog({
					    title: '提示',
					    ok : function () {},
					    okValue : '关闭',
					    content: '服务器正忙,请稍后再试',
					    fixed : true,
					    lock : true
					});
				}
				
			});
	    },
	    cancel : function () {
	    }
	});
	
	/*$.messager.confirm('提示', '确认要取消收藏这条服务吗?' , function(btn){		
		if (btn){	
			$.ajax({
				type : 'POST',
				url : 'myFavorites/delete',
				data : {
					id : id
				},
				dataType : 'json',
				beforeSend : function(){					
				},
				success : function(data) {
					if (data.success) {
						$('#my_favorites_list').datagrid('reload',{});
						art.dialog({
						    title: '提示',
						    content: data.message,
						    fixed : true,
						    ok : function () {},
			    			okValue : '关闭'
						});
					}else {
						art.dialog({
						    title: '提示',
						    ok : function () {},
						    okValue : '关闭',
						    content: data.message,
						    fixed : true,
						    lock : true
						});
					}
				},
				failure : function(response) {
					art.dialog({
					    title: '提示',
					    ok : function () {},
					    okValue : '关闭',
					    content: '服务器正忙,请稍后再试',
					    fixed : true,
					    lock : true
					});
				}
				
			});
		}
	});*/
}