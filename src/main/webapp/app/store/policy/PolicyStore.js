Ext.define('plat.store.policy.PolicyStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.policy.PolicyModel',
    storeId : 'policystore',
    init : function () {
    	console.log('PolicyStore was initialized!!!');
    },
	pageSize : 30,
	autoSync : true,
    proxy: {
        type: 'ajax',
        url : 'policy/queryByParams',
        actionMethods: {  
    		read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'data',
            idProperty: 'id'
        },
        writer: {
			type : "json",  
		    encode : true,  
		    root : "data"
		},
        api:{  
		    read:'policy/queryByParams',  
		    create:'policy/addOrUpdate',  
		    destroy:'policy/delete',  
		    update:'policy/addOrUpdate'  
  		}
    }
});
