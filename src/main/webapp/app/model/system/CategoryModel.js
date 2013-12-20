Ext.define('plat.model.system.CategoryModel', {
    extend: 'Ext.data.Model',
//    fields: ['id','code','text','pid','leaf','description','clazz']
    fields: [{
        name: 'id',
        type: 'int'
    }, {
        name: 'code',
        type: 'string'
    }, {
        name: 'text',
        type: 'string'
    }, {
        name: 'pid',
        type: 'string'
    }, {
        name: 'description',
        type: 'string'
    }, {
        name: 'leaf',
        type: 'boolean'
    }, {
        name: 'clazz',
        type: 'string'
    }]
});