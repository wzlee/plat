Ext.define('plat.store.info.InfoStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.info.InfoModel',
    storeId:'infostore',
    xtype : 'infostore',
    pageSize : 30,
    
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'info/infolist' 
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