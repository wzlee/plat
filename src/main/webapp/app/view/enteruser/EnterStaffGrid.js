Ext.define('plat.view.enteruser.EnterStaffGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'enterstaffgrid',
    id:'enterstaffgrid',
    height: 250,
    width: 400,
    title: '',
//    closable:true,
	closeAction:'hide',
	columnLines:true,
	emptyText:'该用户还未添加子账号',
	store:'enteruser.EnterStaffStore',
    tbar :[
    		'-',
//			{xtype:'textfield',width:150,name:'username',emptyText:'输入用户名...'},
//			'-',
//			{iconCls:'icon-search',text:'查找',action:'search'},'-',	
			{iconCls:'icon-add',text:'添加子账号',action:'add',name:'add'},'-',
			'双击可以查看子账号详情！','->'	
	],
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            columns: [
            	{xtype:'rownumberer',text:'#',align:'center',width:30},
    			{ text: '子账号ID',align:'center', dataIndex: 'id',hidden:true},
				{ text: '会员名',align:'center',width:120, dataIndex: 'userName'},
				{ text: '密码',align:'center',width:80, dataIndex: 'password' ,hidden:true},
				{ text: '员工姓名',align:'center', dataIndex: 'staffName',hidden:true},
				{ text: '性别',align:'center',width:50, dataIndex: 'sex',
					renderer:function(v){
						return v==1?'女':'男';
					}
				
				},
				{ text: '办公电话',align:'center', dataIndex: 'tel',hidden:true},
				{ text: '手机',align:'center',width:80, dataIndex: 'mobile',hidden:true },
				{ text: '电子邮箱',align:'center', dataIndex: 'email',hidden:true},
				{ text: '地址',align:'center', dataIndex: 'address',hidden:true},
				{ text: '分配时间',align:'center',width:140, dataIndex: 'assignTime'},
				{ text: '备注',align:'center', dataIndex: 'remark',hidden:true},
				{ text: '主账号ID',align:'center', dataIndex: 'parent.id',hidden:true},
				{ text: '主账号',align:'center',width:80, dataIndex: 'parent.userName'},
				{ text: '账号状态',align:'center',width:80, dataIndex: 'staffStatus',
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
                		//return PlatMap.Enterprises.status[value];
	                }
	            },
                { text: '分配人ID',align:'center', dataIndex: 'manager.id',hidden:true},
				{ text: '分配人',align:'center',width:80, dataIndex: 'manager.username',
					renderer:function(value,metaData,record){
						if(record.get('assigner.userName')){
							return record.get('assigner.userName');
						}
						return value;
					}
				},
				{ text: '角色ID',align:'center', dataIndex: 'roleId',hidden:true},
				{ text: '拥有的角色',align:'center',width:120, dataIndex: 'staffRole.rolename'},
				{
					xtype:'actioncolumn',
					text:'重置密码',
					align:'center',
					sortable:false,
					width:80,
					items:[
						{
//							iconCls:'icon-edit',
							icon:'resources/images/20130924152121.jpg',
							tooltip:'重置密码',
							 handler: function(grid, rowIndex, colIndex, node, e, record, rowEl){
			                	this.fireEvent('resetpwd', this, grid, rowIndex, colIndex, node, e, record, rowEl);	
			                },	
							isDisabled:function(grid,rowIndex,colIndex){
								var userStatus = grid.getStore().getAt(rowIndex).get('parent.userStatus');
								if(userStatus == 2 || userStatus == 3){
									return true;
								}
								return grid.getStore().getAt(rowIndex).data.password == 'e10adc3949ba59abbe56e057f20f883e';
							}
						}
					]
				},
				{
					xtype:'actioncolumn',
					text:'编辑',
					align:'center',
					sortable:false,
					width:50,
					items: [
		            	{
			                icon:'resources/images/edit.png',
			                tooltip: '编辑子账号',			                
			                handler: function(grid, rowIndex, colIndex, node, e, record, rowEl){
			                	this.fireEvent('editclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);	
			                },
			                isDisabled:function(grid, rowIndex, colIndex){
			                	var userStatus = grid.getStore().getAt(rowIndex).get('parent.userStatus');
								if(userStatus == 2 || userStatus == 3){
									return true;
								}
			                	return grid.getStore().getAt(rowIndex).data.staffStatus != 1;
			                }
	    				}
			        ]
				},
				{
					xtype:'actioncolumn',
					text:'禁用',
					align:'center',
					sortable:false,
					width:50,
					items:[
						{
//							iconCls:'icon-backup',
							icon:'resources/images/drop-no.gif',
							tooltip:'禁用子账号',
							handler:function(grid, rowIndex, colIndex, node, e, record, rowEl) {
			                	this.fireEvent('disableclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);			                    
			                },
							isDisabled:function(grid,rowIndex,colIndex){
								var userStatus = grid.getStore().getAt(rowIndex).get('parent.userStatus');
								if(userStatus == 2 || userStatus == 3){
									return true;
								}
								return grid.getStore().getAt(rowIndex).data.staffStatus != 1;
							}
						}
					]
				},
				{
					xtype:'actioncolumn',
					text:'恢复',
					align:'center',
					sortable:false,
					width:50,
					items:[
						{
//							iconCls:'icon-backup',
//							icon:'resources/images/drop-yes.gif',
							icon:'resources/images/arrow_redo.png',
							tooltip:'恢复子账号',
							handler:function(grid, rowIndex, colIndex, node, e, record, rowEl) {
			                	this.fireEvent('recoverclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);			                    
			                },
							isDisabled:function(grid,rowIndex,colIndex){
								var userStatus = grid.getStore().getAt(rowIndex).get('parent.userStatus');
								if(userStatus == 2 || userStatus == 3){
									return true;
								}
								return grid.getStore().getAt(rowIndex).data.staffStatus != 2;
							}
						}
					]
				},
				{
					xtype:'actioncolumn',
					text:'删除',
					align:'center',
					sortable:false,
					width:50,
					items:[
						{
							iconCls:'icon-backup',
//							icon:'resources/images/delete.png',
							tooltip:'删除子账号',
							handler:function(grid, rowIndex, colIndex, node, e, record, rowEl) {
			                	this.fireEvent('deleteclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);			                    
			                },			
							isDisabled:function(grid,rowIndex,colIndex){
								var userStatus = grid.getStore().getAt(rowIndex).get('parent.userStatus');
								if(userStatus == 2 || userStatus == 3){
									return true;
								}
								return grid.getStore().getAt(rowIndex).data.staffStatus == 3;
							}
						}
					]
				}
            ]
//        dockedItems :[{
//					        xtype: 'pagingtoolbar',
//					        store: 'enteruser.EnterStaffStore',
//					        dock: 'bottom',
//					        displayInfo: true
//					    }]
       
            
        });

        me.callParent(arguments);
    }

});