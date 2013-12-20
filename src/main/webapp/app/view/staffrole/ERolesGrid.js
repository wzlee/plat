Ext.define('plat.view.staffrole.ERolesGrid',{
	extend:'Ext.grid.Panel',
	alias:'widget.erolesgrid',
	title:'角色显示',
	id:'erolesgrid',
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
    	storeId: 'erolesGridStore',
        fields: ['rolename','id','menu','createTime','roledesc','roleType',{name : 'checkeda', mapping : 'apply'}],
        autoLoad: false,
        pageSize : 30,
		proxy: {
	        type: 'ajax',
	        actionMethods: {  
        		read: 'POST'
        	},
	        url: 'smenu/queryrole?type=1',
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
	  	/*{header: '角色类型', align:'center', dataIndex: 'roleType',  flex: 1,renderer:function(text){
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
			            var updateroleWindows = Ext.ComponentQuery.query('eupdaterolewindow'),view=null;
		        			if(updateroleWindows.length==0){
		            			view = Ext.widget('eupdaterolewindow');
		        			}else{
		        				updateroleWindows[0].close();
		            			view = Ext.widget('eupdaterolewindow');
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
									    url: 'smenu/deleterole?type=1',
									    params:{'id':record.data.id},
									    success: function(response) {
									    	var result = Ext.decode(response.responseText);
									    	if(result.success){
									    		Ext.getCmp('erolesgrid').getStore().reload();
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
        store: Ext.data.StoreManager.lookup('erolesGridStore'),
        dock: 'bottom',
        displayInfo: true
	}]
});
