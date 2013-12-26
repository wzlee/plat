Ext.define('plat.store.wx.UserChangeStore', {
	extend: 'Ext.data.Store',
	model:'plat.model.wx.UserChangeModel',
	pageSize: 25,
	folderSort: true,
	autoLoad: false,
	autoSync: false,
    proxy: {
        type: 'ajax',
        url : 'concernusers/getUserChangeByOpenid',
        reader: {
            type: 'json',
            root: 'data',
            idProperty: 'id'
        }
    }
});
