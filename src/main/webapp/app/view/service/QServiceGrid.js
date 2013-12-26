/**
 * xuwf statistics服务综合查询
 */
Ext.define('plat.view.service.QServiceGrid',{
	extend:'Ext.grid.Panel',
	xtype:'qservicegrid',  
	title:'服务列表',
	id:'fwzhcx',
	closable:true,
    closeAction:'hide',
	columnLines:true,
	emptyText:'没有找到相关的数据,请检查输出条件是否存在......',
    store:'service.QServiceStore',
    cls:'font-size:14px',
    tbar :['-',
    			{
    				xtype:'triggerfield',
    				width:200,
    				name:'sname',
    				fieldLabel:'服务名称',
    				emptyText:'输入名称关键字...',
    				labelWidth:60,
    				labelStyle:'font-size:13',
    				triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
    				onTriggerClick:function(){
						this.reset();
					}
    			},
    			'-',
    			{xtype: 'triggerfield',name:'orgName',width:200,triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
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
    			{
    				xtype:'servicestatus',
    				width:200,
    				fieldLabel:'服务状态',
    				name:'status',
    				labelWidth:60,
    				forceSelection:true,
    				labelStyle:'font-size:13',
    				multiSelect:true,
    				emptyText:'选择服务状态...'
    			},
//    			{
//    				xtype:'catetreepicker',
//    				fieldLabel:'服务类别',
//    				width:300,
//    				labelWidth:60,
//    				name:'servicecategory',
//    				displayField:'text',
//    				editable:false,
//    				valueField:'id'
////    				autoScroll:true
// 					
//    			},
    			'-',
    			{iconCls:'icon-search',text:'查找',action:'search'},
    			'-',
//    			{xtype:'displayfield',labelStyle:'font-size:14',text:'双击服务可以查看服务哦！'},
    			'双击服务可以查看服务详情',
    			'->'
//				{xtype:'button',text:'显示分组',enableToggle:true,action:'group'},'-'
	],
//	features: [{
//		ftype:'grouping',
//		name:'group',
//		id:'group',
//		groupByText:'按属性分组',
//		showGroupsText:'显示分组',
//		startCollapsed:false,
//		groupHeaderTpl: '<font color="green">{name}</font> : 共[ <font color="green">{rows.length}</font> ]个服务'
//	}],
    initComponent: function() {
      	var me = this;
        Ext.apply(this, {
			columns: [
						{xtype:'rownumberer',text:'#',align:'center',width:30},
				        { text: '服务ID',align:'center', dataIndex: 'id',hidden:true},
				        { text: '服务编码',align:'center',width:80, dataIndex: 'serviceNo',hidden:true},
				        { text: '服务名称',align:'center',width:150, dataIndex: 'serviceName'},
				        { text: '服务类别ID',align:'center', dataIndex: 'category.id',hidden:true},
				        { text: '服务类别',align:'center',width:100, dataIndex: 'category.text'},
				        { text: '服务状态',align:'center',width:82, dataIndex: 'currentStatus',
				        	renderer:function(v){
				        		return PlatMap.Service.currentStatus[v];
				        	}
				        },
				        { text: '服务机构',align:'center',width:120,dataIndex:'enterprise.name'},
				        { text: '所属窗口',align:'center',width:80, dataIndex: 'enterprise.industryType',
		                	renderer:function(value){                		
		                		return PlatMap.Enterprises.industryType[value];
		                	}
		                },
				        { text: '添加时间',align:'center',width:140, dataIndex: 'registerTime'},
					    { text: '联系人',align:'center',width:70, dataIndex: 'linkMan'},
					    { text: '联系电话',align:'center',width:112, dataIndex: 'tel'},
					    { text: '邮箱',align:'center',width:134, dataIndex: 'email'},
//				        { text: '所属企业',align:'center',width:80, dataIndex: 'enterprise.name'},
				        { text: '来源',align:'center',width:118, dataIndex: 'serviceSource',
				        	renderer:function(v){
				        		return PlatMap.Service.serviceSource[v];
					    }},
					    {
					        xtype : 'actioncolumn',
							text : '配图',
							align : 'center',
							sortable : false,
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
				        { text: '服务方式',align:'center',width:100, dataIndex: 'serviceMethod',hidden:true},
				        { text: '服务描述',align:'center',width:150, dataIndex: 'serviceProcedure',hidden:true},
				        { text: '收费方式',align:'center',width:80, dataIndex: 'chargeMethod',hidden:true},
				        { text: '服务次数',align:'center',width:80, dataIndex: 'serviceNum',hidden:true},
		    			 { text: '服务价格',align:'center',width:80, dataIndex: 'costPrice',hidden:true}
		    		],
		    		
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'service.QServiceStore',  
					        dock: 'bottom',
					        displayInfo: true,
					        displayMsg:"显示第{0}条到第{1}条,共{2}条数据",      
       						emptyMsg:"查找不到任何数据"
					    }]
        });
        this.callParent();
    }
});