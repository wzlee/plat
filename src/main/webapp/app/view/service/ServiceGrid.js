Ext.define('plat.view.service.ServiceGrid',{
	extend:'Ext.grid.Panel',
	alias:'widget.servicegrid',
    
	title:'服务列表',
	closable:true,
    closeAction:'hide',
	columnLines:true,
    store:'service.ServiceStore',
    tbar :['-',
				{xtype:'textfield',width:150,name:'sname',emptyText:'输入搜索关键字...'},'-',
				{xtype:'categorycombo',width:150,name:'category',emptyText:'输入选择类别...'},'-',
				{iconCls:'icon-search',text:'查找',action:'search'},'-',
				{iconCls:'icon-add',text:'添加',action:'add'},'-','->'
//    					{xtype:'button',text:'显示分组',enableToggle:true,action:'group'},'-'
	],
    initComponent: function() {
    	var me = this;
        Ext.apply(this, {
			columns: [
						new Ext.grid.column.RowNumberer({text:'#',align:'center',width:30}),
				        { text: '服务ID',align:'center', dataIndex: 'id',hidden:true},
				        { text: '服务编码',align:'center',width:80, dataIndex: 'serviceNo'},
				        { text: '服务名称',align:'center',width:120, dataIndex: 'serviceName', flex: 1 },
				        { text: '服务类别',align:'center',width:100, dataIndex: 'serviceClass'},
				        { text: '服务状态',align:'center',width:80, dataIndex: 'serviceStatus'},
				        { text: '注册时间',align:'center', dataIndex: 'registerTime', flex: 1 },
//				        { text: '来源',align:'center', dataIndex: 'serviceSource', flex: 1 },
//				        { text: '联系人',align:'center',width:60, dataIndex: 'linkMan'},
//				        { text: '电话',align:'center',width:60, dataIndex: 'tel'},
//				        { text: '邮箱',align:'center',width:80, dataIndex: 'email'},
//				        { text: '服务对象',align:'center',width:100, dataIndex: 'serviceObject'},
				        { text: '服务方式',align:'center',width:100, dataIndex: 'serviceMethod'},
//				        { text: '服务描述',align:'center',width:150, dataIndex: 'serviceProcedure'},
				        { text: '收费方式',align:'center',width:80, dataIndex: 'chargeMethod'},
				        { text: '服务次数',align:'center',width:80, dataIndex: 'serviceNum'},
				        {
				            xtype:'actioncolumn',
				            text:'删除',
				            align:'center',
				            width:50,
				            items: [
				            	{
//					                icon: 'jsLib/extjs/resources/themes/icons/remove.png',
					                iconCls:'icon-remove',
					                tooltip: '删除',
					                handler: function(grid, rowIndex, colIndex) {
					                    var record = grid.getStore().getAt(rowIndex);
//					                    alert("Terminate " + rec.get('firstname'));
					                    Ext.MessageBox.confirm('警告','确定删除【 '+record.data.sname+' 】吗?',function(btn){
								    		if(btn == 'yes'){
								    			grid.getStore().remove(record);
								    		}else{
								    			
								    		}
								    	});
					                }
					            }
				            ]
				        }
		    		],
//		    plugins: [{
//		        ptype: 'rowexpander',
//		        rowBodyTpl : new Ext.XTemplate(
//		            '<p><b>来源:</b> {serviceSource}</p>',
//		            '<p><b>联系人:</b> {linkMan}</p><br>'
//	            )
//		    }],
//	    	features :[{ftype:'groupingsummary',id:'servicegroupingsummary',startCollapsed:true,groupHeaderTpl: '<font color="green">{name}</font> : 共[ <font color="green">{rows.length}</font> ]个服务'}],
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'service.ServiceStore',
					        dock: 'bottom',
					        displayInfo: true
					    }]
        });
        this.callParent();
    }
});