var pid = $("#pid").val();   //该用户的ID
var eType = $("#eType").val();   //企业类型
$(function(){
	$('#staffStatus').combobox({
   		valueField:'id',  
        textField:'text',
        editable:false,
        panelHeight:56
    });
    $('#staffRoleId').combobox({
    	url:'smenu/queryStaffRole?type=' + eType,    	
   		valueField:'id',  
        textField:'rolename',
        editable:false,        
        value:0
    });
	loadstafflist();	  
	$("#search").click(function(){		
		loadstafflist();
	});
});
function loadstafflist(){	
	$('#stafflist').datagrid({
    	method:'post',
        url:'staff/queryUserStaff',        
		singleSelect:true,
		autoRowHeight:false,
		pagination:true,
		pageSize:20,
		remoteSort:false,
		pageList:[10,20,30,40,50],
        queryParams:{
        	pid : pid,
        	staffStatus:$('#staffStatus').combobox("getValue"),
        	staffRoleId:$('#staffRoleId').combobox("getValue")        	
        },
        columns:[[
        	{field:'userName',title:'用户名',align:'center',width:150},        	
            {field:'staffName',title:'姓名',align:'center',width:80,formatter:function(value,rowData,rowIndex){            	
            	return value;
            }},   
            {field:'sex',title:'性别',align:'center',width:50,formatter:function(value,rowData,rowIndex){            	
            	return value == 0 ? value="男":value="女";
            }}, 
            {field:'assignTime',title:'添加时间',width:80,align:'center',formatter:function(value,rowData,rowIndex){
            	return value.substr(0, 10);
            }},
            {field:'mobile',title:'联系电话',align:'center',width:100,sortable:true,formatter : function(value, rowData, rowIndex) {
				
				return value;
			}},
            {field:'staffStatus',title:'状态',align:'center',width:50,sortable:true,formatter : function(value, rowData, rowIndex) {
				switch(value){
					case 1 : return '<span>正常</span>';
						break;
					case 2 : return '<span>禁用</span>';
						break;
					case 3 : return '<span>删除</span>';
						break;
				}				
            }},
            {field:'rolename',title:'所属角色',align:'center',width:180,sortable:true,formatter : function(value, rowData, rowIndex) {
				if(rowData.staffRole){
					return rowData.staffRole.rolename;
				}else {
					return '';
				}	
            }},            
            {field:'controller',title:'操作',align:'center',width:100,sortable:true,formatter:function(value,rowData,rowIndex){
            	
            	if(rowData.staffStatus==1){
            		value = "<a style='color:#0000FF;' href='javascript:void(0);' onclick='toEdit("+rowData.id+")'>编辑</a>";
            	}else {
//            		value = "<a style='color:#797979;cursor:default;' href='javascript:void(0);'>编辑</a>";
            		value = "编辑";
            	}            	
            	if(rowData.staffStatus==3){            		
            		value += "<label style='margin-right: 5px; margin-left: 5px;' >删除</label>";
            		if(rowData.staffStatus==2){            		
            			value += "恢复";
	            	}else {            		
	            		value += "禁用";
	            	}  
            	}else {            		
            		value += "<a style='color:#0000FF;margin-right: 5px; margin-left: 5px;' href='javascript:void(0);' onclick='changeStaff("+ rowIndex + "," + rowData.id +","+ 3 +");'>删除</a>";
            		if(rowData.staffStatus==2){            		
            			value += "<a style='color:#0000FF;' href='javascript:void(0);' onclick='changeStaff("+ rowIndex + "," + rowData.id +","+ 1 +");'>恢复</a>";
            		}else {            		
            			value += "<a style='color:#0000FF;' href='javascript:void(0);' onclick='changeStaff("+ rowIndex + "," + rowData.id +","+ 2 +");'>禁用</a>";
            		} 
            	}         	
            	return value;
            }}
        ]],
        onLoadSuccess:function(){        	
        	if($('#stafflist').datagrid("getData").total == 0){
        		if($('#staffStatus').combo("getValue") == ""){
        			if($('#staffRoleId').combo("getValue") == 0){
        				$('.datagrid-body').html('<div style="margin-left: 350px; margin-top: 50px;">您尚未添加子账号！</div>');
        			}else {
        				$('.datagrid-body').html('<div style="margin-left: 350px; margin-top: 50px;">该条件下没有子账号！</div>');
        			}
        		}else{
        			$('.datagrid-body').html('<div style="margin-left: 350px; margin-top: 50px;">该条件下没有子账号！</div>');
        		}	
        	}
        }
    });
};

function changeStaff(rowIndex,id,staffStatus){
	var tips;
	switch(staffStatus){
		case 3 : tips = '删除';
			break;
		case 2 : tips = '禁用';
			break;
		case 1 : tips = '恢复';
			break;
	}
	art.dialog({
		title: '提示',
		content: '确认'+tips+'吗?',
		ok: function () {
			$.ajax({
				type : 'POST',
				url : 'staff/changeUserStaff',
				data: {
					id:id,
					staffStatus:staffStatus
				},
				dataType : 'json',
				beforeSend:function(){					
				},
				success : function(data) {
					if (data.success) {
						$('#stafflist').datagrid('updateRow',{
							index: rowIndex,
							row: {
								staffStatus:staffStatus						
							}
						});
						art.dialog({
							title: '提示',
							content: '子账号已成功'+tips,
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
function toEdit(id){
	var type = $('#eType').val();
	window.location.href='ucenter/staff_edit?op=88&type='+type+'&id='+id;
}