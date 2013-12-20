Ext.define('plat.store.flat.FlatUserStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.flat.FlatManagerModel',
    storeId : 'flatuserstore',
    init : function () {
    	console.log('FlatUserStore was initialized!!!');
    },
	pageSize : 30,
	autoSync : false,
    proxy: {
        type: 'ajax',
//        url : 'manage/findByFlatName',
        url : 'manage/findByFlatId',
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
//		    read:'manage/findByFlatName', 
		    read:'manage/findByFlatId', 
		    create:'manage/save',  
		    update:'manage/update'  
  		}
    }
});
