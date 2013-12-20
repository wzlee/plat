Ext.define('plat.view.manager.ManagerGrid',{
	extend:'Ext.grid.Panel',
	alias:'widget.managergrid',
	
	id:'managergrid',
	title:'系统用户管理',
	closable:true,
    closeAction:'hide',
    useArrows: true,
	columnLines:true,
	features: [{ftype:'grouping',
	    groupHeaderTpl: [
		    '角色名称: ',
		    '<span>{name:this.formatName}</span>',
		    {
		        formatName: function(name) {
		        	if(name!=null&&name!=''){
		            	name=name;
		        	}else{
		        		name='未分配角色的用户'; 
		        	}
		        	return name;
		        }
		    }
		]
	}],
    store:new Ext.data.Store({
		storeId : 'managerGridStore',
	    fields: [
	    	'username','password','userStatus','userType','flatId','remark','registerTime',
	    	{
	    	name : 'rolename',
	    	mapping : 'role.rolename'
	    	}
	    	
	    ],
	    pageSize : 30,
	    groupField:'rolename',
	    proxy: {
	        type: 'ajax',
	        url: 'menu/queryusers',
	        actionMethods: {  
        		read: 'POST'
        	},
	        reader: {
	            type: 'json',
	            root: 'data',
	            successProperty: 'success'
	        }
		}
    }),
	columns: [
		{ header: '用户名', align:'center', dataIndex: 'username' ,flex: 1},
  		{ header: '用户密码', align:'center', hidden:true, dataIndex: 'password'},
  		{ header: '备注', align:'center', dataIndex: 'remark',width:150 },
  		{ header: '注册时间', align:'center', dataIndex: 'registerTime',flex: 1},
  		{ text: '账号状态',align:'center',width:80, dataIndex: 'userStatus',
			renderer:function(value){
				switch(value){
					case 1:
						value = '<font color="green">有效</font>';
						break;
					case 2:
						value = '<font color="gray">禁用</font>';
						break;
					case 3:
						value = '<font color="red">删除</font>';
						break;
				}
				return value;
    		}
		},
		{
            xtype:'actioncolumn',
            text:'还原',
            align:'center',
            sortable:false,				            
            width:100,
            items: [
            	{
	                icon:'resources/images/arrow_redo.png',
	                tooltip: '还原用户',
	                handler: function(grid, rowIndex, colIndex, node, e, record, rowEl) {
						var record = grid.getStore().getAt(rowIndex);
	                    Ext.MessageBox.confirm('警告','确定还原【 '+record.data.username+' 】吗?',function(btn){
				    		if(btn == 'yes'){
				    			Ext.Ajax.request({
								    type:'POST',
								    url: 'manage/restore',
								    params:{'username':record.data.username},
								    success: function(response) {
								    	var result = Ext.decode(response.responseText);
									    	if(result.success){
									    		Ext.data.StoreManager.lookup('managerGridStore').load();
									    	}
								    },
								    failure: function(form, action) {
								    	Ext.example.msg('还原失败！',result.messgae);
								    }
								});
							}
						});		                    
							                    
	                },
	                isDisabled:function(grid, rowIndex, colIndex){
	                	return grid.getStore().getAt(rowIndex).data.userStatus != "02";
	                }
            	}
	        ]
        },
		{
		    xtype:'actioncolumn',
			text:'禁用',
			align:'center',
			sortable:false,				            
			width:100,
			items: [
				{
		        	icon:'resources/images/drop-no.gif',
					tooltip: '禁用用户',
					handler: function(grid, rowIndex, colIndex, node, e, record, rowEl) {
						var record = grid.getStore().getAt(rowIndex);
	                    Ext.MessageBox.confirm('警告','确定禁用【 '+record.data.username+' 】吗?',function(btn){
				    		if(btn == 'yes'){
				    			Ext.Ajax.request({
								    type:'POST',
								    url: 'manage/forbid',
								    params:{'username':record.data.username},
								    success: function(response) {
								    	var result = Ext.decode(response.responseText);
									    	if(result.success){
									    		Ext.data.StoreManager.lookup('managerGridStore').load();
									    	}
								    },
								    failure: function(form, action) {
								    	Ext.example.msg('禁用失败！',result.messgae);
								    }
								});
							}
						});		                    
					},
					isDisabled:function(grid, rowIndex, colIndex){
						return grid.getStore().getAt(rowIndex).data.userStatus != "01";
		            }
		    	}
		    ]
		},
		{
		    xtype:'actioncolumn',
			text:'删除',
			align:'center',
			sortable:false,				            
			width:100,
			items: [
				{
		        	iconCls:'icon-remove',
					tooltip: '删除用户',
					handler: function(grid, rowIndex, colIndex, node, e, record, rowEl) {
						var record = grid.getStore().getAt(rowIndex);
	                    Ext.MessageBox.confirm('警告','确定删除【 '+record.data.username+' 】吗?',function(btn){
				    		if(btn == 'yes'){
				    			Ext.Ajax.request({
								    type:'POST',
								    url: 'manage/delete',
								    params:{'username':record.data.username},
								    success: function(response) {
								    	var result = Ext.decode(response.responseText);
									    	if(result.success){
									    		Ext.data.StoreManager.lookup('managerGridStore').load();
									    	}
								    },
								    failure: function(form, action) {
								    	Ext.example.msg('删除失败！',result.messgae);
								    }
								});
							}
						});			                    
					},
					isDisabled:function(grid, rowIndex, colIndex){
						return grid.getStore().getAt(rowIndex).data.userStatus == "03";
		            }
		    	}
		    ]
		},{
            xtype:'actioncolumn',
            text:'重置密码',
            align:'center',
            sortable:false,				            
            width:100,
            items: [
            	{
	                icon:'resources/images/20130924152121.jpg',
	                tooltip: '重置密码',
	                handler: function(grid, rowIndex, colIndex, node, e, record, rowEl) {
						var record = grid.getStore().getAt(rowIndex);
	                    Ext.MessageBox.confirm('警告','确定将【 '+record.data.username+' 】重置密码吗?',function(btn){
				    		if(btn == 'yes'){
				    			Ext.Ajax.request({
								    type:'POST',
								    url: 'manage/repwd',
								    params:{'userId':record.data.id},
								    success: function(response) {
								    	var result = Ext.decode(response.responseText);
									    	if(result.success){
									    		Ext.data.StoreManager.lookup('managerGridStore').load();
									    	}
								    },
								    failure: function(form, action) {
								    	Ext.example.msg('还原失败！',result.messgae);
								    }
								});
							}
						});		                    
							                    
	                },
	                isDisabled:function(grid, rowIndex, colIndex){
	                	return grid.getStore().getAt(rowIndex).data.userStatus != "01";
	                }
            	}
	        ]
        }
	],
		  /*{
            xtype:'actioncolumn',
            text:'删除',
            align:'center',
            width:50,
            items: [
            	{
	                iconCls:'icon-remove',
	                tooltip: '删除',
	                handler: function(grid, rowIndex, colIndex) {
	                    var record = grid.getStore().getAt(rowIndex);
	                    Ext.MessageBox.confirm('警告','确定删除【 '+record.data.username+' 】吗?',function(btn){
				    		if(btn == 'yes'){
				    			Ext.Ajax.request({
								    type:'POST',
								    url: 'manage/delete',
								    params:{'username':record.data.username},
								    success: function(response) {
								    	var result = Ext.decode(response.responseText);
									    	if(result.success){
									    		Ext.data.StoreManager.lookup('managerGridStore').load();
									    	}
								    },
								    failure: function(form, action) {
								    	Ext.example.msg('删除失败！',result.messgae);
								    }
								});
							}
						});
	                }
	            }
            ]
		}*/
	
	tbar :['-',
		{xtype:'textfield',width:150,name:'username',emptyText:'输入搜索关键字...'},'-',
		{iconCls:'icon-search',text:'查找用户',action:'search'},'-',
		{iconCls:'icon-add',text:'添加用户',action:'add'},'-','->'
	],
	dockedItems :[{
        xtype: 'pagingtoolbar',
        store: Ext.data.StoreManager.lookup('managerGridStore'),
        dock: 'bottom',
        displayInfo: true
	}]
});