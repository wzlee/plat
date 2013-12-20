Ext.define('plat.store.service.NServiceStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.service.ServiceModel',
    storeId:'nservicestore',
    xtype : 'nservicestore',
    pageSize : 30,
//    groupField: 'cid',
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },
        extraParams: {
            queryStatus: "1,2"
        },
        api:{  
		    read:'service/query',  
		    create:'service/save',  
		    destroy:'service/delete',  
		    update:'service/update'  
  		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message"  
  		}, 
		writer:{  
		    type:"json",  
		    encode:true,
		    root:"service",  
		    allowSingle:true  
		}
    },
//    autoSync:true,
    folderSort: true,
    sorters: [{property: 'registerTime', direction: 'DESC'}],
    listeners: {
        beforeload: function(store){
            var params = store.getProxy().extraParams;
            if (params.query) {
                delete params.queryStatus;
            } else {
                params.queryStatus = "1,2";
            }
        }
    }
});