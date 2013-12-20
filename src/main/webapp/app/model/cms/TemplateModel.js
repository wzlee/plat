Ext.define('plat.model.cms.TemplateModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'mtid', type: 'int'},//模版ID
    	{name:'mtcode', type: 'string'},//模版编码
    	{name:'mtname', type: 'string'},//模版名称
    	{name:'mtdesc', type: 'string'}//模版描述
    ]
});
