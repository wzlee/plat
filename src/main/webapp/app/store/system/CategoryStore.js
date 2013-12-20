Ext.define('plat.store.system.CategoryStore', {
    extend: 'Ext.data.TreeStore',
    model:'plat.model.system.CategoryModel',

    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST',
        	destroy: 'POST',
        	update: 'POST'
            },
        api:{
		    read:'category/findAll',  
		    destroy:'category/delete', 
		    update:'category/update'
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
    autoSync:true,
    folderSort: true,
    autoLoad: false,
    sorters: [{property: 'id', direction: 'ASC'}]
});
