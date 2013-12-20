 var eid = $("#user").attr("value");   //企业ID
 var rowdata=new Array();	//将每行的数据在渲染时存入数组中 
 $(function(){ 	
	$('#sclass').combobox({  
	    data : eval($('#myServices').html()),
			valueField:'id',  
	    textField:'text',
	    editable:false
	});  
	$('#service').tabs({
	    border:false,
	    plain:true,
	    onSelect:function(title){	    	
	   		switch(title){
	   			case '新服务': loadnservice();
	   				break;
	   			case '已上架' : loaduservice();
	   				break;
	   			case '已下架' : loaddservice();
	   				break;
	   			case '已删除' : loadrservice();
	   				break;
	   		}
		}
	});
	$('#search').click(function() {
		var tab = $('#service').tabs('getSelected');
		var index = $('#service').tabs('getTabIndex',tab);
		switch(index){
			case 0 : loadnservice();
				break;
			case 1 : loaduservice();
				break;
			case 2 : loaddservice();
				break;
			case 3 : loadrservice();
				break;
		}

	});
	
 });

 /********加载新服务grid*********/
 function loadnservice(){
 	
 	var nservicegrid = $('#nservice').datagrid({
    	method:'post',
        url:'service/queryservice',        
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,
		pageSize:20,
		remoteSort:false,
		pageList:[10,20,30,40,50],
        queryParams:{
        	serviceName:$('#sname').val(),
        	queryStatus:'1,2',
        	eid:eid,
        	cid:$('#sclass').combobox('getValue')
        },
        columns:[[
        	{field:'rowNum',title:'序号',align:'center',width:30,formatter:function(value,rowData,rowIndex){
        		var pageNumber = $('#nservice').datagrid('options').pageNumber;
        		var pageSize = $('#nservice').datagrid('options').pageSize;
        		var rowIndex=(pageNumber-1)*pageSize+rowIndex+1;
        		     		
        		if(rowData.serviceSource==2){
        			return '<a style="color:red;">*</a>' + rowIndex;
        		}
        		return rowIndex;
        	}},        	
            {field:'serviceName',title:'服务名称',align:'center',width:153
//            	,formatter:function(value,rowData,rowIndex){            	
//           		return '<strong>'+value+'</strong>';
//            	}
            },   
            {field:'category.text',title:'服务类别',align:'center',width:100,formatter:function(value,rowData,rowIndex){            	
            	return rowData.category.text;
            }}, 
            {field:'currentStatus',title:'服务状态',width:100,align:'center',formatter:function(value,rowData,rowIndex){            	
            	switch(value){
            		case 1 : value = '<span>新服务</span>';
            			break;
            		case 2 : value = '<span>上架审核中</span>';
            			break; 
            		case 5 : value = '<span>已删除</span>';
            			break;
            	}
            	return value;            	
            }},
            {field:'registerTime',title:'添加时间',align:'center',width:80,sortable:true,formatter:function(value){            	
				return value.substr(0, 10);
            }},
            {field:'costPrice',title:'价格(￥)',width:60,align:'right',sortable:true,formatter:function(value){
            	return value==0?"面议":value;
            }},
            {field:'action',title:'操作',width:219,align:'center',
            	formatter:function(v,rowData,rowIndex){
            			var value = "<a href='ucenter/service_detail?op=5&id="+rowData.id+"'>详情</a> | ";
            			rowdata[rowIndex] = JSON.stringify(rowData);
            			if(rowData.currentStatus!=1){
            				value += "申请上架 | 删除 | ";
//            				value += "<a style='color:#797979;cursor:default;' href='javascript:void(0);'>申请上架</a> | ";
//            				value += "<a style='color:#797979;cursor:default;' href='javascript:void(0);'>删除</a> | ";
//            				value += "<a href='ucenter/approval_list?op=5&sid="+ rowData.id +"');'>审批记录</a>";
            			}else {
            				value += "<a href='javascript:void(0);' onclick='applyup("+rowData.id+","+rowData.currentStatus+","+rowIndex+");'>申请上架</a> | ";
            				value += "<a href='javascript:void(0);' onclick='deleted("+rowData.id+","+rowData.currentStatus+","+rowIndex+");'>删除</a> | ";
            			}
            			value += "<a href='ucenter/approval_list?op=5&sid="+ rowData.id +"');'>审批记录</a>";
     					return value;
     							 
            	}
            }
        ]],
        onLoadSuccess:function(){
        }
    });
 };
 
 /********加载已上架grid*********/
 function loaduservice(){
 	
 	var uservicegrid = $('#uservice').datagrid({
    	method:'post',
        url:'service/queryservice',        
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,		
		pageSize:20,
		pageList:[10,20,30,40,50],
        queryParams:{
        	serviceName:$('#sname').val(),
        	queryStatus:'3,4,7',
        	eid:eid,
        	cid:$('#sclass').combobox('getValue')
        },
        columns:[[
        	{field:'rowNum',title:'序号',align:'center',width:30,formatter:function(value,rowData,rowIndex){
        		var pageNumber = $('#uservice').datagrid('options').pageNumber;
        		var pageSize = $('#uservice').datagrid('options').pageSize;
        		var rowIndex=(pageNumber-1)*pageSize+rowIndex+1;
        		     		
        		if(rowData.serviceSource==2){
        			return '<a style="color:red;">*</a>' + rowIndex;
        		}
        		return rowIndex;
        	}},
            {field:'serviceName',title:'服务名称',align:'center',width:153
//	            ,formatter:function(value,rowData,rowIndex){            	
//	            	return '<strong>'+value+'</strong>';
//	            }
            },  
            {field:'category.text',title:'服务类别',align:'center',width:100,formatter:function(value,rowData,rowIndex){            	
            	return rowData.category.text;
            }}, 
            {field:'currentStatus',title:'服务状态',width:100,align:'center',formatter:function(value){
            	switch(value){
            		case 3 : value='<span>已上架</span>';
            			break;
            		case 4 : value='<span>变更审核中</span>';
            			break;
            		case 7 : value='<span>下架审核中</span>';
            			break;
            	}
            	return value;
            }},
            {field:'registerTime',title:'添加时间',align:'center',width:80,sortable:true,formatter:function(value){            	
				return value.substr(0, 10);
            }},
            {field:'costPrice',title:'价格(￥)',width:60,align:'right',sortable:true,formatter:function(value){
            	return value==0?"面议":value;
            }},
            {field:'action',title:'操作',width:219,align:'center',
            	formatter:function(v,rowData,rowIndex){
            		var value = "<a href='ucenter/service_detail?op=5&id="+rowData.id+"'>详情</a> | ";
            		rowdata[rowIndex] = JSON.stringify(rowData);
            		if(rowData.currentStatus!=3){
            			value += "申请下架 | 申请变更 | ";
//            			value += "<a style='color:#797979;cursor:default;' href='javascript:void(0);'>申请下架</a> | ";
//            			value += "<a style='color:#797979;cursor:default;' href='javascript:void(0);'>申请变更</a> | ";
//            			value += "<a href='ucenter/approval_list?op=5&sid="+ rowData.id +"');'>审批记录</a>";
            		}else {
            			value += "<a href='javascript:void(0);' onclick='applydown("+rowData.id+","+rowData.currentStatus+","+rowIndex+");'>申请下架</a> | "
            			value += "<a href='ucenter/service_detail?op=5&id="+rowData.id+"'>申请变更</a> | "
            		}
            			value += "<a href='ucenter/approval_list?op=5&sid="+ rowData.id +"');'>审批记录</a>";
     				return value;
            	}
            }
        ]],
        onLoadSuccess:function(){
        }
    });
 };
 
 /********加载已下架grid*********/
 function loaddservice(){
 	
 	var dservicegrid = $('#dservice').datagrid({
    	method:'post',
        url:'service/queryservice',        
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,		
		pageSize:20,
		pageList:[10,20,30,40,50],
        queryParams:{
        	serviceName:$('#sname').val(),
        	queryStatus:'6,2',
        	eid:eid,
        	cid:$('#sclass').combobox('getValue')
        },
        columns:[[
        	{field:'rowNum',title:'序号',align:'center',width:30,formatter:function(value,rowData,rowIndex){
        		var pageNumber = $('#dservice').datagrid('options').pageNumber;
        		var pageSize = $('#dservice').datagrid('options').pageSize;
        		var rowIndex=(pageNumber-1)*pageSize+rowIndex+1;
        		     		
        		if(rowData.serviceSource==2){
        			return '<a style="color:red;">*</a>' + rowIndex;
        		}
        		return rowIndex;
        	}},
            {field:'serviceName',title:'服务名称',align:'center',width:153
//	            ,formatter:function(value,rowData,rowIndex){            	
//	            	return '<strong>'+value+'</strong>';
//	            }
            },  
            {field:'category.text',title:'服务类别',align:'center',width:100,formatter:function(value,rowData,rowIndex){            	
            	return rowData.category.text;
            }}, 
            {field:'currentStatus',title:'服务状态',width:100,align:'center',formatter:function(value){
            	switch(value){            		
            		case 2 : value = '<span>上架审核中</span>';
            			break; 
            		case 5 : value = '<span>已删除</span>';
            			break;
            		case 6 : value = '<span>已下架</span>';
            			break;
            	}
            	return value;
            }},
            {field:'registerTime',title:'添加时间',align:'center',width:80,sortable:true,formatter:function(value){            	
				return value.substr(0, 10);
            }},
            {field:'costPrice',title:'价格(￥)',width:60,align:'right',sortable:true,formatter:function(value){
            	return value==0?"面议":value;
            }},
            {field:'action',title:'操作',width:219,align:'center',
            	formatter:function(v,rowData,rowIndex){
            		var value = "<a href='ucenter/service_detail?op=5&id="+rowData.id+"'>详情</a> | ";
            		rowdata[rowIndex] = JSON.stringify(rowData);
            		if(rowData.currentStatus!=6){
            			value += "申请上架 | 删除 | ";
//            			value += "<a style='color:#797979;cursor:default;' href='javascript:void(0);'>申请上架</a> | ";
//            			value += "<a style='color:#797979;cursor:default;' href='javascript:void(0);'>删除</a> | ";
//            			value += "<a href='ucenter/approval_list?op=5&sid="+ rowData.id +"');'>审批记录</a>";
            		}else {
            			value += "<a href='javascript:void(0);' onclick='applyup("+rowData.id+","+rowData.currentStatus+","+rowIndex+");'>申请上架</a> | "
            			value += "<a href='javascript:void(0);' onclick='deleted("+rowData.id+","+rowData.currentStatus+","+rowIndex+");'>删除</a> | "
            		}
            		value += "<a href='ucenter/approval_list?op=5&sid="+ rowData.id +"');'>审批记录</a>";
     				return value;
            	}
            }
        ]],
        onLoadSuccess:function(){
        }
    });
 };
 
 /********加载已删除grid*********/
 function loadrservice(){
 	
 	var rservicegrid = $('#rservice').datagrid({
    	method:'post',
        url:'service/queryservice',        
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,		
		pageSize:20,
		pageList:[10,20,30,40,50],
        queryParams:{
        	serviceName:$('#sname').val(),
        	queryStatus:'5',
        	eid:eid,
        	cid:$('#sclass').combobox('getValue')
        },
        columns:[[
        	{field:'rowNum',title:'序号',align:'center',width:30,formatter:function(value,rowData,rowIndex){
        		var pageNumber = $('#rservice').datagrid('options').pageNumber;
        		var pageSize = $('#rservice').datagrid('options').pageSize;
        		var rowIndex=(pageNumber-1)*pageSize+rowIndex+1;
        		     		
        		if(rowData.serviceSource==2){
        			return '<a style="color:red;">*</a>' + rowIndex;
        		}
        		return rowIndex;
        	}},
            {field:'serviceName',title:'服务名称',align:'center',width:153
//	            ,formatter:function(value,rowData,rowIndex){            	
//	            	return '<strong>'+value+'</strong>';
//	            }
            },  
            {field:'category.text',title:'服务类别',align:'center',width:100,formatter:function(value,rowData,rowIndex){            	
            	return rowData.category.text;
            }}, 
            {field:'currentStatus',title:'服务状态',width:100,align:'center',formatter:function(value){
            	switch(value){            		
            		case 1 : value = '<span>新服务</span>';
            			break; 
            		case 5 : value = '<span>已删除</span>';
            			break;
            		case 6 : value = '<span>已下架<span>';
            			break;
            	}
            	return value;
            }},
            {field:'registerTime',title:'添加时间',align:'center',width:80,sortable:true,formatter:function(value){            	
				return value.substr(0, 10);
            }},
            {field:'costPrice',title:'价格(￥)',width:60,align:'right',sortable:true,formatter:function(value){
            	return value==0?"面议":value;
            }},
            {field:'action',title:'操作',width:219,align:'center',
            	formatter:function(v,rowData,rowIndex){
            		var value = "<a href='ucenter/service_detail?op=5&id="+rowData.id+"'>详情</a> | ";
            		rowdata[rowIndex] = JSON.stringify(rowData);
            		//if(rowData.currentStatus!=5){
            		//	value += "还原 | ";
//            			value += "<a style='color:#797979;cursor:default;' href='javascript:void(0);'>还原</a> | ";
//            			value += "<a href='ucenter/approval_list?op=5&sid="+ rowData.id +"');'>审批记录</a>";
            		//}else {
            		//	value += "<a href='javascript:void(0);' onclick='restore("+rowData.id+","+rowData.lastStatus+","+rowIndex+");'>还原</a> | ";
            		//}
            		value += "<a href='ucenter/approval_list?op=5&sid="+ rowData.id +"');'>审批记录</a>";
     				return value;
            	}
            }
        ]],
        onLoadSuccess:function(){
        }
    });
 };
  /********申请上架*********/
  /*********只有新服务和已下架服务有申请上架动作,因此要先做判断**************/
 function applyup(id,currentStatus,rowIndex){
 	art.dialog({
		title: '提示',
		content: '确认申请上架吗?',
		ok: function () {
			var grid;
	 		if(currentStatus==1){
	 			grid = $('#nservice');
	 		}else {
	 			grid = $('#dservice');
	 		}
			$.ajax({
				type : 'POST',
				url : 'service/statuschange',
				data: {
					id:id,
					currentStatus:2
				},
				dataType : 'json',
				beforeSend:function(){					
				},
				success : function(data) {					
					if (data.success) {
						grid.datagrid('updateRow',{
							index: rowIndex,
							row: {
								currentStatus: 2								
							}
						});
						art.dialog({
						    title: '提示',
						    content: '服务成功申请上架,请等待审核',
						    ok: function () {
					    	},
					    	okValue:'确定'
						});
					}else {
						art.dialog({
						    title: '提示',
						    content: '申请上架失败，该服务类别不属于你的服务领域',
						    ok: function () {
					    	},
					    	okValue:'确定'
						});						
					}
				},
				failure : function(response) {
					art.dialog({
						    title: '提示',
						    content: '服务器正忙,请稍后再试',
						    ok: function () {
					    	},
					    	okValue:'确定'
					});
				}
				
			});
		},
		cancel: function () {
			
		},
		okValue:'确定',
		cancelValue:'取消'
	});
 };
  /********申请下架*********/
 function applydown(id,currentStatus,rowIndex){
 	art.dialog({
		title: '提示',
		content: '确认申请下架吗?',
		ok: function () {
			$.ajax({
				type : 'POST',
				url : 'service/statuschange',
				data: {
					id:id,
					currentStatus:7
				},
				dataType : 'json',
				beforeSend:function(){					
				},
				success : function(data) {
					if (data.success) {
						$('#uservice').datagrid('updateRow',{
							index: rowIndex,
							row: {
								currentStatus: 7								
							}
						});
						art.dialog({
						    title: '提示',
						    content: '服务成功申请下架,请等待审核',
						    ok: function () {
					    	},
					    	okValue:'确定'
						});
					}else {
						art.dialog({
						    title: '提示',
						    content: '服务器正忙,请稍后再试',
						    ok: function () {
					    	},
					    	okValue:'确定'
						});						
					}
				},
				failure : function(response) {
					art.dialog({
						    title: '提示',
						    content: '服务器正忙,请稍后再试',
						    ok: function () {
					    	},
					    	okValue:'确定'
					});			
				}				
			});
		},
		cancel: function () {
			
		},
		okValue:'确定',
		cancelValue:'取消'
	});
 };
 
  /********删除服务*********/
 /*********只有新服务和已下架服务有删除动作,因此要先做判断**************/
 function deleted(id,currentStatus,rowIndex){
 	var grid;
 	if(currentStatus==1){
 		grid = $('#nservice');
 	}else {
 		grid = $('#dservice');
 	}
 	art.dialog({
		title: '提示',
		content: '确认删除吗?',
		ok: function () {
			$.ajax({
				type : 'POST',
				url : 'service/statuschange',
				data: {
					id:id,
					currentStatus:5
				},
				dataType : 'json',
				beforeSend:function(){					
				},
				success : function(data) {
					if (data.success) {
						grid.datagrid('updateRow',{
							index: rowIndex,
							row: {
								currentStatus: 5								
							}
						});
						art.dialog({
						    title: '提示',
						    content: '服务已成功删除',
						    ok: function () {
					    	},
					    	okValue:'确定'
						});
					}else {
						art.dialog({
						    title: '提示',
						    content: '服务器正忙,请稍后再试',
						    ok: function () {
					    	},
					    	okValue:'确定'
						});						
					}
				},
				failure : function(response) {
					art.dialog({
						    title: '提示',
						    content: '服务器正忙,请稍后再试',
						    ok: function () {
					    	},
					    	okValue:'确定'
					});
				}
				
			});
		},
		cancel: function () {
			
		},
		okValue:'确定',
		cancelValue:'取消'
	});
 };
 
  /********还原服务*********/
 function restore(id,lastStatus,rowIndex){
 	art.dialog({
		title: '提示',
		content: '确认还原吗?',
		ok: function () {
			$.ajax({
				type : 'POST',
				url : 'service/statuschange',
				data: {
					id:id,
					currentStatus:lastStatus
				},
				dataType : 'json',
				beforeSend:function(){					
				},
				success : function(data) {
					if (data.success) {
						$('#rservice').datagrid('updateRow',{
							index: rowIndex,
							row: {
								currentStatus:lastStatus						
							}
						});
						art.dialog({
						    title: '提示',
						    content: '服务已成功还原',
						    ok: function () {
					    	},
					    	okValue:'确定'
						});
					}else {
						art.dialog({
						    title: '提示',
						    content: '还原服务失败，该服务类别不属于你的服务领域',
						    ok: function () {
					    	},
					    	okValue:'确定'
						});						
					}
				},
				failure : function(response) {
					art.dialog({
						    title: '提示',
						    content: '服务器正忙,请稍后再试',
						    ok: function () {
					    	},
					    	okValue:'确定'
					});
				}				
			});
		},
		cancel: function () {
			
		},
		okValue:'确定',
		cancelValue:'取消'
	});
 };

 
