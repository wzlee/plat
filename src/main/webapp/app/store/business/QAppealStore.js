Ext.define('plat.store.business.QAppealStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.AppealModel',
    storeId:'qappealstore',
    xtype : 'qappealstore',
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
    sorters: [{property: 'processTime', direction: 'DESC'}]
});