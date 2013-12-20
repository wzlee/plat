Ext.define('plat.store.cms.NewsStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.cms.NewsModel',
    storeId : 'newsstore',
	groupField: 'cid',
    init : function () {
    	console.log('NewsStore was initialized!!!');
    },
	pageSize : 30,
	autoSync : true,
    proxy: {
        type: 'ajax',
        url : 'news/query',
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
		    read:'news/query',  
		    create:'news/add',  
		    destroy:'news/delete',  
		    update:'news/update'  
  		}
    }
});
