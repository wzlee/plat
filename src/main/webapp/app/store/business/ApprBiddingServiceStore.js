Ext.define('plat.store.business.ApprBiddingServiceStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.BiddingServiceModel',
    storeId:'apprbiddingservicestore',
    xtype : 'apprbiddingservicestore',
    pageSize : 30,
//    groupField: 'category.text',
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        }, 
        extraParams: {
            queryStatus: "2"
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
    sorters: [{property: 'createTime', direction: 'DESC'}],
    listeners: {
        beforeload: function(store){
            var params = store.getProxy().extraParams;
            if (params.query) {
                delete params.queryStatus;
            } else {
                params.queryStatus = "2";
            }
        }
    }
});