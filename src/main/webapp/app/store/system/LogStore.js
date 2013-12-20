Ext.define('plat.store.system.LogStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.system.Log',
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'system/logload',  
		    create:'system/logadd',  
		    destroy:'system/logdelete',  
		    update:'system/logedit'  
      		},  
      	reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message"  
      		},    
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"data",  
		    allowSingle:false  
		}
    },
    folderSort: true,
    sorters: [{property: 'id', direction: 'ASC'}]
});
