Ext.define('plat.store.wx.WeiXinUserStore', {
	extend: 'Ext.data.Store',
	model:'plat.model.wx.WeiXinUserModel',
	pageSize: 25,
	folderSort: true,
	autoLoad: false,
	autoSync: false,
	groupField: 'wxUserToken',
    proxy: {
        type: 'ajax',
        url : 'concernusers/getAllBindUser',
        reader: {
            type: 'json',
            root: 'data',
            idProperty: 'id'
        }
    }
});
