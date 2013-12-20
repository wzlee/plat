Ext.define('plat.store.business.OrderAndBiddingAppealStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.OrderAndBiddingAppealModel',
    storeId:'orderandbiddingappealstore',
    xtype : 'orderandbiddingappealstore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },    
        api:{  
		    read:'order/qAppealList',	    
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
    sorters: [{property: 'appealTime', direction: 'DESC'}]
});