Ext.define('plat.store.business.OrderStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.OrderModel',
    storeId:'orderstore',
    xtype : 'orderstore',
    pageSize : 30,
//    groupField: 'service.category.text',
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },        
        api:{  
		    read:'order/find',	    
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
		    root:"goodsorder" 
		}
    },
    folderSort: true,
    sorters: [{property: 'createTime', direction: 'DESC'}]
});