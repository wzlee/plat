Ext.define('plat.store.business.EvaluationStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.business.EvaluationModel',
    storeId:'evaluationstore',
    xtype : 'evaluationstore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
        },        
        api:{  
		    read:'order/findEval',	    
		    destroy:'order/delete',  
		    update:'order/update'  
      	},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message" 
      	}, 
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"orderEvaluation" 
		}
    },
    folderSort: true,
    sorters: [{property: 'dtime', direction: 'DESC'}]
});