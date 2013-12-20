Ext.define('plat.store.policy.FinacialReportingStore', {
	extend : 'Ext.data.Store',
	model :'plat.model.policy.FinacialReportingModel',
	xtype :'finacialreportingstore',
    storeId : 'finacialreportingstore',
	autoSync : true,
	pageSize : 30,
    proxy: {
        type: 'ajax',
        actionMethods: {  
    		read: 'POST'
        },
        extraParams: {
           title:'',
           pcid:null
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
		    read:'policy/getAllFinacial',
		    create:'policy/addReport',  
		    destroy:'policy/deleteReporting',  
		    update:'policy/addReport'  
  		}
    }
});
