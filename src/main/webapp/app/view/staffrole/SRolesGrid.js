Ext.define('plat.view.staffrole.SRolesGrid',{
	extend:'Ext.grid.Panel',
	alias:'widget.srolesgrid',
	title:'角色显示',
	id:'srolesgrid',
	columnLines:true,
    height: 400,
    width: 500,
    closeAction:'hide',
    useArrows: true,
    tbar :[ '-',
			{xtype:'textfield',width:150,name:'rolename',emptyText:'输入搜索关键字...'},'-',
			{iconCls:'icon-search',text:'查找角色',action:'search'},'-' ,  
			{iconCls:'icon-add',text:'添加角色',action:'add'},'->'
    ],
    store:new Ext.data.Store({
    	storeId: 'srolesGridStore',
        fields: ['rolename','id','menu','createTime','roledesc','roleType',{name : 'checkeda', mapping : 'apply'}],
        autoLoad: false,
        pageSize : 30,
		proxy: {
	        type: 'ajax',
	        actionMethods: {  
        		read: 'POST'
        	},
	        url: 'smenu/queryrole?type=2',
	        reader: {
	            type: 'json',
	            root: 'data',
	            successProperty: 'success'
	        }
	    }
    }),
    columns: [
	  	{header: '角色名', align:'center', dataIndex: 'rolename',  flex: 1},
	  	{header: '角色描述', align:'center', dataIndex: 'roledesc',  flex: 1},
	  	{header: '创建时间', align:'center', dataIndex: 'createTime',  flex: 1},
	  /*	{header: '角色类型', align:'center', dataIndex: 'roleType',  flex: 1,renderer:function(text){
	  		if(text=='1'){
	  			return '企业角色';
	  		}else{
	  			return '服务角色';
	  		}
	  	}},*/
	  	{
		    xtype:'actioncolumn',
			text:'修改',
			align:'center',
			width:50,
			items: [
				{
			        iconCls:'icon-edit',
			        tooltip: '编辑角色',
			        handler: function(grid, rowIndex, colIndex) {
			            var record = grid.getStore().getAt(rowIndex);
			            var updateroleWindows = Ext.ComponentQuery.query('supdaterolewindow'),view=null;
	        			if(updateroleWindows.length==0){
	            			view = Ext.widget('supdaterolewindow');
	        			}else{
	        				updateroleWindows[0].close();
	            			view = Ext.widget('supdaterolewindow');
	        			}
						view.down('form').loadRecord(record);
					}
				}
			]
		},
	  	{
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
			                Ext.MessageBox.confirm('警告','确定删除【 '+record.data.rolename+' 】吗?',function(btn){
					    		if(btn == 'yes'){
					    			Ext.Ajax.request({
									    type:'POST',
									    url: 'smenu/deleterole',
									    params:{'id':record.data.id},
									    success: function(response) {
									    	var result = Ext.decode(response.responseText);
									    	if(result.success){
									    		Ext.getCmp('srolesgrid').getStore().reload();
									    	}else{
									    		Ext.Msg.alert('不能删除',result.message);
									    	}
									    },
										failure: function(response) {}
									});
								}
							});
	        		}
				}
			]
		}
	],
	dockedItems :[{
        xtype: 'pagingtoolbar',
        store: Ext.data.StoreManager.lookup('srolesGridStore'),
        dock: 'bottom',
        displayInfo: true
	}]
});
