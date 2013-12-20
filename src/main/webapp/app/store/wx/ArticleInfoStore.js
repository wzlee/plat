Ext.define('plat.store.wx.ArticleInfoStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.wx.ArticleInfoModel',
    storeId : 'articleinfostore',
    init : function () {
    	console.log('ArticleInfoStore was initialized!!!');
    },
	pageSize : 30,
	autoSync : true,
    proxy: {
        type: 'ajax',
        url : 'articleinfo/query',
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
		    read:'articleinfo/query',  
		    create:'articleinfo/addOrUpdate',  
		    destroy:'articleinfo/delete',  
		    update:'articleinfo/addOrUpdate'  
  		}
    }
});
