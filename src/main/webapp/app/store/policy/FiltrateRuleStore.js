Ext.define('plat.store.policy.FiltrateRuleStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.policy.FiltrateRuleModel',
    storeId : 'filtraterulestore',
   
	pageSize : 30,
	autoSync : true,
    proxy: {
        type: 'ajax',
        url : 'policy/findFiltrateRule',
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
		    read:'policy/findFiltrateRule',  
		    create:'policy/add'
  		}
    }
});
