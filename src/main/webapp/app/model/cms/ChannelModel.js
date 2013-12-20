Ext.define('plat.model.cms.ChannelModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name: 'id', type: 'int'},
    	{name: 'pid', type: 'int'},
    	{name: 'cname', type: 'string'},
    	{name: 'leaf', type: 'boolean'},
    	{name: 'ccode', type: 'string'},
    	{name: 'mtcode', type: 'string'},
    	{name: 'cindex', type: 'string'},
    	{name: 'chttp', type: 'string'},
    	{name: 'linktype', type: 'string'},
    	{name: 'isChannel', type: 'boolean'},
    	{name: 'mnumber', type: 'int'},
    	{name: 'cdesc', type: 'string'}
    ]
});
