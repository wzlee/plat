Ext.define('plat.store.enteruser.EnterpriseStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.enteruser.EnterpriseModel',
    storeId:'enterprisestore',
    xtype : 'enterprisestore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },        
        api:{  
		    read:'enter/query'
		    //update:'enter/update'  
      		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message" 
      		}, 
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"user" 
		}
    },
    folderSort: true,
    sorters: [{property: 'id', direction: 'DESC'}]
});