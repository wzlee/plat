Ext.define('plat.store.mall.MallCategoryStore', {
    extend: 'Ext.data.TreeStore',
    model:'plat.model.mall.MallCategoryModel',
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST',
        	destroy: 'POST',
        	update: 'POST'
        },
        extraParams: {
            hide: null
        },
        api:{
		    read:'mall/findAll',  
		    destroy:'mall/delete', 
		    update:'mall/update'
      		},
  		reader:{  
      		type: 'json',
			root: '',
        	messageProperty:"message"  
      		}, 
		writer:{  
		    type: 'json',
		    encode:true,
		    root: 'data',  
		    allowSingle:true
		}
    },
    autoLoad: false,
    sorters: [{property: 'id', direction: 'ASC'}]
});


