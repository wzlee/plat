Ext.define('plat.view.service.AServiceGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.aservicegrid',
	id : 'fwsjtjcx',
	title : '服务列表',
	closable : true,
	closeAction : 'hide',
	columnLines : true,
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
	store : 'service.AServiceStore',
	tbar : ['-',
			{xtype: 'triggerfield',name:'sname',width:200,triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
					display:false,	//自定义变量判断triggercls是否display
					fieldLabel : '服务名称',
    				emptyText: '请输入服务名称关键字...',    
    				labelWidth : 60,
    				onTriggerClick:function(){
    					this.reset();
    					this.display = false;
	    				this.triggerCell.setDisplayed(false);
	    				this.setWidth(200);
    				}
    		},{xtype: 'triggerfield',name:'orgName',width:200,triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
    				display:false,	//自定义变量判断triggercls是否display
    				fieldLabel : '机构名称',
    				emptyText: '请输入机构名称关键字...',  
    				labelWidth : 60,
    				onTriggerClick:function(){
    					this.reset();
    					this.display = false;
	    				this.triggerCell.setDisplayed(false);
	    				this.setWidth(200);
    				}
    			},'-',
			 '-', {
				xtype : 'combo',
				width : 150,
				name : 'category',
				emptyText : '请选择服务类别...',
				queryMode: 'local',
			    displayField: 'text',
			    valueField: 'value',
			    editable : false,
			    store:  Ext.create('Ext.data.Store', {
				    fields: ['value', 'text'],
				    data : [
				        {"value" : 2, "text" : "上架审核中"},
				        {"value" : 7, "text" : "下架审核中"},
				        {"value" : 4, "text" : "变更审核中"},
				        {"value" : "", "text" : "全部"}
				 ]})
			}, '-', {
				iconCls : 'icon-search',
				text : '查找',
				action : 'search'
			}, '-', '双击服务可以查看服务', '->'],
	initComponent : function () {
		Ext.apply(this, {
			columns : [new Ext.grid.column.RowNumberer({
						text : '#',
						align : 'center',
						width : 30
					}), {
						text : '服务ID',
						align : 'center',
						dataIndex : 'id',
						hidden : true
					},
					{
						text : '服务名称',
						align : 'center',
						width : 120,
						dataIndex : 'serviceName',
						locked : true
					}, {
						text : '添加时间',
						align : 'center',
						width : 140,
						dataIndex : 'registerTime',
						locked : true
					}, {
						text : '服务状态',
						align : 'center',
						width : 84,
						dataIndex : 'currentStatus',
						locked : true,
						renderer : function (v) {
							switch (v) {
								case 2 : 
									v = '上架审核中';
									break;
								case 4 : 
									v = '变更审核中';
									break;
								case 7 : 
									v = '下架审核中';
									break;
								default : 
									v = '数据有误'
							}
							return v;
						}
					}, 
					{ text: '服务机构',align:'center',width:120,dataIndex:'enterprise.name',locked:true},
					{ text: '所属窗口',align:'center',width:80, dataIndex: 'enterprise.industryType',locked:true,
	                	renderer:function(value){                		
	                		return PlatMap.Enterprises.industryType[value];
	                	}
	                },
					{
				        xtype : 'actioncolumn',
						text : '配图',
						align : 'center',
						sortable : false,
						locked : true,
						width : 50,
						items : [{
							icon : 'jsLib/extjs/resources/themes/icons/scan.png',
							tooltip : '查看服务配图',
							handler : function(grid, rowIndex, colIndex, node,
									e, record, rowEl) {
								this.fireEvent('pictureclick', this, grid,
										rowIndex, colIndex, node, e, record,
										rowEl);
							}
						}]
				    },
					{
						xtype : 'actioncolumn',
						text : '批准',
						align : 'center',
						locked : true,
						sortable : false,
						menuDisabled : true,
						width : 50,
						items : [{
							iconCls : 'icon-ok',
//							tooltip : '批准',
							getTip: function(v, meta, record) {
								var cs = record.data.currentStatus; 
		                        if (cs == 2) {
		                        	return "批准服务上架";
		                        } else if (cs == 4) {
		                        	return "批准服务变更"
		                        } else {	//cs == 7 "下架审核中"
		                        	return "批准服务下架";
		                        }
		                    },
							handler : function(grid, rowIndex, colIndex, item, e, record, row) {
								openApprServiceWindow('批准', rowIndex);
							}
						}]
					}, {
						xtype : 'actioncolumn',
						text : '打回',
						locked : true,
						menuDisabled : true,
						align : 'center',
						sortable : false,
						width : 50,
						items : [{
							icon:'resources/images/reject.png',
							getTip: function(v, meta, record) {
								var cs = record.data.currentStatus; 
		                        if (cs == 2) {
		                        	return "拒绝上架申请";
		                        } else if (cs == 4) {
		                        	return "拒绝变更申请"
		                        } else {	//cs == 7 "下架审核中"
		                        	return "拒绝下架申请";
		                        }
		                    },
							handler : function(grid, rowIndex, colIndex, item, e, record) {
								openApprServiceWindow('打回', rowIndex);
							}
						}]
					}, {
						text : '联系人',
						align : 'center',
						width : 70,
						dataIndex : 'linkMan'
					}, {
						text : '联系电话',
						align : 'center',
						width : 116,
						dataIndex : 'tel'
					}, {
						text : '邮箱',
						align : 'center',
						width : 116,
						dataIndex : 'email'
					},{
						text : '服务类别ID',
						align : 'center',
						dataIndex : 'category.id',
						hidden : true
					}, {
						text : '服务类别',
						align : 'center',
						width : 100,
						dataIndex : 'category.text'
					}, { 
						text: '来源',
						align:'center',
						width:100, 
						dataIndex: 'serviceSource',
						renderer:function(value){
				    	return PlatMap.Service.serviceSource[value];
				    }}, {
						text : '服务机构ID',
						align : 'center',
						dataIndex : 'organizeId',
						hidden : true
					}, {
						text : '服务方式',
						align : 'center',
						width : 176,
						dataIndex : 'serviceMethod'
					}, 
//						{
//						text : '收费方式',
//						align : 'center',
//						width : 80,
//						dataIndex : 'chargeMethod'
//					}, 
						{
						text : '服务次数',
						align : 'center',
						width : 80,
						dataIndex : 'serviceNum'
					}],
			dockedItems : [{
				xtype : 'pagingtoolbar',
				store : 'service.AServiceStore',
				dock : 'bottom',
				displayInfo : true
			}]
		});
		this.callParent(arguments);
	}
});