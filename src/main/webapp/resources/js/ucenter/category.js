$(function(){ 
	$("form").submit(function(e){
		e.preventDefault();
    });
	mysend();
	
	$('#addbutton').click(function(){
		var categorydialog = art.dialog.get('category-dialog');
		if(categorydialog == null){
			categorydialog = art.dialog({
				id:'category-dialog',
				lock:true,
				title:'添加类别',
				content:$('#messagecategore').html(),
				initialize:function(){
					$('#categoryid').val('');
					$('#cname').val('');
					$('#cdesc').val('');
					$('#cname1').html("");
				},
				button:[
			    	{
						value :'保存',
						callback : function(){
							$('#messagecategore').submit();
							return false;
						},
						focus : true
					},
					{
						value :'取消',
						callback:function(){
							$('#cname1').html("");
			      			$('#messagecategore')[0].reset();
						}
					}
				]
			});
		}else{
			$('#categoryid').val('');
			$('#cname').val('');
			$('#cdesc').val('');
			$('#cname1').html("");
			categorydialog.visible();
		}
	})
	
	$('#cancelsubmit').click(function(){
		$('#w').window('close');
		$('#messagecategore')[0].reset();
		$('#cname1').html("");
	});
	
	//表单提交
    $('#messagecategore').submit(function() {
	    $(this).ajaxSubmit({
	        beforeSubmit: function(){
	        	if ($('#cname').val().length == 0) {
		            $('#cname').focus();
		            $('#cname1').html("<span style='color:#f00'>消息类别不能为空！</span>");
		            return false;
		        }else {
		        	$('#submit').button('loading');
		        	return true
		        }
	        },
	        data:{
	        	'categoryid':$('#categoryid').val(),
				'cname':$('#cname').val(),
				'cdesc':$('#cdesc').val()
	        },
	        success: function(data) {
		        $('#submit').button('reset');
		        if (data.success) {
//		        	$('#w').window('close');
		        	$('#messagecategore')[0].reset();
		        	$('#cname1').html("");
		        	$('#my-send').datagrid("reload");
		        	art.dialog.get('category-dialog').close();
//		        	art.dialog.alert(data.message);
		        	art.dialog({
					    title: '提示',
					    content: data.message,
					    ok: function () {
					    },
					    okValue:'确定'
					});
		        } else {
		        	$('#cname1').html("<span style='color:#f00'>"+data.message+"</span>");
//		        	alert(data.message);
		        }
		    },
	        dataType: 'json'
	    });
//    	return false;
	});
});
function mysend(){
 	$('#my-send').datagrid({
    	method:'post',
        url:'ucenter/querycategory',
		autoRowHeight:false,
		singleSelect:true,
		pagination:true,
		remoteSort:false,
		pageSize:10,
		pageList:[10,20,30,40,50],
//	        queryParams:{
//	        	flag:0,
//	        	appealType:1,
//	        	userId:userId
//	        },
        columns:[[ 
        	{field:'rowNum',title:'序号',align:'center',width:40,
        		formatter:function(value,rowData,rowIndex){
	        		return rowIndex+1;
    			}
    		},
        	{field:'messageType',title:'类别名称',align:'center',width:210},
        	{field:'remark',title:'类别描述',align:'center',width:210},
            {field:'edit',title:'修改',width:145,align:'center',
            	formatter:function(value,rowData,rowIndex){
            		return "<a  href='javascript:void(0);' onclick='javascript:editc("+JSON.stringify(rowData)+")'>编辑</a>";
            	}
            },
            {field:'delete',title:'删除',width:145,align:'center',
            	formatter:function(value,rowData,rowIndex){
            		return "<a href='javascript:void(0);' id='deletebutton' onclick='javascript:deletec("+rowData.id+")' data-loading-text='删除中...'>删除</a>";
            	}
            }
        ]],
        onLoadSuccess:function(){
        }
    });  
}
function deletec(id){
	$('#deletebutton').button('loading');
	$.post(
		"info/deleteCategory", 
		{ "id": id },
		function(data){
			$('#deletebutton').button('reset');
			if(data.success){
				$('#my-send').datagrid("reload");
			}else{
//				art.dialog.alert(data.message);
	        	art.dialog({
				    title: '提示',
				    content: data.message,
				    ok: function () {
				    },
				    okValue:'确定'
				});
			}
		},
		"json"
	);
}
function editc(rowData){
	var categorydialog = art.dialog.get('category-dialog');
	if(categorydialog == null){
		categorydialog = art.dialog({
			id:'category-dialog',
			lock:true,
			title:'修改类别',
			content:$('#messagecategore').html(),
			initialize:function(){
				$('#categoryid').val(rowData.id);
				$('#cname').val(rowData.messageType);
				$('#cdesc').val(rowData.remark);
				$('#cname1').html("");
			},
			button:[
		    	{
					value :'保存',
					callback : function(){
						$('#messagecategore').submit();
						return flagshow;
					},
					focus : true
				},
				{
					value :'取消',
					callback:function(){
						$('#cname1').html("");
		      			$('#messagecategore')[0].reset();
					}
				}
			]
		});
	}else{
		$('#categoryid').val(rowData.id);
		$('#cname').val(rowData.messageType);
		$('#cdesc').val(rowData.remark);
		$('#cname1').html("");
		categorydialog.visible();
	}
}
