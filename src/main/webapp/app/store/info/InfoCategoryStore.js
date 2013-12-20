Ext.define('plat.store.info.InfoCategoryStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.info.InfoCategoryModel',
    storeId : 'infocategorystore',
    init : function () {
//    	console.log('PolicyCategoryStore was initialized!!!');
    },
    autoLoad : false,
	pageSize : 30,
//	autoSync : true,
    proxy: {
        type: 'ajax',
        url : 'info/queryCategorypage',
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
		    read:'info/queryCategorypage',  
		    create:'info/addOrUpdateCategory',  
		    destroy:'info/deleteCategory',  
		    update:'info/addOrUpdateCategory'  
  		}
    }
});
