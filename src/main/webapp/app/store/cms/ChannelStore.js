Ext.define('plat.store.cms.ChannelStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'widget.channelStore',
    model:'plat.model.cms.ChannelModel',
    folderSort: false,
    autoLoad: false,
    autoSync: false,
    sorters: [{
        property: 'id',
        direction: 'ASC'
    }],
    proxy: {
        type: 'ajax',
        url: 'cms/findAllChannel',
        reader: {
            type: 'json',
            root: ''
        }
    }
});
