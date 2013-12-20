Ext.define('plat.store.enteruser.DisableUserStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.enteruser.EnterModel',
    storeId:'disableuserstore',
    xtype : 'disableuserstore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'user/query',  
		    create:'user/add',  
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