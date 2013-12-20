Ext.define('plat.model.policy.FinacialReportingModel', {
	extend : 'Ext.data.Model',

	fields : [{
					name : 'id',
				type : 'int'
			}, {
				name : 'qualification',
				type : 'String'
			}, {
				name : 'materials',
				type : 'String'
			}, {
				name : 'approvalProcess',
				type : 'String'
			}, {
				name : 'processTimeInterval',
				type : 'String'
			}, {
				name : 'validTime',
				type : 'String'
			}, {
				name : 'webProcessTimeIntervalBegin',
				type : 'String'
			}, {
				name : 'webProcessTimeIntervalEnd',
				type : 'String'
			}, {
				name : 'paperProcessTimeIntervalBegin',
				type : 'String'
			}, {
				name : 'paperProcessTimeIntervalEnd',
				type : 'String'
			}, {
				name : 'addTime',
				type : 'String'
			}, {
				name : 'policyCategory.id',
				type : 'int'
			}, {
				name : 'policyCategory.text',
				type : 'String'
			}, {
				name : 'policyCategory.description',
				type : 'String'
			}, {
				name : 'policyCategory.time',
				type : 'int'
			}, {
				name : 'policy.id',
				type : 'int'
			}, {
				name : 'policy.title',
				type : 'String'
		}]
});
