Ext.define('plat.store.mobileapp.AppMessageStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.mobileapp.AppMessageModel',
    storeId : 'appmessagestore',
    init : function () {
    	console.log('AppMessageStore was initialized!!!');
    },
	pageSize : 30,
	autoSync : true,
	autoLoad : false,
    proxy: {
        type: 'ajax',
        url : 'mobileApp/query',
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
		    read:'mobileApp/query',  
		    create:'mobileApp/message',  
		    destroy:'mobileApp/remove'
  		}
    }
});
