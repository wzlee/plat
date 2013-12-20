Ext.define('plat.view.policy.RuleGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'rulegrid',
	id:'ruleGrid',
	store:new Ext.data.JsonStore({
		data:[	
		{ruleId:'101', name:'企业创立时间：较今日满一年以上'}, {ruleId:'102', name:'企业创立时间：较今日不满一年'}, 
		{ruleId:'201', name:' 创立地点：深圳市'},{ruleId:'202', name:' 创立地点：不在深圳市'},
		{ruleId:'301', name:'企业所属：进出口外贸'}, {ruleId:'302',name: '企业所属：互联网产业'}, {ruleId:'303',name: ' 企业所属：新能源产业'}, {ruleId:'304', name:'企业所属：新材料产业'},
		{ruleId:'305', name:'企业所属：生物产业'}, {ruleId:'306', name:'企业所属：新一代信息技术产业'}, {ruleId:'307' ,name: '企业所属：会展业'}, {ruleId:'308', name:'企业所属：军工配套等新兴产业'},
		{ruleId:'309',name: '企业所属：其他'}, {ruleId:'401',name: '上年主营业务收入:大于300万小于1亿'}, {ruleId:'402',name: '上年主营收入：大于或等于1亿'},
		{ruleId:'403', name:' 上年主营业务收入:小于或等于300万'}, {ruleId:'501', name:'上年总收入：小于或等于2亿'}, {ruleId:'502', name:'上年总收入：大于2亿'}
	],
		fields:["ruleId","name"]}),
	title : '匹配规则',
	columnLines : true,
	initComponent : function () {
		Ext.apply(this, {
			columns : [new Ext.grid.column.RowNumberer({
					text : '#',
					align : 'center',
					width : 30
				}),
				{header : '规则编号', dataIndex : 'ruleId', flex : 1, align : 'center'},
				{header : '规则描述', dataIndex : 'name', flex : 1, align : 'center'}
				]
		});
		this.callParent(arguments);
	}
	
});
 