Ext.define('plat.store.enteruser.EnterUserStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.enteruser.EnterModel',
    storeId:'enteruserstore',
    xtype : 'enteruserstore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },        
        api:{  
		    read:'user/query',	    
		    destroy:'user/delete',  
		    update:'user/update'  
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
    sorters: [{property: 'regTime', direction: 'DESC'}]
});