Ext.define('plat.store.enteruser.EnterStaffStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.enteruser.EnterStaffModel',
    storeId:'enterstaffstore',
    xtype : 'enterstaffstore',
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'staff/query',
//		    create:'staff/save',  
//		    destroy:'staff/delete',  
		    update:'staff/update'  
      		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message"  
      		}, 
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"staff"
//		    allowSingle:true  
		}
    },
    folderSort: true,
    sorters: [{property: 'assignTime', direction: 'DESC'}]
});