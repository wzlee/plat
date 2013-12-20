var selectelement = [];
$(function(){ 
	$("form").submit(function(e){
		e.preventDefault();
    });
    
    //表单提交
    $('#messagecategore').submit(function() {
	    $(this).ajaxSubmit({
	        beforeSubmit: function(){
	        	if ($('#cname').val().length == 0) {
		            $('#cc1').html("");
		            $('#content1').html("");
		            $('#cname').focus();
		            $('#cname1').html("<span style='color:#f00'>收信人不能为空！</span>");
		            return false;
		        }else if($('#cc').datebox('getValue').length == 0){
		            $('#cname1').html("");
		            $('#content1').html("");
		            $('.combo-text ').focus();
		            $('#cc1').html("<span style='color:#f00'>消息类别不能为空！</span>");
		            return false;
		        }else if($('#content').val().length == 0){
		        	$('#cname1').html("");
		            $('#cc1').html("");
		        	$('#content').focus();
		            $('#content1').html("<span style='color:#f00'>消息内容不能为空！</span>");
		            return false;
		        }else if($.inArray($('#cc').datebox('getValue'), selectelement) == -1){
		        	$('#cname1').html("");
		            $('#content1').html("");
		        	$('.combo-text ').focus();
		            $('#cc1').html("<span style='color:#f00'>请填写正确的消息类别！</span>");
		        	return false;
		        }else{
		        	$('#cname1').html("");
		            $('#cc1').html("");
		            $('#content1').html("");
		        	$('#submit').button('loading');
		        	return true;
		        }
	        },
	        data:{
	        	'rname':$('#cname').val(),
	        	'messagetype':$('#cc').datebox('getValue'),
	        	'content':$('#content').val()
	        },
	        success: function(data) {
		        $('#submit').button('reset');
		        if (data.success) {
		        	$('#w').window('close');
		        	$('#messagecategore')[0].reset();
		        	$('#cname1').html("");
		        	$('#my-send').datagrid("reload");
		        	art.dialog.get('info-dialog').close();
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
    
	//窗口里面的取消按钮时
	$('#cancelsubmit').click(function(){
		$('#w').window('close');
		$('#messagecategore')[0].reset();
		$('#cname1').html("");
	});
	

	//点击发送消息按钮
	$('.sendmessage').click(function(){
		$('.combo-text ').height('2000');
		var infodialog = art.dialog.get('info-dialog');
		if(infodialog == null){
			infodialog = art.dialog({
				id:'info-dialog',
				width:550,
				lock:true,
				title:'发送消息',
				content:$('#messagecategore').html(),
			 	initialize: function () {
			 		$('#cname1').html("");
		            $('#cc1').html("");
		            $('#content1').html("");
		            $('#messagecategore')[0].reset();
		 			$('#cc').combobox({   
						url:'info/queryCategory',   
						valueField:'messageType',   
						textField:'messageType',
						onLoadSuccess: function(){
							$.each( $('#cc').data().combobox.data, function(i, n){
								selectelement.push(n.messageType);
							});
						}
					}); 
			 	},
				button:[
			        	{
							value :'确认发送',
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
		            			$('#cc1').html("");
		            			$('#content1').html("");
		            			$('#messagecategore')[0].reset();
							}
						}
		        	]
			});
		}else{
			$('#cname1').html("");
			$('#cc1').html("");
			$('#content1').html("");
			$('#messagecategore')[0].reset();
			infodialog.visible();
		}
			
/*		$('#w').window('open');
		$('#cname1').html("");
        $('#cc1').html("");
        $('#content1').html("");*/
	});
	
	//点击删除按钮
	$('#deletes').click(function(){
		me = this;
		var ids = [];
		var obj = $('#my-send').datagrid('getChecked');
		$.each( obj, function(i, n){
			ids.push(n[0].id);
		});
		
		if(ids.length == 0){
//			art.dialog.alert('你还没有选中消息，不能执行删除操作！');
			art.dialog({
			    title: '提示',
			    content: '你还没有选中消息，不能执行删除操作！',
			    ok: function () {
			    },
			    okValue:'确定'
			});
		}else{
			$(me).button('loading');
			$.post(
				"ucenter/deletes", 
				{ "ids": ids.join(',') },
				function(data){
					$(me).button('reset');
					if(data.success){
//						art.dialog.alert('成功删除'+data.message+'条消息！');
						art.dialog({
						    title: '提示',
						    content: '成功删除'+data.message+'条消息！',
						    ok: function () {
						    },
						    okValue:'确定'
						});
						$('#my-send').datagrid("reload");
					}else{
//						art.dialog.alert(data.message);
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
	});
	//点击删除按钮
	$('#deleter').click(function(){
		me = this
		var ids = [];
		var obj = $('#my-receiver').datagrid('getChecked');
		$.each( obj, function(i, n){
			ids.push(n[0].id);
		});
		
		if(ids.length == 0){
//			art.dialog.alert('你还没有选中消息，不能执行删除操作！')
			art.dialog({
			    title: '提示',
			    content: '你还没有选中消息，不能执行删除操作！',
			    ok: function () {
			    },
			    okValue:'确定'
			});
		}else{
			$(me).button('loading');
			$.post(
				"ucenter/deleter", 
				{ "ids": ids.join(',') },
				function(data){
					$(me).button('reset');
					if(data.success){
//						art.dialog.alert('成功删除'+data.message+'条消息！');
						art.dialog({
						    title: '提示',
						    content: '成功删除'+data.message+'条消息！',
						    ok: function () {
						    },
						    okValue:'确定'
						});
						$('#my-receiver').datagrid("reload");
					}else{
//						art.dialog.alert(data.message);
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
	});
	
//	$('#easyui-linkbutton1').click(function(){
//		mysend();
//	});
//	$('#easyui-linkbutton2').click(function(){
//		receiver();
//	});
//	$('#easyui-linkbutton3').click(function(){
//		deleter();
//	});
	
	$('.easyui-tabs').tabs({
	    border:false,
	    onSelect:function(title){
	   		switch(title){
	   			case '已发送': mysend();
	   				break;
	   			case '已接收' : receiver();
	   				break;
	   			case '已删除' : deleter();
	   				break;
	   		}
    	}
    });
 });
	/*********************已发送Tab*************************/
    function mysend(){
	 	$('#my-send').datagrid({
	    	method:'post',
	        url:'ucenter/send',
			autoRowHeight:false,
			pagination:true,
			remoteSort:false,
			pageSize:10,
			pageList:[10,20,30,40,50],
/*	        queryParams:{
	        	flag:0,
	        	appealType:1,
	        	orderNo:$("#myorderNo").val(),
	        	startTime:$("#mystartTime").datebox('getValue'),
	        	endTime:$("#myendTime").datebox('getValue'),
	        	userId:userId
	        },*/
	        columns:[[ 
	        	{field:'ck',checkbox:true},
	        	{field:'rowNum',title:'序号',align:'center',width:40,
	        		formatter:function(value,rowData,rowIndex){
		        		return rowIndex+1;
        			}
        		},
	        	{field:'id',title:'消息id',align:'center',width:100,
	        		formatter:function(value,rowData,rowIndex){
	            		return rowData[0].id;
	            	}
	        	},
	            {field:'messageClass',title:'消息类别',align:'center',width:110,
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData[0].messageClass.messageType;
	            	}
	            },  
	            {field:'content',title:'消息内容',width:200,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		return "<a href='javascript:void(0);' onclick='javascript: showinfo( this,"+JSON.stringify(rowData[0])+ ",null)'>" +(rowData[0].content.length>30 ? rowData[0].content.substr(0,10)+'...' : rowData[0].content)+"</a>";
	            	}
	            },
	            {field:'收件人',title:'收信人',width:100,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData[1];
	            	}
	            },
	            {field:'sendTime',title:'时间',width:165,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData[0].sendTime;
	            	}
	            }
	        ]],
	        onLoadSuccess:function(){
	        }
	    });  
    }
   /*********************已接收Tab*************************/
	function receiver(){
//		console.log($('#rMessageType').val());
		$('#my-receiver').datagrid({
			method:'post',
	        url:'ucenter/receiver',
			autoRowHeight:false,
			pagination:true,
			pageSize:10,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	messageType:$('#rMessageType').val()
	        },
			columns:[[
				{field:'ck',checkbox:true},
	        	{field:'rowNum',title:'序号',align:'center',width:40,
	        		formatter:function(value,rowData,rowIndex){
		        		return rowIndex+1;
        			}
        		},
	        	{field:'id',title:'消息id',align:'center',width:100,
	        		formatter:function(value,rowData,rowIndex){
	            		return rowData[0].id;
	            	}
	        	},
	            {field:'messageClass',title:'消息类别',align:'center',width:110,
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData[0].messageClass.messageType;
	            	}
	            },  
	            {field:'content',title:'消息内容',width:200,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		if(rowData[1]==0){
		            		return "<a href='javascript:void(0);' onclick='javascript: showinfo(this, "+JSON.stringify(rowData[0])+ ",0)'><strong>" +(rowData[0].content.length>30 ? rowData[0].content.substr(0,10)+'...' : rowData[0].content)+"</strong></a>";
	            		}else{
		            		return "<a href='javascript:void(0);' onclick='javascript: showinfo(this, "+JSON.stringify(rowData[0])+ ",1)'>" +(rowData[0].content.length>30 ? rowData[0].content.substr(0,10)+'...' : rowData[0].content)+"</a>";
	            		}
	            	}
	            },
	            {field:'发件人',title:'发件人',width:100,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData[2];
	            	}
	            },
	            {field:'sendTime',title:'时间',width:165,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData[0].sendTime;
	            	}
	            }
	        ]],
			onLoadSuccess:function(){
				if($('#my-receiver').datagrid('getData').total == 0){
					$('.datagrid-body').html('<div style="color:#666;margin-left: 350px; margin-top: 50px; ">暂时没有您的消息!</div>');
				}
			}
    		});  
	}
   /*********************已删除Tab*************************/
	function deleter(){
		$('#my-delete').datagrid({
			method:'post',
	        url:'ucenter/delete',
			autoRowHeight:false,
			pagination:true,
			pageSize:10,
			pageList:[10,20,30,40,50],
			columns:[[
//				{field:'ck',checkbox:true},
	        	{field:'rowNum',title:'序号',align:'center',width:40,
	        		formatter:function(value,rowData,rowIndex){
		        		return rowIndex+1;
        			}
        		},
	        	{field:'id',title:'消息id',align:'center',width:100,
	        		formatter:function(value,rowData,rowIndex){
	        			return rowData instanceof Array ? rowData[0].id : rowData.id;
	            	}
	        	},
	            {field:'messageClass',title:'消息类别',align:'center',width:110,
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData instanceof Array ? rowData[0].messageClass.messageType :rowData.messageClass.messageType;
	            	}
	            },  
	            {field:'content',title:'消息内容',width:300,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData instanceof Array ? "<a href='javascript:void(0);' onclick='javascript: showinfo(this, "+JSON.stringify(rowData[0])+ ",null)'>" +(rowData[0].content.length>30 ? rowData[0].content.substr(0,10)+'...' : rowData[0].content)+"</a>" : "<a href='javascript:void(0);' onclick='javascript: showinfo(this, "+JSON.stringify(rowData)+ ",null)'>" +(rowData.content.length>30 ? rowData.content.substr(0,10)+'...' : rowData.content)+"</a>";
	            	}
	            },
	            {field:'sendTime',title:'时间',width:200,align:'center',
	            	formatter:function(value,rowData,rowIndex){
	            		return rowData instanceof Array ? rowData[0].sendTime :rowData.sendTime;
	            	}
	            }
	        ]],
			onLoadSuccess:function(){
			}
		});  
    };
    
    function showinfo(good,rowdata,index){
/*    	$('#r').html('');
    	$('#r').html(rowdata.content);
    	$('#r').window('open');*/
    	var infodetail = art.dialog.get('infodetail-dialog');
    	if(infodetail == null){
    		infodetail = art.dialog({
				id:'infodetail-dialog',
				lock:true,
				title:'消息详情',
				follow:good,
				content:rowdata.content,
				ok:function(){
					this.content('');
				},
			 	okValue:'确定'
    		});
    	}

    	if(index==0){
    		$.post(
				"ucenter/updatereadsign", 
				{ "id": rowdata.id },
				function(data){},
				"json"
			);
			$('#my-receiver').datagrid("reload");
    	}
    }
