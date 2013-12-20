var lookIcon = "<img src='jsLib/extjs/resources/themes/icons/scan.png' />";
Ext.define('plat.view.enteruser.ApprUserTab', {
    extend: 'Ext.container.Container',
   	alias : 'widget.apprusertab',
   	
   	closable : true,
   	closeAction : 'hide',
   	
    requires: [
        'Ext.grid.*',
        'Ext.layout.container.HBox',
        'Ext.layout.container.VBox',
        'plat.store.enteruser.ApprUserStore',
        'plat.model.enteruser.ApprUserModel',
        'plat.store.enteruser.ApprPersonalUserStore',
        'plat.model.enteruser.ApprPersonalUserModel'
    ], 
    
    layout:'border',
    
    initComponent: function(){
    	this.realNameStore = Ext.create('plat.store.enteruser.ApprUserStore');
    	Ext.apply(this.realNameStore.proxy.extraParams, {applyType : 1});
    	this.realNameStore.loadPage(1);
    	
    	this.serviceApplyStore = Ext.create('plat.store.enteruser.ApprUserStore');
    	Ext.apply(this.serviceApplyStore.proxy.extraParams, {applyType : 2});
    	this.serviceApplyStore.loadPage(1);
    	
    	this.userRealNameStore = Ext.create('plat.store.enteruser.ApprPersonalUserStore');
    	Ext.apply(this.userRealNameStore.proxy.extraParams, {applyType : 1});
    	this.userRealNameStore.loadPage(1);
    	
    	this.orgRegisterStore = Ext.create('plat.store.enteruser.OrgRegisterApprovalStore');
    	Ext.apply(this.orgRegisterStore.proxy.extraParams);
    	this.orgRegisterStore.loadPage(1);
    	
        Ext.apply(this, {
            items: [{
                xtype: 'container',
                region: 'north',
                padding:'10',
                defaultType: 'button',
                items: [{
                    itemId: 'showRealName',
                    text: '待审核的企业实名认证',
                    scope: this,
                    disabled: true,
                    handler: this.onShowRealNameClick
                }, {
                    itemId: 'showServiceApply',
                    text: '待审核的服务机构认证',
                    margin: '0 0 0 10',
                    scope: this,
                    handler: this.onShowServiceApplyClick
                },
                {
                	itemId: 'showUserRealName',
                    text: '待审核的用户实名认证',
                    margin: '0 0 0 10',
                    scope: this,
                    handler: this.onShowUserRealNameClick
                },
                {
                	itemId: 'showOrgRegister',
                    text: '服务机构注册审核',
                    margin: '0 0 0 10',
                    scope: this,
                    handler: this.onShowOrgRegisterClick
                }]
            }, {
                xtype: 'grid',
				region:'center',
				columnLines : true,
				tbar :['-',				
					{xtype:'textfield', width:150, itemId: 'username', name:'username', emptyText:'申请人会员名'},'-',
					{xtype:'textfield', width:150, itemId: 'enterpriseName', name:'enterpriseName', emptyText:'申请实名'},'-',
					{xtype:'datefield', fieldLabel:'申请时间', itemId: 'applyTimeBegin', labelWidth:60, name:'applyTimeBegin', emptyText:'开始时间'},'-',
					{xtype:'datefield', name:'applyTimeEnd', itemId: 'applyTimeEnd', emptyText:'结束时间'},'-',
					{iconCls:'icon-search', text:'查找', action:'search'},'->'
				],
				
                columns: this.getRealNameColumn(),
                store : this.realNameStore,
                dockedItems : this.getPageTool(this.realNameStore),
                viewConfig: {
                    emptyText: '暂无相关申请',
                    deferEmptyText: false
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
    
    /****************实名认证******************/
    realNameStore : null,
    
    getRealNameColumn : function(){	
    	return [
	        new Ext.grid.column.RowNumberer({text:'#',align:'center',width:30}),
	        { text: '申请会员名',align:'center',width:100, dataIndex: 'user.userName'},
	        { text: '申请实名',align:'center',width:200, dataIndex: 'name'},
	        { text: '组织机构号',align:'center',width:120, dataIndex: 'icRegNumber'},
	        { text: '申请时间',align:'center',width:150, dataIndex: 'applyTime'},
	        {
        		header : '执照副本',
	       		dataIndex : 'licenceDuplicate',
	       		width : 100,
	       		toolTip : '查看执照副本',
	       		align : 'center',
	       		renderer : function (value) {
	       			if (value) {
	       				if(value.indexOf('http') > -1){
	       					return "<a href='" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}else {
	       					return "<a href='upload/" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}		       			
	       			} else {
	       				return "<a href='resources/images/nopic.gif' class='fancybox'>"+ lookIcon +"</a>";
	       			}
	       		}
            },{
				header : '企业公函',
				dataIndex : 'businessLetter',
	       		width : 100,
	       		toolTip : '查看企业公函',
	       		align : 'center',
	       		renderer : function (value) {
	       			if (value) {
	       				if(value.indexOf('http') > -1){
	       					return "<a href='" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}else {
	       					return "<a href='upload/" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}		       			
	       			} else {
	       				return "<a href='resources/images/nopic.gif' class='fancybox'>"+ lookIcon +"</a>";
	       			}
	       		}
            },{
				xtype : 'actioncolumn',
				text : '通过',
				align : 'center',
				sortable : false,
				width : 50,
				iconCls : 'icon-ok',
				tooltip : '通过',
				handler : function(grid, rowIndex, colIndex, item, e, record, row) {
					openApprUserWindow('通过', record);
				}
			}, {
				xtype : 'actioncolumn',
				text : '驳回',
				align : 'center',
				sortable : false,
				width : 50,
//				iconCls : 'icon-remove',
				icon:'resources/images/reject.png',
				tooltip : '驳回',
				handler : function(grid, rowIndex, colIndex, item, e, record, row) {
					openApprUserWindow('驳回', record);
				}
			}
        ];
    },
    
    onShowRealNameClick: function(){
        var grid = this.down('grid');
        Ext.suspendLayouts();
        // 显示查询条件
        grid.down('#username').show();
        // 记录服务机构申请的查询参数
        if(this.down('#showServiceApply').disabled){
        	ParamManager.inputParam(this, ParamManager.serviceApplyMap);
        }
        if(this.down('#showUserRealName').disabled){
        	ParamManager.inputParam(this, ParamManager.userRealNameMap);
        }
        if(this.down('#showOrgRegister').disabled){
        	ParamManager.inputParam(grid, ParamManager.orgRegisterMap);
        }
        // 显示该表格的查询参数
        ParamManager.outParam(this, ParamManager.realNameMap);
        // 表格重新配置
        grid.reconfigure(this.realNameStore, this.getRealNameColumn());
        grid.removeDocked(grid.down('pagingtoolbar'));
        grid.addDocked(this.getPageTool(this.realNameStore));
        this.down('#showServiceApply').enable();
        this.down('#showUserRealName').enable();
        this.down('#showOrgRegister').enable();
        this.down('#showRealName').disable();
        Ext.resumeLayouts(true);  
    },
    
    /****************服务机构******************/
    serviceApplyStore : null,
    
    getServiceApplyColumn : function(){
    	return [
	        new Ext.grid.column.RowNumberer({text:'#',align:'center',width:30}),
	        { text: '申请会员名',align:'center',width:100,dataIndex: 'user.userName'},
	        { text: '申请实名',align:'center',width:200, dataIndex: 'name', 
	        	renderer : function (value, metaData, record) {
	        		if(record.raw.enterprise.isApproved) return record.raw.enterprise.name;
	        		return value;
	        	}
	        },
	        { text: '组织机构号',align:'center',width:150, dataIndex: 'icRegNumber', 
	        	renderer : function (value, metaData, record) {
	        		if(record.raw.enterprise.isApproved) return record.raw.enterprise.icRegNumber;
	        		return value;
	        	}
	        },
	        { text: '实名认证',align:'center',width:100, dataIndex: 'enterprise.isApproved',
	        	renderer : function (value){
	        		return value ? '通过' : '未通过';
	        	}
	        },
	        { text: '申请时间',align:'center',width:150, dataIndex: 'applyTime'},
        	{
        		header : '执照副本',
	       		dataIndex : 'licenceDuplicate',
	       		width : 80,
	       		toolTip : '查看执照副本',
	       		align : 'center',
	       		renderer : function (value, metaData, record) {
	       			if(record.raw.enterprise.isApproved) value = record.raw.enterprise.licenceDuplicate;
	       			if (value) {
	       				if(value.indexOf('http') > -1){
	       					return "<a href='" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}else {
	       					return "<a href='upload/" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}		       			
	       			} else {
	       				return "<a href='resources/images/nopic.gif' class='fancybox'>"+ lookIcon +"</a>";
	       			}
	       		}
            },{
				header : '企业公函',
				dataIndex : 'businessLetter',
	       		width : 80,
	       		toolTip : '查看企业公函',
	       		align : 'center',
	       		renderer : function (value, metaData, record) {
	       			if(record.raw.enterprise.isApproved) value = record.raw.enterprise.businessLetter;
	       			if (value) {
	       				if(value.indexOf('http') > -1){
	       					return "<a href='" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}else {
	       					return "<a href='upload/" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}		       			
	       			} else {
	       				return "<a href='resources/images/nopic.gif' class='fancybox'>"+ lookIcon +"</a>";
	       			}
	       		}
            },{
				xtype : 'actioncolumn',
				text : '通过',
				align : 'center',
				sortable : false,
				width : 50,
				iconCls : 'icon-ok',
				tooltip : '通过',
				handler : function(grid, rowIndex, colIndex, item, e, record, row) {
					openApprUserWindow('通过', record);
				}
			}, {
				xtype : 'actioncolumn',
				text : '驳回',
				align : 'center',
				sortable : false,
				width : 50,
//				iconCls : 'icon-remove',
				icon:'resources/images/reject.png',
				tooltip : '驳回',
				handler : function(grid, rowIndex, colIndex, item, e, record, row) {
					openApprUserWindow('驳回', record);
				}
			}
        ];
    },
    
    onShowServiceApplyClick: function(){
        var grid = this.down('grid');        
        Ext.suspendLayouts();
        // 隐藏查询条件
        grid.down('#username').hide();
        // 记录查询参数
        if(this.down('#showRealName').disabled){
        	ParamManager.inputParam(grid, ParamManager.realNameMap);
        }
        if(this.down('#showUserRealName').disabled){
        	ParamManager.inputParam(grid, ParamManager.userRealNameMap);
        }
        if(this.down('#showOrgRegister').disabled){
        	ParamManager.inputParam(grid, ParamManager.orgRegisterMap);
        }
        // 显示该表格的查询参数
        ParamManager.outParam(grid, ParamManager.serviceApplyMap);
        // 表格重新配置
        grid.reconfigure(this.serviceApplyStore, this.getServiceApplyColumn());
        grid.removeDocked(grid.down('pagingtoolbar'));
        grid.addDocked(this.getPageTool(this.serviceApplyStore));
        this.down('#showRealName').enable();
        this.down('#showUserRealName').enable();
        this.down('#showOrgRegister').enable();
        this.down('#showServiceApply').disable();
        Ext.resumeLayouts(true);
    },
    
    /****************个人用户实名认证******************/
    userRealNameStore : null,
    
    getUserRealNameColumn : function(){
    	return [
	        new Ext.grid.column.RowNumberer({text:'#',align:'center',width:30}),
	        { text: '申请会员名',align:'center',width:100,dataIndex: 'user.userName'},
	        { text: '申请实名',align:'center',width:200, dataIndex: 'name'},
	        { text: '申请时间',align:'center',width:150, dataIndex: 'applyTime'},
        	{
        		header : '个人近照',
	       		dataIndex : 'personalPhoto',
	       		width : 80,
	       		tooltip : '查看个人近照',
	       		align : 'center',
	       		renderer : function (value, metaData, record) {
	       			if (value) {
	       				if(value.indexOf('http') > -1){
	       					return "<a href='" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}else {
	       					return "<a href='upload/" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}		       			
	       			} else {
	       				return "<a href='resources/images/nopic.gif' class='fancybox'>"+ lookIcon +"</a>";
	       			}
	       		}
            },{
				header : '身份证附件照',
				dataIndex : 'idCardPhoto',
	       		width : 110,
	       		tooltip : '查看身份证附件照',
	       		align : 'center',
	       		renderer : function (value, metaData, record) {
	       			if (value) {
	       				if(value.indexOf('http') > -1){
	       					return "<a href='" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}else {
	       					return "<a href='upload/" + value + "' class='fancybox'>"+ lookIcon +"</a>";
	       				}		       			
	       			} else {
	       				return "<a href='resources/images/nopic.gif' class='fancybox'>"+ lookIcon +"</a>";
	       			}
	       		}
            },{
				xtype : 'actioncolumn',
				text : '通过',
				align : 'center',
				sortable : false,
				width : 50,
				iconCls : 'icon-ok',
				tooltip : '通过',
				handler : function(grid, rowIndex, colIndex, item, e, record, row) {
					openApprPersonalUserWindow('通过', record);
				}
			}, {
				xtype : 'actioncolumn',
				text : '驳回',
				align : 'center',
				sortable : false,
				width : 50,
//				iconCls : 'icon-remove',
				icon:'resources/images/reject.png',
				tooltip : '驳回',
				handler : function(grid, rowIndex, colIndex, item, e, record, row) {
					openApprPersonalUserWindow('驳回', record);
				}
			}
        ];
    },
    
    onShowUserRealNameClick: function(){
        var grid = this.down('grid');        
        Ext.suspendLayouts();
        // 显示查询条件
        grid.down('#username').show();
        // 记录查询参数
        if(this.down('#showRealName').disabled){
        	ParamManager.inputParam(grid, ParamManager.realNameMap);
        }
        if(this.down('#showServiceApply').disabled){
        	ParamManager.inputParam(grid, ParamManager.serviceApplyMap);
        }
        if(this.down('#showOrgRegister').disabled){
        	ParamManager.inputParam(grid, ParamManager.orgRegisterMap);
        }
        // 显示该表格的查询参数
        ParamManager.outParam(grid, ParamManager.userRealNameMap);
        // 表格重新配置
        grid.reconfigure(this.userRealNameStore, this.getUserRealNameColumn());
        grid.removeDocked(grid.down('pagingtoolbar'));
        grid.addDocked(this.getPageTool(this.userRealNameStore));
        this.down('#showRealName').enable();
        this.down('#showServiceApply').enable();
        this.down('#showOrgRegister').enable();
        this.down('#showUserRealName').disable();
        Ext.resumeLayouts(true);
    },
    
    /****************服务机构注册审核******************/
    orgRegisterStore : null,
    
    getOrgRegisterColumn : function(){	
    	return [
	        new Ext.grid.column.RowNumberer({text:'#',align:'center',width:30}),
	        { text: '会员名',align:'center',width:100, dataIndex: 'userName'},
	        { text: '申请机构',align:'center',width:150, dataIndex: 'orgName'},
//	        { text: '注册邮箱',align:'center',width:150, dataIndex: 'email'},
	        { text: '组织机构代码',align:'center',width:120, dataIndex: 'icRegNumber'},
	        { text: '申请时间',align:'center',width:150, dataIndex: 'regTime'},
	        { text: '行业',align:'center',width:120,dataIndex:'orgIndustry',
	        	renderer:function(v){
	        		return PlatMap.Enterprises.orgIndustry[v];
	        	}
	        },
	        { text: '机构性质',align:'center',width:120,dataIndex:'orgProperty',
	        	renderer:function(v){
	        		return PlatMap.Enterprises.orgProperty[v];
	        	}
	        },
	        { text: '经营范围',align:'center',width:120,dataIndex:'business'},
//	        {
//        		header : '执照副本',
//	       		dataIndex : 'licenceDuplicate',
//	       		width : 100,
//	       		toolTip : '查看执照副本',
//	       		align : 'center',
//	       		renderer : function (value) {
//	       			if (value) {
//		       			return "<a href='upload/" + value + "' class='fancybox'>"+ lookIcon +"</a>";
//	       			} else {
//	       				return "<a href='resources/images/nopic.gif' class='fancybox'>"+ lookIcon +"</a>";
//	       			}
//	       		}
//            },{
//				header : '企业公函',
//				dataIndex : 'businessLetter',
//	       		width : 100,
//	       		toolTip : '查看企业公函',
//	       		align : 'center',
//	       		renderer : function (value) {
//	       			if (value) {
//		       			return "<a href='upload/" + value + "' class='fancybox'>"+ lookIcon +"</a>";
//	       			} else {
//	       				return "<a href='resources/images/nopic.gif' class='fancybox'>"+ lookIcon +"</a>";
//	       			}
//	       		}
//            },
            {
				xtype : 'actioncolumn',
				text : '通过',
				align : 'center',
				sortable : false,
				width : 50,
				iconCls : 'icon-ok',
				tooltip : '通过',
				handler : function(grid, rowIndex, colIndex, item, e, record, row) {
					Ext.MessageBox.confirm('提示', '您正在通过公司实名为 ['+ record.raw.orgName +'] 的服务机构注册申请，是否继续？', function(btn){
    					if(btn != 'yes') return;
						Ext.Ajax.request({
							url: 'user/approveOrgRegister',
						    params: {
						        rid : record.raw.id,
						        status : 1
						    },
						    success: function(response){
						    	var result = eval('('+response.responseText+')');
						        Ext.Msg.alert('提示', result.message);
						        if(result.success){
						        	grid.getStore().remove(record);
						        }
						    },
						    failure : function(response){
								Ext.Msg.alert('提示', response.responseText)
							}
				    	});
					});
				}
			}, {
				xtype : 'actioncolumn',
				text : '驳回',
				align : 'center',
				sortable : false,
				width : 50,
//				iconCls : 'icon-remove',
				icon:'resources/images/reject.png',
				tooltip : '驳回',
				handler : function(grid, rowIndex, colIndex, item, e, record, row) {
					Ext.MessageBox.confirm('提示', '您正在驳回公司实名为 ['+ record.raw.orgName +'] 的服务机构注册申请，是否继续？', function(btn){
    					if(btn != 'yes') return;
						Ext.Ajax.request({
							url: 'user/approveOrgRegister',
						    params: {
						        rid : record.raw.id,
						        status : 0
						    },
						    success: function(response){
						        var result = eval('('+response.responseText+')');
						        Ext.Msg.alert('提示', result.message);
						        if(result.success){
						        	grid.getStore().remove(record);
						        }
						    },
						    failure : function(response){
								Ext.Msg.alert('提示', response.result.message)
							}
				    	});
					});
				}
			}
        ];
    },
    
    onShowOrgRegisterClick: function(){
        var grid = this.down('grid');
        Ext.suspendLayouts();
        // 显示查询条件
        grid.down('#username').show();
        // 记录服务机构申请的查询参数
        if(this.down('#showServiceApply').disabled){
        	ParamManager.inputParam(this, ParamManager.serviceApplyMap);
        }
        if(this.down('#showUserRealName').disabled){
        	ParamManager.inputParam(this, ParamManager.userRealNameMap);
        }
        if(this.down('#showRealName').disabled){
        	ParamManager.inputParam(grid, ParamManager.realNameMap);
        }
        // 显示该表格的查询参数
        ParamManager.outParam(this, ParamManager.orgRegisterMap);
        // 表格重新配置
        grid.reconfigure(this.orgRegisterStore, this.getOrgRegisterColumn());
        grid.removeDocked(grid.down('pagingtoolbar'));
        grid.addDocked(this.getPageTool(this.orgRegisterStore));
        this.down('#showServiceApply').enable();
        this.down('#showUserRealName').enable();
        this.down('#showRealName').enable();
        this.down('#showOrgRegister').disable();
        Ext.resumeLayouts(true);  
    }
});