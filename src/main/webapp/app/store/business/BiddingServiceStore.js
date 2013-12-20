Ext.define('plat.store.business.BiddingServiceStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.BiddingServiceModel',
    storeId:'biddingservicestore',
    xtype : 'biddingservicestore',
    pageSize : 30,
//    groupField: 'category.text',
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },        
        api:{  
		    read:'bidding/query',	    
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
		    root:"biddingservice" 
		}
    },
    folderSort: true,
    sorters: [{property: 'createTime', direction: 'DESC'}]
});