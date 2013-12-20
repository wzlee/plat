Ext.define('plat.view.mall.MallAddServiceGrid', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.malladdservicegrid',
			xtype : 'malladdservicegrid',

			id : 'malladdservicegrid',
			width : 750,
			hight : 500,
			emptyText:'没有找到相关的数据,请换个查询条件试试......',
			closeAction : 'hide',
			store : 'mall.MallAddServiceStore',
			selModel : new Ext.selection.CheckboxModel(),
			tbar : ['-', 
					{
	                    xtype: 'treecombo',
	                    name:'id',
	                    allowBlank:false,
	                    width: 250,
	                    store: Ext.data.StoreManager.lookup('serviceCategoryStore') ? 
	                    		Ext.data.StoreManager.lookup('serviceCategoryStore'):
	                    		Ext .create( 'plat.store.service.ServiceCategoryStore',
										{
											storeId : 'serviceCategoryStore'
										}),
			            displayField:'text',
						valueField:'id',
						canSelectFolders:false,
	                    rootVisible:false,
	                    editable:false,
	                    emptyText:'请选择服务类别...',
	                    fieldLabel: '服务类别',
	                    labelWidth : 60
		                }, '-', {
						xtype : 'triggerfield',
						width : 200,
						name : 'serviceName',
						fieldLabel : '服务名称',
						emptyText : '输入名称关键字...',
						labelWidth : 60,
						labelStyle : 'font-size:13',
						triggerCls : Ext.baseCSSPrefix + 'form-clear-trigger',
						onTriggerClick : function() {
							this.reset();
						}
					},
					{
						xtype : 'combo',
						fieldLabel: '所属窗口',
				        name: 'industryType',
				        editable : false,
				        width:200,
				        labelWidth : 60,
				        emptyText : '请选择...',
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
				        }),
				        queryMode : 'local',
					    displayField: 'text',
					    valueField: 'value'
					},
					'-', {
						iconCls : 'icon-search',
						text : '查找',
						action : 'search'
					},
					{
						xtype: 'triggerfield',
	                    name:'mallId',
	                    hidden:true
					}

			],

			columns : [
					new Ext.grid.column.RowNumberer({text:'序号',align:'center',width:80}),
					{
						text : '',
						align : 'center',
						dataIndex : 'id',
						hidden : true
					}, {
						text : '服务名称',
						align : 'center',
						width : 120,
						dataIndex : 'serviceName'
					},{
						text : '服务类别',
						align : 'center',
						width : 120,
						dataIndex : 'category.text'
					}, {
						text : '所属窗口',
						align : 'center',
						width : 120,
						dataIndex : 'enterprise.industryType',
						renderer:function(value){
							return PlatMap.Enterprises.industryType[value];
						}
					},{
						text : '联系人',
						align : 'center',
						width : 70,
						dataIndex : 'linkMan'
					}, {
						text : '电话',
						align : 'center',
						width : 124,
						dataIndex : 'tel'
					}, {
						text : '邮箱',
						align : 'center',
						width : 156,
						dataIndex : 'email'
					}, {
						text : '来源',
						align : 'center',
						width : 100,
						dataIndex : 'serviceSource',
						renderer : function(v) {
							return PlatMap.Service.serviceSource[v];
						}
					}]
		});
