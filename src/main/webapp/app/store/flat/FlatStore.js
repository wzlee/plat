Ext.define('plat.store.flat.FlatStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.flat.FlatModel',
    storeId : 'flatstore',
    init : function () {
    	console.log('FlatStore was initialized!!!');
    },
	pageSize : 30,
	autoSync : false,
    proxy: {
        type: 'ajax',
        url : 'flat/query',
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
		    read:'flat/query',  
		    create:'flat/addOrUpdate',  
		    update:'flat/addOrUpdate'  
  		}
    }
});
