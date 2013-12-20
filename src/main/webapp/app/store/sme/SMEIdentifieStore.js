Ext.define('plat.store.sme.SMEIdentifieStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.sme.SMEIdentifieModel',
    storeId : 'smeIdentifieStore',
    
	pageSize : 30,
	autoSync : true,
    proxy: {
        type: 'ajax',
        url : 'sme/queryIdentifie',
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
		    read:'sme/queryIdentifie',  
		    create:'sme/addOrUpdate',  
		    destroy:'sme/delete',  
		    update:'sme/update'  
  		}
    }
});
