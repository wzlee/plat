Ext.define('plat.view.enteruser.DisableUserGrid',{
	extend:'Ext.grid.Panel',
	xtype:'disableusergrid',
    
	title:'禁用列表管理',
	id:'jylbgl',
	closable:true,
	closeAction:'hide',
	columnLines:true,
	emptyText:'没有找到相关帐号,换个关键词再试试吧',
    store:'enteruser.DisableUserStore',
    tbar :['-',
			{xtype: 'triggerfield',name:'uName',width:200,triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
				display:false,	//自定义变量判断triggercls是否display
				emptyText: '请输入会员名...',    			
    			onTriggerClick:function(){
    				this.reset();
    				this.display = false;
	    			this.triggerCell.setDisplayed(false);
	    			this.setWidth(200);
    			}
    		},
    		{xtype: 'triggerfield',name:'enterpriseName',width:200,triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
    				display:false,	//自定义变量判断triggercls是否display
    				emptyText: '请输入企业全称...',    			
    				onTriggerClick:function(){
    					this.reset();
    					this.display = false;
	    				this.triggerCell.setDisplayed(false);
	    				this.setWidth(200);
    				}
    		},
    		{
    				xtype: 'combo',
    				name:'enterpriseType',
    				width:200,
    				emptyText: '请选择用户类型...',
    				queryMode: 'local',
				    displayField: 'text',
				    valueField: 'value',
				    editable : false,
				    store:  Ext.create('Ext.data.Store', {
				    fields: ['value', 'text'],
				    	data : [
					        {"value" : "", "text" : "--------全部--------"},
					        {"value" : 1, "text" : "普通企业"},
					        {"value" : 2, "text" : "认证企业"},
					        {"value" : 3, "text" : "服务机构"},
					        {"value" : 4, "text" : "政府机构"},
					        {"value" : 5, "text" : '个人'}
				 	]})
    		}, 
    		{
				xtype : 'combo',
		        name: 'industryType',
		        editable : false,
		        width:200,
		        labelWidth : 60,
		        queryMode : 'local',
			    displayField: 'text',
			    valueField: 'value',
		        emptyText : '请选择所属窗口...',
		//        store : 'flat.FlatStore',
		        store:  Ext.create('Ext.data.Store', { 
					    fields: ['value', 'text'],
					    data : [{value : 0, text : '--------全部--------'}, 
					    		{value : 16,text : '枢纽平台'},
					    		{value : 1, text : '电子装备'},
					    		{value : 2, text : '服装'},
					    		{value : 3, text : '港澳资源'},
					    		{value : 4, text : '工业设计'},
					    		{value : 5, text : '机械'},
					    		{value : 6, text : '家具'},
					    		{value : 7, text : 'LED'},
					    		{value : 8, text : '软件'},
					    		{value : 9, text : '物流'},
					    		{value : 10, text : '物联网'},
					    		{value : 11, text : '新材料'},
					    		{value : 12, text : '医疗器械'},
					    		{value : 13, text : '钟表'},
					    		{value : 14, text : '珠宝'},
					    		{value : 15, text : '其他'}
					    ]
	        })},
			{iconCls:'icon-search',text:'查找',action:'search'}
	],
    initComponent: function() {
    	var me = this;
    	var editWindow;
        Ext.apply(this, {
			columns: [
						new Ext.grid.column.RowNumberer({text:'#',align:'center',width:30}),
                //{ text: '用户ID',align:'center',width:80, dataIndex: 'id'},
                { text: '会员名',align:'center',width:80, dataIndex: 'userName',
                	renderer:function(value,metaData,record){
                		if(record.data.isPersonal) return '<a href="javascript:void(0);">'+value+'</a>';
                		return value;
                	}
                },
                { text: '企业简称',align:'center',width:80, dataIndex: 'enterprise.forShort',
                	renderer:function(value,metaData,record){
                		if(record.data.isPersonal) return '暂无';
                		return value;
                	}
                },
                { text: '企业全称',align:'center',width:80, dataIndex: 'enterprise.name',flex:1,renderer:function(value,metaData,record){                	
                	if(record.data.isPersonal) return '暂无';
                	return '<a href="javascript:void(0);">' + value + '</a>';                	
                }},
                { text: '用户类型',align:'center',width:80, dataIndex: 'enterprise.type',renderer:function(value,metaData,record){
                	if(record.data.isPersonal) return '个人';
                	return PlatMap.Enterprises.type[value];
                }},  
                { text: '所属窗口',align:'center',width:80, dataIndex: 'enterprise.industryType',
                	renderer:function(value){                		
                		return PlatMap.Enterprises.industryType[value];
                	}
                },
                { text: '个人用户',align:'center',width:80, dataIndex: 'isPersonal',hidden:true},
                { text: '电子邮箱',align:'center',width:100, dataIndex: 'enterprise.email',flex:1,
	                renderer:function(value,metaData ,record){     	
		               	if(record.data.isPersonal){ 
		               		if(record.data.email){
		                		return record.data.isApproved?record.data.email+'<img src="resources/images/drop-yes.gif" title="邮箱已激活">':record.data.email+'<img title="邮箱未激活" src="resources/images/exclamation.png">';
		                	}
		               	}
	                	if(value){
	                		return record.data.isApproved?value+'<img src="resources/images/drop-yes.gif" title="邮箱已激活">':value+'<img title="邮箱未激活" src="resources/images/exclamation.png">';
	                	}
	                }
                },
                { text: '注册时间',align:'center',width:80, dataIndex: 'regTime'},
                //{ text: '关注领域',align:'center',width:80, dataIndex: 'attentionField',flex:1},
                { text: '数据来源',align:'center',width:80, dataIndex: 'sourceId',
                	renderer:function(value){                		
                		return PlatMap.User.sourceId[value];
                	}
                },
//            	{ text: '个人用户',align:'center',width:80, dataIndex: 'isPersonal',
//            		renderer:function(value,metaData ,record){     	
//	                	return record.data.isPersonal?'是':'否';
//	                }
//            	},                
                { text: '帐号状态',align:'center',width:80, dataIndex: 'userStatus',
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
                		//return '<font color="green">'+PlatMap.Enterprises.userStatus[value]+'</font>';
	                }
	            },
//                {
//		            xtype:'actioncolumn',
//		            text:'编辑',		            
//		            align:'center',
//		            sortable:false,				            
//		            width:50,
//		            items: [
//		            	{
//			                icon:'resources/images/edit.png',
//			                tooltip: '编辑用户',			                
//			                handler: function(grid, rowIndex, colIndex, node, e, record, rowEl){
//			                	this.fireEvent('editclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);	
//			                },
//			                isDisabled:function(grid, rowIndex, colIndex){
//			                	return grid.getStore().getAt(rowIndex).data.userStatus != "01";
//			                }
//	    				}
//			        ]
//		        },
		        {
		            xtype:'actioncolumn',
		            text:'还原',
		            align:'center',
		            sortable:false,				            
		            width:50,
		            items: [
		            	{
			                icon:'resources/images/arrow_redo.png',
			                tooltip: '还原用户',
			                handler: function(grid, rowIndex, colIndex, node, e, record, rowEl) {
			                	this.fireEvent('restoreclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);			                    
			                },
			                isDisabled:function(grid, rowIndex, colIndex){
			                	return grid.getStore().getAt(rowIndex).data.userStatus != "02";
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
		            items: [
		            	{
			                iconCls:'icon-remove',
			                tooltip: '删除用户',
			                handler: function(grid, rowIndex, colIndex, node, e, record, rowEl) {
			                	this.fireEvent('deleteclick', this, grid, rowIndex, colIndex, node, e, record, rowEl);			                    
			                },
			                isDisabled:function(grid, rowIndex, colIndex){
			                	return grid.getStore().getAt(rowIndex).data.userStatus == "03";
			                }
		            	}
			        ]
		        }
            ],
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'enteruser.DisableUserStore',
					        dock: 'bottom',
					        displayInfo: true
					    }]
        });
        this.callParent();
    }
});