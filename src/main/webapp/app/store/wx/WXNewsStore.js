Ext.define('plat.store.wx.WXNewsStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.wx.WXNewsModel',
    storeId : 'wxnewsstore',
    init : function () {
    	console.log('WXNewsStore was initialized!!!');
    },
	pageSize : 30,
	autoSync : true,
    proxy: {
        type: 'ajax',
        url : 'wxnews/query',
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
		    read:'wxnews/query',  
		    create:'wxnews/addOrUpdate',  
		    destroy:'wxnews/delete',  
		    update:'wxnews/addOrUpdate'  
  		}
    }
});
