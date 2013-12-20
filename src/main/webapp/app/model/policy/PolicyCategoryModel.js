Ext.define('plat.model.policy.PolicyCategoryModel', {
	extend : 'Ext.data.Model',
	fields: ['description', 'time',
		'type', 'pid', 'children',
		{name : 'leaf', type : 'boolean'},
		{name : 'id', type : 'int'},
		{name : 'text', type : 'string'}
	]
});
