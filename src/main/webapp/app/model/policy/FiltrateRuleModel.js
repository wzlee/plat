Ext.define('plat.model.policy.FiltrateRuleModel', {
	extend : 'Ext.data.Model',
	fields: [
	'id','conditionField','ruleId',
	'policy.id',
	'policy.title'
	]
});
