Ext.define('plat.store.cms.ServiceAgencyStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.cms.ServiceAgencyModel',
    storeId:'serviceagencystore',
    xtype : 'serviceagencystore',
    pageSize : 30,
    autoLoad : false,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },        
        api:{  
		    read:'enter/queryServiceAgency'    
      		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message" 
      		}
    }/*,
    folderSort: true,
    sorters: [{property: 'regTime', direction: 'DESC'}]*/
});