Ext.define('plat.store.info.InfoGroupStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.info.InfoGroupModel',
    storeId:'infogroupstore',
    xtype : 'infogroupstore',
    pageSize : 30,
    
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'info/infogrouplist' 
      		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message"  
      		}
    },
    folderSort: true,
    sorters: [{property: 'sendTime', direction: 'DESC'}]
});