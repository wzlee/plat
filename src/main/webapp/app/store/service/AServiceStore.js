Ext.define('plat.store.service.AServiceStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.service.ServiceModel',
    
    storeId:'aservicestore',
//    groupField: 'cid',
     pageSize : 30,
    proxy: {
        type: 'ajax',
        extraParams : {
			queryStatus : "2,4,7"      
        },
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'service/queryaudit', 
//		    create:'service/add',  
		    destroy:'service/audit',  
		    update:'service/audit'  
      		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty : "message"  
      		},
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"service",  
		    allowSingle:true  
		}
    },
//    autoSync:true,
    folderSort: true,
    sorters: [{property: 'registerTime', direction: 'DESC'}]
});
