 var eid = $("#user").attr("value");   //企业ID
 var sid = $("#sid").attr("value");   //服务ID
 $(function(){
 	var approvegrid = $('#approve').datagrid({
    	method:'post',
        url:'service/queryApproval',        
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,
		pageSize:20,
		remoteSort:false,
		pageList:[10,20,30,40,50],
        queryParams:{        	
        	eid:eid,
        	sid:sid
        },
        columns:[[
        	{field:'rowNum',title:'序号',align:'center',width:30,formatter:function(value,rowData,rowIndex){
        		var pageNumber = $('#approve').datagrid('options').pageNumber;
        		var pageSize = $('#approve').datagrid('options').pageSize;
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
            {field:'operatStatus',title:'审核状态',align:'center',width:60,formatter:function(value,rowData,rowIndex){            	
            	switch(value){
            		case 0 : value = '<span>未通过</span>';
            			break;
            		case 1 : value = '<span>通过</span>';
            			break;
            		case 2 : value = '<span>未审核</span>';
            			break;
            	}
            	return value;
            }}, 
            {field:'currentStatus',title:'申请类型',width:100,align:'center',formatter:function(value,rowData,rowIndex){            	
            	switch(value){
            		case 1 : value = '新服务';
            			break;
            		case 2 : value = '申请上架';
            			break;
            		case 3 : value = '已上架';
            			break;
            		case 4 : value = '申请变更';
            			break; 
            		case 5 : value = '已删除';
            			break;
            		case 6 : value = '已下架';
            			break;
            		case 7 : value = '申请下架';
            			break;
            	}
            	return value;            	
            }},
            {field:'applyTime',title:'申请时间',align:'center',width:130,sortable:true,formatter:function(value,rowData,rowIndex){            	
            	switch(rowData.operatStatus){
            		case 0 : return '';
            			break;
            		case 1 : return '';
            			break;
            		case 2 : return rowData.operatorTime;
            			break;
            	}           	
            }},
            {field:'operatorTime',title:'处理时间',align:'center',width:130,sortable:true,formatter:function(value,rowData,rowIndex){
            	switch(rowData.operatStatus){
            		case 0 : return rowData.operatorTime;
            			break;
            		case 1 : return rowData.operatorTime;
            			break;
            		case 2 : return '';
            			break;
            	}            	         	
            }},
            {field:'context',title:'处理意见',align:'center',width:139}
        ]],
        onLoadSuccess:function(){
        }
    });
 	
 }); 
 
 
