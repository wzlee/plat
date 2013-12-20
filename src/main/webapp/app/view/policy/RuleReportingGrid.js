Ext.define('plat.view.policy.RuleReportingGrid', {
			extend : 'Ext.grid.Panel',
			xtype : 'rulereportinggrid',
			title : '匹配政策列表',
			closable : true,
			closeAction : 'hide',
			layout : 'fit',
			store: 'policy.PolicyReportingStore',
			selModel : new Ext.selection.CheckboxModel(),
			tbar : [{
						text : '保存',
						action : 'save'
					}, {
						xtype : 'triggerfield',
						name : 'ruleId',
						hidden:true
					}],

			columns : [
					{xtype:'rownumberer',text:'序号',align:'center',width:80},
					{
						header : '',
						dataIndex : 'id',
						align : 'center',
						hidden:true
					},
					{
						header : '标题',
						dataIndex : 'title',
						align : 'center',
						flex:1
					}, {
						header : '类别',
						dataIndex : 'policyCategory.text',
						align : 'center',
						flex:1
					} ],

			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'policy.PolicyReportingStore', // same store GridPanel is using
						dock : 'bottom',
						displayInfo : true
					}]
});
