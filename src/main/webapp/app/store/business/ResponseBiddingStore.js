Ext.define('plat.store.business.ResponseBiddingStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.ResponseBiddingModel',
    storeId:'responsebiddingstore',
    xtype : 'responsebiddingstore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },        
        api:{  
		    read:'bidding/findResponseBidding',	    
		    destroy:'bidding/delete',  
		    update:'bidding/update'  
      	},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message" 
      	}, 
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"biddingServiceDetail" 
		}
    },
    folderSort: true,
    sorters: [{property: 'responseTime', direction: 'DESC'}]
});