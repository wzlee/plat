Ext.define('plat.store.mall.MallAdvertisementStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.mall.AdvertisementModel',
    
    storeId:'malladvertisementstore',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
    		read: 'POST'
        },
        api:{  
		    read:'ad/findAll',  
		    create:'ad/add',  
		    destroy:'ad/deleteAd',  
		    update:'ad/update'  
      		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message"  
      		}, 
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"",  
		    allowSingle:true  
		}
    },
//    autoSync:true,
    folderSort: true,
    sorters: [{property: 'id', direction: 'DESC'}]
});
