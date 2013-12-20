Ext.define('plat.store.enteruser.ApprPersonalUserStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.enteruser.ApprPersonalUserModel',
    storeId:'apprpersonaluserstore',
    xtype : 'apprpersonaluserstore',
    pageSize : 30,
    
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'ucenter/userapprovelist' 
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