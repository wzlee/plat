/**
 * 企业用户Store xuwf 2013-8-22
 */
Ext.define('plat.store.enteruser.QEnterUserStore', {
    extend: 'Ext.data.Store',
    model:'plat.model.enteruser.EnterModel',
    storeId:'qenteruserstore',
//    groupField: 'categoryName',
    pageSize : 30,
    proxy: {
        type: 'ajax',
     	actionMethods: {  
        	read: 'POST'
            },
        api:{  
		    read:'user/queryMultiple',  
		    create:'user/add',  
		    destroy:'user/delete',  
		    update:'user/update'  
      		},  
		reader:{  
      		type: 'json',
			root: 'data',
        	messageProperty:"message"  
      		}, 
		writer:{  
		    type:"json",  
		    encode:true,  
		    root:"enterprises",  
		    allowSingle:false  
		}
    },
//    autoSync:true,	//调用api其他方法
    folderSort: true,
    sorters: [{property: 'regTime', direction: 'DESC'}]
});