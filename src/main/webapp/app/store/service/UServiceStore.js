Ext.define('plat.store.service.UServiceStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.service.ServiceModel',
    
    storeId:'uservicestore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
    		read: 'POST'
        },
        extraParams: {
            queryStatus: "3,4,7"
        },
        api:{  
		    read:'service/query',  
		    create:'service/add',  
		    destroy:'service/delete',  
		    update:'service/update'  
      		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message"  
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
