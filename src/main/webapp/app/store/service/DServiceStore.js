Ext.define('plat.store.service.DServiceStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.service.ServiceModel',
    
    storeId:'dservicestore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },
        extraParams: {
            queryStatus: "6,2"
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
    folderSort: true,
    sorters: [{property: 'registerTime', direction: 'DESC'}]
});
