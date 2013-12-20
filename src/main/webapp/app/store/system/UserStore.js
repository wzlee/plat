Ext.define('plat.store.system.UserStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.system.User',
    groupField: 'groupname',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'system/userload',  
		    create:'system/useradd',  
		    destroy:'system/userdelete',  
		    update:'system/useredit'  
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
