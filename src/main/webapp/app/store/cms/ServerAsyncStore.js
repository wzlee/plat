Ext.define('plat.store.cms.ServerAsyncStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'widget.serverAsyncStore',
    model:'plat.model.cms.ServerAsyncModel',
    folderSort: false,
    autoLoad: false,
    autoSync: false,
    sorters: [{
        property: 'id',
        direction: 'ASC'
    }],
    proxy: {
        type: 'ajax',
        url: 'category/findCategoryParent',
        reader: {
            type: 'json',
            root: ''
        }
    }
});
