Ext.define('plat.store.service.RServiceStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.service.ServiceModel',
    
    storeId:'rservicestore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        extraParams: {
            queryStatus: "5"
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
    //autoSync:true,
    folderSort: true,
    sorters: [{property: 'registerTime', direction: 'DESC'}],
    listeners: {
        beforeload: function(store){
            var params = store.getProxy().extraParams;
            if (params.query) {
                delete params.queryStatus;
            } else {
                params.queryStatus = "5";
            }
        }
    }
});
