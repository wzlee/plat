Ext.define('plat.store.mall.MallBannerCategoryStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.mall.ChannelModel',
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST',
        	destroy: 'POST',
        	update: 'POST'
            },
        api:{
		    read:'mall/findBannerCategory',  
		    destroy:'mall/delete', 
		    update:'mall/updateBannerNum'
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


