/**
 * 企业用户搜索 xuwf	2013-8-22
 */
//Ext.apply(Ext.form.field.VTypes,{
//	dateRange:function(val,field){
//		var beginDate = null,	//开始日期
//		beginDateCmp = null,	//开始日期
//		endDate = null,
//		endDateCmp = null,
//		validStatus = true;	//验证状态
//		if(field.dateRange){
//			//获取开始时间
//			if(!Ext.isEmpty(field.dateRange.begin)){
//				beginDateCmp = Ext.getCmp(field.dateRange.begin);
//				beginDate = beginDateCmp.getValue();
////				console.log("beginDate:"+beginDate);
//			}
//			//获取结束时间
//			if(!Ext.isEmpty(field.dateRange.end)){
//				endDateCmp = Ext.getCmp(field.dateRange.end);
//				endDate = endDateCmp.getValue();
////				console.log("endDate:"+endDate);
//			}
//		}
//		//如果开始日期或结束日期有一个为空则校验通过
//		if(!Ext.isEmpty(beginDate) && !Ext.isEmpty(endDate)){
//			validStatus = beginDate < endDate;
//		}
//		return validStatus;
//	},
//	dateRangeText:'起始日期不能大于结束日期,请重新选择.'
//});
Ext.apply(Ext.form.field.VTypes, {
        daterange: function(val, field) {
            var date = field.parseDate(val);
            if (!date) {
                return false;
            }
            if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
                var start = field.up('buttongroup').down('#' + field.startDateField);
                start.setMaxValue(date);
                start.validate();
                this.dateRangeMax = date;
            }
            else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
                var end = field.up('buttongroup').down('#' + field.endDateField);
                end.setMinValue(date);
                end.validate();
                this.dateRangeMin = date;
            }
            /*
             * Always return true since we're only using this vtype to set the
             * min/max allowed values (these are tested for after the vtype test)
             */
            return true;
        }        
});
Ext.define('plat.view.enteruser.QEnterUserGrid',{
	extend:'Ext.grid.Panel',
	xtype:'qenterusergrid',
	title:'用户列表',
	id:'yhssgl',
	closable:true,
	closeAction:'hide',
	columnLines:true,
	emptyText:'没有找到相关的数据,请换个查询条件试试......',
	store:'enteruser.QEnterUserStore',
	tbar:[{
        xtype: 'buttongroup',
        columns: 4,
        title: '',
        items: [
        	{
				xtype:'triggerfield',
				triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
				width:200,
				name:'username',
				fieldLabel:'会员名',
				labelWidth:60,
				emptyText:'输入会员名...',
				labelStyle:'font-size:13',
    			onTriggerClick:function(){
					this.reset();
				}
			},{
				xtype:'triggerfield',
				triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
				width:200,	
				name:'entershort',
				fieldLabel:'企业简称',
				labelWidth:60,
				emptyText:'输入企业简称...',
				labelStyle:'font-size:13',
    			onTriggerClick:function(){
					this.reset();
				}
			},{
				xtype:'triggerfield',
				triggerCls: Ext.baseCSSPrefix + 'form-clear-trigger',
				width:200,
				name:'entername',
				fieldLabel:'企业全称',
				labelWidth:60,
				emptyText:'输入企业全称...',
				labelStyle:'font-size:13',
    			onTriggerClick:function(){
					this.reset();
				}
			},
			{
    				xtype: 'combo',
    				name:'enterpriseType',
    				width:200,
    				labelWidth:60,
    				emptyText: '请选择用户类型...',
    				fieldLabel:'用户类型',
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
//				dateRange:{begin:'beginDate1',end:'endDate1'}, //用于VType类型dateRange
				xtype:'datefield',
				name:'startdt',
				width:200,
				labelWidth:60,
				emptyText:'请选中起始时间...',
				fieldLabel:'注册时间',
				labelStyle:'font-size:13',
				itemId: 'startdt',
		        vtype: 'daterange',
		        endDateField: 'enddt' // id of the end date field
			},
			{
				xtype:'datefield',
				name:'enddt',
				width:200,
				labelWidth:60,
				emptyText:'请选中结束时间...',
				fieldLabel:'结束时间',
				labelStyle:'font-size:13',
				itemId: 'enddt',
		        vtype: 'daterange',
		        startDateField: 'startdt' // id of the start date field
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
        	{iconCls:'icon-search',text:'查找',action:'search'}
        ]
    }],
	initComponent: function() {
      	var me = this;    	
        Ext.apply(this, {	
			columns: [
						{xtype:'rownumberer',text:'#',align:'center',width:30},
				        { text: '企业ID',align:'center', dataIndex: 'id',hidden:true},
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
				        { text: '企业全称',align:'center',width:130, dataIndex: 'enterprise.name',
				        	renderer:function(v,metaData,record){
				        		if(record.data.isPersonal) return '暂无';
					        	return '<a href="javascript:void(0)">' + v + '</a>';
				        	}
				        },
				        { text: '用户类型',align:'center',width:100, dataIndex: 'enterprise.type',
				        	renderer:function(value,metaData,record){
				        		if(record.data.isPersonal) return '个人';
				        		return PlatMap.Enterprises.type[value];
				        	}
				        },
				        { text: '所属窗口',align:'center',width:80, dataIndex: 'enterprise.industryType',
		                	renderer:function(value){                		
		                		return PlatMap.Enterprises.industryType[value];
		                	}
		                },
					    { text: '电子邮箱',align:'center',width:170, dataIndex: 'enterprise.email',
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
				        { text: '注册时间',align:'center',width:150, dataIndex: 'regTime'},
				      	{ text: '数据来源',align:'center',width:160, dataIndex: 'sourceId',
				        	renderer:function(v){
					    		return PlatMap.User.sourceId[v];
					    }},
				        { text: '账号状态',align:'center',width:80, dataIndex: 'userStatus',
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
		                			case 0:
		                				value = '<font color="purple">未设置</font>'
		                				break;
	                			}
                				return value;
	              			 }
				        },
					    { text: '邮箱是否已验证',align:'center',width:60, dataIndex: 'isApproved',hidden:true},
				        { text: '备注',align:'center',width:80, dataIndex: '备注',hidden:true}
				       
		    		],
		    		
	    	dockedItems :[{
					        xtype: 'pagingtoolbar',
					        store: 'enteruser.QEnterUserStore',  
					        dock: 'bottom',
					        displayInfo: true,
					        displayMsg:"显示第{0}条到第{1}条,共{2}条数据",      
       						emptyMsg:"查找不到任何数据"
					    }]
        });
        this.callParent();
    }
});