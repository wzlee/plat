/**
 * 企业管理中心-买家管理中心js xuwf 2013-9-6
 */
$(function(){
	var tabpanel = $('#chatonline');
    
	//切换tab页签，执行不同的方法
    tabpanel.tabs({
    	tabWidth: 80,
	    border:false,
	    onSelect:function(title){
	    	switch(title){
	    		case '分组管理' :
	    			tabpanel.getChatGroup();
	    		break;
	    		case '客服管理' :
	    			tabpanel.getChatUsers();
	    		break;
	    		case '机器人管理' :
	    			tabpanel.getChatRobots();
	    		break;
	    		case '常用语管理' :
	    			tabpanel.getChatSentences();
	    		break;
	    		case '访客检索' :
	    			tabpanel.getChatVisitors();
	    		break;
	    		case '访客留言' :
	    			tabpanel.getChatNotes();
	    		break;
	    		case '客服评价' :
	    			tabpanel.getChatEvals();
	    		break;
	    		case '聊天记录' :
	    			tabpanel.getChatHistory();
	    		break;
	    	}
    	}
    });
    
    //获取某个企业聊天组
    tabpanel.getChatGroup = function(){
    	$('#chatgroup').datagrid({
	    	method:'post',
	        url:'chat/getChatGroup',
	        rownumbers: true,
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10000,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	eid: tabpanel.attr('chateid'),
	        	allGroup: false
	        },
	        columns:[[ 
				{field:'id', title:'编号', align:'center', width:50, hidden:true},
	        	{field:'name', title:'分组名称', align:'center', width:130},
	            {field:'rname', title:'分组机器人', sortable:true, width:100, align:'center'},
	            {field:'created_at', title:'创建时间', width:130, align:'center'},
	            {field:'pic', title:'头像', width:140, align:'center'},
	            {field:'desc', title:'分组备注', align:'center', width:245}
	        ]],
	        toolbar: [{
	        	text: '添加分组',
				iconCls: 'addgroup',
				handler: function(){
					$('#groupwin').window('open').window('setTitle','添加分组');
					$('#combox_robots').combobox({
						width: 200,
						method: 'get',
						url: 'chat/getChatRobots?eid='+tabpanel.attr('chateid'),
						valueField: 'id',
						textField: 'name'
					});
					$('#groupform').form('clear');
					$('#groupform').attr('type', 'add');
				}
			},'-',{
				id: 'updategroup',
				text: '修改分组',
				iconCls: 'updategroup',
				handler: function(){
					var row = $('#chatgroup').datagrid('getSelected');
					if (row){
						$('#groupwin').window('open').window('setTitle','修改分组');
						$('#groupform').form('clear');
						$('#combox_robots').combobox({
							width: 200,
							method: 'get',
							url: 'chat/getChatRobots?eid='+tabpanel.attr('chateid'),
							valueField: 'id',
							textField: 'name'
						});
						$('#groupform').form('load', row);
						$('#groupform').attr('type', 'update');
					} else {
						$.messager.alert('提示', '您还未选择要修改的记录，请选择！');
					}
				}
			},'-',{
				id: 'deletegroup',
				text: '删除分组',
				iconCls: 'deletegroup',
				handler: function(){
					var row = $('#chatgroup').datagrid('getSelected');
					if (row) {
						$.messager.confirm('提示', '您确定要删除该组信息吗？', function(r){
							if(r){
								$.ajax({
								   	type: 'post',
								   	url: 'chat/deleteGroup',
								   	data: {
								   		id: row.id
								   	},
								   	success: function(data){
								   		data = eval('('+ data +')');
						    			if(data.success){
									     	$('#chatgroup').datagrid('reload');
						    			} else {
						    				if(data.backupfield){
						    					$.messager.alert('错误', data.backupfield);
						    				} else {
						    					$.messager.alert('错误', data.message);
						    				}
						    			}
								   	}
								})
							}
						});
					} else {
						$.messager.alert('提示', '您还未选择要删除的信息，请选择！');
					}
				}
			}],
			onClickRow: function(rowIndex, rowData){
				if(rowData.name == '默认组'){
					$('#updategroup').linkbutton('disable');
					$('#deletegroup').linkbutton('disable');
				} else{
					$('#updategroup').linkbutton('enable');
					$('#deletegroup').linkbutton('enable');
				}
			}
	    });
    }
    
    //获取某个企业下客服
    tabpanel.getChatUsers = function(){
    	$('#chatusers').datagrid({
	    	method:'post',
	        url:'chat/getChatUsers',
	        rownumbers: true,
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10000,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	eid: tabpanel.attr('chateid'),
	        	role: 'admin'
	        },
	        columns:[[ 
				{field:'id',title:'编号',align:'center',width:50, hidden:true},
	        	{field:'login',title:'帐号',align:'center',width:120},
	            {field:'nick',title:'名称',align:'center',width:140},  
	            {field:'group',title:'所属群组',sortable:true,width:100,align:'center'},
	            {field:'email',title:'邮箱',width:170,align:'center'},
	            {field:'role',title:'角色',width:80,align:'center', 
	            	formatter: function(value,row,index){
	            		if(value == "admin"){
	            			return "管理员";
	            		} else {
	            			return "客服";
	            		}
	            	}
	            },
	            {field:'created_at',title:'创建时间',width:130,align:'center'}
	        ]],
	        toolbar: [{
	        	text: '添加客服',
				iconCls: 'addusers',
				handler: function(){
					$('#userwin').window('open').window('setTitle','添加客服');
					//获取分组下拉列表数据
					$('#group_id').combobox({
						method: 'get',
						url: 'chat/getChatGroup?eid='+tabpanel.attr('chateid') +'&allGroup=false',
						valueField: 'id',
						textField: 'name'
					});
					$('#userform').form('clear');
					$('#userform').attr('type', 'add');
				}
			},'-',{
				text: '修改客服',
				iconCls: 'updateusers',
				handler: function(){
					var row = $('#chatusers').datagrid('getSelected');
					if (row){
						$('#userwin').window('open').window('setTitle','修改客服');
						$('#userform').form('clear');
						//获取分组下拉列表数据
						$('#group_id').combobox({
							method: 'get',
							url: 'chat/getChatGroup?eid='+tabpanel.attr('chateid') +'&allGroup=false',
							valueField: 'id',
							textField: 'name'
						});
						$('#userform').form('load', row);
						$('#userform').attr('type', 'update');
					} else {
						$.messager.alert('提示', '您还未选择要修改的客服，请选择！');
					}
				}
			},'-',{
				text: '删除客服',
				iconCls: 'deleteusers',
				handler: function(){
					var row = $('#chatusers').datagrid('getSelected');
					if (row) {
						$.messager.confirm('提示', '您确定要删除该客服吗？', function(r){
							if(r){
								$.ajax({
								   	type: 'post',
								   	url: 'chat/deleteUsers',
								   	data: {
								   		id: row.id
								   	},
								   	success: function(data){
								   		data = eval('('+ data +')');
						    			if(data.success){
									     	$('#chatusers').datagrid('reload');
						    			} else {
						    				if(data.backupfield){
						    					$.messager.alert('错误', data.backupfield);
						    				} else {
						    					$.messager.alert('错误', data.message);
						    				}
						    			}
								   	}
								})
							}
						});
					} else {
						$.messager.alert('提示', '您还未选择要删除的客服，请选择！');
					}
				}
			}]
	    });
    }
    
    //获取机器人列表
    tabpanel.getChatRobots = function(){
    	$('#chatrobots').datagrid({
    		method:'post',
	        url:'chat/getChatRobots',
	        fitColumns : true,
	        rownumbers: true,
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10000,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	eid: tabpanel.attr('chateid')
	        },
	        toolbar: [{
	        	text: '添加机器人',
				iconCls: 'robotAdd',
				handler: function(){
					$('#robotwin').window('open').window('setTitle','添加机器人');
					$('#robotform').form('clear');
					$('#robotform').attr('type', 'add');
				}
			},'-',{
				id: 'updaterobot',
				text: '修改机器人',
				iconCls: 'robotUpdate',
				handler: function(){
					var row = $('#chatrobots').datagrid('getSelected');
					if (row){
						$('#robotwin').window('open').window('setTitle','修改机器人');
						$('#robotform').form('clear');
						$('#robotform').form('load', row);
						$('#robotform').attr('type', 'update');
					} else {
						$.messager.alert('提示', '您还未选择要修改的机器人，请选择！');
					}
				}
			},'-',{
				id: 'deleterobot',
				text: '删除机器人',
				iconCls: 'robotDelete',
				handler: function(){
					var row = $('#chatrobots').datagrid('getSelected');
					if (row) {
						$.messager.confirm('提示', '您确定要删除该客服吗？如果该机器人关联了客服组，那么该组将自动关联默认机器人！', function(r){
							if(r){
								$.ajax({
								   	type: 'post',
								   	url: 'chat/deleteRobot',
								   	data: {
								   		id: row.id,
								   		eid: tabpanel.attr('chateid')
								   	},
								   	success: function(data){
								   		data = eval('('+ data +')');
						    			if(data.success){
									     	$('#chatrobots').datagrid('reload');
						    			} else {
						    				if(data.backupfield){
						    					$.messager.alert('错误', data.backupfield);
						    				} else {
						    					$.messager.alert('错误', data.message);
						    				}
						    			}
								   	}
								})
							}
						});
					} else {
						$.messager.alert('提示', '您还未选择要删除的机器人，请选择！');
					}
				}
			},'-',{
				id: 'queryrobot',
				text: '查看机器人问题',
				iconCls: 'robotQusery',
				handler: function(){
					var row = $('#chatrobots').datagrid('getSelected');
					if (row){
						tabpanel.getChatRobotsQuestion(row);
					} else {
						$.messager.alert('提示', '您还未选择要查看的机器人，请选择！');
					}
				}
			}],
	        columns:[[ 
				{field:'id',title:'编号',align:'center',width:50, hidden: true},
	        	{field:'name',title:'机器人名称',align:'center',width:180},
	            {field:'remark',title:'机器人备注',align:'center',width:500}
	        ]],
			onClickRow: function(rowIndex, rowData){
				if(rowData.name == '默认机器人'){
					$('#updaterobot').linkbutton('disable');
					$('#deleterobot').linkbutton('disable');
				} else{
					$('#updaterobot').linkbutton('enable');
					$('#deleterobot').linkbutton('enable');
				}
			},
			onDblClickRow: function(rowIndex, rowData){
				tabpanel.getChatRobotsQuestion(rowData);
			}
    	});
    }
    
    //机器人问题列表
    tabpanel.getChatRobotsQuestion = function(rowData){
    	$('#robotsQuestionGrid').datagrid({
			url : 'chat/getChatRobotsQuestion',
			fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			loadMsg: '请稍等！系统正在加载机器人问题！',
			width: 734, 
			height: 462,
			pagination:true,
			pageSize:10000,
			pageList:[10,20,30,40,50],
			queryParams:{
	        	eid: tabpanel.attr('chateid'),
	        	rid: rowData.id
	        },
			columns : [[
				{title: '问题id', field: 'id', align: 'center', hidden:true},
				{title: '问题编号', field: 'qid', align: 'center', width: 50},
				{title: '问题名称', field: 'question', align: 'center', width: 100},
				{title: '问题答案', field: 'answer', align : 'center', width: 280},
				{title: '创建时间', field : 'created_at', width: 100, align: 'center'}
			]],
			toolbar: [{
				heigth: 100,
	        	text: '添加问题',
				iconCls: 'questionAdd',
				handler: function(){
					$('#questionWin').window('open').window('setTitle','添加问题');
					$('#questionform').form('clear');
					$('#q_robotid').val(rowData.id);
					$('#questionform').attr('type', 'add');
				}
			},'-',{
				id: 'updateQuestion',
				text: '修改问题',
				iconCls: 'questionUpdate',
				handler: function(){
					var row = $('#robotsQuestionGrid').datagrid('getSelected');
					if (row){
						$('#questionWin').window('open').window('setTitle','修改问题');
						$('#questionform').form('clear');
						$('#questionform').form('load', row);
						$('#questionID').disable();
						$('#questionform').attr('type', 'update');
					} else {
						$.messager.alert('提示', '您还未选择要修改的机器人，请选择！');
					}
				}
			},'-',{
				id: 'deleteQuestion',
				text: '删除问题',
				iconCls: 'questionDelete',
				handler: function(){
					var row = $('#robotsQuestionGrid').datagrid('getSelected');
					if (row) {
						$.messager.confirm('提示', '您确定要删除该问题吗？', function(r){
							if(r){
								$.ajax({
								   	type: 'post',
								   	url: 'chat/deleteRobotQuestion',
								   	data: {
								   		id: row.id,
								   		eid: tabpanel.attr('chateid')
								   	},
								   	success: function(data){
								   		data = eval('('+ data +')');
						    			if(data.success){
									     	$('#robotsQuestionGrid').datagrid('reload');
						    			} else {
						    				if(data.backupfield){
						    					$.messager.alert('错误', data.backupfield);
						    				} else {
						    					$.messager.alert('错误', data.message);
						    				}
						    			}
								   	}
								})
							}
						});
					} else {
						$.messager.alert('提示', '您还未选择要删除的机器人，请选择！');
					}
				}
			}]
		});
		$('#robotsQuestionWin').window('open').window('setTitle','机器人问题');
    }
    
     //获取某个企业下的常用语
   	tabpanel.getChatSentences =  function(){
    	$('#chatsentences').datagrid({
	    	method:'post',
	        url:'chat/getChatSentences',
	        rownumbers: true,
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10000,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	eid: tabpanel.attr('chateid')
	        },
	        toolbar: [{
	        	text: '添加常用语',
				iconCls: 'sentencesAdd',
				handler: function(){
					$('#sentencesWin').window('open').window('setTitle','添加常用语');
					//获取分组下拉列表数据
					$('#s_groupID').combobox({
						width: 200,
						method: 'get',
						url: 'chat/getChatGroup?eid='+tabpanel.attr('chateid') +'&allGroup=false',
						valueField: 'id',
						textField: 'name'
					});
					$('#sentencesform').form('clear');
					$('#sentencesform').attr('type', 'add');
				}
			},'-',{
				text: '修改常用语',
				iconCls: 'sentencesUpdate',
				handler: function(){
					var row = $('#chatsentences').datagrid('getSelected');
					if (row){
						$('#sentencesWin').window('open').window('setTitle','修改常用语');
						$('#sentencesform').form('clear');
						//获取分组下拉列表数据
						$('#s_groupID').combobox({
							width: 200,
							method: 'get',
							url: 'chat/getChatGroup?eid='+tabpanel.attr('chateid') +'&allGroup=false',
							valueField: 'id',
							textField: 'name'
						});
						$('#sentencesform').form('load', row);
						$('#sentencesform').attr('type', 'update');
					} else {
						$.messager.alert('提示', '您还未选择要修改的常用语句，请选择！');
					}
				}
			},'-',{
				text: '删除常用语',
				iconCls: 'sentencesDelete',
				handler: function(){
					var row = $('#chatsentences').datagrid('getSelected');
					if (row) {
						$.messager.confirm('提示', '您确定要删除该常用语吗？', function(r){
							if(r){
								$.ajax({
								   	type: 'post',
								   	url: 'chat/deleteSentences',
								   	data: {
								   		id: row.id
								   	},
								   	success: function(data){
								   		data = eval('('+ data +')');
						    			if(data.success){
									     	$('#chatsentences').datagrid('reload');
						    			} else {
						    				if(data.backupfield){
						    					$.messager.alert('错误', data.backupfield);
						    				} else {
						    					$.messager.alert('错误', data.message);
						    				}
						    			}
								   	}
								})
							}
						});
					} else {
						$.messager.alert('提示', '您还未选择要删除的常用语句，请选择！');
					}
				}
			}],
	        columns:[[ 
				{field:'id',title:'编号',align:'center',width:50, hidden:true},
	        	{field:'group',title:'所属群组',align:'center',width:120},
	        	{field:'user',title:'创建用户',align:'center',width:120},
	            {field:'content',title:'内容',sortable:true,width:500,align:'center'}
	        ]]
	    });
    }
    
     //获取某个企业下访客检索的内容
   	tabpanel.getChatVisitors =  function(){
    	$('#chatvisitors').datagrid({
	    	method:'post',
	        url:'chat/getChatVisitors',
	        rownumbers: true,
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10000,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	eid: tabpanel.attr('chateid')
	        },
	        toolbar: '#visitorstoolbar',
	        columns:[[ 
				{field:'id',title:'编号',align:'center',width:50, hidden:true},
	        	{field:'name',title:'唯一id',align:'center',width:100},
	        	{field:'nick',title:'昵称',align:'center',width:80},
	        	{field:'ipaddr',title:'ip地址',align:'center',width:100},
	        	{field:'url',title:'访问页面URL',align:'center',width:220},
	        	{field:'signat',title:'访问时间',align:'center',width:120},
	        	{field:'referer',title:'访问来源',align:'center',width:120},
	        	{field:'location',title:'登陆地址',align:'center',width:120}
	        ]]
	    });
    }
    
    //获取某个企业下访客留言的信息
   	tabpanel.getChatNotes =  function(){
   		var searchNotes = function(type, id){
   			var parms = {
   				chateid: tabpanel.attr('chateid')
   			};
   			if(type == 'status'){
   				parms.status = id;
   				parms.group_id = $('#notes_groupid').combobox('getValue');
   			} else {
   				parms.group_id = id;
   				parms.status = $('#notesstatus').combobox('getValue');
   			}
   			$.ajax({
			   	type: 'post',
			   	url: 'chat/searchNotes',
			   	dataType: 'json',
			   	data: parms,
			   	success: function(data){
			     	$('#chatnotes').datagrid('loadData', data);
			   	}
			});
   		}
   		
    	$('#chatnotes').datagrid({
	    	method:'post',
	        url:'chat/getChatNotes',
	        rownumbers: true,
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10000,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	eid: tabpanel.attr('chateid')
	        },
	        toolbar: '#notestoolbar',
	        columns:[[ 
				{field:'id',title:'编号',align:'center',width:50, hidden:true},
				{field:'group',title:'所属群组',align:'center',width:120},
	        	{field:'user',title:'所属客服',align:'center',width:120},
	        	{field:'name',title:'访客名称',align:'center',width:80},
	        	{field:'contact',title:'联系方式',align:'center',width:90},
	        	{field:'status',title:'状态',align:'center',width:100,
	        		formatter: function(value, row, index){
	        			switch(value){
	        				case '0' :
	        					return '<div class="dissatisfied" style="background-color: #999999;">未处理</div>';
	        				break;
	        				case '1' :
	        					return '<div class="dissatisfied" style="background-color: #999999;">已处理</div>';
	        				break;
	        				case '2' :
	        					return '<div class="dissatisfied" style="background-color: #999999;">无效</div>';
	        				break;
	        			}
	        		}
	        	},
	        	
	        	{field:'content',title:'内容',align:'center',width:220},
	        	{field:'created_at',title:'留言时间',align:'center',width:120},
	        	{field:'visitor',title:'所属访客',align:'center',width:120},
	        	{field:'remark',title:'备注',align:'center',width:120}
	        ]]
	    });
	    //创建工具栏状态combobox组件
	    $('#notesstatus').combobox({
	    	width: 120,
	    	height: 22,
	    	valueField: 'id',
			textField: 'text',
	    	data: [
	    		{id:'', text:'所有状态'},
	    		{id:'2', text:'无效'},
	    		{id:'0', text:'未处理'},
	    		{id:'1', text:'已处理'}
	    	],
			onSelect: function(rec){
				searchNotes('status', rec.id);
			}
	    });
	    //创建工具栏群组combobox组件
	    $('#notes_groupid').combobox({
	    	width: 120,
	    	height: 22,
	    	method: 'get',
			url:'chat/getChatGroup?eid='+tabpanel.attr('chateid') +'&allGroup=true',
			valueField:'id',
			textField:'name',
			onSelect: function(rec){
				searchNotes('group', rec.id);
			}
		});
		
    }
    
    //获取某个企业下客户的评价
   	tabpanel.getChatEvals =  function(){
   		var searchEvals = function(type, id){
   			var parms = {
   				chateid: tabpanel.attr('chateid')
   			};
   			if(type == 'grade'){
   				parms.grade = id;
   				parms.group_id = $('#evals_groupid').combobox('getValue');
   			} else {
   				parms.group_id = id;
   				parms.grade = $('#evals_grade').combobox('getValue');
   			}
   			$.ajax({
			   	type: 'post',
			   	url: 'chat/searchEvals',
			   	dataType: 'json',
			   	data: parms,
			   	success: function(data){
			     	$('#chatevals').datagrid('loadData', data);
			   	}
			});
   		}
   		
    	$('#chatevals').datagrid({
	    	method:'post',
	        url:'chat/getChatEvals',
	        rownumbers: true,
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10000,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	eid: tabpanel.attr('chateid')
	        },
	        toolbar: '#evalstoolbar',
	        columns:[[ 
				{field:'id',title:'编号',align:'center',width:50, hidden:true},
				{field:'group',title:'所属群组',align:'center',width:120},
	        	{field:'user',title:'所属客服',align:'center',width:110},
	        	{field:'visitor',title:'所属访客',align:'center',width:105},
	        	{field:'grade',title:'评分',align:'center',width:100,
	        		formatter: function(value, row, index){
	        			switch(value){
	        				case '1' :
	        					return '<div class="dissatisfied" style="background-color: #B94A48;">非常不满意</div>';
	        				break;
	        				case '2' :
	        					return '<div class="dissatisfied" style="background-color: #F89406">不满意</div>';
	        				break;
	        				case '3' :
	        					return '<div class="dissatisfied" style="background-color: #3A87AD;">基本满意</div>';
	        				break;
	        				case '4' :
	        					return '<div class="dissatisfied" style="background-color: #3A87AD;">满意</div>';
	        				break;
	        				case '5' :
	        					return '<div class="dissatisfied" style="background-color: #468847;">非常满意</div>';
	        				break;
	        			}
	        		}
	        	},
	        	{field:'suggestion',title:'建议',align:'center',width:180},
	        	{field:'created_at',title:'留言时间',align:'center',width:125}
	        ]]
	    });
	    //创建工具栏评分combobox组件
	    $('#evals_grade').combobox({
	    	width: 120,
	    	height: 22,
	    	valueField: 'id',
			textField: 'text',
	    	data: [
	    		{id:'', text:'所有评分'},
	    		{id:'5', text:'非常满意'},
	    		{id:'4', text:'满意'},
	    		{id:'3', text:'基本满意'},
	    		{id:'2', text:'不满意'},
	    		{id:'1', text:'非常不满意'}
	    	],
			onSelect: function(rec){
				searchEvals('grade', rec.id);
			}
	    });
	    //创建工具栏群组combobox组件
	    $('#evals_groupid').combobox({
	    	width: 120,
	    	height: 22,
	    	method: 'get',
			url:'chat/getChatGroup?eid='+tabpanel.attr('chateid') +'&allGroup=true',
			valueField:'id',
			textField:'name',
			onSelect: function(rec){
				searchEvals('group', rec.id);
			}
		});
		
    }
    
    //获取聊天记录
   	tabpanel.getChatHistory =  function(){
    	$('#chathistory').datagrid({
	    	method:'post',
	        url:'chat/getChatHistory',
	        rownumbers: true,
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10000,
			pageList:[10,20,30,40,50],
	        queryParams:{
	        	eid: tabpanel.attr('chateid'),
	        	username: tabpanel.attr('username')
	        },
	        toolbar: '#historytoolbar',
	        columns:[[ 
				{field:'id',title:'编号',align:'center',width:50, hidden:true},
	        	{field:'from',title:'发送者',align:'center',width:120},
	            {field:'to',title:'接收者',align:'center',width:120},  
	            {field:'body',title:'内容',sortable:true,width:370,align:'center'},
	            {field:'created_at',title:'发送时间',width:130,align:'center'}
	        ]]
	    });
    }
    
    //点击分组保存按钮执行的事件
    $('#savegroup').click(function(){
    	var url = 'chat/addGroup';
    	var type = $('#groupform').attr('type');
    	if(type == 'update'){
    		url = 'chat/updateGroup';
    	}
    	$('#groupform').form('submit', {
    		url: url,    
    		onSubmit: function(param){
    			param.eid = tabpanel.attr('chateid');
    		},
    		success:function(data){
    			data = eval('('+ data +')');
    			if(data.success){
    				$('#chatgroup').datagrid('load');
    				$('#groupwin').window('close');
    			} else {
    				$.messager.alert('错误', data.backupfield);
    			}
    		}
    	});
    });
    
    //点击保存用户的确定按钮事件
    $('#saveuser').click(function(){
    	var url = 'chat/addUsers';
    	var type = $('#userform').attr('type');
    	if(type == 'update'){
    		url = 'chat/updateUsers';
    	}
    	$('#userform').form('submit', {
    		url: url,    
    		onSubmit: function(param){
    			param.eid = tabpanel.attr('chateid');
    		},
    		success:function(data){
    			data = eval('('+ data +')');
    			if(data.success){
    				$('#chatusers').datagrid('load');
    				$('#userwin').window('close');
    			} else {
    				if(data.backupfield){
    					$.messager.alert('错误', data.backupfield);
    				} else {
    					$.messager.alert('错误', data.message);
    				}
    			}
    		}
    	});
    });
    
    //查询帐号的按钮单击事件
    $('.searchbox-button').click(function(){
    	var type = $('#userform').attr('type');
    	if(type == 'update') return;
    	$('#usergrid').datagrid({
	    	method:'post',
	        url:'chat/getEnterpriseUser',
	        width: 315,
	        height: 360,
	        rownumbers: true,
			singleSelect:true,
			autoRowHeight:false,
	        queryParams:{
	        	eid: tabpanel.attr('eid'),
	        	chateid: tabpanel.attr('chateid')
	        },
	        columns:[[
	        	{field:'id',title:'编号',align:'center', hidden:true},
	        	{field:'username',title:'用户名称',align:'center',width:160},
	            {field:'staffname',title:'昵称',align:'center',width:100},
	            {field:'email',title:'邮箱',align:'center', hidden:true}
	        ]],
	        onDblClickRow: function(rowIndex, rowData){
        		$('#userid').val(rowData.id);
	        	$('.searchbox-text').val(rowData.username);
	        	$('#nick').val(rowData.staffname);
	        	$('#email').val(rowData.email);
	        	$('#userlist').window('close');
	        }
	    });
	    
    	$('#userlist').window({
    		title: '用户列表',
    		width: 333,
    		height: 400,
    		modal: true,
    		collapsible: false,
    		minimizable: false,
    		maximizable: false
    	});
    }).mouseover(function(){
    	var type = $('#userform').attr('type');
    	if(type == 'update') return;
    	$(this).addClass("searchbox-button-hover");
    }).mouseout(function(){
    	var type = $('#userform').attr('type');
    	if(type == 'update') return;
    	$(this).removeClass("searchbox-button-hover");
    });
    
    //点击了添加组那里的分组图标查询按钮
    $('.searchimg-button').click(function(){
    	$('#groupimgwin').window('open').window('setTitle','添加分组图标');
		$('#groupimgform').form('clear');
    }).mouseover(function(){
    	$(this).addClass("searchimg-button-hover");
    }).mouseout(function(){
    	$(this).removeClass("searchimg-button-hover");
    });
    
    //上传分组图标保存事件
    $('#savegroupimg').click(function(){
    	$('#groupimgform').form('submit', {
    		url: 'public/chatUploadFile',  
    		onSubmit: function(param){},
    		success:function(data){
    			data = eval('('+ data +')');
    			if(data.success){
    				$('#pic').val(data.message);
    				$('#previewTD').show(); //显示分组图标预览的那行
    				$('#previewImg').attr({
    					src: 'upload/chatFile/'+ data.message,
    					width: 32,
    					height: 32
    				});
    				$('#groupimgwin').window('close');
    			} else {
    				$.messager.alert('错误','上传文件失败！');
    			}
    		}
    	});
    });
    
    //点击查询聊天记录搜索按钮
    $('#historysearch').click(function(){
    	$.ajax({
		   	type: 'post',
		   	url: 'chat/searchHistory',
		   	dataType: 'json',
		   	data: {
		   		chateid: tabpanel.attr('chateid'),
		   		username: tabpanel.attr('username'),
		   		content: $('#content').val(),
		   		sendtime: $('#sendtime').datebox('getValue')
		   	},
		   	success: function(data){
		     	$('#chathistory').datagrid('loadData', data);
		   	}
		})
    });
    
    //保存机器人添加、修改事件
    $('#saverobot').click(function(){
		var url = 'chat/addRobot';
    	var type = $('#robotform').attr('type');
    	if(type == 'update'){
    		url = 'chat/updateRobot';
    	}
    	$('#robotform').form('submit', {
    		url: url,    
    		onSubmit: function(param){
    			param.eid = tabpanel.attr('chateid');
    		},
    		success:function(data){
    			data = eval('('+ data +')');
    			if(data.success){
    				$('#chatrobots').datagrid('load');
    				$('#robotwin').window('close');
    			} else {
    				$.messager.alert('错误', data.backupfield);
    			}
    		}
    	});
	});
    
	//保存机器人问题添加、修改事件
    $('#savequestion').click(function(){
		var url = 'chat/addRobotQuestion';
    	var type = $('#questionform').attr('type');
    	if(type == 'update'){
    		url = 'chat/updateRobotQuestion';
    	}
    	$('#questionform').form('submit', {
    		url: url,    
    		onSubmit: function(param){
    			param.eid = tabpanel.attr('chateid');
    		},
    		success:function(data){
    			data = eval('('+ data +')');
    			if(data.success){
    				$('#robotsQuestionGrid').datagrid('load');
    				$('#questionWin').window('close');
    			} else {
    				$.messager.alert('错误', data.backupfield);
    			}
    		}
    	});
	});
	
	//保存常用语句的添加、修改事件
    $('#savesentences').click(function(){
		var url = 'chat/addSentences';
    	var type = $('#sentencesform').attr('type');
    	if(type == 'update'){
    		url = 'chat/updateSentences';
    	}
    	$('#sentencesform').form('submit', {
    		url: url,    
    		onSubmit: function(param){
    			param.eid = tabpanel.attr('chateid');
    			param.username = tabpanel.attr('username');
    		},
    		success:function(data){
    			data = eval('('+ data +')');
    			if(data.success){
    				$('#chatsentences').datagrid('load');
    				$('#sentencesWin').window('close');
    			} else {
    				$.messager.alert('错误', data.backupfield);
    			}
    		}
    	});
	});
    
	$('#visitsearch').click(function(){
		$.ajax({
		   	type: 'post',
		   	url: 'chat/searchVisitors',
		   	dataType: 'json',
		   	data: {
		   		chateid: tabpanel.attr('chateid'),
		   		visitnick: $('#visitnick').val(),
		   		visittime: $('#visittime').datebox('getValue')
		   	},
		   	success: function(data){
		     	$('#chatvisitors').datagrid('loadData', data);
		   	}
		})
	});
	
});


