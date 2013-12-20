Ext.define('plat.store.enteruser.ApprUserStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.enteruser.ApprUserModel',
    storeId:'appruserstore',
    xtype : 'appruserstore',
    pageSize : 30,
    
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'ucenter/approvelist' 
      		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message"  
      		}
    },
    folderSort: true,
    sorters: [{property: 'applyTime', direction: 'DESC'}]
});