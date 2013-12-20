Ext.define('plat.store.wx.AutoMessageStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.wx.AutoMessageModel',
    storeId : 'automessagestore',
    init : function () {
    	console.log('ArticleInfoStore was initialized!!!');
    },
	pageSize : 30,
	autoSync : true,
    proxy: {
        type: 'ajax',
        url : 'automessage/query',
        actionMethods: {  
    		read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'data',
            idProperty: 'id'
        },
        writer: {
			type : "json",  
		    encode : true,  
		    root : "data"
		},
        api:{  
		    read:'automessage/query',  
		    create:'automessage/addOrUpdate',  
		    destroy:'automessage/delete',  
		    update:'automessage/addOrUpdate'  
  		}
    }
});
