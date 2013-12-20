Ext.define('plat.store.business.BiddingServiceDetailStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.BiddingServiceDetailModel',
    storeId:'biddingservicedetailstore',
    xtype : 'biddingservicedetailstore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },        
        api:{  
		    read:'bidding/findBiddingServiceDetail',	    
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
    sorters: [{property: 'processTime', direction: 'DESC'}]
});