Ext.define('plat.store.mall.MallAddServiceStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.service.ServiceModel',
    
    storeId:'malladdservicestore',
    proxy: {
        type: 'ajax',
     	actionMethods: {  
    		read: 'POST'
        },
        api:{  
		    read:'mall/findAddService',  
		    create:'mall/add',  
		    destroy:'mall/delete',  
		    update:'mall/update'  
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
