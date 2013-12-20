Ext.define('plat.store.business.AppealStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.AppealModel',
    storeId:'appealstore',
    xtype : 'appealstore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },    
        extraParams: {
            orderStatus: 9
        },
        api:{  
		    read:'order/findAppeal',	    
		    destroy:'order/deleteAppeal',  
		    update:'order/updateAppeal'  
      	},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message" 
      	}, 
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"appeal" 
		}
    },
    folderSort: true,
    sorters: [{property: 'appealTime', direction: 'DESC'}],
    listeners: {
        beforeload: function(store){
            var params = store.getProxy().extraParams;
            if (params.query) {
                delete params.orderStatus;
            } else {
                params.orderStatus = 9;
            }
        }
    }
});