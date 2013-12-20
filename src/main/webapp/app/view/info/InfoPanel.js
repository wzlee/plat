Ext.define('plat.view.info.InfoPanel',{
extend: 'Ext.container.Container',
   	alias : 'widget.infopanel',
   	
   	closable : true,
   	closeAction : 'hide',
   	
   /* requires: [
        'Ext.grid.*',
        'Ext.layout.container.HBox',
        'Ext.layout.container.VBox',
        'plat.store.enteruser.ApprUserStore',
        'plat.model.enteruser.ApprUserModel'
    ], */
    
    layout:'border',
    
    initComponent: function(){
    	//未删除的发送
    	this.sendStore = Ext.create('plat.store.info.InfoStore',{
    		storeId : 'sendStore'
    	});
    	Ext.apply(this.sendStore.proxy.extraParams, {type : 0});
    	this.sendStore.loadPage(1);
    	
    	//已删除
    	this.deleteStore = Ext.create('plat.store.info.InfoStore', {
    		storeId : 'deleteStore'
    	});
    	Ext.apply(this.deleteStore.proxy.extraParams, {type : 1});
    	this.deleteStore.loadPage(1);
    	
    	
        Ext.apply(this, {
            items: [{
                xtype: 'container',
                region: 'north',
                padding:'10',
                defaultType: 'button',
                items: [{
                    itemId: 'showSend',
                    text: '已发送',
                    scope: this,
                    disabled: true,
                    handler: this.onSendClick
                }, {
                    itemId: 'showDelete',
                    text: '已删除',
                    margin: '0 0 0 10',
                    scope: this,
                    handler: this.onDeleteClick
                }]
            }, {
                xtype: 'grid',
				region:'center',
				columnLines : true,
				tbar :[
					{iconCls:'icon-search', text:'发送消息', action:'save'},'-',
					{itemId:'delete',text:'删除信息',action:'delete'}
				],
				selModel : new Ext.selection.CheckboxModel(),
                columns: this.sendRealNameColumn(),
                store : this.sendStore,
                dockedItems : this.getPageTool(this.sendStore),
                viewConfig: {
                    emptyText: '暂无消息',
                    deferEmptyText: false
                },
                listeners:{
            		cellclick:function(gridpanel, td, cellIndex, record, tr, rowIndex, e, eOpts){	//单元格点击事件             
		        		if(cellIndex==3){
        					var window = Ext.getCmp('detailswindow');
	            			if(!window){
		    					window = Ext.widget('detailswindow',{
		    						title: '消息详细内容'		
		    					});    		
	    					}
	    					window.show();
							window.getComponent('detailswindowform').form.loadRecord(record);
				        }   
					}
                }
            }]    
        });
        this.callParent();
    },
    
    getPageTool : function(store){
    	return {
			xtype : 'pagingtoolbar',
			store : store,
			dock : 'bottom',
			displayInfo : true
		}
    },
    sendStore : null,
    //发送
    sendRealNameColumn : function(){
    	return [
//	        new Ext.grid.column.CheckColumn({text:'#',align:'center',flex:1}),
//	        { xtype :'checkcolumn',text: '#',align:'center',flex:1, dataIndex : 'id' },
	        { text: '消息id',align:'center',flex:1, dataIndex: 'id'},
	        { text: '消息类别',align:'center',flex:1, dataIndex: 'messageClass.messageType'},
	        { text: '消息类容',align:'center',flex:1, dataIndex: 'content',renderer:function(text){
	        	return "<a href='javascript:void(0)'>"+(text.length>10 ? text.substr(0,10)+'...' : text)+"</a>";
	        }},
	        { text: '接收人',align:'center',flex:1, dataIndex: 'receiver'},
	        { text: '时间',align:'center',flex:1, dataIndex: 'sendTime'}
        ];
    },
    //删除
    getRealNameColumn : function(){
    	return [
//	        new Ext.grid.column.RowNumberer({text:'序号',align:'center',flex:1}),
	        { text: '消息id',align:'center',flex:1, dataIndex: 'id'},
	        { text: '消息类别',align:'center',flex:1, dataIndex: 'messageClass.messageType'},
	        { text: '消息类容',align:'center',flex:1, dataIndex: 'content',renderer:function(text){
	        	return "<a href='javascript:void(0)'>"+(text.length>10 ? text.substr(0,10)+'...' : text)+"</a>";
	        }},
	        { text: '接收人',align:'center',flex:1, dataIndex: 'receiver'},
	        { text: '时间',align:'center',flex:1, dataIndex: 'sendTime'}
        ];
    },
    
    //发送
    onSendClick: function(){
        var grid = this.down('grid');
        Ext.suspendLayouts();
        // 显示删除按钮
        grid.down('#delete').show();
        // 记录服务机构申请的查询参数
//        ParamManager.inputParam(this, ParamManager.serviceApplyMap);
//        // 显示改表格的查询参数
//        ParamManager.outParam(this, ParamManager.realNameMap);
        // 表格重新配置
        grid.reconfigure(this.sendStore, this.sendRealNameColumn());
        grid.removeDocked(grid.down('pagingtoolbar'));
        grid.addDocked(this.getPageTool(this.sendStore));
        grid.getSelectionModel().setLocked(false);
        this.down('#showSend').disable();
        this.down('#showDelete').enable();
        Ext.resumeLayouts(true);  
    },
    deleteStore : null,
    //删除
    onDeleteClick: function(){
        var grid = this.down('grid');        
        Ext.suspendLayouts();
        // 隐藏删除按钮
        grid.down('#delete').hide();
        // 记录查询参数
//        ParamManager.inputParam(grid, ParamManager.realNameMap);
        // 显示改表格的查询参数
//        ParamManager.outParam(grid, ParamManager.serviceApplyMap);
        // 表格重新配置
        grid.reconfigure(this.deleteStore, this.getRealNameColumn());
        grid.removeDocked(grid.down('pagingtoolbar'));
        grid.addDocked(this.getPageTool(this.deleteStore));
        grid.getSelectionModel().setLocked(true);
//        console.log(grid.getSelectionModel());
        this.down('#showDelete').disable();
        this.down('#showSend').enable();
        Ext.resumeLayouts(true);
    } 
});
