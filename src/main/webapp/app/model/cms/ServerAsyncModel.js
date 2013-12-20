Ext.define('plat.model.cms.ServerAsyncModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name: 'id', type: 'int'},
    	{name: 'code', type: 'string'},
    	{name: 'pid', type: 'int'},
    	{name: 'text', type: 'string'},
    	{name: 'picture', type: 'string'},
    	{name: 'description', type: 'string'},
    	{name: 'clazz', type: 'string'},
    	{name: 'leaf', type: 'boolean'}
    ]
});
