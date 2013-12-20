Ext.define('plat.store.business.OrderInfoStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.OrderInfoModel',
    storeId:'orderinfostore',
    xtype : 'orderinfostore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },        
        api:{  
		    read:'order/findOrderInfo',	    
		    destroy:'order/delete',  
		    update:'order/update'  
      	},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message" 
      	}, 
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"orderInfo" 
		}
    },
    folderSort: true,
    sorters: [{property: 'processTime', direction: 'DESC'}]
});