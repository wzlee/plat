Ext.define('plat.store.business.BiddingAppealStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.AppealModel',
    storeId:'biddingappealstore',
    xtype : 'biddingappealstore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },    
        api:{  
		    read:'bidding/findBiddingAppeal'
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