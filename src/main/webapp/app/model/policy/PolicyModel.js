Ext.define('plat.model.policy.PolicyModel', {
	extend : 'Ext.data.Model',
	fields: ['id', 'title', 'text', 'uploadFile', 'fileType',
		'description', 'source', 'time', 'pv',
		'policyCategory.id', 'policyCategory.text', 
		'policyCategory.description', 'policyCategory.time',
		'reportingId',
		'qualifications','material','timeLimit','process','validity','top'
	]
});
