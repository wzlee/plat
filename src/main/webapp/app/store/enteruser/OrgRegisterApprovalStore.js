Ext.define('plat.store.enteruser.OrgRegisterApprovalStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.enteruser.OrgRegisterApprovalModel',
    
    xtype : 'orgregisterapprovalstore',
    pageSize : 30,
    
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'user/orgRegisterlist' 
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