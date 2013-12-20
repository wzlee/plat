Ext.define('plat.store.policy.AddrReportPolicyStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.policy.PolicyModel',
    xtype : 'addrreportpolicystore',
    
	pageSize : 30,
	autoSync : true,
    proxy: {
        type: 'ajax',
        url : 'policy/findPolicyNoReport',
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
		    read:'policy/findPolicyNoReport'
  		}
    }
});
